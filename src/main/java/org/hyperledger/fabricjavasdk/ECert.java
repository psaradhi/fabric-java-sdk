package org.hyperledger.fabricjavasdk;

/**
 * Enrollment certificate.
 */
public class ECert extends Certificate {

    public ECert(Object cert, Object privateKey) {
        super(cert, privateKey, PrivacyLevel.Nominal);
    }

}
