package com.jda.wms.stepdefs.gm;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.DeliveryDB;

import cucumber.api.java.en.Given;

public class DeliveryStepDefs {
	private DeliveryDB deliveryDB;
	private Context context;

	@Inject
	public DeliveryStepDefs(DeliveryDB deliveryDB,Context context) {
		this.deliveryDB = deliveryDB;
		this.context = context;
	}

	@Given("^the pallet count should be updated in delivery$")
	public void the_pallet_count_should_be_updated_in_delivery() throws Throwable {
		deliveryDB.updatePalletCount(context.getAsnId(),context.getNoOfLines());
	}
	
	@Given("^the pallet count should be updated as \"([^\"]*)\" in delivery$")
	public void the_pallet_count_should_be_updated_as_in_delivery(int count) throws Throwable {
		deliveryDB.updatePalletCount(context.getAsnId(),count);
	}
}
