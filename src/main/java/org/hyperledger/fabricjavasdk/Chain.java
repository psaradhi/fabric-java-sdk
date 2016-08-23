package org.hyperledger.fabricjavasdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class representing a chain with which the client SDK interacts.
 */
public class Chain {
	private static final Logger logger = Logger.getLogger(Chain.class.getName());

    // Name of the chain is only meaningful to the client
    private String name;

    // The peers on this chain to which the client can connect
    private Vector<Peer> peers;

    // Security enabled flag
    private boolean securityEnabled = true;

    // A member cache associated with this chain
    // TODO: Make an LRU to limit size of member cache
    private Map<String, Member> members = new HashMap<>();

    // The number of tcerts to get in each batch
    private int tcertBatchSize = 200;

    // The registrar (if any) that registers & enrolls new members/users
    private Member registrar;

    // The member services used for this chain
    private MemberServices memberServices;

    // The key-val store used for this chain
    private KeyValStore keyValStore;

    // Is in dev mode or network mode
    private boolean devMode = false;

    // If in prefetch mode, we prefetch tcerts from member services to help performance
    private boolean preFetchMode = true;

    // Temporary variables to control how long to wait for deploy and invoke to complete before
    // emitting events.  This will be removed when the SDK is able to receive events from the
    private int deployWaitTime = 20;
    private int invokeWaitTime = 5;

    // The crypto primitives object
    Object cryptoPrimitives;

    public Chain(String name) {
        this.name = name;
    }

    /**
     * Get the chain name.
     * @returns The name of the chain.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Add a peer given an endpoint specification.
     * @param endpoint The endpoint of the form: { url: "grpcs://host:port", tls: { .... } }
     * @returns {Peer} Returns a new peer.
     */
    public Peer addPeer(String url, String pem) {
        Peer peer = new Peer(url, this, pem);
        this.peers.add(peer);
        return peer;
    }

    /**
     * Get the peers for this chain.
     */
    public Vector<Peer> getPeers() {
        return this.peers;
    }

    /**
     * Get the member whose credentials are used to register and enroll other users, or undefined if not set.
     * @param {Member} The member whose credentials are used to perform registration, or undefined if not set.
     */
    public Member getRegistrar() {
        return this.registrar;
    }

    /**
     * Set the member whose credentials are used to register and enroll other users.
     * @param {Member} registrar The member whose credentials are used to perform registration.
     */
    public void setRegistrar(Member registrar) {
        this.registrar = registrar;
    }

    /**
     * Set the member services URL
     * @param {string} url Member services URL of the form: "grpc://host:port" or "grpcs://host:port"
     */
    public void setMemberServicesUrl(String url, String pem) {
        this.setMemberServices(new MemberServicesImpl(url,pem));
    }

    /**
     * Get the member service associated this chain.
     * @returns {MemberService} Return the current member service, or undefined if not set.
     */
    public MemberServices getMemberServices() {
        return this.memberServices;
    };

    /**
     * Set the member service associated this chain.  This allows the default implementation of member service to be overridden.
     */
    public void setMemberServices(MemberServices memberServices) {
        this.memberServices = memberServices;
        if (memberServices instanceof MemberServicesImpl) {
           this.cryptoPrimitives = ((MemberServicesImpl) memberServices).getCrypto();
        }
    };

    /**
     * Determine if security is enabled.
     */
    public boolean isSecurityEnabled() {
        return this.memberServices != null;
    }

    /**
     * Determine if pre-fetch mode is enabled to prefetch tcerts.
     */
    public boolean isPreFetchMode() {
        return this.preFetchMode;
    }

    /**
     * Set prefetch mode to true or false.
     */
    public void setPreFetchMode(boolean preFetchMode) {
        this.preFetchMode = preFetchMode;
    }

    /**
     * Determine if dev mode is enabled.
     */
    public boolean isDevMode() {
        return this.devMode;
    }

    /**
     * Set dev mode to true or false.
     */
    public void setDevMode(boolean devMode) {
        this.devMode = devMode;
    }

    /**
     * Get the deploy wait time in seconds.
     */
    public int getDeployWaitTime() {
        return this.deployWaitTime;
    }

    /**
     * Set the deploy wait time in seconds.
     * @param secs
     */
    public void setDeployWaitTime(int secs) {
        this.deployWaitTime = secs;
    }

    /**
     * Get the invoke wait time in seconds.
     */
    public int getInvokeWaitTime() {
        return this.invokeWaitTime;
    }

