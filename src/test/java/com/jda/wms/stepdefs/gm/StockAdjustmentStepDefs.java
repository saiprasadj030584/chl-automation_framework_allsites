package com.jda.wms.stepdefs.gm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.InventoryTransactionDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.pages.gm.InventoryTransactionQueryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.PopUpPage;
import com.jda.wms.pages.gm.StockAdjustmentsPage;
import com.jda.wms.pages.gm.Verification;
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
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private OrderLineDB orderLineDB;
	private InventoryDB inventoryDB;

	@Inject
	public StockAdjustmentStepDefs(Context context, JDAFooter jDAFooter, StockAdjustmentsPage stockAdjustmentsPage,
			PopUpPage popUpPage, JdaHomePage jDAHomePage, InventoryTransactionDB inventoryTransactionDB,
			Verification verification, InventoryTransactionQueryPage inventoryTransactionQueryPage,OrderLineDB orderLineDB,InventoryDB inventoryDB) {
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
	}

	@When("^I create a new stock with siteid and location \"([^\"]*)\"$")
	public void i_create_a_new_stock_with_siteid_and_location(String location)
			throws FindFailed, InterruptedException {
		String siteId = context.getSiteId();
		if (siteId.equals("5649")){
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
		//stockAdjustmentsPage.enterPackConfig(context.getPackConfig());
		jDAFooter.clickNextButton();
		stockAdjustmentsPage.enterPallet(pallet);
		jDAFooter.clickNextButton();
		}
		else if (siteId.equals("5885")){
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
			reasonCodeToChoose = "Dirty";
			break;
		case "DMIT":
			reasonCodeToChoose = "Damaged in Transit - for the 'damaged pallet'";
			break;
		case "EXPD":
			reasonCodeToChoose = "Expired";
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
		context.setReasonCode(reasonCode);
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		//context.setTagId(inventoryTransactionDB.getTagID(context.getUpiId(), "Adjustment", date));
		
//		context.setTagId(inventoryTransactionDB.getTagIDWithQty(String.valueOf(context.getQtyOnHand()), "Adjustment",context.getSkuId(), date));
//		System.out.println(context.getTagId());
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
				stockAdjustmentsPage.enterSiteId(context.getSiteId());
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
//		String code = "Putaway";
//	String quantity = inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getUpiId(),DateUtils.getCurrentSystemDateInDBFormat(),code);
//		context.setQtyOnHand(Integer.parseInt(quantity));
//		stockAdjustmentsPage.selectExistingStock();
//		jDAFooter.clickNextButton();
//		Thread.sleep(2000);
//		stockAdjustmentsPage.enterSiteIdExisting(siteId);
//		jDAFooter.pressTab();
//		stockAdjustmentsPage.enterSkuIDExisting(context.getSkuId());
//		jDAFooter.pressTab();
//		jDAFooter.pressTab();
//		jDAFooter.pressTab();
//		stockAdjustmentsPage.enterLocation(toLocation);
//		jDAFooter.clickNextButton();
//		jDAFooter.clickNextButton();
//		String quantityInv= inventoryDB.getQtyOnHand(context.getSkuId(),toLocation);
//		int quantityAdj = Integer.parseInt(quantityInv) - Integer.parseInt(quantity);
//		stockAdjustmentsPage.enterQuantityOnHand(quantityAdj);
//		jDAFooter.clickNextButton();
		
		String code = "Putaway";
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		String date = DateUtils.getCurrentSystemDateInDBFormat();
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
		context.setSkuId(poMap.get(i).get("SKU"));
		context.setRcvQtyDue(Integer.parseInt(poMap.get(i).get("QTY DUE")));
		context.setTagId(
				inventoryTransactionDB.getTagID(context.getPreAdviceId(), "Receipt", context.getSkuId(), date));
	String quantity = inventoryTransactionDB.getUpdateQty(context.getSkuId(), context.getTagId(),DateUtils.getCurrentSystemDateInDBFormat(),code);
		context.setQtyOnHand(Integer.parseInt(quantity));
		String quantityInv= inventoryDB.getQtynHand(context.getSkuId(),toLocation);
		System.out.println("qty inv"+quantityInv);
		System.out.println("qty"+quantity);
		
		int quantityAdj = Integer.parseInt(quantityInv) - Integer.parseInt(quantity);
		System.out.println("quantityAdj"+quantityAdj);
		context.setUpdatedQty(quantityAdj-Integer.parseInt(quantityInv));
		System.out.println("uppp"+context.getUpdatedQty());
		stockAdjustmentsPage.selectExistingStock();
		Thread.sleep(2000);
		stockAdjustmentsPage.enterSiteIdExisting(siteId);
		jDAFooter.pressTab();
		stockAdjustmentsPage.enterSkuIDExisting(context.getSkuId());
		jDAFooter.pressTab();
		jDAFooter.pressTab();
		jDAFooter.pressTab();

		stockAdjustmentsPage.enterLocation(toLocation);
		
		System.out.println("qty ij inv"+quantityInv);
		jDAFooter.pressTab();
		context.setQtyOnHand(Integer.parseInt(quantityInv));
		//context.setUpdatedQty(Integer.parseInt(quantityInv)-quantityAdj);
		System.out.println(context.getQtyOnHand()+" and "+ context.getUpdatedQty());
		//stockAdjustmentsPage.enterQuantityOnHand(String.valueOf(context.getUpdatedQty()));
		stockAdjustmentsPage.enterQuantityOnHand(quantityInv);
		jDAFooter.clickNextButton();
		jDAFooter.clickNextButton();
		stockAdjustmentsPage.enterQuantityOnHand(quantityAdj);
		jDAFooter.clickNextButton();
	}

	}
}