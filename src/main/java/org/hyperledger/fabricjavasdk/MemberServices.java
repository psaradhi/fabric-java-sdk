package org.hyperledger.fabricjavasdk;

import org.hyperledger.fabricjavasdk.exception.EnrollmentException;
import org.hyperledger.fabricjavasdk.exception.RegistrationException;

public interface MemberServices {

    /**
     * Get the security level
     * @returns The security level
     */
    int getSecurityLevel();

    /**
     * Set the security level
     * @params securityLevel The security level
     */
    void setSecurityLevel(int securityLevel);

    /**
     * Get the hash algorithm
     * @returns The security level
     */
    String getHashAlgorithm();

    /**
     * Set the security level
     * @params securityLevel The security level
     */
    void setHashAlgorithm(String hashAlgorithm);

    /**
     * Register the member and return an enrollment secret.
     * @param req Registration request with the following fields: name, role
     * @param registrar The identity of the registar (i.e. who is performing the registration)
     * @return enrollment secret
     */
    String register(RegistrationRequest req, Member registrar) throws RegistrationException;

    /**
     * Enroll the member and return an opaque member object
     * @param req Enrollment request with the following fields: name, enrollmentSecret
     * 
     * @return enrollment details 
     */
    Enrollment enroll(EnrollmentRequest req) throws EnrollmentException;

    /**
     * Get an array of transaction certificates (tcerts).
     * @param req A GetTCertBatchRequest
     */
    void getTCertBatch(GetTCertBatchRequest req);

}
