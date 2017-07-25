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
	private PreAdviceLineStepDefs preAdviceLineStepDefs;

	@Inject
	public PreAdviceHeaderStepsDefs(JDAFooter jdaFooter,
			JDALoginStepDefs jdaLoginStepDefs, JDAHomeStepDefs jdaHomeStepDefs, Context context, PreAdviceHeaderDB preAdviceHeaderDB,UPIReceiptHeaderDB  upiReceiptHeaderDB,Verification verification,DeliveryDB deliveryDB,PreAdviceLineStepDefs preAdviceLineStepDefs) {
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.context = context;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.verification = verification;
		this.deliveryDB = deliveryDB;
		this.preAdviceLineStepDefs=preAdviceLineStepDefs;
	}

	@Given("^the PO \"([^\"]*)\" of type \"([^\"]*)\" with UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status with line items,supplier details$")
	public void the_PO_of_type_with_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(String preAdviceId,String type,
			String upiId, String asnId, String status) throws Throwable {
			context.setPreAdviceId(preAdviceId);
			context.setUpiId(upiId);
			context.setAsnId(asnId);
			context.setSKUType(type);
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
	
	@Given("^the PO \"([^\"]*)\" of type \"([^\"]*)\" with UPI \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status with line items,supplier details$")
	public void the_PO_of_type_with_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(String preAdviceId,String type,
			String upiId1,String upiId2,String upiId3,String asnId, String status) throws Throwable {
			context.setPreAdviceId(preAdviceId);
			ArrayList upiList = new ArrayList();
			upiList.add(upiId1);
			upiList.add(upiId2);
			upiList.add(upiId3);
			
			//context.setUpiId(upiId);
			context.setUpiList(upiList);
			context.setAsnId(asnId);
			context.setSKUType(type);
			logger.debug("PO ID: "+preAdviceId);
			logger.debug("UPI ID: "+upiList.get(0));
			logger.debug("UPI ID: "+upiList.get(1));
			logger.debug("UPI ID: "+upiList.get(2));
			logger.debug("ASN ID: "+asnId);
			logger.debug("Type: "+type);
			
			ArrayList failureList = new ArrayList();
			Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
			
			verification.verifyData("Pre-Advice Status", status, preAdviceHeaderDB.getStatus(preAdviceId), failureList);
			for(int i=0;i<upiList.size();i++)
			{
			verification.verifyData("UPI "+i+" Status", status, upiReceiptHeaderDB.getStatus((String)upiList.get(i)), failureList);
			}
			verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);
			
			context.setSupplierID(preAdviceHeaderDB.getSupplierId(preAdviceId));
			int numLines = Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceId));
			System.out.println("check1"+numLines);
			System.out.println("check2"+upiReceiptHeaderDB.getNumberOfLines(context.getUpiList()));
			Assert.assertEquals("No of Lines in PO and UPI Header do not match", upiReceiptHeaderDB.getNumberOfLines(context.getUpiList()),String.valueOf(numLines));
			context.setNoOfLines(numLines);
			logger.debug("Num of Lines: "+numLines);
			Assert.assertTrue("PO , UPI header , Delivery details not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
}

	
	@Given("^the UPI and ASN should be in status with line items supplier details$")
	public void the_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(
			String upiId, String asnId, String status) throws Throwable {
			
			context.setUpiId(upiId);
			context.setAsnId(asnId);
			logger.debug("UPI ID: "+upiId);
			logger.debug("ASN ID: "+asnId);
			
			ArrayList failureList = new ArrayList();
			Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
			
			verification.verifyData("UPI Status", status, upiReceiptHeaderDB.getStatus(upiId), failureList);
			verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);
			
			Assert.assertTrue("PO , UPI header , Delivery details not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
}

	
	@Given("^the PO \"([^\"]*)\" of type \"([^\"]*)\" should be in \"([^\"]*)\" status with line items,supplier details$")
	public void the_PO_of_type_should_be_in_status_with_line_items_supplier_details(String preAdviceId,String type,
			String status) throws Throwable {
			context.setPreAdviceId(preAdviceId);
			context.setSKUType(type);
			logger.debug("PO ID: "+preAdviceId);
			logger.debug("Type: "+type);
			
			ArrayList failureList = new ArrayList();
			Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
			
			verification.verifyData("Pre-Advice Status", status, preAdviceHeaderDB.getStatus(preAdviceId), failureList);
			
			context.setSupplierID(preAdviceHeaderDB.getSupplierId(preAdviceId));
			int numLines = Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceId));
			context.setNoOfLines(numLines);
			
			Assert.assertTrue("PO details not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
}

	
	@Given("^the PO \"([^\"]*)\" of type \"([^\"]*)\" with UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status and locked with code \"([^\"]*)\"$")
	public void the_PO_of_type_with_UPI_and_ASN_should_be_in_status_and_locked_with_code(String preAdviceId,String type, String upiId,String asnId, String status, String lockCode) throws Throwable{
		context.setPreAdviceId(preAdviceId);
		context.setUpiId(upiId);
		context.setAsnId(asnId);
		context.setSKUType(type);
		context.setLockCode(lockCode);
		
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
		
		preAdviceLineStepDefs.the_PO_should_have_sku_quantity_due_details();
//		purchaseOrderReceivingStepDefs.the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_and_po_to_be_linked_with_upi_line();
//		preAdviceLineStepDefs.i_lock_the_product_with_lock_code(lockCode);
	}
	
	@Given("^the po status should be displayed as \"([^\"]*)\"$")
	public void the_po_status_should_be_displayed_as(String rcvStatus) throws Throwable {
		ArrayList failureList = new ArrayList();
		verification.verifyData("Pre-Advice Status", rcvStatus, preAdviceHeaderDB.getStatus(context.getPreAdviceId()), failureList);
		verification.verifyData("UPI Status", rcvStatus, upiReceiptHeaderDB.getStatus(context.getUpiId()), failureList);
		verification.verifyData("Delivery Status", rcvStatus, deliveryDB.getStatus(context.getAsnId()), failureList);
		Assert.assertTrue("PO , UPI , ASN statuss not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	
	}
