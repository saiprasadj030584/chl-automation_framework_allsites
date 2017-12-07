package com.jda.wms.stepdefs.gm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.SupplierSkuDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
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

	public void fetchQtyDetails() throws Throwable {
		int qtyDue = Integer.parseInt(upiReceiptLineDB.getQtyDue(context.getUpiId(), context.getSkuId()));
		context.setRcvQtyDue(qtyDue);
	}

	@Given("^PO to be linked with upi line for multiple pallets$")
	public void po_to_be_linked_with_upi_line_for_multiple_pallets() throws Throwable {
		// Link PO ID and PO line DI with UPI for each line item
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		int m = 0;
		for (int j = 0; j < context.getUpiList().size(); j++) {

			for (int i = 1; i <= Integer
					.parseInt(context.getPoNumLinesMap().get(context.getPreAdviceList().get(m))); i++) {
				String sku = context.getMultiplePOMap().get(context.getPreAdviceList().get(m)).get(i).get("SKU");
				String poLineId = context.getMultiplePOMap().get(context.getPreAdviceList().get(m)).get(i)
						.get("LINE ID");
				upiReceiptLineDB.updatePreAdviceID(context.getPreAdviceList().get(m), sku, context.getUpiList().get(j));
				upiReceiptLineDB.updatePreAdviceLineID(poLineId, sku, context.getUpiList().get(j));
			}
			m++;
		}
	}

	@Given("^ Multiple PO to be linked with upi line for multiple pallets$")
	public void multiple_po_to_be_linked_with_upi_line_for_multiple_pallets() throws Throwable {
		// Link PO ID and PO line DI with UPI for each line item
		poMap = context.getPOMap();
		upiMap = context.getUPIMap();
		for (int k = 0; k < context.getPreAdviceList().size(); k++) {
			for (int j = 0; j < context.getUpiList().size(); j++) {
				for (int i = 1; i <= Integer
						.parseInt(context.getPoNumLinesMap().get(context.getPreAdviceList().get(k))); i++) {
					String sku = poMap.get(i).get("SKU");
					String poLineId = poMap.get(i).get("LINE ID");
					upiReceiptLineDB.updatePreAdviceID(context.getPreAdviceList().get(k), sku,
							context.getUpiList().get(j));
					upiReceiptLineDB.updatePreAdviceLineID(poLineId, sku, context.getUpiList().get(j));
				}
			}
		}
	}

	public void container_to_be_updated_with_upi_line() throws Throwable {
		upiReceiptLineDB.updateuserdefnote2(context.getUpiId());
	}

	public void container_to_be_updated_with_upi_line_in_multiple_upi() throws Throwable {
		for (int i = 0; i < context.getUpiList().size(); i++) {
			upiReceiptLineDB.updateUserDefNote2(context.getUpiList().get(i));
		}
	}

	@Given("^I fetch supplier id UPC$")
	public void i_fetch_supplier_id_UPC() throws Throwable {
		context.setSkuId(upiReceiptLineDB.getSkuId(context.getUpiId()));
		context.setSupplierID(supplierSkuDb.getSupplierIdWithSku(context.getSkuId()));
		context.setUPC(supplierSkuDb.getSupplierSKU(context.getSkuId(), context.getSupplierID()));
	}

	@Given("^I fetch supplier id UPC qty$")
	public void i_fetch_supplier_id_UPC_qty() throws Throwable {
		context.setSkuId(upiReceiptLineDB.getSkuId(context.getUpiId()));
		context.setSupplierID(supplierSkuDb.getSupplierIdWithSku(context.getSkuId()));
		context.setUPC(supplierSkuDb.getSupplierSKU(context.getSkuId(), context.getSupplierID()));
		context.setRcvQtyDue(Integer.parseInt(upiReceiptLineDB.getQtyDue(context.getUpiId(), context.getSkuId())));
	}

	@Given("^URN_to_be_updated_with_upi_line$")
	public void urn_to_be_updated_with_upi_line() throws Throwable {
		upiReceiptLineDB.updateContainerID(context.getUpiId());
	}

	@Given("^URN_to_be_updated_with_upi_line_in_multiple_upi$")
	public void urn_to_be_updated_with_upi_line_in_multiple_upi() throws Throwable {
		for (int i = 0; i < context.getUpiList().size(); i++) {
			upiReceiptLineDB.updateContainerID(context.getUpiList().get(i));
		}
	}

	public void fetch_Qty_Details() throws Throwable {
		int qty_Due = Integer.parseInt(upiReceiptLineDB.getQtyDue(context.getUpiId(), context.getSkuId()));
		context.setRcvQtyDue(qty_Due);
	}

	public void fetch_Qty_Details_and_upc() throws Throwable {
		int qty_Due = Integer.parseInt(upiReceiptLineDB.getQtyDue(context.getUpiId(), context.getSkuId()));
		context.setRcvQtyDue(qty_Due);
		String supplierId = supplierSkuDb.getSupplierIdWithSku(context.getSkuId());
		String upc = supplierSkuDb.getSupplierSKU(context.getSkuId(), supplierId);
		context.setUPC(upc);
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
			String supplierId = supplierSkuDb.getSupplierIdWithSku(context.getSkuId());
			lineItemsMap.put("SUPPLIER ID", supplierId);
			String upc = supplierSkuDb.getSupplierSKU(context.getSkuId(), supplierId);
			lineItemsMap.put("UPC", upc);

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

	public void i_fetch_supplier_id_UPC_sourced_by_multi_supplier() throws Throwable {
		context.setSkuId(upiReceiptLineDB.getSkuId(context.getUpiId()));
		context.setUPC(skuDb.getUPC(context.getSkuId()));
		if (supplierSkuDb.isMultiSourced(context.getUPC())) {
			context.setSupplierID(supplierSkuDb.getSupplierId(context.getUPC()));
		} else {
			Assert.assertFalse("SKU is not Multi Sourced", supplierSkuDb.isMultiSourced(context.getUPC()));
		}
	}

}