package com.jda.wms.stepdefs.rdt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.FindFailed;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.InventoryTransactionDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderPutawayPage;
import com.jda.wms.pages.rdt.PurchaseOrderRelocatePage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.utils.DateUtils;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PurchaseOrderPutawayStepDefs {
	private PurchaseOrderPutawayPage purchaseOrderPutawayPage;
	Map<Integer, Map<String, String>> poMap;
	Map<String, Map<String, String>> upiMap;
	private Context context;
	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private Verification verification;
	private InventoryDB inventoryDB;
	private PurchaseOrderRelocatePage purchaseOrderRelocatePage;
	private LocationDB locationDB;
	private Hooks hooks;
	private JDAFooter jdaFooter;
	private PuttyFunctionsPage puttyFunctionsPage;
	private InventoryTransactionDB inventoryTransactionDB;
	private SkuDB skuDB;

	@Inject
	public PurchaseOrderPutawayStepDefs(PurchaseOrderPutawayPage purchaseOrderPutawayPage, Context context,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs, Verification verification, InventoryDB inventoryDB,
			LocationDB locationDB, Hooks hooks, JDAFooter jdaFooter, PuttyFunctionsPage puttyFunctionsPage,
			InventoryTransactionDB inventoryTransactionDB, PurchaseOrderRelocatePage purchaseOrderRelocatePage,
			SkuDB skuDB) {
		this.purchaseOrderPutawayPage = purchaseOrderPutawayPage;
		this.context = context;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.verification = verification;
		this.inventoryDB = inventoryDB;
		this.locationDB = locationDB;
		this.hooks = hooks;
		this.jdaFooter = jdaFooter;
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.inventoryTransactionDB = inventoryTransactionDB;
		this.purchaseOrderRelocatePage = purchaseOrderRelocatePage;
		this.skuDB = skuDB;
	}

	@When("^I select normal putaway$")
	public void i_select_normal_putaway() throws Throwable {
		purchaseOrderPutawayPage.selectPutawayMenu();
		purchaseOrderPutawayPage.selectNormalPutawayMenu();
	}

	@When("^I do normal putaway for all tags received$")
	public void i_do_normal_putaway_for_all_tags_received() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_normal_putaway();
		i_should_be_directed_to_putent_page();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));

			i_enter_urn_id_in_putaway();
			jdaFooter.PressEnter();
			// the_tag_details_for_putaway_should_be_displayed();
			// context.setToLocation(locationDB.getLocation("UnLocked"));

			context.setToLocation(inventoryDB.getPutawayLocation(context.getSkuId(), context.getLocation()));
			jdaFooter.pressTab();
			i_enter_to_location(context.getToLocation());
			jdaFooter.PressEnter();
			 i_enter_the_check_string();
			 jdaFooter.PressEnter();
			if (!purchaseOrderPutawayPage.isPutEntDisplayed()) {
				failureList.add("Putaway not completed and Home page not displayed for URN " + context.getUpiId());
				context.setFailureList(failureList);
			}
		}
		hooks.logoutPutty();
	}

	@When("^I choose normal putaway$")
	public void i_choose_normal_putaway() throws Throwable {

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_normal_putaway();
		i_should_be_directed_to_putent_page();
	}

	@When("^I choose normal putaway for returns$")
	public void i_choose_normal_putaway_for_returns() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_normal_putaway();
		i_should_be_directed_to_putent_page();

		i_enter_urn_id_in_putaway();
		jdaFooter.PressEnter();
		jdaFooter.pressTab();
		jdaFooter.pressTab();

		String[] putawayLocation = purchaseOrderPutawayPage.getPutawayLocation().split("_");
		String toLocation = putawayLocation[0];
		context.setToLocation(toLocation);

		jdaFooter.PressEnter();
		i_enter_the_check_string();
		jdaFooter.PressEnter();
		hooks.logoutPutty();

	}

	@When("^I proceed without entering location$")
	public void i_proceed_without_entering_location() throws InterruptedException, FindFailed {
		ArrayList failureList1 = new ArrayList();
		poMap = context.getPOMap();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));

			if (context.getsupplierType().equalsIgnoreCase("FSV")) {
				i_enter_pallet_id_in_putaway(context.getPalletIDList().get(i - 1));
				jdaFooter.PressEnter();
				jdaFooter.PressEnter();
				if (!purchaseOrderPutawayPage.isLocationErrorDisplayed()) {
					failureList1.add("Error message:Cannot find putaway location not displayed as expected for pallet"
							+ context.getPalletIDList().get(i - 1));
				}
				// else {
				// i_enter_urn_id_in_putaway();
				// if (null == context.getLockCode()) {
				// the_tag_details_for_putaway_should_be_displayed();
				// jdaFooter.PressEnter();
				// if (!purchaseOrderPutawayPage.isLocationErrorDisplayed()) {
				// failureList1
				// .add("Error message:Cannot find putaway location not
				// displayed as expected for UPI"
				// + context.getUpiId());
				// }
				// }
				// }
				jdaFooter.PressEnter();
				purchaseOrderPutawayPage.navigateToBackScreen();
			}
			context.setFailureList(failureList1);
		}
	}

	@When("^I proceed without entering IDT location$")
	public void i_proceed_without_entering_IDT_location() throws InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		String dstamp=inventoryDB.getDstamp(context.getSkuId());
		String transactionTime=dstamp.replace(':','.').substring(10,19);
		context.setTransactionTime(transactionTime);
		context.setTagId(inventoryTransactionDB.getTagIdForSpecificTime(context.getSkuId(), "Receipt",
				context.getTransactionTime()));
		System.out.println("tag id" + context.getTagId());
		i_enter_pallet_id_in_putaway(context.getTagId());
		ArrayList failureList1 = new ArrayList();
		jdaFooter.PressEnter();
		jdaFooter.pressTab();
		for (int t = 0; t < 6; t++) {
			puttyFunctionsPage.rightArrow();
		}
		for (int i = 0; i < 6; i++) {
			jdaFooter.pressBackSpace();
		}
		jdaFooter.PressEnter();

		
