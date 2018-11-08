package com.jda.wms.stepdefs.Exit;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.pages.Exit.SupplierSKUMaintenancePage;

import cucumber.api.java.en.Then;

public class SupplierSKUQueryStepDefs{
	
	private final SupplierSKUMaintenancePage supplierSKUMaintenancePage;
	private final SkuDB skuDB;
	
	@Inject
	public SupplierSKUQueryStepDefs(SupplierSKUMaintenancePage supplierSKUMaintenancePage,SkuDB skuDB){
		this.supplierSKUMaintenancePage=supplierSKUMaintenancePage;
		this.skuDB=skuDB;
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
	
}