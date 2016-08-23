package org.hyperledger.fabricjavasdk;

public class EnrollmentRequest {
    // The enrollment ID
    String enrollmentID;
    // The enrollment secret (a one-time password)
    String enrollmentSecret;
    
	public String getEnrollmentID() {
		return enrollmentID;
	}
	public void setEnrollmentID(String enrollmentID) {
		this.enrollmentID = enrollmentID;
	}
	public String getEnrollmentSecret() {
		return enrollmentSecret;
	}
	public void setEnrollmentSecret(String enrollmentSecret) {
		this.enrollmentSecret = enrollmentSecret;
	}
}
