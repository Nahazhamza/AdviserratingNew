package com.adviserratinguiautomation.customexceptions;

public class LogDirectoryCreationException extends BaseAutomationException {
	public LogDirectoryCreationException() {
		super();
	}

	public LogDirectoryCreationException(String message) {
		super(message);
	}

	public LogDirectoryCreationException(String message, Exception ex) {
		super(message, ex);
	}
}