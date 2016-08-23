
/**
 * Enrollment certificate.
 */
export class ECert extends Certificate {

    constructor(public cert:Buffer,
                public privateKey:any) {
        super(cert, privateKey, PrivacyLevel.Nominal);
    }

}
