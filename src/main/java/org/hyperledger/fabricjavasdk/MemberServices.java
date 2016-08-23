
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
     * @param cb Callback of the form: {function(err,enrollmentSecret)}
     */
    void register(RegistrationRequest req, Member registrar, RegisterCallback cb);

    /**
     * Enroll the member and return an opaque member object
     * @param req Enrollment request with the following fields: name, enrollmentSecret
     * @param cb Callback to report an error if it occurs.
     */
    void enroll(EnrollmentRequest req, EnrollCallback cb);

    /**
     * Get an array of transaction certificates (tcerts).
     * @param req A GetTCertBatchRequest
     * @param cb A GetTCertBatchCallback
     */
    void getTCertBatch(GetTCertBatchRequest req, GetTCertBatchCallback cb);

}
