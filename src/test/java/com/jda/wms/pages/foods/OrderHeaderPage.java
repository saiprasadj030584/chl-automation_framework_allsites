package com.jda.wms.pages.foods;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.pages.PageObject;

public class OrderHeaderPage extends PageObject {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private WebDriver webDriver;

	@Inject
	public OrderHeaderPage(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
	}

	public void navigateToOrderHeader() throws FindFailed, InterruptedException {
		screen.wait("images/DataMenu.png", timeoutInSec);
		screen.click("images/DataMenu.png");
		screen.wait("images/OrderSubmenu.png", timeoutInSec);
		screen.click("images/OrderSubmenu.png");
		screen.wait("images/OrderHeader.png", timeoutInSec);
		screen.click("images/OrderHeader.png");
		Thread.sleep(5000);

		screen.wait("images/OrderHeaderQuery1.png", timeoutInSec);
		screen.click("images/OrderHeaderQuery1.png");
		Thread.sleep(2000);
	}

	public void enterOrderNo(String OrderNo) throws FindFailed {
		screen.wait("images/OrderHeaderNo.png", timeoutInSec);
		screen.click("images/OrderHeaderNo.png");
		screen.type(OrderNo);
		screen.wait("images/OrderHeaderExecute.png", timeoutInSec);
		screen.click("images/OrderHeaderExecute.png");
	}

	public void navigateToOrderLineList() throws FindFailed, InterruptedException {
		screen.wait("images/OrderHeaderLines.png", timeoutInSec);
		screen.click("images/OrderHeaderLines.png");
		screen.wait("images/OrderLineMaintenance.png", timeoutInSec);
		screen.doubleClick("images/OrderLineMaintenance.png");
		Thread.sleep(2000);

		screen.wait("images/OrderlineSkuTobeDoucleclik.png", timeoutInSec);
		screen.doubleClick("images/OrderlineSkuTobeDoucleclik.png");
	}

	public void allocateOrder() throws FindFailed, InterruptedException {
		screen.wait("images/OrderLineUpdate.png", timeoutInSec);
		screen.click("images/OrderLineUpdate.png");
		screen.wait("images/OrderlineAllocateCheck.png", timeoutInSec);
		screen.click("images/OrderlineAllocateCheck.png");

		Match mQty = screen.find("images/OrderlineQuantityOrder.png");
		screen.click(mQty.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		int quantity = Integer.parseInt(App.getClipboard());

		while (screen.exists("images/QtySoftAllocated.png") == null) {
			Assert.fail();
		}

		Match mQtysoft1 = screen.find("images/QuantitySoftAllocated.png");
		screen.click(mQtysoft1.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);

		if ((quantity) > 0) {
			screen.wait("images/OrderlineExecute.png", timeoutInSec);
			screen.click("images/OrderlineExecute.png");
			screen.wait("images/OrderlineSaveYes.png", timeoutInSec);
			screen.click("images/OrderlineSaveYes.png");
		} else {
			System.out.println("POC Failure");
			Assert.fail();
		}

		Thread.sleep(60000);
		screen.rightClick();
		screen.wait("images/OrderlineRefresh.png", timeoutInSec);
		screen.click("images/OrderlineRefresh.png");
		screen.wait("images/OrderlineRefreshCurrent.png", timeoutInSec);
		screen.click("images/OrderlineRefreshCurrent.png");
	}

	public String getOrderStatus() throws FindFailed, InterruptedException {
		String orderStatus = null;
		screen.wait("images/OrderHeaderFooter.png", timeoutInSec);
		screen.click("images/OrderHeaderFooter.png");
		Thread.sleep(3000);

		screen.wait("images/OrderHeaderStatus.png", timeoutInSec);
		screen.click("images/OrderHeaderStatus.png");
		screen.rightClick();

		screen.wait("images/OrderlineRefresh.png", timeoutInSec);
		screen.click("images/OrderlineRefresh.png");
		screen.wait("images/OrderlineRefreshCurrent.png", timeoutInSec);
		screen.click("images/OrderlineRefreshCurrent.png");

		Match mStatus = screen.find("images/OrderHeaderStatus.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		orderStatus = App.getClipboard();
		logger.debug("Order status is: " + orderStatus);
		return orderStatus;
	}
}