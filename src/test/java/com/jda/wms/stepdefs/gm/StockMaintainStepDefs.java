package com.jda.wms.stepdefs.gm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.Database;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.SkuSkuConfigDB;
import com.jda.wms.db.gm.SupplierSkuDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.StockAdjustmentsPage;
import com.jda.wms.pages.rdt.PuttyFunctionsPage;
import com.jda.wms.stepdefs.rdt.PuttyFunctionsStepDefs;

public class StockMaintainStepDefs {
	Map<Integer, Map<String, String>> poMap;
	Map<String, Map<String, String>> upiMap;
	private Context context;
	private JDAFooter jDAFooter;
	private StockAdjustmentsPage stockAdjustmentsPage;
	private JdaHomePage jDAHomePage;
	private LocationDB locationDB;
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private OrderLineDB orderLineDB;
	private InventoryDB inventoryDB;
	private SkuDB skuDB;
	private JdaLoginPage jdaLoginPage;

	private SupplierSkuDB supplierSkuDb;
	private SkuSkuConfigDB skuSkuConfigDB;
	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private PuttyFunctionsPage puttyFunctionsPage;
	private InventoryUpdateStepDefs inventoryUpdateStepDefs;
	private InventoryTransactionQueryStepDefs inventoryTransactionQueryStepDefs;
	private Database database;
	String envVar = System.getProperty("user.dir");

	@Inject
	public StockMaintainStepDefs(Context context, JDAFooter jDAFooter, StockAdjustmentsPage stockAdjustmentsPage,
			JdaHomePage jDAHomePage, OrderLineDB orderLineDB, InventoryDB inventoryDB, LocationDB locationDB,
			SkuDB skuDB, SupplierSkuDB supplierSkuDb, SkuSkuConfigDB skuSkuConfigDB,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs, PuttyFunctionsPage puttyFunctionsPage,
			JdaLoginPage jdaLoginPage,InventoryUpdateStepDefs inventoryUpdateStepDefs,InventoryTransactionQueryStepDefs inventoryTransactionQueryStepDefs,Database database) {
		this.context = context;
		this.jDAFooter = jDAFooter;
		this.stockAdjustmentsPage = stockAdjustmentsPage;
		this.jDAHomePage = jDAHomePage;
		this.orderLineDB = orderLineDB;
		this.inventoryDB = inventoryDB;
		this.locationDB = locationDB;
		this.skuDB = skuDB;
		this.supplierSkuDb = supplierSkuDb;
		this.skuSkuConfigDB = skuSkuConfigDB;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.jdaLoginPage = jdaLoginPage;
		this.inventoryUpdateStepDefs=inventoryUpdateStepDefs;
		this.inventoryTransactionQueryStepDefs=inventoryTransactionQueryStepDefs;
		this.database=database;
	}

