package org.hyperledger.fabricjavasdk;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.hyperledger.fabricjavasdk.util.Logger;

/**
 * A local file-based key value store.
 * This implements the KeyValStore interface.
 */
public class FileKeyValStore implements KeyValStore {

    private String file;
    private Logger logger = Logger.getLogger(FileKeyValStore.class);

    public FileKeyValStore(String file) {
    	this.file = file;
    }

    /**
     * Get the value associated with name.
     * @param name
     * @return value associated with the name
     */
    public String getValue(String name) {    	
	    	Properties properties = loadProperties();
	    	return (String) properties.getProperty(name);    	
    }
    
    private Properties loadProperties() {
    	Properties properties = new Properties();
    	try ( InputStream input = new FileInputStream(file);) {	    	
	    	properties.load(input);	 
	    	input.close();
    	} catch(FileNotFoundException e) {
    		logger.warn("Could not find the file \"%s\"", file);
    	} catch(IOException e) {
    		logger.warn("Could not load keyvalue store from file \"%s\", reason:%s", file, e.getMessage());
    	}
    	
    	return properties;
    }

    /**
     * Set the value associated with name.
     * @param name
     * @param value
     */
    public void setValue(String name, String value) {
    	Properties properties = loadProperties();
    	try (
    	    	OutputStream output = new FileOutputStream(file);
        	) {
    	    	properties.setProperty(name, value);
    	    	properties.store(output, "");
    	    	output.close();
    	    	
        	} catch(IOException e) {
        		logger.warn("Could not save the keyvalue store, reason:%s", e.getMessage());
        	}
    }

}