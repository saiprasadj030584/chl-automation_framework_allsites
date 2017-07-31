
package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Assert;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
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

	@Inject
	public UPIReceiptHeaderStepDefs(Context context, UPIReceiptHeaderDB upiReceiptHeaderDB, DeliveryDB deliveryDB,
			Verification verification, UpiReceiptHeaderPage upiReceiptHeaderPage) {
		this.context = context;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.deliveryDB = deliveryDB;
		this.verification = verification;
		this.upiReceiptHeaderPage = upiReceiptHeaderPage;
	}

	@Given("^ASN to be linked with upi header$")
	public void asn_to_be_linked_with_upi_header() throws Throwable {
		upiReceiptHeaderDB.updateASN(context.getUpiId(), context.getAsnId());
	}

	@When("^i enter palletId$")
	public void i_enter_palletId() throws Throwable {
		upiReceiptHeaderPage.enterPallet(context.getPalletID());
		Assert.assertTrue("Record not displayed as expected", upiReceiptHeaderPage.isNoRecordExist());
	}

	@Given("^ASN to be linked with upi header list$")
	public void asn_to_be_linked_with_multiple_upi_header() throws Throwable {
		for (int i = 0; i < context.getUpiList().size(); i++) {
			upiReceiptHeaderDB.updateASN(context.getUpiList().get(i), context.getAsnId());
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
}
