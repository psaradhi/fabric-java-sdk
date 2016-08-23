package org.hyperledger.fabricjavasdk;

public class Endpoint {
	  
	String addr;
	Buffer creds;

	public Endpoint(String url, String pem) {
		/*TODO implement Endpoint constructor 
		Object purl = parseUrl(url);
	    String protocol = purl.protocol.toLowerCase();
	    if (protocol == "grpc") {
	        this.addr = purl.host;
	        this.creds = grpc.credentials.createInsecure();
	    } else if (protocol == "grpcs") {
	        this.addr = purl.host;
	        this.creds = grpc.credentials.createSsl(new Buffer(pem));
	    } else {
	        throw Error("invalid protocol: " + protocol);
	    }
	    */
	}
	
	Object parseUrl(String url) {
	    // TODO: find ambient definition for url
	    
		/*TODO implement parseUrl 
		var purl = urlParser.parse(url, true);
		var protocol = purl.protocol;
	    if (endsWith(protocol, ":")) {
	        purl.protocol = protocol.slice(0, -1);
	    }
	    return purl;
	    */
		
		return null;
	}
}
