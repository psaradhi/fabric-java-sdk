package org.hyperledger.fabricjavasdk.util;

import java.util.logging.Level;

public class Logger {
	private java.util.logging.Logger logger = null;
	
	private Logger(@SuppressWarnings("rawtypes") Class clazz) {
		this.logger = java.util.logging.Logger.getLogger(clazz.getName());
	}
	
	public static Logger getLogger(@SuppressWarnings("rawtypes") Class clazz) {
		return new Logger(clazz);
	}
		
	public void info(String message) {
		logger.info(message);
	}
	
	public void info(String message, Object... args) {
		logger.log(Level.INFO, message, args);
	}
	
	public void warn(String message) {
		logger.warning(message);
	}
	
	public void warn(String message, Object... args) {
		logger.log(Level.WARNING, message, args);
	}
	
	public void debug(String message) {
		logger.fine(message);
	}
	
	public void debug(String message, Object... args) {
		logger.log(Level.FINE, message, args);
	}
}
