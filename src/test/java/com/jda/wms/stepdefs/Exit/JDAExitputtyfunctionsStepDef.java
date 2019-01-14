package com.jda.wms.stepdefs.Exit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sikuli.script.App;
import org.sikuli.script.Match;
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
import com.jda.wms.pages.Exit.JdaHomePage;
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
	//private Object screen;
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
	private final JdaHomePage imageCheckFunction;

	

	@Inject
	public JDAExitputtyfunctionsStepDef(PurchaseOrderReceivingPage purchaseOrderReceivingPage,
			SkuDB skuDB,LocationDB LocationDB,PreAdviceLineDB preAdviceLineDB,SupplierSkuDB supplierSkuDB,
			MoveTaskDB moveTaskDB,StoreTrackingOrderPickingPage storeTrackingOrderPickingPage,
			PuttyFunctionsPage puttyFunctionsPage, Configuration configuration, Context context,
			PreAdviceHeaderDB preAdviceHeaderDB,
			JdaHomePage imageCheckFunction,LocationZonePage LocationZonePage,Hooks hooks) {
			
			{
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
		this.imageCheckFunction = imageCheckFunction;}
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
		Thread.sleep(1000);
	}
	@Then("^configure putty settings$")
	public void configure_putty_settings() throws Throwable {
		Thread.sleep(1000);
		puttyFunctionsPage.configureSettings();
	}
	@Then("^Enter Consignment \"([^\"]*)\"$")
	public void enterConsignment(String Consignment) throws Throwable {
		puttyFunctionsPage.typeConsignment(Consignment);
	}
	
	@Then("^I select Inventory transaction option$")
	public void InventoryTransaction() throws Throwable {
		puttyFunctionsPage.selectInventory();
	}
	
	@Then("^Enter Pallet Id \"([^\"]*)\"$")
	public void getPalletId(String Pallet) throws Throwable {

		puttyFunctionsPage.enterPalletId(Pallet);
	}
	
	@Then("^Enter Pallet Id$")
	public void getPalletId() throws Throwable {

		puttyFunctionsPage.enterPalletId(context.getPalletId());
		puttyFunctionsPage.pressTab();
	}
	
	@Then("^Validate the pallet and consignment is linked$")
	public void consignmentIsLinked() throws Throwable {
		puttyFunctionsPage.linkPalletId();
		Thread.sleep(5000);
		puttyFunctionsPage.pressEnter();
		hooks.logoutPutty();
	}
	@Then("^Validate the pallet and consignment is loaded$")
	public void consignmentIsLoaded() throws Throwable {
		puttyFunctionsPage.linkPalletId();
		Thread.sleep(5000);
		puttyFunctionsPage.pressEnter();
		hooks.logoutPutty();
	}
	
	@When("^I select user directed option in main menu$")
	public void i_select_user_directed_option_in_main_menu() throws Throwable {
		purchaseOrderReceivingPage.selectUserDirectedMenu();
	}
	@When("^I select vehicle loading option in main menu$")
	public void i_select_vehicle_directed_option_in_main_menu() throws Throwable {
		purchaseOrderReceivingPage.selectvehicleDirectedMenu();
	}
	@When("^I select vehicle load option$")
	public void i_select_vehicle_load() throws Throwable {
		purchaseOrderReceivingPage.selectvehicleloadMenu();
	}
	@When("^select multi pallet load$")
	public void i_select_multiPallet_load() throws Throwable {
		purchaseOrderReceivingPage.selectMultiPalletloadMenu();
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
		String sku=context.getSKUHang();
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
		String UPCDB=SkuDB.getUPCDB(sku);
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
		String sku=context.getSKUHang();
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
		String UPCDB=SkuDB.getUPCDB(sku);
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
	@Given("^I select Unpick/Unship$")
	public void I_select_Unpick_Unship_menu() throws Throwable {
//		storeTrackingOrderPickingPage.selectPickingMenu();
		System.out.println("before enter");
		storeTrackingOrderPickingPage.selectPickingMenu();
//		storeTrackingOrderPickingPage.selectPickingMenuForFurtherProcess();
		System.out.println("After enter");
		Assert.assertTrue("Picking Menu not displayed as expected",
		storeTrackingOrderPickingPage.isPickMenuDisplayed());
		storeTrackingOrderPickingPage.selectUnpickMenu();
		Assert.assertTrue("Receiving Task Menu not displayed as expected",
		storeTrackingOrderPickingPage.isUnpickDisplayed());
	    Thread.sleep(2000);
			}
	
	@Given("^I select Relocate and Existing relocate$")
	public void I_select_Relocate_and_Existing_relocate() throws Throwable {
//		storeTrackingOrderPickingPage.selectPickingMenu();
		System.out.println("before enter");
		storeTrackingOrderPickingPage.selectRelocate();
//		storeTrackingOrderPickingPage.selectPickingMenuForFurtherProcess();
		System.out.println("After enter");
		Assert.assertTrue("Relocation Menu not displayed as expected",
		storeTrackingOrderPickingPage.isRelocateDisplayed());
		storeTrackingOrderPickingPage.selectExistingMenu();
		Assert.assertTrue("Receiving Task Menu not displayed as expected",
		storeTrackingOrderPickingPage.isRelocateExistDisplayed());
	    Thread.sleep(2000);
			}
	@Given("^I enter container_id$")
	public void I_enter_container_id_menu() throws Throwable {
	String palletIDforUPI = context.getpalletIDforUPI();
	purchaseOrderReceivingPage.EnterPalletID(palletIDforUPI);
	puttyFunctionsPage.pressEnter();
	Thread.sleep(2000);
	puttyFunctionsPage.pressEnter();
	
	hooks.logoutPutty();
	}
	@Given("^I enter UPC and TagId \"([^\"]*)\"$")
	public void I_enter_UPC_and_TagId(String skuid ) throws Throwable {
	String UPC = skuDB.getUPCDB1(skuid);
	purchaseOrderReceivingPage.EnterUPC(UPC);
	String TagTrans=context.getTagTrans();
	purchaseOrderReceivingPage.Entertag(TagTrans);
	puttyFunctionsPage.pressEnter();
	Thread.sleep(2000);
	puttyFunctionsPage.pressEnter();
	hooks.logoutPutty();
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
		String UPCDB=SkuDB.getUPCDB(skuid);//from DB
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
		String skuid=context.getSKUHang();
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
		String UPCDB=SkuDB.getUPCDB(skuid);//from DB
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
        String skuid=context.getSKUHang();
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
		String UPCDB=SkuDB.getUPCDB(skuid);//from DB
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
		String skuid=context.getSKUHang();
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
	@Given("^I enter URN and Bel and validation of UPC,QTY and Supplier for ASN Direct receiving$")
	public void I_enter_URN_and_Bel_and_validation_of_UPC_QTY_and_Supplier_for_ASN() throws Throwable {
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
		//String ToPallet = null;
		String palletdigit = Utilities.getsevenDigitRandomNumber();
		String ToPallet="P"+palletdigit;
		purchaseOrderReceivingPage.EnterToPallet(ToPallet);
		System.out.println("ToPallet== "+ToPallet);
		context.setPalletId(ToPallet);
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
//		String ToPallet = null;
//		String palletdigit = Utilities.getsevenDigitRandomNumber();
//		ToPallet="P"+palletdigit;
//		purchaseOrderReceivingPage.EnterToPallet(ToPallet);
//		puttyFunctionsPage.nextScreen();
		
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
	
	@Given("^Validate the message for prohibition$")
	public void validate_the_message_for_prohibition() throws Throwable{
		purchaseOrderReceivingPage.isProhibition();
		hooks.logoutPutty();
	}
	
	@Then("^I Navigate Back$")
	public void NavigateBack() throws Throwable {
	puttyFunctionsPage.GoBack();
	}
	@Given("^I enter To Pallet for prohibited sku$")
	public void I_enter_To_Pallet_for_prohibited_sku() throws Throwable {
		String ToPallet = null;
		puttyFunctionsPage.nextScreen();
		Thread.sleep(500);
		puttyFunctionsPage.Tab();
		Thread.sleep(500);
		
		purchaseOrderReceivingPage.EnterToPallet("BA001");
		purchaseOrderReceivingPage.enterYes();
		
		
	}
	@Given("^I select sorting menu$")
	public void I_select_sorting_menu() throws Throwable
	{
		i_have_logged_in_as_warehouse_user_in_putty();
		purchaseOrderReceivingPage.selectUserDirectedMenu();
		storeTrackingOrderPickingPage.selectPickingMenu();
		storeTrackingOrderPickingPage.selectsortation();
		
	}

	@Given("I enter URN for sortation in Direct Receiving")
	public void I_enter_URN_for_sortation_in_Direct_receiving() throws Throwable
	{
		String palletIDforUPI = context.getpalletIDforUPI();
		System.out.println("palletID "+palletIDforUPI);
		purchaseOrderReceivingPage.EnterPalletID(palletIDforUPI);
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.singleTab();
		Thread.sleep(500);
		String ToPallet = null;
		String palletdigit = Utilities.getsevenDigitRandomNumber();
		ToPallet="P"+palletdigit;
		purchaseOrderReceivingPage.EnterToPallet(ToPallet);
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		hooks.logoutPutty();
	}
	@Given("I enter URN for different destination for sortation in Direct Receiving")
	public void I_enter_URN_for_sortation_in_Direct_receiving_for_different_destination() throws Throwable
	{
		String palletIDforUPI = context.getpalletIDforUPI();
		System.out.println("palletID "+palletIDforUPI);
		purchaseOrderReceivingPage.EnterPalletID(palletIDforUPI);
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.singleTab();
		Thread.sleep(500);
		String ToPallet ="P1230071";
		purchaseOrderReceivingPage.EnterToPallet(ToPallet);
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		hooks.logoutPutty();
	}
	@Given("I Validate the Error message for different source and destination")
	public void Validate_the_Error_message_for_different_source_and_destination() throws Throwable
	{
		
		purchaseOrderReceivingPage.isErrorMsgDisplayed();
	}
	@Given("^I enter To Pallet for two urn$")
	public void I_enter_To_Pallet_for_two_urn() throws Throwable {
		String ToPallet = null;
		puttyFunctionsPage.nextScreen();
		Thread.sleep(500);
		puttyFunctionsPage.Tab();
		Thread.sleep(500);
		String palletdigit = Utilities.getsevenDigitRandomNumber();
		ToPallet="P"+palletdigit;
		purchaseOrderReceivingPage.EnterToPallet(ToPallet);
		purchaseOrderReceivingPage.enterYes();
		
		
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
   
   @Given("^I enter URN and Bel and validation of UPC,QTY and Supplier for two URN$")
	public void I_enter_URN_and_Bel_and_validation_of_UPC_QTY_and_Supplier_for_two_URN() throws Throwable {
		Thread.sleep(10000);
		
		GetTCData.getpoId2();
		String skuid=context.getSKUHang();
		puttyFunctionsPage.i_generate_pallet_id_for_two(GetTCData.getpoId2(),skuid);
		String palletID2 = context.getPalletID2();
		System.out.println("palletID "+palletID2);
		Thread.sleep(2000);
		purchaseOrderReceivingPage.EnterPalletID(palletID2);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(1000);
		puttyFunctionsPage.I_generate_belcode_for_overreceipt(GetTCData.getpoId2(),skuid);
		String belCode2 = context.getBelCode2();
		System.out.println("BelCode "+belCode2);
		Thread.sleep(2000);
		purchaseOrderReceivingPage.EnterBel(belCode2);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(1000);
		String UPCValue=purchaseOrderReceivingPage.getUPC2();//from screen
		String prefixlist=StringUtils.substring(UPCValue, 0, 8);
		System.out.println("UPCValue= "+prefixlist);
		String UPCDB=SkuDB.getUPCDB(skuid);//from DB
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
	
@And("^I enter Invalid List Id \"([^\"]*)\"$")
public void I_enter_Invalid_List_Id(String List_Id) throws Throwable{
   storeTrackingOrderPickingPage.enterListID(List_Id);
   puttyFunctionsPage.pressEnter();
}
Screen screen = new Screen();
@And("^I validate Error message is displayed$")
public void I_validate_Error_message_is_displayed() throws Throwable{
   if (screen.exists("images/JDAHome/webaccess.png") != null) {
		Assert.assertTrue(true);
	} else
		Assert.assertFalse(false);
}	

@And("^I select picking$")
public void i_select_picking() throws Throwable {
	System.out.println("before enter");
	storeTrackingOrderPickingPage.selectPickingMenu();
	System.out.println("After enter");
	Assert.assertTrue("Picking Menu not displayed as expected",
			storeTrackingOrderPickingPage.isPickMenuDisplayed());
	
}


@And("^I select sorting option$")
public void i_select_sorting_option() throws Throwable {
	storeTrackingOrderPickingPage.selectSortingInPickMenu();
}
@And("^I enter URN in Pallet id$")
public void I_enter_URN_in_Pallet_id() throws Throwable {
	GetTCData.getpoId();
	String skuid = context.getSkuId2();
//	i_generate_pallet_id_for_UPI(GetTCData.getpoId(),skuid);
	String palletIDforUPI = context.getpalletIDforUPI();
	System.out.println("palletID "+palletIDforUPI);
	purchaseOrderReceivingPage.EnterPalletID(palletIDforUPI);
	Thread.sleep(1000);
}
@And("^I enter to Pallet id$")
public void I_enter_URN_to_Pallet_id() throws Throwable {
	String ToPallet = null;
	String palletdigit = Utilities.getsevenDigitRandomNumber();
	ToPallet="P"+palletdigit;
	Thread.sleep(2000);
	purchaseOrderReceivingPage.EnterToPallet(ToPallet);
	puttyFunctionsPage.pressEnter();
	Thread.sleep(2000);
}
@And("^verify pallet repack is validated successfully$")
public void verify_pallet_repack_is_validated_successfully() throws Throwable {
	storeTrackingOrderPickingPage.isRepackConfirm();
}
@And("^I type ListId and TagId$")
public void I_type_ListId_and_TagId() throws Throwable{
	//String TagId=moveTaskDB.getTag(context.getOrderId());
	String sku=context.getSKUHang();
	String listId=moveTaskDB.getList(context.getOrderId());
	
	storeTrackingOrderPickingPage.enterListID(listId);
	System.out.println("ListId= " +listId);
	puttyFunctionsPage.pressEnter();
	String TagId="9999";
//	context.setTag(TagId);
	System.out.println("TagId="+TagId);
	storeTrackingOrderPickingPage.enterTagId(TagId);
	Thread.sleep(500);
	puttyFunctionsPage.pressEnter();
	Thread.sleep(500);
	String UPCValue=purchaseOrderReceivingPage.getUPC2();//from screen
	System.out.println("upc="+UPCValue);
	String UPCLast=StringUtils.substring(UPCValue, 0, 4);
	System.out.println("UPCLast="+UPCLast);
	String UPCDB=SkuDB.getUPCDB(sku);
	System.out.println("upc="+UPCDB);
	Thread.sleep(1000);
	puttyFunctionsPage.pressEnter();
	Thread.sleep(500);
	puttyFunctionsPage.pressEnter();
	Thread.sleep(5000);
}
@And("^I enter Invalid To Location \"([^\"]*)\"$")
public void I_enter_Invalid_To_Location(String ToLocation) throws Throwable{
	storeTrackingOrderPickingPage.enterToLocation(ToLocation);
	puttyFunctionsPage.pressEnter();
	Thread.sleep(5000);
}

@And("^I validate incorrect location message is displayed$")
public void I_validate_incorrect_location_message_is_displayed() throws Throwable{
	Thread.sleep(5000);
	storeTrackingOrderPickingPage.isInvalidLocationErrorDisplayed();
	Thread.sleep(5000);
	puttyFunctionsPage.pressEnter();
	Thread.sleep(2000);
	puttyFunctionsPage.pressEnter();
}
@And("^validate the error message is displayed$")
public void I_validate_incorrect_message_is_displayed() throws Throwable{
	Thread.sleep(5000);
	storeTrackingOrderPickingPage.isInvalidErrorDisplayed();
	Thread.sleep(5000);
	puttyFunctionsPage.pressEnter();
	Thread.sleep(2000);
	puttyFunctionsPage.pressEnter();
}
 
@And("^I enter dock door \"([^\"]*)\"$")
public void EnterDockDoor(String Id) throws Throwable {
	Thread.sleep(2000);
	puttyFunctionsPage.enterPalletId(Id);
	Thread.sleep(1000);
	puttyFunctionsPage.pressEnter();
}
@And("^I enter invalid pallet \"([^\"]*)\"$")
public void EnterinvalidPallet(String Id) throws Throwable {
	Thread.sleep(2000);
	puttyFunctionsPage.enterPalletId(Id);
	Thread.sleep(1000);
	puttyFunctionsPage.pressTab();
}
@And("^I enter consignment \"([^\"]*)\"$")
public void Enterconsignment(String Id) throws Throwable {
	Thread.sleep(2000);
	puttyFunctionsPage.enterPalletId(Id);
	Thread.sleep(1000);
	puttyFunctionsPage.pressTab();
}

}