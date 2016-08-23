package org.hyperledger.fabricjavasdk;
/**
 * Transaction certificate.
 */
public class TCert extends Certificate {
    public TCert(Object publicKey, Object privateKey) {
        super(publicKey, privateKey, PrivacyLevel.Anonymous);
    }
}
