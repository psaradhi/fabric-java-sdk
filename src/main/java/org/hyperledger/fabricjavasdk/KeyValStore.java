package org.hyperledger.fabricjavasdk;

/**
 * The KeyValStore interface used for persistent storage.
 */
public interface KeyValStore {

    /**
     * Get the value associated with name.
     * @param name     
     */
    String getValue(String name);

    /**
     * Set the value associated with name.
     * @param name
     * @param value
     */
    void setValue(String name, String value);

}
