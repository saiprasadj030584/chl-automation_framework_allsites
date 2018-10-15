package com.jda.wms.stepdefs.Exit;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.context.Context;
import com.jda.wms.dataload.exit.GetTCData;
import com.jda.wms.db.Exit.MoveTaskDB;
import com.jda.wms.db.Exit.PreAdviceHeaderDB;
import com.jda.wms.db.Exit.PreAdviceLineDB;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.pages.Exit.PreAdviceHeaderPage;
import com.jda.wms.pages.Exit.PurchaseOrderReceivingPage;
import com.jda.wms.pages.Exit.PuttyFunctionsPage;
import com.jda.wms.pages.Exit.StoreTrackingOrderPickingPage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class JDAExitputtyfunctionsStepDef {
	private PuttyFunctionsPage puttyFunctionsPage;
	private Configuration configuration;
	private Context context;
	private Object screen;
	private PurchaseOrderReceivingPage purchaseOrderReceivingPage;
	private StoreTrackingOrderPickingPage storeTrackingOrderPickingPage;
	private MoveTaskDB moveTaskDB;
	public static String statusRegion = System.getProperty("USE_DB");
	//public static String region = System.getProperty("REGION");
	public static String region ="SIT";
	public static String host = null;
	public static String port = null;

	@Inject
	public JDAExitputtyfunctionsStepDef(PurchaseOrderReceivingPage purchaseOrderReceivingPage,MoveTaskDB moveTaskDB,StoreTrackingOrderPickingPage storeTrackingOrderPickingPage,PuttyFunctionsPage puttyFunctionsPage, Configuration configuration, Context context) {
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.purchaseOrderReceivingPage = purchaseOrderReceivingPage;
		this.storeTrackingOrderPickingPage=storeTrackingOrderPickingPage;
		this.configuration = configuration;
		this.context = context;
		this.moveTaskDB=moveTaskDB;
	}
	@Given("^I have logged in as warehouse user in putty$")
	public void i_have_logged_in_as_warehouse_user_in_putty() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		puttyFunctionsPage.invokePutty();
		Thread.sleep(5000);
		if (statusRegion == null) {
			statusRegion = "N";
		} else {
			System.out.println("DATABASE Status region---> " + statusRegion);
		}
		if (statusRegion.equalsIgnoreCase("N")) {
			if (region.equalsIgnoreCase("SIT")) {
				host = configuration.getStringProperty("sit-putty-Exit-host");
				port = configuration.getStringProperty("sit-putty-Exit-port");
			} else if (region.equalsIgnoreCase("ST")) {
				host = configuration.getStringProperty("st-putty-foods-host");
				port = configuration.getStringProperty("st-putty-foods-port");
			}
		} else {
			System.out.println("Get environment Details from NPS DB Putty Host:-" + context.getPuttyHost()
					+ "Putty Port:-" + context.getPuttyPort());
			host = context.getPuttyHost();
			port = context.getPuttyPort();
		}
		puttyFunctionsPage.loginPutty(host, port);
		Thread.sleep(4000);
		Assert.assertTrue("Putty Login page not displayed as expected", puttyFunctionsPage.isLoginScreenDisplayed());
		if (statusRegion.equalsIgnoreCase("N")) {
			if (region.equalsIgnoreCase("SIT")) {
				puttyFunctionsPage.enterJdaLogin(configuration.getStringProperty("sit-username"),
						configuration.getStringProperty("sit-password"));
			} else if (region.equalsIgnoreCase("ST")) {
				puttyFunctionsPage.enterJdaLogin(configuration.getStringProperty("st-username"),
						configuration.getStringProperty("st-password"));
			}
		} else {
			System.out.println("Get environment Details from NPS DB UserName:-" + context.getAppUserName()
					+ "Password:-" + context.getAppPassord());
			puttyFunctionsPage.enterJdaLogin(context.getAppUserName(), context.getAppPassord());
		}
		Thread.sleep(3000);

		if (!(puttyFunctionsPage.isMainMenuDisplayed())) {
			failureList.add("Main Menu not displayed as expected");
		}

		Assert.assertTrue("Putty Login not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());

		context.setPuttyLoginFlag(true);
	}
	@Then("^I login as warehouse user in putty$")
	public void i_login_as_warehouse_user_in_putty() throws Throwable {
		i_have_logged_in_as_warehouse_user_in_putty();
	}
	@When("^I select user directed option in main menu$")
	public void i_select_user_directed_option_in_main_menu() throws Throwable {
		purchaseOrderReceivingPage.selectUserDirectedMenu();
	}
	@Given("^I select picking with container pick$")
	public void i_select_picking_with_container_pick() throws Throwable {
//		storeTrackingOrderPickingPage.selectPickingMenu();
		System.out.println("before enter");
		storeTrackingOrderPickingPage.selectPickingMenu();
//		storeTrackingOrderPickingPage.selectPickingMenuForFurtherProcess();
		System.out.println("After enter");
		Assert.assertTrue("Picking Menu not displayed as expected",
				storeTrackingOrderPickingPage.isPickMenuDisplayed());
		storeTrackingOrderPickingPage.selectPickingInPickMenu();
		
		Assert.assertTrue("Pick Task Menu not displayed as expected",
				storeTrackingOrderPickingPage.isPickTaskMenuDisplayed());
		storeTrackingOrderPickingPage.selectContainerPickMenu();
	}
	@Then("^I should be directed to pick entry page$")
	public void i_should_be_directed_to_pick_entry_page() throws Throwable {
		Assert.assertTrue("Pick entry not displayed as expected.",
				storeTrackingOrderPickingPage.isPickEntryDisplayed());
//		String ListId=moveTaskDB.getTag(context.getOrderId());
//		String listId=moveTaskDB.getList(context.getOrderId());
//		context.setListID(listId);
//		storeTrackingOrderPickingPage.enterListID(listId);
//		System.out.println("ListId= " +listId);
//		puttyFunctionsPage.pressEnter();
	}
	@And("^I enter ListId and TagId$")
	public void I_enter_ListId_and_TagId() throws Throwable{
		//String TagId=moveTaskDB.getTag(context.getOrderId());
		String listId=moveTaskDB.getList(context.getOrderId());
		storeTrackingOrderPickingPage.enterListID(listId);
		System.out.println("ListId= " +listId);
		puttyFunctionsPage.pressEnter();
		String TagId="9999";
//		context.setTag(TagId);
		System.out.println("TagId="+TagId);
		storeTrackingOrderPickingPage.enterTagId(TagId);
		Thread.sleep(500);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(500);
		String UPCValue=purchaseOrderReceivingPage.getUPC();//from screen
		System.out.println("upc="+UPCValue);
		String UPCDB=SkuDB.getUPCDB();
		System.out.println("upc="+UPCDB);
//		String actuallist = moveTaskDB.getListID(context.getOrderId());
		String prefixlist=StringUtils.substring(UPCDB, 4, 0);
//		Assert.assertEquals("UPC to be validated", 0, prefixlist);
//		logger.debug("List Id generated with prefix as MANB is : " + actuallist);
//		if (UPCValue.equals(UPCDB)) {
//			System.out.println("validated");}
//		else{
//			System.out.println("upc value not as expected");
//		}
		
		
		puttyFunctionsPage.pressEnter();
		Thread.sleep(500);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(500);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(500);
	
		
	}
	@Given("^I select Receiving menu$")
	public void I_select_Receiving_menu() throws Throwable {
//		storeTrackingOrderPickingPage.selectPickingMenu();
		System.out.println("before enter");
		storeTrackingOrderPickingPage.selectReceivingMenu();
//		storeTrackingOrderPickingPage.selectPickingMenuForFurtherProcess();
		System.out.println("After enter");
		Assert.assertTrue("Receiving Menu not displayed as expected",
		storeTrackingOrderPickingPage.isReceivingMenuDisplayed());
		storeTrackingOrderPickingPage.selectBasicReceivingMenu();
		Assert.assertTrue("Receiving Task Menu not displayed as expected",
		storeTrackingOrderPickingPage.isBasicReceivingMenuDisplayed());
		storeTrackingOrderPickingPage.selectGS1_128ReceiveMenu();
		Assert.assertTrue("GS128Receiving Task Menu not displayed as expected",
		storeTrackingOrderPickingPage.isRcvScnEANCMenuDisplayed());
		GetTCData.getpoId();
		String skuId = "000000000021071852";
		i_generate_pallet_id(GetTCData.getpoId(),skuId);
	}
	@Given("^I generate pallet id$")
	public void i_generate_pallet_id(String preAdviceId, String skuid) throws Throwable {
		System.out.println("preadv"+preAdviceId);
		String palletID = null;
		// First 4 digits - Site id
		String siteid = PreAdviceHeaderDB.getSiteId(preAdviceId);
		System.out.println(siteid);
		// Hardcoded 3 digit
		String barcode = Utilities.getThreeDigitRandomNumber();
		
		// Random generated 6 digit
		String URN = Utilities.getSixDigitRandomNumber();
		// Supplier id : 5 digit
		String supplier = suppliermanipulate(preAdviceId);
		// Dept id : 3 digit
		String dept = deptmanipulate(preAdviceId);
		// Sku quantity : 3 digit
		String skuqtymanipulate = skuQtyManipulate(preAdviceId, skuid);
		// Advice - 6 digit
		String advice = PreAdviceHeaderDB.getUserDefType1(preAdviceId);
		// checkbit - 2 digit
		String checkbit = "10";
		palletID = siteid + barcode + URN + supplier + '0' + dept + advice + skuqtymanipulate + checkbit;
		System.out.println("check"+palletID);
	}
	
//	private String generatePalletID(String preAdviceId, String skuid) throws ClassNotFoundException, SQLException {
//		String palletID = null;
//		// First 4 digits - Site id
//		String siteid = PreAdviceHeaderDB.getSiteId(preAdviceId);
//		// Hardcoded 3 digit
//		String barcode = Utilities.getThreeDigitRandomNumber();
//		// Random generated 6 digit
//		String URN = Utilities.getSixDigitRandomNumber();
//		// Supplier id : 5 digit
//		String supplier = suppliermanipulate(preAdviceId);
//		// Dept id : 3 digit
//		String dept = deptmanipulate(preAdviceId);
//		// Sku quantity : 3 digit
//		String skuqtymanipulate = skuQtyManipulate(preAdviceId, skuid);
//		// Advice - 6 digit
//		String advice = PreAdviceHeaderDB.getUserDefType1(preAdviceId);
//		// checkbit - 2 digit
//		String checkbit = "10";
//		palletID = siteid + barcode + URN + supplier + '0' + dept + advice + skuqtymanipulate + checkbit;
//		
//		return palletID;
//	}
	
	// Get supplierid - 4 digit and manipulated to get only integer
		public String suppliermanipulate(String preAdviceId) throws ClassNotFoundException, SQLException {
			String supplier = PreAdviceHeaderDB.getSupplierId(preAdviceId);
			String[] supplierSplit = supplier.split("M");
			return supplierSplit[1];
		}
		// Get dept - 3 digit
		public String deptmanipulate(String preAdviceId) throws ClassNotFoundException, SQLException {
			String dept = PreAdviceLineDB.getUserDefType2(preAdviceId);
			System.out.println("Dept" + dept);
			String[] deptSplit = dept.split("T");
			return deptSplit[1];
		}
		public String skuQtyManipulate(String preAdviceId, String skuid) throws ClassNotFoundException, SQLException {
			String qtyDue = PreAdviceLineDB.getQtyDue(preAdviceId, context.getSkuId());
			int sumLength = qtyDue.length();
			if (sumLength == 1) {
				qtyDue = "00" + qtyDue;
			} else if (sumLength == 2) {
				qtyDue = "0" + qtyDue;
			}
			return qtyDue;
		}
		
		private String generateBelCode(String preAdviceId, String skuid) throws ClassNotFoundException, SQLException {
			String belCode = null;
			// Checkdigit : 2 any random number
			String checkdigit = Utilities.getTwoDigitRandomNumber();
			// Supplier code : 5 digit
			String supplier = suppliermanipulate(preAdviceId);
			// UPC : 8 digit
			String upc = PreAdviceLineDB.getUpc(context.getSkuId());
			// Quantity : 3 digit
			String skuqtymanipulate = skuQtyManipulate(preAdviceId, skuid);
			// Checkbit hardcoded : 2 digit
			String checkbit = "10";
			belCode = checkdigit + supplier + upc + skuqtymanipulate + checkbit;
			return belCode;
		}
		
	
	
	@Given("^I enter URN and Bel$")
	public void I_enter_URN_and_Bel() throws Throwable {
		
		PurchaseOrderReceivingPage.enterPalletID(context.getUpiId());
		puttyFunctionsPage.pressEnter();
		
		
		
		
//		// To generate Pa"ListId= " +listId);
		puttyFunctionsPage.pressEnter();
		
		
	
	}
	
	}
	
	
