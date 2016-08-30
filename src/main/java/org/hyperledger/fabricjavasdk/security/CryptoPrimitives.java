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
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.agreement.ECDHBasicAgreement;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.ec.ECDecryptor;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.engines.DESEngine;
import org.bouncycastle.crypto.engines.IESEngine;
import org.bouncycastle.crypto.generators.KDF2BytesGenerator;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.DSAKCalculator;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Arrays;

import io.netty.util.internal.StringUtil;

public class CryptoPrimitives {

	private String algorithm; 
	private int securityLevel;	
	private String curveName;
//	private String suite;
	
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

	public KeyPair ecdsaKeyGen() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
		return generateKey("ECDSA", this.curveName);
	}
	
	public KeyPair eciesKeyGen() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
		return generateKey("ECEIS", this.curveName);
	}
	
	private KeyPair generateKey(String encryptionName, String curveName) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
		ECGenParameterSpec ecGenSpec = new ECGenParameterSpec(curveName);		
		KeyPairGenerator g = KeyPairGenerator.getInstance(encryptionName, BouncyCastleProvider.PROVIDER_NAME);
		g.initialize(ecGenSpec, new SecureRandom());		
		KeyPair pair = g.generateKeyPair();		
		return pair;
	}
	
	public byte[] eciesDecrypt(KeyPair keyPair, byte[] data) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {		
//		return decrypt(keyPair, data, "ECIES");
		IESCipher cipher = new IESCipher(new IESEngine(new ECDHBasicAgreement(), 
				new KDF2BytesGenerator(new SHA3Digest()), new HMac(new SHA256Digest()),
	            new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()))));

	    cipher.engineInit(Cipher.DECRYPT_MODE, keyPair.getPrivate(), new SecureRandom());
	    return cipher.engineDoFinal(data, 0, data.length);
		
	}
	
//	private byte[] decrypt(KeyPair keyPair, byte[] data, String encryptionName) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {		
//		Cipher cipher = Cipher.getInstance(encryptionName, BouncyCastleProvider.PROVIDER_NAME);		
//		cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
//		
//		cipher.update(data);
//		return cipher.doFinal();
//	}
	
	public BigInteger[] ecdsaSign(PrivateKey privateKey, byte[] data) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
//		ECDSASigner signer = new ECDSASigner (new HMacDSAKCalculator (new SHA256Digest ()));
		ECDSASigner signer = new ECDSASigner ();
		signer.init (true, ECUtil.generatePrivateKeyParameter(privateKey));
		return signer.generateSignature (data);	
	}
	
	public PrivateKey ecdsaKeyFromPrivate(byte[] key) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException  {
		
		return KeyFactory.getInstance("ECDSA").generatePrivate(
                new ECPrivateKeySpec(new BigInteger(key),
                		ECNamedCurveTable.getParameterSpec(this.curveName)
                )
        );
	}
	
	private void init() {
		if (securityLevel != 256 && securityLevel != 384) {
            throw new RuntimeException("Illegal level: " + securityLevel + " must be either 256 or 384");
        }
		if (StringUtil.isNullOrEmpty(this.algorithm) || ! (this.algorithm.equalsIgnoreCase("SHA2") || this.algorithm.equalsIgnoreCase("SHA3"))) {
            throw new RuntimeException("Illegal Hash function family: " + this.algorithm + " - must be either SHA2 or SHA3");
		}
        

//        this.suite = this.algorithm.toLowerCase() + '-' + this.securityLevel;
        if (this.securityLevel == 256) {
            this.curveName = "secp256r1";
        } else if (this.securityLevel == 384) {
            this.curveName = "secp384r1";
        }

//        switch (this.suite) {
//            case "sha3-256":
//                debug("Using sha3-256");
//                this.hashFunction = sha3_256;
//                this.hashFunctionKeyDerivation = hashPrimitives.hash_sha3_256;
//                this.hashOutputSize = 32;
//                break;
//            case "sha3-384":
//                debug("Using sha3-384");
//                this.hashFunction = sha3_384;
//                this.hashFunctionKeyDerivation = hashPrimitives.hash_sha3_384;
//                this.hashOutputSize = 48;
//                break;
//            case "sha2-256":
//                debug("Using sha2-256");
//                this.hashFunction = hashPrimitives.sha2_256;
//                this.hashFunctionKeyDerivation = hashPrimitives.hash_sha2_256;
//                this.hashOutputSize = 32;
//                break;
//        }

//        switch (this.securityLevel) {
//            case 256:
//                this.ecdsaCurve = elliptic.curves['p256'];
//                break;
//            case 384:
//                this.ecdsaCurve = elliptic.curves['p384'];
//                break;
//        }

    }
	
}
