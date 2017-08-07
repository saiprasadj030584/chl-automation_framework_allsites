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
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.db.gm.PreAdviceHeaderDB;
import com.jda.wms.db.gm.PreAdviceLineDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.PreAdviceHeaderPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.stepdefs.rdt.PurchaseOrderReceivingStepDefs;

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
	private UPIReceiptLineDB upiReceiptLineDB;
	private final PreAdviceLineDB preAdviceLineDB;
	private JdaHomePage jdaHomePage;
	private PreAdviceHeaderPage preAdviceHeaderPage;
	

	@Inject
	public PreAdviceHeaderStepsDefs(JDAFooter jdaFooter,
			JDALoginStepDefs jdaLoginStepDefs, JDAHomeStepDefs jdaHomeStepDefs, Context context, PreAdviceHeaderDB preAdviceHeaderDB,UPIReceiptHeaderDB  upiReceiptHeaderDB,Verification verification,DeliveryDB deliveryDB,PreAdviceLineStepDefs preAdviceLineStepDefs,PreAdviceLineDB preAdviceLineDB,UPIReceiptLineDB upiReceiptLineDB,JdaHomePage jdaHomePage) {
		this.jdaFooter = jdaFooter;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.context = context;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.verification = verification;
		this.deliveryDB = deliveryDB;
		this.preAdviceLineStepDefs=preAdviceLineStepDefs;
		this.preAdviceLineDB = preAdviceLineDB;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.jdaHomePage=jdaHomePage;
		
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
	
	@Given("^the PO \"([^\"]*)\" of type \"([^\"]*)\" with multiple UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status with line items,supplier details$")
	public void the_PO_of_type_with_multiple_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(String preAdviceId,String type,
			String upiId,String asnId, String status) throws Throwable {
			context.setPreAdviceId(preAdviceId);
			ArrayList<String> upiList = new ArrayList<String>();
			String[] multipleupi = upiId.split(",");
			for(int i=0;i<multipleupi.length;i++)
			{
				upiList.add(multipleupi[i]);
			}
			context.setUpiList(upiList);
			context.setAsnId(asnId);
			context.setSKUType(type);
			logger.debug("PO ID: "+preAdviceId);
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
			for(int i=0;i<upiList.size();i++)
			{
			Assert.assertEquals("No of Lines in PO and UPI Header do not match", upiReceiptHeaderDB.getNumberOfLines(context.getUpiList().get(i)),String.valueOf(numLines));
			}
			context.setNoOfLines(numLines);
			logger.debug("Num of Lines: "+numLines);
			Assert.assertTrue("PO , UPI header , Delivery details not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
}
	
	@Given("^the multiple PO \"([^\"]*)\" of type \"([^\"]*)\" with multiple UPI \"([^\"]*)\" and ASN \"([^\"]*)\" should be in \"([^\"]*)\" status with line items,supplier details$")
	public void the_multiple_PO_of_type_with_multiple_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(String preAdviceId,String type,
			String upiId,String asnId, String status) throws Throwable {
		ArrayList<String> preAdviceList = new ArrayList<String>();
		Map<String,String> PONumLinesMap = new HashMap<String,String>();
		Map<String,Integer> upiNumLines = new HashMap<String,Integer>();
		String[] multiplePreAdvice = preAdviceId.split(",");
		for(int i=0;i<multiplePreAdvice.length;i++)
		{
			preAdviceList.add(multiplePreAdvice[i]);
		}
		context.setPreAdviceList(preAdviceList);
			ArrayList<String> upiList = new ArrayList<String>();
			String[] multipleupi = upiId.split(",");
			for(int i=0;i<multipleupi.length;i++)
			{
				upiList.add(multipleupi[i]);
			}
			context.setUpiList(upiList);
			context.setAsnId(asnId);
			context.setSKUType(type);
			logger.debug("ASN ID: "+asnId);
			logger.debug("Type: "+type);
			
			ArrayList failureList = new ArrayList();
			Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
			for(int i=0;i<preAdviceList.size();i++)
			{
				System.out.println("predavice"+preAdviceList.get(i));
			verification.verifyData("Pre-Advice Status", status, preAdviceHeaderDB.getStatus((String)preAdviceList.get(i)), failureList);
			}
			for(int i=0;i<upiList.size();i++)
			{
			verification.verifyData("UPI "+i+" Status", status, upiReceiptHeaderDB.getStatus((String)upiList.get(i)), failureList);
			}
			verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);
			
			ArrayList<String> supplierIdList = new ArrayList<String>();
			for(int i=0;i<preAdviceList.size();i++)
			{
				String supplierId=preAdviceHeaderDB.getSupplierId(preAdviceList.get(i));
				supplierIdList.add(supplierId);
			}
			context.setSupplierIdList(supplierIdList);
			int poNumLines=0,upiNumLinesCount=0;
			for(int i=0;i<preAdviceList.size();i++)
			{
			PONumLinesMap.put(context.getPreAdviceList().get(i),preAdviceHeaderDB.getNumberOfLines(preAdviceList.get(i)));
			poNumLines+= Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceList.get(i)));
			}
			
			for(int i=0;i<context.getUpiList().size();i++)
			{
				upiNumLines.put(context.getUpiList().get(i), Integer.parseInt(upiReceiptHeaderDB.getNumberOfLines(context.getUpiList().get(i))));
				upiNumLinesCount +=Integer.parseInt(upiReceiptHeaderDB.getNumberOfLines(context.getUpiList().get(i)));
			}
			context.setUpiNumLinesMap(upiNumLines);
			//System.out.println("number of lines"+numLines);
			context.setPoNumLinesMap(PONumLinesMap);
			Assert.assertEquals("No of Lines in PO and UPI Header do not match", upiNumLinesCount,poNumLines);
			context.setNoOfLines(poNumLines);
			//logger.debug("Num of Lines: "+numLines);
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
			Assert.assertTrue("UPI header , Delivery details not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
}
	
	@Given("^the multiple UPI and ASN should be in status with line items supplier details$")
	public void the_multiple_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(
			String upiId, String asnId, String status) throws Throwable {
		ArrayList<String> upiList = new ArrayList<String>();
		String[] multipleupi = upiId.split(",");
		for(int i=0;i<multipleupi.length;i++)
		{
			upiList.add(multipleupi[i]);
		}
		context.setUpiList(upiList);
		context.setAsnId(asnId);
			logger.debug("ASN ID: "+asnId);
			
			ArrayList failureList = new ArrayList();
			Map<Integer, ArrayList<String>> tagIDMap = new HashMap<Integer, ArrayList<String>>();
			for(int i=0;i<context.getUpiList().size();i++)
			{
			verification.verifyData("UPI Status", status, upiReceiptHeaderDB.getStatus(context.getUpiList().get(i)), failureList);
			}
			verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);
			Assert.assertTrue("UPI header , Delivery details not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
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

	
	
	
	@Given("^the po status should be displayed as \"([^\"]*)\"$")
	public void the_po_status_should_be_displayed_as(String rcvStatus) throws Throwable {
		ArrayList failureList = new ArrayList();
		verification.verifyData("Pre-Advice Status", rcvStatus, preAdviceHeaderDB.getStatus(context.getPreAdviceId()), failureList);
		verification.verifyData("UPI Status", rcvStatus, upiReceiptHeaderDB.getStatus(context.getUpiId()), failureList);
		verification.verifyData("Delivery Status", rcvStatus, deliveryDB.getStatus(context.getAsnId()), failureList);
		Assert.assertTrue("PO , UPI , ASN statuss not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Given("^the po status should be displayed as \"([^\"]*)\" for all the po$")
	public void the_po_status_should_be_displayed_as_for_all_the_po(String rcvStatus) throws Throwable {
		ArrayList failureList = new ArrayList();
		for(int i=0;i<context.getPreAdviceList().size();i++)
		{
		verification.verifyData("Pre-Advice Status", rcvStatus, preAdviceHeaderDB.getStatus(context.getPreAdviceList().get(i)), failureList);
		}
		for(int i=0;i<context.getUpiList().size();i++)
		{
		verification.verifyData("UPI Status", rcvStatus, upiReceiptHeaderDB.getStatus(context.getUpiList().get(i)), failureList);
		}
		verification.verifyData("Delivery Status", rcvStatus, deliveryDB.getStatus(context.getAsnId()), failureList);
		Assert.assertTrue("PO , UPI , ASN statuss not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	
	
	@Given("^the po status should be displayed as \"([^\"]*)\" for blind receive$")
	public void the_po_status_should_be_displayed_as_for_blind_receive(String rcvStatus) throws Throwable {
		ArrayList failureList = new ArrayList();
		verification.verifyData("UPI Status", rcvStatus, upiReceiptHeaderDB.getStatus(context.getUpiId()), failureList);
		verification.verifyData("Delivery Status", rcvStatus, deliveryDB.getStatus(context.getAsnId()), failureList);
		Assert.assertTrue("UPI , ASN statuss not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
		@Given("^the FSV po status should be displayed as \"([^\"]*)\"$")
	public void the_FSV_po_status_should_be_displayed_as(String rcvStatus) throws Throwable {
			jdaHomePage.navigateToPreAdviceHeaderPage();
			preAdviceHeaderPage.queryWithPreadviceId();
		Assert.assertEquals("PO status not displayed as expected", rcvStatus, preAdviceHeaderDB.getStatus(context.getPreAdviceId()));
	}
	
	// FSV Receiving
	@Given("^the FSV PO \"([^\"]*)\" of type \"([^\"]*)\" should be in \"([^\"]*)\" status at site id \"([^\"]*)\"$")
	public void the_FSV_PO_of_type_should_be_in_status_at_site_id(String preAdviceId, String type, String status,
			String siteId) throws Throwable {
		context.setPreAdviceId(preAdviceId);
		context.setSKUType(type);
		context.setSiteId(siteId);
		context.setsupplierType("FSV");
		logger.debug("PO ID: " + preAdviceId);
		logger.debug("SITE ID: " + siteId);
		logger.debug("Type: " + type);
		logger.debug("SUPPLIER TYPE: " + context.getsupplierType());
		ArrayList failureList = new ArrayList();

		verification.verifyData("Pre-Advice Status", status, preAdviceHeaderDB.getStatus(preAdviceId), failureList);
		verification.verifyData("FSV/Direct PO", context.getsupplierType(),preAdviceHeaderDB.getUserDefType5(preAdviceId), failureList);
		context.setSupplierID(preAdviceHeaderDB.getSupplierId(preAdviceId));
		
		int numLines = Integer.parseInt(preAdviceHeaderDB.getNumberOfLines(preAdviceId));
		context.setNoOfLines(numLines);
		logger.debug("Num of Lines: " + numLines);
		Assert.assertTrue("FSV PO details not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}
	
	// FSV receiving
	@Given("^the PO should not be linked with UPI line \"([^\"]*)\"$")
	public void the_PO_should_not_be_linked_with_UPI_line(String preAdviceId) throws Throwable {
		context.setPreAdviceId(preAdviceId);
		boolean isUPIRecordExists = upiReceiptLineDB.isUPIRecordExists(preAdviceId);
		logger.debug("PO ID: " + preAdviceId);
		Assert.assertFalse("Pre Advice ID is linked to UPI for FSV PO",isUPIRecordExists);
	}
	}
