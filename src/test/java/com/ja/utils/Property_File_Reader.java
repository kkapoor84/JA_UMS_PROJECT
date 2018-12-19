package com.ja.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class Property_File_Reader {
	public static String propertyfilepath = System.getProperty("user.dir")
			+ "/src/test/java/com/ja/config/config.properties";
	static Properties P;
	static String value;

	public static void Property_File_R() throws Exception {
		File F = new File(propertyfilepath);
		try {
			FileInputStream FIS = new FileInputStream(F);
			P = new Properties();
			P.load(FIS);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getvalue(String key) throws Exception {
		if (P == null) {
			Property_File_R();
		}
		value = P.getProperty(key);
		return value;
	}
}
