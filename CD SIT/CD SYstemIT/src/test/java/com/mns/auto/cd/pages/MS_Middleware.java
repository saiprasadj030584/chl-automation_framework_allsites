package com.mns.auto.cd.pages;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Assert;

import com.google.inject.Inject;
import com.mns.auto.cd.database.DBConnection;
import com.mns.auto.cd.memory.KeepInMemory;
import com.mns.auto.cd.utils.TestDataReader;

public class MS_Middleware {

	private DBConnection dbConnection;
	private KeepInMemory keepInMemory;
	private TestDataReader testDataReader;
	String sql_update;

	static String msg_id_sql, MWTestdataName, interfaceNumber, msg_id_one;
	static int check;
	static List<String> audit = new ArrayList<String>();
	final static List<String> audit_io = Arrays.asList("I", "O");
	static List<String> endSystem = new ArrayList<String>();

	private static HashMap<String, String> inventoryDetails = new HashMap<String, String>();

	@Inject
	public MS_Middleware(KeepInMemory keepInMemory, TestDataReader testDataReader, DBConnection dbConnection) {
		this.keepInMemory = keepInMemory;
		this.dbConnection = dbConnection;
		this.testDataReader = testDataReader;
	}

	public void MSMiddleWareAuditValidation() throws SQLException, ClassNotFoundException, IOException {
		Properties prop = new Properties();
		InputStream input = null;
		Connection conn = null;
		Statement stmt = null;

		// STEP 1: Connecting to MS DB
		System.out.println("Connecting to database...");
		dbConnection.connectDatabase("DB2", "Ms_Db", "CATE_A");// keepInMemory.getRegion());
		System.out.println("Connected to MS database...");

		// STEP 2: Get System DateTime
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
		String PROCESSED_TIME = sdf.format(cal.getTime());
		System.out.println("System_Time :" + PROCESSED_TIME);

		// STEP 5: Execute a SQL DB query
		System.out.println("------------------------------------------");
		System.out.println("Creating statement in SQL DB...");
		// Retrieve by column name
		msg_id_sql = keepInMemory.getVersion();// testDataReader.readCSVFile(keepInMemory.getInterfaceId(),keepInMemory.getRegion(),keepInMemory.getVersion(),
												// "MWTESTDATAVALUE");
		String source_systemID = keepInMemory.getRegion();// testDataReader.readCSVFile(keepInMemory.getInterfaceId(),keepInMemory.getRegion(),keepInMemory.getVersion(),
															// "SOURCE");
		String dest_systemID = keepInMemory.getProject();// testDataReader.readCSVFile(keepInMemory.getInterfaceId(),keepInMemory.getRegion(),keepInMemory.getVersion(),
															// "DESTINATION");
		MWTestdataName = keepInMemory.getMsg();// testDataReader.readCSVFile(keepInMemory.getInterfaceId(),keepInMemory.getRegion(),keepInMemory.getVersion(),
												// "MWTESTDATANAME");
		System.out.println("*************" + keepInMemory.getMsg());
		interfaceNumber = keepInMemory.getInterfaceId();// testDataReader.readCSVFile(keepInMemory.getInterfaceId(),keepInMemory.getRegion(),keepInMemory.getVersion(),
														// "INTFCNUMBER");

		// Display values
		System.out.print("MW_TEST_DATA_VALUE : " + msg_id_sql + System.lineSeparator());
		System.out.print("Source_Sytem: " + source_systemID + System.lineSeparator());
		System.out.print("Dest_System : " + dest_systemID + System.lineSeparator());
		if (MWTestdataName != "NULL" && msg_id_sql != "") {
			// STEP 6: Execute a MS DB query
			System.out.println("------------------------------------------");
			System.out.println("Creating statement in MS DB...");
			conn = keepInMemory.getDBConnection();
			stmt = conn.createStatement();
			String msdb_query;
			if (MWTestdataName.equalsIgnoreCase("Message_id"))
				/*
				 * msdb_query =
				 * "SELECT * FROM  MS.TXN_CME_AUDIT  WHERE CME_MSG_ID = '"
				 * +msg_id_sql+"'";
				 */// AND SYS_INSERT_TS >CURRENT TIMESTAMP - 15 MINUTES ";

				msdb_query = "SELECT * FROM edcndc.inventory_transactions where messageid = '" + msg_id_sql + "'";

			else if (MWTestdataName.contains("Correlation_id"))
				msdb_query = "SELECT CME_INTRFC_ID,MSG_DIRECTION,INTRFC_PRTNR_SYS_ID FROM  MS.TXN_CME_AUDIT  WHERE CME_CORRL_ID = '"
						+ msg_id_sql + "'";// AND SYS_INSERT_TS >CURRENT
											// TIMESTAMP - 15 MINUTES ";
			else
				msdb_query = "SELECT CME_INTRFC_ID,MSG_DIRECTION,INTRFC_PRTNR_SYS_ID FROM  MS.TXN_CME_AUDIT  WHERE CME_INTRFC_ID like '%"
						+ interfaceNumber.substring(2, 5) + "' AND SYS_INSERT_TS > CURRENT TIMESTAMP - 15 MINUTES";
			System.out.println("MS_DB_QUERY :" + msdb_query);
			ResultSet rs = stmt.executeQuery(msdb_query);
			// To Handle if Result Set is empty
			if (rs.next() == false) {

				Assert.fail("Result Set is empty");

			}
			// STEP 7: Extract data from result set from MS DB
			else {
				do {// Retrieve by column name

					String io = rs.getString("MSG_DIRECTION");
					String intrfc = rs.getString("INTRFC_PRTNR_SYS_ID");
					audit.add(io);
					endSystem.add(intrfc);
					// Display values
					// System.out.println("------------------------------");
					// System.out.print("MSG_ID: " + msg_id_sql +
					// System.lineSeparator());
					// System.out.print("System Name: " + intrfc +
					// System.lineSeparator());

					// System.out.print("I/O Log: " + io +
					// System.lineSeparator());

					// System.out.print(check + System.lineSeparator());
				} while (rs.next());
			}
			System.out.println("------------------------------------------");
			System.out.println("Audits Captured : " + audit);
			System.out.println("Target_Systems Captured : " + endSystem);

			System.out.println("------------------------------------------");
			String sys_id = null;
			String[] systemIdList = dest_systemID.split(";");
			int destcheck = 0;
			int i = 0;
			System.out.println("Validating dest system");

			if (audit.containsAll(audit_io)) {
				for (; i < systemIdList.length; i++) {
					sys_id = systemIdList[i];
					System.out.println(sys_id);
					System.out.println(i);
					// && endSystem.contains(sys_id)

					if (endSystem.contains(sys_id)) {
						System.out.println("inside if");
						destcheck++;

					}

				}
			}

			else {

				Assert.fail("MW_comments: Inbound audit is available but Outbound is unavailable in middleware");

			}
			System.out.println(i);
			System.out.println("------------------------------------------");
			rs.close();
			stmt.close();
			conn.close();

		}
	}

