package com.jda.wms.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.StringTokenizer;
import com.jda.wms.context.Context;



public class tesetExecutionFile {
	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException{
		//ArrayList<String> name = getandDisplayData();
		//System.out.println("executed success" + name);
		ArrayList<String> source = UpdatemultiSkuDataDirectFile();
		System.out.println("executed success" + source);
    
	}


	public  static ArrayList<String> getandDisplayData(){
		BufferedReader br = null;
		String[][] output = new String[50][4];
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> age = new ArrayList<String>();
		ArrayList<String> company = new ArrayList<String>();

		int row = 0;
		boolean firstLine = true;
		try {
			br = new BufferedReader(new FileReader("D:/Sai Prasad/SupportingFiles/testSheet.csv"));
			String line = br.readLine();
			System.out.println(line);
			while (line != null) {
				if (firstLine) {
					firstLine = false;
					line = br.readLine();
					continue;
				}
				String[] parts = line.split(",");
				for (int i = 0; i < 3; i++) {
					output[row][i] = parts[i];
					System.out.println(output[row][i]);

				}
				name.add(output[row][0]);
				age.add(output[row][1]);
				company.add(output[row][2]);
				line = br.readLine();				
				row++;
			}
			br.close();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return name;		
	}



	/*public static void UpdatemultiSkuDataDirectFile()
			throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

		String path = "D:/Sai Prasad/SupportingFiles/testSheet.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			for (String line; (line = br.readLine()) != null;) {
				// System.out.print(line + "\n");
				line = line.trim();
				System.out.print(line + "\n");
				// String SKUID
				StringTokenizer st = new StringTokenizer(line);
				ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(
						Arrays.asList(line.trim().split(",")));

				for (int i = 0; i < myList.size(); i++) {
					System.out.println("the CSV file has:" + myList.get(i));
				}
			}
		}
	}*/
	public static ArrayList<String> UpdatemultiSkuDataDirectFile()
			throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

		
		ArrayList<String> source_id = new ArrayList<String>();
		ArrayList<String> hub_id = new ArrayList<String>();
		ArrayList<String> customer_id = new ArrayList<String>();
		ArrayList<String> sku_id = new ArrayList<String>();
		ArrayList<String> upc = new ArrayList<String>();
		ArrayList<String> supplier_id = new ArrayList<String>();		
		ArrayList<String> line_id = new ArrayList<String>();
		ArrayList<String> qty_due = new ArrayList<String>();
		ArrayList<String> odn = new ArrayList<String>();
		ArrayList<String> urn_advice = new ArrayList<String>();
		ArrayList<String> sto = new ArrayList<String>();
		ArrayList<String> asn_id = new ArrayList<String>();
		ArrayList<String> master_urn = new ArrayList<String>();
		
		String path = "D:/Sai Prasad/SupportingFiles/SkuData.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			boolean firstLine =true;
			for (String line; (line = br.readLine()) != null;)  {
				if (firstLine) {
					firstLine = false;					
					line = br.readLine();				
				}
				line = line.trim();
				StringTokenizer st = new StringTokenizer(line);
				ArrayList<String> myList = (ArrayList<String>) new ArrayList<String>(
						Arrays.asList(line.trim().split(",")));

				for (int i = 0; i < myList.size(); i++) {
					System.out.println("the CSV file has:" + myList.get(i));					
					}	
				/*context.setSkuListForInventory(skuList);
				context.setSkuIDList(skuList);
				context.getSkuIDList().addAll(skuList);
				context.setQtyListForInventory(QtyList);
				context.setQtyList(QtyList);
				context.getQtyList().addAll(QtyList);
				context.setCustmList(CustmList);
				context.getCustmList().addAll(CustmList);*/
				source_id.add(myList.get(0));
				hub_id.add(myList.get(1));
				customer_id.add(myList.get(2));		
				sku_id.add(myList.get(3));
				upc.add(myList.get(4));
				supplier_id.add(myList.get(5));	
				line_id.add(myList.get(6));
				qty_due.add(myList.get(7));
				odn.add(myList.get(8));	
				urn_advice.add(myList.get(9));
				sto.add(myList.get(10));
				asn_id.add(myList.get(11));
				master_urn.add(myList.get(12));
				
			}	
		}catch (Exception ex) {
			System.out.println(ex.toString());	
		}
		return source_id;
	}
}
	
	
		
	


