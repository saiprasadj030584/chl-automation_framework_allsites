package com.jda.wms.pages.foods;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
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

	public void navigateToSKUMaintanence() throws FindFailed, InterruptedException {
		clickDataMenu();
		hoverSKU();
		clickSKUSubmenu();
	}

	private void clickSKUSubmenu() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/Data/Sku/skuSku.png", timeoutInSec);
		screen.click("images/Menu/Data/Sku/skuSku.png");
		Thread.sleep(3000);
	}

	public void clickDataMenu() throws FindFailed {
		screen.wait("images/Menu/menuData.png", timeoutInSec);
		screen.click("images/Menu/menuData.png");
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
	}

	public void clickSupplierSKU() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/SupplierSKU.png", timeoutInSec);
		screen.click("images/JDAHome/SupplierSKU.png");
		Thread.sleep(3000);
	}

	public void hoverDataInventory() throws FindFailed {
		screen.wait("images/Menu/Data/dataInventory.png", timeoutInSec);
		screen.click("images/Menu/Data/dataInventory.png");
		screen.mouseMove(80, 0);
	}

	public void clickDataInventory() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/Data/Inventory.png", timeoutInSec);
		screen.click("images/Menu/Data/Inventory.png");
		Thread.sleep(4000);
	}

	public void clickInventoryUpdate() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/Operations/operationInventoryUpdate.png", timeoutInSec);
		screen.click("images/Menu/Operations/operationInventoryUpdate.png");
		Thread.sleep(4000);
	}

	public void clickOperationsMenu() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/menuOperations.png", timeoutInSec);
		screen.click("images/Menu/menuOperations.png");
		Thread.sleep(4000);
	}

	public void hoverOperationsInventory() throws FindFailed {
		screen.wait("images/Menu/Operations/operationInventory.png", timeoutInSec);
		screen.click("images/Menu/Operations/operationInventory.png");
		screen.mouseMove(80, 0);
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

	public void clickInventorytab() throws FindFailed, InterruptedException {
		screen.wait("images/JDA Footer/inventory.png", timeoutInSec);
		screen.click("images/JDA Footer/inventory.png");
		Thread.sleep(3000);
	}
	public void clickInventoryTransaction() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/Data/Inventory/inventoryInventoryTransaction.png", timeoutInSec);
		screen.click("images/Menu/Data/Inventory/inventoryInventoryTransaction.png");
		Thread.sleep(3000);
	}
	
}
