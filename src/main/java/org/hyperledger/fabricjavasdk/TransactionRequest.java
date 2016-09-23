package org.hyperledger.fabricjavasdk;

import java.util.ArrayList;

/**
 * A base transaction request common for DeployRequest, InvokeRequest, and QueryRequest.
 */
public class TransactionRequest {
    // The local path containing the chaincode to deploy in network mode.
    String chaincodePath;
    // The name identifier for the chaincode to deploy in development mode.
    String chaincodeName;
	// The chaincode ID as provided by the 'submitted' event emitted by a TransactionContext
    String chaincodeID;
    // The name of the function to invoke
    String fcn;
    // The arguments to pass to the chaincode invocation
    ArrayList<String> args;
    // Specify whether the transaction is confidential or not.  The default value is false.
    boolean confidential = false;
    // Optionally provide a user certificate which can be used by chaincode to perform access control
    Certificate userCert;
    // Optionally provide additional metadata
    byte[] metadata;
    
    
	public String getChaincodePath() {
		return null == chaincodePath ? "" : chaincodePath;
	}
	public void setChaincodePath(String chaincodePath) {
		this.chaincodePath = chaincodePath;
	}
	public String getChaincodeName() {
		return chaincodeName;
	}
	public void setChaincodeName(String chaincodeName) {
		this.chaincodeName = chaincodeName;
	}
	public String getChaincodeID() {
		return chaincodeID;
	}
	public void setChaincodeID(String chaincodeID) {
		this.chaincodeID = chaincodeID;
	}
	public String getFcn() {
		return fcn;
	}
	public void setFcn(String fcn) {
		this.fcn = fcn;
	}
	public ArrayList<String> getArgs() {
		return args;
	}
	public void setArgs(ArrayList<String> args) {
		this.args = args;
	}
	public boolean isConfidential() {
		return confidential;
	}
	public void setConfidential(boolean confidential) {
		this.confidential = confidential;
	}
	public Certificate getUserCert() {
		return userCert;
	}
	public void setUserCert(Certificate userCert) {
		this.userCert = userCert;
	}
	public byte[] getMetadata() {
		return metadata;
	}
	public void setMetadata(byte[] metadata) {
		this.metadata = metadata;
	}
}
