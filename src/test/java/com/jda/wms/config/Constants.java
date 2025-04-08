package com.jda.wms.config;

public class Constants {
	public static final String USER_DIR = System.getProperty("user.dir", ".");
	public static final String CONFIG_FILE = USER_DIR + "/config/config.yml";
	public static final String CONFIG_FILE1 = USER_DIR + "/config/dataConfig.yml";
	public static final String CONFIG = System.getProperty("config", "default");
	public static final String CONFIG1 = System.getProperty("dataConfig", "default");
	public static final String IMAGE_PATH = USER_DIR + "/images/";
	public static final String RDC_DOCK_LOC = "RECBX01";
	public static final String NDC_DOCK_LOC = "REC007";
	public static final String RDC_XDOCK_LOC = "RECXD01";
	public static final String RELEASE_STATUS = "Released";
	public static final String COMPLETE_STATUS = "Complete";
	public static final long short_sleep = 2000; 
	public static final long long_sleep = 5000; 
	public static final long medium_sleep = 3000; 
	public static final String UPI_STATUS_MATCH_MESSAGE = "** UPI Status Match **";	
	public static final String UPI_STATUS_NOMATCH_MESSAGE = "** UPI Status Don't Match **";	
	public static final String PO_STATUS_MATCH_MESSAGE = "** PO Status Match **";	
	public static final String PO_STATUS_NOMATCH_MESSAGE = "** PO Status Don't Match **";	
	public static final String SCREEN_MATCH	 = "** Screen Match **";
	public static final String SCREEN_NOMATCH	 = "** Screen Don't Match **";
	public static final String putty_host = "hlxc00dc083.unix.marksandspencercate.com";
	public static final String putty_port = "25813"; //  29320  29828
	public static final String rdt_user_id = "P9166258";
	public static final String rdt_password = "1234";
	public static final String OwnerID = "M+S";
	public static final String perf_indicator = "Y";
	public static final String ClientID = "M+S";
	public static final String db_host = "jdbc:oracle:thin:@hlxc00dc084.unix.marksandspencercate.com:1521:WMSJDAC3";
	public static final String db_user_id = "dcsdba";
	public static final String db_password = "Welham135792";
	public static final String TRAILER_TYPE = "TRAILER";
	public static final String CARRIER_NAME= "TEST CARRIER1";
	public static final String SERVICE_LEVEL= "SERVICE1";
	public static final String CHAMBER_ID = "1";
	public static final String LOAD_SEQUENCE = "10";
	public static final String MAX_PALLETS = "10";
	public static final String ORDER_STATUS_MISMATCH = "** Cannot proceed recheck the order status **";
	public static final String PROCESS_INCOMPLETE = "** Process unsuccessfull **";
	public static final String INVALID_SITE = "** Invalid Site **";
	public static final String PRE_RELEASE_STATUS = "PreReceived";
	public static final String URN_RECEIVED = "URN is already received";
	public static final String Brandswholesale_Tdept="T82 T18 T13 T54 T96 T97";
	public static final String Brandsconsignment_Tdept="T83 T19 T58 T94 T55 T66";
	public static final String NonBrands_Tdept="T11 T23 T29 T32 T42 T44 T35 T01 T22 T81 T34 T39 T02 T05 T09 T21 T84 T38 T25 T31 T56";
	public static final String RMS_REASON = "OVERHUMOV";	
	public static final String PRINTER="P2051";
	public static final String LOCATION_ID="RECB002";
}

