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
import com.jda.wms.dataload.gm.DataSetUp;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.db.gm.PreAdviceHeaderDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Given;

public class PreAdviceHeaderStepsDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private JDAFooter jdaFooter;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private Context context;
	private JDALoginStepDefs jdaLoginStepDefs;
	private final PreAdviceHeaderDB preAdviceHeaderDB;
	private UPIReceiptHeaderDB  upiReceiptHeaderDB;
	private Verification verification;
	private DeliveryDB deliveryDB;

	@Inject
	public PreAdviceHeaderStepsDefs(JDAFooter jdaFooter,
			JDALoginStepDefs jdaLoginStepDefs, JDAHomeStepDefs jdaHomeStepDefs, Context context, PreAdviceHeaderDB preAdviceHeaderDB,UPIReceiptHeaderDB  upiReceiptHeaderDB,Verification verification,DeliveryDB deliveryDB) {
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.context = context;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.verification = verification;
		this.deliveryDB = deliveryDB;
	}

	@Given("^the PO \"([^\"]*)\" of type \"([^\"]*)\" with UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status with line items,supplier details$")
	public void the_PO_of_type_with_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(String preAdviceId,String type,
			String upiId, String asnId, String status) throws Throwable {
			context.setPreAdviceId(preAdviceId);
			context.setUpiId(upiId);
			context.setAsnId(asnId);
			context.setSKUType(type);
//			DataSetUp.setPOData();
			logger.debug("PO ID: "+preAdviceId);
			logger.debug("UPI ID: "+upiId);
			logger.debug("ASN ID: "+asnId);
			logger.debug("Type: "+type);
			
			ArrayList failureList = new ArrayList();
			Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
			
			verification.verifyData("Pre-Advice Status", status, preAdviceHeaderDB.getStatus(preAdviceId), failureList);
			verification.verifyData("UPI Status", status, upiReceiptHeaderDB.getStatus(upiId), failureList);
			verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);
			
			context.setSupplierID(preAdviceHeaderDB.getSupplierId(preAdviceId));
			int numLines = Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceId));
			Assert.assertEquals("No of Lines in PO and UPI Header do not match", upiReceiptHeaderDB.getNumberOfLines(upiId),String.valueOf(numLines));
			context.setNoOfLines(numLines);
			logger.debug("Num of Lines: "+numLines);
			
			Assert.assertTrue("PO , UPI header , Delivery details not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
}
	
	@Given("^the po status should be displayed as \"([^\"]*)\"$")
	public void the_po_status_should_be_displayed_as(String rcvStatus) throws Throwable {
		ArrayList failureList = new ArrayList();
		
		verification.verifyData("Pre-Advice Status", rcvStatus, preAdviceHeaderDB.getStatus(context.getPreAdviceId()), failureList);
		verification.verifyData("UPI Status", rcvStatus, upiReceiptHeaderDB.getStatus(context.getUpiId()), failureList);
		verification.verifyData("Delivery Status", rcvStatus, deliveryDB.getStatus(context.getAsnId()), failureList);
		
		Assert.assertTrue("PO , UPI , ASN statuss not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
		
	}
	// FSV Receiving
	@Given("^the PO \"([^\"]*)\" of type \"([^\"]*)\" and site id \"([^\"]*)\"$")
	public void the_PO_of_type_and_site_id(String preAdviceId, String siteId, String type,String status,String supplierType) throws Throwable {
		context.setPreAdviceId(preAdviceId);
		context.setSKUType(type);
		context.setsiteid(siteId);
		context.setsupplierType(supplierType);
		logger.debug("PO ID: "+preAdviceId);
		logger.debug("SITE ID: "+siteId);
		logger.debug("Type: "+type);
		logger.debug("SUPPLIER TYPE: "+supplierType);
		ArrayList failureList = new ArrayList();
		Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
		
		verification.verifyData("Pre-Advice Status", status, preAdviceHeaderDB.getStatus(preAdviceId), failureList);
		context.setSupplierID(preAdviceHeaderDB.getSupplierId(preAdviceId));
		int numLines = Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceId));
		context.setNoOfLines(numLines);
		logger.debug("Num of Lines: "+numLines);
		
	}

	@Given("^verify PO should not linked with UPI line \"([^\"]*)\"$")
	public void verify_PO_should_not_linked_with_UPI_line(String preAdviceId) throws Throwable {
		
}
}




