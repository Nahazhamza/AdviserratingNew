package com.adviserratinguiautomation.browser;

import com.adviserratinguiautomation.base.BaseTestFixture;
import com.adviserratinguiautomation.customexceptions.ExceptionHandeler;
import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.resourceRead.ResourceRead;
import org.apache.log4j.Logger;
import org.jutils.jprocesses.JProcesses;
import org.jutils.jprocesses.model.ProcessInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BrowserProcessFactory {

	final static Logger log = Logger.getLogger(BrowserProcessFactory.class);

	private String APP_SETTINGS_BROWSER = "Browser";

	public List<ProcessInfo> getCurrentProcessInstance() {
		log.info("Entered the getCurrentProcessInstance method in BrowserProcessFactory");
		List<ProcessInfo> browserProcessList = null;
		try {
			String browserName = new ResourceRead().getResourceValueFromXML().getProperty(APP_SETTINGS_BROWSER);
			List<ProcessInfo> commonProcessList = JProcesses.getProcessList();
			browserProcessList = new ArrayList<>();
			for (final ProcessInfo processInfo : commonProcessList) {
				switch (browserName) {
				case BaseTestFixture.Chrome: {
					if (processInfo.getName().equals("chrome.exe")) {
						browserProcessList.add(processInfo);
					}
					break;
				}
				case BaseTestFixture.FireFox: {
					if (processInfo.getName().equals("firefox.exe")) {
						browserProcessList.add(processInfo);
					}
					break;
				}
				}
			}
		} catch (IOException e) {
			new ExceptionHandeler().genricExceptionHandeler(e);
		} catch (ResourceCustomException e) {
			new ExceptionHandeler().resourceExceptionHandeler(e);
		}
		log.info("Exited the getCurrentProcessInstance method in BrowserProcessFactory");
		return browserProcessList;
	}

}