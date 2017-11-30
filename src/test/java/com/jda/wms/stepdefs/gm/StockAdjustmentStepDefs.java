package com.jda.wms.stepdefs.gm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.InventoryTransactionDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.SkuSkuConfigDB;
import com.jda.wms.db.gm.SupplierSkuDB;
import com.jda.wms.pages.gm.InventoryTransactionQueryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.PopUpPage;
import com.jda.wms.pages.gm.StockAdjustmentsPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderStockCheckPage;
import com.jda.wms.utils.DateUtils;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

public class StockAdjustmentStepDefs {
	Map<Integer, Map<String, String>> poMap;
	Map<String, Map<String, String>> upiMap;
	private Context context;
	private JDAFooter jDAFooter;
	private Verification verification;
	private StockAdjustmentsPage stockAdjustmentsPage;
	private InventoryTransactionQueryPage inventoryTransactionQueryPage;
	private InventoryTransactionDB inventoryTransactionDB;
	private PopUpPage popUpPage;
	private JdaHomePage jDAHomePage;
	private LocationDB locationDB;
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private OrderLineDB orderLineDB;
	private InventoryDB inventoryDB;
	private SkuDB skuDB;
	private PurchaseOrderStockCheckPage purchaseOrderStockCheckPage;
	private SupplierSkuDB supplierSkuDb;
	private SkuSkuConfigDB skuSkuConfigDB;

	@Inject
	public StockAdjustmentStepDefs(Context context, JDAFooter jDAFooter, StockAdjustmentsPage stockAdjustmentsPage,
			PopUpPage popUpPage, JdaHomePage jDAHomePage, InventoryTransactionDB inventoryTransactionDB,
			Verification verification, InventoryTransactionQueryPage inventoryTransactionQueryPage,
			OrderLineDB orderLineDB, InventoryDB inventoryDB, LocationDB locationDB, SkuDB skuDB,
			PurchaseOrderStockCheckPage purchaseOrderStockCheckPage, SupplierSkuDB supplierSkuDb,
			SkuSkuConfigDB skuSkuConfigDB) {
		this.context = context;
		this.jDAFooter = jDAFooter;
		this.stockAdjustmentsPage = stockAdjustmentsPage;
		this.popUpPage = popUpPage;
		this.jDAHomePage = jDAHomePage;
		this.inventoryTransactionDB = inventoryTransactionDB;
		this.verification = verification;
		this.inventoryTransactionQueryPage = inventoryTransactionQueryPage;
		this.orderLineDB = orderLineDB;
		this.inventoryDB = inventoryDB;
		this.locationDB = locationDB;
		this.skuDB = skuDB;
		this.purchaseOrderStockCheckPage = purchaseOrderStockCheckPage;
		this.supplierSkuDb = supplierSkuDb;
		this.skuSkuConfigDB = skuSkuConfigDB;
	}

	@When("^I create a new stock with siteid and location \"([^\"]*)\"$")
	public void i_create_a_new_stock_with_siteid_and_location(String location) throws FindFailed, InterruptedException {

		String siteId = context.getSiteID();
		System.out.println("print" + siteId);
		if (siteId.equals("5649")) {
			String owner = "M+S";
			String clientid = "M+S";
			String quantity = Utilities.getTwoDigitRandomNumber();
			context.setQtyOnHand(Integer.parseInt(quantity));
			String pallet = "PALLET";

			stockAdjustmentsPage.selectNewStock();
			jDAFooter.clickNextButton();
			Thread.sleep(2000);
			stockAdjustmentsPage.enterSkuId(context.getSkuId());
			jDAFooter.pressTab();
			stockAdjustmentsPage.enterLocation(location);
			stockAdjustmentsPage.enterOwnerId(owner);
			stockAdjustmentsPage.enterClientId(clientid);
			stockAdjustmentsPage.enterSiteId(siteId);
			stockAdjustmentsPage.enterQuantityOnHand(quantity);
			stockAdjustmentsPage.enterPackConfig(context.getPackConfig());
			jDAFooter.clickNextButton();
			stockAdjustmentsPage.enterPallet(pallet);
			jDAFooter.clickNextButton();
		} else if (siteId.equals("5885")) {

			context.setQtyOnHand(context.getRcvQtyDue());

			stockAdjustmentsPage.selectNewStock();
			jDAFooter.clickNextButton();
			Thread.sleep(2000);
			stockAdjustmentsPage.enterSkuId(context.getSkuId());
			jDAFooter.pressTab();
			stockAdjustmentsPage.enterLocation(location);
			stockAdjustmentsPage.enterSiteId(siteId);
			stockAdjustmentsPage.enterQuantityOnHand(String.valueOf(context.getRcvQtyDue()));
			jDAFooter.clickNextButton();
			stockAdjustmentsPage.enterContainerId(context.getUpiId());
			stockAdjustmentsPage.enterPalletId(context.getUpiId());
			stockAdjustmentsPage.enterPalletType("PALLET");
			jDAFooter.clickNextButton();
		}
	}

