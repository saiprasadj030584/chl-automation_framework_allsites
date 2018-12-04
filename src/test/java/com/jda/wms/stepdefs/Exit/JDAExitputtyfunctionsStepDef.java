package com.jda.wms.stepdefs.Exit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
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
import com.jda.wms.db.Exit.LocationDB;
import com.jda.wms.db.Exit.SupplierSkuDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.Exit.PreAdviceHeaderPage;
import com.jda.wms.pages.Exit.PurchaseOrderReceivingPage;
import com.jda.wms.pages.Exit.PuttyFunctionsPage;
import com.jda.wms.pages.Exit.StoreTrackingOrderPickingPage;
import com.jda.wms.utils.Utilities;
import com.jda.wms.pages.Exit.LocationZonePage;

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
	private LocationDB LocationDB;
	public static String statusRegion = System.getProperty("USE_DB");
	//public static String region = System.getProperty("REGION");
	public static String region ="SIT";
	public static String host = null;
	public static String port = null;
	
	private PreAdviceHeaderDB preAdviceHeaderDB;
	private SupplierSkuDB supplierSkuDB;
	private PreAdviceLineDB preAdviceLineDB;
	private LocationZonePage LocationZonePage;
	private Hooks hooks;

	

	@Inject
	public JDAExitputtyfunctionsStepDef(PurchaseOrderReceivingPage purchaseOrderReceivingPage,
			SkuDB skuDB,LocationDB LocationDB,PreAdviceLineDB preAdviceLineDB,SupplierSkuDB supplierSkuDB,
			MoveTaskDB moveTaskDB,StoreTrackingOrderPickingPage storeTrackingOrderPickingPage,
			PuttyFunctionsPage puttyFunctionsPage, Configuration configuration, Context context,
			PreAdviceHeaderDB preAdviceHeaderDB,LocationZonePage LocationZonePage,Hooks hooks) {
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
		this.LocationDB=LocationDB;
		this.LocationZonePage=LocationZonePage;
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
		String TagId="158888";
//		context.setTag(TagId);
		System.out.println("TagId="+TagId);
		storeTrackingOrderPickingPage.enterTagId(TagId);
		Thread.sleep(500);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(500);
		String UPCValue=purchaseOrderReceivingPage.getUPC2();//from screen
		System.out.println("upc="+UPCValue);
		String UPCLast=StringUtils.substring(UPCValue, 0, 4);
//		String[] UPCSplit = UPCLast.split("****");
		System.out.println("UPCLast="+UPCLast);
		String UPCDB=SkuDB.getUPCDB();
		System.out.println("upc="+UPCDB);
//		String[] UPCDBLast = UPCDB.split("(?<!\\d)(?<!\\d)");
//		return UPCDBLast[1];
//		String  UPCDBLastFinal= UPCDBLast[1];
		String UPCDBLast=StringUtils.substring(UPCDB, 4, 8);
		System.out.println("UPCDBLastFinal="+UPCDBLast);
		Assert.assertEquals("UPC validated", UPCDBLast, UPCLast);
			
		String QTYValue=purchaseOrderReceivingPage.getQTY();//from screen
		System.out.println("QTYValue= "+QTYValue);
		String DBlist=StringUtils.substring(QTYValue, 0, 2);
		System.out.println("DBlist:" +DBlist);
        Thread.sleep(1000);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(500);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(500);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(500);
	
	hooks.logoutPutty();	
	}
	@And("^I enter ListId and TagId for IDT$")
	public void I_enter_ListId_and_TagId_for_IDT() throws Throwable{
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
		String UPCLast=StringUtils.substring(UPCValue, 0, 4);
		System.out.println("UPCLast="+UPCLast);
		String UPCDB=SkuDB.getUPCDB();
		System.out.println("upc="+UPCDB);
		Thread.sleep(1000);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(500);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(500);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(500);
		hooks.logoutPutty();
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
		Thread.sleep(2000);
		
	}
	@And("^Verify scan URN screen displayed$")
	public void verify_scan_URN_screen_displayed() throws FindFailed, InterruptedException, IOException
	{
		storeTrackingOrderPickingPage.clientID();
		hooks.logoutPutty();
	}
	
