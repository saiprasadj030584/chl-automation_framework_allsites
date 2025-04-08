package com.jda.wms.functions;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import com.jda.wms.context.Context;
import com.google.inject.Inject;
import com.jda.wms.handlers.WebHandler;
import com.jda.wms.handlers.DbHandler;
import com.jda.wms.utils.Utilities;
import com.jda.wms.handlers.ValidationHandler;
import com.jda.wms.UI.pages.OrderPreparationPage;
import com.jda.wms.config.Constants;
import com.jda.wms.db.BookingInDiaryDetails;
import com.jda.wms.db.ClusteringDB;
import com.jda.wms.db.ConsignmentDB;
import com.jda.wms.db.ConsignmentDropDB;
import com.jda.wms.db.MandsDB;
import com.jda.wms.db.OrderHeaderDB;
import com.jda.wms.db.SkuDB;
import com.jda.wms.db.StockCheckDB;
import com.jda.wms.db.TrailerDB;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junit.framework.Assert;

import org.sikuli.script.Screen;

public class WebFunctions {
	private WebHandler uiHandler;
	private OrderPreparationPage orderPreparationPage;
	private ValidationHandler validationHandler;
	private ClusteringDB clusteringDB;
	private static Context context;
	private StockCheckDB stockCheckDB;
	private SkuDB skudb;
	private OrderHeaderDB orderheaderDB;
	private TrailerDB trailerdb;
	private Utilities utilities;
	private Constants constants;
	private ConsignmentDB consignmentdb;
	private BookingInDiaryDetails bookingindiarydetails;
	private MandsDB mandsdb;
	private DbHandler dbHandler;
	private ConsignmentDropDB consignmentdropdb;
	Screen screen = new Screen();

	@Inject
	public WebFunctions(WebHandler uiHandler, OrderPreparationPage orderPreparationPage,
			ValidationHandler validationHandler, ClusteringDB clusteringDB, StockCheckDB stockCheckDB, SkuDB skudb,
			Constants constants, ConsignmentDB consignmentdb, TrailerDB trailerdb, MandsDB mandsdb,
			ConsignmentDropDB consignmentdropdb, BookingInDiaryDetails bookingindiarydetails, Context context,
			OrderHeaderDB orderheaderDB,DbHandler dbHandler) {
		this.uiHandler = uiHandler;
		this.dbHandler = dbHandler;
		this.orderPreparationPage = orderPreparationPage;
		this.validationHandler = validationHandler;
		this.clusteringDB = clusteringDB;
		this.stockCheckDB = stockCheckDB;
		this.skudb = skudb;
		this.utilities = utilities;
		this.context = context;
		this.constants = constants;
		this.trailerdb = trailerdb;
		this.mandsdb = mandsdb;
		this.consignmentdropdb = consignmentdropdb;
		this.bookingindiarydetails = bookingindiarydetails;
		this.consignmentdb = consignmentdb;
		this.orderheaderDB = orderheaderDB;
	}

	@Before
	public void beforeTest() {
		System.out.println("**Execution of " + name.getMethodName() + " in WebFunctions started");
	}

	@After
	public void afterTest() {
		System.out.println("**Execution of " + name.getMethodName() + " in WebFunctions completed");
	}

	@Rule
	public TestName name = new TestName();

	@Given("^Login to the JDA screen$")
	public void login_to_webclient() throws Throwable {
		uiHandler.login_to_JDA_Dispatcher_web_screen();
	}

	@And("^Go to \"([^\"]*)\"")
	public void go_to_screen(String screen) throws Throwable {
		uiHandler.searchScreen(screen);
	}

	@And("^allocate the order with order id \"([^\"]*)\"$")
	public void allocate_Order_With_Order_Id(String orderID) throws Throwable {
		validationHandler.validateOrderStatus(orderID);
		Thread.sleep(2000);
		uiHandler.clickNextButton();
		Thread.sleep(3000);
		uiHandler.typeString(orderID);
		Thread.sleep(5000);
		uiHandler.clickNextButton();
		Thread.sleep(3000);
		uiHandler.clickNextButton();
		Thread.sleep(50000);
		uiHandler.clickDoneButton();
		Thread.sleep(50000);
		uiHandler.clickDoneButton();
		Thread.sleep(3000);
	}

	@Given("^prepare orders loaded with consignment name and order id \"([^\"]*)\" \"([^\"]*)\"$")
	public void Orderpreparation(String consignment_ID, String Orderid) throws Throwable {
		Thread.sleep(4000);
		uiHandler.clickNextButton();
		orderPreparationPage.enterOrderId(Orderid);
		Thread.sleep(2000);
		uiHandler.clickNextButton();
		Thread.sleep(2000);
		uiHandler.typeString(consignment_ID);
		Thread.sleep(2000);
		orderPreparationPage.selectAvailableRecord();
		Thread.sleep(4000);
		uiHandler.clickNextButton();
		Thread.sleep(3000);
		uiHandler.typeKey("DOWN");
		uiHandler.typeKey("DOWN");
		uiHandler.typeKey("DOWN");
		uiHandler.typeKey("DOWN");
		Thread.sleep(2000);
		uiHandler.clickNextButton();
		Thread.sleep(2000);
		uiHandler.clickNextButton();
		Thread.sleep(3000);
		uiHandler.clickNextButton();
		Thread.sleep(2000);
		uiHandler.clickDoneButton();
		uiHandler.PressEnter();
		Thread.sleep(2000);
		uiHandler.PressEnter();
		Thread.sleep(2000);
		uiHandler.clickCloseButton();
	}

