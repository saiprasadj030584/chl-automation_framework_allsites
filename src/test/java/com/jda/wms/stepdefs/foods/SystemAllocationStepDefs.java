package com.jda.wms.stepdefs.foods;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.db.OrderHeaderDB;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.SystemAllocationPage;

import cucumber.api.java.en.When;

public class SystemAllocationStepDefs {
	private SystemAllocationPage systemAllocationPage;
	private JDAFooter jDAFooter;
	private OrderHeaderDB orderHeaderDB;

	@Inject
	public SystemAllocationStepDefs(SystemAllocationPage systemAllocationPage, JDAFooter jDAFooter,
			OrderHeaderDB orderHeaderDB) {
		this.systemAllocationPage = systemAllocationPage;
		this.jDAFooter = jDAFooter;
		this.orderHeaderDB = orderHeaderDB;
	}

	@When("^I have order id \"([^\"]*)\" with soft allocated status$")
	public void i_have_order_id_with_soft_allocated_status(String orderId) throws Throwable {
		String softAllocated = orderHeaderDB.getSoftAllocated(orderId);
		if (softAllocated == null) {
			orderHeaderDB.updateSoftAllocated(orderId);
		}
		systemAllocationPage.enterOrderId(orderId);
	}

	@When("^the record should be displayed in system allocation$")
	public void the_record_should_be_displayed_in_system_allocation() throws Throwable {
		Assert.assertTrue("Record is not displayed for the order in System allocation", systemAllocationPage.isRecordExist());
	}

	@When("^I proceed to allocate the record$")
	public void i_proceed_to_allocate_the_record() throws Throwable {
		jDAFooter.clickNextButton();
		jDAFooter.clickDoneButton();
		Thread.sleep(6000);
		jDAFooter.clickDoneButton();
	}

}
