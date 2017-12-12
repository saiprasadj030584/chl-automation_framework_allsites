
package com.jda.wms.stepdefs.gm;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.PreAdviceLineDB;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.PackConfigLinkingPage;
import com.jda.wms.pages.gm.PackConfigMaintenancePage;
import com.jda.wms.pages.gm.PreAdviceLineMaintenancePage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PackConfigLinkingStepDefs {

	private Context context;
	private JDAHomeStepDefs jDAHomeStepDefs;
	private JdaHomePage jdaHomePage;
	private SkuDB skuDB;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Verification verification;
	private PreAdviceLineDB preAdviceLineDB;
	private UPIReceiptLineDB upiReceiptLineDB;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private JDALoginStepDefs jdaLoginStepDefs;
	private PreAdviceLineMaintenancePage preAdviceLineMaintenancePage;
	private JDAFooter jdaFooter;
	private PackConfigMaintenancePage packConfigMaintenancePage;
	private PackConfigLinkingPage packConfigLinkingPage;

	@Inject
	public PackConfigLinkingStepDefs(Context context, Verification verification, PreAdviceLineDB preAdviceLineDB,
			JdaHomePage jdaHomePage, UPIReceiptLineDB upiReceiptLineDB, SkuDB skuDB, JDALoginStepDefs jdaLoginStepDefs,
			JDAHomeStepDefs jdaHomeStepDefs, PreAdviceLineMaintenancePage preAdviceLineMaintenancePage,
			JDAFooter jdaFooter,PackConfigMaintenancePage packConfigMaintenancePage,PackConfigLinkingPage packConfigLinkingPage) {
		this.context = context;
		this.jdaHomePage = jdaHomePage;
		this.verification = verification;
		this.preAdviceLineDB = preAdviceLineDB;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.skuDB = skuDB;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
		this.preAdviceLineMaintenancePage = preAdviceLineMaintenancePage;
		this.jdaFooter = jdaFooter;
		this.packConfigMaintenancePage=packConfigMaintenancePage;
		this.packConfigLinkingPage=packConfigLinkingPage;
	}


	@Then("^I link SKU with configId in Pack Config Linking$")
	public void i_link_sku_with_configId_in_Pack_Config_Linking() throws Throwable {
		
		packConfigLinkingPage.clickPackConigLinkingToSku();
		jdaFooter.clickNextButton();
		packConfigLinkingPage.enterSkuId(context.getPackConfigID());
		jdaFooter.clickNextButton();
		packConfigLinkingPage.enterSkuId(context.getSkuId());
		jdaFooter.clickNextButton();
		packConfigLinkingPage.selectLinkPackConfig();
		jdaFooter.clickDoneButton();
	}
	
	
	@Then("^I unlink the pack config with the sku$")
	public void i_unlink_the_pack_config_with_the_sku() throws Throwable {
		jdaHomePage.navigateToPackConfigLinking();
		Thread.sleep(6000);
		packConfigLinkingPage.clickPackConigLinkingToSku();
		jdaFooter.clickNextButton();
		packConfigLinkingPage.enterSkuId(context.getPackConfigID());
		jdaFooter.clickNextButton();
		packConfigLinkingPage.enterSkuId(context.getSkuId());
		jdaFooter.clickNextButton();
		packConfigLinkingPage.selectPackConfigForUnlink();
		jdaFooter.clickDoneButton();
	//	Assert.assertFalse("Pack config details not updated" ,skuDB.isSkuExistsForPackConfig(context.getSkuId(),context.getPackConfigID()));

	}
	

	

	
}
