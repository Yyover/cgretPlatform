package com.javaee6.cgret.util;

import java.util.Properties;

/**
 * @author Yellowyao
 */
public class PropertiesUtils {
	public static Properties pps;
	
	public static String GetString(String key) {
		return pps.getProperty(key);
		
	}
}
