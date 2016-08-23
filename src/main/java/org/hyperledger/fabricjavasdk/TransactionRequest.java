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
    boolean confidential;
    // Optionally provide a user certificate which can be used by chaincode to perform access control
    Certificate userCert;
    // Optionally provide additional metadata
    Buffer metadata;
}
