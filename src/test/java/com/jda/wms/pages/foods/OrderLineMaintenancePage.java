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

public class OrderLineMaintenancePage extends PageObject {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public OrderLineMaintenancePage(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
	}

	public void navigatedOrderLinepage() throws FindFailed, InterruptedException {
		doubleClickOrderLineMaintenance();
		doubleClickSKULine();
	}

	public void allocateOrder() throws FindFailed, InterruptedException {
		clickUpdateButton();
		selectAllocateCheckbox();
		validateQuantityOrder();
		refreshOrderlinePage();
	}

	private void doubleClickOrderLineMaintenance() throws FindFailed, InterruptedException {
		screen.wait("images/OrderLineMaintenance.png", timeoutInSec);
		screen.doubleClick("images/OrderLineMaintenance.png");
		Thread.sleep(2000);
	}

	private void doubleClickSKULine() throws FindFailed {
		screen.wait("images/OrderlineSkuTobeDoucleclik.png", timeoutInSec);
		screen.doubleClick("images/OrderlineSkuTobeDoucleclik.png");
	}

	private void clickUpdateButton() throws FindFailed {
		screen.wait("images/OrderLineUpdate.png", timeoutInSec);
		screen.click("images/OrderLineUpdate.png");
	}

	private void selectAllocateCheckbox() throws FindFailed {
		screen.wait("images/OrderlineAllocateCheck.png", timeoutInSec);
		screen.click("images/OrderlineAllocateCheck.png");
	}

	private void validateQuantityOrder() throws FindFailed {
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
			clickExecuteButton();
			clickSaveButton();
		} else {
			System.out.println("POC Failure");
			Assert.fail();
		}
	}

	// private void orderLineQuantity

	private void clickExecuteButton() throws FindFailed {
		screen.wait("images/OrderlineExecute.png", timeoutInSec);
		screen.click("images/OrderlineExecute.png");
	}

	private void clickSaveButton() throws FindFailed {
		screen.wait("images/OrderlineSaveYes.png", timeoutInSec);
		screen.click("images/OrderlineSaveYes.png");
	}

	private void refreshOrderlinePage() throws FindFailed, InterruptedException {
		Thread.sleep(60000);
		screen.rightClick();
		screen.wait("images/OrderlineRefresh.png", timeoutInSec);
		screen.click("images/OrderlineRefresh.png");
		screen.wait("images/OrderlineRefreshCurrent.png", timeoutInSec);
		screen.click("images/OrderlineRefreshCurrent.png");
	}

}
