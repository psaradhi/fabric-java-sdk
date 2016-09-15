package org.hyperledger.fabricjavasdk;

import java.io.Serializable;

// Enrollment metadata
public class Enrollment implements Serializable {    
	private static final long serialVersionUID = 550416591376968096L;
	private String key;
    private String cert;
    private String chainKey;
    
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCert() {
		return cert;
	}
	public void setCert(String cert) {
		this.cert = cert;
	}
	public String getChainKey() {
		return chainKey;
	}
	public void setChainKey(String chainKey) {
		this.chainKey = chainKey;
	}
    
	@Override
	public String toString() {
		return "[key="+key+", cert="+cert+", chainKey="+chainKey+"]";
	}
    
}
