package com.pax.core.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/5/26
 * @description:
 */
public class PropertiesUtils {

    public static final String getValue(String fileName,String key){
        try {
            Properties prop = new Properties();
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

            prop.load(is);
            String value = prop.getProperty(key);
            return value;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String s=getValue("config.deploy.test.properties","swif.channel");
        System.out.println(s);
    }
}
