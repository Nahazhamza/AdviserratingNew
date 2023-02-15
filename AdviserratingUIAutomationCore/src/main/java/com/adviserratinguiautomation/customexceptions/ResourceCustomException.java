package com.adviserratinguiautomation.customexceptions;

public class ResourceCustomException extends BaseAutomationException {

	public ResourceCustomException() {
		super();
	}

	public ResourceCustomException(String message) {
		super(message);
	}

	public ResourceCustomException(String message, Exception ex) {
		super(message, ex);
	}
}