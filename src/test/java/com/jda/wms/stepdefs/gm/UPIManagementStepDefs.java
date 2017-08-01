package com.jda.wms.stepdefs.gm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.sikuli.script.Match;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.SupplierSkuDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.UPIManagementPage;
import com.jda.wms.pages.gm.UpiReceiptHeaderPage;

import cucumber.api.java.en.Given;

public class UPIManagementStepDefs {
	
	private Context context;
	private UPIReceiptLineDB upiReceiptLineDB;
	private SupplierSkuDB supplierSkuDb;
	private SkuDB skuDb;
	private Map<Integer, Map<String, String>> poMap;
	private Map<String, Map<String, String>> upiMap;
	private UPIManagementPage uPIManagementPage;
	private UpiReceiptHeaderPage upiReceiptHeaderPage;

	@Inject
	public UPIManagementStepDefs(Context context,UPIReceiptLineDB upiReceiptLineDB,SupplierSkuDB supplierSkuDb,SkuDB skuDb,UPIManagementPage uPIManagementPage,UpiReceiptHeaderPage upiReceiptHeaderPage) {
		this.context = context;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.supplierSkuDb=supplierSkuDb;
		this.skuDb=skuDb;
		this.uPIManagementPage=uPIManagementPage;
		this.upiReceiptHeaderPage=upiReceiptHeaderPage;
	}
	@Given("^I search with ASN in UPI Management screen$")
	public void i_search_with_asn_in_upi_management_screen() throws Throwable {
		uPIManagementPage.searchWithAsn(context.getAsnId());
	}
	@Given("^the due date should be displayed for the ASN$")
	public void the_due_date_should_be_displayed_for_the_ASN() throws Throwable {
		Assert.assertTrue("No Records Found", uPIManagementPage.isRecordsFound());
		uPIManagementPage.doubleClickTheRecord();
		System.out.println(context.getDueDate());
		System.out.println(upiReceiptHeaderPage.getDueDate());
		Assert.assertEquals("Due date mismatch",context.getDueDate(),upiReceiptHeaderPage.getDueDate());
		
		
	}
	
	}
	
