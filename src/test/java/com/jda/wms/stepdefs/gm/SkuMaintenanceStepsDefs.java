package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.db.gm.PreAdviceHeaderDB;
import com.jda.wms.db.gm.PreAdviceLineDB;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.foods.PackConfigMaintenancePage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.PreAdviceHeaderPage;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Given;

public class SkuMaintenanceStepsDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private Context context;
	private JDALoginStepDefs jdaLoginStepDefs;
	private final PreAdviceHeaderDB preAdviceHeaderDB;
	private UPIReceiptHeaderDB upiReceiptHeaderDB;
	private Verification verification;
	private DeliveryDB deliveryDB;
	private JdaLoginPage jdaLoginPage;
	private PreAdviceLineStepDefs preAdviceLineStepDefs;
	private UPIReceiptLineDB upiReceiptLineDB;
	private final PreAdviceLineDB preAdviceLineDB;
	private JdaHomePage jdaHomePage;
	private PreAdviceHeaderPage preAdviceHeaderPage;
	private SkuDB skuDB;
	private PackConfigMaintenancePage packConfigMaintenancePage;

	@Inject
	public SkuMaintenanceStepsDefs(JDAFooter jdaFooter, JDALoginStepDefs jdaLoginStepDefs,
			JDAHomeStepDefs jdaHomeStepDefs, Context context, PreAdviceHeaderDB preAdviceHeaderDB,
			UPIReceiptHeaderDB upiReceiptHeaderDB, Verification verification, DeliveryDB deliveryDB,
			PreAdviceLineStepDefs preAdviceLineStepDefs, PreAdviceLineDB preAdviceLineDB,

			UPIReceiptLineDB upiReceiptLineDB, JdaHomePage jdaHomePage,SkuDB skuDB,PackConfigMaintenancePage packConfigMaintenancePage) {

		this.jdaFooter = jdaFooter;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
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
		this.skuDB=skuDB;
		this.packConfigMaintenancePage=packConfigMaintenancePage;
	}

	@Given("^the sku of type \"([^\"]*)\" and not new product$")
	public void the_sku_of_type_and_new_product(String skuType
			) throws Throwable {
		context.setSKUType(skuType);
		ArrayList failureList = new ArrayList();

		String type = null;
		switch (context.getSKUType()) {
		case "Boxed":
			type = "B";
			break;
		case "Hanging":
			type = "H";
			break;
		}
		context.setSkuId(skuDB.getSKU(type));
		verification.verifyData("SKU Type", type, skuDB.getSKUType(context.getSkuId()), failureList);
		verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue(context.getSkuId()),
				failureList);
		Assert.assertTrue("PO , UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Given("^the pack config details should be updated in sku maintenance page$")
	public void the_pack_config_details_should_be_updated_in_sku_maintenance_page(
			) throws Throwable {

		Assert.assertTrue("Pack config dfetails not updated" ,skuDB.isSkuExistsForPackConfig(context.getSkuId(),context.getPackConfigID()));
	}
	}