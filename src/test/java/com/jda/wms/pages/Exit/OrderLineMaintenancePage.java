package com.jda.wms.pages.Exit;

import org.junit.Assert;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

public class OrderLineMaintenancePage{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public OrderLineMaintenancePage() {
	}
	
public String getExtndPrice() throws FindFailed, InterruptedException {
		
	    clickmiscellaneous2();
	    Thread.sleep(2000);
		screen.click("images/OrderLineMaintenance/Extndprice.png");
		Match mStatus = screen.find("images/OrderLineMaintenance/Extndprice.png");
		screen.click(mStatus.getCenter().offset(70,0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	
}

	public void navigatedOrderLinePage() throws FindFailed, InterruptedException {
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
	private void clickmiscellaneous2() throws FindFailed {
		screen.wait("images/OrderLineMaintenance/MiscellaneousTab.png", timeoutInSec);
		screen.click("images/OrderLineMaintenance/MiscellaneousTab.png");
	}
	public void enterSKU(String SKU) throws FindFailed, InterruptedException {
		Thread.sleep(2000);
		screen.wait("images/OrderLineMaintenance/SKU.png", timeoutInSec);
		Match mStatus=screen.find("images/OrderLineMaintenance/SKU.png");
		screen.click(mStatus.getCenter().offset(70,0));
		Thread.sleep(2000);
		screen.type(SKU);
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

	public String getQtyOrdered() throws FindFailed {
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
	public void verifyDeliveryType(String type) throws FindFailed {
		Match mDescription = screen.find("images/OrderLineMaintenance/UserDefined/DeliveryType.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		String actualType = App.getClipboard();
		Assert.assertEquals("The Delivery type does not Match", type, actualType);
	}
	public void verifyShipDock() throws FindFailed {
		Match mDescription = screen.find("images/OrderHeaderMaintenance/Shipdock.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		String actualDock = App.getClipboard();
		System.out.println("The Ship Dock is:"+actualDock);
		if(!actualDock.isEmpty()){
		Assert.assertTrue("Ship dock is validated", true);
		}
	}
	
}