	public void i_maintain_stock_in_inventory_write_off(ArrayList<String> skuList, String uniqueTag) throws Throwable {
		boolean allocation = false;
		String lockcode=null;
		if(uniqueTag.contains("samples"))
		{
			lockcode="HOREQ";
		}
		else if(uniqueTag.contains("jobbers"))
		{
			lockcode="SJ";
		}
		for (int i = 0; i < context.getSkuList().size(); i++) {
			context.setSkuId((String) context.getSkuList().get(i));
			context.setSKUType(skuDB.getSKUType(context.getSkuId()));
			ArrayList<String> locationList = inventoryDB.getLocationsForSku(context.getSkuId());
			System.out.println(locationList);
			ArrayList<String> validLocations = new ArrayList<String>();
			int totalQtyOnHand = 0;
			for (int j = 0; j < locationList.size(); j++) {
				context.setLocation(locationList.get(j));

				if (locationDB.getLocationZone(context.getLocation()) != null) {
					if (context.getSKUType().equalsIgnoreCase("H")) {
						if (locationDB.getLocationZone(context.getLocation()).equalsIgnoreCase("HANG")
								&& locationDB.getUserDefType2(context.getLocation()).contains("HANG")
								&& locationDB.getUserDefType3(context.getLocation()).contains("HANG")
								&& locationDB.getUserDefType1(context.getLocation())
										.contains(skuDB.getProductGroup(context.getSkuId()))
								&& locationDB.getLockStatus(context.getLocation()).equalsIgnoreCase("UnLocked")) {
							System.out.println(inventoryDB.getLockStatus(context.getLocation(), context.getSkuId()));
							if (inventoryDB.getLockStatus(context.getLocation(), context.getSkuId())
									.equalsIgnoreCase("Locked")&& inventoryDB.getLockCodeList(context.getLocation(), context.getSkuId())
									.contains(lockcode)
									&& Integer.parseInt(inventoryDB.getQtynHand(context.getSkuId(),
											context.getLocation())) != Integer
													.parseInt(inventoryDB.getQtyAllocated(context.getSkuId(),
															context.getLocation()))) {
								System.out.println("entered" + context.getLocation());
								validLocations.add(context.getLocation());
								totalQtyOnHand += Integer.parseInt(
										inventoryDB.getQtyForSkuInLocation(context.getSkuId(), context.getLocation()));
							}
						}
					}

					else if (context.getSKUType().equalsIgnoreCase("B")) {
						if (locationDB.getLocationZone(context.getLocation()).contains("BOX")
								&& locationDB.getUserDefType2(context.getLocation()).contains("BOX")
								&& locationDB.getUserDefType3(context.getLocation()).contains("BOX")
								&& locationDB.getLockStatus(context.getLocation()).equalsIgnoreCase("UnLocked")) {
							System.out.println(inventoryDB.getLockStatus(context.getLocation(), context.getSkuId()));
							if (inventoryDB.getLockStatus(context.getLocation(), context.getSkuId())
									.equalsIgnoreCase("Locked")&& inventoryDB.getLockCodeList(context.getLocation(), context.getSkuId())
									.contains(lockcode)
									&& Integer.parseInt(inventoryDB.getQtynHand(context.getSkuId(),
											context.getLocation())) != Integer
													.parseInt(inventoryDB.getQtyAllocated(context.getSkuId(),
															context.getLocation()))) {
								System.out.println("entered" + context.getLocation());
								validLocations.add(context.getLocation());
								totalQtyOnHand += Integer.parseInt(
										inventoryDB.getQtyForSkuInLocation(context.getSkuId(), context.getLocation()));
							}
						}
					}

				}
			}
			System.out.println("VALID LOCATIONS" + validLocations);
			System.out.println("totalQtyOnHand" + totalQtyOnHand);
			if (totalQtyOnHand >= Integer
					.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(), context.getSkuId()))) {
				allocation = true;
			}

