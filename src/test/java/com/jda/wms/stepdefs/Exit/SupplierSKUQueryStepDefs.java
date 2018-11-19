package com.jda.wms.stepdefs.Exit;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.sikuli.script.FindFailed;

import com.google.inject.Inject;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.pages.Exit.SupplierSKUMaintenancePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import com.jda.wms.pages.Exit.SKUQueryPage;

public class SupplierSKUQueryStepDefs{
	
	private final SupplierSKUMaintenancePage supplierSKUMaintenancePage;
	private final SkuDB skuDB;
	private final SKUQueryPage sKUQueryPage;
	
	@Inject
	public SupplierSKUQueryStepDefs(SupplierSKUMaintenancePage supplierSKUMaintenancePage,SkuDB skuDB,SKUQueryPage sKUQueryPage){
		this.supplierSKUMaintenancePage=supplierSKUMaintenancePage;
		this.skuDB=skuDB;
		this.sKUQueryPage=sKUQueryPage;
	}
	
	@Then("^Verify the Delivery lead time in future date$")
	public void verify_the_delivery_lead_time_in_future_date() throws Throwable {
		String dlt=supplierSKUMaintenancePage.getDeliveryLeadtime();
		System.out.println("Delivery lead time is:  " + dlt);
		Assert.assertNotEquals("",dlt);
		Calendar date = new GregorianCalendar();
		int nyear = date.get(Calendar.YEAR);
		System.out.println("Current Year is:  " + nyear);
		String numberString = "" + nyear;
		String firsttwoLetterchar = numberString.substring(0, 2);
		int firsttwoDigit = Integer.parseInt("" + firsttwoLetterchar);
		System.out.println("firstTwoDigit is:  " + firsttwoDigit);
		String lastwoLetterchar = dlt.substring(2);
		int lasttwoDigit = Integer.parseInt("" + lastwoLetterchar);
		System.out.println("LastTwoDigit is:  " + lasttwoDigit);
		
		int ftryr = Integer.parseInt(firsttwoLetterchar + lastwoLetterchar);
		System.out.println("Future year is:  " + ftryr);
		Assert.assertTrue(nyear < ftryr);
	}
	@And("^Verify the Suppliersku record is available \"([^\"]*)\"$")
	public void verify_the_suppliersku_record(String SKU) throws Throwable{
		sKUQueryPage.supplierid_Validation(SKU);
		
	}
	@Then("^Query with Supplier_ID \"([^\"]*)\"$")
	public void query_with_supplier_id(String SKU) throws FindFailed, ClassNotFoundException, InterruptedException
	{
		sKUQueryPage.enterSupplierSKU(SKU);
		
	}
	@Then("^Verify factory code for a supplier$")
	public void verify_factory_code_for_a_supplier() throws FindFailed, InterruptedException
	{
		sKUQueryPage.getfactoryCode();
		sKUQueryPage.factorycode_validation();
		
	}
}