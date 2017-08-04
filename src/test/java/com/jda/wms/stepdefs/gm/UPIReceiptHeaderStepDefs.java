
package com.jda.wms.stepdefs.gm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;

import com.google.inject.Inject;

import com.jda.wms.context.Context;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Given;

public class UPIReceiptHeaderStepDefs {
	private Context context;
	private UPIReceiptHeaderDB upiReceiptHeaderDB;
	private Verification verification;
	private DeliveryDB deliveryDB;

	@Inject
	public UPIReceiptHeaderStepDefs(Context context,UPIReceiptHeaderDB upiReceiptHeaderDB,Verification verification,DeliveryDB deliveryDB) {
		this.context = context;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.verification=verification;
		this.deliveryDB=deliveryDB;
	}

@Given("^ASN to be linked with upi header$")
public void asn_to_be_linked_with_upi_header() throws Throwable {
	upiReceiptHeaderDB.updateASN(context.getUpiId(),context.getAsnId());
}


@Given("^ASN to be linked with multiple upi header$")
public void asn_to_be_linked_with_multiple_upi_header() throws Throwable {
	for(int i=0;i<context.getUpiList().size();i++)
	{
	upiReceiptHeaderDB.updateASN(context.getUpiList().get(i),context.getAsnId());
	}
}

@Given("^SSSC_URN_to_be_updated_with_upi_header$")
public void SSSC_URN_to_be_updated_with_upi_header() throws Throwable 
{
	upiReceiptHeaderDB.updateSSSCURN(context.getUpiId());
}

@Given("^SSSC_URN_to_be_updated_with_multiple_upi_header$")
public void SSSC_URN_to_be_updated_with_multiple_upi_header() throws Throwable 
{
	for(int i=0;i<context.getUpiList().size();i++)
	{
	upiReceiptHeaderDB.updateSSSCURN(context.getUpiList().get(i));
	}
}

@Given("^the pallet and asn status should be displayed as \"([^\"]*)\"$")
public void the_pallet_and_asn_status_should_be_displayed_as(String rcvStatus) throws Throwable {
	ArrayList failureList = new ArrayList();
	verification.verifyData("UPI Status", rcvStatus, upiReceiptHeaderDB.getStatus(context.getUpiId()), failureList);
	verification.verifyData("Delivery Status", rcvStatus, deliveryDB.getStatus(context.getAsnId()), failureList);
	Assert.assertTrue("UPI , ASN statuss not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
}



}
