package org.hyperledger.fabricjavasdk;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class PeerClientTest {

	Chain testChain = null;
	
	@Before
	public void init() {
		testChain = new Chain("chain1");
		testChain.setMemberServicesUrl("", null);
		testChain.setKeyValStore(new FileKeyValStore("/home/pardha/test.properties"));
		testChain.addPeer("grpc://localhost:7051", null);
		
	}
	
	@Test
	public void testDeploy() {
		DeployRequest request = new DeployRequest();
		request.setChaincodeID("mycc");
		request.setChaincodeName("mycc");
		request.setArgs(new ArrayList<>(Arrays.asList("init", "a", "100", "b", "200")));
		
		RegistrationRequest registrationRequest = new RegistrationRequest();
		registrationRequest.setEnrollmentID("User1");
		registrationRequest.setAffiliation("00001");
//		registrationRequest.setAccount(); TODO setAccount missing from registrationRequest?
		
		Member member = testChain.registerAndEnroll(registrationRequest);
		member.deploy(request);
	}

	@Test
	public void testQuery() {

		/*
		 * There is no way to specify the order of tests in Junit.
		 * So, let's ensure that chaincode "mycc" is deployed first.
		 */
		testDeploy();
		
		QueryRequest request = new QueryRequest();
		request.setChaincodeID("mycc");
		request.setChaincodeName("mycc");
		request.setArgs(new ArrayList<>(Arrays.asList("query", "a")));
		
		RegistrationRequest registrationRequest = new RegistrationRequest();
		registrationRequest.setEnrollmentID("User1");
		registrationRequest.setAffiliation("00001");
//		registrationRequest.setAccount(); TODO setAccount missing from registrationRequest?
		
		Member member = testChain.registerAndEnroll(registrationRequest);
		member.query(request);
		
	}

}
