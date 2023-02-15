package propertiesLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class PropertiesLoader {

    Properties prop = new Properties();
    static PropertiesLoader INSTANCE=null;
    static String fileName=null;
    Logger logger = LogManager.getLogger(this.getClass().getName());

    private PropertiesLoader() {
        InputStream input=null;
        try {

            input = PropertiesLoader.class.getClassLoader().getResourceAsStream("AdviserRatingPageResource.properties");
            prop.load(input);

        } catch (IOException ex) {
            logger.error(ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
    }
    

    private PropertiesLoader(String fileName) {
        InputStream input = null;
        try {
            input = new FileInputStream(fileName);
            prop.load(input);

        } catch (IOException ex) {
         logger.error(ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
    }

    public static PropertiesLoader getInstance(){

        if(INSTANCE==null){
            if(fileName!=null){
                INSTANCE = new PropertiesLoader(fileName);
            }else{
                INSTANCE = new PropertiesLoader();
            }
        }
        return INSTANCE;
    }

    public Properties getProperties(){
        return prop;
    }
    public static void setConfigFilePath(String filePath){
        fileName=filePath;
    }


}
