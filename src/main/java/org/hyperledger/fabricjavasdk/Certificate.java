
// The base Certificate class
class Certificate {
    constructor(public cert:Buffer,
                public privateKey:any,
                /** Denoting if the Certificate is anonymous or carrying its owner's identity. */
                public privLevel?:PrivacyLevel) {  // TODO: privLevel not currently used?
    }

    encode():Buffer {
        return this.cert;
    }
}
