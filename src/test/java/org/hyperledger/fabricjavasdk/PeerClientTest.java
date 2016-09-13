package org.hyperledger.fabricjavasdk;

import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;

import org.hyperledger.fabricjavasdk.exception.RegistrationException;
import org.junit.Before;
import org.junit.Test;

public class PeerClientTest {

	Chain testChain = null;
	
	@Before
	public void init() {
		testChain = new Chain("chain1");
		try {
			testChain.setMemberServicesUrl("grpc://localhost:7054", null);
			testChain.setKeyValStore(new FileKeyValStore("/home/pardha/test.properties"));
			testChain.addPeer("grpc://localhost:7051", null);

			deploy();
		} catch(CertificateException | RegistrationException cex) {
			cex.printStackTrace();// TODO: Handle the exception properly
		}
		
	}
	
	public void deploy() throws RegistrationException {
		DeployRequest request = new DeployRequest();
		request.setChaincodePath("github.com/hyperledger/fabric/examples/chaincode/go/chaincode_example02");
		request.setArgs(new ArrayList<>(Arrays.asList("init", "a", "100", "b", "20000")));
		
		RegistrationRequest registrationRequest = new RegistrationRequest();
		registrationRequest.setEnrollmentID("User1");
		registrationRequest.setAffiliation("00001");
//		registrationRequest.setAccount(); TODO setAccount missing from registrationRequest?
		
		Member member = testChain.registerAndEnroll(registrationRequest);
		member.deploy(request);
	}

	@Test
	public void testQuery() throws RegistrationException {

		QueryRequest request = new QueryRequest();
		request.setArgs(new ArrayList<>(Arrays.asList("query", "a")));
		
		RegistrationRequest registrationRequest = new RegistrationRequest();
		registrationRequest.setEnrollmentID("User1");
		registrationRequest.setAffiliation("00001");
//		registrationRequest.setAccount(); TODO setAccount missing from registrationRequest?
		
		Member member = testChain.registerAndEnroll(registrationRequest);
		member.query(request);
		
	}

	@Test
	public void testInvoke() throws RegistrationException {

		InvokeRequest request = new InvokeRequest();
		request.setArgs(new ArrayList<>(Arrays.asList("invoke", "a", "b", "200")));
		
		RegistrationRequest registrationRequest = new RegistrationRequest();
		registrationRequest.setEnrollmentID("User1");
		registrationRequest.setAffiliation("00001");
//		registrationRequest.setAccount(); TODO setAccount missing from registrationRequest?
		
		Member member = testChain.registerAndEnroll(registrationRequest);
		member.invoke(request);
		
	}

	
}
