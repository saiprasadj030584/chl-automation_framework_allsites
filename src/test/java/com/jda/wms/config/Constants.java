package com.jda.wms.config;

public class Constants {
	public static final String USER_DIR = System.getProperty("user.dir", ".");
	public static final String CONFIG_FILE = USER_DIR + "/config/config.yml";
	public static final String CONFIG_FILE1 = USER_DIR + "/config/dataConfig.yml";
	public static final String CONFIG = System.getProperty("config", "default");
	public static final String CONFIG1 = System.getProperty("dataConfig", "default");
	public static final String IMAGE_PATH = USER_DIR + "/images/";
}
