package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.datasetup.gm.GetTcData;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.db.gm.PreAdviceHeaderDB;
import com.jda.wms.db.gm.PreAdviceLineDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.PreAdviceHeaderPage;
import com.jda.wms.pages.gm.UpiReceiptHeaderPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;

public class PreAdviceHeaderStepsDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private JDAHomeStepDefs jdaHomeStepDefs;
	private Context context;
	private JDALoginStepDefs jdaLoginStepDefs;
	private final PreAdviceHeaderDB preAdviceHeaderDB;
	private UPIReceiptHeaderDB upiReceiptHeaderDB;
	private Verification verification;
	private DeliveryDB deliveryDB;
	private JdaLoginPage jdaLoginPage;
	private PreAdviceLineStepDefs preAdviceLineStepDefs;
	private UPIReceiptLineDB upiReceiptLineDB;
	private final PreAdviceLineDB preAdviceLineDB;
	private JdaHomePage jdaHomePage;
	private JDAFooter jdaFooter;
	private PreAdviceHeaderPage preAdviceHeaderPage;
	private GetTcData getTcData;

	private UpiReceiptHeaderPage upiReceiptHeaderPage;

	@Inject
	public PreAdviceHeaderStepsDefs(JDAFooter jdaFooter, JDALoginStepDefs jdaLoginStepDefs,
			JDAHomeStepDefs jdaHomeStepDefs, Context context, PreAdviceHeaderDB preAdviceHeaderDB,
			UPIReceiptHeaderDB upiReceiptHeaderDB, Verification verification, DeliveryDB deliveryDB,
			PreAdviceLineStepDefs preAdviceLineStepDefs, PreAdviceLineDB preAdviceLineDB,
			UPIReceiptLineDB upiReceiptLineDB, JdaHomePage jdaHomePage, GetTcData getTcData) {

		this.jdaFooter = jdaFooter;
		this.jdaLoginStepDefs = jdaLoginStepDefs;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.context = context;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.verification = verification;
		this.deliveryDB = deliveryDB;
		this.preAdviceLineStepDefs = preAdviceLineStepDefs;
		this.preAdviceLineDB = preAdviceLineDB;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.jdaHomePage = jdaHomePage;
		this.getTcData = getTcData;
	}

	@Given("^the PO of type \"([^\"]*)\" with UPI and ASN should be in \"([^\"]*)\" status with line items,supplier details$")
	public void the_PO_of_type_with_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(String type,
			String status) throws Throwable {

		String upiId = getTcData.getUpi();
		String asnId = getTcData.getAsn();
		String preAdviceId = getTcData.getPo();
//		 String preAdviceId = "8408826481";
//		 String upiId = "64290414739733995946";
//		 String asnId = "8454466968";

		context.setPreAdviceId(preAdviceId);
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		context.setSKUType(type);
		logger.debug("PO ID: " + preAdviceId);
		logger.debug("UPI ID: " + upiId);
		logger.debug("ASN ID: " + asnId);
		logger.debug("Type: " + type);

		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();

		 verification.verifyData("Pre-Advice Status", status,preAdviceHeaderDB.getStatus(preAdviceId), failureList);
		 verification.verifyData("UPI Status", status, upiReceiptHeaderDB.getStatus(upiId), failureList);
		 verification.verifyData("Delivery Status", status,deliveryDB.getStatus(asnId), failureList);

		context.setSupplierID(preAdviceHeaderDB.getSupplierId(preAdviceId));
		int numLines = Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceId));
		Assert.assertEquals("No of Lines in PO and UPI Header do not match", upiReceiptHeaderDB.getNumberOfLines(upiId),
				String.valueOf(numLines));
		context.setNoOfLines(numLines);
		logger.debug("Num of Lines: " + numLines);

		Assert.assertTrue("PO , UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@Given("^the PO  should be in \"([^\"]*)\" status with line items,supplier details$")
	public void the_PO_should_be_in_status_with_line_items_supplier_details(String preAdviceId, String status)
			throws Throwable {
		context.setPreAdviceId(preAdviceId);

		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();

		verification.verifyData("Pre-Advice Status", status, preAdviceHeaderDB.getStatus(preAdviceId), failureList);

		context.setSupplierID(preAdviceHeaderDB.getSupplierId(preAdviceId));
		int numLines = Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceId));
		context.setNoOfLines(numLines);

		Assert.assertTrue("PO , UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	@Given("^the PO \"([^\"]*)\" of type \"([^\"]*)\" with multiple UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status with line items,supplier details$")
	public void the_PO_of_type_with_multiple_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(
			String preAdviceId, String type, String upiId, String asnId, String status) throws Throwable {
		context.setPreAdviceId(preAdviceId);
		ArrayList<String> upiList = new ArrayList<String>();
		String[] multipleupi = upiId.split(",");
		for (int i = 0; i < multipleupi.length; i++) {
			upiList.add(multipleupi[i]);
		}
		context.setUpiList(upiList);
		context.setAsnId(asnId);
		context.setSKUType(type);
		logger.debug("PO ID: " + preAdviceId);
		logger.debug("ASN ID: " + asnId);
		logger.debug("Type: " + type);

		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();

		verification.verifyData("Pre-Advice Status", status, preAdviceHeaderDB.getStatus(preAdviceId), failureList);
		for (int i = 0; i < upiList.size(); i++) {
			verification.verifyData("UPI " + i + " Status", status,
					upiReceiptHeaderDB.getStatus((String) upiList.get(i)), failureList);
		}
		verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);

		context.setSupplierID(preAdviceHeaderDB.getSupplierId(preAdviceId));
		int numLines = Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceId));
		for (int i = 0; i < upiList.size(); i++) {
			Assert.assertEquals("No of Lines in PO and UPI Header do not match",
					upiReceiptHeaderDB.getNumberOfLines(context.getUpiList().get(i)), String.valueOf(numLines));
		}
		context.setNoOfLines(numLines);
		logger.debug("Num of Lines: " + numLines);
		Assert.assertTrue("PO , UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

//	@Given("^the multiple PO of type \"([^\"]*)\" with multiple UPI and ASN should be in \"([^\"]*)\" status with line items,supplier details$")
//	public void the_multiple_PO_of_type_with_multiple_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(
//			String type, String status) throws Throwable {
//		String preAdviceId = getTcData.getPo();
//		String upiId = getTcData.getUpi();
//		String asnId = getTcData.getAsn();
//
//		ArrayList<String> preAdviceList = new ArrayList<String>();
//		Map<String, String> PONumLinesMap = new HashMap<String, String>();
//		Map<String, Integer> upiNumLines = new HashMap<String, Integer>();
//		String[] multiplePreAdvice = preAdviceId.split(",");
//		for (int i = 0; i < multiplePreAdvice.length; i++) {
//			preAdviceList.add(multiplePreAdvice[i]);
//		}
//		context.setPreAdviceList(preAdviceList);
//		ArrayList<String> upiList = new ArrayList<String>();
//		String[] multipleupi = upiId.split(",");
//		for (int i = 0; i < multipleupi.length; i++) {
//			upiList.add(multipleupi[i]);
//		}
//		context.setUpiList(upiList);
//		context.setAsnId(asnId);
//		context.setSKUType(type);
//		logger.debug("ASN ID: " + asnId);
//		logger.debug("Type: " + type);
//
//		ArrayList failureList = new ArrayList();
//		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
//
//		// Verify status of multiple PO
//		for (int i = 0; i < preAdviceList.size(); i++) {
//			verification.verifyData("Pre-Advice Status", status,
//					preAdviceHeaderDB.getStatus((String) preAdviceList.get(i)), failureList);
//		}
//
//		// Verify status of multiple UPI
//		for (int i = 0; i < upiList.size(); i++) {
//			verification.verifyData("UPI " + i + " Status", status,
//					upiReceiptHeaderDB.getStatus((String) upiList.get(i)), failureList);
//		}
//
//		verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);
//
//		ArrayList<String> supplierIdList = new ArrayList<String>();
//
//		int poNumLines = 0, upiNumLinesCount = 0;
//		for (int i = 0; i < preAdviceList.size(); i++) {
//			PONumLinesMap.put(context.getPreAdviceList().get(i),
//					preAdviceHeaderDB.getNumberOfLines(preAdviceList.get(i)));
//			poNumLines += Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceList.get(i)));
//		}
//
//		for (int i = 0; i < context.getUpiList().size(); i++) {
//			upiNumLines.put(context.getUpiList().get(i),
//					Integer.parseInt(upiReceiptHeaderDB.getNumberOfLines(context.getUpiList().get(i))));
//			upiNumLinesCount += Integer.parseInt(upiReceiptHeaderDB.getNumberOfLines(context.getUpiList().get(i)));
//		}
//		context.setUpiNumLinesMap(upiNumLines);
//		// System.out.println("number of lines"+numLines);
//		context.setPoNumLinesMap(PONumLinesMap);
//		if (upiNumLinesCount != poNumLines) {
//			failureList.add("No of Lines in PO and UPI Header do not match");
//		}
//		context.setNoOfLines(poNumLines);
//		Assert.assertTrue("PO , UPI header , Delivery details not displayed as expected. ["
//				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
//	}


	@Given("^the PO of type \"([^\"]*)\" should be in \"([^\"]*)\" status with line items,supplier details$")
	public void the_PO_of_type_should_be_in_status_with_line_items_supplier_details(String type, String status)
			throws Throwable {
		String preAdviceId = getTcData.getPo();

		context.setPreAdviceId(preAdviceId);
		context.setSKUType(type);
		logger.debug("PO ID: " + preAdviceId);
		logger.debug("Type: " + type);

		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();

		verification.verifyData("Pre-Advice Status", status, preAdviceHeaderDB.getStatus(preAdviceId), failureList);

		context.setSupplierID(preAdviceHeaderDB.getSupplierId(preAdviceId));
		int numLines = Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceId));
		context.setNoOfLines(numLines);

		Assert.assertTrue("PO details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
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

	@Given("^the po status should be \"([^\"]*)\" while upi and asn status should \"([^\"]*)\"$")
	public void the_po_status_should_be_while_upi_and_asn_status_should(String poStatus, String rcvStatus)
			throws Throwable {
		ArrayList failureList = new ArrayList();

		verification.verifyData("Pre-Advice Status", poStatus, preAdviceHeaderDB.getStatus(context.getPreAdviceId()),
				failureList);
		verification.verifyData("UPI Status", rcvStatus, upiReceiptHeaderDB.getStatus(context.getUpiId()), failureList);
		verification.verifyData("Delivery Status", rcvStatus, deliveryDB.getStatus(context.getAsnId()), failureList);

		Assert.assertTrue(
				"PO , UPI , ASN statuss not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Given("^the po status should be displayed as \"([^\"]*)\" for all the po$")
	public void the_po_status_should_be_displayed_as_for_all_the_po(String rcvStatus) throws Throwable {
		ArrayList failureList = new ArrayList();
		for (int i = 0; i < context.getPreAdviceList().size(); i++) {
			verification.verifyData("Pre-Advice Status", rcvStatus,
					preAdviceHeaderDB.getStatus(context.getPreAdviceList().get(i)), failureList);
		}
		for (int i = 0; i < context.getUpiList().size(); i++) {
			verification.verifyData("UPI Status", rcvStatus, upiReceiptHeaderDB.getStatus(context.getUpiList().get(i)),
					failureList);
		}
		verification.verifyData("Delivery Status", rcvStatus, deliveryDB.getStatus(context.getAsnId()), failureList);
		Assert.assertTrue(
				"PO , UPI , ASN statuss not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Given("^the po status should be displayed as \"([^\"]*)\" for blind receive$")
	public void the_po_status_should_be_displayed_as_for_blind_receive(String rcvStatus) throws Throwable {
		ArrayList failureList = new ArrayList();

		verification.verifyData("UPI Status", rcvStatus, upiReceiptHeaderDB.getStatus(context.getUpiId()), failureList);
		verification.verifyData("Delivery Status", rcvStatus, deliveryDB.getStatus(context.getAsnId()), failureList);
		Assert.assertTrue(
				" UPI , ASN statuss not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Given("^the FSV po status should be displayed as \"([^\"]*)\"$")
	public void the_FSV_po_status_should_be_displayed_as(String rcvStatus) throws Throwable {
		Assert.assertEquals("PO status not displayed as expected", rcvStatus,
				preAdviceHeaderDB.getStatus(context.getPreAdviceId()));
	}

	@Given("^the upi status should be displayed as \"([^\"]*)\" for blind receive$")
	public void the_upi_status_should_be_displayed_as_for_blind_receive(String rcvStatus) throws Throwable {
		ArrayList failureList = new ArrayList();
		verification.verifyData("UPI Status", rcvStatus, upiReceiptHeaderDB.getStatus(context.getUpiId()), failureList);
		verification.verifyData("Delivery Status", rcvStatus, deliveryDB.getStatus(context.getAsnId()), failureList);
		Assert.assertTrue("UPI , ASN status not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	// FSV Receiving

	@Given("^the FSV PO of type \"([^\"]*)\" should be in \"([^\"]*)\" status at site id$")
	public void the_FSV_PO_of_type_should_be_in_status_at_site_id(String type, String status) throws Throwable {

		context.setSiteID("5649");
		// String preAdviceId = getTcData.getPo();
		// String preAdviceId = "25400900903";

		String preAdviceId = context.getPreAdviceId();
		String siteId = context.getSiteID();

		// String preAdviceId = getTcData.getPo();
		// String siteId = context.getSiteId();

		context.setPreAdviceId(preAdviceId);
		// String siteId ="5649";
		context.setSKUType(type);
		context.setSiteID(siteId);
		context.setsupplierType("FSV");
		logger.debug("PO ID: " + preAdviceId);
		logger.debug("SITE ID: " + siteId);
		logger.debug("Type: " + type);
		logger.debug("SUPPLIER TYPE: " + context.getsupplierType());
		ArrayList failureList = new ArrayList();

		verification.verifyData("Pre-Advice Status", status, preAdviceHeaderDB.getStatus(preAdviceId), failureList);
		verification.verifyData("FSV/Direct PO", context.getsupplierType(),
				preAdviceHeaderDB.getUserDefType5(preAdviceId), failureList);
		context.setSupplierID(preAdviceHeaderDB.getSupplierId(preAdviceId));

		int numLines = Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceId));
		context.setNoOfLines(numLines);
		logger.debug("Num of Lines: " + numLines);

		// Updating advice ID
		context.setAdviceId(Utilities.getSixDigitRandomNumber());
		preAdviceHeaderDB.updateAdviceForSku(context.getPreAdviceId(), context.getAdviceId());

		Assert.assertTrue("FSV PO details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	// FSV receiving
	@Given("^the PO should not be linked with UPI line$")
	public void the_PO_should_not_be_linked_with_UPI_line() throws Throwable {
		String preAdviceId = context.getPreAdviceId();
		boolean isUPIRecordExists = upiReceiptLineDB.isUPIRecordExists(preAdviceId);
		logger.debug("PO ID: " + preAdviceId);
		Assert.assertFalse("Pre Advice ID is linked to UPI for FSV PO", isUPIRecordExists);
	}

	@Given("^the PO should be updated with valid advice number$")
	public void the_PO_should__be_updated_with_valid_advice_number() throws Throwable {
		// hgih
		// String adviceNumber = context.getPreAdviceId();
		// preAdviceLineDB.updateAdviceNumber(adviceNumber,context.getPreAdviceId());
		// preAdviceHeaderDB.updateAdviceNumber(adviceNumber,context.getPreAdviceId());

	}

	@Given("^I have an invalid UPI$")
	public void i_have_an_invalid_UPI() throws Throwable {
		String upi = Utilities.get32DigitRandomNumber();
		Assert.assertFalse("Record found not as expected", upiReceiptHeaderDB.isRecordExistsForPalletId(upi));
	}

	@Given("^the idt status should be displayed as \"([^\"]*)\"$")
	public void the_idt_status_should_be_displayed_as(String rcvStatus) throws Throwable {
		ArrayList failureList = new ArrayList();

		verification.verifyData("UPI Status", rcvStatus, upiReceiptHeaderDB.getStatus(context.getUpiId()), failureList);
		verification.verifyData("Delivery Status", rcvStatus, deliveryDB.getStatus(context.getAsnId()), failureList);
		Assert.assertTrue(
				" UPI , ASN statuss not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Given("^I update the compliance flag in pre advice header$")
	public void i_update_the_compliance_flag_in_pre_advice_header() throws Throwable {
		for (int i = 0; i < context.getNoOfLines(); i++) {
			preAdviceHeaderDB.updateComplianceFlag(context.getPreAdviceId());
		}
	}

	

	@Given("^the multiple PO of type \"([^\"]*)\" with multiple UPI and ASN should be in \"([^\"]*)\" status with line items,supplier details$")
	public void the_multiple_PO_of_type_with_multiple_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(
			String type,String status) throws Throwable {
		
		ArrayList<String> upiList = new ArrayList<String>();
		ArrayList<String> preAdviceList = new ArrayList<String>();
		
		preAdviceList=context.getPoList();
		upiList=context.getUpiList();
		String asnId=context.getAsnId();
		
		
		
		Map<String, String> PONumLinesMap = new HashMap<String, String>();
		Map<String, Integer> upiNumLines = new HashMap<String, Integer>();
//		String[] multiplePreAdvice = preAdviceId.split(",");
//		for (int i = 0; i < multiplePreAdvice.length; i++) {
//			preAdviceList.add(multiplePreAdvice[i]);
//		}
		context.setPreAdviceList(preAdviceList);
		
//		String[] multipleupi = upiId.split(",");
//		for (int i = 0; i < multipleupi.length; i++) {
//			upiList.add(multipleupi[i]);
//		}
		context.setUpiList(upiList);
		context.setAsnId(asnId);
		context.setSKUType(type);
		logger.debug("ASN ID: " + asnId);
		logger.debug("Type: " + type);

		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();

		// Verify status of multiple PO
		for (int i = 0; i < preAdviceList.size(); i++) {
			verification.verifyData("Pre-Advice Status", status,
					preAdviceHeaderDB.getStatus((String) preAdviceList.get(i)), failureList);
		}

		// Verify status of multiple UPI
		for (int i = 0; i < upiList.size(); i++) {
			verification.verifyData("UPI " + i + " Status", status,
					upiReceiptHeaderDB.getStatus((String) upiList.get(i)), failureList);
		}

		verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);

		ArrayList<String> supplierIdList = new ArrayList<String>();

		int poNumLines = 0, upiNumLinesCount = 0;
		for (int i = 0; i < preAdviceList.size(); i++) {
			PONumLinesMap.put(context.getPreAdviceList().get(i),
					preAdviceHeaderDB.getNumberOfLines(preAdviceList.get(i)));
			poNumLines += Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceList.get(i)));
		}

		for (int i = 0; i < context.getUpiList().size(); i++) {
			upiNumLines.put(context.getUpiList().get(i),
					Integer.parseInt(upiReceiptHeaderDB.getNumberOfLines(context.getUpiList().get(i))));
			upiNumLinesCount += Integer.parseInt(upiReceiptHeaderDB.getNumberOfLines(context.getUpiList().get(i)));
		}
		context.setUpiNumLinesMap(upiNumLines);
		// System.out.println("number of lines"+numLines);
		context.setPoNumLinesMap(PONumLinesMap);
		if (upiNumLinesCount != poNumLines) {
			failureList.add("No of Lines in PO and UPI Header do not match");
		}
		
		//sku match check
		
		
		
		context.setNoOfLines(poNumLines);
		Assert.assertTrue("PO , UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Given("^the single PO of type \"([^\"]*)\" with multiple UPI and multiple ASN should be in \"([^\"]*)\" status with line items,supplier details$")
	public void the_single_PO_of_type_with_multiple_UPI_and_multiple_ASN_should_be_in_status_with_line_items_supplier_details(
			String type,String status) throws Throwable {
		
		ArrayList<String> upiList = new ArrayList<String>();
		ArrayList<String> asnList = new ArrayList<String>();
		
		String preAdviceId=context.getPreAdviceId();
		upiList=context.getUpiList();
		asnList=context.getAsnList();
		
		
		ArrayList<String> preAdviceList = new ArrayList<String>();
		Map<String, String> PONumLinesMap = new HashMap<String, String>();
		Map<String, Integer> upiNumLines = new HashMap<String, Integer>();
		String[] multiplePreAdvice = preAdviceId.split(",");
		for (int i = 0; i < multiplePreAdvice.length; i++) {
			preAdviceList.add(multiplePreAdvice[i]);
		}
		context.setPreAdviceList(preAdviceList);
		
//		String[] multipleupi = upiId.split(",");
//		for (int i = 0; i < multipleupi.length; i++) {
//			upiList.add(multipleupi[i]);
//		}
		context.setUpiList(upiList);
		context.setAsnList(asnList);
		context.setSKUType(type);
		logger.debug("ASN ID: " + asnList);
		logger.debug("Type: " + type);

		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();

		// Verify status of multiple PO
		for (int i = 0; i < preAdviceList.size(); i++) {
			verification.verifyData("Pre-Advice Status", status,
					preAdviceHeaderDB.getStatus((String) preAdviceList.get(i)), failureList);
		}

		// Verify status of multiple UPI
		for (int i = 0; i < upiList.size(); i++) {
			verification.verifyData("UPI " + i + " Status", status,
					upiReceiptHeaderDB.getStatus((String) upiList.get(i)), failureList);
		}

		for (int i = 0; i < asnList.size(); i++) {
			verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnList.get(i)), failureList);
		}
		

		ArrayList<String> supplierIdList = new ArrayList<String>();

		int poNumLines = 0, upiNumLinesCount = 0;
		for (int i = 0; i < preAdviceList.size(); i++) {
			PONumLinesMap.put(context.getPreAdviceList().get(i),
					preAdviceHeaderDB.getNumberOfLines(preAdviceList.get(i)));
			poNumLines += Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceList.get(i)));
		}

		for (int i = 0; i < context.getUpiList().size(); i++) {
			upiNumLines.put(context.getUpiList().get(i),
					Integer.parseInt(upiReceiptHeaderDB.getNumberOfLines(context.getUpiList().get(i))));
			upiNumLinesCount += Integer.parseInt(upiReceiptHeaderDB.getNumberOfLines(context.getUpiList().get(i)));
		}
		context.setUpiNumLinesMap(upiNumLines);
		// System.out.println("number of lines"+numLines);
		context.setPoNumLinesMap(PONumLinesMap);
		System.out.println("UPI NO LINES MAP"+context.getUpiNumLinesMap());
		System.out.println("PO NO LINES MAP"+context.getPoNumLinesMap());
		if (upiNumLinesCount != poNumLines) {
			failureList.add("No of Lines in PO and UPI Header do not match");
		}
		
		//sku match check
		
		
		
		context.setNoOfLines(poNumLines);
		Assert.assertTrue("PO , UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}




	@Given("^the compliance details should be updated in preadvice header$")
	public void the_compliance_flag_details_should_be_updated_in_preadvice_header () throws Throwable {
		Assert.assertEquals("Compliance Flag not updated", "COMP",
				preAdviceHeaderDB.getComplianceFlag(context.getPreAdviceId()));
	} 
	
	/*@Given("^the multiple UPI and ASN should be in status with line items supplier detail$")
	public void the_multiple_UPI_and_ASN_should_be_in_status_with_line_items_supplier_detail(String upiId,
			String asnId, String status) throws Throwable {
		ArrayList<String> upiList = new ArrayList<String>();
		String[] multipleupi = upiId.split(",");
		for (int i = 0; i < multipleupi.length; i++) {
			upiList.add(multipleupi[i]);
		}
		context.setUpiList(upiList);
		context.setAsnId(asnId);
		logger.debug("ASN ID: " + asnId);

		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
		for (int i = 0; i < context.getUpiList().size(); i++) {
			verification.verifyData("UPI Status", status, upiReceiptHeaderDB.getStatus(context.getUpiList().get(i)),
					failureList);
		}
		verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);
		Assert.assertTrue("UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}*/
	

	@Given("^the PO of type \"([^\"]*)\" with UPI and ASN with line items,supplier details$")
	public void the_PO_of_type_with_UPI_and_ASN_with_line_items_supplier_details(String type) throws Throwable {
		String upiId = getTcData.getUpi();
		String asnId = getTcData.getAsn();
		String preAdviceId = getTcData.getPo();

		context.setPreAdviceId(preAdviceId);
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		context.setSKUType(type);
		logger.debug("PO ID: " + preAdviceId);
		logger.debug("UPI ID: " + upiId);
		logger.debug("ASN ID: " + asnId);
		logger.debug("Type: " + type);

		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
		// verification.verifyData("Delivery Status", status,
		// deliveryDB.getStatus(asnId), failureList);

		context.setSupplierID(preAdviceHeaderDB.getSupplierId(preAdviceId));
		int numLines = Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceId));
		Assert.assertEquals("No of Lines in PO and UPI Header do not match", upiReceiptHeaderDB.getNumberOfLines(upiId),
				String.valueOf(numLines));
		context.setNoOfLines(numLines);
		logger.debug("Num of Lines: " + numLines);

		Assert.assertTrue("PO , UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Given("^the multiple UPI and ASN should be in status with line items supplier details$")
	public void the_multiple_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(
			) throws Throwable {
		String status=context.getStatus();
		String asnId=context.getAsnId();
		String upiId=context.getUpiId();
		System.out.println("upi id"+ upiId);
		ArrayList<String> upiList = new ArrayList<String>();
		String[] multipleupi = upiId.split(",");
		for (int i = 0; i < multipleupi.length; i++) {
			upiList.add(multipleupi[i]);
		}
		context.setUpiList(upiList);
		context.setAsnId(asnId);
		logger.debug("ASN ID: " + asnId);

		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
		for (int i = 0; i < context.getUpiList().size(); i++) {
			verification.verifyData("UPI Status", status, upiReceiptHeaderDB.getStatus(context.getUpiList().get(i)),
					failureList);
		}
		verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);
		Assert.assertTrue("UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	
	@Given("^the UPI and ASN should be in status with line items supplier details$")
	public void the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(String status) throws Throwable {

		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
		context.setSupplier(deliveryDB.getSupplier(context.getAsnId()));
		 verification.verifyData("UPI Status", status,
		 upiReceiptHeaderDB.getStatus(context.getUpiId()), failureList);
		 verification.verifyData("Delivery Status", status,
		 deliveryDB.getStatus(context.getAsnId()), failureList);
		Assert.assertTrue("UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Given("^the single PO of type \"([^\"]*)\" with multiple UPI and ASN should be in \"([^\"]*)\" status with line items,supplier details$")
	public void the_single_PO_of_type_with_multiple_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(
			String type,String status) throws Throwable {
		
		ArrayList<String> upiList = new ArrayList<String>();
		
		String preAdviceId=context.getPreAdviceId();
		upiList=context.getUpiList();
		String asnId=context.getAsnId();
		
//		String preAdviceId="9878802191";
//		upiList.add("43703526722963820890");
//		upiList.add("73383866535580426493");
//		String asnId="9042636881";
		
		
		ArrayList<String> preAdviceList = new ArrayList<String>();
		Map<String, String> PONumLinesMap = new HashMap<String, String>();
		Map<String, Integer> upiNumLines = new HashMap<String, Integer>();
		String[] multiplePreAdvice = preAdviceId.split(",");
		for (int i = 0; i < multiplePreAdvice.length; i++) {
			preAdviceList.add(multiplePreAdvice[i]);
		}
		context.setPreAdviceList(preAdviceList);
		
//		String[] multipleupi = upiId.split(",");
//		for (int i = 0; i < multipleupi.length; i++) {
//			upiList.add(multipleupi[i]);
//		}
		context.setUpiList(upiList);
		context.setAsnId(asnId);
		context.setSKUType(type);
		logger.debug("ASN ID: " + asnId);
		logger.debug("Type: " + type);

		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();

		// Verify status of multiple PO
		for (int i = 0; i < preAdviceList.size(); i++) {
			verification.verifyData("Pre-Advice Status", status,
					preAdviceHeaderDB.getStatus((String) preAdviceList.get(i)), failureList);
		}

		// Verify status of multiple UPI
		for (int i = 0; i < upiList.size(); i++) {
			verification.verifyData("UPI " + i + " Status", status,
					upiReceiptHeaderDB.getStatus((String) upiList.get(i)), failureList);
		}

		verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);

		ArrayList<String> supplierIdList = new ArrayList<String>();

		int poNumLines = 0, upiNumLinesCount = 0;
		for (int i = 0; i < preAdviceList.size(); i++) {
			PONumLinesMap.put(context.getPreAdviceList().get(i),
					preAdviceHeaderDB.getNumberOfLines(preAdviceList.get(i)));
			poNumLines += Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceList.get(i)));
		}

		for (int i = 0; i < context.getUpiList().size(); i++) {
			upiNumLines.put(context.getUpiList().get(i),
					Integer.parseInt(upiReceiptHeaderDB.getNumberOfLines(context.getUpiList().get(i))));
			upiNumLinesCount += Integer.parseInt(upiReceiptHeaderDB.getNumberOfLines(context.getUpiList().get(i)));
		}
		context.setUpiNumLinesMap(upiNumLines);
		// System.out.println("number of lines"+numLines);
		context.setPoNumLinesMap(PONumLinesMap);
		if (upiNumLinesCount != poNumLines) {
			failureList.add("No of Lines in PO and UPI Header do not match");
		}
		
		//sku match check
		
		
		
		context.setNoOfLines(poNumLines);
		Assert.assertTrue("PO , UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}

	

}