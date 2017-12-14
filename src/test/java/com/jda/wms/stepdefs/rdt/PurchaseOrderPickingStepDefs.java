package com.jda.wms.stepdefs.rdt;


import com.jda.wms.stepdefs.gm.SchedulerProgramStepDefs;
import com.jda.wms.stepdefs.gm.ClusteringStepDefs;
import com.jda.wms.stepdefs.gm.JDAHomeStepDefs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Assert;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.AddressDB;
import com.jda.wms.db.gm.BookingInDiary;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.MoveTaskDB;
import com.jda.wms.db.gm.MoveTaskUpdateDB;
import com.jda.wms.db.gm.OrderContainerDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.rdt.PurchaseOrderPickingPage;
import com.jda.wms.pages.rdt.PurchaseOrderRelocatePage;
import com.jda.wms.pages.rdt.PurchaseOrderVehicleLoadingPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderPickingPage;
import com.jda.wms.pages.rdt.PurchaseOrderVehicleLoadingPage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.stepdefs.gm.JDAHomeStepDefs;
import com.jda.wms.stepdefs.gm.SchedulerProgramStepDefs;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PurchaseOrderPickingStepDefs {
	private PurchaseOrderPickingPage purchaseOrderPickingPage;
	Map<Integer, Map<String, String>> poMap;
	Map<String, Map<String, String>> upiMap;
	private Context context;
	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private Verification verification;
	private InventoryDB inventoryDB;
	private LocationDB locationDB;
	private Hooks hooks;
	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private PuttyFunctionsPage puttyFunctionsPage;
	private MoveTaskDB moveTaskDB;
	private MoveTaskUpdateDB moveTaskUpdateDB;
	private PurchaseOrderVehicleLoadingPage purchaseOrderVehicleLoadingPage;
	private BookingInDiary bookingInDiary;
	private OrderContainerDB orderContainerDB;
	private OrderHeaderDB orderHeaderDB;
	private AddressDB addressDB;

	private PurchaseOrderRelocatePage purchaseOrderRelocatePage;
	private SchedulerProgramStepDefs schedulerProgramStepDefs;

 private ClusteringStepDefs clusteringStepDefs;
 private OrderLineDB orderLineDB;

	@Inject
	public PurchaseOrderPickingStepDefs(PurchaseOrderPickingPage purchaseOrderPickingPage, Context context,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs, Verification verification, InventoryDB inventoryDB,
			LocationDB locationDB,OrderLineDB orderLineDB, Hooks hooks, JDAFooter jdaFooter, PuttyFunctionsPage puttyFunctionsPage,MoveTaskDB moveTaskDB, MoveTaskUpdateDB moveTaskUpdateDB,
			PurchaseOrderVehicleLoadingPage purchaseOrderVehicleLoadingPage, BookingInDiary bookingInDiary,
			OrderContainerDB orderContainerDB, OrderHeaderDB orderHeaderDB, AddressDB addressDB,
			SchedulerProgramStepDefs schedulerProgramStepDefs, JDAHomeStepDefs jdaHomeStepDefs,
			ClusteringStepDefs clusteringStepDefs,PurchaseOrderRelocatePage purchaseOrderRelocatePage) {


		this.context = context;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.verification = verification;
		this.inventoryDB = inventoryDB;
		this.locationDB = locationDB;
		this.hooks = hooks;
		this.jdaFooter = jdaFooter;
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.purchaseOrderPickingPage = purchaseOrderPickingPage;
		this.moveTaskDB = moveTaskDB;
		this.moveTaskUpdateDB = moveTaskUpdateDB;
		this.purchaseOrderVehicleLoadingPage = purchaseOrderVehicleLoadingPage;
		this.bookingInDiary = bookingInDiary;
		this.orderContainerDB = orderContainerDB;
		this.orderHeaderDB = orderHeaderDB;
		this.addressDB = addressDB;
		this.schedulerProgramStepDefs = schedulerProgramStepDefs;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.clusteringStepDefs = clusteringStepDefs;
this.orderLineDB=orderLineDB;
	this.purchaseOrderRelocatePage=purchaseOrderRelocatePage;
	}

	@Given("^I perform picking$")
	public void i_perform_picking() throws Throwable {
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		Assert.assertNotNull("List ID is not generated as expected", context.getListID());

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		if (context.getListID().contains("PICK")) {
			purchaseOrderPickingPage.enterListId(context.getListID());
			puttyFunctionsPage.pressEnter();
			purchaseOrderPickingPage.enterPrinterNO("P2003");
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
			String[] putawayLocation = purchaseOrderPickingPage.getPickingLocation().split("_");
			String toLocation = putawayLocation[0];
			context.setToLocation(toLocation);
			puttyFunctionsPage.pressEnter();
			i_enter_the_check_string_for_marshalling();
			puttyFunctionsPage.pressEnter();
		} else if (context.getListID().contains("DOL")) {
			if (context.getSKUType().equalsIgnoreCase("Boxed")) {
				moveTaskUpdateDB.releaseOrderId(context.getOrderId());
				purchaseOrderPickingPage.enterListId(context.getListID());
				puttyFunctionsPage.pressEnter();
				puttyFunctionsPage.pressEnter();
				String containerId = moveTaskDB.getContainerId(context.getOrderId());
				purchaseOrderPickingPage.enterContainerId(containerId);
				puttyFunctionsPage.pressEnter();
				puttyFunctionsPage.pressEnter();
			} else if (context.getSKUType().equalsIgnoreCase("Hanging")) {
				moveTaskUpdateDB.releaseOrderId(context.getOrderId());
				
				purchaseOrderPickingPage.enterListId(context.getListID());

				puttyFunctionsPage.pressEnter();
				String customer = orderHeaderDB.getCustomer(context.getOrderId());
				context.setCustomer(customer);
				String tagValueL = addressDB.getLowerTagValue();
				String tagValueH = addressDB.getHigherTagValue();
				int tag = (int) (Math.random() * (Integer.parseInt(tagValueH) - Integer.parseInt(tagValueL)))
						+ Integer.parseInt(tagValueL);
				context.setTagId(String.valueOf(tag));
				System.out.println(String.valueOf(tag));
				purchaseOrderPickingPage.enterTagId(context.getTagId());
				puttyFunctionsPage.pressEnter();
				puttyFunctionsPage.pressEnter();
				purchaseOrderPickingPage.enterContainerId(String.valueOf(tag));
				puttyFunctionsPage.pressEnter();
			}
		} else if (context.getListID().contains("HNRT")) {
			moveTaskUpdateDB.releaseOrderId(context.getOrderId());
			System.out.println("entered zoom");
			purchaseOrderPickingPage.enterListId(context.getListID());
			System.out.println("entered zoom1");
			puttyFunctionsPage.pressEnter();

			String customer = orderHeaderDB.getCustomer(context.getOrderId());
			context.setCustomer(customer);
			String tagValueL = addressDB.getLowerTagValue();
			String tagValueH = addressDB.getHigherTagValue();
			int tag = (int) (Math.random() * (Integer.parseInt(tagValueH) - Integer.parseInt(tagValueL)))
					+ Integer.parseInt(tagValueL);
			context.setTagId(String.valueOf(tag));
			System.out.println(String.valueOf(tag));
			purchaseOrderPickingPage.enterTagId(String.valueOf(tag));
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
			purchaseOrderPickingPage.enterContainerId(String.valueOf(tag));
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
		}
		// Assert.assertTrue("Picking Entry is not as expected",
		// purchaseOrderPickingPage.isPckEntPageDisplayed());
		Assert.assertTrue("picking completion is not as expected", purchaseOrderPickingPage.isPickCmpPageDisplayed());
		hooks.logoutPutty();

	}

	@Given("^I proceed for boxed vehicle loading for multiple bookings of split pick$")
	public void i_proceed_for_for_boxed_vehicle_loading_for_multiple_bookings_of_split_pick() throws Throwable {
		context.setVehicleLoadRequired(true);
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderVehicleLoadingPage.selectVehicleLoadMenu();
		purchaseOrderVehicleLoadingPage.selectMultiPalletLoadMenu();
		ArrayList<String> palletList = moveTaskDB.selectPalletIdList(context.getOrderId());
		int i = 0;
		while (i < context.getBookingList().size()) {
			String dockdoor = bookingInDiary.selectDockDoor(context.getBookingList().get(i));
			purchaseOrderVehicleLoadingPage.enterDockDoorForFlatpack(dockdoor);
			puttyFunctionsPage.pressTab();
			String urn = palletList.get(i);
			purchaseOrderVehicleLoadingPage.enterURN(urn);
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
			Thread.sleep(2000);
			if (i != (context.getBookingList().size() - 1)) {
				Assert.assertTrue("vehicle loading not as expected",
						purchaseOrderVehicleLoadingPage.isVehEntPageDisplayed());
			} else {
				puttyFunctionsPage.pressEnter();
				Assert.assertTrue("vehicle loading not as expected",
						purchaseOrderVehicleLoadingPage.isVehEntPageDisplayed());

			}
			i++;
		}
		if (palletList.size() > context.getBookingList().size()) {
			for (int k = context.getBookingList().size() - 1; k < palletList.size(); k++) {
				String dockdoor = bookingInDiary
						.selectDockDoor(context.getBookingList().get(context.getBookingList().size() - 1));
				purchaseOrderVehicleLoadingPage.enterDockDoorForFlatpack(dockdoor);
				puttyFunctionsPage.pressTab();

				String urn = palletList.get(k);
				purchaseOrderVehicleLoadingPage.enterURN(urn);
				puttyFunctionsPage.pressEnter();
				puttyFunctionsPage.pressEnter();
				Thread.sleep(2000);
				Assert.assertTrue("vehicle loading not as expected",
						purchaseOrderVehicleLoadingPage.isVehEntPageDisplayed());
			}
		}

		hooks.logoutPutty();
		context.setVehicleLoadRequired(false);

	}
	@Given("^I proceed for boxed vehicle loading for multiple bookings of split pick for multiple order$")
	public void i_proceed_for_for_boxed_vehicle_loading_for_multiple_bookings_of_split_pick_for_multiple_order() throws Throwable {
		ArrayList<String> Orderlist=context.getOrderList();
		for (int z=0;z<Orderlist.size();z++){
		context.setOrderId(Orderlist.get(z));	
		context.setVehicleLoadRequired(true);
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderVehicleLoadingPage.selectVehicleLoadMenu();
		purchaseOrderVehicleLoadingPage.selectMultiPalletLoadMenu();
		ArrayList<String> palletList = moveTaskDB.selectPalletIdList(context.getOrderId());
		int i = 0;
		while (i < context.getBookingList().size()) {
			String dockdoor = bookingInDiary.selectDockDoor(context.getBookingList().get(i));
			purchaseOrderVehicleLoadingPage.enterDockDoorForFlatpack(dockdoor);
			puttyFunctionsPage.pressTab();
			String urn = palletList.get(i);
			purchaseOrderVehicleLoadingPage.enterURN(urn);
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
			Thread.sleep(2000);
			if (i != (context.getBookingList().size() - 1)) {
				Assert.assertTrue("vehicle loading not as expected",
						purchaseOrderVehicleLoadingPage.isVehEntPageDisplayed());
			} else {
				puttyFunctionsPage.pressEnter();
				Assert.assertTrue("vehicle loading not as expected",
						purchaseOrderVehicleLoadingPage.isVehEntPageDisplayed());

			}
			i++;
		}
		if (palletList.size() > context.getBookingList().size()) {
			for (int k = context.getBookingList().size() - 1; k < palletList.size(); k++) {
				String dockdoor = bookingInDiary
						.selectDockDoor(context.getBookingList().get(context.getBookingList().size() - 1));
				purchaseOrderVehicleLoadingPage.enterDockDoorForFlatpack(dockdoor);
				puttyFunctionsPage.pressTab();

				String urn = palletList.get(k);
				purchaseOrderVehicleLoadingPage.enterURN(urn);
				puttyFunctionsPage.pressEnter();
				puttyFunctionsPage.pressEnter();
				Thread.sleep(2000);
				Assert.assertTrue("vehicle loading not as expected",
						purchaseOrderVehicleLoadingPage.isVehEntPageDisplayed());
			}
		}

		hooks.logoutPutty();
		context.setVehicleLoadRequired(false);
		}
	}
	@Given("^I perform split picking for hanging retail$")
	public void i_perform_split_picking_for_hanging_retail() throws Throwable {
		ArrayList<String> listIdArray = moveTaskDB.getListIdArray(context.getOrderId());
		for (int j = 0; j < listIdArray.size(); j++) {

			context.setListID(moveTaskDB.getListId(context.getOrderId()));
			context.setSkuId(moveTaskDB.getSkuId(context.getListID()));
			{
				for (int i = 0; i < 2; i++) {
					puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
					puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
					purchaseOrderPickingPage.selectPickingMenu();
					purchaseOrderPickingPage.selectPickingMenu2();
					purchaseOrderPickingPage.selectContainerPick();
					moveTaskUpdateDB.releaseOrderId(context.getOrderId());

					context.setListID(moveTaskDB.getListId(context.getOrderId()));
					purchaseOrderPickingPage.enterListId(context.getListID());
					puttyFunctionsPage.pressEnter();
					
					// address table
					String customer = orderHeaderDB.getCustomer(context.getOrderId());
					context.setCustomer(customer);
					String tagValueL = addressDB.getLowerTagValue();
					String tagValueH = addressDB.getHigherTagValue();
					int tag = (int) (Math.random() * (Integer.parseInt(tagValueH) - Integer.parseInt(tagValueL)))
							+ Integer.parseInt(tagValueL);
					context.setTagId(String.valueOf(tag));
					System.out.println(String.valueOf(tag));

					System.out.println("TAG ID" + context.getTagId());
					purchaseOrderPickingPage.enterTagId(context.getTagId());
					puttyFunctionsPage.pressEnter();
					puttyFunctionsPage.pressEnter(); //full qty enter
					if(i==0)
					{
					purchaseOrderPickingPage.changeQtyPick(
							Integer.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(), context.getSkuId())));
					puttyFunctionsPage.pressEnter();
					purchaseOrderPickingPage.selectSplitPick();
					}
					else
					{
						puttyFunctionsPage.pressEnter();	
					}
					purchaseOrderPickingPage.enterContainerId(context.getTagId());
					puttyFunctionsPage.pressEnter(); 
					if(i==0)
					{
					jdaFooter.clickMoreButton();
					jdaFooter.clickMoreButton();
					}
					Assert.assertTrue("To Location page is not as expected",
							purchaseOrderPickingPage.isPckLocPageDisplayed());
					puttyFunctionsPage.pressEnter();
					Assert.assertTrue("Picking completion is not as expected",
							purchaseOrderPickingPage.isPickEntPageDisplayed());
					hooks.logoutPutty();
					if (i == 0) {
						// do clustering
						jdaHomeStepDefs.i_navigate_to_mannual_clustering_screen();
						clusteringStepDefs.i_proceed_with_clustering_for("RETAIL");
						// again scheduler program
						jdaHomeStepDefs.i_navigate_scheduler_program_page();
						schedulerProgramStepDefs.i_run_the_program();
					}
				}
			}
		}
	}
	@Given("^I perform multiple split picking for retail$")
	public void i_perform_multiple_split_picking_for_hanging_retail() throws Throwable {
		ArrayList<String> Orderlist=context.getOrderList();
		for (int z=0;z<Orderlist.size();z++){
		context.setOrderId(Orderlist.get(z));	
		
		ArrayList<String> listIdArray = moveTaskDB.getListIdArray(context.getOrderId());
		for (int j = 0; j < listIdArray.size(); j++) {

			context.setListID(moveTaskDB.getListId(context.getOrderId()));
			context.setSkuId(moveTaskDB.getSkuId(context.getListID()));
			{
				for (int i = 0; i < 2; i++) {
					puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
					puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
					purchaseOrderPickingPage.selectPickingMenu();
					purchaseOrderPickingPage.selectPickingMenu2();
					purchaseOrderPickingPage.selectContainerPick();
					moveTaskUpdateDB.releaseOrderId(context.getOrderId());

					context.setListID(moveTaskDB.getListId(context.getOrderId()));
					purchaseOrderPickingPage.enterListId(context.getListID());
					puttyFunctionsPage.pressEnter();
					
					// address table
					String customer = orderHeaderDB.getCustomer(context.getOrderId());
					context.setCustomer(customer);
					String tagValueL = addressDB.getLowerTagValue();
					String tagValueH = addressDB.getHigherTagValue();
					int tag = (int) (Math.random() * (Integer.parseInt(tagValueH) - Integer.parseInt(tagValueL)))
							+ Integer.parseInt(tagValueL);
					context.setTagId(String.valueOf(tag));
					System.out.println(String.valueOf(tag));

					System.out.println("TAG ID" + context.getTagId());
					purchaseOrderPickingPage.enterTagId(context.getTagId());
					puttyFunctionsPage.pressEnter();
					puttyFunctionsPage.pressEnter(); //full qty enter
					if(i==0)
					{
					purchaseOrderPickingPage.changeQtyPick(
							Integer.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(), context.getSkuId())));
					puttyFunctionsPage.pressEnter();
					purchaseOrderPickingPage.selectSplitPick();
					}
					else
					{
						puttyFunctionsPage.pressEnter();	
					}
					purchaseOrderPickingPage.enterContainerId(context.getTagId());
					puttyFunctionsPage.pressEnter(); 
					if(i==0)
					{
					jdaFooter.clickMoreButton();
					jdaFooter.clickMoreButton();
					}
					Assert.assertTrue("To Location page is not as expected",
							purchaseOrderPickingPage.isPckLocPageDisplayed());
					puttyFunctionsPage.pressEnter();
					Assert.assertTrue("Picking completion is not as expected",
							purchaseOrderPickingPage.isPickEntPageDisplayed());
					hooks.logoutPutty();
					if (i == 0) {
						// do clustering
						jdaHomeStepDefs.i_navigate_to_mannual_clustering_screen();
						clusteringStepDefs.i_proceed_with_clustering_for("RETAIL");
						// again scheduler program
						jdaHomeStepDefs.i_navigate_scheduler_program_page();
						schedulerProgramStepDefs.i_run_the_program();
					}
				}
			}
		}
		}
	}
	@Given("^I perform nonretail picking$")
	public void i_perform_nonretail_picking() throws Throwable {
		moveTaskUpdateDB.releaseOrderId(context.getOrderId());
		
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
		
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		purchaseOrderPickingPage.enterListId(context.getListID());
		if(!purchaseOrderPickingPage.validContainerIdAvailable())
			
		purchaseOrderPickingPage.enter14digitpalletId();

		ArrayList skulist = new ArrayList();
		skulist = orderLineDB.getskuList(context.getOrderId());
		context.setSkuFromOrder(skulist);
		int containerid = purchaseOrderPickingPage.generate5digitcontainerID();
		
		int i = 0;
		Thread.sleep(2000);
		
		Thread.sleep(2000);
		
		//Looping for picking all the Sku's
		
		for (; i <= skulist.size(); i++)
		{
			purchaseOrderPickingPage.enterContainerID(String.valueOf(containerid));
			String Sku =(String) skulist.get(i);
		
		
			puttyFunctionsPage.pressEnter(); //tucskucon
			Thread.sleep(1000);
			puttyFunctionsPage.pressEnter();  //pclsubconcmp screen
			Thread.sleep(1000);
			
			purchaseOrderPickingPage.enterContainerID(String.valueOf(containerid)); //pclconcnf screen
			Thread.sleep(2000);
			
		 	purchaseOrderPickingPage.noOfTag(); //quesEnt
		 	
		if(purchaseOrderPickingPage.isPckPalToAvailable())  //pckpalto screen
			{i++;break;} 
		 	containerid++;
			}
		int j=0;
		while(j<5){
			if(purchaseOrderPickingPage.isPckPalToAvailable()){
		puttyFunctionsPage.pressEnter();
		Thread.sleep(1000);}
			else{
				break;
			}
		j++;
		}
		
		if (i< skulist.size())
			Assert.fail("picking is not valid One upc validation failed");
		
		
		hooks.logoutPutty();

	}
	

	@Then("^I enter the check string for marshalling$")
	public void i_enter_the_check_string_for_marshalling() throws Throwable {
		purchaseOrderPickingPage.enterCheckString(locationDB.getCheckString(context.getToLocation()));
	}

	@When("^I enter the invalid  UPC$")
	public void i_enter_the_invalid_UPC() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		moveTaskUpdateDB.releaseOrderId(context.getOrderId());
		purchaseOrderPickingPage.enterListId(context.getListID());
