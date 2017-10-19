package com.jda.wms.stepdefs.gm;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.datasetup.gm.GetTcData;
import com.jda.wms.db.gm.PreAdviceHeaderDB;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.stepdefs.rdt.PurchaseOrderReceivingStepDefs;

import cucumber.api.java.en.Given;

public class PreReceivingStepDefs {
	private PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs;
	private PreAdviceLineStepDefs preAdviceLineStepDefs;
	private PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs;
	private PreAdviceHeaderDB preAdviceHeaderDB;
	private GetTcData getTcData;
	private JdaLoginPage jdaLoginPage;

	@Inject
	public PreReceivingStepDefs(PreAdviceHeaderStepsDefs preAdviceHeaderStepsDefs,
			PreAdviceLineStepDefs preAdviceLineStepDefs, JdaLoginPage jdaLoginPage,
			PurchaseOrderReceivingStepDefs purchaseOrderReceivingStepDefs, PreAdviceHeaderDB preAdviceHeaderDB,
			Verification verification, GetTcData getTcData) {
		this.preAdviceHeaderStepsDefs = preAdviceHeaderStepsDefs;
		this.preAdviceLineStepDefs = preAdviceLineStepDefs;
		this.purchaseOrderReceivingStepDefs = purchaseOrderReceivingStepDefs;
		this.preAdviceHeaderDB = preAdviceHeaderDB;
		this.getTcData = getTcData;
		this.jdaLoginPage = jdaLoginPage;
	}

	@Given("^the PO, UPI, ASN of type \"([^\"]*)\" details should be displayed$")
	public void the_PO_UPI_ASN_of_type_details_should_be_displayed(String type) throws Throwable {

		String preAdviceId = getTcData.getPo();
		String upiId = getTcData.getUpi();
		String asnId = getTcData.getAsn();

		preAdviceHeaderStepsDefs
				.the_PO_of_type_with_UPI_and_ASN_should_be_in_status_with_line_items_supplier_details(type, "Released");
		preAdviceLineStepDefs.the_PO_should_have_sku_quantity_due_details();
		purchaseOrderReceivingStepDefs
				.the_pallet_count_should_be_updated_in_delivery_asn_to_be_linked_with_upi_header_and_po_to_be_linked_with_upi_line();
		Assert.assertNotNull("Supplier ID not displayed as expected", preAdviceHeaderDB.getSupplierId(preAdviceId));
	}

	@Given("^the PO should be in \"([^\"]*)\" status$")
	public void the_PO_should_be_in_status(String status) throws Throwable {
		// String preAdviceId = getTcData.getPo();
		String preAdviceId = "9317110312";

		System.out.println("preAdviceId " + preAdviceId);
		preAdviceHeaderStepsDefs.the_PO_should_be_in_status_with_line_items_supplier_details(preAdviceId, "Released");
	}

	@Given("^the PO of type \"([^\"]*)\" details should be displayed$")
	public void the_PO_of_type_details_should_be_displayed(String type) throws Throwable {
		// String preAdviceId = getTcData.getPo();
		String preAdviceId = "9317010307";

		preAdviceHeaderStepsDefs.the_PO_of_type_should_be_in_status_with_line_items_supplier_details(preAdviceId, type,
				"Released");
		Assert.assertNotNull("Supplier ID not displayed as expected", preAdviceHeaderDB.getSupplierId(preAdviceId));

	}

}
