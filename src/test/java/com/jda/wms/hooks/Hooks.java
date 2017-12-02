package com.jda.wms.hooks;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.datasetup.gm.DataSetupRunner;
import com.jda.wms.datasetup.gm.DbConnection;
import com.jda.wms.datasetup.gm.GetTcData;
import com.jda.wms.db.gm.Database;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	Screen screen = new Screen();
	private Context context;
	String envVar = System.getProperty("user.dir");
	private DataSetupRunner dataSetupRunner;
	public static DbConnection NPSdataBase;
	private Database jdaJdatabase;
	private GetTcData gettcdata;
	private Hooks_autoUI hooksautoUI;

	@Inject
	public Hooks(Context context, DataSetupRunner dataSetupRunner, DbConnection dataBase,
			Database jdaJdatabase, GetTcData gettcdata, Hooks_autoUI hooksautoUI) {

		this.context = context;
		this.dataSetupRunner = dataSetupRunner;
		this.NPSdataBase = dataBase;
		this.jdaJdatabase = jdaJdatabase;
		this.gettcdata = gettcdata;
		this.hooksautoUI = hooksautoUI;
	}

	@Before("~@Email")
	public void iniatateDataSetup(Scenario scenario) throws Exception {
		context.setScenario(scenario);
		System.out.println("1st Before start");
		ArrayList<String> tagListForScenario = (ArrayList<String>) scenario.getSourceTagNames();
		System.out.println("Uniq Tag --->" + tagListForScenario);

		System.out.println("Starting Execution" + scenario.getName());
		hooksautoUI.getParentRequestID();
		System.out.println("PREQ_ID " + context.getParentRequestId());
		hooksautoUI.insertDetails(scenario.getName());

		for (String tag : tagListForScenario) {
			if (tag.contains("@ds")) {
				dataSetupRunner.getTagListFromAutoDb();

				if (!(scenario.getName().contains("Triggering automation email"))) {
					System.out.println("Datasetup Started");
					dataSetupRunner.insertDataToJdaDB(tagListForScenario);
					System.out.println("Datasetup completed");
				} else {
					System.out.println("Datasetup not require for email scenario");
				}
				System.out.println(context.getTestData());
			}
		}
		System.out.println("1st Before end");
	}


	/*private void getSiteID() throws ClassNotFoundException {
		try {
			if (context.getSQLDBConnection() == null) {
				hooksautoUI.sqlConnectOpen();
			}
			Statement stmt = null;
			stmt = context.getSQLDBConnection().createStatement();
			System.out.println(
					"SELECT SITE_ID FROM [dbo].[JDA_SITE_ID] where P_REQ_ID='" + context.getParentRequestId() + "'");
			String query = "SELECT SITE_ID FROM [dbo].[JDA_SITE_ID] where P_REQ_ID='" + context.getParentRequestId()
					+ "'";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				context.setSiteID(rs.getString("SITE_ID"));
				System.out.println("" + context.getSiteID());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertSiteID() {
		try {
			System.out.println("INSERT INTO JDA_SITE_ID (P_REQ_ID,SITE_ID) VALUES ('" + context.getParentRequestId()
					+ "','" + context.getSiteID() + "')");
			String insertQuery = "INSERT INTO JDA_SITE_ID (P_REQ_ID,SITE_ID) VALUES ('" + context.getParentRequestId()
					+ "','" + context.getSiteID() + "')";
			context.getSQLDBConnection().createStatement().execute(insertQuery);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}*/


	 @After
	public void logoutPutty() throws FindFailed, InterruptedException, IOException {
		if (context.isPuttyLoginFlag() == true) {
			// context.getPuttyProcess().waitFor();
			while (screen.exists("/images/Putty/3Logout.png") == null) {
				screen.type(Key.F12);
			}
			screen.type("3");
			Thread.sleep(1000);
			screen.type(Key.ENTER);
			Thread.sleep(2000);

			Process p = Runtime.getRuntime().exec("cmd /c " + envVar + "\\bin\\puttykillAdmin.lnk");
			// //Process p = Runtime.getRuntime().exec("cmd /c
			// C:\\Users\\kiruthika.srinivasan\\Desktop\\puttykill_Admin.lnk");
			// p.waitFor();

			screen.type(Key.F4, Key.ALT);
			Thread.sleep(2000);
			screen.type(Key.ENTER);
			Thread.sleep(2000);
			context.setPuttyLoginFlag(false);
			// screen.wait("images/Putty/PuttyClose.png", 20);
			// screen.click("images/Putty/PuttyClose.png", 25);
			// Thread.sleep(1000);

			// screen.wait("images/Putty/PuttyCloseOK.png", 20);
			// screen.click("images/Putty/PuttyCloseOK.png", 25);
			// Thread.sleep(1000);
		}

		Process p = Runtime.getRuntime().exec("cmd /c " + envVar + "\\bin\\puttykillAdmin.lnk");
	}

	@After
	public void closeDBConnection() throws SQLException {
		// if (!context.getConnection().equals(null)) {
		if (!(null == context.getConnection())) {
			context.getConnection().close();
			logger.debug("DB Connection closed");
		}
	}

}