//		if (null == context.getLockCode()) {
//			i_enter_urn_id_in_putaway();
//			the_tag_details_for_putaway_should_be_displayed();
//			jdaFooter.PressEnter();
//			if (!purchaseOrderPutawayPage.isLocationErrorDisplayed()) {
//				failureList1.add("Error message:Cannot find putaway location not displayed as expected for UPI"
//						+ context.getUpiId());
//			}
//		}
//		jdaFooter.PressEnter();
//		purchaseOrderPutawayPage.navigateToBackScreen();
//
//		context.setFailureList(failureList1);

	}

	@When("^I perform normal putaway after relocation for FSV PO$")
	public void i_perform_normal_putaway_after_relocation_for_fsv_po() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_normal_putaway();
		i_should_be_directed_to_putent_page();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setTagId(
					inventoryTransactionDB.getTagID(context.getPreAdviceId(), "Receipt", context.getSkuId(), date));
			context.setUpiId(context.getTagId());
			i_enter_urn_id_in_putaway_for_fsv_po(context.getTagId());
			if (null == context.getLockCode()) {
				the_tag_details_for_putaway_should_be_displayed_after_relocation();

			}
			jdaFooter.PressEnter();
			Assert.assertTrue("ChkTo page not displayed", purchaseOrderRelocatePage.isChkToDisplayed());
			purchaseOrderRelocatePage.enterChks(locationDB.getCheckString(context.getToLocation()));
			jdaFooter.PressEnter();
			i_should_be_directed_to_putent_page();

		}
	}

	@When("^I perform normal putaway$")
	public void i_perform_normal_putaway() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_normal_putaway();
		i_should_be_directed_to_putent_page();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setTagId(
					inventoryTransactionDB.getTagID(context.getPreAdviceId(), "Receipt", context.getSkuId(), date));
			context.setUpiId(context.getTagId());
			i_enter_urn_id_in_putaway(context.getTagId());
			jdaFooter.PressEnter();
			if (null == context.getLockCode()) {
				the_tag_details_for_putaway_should_be_displayed_after_relocation();

			}
			jdaFooter.PressEnter();
			if (purchaseOrderRelocatePage.isChkToDisplayed()) {
				Assert.assertTrue("ChkTo page not displayed", purchaseOrderRelocatePage.isChkToDisplayed());
				purchaseOrderRelocatePage.enterChks(locationDB.getCheckString(context.getToLocation()));
				jdaFooter.PressEnter();
			} else {

			}
			i_should_be_directed_to_putent_page();

		}
	}

	@When("^I perform normal putaway after relocation$")
	public void i_perform_normal_putaway_after_relocation() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_normal_putaway();
		i_should_be_directed_to_putent_page();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setTagId(
					inventoryTransactionDB.getTagID(context.getPreAdviceId(), "Receipt", context.getSkuId(), date));
			context.setUpiId(context.getTagId());
			i_enter_urn_id_in_putaway(context.getTagId());
			jdaFooter.PressEnter();
			if (null == context.getLockCode()) {
				the_tag_details_for_putaway_should_be_displayed_after_relocation();

			}
			jdaFooter.PressEnter();
			if (purchaseOrderRelocatePage.isChkToDisplayed()) {
				Assert.assertTrue("ChkTo page not displayed", purchaseOrderRelocatePage.isChkToDisplayed());
				purchaseOrderRelocatePage.enterChks(locationDB.getCheckString(context.getToLocation()));
				jdaFooter.PressEnter();
			} else {

			}
			i_should_be_directed_to_putent_page();

		}
	}

	@When("^I perform normal returns putaway after relocation$")
	public void i_perform_normal_returns_putaway_after_relocation() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		// poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_normal_putaway();
		i_should_be_directed_to_putent_page();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(context.getSkuFromUPI().get(i - 1));
			System.out.println(context.getSkuId());
			context.setTagId(inventoryTransactionDB.getTagID(context.getUpiId(), "Receipt", context.getSkuId(), date));
			i_enter_urn_id_in_putaway(context.getTagId());
			if (null == context.getLockCode()) {
				the_tag_details_for_putaway_should_be_displayed_after_relocation();

			}
			jdaFooter.PressEnter();
			if (purchaseOrderRelocatePage.isChkToDisplayed()) {
				Assert.assertTrue("ChkTo page not displayed", purchaseOrderRelocatePage.isChkToDisplayed());
				purchaseOrderRelocatePage.enterChks(locationDB.getCheckString(context.getToLocation()));
				jdaFooter.PressEnter();
			} else {

			}
			i_should_be_directed_to_putent_page();

		}
	}

	@When("^I perform normal returns putaway$")
	public void i_perform_normal_returns_putaway() throws Throwable {
		System.out.println(context.getUpiId());

		ArrayList<String> failureList = new ArrayList<String>();
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		i_select_normal_putaway();
		i_should_be_directed_to_putent_page();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(context.getSkuFromUPI().get(i - 1));
			System.out.println(context.getSkuId());
			context.setTagId(inventoryTransactionDB.getTagID(context.getUpiId(), "Receipt", context.getSkuId(), date));
			i_enter_urn_id_in_putaway(context.getTagId());
			if (null == context.getLockCode()) {
				the_tag_details_for_putaway_should_be_displayed();

			}
			jdaFooter.PressEnter();
			if (purchaseOrderRelocatePage.isChkToDisplayed()) {
				Assert.assertTrue("ChkTo page not displayed", purchaseOrderRelocatePage.isChkToDisplayed());
				purchaseOrderRelocatePage.enterChks(locationDB.getCheckString(context.getToLocation()));
				jdaFooter.PressEnter();
			}
			i_should_be_directed_to_putent_page();

		}
	}

	@When("^I should not be able to putaway locked PO$")
	public void i_should_not_be_able_to_putaway_locked_po() throws Throwable {
		ArrayList failureList = new ArrayList();
		i_choose_normal_putaway();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			i_enter_urn_id_in_putaway();
			jdaFooter.PressEnter();
			if (purchaseOrderPutawayPage.isPutCmpPageDisplayed()) {
				failureList.add("Putaway details page should not be displayed as the PO is locked for putaway for SKU"
						+ context.getSkuId());
				jdaFooter.PressEnter();
			} else if (purchaseOrderPutawayPage.isPutGrpPageDisplayed()) {
				failureList.add("Putaway details page should not be displayed as the PO is locked for putaway for SKU"
						+ context.getSkuId());
				jdaFooter.pressF12();
			}

		}
		Assert.assertTrue("Putaway for Locked PO is not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@When("^the error message should be displayed as cannot find putaway location$")
	public void the_error_message_should_be_displayed_as_cannot_find_putaway_location() throws InterruptedException {
		Assert.assertTrue("PO , UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(context.getFailureList().toArray()) + "].", context.getFailureList().isEmpty());
	}

	@When("^I proceed without entering quantity$")
	public void i_proceed_without_entering_quantity() throws InterruptedException {
		jdaFooter.pressTab();
		for (int i = 0; i < 25; i++) {
			jdaFooter.pressBackSpace();
		}
		jdaFooter.PressEnter();
	}

	@When("^I proceed without entering location for returns$")
	public void i_proceed_without_entering_location_for_returns() throws Throwable {

		i_enter_pallet_id_in_putaway(context.getTagId());

		jdaFooter.PressEnter();
		if (context.getSiteId().equalsIgnoreCase("5649")) {

			for (int i = 0; i < 9; i++) {
				puttyFunctionsPage.rightArrow();
			}
			for (int i = 0; i < 9; i++) {
				jdaFooter.pressBackSpace();
			}
		} else if (context.getSiteId().equalsIgnoreCase("5885")) {
			jdaFooter.pressTab();
			jdaFooter.pressTab();
			for (int i = 0; i < 9; i++) {
				puttyFunctionsPage.rightArrow();
			}
			for (int i = 0; i < 9; i++) {
				jdaFooter.pressBackSpace();
			}
		}

		jdaFooter.PressEnter();
	}

	@When("^I proceed without entering IDT quantity$")
	public void i_proceed_without_entering_IDT_quantity() throws InterruptedException, FindFailed, ClassNotFoundException, SQLException {
		context.setTagId(inventoryTransactionDB.getTagIdForSpecificTime(context.getSkuId(), "Receipt",
				context.getTransactionTime()));
		i_enter_pallet_id_in_putaway(context.getTagId());

		jdaFooter.PressEnter();
		for (int i = 0; i < 9; i++) {
			puttyFunctionsPage.rightArrow();
		}
		for (int i = 0; i < 9; i++) {
			jdaFooter.pressBackSpace();
		}
		jdaFooter.PressEnter();
	}

	@When("^I proceed without entering quantity for returns$")
	public void i_proceed_without_entering_quantity_for_returns() throws InterruptedException, FindFailed {
		i_enter_pallet_id_in_putaway(context.getTagId());
		jdaFooter.PressEnter();
		jdaFooter.pressTab();
		for (int t = 0; t < 7; t++) {
			puttyFunctionsPage.rightArrow();
		}
		for (int t = 0; t < 7; t++) {
			puttyFunctionsPage.backspace();
		}
		jdaFooter.PressEnter();
	}

	@When("^the error message should be displayed as invalid quantity exception$")
	public void the_error_message_should_be_displayed_as_invalid_quantity_exception() throws InterruptedException {
		Assert.assertTrue("Error message:Invalid Quantity Exception not displayed as expected",
				purchaseOrderPutawayPage.isQuantityErrorDisplayed());
		jdaFooter.PressEnter();
	}

	@Then("^the warning message should be displayed$")
	public void the_warning_message_should_be_displayed() throws Throwable {
		Assert.assertTrue("warning message:Invalid location Exception not displayed as expected",
				purchaseOrderPutawayPage.isLocationWarningrDisplayed());
		puttyFunctionsPage.backscreen();
		Thread.sleep(1000);

	}

	@Then("^the warning message should be displayed for returns$")
	public void the_warning_message_should_be_displayed_for_returns() throws Throwable {
		Assert.assertTrue("warning message:Invalid location Exception not displayed as expected",
				purchaseOrderPutawayPage.isLocationWarningrDisplayed());
		puttyFunctionsPage.backscreen();
		Thread.sleep(1000);
	}

	@When("^I enter to location$")
	public void i_enter_to_location(String location) throws InterruptedException {
		purchaseOrderPutawayPage.enterLocation(location);
	}

	@Then("^I should be directed to putent page$")
	public void i_should_be_directed_to_putent_page() throws Throwable {
		Assert.assertTrue("Putaway Home page not displayed.", purchaseOrderPutawayPage.isPutEntDisplayed());
	}

	@Then("^I enter the check string$")
	public void i_enter_the_check_string() throws Throwable {
		Assert.assertTrue("Chk To Page not displayed to enter check string",
				purchaseOrderPutawayPage.isChkToDisplayed());
		purchaseOrderPutawayPage.enterCheckString(locationDB.getCheckString(context.getToLocation()));

	}

	@Then("^I enter the check string for location$")
	public void i_enter_the_check_string_for_location() throws Throwable {
		Assert.assertTrue("Chk To Page not displayed to enter check string",
				purchaseOrderPutawayPage.isChkToDisplayed());
		purchaseOrderPutawayPage.enterCheckString(locationDB.getCheckString(context.getToLocation2()));

	}

	@When("^I enter urn id in putaway$")
	public void i_enter_urn_id_in_putaway() throws FindFailed, InterruptedException {

		purchaseOrderPutawayPage.enterURNID(context.getTagId());

	}

	@When("^I enter pallet id in putaway$")
	public void i_enter_pallet_id_in_putaway(String palletId) throws FindFailed, InterruptedException {
		purchaseOrderPutawayPage.enterURNID(palletId);
	}

	public void i_enter_urn_id_in_putaway(String tagId) throws FindFailed, InterruptedException {
		purchaseOrderPutawayPage.enterURNID(tagId);

	}

	@When("^I enter urn id in putaway for FSV PO$")
	public void i_enter_urn_id_in_putaway_for_fsv_po() throws FindFailed, InterruptedException {
		purchaseOrderPutawayPage.enterURNID(context.getUpiId());
	}

	public void i_enter_urn_id_in_putaway_for_fsv_po(String tagId) throws FindFailed, InterruptedException {
		purchaseOrderPutawayPage.enterURNID(tagId);
	}

	@When("^the tag details for putaway should be displayed after relocation$")
	public void the_tag_details_for_putaway_should_be_displayed_after_relocation()
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		ArrayList failureList = new ArrayList();
		Assert.assertTrue("PutCmp page not displayed to enter To Location",
				purchaseOrderPutawayPage.isPutCmpPageDisplayed());
		verification.verifyData("From Location", context.getFromLocation(), purchaseOrderPutawayPage.getFromLocation(),
				failureList);

		//TODO-Check this verification
//		verification.verifyData("Tag ID", context.getUpiId(), purchaseOrderPutawayPage.getTagId(), failureList);
		if(purchaseOrderPutawayPage.getToLocation()!=null)
		{
		context.setToLocation(purchaseOrderPutawayPage.getToLocation());
		}
		else
		{
			System.out.println("CHECKKKKK"+context.getSKUType());
			if(context.getSKUType().equalsIgnoreCase("Hanging"))
		{
				System.out.println("Entered !!!!!!!");
			context.setToLocation(inventoryDB.getToLocationForPutaway("HANG",skuDB.getProductGroup(context.getSkuId())));
			}
			else if(context.getSKUType().equalsIgnoreCase("Boxed"))
			{
				System.out.println("Entered 2222222");
			context.setToLocation(inventoryDB.getToLocationForPutawayBoxed("BOX"));

			}
			jdaFooter.pressTab();
			i_enter_to_location(context.getToLocation());
		}
		Assert.assertTrue("SKU Attributes are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@When("^the tag details for putaway should be displayed$")
	public void the_tag_details_for_putaway_should_be_displayed() throws FindFailed, InterruptedException {
		ArrayList failureList = new ArrayList();
		// Assert.assertTrue("PutCmp page not displayed to enter To Location",
		// purchaseOrderPutawayPage.isPutCmpPageDisplayed());
		verification.verifyData("From Location", context.getLocationID(), purchaseOrderPutawayPage.getFromLocation(),
				failureList);
		context.setFromLocation(context.getLocationID());
		// verification.verifyData("Tag ID", context.getUpiId(),
		// purchaseOrderPutawayPage.getTagId(), failureList);
		// context.setToLocation(purchaseOrderPutawayPage.getToLocation());
		Assert.assertTrue("SKU Attributes are not as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Then("^the  error message should be displayed$")
	public void the_error_message_should_be_displayed() throws Throwable {
		Assert.assertTrue(
				" error message is not displayed. [" + Arrays.asList(context.getFailureList().toArray()) + "].",
				context.getFailureList().isEmpty());
	}

	@When("^I proceed by entering less quantity$")
	public void i_proceed_by_entering_less_quantity() throws Throwable {
		upiMap = context.getUPIMap();
		poMap = context.getPOMap();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			String quantity = String.valueOf(context.getRcvQtyDue() - 2);
			Thread.sleep(2000);

			purchaseOrderPutawayPage.enterURNID(context.getTagId());
			jdaFooter.PressEnter();
			for (int j = 0; j < 10; j++) {
				puttyFunctionsPage.rightArrow();
			}

			for (int k = 0; k < 10; k++) {
				puttyFunctionsPage.backspace();

			}
			purchaseOrderPutawayPage.enterQuantity(quantity);
			jdaFooter.PressEnter();
			Assert.assertTrue("Location Full Message not displayed as expected",
					purchaseOrderPutawayPage.isLocationFullDisplayed());

			purchaseOrderPutawayPage.selectLocationFullMenu();
			jdaFooter.PressEnter();

			Assert.assertTrue("Searching for New putaway location not displayed as expected",
					purchaseOrderPutawayPage.isSearchForNewPutawayDisplayed());
			jdaFooter.PressEnter();

			// To putaway location for first few quantity
			context.setPutawayLocation1(inventoryDB.getPutawayLocation1(context.getSkuId()));
			jdaFooter.pressTab();
			purchaseOrderPutawayPage.enterLocation(context.getPutawayLocation1());
			jdaFooter.PressEnter();

			purchaseOrderPutawayPage.enterCheckString(locationDB.getCheckString(context.getPutawayLocation1()));

			jdaFooter.PressEnter();

			// To putaway location for remaining quantity
			// context.setPutawayLocation2(inventoryDB.getPutawayLocation2(context.getSkuId()));
			jdaFooter.pressTab();
			purchaseOrderPutawayPage.enterLocation(context.getPutawayLocation1());
			jdaFooter.PressEnter();

			purchaseOrderPutawayPage.enterCheckString(locationDB.getCheckString(context.getPutawayLocation1()));

			jdaFooter.PressEnter();

			Assert.assertTrue("PutAway completion is not as expected",
					purchaseOrderPutawayPage.isPutCmpPageDisplayed());
		}
		hooks.logoutPutty();
	}

	@When("^I proceed by entering less quantity for FSV$")
	public void i_proceed_by_entering_less_quantity_for_FSV() throws Throwable {
		ArrayList<String> skuList = new ArrayList<String>();
		poMap = context.getPOMap();
		skuList = context.getSkuList();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));

			context.setRcvQtyDue(Integer.parseInt(poMap.get(i).get("QTY DUE")));
			String quantity = String.valueOf(context.getRcvQtyDue() - 10);
			Thread.sleep(2000);

			purchaseOrderPutawayPage.enterURNID(context.getTagId());
			jdaFooter.PressEnter();
			for (int j = 0; j < 11; j++) {
				puttyFunctionsPage.rightArrow();
			}

			for (int k = 0; k < 11; k++) {
				puttyFunctionsPage.backspace();

			}
			purchaseOrderPutawayPage.enterQuantity(quantity);
			jdaFooter.PressEnter();
			Assert.assertTrue("Location Full Message not displayed as expected",
					purchaseOrderPutawayPage.isLocationFullDisplayed());

			purchaseOrderPutawayPage.selectLocationFullMenu();
			jdaFooter.PressEnter();

			String[] putawayLocation = purchaseOrderPutawayPage.getPutawayLocation().split("_");
			String toLocation = putawayLocation[0];
			context.setToLocation(toLocation);
			jdaFooter.PressEnter();

			i_enter_the_check_string();
			jdaFooter.PressEnter();

			String[] putawayLocation2 = purchaseOrderPutawayPage.getPutawayLocation().split("_");
			String toLocation2 = putawayLocation2[0];
			context.setToLocation2(toLocation2);

			jdaFooter.PressEnter();
			i_enter_the_check_string_for_location();

			jdaFooter.PressEnter();

			Assert.assertTrue("PutAway completion is not as expected",
					purchaseOrderPutawayPage.isPutCmpPageDisplayed());
		}
		hooks.logoutPutty();
	}

	@When("^I proceed by entering less quantity for IDT$")
	public void i_proceed_by_entering_less_quantity_for_IDT() throws Throwable {
		ArrayList<String> skuList = new ArrayList<String>();
		upiMap = context.getUPIMap();

		skuList = context.getSkuList();

		context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
		String quantity = String.valueOf(context.getRcvQtyDue() - 10);
		Thread.sleep(2000);
		context.setTagId(inventoryTransactionDB.getTagIdForSpecificTime(context.getSkuId(), "Receipt",
				context.getTransactionTime()));
		purchaseOrderPutawayPage.enterURNID(context.getTagId());
		jdaFooter.PressEnter();
		for (int j = 0; j < 11; j++) {
			puttyFunctionsPage.rightArrow();
		}

		for (int k = 0; k < 11; k++) {
			puttyFunctionsPage.backspace();

		}
		purchaseOrderPutawayPage.enterQuantity(quantity);
		jdaFooter.PressEnter();
		Assert.assertTrue("Location Full Message not displayed as expected",
				purchaseOrderPutawayPage.isLocationFullDisplayed());

		purchaseOrderPutawayPage.selectLocationFullMenu();
		jdaFooter.PressEnter();

		String[] putawayLocation = purchaseOrderPutawayPage.getPutawayLocation().split("_");
		String toLocation = putawayLocation[0];
		context.setToLocation(toLocation);
		jdaFooter.PressEnter();

		i_enter_the_check_string();
		jdaFooter.PressEnter();

		String[] putawayLocation2 = purchaseOrderPutawayPage.getPutawayLocation().split("_");
		String toLocation2 = putawayLocation2[0];
		context.setToLocation2(toLocation2);

		jdaFooter.PressEnter();
		i_enter_the_check_string_for_location();

		jdaFooter.PressEnter();

		Assert.assertTrue("PutAway completion is not as expected", purchaseOrderPutawayPage.isPutCmpPageDisplayed());

		hooks.logoutPutty();

	}

	@When("^I proceed by overriding the location  \"([^\"]*)\" for returns$")
	public void i_proceed_by_overriding_the_location_for_returns(String location) throws Throwable {
		upiMap = context.getUPIMap();
		purchaseOrderPutawayPage.enterURNID(context.getTagId());
		jdaFooter.PressEnter();
		puttyFunctionsPage.pressTab();
		puttyFunctionsPage.pressTab();
		for (int k = 0; k < 10; k++) {
			puttyFunctionsPage.rightArrow();
		}
		for (int j = 0; j < 10; j++) {
			puttyFunctionsPage.backspace();
		}
		jdaFooter.PressEnter();
		purchaseOrderPutawayPage.selectOverride();
		jdaFooter.PressEnter();
		jdaFooter.PressEnter();
		String[] putawayLocation = purchaseOrderPutawayPage.getPutawayLocation().split("_");
		String toLocation = putawayLocation[0];
		context.setToLocation(toLocation);
		i_enter_the_check_string();
		jdaFooter.PressEnter();
		hooks.logoutPutty();
	}

	@When("^I proceed by overriding the location  \"([^\"]*)\" for IDT$")
	public void i_proceed_by_overriding_the_location_for_IDT(String location) throws Throwable {
		upiMap = context.getUPIMap();
		context.setTagId(inventoryTransactionDB.getTagIdForSpecificTime(context.getSkuId(), "Receipt",
				context.getTransactionTime()));
		purchaseOrderPutawayPage.enterURNID(context.getTagId());
		jdaFooter.PressEnter();
		puttyFunctionsPage.pressTab();
		for (int k = 0; k < 10; k++) {
			puttyFunctionsPage.rightArrow();
		}
		for (int j = 0; j < 10; j++) {
			puttyFunctionsPage.backspace();
		}
		jdaFooter.PressEnter();
		purchaseOrderPutawayPage.selectOverride();
		jdaFooter.PressEnter();
		jdaFooter.PressEnter();
		String[] putawayLocation = purchaseOrderPutawayPage.getPutawayLocation().split("_");
		String toLocation = putawayLocation[0];
		context.setToLocation(toLocation);
		i_enter_the_check_string();
		jdaFooter.PressEnter();
		hooks.logoutPutty();
	}

	@When("^I proceed by overriding the location \"([^\"]*)\" for FSV$")
	public void i_proceed_by_overriding_the_location_for_FSV(String location) throws Throwable {
		upiMap = context.getUPIMap();
		purchaseOrderPutawayPage.enterURNID(context.getTagId());
		jdaFooter.PressEnter();
		puttyFunctionsPage.pressTab();
		for (int k = 0; k < 10; k++) {
			puttyFunctionsPage.rightArrow();
		}
		for (int j = 0; j < 10; j++) {
			puttyFunctionsPage.backspace();
		}
		jdaFooter.PressEnter();
		purchaseOrderPutawayPage.selectOverride();
		jdaFooter.PressEnter();
		jdaFooter.PressEnter();
		String[] putawayLocation = purchaseOrderPutawayPage.getPutawayLocation().split("_");
		String toLocation = putawayLocation[0];
		context.setToLocation(toLocation);
		i_enter_the_check_string();
		jdaFooter.PressEnter();
		hooks.logoutPutty();
	}

	@When("^I proceed by overriding the location  \"([^\"]*)\" for PO$")
	public void i_proceed_by_overriding_the_location_for_PO(String location) throws Throwable {
		context.setTagId(inventoryTransactionDB.getTagId(context.getPreAdviceId(), "Receipt"));
		upiMap = context.getUPIMap();
		purchaseOrderPutawayPage.enterURNID(context.getTagId());
		jdaFooter.PressEnter();
		for (int k = 0; k < 10; k++) {
			puttyFunctionsPage.rightArrow();
		}
		for (int j = 0; j < 10; j++) {
			puttyFunctionsPage.backspace();
		}
		jdaFooter.PressEnter();
		purchaseOrderPutawayPage.selectOverride();
		jdaFooter.PressEnter();
		jdaFooter.PressEnter();
		String[] putawayLocation = purchaseOrderPutawayPage.getPutawayLocation().split("_");
		String toLocation = putawayLocation[0];
		context.setToLocation(toLocation);
		i_enter_the_check_string();
		jdaFooter.PressEnter();
		hooks.logoutPutty();
	}
}