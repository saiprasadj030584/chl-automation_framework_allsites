package com.jda.wms.stepdefs.gm;

import java.sql.SQLException;

import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryTransactionDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.PopUpPage;
import com.jda.wms.pages.gm.StockAdjustmentsPage;
import com.jda.wms.utils.DateUtils;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.When;

public class StockAdjustmentStepDefs {
	private Context context;
	private JDAFooter jDAFooter;
	private StockAdjustmentsPage stockAdjustmentsPage;
	private InventoryTransactionDB inventoryTransactionDB;
	private PopUpPage popUpPage;
	private JdaHomePage jDAHomePage;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public StockAdjustmentStepDefs(Context context, JDAFooter jDAFooter, StockAdjustmentsPage stockAdjustmentsPage,
			PopUpPage popUpPage, JdaHomePage jDAHomePage,InventoryTransactionDB inventoryTransactionDB) {
		this.context = context;
		this.jDAFooter = jDAFooter;
		this.stockAdjustmentsPage = stockAdjustmentsPage;
		this.popUpPage = popUpPage;
		this.jDAHomePage = jDAHomePage;
		this.inventoryTransactionDB=inventoryTransactionDB;
	}

	@When("^I create a new stock with siteid \"([^\"]*)\" and location \"([^\"]*)\"$")
	public void i_create_a_new_stock_with_siteid_and_location(String siteId, String location)
			throws FindFailed, InterruptedException {
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
		}

		stockAdjustmentsPage.chooseReasonCode(reasonCodeToChoose);
		jDAFooter.clickDoneButton();
		jDAFooter.PressEnter();
		jDAFooter.PressEnter();
		context.setReasonCode(reasonCode);
	}
	
	
	
			@When("^the inventory is unlocked and the return stock is over received$")
			public void the_inventory_is_unlocked_and_the_return_stock_is_over_received()
					throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
				jDAHomePage.navigateToStockAdjustment();
				jDAFooter.clickNextButton();
				Thread.sleep(2000);
				stockAdjustmentsPage.enterSkuId(context.getSkuId());
				jDAFooter.pressTab();
				stockAdjustmentsPage.clickMiscellaneousTab();
				stockAdjustmentsPage.enterReceiptId(context.getUpiId());
				jDAFooter.pressTab();
				jDAFooter.clickNextButton();
				jDAFooter.clickNextButton();
				String date = DateUtils.getCurrentSystemDateInDBFormat();
				Assert.assertEquals("Quantity not over received",String.valueOf(context.getQtyOnHand()),inventoryTransactionDB.getUpdateQtyUnlocked(context.getSkuId(),context.getUpiId(),date));
				
	
	}
			
	@When("^I change on hand qty and reason code$")
	public void i_change_on_hand_qty_and_raeson_code()
			throws FindFailed, InterruptedException {
		
//		String quantity = Utilities.getTwoDigitRandomNumber();
//		context.setQtyOnHand(Integer.parseInt(quantity));
		//String pallet = "PALLET";

		jDAFooter.clickNextButton();
		Thread.sleep(2000);
		stockAdjustmentsPage.enterSkuId(context.getSkuId());
		jDAFooter.pressTab();
		stockAdjustmentsPage.clickMiscellaneousTab();
		stockAdjustmentsPage.enterReceiptId(context.getUpiId());
		jDAFooter.pressTab();
		jDAFooter.clickNextButton();
		jDAFooter.clickNextButton();
		context.setQtyOnHand(context.getRcvQtyDue()+5);
		stockAdjustmentsPage.enterQuantityOnHand(String.valueOf(context.getRcvQtyDue()+5));
		jDAFooter.clickNextButton();
		stockAdjustmentsPage.enterReasonCode("RMS - Over receipt against HU with movement label");
		jDAFooter.clickExecuteButton();
		
	}

}
