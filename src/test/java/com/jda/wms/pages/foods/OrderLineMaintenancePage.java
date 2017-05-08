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
		clickExecuteButton();
		clickSaveButton();
		refreshOrderlinePage();
	}

	private void doubleClickOrderLineMaintenance() throws FindFailed, InterruptedException {
		screen.wait("images/OrderLineMaintenance.png", timeoutInSec);
		screen.doubleClick("images/OrderLineMaintenance.png");
		Thread.sleep(2000);
	}

	private void doubleClickSKULine() throws FindFailed {
		screen.wait("images/OrderlineSkuFirstLine.png", timeoutInSec);
		screen.doubleClick("images/OrderlineSkuFirstLine.png");
	}

	private void clickUpdateButton() throws FindFailed {
		screen.wait("images/OrderLineUpdate.png", timeoutInSec);
		screen.click("images/OrderLineUpdate.png");
	}

	private void selectAllocateCheckbox() throws FindFailed {
		screen.wait("images/OrderlineAllocateCheckbox.png", timeoutInSec);
		screen.click("images/OrderlineAllocateCheckbox.png");
	}

	private void validateQuantityOrder() throws FindFailed, InterruptedException {
		Match mQty = screen.find("images/OrderlineQuantityOrder.png");
		screen.click(mQty.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		int quantity = Integer.parseInt(App.getClipboard());

		while (screen.exists("images/QuantitySoftAllocated.png") == null) {
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

	private void clickExecuteButton() throws FindFailed {
		screen.wait("images/OrderlineExecuteButton.png", timeoutInSec);
		screen.click("images/OrderlineExecuteButton.png");
	}

	private void clickSaveButton() throws FindFailed, InterruptedException {
		screen.wait("images/OrderlineSaveYes.png", timeoutInSec);
		screen.click("images/OrderlineSaveYes.png");
		Thread.sleep(60000);
	}

	public void refreshOrderlinePage() throws FindFailed, InterruptedException {
		screen.rightClick();
		screen.wait("images/OrderlineRefreshOption.png", timeoutInSec);
		screen.click("images/OrderlineRefreshOption.png");
		screen.wait("images/OrderlineRefreshCurrentRecord.png", timeoutInSec);
		screen.click("images/OrderlineRefreshCurrentRecord.png");
	}

	public void selectFirstRecord() throws InterruptedException, FindFailed {
		Match mPreAdviceIdHeader = screen.find("images/OrderLineMaintenance/OrderId.png");
		Thread.sleep(2000);
		screen.doubleClick(mPreAdviceIdHeader.below(10));
		Thread.sleep(3000);
	}

	public String getSkuId() throws FindFailed {
		Match mSkuId = screen.find("images/OrderLineMaintenance/General/Sku.png");
		screen.click(mSkuId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getQtyordered() throws FindFailed {
		Match mSkuId = screen.find("images/OrderLineMaintenance/General/QtyOrdered.png");
		screen.click(mSkuId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getQtyTasked() throws FindFailed {
		Match mSkuId = screen.find("images/OrderLineMaintenance/General/QtyTasked.png");
		screen.click(mSkuId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getPackConfig() throws FindFailed {
		Match mSkuId = screen.find("images/OrderLineMaintenance/General/PackConfig.png");
		screen.click(mSkuId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void clickUserDefinedTab() throws FindFailed, InterruptedException {
		screen.wait("images/OrderLineMaintenance/UserDefined.png", timeoutInSec);
		screen.click("images/OrderLineMaintenance/UserDefined.png");
		Thread.sleep(3000);
		
	}

	public String getCaseRatio() throws FindFailed {
		Match mDescription = screen.find("images/OrderLineMaintenance/UserDefined/CaseRatio.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void clickGeneralTab() throws FindFailed, InterruptedException {
		screen.wait("images/OrderLineMaintenance/GeneralTab.png", timeoutInSec);
		screen.click("images/OrderLineMaintenance/GeneralTab.png");
		Thread.sleep(3000);
		
	}

	public void enterOrderID(String orderId) {
		screen.type(orderId);
		
	}
}
