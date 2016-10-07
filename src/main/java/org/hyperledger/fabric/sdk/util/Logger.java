/*
 *  Copyright 2016 DTCC, Fujitsu Australia Software Technology - All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 	  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.hyperledger.fabric.sdk.util;

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
		logger.log(Level.INFO, String.format(message, args));
	}

	public void warn(String message) {
		logger.warning(message);
	}

	public void warn(String message, Object... args) {
		logger.log(Level.WARNING, String.format(message, args));
	}

	public void debug(String message) {
		logger.fine(message);
	}

	public void debug(String message, Object... args) {
		logger.log(Level.FINE, String.format(message, args));
	}
}
