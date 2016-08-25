package org.hyperledger.fabricjavasdk;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLException;

import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.handler.ssl.SslContext;
import io.netty.util.internal.StringUtil;

public class Endpoint {
	  
	private String addr;
	private int port;
	private ManagedChannelBuilder<?> channelBuilder = null;

	public Endpoint(String url, String pem) {		 
		Properties purl = parseUrl(url);
	    String protocol = purl.getProperty("protocol");
	    this.addr = purl.getProperty("host");
	    this.port = Integer.parseInt(purl.getProperty("port"));
	    
	    if (protocol.equalsIgnoreCase("grpc")) {//	    	
	        this.channelBuilder = ManagedChannelBuilder.forAddress(addr, port)
	        		.usePlaintext(true);
	    } else if (protocol.equalsIgnoreCase("grpcs")) {
	    	if (StringUtil.isNullOrEmpty(pem)) {
	    		// use root certificate
	    		this.channelBuilder = ManagedChannelBuilder.forAddress(addr, port);
	    	} else {
	    		try {
		    		SslContext sslContext = GrpcSslContexts.forClient().trustManager(new java.io.File(pem)).build();
			    	this.channelBuilder = NettyChannelBuilder.forAddress(addr, port)
			    			.sslContext(sslContext);
	    		} catch(SSLException sslex) {
	    			throw new RuntimeException(sslex);
	    		}
	    	}	    	
	    } else {
	        throw new RuntimeException("invalid protocol: " + protocol);
	    }	    
	}
	
	public ManagedChannelBuilder<?> getChannelBuilder() {		
		return this.channelBuilder;
	}
	
	public String getHost() {
		return this.addr;
	}
	
	public int getPort() {
		return this.port;
	}
	private Properties parseUrl(String url) {
		if (StringUtil.isNullOrEmpty(url)) {
			throw new RuntimeException("URL cannot be null or empty");
		}
		
		Properties props = new Properties();
		Pattern p = Pattern.compile("([^:]+)[:]//([^:]+)[:]([0-9]+)", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(url);
		if (m.matches()) {
			props.setProperty("protocol", m.group(1));
			props.setProperty("host", m.group(2));
			props.setProperty("port", m.group(3));
		} else {
			throw new RuntimeException("URL must be of the format protocol://host:port");
		}
		
		// TODO: allow all possible formats of the URL
		return props;
	}
}
