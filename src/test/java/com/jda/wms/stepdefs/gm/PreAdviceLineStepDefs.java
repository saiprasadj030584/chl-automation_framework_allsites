package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.PreAdviceLineDB;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.PreAdviceLineMaintenancePage;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Given;

public class PreAdviceLineStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Context context;
	private Verification verification;
	private PreAdviceLineDB  preAdviceLineDB;
	private UPIReceiptLineDB  upiReceiptLineDB;
	private SkuDB skuDB;
	private JDALoginStepDefs jdaLoginStepDefs;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private PreAdviceLineMaintenancePage preAdviceLineMaintenancePage;
	private JDAFooter jdaFooter;
	
	@Inject
	public PreAdviceLineStepDefs(Context context,Verification verification,PreAdviceLineDB  preAdviceLineDB,UPIReceiptLineDB  upiReceiptLineDB,SkuDB skuDB,JDALoginStepDefs jdaLoginStepDefs,JDAHomeStepDefs jdaHomeStepDefs,PreAdviceLineMaintenancePage preAdviceLineMaintenancePage,JDAFooter jdaFooter) {
		this.context = context;
		this.verification = verification;
		this.preAdviceLineDB= preAdviceLineDB;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.skuDB = skuDB;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.preAdviceLineMaintenancePage = preAdviceLineMaintenancePage;
		this.jdaFooter = jdaFooter;
	}


	@Given("^the PO should have sku, quantity due details$")
	public void the_PO_should_have_sku_quantity_due_details() throws Throwable {
		ArrayList failureList = new ArrayList();
		ArrayList skuFromPO = new ArrayList();
		ArrayList skuFromUPI = new ArrayList();
		Map<Integer, Map<String, String>> POMap = new HashMap<Integer, Map<String, String>>();
		Map<String, Map<String, String>> UPIMap = new HashMap<String, Map<String, String>>();
		
		skuFromPO = preAdviceLineDB.getSkuIdList(context.getPreAdviceId());
		skuFromUPI = upiReceiptLineDB.getSkuIdList(context.getUpiId());
		
		if (!skuFromPO.containsAll(skuFromUPI)){
			for (int i=0;i<skuFromPO.size();i++){
				if (!skuFromPO.get(i).equals(skuFromUPI.get(i))){
					failureList.add("SKU id "+skuFromPO.get(i)+" from PreAdvice does not match with SKU id "+skuFromUPI+" from UPI for line item "+i);
				}
			}
		}
		else{
			//Add SKU details to PO Map
			for (int i=1;i<=context.getNoOfLines();i++){
				Map<String, String> lineItemsMap = new HashMap<String, String>();
				context.setSkuId((String)skuFromPO.get(i-1));
				lineItemsMap.put("SKU", context.getSkuId());
				lineItemsMap.put("QTY DUE",preAdviceLineDB.getQtyDue(context.getPreAdviceId(), context.getSkuId()));
				lineItemsMap.put("LINE ID",preAdviceLineDB.getLineId(context.getPreAdviceId(), context.getSkuId()));
				POMap.put(i, lineItemsMap);
			}
			context.setPOMap(POMap);
			
			//Add SKU details to UPI Map
			for (int i=1;i<=context.getNoOfLines();i++){
				Map<String, String> lineItemsMap = new HashMap<String, String>();
				context.setSkuId((String)skuFromPO.get(i-1));
//				lineItemsMap.put("SKU", context.getSkuId());
				lineItemsMap.put("QTY DUE",upiReceiptLineDB.getQtyDue(context.getUpiId(),context.getSkuId()));
				lineItemsMap.put("LINE ID",upiReceiptLineDB.getLineId(context.getUpiId(),context.getSkuId()));
				lineItemsMap.put("PACK CONFIG",upiReceiptLineDB.getPackConfig(context.getUpiId(),context.getSkuId()));
				lineItemsMap.put("UPC", "");
				UPIMap.put(context.getSkuId(), lineItemsMap);
			}
			context.setUPIMap(UPIMap);
			
			System.out.println("PO Map "+context.getPOMap());
			System.out.println("UPI Map "+context.getUPIMap());
			
			//To Validate Modularity,New Product Check for SKU
			String type = null;
			switch (context.getSKUType()){
			case "Boxed": type="B";break;
			case "Hanging": type="H";break;
			}
			verification.verifyData("SKU Type", type, skuDB.getSKUType(context.getSkuId()), failureList);
			verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue(context.getSkuId()), failureList);
		}
		
		Assert.assertTrue("PO line item attributes not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].",failureList.isEmpty());

	}
	
	@Given("^I click on user defined tab$")
	public void i_click_on_user_defined_tab() throws Throwable {
		preAdviceLineMaintenancePage.clickUserDefinedTab();
	}
	
	
	@Given("^I click on general tab$")
	public void i_click_on_general_tab() throws Throwable {
		preAdviceLineMaintenancePage.clickGeneralTab();
	}
	
	@Given("^I lock the product with lock code \"([^\"]*)\"$")
	public void i_lock_the_product_with_lock_code(String lockCode) throws Throwable {
		context.setLockCode(lockCode);
//		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_dispatcher_food_application();
		jdaHomeStepDefs.i_am_on_to_pre_advice_line_maintenance_page();
		jdaFooter.clickQueryButton();
		preAdviceLineMaintenancePage.enterPreAdviceID(context.getPreAdviceId());
		jdaFooter.clickExecuteButton();
		i_click_on_user_defined_tab();
		jdaFooter.clickUpdateButton();
//		preAdviceLineMaintenancePage.pressTab();
		String userDefType3 = getUserDefinedType3(lockCode);
		String userDefType4 = getUserDefinedType4(lockCode);
		String fireWallCheck = isUserDefCheck1Required(lockCode);
		
		if(null!=userDefType3){
			preAdviceLineMaintenancePage.updateUserDefinedType3(userDefType3);
		}
		
		if(null!=userDefType4){
			preAdviceLineMaintenancePage.updateUserDefinedType4(userDefType4);
		}
		
		if((null!=fireWallCheck)&&(fireWallCheck.equals("Y"))){
			preAdviceLineMaintenancePage.updateUserDefinedCheck1();
		}
		jdaFooter.clickExecuteButton();
		jdaFooter.PressEnter();
		i_click_on_general_tab();
		Assert.assertEquals("Lock Code is not updated as expected",lockCode,preAdviceLineDB.getLockCode(context.getPreAdviceId()));
	}


	private String getUserDefinedType3(String lockCode) {
		String userDefType3 = null;
		switch(lockCode){
		case "QAFTS": userDefType3 = "FIRST TIME SEEN"; break;
		case "QACOMP": userDefType3 = "COMP"; break;
		case "QAPC": userDefType3 = "PRICE CHECK"; break;
		case "QAFTSFWL": userDefType3 = "FIRST TIME SEEN"; break;
		case "QAPCFWL": userDefType3 = "PRICE CHECK"; break;
		case "QAFTSRW": userDefType3 = "FIRST TIME SEEN"; break;
		case "QACOMPRW": userDefType3 = "COMP"; break;
		case "QAPCRW": userDefType3 = "PRICE CHECK"; break;
		case "QAFTSFWLRW": userDefType3 = "FIRST TIME SEEN"; break;
		case "QACOMFWLRW": userDefType3 = "COMP"; break;
		case "QAPCFWLRW": userDefType3 = "PRICE CHECK"; break;
		default: userDefType3 = null;break;
	}
		return userDefType3;
}
	
	private String getUserDefinedType4(String lockCode) {
		String userDefType4 = null;
		switch(lockCode){
		case "REWORK": userDefType4 = "REWORK"; break;
		case "QAFTSRW": userDefType4 = "REWORK"; break;
		case "QACOMPRW": userDefType4 = "REWORK"; break;
		case "QAPCRW": userDefType4 = "REWORK"; break;
		case "FWLRW": userDefType4 = "REWORK"; break;
		case "QAFTSFWLRW": userDefType4 = "REWORK"; break;
		case "QACOMFWLRW": userDefType4 = "REWORK"; break;
		case "QAPCFWLRW": userDefType4 = "REWORK"; break;
		default: userDefType4 = null;break;
	}
		return userDefType4;
}
	
	private String isUserDefCheck1Required(String lockCode) {
		String fireWallCheck = null;
		switch(lockCode){
		case "FWL": fireWallCheck = "Y"; break;
		case "QAFTSFWL": fireWallCheck = "Y"; break;
		case "QAPCFWL": fireWallCheck = "Y"; break;
		case "FWLRW": fireWallCheck = "Y"; break;
		case "QAFTSFWLRW": fireWallCheck = "Y"; break;
		case "QACOMFWLRW": fireWallCheck = "Y"; break;
		case "QAPCFWLRW": fireWallCheck = "Y"; break;
	}
		return fireWallCheck;
}
}
