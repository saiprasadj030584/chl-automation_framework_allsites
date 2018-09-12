package com.jda.wms.stepdefs.Exit;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.Exit.AddressMaintenancePage;
import com.jda.wms.pages.Exit.CEConsignmentMaintenancePage;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.PopUpPage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.When;

public class CEConsignmentMaintenanceStepDefs {
	
	private final CEConsignmentMaintenancePage ceConsignmentMaintenancePage;
	private final Context context;
	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdaFooter;
	private final AddressMaintenancePage addressMaintenancePage; 
	private final JDALoginStepDefs jdaLoginStepDefs;
	private AddressMaintenanceStepDefs addressMaintenanceStepDefs;
	private PopUpPage popUpPage;

	//private String euCountries = "AlbaniaAndorraArmeniaAustriaAzerbaijanBelarusBelgiumBosnia-HerzegovinaBulgariaCroatiaCyprusCzech RepublicDenmarkEstoniaFinlandFranceGeorgiaGermanyGreeceHungaryIcelandIrelandItalyKazakhstanKosovoLatviaLiechtensteinLithuaniaLuxembourgMacedoniaMaltaMoldovaMonacoMontserratNetherlandsNorwayPolandPortugalRomaniaRussiaSouth AfricaSan MarinoSerbiaSlovakiaSloveniaSpainSwedenSwitzerlandTurkeyUkraineVatican City State";
	private String euCountries = "NLDPOLPRTROUSVKSVNESPSWEAUTBELBGRHRVCYPCZEDNKESTFINFRADEUGRCHUNIRLITALVALTULUXMLT";

	@Inject
	public CEConsignmentMaintenanceStepDefs(CEConsignmentMaintenancePage ceConsignmentMaintenancePage,JDALoginStepDefs jdaLoginStepDefs, Context context, JdaHomePage jdaHomePage, JDAFooter jdaFooter, AddressMaintenancePage addressMaintenancePage,AddressMaintenanceStepDefs addressMaintenanceStepDefs, PopUpPage popUpPage) {
		this.ceConsignmentMaintenancePage = ceConsignmentMaintenancePage;
		this.context = context;
		this.jdaHomePage = jdaHomePage;
		this.jdaFooter = jdaFooter;
		this.addressMaintenancePage = addressMaintenancePage;
		this.addressMaintenanceStepDefs = addressMaintenanceStepDefs;
		this.popUpPage = popUpPage;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
	}
	
	@When("^I create consignment for the supplier$")
	public void i_create_consignment_for_the_supplier() throws Throwable {
		jdaLoginStepDefs.i_have_logged_in_as_warehouse_user_in_JDA_Exit_application();
		jdaHomePage.navigateToCEConsignmentMaintenenacePage(); 
		Thread.sleep(3000);
		jdaFooter.clickAddButton();
		
		if (context.getCountry().equals("GBR")){
			ceConsignmentMaintenancePage.selectReceiptType("Other Warehouse");
		}
		//TODO try to get the european country list from JSON  000000247
		else if (euCountries.contains(context.getCountry())){
			ceConsignmentMaintenancePage.selectReceiptType("From EU");
		}
		Thread.sleep(2500);
		ceConsignmentMaintenancePage.selectCEStatus("Released");
		ceConsignmentMaintenancePage.enterSiteID("9771");
		ceConsignmentMaintenancePage.enterSupplier(context.getSupplierID());
		ceConsignmentMaintenancePage.enterConsignerExciseNumber(context.getCeWarehouseTax());
		String arcNo = "16ESD08200000"+Utilities.getEightDigitRandomNumber();
		context.setConsignmentID(arcNo);
		ceConsignmentMaintenancePage.enterECMSEADARC(arcNo);
		ceConsignmentMaintenancePage.enterECMSEADDate();
		ceConsignmentMaintenancePage.enterECMSEADTime();
		jdaFooter.clickExecuteButton();
		Assert.assertTrue("Pop up not displayed after submission of consignment", popUpPage.isPopUpDisplayed());
		popUpPage.clickYes();
		
		String consignmentID = ceConsignmentMaintenancePage.getConsignmentID();
		context.setConsignmentID(consignmentID);
		Assert.assertNotNull(
				"Consignment ID is not displayed as expected. Expected [Not Null] but was [" + consignmentID + "]",
				consignmentID);
	}
}
