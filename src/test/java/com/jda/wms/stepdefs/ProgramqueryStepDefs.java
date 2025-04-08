package com.jda.wms.stepdefs;

import org.junit.Assert;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.UI.pages.ProgramQueryPage;
import com.jda.wms.UI.pages.SKUQueryPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class ProgramqueryStepDefs{
	private final ProgramQueryPage programQueryPage; 
	private final SKUQueryPage sKUQueryPage;
	
	@Inject
	public ProgramqueryStepDefs(ProgramQueryPage programQueryPage,SKUQueryPage sKUQueryPage){
		this.programQueryPage=programQueryPage;
		this.sKUQueryPage=sKUQueryPage;
	}
		
	
	
	@And("^Specify the Program name \"([^\"]*)\"$")
	public void specify_the_programname(String name) throws Throwable {
		programQueryPage.enterProgramName(name);
	}
	@And("^Run the program$")
	public void run_the_program() throws Throwable {
		programQueryPage.clickRun();
	}
	@Then("^Verify the fields in SKU \"([^\"]*)\"$")
	public void validation_of_fields(String SKU) throws Throwable {
		sKUQueryPage.Stroke_Validation(SKU);
		sKUQueryPage.CommodityCode_Validation(SKU);
		sKUQueryPage.validateweight(SKU);
		sKUQueryPage.clickSupplierSkuFromSKU();
		sKUQueryPage.supplierid_Validation(SKU);
		
	}
	
}