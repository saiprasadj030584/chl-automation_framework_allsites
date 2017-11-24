package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.datasetup.gm.GetTcData;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.LocationDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.SkuSkuConfigDB;
import com.jda.wms.pages.gm.InventoryQueryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.OrderHeaderPage;
import com.jda.wms.pages.gm.StockAdjustmentsPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.stepdefs.rdt.PurchaseOrderStockCheckStepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.emory.mathcs.backport.java.util.Arrays;

public class OrderHeaderMaintenanceStepDefs {
	ArrayList<String> failureList = new ArrayList<String>();
	private Context context;
	private JDAFooter jDAFooter;
	private OrderHeaderDB orderHeaderDB;
	private JdaHomePage jDAHomePage;
	private Verification verification;
	private OrderHeaderPage orderHeaderPage;
	private JdaLoginPage jdaLoginPage;
	private OrderLineDB orderLineDB;
	private GetTcData getTcData;
	private InventoryDB inventoryDB;
	private JDALoginStepDefs jdaLoginStepDefs;
	private LocationDB locationDb;
	private SkuDB skuDB;
	private InventoryQueryPage inventoryQueryPage;
	private LocationDB locationDB;
	private StockAdjustmentsPage stockAdjustmentsPage;
	private SkuSkuConfigDB skuSkuConfigDB;
	private PurchaseOrderStockCheckStepDefs purchaseOrderStockCheckStepDefs;

	@Inject
	public OrderHeaderMaintenanceStepDefs(Context context, JDAFooter jDAFooter, JdaHomePage jDAHomePage,
			OrderHeaderDB orderHeaderDB, Verification verification, OrderHeaderPage orderHeaderPage,
			JdaLoginPage jdaLoginPage, OrderLineDB orderLineDB, GetTcData getTcData, InventoryDB inventoryDB,

			JDALoginStepDefs jdaLoginStepDefs, InventoryQueryPage inventoryQueryPage, LocationDB locationDb,
			SkuDB skuDB, LocationDB locationDB, StockAdjustmentsPage stockAdjustmentsPage,
			SkuSkuConfigDB skuSkuConfigDB,PurchaseOrderStockCheckStepDefs purchaseOrderStockCheckStepDefs) {

		this.context = context;
		this.jDAFooter = jDAFooter;
		this.jDAHomePage = jDAHomePage;
		this.orderHeaderDB = orderHeaderDB;
		this.verification = verification;
		this.orderHeaderPage = orderHeaderPage;
		this.jdaLoginPage = jdaLoginPage;
		this.orderLineDB = orderLineDB;
		this.getTcData = getTcData;
		this.inventoryDB = inventoryDB;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
		this.inventoryQueryPage = inventoryQueryPage;
		this.locationDb = locationDb;
		this.skuDB = skuDB;
		this.locationDB = locationDB;
		this.stockAdjustmentsPage = stockAdjustmentsPage;
		this.skuSkuConfigDB = skuSkuConfigDB;
		this.purchaseOrderStockCheckStepDefs = purchaseOrderStockCheckStepDefs ;
	}
}