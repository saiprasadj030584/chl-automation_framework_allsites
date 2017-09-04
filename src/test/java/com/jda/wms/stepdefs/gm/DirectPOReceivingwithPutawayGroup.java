
package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.InventoryTransactionDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.gm.InventoryQueryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.pages.rdt.PurchaseOrderPutawayPage;
import com.jda.wms.pages.rdt.PurchaseOrderReceivingPage;
import com.jda.wms.stepdefs.rdt.PurchaseOrderPutawayStepDefs;
import com.jda.wms.stepdefs.rdt.PurchaseOrderReceivingStepDefs;
import com.jda.wms.stepdefs.rdt.PuttyFunctionsStepDefs;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;
public final class DirectPOReceivingwithPutawayGroup {
	Map<Integer, Map<String, String>> poMap;
	Map<String, Map<String, String>> upiMap;
	private Context context;
	private Verification verification;
	private InventoryDB inventoryDB;
	private JdaLoginPage jdaLoginPage;
	private JDAFooter jDAFooter;
	private InventoryQueryPage inventoryQueryPage;
	private PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs;
	private PreAdviceLineStepDefs preAdviceLineStepDefs;
	private InventoryQueryStepDefs inventoryQueryStepDefs;
	private InventoryTransactionQueryStepDefs inventoryTransactionQueryStepDefs;
	private DeliveryStepDefs deliveryStepDefs;
	private UPIReceiptHeaderStepDefs upiReceiptHeaderStepDefs;
	private UPIReceiptLineStepDefs upiReceiptLineStepDefs;
	private PuttyFunctionsStepDefs puttyFunctionsStepDefs;
	private PurchaseOrderPutawayPage purchaseOrderPutawayPage;
	private Hooks hooks;
	private LocationDB locationDB;
	private InventoryTransactionDB inventoryTransactionDB;
	private PurchaseOrderPutawayStepDefs purchaseOrderPutawayStepDefs;
	private PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs;
	private JDAHomeStepDefs JDAHomeStepDefs;
	private StockAdjustmentStepDefs stockAdjustmentStepDefs;
	private PurchaseOrderReceivingPage purchaseOrderReceivingPage;
	Screen screen = new Screen();

	@Inject
	public DirectPOReceivingwithPutawayGroup(Context context, Verification verification,
			InventoryDB inventoryDB, JdaLoginPage jdaLoginPage, JDAFooter jDAFooter,
			InventoryQueryPage inventoryQueryPage, PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs,
			PreAdviceLineStepDefs preAdviceLineStepDefs, InventoryQueryStepDefs inventoryQueryStepDefs,
			InventoryTransactionQueryStepDefs inventoryTransactionQueryStepDefs, DeliveryStepDefs deliveryStepDefs,
			UPIReceiptHeaderStepDefs upiReceiptHeaderStepDefs, UPIReceiptLineStepDefs upiReceiptLineStepDefs,
			PuttyFunctionsStepDefs puttyFunctionsStepDefs, PurchaseOrderPutawayPage purchaseOrderPutawayPage,
			Hooks hooks, LocationDB locationDB, PurchaseOrderPutawayStepDefs purchaseOrderPutawayStepDefs,
			PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs, JDAHomeStepDefs JDAHomeStepDefs,
			StockAdjustmentStepDefs stockAdjustmentStepDefs, InventoryTransactionDB inventoryTransactionDB,PurchaseOrderReceivingPage purchaseOrderReceivingPage) {
		this.context = context;
		this.inventoryTransactionDB = inventoryTransactionDB;
		this.verification = verification;
		this.inventoryDB = inventoryDB;
		this.jdaLoginPage = jdaLoginPage;
		this.jDAFooter = jDAFooter;
		this.inventoryQueryPage = inventoryQueryPage;
		this.preAdviceHeaderStepsDefs = preAdviceHeaderStepsDefs;
		this.preAdviceLineStepDefs = preAdviceLineStepDefs;
		this.inventoryQueryStepDefs = inventoryQueryStepDefs;
		this.inventoryTransactionQueryStepDefs = inventoryTransactionQueryStepDefs;
		this.deliveryStepDefs = deliveryStepDefs;
		this.upiReceiptHeaderStepDefs = upiReceiptHeaderStepDefs;
		this.upiReceiptLineStepDefs = upiReceiptLineStepDefs;
		this.puttyFunctionsStepDefs = puttyFunctionsStepDefs;
		this.purchaseOrderReceivingPage = purchaseOrderReceivingPage;
		this.purchaseOrderPutawayPage = purchaseOrderPutawayPage;
		this.hooks = hooks;
		this.locationDB = locationDB;
		this.purchaseOrderPutawayStepDefs = purchaseOrderPutawayStepDefs;
		this.purchaseOrderReceivingStepDefs = purchaseOrderReceivingStepDefs;
		this.JDAHomeStepDefs = JDAHomeStepDefs;
		this.stockAdjustmentStepDefs = stockAdjustmentStepDefs;

}
	@When("^I receive all skus having different putaway group for the purchase order at location \"([^\"]*)\"$")
	public void i_receive_all_skus_having_differnt_putaway_group_for_the_purchase_order_at_location(String location) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		context.setLocation(location);
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();

