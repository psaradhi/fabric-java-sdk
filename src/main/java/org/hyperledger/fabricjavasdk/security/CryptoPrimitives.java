package org.hyperledger.fabricjavasdk.security;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.generators.HKDFBytesGenerator;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.HKDFParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

import io.netty.util.internal.StringUtil;

public class CryptoPrimitives {

	private String algorithm;
	private int securityLevel;
	private String curveName;
	// private String suite;

	private static final String SECURITY_PROVIDER = "BC";
	private static final String ASYMMETRIC_KEY_TYPE = "EC";
	private static final String KEY_AGREEMENT_ALGORITHM = "ECDH";
	/** OpenSSL name of the NIST P-126 Elliptic Curve */
	private static final String EC_CURVE = "secp256r1";
	private static final String SYMMETRIC_KEY_TYPE = "AES";
	private static final int SYMMETRIC_KEY_BYTE_COUNT = 32;
	private static final String MAC_ALGORITHM = "HmacSHA256";
	private static final String SYMMETRIC_ALGORITHM = "AES/CFB/NoPadding";
	private static final int MAC_KEY_BYTE_COUNT = 32;
	private static final byte[] HKDF_INFO = null;
	private static final byte[] HKDF_SALT = null /*
													 * equivalent to a zeroed salt
													 * of hashLen
													 */;

	public CryptoPrimitives(String algorithm, int securityLevel) {
		this.algorithm = algorithm;
		this.securityLevel = securityLevel;
		Security.addProvider(new BouncyCastleProvider());
		this.init();
	}

	public int getSecurityLevel() {
		return securityLevel;
	}

	public void setSecurityLevel(int securityLevel) {
		this.securityLevel = securityLevel;
	}

	public String getHashAlgorithm() {
		return this.algorithm;
	}

