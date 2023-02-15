package com.adviserratinguiautomation.customexceptions.drivercustomexceptions;

import com.adviserratinguiautomation.customexceptions.BaseAutomationException;

public class WebDriverInstanceNullException extends BaseAutomationException {

	public WebDriverInstanceNullException() {
		super();
	}

	public WebDriverInstanceNullException(String message) {
		super(message);
	}

	public WebDriverInstanceNullException(String message, Exception ex) {
		super(message, ex);
	}
}