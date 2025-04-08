package com.jda.wms.handlers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.codehaus.plexus.util.StringUtils;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Database;
import com.jda.wms.utils.Utilities;

public class DataHandler {
	private static Context context;
	private static Database database;

	@Inject
	public DataHandler(Context context, Database database) {
		this.context = context;
		this.database = database;

	}

	public void ReadDatafromCsv() throws InterruptedException, IOException, ClassNotFoundException, SQLException {
		String customer_id = null;
		String upc = null;
		System.out.println("enter ReadData");

		ArrayList<String> source_id_list = new ArrayList<String>();
		ArrayList<String> hub_id_list = new ArrayList<String>();
		ArrayList<String> customer_id_list = new ArrayList<String>();
		ArrayList<String> sku_id_list = new ArrayList<String>();
		ArrayList<String> upc_list = new ArrayList<String>();
		ArrayList<String> supplier_id_list = new ArrayList<String>();
		ArrayList<String> line_id_list = new ArrayList<String>();
		ArrayList<String> qty_due_list = new ArrayList<String>();
		ArrayList<String> odn_list = new ArrayList<String>();
		ArrayList<String> urn_advice_list = new ArrayList<String>();
		ArrayList<String> sto_list = new ArrayList<String>();
		ArrayList<String> asn_id_list = new ArrayList<String>();
		ArrayList<String> master_urn_list = new ArrayList<String>();

		String path = "C:/Automation_supporting_files/SkuData/SkuData.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			boolean firstLine = true;
			// BufferedReader br = new BufferedReader(new FileReader(path));
			for (String line; (line = br.readLine()) != null;) {
				System.out.println("entered for");
				if (firstLine) {
					firstLine = false;
					line = br.readLine();
				}
				line = line.trim();
				StringTokenizer st = new StringTokenizer(line);
				ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(
						Arrays.asList(line.trim().split(",")));

				String sku = String.format("%0" + (18 - myList.get(3).length()) + "d%s", 0, myList.get(3));
				int upcLength = (8 - myList.get(4).length());
				int customerIdLength = (4 - myList.get(2).length());
				if (upcLength != 0) {
					upc = String.format("%0" + (8 - myList.get(4).length()) + "d%s", 0, myList.get(4));
				} else {
					upc = myList.get(4);
				}

				if (customerIdLength != 0) {
					customer_id = String.format("%0" + (4 - myList.get(2).length()) + "d%s", 0, myList.get(2));
				} else {
					customer_id = myList.get(2);
				}

				source_id_list.add(myList.get(0));
				hub_id_list.add(myList.get(1));
				customer_id_list.add(customer_id);
				sku_id_list.add(sku);
				upc_list.add(upc);
				supplier_id_list.add(myList.get(5));
				line_id_list.add(myList.get(6));
				qty_due_list.add(myList.get(7));
				if (myList.get(8).equals("0")) {
					odn_list.add("");
				} else {
					odn_list.add(myList.get(8));
				}
				;
				urn_advice_list.add(myList.get(9));
				if (myList.get(10).equals("0")) {
					sto_list.add("");
				} else {
					sto_list.add(myList.get(10));
				}
				;
				asn_id_list.add(myList.get(11));
				if (myList.get(12).equals("0")) {
					master_urn_list.add("");
				} else {
					master_urn_list.add(myList.get(12));
				}
				;
			}
			context.setSkuIDList(sku_id_list);
			context.setSourceIDList(source_id_list);
			context.sethubList(hub_id_list);
			context.setUpcList(upc_list);
			context.setCustomerList(customer_id_list);
			context.setSupplierList(supplier_id_list);
			context.setLineIdList(line_id_list);
			context.setQtyDue(qty_due_list);
			context.setOdnList(odn_list);
			context.seturnAdviceList(urn_advice_list);
			context.setStoList(sto_list);
			context.setAsnIdList(asn_id_list);
			context.setmasterUrnList(master_urn_list);
			context.setSkuDataSize(sku_id_list.size());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	public void loadToSkuData() throws InterruptedException, IOException, ClassNotFoundException, SQLException {
		String deletequery = "delete from sku_data";

		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		System.out.println("statement");
		stmt.executeQuery(deletequery);
		for (int i = 0; i < context.getSkuDataSize(); i++) {
			System.out.println("source ID" + context.getSourceIDList().get(i));

			// String updatequery1 = "Insert into SKU_DATA
			// (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,UPC,SUPPLIER_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN)
			// values
			// ('"+context.getSourceIDList().get(i)+"','"+context.getHubList().get(i)+"','"+context.getCustomerList().get(i)+"','0000000000"+context.getSkuIDList().get(i)+"','0"+context.getUpcList().get(i)+"','"+context.getSupplierList().get(i)+"','"+context.getLineIdList().get(i)+"','"+context.getQtyDue().get(i)+"','"+context.getOdnList().get(i)+"','"+context.getUrnAdviceList().get(i)+"','"+context.getStoList().get(i)+"','"+context.getAsnIdList().get(i)+"','"+context.getMasterUrnList().get(i)+"')";
			String updatequery1 = "Insert into SKU_DATA (SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,UPC,SUPPLIER_ID,LINE_ID,QTY_DUE,ODN,URN_ADVICE,STO,ASN_ID,MASTER_URN) values ('"
					+ context.getSourceIDList().get(i) + "','" + context.getHubList().get(i) + "','"
					+ context.getCustomerList().get(i) + "','" + context.getSkuIDList().get(i) + "','"
					+ context.getUpcList().get(i) + "','" + context.getSupplierList().get(i) + "','"
					+ context.getLineIdList().get(i) + "','" + context.getQtyDue().get(i) + "','"
					+ context.getOdnList().get(i) + "','" + context.getUrnAdviceList().get(i) + "','"
					+ context.getStoList().get(i) + "','" + context.getAsnIdList().get(i) + "','"
					+ context.getMasterUrnList().get(i) + "')";
			stmt.executeQuery(updatequery1);
			System.out.println(updatequery1);

		}
		context.getConnection().commit();
		System.out.println("SKU_DATA table is updated");
	}

	public void Load_data_to_wms_database(String file_name) throws InterruptedException, IOException {

		@SuppressWarnings("static-access")

		Process p = Runtime.getRuntime()
				.exec("cmd /c C:/Automation_supporting_files/LnkFiles/Load_Data/" + file_name + ".lnk");
		Thread.sleep(5000);
		p.waitFor(30, TimeUnit.SECONDS);
		System.out.println("Script Executed successfully");

	}

	public String getNineDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		String r1 = String.valueOf(r.nextInt(900000) + 100000000);
		// return Int.toString(valueOf(r.nextInt(900000) + 100000000));
		return r1;
	}

	public String randomPallet() {
		String palletdigit = Utilities.getsevenDigitRandomNumber();
		String toPallet = "P" + palletdigit;
		return toPallet;

	}

	public String getEightDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		String r1 = String.valueOf(r.nextInt(90000) + 10000000);
		// return Int.toString(valueOf(r.nextInt(9000) + 1000000));
		return r1;
	}

}