package com.jda.wms.stepdefs.gm;

import java.util.Map;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.UPIReceiptLineDB;

import cucumber.api.java.en.Given;

public class UPIReceiptLineStepDefs {

	private Context context;
	private UPIReceiptLineDB upiReceiptLineDB;
	private Map<Integer, Map<String, String>> poMap;
	private Map<String, Map<String, String>> upiMap;

	@Inject
	public UPIReceiptLineStepDefs(Context context, UPIReceiptLineDB upiReceiptLineDB) {
		this.context = context;
		this.upiReceiptLineDB = upiReceiptLineDB;
	}

	@Given("^PO to be linked with upi line$")
	public void po_to_be_linked_with_upi_line() throws Throwable {
		// Link PO ID and PO line DI with UPI for each line item
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			String sku = poMap.get(i).get("SKU");
			String poLineId = poMap.get(i).get("LINE ID");
			upiReceiptLineDB.updatePreAdviceID(context.getPreAdviceId(), sku, context.getUpiId());
			upiReceiptLineDB.updatePreAdviceLineID(poLineId, sku, context.getUpiId());
		}
	}
}
