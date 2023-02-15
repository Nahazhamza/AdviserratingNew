package com.adviserratinguiautomation.customexceptions;

import org.apache.log4j.Logger;

public abstract class BaseAutomationException extends Exception {

	final static Logger log = Logger.getLogger(BaseAutomationException.class);

	public BaseAutomationException() {
		super();
	}

	public BaseAutomationException(String message) {
		super(message);
	}

	public BaseAutomationException(String message, Exception innerException) {
		super(message, innerException);
	}
}