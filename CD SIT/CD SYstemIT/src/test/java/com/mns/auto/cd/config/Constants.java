package com.mns.auto.cd.config;

public class Constants {

	public static final String USER_DIR = System.getProperty("user.dir", ".");
	

	public static final String CONFIG_FILE=USER_DIR + "/config/config.yml";

	public static final String CONFIG = System.getProperty("config", "default");

	public static final String LOCATOR_PATH = USER_DIR + "/config/locator/";

	public static final String CHROME_KILL_FILE = USER_DIR + "src/test/resources/batFiles/chromekill.lnk";
	
	public static final String IE_KILL_FILE = USER_DIR + "src/test/resources/batFiles/IEKill_admin.lnk"; 
	
	public static final String GECKO_KILL_FILE = USER_DIR + "src/test/resources/batFiles/geckoKill.lnk"; 
	
	public static final String PUTTY_KILL = USER_DIR + "src/test/resources/batFiles/puttykillAdmin.lnk";
	
	public static final String EXCEL_KILL = USER_DIR + "src/test/resources/batFiles/ExcelKillAdmin.lnk";
	
	public static final String OLLERTON1 = USER_DIR + "/src/test/resources/PJS_Files/OllertonRegression/OllertonRegression.pjs";
	public static final String OLLERTON2 = USER_DIR + "/src/test/resources/PJS_Files/Ollerton2/Ollerton_Regression.pjs";
	
	public static final String DEPLOYMENT_BATCHFILES = USER_DIR + "/src/test/resources/Deployment/BatchFiles/"; 
	
//	public static final String DEPLOYMENT_BATCHFILES = "D:/CD SIT/CD SYstemIT/src/test/resources/Deployment/BatchFiles/";

}
