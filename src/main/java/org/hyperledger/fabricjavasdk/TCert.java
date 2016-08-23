
/**
 * Transaction certificate.
 */
class TCert extends Certificate {
    constructor(public publicKey:any,
                public privateKey:any) {
        super(publicKey, privateKey, PrivacyLevel.Anonymous);
    }
}
