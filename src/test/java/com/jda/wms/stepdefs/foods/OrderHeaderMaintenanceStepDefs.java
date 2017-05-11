package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.AddressMaintenancePage;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.OrderHeaderMaintenancePage;

import cucumber.api.java.en.Given;

public class OrderHeaderMaintenanceStepDefs {
	private OrderHeaderMaintenancePage orderHeaderMaintenancePage;
	private JDAHomeStepDefs jdaHomeStepDefs;
	private JDAFooter jdaFooter;
	private Context context;
	private AddressMaintenancePage addressMaintenancePage;

	@Inject
	public void OrderHeaderStepDefs(OrderHeaderMaintenancePage orderHeaderMaintenancePage,
			JDAHomeStepDefs jdaHomeStepDefs, JDAFooter jdaFooter, Context context,
			AddressMaintenancePage addressMaintenancePage) {
		this.orderHeaderMaintenancePage = orderHeaderMaintenancePage;
		this.jdaHomeStepDefs = jdaHomeStepDefs;
		this.jdaFooter = jdaFooter;
		this.context = context;
		this.addressMaintenancePage = addressMaintenancePage;
	}

	@Given("^the bulk pick order \"([^\"]*)\" should be \"([^\"]*)\" status, order details in the order header maintenance table$")
	public void the_bulk_pick_order_should_be_status_order_details_in_the_order_header_maintenance_table(String orderID,
			String status) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		jdaHomeStepDefs.i_navigate_to_order_header();
		jdaFooter.clickQueryButton();
		orderHeaderMaintenancePage.enterOrderNo(orderID);
		jdaFooter.clickExecuteButton();