    /**
     * Set the invoke wait time in seconds.
     * @param secs
     */
    public void setInvokeWaitTime(int secs) {
        this.invokeWaitTime = secs;
    }

    /**
     * Get the key val store implementation (if any) that is currently associated with this chain.
     * @returns {KeyValStore} Return the current KeyValStore associated with this chain, or undefined if not set.
     */
    public KeyValStore getKeyValStore() {
        return this.keyValStore;
    }

    /**
     * Set the key value store implementation.
     */
    public void setKeyValStore(KeyValStore keyValStore) {
        this.keyValStore = keyValStore;
    }

    /**
     * Get the tcert batch size.
     */
    public int getTCertBatchSize() {
        return this.tcertBatchSize;
    }

    /**
     * Set the tcert batch size.
     */
    public void setTCertBatchSize(int batchSize) {
        this.tcertBatchSize = batchSize;
    }

    /**
     * Get the user member named 'name'.
     * @param cb Callback of form "function(err,Member)"
     */
    public Member getMember(String name) {
        if (null == keyValStore) throw new RuntimeException("No key value store was found.  You must first call Chain.configureKeyValStore or Chain.setKeyValStore");
        if (null == memberServices) throw new RuntimeException("No member services was found.  You must first call Chain.configureMemberServices or Chain.setMemberServices");
        getMemberHelper(name); 
        //TODO add logic implemented in callback
        return null; //TODO return correct member
    }

    /**
     * Get a user.
     * A user is a specific type of member.
     * Another type of member is a peer.
     */
    Member getUser(String name) {
        return getMember(name);
    }

    // Try to get the member from cache.
    // If not found, create a new one, restore the state if found, and then store in cache.
    private Member getMemberHelper(String name) {
        // Try to get the member state from the cache
        Member member = (Member) members.get(name);
        if (null != member) return member;
        
        // Create the member and try to restore it's state from the key value store (if found).
        member = new Member(name, this);
        member.restoreState(); // TODO add logic present in callback 
        return member;
    }

    /**
     * Register a user or other member type with the chain.
     * @param registrationRequest Registration information.
     * @param cb Callback with registration results
     */
    public void register(RegistrationRequest registrationRequest) {
        Member member = getMember(registrationRequest.enrollmentID);
	    member.register(registrationRequest);
    }

    /**
     * Enroll a user or other identity which has already been registered.
     * If the user has already been enrolled, this will still succeed.
     * @param name The name of the user or other member to enroll.
     * @param secret The secret of the user or other member to enroll.
     * @param cb The callback to return the user or other member.
     */
    void enroll(String name, String secret) {
        Member member = getMember(name);
        member.enroll(secret); // TODO add logic present in callback
    }

    /**
     * Register and enroll a user or other member type.
     * This assumes that a registrar with sufficient privileges has been set.
     * @param registrationRequest Registration information.
     * @params
     */
    Member registerAndEnroll(RegistrationRequest registrationRequest) {
        Member member = getMember(registrationRequest.enrollmentID);
        if (member.isEnrolled()) {
               debug("already enrolled");
               return member;
        }

        member.registerAndEnroll(registrationRequest);
        return null;//TODO add logic implemented in callback
    }

    /**
     * Send a transaction to a peer.
     * @param tx A transaction
     * @param eventEmitter An event emitter
     */
    void sendTransaction(Transaction tx) {
        if (this.peers.size() == 0) {
            throw new RuntimeException(String.format("chain %s has no peers", getName()));
        }

        /*TODO implement sendTransaction
        let trySendTransaction = (pidx) => {
	       if( pidx >= peers.length ) {
		      eventEmitter.emit('error', new EventTransactionError("None of "+peers.length+" peers reponding"));
		      return;
	       }
	       let p = urlParser.parse(peers[pidx].getUrl());
	       let client = new net.Socket();
	       let tryNext = () => {
		      debug("Skipping unresponsive peer "+peers[pidx].getUrl());
		      client.destroy();
		      trySendTransaction(pidx+1);
	       }
	       client.on('timeout', tryNext);
	       client.on('error', tryNext);
	       client.connect(p.port, p.hostname, () => {
		   if( pidx > 0  &&  peers === this.peers )
		      this.peers = peers.slice(pidx).concat(peers.slice(0,pidx));
		   client.destroy();
		   peers[pidx].sendTransaction(tx, eventEmitter);
	    });
		}
		trySendTransaction(0);
    	}
    */
    }
    
    private static void info(String msg, Object... params) {
        logger.log(Level.INFO, msg, params);
      }
    private static void debug(String msg, Object... params) {
        logger.log(Level.FINE, msg, params);
      }


}
