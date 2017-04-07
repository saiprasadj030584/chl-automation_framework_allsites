package com.jda.wms.stepdefs.foods;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.OrderHeaderPage;
import com.jda.wms.pages.foods.ScreenShotCapture;

import cucumber.api.Scenario;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderHeaderStepDefs {
	public Scenario myScenario;
	private final OrderHeaderPage orderHeaderPage;
	private final ScreenShotCapture Screenshot;
	private WebDriver webDriver;

	@Inject
	public OrderHeaderStepDefs(WebDriver webDriver, OrderHeaderPage orderHeaderPage,
			ScreenShotCapture screenCaptureStepDefs) {
		this.webDriver = webDriver;
		this.orderHeaderPage = orderHeaderPage;
		this.Screenshot = screenCaptureStepDefs;
	}

	@When("^User is in Order header and executes the order no$")
	public void user_is_in_Order_header_and_executes_the_order_no() throws Throwable {
		try {
			Thread.sleep(3000);
			orderHeaderPage.navigateToOrderHeader();
			orderHeaderPage.enterOrderNo("6600033481");
			Thread.sleep(3000);
			// capture();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("^User select the SKU line$")
	public void user_select_the_SKU_line() throws Throwable {
		try {
			try {
				orderHeaderPage.navigateToOrderLineList();
				// capture();
				// Assert.fail();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("^User Allocates the product$")
	public void user_Allocates_the_product() throws Throwable {
		try {
			orderHeaderPage.allocateOrder();
			Thread.sleep(3000);
			// capture();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^Validate the status in Order Header screen$")
	public void validate_the_status_in_Order_Header_screen() throws Throwable {
		try {
			orderHeaderPage.verifyOrderStatus();
			Thread.sleep(3000);
			// capture();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}