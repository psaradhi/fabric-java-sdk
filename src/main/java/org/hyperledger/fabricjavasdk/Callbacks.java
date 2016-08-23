
// The callback from the Chain.getMember or Chain.getUser methods
interface GetMemberCallback { 
	void callback(Error err, Member member);
}

// The callback from the Chain.register method
interface RegisterCallback { 
	void callback(Error err, String enrollmentPassword); 
}

// The callback from the Chain.enroll method
interface EnrollCallback { 
	void callback(Error err, Enrollment enrollment);
}

// The callback from the newBuildOrDeployTransaction
interface DeployTransactionCallback { 
	void callback(Error err, Transaction deployTx);
}

// The callback from the newInvokeOrQueryTransaction
interface InvokeOrQueryTransactionCallback { 
	void callback(Error err, Transaction invokeOrQueryTx)
}

// A callback to the MemberServices.getTCertBatch method
interface GetTCertBatchCallback { 
	void callback(Error err, tcerts?:TCert[]);
}

interface GetTCertCallback { 
	void callback(Error err, tcert?:TCert);
}

interface GetTCertCallback { 
	void callback(Error err, tcert?:TCert);
}

/**
 * Common error callback.
 */
interface ErrorCallback { 
	void callback(Error err);
}

/**
 * A callback for the KeyValStore.getValue method.
 */
interface GetValueCallback {
	void callback(Error err, String value);
}


enum PrivacyLevel {
    Nominal = 0,
    Anonymous = 1
}