	public void setHashAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public KeyPair ecdsaKeyGen()
			throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
		return generateKey("ECDSA", this.curveName);
	}

	public KeyPair eciesKeyGen()
			throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
		return generateKey("ECEIS", this.curveName);
	}

	private KeyPair generateKey(String encryptionName, String curveName)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
		ECGenParameterSpec ecGenSpec = new ECGenParameterSpec(curveName);
		KeyPairGenerator g = KeyPairGenerator.getInstance(encryptionName, BouncyCastleProvider.PROVIDER_NAME);
		g.initialize(ecGenSpec, new SecureRandom());
		KeyPair pair = g.generateKeyPair();
		return pair;
	}

	public byte[] eciesDecrypt(KeyPair keyPair, byte[] data) throws NoSuchAlgorithmException, NoSuchProviderException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		// split the key into various parts
		byte[] ephemeralPublicKeyBytes = Arrays.copyOfRange(data, 0, 65);
		byte[] encryptedMessage = Arrays.copyOfRange(data, 65, 93);
		byte[] tag = Arrays.copyOfRange(data, 93, 125);

		// Parsing public key.
		ECParameterSpec asymmetricKeyParams = generateECParameterSpec();
		KeyFactory asymmetricKeyFactory = KeyFactory.getInstance(ASYMMETRIC_KEY_TYPE, SECURITY_PROVIDER);
		try {
			PublicKey ephemeralPublicKey = asymmetricKeyFactory.generatePublic(new ECPublicKeySpec(
					asymmetricKeyParams.getCurve().decodePoint(ephemeralPublicKeyBytes), asymmetricKeyParams));

			// Deriving shared secret.
			KeyAgreement keyAgreement = KeyAgreement.getInstance(KEY_AGREEMENT_ALGORITHM, SECURITY_PROVIDER);
			keyAgreement.init(keyPair.getPrivate());
			keyAgreement.doPhase(ephemeralPublicKey, true);
			byte[] sharedSecret = keyAgreement.generateSecret();

			System.out.println("Z - sharedSecret=" + toPrintByteArray(sharedSecret));

			// Deriving encryption and mac keys.
			HKDFBytesGenerator hkdfBytesGenerator = new HKDFBytesGenerator(new SHA3Digest());

			hkdfBytesGenerator.init(new HKDFParameters(sharedSecret, HKDF_SALT, HKDF_INFO));
			byte[] encryptionKey = new byte[SYMMETRIC_KEY_BYTE_COUNT];
			hkdfBytesGenerator.generateBytes(encryptionKey, 0, SYMMETRIC_KEY_BYTE_COUNT);
			System.out.println("kE - encryptionKey=" + toPrintByteArray(encryptionKey));

			byte[] macKey = new byte[MAC_KEY_BYTE_COUNT];
			hkdfBytesGenerator.generateBytes(macKey, 0, MAC_KEY_BYTE_COUNT);
			System.out.println("kM - macKey=" + toPrintByteArray(macKey));

			// Verifying Message Authentication Code (aka mac/tag)
			byte[] expectedTag = calculateMac(macKey, encryptedMessage);
			if (!Arrays.areEqual(tag, expectedTag)) {
				throw new RuntimeException("Bad Message Authentication Code!");
			}

			// Decrypting the message.
			byte[] iv = Arrays.copyOfRange(encryptedMessage, 0, 16);
			byte[] encrypted = Arrays.copyOfRange(encryptedMessage, 16, encryptedMessage.length);
			byte[] output = aesDecrypt(encryptionKey, iv, encrypted);

			System.out.println("Original data:" + toPrintByteArray(output));

			return output;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Could not decrypt the message", e);
		}

	}

	private byte[] calculateMac(byte[] macKey, byte[] encryptedMessage)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException {
		HMac hmac = new HMac(new SHA3Digest());
		hmac.init(new KeyParameter(macKey));
		hmac.update(encryptedMessage, 0, encryptedMessage.length);
		byte[] out = new byte[MAC_KEY_BYTE_COUNT];
		hmac.doFinal(out, 0);
		return out;
	}

	private byte[] aesDecrypt(byte[] encryptionKey, byte[] iv, byte[] encryptedMessage)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		Cipher cipher = Cipher.getInstance(SYMMETRIC_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(encryptionKey, SYMMETRIC_KEY_TYPE), new IvParameterSpec(iv));
		return cipher.doFinal(encryptedMessage);

	}

	private String toPrintByteArray(byte[] data) {
		String s = "[";
		for (int i = 0; i < data.length; i++) {
			if (data[i] < 0) {
				s += data[i] & 0xFF;
			} else {
				s += data[i] & 0xFF;
			}

			s += " ";
		}
		s += "]";
		return s;
	}

	private ECNamedCurveParameterSpec generateECParameterSpec() {
		ECNamedCurveParameterSpec bcParams = ECNamedCurveTable.getParameterSpec(this.curveName);
		return bcParams;
	}

	public BigInteger[] ecdsaSign(PrivateKey privateKey, byte[] data)
			throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		// ECDSASigner signer = new ECDSASigner (new HMacDSAKCalculator (new
		// SHA256Digest ()));
		ECDSASigner signer = new ECDSASigner();
		signer.init(true, ECUtil.generatePrivateKeyParameter(privateKey));
		return signer.generateSignature(data);
	}

	public PrivateKey ecdsaKeyFromPrivate(byte[] key)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {

		return KeyFactory.getInstance("ECDSA").generatePrivate(
				new ECPrivateKeySpec(new BigInteger(key), ECNamedCurveTable.getParameterSpec(this.curveName)));
	}

	private void init() {
		if (securityLevel != 256 && securityLevel != 384) {
			throw new RuntimeException("Illegal level: " + securityLevel + " must be either 256 or 384");
		}
		if (StringUtil.isNullOrEmpty(this.algorithm)
				|| !(this.algorithm.equalsIgnoreCase("SHA2") || this.algorithm.equalsIgnoreCase("SHA3"))) {
			throw new RuntimeException(
					"Illegal Hash function family: " + this.algorithm + " - must be either SHA2 or SHA3");
		}

		// this.suite = this.algorithm.toLowerCase() + '-' + this.securityLevel;
		if (this.securityLevel == 256) {
			this.curveName = "secp256r1";
		} else if (this.securityLevel == 384) {
			this.curveName = "secp384r1";
		}

		// switch (this.suite) {
		// case "sha3-256":
		// debug("Using sha3-256");
		// this.hashFunction = sha3_256;
		// this.hashFunctionKeyDerivation = hashPrimitives.hash_sha3_256;
		// this.hashOutputSize = 32;
		// break;
		// case "sha3-384":
		// debug("Using sha3-384");
		// this.hashFunction = sha3_384;
		// this.hashFunctionKeyDerivation = hashPrimitives.hash_sha3_384;
		// this.hashOutputSize = 48;
		// break;
		// case "sha2-256":
		// debug("Using sha2-256");
		// this.hashFunction = hashPrimitives.sha2_256;
		// this.hashFunctionKeyDerivation = hashPrimitives.hash_sha2_256;
		// this.hashOutputSize = 32;
		// break;
		// }

		// switch (this.securityLevel) {
		// case 256:
		// this.ecdsaCurve = elliptic.curves['p256'];
		// break;
		// case 384:
		// this.ecdsaCurve = elliptic.curves['p384'];
		// break;
		// }

	}

}
