package com.eBay.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public final class PropertiesHelper {
	public static String getProperty(String propertyCategory, String property){
		File classpathRoot = new File(System.getProperty("user.dir"));
		
		Properties prop = new Properties();
		InputStream input = null;
		try {
			
			input = new FileInputStream(classpathRoot+"/src/resources/properties/"+propertyCategory+".properties");
			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println(property +" property value "+prop.getProperty(property));
			return(prop.getProperty(property));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
		return null;
	}
}
