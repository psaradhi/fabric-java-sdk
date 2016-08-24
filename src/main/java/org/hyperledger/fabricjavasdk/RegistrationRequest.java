package org.hyperledger.fabricjavasdk;

import java.util.ArrayList;

/**
 * A registration request is information required to register a user, peer, or other
 * type of member.
 */
public class RegistrationRequest {
    // The enrollment ID of the member
    String enrollmentID;
    // Roles associated with this member.
    // Fabric roles include: 'client', 'peer', 'validator', 'auditor'
    // Default value: ['client']
    ArrayList<String> roles;
    // Affiliation for a user
    String affiliation;
    // 'registrar' enables this identity to register other members with types
    // and can delegate the 'delegationRoles' roles
	public String getEnrollmentID() {
		return enrollmentID;
	}
	public void setEnrollmentID(String enrollmentID) {
		this.enrollmentID = enrollmentID;
	}
	public ArrayList<String> getRoles() {
		return roles;
	}
	public void setRoles(ArrayList<String> roles) {
		this.roles = roles;
	}
	public String getAffiliation() {
		return affiliation;
	}
	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}
 
    //TODO uncomment registrar
    /*
    registrar?:{
        // The allowable roles which this member can register
        roles:string[],
        // The allowable roles which can be registered by members registered by this member
        delegateRoles?:string[]
    };
    */
}
