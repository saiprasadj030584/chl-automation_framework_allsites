
package com.jda.wms.stepdefs.gm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;

import com.google.inject.Inject;

import com.jda.wms.context.Context;
import com.jda.wms.db.gm.DeliveryDB;
import com.jda.wms.db.gm.UPIReceiptHeaderDB;
import com.jda.wms.pages.gm.UnPickingUnShippingPage;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Given;

public class UnPickingUnShippingStepDefs {
	private Context context;
	private UPIReceiptHeaderDB upiReceiptHeaderDB;
	private Verification verification;
	private DeliveryDB deliveryDB;
	private UnPickingUnShippingPage unPickingUnShippingPage; 

	@Inject
	public UnPickingUnShippingStepDefs(Context context,UPIReceiptHeaderDB upiReceiptHeaderDB,Verification verification,DeliveryDB deliveryDB,UnPickingUnShippingPage unPickingUnShippingPage) {
		this.context = context;
		this.upiReceiptHeaderDB = upiReceiptHeaderDB;
		this.verification=verification;
		this.deliveryDB=deliveryDB;
		this.unPickingUnShippingPage=unPickingUnShippingPage;
	}

@Given("^I perform unpicking$")
public void i_perform_unpicking() throws Throwable {
	unPickingUnShippingPage.performUnpick();
}



}
