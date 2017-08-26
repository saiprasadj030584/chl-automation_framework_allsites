package com.jda.wms.stepdefs.rdt;

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
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderPutawayPage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;

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
	private LocationDB locationDB;
	private Hooks hooks;
	private JDAFooter jdaFooter;
	private PuttyFunctionsPage puttyFunctionsPage;
	private InventoryTransactionDB inventoryTransactionDB;

	@Inject
	public PurchaseOrderPutawayStepDefs(PurchaseOrderPutawayPage purchaseOrderPutawayPage, Context context,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs, Verification verification, InventoryDB inventoryDB,
			LocationDB locationDB, Hooks hooks, JDAFooter jdaFooter, PuttyFunctionsPage puttyFunctionsPage,
			InventoryTransactionDB inventoryTransactionDB) {
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
			the_tag_details_for_putaway_should_be_displayed();
			context.setToLocation(inventoryDB.getPutawayLocation(context.getSkuId(), context.getLocation()));
			i_enter_to_location(context.getToLocation());
			i_enter_the_check_string();
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
		System.out.println("kk" + context.getToLocation());

		jdaFooter.PressEnter();
		i_enter_the_check_string();
		jdaFooter.PressEnter();
		hooks.logoutPutty();

	}

	@When("^I should not be able to putaway locked PO$")
	public void i_should_not_be_able_to_putaway_locked_po() throws Throwable {
		i_choose_normal_putaway();
		Assert.assertFalse("Putaway details page should not be displayed as the PO is locked for putaway",
				purchaseOrderPutawayPage.isPutCmpPageDisplayed());
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
					System.out.println("Check");
					failureList1.add("Error message:Cannot find putaway location not displayed as expected for pallet"
							+ context.getPalletIDList().get(i - 1));
				} else {
					i_enter_urn_id_in_putaway();
					if (null == context.getLockCode()) {
						the_tag_details_for_putaway_should_be_displayed();
						jdaFooter.PressEnter();
						if (!purchaseOrderPutawayPage.isLocationErrorDisplayed()) {
							failureList1
									.add("Error message:Cannot find putaway location not displayed as expected for UPI"
											+ context.getUpiId());
						}
					}
				}

				jdaFooter.PressEnter();
				purchaseOrderPutawayPage.navigateToBackScreen();
			}
			context.setFailureList(failureList1);
			System.out.println(context.getFailureList());
		}
	}

	@When("^I proceed without entering IDT location$")
	public void i_proceed_without_entering_IDT_location() throws InterruptedException, FindFailed {
		// i_enter_pallet_id_in_putaway(context.getTagId());
		ArrayList failureList1 = new ArrayList();
		i_enter_pallet_id_in_putaway("3884");

		jdaFooter.PressEnter();
		jdaFooter.pressTab();
		for (int t = 0; t < 6; t++) {
			puttyFunctionsPage.rightArrow();
		}
		for (int i = 0; i < 6; i++) {
			jdaFooter.pressBackSpace();
		}
		jdaFooter.PressEnter();

		i_enter_urn_id_in_putaway();
		if (null == context.getLockCode()) {
			the_tag_details_for_putaway_should_be_displayed();
			jdaFooter.PressEnter();
			if (!purchaseOrderPutawayPage.isLocationErrorDisplayed()) {
				failureList1.add("Error message:Cannot find putaway location not displayed as expected for UPI"
						+ context.getUpiId());
			}
		}
		jdaFooter.PressEnter();
		purchaseOrderPutawayPage.navigateToBackScreen();

		context.setFailureList(failureList1);

	}

	@When("^the error message should be displayed as cannot find putaway location$")
	public void the_error_message_should_be_displayed_as_cannot_find_putaway_location() throws InterruptedException {
		Assert.assertTrue("Error message:Cannot find putaway location not displayed. ["
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
	public void i_proceed_without_entering_IDT_quantity() throws InterruptedException, FindFailed {
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
		// Assert.assertTrue("Chk To Page not displayed to enter check string",
		// purchaseOrderPutawayPage.isChkToDisplayed());
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
		System.out.println("hi " + palletId);
		purchaseOrderPutawayPage.enterURNID(palletId);
	}

	@When("^the tag details for putaway should be displayed$")
	public void the_tag_details_for_putaway_should_be_displayed() throws FindFailed, InterruptedException {
		ArrayList failureList = new ArrayList();
		Assert.assertTrue("PutCmp page not displayed to enter To Location",
				purchaseOrderPutawayPage.isPutCmpPageDisplayed());
		verification.verifyData("From Location", context.getLocation(), purchaseOrderPutawayPage.getFromLocation(),
				failureList);
		verification.verifyData("Tag ID", context.getUpiId(), purchaseOrderPutawayPage.getTagId(), failureList);
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
		// upiMap = context.getUPIMap();
		poMap = context.getPOMap();
		skuList = context.getSkuList();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			// for (int s = 0; s < skuList.size(); s++) {
			// context.setSkuId(skuList.get(s));

			context.setRcvQtyDue(Integer.parseInt(poMap.get(i).get("QTY DUE")));
			String quantity = String.valueOf(context.getRcvQtyDue() - 10);
			Thread.sleep(2000);
			System.out.println(context.getRcvQtyDue() - 10);
			// purchaseOrderPutawayPage.enterURNID("4413");

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

	@When("^I proceed by enterin less quantity for returns$")
	public void i_proceed_by_enterin_less_quantity_for_returns() throws Throwable {
		ArrayList<String> skuList = new ArrayList<String>();
		upiMap = context.getUPIMap();

		skuList = context.getSkuList();

		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			// context.setSkuId(poMap.get(i).get("SKU"));
			for (int s = 0; s < skuList.size(); s++) {
				context.setSkuId(skuList.get(s));

				context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
				String quantity = String.valueOf(context.getRcvQtyDue() - 1);
				Thread.sleep(2000);
				System.out.println(context.getRcvQtyDue() - 1);
				// purchaseOrderPutawayPage.enterURNID("4413");
				i_enter_urn_id_in_putaway();
				// purchaseOrderPutawayPage.enterURNID(context.getTagId());
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

				// Assert.assertTrue("Searching for New putaway location not
				// displayed as expected",
				// purchaseOrderPutawayPage.isSearchForNewPutawayDisplayed());
				// jdaFooter.PressEnter();

				// To putaway location for first few quantity
				// context.setPutawayLocation1(inventoryDB.getPutawayLocation1(context.getSkuId()));
				// jdaFooter.pressTab();
				// purchaseOrderPutawayPage.enterLocation(context.getPutawayLocation1());
				String[] putawayLocation = purchaseOrderPutawayPage.getPutawayLocation().split("_");
				String toLocation = putawayLocation[0];
				context.setToLocation(toLocation);
				jdaFooter.PressEnter();

				// purchaseOrderPutawayPage.enterCheckString(locationDB.getCheckString(context.getPutawayLocation1()));
				i_enter_the_check_string();
				jdaFooter.PressEnter();

				// To putaway location for remaining quantity
				// context.setPutawayLocation2(inventoryDB.getPutawayLocation2(context.getSkuId()));
				// jdaFooter.pressTab();
				// purchaseOrderPutawayPage.enterLocation(context.getPutawayLocation1());
				String[] putawayLocation2 = purchaseOrderPutawayPage.getPutawayLocation().split("_");
				String toLocation2 = putawayLocation2[0];
				context.setToLocation2(toLocation2);

				jdaFooter.PressEnter();
				i_enter_the_check_string_for_location();

				jdaFooter.PressEnter();

				// purchaseOrderPutawayPage.enterCheckString(locationDB.getCheckString(context.getPutawayLocation1()));
				System.out.println("hi");

				// jdaFooter.PressEnter();

				Assert.assertTrue("PutAway completion is not as expected",
						purchaseOrderPutawayPage.isPutCmpPageDisplayed());
			}
			hooks.logoutPutty();
		}
	}

	@When("^I proceed by overriding the location  \"([^\"]*)\"$")
	public void i_proceed_by_overriding_the_location(String location) throws Throwable {
		upiMap = context.getUPIMap();
		// poMap = context.getPOMap();

		// for (int i = context.getLineItem(); i <= 1; i++) {
		// System.out.println("SKUUUUUUU" + poMap.get(i).get("SKU"));
		// context.setSkuId(poMap.get(i).get("SKU"));
		// context.setTagId(inventoryTransactionDB.getTagID("Receipt",context.getSkuId(),
		// date));

		purchaseOrderPutawayPage.enterURNID(context.getTagId());
		// purchaseOrderPutawayPage.enterURNID("770576");
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
		// puttyFunctionsPage.pressTab();
		// context.setToLocation(inventoryDB.getPutawayLocation(context.getSkuId(),
		// context.getLocation()));
		// context.setToLocation(purchaseOrderPutawayPage.getPutawayLocation());
		// i_enter_to_location(context.getToLocation());
		String[] putawayLocation = purchaseOrderPutawayPage.getPutawayLocation().split("_");
		String toLocation = putawayLocation[0];
		context.setToLocation(toLocation);
		// jdaFooter.PressEnter();
		i_enter_the_check_string();
		// purchaseOrderPutawayPage.enterLocation(location);
		// jdaFooter.PressEnter();
		// purchaseOrderPutawayPage.enterCheckString(locationDB.getCheckString(context.getToLocation()));
		jdaFooter.PressEnter();
		hooks.logoutPutty();
	}
}
