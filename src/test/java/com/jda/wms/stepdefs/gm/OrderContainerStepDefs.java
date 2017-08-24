package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.OrderContainerDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.db.gm.PreAdviceHeaderDB;
import com.jda.wms.db.gm.PreAdviceLineDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.OrderContainerPage;
import com.jda.wms.pages.gm.PreAdviceHeaderPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.stepdefs.rdt.PurchaseOrderReceivingStepDefs;

import cucumber.api.java.en.Given;

public class OrderContainerStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private OrderContainerDB orderContainerDB;
	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private Context context;
	private JDALoginStepDefs jdaLoginStepDefs;
	private final PreAdviceHeaderDB preAdviceHeaderDB;
	private UPIReceiptHeaderDB upiReceiptHeaderDB;
	private Verification verification;
	private DeliveryDB deliveryDB;
	private PreAdviceLineStepDefs preAdviceLineStepDefs;
	private UPIReceiptLineDB upiReceiptLineDB;
	private final PreAdviceLineDB preAdviceLineDB;
	private JdaHomePage jdaHomePage;
	private PreAdviceHeaderPage preAdviceHeaderPage;
	private OrderHeaderDB orderHeaderDB;
	private InventoryDB inventoryDB;
	private OrderLineDB orderLineDB;
	private OrderContainerPage orderContainerPage;

	@Inject
	public OrderContainerStepDefs(JDAFooter jdaFooter, JDALoginStepDefs jdaLoginStepDefs, JDAHomeStepDefs jdaHomeStepDefs,
			Context context, PreAdviceHeaderDB preAdviceHeaderDB, UPIReceiptHeaderDB upiReceiptHeaderDB,
			Verification verification, DeliveryDB deliveryDB, PreAdviceLineStepDefs preAdviceLineStepDefs,
			PreAdviceLineDB preAdviceLineDB, UPIReceiptLineDB upiReceiptLineDB, JdaHomePage jdaHomePage,
			OrderHeaderDB orderHeaderDB,InventoryDB inventoryDB,OrderLineDB orderLineDB,OrderContainerPage orderContainerPage,OrderContainerDB orderContainerDB) {
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.context = context;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.verification = verification;
		this.deliveryDB = deliveryDB;
		this.preAdviceLineStepDefs = preAdviceLineStepDefs;
		this.preAdviceLineDB = preAdviceLineDB;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.jdaHomePage = jdaHomePage;
		this.orderHeaderDB = orderHeaderDB;
		this.inventoryDB=inventoryDB;
		this.orderLineDB=orderLineDB;
		this.orderContainerPage=orderContainerPage;
		this.orderContainerDB=orderContainerDB;

	}

	@Given("^the picked stock details should be updated$")
	public void the_order_id_should_be_in_status(String orderNumber, String status) throws Throwable {
		jdaFooter.clickQueryButton();
		orderContainerPage.queryWithOrderId(context.getOrderId());
		Assert.assertEquals("OrderContainer not updated as expected", "Open",
				orderContainerDB.getStatus(context.getOrderId()));
		//order haeder picked
		
		
	}
	
	
}