		puttyFunctionsStepDefs.i_have_logged_in_as_warehouse_user_in_putty();
		puttyFunctionsStepDefs.i_select_user_directed_option_in_main_menu();
		purchaseOrderReceivingStepDefs.i_receive_the_po_with_basic_and_pre_advice_receiving();
		purchaseOrderReceivingStepDefs.i_should_be_directed_to_pre_advice_entry_page();
		purchaseOrderReceivingStepDefs.i_enter_urn_id();
		jDAFooter.PressEnter();
        System.out.println("Line"+context.getLineItem()+"No:"+context.getNoOfLines());
		for (int i = context.getLineItem(); i <= context.getNoOfLines(); i++) {
			context.setSkuId(poMap.get(i).get("SKU"));
			System.out.println("sku id: "+context.getSkuId() +"and" +context.getLockCode());
			context.setPackConfig(upiMap.get(context.getSkuId()).get("PACK CONFIG"));
			context.setRcvQtyDue(Integer.parseInt(upiMap.get(context.getSkuId()).get("QTY DUE")));
			if (null == context.getLockCode()) {
				if(purchaseOrderReceivingPage.isRCVLinEnt())
				{
				i_enter_upc_id();
				jDAFooter.PressEnter();
				}
				enter_tag_and_verify_upc_details_should_be_displayed();
				jDAFooter.pressTab();
				jDAFooter.pressTab();
				jDAFooter.pressTab();
				jDAFooter.pressTab();
				purchaseOrderReceivingStepDefs.i_enter_the_location();
				jDAFooter.PressEnter();
				if(purchaseOrderReceivingPage.isLabelPrintedPageDisplayed()){
					jDAFooter.PressEnter();
				}
				else{
				Assert.assertTrue("Rcv Pallet Entry Page not displayed",
						purchaseOrderReceivingPage.isRcvPalletEntPageDisplayed());
				if(!purchaseOrderReceivingPage.getPallet().split("_").equals(null)){
				String urn = null;
				String[] rcvPutSplit = purchaseOrderReceivingPage.getPallet().split("_");

				if (rcvPutSplit[0].contains("MEZF")) {
					urn = "M2Z01" + Utilities.getSevenDigitRandomNumber();
				}
				else{
					urn = rcvPutSplit[0].substring(1, 2) + Utilities.getSevenDigitRandomNumber();
				}
			
				purchaseOrderReceivingPage.enterURNID(urn);
				jDAFooter.PressEnter();
				context.setPalletID(urn);
				}
				else {
					purchaseOrderReceivingStepDefs.i_enter_urn_id();
					jDAFooter.PressEnter();
					Thread.sleep(2000);
				}

				if (!purchaseOrderReceivingPage.isPreAdviceEntryDisplayed()) {
					failureList.add("Receive not completed and Home page not displayed for URN " + context.getUpiId());
					context.setFailureList(failureList);
				}
			}
		
		}}
			hooks.logoutPutty();
	}
	private void i_enter_upc_id() throws FindFailed, InterruptedException {
		purchaseOrderReceivingPage.enterUPC(context.getSkuId());
	}
	
	@When("^enter tag and verify upc details should be displayed$")
	public void enter_tag_and_verify_upc_details_should_be_displayed() throws FindFailed, InterruptedException {
		ArrayList failureList = new ArrayList();
		Assert.assertTrue("RcvPreCmp page not displayed to enter Location",
				purchaseOrderReceivingPage.isLocationDisplayed());
		jDAFooter.pressTab();
		purchaseOrderReceivingStepDefs.i_enter_tag_id();
		String[] packConfigSplit = purchaseOrderReceivingPage.getPackConfig().split("_");
		String packConfig = packConfigSplit[0];
		verification.verifyData("Pack Config", context.getPackConfig(), packConfig, failureList);

		verification.verifyData("Supplier", context.getSupplierID(), purchaseOrderReceivingPage.getSupplierId(),
				failureList);

		String[] qtySplit = purchaseOrderReceivingPage.getQtyToReceive().split("_");
		String qtyToRcv = qtySplit[0];
		verification.verifyData("Qty to Receive", String.valueOf(context.getRcvQtyDue()), qtyToRcv, failureList);

		String[] upcSplit = purchaseOrderReceivingPage.getUPC().split("_");
		String upc = upcSplit[0];
		context.setUPC(upc);
		Assert.assertTrue(
				"Tag and UPC details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	
}
