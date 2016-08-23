
package org.hyperledger.fabricjavasdk;

public class Transaction {

	private protos.Fabric.Transaction transaction;
	private String chaincodeID;
	
	public Transaction(protos.Fabric.Transaction transaction, String chaincodeID) {
		this.transaction = transaction;
		this.chaincodeID = chaincodeID;
	}

	public protos.Fabric.Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(protos.Fabric.Transaction transaction) {
		this.transaction = transaction;
	}

	public String getChaincodeID() {
		return chaincodeID;
	}

	public void setChaincodeID(String chaincodeID) {
		this.chaincodeID = chaincodeID;
	}
	
	
}
