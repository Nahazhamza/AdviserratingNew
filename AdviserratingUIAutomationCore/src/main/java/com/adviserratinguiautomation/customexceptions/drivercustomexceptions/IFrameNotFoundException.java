package com.adviserratinguiautomation.customexceptions.drivercustomexceptions;

import com.adviserratinguiautomation.customexceptions.BaseAutomationException;

public class IFrameNotFoundException extends BaseAutomationException {

	public IFrameNotFoundException() {
		super();
	}

	public IFrameNotFoundException(String message) {
		super(message);
	}

	public IFrameNotFoundException(String message, Exception ex) {
		super(message, ex);
	}
}