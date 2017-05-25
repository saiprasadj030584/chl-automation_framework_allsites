package com.jda.wms.pages.rdt;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class StoreTrackingOrderPickingPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void selectPickingMenu() throws InterruptedException {
		screen.type("3");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public boolean isPickMenuDisplayed() {
		if (screen.exists("images/Putty/Picking/PickMenu.png") != null)
			return true;
		else
			return false;
	}

	public void selectPickingInPickMenu() throws InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public boolean isPickTaskMenuDisplayed() {
		if (screen.exists("images/Putty/Picking/PickTaskMenu.png") != null)
			return true;
		else
			return false;
	}

	public void selectContainerPickMenu() throws InterruptedException {
		screen.type("3");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public boolean isPickEntryDisplayed() throws InterruptedException {
		Thread.sleep(5000);
		if ((screen.exists("images/Putty/Picking/PickEntry.png") != null))
			return true;
		else
			return false;
	}

	public void enterTaskID(String orderId) throws InterruptedException {
		screen.type(orderId);
		Thread.sleep(1000);
	}
	
	public void pressTab() throws InterruptedException{
		screen.type(Key.TAB);
		Thread.sleep(1000);
	}
	
	public void pressEnter() throws InterruptedException{
		screen.type(Key.ENTER);
		Thread.sleep(4000);
	}

	public void enterListID(String listId) throws InterruptedException {
		screen.type(listId);
		Thread.sleep(1000);
	}

	public String getListIDDisplayed() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/ListID.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		screen.doubleClick(mStatus.getCenter().offset(40, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public String getSkuId() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/SKUId.png");
		screen.click(mStatus.below(14));
		Thread.sleep(2000);
		screen.doubleClick(mStatus.below(2));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	
	public String getLocation() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/FromLocation.png");
		screen.click(mStatus.below(10));
		Thread.sleep(2000);
		screen.doubleClick(mStatus.below(1));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void enterSkuId(String sku) throws InterruptedException {
		screen.type(sku);
		Thread.sleep(2000);
	}

	public String getTagId() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/TagId.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		screen.doubleClick(mStatus.getCenter().offset(40, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public void enterQuantity(String qtyToPick) throws InterruptedException {
		screen.type(qtyToPick);
		Thread.sleep(2000);
	}

	public void navigateToNextPage() throws InterruptedException {
		screen.type("x", Key.CTRL);
		screen.type(Key.NUM4);
		Thread.sleep(2000);
	}

	public String getQuantity() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/Quantity.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		screen.doubleClick(mStatus.getCenter().offset(40, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void enterManufactureDate(String manufactureDate) throws InterruptedException {
		screen.type(manufactureDate);
		Thread.sleep(2000);
	}

	public void enterExpiryDate(String expDate) throws InterruptedException {
		screen.type(expDate);
		Thread.sleep(2000);
	}

	public String getToPallet() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/ToPallet.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.doubleClick(mStatus.getCenter().offset(50, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getToLocation() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/ToLocation.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		screen.doubleClick(mStatus.getCenter().offset(40, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	
	public String getDestination() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/FinalLocation.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		screen.doubleClick(mStatus.getCenter().offset(40, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void enterDestination(String destination) throws InterruptedException {
		screen.type(destination);
		Thread.sleep(2000);
	}
}