	@When("^I create a new stock with siteid \"([^\"]*)\" and location \"([^\"]*)\"$")
	public void i_create_a_new_stock_with_siteid_and_location(String siteID, String location)
			throws FindFailed, InterruptedException {
		context.setSiteID(siteID);
		String siteId = context.getSiteID();
		if (siteId.equals("5649")) {
			String owner = "M+S";
			String clientid = "M+S";
			String quantity = Utilities.getTwoDigitRandomNumber();
			context.setQtyOnHand(Integer.parseInt(quantity));
			String pallet = "PALLET";
			stockAdjustmentsPage.selectNewStock();
			jDAFooter.clickNextButton();
			Thread.sleep(2000);
			stockAdjustmentsPage.enterSkuId(context.getSkuId());
			jDAFooter.pressTab();
			stockAdjustmentsPage.enterLocation(location);
			stockAdjustmentsPage.enterOwnerId(owner);
			stockAdjustmentsPage.enterClientId(clientid);
			stockAdjustmentsPage.enterSiteId(siteId);
			stockAdjustmentsPage.enterQuantityOnHand(quantity);
			stockAdjustmentsPage.enterPackConfig(context.getPackConfig());
			jDAFooter.clickNextButton();
			stockAdjustmentsPage.enterPallet(pallet);
			jDAFooter.clickNextButton();
		} else if (siteId.equals("5885")) {
			context.setQtyOnHand(context.getRcvQtyDue());

			stockAdjustmentsPage.selectNewStock();
			jDAFooter.clickNextButton();
			Thread.sleep(2000);
			stockAdjustmentsPage.enterSkuId(context.getSkuId());
			jDAFooter.pressTab();
			stockAdjustmentsPage.enterLocation(location);
			stockAdjustmentsPage.enterSiteId(siteId);
			stockAdjustmentsPage.enterQuantityOnHand(String.valueOf(context.getRcvQtyDue()));
			jDAFooter.clickNextButton();
			stockAdjustmentsPage.enterContainerId(context.getUpiId());
			stockAdjustmentsPage.enterPalletId(context.getUpiId());
			stockAdjustmentsPage.enterPalletType("PALLET");
			jDAFooter.clickNextButton();
		}
	}

	@When("^I choose the reason code as \"([^\"]*)\"$")
	public void I_choose_the_reason_code_as(String reasonCode) throws Throwable {
		String reasonCodeToChoose = null;
		switch (reasonCode) {

		case "Dirty":
			reasonCodeToChoose = "DIRTY";
			break;
		case "DMIT":
			reasonCodeToChoose = "DMIT";
			break;
		case "EXPD":
			reasonCodeToChoose = "EXPD";
			break;
		case "FOUND":
			reasonCodeToChoose = "FOUND";
			break;
		case "INCOMPLETE":
			reasonCodeToChoose = "INCOMPLETE";
			break;
		case "LOST":
			reasonCodeToChoose = "LOST";
			break;
		case "SAMPLES":
			reasonCodeToChoose = "SAMPLES";
			break;
		case "SC":
			reasonCodeToChoose = "Stock Count";
			break;
		case "Receiving Correction":
			reasonCodeToChoose = "Receiving Correction";
			break;
		case "RMS - Unexpected receipt with movement label":
			reasonCodeToChoose = "RMS - Unexpected receipt with movement label";
			break;
		case "RMS - Unexpected receipt without movement label":
			reasonCodeToChoose = "RMS - Unexpected receipt without movement label";
			break;
		case "RMS  Non advised receipt with movement label":
			reasonCodeToChoose = "RMS  Non advised receipt with movement label";
			break;
		case "RMS - Non advised receipt without movement label":
			reasonCodeToChoose = "RMS - Non advised receipt without movement label";
			break;
		}
		Thread.sleep(2000);
		stockAdjustmentsPage.chooseReasonCode(reasonCodeToChoose);
		jDAFooter.clickDoneButton();
		jDAFooter.PressEnter();
		jDAFooter.PressEnter();

		context.setReasonCode(reasonCodeToChoose);
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		// context.setTagId(inventoryTransactionDB.getTagID(context.getUpiId(),
		// "Adjustment", date));
		System.out.println("Reason Code" + reasonCodeToChoose);
		if (reasonCodeToChoose.equalsIgnoreCase("Stock Count")) {
			context.setReasonCode("SC");
		}
	}