			if (context.getLocationID() != null) {

			} else {

				System.out.println("Stock is not present in other locations");

				if (!(allocation)) {
					// default:stock adjustment to qty 500
					jdaLoginPage.login();
					jDAHomePage.navigateToStockAdjustment();
					Thread.sleep(2000);
					stockAdjustmentsPage.selectNewStock();
					jDAFooter.clickNextButton();
					Thread.sleep(2000);
					stockAdjustmentsPage.enterSkuId(context.getSkuId());
					jDAFooter.pressTab();
					//
					if (validLocations.size() != 0) {
						stockAdjustmentsPage.enterLocation(validLocations.get(0));
					} else {
						if (context.getSKUType().equalsIgnoreCase("B")) {
							stockAdjustmentsPage.enterLocation(locationDB.getToLocationForPutawayBoxed("BOX"));
						} else if (context.getSKUType().equalsIgnoreCase("H")) {
							stockAdjustmentsPage.enterLocation(locationDB.getToLocationForPutaway("HANG",
									skuDB.getProductGroup(context.getSkuId())));
						}
						stockAdjustmentsPage.enterSiteId(context.getSiteID());
						stockAdjustmentsPage.enterQuantityOnHand("500");
						stockAdjustmentsPage.enterOrigin("NONE");

						stockAdjustmentsPage
								.enterPackConfig(skuSkuConfigDB.getPackConfigList(context.getSkuId()).get(0));
						jDAFooter.clickNextButton();
						stockAdjustmentsPage.enterPalletType("PALLET");
						jDAFooter.clickNextButton();
						stockAdjustmentsPage.enterReasonCode();
						jDAFooter.clickDoneButton();
						stockAdjustmentsPage.handlePopUp();
					}
					
					//inventory update locka sttaus chnage samples
					
					if(lockcode.equalsIgnoreCase("HOREQ"))
					{
						jDAHomePage.navigateToInventoryUpdate();
						inventoryUpdateStepDefs.i_select_the_update_type_as("Lock Status Change");
						inventoryUpdateStepDefs.i_search_the_inventory_for_the_tag();
						inventoryUpdateStepDefs.the_tag_details_should_be_displayed();
						inventoryUpdateStepDefs.i_select_the_status_as_and_code_as("Locked","1Stock Removal-Sample");
						jDAHomePage.navigateToInventoryTransactionPage();
						inventoryTransactionQueryStepDefs.i_choose_the_code_as_and_search_the_tag_id("Inventory Lock");
						inventoryTransactionQueryStepDefs.the_status_should_be_updated();
					}
					else if(lockcode.equalsIgnoreCase("SJ"))
					{
						jDAHomePage.navigateToInventoryUpdate();
						inventoryUpdateStepDefs.i_select_the_update_type_as("Lock Status Change");
						inventoryUpdateStepDefs.i_search_the_inventory_for_the_tag();
						inventoryUpdateStepDefs.the_tag_details_should_be_displayed();
						inventoryUpdateStepDefs.i_select_the_status_as_and_code_as("Locked","1Stock Removal-Jobber");
						jDAHomePage.navigateToInventoryTransactionPage();
						inventoryTransactionQueryStepDefs.i_choose_the_code_as_and_search_the_tag_id("Inventory Lock");
						inventoryTransactionQueryStepDefs.the_status_should_be_updated();
					}

				}

			}
		}

	}

	public void i_maintain_stock_in_inventory(ArrayList<String> skuList, String uniqueTag) throws Throwable {
		if (uniqueTag.contains("suspense")) {
			context.setLocationID("Suspense");
		} else if (uniqueTag.contains("prohibit")) {
			context.setLocationID("Prohibition");
		}
		boolean allocation = false;
		for (int i = 0; i < context.getSkuList().size(); i++) {
			context.setSkuId((String) context.getSkuList().get(i));
			context.setSKUType(skuDB.getSKUType(context.getSkuId()));
			ArrayList<String> locationList = inventoryDB.getLocationListForSku(context.getSkuId());
			System.out.println(locationList);
			ArrayList<String> validLocations = new ArrayList<String>();
			int totalQtyOnHand = 0;
			for (int j = 0; j < locationList.size(); j++) {
				context.setLocation(locationList.get(j));

				if (locationDB.getLocationZone(context.getLocation()) != null) {
					if (context.getSKUType().equalsIgnoreCase("H")) {
						if (locationDB.getLocationZone(context.getLocation()).equalsIgnoreCase("HANG")
								&& locationDB.getUserDefType2(context.getLocation()).contains("HANG")
								&& locationDB.getUserDefType3(context.getLocation()).contains("HANG")
								&& locationDB.getUserDefType1(context.getLocation())
										.contains(skuDB.getProductGroup(context.getSkuId()))
								&& locationDB.getLockStatus(context.getLocation()).equalsIgnoreCase("UnLocked")) {
							System.out.println(inventoryDB.getLockStatus(context.getLocation(), context.getSkuId()));
							if (inventoryDB.getLockStatus(context.getLocation(), context.getSkuId())
									.equalsIgnoreCase("UnLocked")
									&& Integer.parseInt(inventoryDB.getQtynHand(context.getSkuId(),
											context.getLocation())) != Integer
													.parseInt(inventoryDB.getQtyAllocated(context.getSkuId(),
															context.getLocation()))) {
								System.out.println("entered" + context.getLocation());
								validLocations.add(context.getLocation());
								totalQtyOnHand += Integer.parseInt(
										inventoryDB.getQtyForSkuInLocation(context.getSkuId(), context.getLocation()));
							}
						}
					}

					else if (context.getSKUType().equalsIgnoreCase("C")) {
						System.out.println("Entered GOH");
						if (locationDB.getLocationZone(context.getLocation()).equalsIgnoreCase("HANG")
								&& locationDB.getUserDefType2(context.getLocation()).contains("HANG")
								&& locationDB.getUserDefType3(context.getLocation()).contains("HANG")
								&& locationDB.getLockStatus(context.getLocation()).equalsIgnoreCase("UnLocked")) {
							System.out.println(inventoryDB.getLockStatus(context.getLocation(), context.getSkuId()));
							if (inventoryDB.getLockStatus(context.getLocation(), context.getSkuId())
									.equalsIgnoreCase("UnLocked")
									&& Integer.parseInt(inventoryDB.getQtynHand(context.getSkuId(),
											context.getLocation())) != Integer
													.parseInt(inventoryDB.getQtyAllocated(context.getSkuId(),
															context.getLocation()))) {
								System.out.println("entered" + context.getLocation());
								validLocations.add(context.getLocation());
								totalQtyOnHand += Integer.parseInt(
										inventoryDB.getQtyForSkuInLocation(context.getSkuId(), context.getLocation()));
							}
						}
					}

					else if (context.getSKUType().equalsIgnoreCase("B")) {
						if (locationDB.getLocationZone(context.getLocation()).contains("BOX")
								&& locationDB.getUserDefType2(context.getLocation()).contains("BOX")
								&& locationDB.getUserDefType3(context.getLocation()).contains("BOX")
								&& locationDB.getLockStatus(context.getLocation()).equalsIgnoreCase("UnLocked")) {
							System.out.println(inventoryDB.getLockStatus(context.getLocation(), context.getSkuId()));
							if (inventoryDB.getLockStatus(context.getLocation(), context.getSkuId())
									.equalsIgnoreCase("UnLocked")
									&& Integer.parseInt(inventoryDB.getQtynHand(context.getSkuId(),
											context.getLocation())) != Integer
													.parseInt(inventoryDB.getQtyAllocated(context.getSkuId(),
															context.getLocation()))) {
								System.out.println("entered" + context.getLocation());
								validLocations.add(context.getLocation());
								totalQtyOnHand += Integer.parseInt(
										inventoryDB.getQtyForSkuInLocation(context.getSkuId(), context.getLocation()));
							}
						}
					}

					else if (context.getSKUType().equalsIgnoreCase("P")) {
						if ((locationDB.getLocationZone(context.getLocation()).contains("BOX")
								&& locationDB.getUserDefType2(context.getLocation()).contains("BOX")
								&& locationDB.getUserDefType3(context.getLocation()).contains("FLAT")
								&& locationDB.getLockStatus(context.getLocation()).equalsIgnoreCase("UnLocked"))
								|| (locationDB.getLocationZone(context.getLocation()).contains("HANG")
										&& locationDB.getUserDefType2(context.getLocation()).contains("HANG")
										&& locationDB.getUserDefType3(context.getLocation()).contains("FLAT")
										&& locationDB.getUserDefType1(context.getLocation())
												.contains(skuDB.getProductGroup(context.getSkuId()))
										&& locationDB.getLockStatus(context.getLocation())
												.equalsIgnoreCase("UnLocked"))) {
							System.out.println(inventoryDB.getLockStatus(context.getLocation(), context.getSkuId()));
							if (inventoryDB.getLockStatus(context.getLocation(), context.getSkuId())
									.equalsIgnoreCase("UnLocked")
									&& Integer.parseInt(inventoryDB.getQtynHand(context.getSkuId(),
											context.getLocation())) != Integer
													.parseInt(inventoryDB.getQtyAllocated(context.getSkuId(),
															context.getLocation()))) {
								System.out.println("entered" + context.getLocation());
								validLocations.add(context.getLocation());
								totalQtyOnHand += Integer.parseInt(
										inventoryDB.getQtyForSkuInLocation(context.getSkuId(), context.getLocation()));
							}
						}
					}

				}
			}
			System.out.println("VALID LOCATIONS" + validLocations);
			System.out.println("totalQtyOnHand" + totalQtyOnHand);
			if (totalQtyOnHand >= Integer
					.parseInt(orderLineDB.getQtyOrdered(context.getOrderId(), context.getSkuId()))) {
				allocation = true;
			}

			if (context.getLocationID() != null) {
				if (context.getLocationID().equalsIgnoreCase("suspense")) {
					String location = null;

					if (!inventoryDB.isSkuInSuspenseLocation(context.getSkuId())) {
						System.out.println("Sku not in suspense location " + context.getSkuId());
						// do stock check
						System.out.println("SUSPENSE" + validLocations.get(0));
						System.out.println(inventoryDB.getQtynHand(context.getSkuId(), validLocations.get(0)));
						if (validLocations.size() != 0) {
							i_do_new_stock_check_at_location_with_quantity(validLocations.get(0), String.valueOf(Integer
									.valueOf(inventoryDB.getQtynHand(context.getSkuId(), validLocations.get(0)))));
							inventoryDB.updateInventoryQty(validLocations.get(0), String.valueOf(0));
						} else {
							if (context.getSKUType().equalsIgnoreCase("B")) {

								location = locationDB.getToLocationForPutawayBoxed("BOX");
							} else if (context.getSKUType().equalsIgnoreCase("H")) {

								locationDB.getToLocationForPutaway("HANG", skuDB.getProductGroup(context.getSkuId()));

							} else if (context.getSKUType().equalsIgnoreCase("P")) {

								location = locationDB
										.getToLocationForPutawayFlatpack(skuDB.getProductGroup(context.getSkuId()));
							} else if (context.getSKUType().equalsIgnoreCase("C")) {
								location = locationDB.getPutawayLocationForGoh("HANG");
							}

							i_do_new_stock_check_at_location_with_quantity(location, String
									.valueOf(Integer.valueOf(inventoryDB.getQtynHand(context.getSkuId(), location))));
							inventoryDB.updateInventoryQty(location, String.valueOf(0));
						}

					}
					System.out.println("ALLOCATION" + allocation);
					if (allocation) {
						System.out.println("Stock is present in other locations");
						// update in inventory of valid locations to 0
						if (validLocations.size() != 0) {
							for (int j = 0; j < validLocations.size(); j++) {
								System.out.println("SUSPENSE" + validLocations.get(j));
								System.out.println(inventoryDB.getQtynHand(context.getSkuId(), validLocations.get(j)));
								inventoryDB.updateInventoryQty(validLocations.get(j), String.valueOf(0));
							}
						}
					}
				}

				if (context.getLocationID().equalsIgnoreCase("Prohibition")) {
					boolean prohibited = false;
					for (int k = 0; k < validLocations.size(); k++) {
						if (inventoryDB.isStockForSkuInProhibitedLocation(context.getSkuId(), validLocations.get(k))) {
							prohibited = true;
						}

					}
					System.out.println("PROHIBITION" + prohibited);

					if (!(prohibited)) {

						if (context.getSKUType().equalsIgnoreCase("B")) {
							i_do_new_stock_check_at_location_with_quantity_and_supplier(
									locationDB.getToLocationForPutawayBoxed("BOX"), "100",
									supplierSkuDb.getProhibitedSupplier(context.getSkuId()));
						} else if (context.getSKUType().equalsIgnoreCase("C")
								|| context.getSKUType().equalsIgnoreCase("H")) {
							i_do_new_stock_check_at_location_with_quantity_and_supplier(
									locationDB.getToLocationForPutaway("HANG",
											skuDB.getProductGroup(context.getSkuId())),
									"100", supplierSkuDb.getProhibitedSupplier(context.getSkuId()));
						} else if (context.getSKUType().equalsIgnoreCase("P")) {
							i_do_new_stock_check_at_location_with_quantity_and_supplier(
									locationDB
											.getToLocationForPutawayFlatpack(skuDB.getProductGroup(context.getSkuId())),
									"100", supplierSkuDb.getProhibitedSupplier(context.getSkuId()));
						}
					}

					System.out.println("ALLOCATION" + allocation);
					if (allocation) {
						System.out.println("Stock is present in other locations");
						// update in inventory of valid locations to 0
						if (validLocations.size() != 0) {
							for (int j = 0; j < validLocations.size(); j++) {
								if (!(inventoryDB.getOriginIdValue(validLocations.get(j)).equalsIgnoreCase("TUR"))) {
									System.out.println("SUSPENSE" + validLocations.get(j));
									System.out.println(
											inventoryDB.getQtynHand(context.getSkuId(), validLocations.get(j)));
									inventoryDB.updateInventoryQty(validLocations.get(j), String.valueOf(0));
								}
							}
						}
					}
				}

			} else {

				System.out.println("Stock is not present in other locations");

				if (!(allocation)) {
					// default:stock adjustment to qty 500
					jdaLoginPage.login();
					jDAHomePage.navigateToStockAdjustment();
					Thread.sleep(2000);
					stockAdjustmentsPage.selectNewStock();
					jDAFooter.clickNextButton();
					Thread.sleep(2000);
					stockAdjustmentsPage.enterSkuId(context.getSkuId());
					jDAFooter.pressTab();
					//
					if (validLocations.size() != 0) {
						stockAdjustmentsPage.enterLocation(validLocations.get(0));
					} else {
						if (context.getSKUType().equalsIgnoreCase("B")) {
							stockAdjustmentsPage.enterLocation(locationDB.getToLocationForPutawayBoxed("BOX"));
						} else if (context.getSKUType().equalsIgnoreCase("C")
								|| context.getSKUType().equalsIgnoreCase("H")) {
							stockAdjustmentsPage.enterLocation(locationDB.getToLocationForPutaway("HANG",
									skuDB.getProductGroup(context.getSkuId())));
						} else if (context.getSKUType().equalsIgnoreCase("P")) {
							stockAdjustmentsPage.enterLocation(locationDB
									.getToLocationForPutawayFlatpack(skuDB.getProductGroup(context.getSkuId())));
						}
					}
					stockAdjustmentsPage.enterSiteId(context.getSiteID());
					stockAdjustmentsPage.enterQuantityOnHand("500");
					stockAdjustmentsPage.enterOrigin("NONE");

					stockAdjustmentsPage.enterPackConfig(skuSkuConfigDB.getPackConfigList(context.getSkuId()).get(0));
					jDAFooter.clickNextButton();
					stockAdjustmentsPage.enterPalletType("PALLET");
					jDAFooter.clickNextButton();
					stockAdjustmentsPage.enterReasonCode();
					jDAFooter.clickDoneButton();
					stockAdjustmentsPage.handlePopUp();
				}

			}

		}
		database.reconnectDB();

	}

	public void i_do_new_stock_check_at_location_with_quantity_and_supplier(String location, String qty,
			String supplier) throws Throwable {
		context.setLocationID(location);
		context.setQtyOrdered(Integer.parseInt(qty));
		context.setUPC(supplierSkuDb.getSupplierSKU(context.getSkuId(), supplier));
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		puttyFunctionsPage.nextScreen();
		selectInventoryMenu();
		selectNewStockCheck();
		Assert.assertTrue("Stock Check Menu not displayed as expected", isNewStockCheckEntPageDisplayed());

		i_do_new_stock_check_with_supplier(supplier);
		logoutPutty();
	}

	private void logoutPutty() throws InterruptedException, IOException {
		if (context.isPuttyLoginFlag() == true) {
			// context.getPuttyProcess().waitFor();
			while (screen.exists("/images/Putty/3Logout.png") == null) {
				screen.type(Key.F12);
			}
			screen.type("3");
			Thread.sleep(1000);
			screen.type(Key.ENTER);
			Thread.sleep(2000);
			Process p = Runtime.getRuntime().exec("cmd /c " + envVar + "\\bin\\puttykillAdmin.lnk");
			screen.type(Key.F4, Key.ALT);
			Thread.sleep(2000);
			screen.type(Key.ENTER);
			Thread.sleep(2000);
			context.setPuttyLoginFlag(false);
		} else {
			Process p = Runtime.getRuntime().exec("cmd /c " + envVar + "\\bin\\puttykillAdmin.lnk");
		}
	}

	public void i_do_new_stock_check_at_location_with_quantity(String location, String qty) throws Throwable {
		context.setLocationID(location);
		context.setQtyOrdered(Integer.parseInt(qty));
		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		puttyFunctionsPage.nextScreen();
		selectInventoryMenu();
		selectNewStockCheck();
		Assert.assertTrue("Stock Check Menu not displayed as expected", isNewStockCheckEntPageDisplayed());
		i_do_new_stock_check();
	}

	public void i_do_new_stock_check_with_supplier(String supplier) throws Throwable {
		i_enter_location(context.getLocationID());// valid location that should
													// nt be inventory
		context.setToLocation(context.getLocationID());

		jDAFooter.PressEnter();
		i_enter_no_or_yes("Y");
		jDAFooter.PressEnter();
		jDAFooter.pressTab();
		i_enter_upc(context.getUPC());
		jDAFooter.pressTab();
		i_enter_supplier(supplier);
		jDAFooter.pressTab();
		i_enter_quantity(String.valueOf(context.getQtyOrdered()));
		jDAFooter.PressEnter();
		if (isChkToDisplayed()) {
			i_enter_the_check_string();
		}
		jDAFooter.PressEnter();
		i_enter_no_or_yes("N");
		jDAFooter.PressEnter();

		Assert.assertTrue("Stock Check Menu not displayed as expected", isNewStockCheckEntPageDisplayed());
	}

	public boolean isChkToDisplayed() {
		if (screen.exists("images/Putty/Putaway/ChkTo.png") != null)
			return true;
		else
			return false;
	}

	public void i_enter_the_check_string() throws Throwable {
		Assert.assertTrue("Chk To Page not displayed to enter check string", isChkToDisplayed());
		enterCheckString(locationDB.getCheckString(context.getToLocation()));

	}

	public void enterCheckString(String chkString) throws InterruptedException {
		screen.type(chkString);
		Thread.sleep(2000);
	}

	public void selectInventoryMenu() throws FindFailed, InterruptedException {
		screen.type("5");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectNewStockCheck() throws FindFailed, InterruptedException {
		screen.type("2");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public boolean isNewStockCheckEntPageDisplayed() {
		if (screen.exists("images/Putty/StockCheck/NewStockCheck/StkNewEnt.png") != null)
			return true;
		else
			return false;
	}

	public void i_enter_location(String location) throws FindFailed, InterruptedException {
		screen.type(location);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void i_enter_no_or_yes(String asn) throws InterruptedException {
		screen.type(asn);
		Thread.sleep(2000);
	}

	public void i_enter_supplier(String supplier) throws InterruptedException {
		screen.type(supplier);
		Thread.sleep(2000);
	}

	public void i_enter_upc(String upc) throws InterruptedException {
		screen.type(upc);
		Thread.sleep(2000);
	}

	public void i_enter_quantity(String qty) throws InterruptedException {
		screen.type(qty);
		Thread.sleep(2000);
	}

	public void i_do_new_stock_check() throws Throwable {
		i_enter_location(context.getLocationID());
		if (isChkToDisplayed()) {
			i_enter_the_check_string();
		}
		i_enter_quantity(String.valueOf(context.getQtyOrdered()));
		jDAFooter.PressEnter();
		i_enter_quantity(String.valueOf(context.getQtyOrdered()));
		i_enter_no_or_yes("N");
		jDAFooter.PressEnter();
		Assert.assertTrue("Stock Check Menu not displayed as expected", isNewStockCheckEntPageDisplayed());
	}

}