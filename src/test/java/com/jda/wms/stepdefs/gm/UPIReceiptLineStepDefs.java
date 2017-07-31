package com.jda.wms.stepdefs.gm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.SupplierSkuDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;

import cucumber.api.java.en.Given;

public class UPIReceiptLineStepDefs {
	
	private Context context;
	private UPIReceiptLineDB upiReceiptLineDB;
	private SupplierSkuDB supplierSkuDb;
	private SkuDB skuDb;
	private Map<Integer, Map<String, String>> poMap;
	private Map<String, Map<String, String>> upiMap;

	@Inject
	public UPIReceiptLineStepDefs(Context context,UPIReceiptLineDB upiReceiptLineDB,SupplierSkuDB supplierSkuDb,SkuDB skuDb) {
		this.context = context;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.supplierSkuDb=supplierSkuDb;
		this.skuDb=skuDb;
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
	
	@Given("^PO to be linked with upi line for multiple pallets$")
	public void po_to_be_linked_with_upi_line_for_multiple_pallets() throws Throwable {
		//Link PO ID and PO line DI with UPI for each line item
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		for (int k=0;k<context.getPreAdviceList().size();k++)
		{
		
		for (int j=0;j<context.getUpiList().size();j++)
		{
		for (int i=1;i<=
				Integer.parseInt(context.getPoNumLinesMap().get(context.getPreAdviceList().get(k)));i++){
			String sku = context.getMultiplePOMap().get(context.getPreAdviceList().get(k)).get(i).get("SKU");
			String poLineId=context.getMultiplePOMap().get(context.getPreAdviceList().get(k)).get(i).get("LINE ID");
			upiReceiptLineDB.updatePreAdviceID(context.getPreAdviceList().get(k),sku,context.getUpiList().get(j));
			upiReceiptLineDB.updatePreAdviceLineID(poLineId,sku,context.getUpiList().get(j));
		}
		}
		}
	}
	
	@Given("^ Multiple PO to be linked with upi line for multiple pallets$")
	public void multiple_po_to_be_linked_with_upi_line_for_multiple_pallets() throws Throwable {
		//Link PO ID and PO line DI with UPI for each line item
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		for (int k=0;k<context.getPreAdviceList().size();k++)
		{
		for (int j=0;j<context.getUpiList().size();j++)
		{
		for (int i=1;i<=
				Integer.parseInt(context.getPoNumLinesMap().get(context.getPreAdviceList().get(k)));i++){
			String sku = poMap.get(i).get("SKU");
			String poLineId= poMap.get(i).get("LINE ID");
			upiReceiptLineDB.updatePreAdviceID(context.getPreAdviceList().get(k),sku,context.getUpiList().get(j));
			upiReceiptLineDB.updatePreAdviceLineID(poLineId,sku,context.getUpiList().get(j));
		}
		}
		}
	}
	
	public void container_to_be_updated_with_upi_line() throws Throwable {
		upiReceiptLineDB.updateUserDefNote2(context.getUpiId());
		}
	
	@Given("^I fetch supplier id UPC$")
	public void i_fetch_supplier_id_UPC() throws Throwable {
		context.setSkuId(upiReceiptLineDB.getSkuId(context.getUpiId()));
		context.setSupplierID(supplierSkuDb.getSupplierIdWithSku(context.getSkuId()));
		context.setUPC(supplierSkuDb.getSupplierSKU(context.getSkuId(),context.getSupplierID()));
		}
	
	@Given("^URN_to_be_updated_with_upi_line$")
	public void urn_to_be_updated_with_upi_line() throws Throwable {
		upiReceiptLineDB.updateContainerID(context.getUpiId());
		}
	
	public void fetch_Qty_Details() throws Throwable
	{
				int qty_Due=Integer.parseInt(upiReceiptLineDB.getQtyDue(context.getUpiId(), context.getSkuId()));
				context.setRcvQtyDue(qty_Due);
	}
	
	
}

