package org.hyperledger.fabricjavasdk;

import java.util.ArrayList;
import java.util.Stack;

// A class to get TCerts.
// There is one class per set of attributes requested by each member.
public class TCertGetter {

    private Chain chain;
    private Member member;
    private ArrayList<String> attrs;
    private String key;
    private MemberServices memberServices;
    private Stack<TCert> tcerts;
//TODO implement stats
//    private stats.Rate arrivalRate = new stats.Rate();
//    private stats.ResponseTime getTCertResponseTime = new stats.ResponseTime();
//    private getTCertWaiters:GetTCertCallback[] = [];
    private boolean gettingTCerts = false;

    /**
    * Constructor for a member.
    * @param cfg {string | RegistrationRequest} The member name or registration request.
    * @returns {Member} A member who is neither registered nor enrolled.
    */
    public TCertGetter(Member member, ArrayList<String> attrs, String key) {
        this.member = member;
        this.attrs = attrs;
        this.key = key;
        this.chain = member.getChain();
        this.memberServices = member.getMemberServices();
        this.tcerts = new Stack<>();
    }

    /**
    * Get the chain.
    * @returns {Chain} The chain.
    */
    public Chain getChain() {
        return this.chain;
    };

    public void getUserCert() {
        this.getNextTCert();
    }

    /**
    * Get the next available transaction certificate.
    * @param cb
    */
    public TCert getNextTCert() {

//TODO    	self.arrivalRate.tick();
        return tcerts.size() > 0 ? tcerts.pop() : null;

        //TODO implement the commented logic
            /*
        } else {
            self.getTCertWaiters.push(cb);
        }
        if (self.shouldGetTCerts()) {
            self.getTCerts();
        }
        */
    }

    // Determine if we should issue a request to get more tcerts now.
    private boolean shouldGetTCerts() {
    	return false;        //TODO implement shouldGetTCerts

    	
    	/*
    	let self = this;
        // Do nothing if we are already getting more tcerts
        if (self.gettingTCerts) {
            debug("shouldGetTCerts: no, already getting tcerts");
            return false;
        }
        // If there are none, then definitely get more
        if (self.tcerts.length == 0) {
            debug("shouldGetTCerts: yes, we have no tcerts");
            return true;
        }
        // If we aren't in prefetch mode, return false;
        if (!self.chain.isPreFetchMode()) {
            debug("shouldGetTCerts: no, prefetch disabled");
            return false;
        }
        // Otherwise, see if we should prefetch based on the arrival rate
        // (i.e. the rate at which tcerts are requested) and the response
        // time.
        // "arrivalRate" is in req/ms and "responseTime" in ms,
        // so "tcertCountThreshold" is number of tcerts at which we should
        // request the next batch of tcerts so we don't have to wait on the
        // transaction path.  Note that we add 1 sec to the average response
        // time to add a little buffer time so we don't have to wait.
        let arrivalRate = self.arrivalRate.getValue();
        let responseTime = self.getTCertResponseTime.getValue() + 1000;
        let tcertThreshold = arrivalRate * responseTime;
        let tcertCount = self.tcerts.length;
        let result = tcertCount <= tcertThreshold;
        debug(util.format("shouldGetTCerts: %s, threshold=%s, count=%s, rate=%s, responseTime=%s",
        result, tcertThreshold, tcertCount, arrivalRate, responseTime));
        return result;
        
        */
    }

    // Call member services to get more tcerts
    private void getTCerts() {
    	//TODO implement getTCerts
    	/*
    	let self = this;
        let req = {
            name: self.member.getName(),
            enrollment: self.member.getEnrollment(),
            num: self.member.getTCertBatchSize(),
            attrs: self.attrs
        };
        self.getTCertResponseTime.start();
        self.memberServices.getTCertBatch(req, function (err, tcerts) {
            if (err) {
                self.getTCertResponseTime.cancel();
                // Error all waiters
                while (self.getTCertWaiters.length > 0) {
                    self.getTCertWaiters.shift()(err);
                }
                return;
            }
            self.getTCertResponseTime.stop();
            // Add to member's tcert list
            while (tcerts.length > 0) {
                self.tcerts.push(tcerts.shift());
            }
            // Allow waiters to proceed
            while (self.getTCertWaiters.length > 0 && self.tcerts.length > 0) {
                let waiter = self.getTCertWaiters.shift();
                waiter(null,self.tcerts.shift());
            }
        });
        */
    }

} // end TCertGetter
