package com.jda.wms.stepdefs.gm;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;

import cucumber.api.java.en.Given;

public class UPIReceiptHeaderStepDefs {
	private Context context;
	private UPIReceiptHeaderDB upiReceiptHeaderDB;

	@Inject
	public UPIReceiptHeaderStepDefs(Context context,UPIReceiptHeaderDB upiReceiptHeaderDB) {
		this.context = context;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
	}

@Given("^ASN to be linked with upi header$")
public void asn_to_be_linked_with_upi_header() throws Throwable {
	upiReceiptHeaderDB.updateASN(context.getUpiId(),context.getAsnId());
}
}
