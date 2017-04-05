package com.jda.wms.stepdefs.foods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import com.jda.wms.pages.foods.OrderHeaderPage;

import cucumber.api.Scenario;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderHeaderStepDefs {
	public Scenario myScenario;
	private final OrderHeaderPage order;
	private final ScreenCaptureStepDefs Screenshot;
	private WebDriver webDriver;

	@Inject
	public OrderHeaderStepDefs(WebDriver webDriver, OrderHeaderPage orderHeaderPage,
			ScreenCaptureStepDefs screenCaptureStepDefs) {
		this.webDriver = webDriver;
		this.order = orderHeaderPage;
		this.Screenshot = screenCaptureStepDefs;
	}

	// @Override
	// @Before()
	// public void embedScreenshotStep(Scenario scenario) {
	// myScenario = scenario;
	// }

	// JdaData_OrderHeader order = new JdaData_OrderHeader();
	// StepDefinitions_Screencapture Screenshot = new
	// StepDefinitions_Screencapture();

	public void orderline() throws IOException {
		try {
			order.jdaorderline();
			Screenshot.screenshotcapture();
			String filePath = "C:\\Users\\Santhaseelan.Shanmug\\Workspace\\JDA_Automation\\target\\TempScreenshot\\img_New.png";
			Path path = Paths.get(filePath);
			myScenario.embed(Files.readAllBytes(path), "image/png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @When("^User is in Order header and executes the order no$")
	// public void
	// user_is_in_Order_header_and_executes_the_order_no(List<Orderheader_details>
	// OrderheaderdetailsList)
	// throws Throwable {
	//
	// try {
	// Thread.sleep(3000);
	// Orderheader_details details = OrderheaderdetailsList.get(0);
	// order.jdaOrder();
	// order.enter_OrderNo(details.order_No());
	//
	// Thread.sleep(3000);
	// capture();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	@When("^User is in Order header and executes the order no$")
	public void user_is_in_Order_header_and_executes_the_order_no() throws Throwable {

		try {
			Thread.sleep(3000);
			order.jdaOrder();
			order.enter_OrderNo("6600033481");
			Thread.sleep(3000);
			capture();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("^User select the SKU line$")
	public void user_select_the_SKU_line() throws Throwable {

		try {

			try {
				order.jdaorderline();
				capture();
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
			order.allocated_product();
			Thread.sleep(3000);
			capture();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^Validate the status in Order Header screen$")
	public void validate_the_status_in_Order_Header_screen() throws Throwable {
		try {
			order.status_changed();
			Thread.sleep(3000);
			capture();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void capture() {
		try {
			Screenshot.screenshotcapture();
			String filePath = "C:\\Users\\Santhaseelan.Shanmug\\Workspace\\JDA_Automation\\target\\TempScreenshot\\img_New.png";
			Path path = Paths.get(filePath);
			myScenario.embed(Files.readAllBytes(path), "image/png");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