//	
	@Given("^I enter URN and Bel and validation of UPC,QTY and Supplier for over receipt$")
	public void I_enter_URN_and_Bel_and_validation_of_UPC_QTY_and_Supplier_over_receipt() throws Throwable {
		Thread.sleep(1000);
		GetTCData.getpoId();
		String skuid=context.getSKUHang();
		puttyFunctionsPage.i_generate_pallet_id(GetTCData.getpoId(),skuid);
		String palletID = context.getPalletID();
		System.out.println("palletID "+palletID);
		Thread.sleep(2000);
		purchaseOrderReceivingPage.EnterPalletID(palletID);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(1000);
		puttyFunctionsPage.I_generate_belcode_for_overreceipt(GetTCData.getpoId(),skuid);
		String belCode = context.getBelCode();
		System.out.println("BelCode "+belCode);
		Thread.sleep(2000);
		purchaseOrderReceivingPage.EnterBel(belCode);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(1000);
		String UPCValue=purchaseOrderReceivingPage.getUPC2();//from screen
		String prefixlist=StringUtils.substring(UPCValue, 0, 8);
		System.out.println("UPCValue= "+prefixlist);
		String UPCDB=SkuDB.getUPCDB();//from DB
		System.out.println("UPCDB= "+UPCDB);
//		Assert.assertEquals("UPC validated", UPCDB, prefixlist);
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
	
	@Given("^I enter URN and Bel and validation of UPC,QTY and Supplier for over unknown stock$")
	public void I_enter_URN_and_Bel_and_validation_of_UPC_QTY_and_Supplier_over_unknown_stock() throws Throwable {
		Thread.sleep(1000);
		GetTCData.getpoId();
		String skuid=context.getSkuId();
		puttyFunctionsPage.i_generate_pallet_id(GetTCData.getpoId(),skuid);
		String palletID = context.getPalletID();
		System.out.println("palletID "+palletID);
		Thread.sleep(2000);
		purchaseOrderReceivingPage.EnterPalletID(palletID);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(1000);
		puttyFunctionsPage.I_generate_belcode(GetTCData.getpoId(),skuid);
		String belCode = context.getBelCode();
		System.out.println("BelCode "+belCode);
		Thread.sleep(2000);
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
	
	@Given("^I enter URN and Bel and validation of UPC,QTY and Supplier$")
	public void I_enter_URN_and_Bel_and_validation_of_UPC_QTY_and_Supplier() throws Throwable {
		Thread.sleep(1000);
		GetTCData.getpoId();

		String skuid=context.getSkuId2();
//		String skuid2=context.getSKUHang();

		String skuid2=context.getSKUHang();

		puttyFunctionsPage.i_generate_pallet_id(GetTCData.getpoId(),skuid);
		String palletID = context.getPalletID();
		System.out.println("palletID "+palletID);
		Thread.sleep(2000);
		purchaseOrderReceivingPage.EnterPalletID(palletID);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(1000);
		puttyFunctionsPage.I_generate_belcode(GetTCData.getpoId(),skuid);
		String belCode = context.getBelCode();
		System.out.println("BelCode "+belCode);
		Thread.sleep(2000);
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
	@Given("^I enter URN and Bel and validation of UPC,QTY and Supplier for Unknown$")
	public void I_enter_URN_and_Bel_and_validation_of_UPC_QTY_and_Supplier_for_unknown() throws Throwable {
		Thread.sleep(1000);
		GetTCData.getpoId();
		String skuid=context.getSkuId();
		puttyFunctionsPage.i_generate_pallet_id(GetTCData.getpoId(),skuid);
		String palletID = context.getPalletID();
		System.out.println("palletID "+palletID);
		Thread.sleep(2000);
		purchaseOrderReceivingPage.EnterPalletID(palletID);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(1000);
		puttyFunctionsPage.I_generate_belcode_for_unknown(GetTCData.getpoId(),skuid);
		String belCode = context.getBelCode();
		System.out.println("BelCode "+belCode);
		Thread.sleep(2000);
		purchaseOrderReceivingPage.EnterBel(belCode);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(1000);
						}
	@Given("^I enter URN and Bel and validation of UPC,QTY and Supplier for ASN$")
	public void I_enter_URN_and_Bel_and_validation_of_UPC_QTY_and_Supplier_for_ASN() throws Throwable {
		GetTCData.getpoId();
		String skuid = context.getSkuId2();
//		i_generate_pallet_id_for_UPI(GetTCData.getpoId(),skuid);
		String palletIDforUPI = context.getpalletIDforUPI();
		System.out.println("palletID "+palletIDforUPI);
		purchaseOrderReceivingPage.EnterPalletID(palletIDforUPI);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(1000);
		Assert.assertTrue("RCVBli screen is not displayed as expected",
		storeTrackingOrderPickingPage.isRCVBLIMenuDisplayed());
		Thread.sleep(300);
		puttyFunctionsPage.I_generate_belcode_for_UPI(GetTCData.getpoId(),skuid);
		String belCode = context.getBelCode();
		System.out.println("BelCode "+belCode);
		purchaseOrderReceivingPage.EnterBel(belCode);
		Thread.sleep(300);
		puttyFunctionsPage.singleTab();
		String ToPallet = null;
		String palletdigit = Utilities.getsevenDigitRandomNumber();
		ToPallet="P"+palletdigit;
		purchaseOrderReceivingPage.EnterToPallet(ToPallet);
		puttyFunctionsPage.pressEnter();
		hooks.logoutPutty();
		}
	
	@Given("^I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock$")
	public void I_enter_URN_and_Bel_and_validation_of_UPC_QTY_Supplier_and_location_for_ASN_for_red_stock() throws Throwable {
		GetTCData.getpoId();
		String skuid = context.getSKUHang();
//		i_generate_pallet_id_for_UPI(GetTCData.getpoId(),skuid);
		String palletIDforUPI = context.getpalletIDforUPI();
		System.out.println("palletID "+palletIDforUPI);
		purchaseOrderReceivingPage.EnterPalletID(palletIDforUPI);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(1000);
		Assert.assertTrue("RCVBli screen is not displayed as expected",
		storeTrackingOrderPickingPage.isRCVBLIMenuDisplayed());
		Thread.sleep(300);
		puttyFunctionsPage.I_generate_belcode_for_UPI(GetTCData.getpoId(),skuid);
		String belCode = context.getBelCode();
		System.out.println("BelCode "+belCode);
		purchaseOrderReceivingPage.EnterBel(belCode);
		Thread.sleep(300);
		puttyFunctionsPage.singleTab();
		//--requirement as per red stock-- to pallet should be entered with red location--//
//		String Location = LocationDB.getRedLocation();
//		System.out.println("Red Location="+Location);
		purchaseOrderReceivingPage.EnterToPallet("BA001");
		puttyFunctionsPage.pressEnter();
		validate_that_red_lock_code_applied_message();
		hooks.logoutPutty();
	
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
	@Given("^I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date$")
	public void I_enter_URN_and_Bel_and_validation_of_UPC_QTY_and_Supplier_for_ASN_with_batch_and_expiry_date() throws Throwable {
		GetTCData.getpoId();
		String skuid = context.getSKUHang();
//		i_generate_pallet_id_for_UPI(GetTCData.getpoId(),skuid);
		String palletIDforUPI = context.getpalletIDforUPI();
		System.out.println("palletID "+palletIDforUPI);
		purchaseOrderReceivingPage.EnterPalletID(palletIDforUPI);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(1000);
		Assert.assertTrue("RCVBli screen is not displayed as expected",
		storeTrackingOrderPickingPage.isRCVBLIMenuDisplayed());
		Thread.sleep(300);
		puttyFunctionsPage.I_generate_belcode_for_UPI(GetTCData.getpoId(),skuid);
		String belCode = context.getBelCode();
		System.out.println("BelCode "+belCode);
		purchaseOrderReceivingPage.EnterBel(belCode);
		Thread.sleep(300);
		puttyFunctionsPage.singleTab();
		String ToPallet = null;
		String palletdigit = Utilities.getsevenDigitRandomNumber();
		ToPallet="P"+palletdigit;
		purchaseOrderReceivingPage.EnterToPallet(ToPallet);
		puttyFunctionsPage.nextScreen();
		String Expirydate= Utilities.getAddedSystemYear();
		purchaseOrderReceivingPage.EnterToExpirydate(Expirydate);
//		String ExpiryDate= purchaseOrderReceivingPage.getExpiryDate();
//		
//		Assert.assertNotNull("Expiry date not validated", ExpiryDate);
		puttyFunctionsPage.pressEnter();
		hooks.logoutPutty();
	
		}
	
	@Given("^I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date for hang$")
	public void I_enter_URN_and_Bel_and_validation_of_UPC_QTY_and_Supplier_for_ASN_with_batch_and_expiry_date_for_hang() throws Throwable {
		GetTCData.getpoId();
		String skuid = context.getSkuId2();
//		i_generate_pallet_id_for_UPI(GetTCData.getpoId(),skuid);
		String palletIDforUPI = context.getpalletIDforUPI();
		System.out.println("palletID "+palletIDforUPI);
		purchaseOrderReceivingPage.EnterPalletID(palletIDforUPI);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(1000);
		Assert.assertTrue("RCVBli screen is not displayed as expected",
		storeTrackingOrderPickingPage.isRCVBLIMenuDisplayed());
		Thread.sleep(300);
		puttyFunctionsPage.I_generate_belcode_for_UPI(GetTCData.getpoId(),skuid);
		String belCode = context.getBelCode();
		System.out.println("BelCode "+belCode);
		purchaseOrderReceivingPage.EnterBel(belCode);
		Thread.sleep(300);
		puttyFunctionsPage.singleTab();
//		String ToPallet = null;
//		String palletdigit = Utilities.getsevenDigitRandomNumber();
//		ToPallet="P"+palletdigit;
		purchaseOrderReceivingPage.EnterToPallet("BA001");
		puttyFunctionsPage.nextScreen();
		String Expirydate= Utilities.getAddedSystemYear();
		purchaseOrderReceivingPage.EnterToExpirydate(Expirydate);
		puttyFunctionsPage.pressEnter();
		hooks.logoutPutty();
	
		}
	@Then("Validate that Red Lock Code applied message$")
	public void validate_that_red_lock_code_applied_message() throws Throwable {
		Assert.assertTrue("Red LockCode not Apllied",LocationZonePage.isRedLockApplied());
	}
	
   @And("^Validate OverReceipt Error$")
   public void validate_overreceipt_error()throws Throwable 
   {
	   puttyFunctionsPage.Overreceipterror();
	   puttyFunctionsPage.pressEnter();
	   hooks.logoutPutty();
   }

   @And("^Validate Unknown stock Error$")
   public void validate_unknownStock_error()throws Throwable 
   {
	   puttyFunctionsPage.UnknownStock();
	   puttyFunctionsPage.pressEnter();
	   hooks.logoutPutty();
   }
	
	}
	
	
