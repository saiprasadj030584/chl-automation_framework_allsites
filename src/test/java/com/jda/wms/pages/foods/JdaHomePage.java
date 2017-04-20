package com.jda.wms.pages.foods;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.pages.PageObject;

import cucumber.runtime.Env;

public class JdaHomePage extends PageObject {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public JdaHomePage(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
	}

	public void navigateToOrderHeader() throws FindFailed, InterruptedException {
		clickDataMenu();
		hoverOrder();
		clickOrderHeader();
	}

	public void clickDataMenu() throws FindFailed {
		screen.wait("images/JDAHome/DataMenu.png", timeoutInSec);
		screen.click("images/JDAHome/DataMenu.png");
	}

	private void hoverOrder() throws FindFailed {
		screen.wait("images/OrderSubmenu.png", timeoutInSec);
		screen.click("images/OrderSubmenu.png");
	}

	private void clickOrderHeader() throws FindFailed {
		screen.wait("images/OrderHeader.png", timeoutInSec);
		screen.click("images/OrderHeader.png");
	}

	public void hoverSKU() throws FindFailed {
		screen.wait("images/JDAHome/SKU.png", timeoutInSec);
		screen.click("images/JDAHome/SKU.png");
		screen.mouseMove(80, 0);
	}

	public void clickSupplierSKU() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/SupplierSKU.png", timeoutInSec);
		screen.click("images/JDAHome/SupplierSKU.png");
		Thread.sleep(3000);
	}

	public void hoverDataInventory() throws FindFailed {
		screen.wait("images/JDAHome/InventoryMenu.png", timeoutInSec);
		screen.click("images/JDAHome/InventoryMenu.png");
		screen.mouseMove(80, 0);
	}

	public void clickDataInventory() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/Inventory.png", timeoutInSec);
		screen.click("images/JDAHome/Inventory.png");
		Thread.sleep(4000);
	}

	public void clickInventoryUpdate() throws FindFailed, InterruptedException {
		screen.wait("images/images/Menu/Operations/InventoryUpdate.png", timeoutInSec);
		screen.click("images/images/Menu/Operations/InventoryUpdate.png");
		Thread.sleep(4000);
	}

	public void clickOperationsMenu() throws FindFailed, InterruptedException {
		screen.wait("images/images/Menu/menuOperations.png", timeoutInSec);
		screen.click("images/images/Menu/menuOperations.png");
		Thread.sleep(4000);
	}

	public void hoverOperationsInventory() throws FindFailed {
		screen.wait("images/images/Menu/Operations/Inventory.png", timeoutInSec);
		screen.click("images/images/Menu/Operations/Inventory.png");
		screen.mouseMove(80, 0);
	}

}
