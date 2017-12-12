package com.jda.wms.stepdefs.gm;

import java.sql.SQLException;

import org.junit.Assert;
import org.sikuli.script.FindFailed;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.AddressDB;
import com.jda.wms.db.gm.MoveTaskDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.OrderGroupingPage;
import com.jda.wms.pages.gm.OrderHeaderPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderGroupingStepdefs {
	private Context context;
	private AddressDB address;
	private OrderHeaderDB orderHeaderDB;
	private OrderGroupingPage orderGroupingPage;
	private JdaLoginPage jdaLoginPage;
	private JdaHomePage jdahomepage;
	private JDAFooter jDAFooter;
	private OrderHeaderPage orderHeaderPage;
	private MoveTaskDB movetask;

	@Inject
	public OrderGroupingStepdefs(Context context, AddressDB address, MoveTaskDB movetask, OrderHeaderDB orderHeaderDB,
			OrderGroupingPage orderGroupingPage, JdaLoginPage jdaLoginPage, JdaHomePage jdahomepage,
			JDAFooter jDAFooter, OrderHeaderPage orderHeaderPage) {
		this.address = address;
		this.context = context;
		this.orderHeaderDB = orderHeaderDB;
		this.orderGroupingPage = orderGroupingPage;
		this.jdaLoginPage = jdaLoginPage;
		this.jdahomepage = jdahomepage;
		this.jDAFooter = jDAFooter;
		this.orderHeaderPage = orderHeaderPage;
		this.movetask = movetask;
	}

	@Given("^I have setup the data to check just in time$")
	public void i_have_setup_the_data_to_check_just_in_time() throws Throwable {
		orderHeaderDB.updateWorkOrder(context.getOrderId());
		address.updateUserDefNote2(context.getOrderId());

	}

	@When("^check whether the stock is allocated automatically$")
	public void check_whether_the_stock_is_allocated_automatically() throws Throwable {
		jdaLoginPage.login();
		jdahomepage.navigateToOrderGroupingPage();
		Thread.sleep(2000);
		orderGroupingPage.clickonOKButton();
		Thread.sleep(2000);
		orderGroupingPage.clickOnWaveplan();
		Thread.sleep(1000);
		// orderGroupingPage.SiteIddropbox(context.getSiteId());
		orderGroupingPage.SiteIddropbox("5649");
		jDAFooter.pressTab();
		orderGroupingPage.TypeinWaveplanTextBox("NDCSTATIC");
		orderGroupingPage.ClickNextButton();
		Thread.sleep(5000);
		orderGroupingPage.ClickNextButton();
		Thread.sleep(50000);
		orderGroupingPage.clickDoneButton();
		Thread.sleep(10000);

	}

	@Then("^Navigate to order to check order is allocated$")
	public void Navigate_to_order_to_check_order_is_allocated()
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		Thread.sleep(2000);
		jdahomepage.navigateToOrderHeaderMaintenance();
		jDAFooter.clickQueryButton();
		orderHeaderPage.enterOrderNo(context.getOrderId());
		jDAFooter.clickExecuteButton();

		System.out.println("Order status" + orderHeaderDB.getStatus(context.getOrderId()));
		if (orderHeaderDB.getStatus(context.getOrderId()).contains("Released"))
			Assert.fail("Order status still in released status");

	}

	@Then("^Navigate to order to check order is picked$")
	public void Navigate_to_order_to_check_order_is_picked()
			throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		Thread.sleep(2000);
		jdahomepage.navigateToOrderHeaderMaintenance();
		jDAFooter.clickQueryButton();
		orderHeaderPage.enterOrderNo(context.getOrderId());
		jDAFooter.clickExecuteButton();

		System.out.println("Order status" + orderHeaderDB.getStatus(context.getOrderId()));
		if (!orderHeaderDB.getStatus(context.getOrderId()).contains("Picked"))
			Assert.fail("Order status still is not in picked status");

	}

}
