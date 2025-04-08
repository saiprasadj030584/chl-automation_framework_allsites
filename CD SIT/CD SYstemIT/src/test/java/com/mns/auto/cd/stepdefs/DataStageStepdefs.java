package com.mns.auto.cd.stepdefs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import com.google.inject.Inject;
import com.mns.auto.cd.memory.KeepInMemory;
import com.mns.auto.cd.pages.DataStage;

public class DataStageStepdefs {

	private final DataStage dataStage;
	private final KeepInMemory keepInMemory;
	public String StgInterfaceName;
	public String DSuserName;
	public String DSpassword;
	public String DS_Hostname;
	public String JobName;
	public String DSRegion;
	public int DSReturnCode;

	@Inject
	public DataStageStepdefs(DataStage dataStage, KeepInMemory keepInMemory) {
		this.dataStage = dataStage;
		this.keepInMemory = keepInMemory;
	}

	@Given("^import DS details and connect to DS server in \"([^\"]*)\" for the interface \"([^\"]*)\"$")
	public void import_DS_details_and_connect_to_DS_server_in_for_the_interface(String region, String interfaceName)
			throws Throwable {
		StgInterfaceName = interfaceName;

		String xlsFile = "src/test/resources/InputFiles/DSConn.xls";
		File inputWorkbook = new File(xlsFile);
		try {

			Workbook w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet1 = w.getSheet(0);
			DSuserName = sheet1.getCell(1, 1).getContents();
			DSpassword = sheet1.getCell(2, 1).getContents();
		} catch (BiffException e) {
			e.printStackTrace();
		}

		File f = new File("src/test/resources/InputFiles/EDW_DS_Details.csv");
		ArrayList FileContent = new ArrayList();
		String line = null;
		BufferedReader Reader = new BufferedReader(new FileReader(f));
		line = Reader.readLine();
		while (line != null) {
			FileContent.add(line);
			line = Reader.readLine();
		}
		int dataIndex = 0;
		for (int index = 0; index < FileContent.size(); index++) {
			if (FileContent.get(index).toString().contains(region)
					&& FileContent.get(index).toString().contains(StgInterfaceName)) {
				dataIndex = index;
				break;
			}
		}
		String DataArr[] = FileContent.get(dataIndex).toString().split(",");
		DS_Hostname = DataArr[2];
		JobName = DataArr[3];
		System.out.println("DS_Hostname " + DS_Hostname + "\nJobName " + JobName);
		DSRegion = region;
	}

	@Then("^execute datastage job$")
	public void execute_datastage_job() throws Throwable {
		keepInMemory.setServer(DS_Hostname);
		keepInMemory.setUserName(DSuserName);
		keepInMemory.setPassWord(DSpassword);
		keepInMemory.setShellScriptName(JobName);
		DSReturnCode = dataStage.TriggerDataStage(DSRegion);
	}

	@Then("^validate resultant status code$")
	public void validate_resultant_status_code() throws Throwable {
		if (DSReturnCode == 0) {
			System.out.println("\nSuccessful job run!");
		} else {
			System.out.println("\nJob Aborted!");
			throw new RuntimeException("Job Aborted!");
		}
	}

}
