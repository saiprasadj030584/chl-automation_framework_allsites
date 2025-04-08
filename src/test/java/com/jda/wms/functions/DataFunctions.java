package com.jda.wms.functions;

import java.io.IOException;
import java.sql.SQLException;

import org.sikuli.script.FindFailed;
import cucumber.api.java.en.Given;

import com.google.inject.Inject;
import com.jda.wms.handlers.DataHandler;

public class DataFunctions {
	private DataHandler dataHandler;
	
	@Inject
	public DataFunctions(DataHandler dataHandler){
		this.dataHandler = dataHandler;	}
	
	
	@Given("^Load Data to Sku_Data Table From CSV")
	public void LoadDatatoSkuDataFromCsv() throws Throwable{
		dataHandler.ReadDatafromCsv();
		dataHandler.loadToSkuData();
	}	

	/**
	 * Make sure the file name (Both .lnk and .sql files are present
	 * in the path C:/Automation_supporting_files/LnkFiles/Load_Data
	 * before running the below method.
	 * @param file_name
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FindFailed
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	@Given("^Load Data to WMS by executing script \"([^\"]*)\"$")
	public void LoadDataToWmsByScript(String file_name)throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		dataHandler.Load_data_to_wms_database(file_name);
	}

}
