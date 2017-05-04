package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.AddressMaintenancePage;
import com.jda.wms.pages.foods.CEConsignmentMaintenancePage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.*;
import edu.emory.mathcs.backport.java.util.Arrays;

public class CEConsignmentMaintenanceStepDefs {
	
	private final CEConsignmentMaintenancePage ceConsignmentMaintenancePage;
	private final Context context;
	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdaFooter;
	private final AddressMaintenancePage addressMaintenancePage;
	private AddressMaintenanceStepDefs addressMaintenanceStepDefs;
	private String euCountries = "AlbaniaAndorraArmeniaAustriaAzerbaijanBelarusBelgiumBosnia-HerzegovinaBulgariaCroatiaCyprusCzech RepublicDenmarkEstoniaFinlandFranceGeorgiaGermanyGreeceHungaryIcelandIrelandItalyKazakhstanKosovoLatviaLiechtensteinLithuaniaLuxembourgMacedoniaMaltaMoldovaMonacoMontserratNetherlandsNorwayPolandPortugalRomaniaRussiaSan MarinoSerbiaSlovakiaSloveniaSpainSwedenSwitzerlandTurkeyUkraineVatican City State";

	@Inject
	public CEConsignmentMaintenanceStepDefs(CEConsignmentMaintenancePage ceConsignmentMaintenancePage, Context context, JdaHomePage jdaHomePage, JDAFooter jdaFooter, AddressMaintenancePage addressMaintenancePage,AddressMaintenanceStepDefs addressMaintenanceStepDefs) {
		this.ceConsignmentMaintenancePage = ceConsignmentMaintenancePage;
		this.context = context;
		this.jdaHomePage = jdaHomePage;
		this.jdaFooter = jdaFooter;
		this.addressMaintenancePage = addressMaintenancePage;
		this.addressMaintenanceStepDefs = addressMaintenanceStepDefs;
	}
	
	@When("^I create consignment for the supplier$")
	public void i_create_consignment_for_the_supplier() throws Throwable {
		//For scenario purpose
		context.setSupplierID("F06259");
		context.setCountry("France");
		context.setPreAdviceId("2050004488");
		jdaHomePage.navigateToAddressMaintenancePage();
		jdaFooter.clickQueryButton();
		addressMaintenancePage.enterAddressID(context.getSupplierID());
		jdaFooter.clickExecuteButton();
		//For scenario purpose
		
		addressMaintenanceStepDefs.i_navigate_to_customs_excise_tab_in_address_maintenance();
		addressMaintenanceStepDefs.the_CE_warehouse_type_should_be_displayed_as_excise();
		addressMaintenanceStepDefs.the_CE_tax_warehouse_should_be_displayed();
		
		jdaHomePage.navigateToCEConsignmentMaintenenacePage();
		jdaFooter.clickAddButton();
		
		if (context.getCountry().equals("United Kingdom")){
			ceConsignmentMaintenancePage.selectReceiptType("Other Warehouse");
		}
		else if (euCountries.contains(context.getCountry())){
			ceConsignmentMaintenancePage.selectReceiptType("From EU");
		}
		
		ceConsignmentMaintenancePage.selectCEStatus("Released");
		ceConsignmentMaintenancePage.enterSiteID("9771");
		ceConsignmentMaintenancePage.enterSupplier(context.getSupplierID());
		ceConsignmentMaintenancePage.enterConsignerExciseNumber(context.getCeWarehouseTax());
		String arcNo = "16ESD08200000"+Utilities.getEightDigitRAndomNumber();
		System.out.println(arcNo);
		context.setConsignmentID(arcNo);
		ceConsignmentMaintenancePage.enterECMSEADARC(arcNo);
		ceConsignmentMaintenancePage.enterECMSEADDate();
		ceConsignmentMaintenancePage.enterECMSEADTime();
		jdaFooter.clickExecuteButton();
		ceConsignmentMaintenancePage.clickYes();
		
		String consignmentID = ceConsignmentMaintenancePage.getConsignmentID();
		context.setConsignmentID(consignmentID);
		Assert.assertNotNull(
				"Consignment ID is not displayed as expected. Expected [Not Null] but was [" + consignmentID + "]",
				consignmentID);
	}
}
