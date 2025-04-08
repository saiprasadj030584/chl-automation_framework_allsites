package com.mns.auto.cd.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.google.inject.Inject;
import com.mns.auto.cd.memory.KeepInMemory;

public class TestDataReader {
	private String intfcName;
	private String queueName;
	private String linuxUnixInputPath;
	private String jobName;
	private String application;
	private String articleNumber;
	private String fileName;
	private String linuxUnixOutputPath;
	private String jobPath;
	private static String EnvVar;
	private String jobParam;
	private String source;
	private String middleware;
	private String destination;
	private String sourceTestDataName;
	private String sourceTestDataValue;
	private String mwTestdataName;
	private String mwTestdataValue;
	private String destinationTestDataName;
	private String destinationTestDataValue;
	private String systemId;
	private String region;
	private String version;
	private KeepInMemory keepInMemory;
	private String query;
	private String dbName;
	private String dbType;

	@Inject
	public TestDataReader(String intfcName, String queueName, String linuxUnixInputPath, String jobName,
			String application, String articleNumber, String fileName, String linuxUnixOutputPath,
			KeepInMemory keepInMemory, String jobPath, String jobParam, String source, String middleware,
			String destination, String sourceTestDataName, String sourceTestDataValue, String mwTestdataName,
			String mwTestdataValue, String destinationTestDataName, String destinationTestDataValue, String systemId,
			String region, String version, String query, String dbName, String dbType) {
		this.intfcName = intfcName;
		this.queueName = queueName;
		this.linuxUnixInputPath = linuxUnixInputPath;
		this.jobName = jobName;
		this.application = application;
		this.articleNumber = articleNumber;
		this.fileName = fileName;
		this.linuxUnixOutputPath = linuxUnixOutputPath;
		this.jobPath = jobPath;
		this.keepInMemory = keepInMemory;
		this.jobParam = jobParam;
		this.source = source;
		this.middleware = middleware;
		this.destination = destination;
		this.sourceTestDataName = sourceTestDataName;
		this.sourceTestDataValue = sourceTestDataValue;
		this.mwTestdataName = mwTestdataName;
		this.mwTestdataValue = mwTestdataValue;
		this.destinationTestDataName = destinationTestDataName;
		this.destinationTestDataValue = destinationTestDataValue;
		this.systemId = systemId;
		this.region = region;
		this.version = version;
		this.query = query;
		this.dbName = dbName;
		this.dbType = dbType;

	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public TestDataReader() {
		// this empty constructor is required
	}

	public String getIntfcName() {
		return intfcName;
	}

	public void setIntfcName(String intfcname) {
		this.intfcName = intfcname;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queuename) {
		this.queueName = queuename;
	}

	public String getLinuxUnixInputPath() {
		return linuxUnixInputPath;
	}

	public void setLinuxUnixInputPath(String linuxUnixInputPath) {
		this.linuxUnixInputPath = linuxUnixInputPath;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String dsjobname) {
		this.jobName = dsjobname;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLinuxUnixOutputPath() {
		return linuxUnixOutputPath;
	}

	public void setLinuxUnixOutputPath(String linuxUnixOutputPath) {
		this.linuxUnixOutputPath = linuxUnixOutputPath;
	}

	public String getJobPath() {
		return jobPath;
	}

	public void setJobPath(String jobPath) {
		this.jobPath = jobPath;
	}

	public String getJobParam() {
		return jobParam;
	}

	public void setJobParam(String jobParam) {
		this.jobParam = jobParam;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMiddleware() {
		return middleware;
	}

	public void setMiddleware(String middleware) {
		this.middleware = middleware;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getSourceTestDataName() {
		return sourceTestDataName;
	}

	public void setSourceTestDataName(String sourceTestDataName) {
		this.sourceTestDataName = sourceTestDataName;
	}

	public String getSourceTestDataValue() {
		return sourceTestDataValue;
	}

	public void setSourceTestDataValue(String sourceTestDataValue) {
		this.sourceTestDataValue = sourceTestDataValue;
	}

	public String getMwTestdataName() {
		return mwTestdataName;
	}

	public void setMwTestdataName(String mwTestdataName) {
		this.mwTestdataName = mwTestdataName;
	}

	public String getMwTestdataValue() {
		return mwTestdataValue;
	}

	public void setMwTestdataValue(String mwTestdataValue) {
		this.mwTestdataValue = mwTestdataValue;
	}

	public String getDestinationTestDataName() {
		return destinationTestDataName;
	}

	public void setDestinationTestDataName(String destinationTestDataName) {
		this.destinationTestDataName = destinationTestDataName;
	}

	public String getDestinationTestDataValue() {
		return destinationTestDataValue;
	}

	public void setDestinationTestDataValue(String destinationTestDataValue) {
		this.destinationTestDataValue = destinationTestDataValue;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String readCSVFile(String intfc_Name, String region, String version, String column_name) {
		System.out.println("CHECKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
		System.out.println(intfc_Name);
		System.out.println(version);
		System.out.println(column_name);

		ICsvBeanReader beanReader = null;
		String value = null;
		CellProcessor[] processors = new CellProcessor[] { new NotNull(), // IntfcName
				new NotNull(), // queueName
				new NotNull(), // LinuxUnixPath
				new NotNull(), // JobName
				new NotNull(), // Application
				new NotNull(), // ArticleNumber
				new NotNull(), // FileName
				new NotNull(), // LinuxUnixOutputPath
				new NotNull(), // JobParam
				new NotNull(), // JobPath
				new NotNull(), // source
				new NotNull(), // middleware
				new NotNull(), // destination
				new NotNull(), // sourceTestDataName
				new NotNull(), // sourceTestDataValue
				new NotNull(), // mwTestDataName
				new NotNull(), // mwTestDataValue
				new NotNull(), // destinationTestDataName
				new NotNull(), // destinationTestDataValue
				new NotNull(), // systemId
				new NotNull(), // region
				new NotNull(), // version
				new NotNull(), // query
				new NotNull(), // dbName
				new NotNull(), // dbType

		};

		try {
			System.out.println("Project name in csv redAer" + keepInMemory.getProject());
			EnvVar = System.getProperty("user.dir");
			System.out.println(EnvVar);
			System.out.println(EnvVar + "\\src\\test\\resources\\testdata\\" + keepInMemory.getProject() + ".csv");
			beanReader = new CsvBeanReader(
					new FileReader(EnvVar + "\\src\\test\\resources\\testdata\\" + keepInMemory.getProject() + ".csv"),
					CsvPreference.STANDARD_PREFERENCE);
			String[] header = beanReader.getHeader(true);
			TestDataReader columnVal = null;
			ArrayList<String> intfcList = new ArrayList<String>();
			while ((columnVal = beanReader.read(TestDataReader.class, header, processors)) != null) {
				intfcList.add(columnVal.getIntfcName());
				System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGG");
				System.out.println(columnVal.getIntfcName().equalsIgnoreCase(intfc_Name));
				System.out.println(columnVal.getRegion().equalsIgnoreCase(region));
				System.out.println("VERSION" + columnVal.getVersion());
				System.out.println(version);
				System.out.println(columnVal.getVersion().equalsIgnoreCase(version));
				System.out.println(column_name);
				System.out.println(columnVal.getIntfcName().equalsIgnoreCase(intfc_Name)
						&& columnVal.getRegion().equalsIgnoreCase(region)
						&& columnVal.getVersion().equalsIgnoreCase(version));
				if (columnVal.getIntfcName().equalsIgnoreCase(intfc_Name)
						&& columnVal.getRegion().equalsIgnoreCase(region)
						&& columnVal.getVersion().equalsIgnoreCase(version)) {

					switch (column_name.toUpperCase()) {
					case "INTFCNAME":
						value = columnVal.getIntfcName();
						break;

					case "QUEUENAME":
						value = columnVal.getQueueName();
						break;

					case "LINUXUNIXINPUTPATH":
						System.out.println("LINUXUNIXINPUTPATH");
						value = columnVal.getLinuxUnixInputPath();
						break;

					case "JOBNAME":
						value = columnVal.getJobName();
						break;

					case "APPLICATION":
						value = columnVal.getApplication();
						break;

					case "ARTICLENUMBER":
						value = columnVal.getArticleNumber();
						break;

					case "FILENAME":
						value = columnVal.getFileName();
						break;

					case "LINUXUNIXOUTPUTPATH":
						value = columnVal.getLinuxUnixOutputPath();
						break;

					case "JOBPATH":
						value = columnVal.getJobPath();
						break;

					case "SOURCE":
						value = columnVal.getSource();
						break;

					case "MIDDLEWARE":
						value = columnVal.getMiddleware();
						break;

					case "DESTINATION":
						value = columnVal.getDestination();
						break;

					case "SOURCETESTDATANAME":
						value = columnVal.getSourceTestDataName();
						break;

					case "SOURCETESTDATAVALUE":
						value = columnVal.getSourceTestDataValue();
						break;

					case "MWTESTDATANAME":
						value = columnVal.getMwTestdataName();
						break;

					case "MWTESTDATAVALUE":
						value = columnVal.getMwTestdataValue();
						break;

					case "DESTINATIONTESTDATANAME":
						value = columnVal.getDestinationTestDataName();
						break;

					case "DESTINATIONTESTDATAVALUE":
						value = columnVal.getDestinationTestDataValue();
						break;

					case "SYSTEMID":
						value = columnVal.getSystemId();
						break;

					case "REGION":
						value = columnVal.getRegion();
						break;

					case "VERSION":
						value = columnVal.getVersion();
						break;

					case "JOBPARAM":
						value = columnVal.getJobParam();
						break;

					case "QUERY":
						value = columnVal.getQuery();
						break;

					case "DBNAME":
						value = columnVal.getDbName();
						break;

					case "DBTYPE":
						value = columnVal.getDbType();
						break;

					}
				}
			}
			System.out.println(intfcList);
			if (intfcList.contains(intfc_Name)) {
				// do nothing
			} else {
				Assert.fail("Interface Name : " + intfc_Name + " not found in Test Data .csv file");
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			System.err.println("Could not find the CSV file: " + ex.getMessage());
		} catch (IOException ex) {

			ex.printStackTrace();
			System.err.println("Error reading the CSV file: " + ex.getMessage());
		} finally {
			if (beanReader != null) {
				try {
					beanReader.close();
				} catch (IOException ex) {
					System.err.println("Error closing the reader: " + ex);
				}
			}
		}
		return value;

	}

}
