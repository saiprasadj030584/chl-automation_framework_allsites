
package com.jda.wms.stepdefs.gm;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.PreAdviceLineDB;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.InventoryTransactionQueryPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.PackConfigMaintenancePage;
import com.jda.wms.pages.gm.PreAdviceLineMaintenancePage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PackConfigStepDefs {

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
	private InventoryTransactionQueryPage inventoryTransactionQueryPage;

	@Inject
	public PackConfigStepDefs(Context context, Verification verification, PreAdviceLineDB preAdviceLineDB,
			JdaHomePage jdaHomePage, UPIReceiptLineDB upiReceiptLineDB, SkuDB skuDB, JDALoginStepDefs jdaLoginStepDefs,
			JDAHomeStepDefs jdaHomeStepDefs, PreAdviceLineMaintenancePage preAdviceLineMaintenancePage,
			JDAFooter jdaFooter,PackConfigMaintenancePage packConfigMaintenancePage,InventoryTransactionQueryPage inventoryTransactionQueryPage) {
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
		this.inventoryTransactionQueryPage=inventoryTransactionQueryPage;
	}

	@Given("^the SKU \"([^\"]*)\" is loaded in warehouse$")
	public void the_SKU_is_loaded_in_warehouse(String skuId) throws Throwable {
		context.setSkuId(skuId);
		Assert.assertTrue("the SKU is loaded in warehouse", skuDB.validateSku(skuId));
		jdaHomePage.navigateToPackConfigPage();
	}

	@When("^I create config with TagVolume \"([^\"]*)\" and TrackingLevel \"([^\"]*)\"$")
	public void i_create_config_with_TagVolume_and_TrackingLevel(String arg1,String arg2) throws Throwable {
		String sku=context.getSkuId();
//		 int i = 0;
//	        while (sku.charAt(i) == '0')
//	            i++;
//	 
//	        // Convert str into StringBuffer as Strings
//	        // are immutable.
//	        StringBuffer sb = new StringBuffer(sku);
//	 
//	        // The  StringBuffer replace function removes
//	        // i characters from given index (0 here)
//	        sb.replace(0, i, "");
	 
	        String packConfig=Utilities.getFiveDigitRandomNumber()+"EA";
	        context.setPackConfigID(packConfig);
	        jdaHomePage.navigateToPackConfigMaintenance();
	        jdaFooter.clickAddButton();
	        packConfigMaintenancePage.enterPackConfigId(context.getPackConfigID());
	        
	        packConfigMaintenancePage.enterTagVolume(arg1);
	        packConfigMaintenancePage.clickTrackingLevelsTab();
	        packConfigMaintenancePage.enterTrackingLevel(arg2);
	        packConfigMaintenancePage.clickRDTTab();
	        packConfigMaintenancePage.clickRDTDisplayLevel1();
	        jdaFooter.clickExecuteButton();
	        jdaFooter.PressEnter();
	        	
	        }
	
	@Then("^I delete the pack config$")
	public void i_delete_the_pack_config() throws Throwable {
		
		jdaHomePage.navigateToPackConfigMaintenance();
        jdaFooter.clickQueryButton();
        packConfigMaintenancePage.enterPackConfigId(context.getPackConfigID());
        jdaFooter.clickExecuteButton();
        jdaFooter.clickDeleteButton();
        jdaFooter.PressEnter();
        jdaFooter.clickQueryButton();
        packConfigMaintenancePage.enterPackConfigId(context.getPackConfigID());
        jdaFooter.clickExecuteButton();
        Assert.assertTrue("Records Available-Deletion Failed" ,inventoryTransactionQueryPage.isNoRecordsExists());
        
	}
}
