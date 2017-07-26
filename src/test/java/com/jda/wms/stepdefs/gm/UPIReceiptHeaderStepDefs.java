package com.jda.wms.stepdefs.gm;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.pages.gm.UpiReceiptHeaderPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class UPIReceiptHeaderStepDefs {
	private Context context;
	private UPIReceiptHeaderDB upiReceiptHeaderDB;
	private UpiReceiptHeaderPage upiReceiptHeaderPage;

	@Inject
	public UPIReceiptHeaderStepDefs(Context context,UPIReceiptHeaderDB upiReceiptHeaderDB,UpiReceiptHeaderPage upiReceiptHeaderPage) {
		this.context = context;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.upiReceiptHeaderPage=upiReceiptHeaderPage;
	}

@Given("^ASN to be linked with upi header$")
public void asn_to_be_linked_with_upi_header() throws Throwable {
	upiReceiptHeaderDB.updateASN(context.getUpiId(),context.getAsnId());
}

@When("^i enter palletId$")
public void i_enter_palletId() throws Throwable {
	upiReceiptHeaderPage.enterPallet(context.getPalletID());
	Assert.assertTrue("Record not displayed as expected",
			upiReceiptHeaderPage.isNoRecordExist());
}
}
