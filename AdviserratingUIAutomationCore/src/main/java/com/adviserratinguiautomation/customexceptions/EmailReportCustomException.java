package com.adviserratinguiautomation.customexceptions;

public class EmailReportCustomException extends BaseAutomationException {

	public EmailReportCustomException() {
		super();
	}

	public EmailReportCustomException(String message) {
		super(message);
	}

	public EmailReportCustomException(String message, Exception ex) {
		super(message, ex);
	}

}
