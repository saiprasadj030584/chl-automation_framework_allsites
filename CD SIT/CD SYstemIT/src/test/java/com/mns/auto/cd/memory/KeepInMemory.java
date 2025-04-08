package com.mns.auto.cd.memory;

import java.sql.Connection;
import java.util.ArrayList;

import com.mns.auto.cd.memory.KeepInMemory;

import cucumber.api.Scenario;

public class KeepInMemory {

	private static Connection iARMSQLDBConnection = null;
	private static Connection cxmSQLDBConnection = null;
	private static Connection connectionSQLDB = null;
	private Connection connection = null;
	private static String parentRequestId;
	private static String childStartTime;
	private Scenario scenario;
	private static String errorMessage;
	private String url;
	private String puttyHost;
	private String puttyPort;
	private String appUsername;
	private String appPassword;
	private String dBHost;
	private String dBUsername;
	private String dBPassword;
	private ArrayList upcList;
	private ArrayList articleIdList;
	private String userName;
	private String region;
	private String useDBForData;
	private String metricsDB;
	private static String articleNo;
	private static String seasonValue;
	private static String styleName;
	private static String interfaceId;
	private static String version;
	private static String project;
	private static String msg; 
	
	private String server;
	
	private String passWord;
	private String shellScriptName;

	public void setParentRequestId(String parentRequestId) {
		this.parentRequestId = parentRequestId;
	}

	public String getParentRequestId() {
		return parentRequestId;
	}

	// public Connection getConnection() {
	// return connection;
	// }
	//
	// public void setConnection(Connection connection) {
	// this.connection = connection;
	// }

	public Connection getiARMSQLDBConnection() {
		return iARMSQLDBConnection;
	}

	public void setiARMSQLDBConnection(Connection iARMSQLDBConnection) {
		this.iARMSQLDBConnection = iARMSQLDBConnection;
	}

	public void setChildStartTime(String childStartTime) {

		this.childStartTime = childStartTime;
	}

	public String getChildStartTime() {
		return childStartTime;
	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;

	}

	public void setErrorMessage(String errorMessage) {

		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public String getURL() {
		return url;
	}

	public void setPuttyHost(String puttyHost) {
		this.puttyHost = puttyHost;
	}

	public String getPuttyHost() {
		return puttyHost;
	}

	public void setPuttyPort(String puttyPort) {
		this.puttyPort = puttyPort;

	}

	public String getPuttyPort() {
		return puttyPort;
	}

	public void setAppUserName(String appUsername) {
		this.appUsername = appUsername;
	}

	public String getAppUserName() {
		return appUsername;
	}

	public void setAppPassord(String appPassword) {
		this.appPassword = appPassword;

	}

	public String getAppPassord() {
		return appPassword;
	}

	public void setDBHost(String dBHost) {
		this.dBHost = dBHost;
	}

	public String getDBHost() {
		return dBHost;
	}

	public void setDBUserName(String dBUsername) {
		this.dBUsername = dBUsername;

	}

	public String getDBUserName() {
		return dBUsername;
	}

	public void setDBPassword(String dBPassword) {
		this.dBPassword = dBPassword;

	}

	public String getDBPassword() {
		return dBPassword;
	}

	public ArrayList getUpcList() {
		return upcList;
	}

	public void setUpcList(ArrayList upcList) {
		this.upcList = upcList;
	}

	public ArrayList getArticleId() {
		return articleIdList;
	}

	public void setArticleId(ArrayList articleIdList) {
		this.articleIdList = articleIdList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getUseDBForData() {
		return useDBForData;
	}

	public void setUseDBForData(String useDBForData) {
		this.useDBForData = useDBForData;
	}

	public String getMetricsDB() {
		return metricsDB;
	}

	public void setMetricsDB(String metricsDB) {
		this.metricsDB = metricsDB;
	}

	public static Connection getCxmSQLDBConnection() {
		return cxmSQLDBConnection;
	}

	public static void setCxmSQLDBConnection(Connection cxmSQLDBConnection) {
		KeepInMemory.cxmSQLDBConnection = cxmSQLDBConnection;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public String getArticleNo() {
		return articleNo;
	}

	public void setSeasonValue(String seasonValue) {
		this.seasonValue = seasonValue;
		
	}
	
	public String getSeasonValue() {
		return seasonValue;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	} 
	
	public String getStyleName() {
		return styleName;
	}
	
	public String getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}
	
	public static String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		KeepInMemory.version = version;
	} 
	
	public static String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}
	
	public static String getMsg() {
		return msg;
	}

	public  void setMsg(String msg) {
		KeepInMemory.msg = msg;
	}
	
	public Connection getConnection() {
		return connectionSQLDB;
	}

	public void setConnection(Connection connectionSQLDB) {
		this.connectionSQLDB = connectionSQLDB;
	}
	
	public Connection getDBConnection() {
		return connection;
	}

	public void setDBConnection(Connection connection) {
		this.connection = connection;
	} 
	
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getShellScriptName() {
		return shellScriptName;
	}
	public void setShellScriptName(String shellScriptName) {
		this.shellScriptName = shellScriptName;
	}

}
