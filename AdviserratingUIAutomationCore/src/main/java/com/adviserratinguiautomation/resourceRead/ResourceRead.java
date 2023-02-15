package com.adviserratinguiautomation.resourceRead;

import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class is used to read the property values from the XML file
 */
public class ResourceRead {

    //Logger
    final static Logger log = Logger.getLogger(ResourceRead.class);

    /**
     * @return Properties object
     * @throws IOException
     * This method is used to fetch the properties values mentioned in the config.xml
     */
    public static Properties getResourceValueFromXML() throws ResourceCustomException, IOException  {
        log.info("Entered the getResourceValueFromXML in ResourceRead");
        File stocks = new File("../AdviserratingUIAutomationTest/src/main/resources/config.xml");
        Properties props = new Properties();
        if(stocks.exists()){
            FileInputStream fis = new FileInputStream(stocks);
            props.loadFromXML(fis);
        }else{
            throw new ResourceCustomException("Configuration file not found in classpath");
        }
        log.info("Exited the getResourceValueFromXML in ResourceRead");
        return props;
    }

    public static Properties getEnvironmentConfigValue() throws ResourceCustomException, IOException  {
        log.info("Entered the getEnvironmentConfigValue in ResourceRead");
        String selectedEnvironment = System.getProperty("Environment");
        String envConfigXML = "../AdviserratingUIAutomationTest/src/main/resources/config.xml";
        log.info("Selected EnvironmentConfig File is: " + envConfigXML);
        File envStocks = new File(envConfigXML);
        Properties envprops = new Properties();
        if(envStocks.exists()){
            FileInputStream envfis = new FileInputStream(envStocks);
            envprops.loadFromXML(envfis);
        }else{
            throw new ResourceCustomException("Configuration file not found in classpath");
        }
        return envprops;
    }
    
    public static Properties getEmailConfigValue() throws ResourceCustomException, IOException  {
        log.info("Entered the getEmailConfigValue in ResourceRead");
        File stocks = new File("../AdviserratingUIAutomationTest/src/main/resources/email-config.xml");
        Properties props = new Properties();
        if(stocks.exists()){
            FileInputStream fis = new FileInputStream(stocks);
            props.loadFromXML(fis);
        }else{
            throw new ResourceCustomException("Configuration file not found in classpath");
        }
        log.info("Exited the getEmailConfigValue in ResourceRead");
        return props;
    }

    /**
     * @return Propeties object
     * @throws IOException
     * This method is used to fetch the properties values mentioned in the .properties Files
     */
    public Properties getResourceValueFromProperties(String propertyFileName) throws ResourceCustomException, IOException {
        log.info("Entered the getResourceValueFromProperties in ResourceRead");
        InputStream inputStream = getClass().getResourceAsStream(propertyFileName);
        Properties properties = new Properties();
        if (inputStream != null) {
            properties.load(inputStream);
          //  properties.load(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        } else {
            throw new ResourceCustomException("Property file " + propertyFileName + " not found in classpath");
        }
        log.info("Exited the getResourceValueFromProperties in ResourceRead");
        return properties;
    }
}