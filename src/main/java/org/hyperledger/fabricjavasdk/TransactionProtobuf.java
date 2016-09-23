package org.hyperledger.fabricjavasdk;
/**
 * A transaction.
 */
public interface TransactionProtobuf {
    String getType();
    void setCert(byte[] cert);
    void setSignature(byte[] sig);
    void setConfidentialityLevel(int value);
    int  getConfidentialityLevel();
    void setConfidentialityProtocolVersion(String version);
    void setNonce(byte[] nonce);
    void setToValidators(byte[] buffer);
    byte[] getChaincodeID();
    void setChaincodeID(byte[] buffer);
    byte[] getMetadata();
    void setMetadata(byte[] buffer);
    byte[] getPayload();
    void setPayload(byte[] buffer);
    byte[] toByteArray();
}
