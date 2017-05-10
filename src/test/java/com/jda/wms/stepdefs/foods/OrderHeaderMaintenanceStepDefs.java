package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.OrderHeaderMaintenancePage;

import cucumber.api.java.en.Given;

public class OrderHeaderMaintenanceStepDefs {
	private OrderHeaderMaintenancePage orderHeaderMaintenancePage;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private JDAFooter jdaFooter;

	@Inject
	public void OrderHeaderStepDefs(OrderHeaderMaintenancePage orderHeaderMaintenancePage,
			JDAHomeStepDefs jdaHomeStepDefs, JDAFooter jdaFooter) {
		this.orderHeaderMaintenancePage = orderHeaderMaintenancePage;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.jdaFooter = jdaFooter;
	}

	@Given("^the STO \"([^\"]*)\" should be \"([^\"]*)\" status with type \"([^\"]*)\" and have order date,from site id, number of lines,created by,more task status,order time,creation date and creation time in the order header maintenance table$")
	public void the_STO_should_be_status_with_type_and_have_order_date_from_site_id_number_of_lines_created_by_more_task_status_order_time_creation_date_and_creation_time_in_the_order_header_maintenance_table(
			String orderID, String status, String type) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		jdaHomeStepDefs.i_navigate_to_order_header();
		jdaFooter.clickQueryButton();
		orderHeaderMaintenancePage.enterOrderNo(orderID);
		jdaFooter.clickExecuteButton();

		String statusOrderHeader = orderHeaderMaintenancePage.getOrderStatus();
		System.out.println(statusOrderHeader);
		if (!statusOrderHeader.equals(status)) {
			failureList.add("Status is not as expected. Expected [" + status + "] but was [" + statusOrderHeader + "]");
		}

		String orderdate = orderHeaderMaintenancePage.getOrderDate();
		if (orderdate.equals(null)) {
			failureList.add("OrderDate is not as expected. Expected [Not NULL] but was [" + orderdate + "]");
		}

		String createdby = orderHeaderMaintenancePage.getCreatedBy();
		if (createdby.equals(null)) {
			failureList.add("CreatedBy is not as expected. Expected [Not NULL] but was [" + createdby + "]");
		}

		String ordertime = orderHeaderMaintenancePage.getOrderTime();
		if (ordertime.equals(null)) {
			failureList.add("OrderTime is not as expected. Expected [Not NULL] but was [" + ordertime + "]");
		}

		String creationdate = orderHeaderMaintenancePage.getCreationDate();
		if (creationdate.equals(null)) {
			failureList.add("OrderTime is not as expected. Expected [Not NULL] but was [" + creationdate + "]");
		}
		String creationtime = orderHeaderMaintenancePage.getCreationTime();
		if (creationtime.equals(null)) {
			failureList.add("CreationTime is not as expected. Expected [Not NULL] but was [" + creationtime + "]");
		}

		String movetaskstatus = orderHeaderMaintenancePage.getMoveTaskStatus();
		if (movetaskstatus.equals(null)) {
			failureList.add("CreationTime is not as expected. Expected [Not NULL] but was [" + movetaskstatus + "]");
		}
		String fromsiteId = orderHeaderMaintenancePage.getFromSiteId();
		if (fromsiteId.equals(null)) {
			failureList.add(" From SiteId is not as expected. Expected [Not NULL] but was [" + fromsiteId + "]");
		}

		boolean isType = orderHeaderMaintenancePage.isTypeExist();
		if (!isType) {
			failureList.add("Type is not displayed as RDC");
		}
		String numberoflines = orderHeaderMaintenancePage.getNumberOfLines();
		if (numberoflines.equals(null)) {
			failureList.add(" Number Of Lines is not as expected. Expected [Not NULL] but was [" + numberoflines + "]");
		}
		Assert.assertTrue(
				"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the order should have delivery address details in delivery address tab$")
	public void the_order_should_have_delivery_address_details_in_delivery_address_tab() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		orderHeaderMaintenancePage.clickDeliveryAddressTab();
	

String customer = orderHeaderMaintenancePage.getCustomer();
	if(customer.equals(null))	{
		failureList.add("Customer is not as expected. Expected [Not NULL] but was [" + customer + "]");
	}
	String name = orderHeaderMaintenancePage.getName();
	if (name.equals(null)) {
		failureList.add(" Name is not as expected. Expected [Not NULL] but was [" +name + "]");
	}
	String address1 = orderHeaderMaintenancePage.getAddress1();
	if (address1.equals(null)) {
		failureList.add(" Address1 is not as expected. Expected [Not NULL] but was [" +address1 + "]");
	}
	String country = orderHeaderMaintenancePage.getCountry();
	if (country.equals(null)) {
		failureList.add(" Country is not as expected. Expected [Not NULL] but was [" +country + "]");
	}
	Assert.assertTrue(
			"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
			failureList.isEmpty());
}
	
	
	@Given("^the order should have delivery details in delivery details and user defined tabs$")
	public void the_order_should_have_delivery_details_in_delivery_details_and_user_defined_tabs() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		orderHeaderMaintenancePage.clickDeliveryDetailsTab();
		
		String shipbydate = orderHeaderMaintenancePage.getShipByDate();
		if (shipbydate.equals(null)) {
			failureList.add("Ship By Date is not as expected. Expected [Not NULL] but was [" +shipbydate + "]");
		}
		String shipbytime = orderHeaderMaintenancePage.getShipByTime();
		if (shipbytime.equals(null)) {
			failureList.add("Ship By Time is not as expected. Expected [Not NULL] but was [" +shipbytime + "]");
		}
		String deliverybytime = orderHeaderMaintenancePage.getDeliveryByTime();
		if (deliverybytime.equals(null)) {
			failureList.add("Delivery By Time is not as expected. Expected [Not NULL] but was [" +deliverybytime+ "]");
		}
		String deliverybydate = orderHeaderMaintenancePage.getDeliveryByDate();
		if (deliverybydate.equals(null)) {
			failureList.add("Delivery By Date is not as expected. Expected [Not NULL] but was [" +deliverybydate+ "]");
		}
		Assert.assertTrue(
				"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	
		
		orderHeaderMaintenancePage.clickUserDefinedTab();
			
		
}
}
