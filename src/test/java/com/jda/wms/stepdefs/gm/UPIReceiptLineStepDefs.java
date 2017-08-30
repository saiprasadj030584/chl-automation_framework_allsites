package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.SupplierSkuDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Given;

public class UPIReceiptLineStepDefs {

	private Context context;
	private UPIReceiptLineDB upiReceiptLineDB;
	private SupplierSkuDB supplierSkuDb;
	private SkuDB skuDb;
	private Map<Integer, Map<String, String>> poMap;
	private Map<String, Map<String, String>> upiMap;
	private Verification verification;

	@Inject
	public UPIReceiptLineStepDefs(Context context, UPIReceiptLineDB upiReceiptLineDB, SupplierSkuDB supplierSkuDb,
			SkuDB skuDb, Verification verification) {
		this.context = context;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.supplierSkuDb = supplierSkuDb;
		this.skuDb = skuDb;
		this.verification = verification;
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

	@Given("^URN_to_be_updated_with_upi_line$")
	public void urn_to_be_updated_with_upi_line() throws Throwable {
		upiReceiptLineDB.updateContainerID(context.getUpiId());
	}

	public void fetchQtyDetails() throws Throwable {
		int qtyDue = Integer.parseInt(upiReceiptLineDB.getQtyDue(context.getUpiId(), context.getSkuId()));
		context.setRcvQtyDue(qtyDue);
	}

	@Given("^PO to be linked with upi line for multiple pallets$")
	public void po_to_be_linked_with_upi_line_for_multiple_pallets() throws Throwable {
		// Link PO ID and PO line DI with UPI for each line item
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		for (int j = 0; j < context.getUpiList().size(); j++) {
			for (int i = 1; i <= context.getNoOfLines(); i++) {
				String sku = poMap.get(i).get("SKU");
				String poLineId = poMap.get(i).get("LINE ID");
				upiReceiptLineDB.updatePreAdviceID(context.getPreAdviceId(), sku, context.getUpiList().get(j));
				upiReceiptLineDB.updatePreAdviceLineID(poLineId, sku, context.getUpiList().get(j));
			}
		}
	}

	public void container_to_be_updated_with_upi_line() throws Throwable {
		upiReceiptLineDB.updateuserdefnote2(context.getUpiId());
	}

	public void fetch_Qty_Details() throws Throwable {
		int qty_Due = Integer.parseInt(upiReceiptLineDB.getQtyDue(context.getUpiId(), context.getSkuId()));
		context.setRcvQtyDue(qty_Due);
	}

	@Given("^the UPI should have sku, quantity due details$")
	public void the_UPI_should_have_sku_quantity_due_details() throws Throwable {
		ArrayList failureList = new ArrayList();
		ArrayList skuFromUPI = new ArrayList();
		Map<String, Map<String, String>> UPIMap = new HashMap<String, Map<String, String>>();
		skuFromUPI = upiReceiptLineDB.getSkuIdList(context.getUpiId());
		context.setSkuList(skuFromUPI);

		// Add SKU details to UPI Map
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			context.setSkuId((String) skuFromUPI.get(i - 1));
			Map<String, String> lineItemsMap = new HashMap<String, String>();
			lineItemsMap.put("QTY DUE", upiReceiptLineDB.getQtyDue(context.getUpiId(), context.getSkuId()));
			lineItemsMap.put("LINE ID", upiReceiptLineDB.getLineId(context.getUpiId(), context.getSkuId()));
			lineItemsMap.put("PACK CONFIG", upiReceiptLineDB.getPackConfig(context.getUpiId(), context.getSkuId()));
			lineItemsMap.put("UPC", "");
			lineItemsMap.put("CONTAINER", upiReceiptLineDB.getContainer(context.getUpiId()));
			UPIMap.put(context.getSkuId(), lineItemsMap);
		}
		context.setUPIMap(UPIMap);
		context.setSupplierID(supplierSkuDb.getSupplierIdWithSku(context.getSkuId()));

		for (int i = 1; i <= context.getNoOfLines(); i++) {
			context.setSkuId((String) skuFromUPI.get(i - 1));
			verification.verifyData("Shipping Type", "ZIDC",
					upiReceiptLineDB.getShippingType(context.getUpiId(), context.getSkuId()), failureList);
		}
		Assert.assertTrue("UPI details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Given("^I fetch supplier id UPC$")
	public void i_fetch_supplier_id_UPC() throws Throwable {
		ArrayList skuFromUPI = new ArrayList();
		skuFromUPI = upiReceiptLineDB.getSkuIdList(context.getUpiId());
		context.setSkuList(skuFromUPI);

		context.setSkuId(upiReceiptLineDB.getSkuId(context.getUpiId()));

		context.setSupplierID(supplierSkuDb.getSupplierIdWithSku(context.getSkuId()));
		context.setUPC(supplierSkuDb.getSupplierSKU(context.getSkuId()));
	}

	public void i_fetch_supplier_id_UPC_sourced_by_multi_supplier() throws Throwable {
		context.setSkuId(upiReceiptLineDB.getSkuId(context.getUpiId()));
		context.setUPC(skuDb.getUPC(context.getSkuId()));
		if (supplierSkuDb.isMultiSourced(context.getUPC())) {
			context.setSupplierID(supplierSkuDb.getSupplierId(context.getUPC()));
		} else {
			Assert.assertFalse("SKU is not Multi Soured", supplierSkuDb.isMultiSourced(context.getUPC()));
		}
	}

}