	@And("^I cluster the \"([^\"]*)\" order for \"([^\"]*)\"$")
	public void i_cluster_the_order(String group_id, String site_id) throws Throwable {
		try {

			validationHandler.validateGroupId(group_id);
			Thread.sleep(2000);
			{

				if (screen.exists("/images/Clustering/ClusteringHeader.png") != null) {
					uiHandler.clickTarget("/images/Clustering/siteid.png");
					uiHandler.typeString(site_id);
					screen.click();
					Thread.sleep(2000);
					uiHandler.clickTarget("/images/Clustering/GroupId.png");
					uiHandler.typeString(group_id);
					screen.click();
					Thread.sleep(2000);
					uiHandler.clickTarget("/images/Clustering/MaxPickers.png");
					uiHandler.typeString("1");
					Thread.sleep(2000);
					uiHandler.clickTarget("/images/Clustering/Next.png");
					while (screen.exists("/images/Clustering/ClusteringScreen.png") != null) {
						System.out.println("Clustering....");
						Thread.sleep(2000);
					}
					uiHandler.clickTarget("/images/Clustering/Done.png");
					Thread.sleep(3000);
				} else {
					System.out.println("Oops! Wrong Screen!");
					Assert.fail("Oops! Wrong Screen!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@And("^I cluster the order with a config id \"([^\"]*)\" and  \"([^\"]*)\"$")
	public void i_cluster_the_order_with_a_config_id(String config_id, String site_id) throws Throwable {
		try {

			Thread.sleep(2000);
			validationHandler.validateConfigId(config_id);
			Thread.sleep(2000);
			validationHandler.validateConfigIdInData(config_id);
			Thread.sleep(2000);
			if (screen.exists("/images/Clustering/ClusteringHeader.png") != null) {

				uiHandler.clickTarget("/images/Clustering/siteid.png");
				uiHandler.clickTarget("/images/Clustering/siteid.png");
				uiHandler.typeString(site_id);
				uiHandler.PressEnter();
				Thread.sleep(2000);
				uiHandler.clickTarget("/images/Clustering/ConfigID.png");
				uiHandler.clickTarget("/images/Clustering/ConfigID.png");
				uiHandler.typeString(config_id);
				uiHandler.PressEnter();
				Thread.sleep(2000);
				uiHandler.clickTarget("/images/Clustering/MaxPickers.png");
				uiHandler.clickTarget("/images/Clustering/MaxPickers.png");
				uiHandler.typeString("1");
				uiHandler.PressEnter();
				Thread.sleep(2000);
				Thread.sleep(3000);
				uiHandler.clickTarget("/images/Clustering/Next.png");
				Thread.sleep(3000);
				uiHandler.clickTarget("/images/Clustering/Next.png");
				while (screen.exists("/images/Clustering/ClusteringScreen.png") != null) {
					System.out.println("Clustering....");
					Thread.sleep(2000);
				}
				uiHandler.clickTarget("/images/Clustering/Done.png");
				Thread.sleep(3000);

			} else {

				System.out.println("Oops! Wrong Screen!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@And("^I enter the tag id to generate list id \"([^\"]*)\"$")
	public void i_enter_the_tag_id_to_generate_list_id(String TagId) throws Throwable {

		try {

			Thread.sleep(3000);
			uiHandler.clickTarget("/images/StockCheckListGeneration/RadiobuttonGenerateByInventory.png");
			uiHandler.clickTarget("/images/StockCheckListGeneration/Next.png");
			Thread.sleep(3000);
			Match m = screen.exists("/images/StockCheckListGeneration/EnterTagId.png");
			m.setTargetOffset(70, 0);
			screen.click(m);
			uiHandler.typeString(TagId);
			uiHandler.clickTarget("/images/StockCheckListGeneration/Next.png");
			uiHandler.clickTarget("/images/StockCheckListGeneration/Add.png");
			Thread.sleep(3000);
			uiHandler.clickTarget("/images/StockCheckListGeneration/Next.png");
			uiHandler.clickTarget("/images/StockCheckListGeneration/Done.png");
			Thread.sleep(2000);
			uiHandler.clickTarget("/images/StockCheckListGeneration/Ok.png");
			Thread.sleep(3000);
			System.out.println("List id is " + stockCheckDB.getListId(TagId));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Stock Adjustment Methods
	 * 
	 * @author Mani C
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	// Updated Code below
	//
	// @Given("^Do Existing Stockadjustment for
	// \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	// public void do_Existing_Stockadjustment_for(String sku, String location,
	// String qty, String reason) throws Throwable {
	// Thread.sleep(10000);
	// String str = skudb.getSkuId(sku);
	// if(sku.equals(str))
	// {
	// Thread.sleep(6000);
	// uiHandler.clickNextButton();
	// Thread.sleep(3000);
	// screen.type(Key.TAB);
	// screen.type(Key.TAB);
	// screen.type(Key.TAB);
	// uiHandler.typeString(sku);
	// Thread.sleep(3000);
	// screen.type(Key.TAB);
	// uiHandler.typeString(location);
	// Thread.sleep(3000);
	// uiHandler.clickNextButton();
	// Thread.sleep(3000);
	// uiHandler.clickNextButton();
	// Thread.sleep(3000);
	// screen.type("a", Key.CTRL);
	// screen.type(Key.BACKSPACE);
	// uiHandler.typeString(qty);
	// Thread.sleep(3000);
	// uiHandler.clickNextButton();
	// Thread.sleep(3000);
	// uiHandler.clickTarget("/images/StockAdjustment/ReasonCode1.png");
	// uiHandler.typeString(reason);
	// Thread.sleep(3000);
	// uiHandler.clickDoneButton();
	// Thread.sleep(3000);
	// }
	// else
	// {
	// System.out.println("There is no record for this SKU");
	// }
	//
	// }

	/**
	 * New stock adjustment
	 * 
	 * @author Mani C
	 * @throws InterruptedException
	 * @throws FindFailed
	 *             Updated Code Below
	 * 
	 */

	// @Given("^Navigate to the New Stock Adjusment$")
	// public void Navigate_to_the_New_Stock_Adjustment () throws Throwable
	// {
	// uiHandler.searchScreen("Stock Adjustment");
	// }
	// @Given("^Do New Stock Adjustment for
	// \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	// public void do_New_Stock_Adjustment_for(String sku,String
	// Locationid,String Qty,String PalletType,String reason)
	// throws Throwable
	// {
	// Thread.sleep(5000);
	// uiHandler.doubleClickTarget("/images/StockAdjustment/NewStock.png");
	// Thread.sleep(3000);
	// uiHandler.clickNextButton();
	// Thread.sleep(3000);
	// screen.type(Key.TAB);
	// String OwnerId= skudb.getOwnerId(sku);
	// uiHandler.typeString(OwnerId);
	// Thread.sleep(3000);
	// screen.type(Key.TAB);
	// String SiteId= skudb.getSiteId(sku);
	// uiHandler.typeString(SiteId);
	// Thread.sleep(3000);
	// screen.type(Key.TAB);
	// uiHandler.typeString(sku);
	// Thread.sleep(3000);
	// screen.type(Key.TAB);
	// uiHandler.typeString(Locationid);
	// Thread.sleep(3000);
	// screen.type(Key.TAB);
	// String ClientId= skudb.getClientId(sku);
	// uiHandler.typeString(ClientId);
	// Thread.sleep(3000);
	// screen.type(Key.TAB);
	// screen.type(Key.TAB);
	// screen.type(Key.TAB);
	// String ConfigId= skudb.getConfigId(sku);
	// uiHandler.typeString(ConfigId);
	// Thread.sleep(3000);
	// screen.type(Key.TAB);
	// screen.type(Key.TAB);
	// uiHandler.typeString(Qty);
	// Thread.sleep(3000);
	// uiHandler.clickNextButton();
	// Thread.sleep(3000);
	// uiHandler.typeString(PalletType);
	// uiHandler.clickNextButton();
	// Thread.sleep(3000);
	// uiHandler.doubleClickTarget("/images/StockAdjustment/ReasonCode1.png");
	// uiHandler.typeString(reason);
	// Thread.sleep(4000);
	// uiHandler.clickDoneButton();
	// Thread.sleep(3000);
	// uiHandler.doubleClickTarget("/images/StockAdjustment/Yes.png");
	// Thread.sleep(3000);
	// uiHandler.doubleClickTarget("/images/StockAdjustment/Yes.png");
	//
	// }

	@Given("^Do Existing Stockadjustment for \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void do_Existing_Stockadjustment_for(String sku, String location, String qty, String reason)
			throws Throwable {
		Thread.sleep(10000);
		String str = skudb.getSkuId(sku);
		String Siteid = skudb.getSiteId(sku);
		if (Siteid == "7401") {
			if (sku.equals(str)) {
				Thread.sleep(6000);
				uiHandler.clickNextButton();
				Thread.sleep(3000);
				screen.type(Key.TAB);

				uiHandler.typeString(sku);
				Thread.sleep(3000);
				screen.type(Key.TAB);
				Thread.sleep(3000);
				screen.type(Key.TAB);
				Thread.sleep(3000);
				screen.type(Key.TAB);
				Thread.sleep(3000);
				uiHandler.typeString(location);
				Thread.sleep(3000);
				uiHandler.clickNextButton();
				Thread.sleep(3000);
				uiHandler.clickNextButton();
				Thread.sleep(3000);
				screen.type("a", Key.CTRL);
				screen.type(Key.BACKSPACE);
				uiHandler.typeString(qty);
				Thread.sleep(3000);
				uiHandler.clickNextButton();
				Thread.sleep(3000);
				uiHandler.typeString(reason);
				Thread.sleep(3000);
				uiHandler.clickDoneButton();
				Thread.sleep(3000);
			} else {
				System.out.println("There is no record for this SKU");
			}
		}

		if (Siteid == "5659" || Siteid == "5665") {
			if (sku.equals(str)) {
				Thread.sleep(6000);
				uiHandler.clickNextButton();
				Thread.sleep(3000);
				screen.type(Key.TAB);
				uiHandler.typeString(Siteid);
				screen.type(Key.TAB);
				uiHandler.typeString(sku);
				Thread.sleep(3000);
				screen.type(Key.TAB);
				uiHandler.typeString(location);
				Thread.sleep(3000);
				uiHandler.clickNextButton();
				Thread.sleep(3000);
				uiHandler.clickNextButton();
				Thread.sleep(3000);
				screen.type("a", Key.CTRL);
				screen.type(Key.BACKSPACE);
				uiHandler.typeString(qty);
				Thread.sleep(3000);
				uiHandler.clickNextButton();
				Thread.sleep(3000);

				uiHandler.typeString(reason);
				Thread.sleep(3000);
				uiHandler.clickDoneButton();
				Thread.sleep(3000);
			} else {
				System.out.println("There is no record for this SKU");
			}

		}
		if (Siteid == "5885") {
			if (sku.equals(str)) {
				Thread.sleep(6000);
				uiHandler.clickNextButton();
				Thread.sleep(3000);
				screen.type(Key.TAB);
				uiHandler.typeString(sku);
				Thread.sleep(3000);
				screen.type(Key.TAB);
				Thread.sleep(3000);
				uiHandler.typeString(location);
				Thread.sleep(3000);
				uiHandler.clickNextButton();
				Thread.sleep(3000);
				uiHandler.clickNextButton();
				Thread.sleep(3000);
				screen.type("a", Key.CTRL);
				screen.type(Key.BACKSPACE);
				uiHandler.typeString(qty);
				Thread.sleep(3000);
				uiHandler.clickNextButton();
				Thread.sleep(3000);
				uiHandler.typeString(reason);
				Thread.sleep(3000);
				uiHandler.clickDoneButton();
				Thread.sleep(3000);
			} else {
				System.out.println("There is no record for this SKU");
			}

		}

	}

	@Given("^Do New Stock Adjustment \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void do_New_Stock_Adjustment(String sku, String Locationid, String Qty, String PalletType, String reason)
			throws Throwable

	{
		String Siteid = skudb.getSiteId(sku);
		if (Siteid.equals("5649")  || Siteid.equals("5665")) {
			Thread.sleep(10000);
			uiHandler.doubleClickTarget("/images/StockAdjustment/Start/NewStock.png");
			Thread.sleep(3000);
			uiHandler.clickNextButton();
			Thread.sleep(3000);
			screen.type(Key.TAB);
			Thread.sleep(2000);
			String SiteId = skudb.getSiteId(sku);
			
			uiHandler.typeString(Constants.ClientID);
			screen.type(Key.TAB);
			Thread.sleep(2000);
			uiHandler.typeString(SiteId);
			screen.type(Key.TAB);
			Thread.sleep(3000);
			uiHandler.typeString(sku);
			Thread.sleep(3000);
			screen.type(Key.TAB);
			
			uiHandler.typeString(Locationid);
			Thread.sleep(3000);
			screen.type(Key.TAB);
			Thread.sleep(3000);
			
			uiHandler.typeString(Constants.OwnerID);
			Thread.sleep(2000);
			screen.type(Key.TAB);
			Thread.sleep(3000);
			
			screen.type(Key.TAB);
			Thread.sleep(3000);
			screen.type(Key.TAB);
			Thread.sleep(3000);

			String ConfigId = skudb.getConfigId(sku);
			uiHandler.typeString(ConfigId);
			Thread.sleep(3000);
			screen.type(Key.TAB);
//			Thread.sleep(3000);
			screen.type(Key.TAB);
			Thread.sleep(3000);
			uiHandler.typeString(Qty);
			Thread.sleep(3000);
			uiHandler.clickNextButton();
			Thread.sleep(3000);
			uiHandler.typeString(PalletType);
			Thread.sleep(3000);
			uiHandler.clickNextButton();
			Thread.sleep(3000);
			uiHandler.typeString(reason);
			Thread.sleep(4000);
			uiHandler.clickDoneButton();
			Thread.sleep(3000);
			screen.type(Key.ENTER);
			Thread.sleep(3000);
			screen.type(Key.ENTER);
		}
		if (Siteid == "7401") {
			Thread.sleep(7000);
			uiHandler.doubleClickTarget("/images/StockAdjustment/Start/NewStock.png");
			Thread.sleep(3000);
			uiHandler.clickNextButton();
			Thread.sleep(3000);
			screen.type(Key.TAB);
			uiHandler.typeString(sku);
			Thread.sleep(3000);
			screen.type(Key.TAB);
			String SiteId = skudb.getSiteId(sku);
			Thread.sleep(3000);
			uiHandler.typeString(Locationid);
			Thread.sleep(3000);
			screen.type(Key.TAB);
			Thread.sleep(3000);
			screen.type(Key.TAB);
			Thread.sleep(3000);
			screen.type(Key.TAB);
			Thread.sleep(3000);

			String ConfigId = skudb.getConfigId(sku);
			uiHandler.typeString(ConfigId);
			Thread.sleep(3000);
			screen.type(Key.TAB);
			Thread.sleep(3000);
			screen.type(Key.TAB);
			Thread.sleep(3000);
			uiHandler.typeString(Qty);
			Thread.sleep(3000);
			uiHandler.clickNextButton();
			Thread.sleep(3000);
			uiHandler.typeString(PalletType);
			Thread.sleep(3000);
			uiHandler.clickNextButton();
			Thread.sleep(3000);
			uiHandler.typeString(reason);
			Thread.sleep(4000);
			uiHandler.clickDoneButton();
			Thread.sleep(3000);
			screen.type(Key.ENTER);
			Thread.sleep(3000);
			screen.type(Key.ENTER);
		}
		if (Siteid == "5885") {

			Thread.sleep(7000);
			uiHandler.doubleClickTarget("/images/StockAdjustment/Start/NewStock.png");
			Thread.sleep(3000);
			uiHandler.clickNextButton();
			Thread.sleep(3000);
			screen.type(Key.TAB);
			uiHandler.typeString(sku);
			Thread.sleep(3000);
			screen.type(Key.TAB);
			String SiteId = skudb.getSiteId(sku);
			Thread.sleep(3000);
			uiHandler.typeString(Locationid);
			Thread.sleep(3000);
			screen.type(Key.TAB);
			Thread.sleep(3000);
			screen.type(Key.TAB);
			Thread.sleep(3000);
			screen.type(Key.TAB);
			Thread.sleep(3000);

			String ConfigId = skudb.getConfigId(sku);
			uiHandler.typeString(ConfigId);
			Thread.sleep(3000);
			screen.type(Key.TAB);
			Thread.sleep(3000);
			screen.type(Key.TAB);
			Thread.sleep(3000);
			uiHandler.typeString(Qty);
			Thread.sleep(3000);
			uiHandler.clickNextButton();
			Thread.sleep(3000);
			uiHandler.typeString(PalletType);
			Thread.sleep(3000);
			uiHandler.clickNextButton();
			Thread.sleep(3000);
			uiHandler.typeString(reason);
			Thread.sleep(4000);
			uiHandler.clickDoneButton();
			Thread.sleep(3000);
			screen.type(Key.ENTER);
			Thread.sleep(3000);
			screen.type(Key.ENTER);
		}
	}

	/**
	 * Author: Vedika The following is the code for Consignment Trailer Linking
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	@Then("^I perform consignment trailer linking for consignment \"([^\"]*)\"$")
	public void i_perform_consignment_trailer_linking_for_consignment(String consignment) {

		try {
			context.setConsignmentName(consignment);

			Thread.sleep(4000);

			if (screen.exists("/images/TrailerConsignmentLinking/ConsignmentTrailerLinkingHeader.png") != null) {

				uiHandler.clickTarget("/images/TrailerConsignmentLinking/Trailer.png");
				Thread.sleep(2000);
				uiHandler.typeString(context.getTrailerID());

				Thread.sleep(2000);
				if (screen.exists("/images/ConsignmentDrop/siteID.png") != null) {
					uiHandler.clickTarget("/images/ConsignmentDrop/siteID.png");
					Thread.sleep(2000);
					uiHandler.typeString("5542");
					Thread.sleep(2000);
					screen.click();
				}

				Thread.sleep(2000);
				uiHandler.clickTarget("/images/TrailerConsignmentLinking/Consignment.png");
				Thread.sleep(2000);
				uiHandler.typeString(consignment);
				Thread.sleep(2000);

				uiHandler.clickNext();

				uiHandler.clickNext();

				Match m = screen.find("/images/TrailerConsignmentLinking/Add.png");
				if (m != null) {
					Thread.sleep(2000);
					screen.click();
					Thread.sleep(2000);
					uiHandler.clickNext();

					// uiHandler.typeString("1");
					uiHandler.typeKey("CONTROL-A");
					uiHandler.typeString(constants.CHAMBER_ID);
					Thread.sleep(2000);
					uiHandler.typeKey("TAB");
					uiHandler.chooseFromOptions("/images/TrailerConsignmentLinking/DoorID.png");

					Thread.sleep(2000);
					uiHandler.clickDone();

					validateAfterLinking(consignment);

				} else {
					Assert.fail(constants.SCREEN_NOMATCH);
				}

			} else {
				Assert.fail(constants.SCREEN_NOMATCH);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Author: Vedika The following is the code for Trailer Creation
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	@Then("^I create a trailer \"([^\"]*)\" for site \"([^\"]*)\"$")
	public void i_create_a_trailer_for_site(String trailerName, String siteId) {
		try {

			context.setTrailerID(trailerName);

			Thread.sleep(5000);

			if ((screen.exists("/images/TrailerCreation/TrailerHeader.png") != null)
					|| (screen.exists("/images/TrailerCreation/TrailerQuery.png") != null)) {

				Thread.sleep(3000);
				if (screen.exists("/images/TrailerCreation/AddButton.png") == null) {

					System.out.println("Add Button is Not found");
					uiHandler.toggleMaintenanceMode();

				}
				uiHandler.clickAddButton();
				uiHandler.clickTarget("/images/TrailerShipping/Trailer.png");
				Thread.sleep(2000);
				uiHandler.typeString(trailerName);
				Thread.sleep(2000);
				uiHandler.typeKey("TAB");
				uiHandler.typeString(constants.TRAILER_TYPE);
				Thread.sleep(2000);
				if (siteId.equals("5542")) {

					Match image = screen.exists("/images/TrailerCreation/CarrierName.png");
					image.setTargetOffset(70, 0);
					image.click();
					Thread.sleep(2000);
					uiHandler.typeString(constants.CARRIER_NAME);
					Thread.sleep(2000);

				}
				uiHandler.clickTarget("/images/TrailerCreation/ExecuteButton.png");
				Thread.sleep(2000);
				uiHandler.clickTarget("/images/TrailerCreation/YesButton.png");

			} else {
				Assert.fail(constants.SCREEN_NOMATCH);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^I navigate to dock scheduling screen$")
	public void i_navigate_to_dock_scheduling_screen() throws FindFailed, InterruptedException {

		uiHandler.searchScreen("Dock Scheduler Screen");

	}

	/**
	 * Author: Vedika The following is the code for Booking a Dock
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	@Then("^I book a dock for site \"([^\"]*)\" and order \"([^\"]*)\" for trailer \"([^\"]*)\"$")
	public void i_book_a_dock_for_site_and_order_for_trailer(String siteID, String orderId, String trailer)
			throws InterruptedException {
		try {

			Thread.sleep(5000);

			validateConsignment(orderheaderDB.getConsignment(orderId));

			if (screen.exists("/images/DockScheduler/Start/CreateNewBooking.png") != null) {

				uiHandler.clickTarget("/images/DockScheduler/Start/CreateNewBooking.png");
				Thread.sleep(2000);
				Match m = screen.exists("/images/DockScheduler/Start/SiteID.png");
				if (m != null) {

					m.setTargetOffset(70, 0);
					m.click();
					uiHandler.typeString(siteID);
					Thread.sleep(2000);
					screen.click();

				}

				Thread.sleep(2000);
				uiHandler.clickNext();
				// uiHandler.doubleClickTarget("/images/Clustering/Next.png");

				Thread.sleep(4000);
				uiHandler.typeString("Consignment");
				uiHandler.typeKey("TAB");
				uiHandler.typeString(orderheaderDB.getConsignment(orderId));
				Thread.sleep(3000);
				uiHandler.clickTarget("/images/JDAHome/Search_button.png");
				Thread.sleep(2000);

				m = screen.find("/images/DockScheduler/Build/Consignment.png");
				Thread.sleep(2000);
				screen.doubleClick(m.below(10));

				uiHandler.clickNext();

				screen.click("images/DockScheduler/Schedule/Slot.png");
				Thread.sleep(3000);

				uiHandler.clickNext();
				m = screen.exists("/images/DockScheduler/Schedule/BookingDetails/DockStatus.png");
				m.setTargetOffset(70, 0);
				m.click();
				uiHandler.typeString("In Progress");
				uiHandler.typeKey("TAB");
				uiHandler.typeKey("TAB");
				Thread.sleep(2000);
				uiHandler.typeKey("TAB");
				uiHandler.typeString(constants.TRAILER_TYPE);
				Thread.sleep(2000);
				uiHandler.typeKey("TAB");
				uiHandler.typeString(trailer);
				uiHandler.typeKey("TAB");
				uiHandler.typeString("10");
				uiHandler.typeKey("TAB");
				uiHandler.typeString("10");
				Thread.sleep(3000);
				uiHandler.clickTarget("/images/DockScheduler/Schedule/BookingDetails/Ok.png");
				Thread.sleep(3000);
				uiHandler.clickTarget("/images/Clustering/Done.png");

				validateAfterDockSchedule(orderheaderDB.getConsignment(orderId));
				;

			} else {

				Assert.fail(constants.SCREEN_NOMATCH);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Author: Vedika The following is the code for validate if Consignment is
	 * present
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	public void validateConsignment(String consignment) throws ClassNotFoundException, SQLException {

		System.out.println("Validating Consignment.....");

		try {
			if (consignmentdb.isConsignmentExist(consignment) != false) {
				System.out.println("Consignment Created Successfully!");
			} else {
				Assert.fail("Consignment is not present.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Author: Vedika The following is the code for validation after Pallet
	 * Consignment Linking
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	public void validateAfterLinking(String consignment) throws ClassNotFoundException, SQLException {

		System.out.println("Validating Trailer Consignment Linking.....");

		try {
			if (consignmentdb.getTrailerID(consignment) != null) {
				System.out.println("Linked Successfully!");
			} else {
				Assert.fail(constants.PROCESS_INCOMPLETE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Author: Vedika The following is the code for validation after Trailer
	 * Creation
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	public void validateAfterTrailerCreation(String trailerID) throws ClassNotFoundException, SQLException {

		System.out.println("Validating Trailer Creation ....");

		try {
			if (trailerdb.getTrailerDetails(trailerID) != null) {
				System.out.println("Trailer Created Successfully!");
			} else {
				Assert.fail(constants.PROCESS_INCOMPLETE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Author: Vedika The following is the code for Consignment Creation for
	 * HEMEL
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	@Then("^I create consignment \"([^\"]*)\" for HEMEL with address id \"([^\"]*)\"$")
	public void i_create_consignment_for_HEMEL(String consignmentName, String addressId) {

		Match image;

		try {
			context.setConsignmentName(consignmentName);
			if (screen.exists("/images/OrderPreparationHEMEL/ConsignmentHeader.png") != null
					|| screen.exists("/images/OrderPreparationHEMEL/ConsignmentQuery.png") != null) {

				if (screen.exists("/images/TrailerCreation/AddButton.png") == null) {

					System.out.println("Toggling Maintenance Mode");
					uiHandler.toggleMaintenanceMode();
				}
				uiHandler.clickAddButton();
				uiHandler.clickTarget("/images/OrderPreparationHEMEL/Consignment.png");
				Thread.sleep(2000);
				uiHandler.typeString(consignmentName);

				if (screen.exists("/images/Clustering/siteid.png") != null) {
					uiHandler.clickTarget("/images/Clustering/siteid.png");
					Thread.sleep(2000);
					uiHandler.typeString("5542");
					Thread.sleep(2000);
					screen.click();
				}

				uiHandler.doubleClickTarget("/images/OrderPreparationHEMEL/Misc1.png");
				Thread.sleep(3000);

				image = screen.exists("/images/TrailerCreation/CarrierName.png");
				image.setTargetOffset(70, 0);
				image.click();
				Thread.sleep(2000);
				uiHandler.typeString(constants.CARRIER_NAME);
				Thread.sleep(2000);

				image = screen.exists("/images/OrderPreparationHEMEL/ServiceLevel.png");
				image.setTargetOffset(70, 0);
				image.click();
				Thread.sleep(2000);
				uiHandler.typeString(constants.SERVICE_LEVEL);
				Thread.sleep(2000);

				uiHandler.clickTarget("/images/OrderPreparationHEMEL/ConsignmentBuild.png");
				Thread.sleep(3000);
				uiHandler.clickTarget("/images/OrderPreparationHEMEL/ModeOfTransport.png");
				Thread.sleep(2000);
				uiHandler.typeString(mandsdb.getModeOfTransport(addressId));
				Thread.sleep(2000);
				uiHandler.doubleClickTarget("/images/OrderPreparationHEMEL/DateofLoading.png");
				Thread.sleep(2000);
				uiHandler.chooseCurrentDate();
				Thread.sleep(2000);
				uiHandler.clickTarget("/images/OrderPreparationHEMEL/AirwayBill.png");
				Thread.sleep(2000);
				uiHandler.typeString(consignmentdb.getAirwayBill());
				Thread.sleep(2000);
				uiHandler.clickTarget("/images/OrderPreparationHEMEL/PortofLoading.png");
				Thread.sleep(2000);
				uiHandler.typeString(consignmentdb.getPortOfLoading());
				Thread.sleep(2000);

				uiHandler.clickTarget("/images/TrailerCreation/ExecuteButton.png");
				Thread.sleep(2000);
				validateAfterConsignmentCreation(consignmentName);

			} else {
				System.out.println(constants.SCREEN_NOMATCH);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Author: Vedika The following is the code for Consignment Drop
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	@Then("^I perform Consignment Drop for consignment with address id \"([^\"]*)\"$")
	public void i_perform_Consignment_Drop_for_consignment_with_address_id(String addressId)
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {

		if (screen.exists("/images/ConsignmentDrop/ConsignmentDropHeader.png") != null
				|| screen.exists("/images/ConsignmentDrop/ConsignmentDropHeader1.png") != null) {

			if (screen.exists("/images/TrailerCreation/AddButton.png") == null) {

				System.out.println("Toggling Maintenance Mode");
				uiHandler.toggleMaintenanceMode();
			}
			uiHandler.clickAddButton();

			Thread.sleep(3000);
			uiHandler.clickTarget("/images/ConsignmentDrop/Consignment.png");
			Thread.sleep(2000);
			uiHandler.typeString(context.getConsignmentName());

			if (screen.exists("/images/ConsignmentDrop/siteID.png") != null) {
				uiHandler.clickTarget("/images/ConsignmentDrop/siteID.png");
				Thread.sleep(2000);
				uiHandler.typeString("5542");
				Thread.sleep(2000);
				screen.click();
			}

			Thread.sleep(2000);
			uiHandler.doubleClickTarget("/images/ConsignmentDrop/ChamberID.png");
			Thread.sleep(2000);
			uiHandler.typeString(constants.CHAMBER_ID);

			Thread.sleep(2000);
			// uiHandler.clickTarget("/images/ConsignmentDrop/AddressID.png");
			uiHandler.typeKey("TAB");
			Thread.sleep(2000);
			uiHandler.typeString(addressId);

			Thread.sleep(2000);
			// uiHandler.clickTarget("/images/ConsignmentDrop/LoadSequence.png");
			uiHandler.typeKey("TAB");
			Thread.sleep(2000);
			uiHandler.typeString(constants.LOAD_SEQUENCE);

			Thread.sleep(2000);
			// uiHandler.clickTarget("/images/ConsignmentDrop/MaxPallets.png");
			uiHandler.typeKey("TAB");
			Thread.sleep(2000);
			uiHandler.typeString(constants.MAX_PALLETS);

			uiHandler.clickTarget("/images/TrailerCreation/ExecuteButton.png");
			Thread.sleep(2000);

			validateAfterConsignmentDropCreation(context.getConsignmentName());

		} else {
			Assert.fail(constants.SCREEN_NOMATCH);
		}

	}

	/**
	 * Author: Vedika The following is the code for validation after Consignment
	 * Creation
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	public void validateAfterConsignmentCreation(String consignment) throws ClassNotFoundException, SQLException {

		System.out.println("Validating Consignment Creation.....");

		try {
			if (consignmentdb.isConsignmentExist(consignment) != false) {
				System.out.println("Consignment Created Successfully!");
			} else {
				Assert.fail(constants.PROCESS_INCOMPLETE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Author: Vedika The following is the code for validation after Consignment
	 * Drop Creation
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	public void validateAfterConsignmentDropCreation(String consignment) throws ClassNotFoundException, SQLException {

		System.out.println("Validating Consignment Drop Creation.....");

		try {
			if (consignmentdropdb.isConsignmentDropExist(consignment) != false) {
				System.out.println("Consignment Drop Done Successfully!");
			} else {
				Assert.fail(constants.PROCESS_INCOMPLETE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Author: Vedika The following is the code for validation after Dock
	 * Scheduling
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	public void validateAfterDockSchedule(String consignment) throws ClassNotFoundException, SQLException {

		System.out.println("Validating Dock Schedule.....");

		try {
			if (bookingindiarydetails.getBookingID(consignment, context.getTrailerID()) != null) {
				System.out.println("Dock Scheduled Successfully!");
			} else {
				Assert.fail(constants.PROCESS_INCOMPLETE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// *****************Trailer Shipping******************//

	@Then("^I navigate to \"([^\"]*)\"$")
	public void i_navigate_to(String screenname) throws Throwable {
		uiHandler.searchScreen(screenname);
	}

	/**
	 * @author SRIRAMASUBRAMANIAN.K The below code is for Trailer Shipment for
	 *         all DCs except HEMEL
	 * @param siteid
	 * @param trailer
	 * @param sealid
	 * @throws Throwable
	 */

	@Given("^I enter the site id Trailer id and seal id to perform shipment of trailer \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void i_enter_the_site_id_Trailer_id_and_seal_id_to_perform_shipment_of_trailer(String siteid, String trailer,
			String sealid) throws Throwable {

		try {

			Thread.sleep(3000);

			Match m = screen.exists("/images/TrailerShipping/SiteID.png");
			if (screen.exists("/images/TrailerShipping/SiteID.png") != null) {
				Thread.sleep(3000);
				uiHandler.typeKey("TAB");
				uiHandler.typeString(siteid);
				Thread.sleep(3000);
				uiHandler.typeKey("TAB");
				uiHandler.typeString(trailer);
				uiHandler.doubleClickTarget("/images/TrailerShipping/Next.png");
				Thread.sleep(3000);

				uiHandler.doubleClickTarget("/images/TrailerShipping/SealNO.png");
				uiHandler.typeString(sealid);
				Thread.sleep(2000);
				uiHandler.doubleClickTarget("/images/TrailerShipping/okk.png");
				Thread.sleep(3000);
				uiHandler.doubleClickTarget("/images/TrailerShipping/Done.png");
			} else {
				uiHandler.typeKey("TAB");
				uiHandler.typeString(trailer);
				uiHandler.doubleClickTarget("/images/TrailerShipping/Next.png");
				Thread.sleep(3000);

				uiHandler.doubleClickTarget("/images/TrailerShipping/SealNO.png");
				uiHandler.typeString(sealid);
				Thread.sleep(2000);
				uiHandler.doubleClickTarget("/images/TrailerShipping/okk.png");
				Thread.sleep(3000);
				uiHandler.doubleClickTarget("/images/TrailerShipping/Done.png");
			}
			Match i = screen.exists("/images/TrailerShipping/TrailerNotFoundError.png");

			if (i == i) {
				uiHandler.doubleClickTarget("/images/TrailerShipping/okk.png");
			} else
				uiHandler.doubleClickTarget("/images/TrailerShipping/Done.png");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author SRIRAMASUBRAMANIAN.K
	 * @param receipttype
	 * @param receiptid
	 * @param lineid
	 * @param siteid
	 * @param skuid
	 * @param tagid
	 * @param qty
	 * @throws Throwable
	 */
	@Given("^I enter the Receipt type Receipt id Line id site id Sku id Tag id and qty to reverse to perform Receipt reversal \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void i_enter_the_Receipt_type_Receipt_id_Line_id_site_id_Sku_id_Tag_id_and_qty_to_reverse_to_perform_Receipt_reversal(
			String receipttype, String receiptid, String lineid, String siteid, String skuid, String tagid, String qty)
			throws Throwable {
		try {
			dbHandler.tagidcheck(tagid);

			Thread.sleep(2000);
			uiHandler.typeString(receipttype);
			Thread.sleep(2000);
			uiHandler.typeKey("TAB");
			uiHandler.typeString(receiptid);
			Thread.sleep(2000);
			uiHandler.typeKey("TAB");
			uiHandler.typeString(lineid);
			Thread.sleep(2000);
			uiHandler.typeKey("TAB");
			uiHandler.typeString(siteid);
			uiHandler.typeKey("TAB");
			uiHandler.typeKey("TAB");
			uiHandler.typeKey("TAB");
			Thread.sleep(2000);
			uiHandler.typeString(skuid);
			uiHandler.typeKey("TAB");
			Thread.sleep(2000);
			uiHandler.typeString(tagid);
			uiHandler.typeKey("TAB");
			Thread.sleep(2000);
			uiHandler.doubleClickTarget("/images/ReceiptReversal/Next.png");
			if (qty == "All") {
				Thread.sleep(2000);
				uiHandler.doubleClickTarget("/images/ReceiptReversal/ScrollNext.png");
				Thread.sleep(2000);
				uiHandler.doubleClickTarget("/images/ReceiptReversal/checkbox.png");
				Thread.sleep(2000);
				uiHandler.doubleClickTarget("/images/ReceiptReversal/Next.png");
				Thread.sleep(2000);
				uiHandler.doubleClickTarget("/images/ReceiptReversal/Done.png");
				Thread.sleep(2000);
				uiHandler.doubleClickTarget("/images/ReceiptReversal/yesbutton.png");
			} else {
				Thread.sleep(2000);
				uiHandler.doubleClickTarget("/images/ReceiptReversal/QtyToReverse.png");
				Thread.sleep(2000);
				uiHandler.typeString(qty);
				Thread.sleep(2000);
				uiHandler.doubleClickTarget("/images/ReceiptReversal/Next.png");
				Thread.sleep(2000);
				uiHandler.doubleClickTarget("/images/ReceiptReversal/Done.png");
				Thread.sleep(2000);
				uiHandler.doubleClickTarget("/images/ReceiptReversal/yesbutton.png");
			}
		} finally {

		}

	}

	/**
	 * Receipt Reversal -Welham Komagal
	 */

	@Given("^I Reverse the receipt for the SiteID \"([^\"]*)\" Receipt type \"([^\"]*)\" ReceiptID \"([^\"]*)\" LineID \"([^\"]*)\" SkuID \"([^\"]*)\" TagID \"([^\"]*)\" QTY \"([^\"]*)\"$")
	public void i_Reverse_the_receipt(String SiteID, String Receipt_type, String ReceiptID, String LineID, String SkuID,
			String TagID, String QTY) throws Throwable {

		try {

			// try {

			validationHandler.validateTagId(TagID);
			Thread.sleep(2000);

			if (SiteID.equals("7401")) {

				Thread.sleep(3000);
				// uiHandler.clickTarget("/images/ReceiptReversal/ReceiptReversal_KO/ReceiptType.PNG");
				uiHandler.typeString(Receipt_type);
				Thread.sleep(3000);

				uiHandler.typeKey("TAB");
				uiHandler.typeString(ReceiptID);
				Thread.sleep(2000);

				uiHandler.typeKey("TAB");
				uiHandler.clickTarget("/images/ReceiptReversal_KO/LineID.PNG");
				uiHandler.typeString(LineID);
				Thread.sleep(2000);

				if (screen.exists("/images/ReceiptReversal_KO/SiteID.PNG") != null) {
					uiHandler.clickTarget("/images/ReceiptReversal_KO/SiteID.PNG");
					Thread.sleep(2000);
					uiHandler.typeString(SiteID);
					Thread.sleep(2000);
				}

				if (screen.exists("/images/ReceiptReversal_KO/OwnerID.png") != null) {
					uiHandler.clickTarget("/images/ReceiptReversal_KO/OwnerID.png");
					Thread.sleep(2000);
					uiHandler.typeString(Constants.OwnerID);
					Thread.sleep(2000);
					screen.click();
					screen.type(Key.TAB);
				}

				if (screen.exists("/images/ReceiptReversal_KO/ClientID.png") != null) {
					// uiHandler.clickTarget("/images/ReceiptReversal_KO/ClientID.png");
					Thread.sleep(2000);
					uiHandler.typeString(Constants.ClientID);
					Thread.sleep(2000);
				}

				screen.type(Key.TAB);
				uiHandler.typeString(SkuID);
				Thread.sleep(3000);

				screen.type(Key.TAB);
				uiHandler.typeString(TagID);
				Thread.sleep(3000);
				uiHandler.clickNextButton();
				Thread.sleep(3000);

				if (screen.exists("/images/ReceiptReversal_KO/noInventory.PNG") != null) {
					Assert.fail("No inventory exists.Please enter correct data");
				}

				if (QTY.equals("ALL")) {
					// uiHandler.doubleClickTarget("images/ReceiptReversal/ScrollNext.png");
					uiHandler.clickTarget("images/ReceiptReversal_KO/Reversals/CheckBox.png");
					uiHandler.clickNextButton();

					uiHandler.doubleClickTarget("images/ReceiptReversal_KO/Done1.png");

					uiHandler.clickTarget("images/ReceiptReversal_KO/Yes.png");
					if (screen.exists("images/ReceiptReversal_KO/Yes.png") != null) {
						uiHandler.clickTarget("images/ReceiptReversal_KO/Yes.png");
					}
					if (screen.exists("/images/ReceiptReversal_KO/ReceiptReversalError.png") != null) {
						Assert.fail(Constants.PROCESS_INCOMPLETE);
					}
				} else {

					uiHandler.doubleClickTarget("images/ReceiptReversal_KO/QTYtoReverse1.png");
					uiHandler.doubleClickTarget("images/ReceiptReversal_KO/QTYtoReverse2.png");
					uiHandler.typeString(QTY);
					uiHandler.clickNextButton();
					// Thread.sleep(3000);
					uiHandler.doubleClickTarget("images/ReceiptReversal_KO/Done1.png");
					// Thread.sleep(3000);
					uiHandler.clickTarget("images/ReceiptReversal_KO/Yes.png");

					if (screen.exists("images/ReceiptReversal_KO/Yes.png") != null) {
						uiHandler.clickTarget("images/ReceiptReversal_KO/Yes.png");
					}

					if (screen.exists("/images/ReceiptReversal_KO/ReceiptReversalError.png") != null) {
						Assert.fail(Constants.PROCESS_INCOMPLETE);
					}

				}

			}

			else {
				System.out.println("This is not Welham Site");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author Vedika.Vinod The below function is to perform receipt reversal
	 * @param order_id
	 * @param site_id
	 * @param cluster_type
	 * @param cluster_type_value
	 * @throws Throwable
	 */
	@Then("^I perform receipt reversal for site \"([^\"]*)\" for type \"([^\"]*)\" reversal with line id \"([^\"]*)\" sku id \"([^\"]*)\" tag id \"([^\"]*)\" receipt id \"([^\"]*)\" for quantity \"([^\"]*)\"$")
	public void i_perform_receipt_reversal_for_site_for_type_reversal_with_line_id_sku_id_tag_id_receipt_id_for_quantity(
			String siteid, String receipt_type, String line_id, String sku_id, String tag_id, String receipt_id,
			String qty) throws Throwable {

		if (screen.exists("/images/ReceiptReversal/Reversals/ReceiptReversalHeader.PNG") != null) {
			System.out.println("Starting receipt reversal");
			Thread.sleep(2000);
			uiHandler.typeString(receipt_type);
			uiHandler.typeKey("TAB");
			Thread.sleep(2000);
			// if (!receipt_id.equals("")) {
			uiHandler.typeString(receipt_id);
			Thread.sleep(2000);
			// }
			uiHandler.typeKey("TAB");
			// if (!line_id.equals("")) {
			uiHandler.typeString(line_id);
			Thread.sleep(2000);
			// }
			// uiHandler.typeKey("TAB");
			if (!sku_id.equals("")) {
				uiHandler.clickTarget("/images/ReceiptReversal/Reversals/SKUID.PNG");
				Thread.sleep(2000);
				uiHandler.typeString(sku_id);
				Thread.sleep(2000);
			}
			// uiHandler.typeKey("TAB");
			if (!tag_id.equals("")) {
				uiHandler.clickTarget("/images/ReceiptReversal/Reversals/TagId.PNG");
				Thread.sleep(2000);
				uiHandler.typeString(tag_id);
				Thread.sleep(2000);
			}

			uiHandler.clickNextButton();

			if (screen.exists("/images/ReceiptReversal/Reversals/NoInventory.PNG") != null) {
				Assert.fail("No inventory exists.Please enter correct data");
			}
			// if
			// (screen.exists("/images/ReceiptReversal/Reversals/NoInventory.PNG")
			// == null) {
			// Assert.fail("Something went wrong." + Constants.SCREEN_NOMATCH);
			// }

			if (qty.equals("Y")) {

				Match m = screen.find("/images/ReceiptReversal/Reversals/checkbox.png");
				m.click();

			} else {

				Match m = screen.find("/images/ReceiptReversal/Reversals/QtyToReverse.png");
				screen.click(m.below(10));
				Thread.sleep(2000);
				uiHandler.typeString(qty);
				Thread.sleep(2000);

			}

			uiHandler.clickNextButton();
			Thread.sleep(2000);

			uiHandler.clickTarget("/images/StockCheckListGeneration/Done.png");
			Thread.sleep(2000);

			uiHandler.clickTarget("/images/TrailerCreation/YesButton.png");

			if (screen.exists("/images/TrailerCreation/YesButton.png") != null) {
				uiHandler.clickTarget("/images/TrailerCreation/YesButton.png");
			}

			if (screen.exists("/images/TrailerCreation/YesButton.png") != null) {
				uiHandler.clickTarget("/images/TrailerCreation/YesButton.png");
			}

			if (screen.exists("/images/ReceiptReversal/Reversals/ReceiptReversalError.PNG") != null) {
				Assert.fail(Constants.PROCESS_INCOMPLETE);
			}

		} else

		{
			Assert.fail(Constants.SCREEN_NOMATCH);
		}
	}

	@And("^unpick the order with tagid or consignment or sku or container or order and siteid \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void unpick_the_order(String tag_id, String consignment, String sku, String container, String order,
			String site_id) throws Throwable {
		System.out.println("it entered the unpick");

		uiHandler.clickTarget("/images/UnPicking/siteid.png");
		uiHandler.typeString(site_id);
		screen.click();
		Thread.sleep(2000);
		uiHandler.clickTarget("/images/UnPicking/next.png");
		Thread.sleep(2000);
		while (screen.exists("/images/UnPicking/search.png") != null) {
			if (tag_id.length() != 0) {
				uiHandler.clickTarget("/images/UnPicking/tagid.png");
				uiHandler.typeString(tag_id);
			}
			if (consignment.length() != 0) {
				uiHandler.clickTarget("/images/UnPicking/consignment.png");
				uiHandler.typeString(consignment);
			}
			if (sku.length() != 0) {
				uiHandler.clickTarget("/images/UnPicking/sku.png");
				uiHandler.typeString(sku);
			}
			if (container.length() != 0) {
				uiHandler.clickTarget("/images/UnPicking/container.png");
				uiHandler.typeString(container);
			}
			if (order.length() != 0) {
				uiHandler.clickTarget("/images/UnPicking/ordersearch.png");
				uiHandler.typeString(order);
			}
			screen.click();
			Thread.sleep(2000);
			uiHandler.clickTarget("/images/UnPicking/next.png");
			Thread.sleep(2000);
		}

		screen.wait("images/UnPicking/picked.png", 10);
		screen.click("images/UnPicking/picked.png");
		Thread.sleep(1000);
		uiHandler.typeKey("CONTROL-A");

		Thread.sleep(2000);
		uiHandler.clickTarget("/images/UnPicking/next.png");
		Thread.sleep(2000);

		Thread.sleep(2000);
		uiHandler.clickTarget("/images/UnPicking/done.png");
		Thread.sleep(2000);

	}

	@And("^unpick the order with tagid or consignment or sku or container or order and siteid,numberoforder \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void unpick_the_order(String tag_id, String consignment, String sku, String container, String order,
			String site_id, String numberoforder) throws Throwable {
		System.out.println("it entered the unpick");

		uiHandler.clickTarget("/images/UnPicking/siteid.png");
		uiHandler.typeString(site_id);
		screen.click();
		Thread.sleep(2000);
		uiHandler.clickTarget("/images/UnPicking/next.png");
		Thread.sleep(2000);
		while (screen.exists("/images/UnPicking/search.png") != null) {
			if (tag_id.length() != 0) {
				uiHandler.clickTarget("/images/UnPicking/tagid.png");
				uiHandler.typeString(tag_id);
			}
			if (consignment.length() != 0) {
				uiHandler.clickTarget("/images/UnPicking/consignment.png");
				uiHandler.typeString(consignment);
			}
			if (sku.length() != 0) {
				uiHandler.clickTarget("/images/UnPicking/sku.png");
				uiHandler.typeString(sku);
			}
			if (container.length() != 0) {
				uiHandler.clickTarget("/images/UnPicking/container.png");
				uiHandler.typeString(container);
			}
			if (order.length() != 0) {
				uiHandler.clickTarget("/images/UnPicking/ordersearch.png");
				uiHandler.typeString(order);
			}
			screen.click();
			Thread.sleep(2000);
			uiHandler.clickTarget("/images/UnPicking/next.png");
			Thread.sleep(2000);
		}

		screen.wait("images/UnPicking/picked.png", 10);
		screen.click("images/UnPicking/picked.png");
		Thread.sleep(1000);
		for (int i = 1; i < Integer.parseInt(numberoforder); i++) {
			uiHandler.typeKey("SHIFT-DOWN");
		}
		uiHandler.clickNext();
		Thread.sleep(2000);
		uiHandler.clickTarget("/images/UnPicking/next.png");
		Thread.sleep(2000);

		Thread.sleep(2000);
		uiHandler.clickTarget("/images/UnPicking/done.png");
		Thread.sleep(2000);

	}

	@And("^allocate the order with order id for new features\"([^\"]*)\"$")
	public void allocate_Order_With_Order_Id_for_new_features(String orderID) throws Throwable{
		validationHandler.validateOrderStatus(orderID);
		if(orderheaderDB.getStatus(orderID).equals("Allocated")){
			System.out.println("Order is Already Allocated");
			return;
		}
		Thread.sleep(2000);
		uiHandler.clickNextButton();		
		Thread.sleep(3000);
		uiHandler.typeString(orderID);
		Thread.sleep(5000);
		uiHandler.clickNextButton();
		Thread.sleep(3000);
		uiHandler.clickNextButton();
		Thread.sleep(50000);
		uiHandler.clickDoneButton();
		Thread.sleep(50000);
		uiHandler.clickDoneButton();
		Thread.sleep(3000);
	}


	@Given("^prepare orders loaded with consignment name and order id for new features\"([^\"]*)\" \"([^\"]*)\"$")
	public void Orderpreparation_for_new_features(String consignment_ID, String Orderid) throws Throwable{
		Thread.sleep(4000);
		uiHandler.clickNextButton();
		orderPreparationPage.enterOrderId(Orderid);
		Thread.sleep(2000);
		uiHandler.clickNextButton();
		Thread.sleep(2000);
		if (screen.exists("/images/OrderPreparation/Incorrect_OrderID.png") != null) {
			System.out.println("Invalid order_id or order_id already have consignment");
			return;
		}
		Thread.sleep(2000);
		uiHandler.typeString(consignment_ID);
		Thread.sleep(2000);
		orderPreparationPage.selectAvailableRecord();
		Thread.sleep(4000);
		uiHandler.clickNextButton();
		Thread.sleep(3000);
		uiHandler.typeKey("DOWN");
		uiHandler.typeKey("DOWN");
		uiHandler.typeKey("DOWN");
		uiHandler.typeKey("DOWN");
		Thread.sleep(2000);
		uiHandler.clickNextButton();
		Thread.sleep(2000);
		uiHandler.clickNextButton();
		Thread.sleep(3000);
		uiHandler.clickNextButton();
		Thread.sleep(2000);
		uiHandler.clickDoneButton();
		Thread.sleep(2000);
		uiHandler.PressEnter();
		Thread.sleep(2000);
		uiHandler.PressEnter();
		if (screen.exists("/images/OrderPreparation/Consignment_already_in_use.png") != null) {
			System.out.println("The consignment number is already for another orders");
			return;
		}
		Thread.sleep(2000);
	    uiHandler.clickCloseButton();
	}
	
}
