package com.jda.wms.pages.foods;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import com.google.inject.Inject;
import com.jda.wms.pages.PageObject;

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

	public void clickSKUSubmenu() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/Data/Sku/skuSku.png", timeoutInSec);
		screen.click("images/Menu/Data/Sku/skuSku.png");
		Thread.sleep(3000);
	}

	public void clickDataMenu() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/menuData.png", timeoutInSec);
		screen.click("images/Menu/menuData.png");
		Thread.sleep(2000);
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
		screen.wait("images/Menu/Data/dataSku.png", timeoutInSec);
		screen.click("images/Menu/Data/dataSku.png");
	}

	public void clickSupplierSKU() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/SupplierSKU.png", timeoutInSec);
		screen.click("images/JDAHome/SupplierSKU.png");
		Thread.sleep(3000);
	}

	public void hoverPackConfig() throws FindFailed {
		screen.wait("images/JDAHome/PackConfig.png", timeoutInSec);
		screen.click("images/JDAHome/PackConfig.png");
		screen.mouseMove(70, 0);
	}

	public void clickPackConfig() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/PackConfigScreen.png", timeoutInSec);
		screen.click("images/JDAHome/PackConfigScreen.png");
		Thread.sleep(3000);
	}

	
	public void hoverDataInventory() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/Data/dataInventory.png", timeoutInSec);
		screen.click("images/Menu/Data/dataInventory.png");
		screen.mouseMove(70, 0);
		Thread.sleep(2000);
	}

	public void clickInventory() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/Data/Inventory/inventoryInventory.png", timeoutInSec);
		screen.click("images/Menu/Data/Inventory/inventoryInventory.png");
		Thread.sleep(8000);
	}

	public void clickOperations() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/menuOperations.png", timeoutInSec);
		screen.click("images/Menu/menuOperations.png");
		Thread.sleep(2000);
	}

	public void hoverOperationsInventory() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/Operations/operationInventory.png", timeoutInSec);
		screen.click("images/Menu/Operations/operationInventory.png");
		screen.mouseMove(80, 0);
		Thread.sleep(2000);
	}

	public void clickStockAdjustment() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/Operations/Inventory/StockAdjustment.png", timeoutInSec);
		screen.click("images/Menu/Operations/Inventory/StockAdjustment.png");
		Thread.sleep(8000);
	}

	public void clickInventoryTransaction() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/Data/Inventory/inventoryInventoryTransaction.png", timeoutInSec);
		screen.click("images/Menu/Data/Inventory/inventoryInventoryTransaction.png");
		Thread.sleep(2000);
	}
	
	public void clickSearchIcon() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/SearchIconButton.png", timeoutInSec);
		screen.click("images/JDAHome/SearchIconButton.png");
		Thread.sleep(3000);
	}
	
	public void navigateToInventoryQuery() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Inventory Query");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}
	
	public void navigateToStockAdjustment() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Stock Adjustment");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToInventoryTransaction() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("(ITL) query");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}
}
