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
import com.jda.wms.dataload.gm.DataSetUp;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.db.gm.PreAdviceHeaderDB;
import com.jda.wms.db.gm.PreAdviceLineDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;

public class PreAdviceHeaderStepsDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private Context context;
	private JDALoginStepDefs jdaLoginStepDefs;
	private final PreAdviceHeaderDB preAdviceHeaderDB;
	private final PreAdviceLineDB preAdviceLineDB;
	private UPIReceiptHeaderDB upiReceiptHeaderDB;
	private UPIReceiptLineDB upiReceiptLineDB;
	private Verification verification;
	private DeliveryDB deliveryDB;

	@Inject
	public PreAdviceHeaderStepsDefs(JDAFooter jdaFooter, JDALoginStepDefs jdaLoginStepDefs,
			JDAHomeStepDefs jdaHomeStepDefs, Context context, PreAdviceHeaderDB preAdviceHeaderDB,
			UPIReceiptHeaderDB upiReceiptHeaderDB, Verification verification, DeliveryDB deliveryDB,
			UPIReceiptLineDB upiReceiptLineDB, PreAdviceLineDB preAdviceLineDB) {
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.context = context;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.preAdviceLineDB = preAdviceLineDB;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.verification = verification;
		this.deliveryDB = deliveryDB;
	}

	@Given("^the PO \"([^\"]*)\" of type \"([^\"]*)\" with UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status with line items,supplier details$")
	public void the_PO_of_type_with_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(String preAdviceId,
			String type, String upiId, String asnId, String status) throws Throwable {
		context.setPreAdviceId(preAdviceId);
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		context.setSKUType(type);
		// DataSetUp.setPOData();
		logger.debug("PO ID: " + preAdviceId);
		logger.debug("UPI ID: " + upiId);
		logger.debug("ASN ID: " + asnId);
		logger.debug("Type: " + type);

		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();

		verification.verifyData("Pre-Advice Status", status, preAdviceHeaderDB.getStatus(preAdviceId), failureList);
		verification.verifyData("UPI Status", status, upiReceiptHeaderDB.getStatus(upiId), failureList);
		verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);

		context.setSupplierID(preAdviceHeaderDB.getSupplierId(preAdviceId));
		int numLines = Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceId));
		Assert.assertEquals("No of Lines in PO and UPI Header do not match", upiReceiptHeaderDB.getNumberOfLines(upiId),
				String.valueOf(numLines));
		context.setNoOfLines(numLines);
		logger.debug("Num of Lines: " + numLines);

		Assert.assertTrue("PO , UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@Given("^the po status should be displayed as \"([^\"]*)\"$")
	public void the_po_status_should_be_displayed_as(String rcvStatus) throws Throwable {
		ArrayList failureList = new ArrayList();

		verification.verifyData("Pre-Advice Status", rcvStatus, preAdviceHeaderDB.getStatus(context.getPreAdviceId()),
				failureList);
		verification.verifyData("UPI Status", rcvStatus, upiReceiptHeaderDB.getStatus(context.getUpiId()), failureList);
		verification.verifyData("Delivery Status", rcvStatus, deliveryDB.getStatus(context.getAsnId()), failureList);

		Assert.assertTrue(
				"PO , UPI , ASN statuss not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());

	}

	// FSV Receiving
	@Given("^the PO \"([^\"]*)\" of type \"([^\"]*)\" should be in \"([^\"]*)\" status at site id \"([^\"]*)\"$")
	public void the_PO_of_type_should_be_in_status_at_site_id(String preAdviceId, String type, String status,
			String siteId) throws Throwable {
		context.setPreAdviceId(preAdviceId);
		context.setSKUType(type);
		context.setsiteid(siteId);
		context.setsupplierType("FSV");
		logger.debug("PO ID: " + preAdviceId);
		logger.debug("SITE ID: " + siteId);
		logger.debug("Type: " + type);
		logger.debug("SUPPLIER TYPE: " + context.getsupplierType());
		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();

		verification.verifyData("Pre-Advice Status", status, preAdviceHeaderDB.getStatus(preAdviceId), failureList);
		verification.verifyData("FSV/Direct PO", context.getsupplierType(),
				preAdviceHeaderDB.getUserDefType5(preAdviceId), failureList);
		context.setSupplierID(preAdviceHeaderDB.getSupplierId(preAdviceId));
		int numLines = Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceId));
		context.setNoOfLines(numLines);
		logger.debug("Num of Lines: " + numLines);

		// To generate Pallet ID
		context.setPalletID(generatePalletID(preAdviceId));
		context.setBelCode(generateBelCode(preAdviceId));
		Assert.assertTrue("FSV PO details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	private String generateBelCode(String preAdviceId) throws ClassNotFoundException, SQLException {
		String belCode = null;
		// Checkdigit : 2 any random number
		String checkdigit = Utilities.getEightDigitRandomNumber();
		
		// UPC : 8 digit
		String upc = preAdviceLineDB.getupc(context.getSkuId());
		// Checkbit hardcoded : 2 digit
		String checkbit = "10";
        belCode = checkdigit + suppliermanipulate(preAdviceId) + preAdviceLineDB.getupc(context.getSkuId())+skuqtymanipulate(preAdviceId)+checkbit;
        
		return belCode;
	}
	// individual sku qty assigned to pre advice id : 3 digit

	public String skuqtymanipulate(String preAdviceId) throws ClassNotFoundException, SQLException {
		String qtydue = preAdviceLineDB.getQtyDue(preAdviceId,context.getSkuId());
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

	private String generatePalletID(String preAdviceId) throws ClassNotFoundException, SQLException {
		String palletID = null;
		// First 4 digits - Site id
		String siteid = preAdviceHeaderDB.getSiteID(preAdviceId);
		// Hardcoded 3 digit
		String barcode = "950";
		// Random generated 6 digit
		String URN = Utilities.getSixDigitRandomNumber();

		// Advice - 6 digit
		String advice = preAdviceHeaderDB.getUserDefType1(preAdviceId);

		// checkbit - 2 digit
		String checkbit = Utilities.getTwoDigitRandomNumber();
		palletID = siteid + barcode + URN + suppliermanipulate(preAdviceId) + deptmanipulate(preAdviceId) + advice
				+ qtymanipulate(preAdviceId) + checkbit;

		return palletID;
	}

	// Get supplierid - 4 digit and manipulated to get only integer
	public String[] suppliermanipulate(String preAdviceId) throws ClassNotFoundException, SQLException {
		String supplier = preAdviceHeaderDB.getSupplierId(preAdviceId);
		String[] supplierSplit = supplier.split("M");
		for (int i = 1; i < supplierSplit.length; i++) {
			System.out.println(supplierSplit[i]);
		}
		return supplierSplit;
	}

	// Get dept - 3 digit
	public String[] deptmanipulate(String preAdviceId) throws ClassNotFoundException, SQLException {
		String dept = preAdviceHeaderDB.getUserDefType2(preAdviceId);
		String[] deptSplit = dept.split("T");
		for (int i = 1; i < deptSplit.length; i++) {
			System.out.println("0" + deptSplit[i]);
		}
		return deptSplit;
	}

	// Sum of quantity from the line - 3 digit
	public String qtymanipulate(String preAdviceId) throws ClassNotFoundException, SQLException {
		String sumqty = preAdviceLineDB.getQtyDue(preAdviceId,context.getSkuId());
		String sumLength = String.valueOf(sumqty.length());
		if (sumLength.equals("1")) {
			sumqty = "00" + sumqty;
			// System.out.println(sumqty);
		} else if (sumLength.equals("2")) {
			sumqty = "0" + sumqty;
			// System.out.println(sumqty);
		}
		return sumqty;
	}

	// FSV receiving
	@Given("^verify PO should not linked with UPI line \"([^\"]*)\"$")
	public void verify_PO_should_not_linked_with_UPI_line(String preAdviceId) throws Throwable {
		context.setPreAdviceId(preAdviceId);
		logger.debug("PO ID: " + preAdviceId);
		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
		verification.verifyDataempty("Pre advice id is not linked with UPI", context.getPreAdviceId(),
				upiReceiptLineDB.getPreAdviceId(context.getPreAdviceId()), failureList);

	}
}
