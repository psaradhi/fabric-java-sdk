package org.hyperledger.fabric.shim.crypto.signature;

public interface SignatureVerifier {
    boolean verify(byte[] publicKey, byte[] signature, byte[] payload);
}
