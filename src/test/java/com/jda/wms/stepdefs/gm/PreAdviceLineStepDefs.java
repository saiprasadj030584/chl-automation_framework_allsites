package com.jda.wms.stepdefs.gm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.PreAdviceHeaderDB;
import com.jda.wms.db.gm.PreAdviceLineDB;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.SupplierSkuDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.PreAdviceLineMaintenancePage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;

public class PreAdviceLineStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Context context;
	private Verification verification;
	private PreAdviceLineDB preAdviceLineDB;
	private UPIReceiptLineDB upiReceiptLineDB;
	private PreAdviceHeaderDB preAdviceHeaderDB;
	private SkuDB skuDB;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private JDALoginStepDefs jdaLoginStepDefs;
	private PreAdviceLineMaintenancePage preAdviceLineMaintenancePage;
	private JDAFooter jdaFooter;
	private SupplierSkuDB supplierSkuDb;
	

	@Inject
	public PreAdviceLineStepDefs(Context context, Verification verification, PreAdviceLineDB preAdviceLineDB,
			UPIReceiptLineDB upiReceiptLineDB, SkuDB skuDB, JDALoginStepDefs jdaLoginStepDefs,
			JDAHomeStepDefs jdaHomeStepDefs, PreAdviceLineMaintenancePage preAdviceLineMaintenancePage,
			JDAFooter jdaFooter,PreAdviceHeaderDB preAdviceHeaderDB,SupplierSkuDB supplierSkuDb) {
		this.context = context;
		this.verification = verification;
		this.preAdviceLineDB = preAdviceLineDB;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.skuDB = skuDB;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
		this.preAdviceLineMaintenancePage = preAdviceLineMaintenancePage;
		this.jdaFooter = jdaFooter;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.supplierSkuDb=supplierSkuDb;
	}

	@Given("^the PO should have sku, quantity due details$")
	public void the_PO_should_have_sku_quantity_due_details() throws Throwable {
		System.out.println("entered");
		ArrayList failureList = new ArrayList();
		ArrayList skuFromPO = new ArrayList();
		ArrayList skuFromUPI = new ArrayList();
		Map<Integer, Map<String, String>> POMap = new HashMap<Integer, Map<String, String>>();
		Map<String, Map<String, String>> UPIMap = new HashMap<String, Map<String, String>>();

		skuFromPO = preAdviceLineDB.getSkuIdList(context.getPreAdviceId());
		context.setSkuFromPO(skuFromPO);
		skuFromUPI = upiReceiptLineDB.getSkuIdList(context.getUpiId());
		context.setSkuFromUPI(skuFromUPI);

			for (int i = 1; i <= context.getNoOfLines(); i++) {
				Map<String, String> lineItemsMap = new HashMap<String, String>();
				context.setSkuId((String) skuFromPO.get(i - 1));
				lineItemsMap.put("SKU", context.getSkuId());
				lineItemsMap.put("QTY DUE", preAdviceLineDB.getQtyDue(context.getPreAdviceId(), context.getSkuId()));
				lineItemsMap.put("LINE ID", preAdviceLineDB.getLineId(context.getPreAdviceId(), context.getSkuId()));
				POMap.put(i, lineItemsMap);
			}
			context.setPOMap(POMap);
			System.out.println(context.getPOMap());

			// Add SKU details to UPI Map
			for (int i = 1; i <= context.getNoOfLines(); i++) {
				Map<String, String> lineItemsMap = new HashMap<String, String>();
				context.setSkuId((String) skuFromPO.get(i - 1));
				lineItemsMap.put("QTY DUE", upiReceiptLineDB.getQtyDue(context.getUpiId(), context.getSkuId()));
				lineItemsMap.put("LINE ID", upiReceiptLineDB.getLineId(context.getUpiId(), context.getSkuId()));
				lineItemsMap.put("PACK CONFIG", upiReceiptLineDB.getPackConfig(context.getUpiId(), context.getSkuId()));
				String supplierId=supplierSkuDb.getSupplierIdWithSku(context.getSkuId());
				lineItemsMap.put("SUPPLIER ID", supplierId);
				lineItemsMap.put("UPC", supplierSkuDb.getSupplierSKU(context.getSkuId(),supplierId));
				UPIMap.put(context.getSkuId(),lineItemsMap);
			}
			context.setUPIMap(UPIMap);
			System.out.println(context.getUPIMap());
			
			


			// To Validate Modularity,New Product Check for SKU
			String type = null;
			switch (context.getSKUType()) {
			case "Boxed":
				type = "B";
				break;
			case "Hanging":
				type = "H";
				break;
			}
			verification.verifyData("SKU Type", type, skuDB.getSKUType(context.getSkuId()), failureList);
			verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue(context.getSkuId()), failureList);
		

		Assert.assertTrue(
				"PO line item attributes not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	@Given("^the PO with multiple upi should have sku, quantity due details$")
	public void the_PO_with_multiple_upi_should_have_sku_quantity_due_details() throws Throwable {
		ArrayList failureList = new ArrayList();
		ArrayList skuFromPO = new ArrayList();
		ArrayList skuFromUPI = new ArrayList();
		Map<Integer, Map<String, String>> POMap = new HashMap<Integer, Map<String, String>>();
		
		Map<String, Map<String, Map<String, String>>> MultipleUPIMap = new HashMap<String, Map<String, Map<String, String>>>();

		skuFromPO = preAdviceLineDB.getSkuIdList(context.getPreAdviceId());
		skuFromUPI = upiReceiptLineDB.getSkuIdList(context.getUpiList());

			for (int i = 1; i <= context.getNoOfLines(); i++) {
				
				Map<String, String> lineItemsMap = new HashMap<String, String>();
				context.setSkuId((String) skuFromPO.get(i - 1));
				lineItemsMap.put("SKU", context.getSkuId());
				lineItemsMap.put("QTY DUE", preAdviceLineDB.getQtyDue(context.getPreAdviceId(), context.getSkuId()));
				lineItemsMap.put("LINE ID", preAdviceLineDB.getLineId(context.getPreAdviceId(), context.getSkuId()));
				POMap.put(i, lineItemsMap);
			}
			context.setPOMap(POMap);
			
			for (int j = 0; j < context.getUpiList().size(); j++) {
				Map<String, Map<String, String>> skuMap = new HashMap<String, Map<String, String>>();
				for (int i = 1; i <= context.getNoOfLines(); i++) {
					context.setSkuId((String) skuFromPO.get(i - 1));
					Map<String, String> lineItemsMap = new HashMap<String, String>();
					lineItemsMap.put("QTY DUE", upiReceiptLineDB.getQtyDue(context.getUpiList().get(j), context.getSkuId()));
					lineItemsMap.put("LINE ID", upiReceiptLineDB.getLineId(context.getUpiList().get(j), context.getSkuId()));
					lineItemsMap.put("PACK CONFIG", upiReceiptLineDB.getPackConfig(context.getUpiList().get(j), context.getSkuId()));
					lineItemsMap.put("UPC", "");
					skuMap.put(context.getSkuId(), lineItemsMap);
				}
				MultipleUPIMap.put((String) context.getUpiList().get(j), skuMap);
			// To Validate Modularity,New Product Check for SKU
			String type = null;
			switch (context.getSKUType()) {
			case "Boxed":
				type = "B";
				break;
			case "Hanging":
				type = "H";
				break;
			}
			verification.verifyData("SKU Type", type, skuDB.getSKUType(context.getSkuId()), failureList);
			verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue(context.getSkuId()), failureList);
		}
			context.setMultipleUPIMap(MultipleUPIMap);
	}
	
	@Given("^the multiple PO with multiple upi should have sku, quantity due details$")
	public void the_multiple_PO_with_multiple_upi_should_have_sku_quantity_due_details() throws Throwable {
		ArrayList failureList = new ArrayList();
		ArrayList skuFromPO = new ArrayList();
		ArrayList skuFromUPI = new ArrayList();
		Map<String,String> poNumLinesMap=new HashMap<String,String>();
		Map<Integer, Map<String, String>> POMap=new HashMap<Integer, Map<String, String>>();
		Map<String, Map<String, Map<String, String>>> MultipleUPIMap = new HashMap<String, Map<String, Map<String, String>>>();
		Map<String, Map<Integer, Map<String, String>>> MultiplePOMap = new HashMap<String, Map<Integer, Map<String, String>>>();

		for(int i=0;i<context.getPreAdviceList().size();i++){
		skuFromPO.addAll(preAdviceLineDB.getSkuIdList(context.getPreAdviceList().get(i)));
		}
		context.setSkuFromPO(skuFromPO);
		
		for(int i=0;i<context.getUpiList().size();i++){
		skuFromUPI.addAll(upiReceiptLineDB.getSkuIdList(context.getUpiList().get(i)));
		}
		context.setSkuFromUPI(skuFromUPI);

		int m=0,n=0;
		for (int j = 0; j <context.getPreAdviceList().size(); j++) {
			for (int i = 1; i <=Integer.parseInt(context.getPoNumLinesMap().get(context.getPreAdviceList().get(j))); i++) {
				m++;
				Map<String, String> lineItemsMap = new HashMap<String, String>();
				POMap= new HashMap<Integer, Map<String, String>>();
				context.setSkuId((String) skuFromPO.get(m - 1));
				lineItemsMap.put("SKU", context.getSkuId());
				lineItemsMap.put("QTY DUE", preAdviceLineDB.getQtyDue(context.getPreAdviceList().get(j), context.getSkuId()));
				lineItemsMap.put("LINE ID", preAdviceLineDB.getLineId(context.getPreAdviceList().get(j), context.getSkuId()));
				POMap.put(i, lineItemsMap);
			}
			System.out.println("POMap "+POMap);
			MultiplePOMap.put(context.getPreAdviceList().get(j), POMap);
			System.out.println("MultiplePOMap "+MultiplePOMap);
		}
			context.setMultiplePOMap(MultiplePOMap);
			
			for (int j = 0; j < context.getUpiList().size(); j++) {
				Map<String, Map<String, String>> skuMap = new HashMap<String, Map<String, String>>();
				for (int i = 1; i <=context.getUpiNumLinesMap().get(context.getUpiList().get(j)); i++) {
					n++;
					context.setSkuId((String) skuFromUPI.get(n - 1));
					Map<String, String> lineItemsMap = new HashMap<String, String>();
					lineItemsMap.put("SKU",context.getSkuId());
					lineItemsMap.put("QTY DUE", upiReceiptLineDB.getQtyDue(context.getUpiList().get(j), context.getSkuId()));
					lineItemsMap.put("LINE ID", upiReceiptLineDB.getLineId(context.getUpiList().get(j), context.getSkuId()));
					lineItemsMap.put("PACK CONFIG", upiReceiptLineDB.getPackConfig(context.getUpiList().get(j), context.getSkuId()));
					lineItemsMap.put("PART SET", skuDB.getPartSet(context.getSkuId()));
					String supplierId=supplierSkuDb.getSupplierIdWithSku(context.getSkuId());
					lineItemsMap.put("SUPPLIER ID", supplierId);
					lineItemsMap.put("UPC", supplierSkuDb.getSupplierSKU(context.getSkuId(),supplierId));
					skuMap.put(context.getSkuId(), lineItemsMap);
				}
				MultipleUPIMap.put((String) context.getUpiList().get(j), skuMap);
				context.setMultipleUPIMap(MultipleUPIMap);
				System.out.println(context.getMultipleUPIMap());
			
				// To Validate Modularity,New Product Check for SKU
				for (int i=0;i<skuFromPO.size();i++){
					String type = null;
					switch (context.getSKUType()) {
					case "Boxed":
						type = "B";
						break;
					case "Hanging":
						type = "H";
						break;
					}
					verification.verifyData("SKU Type", type, skuDB.getSKUType(context.getSkuId()), failureList);
					verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue(context.getSkuId()), failureList);
				}
		}
			Assert.assertTrue(
					"PO & UPI line item attributes not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
					failureList.isEmpty());
	}

	
	@Given("^the upi should have sku, quantity due details$")
	public void the_upi_should_have_sku_quantity_due_details() throws Throwable {
		ArrayList failureList = new ArrayList();
		ArrayList skuFromUPI = new ArrayList();
		Map<String, Map<String, String>> UPIMap = new HashMap<String, Map<String, String>>();
		context.setRcvQtyDue(0);

		skuFromUPI = upiReceiptLineDB.getSkuIdList(context.getUpiId());
		context.setSkuFromUPI(skuFromUPI);
		String supplierId;

			// Add SKU details to UPI Map
			for (int i = 1; i <= context.getNoOfLines(); i++) {
				Map<String, String> lineItemsMap = new HashMap<String, String>();
				context.setSkuId((String) skuFromUPI.get(i - 1));
				lineItemsMap.put("QTY DUE", upiReceiptLineDB.getQtyDue(context.getUpiId(), context.getSkuId()));
				context.setRcvQtyDue(context.getRcvQtyDue()+Integer.parseInt(upiReceiptLineDB.getQtyDue(context.getUpiId(), context.getSkuId())));
				lineItemsMap.put("LINE ID", upiReceiptLineDB.getLineId(context.getUpiId(), context.getSkuId()));
				lineItemsMap.put("PACK CONFIG", upiReceiptLineDB.getPackConfig(context.getUpiId(), context.getSkuId()));
				lineItemsMap.put("PART SET", skuDB.getPartSet(context.getSkuId()));
				supplierId=supplierSkuDb.getSupplierIdWithSku(context.getSkuId());
				lineItemsMap.put("SUPPLIER ID", supplierId);
				lineItemsMap.put("UPC", supplierSkuDb.getSupplierSKU(context.getSkuId(),supplierId));
				UPIMap.put(context.getSkuId(),lineItemsMap);
			}
			context.setUPIMap(UPIMap);
			
			
			Assert.assertTrue(
					"PO & UPI line item attributes not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
					failureList.isEmpty());
	}
		
	
	@Given("^the multiple upi should have sku, quantity due details$")
	public void the_multiple_upi_should_have_sku_quantity_due_details() throws Throwable {
		
		ArrayList failureList = new ArrayList();
		ArrayList skuFromUPI = new ArrayList();
		//Map<String, Map<String, String>> UPIMap = new HashMap<String, Map<String, String>>();
		Map<String, Map<String, Map<String, String>>> MultipleUPIMap = new HashMap<String, Map<String, Map<String, String>>>();
		context.setRcvQtyDue(0);

		for(int i=0;i<context.getUpiList().size();i++)
		{
		skuFromUPI.addAll(upiReceiptLineDB.getSkuIdList(context.getUpiList().get(i)));
		
		}
		context.setSkuFromUPI(skuFromUPI);
		String supplierId;

		// Add SKU details to Multiple UPI Map	
		int m=0;
		for (int j = 0; j < context.getUpiList().size(); j++) {
			context.setUpiId(context.getUpiList().get(j));
			Map<String, Map<String, String>> skuMap = new HashMap<String, Map<String, String>>();
			for (int i = 1; i <= context.getUpiNumLinesMap().get(context.getUpiList().get(j)); i++) {
				Map<String, String> lineItemsMap = new HashMap<String, String>();
				m++;
				context.setSkuId((String) skuFromUPI.get(m - 1));
				lineItemsMap.put("QTY DUE", upiReceiptLineDB.getQtyDue(context.getUpiId(), context.getSkuId()));
				context.setRcvQtyDue(context.getRcvQtyDue()+Integer.parseInt(upiReceiptLineDB.getQtyDue(context.getUpiId(), context.getSkuId())));
				lineItemsMap.put("LINE ID", upiReceiptLineDB.getLineId(context.getUpiId(), context.getSkuId()));
				lineItemsMap.put("PACK CONFIG", upiReceiptLineDB.getPackConfig(context.getUpiId(), context.getSkuId()));
				lineItemsMap.put("PART SET", skuDB.getPartSet(context.getSkuId()));
				supplierId=supplierSkuDb.getSupplierIdWithSku(context.getSkuId());
				lineItemsMap.put("SUPPLIER ID", supplierId);
				lineItemsMap.put("UPC", supplierSkuDb.getSupplierSKU(context.getSkuId(),supplierId));
				skuMap.put(context.getSkuId(), lineItemsMap);
			}
			MultipleUPIMap.put((String) context.getUpiList().get(j), skuMap);
			context.setMultipleUPIMap(MultipleUPIMap);
			System.out.println(context.getMultipleUPIMap());
		
			// To Validate Modularity,New Product Check for SKU
			for (int i=0;i<skuFromUPI.size();i++){
				String type = null;
				switch (context.getSKUType()) {
				case "Boxed":
					type = "B";
					break;
				case "Hanging":
					type = "H";
					break;
				}
				verification.verifyData("SKU Type", type, skuDB.getSKUType(context.getSkuId()), failureList);
				verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue(context.getSkuId()), failureList);
			}
	}
		Assert.assertTrue(
				"PO & UPI line item attributes not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
			

	





	@Given("^the PO is locked with lockcode \"([^\"]*)\" in pre advice line$")
	public void the_PO_is_locked_with_lockcode_in_pre_advice_line(String lockCode) throws Throwable {
		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		jdaHomeStepDefs.i_am_on_to_pre_advice_line_maintenance_page();
		preAdviceLineMaintenancePage.selectlockcode(lockCode);
		preAdviceLineDB.updateLockCode(context.getPreAdviceId(), lockCode);
	}

	@Given("^I click on user defined tab$")
	public void i_click_on_user_defined_tab() throws Throwable {
		preAdviceLineMaintenancePage.clickUserDefinedTab();
	}
	
	@Given("^I scroll to line item$")
	public void i_scroll_to_next_line_item() throws Throwable {
		preAdviceLineMaintenancePage.clickNextInScroll();
	}

	@Given("^I click on general tab$")
	public void i_click_on_general_tab() throws Throwable {
		preAdviceLineMaintenancePage.clickGeneralTab();
	}

	@Given("^I lock the product with lock code \"([^\"]*)\"$")
	public void i_lock_the_product_with_lock_code(String lockCode) throws Throwable {
		context.setLockCode(lockCode);
		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		jdaHomeStepDefs.i_am_on_to_pre_advice_line_maintenance_page();
		jdaFooter.clickQueryButton();
		preAdviceLineMaintenancePage.enterPreAdviceID(context.getPreAdviceId());
		jdaFooter.clickExecuteButton();
		for( int i=0;i<context.getNoOfLines();i++)
		{
			if(i!=0)
			{
				i_scroll_to_next_line_item();			
			}
		i_click_on_user_defined_tab();
		jdaFooter.clickUpdateButton();
		String userDefType3 = getUserDefinedType3(lockCode);
		String userDefType4 = getUserDefinedType4(lockCode);
		String fireWallCheck = isUserDefCheck1Required(lockCode);

		if (null != userDefType3) {
			preAdviceLineMaintenancePage.updateUserDefinedType3(userDefType3);
		}

		if (null != userDefType4) {
			preAdviceLineMaintenancePage.updateUserDefinedType4(userDefType4);
		}

		if ((null != fireWallCheck) && (fireWallCheck.equals("Y"))) {
			preAdviceLineMaintenancePage.updateUserDefinedCheck1();
		}
		jdaFooter.clickExecuteButton();
		jdaFooter.PressEnter();
		i_click_on_general_tab();
		Assert.assertEquals("Lock Code is not updated as expected", lockCode,
				preAdviceLineDB.getLockCode(context.getPreAdviceId()));
		}
	}

	private String getUserDefinedType3(String lockCode) {
		String userDefType3 = null;
		switch (lockCode) {
		case "QAFTS":
			userDefType3 = "FIRST TIME SEEN";
			break;
		case "QACOMP":
			userDefType3 = "COMP";
			break;
		case "QAPC":
			userDefType3 = "PRICE CHECK";
			break;
		case "QAFTSFWL":
			userDefType3 = "FIRST TIME SEEN";
			break;
		case "QAPCFWL":
			userDefType3 = "PRICE CHECK";
			break;
		case "QAFTSRW":
			userDefType3 = "FIRST TIME SEEN";
			break;
		case "QACOMPRW":
			userDefType3 = "COMP";
			break;
		case "QAPCRW":
			userDefType3 = "PRICE CHECK";
			break;
		case "QAFTSFWLRW":
			userDefType3 = "FIRST TIME SEEN";
			break;
		case "QACOMFWLRW":
			userDefType3 = "COMP";
			break;
		case "QAPCFWLRW":
			userDefType3 = "PRICE CHECK";
			break;
		default:
			userDefType3 = null;
			break;
		}
		return userDefType3;
	}

	private String getUserDefinedType4(String lockCode) {
		String userDefType4 = null;
		switch (lockCode) {
		case "REWORK":
			userDefType4 = "REWORK";
			break;
		case "QAFTSRW":
			userDefType4 = "REWORK";
			break;
		case "QACOMPRW":
			userDefType4 = "REWORK";
			break;
		case "QAPCRW":
			userDefType4 = "REWORK";
			break;
		case "FWLRW":
			userDefType4 = "REWORK";
			break;
		case "QAFTSFWLRW":
			userDefType4 = "REWORK";
			break;
		case "QACOMFWLRW":
			userDefType4 = "REWORK";
			break;
		case "QAPCFWLRW":
			userDefType4 = "REWORK";
			break;
		default:
			userDefType4 = null;
			break;
		}
		return userDefType4;
	}

	private String isUserDefCheck1Required(String lockCode) {
		String fireWallCheck = null;
		switch (lockCode) {
		case "FWL":
			fireWallCheck = "Y";
			break;
		case "QAFTSFWL":
			fireWallCheck = "Y";
			break;
		case "QAPCFWL":
			fireWallCheck = "Y";
			break;
		case "FWLRW":
			fireWallCheck = "Y";
			break;
		case "QAFTSFWLRW":
			fireWallCheck = "Y";
			break;
		case "QACOMFWLRW":
			fireWallCheck = "Y";
			break;
		case "QAPCFWLRW":
			fireWallCheck = "Y";
			break;
		}
		return fireWallCheck;
	}
	
	// FSV receiving
	@Given("^the FSV PO line should have sku, quantity due details$")
	public void the_FSV_PO_line_should_have_sku_quantity_due_details() throws Throwable {
		ArrayList failureList = new ArrayList();
		ArrayList skuFromPO = new ArrayList();
		Map<Integer, Map<String, String>> POMap = new HashMap<Integer, Map<String, String>>();

		skuFromPO = preAdviceLineDB.getSkuIdList(context.getPreAdviceId());

		if (skuFromPO == null) {
			failureList.add("SKU id is not assgined to the PreAdvice id : " + context.getPreAdviceId());
		} else {
			// Add SKU details to PO Map
			for (int i = 1; i <= context.getNoOfLines(); i++) {
				Map<String, String> lineItemsMap = new HashMap<String, String>();
				context.setSkuId((String) skuFromPO.get(i - 1));
				lineItemsMap.put("SKU", context.getSkuId());
				lineItemsMap.put("QTY DUE", preAdviceLineDB.getQtyDue(context.getPreAdviceId(), context.getSkuId()));
				lineItemsMap.put("LINE ID", preAdviceLineDB.getLineId(context.getPreAdviceId(), context.getSkuId()));
				POMap.put(i, lineItemsMap);
			}
			context.setPOMap(POMap);
			System.out.println("PO Map " + context.getPOMap());
			
			ArrayList palletList = new ArrayList();
			ArrayList<String> belCodeList = new ArrayList();
			ArrayList newPalletList = new ArrayList();
			for (int i = 1; i <= context.getNoOfLines(); i++){
				context.setSkuId((String) skuFromPO.get(i-1));
				// To generate Pallet ID
				palletList.add(generatePalletID(context.getPreAdviceId(),context.getSkuId()));
				// To generate Belcode
				belCodeList.add(generateBelCode(context.getPreAdviceId(),context.getSkuId()));
                // To generate newpallet
				newPalletList.add(generateNewPallet());
			}
			
			context.setPalletIDList(palletList);
			context.setBelCodeList(belCodeList);
			context.setNewPallet(newPalletList);

			// To Validate Modularity,New Product Check for SKU
			String type = null;
			switch (context.getSKUType()) {
			case "Boxed":
				type = "B";
				break;
			case "Hanging":
				type = "H";
				break;
			}
			verification.verifyData("SKU Type", type, skuDB.getSKUType(context.getSkuId()), failureList);
			verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue(context.getSkuId()), failureList);
		Assert.assertTrue(
				"PO line item attributes not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	}
	
	@Given("^the FSV PO with \"([^\"]*)\" should have sku, quantity due details$")
	public void the_FSV_PO_with_should_have_sku_quantity_due_details(String lockcode) throws Throwable {
		System.out.println("sgjh"+lockcode);
		ArrayList failureList = new ArrayList();
		ArrayList skuFromPO = new ArrayList();
		Map<Integer, Map<String, String>> POMap = new HashMap<Integer, Map<String, String>>();

		skuFromPO = preAdviceLineDB.getSkuIdList(context.getPreAdviceId());

		if (skuFromPO == null) {
			failureList.add("SKU id is not assgined to the PreAdvice id : " + context.getPreAdviceId());
		} else {
			// Add SKU details to PO Map
			for (int i = 1; i <= context.getNoOfLines(); i++) {
				Map<String, String> lineItemsMap = new HashMap<String, String>();
				context.setSkuId((String) skuFromPO.get(i - 1));
				lineItemsMap.put("SKU", context.getSkuId());
				lineItemsMap.put("QTY DUE", preAdviceLineDB.getQtyDue(context.getPreAdviceId(), context.getSkuId()));
				lineItemsMap.put("LINE ID", preAdviceLineDB.getLineId(context.getPreAdviceId(), context.getSkuId()));
				POMap.put(i, lineItemsMap);
			}
			context.setPOMap(POMap);
			System.out.println("PO Map " + context.getPOMap());
			
			ArrayList palletList = new ArrayList();
			ArrayList<String> belCodeList = new ArrayList();
			ArrayList newPalletList = new ArrayList();
			for (int i = 1; i <= context.getNoOfLines(); i++){
				context.setSkuId((String) skuFromPO.get(i-1));
				// To generate Pallet ID
				palletList.add(generatePalletID(context.getPreAdviceId(),context.getSkuId()));
				// To generate Belcode
				belCodeList.add(generateBelCode(context.getPreAdviceId(),context.getSkuId()));
                // To generate newpallet
				System.out.println(lockcode);
				if(lockcode.equalsIgnoreCase("DMGD"))
				{
				newPalletList.add(generateNewPalletDamaged());
				}
				else
				{
					newPalletList.add(generateNewPalletLockCode());
				}
			}
			
			context.setPalletIDList(palletList);
			context.setBelCodeList(belCodeList);
			context.setNewPallet(newPalletList);

			// To Validate Modularity,New Product Check for SKU
			String type = null;
			switch (context.getSKUType()) {
			case "Boxed":
				type = "B";
				break;
			case "Hanging":
				type = "H";
				break;
			}
			verification.verifyData("SKU Type", type, skuDB.getSKUType(context.getSkuId()), failureList);
			verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue(context.getSkuId()), failureList);
		Assert.assertTrue(
				"PO line item attributes not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	}

	
		
	private String generateNewPallet() {
		String newpallet = "RA" + Utilities.getFourDigitRandomNumber();
 		return newpallet;
	}
	
	private String generateNewPalletLockCode() {
		String newpallet = "QA" + Utilities.getFourDigitRandomNumber();
 		return newpallet;
	}
	private String generateNewPalletDamaged() {
		String newpallet = "SD" + Utilities.getFourDigitRandomNumber();
 		return newpallet;
	}

	private String generatePalletID(String preAdviceId, String skuid) throws ClassNotFoundException, SQLException {
		String palletID = null;
		// First 4 digits - Site id
		String siteid = preAdviceHeaderDB.getSiteID(preAdviceId);
		// Hardcoded 3 digit
		String barcode = Utilities.getThreeDigitRandomNumber();
		// Random generated 6 digit
		String URN = Utilities.getSixDigitRandomNumber();
		//Supplier id : 5 digit
		String supplier = suppliermanipulate(preAdviceId);
		// Dept id : 3 digit
		String dept = deptmanipulate(preAdviceId);
		// Sku quantity  : 3 digit
		String skuqtymanipulate = skuQtyManipulate(preAdviceId,skuid);
		// Advice - 6 digit
		String advice = preAdviceHeaderDB.getUserDefType1(preAdviceId);
		// checkbit - 2 digit
		String checkbit = "10";
		palletID = siteid + barcode + URN + supplier + '0' + dept + advice
				+ skuqtymanipulate + checkbit;
		System.out.println(palletID);
		return palletID;
	}

	// Get supplierid - 4 digit and manipulated to get only integer
	public String suppliermanipulate(String preAdviceId) throws ClassNotFoundException, SQLException {
		String supplier = preAdviceHeaderDB.getSupplierId(preAdviceId);
		String[] supplierSplit = supplier.split("M");
		return supplierSplit[1];
	}

	// Get dept - 3 digit
	public String deptmanipulate(String preAdviceId) throws ClassNotFoundException, SQLException {
		String dept = preAdviceHeaderDB.getUserDefType2(preAdviceId);
		String[] deptSplit = dept.split("T");
		return deptSplit[1];
	}

	public String qtyManipulateForUpi(String preAdviceId) throws ClassNotFoundException, SQLException {
		String sumqty = preAdviceLineDB.getQtyDue(preAdviceId, context.getSkuId());
		String sumLength = String.valueOf(sumqty.length());
		if (sumLength.equals("1")) {
			sumqty = "00" + sumqty;
		} else if (sumLength.equals("2")) {
			sumqty = "0" + sumqty;
		}
		return sumqty;
	}

	private String generateBelCode(String preAdviceId,String skuid) throws ClassNotFoundException, SQLException {
		String belCode = null;
		// Checkdigit : 2 any random number
		String checkdigit = Utilities.getTwoDigitRandomNumber();
		// Supplier code : 5 digit
		String supplier = suppliermanipulate(preAdviceId);
		// UPC : 8 digit
		String upc = preAdviceLineDB.getUpc(context.getSkuId());
		//Quantity : 3 digit
		String skuqtymanipulate= skuQtyManipulate(preAdviceId,skuid);
		// Checkbit hardcoded : 2 digit
		String checkbit = "10";
		belCode = checkdigit + supplier+ upc + skuqtymanipulate + checkbit;
        System.out.println(belCode);
		return belCode;
	}
	
	// individual sku qty assigned to pre advice id : 3 digit
	public String skuQtyManipulate(String preAdviceId,String skuid) throws ClassNotFoundException, SQLException {
		String qtyDue = preAdviceLineDB.getQtyDue(preAdviceId, context.getSkuId());
		int sumLength = qtyDue.length();
		if (sumLength==1) {
			qtyDue = "00" + qtyDue;
		} else if (sumLength==2) {
			qtyDue = "0" + qtyDue;
		}
		return qtyDue;
	}
}
