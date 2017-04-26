package com.jda.wms.pages.foods;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.pages.PageObject;

public class JdaHomePage extends PageObject {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	Region reg = new Region(0, 0, 4000, 1000);

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
		// clickDataMenu();
		// hoverSKU();
		// clickSKUSubmenu();
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("SKU Maintenance");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
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

	public void clickInventory() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/Data/Inventory/Inventory/Inventory.png", timeoutInSec);
		screen.click("images/Menu/Data/Inventory/Inventory/Inventory.png");
		Thread.sleep(4000);
	}

	public void clickInventoryUpdate() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/Operations/OperationInventoryUpdate.png", timeoutInSec);
		screen.click("images/Menu/Operations/OperationInventoryUpdate.png");
		Thread.sleep(4000);
	}

	public void clickOperationsMenu() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/MenuOperations.png", timeoutInSec);
		screen.click("images/Menu/MenuOperations.png");
		Thread.sleep(4000);
	}

	public void hoverOperationsInventory() throws FindFailed {
		screen.wait("images/Menu/Operations/OperationInventory.png", timeoutInSec);
		screen.click("images/Menu/Operations/OperationInventory.png");
		screen.mouseMove(80, 0);
	}

	public void hoverGeneral() throws FindFailed {
		screen.wait("images/JDAHome/general.png", timeoutInSec);
		screen.click("images/JDAHome/general.png");
		screen.mouseMove(70, 0);
	}

	public void hoverSetup() throws FindFailed {
		screen.wait("images/JDAHome/Setup.png", timeoutInSec);
		screen.click("images/JDAHome/Setup.png");
		screen.mouseMove(70, 0);
	}

	public void clickAddress() throws FindFailed {
		screen.wait("images/JDAHome/Address.png", timeoutInSec);
		screen.click("images/JDAHome/Address.png");
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

	public void hoverInventory() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/Inventory.png", timeoutInSec);
		screen.click("images/JDAHome/Inventory.png");
		Thread.sleep(3000);
		screen.mouseMove(70, 0);
	}

	public void clickInventorytab() throws FindFailed, InterruptedException {
		screen.wait("images/JDAFooter/Inventory.png", timeoutInSec);
		screen.click("images/JDAFooter/Inventory.png");
		Thread.sleep(3000);
	}

	public void clickInventoryTransaction() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/Data/Inventory/inventoryInventoryTransaction.png", timeoutInSec);
		screen.click("images/Menu/Data/Inventory/inventoryInventoryTransaction.png");
		Thread.sleep(3000);
	}

	public void navigateToInventoryQueryPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Inventory Query");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void clickWelcomeButton() throws FindFailed {
		screen.wait("images/JDAHome/Welcome.png", timeoutInSec);
		screen.click("images/JDAHome/Welcome.png");
	}

	public void clickSearchIcon() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/searchScreenButton.png", timeoutInSec);
		screen.click("images/JDAHome/searchScreenButton.png");
		Thread.sleep(3000);
	}

	public void navigateToInventoryUpdate() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Inventory Update");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToInventoryTransactionPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Inventory transaction (ITL) query screen");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void enterTabKey() {
		screen.type(Key.TAB);
	}
}