	public void generate_result_file() throws Throwable {
		Statement stmt = null;
		Connection conn = null;
		dbConnection.connectDatabase("DB2", "MS_DB", "CATE_A");
		conn = keepInMemory.getDBConnection();
		stmt = conn.createStatement();
		MWTestdataName = keepInMemory.getMsg();
		System.out.println("----------------generate the result------------------------");
		String msdb_query;
		if (MWTestdataName.equalsIgnoreCase("Message_id"))
			msdb_query = "SELECT * FROM  MS.TXN_CME_AUDIT  WHERE CME_MSG_ID = '" + msg_id_sql + "'";
		else if (MWTestdataName.contains("Correlation_id"))
			msdb_query = "SELECT * FROM  MS.TXN_CME_AUDIT  WHERE CME_CORRL_ID = '" + msg_id_sql + "'";
		else
			msdb_query = "SELECT CME_INTRFC_ID,MSG_DIRECTION,INTRFC_PRTNR_SYS_ID FROM  MS.TXN_CME_AUDIT  WHERE CME_INTRFC_ID like '%"
					+ interfaceNumber.substring(2, 5) + "' AND SYS_INSERT_TS > CURRENT TIMESTAMP - 15 MINUTES";
		System.out.println("MS_DB_QUERY :" + msdb_query);
		ResultSet rs = stmt.executeQuery(msdb_query);
		int rowid = 2;
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet spreadsheet1 = wb.createSheet("Query Results");
		HSSFRow rowhead = spreadsheet1.createRow((short) 0);
		HSSFRow row;
		row = spreadsheet1.createRow(rowid++);
		rowid += 2;
		int cellid = 0;
		Cell cell = row.createCell(cellid++);
		cell.setCellValue("MS_DB_QUERY :" + msdb_query);
		System.out.println("\n\n----------------Report Generation in progress--------------\t");
		int firstRecord = 0;
		while (rs.next()) {
			ResultSetMetaData rsmd = rs.getMetaData();
			int colcount = rsmd.getColumnCount();
			if (firstRecord == 0) {
				HSSFCellStyle style = wb.createCellStyle();
				style.setFillForegroundColor(HSSFColor.LIME.index);
				style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				HSSFFont font = wb.createFont();
				font.setColor(HSSFColor.RED.index);
				style.setFont(font);

				cell.setCellStyle(style);
				row = spreadsheet1.createRow(rowid++);
				for (cellid = 1; cellid < colcount; cellid++) {

					Cell cell1 = row.createCell(cellid);
					cell1.setCellStyle(style);
					cell1.setCellValue((String) rs.getMetaData().getColumnName(cellid).toString());
				}
				firstRecord = 1;
			}

			HSSFCellStyle style = wb.createCellStyle();
			style.setFillForegroundColor(HSSFColor.BRIGHT_GREEN.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			HSSFFont font = wb.createFont();
			font.setColor(HSSFColor.ROYAL_BLUE.index);
			style.setFont(font);

			cell.setCellStyle(style);
			row = spreadsheet1.createRow(rowid++);
			for (cellid = 1; cellid < colcount; cellid++) {
				Cell cell1 = row.createCell(cellid);
				String cellType = rs.getMetaData().getColumnTypeName(cellid);
				try {

					String Value = (String) rs.getObject(cellid).toString();

					cell1.setCellValue((String) rs.getObject(cellid).toString());
				} catch (Exception e) {

					cell1.setCellValue("NULL");
				}
			}
			for (cellid = 1; cellid < colcount; cellid++) {
				spreadsheet1.autoSizeColumn(cellid);
			}
		}
		String ts = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		FileOutputStream outputStream = new FileOutputStream(
				"D:\\vaibhav\\PLM_Automation\\results\\" + interfaceNumber + "_audits_" + ts + ".xls");
		// D:\\vaibhav\\PLM_Automation\\results\\"+interfaceNumber+"_"+ts+".xls"
		wb.write(outputStream);
	}
}