		String status1 = orderHeaderMaintenancePage.getStatus();
		System.out.println(status1);
		if (!status1.equals(status1)) {
			failureList.add("Status is not as expected. Expected [" + status1 + "] but was [" + status1 + "]");
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
		String type = orderHeaderMaintenancePage.getType();
		if (!type.equals("RDC")) {
			failureList.add("Type is not as expected. Expected [RDC] but was [" + type + "]");
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
		context.setCustomer(customer);
		if (customer.equals(null)) {
			failureList.add("Customer is not as expected. Expected [Not NULL] but was [" + customer + "]");
		}
		String name = orderHeaderMaintenancePage.getName();
		if (name.equals(null)) {
			failureList.add(" Name is not as expected. Expected [Not NULL] but was [" + name + "]");
		}
		String address1 = orderHeaderMaintenancePage.getAddress1();
		if (address1.equals(null)) {
			failureList.add(" Address1 is not as expected. Expected [Not NULL] but was [" + address1 + "]");
		}
		String country = orderHeaderMaintenancePage.getCountry();
		if (country.equals(null)) {
			failureList.add(" Country is not as expected. Expected [Not NULL] but was [" + country + "]");
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
			failureList.add("Ship By Date is not as expected. Expected [Not NULL] but was [" + shipbydate + "]");
		}
		String shipbytime = orderHeaderMaintenancePage.getShipByTime();
		if (shipbytime.equals(null)) {
			failureList.add("Ship By Time is not as expected. Expected [Not NULL] but was [" + shipbytime + "]");
		}
		String deliverybytimefromdeliverydetailstab = orderHeaderMaintenancePage
				.getDeliveryByTimeFromDeliveryDetailsTab();
		if (deliverybytimefromdeliverydetailstab.equals(null)) {
			failureList.add("Delivery By Time is not as expected. Expected [Not NULL] but was ["
					+ deliverybytimefromdeliverydetailstab + "]");
		}
		String deliverybydatefromdeliverydetailstab = orderHeaderMaintenancePage
				.getDeliveryByDateFromDeliveryDetailsTab();
		if (deliverybydatefromdeliverydetailstab.equals(null)) {
			failureList.add("Delivery By Date is not as expected. Expected [Not NULL] but was ["
					+ deliverybydatefromdeliverydetailstab + "]");
		}

		orderHeaderMaintenancePage.clickUserDefinedTab();

		String deliverytype = orderHeaderMaintenancePage.getDeliveryType();
		if (deliverytype.equals(null)) {
			failureList.add("Delivery Type is not as expected. Expected [Not NULL] but was [" + deliverytype + "]");
		}
		String deliverybytimefromuserdefinedtab = orderHeaderMaintenancePage.getDeliveryByTimeFromUserDefinedTab();
		if (deliverybytimefromuserdefinedtab.equals(null)) {
			failureList.add("Delivery By Time From User Defined  tab is not as expected. Expected [Not NULL] but was ["
					+ deliverybytimefromuserdefinedtab + "]");
		}
		String deliverybydatefromuserdefinedtab = orderHeaderMaintenancePage.getDeliveryByDateFromUserDefinedtab();
		if (deliverybydatefromuserdefinedtab.equals(null)) {
			failureList.add("Delivery By Time From User Defined  tab is not as expected. Expected [Not NULL] but was ["
					+ deliverybydatefromuserdefinedtab + "]");
		}
		Assert.assertTrue(
				"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the STO \"([^\"]*)\" should be \"([^\"]*)\" status, order details in the order header maintenance table$")
	public void the_STO_should_be_status_order_details_in_the_order_header_maintenance_table(String orderID,
			String status) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		jdaHomeStepDefs.i_navigate_to_order_header();
		jdaFooter.clickQueryButton();
		orderHeaderMaintenancePage.enterOrderNo(orderID);
		jdaFooter.clickExecuteButton();

		String status1 = orderHeaderMaintenancePage.getStatus();
		System.out.println(status1);
		if (!status1.equals(status1)) {
			failureList.add("Status is not as expected. Expected [" + status1 + "] but was [" + status1 + "]");
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
		String type = orderHeaderMaintenancePage.getType();
		if (!type.equals("STR")) {
			failureList.add("Type is not as expected. Expected [STR] but was [" + type + "]");
		}
		String numberoflines = orderHeaderMaintenancePage.getNumberOfLines();
		if (numberoflines.equals(null)) {
			failureList.add(" Number Of Lines is not as expected. Expected [Not NULL] but was [" + numberoflines + "]");
		}
		Assert.assertTrue(
				"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());

	}

	@Given("^the customer should have CSSM flag updated in address maintenance table$")
	public void the_customer_should_have_CSSM_flag_updated_in_address_maintenance_table() throws Throwable {

		jdaHomeStepDefs.i_navigate_to_address_maintenance_page();
		jdaFooter.clickQueryButton();
		addressMaintenancePage.enterAddressID(context.getCustomer());
		jdaFooter.clickExecuteButton();

		addressMaintenancePage.clickUserDefinedTab();
		Assert.assertTrue("The address Id is not a STR. Expected [checkbox selected] but was [not selected]",
				addressMaintenancePage.isCSSMChecked());
	}

	@Given("^the INT \"([^\"]*)\" should be \"([^\"]*)\" status, order details in the order header maintenance table$")
	public void the_INT_should_be_status_order_details_in_the_order_header_maintenance_table(String orderID,
			String status) throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		jdaHomeStepDefs.i_navigate_to_order_header();
		jdaFooter.clickQueryButton();
		orderHeaderMaintenancePage.enterOrderNo(orderID);
		jdaFooter.clickExecuteButton();

		String status1 = orderHeaderMaintenancePage.getStatus();
		System.out.println(status1);
		if (!status1.equals(status1)) {
			failureList.add("Status is not as expected. Expected [" + status1 + "] but was [" + status1 + "]");
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
		String type = orderHeaderMaintenancePage.getType();
		if (!type.equals("INT SEA")) {
			failureList.add("Type is not as expected. Expected [INT SEA] but was [" + type + "]");
		}
		String numberoflines = orderHeaderMaintenancePage.getNumberOfLines();
		if (numberoflines.equals(null)) {
			failureList.add(" Number Of Lines is not as expected. Expected [Not NULL] but was [" + numberoflines + "]");
		}
		Assert.assertTrue(
				"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

	@Given("^the order should have hub details in hub address tab$")
	public void the_order_should_have_hub_details_in_hub_address_tab() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		orderHeaderMaintenancePage.clickHubAddressTab();

		String hub = orderHeaderMaintenancePage.getHub();
		if (hub.equals(null)) {
			failureList.add("Hub is not as expected. Expected [Not NULL] but was [" + hub + "]");
		}
		String hubname = orderHeaderMaintenancePage.getHubName();
		if (hubname.equals(null)) {
			failureList.add("Hub Name is not as expected. Expected [Not NULL] but was [" + hubname + "]");
		}
		String hubcountry = orderHeaderMaintenancePage.getHubCountry();
		if (hubcountry.equals(null)) {
			failureList.add("Hub is not as expected. Expected [Not NULL] but was [" + hubcountry + "]");
		}
		Assert.assertTrue(
				"Order Header Maintenance details are not as expected." + Arrays.asList(failureList.toString()),
				failureList.isEmpty());
	}

}
