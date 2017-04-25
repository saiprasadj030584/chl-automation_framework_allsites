package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class InventoryQueryPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public InventoryQueryPage() {
	}

	public void clickQueryButton() throws FindFailed, InterruptedException {
		screen.type(Key.F2);
		Thread.sleep(3000);
	}

	public void enterTagId(String tagId) throws FindFailed {
		// screen.wait("/images/JDASupplierSKU/SKU.png", timeoutInSec);
		// screen.click("/images/JDASupplierSKU/SKU.png");
		screen.type(tagId);
	}

	public void clickExecuteButton() throws InterruptedException {
		screen.type(Key.F7);
		Thread.sleep(3000);
	}

	public String getStatus() throws FindFailed, InterruptedException {
		Match status = screen.find("/images/InventoryQuery/Status.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(3000);
		return App.getClipboard();
	}

	public String getLockCode() throws FindFailed, InterruptedException {
		Match status = screen.find("/images/InventoryQuery/lockCode.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(3000);
		return App.getClipboard();
	}

	public void refreshInventoryQuery() throws FindFailed, InterruptedException {
		screen.wait("/images/InventoryQuery/Status.png", timeoutInSec);
		screen.click("/images/InventoryQuery/Status.png");
		screen.rightClick();
		screen.wait("images/OrderlineRefreshOption.png", timeoutInSec);
		screen.click("images/OrderlineRefreshOption.png");
		screen.wait("images/OrderlineRefreshCurrentRecord.png", timeoutInSec);
		screen.click("images/OrderlineRefreshCurrentRecord.png");
	}

	public String getSkuId() throws FindFailed, InterruptedException {
		Match status = screen.find("images/InventoryQuery/Sku.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(3000);
		return App.getClipboard();
	}

	public void clickUserDefinedTab() throws FindFailed {
		screen.wait("images/InventoryQuery/UserDefined/UserDefined.png", timeoutInSec);
		screen.click("images/InventoryQuery/UserDefined/UserDefined.png");
	}

	public String getProductGroup() throws FindFailed, InterruptedException {
		Match status = screen.find("images/InventoryQuery/UserDefined/ProductGroup.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(3000);
		return App.getClipboard();
	}

	public String getABV() throws FindFailed, InterruptedException {
		Match status = screen.find("images/InventoryQuery/UserDefined/ABV.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(3000);
		return App.getClipboard();
	}

	public String checkUpdatedABV() throws FindFailed, InterruptedException {
		Match status = screen.find("images/InventoryQuery/UserDefined/ABV.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(3000);
		return App.getClipboard();
	}

	
}
