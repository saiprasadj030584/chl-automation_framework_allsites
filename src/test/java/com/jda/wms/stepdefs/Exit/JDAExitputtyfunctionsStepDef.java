package com.jda.wms.stepdefs.Exit;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.context.Context;
import com.jda.wms.dataload.exit.GetTCData;
import com.jda.wms.db.Exit.MoveTaskDB;
import com.jda.wms.db.Exit.PreAdviceHeaderDB;
import com.jda.wms.db.Exit.PreAdviceLineDB;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.db.Exit.SupplierSkuDB;
import com.jda.wms.hooks.Hooks;
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
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private PuttyFunctionsPage puttyFunctionsPage;
	private Configuration configuration;
	private Context context;
	private Object screen;
	private PurchaseOrderReceivingPage purchaseOrderReceivingPage;
	private StoreTrackingOrderPickingPage storeTrackingOrderPickingPage;
	private MoveTaskDB moveTaskDB;
	private SkuDB skuDB;
	public static String statusRegion = System.getProperty("USE_DB");
	//public static String region = System.getProperty("REGION");
	public static String region ="SIT";
	public static String host = null;
	public static String port = null;
	
	private PreAdviceHeaderDB preAdviceHeaderDB;
	private SupplierSkuDB supplierSkuDB;
	private PreAdviceLineDB preAdviceLineDB;
	private Hooks hooks;

	

	@Inject
	public JDAExitputtyfunctionsStepDef(PurchaseOrderReceivingPage purchaseOrderReceivingPage,
			SkuDB skuDB,PreAdviceLineDB preAdviceLineDB,SupplierSkuDB supplierSkuDB,
			MoveTaskDB moveTaskDB,StoreTrackingOrderPickingPage storeTrackingOrderPickingPage,
			PuttyFunctionsPage puttyFunctionsPage, Configuration configuration, Context context,
			PreAdviceHeaderDB preAdviceHeaderDB,Hooks hooks) {
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.purchaseOrderReceivingPage = purchaseOrderReceivingPage;
		this.storeTrackingOrderPickingPage=storeTrackingOrderPickingPage;
		this.configuration = configuration;
		this.context = context;
		this.moveTaskDB=moveTaskDB;
		this.preAdviceHeaderDB=preAdviceHeaderDB;
		this.supplierSkuDB= supplierSkuDB;
		this.preAdviceLineDB = preAdviceLineDB;
		this.skuDB=skuDB;
		this.hooks=hooks;
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
		String UPCValue=purchaseOrderReceivingPage.getUPC2();//from screen
		System.out.println("upc="+UPCValue);
		String UPCDB=SkuDB.getUPCDB();
		System.out.println("upc="+UPCDB);
		String prefixlist=StringUtils.substring(UPCDB, 4, 0);
//		String actuallist = moveTaskDB.getListID(context.getOrderId());
		//Assert.assertEquals("UPC to be validated", 0, prefixlist);
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
		Thread.sleep(1000);
		
	}
	@Given("^I generate pallet id$")
	public void i_generate_pallet_id(String preAdviceId, String skuid) throws Throwable {
		System.out.println("skuid "+skuid);
		context.setSkuId2(skuid);
		System.out.println("preadv "+preAdviceId);
		String palletID = null;
		// First 4 digits - Site id
//		String siteid = preAdviceHeaderDB.getSiteId(preAdviceId);
		String siteid ="7993";
		System.out.println("siteid "+siteid);
		// Hardcoded 3 digit
//		String barcode = Utilities.getThreeDigitRandomNumber();
		String barcode = "145";
		System.out.println("barcode "+barcode);
		// Random generated 6 digit
		String URN = Utilities.getSixDigitRandomNumber();
		System.out.println("URN "+URN );
		// Supplier id : 5 digit
		String supplier = suppliermanipulate(skuid);
		System.out.println("supplier "+supplier);
		// Dept id : 3 digit
		String dept = deptmanipulate(skuid);
		System.out.println("dept "+dept);
		// Sku quantity : 3 digit
		String skuqtymanipulate = skuQtyManipulate(preAdviceId, skuid);
		System.out.println("skuqtymanipulate "+skuqtymanipulate);
		// Advice - 6 digit
		String advice = preAdviceHeaderDB.getUserDefType1(preAdviceId);
		System.out.println("advice "+advice);
		// checkbit - 2 digit
		String checkbit = "10";
		System.out.println("checkbit "+checkbit);
		palletID = siteid+ barcode + URN + supplier + '0' + dept + advice + skuqtymanipulate + checkbit;
		context.setPalletID(palletID);
		System.out.println("check palletid "+palletID);
	}
	@Given("^I generate pallet id$")
	public void i_generate_pallet_id_for_UPI(String preAdviceId, String skuid) throws Throwable {
		System.out.println("skuid "+skuid);
		context.setSkuId2(skuid);
		System.out.println("preadv "+preAdviceId);
		String palletID = null;
		// First 4 digits - Site id
//		String siteid = preAdviceHeaderDB.getSiteId(preAdviceId);
		String siteid ="7993";
		System.out.println("siteid "+siteid);
		// Hardcoded 3 digit
//		String barcode = Utilities.getThreeDigitRandomNumber();
		String barcode = "145";
		System.out.println("barcode "+barcode);
		// Random generated 6 digit
		String URN = Utilities.getSixDigitRandomNumber();
		System.out.println("URN "+URN );
		// Supplier id : 5 digit
		String supplier = suppliermanipulate(skuid);
		System.out.println("supplier "+supplier);
		// Dept id : 3 digit
		String dept = deptmanipulate(skuid);
		System.out.println("dept "+dept);
		// Sku quantity : 3 digit
		String skuqtymanipulate = skuQtyManipulate(preAdviceId, skuid);
		System.out.println("skuqtymanipulate "+skuqtymanipulate);
		// Advice - 6 digit
//		String advice = preAdviceHeaderDB.getUserDefType1(preAdviceId);
//		System.out.println("advice "+advice);
		String advice = Utilities.getSixDigitRandomNumber();
		System.out.println("advice "+advice);
		// checkbit - 2 digit
		String checkbit = "10";
		System.out.println("checkbit "+checkbit);
		palletID = siteid+ barcode + URN + supplier + '0' + dept + advice + skuqtymanipulate + checkbit;
		context.setPalletID(palletID);
		System.out.println("check palletid "+palletID);
	}

	// Get supplierid - 4 digit and manipulated to get only integer
		public String suppliermanipulate(String skuid) throws ClassNotFoundException, SQLException {
			System.out.println("check"+skuid);
//			context.getSkuId2();
			String supplier = supplierSkuDB.getSupplierId(skuid);
			String[] supplierSplit = supplier.split("M");
			return supplierSplit[1];
		}
		// Get dept - 3 digit
		public String deptmanipulate(String skuid) throws ClassNotFoundException, SQLException {
			String dept = SkuDB.getProductGroup(skuid);
			System.out.println("Dept" + dept);
			String[] deptSplit = dept.split("T");
			return deptSplit[1];
			
			
		}
		public String skuQtyManipulate(String preAdviceId,String skuid) throws ClassNotFoundException, SQLException {
			String qtyDue = preAdviceLineDB.getQtyDue(preAdviceId, skuid);
			int sumLength = qtyDue.length();
			if (sumLength == 1) {
				qtyDue = "00" + qtyDue;
			} else if (sumLength == 2) {
				qtyDue = "0" + qtyDue;
			} 
			return qtyDue;
		}
		public String skuQtyManipulate2(String preAdviceId,String skuid) throws ClassNotFoundException, SQLException {
			String qtyDue = preAdviceLineDB.getQtyDue(preAdviceId, skuid);
			int sumLength = qtyDue.length();
			if (sumLength == 1) {
				qtyDue = "000" + qtyDue;
			} else if (sumLength == 2) {
				qtyDue = "00" + qtyDue;
			} else if (sumLength == 3) {
				qtyDue = "0" + qtyDue;
			}
			return qtyDue;
		}
		@Given("^I generate belcode$")
		private void I_generate_belcode(String preAdviceId, String skuid) throws ClassNotFoundException, SQLException {
			String belCode = null;
			context.setSkuId2(skuid);
			// Checkdigit : 2 any random number
			String checkdigit = Utilities.getTwoDigitRandomNumber();
			System.out.println("checkdigit "+checkdigit);
			// Supplier code : 5 digit
			String supplier = suppliermanipulate(skuid);
			System.out.println("supplier "+supplier);
			// UPC : 8 digit
			String upc = preAdviceLineDB.getUpc(skuid);
			System.out.println("upc "+upc );
			// Quantity : 4 digit
			String skuqtymanipulate = skuQtyManipulate2(preAdviceId, skuid);
			System.out.println("skuqtymanipulate "+skuqtymanipulate);
			// Checkbit hardcoded : 1 digit
			String checkbit = "1";
			System.out.println("checkbit "+checkbit);
			belCode = checkdigit + supplier + upc + skuqtymanipulate + checkbit;
			context.setBelCode(belCode);
			System.out.println(belCode);;
		}
		
	
	
	@Given("^I enter URN and Bel and validation of UPC,QTY and Supplier$")
	public void I_enter_URN_and_Bel_and_validation_of_UPC_QTY_and_Supplier() throws Throwable {
		GetTCData.getpoId();
		String skuid = "000000000021071852";
		i_generate_pallet_id(GetTCData.getpoId(),skuid);
		String palletID = context.getPalletID();
		System.out.println("palletID "+palletID);
		purchaseOrderReceivingPage.EnterPalletID(palletID);
		puttyFunctionsPage.pressEnter();
		I_generate_belcode(GetTCData.getpoId(),skuid);
		String belCode = context.getBelCode();
		System.out.println("BelCode "+belCode);
		purchaseOrderReceivingPage.EnterBel(belCode);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(1000);
		String UPCValue=purchaseOrderReceivingPage.getUPC2();//from screen
		String prefixlist=StringUtils.substring(UPCValue, 0, 8);
		System.out.println("UPCValue= "+prefixlist);
		String UPCDB=SkuDB.getUPCDB();//from DB
		System.out.println("UPCDB= "+UPCDB);
		Assert.assertEquals("UPC validated", UPCDB, prefixlist);
		Thread.sleep(1000);
		String QTYValue=purchaseOrderReceivingPage.getQTY();//from screen
		System.out.println("QTYValue= "+QTYValue);
		String DBlist=StringUtils.substring(QTYValue, 0, 2);
		String preAdviceID=GetTCData.getpoId();
		String QTYDB=skuDB.getQTYDB(preAdviceID,skuid);//from DB
		System.out.println("QTYDB= "+QTYDB);
		Assert.assertEquals("UPC validated", QTYDB, DBlist);
		logger.debug("Validated QTY value : " + DBlist);
		Thread.sleep(1000);
		String SupplierValue=purchaseOrderReceivingPage.getSupplier();//from screen
		System.out.println("SupplierValue= "+SupplierValue);
		String SupplierDB = supplierSkuDB.getSupplierId(skuid);//from DB
		System.out.println("SupplierDB= "+SupplierDB);
		Assert.assertEquals("UPC validated", SupplierDB, SupplierValue);
		logger.debug("Validated Supplier Value: " + SupplierValue);
		Thread.sleep(1000);
		
		
	}
	@Given("^I enter To Pallet$")
	public void I_enter_To_Pallet() throws Throwable {
		String ToPallet = null;
		puttyFunctionsPage.nextScreen();
		Thread.sleep(500);
		puttyFunctionsPage.Tab();
		Thread.sleep(500);
		String palletdigit = Utilities.getsevenDigitRandomNumber();
		ToPallet="P"+palletdigit;
		purchaseOrderReceivingPage.EnterToPallet(ToPallet);
		purchaseOrderReceivingPage.enterYes();
		
		hooks.logoutPutty();
		
	}
	
	}
	
	
