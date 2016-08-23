package org.hyperledger.fabricjavasdk;

import java.util.ArrayList;

// A request to get a batch of TCerts
public class GetTCertBatchRequest {
	
	private String name;
	private Enrollment enrollment;
	private int num;
	private ArrayList<String> attrs;
	
	public GetTCertBatchRequest( String name,
                Enrollment enrollment,
                int num,
                ArrayList<String> attrs) {
		this.name = name;
		this.enrollment = enrollment;
		this.num = num;
		this.attrs = attrs;
	}
}
