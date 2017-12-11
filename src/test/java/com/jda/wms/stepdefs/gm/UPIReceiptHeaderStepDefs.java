package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.datasetup.gm.GetTcData;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.UpiReceiptHeaderPage;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class UPIReceiptHeaderStepDefs {
	private Context context;
	private UPIReceiptHeaderDB upiReceiptHeaderDB;
	private DeliveryDB deliveryDB;
	private Verification verification;
	private UpiReceiptHeaderPage upiReceiptHeaderPage;
	private UPIReceiptLineDB uPIReceiptLineDB;
	private GetTcData getTcData;

	@Inject
	public UPIReceiptHeaderStepDefs(Context context, UPIReceiptHeaderDB upiReceiptHeaderDB, DeliveryDB deliveryDB,
			Verification verification, UpiReceiptHeaderPage upiReceiptHeaderPage, UPIReceiptLineDB uPIReceiptLineDB,GetTcData getTcData) {
		this.context = context;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.deliveryDB = deliveryDB;
		this.verification = verification;
		this.upiReceiptHeaderPage = upiReceiptHeaderPage;
		this.uPIReceiptLineDB = uPIReceiptLineDB;
		this.getTcData = getTcData;
	}

	@Given("^ASN to be linked with upi header$")
	public void asn_to_be_linked_with_upi_header() throws Throwable {
		upiReceiptHeaderDB.updateASN(context.getUpiId(), context.getAsnId());
	}

	@Given("^SSSC_URN_to_be_updated_with_multiple_upi_header$")
	public void SSSC_URN_to_be_updated_with_multiple_upi_header() throws Throwable {
		for (int i = 0; i < context.getUpiList().size(); i++) {
			upiReceiptHeaderDB.updateSSSCURN(context.getUpiList().get(i));
		}
	}
	@Given("^order header line to be linked with upi header line$")
	public void order_header_line_to_be_linked_with_upi_header_line() throws Throwable {
		// String type1 =
		// uPIReceiptLineDB.getUserDefinedType1(context.getUpiId());
		// String type2 = orderLineDB.getUserDefinedType1(context.getOrderId());
		// Assert.assertEquals("User defined tab 1 does not match", type1,
		// type2);
		upiReceiptHeaderDB.updateASN(context.getUpiId(), context.getAsnId());
		uPIReceiptLineDB.updatePreAdviceID(context.getPreAdviceId(), context.getUpiId(), null);

	}

	@Given("^ASN and container to be linked with upi header$")
	public void asn_and_container_to_be_linked_with_upi_header() throws Throwable {
		upiReceiptHeaderDB.updateASN(context.getUpiId(), context.getAsnId());

		ArrayList skuList = uPIReceiptLineDB.getSkuIdList(context.getUpiId());
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			String sku = (String) skuList.get(i - 1);
			uPIReceiptLineDB.updateContainerID(context.getUpiId(), sku);
		}
		uPIReceiptLineDB.updateContainerID(context.getUpiId());
	}

	@When("^i enter palletId$")
	public void i_enter_palletId() throws Throwable {
		upiReceiptHeaderPage.enterPallet(context.getPalletID());
		Assert.assertTrue("Record not displayed as expected", upiReceiptHeaderPage.isNoRecordExist());
	}

	@Given("^ASN to be linked with upi header list$")
	public void asn_to_be_linked_with_multiple_upi_header() throws Throwable {
		System.out.println("size"+context.getUpiList().size());
		for (int i = 0; i < context.getUpiList().size(); i++) {
			System.out.println(context.getUpiList().get(i));
			upiReceiptHeaderDB.updateASN(context.getUpiList().get(i), context.getAsnId());
		}
	}
	
	@Given("^Multiple ASN to be linked with upi header list$")
	public void multiple_asn_to_be_linked_with_multiple_upi_header() throws Throwable {
		if(context.getAsnList().size()==context.getUpiList().size())
		{
			for(int i=0;i<context.getAsnList().size();i++)
			{
				upiReceiptHeaderDB.updateASN(context.getUpiList().get(i),context.getAsnList().get(i));
			}
		}
		else if(context.getAsnList().size()<context.getUpiList().size())
		{
			int i=0;
			while(i<context.getAsnList().size()-1)
			{
				upiReceiptHeaderDB.updateASN(context.getUpiList().get(i),context.getAsnList().get(i));
				i++;
			}
			while(i!=context.getUpiList().size())
			{
				upiReceiptHeaderDB.updateASN(context.getUpiList().get(i),context.getAsnList().get(context.getAsnList().size()-1));
				i++;
			}
//			for(int i=0;i<context.getAsnList().size();i++)
//			{
//				if(i<context.getAsnList().size()-1)
//				{
//				upiReceiptHeaderDB.updateASN(context.getUpiList().get(i),context.getAsnList().get(i));
//				}
//				else
//				{
//					upiReceiptHeaderDB.updateASN(context.getUpiList().get(i),context.getAsnList().get(context.getAsnList().size()-1));
//				}
//			}
		}
	}

	@Given("^SSSC_URN_to_be_updated_with_upi_header$")
	public void SSSC_URN_to_be_updated_with_upi_header() throws Throwable {
		upiReceiptHeaderDB.updateSSSCURN(context.getUpiId());
	}

	@Given("^the pallet and asn status should be displayed as \"([^\"]*)\"$")
	public void the_pallet_and_asn_status_should_be_displayed_as(String rcvStatus) throws Throwable {
		ArrayList failureList = new ArrayList();
		verification.verifyData("UPI Status", rcvStatus, upiReceiptHeaderDB.getStatus(context.getUpiId()), failureList);
		verification.verifyData("Delivery Status", rcvStatus, deliveryDB.getStatus(context.getAsnId()), failureList);
		Assert.assertTrue(
				"UPI , ASN statuss not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
	}

	@Given("^the UPI and ASN of type \"([^\"]*)\" should be in \"([^\"]*)\" status for IDT$")
	public void the_UPI_and_ASN_of_type_should_be_in_status_for_IDT(String datatype, String status) throws Throwable {
		String upiId = getTcData.getUpi();
		String asnId = getTcData.getAsn();
		context.setSKUType(datatype);
	//	context.setUpiId(upiId);
	//	context.setAsnId(asnId);
		String ShippingType = "ZIDC";
		ArrayList failureList = new ArrayList();
		verification.verifyData("UPI Status", status, upiReceiptHeaderDB.getStatus(upiId), failureList);
		verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);
		verification.verifyData("Shipping Type", ShippingType, upiReceiptHeaderDB.getShippingType(upiId), failureList);

		int numLines = Integer.parseInt(upiReceiptHeaderDB.getNumberOfLines(upiId));
		Assert.assertEquals("No of Lines in PO and UPI Header do not match", upiReceiptHeaderDB.getNumberOfLines(upiId),
				String.valueOf(numLines));
		context.setNoOfLines(numLines);
		Assert.assertTrue("UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
	
	@Given("^the UPI of type \"([^\"]*)\"and ASN should be in \"([^\"]*)\" status for IDT$")
	public void the_UPI_of_type_and_ASN_should_be_in_status_for_IDT(String type,String status) throws Throwable {

		// String upiId = getTcData.getUpi();
		// String asnId = getTcData.getAsn();

	//	String upiId = "56490001384579299100395756000210";
	//	String asnId = "0000003184";

		String upiId = context.getUpiId();
		String asnId = context.getAsnId();

		context.setUpiId(upiId);

		context.setAsnId(asnId);
		context.setSKUType(type);
		String ShippingType = "ZIDC";
		ArrayList failureList = new ArrayList();
		System.out.println("entered here");
		verification.verifyData("UPI Status", status, upiReceiptHeaderDB.getStatus(upiId), failureList);
		verification.verifyData("Delivery Status", status, deliveryDB.getStatus(asnId), failureList);
		verification.verifyData("Shipping Type", ShippingType, upiReceiptHeaderDB.getShippingType(upiId), failureList);

		int numLines = Integer.parseInt(upiReceiptHeaderDB.getNumberOfLines(upiId));
		Assert.assertEquals("No of Lines in PO and UPI Header do not match", upiReceiptHeaderDB.getNumberOfLines(upiId),
				String.valueOf(numLines));
		context.setNoOfLines(numLines);
		Assert.assertTrue("UPI header , Delivery details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
}