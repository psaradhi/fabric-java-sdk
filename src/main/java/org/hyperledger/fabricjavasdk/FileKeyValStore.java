package org.hyperledger.fabricjavasdk;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * A local file-based key value store.
 * This implements the KeyValStore interface.
 */
public class FileKeyValStore implements KeyValStore {

    private String file;

    public FileKeyValStore(String file) {
    	this.file = file;
    }

    /**
     * Get the value associated with name.
     * @param name
     * @param cb function(err,value)
     */
    public String getValue(String name) {
    	try ( InputStream input = new FileInputStream(file);) {
	    	Properties properties = new Properties();
	    	properties.load(input);
	    	return (String) properties.getProperty(name);
    	} catch(FileNotFoundException e) {
    		e.printStackTrace(); //TODO log this error
    		return null;
    	} catch(IOException e) {
    		e.printStackTrace(); //TODO log this error
    		return null;
    	}
    }

    /**
     * Set the value associated with name.
     * @param name
     * @param cb function(err)
     */
    public void setValue(String name, String value) {
    	try (
    	    	OutputStream output = new FileOutputStream(file);
        	) {
    	    	Properties properties = new Properties();
    	    	properties.setProperty(name, value);
    	    	properties.store(output, null);
    	    	
        	} catch(IOException e) {
        		e.printStackTrace(); //TODO log this error
        		return;
        	}
    }

} // end FileKeyValStore