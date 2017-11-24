package com.jda.wms.pages.gm;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class JdaHomePage {

	Screen screen = new Screen();
	int timeoutInSec = 20;
	private final JdaLoginPage jdaLoginPage;
	private JDAFooter jdaFooter;
	Region reg = new Region(0, 0, 4000, 1000);

	@Inject
	public JdaHomePage(JdaLoginPage jdaLoginPage,JDAFooter jdaFooter) {
		this.jdaLoginPage = jdaLoginPage;
		this.jdaFooter = jdaFooter;
	}

	public void navigateToOrderHeader() throws FindFailed, InterruptedException {
		clickDataMenu();
		hoverOrder();
		clickOrderHeader();
		Thread.sleep(3000);
	}

	public void navigateToTrailerShippingPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Trailer shipping screen");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToSystemAllocationPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("system Allocation");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void navigateToOrderContainerPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Order Container");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void navigateToUnpickingAndUnshippingPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Unpicking and Unshipping");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void navigateToSystemAllocationPageViaMenu() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/Operations.png", timeoutInSec);
		screen.click("images/Menu/Operations.png");
		Thread.sleep(1000);
		screen.wait("images/Menu/Operations/Order.png", timeoutInSec);
		screen.click("images/Menu/Operations/Order.png");
		Thread.sleep(1000);
		screen.wait("images/Menu/Operations/Order/Allocation.png", timeoutInSec);
		screen.click("images/Menu/Operations/Order/Allocation.png");
		Thread.sleep(1000);
		screen.wait("images/Menu/Operations/Order/Allocation/SystemAllocation.png", timeoutInSec);
		screen.click("images/Menu/Operations/Order/Allocation/SystemAllocation.png");
		Thread.sleep(1000);
	}

	public void navigateToMannualClusteringPage() throws InterruptedException, FindFailed {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type(" Clustering");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToSKUMaintanence() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("SKU Maintenance");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToLocationMaintanence() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("location maintenance/query screen*");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
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

	public void navigateToStockCheckQueryPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("stock check task query screen");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(1000);
	}

	private void hoverOrder() throws FindFailed {
		screen.wait("images/OrderSubmenu.PNG", timeoutInSec);
		screen.click("images/OrderSubmenu.PNG");
	}

	private void clickOrderHeader() throws FindFailed {
		screen.wait("images/OrderHeader.PNG", timeoutInSec);
		screen.click("images/OrderHeader.PNG");
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

	public void hoverOperationsInventory() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/Operations/operationInventory.png", timeoutInSec);
		screen.click("images/Menu/Operations/operationInventory.png");
		screen.mouseMove(80, 0);
		Thread.sleep(2000);
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

	public void navigateToStockCheckListGeneration() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("stock check list generation");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(1000);
	}

	public void clickOperations() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/menuOperations.png", timeoutInSec);
		screen.click("images/Menu/menuOperations.png");
		Thread.sleep(2000);
	}

	public void clickStockAdjustment() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/Operations/Inventory/StockAdjustment.png", timeoutInSec);
		screen.click("images/Menu/Operations/Inventory/StockAdjustment.png");
		Thread.sleep(8000);
	}

	public void clickInventorytab() throws FindFailed, InterruptedException {
		screen.wait("images/JDAFooter/Inventory.png", timeoutInSec);
		screen.click("images/JDAFooter/Inventory.png");
		Thread.sleep(3000);
	}

	public void clickInventoryTransaction() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/Data/Inventory/inventoryInventoryTransaction.png", timeoutInSec);
		screen.click("images/Menu/Data/Inventory/inventoryInventoryTransaction.png");
		Thread.sleep(2000);
	}

	public void hoverPreAdvice() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/PreAdvice.png", timeoutInSec);
		screen.click("(images/JDAHome/PreAdvice.png");
		Thread.sleep(3000);
	}

	public void clickPreAdviceHeader() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/PreAdviceHeader.png", timeoutInSec);
		screen.click("images/JDAHome/PreAdviceHeader.png");
	}

	public void navigateToInventoryQueryPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Inventory Query");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void navigateToStockAdjustment() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(2000);
		screen.type("Stock Adjustment");
		screen.type(Key.ENTER);
		Thread.sleep(2000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void clickWelcomeButton() throws FindFailed {
		screen.wait("images/JDAHome/Welcome.png", timeoutInSec);
		screen.click("images/JDAHome/Welcome.png");
	}

	/*
	 * public void clickSearchIcon() throws FindFailed, InterruptedException {
	 * Thread.sleep(5000); if
	 * (screen.exists("images/JDAHome/searchScreenButton.png") != null) {
	 * System.out.println("Application search icon found"); if
	 * (screen.exists("images/JDAHome/Welcomed.png") != null) {
	 * screen.wait("images/JDAHome/Welcomed.png", timeoutInSec);
	 * screen.click("images/JDAHome/Welcomed.png"); Thread.sleep(2000);
	 * screen.type("f", Key.CTRL); Thread.sleep(2000); } else if
	 * (screen.exists("images/JDAHome/Welcome.png") != null) {
	 * screen.wait("images/JDAHome/Welcome.png", timeoutInSec);
	 * screen.click("images/JDAHome/Welcome.png"); Thread.sleep(2000);
	 * screen.type("f", Key.CTRL); Thread.sleep(2000); } }
	 * 
	 * else if (screen.exists("images/JDAHome/JdaHomeLogin.png") != null) {
	 * System.out.println("Application login page found instead of search icon"
	 * ); jdaLoginPage.enterUsername(); jdaLoginPage.enterPassword();
	 * jdaLoginPage.clickConnectButton(); Thread.sleep(5000); if
	 * (screen.exists("images/JDAHome/Welcomed.png") != null) {
	 * screen.wait("images/JDAHome/Welcomed.png", timeoutInSec);
	 * screen.click("images/JDAHome/Welcomed.png"); Thread.sleep(2000);
	 * screen.type("f", Key.CTRL); Thread.sleep(2000); } else {
	 * System.out.println(
	 * "1. Application issue - Kill IE driver and luanch application from first"
	 * ); jdaLoginPage.login(); screen.wait("images/JDAHome/Welcomed.png",
	 * timeoutInSec); screen.click("images/JDAHome/Welcomed.png");
	 * Thread.sleep(2000); } } else { System.out.println(
	 * "2. Application issue - Kill IE driver and luanch application from first"
	 * ); jdaLoginPage.login(); screen.wait("images/JDAHome/Welcomed.png",
	 * timeoutInSec); screen.click("images/JDAHome/Welcomed.png");
	 * Thread.sleep(2000); }
	 * 
	 * }
	 */

	public void clickSearchIcon() throws FindFailed, InterruptedException {
		Thread.sleep(5000);
		if (screen.exists("images/JDAHome/searchScreenButton.png") != null) {
			System.out.println("Application search icon found");
			if (screen.exists("images/JDAHome/Welcomed.png") != null) {
				screen.wait("images/JDAHome/Welcomed.png", timeoutInSec);
				screen.click("images/JDAHome/Welcomed.png");
				Thread.sleep(2000);
//				System.out.println("Right Click on welcome tab");
				jdaFooter.rightClick();
				Thread.sleep(2000);
				screen.type(Key.UP);
				screen.type(Key.UP);
				screen.type(Key.UP);
				screen.type(Key.UP);
				screen.type(Key.UP);
				screen.type(Key.UP);
				Thread.sleep(2000);
				screen.type(Key.ENTER);
				Thread.sleep(2000);
				System.out.println("Search for Screen selection ");
				if (screen.exists("images/ScreenSelection.png") != null) {
					System.out.println("Screen selection found");
					screen.wait("images/SearchFor.png", timeoutInSec);
					screen.click("images/SearchFor.png");
				}
			
			else if (screen.exists("images/JDAHome/Welcome.png") != null) {
				System.out.println("Screen selection not Found . Try again to right click welcome screen");
				screen.wait("images/JDAHome/Welcome.png", timeoutInSec);
				screen.click("images/JDAHome/Welcome.png");
				Thread.sleep(2000);
				jdaFooter.rightClick();
				Thread.sleep(2000);
				screen.type(Key.UP);
				screen.type(Key.UP);
				screen.type(Key.UP);
				screen.type(Key.UP);
				screen.type(Key.UP);
				//screen.type(Key.UP);
				Thread.sleep(2000);
				screen.type(Key.ENTER);
				Thread.sleep(2000);
				if (screen.exists("images/ScreenSelection.png") != null) {
					System.out.println("Screen selection found");
					screen.wait("images/SearchFor.png", timeoutInSec);
					screen.click("images/SearchFor.png");
				}
			}
			} 
		}

		else if (screen.exists("images/JDAHome/JdaHomeLogin.png") != null) {
			System.out.println("Application login page found instead of search icon");
			jdaLoginPage.enterUsername();
			jdaLoginPage.enterPassword();
			jdaLoginPage.clickConnectButton();
			Thread.sleep(5000);
			if (screen.exists("images/JDAHome/Welcomed.png") != null) {
				screen.wait("images/JDAHome/Welcomed.png", timeoutInSec);
				screen.click("images/JDAHome/Welcomed.png");
				Thread.sleep(2000);
				screen.type("f", Key.CTRL);
				Thread.sleep(2000);
				if (screen.exists("images/ScreenSelection.png") != null) {
					System.out.println("Screen selection found");
					screen.wait("images/SearchFor.png", timeoutInSec);
					screen.click("images/SearchFor.png");
				}
			} else {
				System.out.println("1. Application issue - Kill IE driver and luanch application from first");
				applicationRestart();
			}
		} else {
			System.out.println("2. Application issue - Kill IE driver and luanch application from first");
			applicationRestart();
		}
	}
	// Commented the earlier find screen option
//	public void clickSearchIcon() throws FindFailed, InterruptedException {
//		Thread.sleep(5000);
//		if (screen.exists("images/JDAHome/searchScreenButton.png") != null) {
//			System.out.println("Application search icon found");
//			if (screen.exists("images/JDAHome/Welcomed.png") != null) {
//				screen.wait("images/JDAHome/Welcomed.png", timeoutInSec);
//				screen.click("images/JDAHome/Welcomed.png");
//				Thread.sleep(2000);
//				screen.type("f", Key.CTRL);
//				Thread.sleep(2000);
//				if (screen.exists("images/ScreenSelection.png") != null) {
//					System.out.println("Screen selection found");
//					screen.wait("images/SearchFor.png", timeoutInSec);
//					screen.click("images/SearchFor.png");
//				}
//			} else if (screen.exists("images/JDAHome/Welcome.png") != null) {
//				screen.wait("images/JDAHome/Welcome.png", timeoutInSec);
//				screen.click("images/JDAHome/Welcome.png");
//				Thread.sleep(2000);
//				screen.type("f", Key.CTRL);
//				Thread.sleep(2000);
//				if (screen.exists("images/ScreenSelection.png") != null) {
//					System.out.println("Screen selection found");
//					screen.wait("images/SearchFor.png", timeoutInSec);
//					screen.click("images/SearchFor.png");
//				}
//			}
//		}
//
//		else if (screen.exists("images/JDAHome/JdaHomeLogin.png") != null) {
//			System.out.println("Application login page found instead of search icon");
//			jdaLoginPage.enterUsername();
//			jdaLoginPage.enterPassword();
//			jdaLoginPage.clickConnectButton();
//			Thread.sleep(5000);
//			if (screen.exists("images/JDAHome/Welcomed.png") != null) {
//				screen.wait("images/JDAHome/Welcomed.png", timeoutInSec);
//				screen.click("images/JDAHome/Welcomed.png");
//				Thread.sleep(2000);
//				screen.type("f", Key.CTRL);
//				Thread.sleep(2000);
//				if (screen.exists("images/ScreenSelection.png") != null) {
//					System.out.println("Screen selection found");
//					screen.wait("images/SearchFor.png", timeoutInSec);
//					screen.click("images/SearchFor.png");
//				}
//			} else {
//				System.out.println("1. Application issue - Kill IE driver and luanch application from first");
//				applicationRestart();
//			}
//		} else {
//			System.out.println("2. Application issue - Kill IE driver and luanch application from first");
//			applicationRestart();
//		}
//	}

	public void applicationRestart() throws InterruptedException, FindFailed {
		try {
			Process p = Runtime.getRuntime().exec("cmd /c C:/Automation_supporting_files/LnkFiles/Iexplorekill.lnk");
			p.waitFor(30, TimeUnit.SECONDS);
		} catch (IOException e) {

			e.printStackTrace();
		}
		jdaLoginPage.driver = null;
		jdaLoginPage.login();
		screen.wait("images/JDAHome/Welcomed.png", timeoutInSec);
		screen.click("images/JDAHome/Welcomed.png");
		Thread.sleep(2000);
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
		screen.type("(ITL) query");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(4000);
	}

	public void navigateToUpiReceiptHeaderPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("UPI Receipt Header");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(4000);
	}

	public void navigateToUpiManagementPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("UPI management screen");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(4000);
	}

	public void navigateToPreAdviceHeaderPage() throws FindFailed, InterruptedException {
		screen.type("f", Key.CTRL);
		Thread.sleep(1000);
		screen.type("Pre-advice header maintenance/query screen");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToPreAdviceHeaderMaintenance() throws FindFailed, InterruptedException {
		System.out.println("787tygjggjgfvmmbmb");
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Pre-advice header maintenance/query screen");
		screen.click("images/JDAHome/Search_button.png");

		// screen.type(Key.ENTER);
		Thread.sleep(2000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToPackConfigMaintenance() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Pack configuration maintenance/query screen");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);

	}

	public void enterTabKey() {
		screen.type(Key.TAB);
	}

	public void navigateToMoveTaskUpdate() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Move Task Update");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToMoveTaskQuery() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Move Task Query");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToAddressMaintenancePage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Address");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToCEConsignmentMaintenenacePage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("C&E consignment maintenance");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(1000);
	}

	public void navigateToPickFaceMaintenance() throws InterruptedException, FindFailed {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Pick face maintenance/query screen");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToPackConfigPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("pack configuration maintenance/query screen");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToLocationPage() throws FindFailed, InterruptedException {
		screen.type("f", Key.CTRL);
		Thread.sleep(1000);
		screen.type("Location maintenance/query screen*");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToConsignmentLinkingPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("C&E consignment linking");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToPreAdviceLineMaintenance() throws FindFailed, InterruptedException {
		clickSearchIcon();
		screen.type("Pre-advice line maintenance/query screen");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void navigateToOrderLineMaintenance() throws InterruptedException, FindFailed {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Order Line Maintenance/query screen");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToReceiptReversalPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Receipt Reversal");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToShipDockReassignment() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Ship dock reassignment screen");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToOrderHeaderMaintenance() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Order Header Maintenance/query screen");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToOrderPreparationPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Order Preparation");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToTrailerMaintanencePage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Trailer maintenance/query screen*");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToDockSchedulerPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Dock scheduler screen");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToDockSchedulerBookingsPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Dock Scheduler entries query screen*");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void scrollDown() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/ScrollDown.png", timeoutInSec);
		screen.click("images/JDAHome/ScrollDown.png");
		Thread.sleep(2000);
	}

	public void scrollRight() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/ScrollRight.png", timeoutInSec);
		screen.click("images/JDAHome/ScrollRight.png");
		Thread.sleep(2000);
	}

	public void scrollLeft() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/ScrollLeft.png", timeoutInSec);
		screen.click("images/JDAHome/ScrollLeft.png");
		Thread.sleep(2000);
	}

	public void navigateToVehicleUnloadingPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Vehicle Unloading*");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToMoveTaskListGenerationPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Move task list generation screen");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void navigateToOrderManagementPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Order Management");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void navigateToInventoryTransactionQueryPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("(ILT)");
		// screen.type(Key.ENTER);
		screen.click("images/JDAHome/Search_button.png");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void scrollRightBig() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/ScrollRightBig.png", timeoutInSec);
		screen.click("images/JDAHome/ScrollRightBig.png");
		Thread.sleep(1000);
	}

	public void scrollLeftBig() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/ScrollLeftBig.png", timeoutInSec);
		screen.click("images/JDAHome/ScrollLeftBig.png");
		Thread.sleep(1000);
	}

	public void navigateToReportSelectionPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Report Selection");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(1000);
	}

	public void navigatetoDeliveryManagementPage() throws InterruptedException, FindFailed {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Delivery Management");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(1000);
	}

	public void navigateToDeliveryPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Delivery");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToStockCheckTaskQueryPage() throws InterruptedException, FindFailed {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Stock check task query screen");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToStockCheckListCompletionPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Stock check task list completion screen");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToOrderContainerMaintenancePage() throws InterruptedException, FindFailed {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Order container maintence/query");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigatetoMoveTaskManagementPage() throws InterruptedException, FindFailed {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Move Task Management");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(1000);
	}

	public void navigateToPackConfigLinking() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Pack configuration Linking screen");
		screen.click("images/JDAHome/Search_button.png");
		// screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToSchedulerProgramPage() throws InterruptedException, FindFailed {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("scheduler program");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}
}