//		if (context.getListID().contains("DOL")) {
//		i_enter_the_UPC();
	//	puttyFunctionsPage.pressEnter();
		//purchaseOrderPickingPage.enterPrinterNO("P2003");
		puttyFunctionsPage.pressEnter();
		i_enter_the_UPC();
		puttyFunctionsPage.pressEnter();
		if (!purchaseOrderPickingPage.isInvalidSkuDetailsDisplayed()) {
			failureList.add("Error message:Invalid Clientsku");
		}
		// puttyFunctionsPage.pressEnter();

		context.setFailureList(failureList);
		hooks.logoutPutty();
	}


	@Given("^I enter the ivalid UPC for hanging$")
	public void i_enter_the_invalid_UPC_for_hanging() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		// context.setListID(moveTaskDB.getListID("5103200521"));
		moveTaskUpdateDB.releaseOrderId(context.getOrderId());
		// moveTaskUpdateDB.releaseOrderId("5103200521");
		purchaseOrderPickingPage.enterListId(context.getListID());
		puttyFunctionsPage.pressEnter();
		// puttyFunctionsPage.pressEnter();
		String customer = orderHeaderDB.getCustomer(context.getOrderId());
		context.setCustomer(customer);
		String tagValueL = addressDB.getLowerTagValue();
		String tagValueH = addressDB.getHigherTagValue();
		int tag = (int) (Math.random() * (Integer.parseInt(tagValueH) - Integer.parseInt(tagValueL)))
				+ Integer.parseInt(tagValueL);
		context.setTagId(String.valueOf(tag));
		System.out.println(String.valueOf(tag));
		purchaseOrderPickingPage.enterTagId(String.valueOf(tag));
		puttyFunctionsPage.pressEnter();
		i_enter_the_UPC();
		puttyFunctionsPage.pressEnter();
		if (!purchaseOrderPickingPage.isInvalidSkuDetailsDisplayed()) {
			failureList.add("Error message:Invalid Clientsku");
		}
		// puttyFunctionsPage.pressEnter();

		context.setFailureList(failureList);
		hooks.logoutPutty();

	}

	@Then("^the error message should be displayed as invalid details$")
	public void the_error_message_should_be_displayed_as_invalid_details() throws Throwable {
		Assert.assertTrue(
				"Error message:Invalid Clientsku. [" + Arrays.asList(context.getFailureList().toArray()) + "].",
				context.getFailureList().isEmpty());
	}

	@Then("^I enter the UPC$")
	public void i_enter_the_UPC() throws Throwable {
		for (int i = 0; i < 18; i++) {
			puttyFunctionsPage.rightArrow();
		}
		for (int i = 0; i < 1; i++) {
			jdaFooter.pressBackSpace();
		}

	}

	@Then("^I proceed with picking$")
	public void i_proceed_with_picking() throws Throwable {
		moveTaskDB.updateStatus(context.getOrderId());
		Thread.sleep(4000);
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		purchaseOrderPickingPage.enterListId(context.getListID());
		puttyFunctionsPage.pressEnter();
		purchaseOrderPickingPage.enterPrinterNO("PR003");
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
	}

	@Then("^the picking should be completed$")
	public void the_picking_should_be_completed() throws Throwable {
		String[] putawayLocation = purchaseOrderPickingPage.getPickingLocation().split("_");
		String toLocation = putawayLocation[0];
		context.setToLocation(toLocation);
		puttyFunctionsPage.pressEnter();
		i_enter_the_check_string_for_marshalling();
		puttyFunctionsPage.pressEnter();
		Assert.assertTrue("Picking Entry is not as expected", purchaseOrderPickingPage.isPckEntPageDisplayed());
		hooks.logoutPutty();
	}

	@Then("^the part set warning should be displayed$")
	public void the_part_set_warning_should_be_displayed() throws Throwable {
		purchaseOrderPickingPage.isPartSetQtyDisplayed();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
	}

	@Then("^the part set instruction should be displayed$")
	public void the_part_set_instruction_should_be_displayed() throws Throwable {
		Assert.assertTrue("Message Menu page not displayed as expected", purchaseOrderPickingPage.isMsgMenuDisplayed());
		Assert.assertTrue("Part set Instruction page is not displayed as expected",
				purchaseOrderPickingPage.isPartSetInstructionDisplayed());
	}

	@Then("^I proceed with picking to validate multi part set instruction$")
	public void i_proceed_with_picking_to_validate_multi_part_set_instruction() throws Throwable {
		moveTaskDB.updateStatus(context.getOrderId());
		Thread.sleep(4000);
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		purchaseOrderPickingPage.enterListId(context.getListID());
		the_part_set_instruction_should_be_displayed();
		puttyFunctionsPage.pressEnter();
		purchaseOrderPickingPage.enterPrinterNO("PR003");
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
	}

	@Then("^I should be directed to pickent page$")
	public void i_should_be_directed_to_pickent_page() throws Throwable {
		Assert.assertTrue("Picking Process not completed and Home page not displayed.",
				purchaseOrderPickingPage.isPickEnt());
	}

	@Given("^I select container picking$")
	public void i_select_container_picking() throws Throwable {
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
	}

	@Given("^I perform picking for discrepancy$")
	public void i_perform_picking_for_discrepancy() throws Throwable {
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		purchaseOrderPickingPage.enterListId(context.getListID());
		purchaseOrderPickingPage.getQuantity();
		purchaseOrderPickingPage.entrMinimumQty();
		purchaseOrderPickingPage.getTagId();
		purchaseOrderPickingPage.selectReason();
		purchaseOrderPickingPage.enterContainerId();
		String[] putawayLocation = purchaseOrderPickingPage.getPickingLocation().split("_");
		String toLocation = putawayLocation[0];
		context.setToLocation(toLocation);
		puttyFunctionsPage.pressEnter();
		i_enter_the_check_string_for_marshalling();
		puttyFunctionsPage.pressEnter();
	}


	@Given("^I perform picking for hanging discrepancy$")
	public void i_perform_picking_for_hanging_discrepancy() throws Throwable {
		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		purchaseOrderPickingPage.enterListId(context.getListID());
		String customer = orderHeaderDB.getCustomer(context.getOrderId());
		context.setCustomer(customer);
		String tagValueL = addressDB.getLowerTagValue();
		String tagValueH = addressDB.getHigherTagValue();
		int tag = (int) (Math.random() * (Integer.parseInt(tagValueH) - Integer.parseInt(tagValueL)))
				+ Integer.parseInt(tagValueL);
		context.setTagId(String.valueOf(tag));
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		System.out.println("tag to pick" + tag);
		purchaseOrderPickingPage.enterPicToTagId(String.valueOf(tag));
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		// purchaseOrderPickingPage.getQuantity();
		purchaseOrderPickingPage.entrMinimumQty();
		puttyFunctionsPage.pressEnter();
		purchaseOrderPickingPage.selectReason();
		purchaseOrderPickingPage.enterContainerId(String.valueOf(tag));
		int d = 0;
		do {
			System.out.println(purchaseOrderPickingPage.isPckPalToExists());
			if (!purchaseOrderPickingPage.isPckPalToExists()) {
				System.out.println("inside IF pallet check");
				puttyFunctionsPage.pressEnter();
				Thread.sleep(2000);
				if (purchaseOrderPickingPage.isPckConCnfExists()) {
					purchaseOrderPickingPage.enterContainerId(String.valueOf(tag));
				}
				d++;
			} else {
				System.out.println("found screen");
				break;
			}
		} while (d > 0);
		puttyFunctionsPage.pressEnter();
		Assert.assertTrue("Picking completion is not as expected", purchaseOrderPickingPage.isPickEntPageDisplayed());
		hooks.logoutPutty();
	}

	@Given("^I perform picking for multiple order$")
	public void i_perform_picking_for_multiple_order() throws Throwable {
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
		for (int i = 1; i < context.getOrderList().size(); i++) {
			context.setOrderId(context.getOrderList().get(i));
			context.setListID(moveTaskDB.getListID(context.getOrderId()));
			purchaseOrderPickingPage.enterListId(context.getListID());
			String customer = orderHeaderDB.getCustomer(context.getOrderId());
			context.setCustomer(customer);
			String tagValueL = addressDB.getLowerTagValue();
			String tagValueH = addressDB.getHigherTagValue();
			int tag = (int) (Math.random() * (Integer.parseInt(tagValueH) - Integer.parseInt(tagValueL)))
					+ Integer.parseInt(tagValueL);
			context.setTagId(String.valueOf(tag));
			puttyFunctionsPage.pressEnter();
			purchaseOrderPickingPage.enterPicToTagId(context.getTagId());
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
			purchaseOrderPickingPage.enterContainerId(String.valueOf(tag));
			int d = 0;
			do {
				System.out.println(purchaseOrderPickingPage.isPckPalToExists());
				if (!purchaseOrderPickingPage.isPckPalToExists()) {
					System.out.println("inside IF pallet check");
					puttyFunctionsPage.pressEnter();
					Thread.sleep(2000);
					if (purchaseOrderPickingPage.isPckConCnfExists()) {
						purchaseOrderPickingPage.enterContainerId(String.valueOf(tag));
					}
					d++;
				} else {
					System.out.println("found screen");
					break;
				}
			} while (d > 0);
			puttyFunctionsPage.pressEnter();
			Assert.assertTrue("Picking completion is not as expected",
					purchaseOrderPickingPage.isPickEntPageDisplayed());
		}
		hooks.logoutPutty();
	}

	@Given("^I perform picking for boxed$")
	public void i_perform_picking_for_boxed() throws Throwable {
		Assert.assertNotNull("List ID is not generated as expected", context.getListID());
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();


		for (int i = 0; i < moveTaskDB.getListIdArray(context.getOrderId()).size(); i++) {
			context.setListID(moveTaskDB.getListIdArray(context.getOrderId()).get(i));
			moveTaskUpdateDB.releaseOrderId(context.getOrderId());
			purchaseOrderPickingPage.enterListId(context.getListID());
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
			// String ToTAg= purchaseOrderPickingPage.getToTag();
			puttyFunctionsPage.pressEnter();
			String ContainerID = moveTaskDB.getContainerId(context.getOrderId());
			// purchaseOrderPickingPage.enterContainerId(ContainerID);
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
			Assert.assertTrue("Picking completion is not as expected",
					purchaseOrderPickingPage.isPickEntPageDisplayed());
		}

		hooks.logoutPutty();

	}


	@Given("^I perform picking for multiple order of type boxed$")
	public void i_perform_picking_for_multiple_order_of_type_boxed() throws Throwable {
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();

		for (int i = 1; i < context.getOrderList().size(); i++) {
			context.setOrderId(context.getOrderList().get(i));
			context.setListID(moveTaskDB.getListID(context.getOrderId()));
			Assert.assertNotNull("List ID is not generated as expected", context.getListID());
			purchaseOrderPickingPage.enterListId(context.getListID());
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
			purchaseOrderPickingPage.enterPrinterNO("dummy3");

			int d = 0;
			do {
				System.out.println(purchaseOrderPickingPage.isPckPalToExists());
				if (!purchaseOrderPickingPage.isPckPalToExists()) {
					System.out.println("inside IF pallet check");
					puttyFunctionsPage.pressEnter();
					Thread.sleep(2000);
					d++;
				} else {
					System.out.println("found screen");
					break;
				}
			} while (d > 0);

			puttyFunctionsPage.pressEnter();
			Assert.assertTrue("Picking Entry is not as expected", purchaseOrderPickingPage.isPckEntPageDisplayed());
			ArrayList failureList = new ArrayList();
			verification.verifyData("Order Status", "Ready to Load", orderHeaderDB.getStatus(context.getOrderId()),
					failureList);
			Assert.assertTrue(
					"Order Status details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
					failureList.isEmpty());

		}
		hooks.logoutPutty();
	}

	@Given("^I perform picking for single order of type boxed$")
	public void i_perform_picking_for_single_order_of_type_boxed() throws Throwable {
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();

		context.setListID(moveTaskDB.getListID(context.getOrderId()));
		Assert.assertNotNull("List ID is not generated as expected", context.getListID());
		purchaseOrderPickingPage.enterListId(context.getListID());
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		purchaseOrderPickingPage.enterPrinterNO("dummy3");

		int d = 0;
		do {
			System.out.println(purchaseOrderPickingPage.isPckPalToExists());
			if (!purchaseOrderPickingPage.isPckPalToExists()) {
				System.out.println("inside IF pallet check");
				puttyFunctionsPage.pressEnter();
				Thread.sleep(2000);
				d++;
			} else {
				System.out.println("found screen");
				break;
			}
		} while (d > 0);

		puttyFunctionsPage.pressEnter();
		Assert.assertTrue("Picking Entry is not as expected", purchaseOrderPickingPage.isPckEntPageDisplayed());

		hooks.logoutPutty();
	}


	@Given("^I proceed for boxed vehicle loading$")
	public void i_proceed_for_for_boxed_vehicle_loading() throws Throwable {
		context.setVehicleLoadRequired(true);
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderVehicleLoadingPage.selectVehicleLoadMenu();
		purchaseOrderVehicleLoadingPage.selectMultiPalletLoadMenu();
		String dockdoor = bookingInDiary.selectDockDoor(context.getBookingID());
		purchaseOrderVehicleLoadingPage.enterDockDoorForFlatpack(dockdoor);
		puttyFunctionsPage.pressTab();
		String urn = moveTaskDB.selectPalletId(context.getOrderId());
		purchaseOrderVehicleLoadingPage.enterURN(urn);
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();

		Thread.sleep(2000);
		Assert.assertTrue("vehicle loading not as expected", purchaseOrderVehicleLoadingPage.isVehEntPageDisplayed());

		// purchaseOrderVehicleLoadingPage.isVehEntPageDisplayed();
		// Assert.assertTrue("Vehicle Loading is not as expected",
		// purchaseOrderVehicleLoadingPage.isVehEntPageDisplayed());
		hooks.logoutPutty();
		context.setVehicleLoadRequired(false);

	}

	@Given("^I proceed for boxed vehicle loading for multiple bookings$")
	public void i_proceed_for_for_boxed_vehicle_loading_for_multiple_bookings() throws Throwable {

		puttyFunctionsPage.pressEnter();
		Thread.sleep(2000);
		Assert.assertTrue("vehicle loading not as expected", puttyFunctionsPage.isVehEntPageDisplayed());
		hooks.logoutPutty();
		context.setVehicleLoadRequired(false);
	}

	@Given("^I proceed for vehicle loading with multiple order$")
	public void i_proceed_for_vehicle_loading_with_multiple_order() throws Throwable {

		context.setVehicleLoadRequired(true);
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderVehicleLoadingPage.selectVehicleLoadMenu();
		purchaseOrderVehicleLoadingPage.selectMultiPalletLoadMenu();

		ArrayList palletIDList = new ArrayList();
		ArrayList failureList = new ArrayList();
		for (int i = 0; i < context.getOrderList().size(); i++) {
			// To check if multiple orders have same pallet ID
			context.setOrderId(context.getOrderList().get(i));
			String urn = moveTaskDB.selectPalletId(context.getOrderId());
			palletIDList.add(urn);
		}
		Set<String> hs = new HashSet<>();
		hs.addAll(palletIDList);
		palletIDList.clear();
		palletIDList.addAll(hs);
		context.setPalletIDList(palletIDList);
		System.out.println("pallet id list" + context.getPalletIDList());

		for (int i = 0; i < context.getPalletIDList().size(); i++) {
			// context.setOrderId(context.getOrderList().get(i));
			System.out.println("get booking id" + context.getBookingID());
			String dockdoor = bookingInDiary.selectDockDoor(context.getBookingID());
			purchaseOrderVehicleLoadingPage.enterDockDoorForFlatpack(dockdoor);
			puttyFunctionsPage.pressTab();
			// String urn = moveTaskDB.selectPalletId(context.getOrderId());
			purchaseOrderVehicleLoadingPage.enterURN((String) context.getPalletIDList().get(i));
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
			Thread.sleep(3000);
			// Assert.assertTrue("Vehicle loading not as expected",
			// puttyFunctionsPage.isVehEntPageDisplayed());
			if (!puttyFunctionsPage.isVehEntPageDisplayed()) {
				System.out.println("Vehicle loading not as expected for pallet " + context.getPalletIDList().get(i));
				failureList.add("Vehicle loading not as expected for pallet " + context.getPalletIDList().get(i));
			}
		}
		hooks.logoutPutty();
		context.setVehicleLoadRequired(false);
		Assert.assertTrue("Vehicle loading not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	

	@Given("^I proceed for vehicle unload$")
	public void i_proceed_for_for_vehicle_unload() throws Throwable {
		String siteid = context.getSiteID();
		context.setVehicleLoadRequired(true);
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderVehicleLoadingPage.selectVehicleLoadMenu();
		purchaseOrderVehicleLoadingPage.selectVehicleUnloadMenu();
	}

	@Then("^Trailer should display as Invalid Pallet ID$")
	public void Trailer_should_display_as_Invalid_Pallet_ID() throws Throwable {
		Assert.assertTrue("Vehicle unload entry page not displayed as expected",
				puttyFunctionsPage.isVehicleUnloadEntryPageDisplayed());
		String urn = Utilities.getTenDigitRandomNumber();
		purchaseOrderVehicleLoadingPage.enterInvaidPalletId(urn);
		puttyFunctionsPage.pressTab();
		purchaseOrderVehicleLoadingPage.enterTrailer(context.getTrailerNo());
		Assert.assertTrue("Invalid pallet ID not as expected", puttyFunctionsPage.isinvalidpalletIdPageDisplayed());
		hooks.logoutPutty();
	}

	@When("^I proceed for boxed vehicle unloading$")
	public void i_proceed_for_boxed_vehicle_unloading() throws Throwable {
		String siteid = context.getSiteID();
		context.setVehicleLoadRequired(true);
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderVehicleLoadingPage.selectVehicleLoadMenu();
		purchaseOrderVehicleLoadingPage.selectVehicleUnloadMenu();
		String urn = orderContainerDB.selectURN(context.getOrderId());
		purchaseOrderVehicleLoadingPage.enterPalletID(urn);
		Thread.sleep(2000);
		puttyFunctionsPage.pressTab();
		purchaseOrderVehicleLoadingPage.enterTrailer(context.getTrailerNo());
		puttyFunctionsPage.pressEnter();
		Thread.sleep(2000);

	}

	@When("^I proceed for boxed vehicle unloading with multiple order$")
	public void i_proceed_for_boxed_vehicle_unloading_with_multiple_order() throws Throwable {
		String siteid = context.getSiteID();
		context.setVehicleLoadRequired(true);
		Thread.sleep(3000);
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderVehicleLoadingPage.selectVehicleLoadMenu();
		purchaseOrderVehicleLoadingPage.selectVehicleUnloadMenu();
		for (int i = 0; i < context.getOrderList().size(); i++) {
			context.setOrderId(context.getOrderList().get(i));
			String urn = orderContainerDB.selectURN(context.getOrderId());
			purchaseOrderVehicleLoadingPage.enterPalletID(urn);
			Thread.sleep(2000);
			puttyFunctionsPage.pressTab();
			purchaseOrderVehicleLoadingPage.enterTrailer(context.getTrailerNo());
			puttyFunctionsPage.pressEnter();
			Thread.sleep(2000);
			Assert.assertTrue("vehicle unloading not as expected",
					puttyFunctionsPage.isVehicleUnloadEntryPageDisplayed());
		}
		hooks.logoutPutty();
	}

	@When("^I proceed for \"([^\"]*)\" vehicle unloading with multiple order$")
	public void i_proceed_for_vehicle_unloading_with_multiple_order(String dataType) throws Throwable {
		String siteid = context.getSiteID();
		context.setVehicleLoadRequired(true);
		Thread.sleep(3000);
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderVehicleLoadingPage.selectVehicleLoadMenu();
		purchaseOrderVehicleLoadingPage.selectVehicleUnloadMenu();
		for (int i = 0; i < context.getPalletIDList().size(); i++) {
			// context.setOrderId(context.getOrderList().get(i));
			// String urn=orderContainerDB.selectURN(context.getOrderId());
			purchaseOrderVehicleLoadingPage.enterPalletID(context.getPalletIDList().get(i));
			Thread.sleep(2000);
			if (dataType.equalsIgnoreCase("Boxed")) {
				puttyFunctionsPage.pressTab();
			}
			purchaseOrderVehicleLoadingPage.enterTrailer(context.getTrailerNo());
			puttyFunctionsPage.pressEnter();
			Thread.sleep(2000);
			Assert.assertTrue("vehicle unloading not as expected",
					puttyFunctionsPage.isVehicleUnloadEntryPageDisplayed());
		}
		hooks.logoutPutty();

	}

	@Then("^vehicle should be unload$")
	public void vehicle_should_be_unload() throws Throwable {
		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
		verification.verifyData("Order Status", "Ready to Load", orderHeaderDB.getStatus(context.getOrderId()),
				failureList);
		Assert.assertTrue(
				"Order Status details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());

		Assert.assertTrue("vehicle unloading not as expected", puttyFunctionsPage.isVehicleUnloadEntryPageDisplayed());
		hooks.logoutPutty();
	}

	@Then("^vehicle should be unload for multiple order$")
	public void vehicle_should_be_unload_for_multiple_order() throws Throwable {
		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
		for (int i = 0; i < context.getOrderList().size(); i++) {
			context.setOrderId(context.getOrderList().get(i));
			verification.verifyData("Order Status", "Ready to Load", orderHeaderDB.getStatus(context.getOrderId()),
					failureList);
			// Assert.assertTrue("vehicle unloading not as expected",
			// puttyFunctionsPage.isVehicleUnloadEntryPageDisplayed());
			System.out.println("order id to be unload" + context.getOrderId());
		}
		Assert.assertTrue(
				"Order Status details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
		// hooks.logoutPutty();
	}


	@Then("^Trailer should enter page displayed$")
	public void Trailer_should_enter() throws Throwable {
		Assert.assertTrue("Vehicle unload entry page not displayed as expected",
				puttyFunctionsPage.isVehicleUnloadEntryPageDisplayed());
		String urn = orderContainerDB.selectURN(context.getOrderId());
		purchaseOrderVehicleLoadingPage.enterPalletID(urn);
		puttyFunctionsPage.pressEnter();
		// puttyFunctionsPage.pressTab();
		// purchaseOrderVehicleLoadingPage.enterTrailer(context.getTrailerNo());
		Assert.assertTrue("Trailer entered page not displayed as expected",
				puttyFunctionsPage.isTrailerEnterPageDisplayed());
		hooks.logoutPutty();
	}



	@Given("^I perform picking for \"([^\"]*)\" type$")
	public void i_perform_picking_for_type(String type) throws Throwable {

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
		context.setListID(moveTaskDB.getListId(context.getOrderId()));
		moveTaskUpdateDB.releaseOrderId(context.getOrderId());
		purchaseOrderPickingPage.enterListId(context.getListID());
		puttyFunctionsPage.pressEnter();
		String customer = orderHeaderDB.getCustomer(context.getOrderId());
		context.setCustomer(customer);
		String tagValueL = addressDB.getLowerTagValue();
		String tagValueH = addressDB.getHigherTagValue();
		int tag = (int) (Math.random() * (Integer.parseInt(tagValueH) - Integer.parseInt(tagValueL)))
				+ Integer.parseInt(tagValueL);
		context.setTagId(String.valueOf(tag));
		puttyFunctionsPage.pressEnter();
		purchaseOrderPickingPage.enterPicToTagId(context.getTagId());
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		purchaseOrderPickingPage.enterContainerId(String.valueOf(tag));
		int d = 0;
		do {
			System.out.println(purchaseOrderPickingPage.isPckPalToExists());
			if (!purchaseOrderPickingPage.isPckPalToExists()) {
				System.out.println("inside IF pallet check");
				puttyFunctionsPage.pressEnter();
				Thread.sleep(2000);
				if (purchaseOrderPickingPage.isPckConCnfExists()) {
					purchaseOrderPickingPage.enterContainerId(String.valueOf(tag));
				}
				d++;
			} else {
				System.out.println("found screen");
				break;
			}
		} while (d > 0);
		puttyFunctionsPage.pressEnter();
		Assert.assertTrue("Picking completion is not as expected", purchaseOrderPickingPage.isPickEntPageDisplayed());
		hooks.logoutPutty();

	}

	@Given("^I perform picking for GOH$")
	public void i_perform_picking_for_GOH() throws Throwable {
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
		// context.setOrderId("5104200724");
		context.setListID(moveTaskDB.getListId(context.getOrderId()));
		moveTaskUpdateDB.releaseOrderId(context.getOrderId());
		purchaseOrderPickingPage.enterListId(context.getListID());
		puttyFunctionsPage.pressEnter();
		String customer = orderHeaderDB.getCustomer(context.getOrderId());
		context.setCustomer(customer);
		String tagValueL = addressDB.getLowerTagValue();
		String tagValueH = addressDB.getHigherTagValue();
		int tag = (int) (Math.random() * (Integer.parseInt(tagValueH) - Integer.parseInt(tagValueL)))
				+ Integer.parseInt(tagValueL);
		context.setTagId(String.valueOf(tag));
		System.out.println(String.valueOf(tag));

		System.out.println("TAG ID" + context.getTagId());
		purchaseOrderPickingPage.enterTagId(context.getTagId());
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();

		purchaseOrderPickingPage.enterContainerId(context.getTagId());
		puttyFunctionsPage.pressEnter(); // Location

		Assert.assertTrue("Picking completion is not as expected", purchaseOrderPickingPage.isPickEntPageDisplayed());
		hooks.logoutPutty();

	}

	@Given("^I perform picking for flatpack$")
	public void i_perform_picking_for_flatpack() throws Throwable {
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();
		// context.setOrderId("5104626650");
		context.setListID(moveTaskDB.getListId(context.getOrderId()));
		// context.setListID(moveTaskDB.getListID("5104200528"));
		moveTaskUpdateDB.releaseOrderId(context.getOrderId());
		// moveTaskUpdateDB.releaseOrderId("5104200528");
		purchaseOrderPickingPage.enterListId(context.getListID());
		puttyFunctionsPage.pressEnter();
		context.setTagId(Utilities.getSixDigitRandomNumber());
		purchaseOrderPickingPage.enterTagId(context.getTagId());
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		purchaseOrderPickingPage.enterContainerId(context.getTagId());
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();
		Assert.assertTrue("Picking completion is not as expected", purchaseOrderPickingPage.isPickEntPageDisplayed());
		hooks.logoutPutty();

	}

	@Given("^I proceed for vehicle loading$")
	public void i_proceed_for_for_vehicle_loading() throws Throwable {
		context.setVehicleLoadRequired(true);
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderVehicleLoadingPage.selectVehicleLoadMenu();
		purchaseOrderVehicleLoadingPage.selectMultiPalletLoadMenu();
		String dockdoor = bookingInDiary.selectDockDoor(context.getBookingID());
		purchaseOrderVehicleLoadingPage.enterDockDoorForFlatpack(dockdoor);
		puttyFunctionsPage.pressTab();
		String urn = moveTaskDB.selectURN(context.getOrderId());
		purchaseOrderVehicleLoadingPage.enterURN(urn);
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.pressEnter();

		puttyFunctionsPage.pressEnter();
		Thread.sleep(2000);
		Assert.assertTrue("vehicle loading not as expected", puttyFunctionsPage.isVehEntPageDisplayed());
		hooks.logoutPutty();
		context.setVehicleLoadRequired(false);
	}

	@When("^I proceed for vehicle unloading$")
	public void i_proceed_for_vehicle_unloading() throws Throwable {
		String siteid = context.getSiteID();
		context.setVehicleLoadRequired(true);
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderVehicleLoadingPage.selectVehicleLoadMenu();
		purchaseOrderVehicleLoadingPage.selectVehicleUnloadMenu();
		// Assert.assertTrue("Vehicle unload entry page not displayed as
		// expected", puttyFunctionsPage.isVehicleUnloadEntryPageDisplayed());
		String urn = orderContainerDB.selectURN(context.getOrderId());
		purchaseOrderVehicleLoadingPage.enterPalletID(urn);
		Thread.sleep(2000);
		puttyFunctionsPage.pressTab();
		purchaseOrderVehicleLoadingPage.enterTrailer(context.getTrailerNo());
		puttyFunctionsPage.pressEnter();
		Thread.sleep(2000);
	}

	@When("^I proceed for GOH vehicle unloading$")
	public void i_proceed_for_GOH_vehicle_unloading() throws Throwable {
		String siteid = "5649";
		context.setVehicleLoadRequired(true);
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderVehicleLoadingPage.selectVehicleLoadMenu();
		purchaseOrderVehicleLoadingPage.selectVehicleUnloadMenu();
		// Assert.assertTrue("Vehicle unload entry page not displayed as
		// expected", puttyFunctionsPage.isVehicleUnloadEntryPageDisplayed());
		String urn = orderContainerDB.selectURN(context.getOrderId());
		purchaseOrderVehicleLoadingPage.enterPalletID(urn);
		Thread.sleep(2000);
		puttyFunctionsPage.pressTab();
		purchaseOrderVehicleLoadingPage.enterTrailer(context.getTrailerNo());
		puttyFunctionsPage.pressEnter();
		Thread.sleep(2000);
	}


	@Given("^I perform picking for Hanging Retail$")
	public void i_perform_picking_for_hanging_retail() throws Throwable {
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectPickingMenu2();
		purchaseOrderPickingPage.selectContainerPick();

		moveTaskUpdateDB.releaseOrderId(context.getOrderId());
		for (int i = 0; i < moveTaskDB.getListIdArray(context.getOrderId()).size(); i++) {
			context.setListID(moveTaskDB.getListIdArray(context.getOrderId()).get(i));

			purchaseOrderPickingPage.enterListId(context.getListID());

			puttyFunctionsPage.pressEnter();
			// address table
			String customer = orderHeaderDB.getCustomer(context.getOrderId());
			context.setCustomer(customer);
			String tagValueL = addressDB.getLowerTagValue();
			String tagValueH = addressDB.getHigherTagValue();
			int tag = (int) (Math.random() * (Integer.parseInt(tagValueH) - Integer.parseInt(tagValueL)))
					+ Integer.parseInt(tagValueL);
			context.setTagId(String.valueOf(tag));
			System.out.println(String.valueOf(tag));

			System.out.println("TAG ID" + context.getTagId());
			purchaseOrderPickingPage.enterTagId(context.getTagId());
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();

			purchaseOrderPickingPage.enterContainerId(context.getTagId());
			puttyFunctionsPage.pressEnter(); // Location

			Assert.assertTrue("Picking completion is not as expected",
					purchaseOrderPickingPage.isPickEntPageDisplayed());
		}
		hooks.logoutPutty();

	}

	@Given("^I proceed with sortation$")
	public void i_proceed_with_sortation() throws Throwable {
		// context.setSortationRequired(true);
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderPickingPage.selectPickingMenu();
		purchaseOrderPickingPage.selectSortingMenu();
		String ToPallet = Utilities.getTenDigitRandomNumber() + Utilities.getFiveDigitRandomNumber();
		context.setToPallet(ToPallet);
		purchaseOrderPickingPage.enterToPallet(context.getToPallet());
		puttyFunctionsPage.pressEnter();
		puttyFunctionsPage.nextScreen();
		puttyFunctionsPage.pressTab();
		String fromPallet = orderContainerDB.selectContainer(context.getOrderId());
		purchaseOrderPickingPage.enterFromPallet(fromPallet);
		puttyFunctionsPage.pressEnter();
		Assert.assertTrue("Sortation completion is not as expected",
				purchaseOrderPickingPage.isPalRpkFrmCPageDisplayed());
		hooks.logoutPutty();

	}

	@Given("^I perform split picking for boxed retail$")
	public void i_perform_split_picking_for_boxed_retail() throws Throwable {
		ArrayList<String> listIdArray = moveTaskDB.getListIdArray(context.getOrderId());
		for (int j = 0; j < listIdArray.size(); j++) {

			context.setListID(moveTaskDB.getListId(context.getOrderId()));
			context.setSkuId(moveTaskDB.getSkuId(context.getListID()));
			{
				for (int i = 0; i < 2; i++) {
					puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
					puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
					purchaseOrderPickingPage.selectPickingMenu();
					purchaseOrderPickingPage.selectPickingMenu2();
					purchaseOrderPickingPage.selectContainerPick();
					moveTaskUpdateDB.releaseOrderId(context.getOrderId());
					context.setListID(moveTaskDB.getListId(context.getOrderId()));
					purchaseOrderPickingPage.enterListId(context.getListID());
					puttyFunctionsPage.pressEnter();
					purchaseOrderPickingPage.enterPrinterNO("dummy3");
					puttyFunctionsPage.pressEnter();
					puttyFunctionsPage.pressEnter();
					if (i == 0) {
						puttyFunctionsPage.pressTab();
						purchaseOrderPickingPage.changeQtyPick(
								Integer.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(), context.getSkuId())));
						puttyFunctionsPage.pressEnter();
						purchaseOrderPickingPage.selectSplitPick();
						puttyFunctionsPage.pressEnter();
						jdaFooter.clickMoreButton();
						jdaFooter.clickMoreButton();
					} else if (i == 1) {
						puttyFunctionsPage.pressEnter();
						puttyFunctionsPage.pressEnter();
					}
					Assert.assertTrue("To Location page is not as expected",
							purchaseOrderPickingPage.isPckLocPageDisplayed());
					puttyFunctionsPage.pressEnter();
					Assert.assertTrue("Picking completion is not as expected",
							purchaseOrderPickingPage.isPickEntPageDisplayed());
					hooks.logoutPutty();
					if (i == 0) {
						// do clustering
						jdaHomeStepDefs.i_navigate_to_mannual_clustering_screen();
						clusteringStepDefs.i_proceed_with_clustering();
						// again scheduler program
						jdaHomeStepDefs.i_navigate_scheduler_program_page();
						schedulerProgramStepDefs.i_run_the_program();
					}
				}
			}
		}
	}

	@Given("^I perform split picking for multiple order of type boxed retail$")
	public void i_perform_split_picking_for_multiple_order_of_type_boxed_retail() throws Throwable {
		for (int k = 0; k < context.getOrderList().size(); k++) {
			context.setOrderId(context.getOrderList().get(k));
			ArrayList<String> listIdArray = moveTaskDB.getListIdArray(context.getOrderId());
			for (int j = 0; j < listIdArray.size(); j++) {
				context.setListID(moveTaskDB.getListId(context.getOrderId()));
				context.setSkuId(moveTaskDB.getSkuId(context.getListID()));
				{
					for (int i = 0; i < 2; i++) {
						puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
						puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
						purchaseOrderPickingPage.selectPickingMenu();
						purchaseOrderPickingPage.selectPickingMenu2();
						purchaseOrderPickingPage.selectContainerPick();
						moveTaskUpdateDB.releaseOrderId(context.getOrderId());
						context.setListID(moveTaskDB.getListId(context.getOrderId()));
						purchaseOrderPickingPage.enterListId(context.getListID());
						puttyFunctionsPage.pressEnter();
						purchaseOrderPickingPage.enterPrinterNO("dummy3");
						puttyFunctionsPage.pressEnter();
						puttyFunctionsPage.pressEnter();
						if (i == 0) {
							puttyFunctionsPage.pressTab();
							purchaseOrderPickingPage.changeQtyPick(Integer
									.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(), context.getSkuId())));
							puttyFunctionsPage.pressEnter();
							purchaseOrderPickingPage.selectSplitPick();
							puttyFunctionsPage.pressEnter();
							jdaFooter.clickMoreButton();
							jdaFooter.clickMoreButton();
						} else if (i == 1) {
							puttyFunctionsPage.pressEnter();
							puttyFunctionsPage.pressEnter();
						}
						Assert.assertTrue("To Location page is not as expected",
								purchaseOrderPickingPage.isPckLocPageDisplayed());
						puttyFunctionsPage.pressEnter();
						Assert.assertTrue("Picking completion is not as expected",
								purchaseOrderPickingPage.isPickEntPageDisplayed());
						hooks.logoutPutty();
						if (i == 0) {
							// do clustering
							jdaHomeStepDefs.i_navigate_to_mannual_clustering_screen();
							clusteringStepDefs.i_proceed_with_clustering();
							// again scheduler program
							jdaHomeStepDefs.i_navigate_scheduler_program_page();
							schedulerProgramStepDefs.i_run_the_program();
						}

					}
				}
			}
		}
	}


	@Given("^I perform split picking for boxed non retail$")
	public void i_perform_split_picking_for_boxed_non_retail() throws Throwable {
		ArrayList<String> listIdArray = moveTaskDB.getListIdArray(context.getOrderId());
		String container=null;
		for (int j = 0; j < listIdArray.size(); j++) {
			context.setListID(moveTaskDB.getListId(context.getOrderId()));
			context.setSkuId(moveTaskDB.getSkuId(context.getListID()));
			{
				for (int i = 0; i < 2; i++) {
					puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
					puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
					purchaseOrderPickingPage.selectPickingMenu();
					purchaseOrderPickingPage.selectPickingMenu2();
					purchaseOrderPickingPage.selectContainerPick();
					moveTaskUpdateDB.releaseOrderId(context.getOrderId());
					context.setListID(moveTaskDB.getListId(context.getOrderId()));
					purchaseOrderPickingPage.enterListId(context.getListID());
					purchaseOrderPickingPage.enterPallet(Utilities.getFourteenDigitRandomNumber());
					puttyFunctionsPage.pressEnter();
					container=Utilities.getSixDigitRandomNumber();
					purchaseOrderPickingPage.enterPallet(container);
					puttyFunctionsPage.pressEnter();
					puttyFunctionsPage.pressEnter();
					if (i == 0) {
						purchaseOrderPickingPage.changeQtyPick(
								Integer.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(), context.getSkuId())));
						puttyFunctionsPage.pressEnter();
						purchaseOrderPickingPage.selectSplitPick();
						purchaseOrderPickingPage.enterPallet(container);
						puttyFunctionsPage.pressEnter();
						jdaFooter.clickMoreButton();
						jdaFooter.clickMoreButton();
					} else if (i == 1) {
						puttyFunctionsPage.pressEnter();
						purchaseOrderPickingPage.enterPallet(container);
						puttyFunctionsPage.pressEnter();
					}
					purchaseOrderPickingPage.enterPallet("1");
					puttyFunctionsPage.pressEnter();
					Assert.assertTrue("To Location page is not as expected",
							purchaseOrderPickingPage.isPckLocPageDisplayed());
					puttyFunctionsPage.pressEnter();
					if (purchaseOrderRelocatePage.isChkToDisplayed()) {
						Assert.assertTrue("ChkTo page not displayed", purchaseOrderRelocatePage.isChkToDisplayed());
						context.setToLocation(purchaseOrderPickingPage.getToLocation());
						purchaseOrderRelocatePage.enterChks(locationDB.getCheckString(context.getToLocation()));
						jdaFooter.PressEnter();
					}
					Assert.assertTrue("Picking completion is not as expected",
							purchaseOrderPickingPage.isPickEntPageDisplayed());
					hooks.logoutPutty();
					if (i == 0) {
						// do clustering
						jdaHomeStepDefs.i_navigate_to_mannual_clustering_screen();
						clusteringStepDefs.i_proceed_with_clustering_for("NON RETAIL");
						// again scheduler program
						jdaHomeStepDefs.i_navigate_scheduler_program_page();
						schedulerProgramStepDefs.i_run_the_program();
					}
				}
			}
		}
	}
	
	@Given("^I perform split picking for hanging non retail$")
	public void i_perform_split_picking_for_hanging_non_retail() throws Throwable {
		ArrayList<String> listIdArray = moveTaskDB.getListIdArray(context.getOrderId());
		for (int j = 0; j < listIdArray.size(); j++) {

			context.setListID(moveTaskDB.getListId(context.getOrderId()));
			context.setSkuId(moveTaskDB.getSkuId(context.getListID()));
			{
				for (int i = 0; i < 2; i++) {
					puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
					puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
					purchaseOrderPickingPage.selectPickingMenu();
					purchaseOrderPickingPage.selectPickingMenu2();
					purchaseOrderPickingPage.selectContainerPick();
					moveTaskUpdateDB.releaseOrderId(context.getOrderId());

					context.setListID(moveTaskDB.getListId(context.getOrderId()));
					purchaseOrderPickingPage.enterListId(context.getListID());
					puttyFunctionsPage.pressEnter();
					
					// address table
					String customer = orderHeaderDB.getCustomer(context.getOrderId());
					context.setCustomer(customer);
					String tagValueL = addressDB.getLowerTagValue();
					String tagValueH = addressDB.getHigherTagValue();
					int tag = (int) (Math.random() * (Integer.parseInt(tagValueH) - Integer.parseInt(tagValueL)))
							+ Integer.parseInt(tagValueL);
					context.setTagId(String.valueOf(tag));
					System.out.println(String.valueOf(tag));

					System.out.println("TAG ID" + context.getTagId());
					purchaseOrderPickingPage.enterTagId(context.getTagId());
					puttyFunctionsPage.pressEnter();
					puttyFunctionsPage.pressEnter(); //full qty enter
					if(i==0)
					{
					purchaseOrderPickingPage.changeQtyPick(
							Integer.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(), context.getSkuId())));
					puttyFunctionsPage.pressEnter();
					purchaseOrderPickingPage.selectSplitPick();
					}
					else
					{
						puttyFunctionsPage.pressEnter();	
					}
					purchaseOrderPickingPage.enterContainerId(context.getTagId());
					puttyFunctionsPage.pressEnter(); 
					if(i==0)
					{
					jdaFooter.clickMoreButton();
					jdaFooter.clickMoreButton();
					}
					if(purchaseOrderPickingPage.isBoxSizeEntryPageDisplayed())
					{
						purchaseOrderPickingPage.enterPallet("1");
						puttyFunctionsPage.pressEnter();	
					}
					Assert.assertTrue("To Location page is not as expected",
							purchaseOrderPickingPage.isPckLocPageDisplayed());
					puttyFunctionsPage.pressEnter();
					if (purchaseOrderRelocatePage.isChkToDisplayed()) {
						Assert.assertTrue("ChkTo page not displayed", purchaseOrderRelocatePage.isChkToDisplayed());
						context.setToLocation(purchaseOrderPickingPage.getToLocation());
						purchaseOrderRelocatePage.enterChks(locationDB.getCheckString(context.getToLocation()));
						jdaFooter.PressEnter();
					}
					Assert.assertTrue("Picking completion is not as expected",
							purchaseOrderPickingPage.isPickEntPageDisplayed());
					hooks.logoutPutty();
					if (i == 0) {
						// do clustering
						jdaHomeStepDefs.i_navigate_to_mannual_clustering_screen();
						clusteringStepDefs.i_proceed_with_clustering_for("NON RETAIL");
						// again scheduler program
						jdaHomeStepDefs.i_navigate_scheduler_program_page();
						schedulerProgramStepDefs.i_run_the_program();
					}
				}
			}
		}
	}
	
	@Given("^I perform picking for boxed retail multiple orders$")
	public void i_perform_picking_for_boxed_retail_multiple_orders() throws Throwable {
		ArrayList<String> orderList = context.getOrderList();
		for (int k = 0; k < orderList.size(); k++) {
			context.setOrderId(orderList.get(k));
			ArrayList<String> listIdArray = moveTaskDB.getListIdArray(context.getOrderId());
			for (int j = 0; j < listIdArray.size(); j++) {

				context.setListID(moveTaskDB.getListId(context.getOrderId()));
				context.setSkuId(moveTaskDB.getSkuId(context.getListID()));
				puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
				puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
				purchaseOrderPickingPage.selectPickingMenu();
				purchaseOrderPickingPage.selectPickingMenu2();
				purchaseOrderPickingPage.selectContainerPick();
				moveTaskUpdateDB.releaseOrderId(context.getOrderId());

				context.setListID(moveTaskDB.getListId(context.getOrderId()));
				purchaseOrderPickingPage.enterListId(context.getListID());
				puttyFunctionsPage.pressEnter();
				purchaseOrderPickingPage.enterPrinterNO("dummy3");
				puttyFunctionsPage.pressEnter();
				puttyFunctionsPage.pressEnter();
				puttyFunctionsPage.pressEnter();
				puttyFunctionsPage.pressEnter();
				if (!(k == orderList.size() - 1)) {
					jdaFooter.clickMoreButton();
					jdaFooter.clickMoreButton();
				}
				Assert.assertTrue("To Location page is not as expected",
						purchaseOrderPickingPage.isPckLocPageDisplayed());
				puttyFunctionsPage.pressEnter();
				if (purchaseOrderRelocatePage.isChkToDisplayed()) {
					Assert.assertTrue("ChkTo page not displayed", purchaseOrderRelocatePage.isChkToDisplayed());
					context.setToLocation(purchaseOrderPickingPage.getToLocation());
					purchaseOrderRelocatePage.enterChks(locationDB.getCheckString(context.getToLocation()));
					jdaFooter.PressEnter();
				}
				Assert.assertTrue("Picking completion is not as expected",
						purchaseOrderPickingPage.isPickEntPageDisplayed());
				hooks.logoutPutty();
			}

			if (k != orderList.size() - 1) {
				// do clustering
				jdaHomeStepDefs.i_navigate_to_mannual_clustering_screen();
				clusteringStepDefs.i_proceed_with_clustering_for("RETAIL");
				// again scheduler program
				jdaHomeStepDefs.i_navigate_scheduler_program_page();
				schedulerProgramStepDefs.i_run_the_program();
			}
		}
	}	

}
