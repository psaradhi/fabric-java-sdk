package org.hyperledger.fabricjavasdk;
/**
 * A transaction.
 */
public interface TransactionProtobuf {
    String getType();
    void setCert(Buffer cert);
    void setSignature(Buffer sig);
    void setConfidentialityLevel(int value);
    int  getConfidentialityLevel();
    void setConfidentialityProtocolVersion(String version);
    void setNonce(Buffer nonce);
    void setToValidators(Buffer buffer);
    Buffer getChaincodeID();
    void setChaincodeID(Buffer buffer);
    Buffer getMetadata();
    void setMetadata(Buffer buffer);
    Buffer getPayload();
    void setPayload(Buffer buffer);
    Buffer toBuffer();
}
