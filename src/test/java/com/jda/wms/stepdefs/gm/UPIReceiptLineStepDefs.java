package com.jda.wms.stepdefs.gm;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.SupplierSkuDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;

import cucumber.api.java.en.Given;

public class UPIReceiptLineStepDefs {
	
	private Context context;
	private UPIReceiptLineDB upiReceiptLineDB;
	private SupplierSkuDB supplierSkuDb;
	private Map<Integer, Map<String, String>> poMap;
	private Map<String, Map<String, String>> upiMap;

	@Inject
	public UPIReceiptLineStepDefs(Context context,UPIReceiptLineDB upiReceiptLineDB,SupplierSkuDB supplierSkuDb) {
		this.context = context;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.supplierSkuDb=supplierSkuDb;
	}
	@Given("^PO to be linked with upi line$")
	public void po_to_be_linked_with_upi_line() throws Throwable {
		//Link PO ID and PO line DI with UPI for each line item
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		for (int i=1;i<=
				context.getNoOfLines();i++){
			String sku = poMap.get(i).get("SKU");
			String poLineId= poMap.get(i).get("LINE ID");
			upiReceiptLineDB.updatePreAdviceID(context.getPreAdviceId(),sku,context.getUpiId());
			upiReceiptLineDB.updatePreAdviceLineID(poLineId,sku,context.getUpiId());
		}
	}
	
	public void container_to_be_updated_with_upi_line() throws Throwable {
		upiReceiptLineDB.update_user_def_note2(context.getUpiId());
		}
	
	@Given("^Container to be updated with upi line$")
	public void i_fetch_supplier_id_UPC() throws Throwable {
		context.setUPC(upiReceiptLineDB.fetchUPC(context.getUpiId()));
		context.setSupplierID(supplierSkuDb.getSupplierId(context.getUPC()));
		}
	
	@Given("^URN_to_be_updated_with_upi_line$")
	public void urn_to_be_updated_with_upi_line() throws Throwable {
		upiReceiptLineDB.updateContainerID(context.getUpiId());
		}
	
	
}
