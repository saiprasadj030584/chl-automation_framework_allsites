package com.jda.wms.stepdefs.foods;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.OrderHeaderDB;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.SystemAllocationPage;

import cucumber.api.java.en.When;

public class SystemAllocationStepDefs {
	private SystemAllocationPage systemAllocationPage;
	private JDAFooter jDAFooter;
	private OrderHeaderDB orderHeaderDB;
	private Context context;
	private JDAHomeStepDefs jdaHomeStepDefs;
	
	@Inject
	public SystemAllocationStepDefs(SystemAllocationPage systemAllocationPage, JDAFooter jDAFooter,
			OrderHeaderDB orderHeaderDB, Context context,JDAHomeStepDefs jdaHomeStepDefs) {
		this.systemAllocationPage = systemAllocationPage;
		this.jDAFooter = jDAFooter;
		this.orderHeaderDB = orderHeaderDB;
		this.context = context;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
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
	
	@When("^I system allocate the order$")	
	public void i_system_allocate_the_order() throws Throwable {
		jdaHomeStepDefs.i_navigate_to_system_allocation_page();
		jDAFooter.clickNextButton();
		Thread.sleep(2000);
		i_have_order_id_with_soft_allocated_status(context.getOrderId());
		jDAFooter.clickNextButton();
		Thread.sleep(2000);
		the_record_should_be_displayed_in_system_allocation();
	i_proceed_to_allocate_the_record();
	}

	@When("^I proceed to allocate the record$")
	public void i_proceed_to_allocate_the_record() throws Throwable {
		jDAFooter.clickNextButton();
		jDAFooter.clickDoneButton();
		Thread.sleep(6000);
		jDAFooter.clickDoneButton();
	}

}
