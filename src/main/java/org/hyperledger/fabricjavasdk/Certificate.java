package org.hyperledger.fabricjavasdk;

// The base Certificate class
public class Certificate {
	private Object cert;
	
	public Certificate(Object cert,
                Object privateKey,
                /** Denoting if the Certificate is anonymous or carrying its owner's identity. */
                PrivacyLevel privLevel) {  // TODO: privLevel not currently used?
    }

    public Object encode() {
        return this.cert;
    }
}
