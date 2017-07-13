package com.jda.wms.stepdefs.gm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.PreAdviceHeaderDB;
import com.jda.wms.db.gm.PreAdviceLineDB;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.*;

public class PreAdviceLineStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Context context;
	private Verification verification;
	private PreAdviceLineDB preAdviceLineDB;
	private UPIReceiptLineDB upiReceiptLineDB;
	private PreAdviceHeaderDB preAdviceHeaderDB;
	private SkuDB skuDB;

	@Inject
	public PreAdviceLineStepDefs(Context context, Verification verification, PreAdviceLineDB preAdviceLineDB,
			UPIReceiptLineDB upiReceiptLineDB, SkuDB skuDB, PreAdviceHeaderDB preAdviceHeaderDB) {
		this.context = context;
		this.verification = verification;
		this.preAdviceLineDB = preAdviceLineDB;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.skuDB = skuDB;
	}

	@Given("^the PO should have sku, quantity due details$")
	public void the_PO_should_have_sku_quantity_due_details() throws Throwable {
		ArrayList failureList = new ArrayList();
		ArrayList skuFromPO = new ArrayList();
		ArrayList skuFromUPI = new ArrayList();
		Map<Integer, Map<String, String>> POMap = new HashMap<Integer, Map<String, String>>();
		Map<String, Map<String, String>> UPIMap = new HashMap<String, Map<String, String>>();

		skuFromPO = preAdviceLineDB.getSkuIdList(context.getPreAdviceId());
		skuFromUPI = upiReceiptLineDB.getSkuIdList(context.getUpiId());

		if (!skuFromPO.containsAll(skuFromUPI)) {
			for (int i = 0; i < skuFromPO.size(); i++) {
				if (!skuFromPO.get(i).equals(skuFromUPI.get(i))) {
					failureList.add("SKU id " + skuFromPO.get(i) + " from PreAdvice does not match with SKU id "
							+ skuFromUPI + " from UPI for line item " + i);
				}
			}
		} else {
			// Add SKU details to PO Map
			for (int i = 1; i <= context.getNoOfLines(); i++) {
				Map<String, String> lineItemsMap = new HashMap<String, String>();
				context.setSkuId((String) skuFromPO.get(i - 1));
				lineItemsMap.put("SKU", context.getSkuId());
				lineItemsMap.put("QTY DUE", preAdviceLineDB.getQtyDue(context.getPreAdviceId(), context.getSkuId()));
				lineItemsMap.put("LINE ID", preAdviceLineDB.getLineId(context.getPreAdviceId(), context.getSkuId()));
				POMap.put(i, lineItemsMap);
			}
			context.setPOMap(POMap);

			// Add SKU details to UPI Map
			for (int i = 1; i <= context.getNoOfLines(); i++) {
				Map<String, String> lineItemsMap = new HashMap<String, String>();
				context.setSkuId((String) skuFromPO.get(i - 1));
				// lineItemsMap.put("SKU", context.getSkuId());
				lineItemsMap.put("QTY DUE", upiReceiptLineDB.getQtyDue(context.getUpiId(), context.getSkuId()));
				lineItemsMap.put("LINE ID", upiReceiptLineDB.getLineId(context.getUpiId(), context.getSkuId()));
				lineItemsMap.put("PACK CONFIG", upiReceiptLineDB.getPackConfig(context.getUpiId(), context.getSkuId()));
				lineItemsMap.put("UPC", "");
				UPIMap.put(context.getSkuId(), lineItemsMap);
			}
			context.setUPIMap(UPIMap);

			System.out.println("PO Map " + context.getPOMap());
			System.out.println("UPI Map " + context.getUPIMap());

			// To Validate Modularity,New Product Check for SKU
			String type = null;
			switch (context.getSKUType()) {
			case "Boxed":
				type = "B";
				break;
			case "Hanging":
				type = "H";
				break;
			}
			verification.verifyData("SKU Type", type, skuDB.getSKUType(context.getSkuId()), failureList);
			verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue(context.getSkuId()), failureList);
		}

		Assert.assertTrue(
				"PO line item attributes not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());

	}

	// FSV receiving

	@Given("^the FSV PO line should have sku, quantity due details$")
	public void the_FSV_PO_line_should_have_sku_quantity_due_details() throws Throwable {
		ArrayList failureList = new ArrayList();
		ArrayList skuFromPO = new ArrayList();
		Map<Integer, Map<String, String>> POMap = new HashMap<Integer, Map<String, String>>();

		skuFromPO = preAdviceLineDB.getSkuIdList(context.getPreAdviceId());

		if (skuFromPO == null) {
			failureList.add("SKU id is not assgined to the PreAdvice id : " + context.getPreAdviceId());
		} else {
			// Add SKU details to PO Map
			for (int i = 1; i <= context.getNoOfLines(); i++) {
				Map<String, String> lineItemsMap = new HashMap<String, String>();
				context.setSkuId((String) skuFromPO.get(i - 1));
				lineItemsMap.put("SKU", context.getSkuId());
				lineItemsMap.put("QTY DUE", preAdviceLineDB.getQtyDue(context.getPreAdviceId(), context.getSkuId()));
				lineItemsMap.put("LINE ID", preAdviceLineDB.getLineId(context.getPreAdviceId(), context.getSkuId()));
				POMap.put(i, lineItemsMap);
			}
			context.setPOMap(POMap);

			System.out.println("PO Map " + context.getPOMap());
			for (int i = 1; i <= context.getNoOfLines(); i++){
				context.setSkuId((String) skuFromPO.get(i - 1));
				// To generate Pallet ID
				context.setPalletID(generatePalletID(context.getPreAdviceId(),context.getSkuId()));
				// To generate Belcode
				context.setBelCode(generateBelCode(context.getPreAdviceId(),context.getSkuId()));
			}

			// To Validate Modularity,New Product Check for SKU
			String type = null;
			switch (context.getSKUType()) {
			case "Boxed":
				type = "B";
				break;
			case "Hanging":
				type = "H";
				break;
			}
			verification.verifyData("SKU Type", type, skuDB.getSKUType(context.getSkuId()), failureList);
			verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue(context.getSkuId()), failureList);
		Assert.assertTrue(
				"PO line item attributes not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	}
	private String generatePalletID(String preAdviceId, String skuid) throws ClassNotFoundException, SQLException {
		String palletID = null;
		// First 4 digits - Site id
		String siteid = preAdviceHeaderDB.getSiteID(preAdviceId);
		// Hardcoded 3 digit
		String barcode = "950";
		// Random generated 6 digit
		String URN = Utilities.getFiveDigitRandomNumber();
		//Supplier id : 5 digit
		String[] suppliermanipulate = suppliermanipulate(preAdviceId);
		// Dept id : 3 digit
		String[] deptmanipulate = deptmanipulate(preAdviceId);
		// Sku quantity  : 3 digit
		String skuqtymanipulate = skuqtymanipulate(preAdviceId,skuid);
		// Advice - 6 digit
		String advice = preAdviceHeaderDB.getUserDefType1(preAdviceId);
		// checkbit - 2 digit
		String checkbit = "10";
		palletID = siteid + barcode + URN + '0'+ suppliermanipulate[1] + '0' + deptmanipulate[1] + advice
				+ skuqtymanipulate + checkbit;
		System.out.println(palletID);
		return palletID;
	}

	// Get supplierid - 4 digit and manipulated to get only integer
	public String[] suppliermanipulate(String preAdviceId) throws ClassNotFoundException, SQLException {
		String supplier = preAdviceHeaderDB.getSupplierId(preAdviceId);
		String[] supplierSplit = supplier.split("M");
		for (int i = 0; i < supplierSplit.length; i++) {
			System.out.println(supplierSplit[i]);
		}
		return supplierSplit;
	}

	// Get dept - 3 digit
	public String[] deptmanipulate(String preAdviceId) throws ClassNotFoundException, SQLException {
		String dept = preAdviceHeaderDB.getUserDefType2(preAdviceId);
		String[] deptSplit = dept.split("T");
		for (int i = 0; i < deptSplit.length; i++) {
			System.out.println(deptSplit[i]);
		}
		return deptSplit;
	}

	/*// Sum of quantity from the line - 3 digit
	public String qtymanipulate(String preAdviceId) throws ClassNotFoundException, SQLException {
		String sumqty = preAdviceLineDB.getQtyDue(preAdviceId, context.getSkuId());
		String sumLength = String.valueOf(sumqty.length());
		if (sumLength.equals("1")) {
			sumqty = "00" + sumqty;
			// System.out.println(sumqty);
		} else if (sumLength.equals("2")) {
			sumqty = "0" + sumqty;
			// System.out.println(sumqty);
		}
		return sumqty;
	}*/

	private String generateBelCode(String preAdviceId,String skuid) throws ClassNotFoundException, SQLException {
		String belCode = null;
		// Checkdigit : 2 any random number
		String checkdigit = Utilities.getTwoDigitRandomNumber();
		// Supplier code : 5 digit
		String[] suppliermanipulate = suppliermanipulate(preAdviceId);
		// UPC : 8 digit
		String upc = preAdviceLineDB.getupc(context.getSkuId());
		//Quantity : 3 digit
		String skuqtymanipulate= skuqtymanipulate(preAdviceId,skuid);
		// Checkbit hardcoded : 2 digit
		String checkbit = "10";
		belCode = checkdigit + suppliermanipulate[1]+ upc + skuqtymanipulate + checkbit;
        System.out.println(belCode);
		return belCode;
	}
	// individual sku qty assigned to pre advice id : 3 digit

	public String skuqtymanipulate(String preAdviceId,String skuid) throws ClassNotFoundException, SQLException {
		String qtydue = preAdviceLineDB.getQtyDue(preAdviceId, context.getSkuId());
		String sumLength = String.valueOf(qtydue.length());
		if (sumLength.equals("1")) {
			qtydue = "00" + qtydue;
			// System.out.println(sumqty);
		} else if (sumLength.equals("2")) {
			qtydue = "0" + qtydue;
			// System.out.println(sumqty);
		}
		return qtydue;
	}

}
