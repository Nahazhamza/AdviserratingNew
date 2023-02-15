package com.adviserratinguiautomation.customexceptions;

import org.apache.log4j.Logger;

public class DuplicateRecordsException extends Exception {

	final static Logger log = Logger.getLogger(BaseAutomationException.class);

	public DuplicateRecordsException() {
		super();
	}

	public DuplicateRecordsException(String message) {
		super(message);
	}

	public DuplicateRecordsException(String message, Exception innerException) {
		super(message, innerException);
	}
}
