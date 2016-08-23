
/**
 * A registration request is information required to register a user, peer, or other
 * type of member.
 */
public interface RegistrationRequest {
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
    registrar?:{
        // The allowable roles which this member can register
        roles:string[],
        // The allowable roles which can be registered by members registered by this member
        delegateRoles?:string[]
    };
}