	@When("^I change on hand qty and reason code to \"([^\"]*)\"$")
	public void i_change_on_hand_qty_and_raeson_code(String reasonCode)
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		jDAFooter.clickNextButton();
		Thread.sleep(2000);
		stockAdjustmentsPage.enterSku(context.getSkuId());
		jDAFooter.pressTab();
		stockAdjustmentsPage.clickMiscellaneousTab();
		stockAdjustmentsPage.enterReceiptId(context.getUpiId());
		jDAFooter.pressTab();
		jDAFooter.clickNextButton();
		jDAFooter.clickNextButton();
		context.setQtyOnHand(context.getRcvQtyDue() + 5);
		jDAFooter.deleteExistingContent();
		stockAdjustmentsPage.enterQuantityOnHand(String.valueOf(context.getRcvQtyDue() + 5));
		jDAFooter.clickNextButton();
		context.setReasonCode(reasonCode);
		stockAdjustmentsPage.enterReasonCode(reasonCode);
		jDAFooter.clickDoneButton();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		context.setTagId(inventoryTransactionDB.getTagID(context.getUpiId(), "Adjustment", date));
	}

	@And("^I have inventory available for the order line items$")
	public void i_have_inventory_available_for_the_order_line_items() throws Throwable {
		ArrayList skuList = orderLineDB.getskuList(context.getOrderId());
		for (int s = 0; s < skuList.size(); s++) {
			String countrecordforSku = inventoryDB.getStockAvailablityRecords(context.getSkuId());
			int totalrecordforSku = Integer.parseInt(countrecordforSku);
			if (totalrecordforSku == 0) {
				String packConfig = inventoryDB.getPackConfig(context.getSkuId());
				String country = "DENMARK";
				String quantity = Utilities.getTwoDigitRandomNumber();
				context.setQtyOnHand(Integer.parseInt(quantity));
				context.setCountry(country);
				String palletType = "SRP";
				jDAHomePage.navigateToStockAdjustment();
				Thread.sleep(2000);
				stockAdjustmentsPage.selectNewStock();
				jDAFooter.clickNextButton();
				Thread.sleep(2000);
				stockAdjustmentsPage.enterSkuId(context.getSkuId());
				jDAFooter.pressTab();
				stockAdjustmentsPage.enterLocation(context.getLocation());
				stockAdjustmentsPage.enterSiteId(context.getSiteID());
				stockAdjustmentsPage.enterOrigin(context.getCountry());
				stockAdjustmentsPage.enterQuantityOnHand(quantity);
				stockAdjustmentsPage.enterPackConfig(packConfig);
				jDAFooter.clickNextButton();
				stockAdjustmentsPage.enterPalletType(palletType);
				jDAFooter.clickNextButton();
				stockAdjustmentsPage.enterReasonCode();
				jDAFooter.clickDoneButton();
				stockAdjustmentsPage.handlePopUp();
			}
		}
	}

	public void i_select_a_existing_stock_with_siteid_location_and_tag_id(String siteId, String toLocation,
			String tagId) throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		// String code = "Putaway";
		// String quantity =
		// inventoryTransactionDB.getUpdateQty(context.getSkuId(),
		// context.getUpiId(),DateUtils.getCurrentSystemDateInDBFormat(),code);
		// context.setQtyOnHand(Integer.parseInt(quantity));
		// stockAdjustmentsPage.selectExistingStock();
		// jDAFooter.clickNextButton();
		// Thread.sleep(2000);
		// stockAdjustmentsPage.enterSiteIdExisting(siteId);
		// jDAFooter.pressTab();
		// stockAdjustmentsPage.enterSkuIDExisting(context.getSkuId());
		// jDAFooter.pressTab();
		// jDAFooter.pressTab();
		// jDAFooter.pressTab();
		// stockAdjustmentsPage.enterLocation(toLocation);
		// jDAFooter.clickNextButton();
		// jDAFooter.clickNextButton();
		// String quantityInv=
		// inventoryDB.getQtyOnHand(context.getSkuId(),toLocation);
		// int quantityAdj = Integer.parseInt(quantityInv) -
		// Integer.parseInt(quantity);
		// stockAdjustmentsPage.enterQuantityOnHand(quantityAdj);
		// jDAFooter.clickNextButton();

		String code = "Putaway";

		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			context.setRcvQtyDue(Integer.parseInt(poMap.get(i).get("QTY DUE")));
			context.setTagId(
					inventoryTransactionDB.getTagID(context.getPreAdviceId(), "Receipt", context.getSkuId(), date));
			String quantity = inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getTagId(),
					DateUtils.getCurrentSystemDateInDBFormat(), code);

			context.setQtyOnHand(Integer.parseInt(quantity));
			String quantityInv = inventoryDB.getQtynHand(context.getSkuId(), toLocation);
			System.out.println("qty inv" + quantityInv);
			System.out.println("qty" + quantity);

			int quantityAdj = Integer.parseInt(quantityInv) - Integer.parseInt(quantity);
			System.out.println("quantityAdj" + quantityAdj);
			context.setUpdatedQty(quantityAdj - Integer.parseInt(quantityInv));
			System.out.println("uppp" + context.getUpdatedQty());
			stockAdjustmentsPage.selectExistingStock();
			Thread.sleep(2000);
			stockAdjustmentsPage.enterSiteIdExisting(siteId);
			jDAFooter.pressTab();
			stockAdjustmentsPage.enterSkuIDExisting(context.getSkuId());
			jDAFooter.pressTab();
			jDAFooter.pressTab();
			jDAFooter.pressTab();

			stockAdjustmentsPage.enterLocation(toLocation);

			System.out.println("qty ij inv" + quantityInv);
			jDAFooter.pressTab();
			context.setQtyOnHand(Integer.parseInt(quantityInv));
			// context.setUpdatedQty(Integer.parseInt(quantityInv)-quantityAdj);
			System.out.println(context.getQtyOnHand() + " and " + context.getUpdatedQty());
			// stockAdjustmentsPage.enterQuantityOnHand(String.valueOf(context.getUpdatedQty()));
			stockAdjustmentsPage.enterQuantityOnHand(quantityInv);
			jDAFooter.clickNextButton();
			jDAFooter.clickNextButton();
			stockAdjustmentsPage.enterQuantityOnHand(quantityAdj);
			jDAFooter.clickNextButton();
		}

	}

	@And("^I enter SkuId for existing stock at siteId$")
	public void I_enter_SkuId_for_existing_stock_at_siteId()
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		// String date = DateUtils.getCurrentSystemDateInDBFormat();
		// context.setTagId(
		// inventoryTransactionDB.getTagID(context.getPreAdviceId(), "Receipt",
		// context.getSkuId(), date));
		System.out.println("site id" + context.getSiteID());
		// context.setSiteId(context.getSiteId());
		jDAFooter.clickNextButton();

		stockAdjustmentsPage.enterTagId(context.getTagId());
		jDAFooter.pressTab();
		stockAdjustmentsPage.enterSiteIdForStock(context.getSiteID());
		jDAFooter.pressTab();
		stockAdjustmentsPage.enterSkuId(context.getSkuId());
		jDAFooter.clickNextButton();
		jDAFooter.clickNextButton();
		String qtyonhandbeforeadjustment = String.valueOf(context.getRcvQtyDue());
		// String
		// qtyonhandbeforeadjustment=inventoryDB.getQtyOnHandForSKU(context.getSkuId());
		context.setqtyOnHandBeforeAdjustment(Integer.parseInt(qtyonhandbeforeadjustment));
		String decrementQty = Integer.toString(context.getQtyOnHandBeforeAdjustment() - 1);
		stockAdjustmentsPage.updateQtyOnHand(decrementQty);
		context.setQtyonhandafteradjustment(Integer.parseInt(decrementQty));
		jDAFooter.clickNextButton();
		// stockAdjustmentsPage.enterReasonCode();
		// jDAFooter.clickDoneButton();
	}

	public void i_maintain_stock_in_inventory(ArrayList<String> skuList, String uniqueTag) throws Throwable {
		boolean allocation = false;
		for (int i = 0; i < context.getSkuList().size(); i++) {
			context.setSkuId((String) context.getSkuList().get(i));
			ArrayList<String> locationList = inventoryDB.getLocationsForSku(context.getSkuId());
			System.out.println(locationList);
			ArrayList<String> validLocations = new ArrayList<String>();
			int totalQtyOnHand = 0;
			for (int j = 0; j < locationList.size(); j++) {
				context.setLocation(locationList.get(j));

				if (locationDB.getLocationZone(context.getLocation()) != null) {
					if (context.getSKUType().equalsIgnoreCase("Hanging")) {
						if (locationDB.getLocationZone(context.getLocation()).equalsIgnoreCase("HANG")
								&& locationDB.getUserDefType2(context.getLocation()).contains("HANG")
								&& locationDB.getUserDefType3(context.getLocation()).contains("HANG")
								&& locationDB.getUserDefType1(context.getLocation())
										.contains(skuDB.getProductGroup(context.getSkuId()))) {
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

					else if (context.getSKUType().equalsIgnoreCase("GOH")) {
						System.out.println("Entered GOH");
						if (locationDB.getLocationZone(context.getLocation()).equalsIgnoreCase("HANG")
								&& locationDB.getUserDefType2(context.getLocation()).contains("HANG")
								&& locationDB.getUserDefType3(context.getLocation()).contains("HANG")) {
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

					else if (context.getSKUType().equalsIgnoreCase("Boxed")) {
						if (locationDB.getLocationZone(context.getLocation()).contains("BOX")
								&& locationDB.getUserDefType2(context.getLocation()).contains("BOX")
								&& locationDB.getUserDefType3(context.getLocation()).contains("BOX")) {
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

					else if (context.getSKUType().equalsIgnoreCase("Flatpack")) {
						if ((locationDB.getLocationZone(context.getLocation()).contains("BOX")
								&& locationDB.getUserDefType2(context.getLocation()).contains("BOX")
								&& locationDB.getUserDefType3(context.getLocation()).contains("FLAT"))
								|| (locationDB.getLocationZone(context.getLocation()).contains("HANG")
										&& locationDB.getUserDefType2(context.getLocation()).contains("HANG")
										&& locationDB.getUserDefType3(context.getLocation()).contains("FLAT")
										&& locationDB.getUserDefType1(context.getLocation())
												.contains(skuDB.getProductGroup(context.getSkuId())))) {
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

					if (!inventoryDB.isSkuInSuspenseLocation(context.getSkuId())) {
						System.out.println("Sku not in suspense location " + context.getSkuId());
						// do stock check
						System.out.println("SUSPENSE" + validLocations.get(0));
						System.out.println(inventoryDB.getQtynHand(context.getSkuId(), validLocations.get(0)));
						purchaseOrderStockCheckPage.i_do_new_stock_check_at_location_with_quantity(
								validLocations.get(0), String.valueOf(Integer
										.valueOf(inventoryDB.getQtynHand(context.getSkuId(), validLocations.get(0)))));

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
								// purchaseOrderStockCheckPage.i_do_new_stock_check_at_location_with_quantity(validLocations.get(j),inventoryDB.getQtynHand((String)
								// skuFromOrder.get(i),validLocations.get(j)));
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

						if (context.getSKUType().equalsIgnoreCase("Boxed")) {
							purchaseOrderStockCheckPage.i_do_new_stock_check_at_location_with_quantity_and_supplier(
									locationDB.getToLocationForPutawayBoxed("BOX"), "100",
									supplierSkuDb.getProhibitedSupplier(context.getSkuId()));
						} else if (context.getSKUType().equalsIgnoreCase("GOH")
								|| context.getSKUType().equalsIgnoreCase("Hanging")) {
							purchaseOrderStockCheckPage.i_do_new_stock_check_at_location_with_quantity_and_supplier(
									locationDB.getToLocationForPutaway("HANG",
											skuDB.getProductGroup(context.getSkuId())),
									"100", supplierSkuDb.getProhibitedSupplier(context.getSkuId()));
						} else if (context.getSKUType().equalsIgnoreCase("Flatpack")) {
							purchaseOrderStockCheckPage.i_do_new_stock_check_at_location_with_quantity_and_supplier(
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
								if (!(inventoryDB.getOriginId(validLocations.get(j)).equalsIgnoreCase("TUR"))) {
									System.out.println("SUSPENSE" + validLocations.get(j));
									System.out.println(
											inventoryDB.getQtynHand(context.getSkuId(), validLocations.get(j)));
									inventoryDB.updateInventoryQty(validLocations.get(j), String.valueOf(0));
								}
							}
						}
					} //
				}

			} else {

				// Assert.assertTrue("Stock is not present in other locations" ,
				// allocation);

				System.out.println("Stock is not present in other locations");

				if (!(allocation)) {
					// default:stock adjustment to qty 500
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
						if (context.getSKUType().equalsIgnoreCase("Boxed")) {
							stockAdjustmentsPage.enterLocation(locationDB.getToLocationForPutawayBoxed("BOX"));
						} else if (context.getSKUType().equalsIgnoreCase("GOH")
								|| context.getSKUType().equalsIgnoreCase("Hanging")) {
							stockAdjustmentsPage.enterLocation(locationDB.getToLocationForPutaway("HANG",
									skuDB.getProductGroup(context.getSkuId())));
						} else if (context.getSKUType().equalsIgnoreCase("Flatpack")) {
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

	}
}