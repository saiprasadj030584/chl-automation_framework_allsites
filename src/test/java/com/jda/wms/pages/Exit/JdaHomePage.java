package com.jda.wms.pages.Exit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

import cucumber.api.java.en.And;

@SuppressWarnings("unused")
public class JdaHomePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	Region reg = new Region(0, 0, 4000, 1000);
	private final JdaLoginPage jdaLoginPage;

	@Inject
	public JdaHomePage(JdaLoginPage jdaLoginPage) {
		this.jdaLoginPage = jdaLoginPage;
	}


	public void navigateToOrderHeader() throws FindFailed, InterruptedException {
		clickDataMenu();
		hoverOrder();
		clickOrderHeader();
		Thread.sleep(3000);
	}

	public void navigateToLocation() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Location");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
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
	public void ClickAdminMenu() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/Admin.png", timeoutInSec);
		screen.click("images/JDAHome/Admin.png");
		Thread.sleep(2000);
	}
	public boolean isUserAccessavail() throws FindFailed, InterruptedException {
		if (screen.exists("images/JDAHome/Useraccessadmin.png") != null) {
			return true;
		} else
			return false;
	}
	public boolean isUserAccessavaild() throws FindFailed, InterruptedException {
		if (screen.exists("images/JDAHome/UserGroupFunctionAccess.png") != null) {
			return true;
		} else
			return false;
	}
	public void selectUserGroup() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/FunctionAccessGroupDropdown.png", timeoutInSec);
		screen.click("images/JDAHome/FunctionAccessGroupDropdown.png");
		Thread.sleep(2000);
		 Match header = screen.find("images/JDAHome/UserGroupAdvuser.png");
		   reg=header.below(150).left(5).right(1000);
		   reg.hover(header);
		   reg.click(header);
	}
	public boolean iswebAccessavail() throws FindFailed, InterruptedException {
		if (screen.exists("images/JDAHome/webaccess.png") != null) {
			return true;
		} else
			return false;
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
	public void hoverInventory() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/Operations/Inventory.png", timeoutInSec);
		screen.click("images/Menu/Operations/Inventory.png");
		screen.mouseMove(80, 0);
		Thread.sleep(2000);
	}

	public void hoverGeneral() throws FindFailed {
		screen.wait("images/JDAHome/general.png", timeoutInSec);
		screen.click("images/JDAHome/general.png");
		screen.mouseMove(70, 0);
	}
	public void hoverSKU() throws FindFailed {
		screen.wait("images/JDAHome/SKU.png", timeoutInSec);
		screen.click("images/JDAHome/SKU.png");
		screen.mouseMove(70, 0);
	}
	public void hoverorder() throws FindFailed {
		screen.wait("images/JDAHome/order.png", timeoutInSec);
		screen.click("images/JDAHome/order.png");
		screen.mouseMove(70, 0);
	}
	public void hoverUPI() throws FindFailed {
		screen.wait("images/JDAHome/UPI.png", timeoutInSec);
		screen.click("images/JDAHome/UPI.png");
		screen.mouseMove(70, 0);
	}
	public void hoverDelivery() throws FindFailed {
		screen.wait("images/JDAHome/Delivery.png", timeoutInSec);
		screen.click("images/JDAHome/Delivery.png");
		screen.mouseMove(70, 0);
	}
	public void clickDelivery() throws FindFailed {
		screen.wait("images/JDAHome/Delivery2.png", timeoutInSec);
		screen.click("images/JDAHome/Delivery2.png");
		screen.mouseMove(70, 0);
	}
	public void hoverUser() throws FindFailed {
		screen.wait("images/JDAHome/User.png", timeoutInSec);
		screen.click("images/JDAHome/User.png");
		screen.mouseMove(70, 0);
	}
	public void hoversetup() throws FindFailed {
		screen.wait("images/JDAHome/Setup.png", timeoutInSec);
		screen.click("images/JDAHome/Setup.png");
		screen.mouseMove(70, 0);
	}

	public void hoverAccesscontrol() throws FindFailed {
		screen.wait("images/JDAHome/Accesscontrol.png", timeoutInSec);
		screen.click("images/JDAHome/Accesscontrol.png");
		screen.mouseMove(70, 0);
	}
	public void clickWorkstation() throws FindFailed {
		screen.wait("images/JDAHome/wrkstnacess.png", timeoutInSec);
		screen.click("images/JDAHome/wrkstnacess.png");
		screen.mouseMove(70, 0);
	}
		
	public void hoverLocationG() throws FindFailed {
		screen.wait("images/Location/HooverLocation.png", timeoutInSec);
		screen.click("images/Location/HooverLocation.png");
		screen.mouseMove(70, 0);
	}

	public void hoverSetup() throws FindFailed {
		screen.wait("images/JDAHome/Setup.png", timeoutInSec);
		screen.click("images/JDAHome/Setup.png");
		screen.mouseMove(70, 0);
	}
	public void hoverLocation() throws FindFailed {
		screen.wait("images/JDAHome/HooverLocation.png", timeoutInSec);
		screen.click("images/JDAHome/HooverLocation.png");
		screen.mouseMove(70, 0);
	}
	public void clickLocationZoneG() throws FindFailed {
		screen.wait("images/JDAHome/LocationZone.png", timeoutInSec);
		screen.click("images/JDAHome/LocationZone.png");
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
		Thread.sleep(1000);
		screen.type("Stock Adjustment");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void clickWelcomeButton() throws FindFailed {
		screen.wait("images/JDAHome/Welcome.png", timeoutInSec);
		screen.click("images/JDAHome/Welcome.png");
	}

	/*public void clickSearchIcon() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/searchScreenButton.png", timeoutInSec);
		screen.click("images/JDAHome/searchScreenButton.png");
		Thread.sleep(3000);
	}*/ 
	
	public void clickSearchIcon() throws FindFailed, InterruptedException {
		Thread.sleep(5000);
		if (screen.exists("images/JDAHome/SearchScreenButton.png") != null) {
			System.out.println("Application search icon found");
			if (screen.exists("images/JDAHome/Welcomed.png") != null) {
				screen.wait("images/JDAHome/Welcomed.png", timeoutInSec);
				screen.click("images/JDAHome/Welcomed.png");
				Thread.sleep(2000);
				screen.type("f", Key.CTRL);
				Thread.sleep(2000);
			} else if (screen.exists("images/JDAHome/Welcome.png") == null) {
				screen.wait("images/JDAHome/Welcome.png", timeoutInSec);
				screen.click("images/JDAHome/Welcome.png");
				Thread.sleep(2000);
				screen.type("f", Key.CTRL);
				Thread.sleep(2000);
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
			} else {
				System.out.println("1. Application issue - Kill IE driver and luanch application from first");
				try {
					Process p = Runtime.getRuntime()
							.exec("cmd /c C:/Automation_supporting_files/LnkFiles/Iexplorekill.lnk");
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
		} else {
			System.out.println("2. Application issue - Kill IE driver and luanch application from first");
			try {
				Process p = Runtime.getRuntime()
						.exec("cmd /c C:/Automation_supporting_files/LnkFiles/Iexplorekill.lnk");
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

	}

	public void navigateToInventoryUpdate() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Inventory Update");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void navigateToInventoryTransactionPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(2000);
		screen.type("(ITL) query");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(4000);
	}

	public void navigateToPreAdviceHeaderPage() throws FindFailed, InterruptedException {
		screen.type("f", Key.CTRL);
		Thread.sleep(1000);
		screen.type("Pre-advice header maintenance/query screen");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}
    public void clickPreadviceheader()throws FindFailed, InterruptedException {
    	screen.wait("images/PreAdviceLine/preadviceheader.png", timeoutInSec);
		screen.click("images/PreAdviceLine/preadviceheader.png");
		Thread.sleep(2000);
    }
    public void clickPreadviceline()throws FindFailed, InterruptedException {
    	screen.wait("images/PreAdviceHeader/preadviceline.png", timeoutInSec);
		screen.click("images/PreAdviceHeader/preadviceline.png");
		Thread.sleep(2000);
    }
	public void navigateToPreAdviceLinePage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Pre-advice line maintenance/query screen");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToPackConfigMaintenance() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Pre-advice header maintenance/query screen");
		screen.type(Key.ENTER);
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
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToMoveTaskQuery() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Move Task Query");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToAddressMaintenancePage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Address");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToCEConsignmentMaintenenacePage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("C&E consignment maintenance");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(1000);
	}

	public void navigateToPickFaceMaintenance() throws InterruptedException, FindFailed {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Pick face maintenance/query screen");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}
	
 

	public void navigateToPackConfigPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("pack configuration maintenance/query screen");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToLocationPage() throws FindFailed, InterruptedException {
		screen.type("f", Key.CTRL);
		Thread.sleep(1000);
		screen.type("Location maintenance/query screen*");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToConsignmentLinkingPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("C&E consignment linking");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToPreAdviceLineMaintenance() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Pre-advice line maintenance/query screen");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void navigateToOrderLineMaintenance() throws InterruptedException, FindFailed {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Order Line Maintenance/query screen");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToReceiptReversalPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Receipt Reversal");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToShipDockReassignment() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Ship dock reassignment screen");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToOrderHeaderMaintenance() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Order Header Maintenance/query screen");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToOrderPreparationPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Order Preparation");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}
	public void navigateToOrderheaderPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Order Header");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToTrailerMaintanencePage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Trailer maintenance/query screen*");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToDockSchedulerPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Dock scheduler screen");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToDockSchedulerBookingsPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Dock Scheduler entries query screen*");
		screen.type(Key.ENTER);
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
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void navigateToMoveTaskListGenerationPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Move task list generation screen");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void navigateToMoveTaskListManagementPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Move task management");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}
	public void navigateToOrderManagementPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Order Management");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}
	public void navigateToOrderContainerPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(3000);
		screen.type("Order Container");
		Thread.sleep(5000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void checkImageOnScreenAndClickTheGivenImageObject(String imagePath, float limit) throws FindFailed {
		Pattern patternImg = new Pattern(imagePath);
		patternImg.similar(limit);
		screen.wait(patternImg, timeoutInSec);
		screen.click(patternImg);
	}

	public void checkImageOnScreenAndClickTheGivenImageObjectOffset(String imagePath, float limit, int offsetValue)
			throws FindFailed {
		Pattern patternImg = new Pattern(imagePath);
		patternImg.similar(limit);
		Match match = screen.find(patternImg);
		screen.click(match.getCenter().offset(offsetValue, 0));
	}

	public boolean checkImageOnScreenForExist(String imagePath, float limit) throws FindFailed {
		Pattern patternImg = new Pattern(imagePath);
		patternImg.similar(limit);
		if (screen.exists(patternImg) != null) {
			return true;
		} else
			return false;
	}


	public void navigateToConatinerCheckingPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Container Checking");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void navigateToStockRelocatioPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Stock Relocation");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void navigateToMoveTaskQueryPage() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Move task query ");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}
	public void navigateToSite() throws FindFailed, InterruptedException {
		clickDataMenu();
		hoverGeneral();
		hoverSetup();
		Thread.sleep(2000);
		clickSite();
		Thread.sleep(7000);
	}
	public void navigateTolocation() throws FindFailed, InterruptedException {
		clickDataMenu();
		hoverLocation();
		Thread.sleep(100);
		clickLocation();
		Thread.sleep(3000);
	}
	
	public void navigateTolocationZoneG() throws FindFailed, InterruptedException {
		clickDataMenu();
		hoverLocation();
		clickLocationZoneG();
		Thread.sleep(3000);
	}
	public void navigateToAddress() throws FindFailed, InterruptedException {
		clickDataMenu();
		hoverGeneral();
		hoverSetup();
		Thread.sleep(100);
		clickAddress();
		Thread.sleep(3000);
	}
	public void navigateToSKU() throws FindFailed, InterruptedException {
		clickDataMenu();
		hoverSKU();
		Thread.sleep(100);
		clickSKU();
		Thread.sleep(3000);
	}
	public void navigateToSKUMaintenance() throws FindFailed, InterruptedException {
		clickDataMenu();
		hoverSKU();
		Thread.sleep(100);
		clickSKUMaintenance();
		Thread.sleep(3000);
	}
	public void navigateToSupplierSKUHome() throws FindFailed, InterruptedException {
//		clickDataMenu();
//		hoverorder();
//		Thread.sleep(100);
//		clickSupplierSKUHome();
//		Thread.sleep(3000);
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("supplier sku");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}
	public void navigateToorderlineHome() throws FindFailed, InterruptedException {
		clickDataMenu();
		hoverorder();
		Thread.sleep(100);
		clickorderlineHome();
		Thread.sleep(3000);
	}
	public void navigateToUPIheader() throws FindFailed, InterruptedException {
		clickDataMenu();
		hoverUPI();
		Thread.sleep(100);
		clickUPIheader();
		Thread.sleep(3000);
	}
	public void navigateToDelivery() throws FindFailed, InterruptedException {
//		clickDataMenu();
//		hoverDelivery();
//		Thread.sleep(100);
//		clickDelivery();
//		Thread.sleep(3000);
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Delivery");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}
	
	public void navigateToReportSelectionMenu() throws FindFailed, InterruptedException {
		clickReportsMenu();
		hoverReportSelection();
		Thread.sleep(100);
//		clickReportSelection();
//		Thread.sleep(3000);
	}
//	public void clickReportSelection() throws FindFailed, InterruptedException {
//		screen.wait("images/JDAHome/ReportSelection.png", timeoutInSec);
//		screen.click("images/JDAHome/ReportSelection.png");
//		Thread.sleep(2000);
//	}

	public void hoverReportSelection() throws FindFailed, InterruptedException {
			screen.wait("images/JDAHome/ReportSelection.png", timeoutInSec);
			screen.click("images/JDAHome/ReportSelection.png");
			screen.mouseMove(70, 0);
		}

	private void clickReportsMenu() throws FindFailed, InterruptedException {
		screen.wait("images/Menu/menuReports.png", timeoutInSec);
		screen.click("images/Menu/menuReports.png");
	}


	private void clickSite() throws FindFailed {
		screen.wait("images/JDAHome/Site.png", timeoutInSec);
		screen.click("images/JDAHome/Site.png");
	}
	public void clickSKU() throws FindFailed {
		screen.wait("images/JDAHome/SKUG.png", timeoutInSec);
		screen.click("images/JDAHome/SKUG.png");
	}
	public void clickSKUMaintenance() throws FindFailed {
		screen.wait("images/JDAHome/SKUG.png", timeoutInSec);
		screen.click("images/JDAHome/SKUG.png");
	}
	public void clickSupplierSKUHome() throws FindFailed {
		screen.wait("images/JDAHome/SupplierSKU1.png", timeoutInSec);
		screen.click("images/JDAHome/SupplierSKU1.png");
	}
	public void clickorderlineHome() throws FindFailed {
		screen.wait("images/JDAHome/orderline.png", timeoutInSec);
		screen.click("images/JDAHome/orderline.png");
	}
	public void clickUPIheader() throws FindFailed {
		screen.wait("images/JDAHome/UPIReceiptheader.png", timeoutInSec);
		screen.click("images/JDAHome/UPIReceiptheader.png");
	}


	public void clickUsergroup() throws FindFailed {
		screen.wait("images/JDAHome/UserGroup.png", timeoutInSec);
		screen.click("images/JDAHome/UserGroup.png");
	}
	public void clickscheduler() throws FindFailed {
		screen.wait("images/JDAHome/scheduler.png", timeoutInSec);
		screen.click("images/JDAHome/scheduler.png");
	}
	public void hoverScheduler() throws FindFailed {
		screen.wait("images/JDAHome/scheduler.png", timeoutInSec);
		screen.click("images/JDAHome/scheduler.png");
		screen.mouseMove(70, 0);
	}
	public void clickSchedulerPrgm() throws FindFailed {
		screen.wait("images/JDAHome/schedulerprogram.png", timeoutInSec);
		screen.click("images/JDAHome/schedulerprogram.png");
	}
	public void clickUsergroupFunctionaccess() throws FindFailed {
		screen.wait("images/JDAHome/usergrpfctn.png", timeoutInSec);
		screen.click("images/JDAHome/usergrpfctn.png");
	}
	private void clickLocation() throws FindFailed {
		screen.wait("images/JDAHome/Location.png", timeoutInSec);
		screen.click("images/JDAHome/Location.png");
	}
	public void navigateToAdmin() throws FindFailed, InterruptedException {
		ClickAdminMenu();
		hoverUser();
		Thread.sleep(1000);
		clickUsergroup();
		Thread.sleep(3000);
	}
	public void navigateToSchedulerprgm() throws FindFailed, InterruptedException {
		//commented in as Scheduler Program is not able to click
		/*ClickAdminMenu();
		hoversetup();
		Thread.sleep(3000);
		hoverScheduler();
		clickscheduler();
		Thread.sleep(3000);
		clickSchedulerPrgm();
		Thread.sleep(3000);*/
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Scheduler Program ");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}
	
	public void navigateToAccesscontrol() throws FindFailed, InterruptedException {
		ClickAdminMenu();
		hoverAccesscontrol();
		Thread.sleep(1000);
		clickUsergroupFunctionaccess();
		Thread.sleep(3000);
	}
	public void navigateToWorkstation() throws FindFailed, InterruptedException {
		ClickAdminMenu();
		hoverAccesscontrol();
		Thread.sleep(1000);
		clickWorkstation();
		Thread.sleep(3000);

	}
	public void navigateToInventory() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Inventory transaction (ITL) query screen");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}
	public void navigateToConsignmentMaintenance() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Consignment Maintainance");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}
	public void navigateToConsignmentDropMaintenance() throws FindFailed, InterruptedException {
		clickSearchIcon();
		Thread.sleep(1000);
		screen.type("Consignment drop Maintainance");
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}
//	public void navigateToInventory() throws FindFailed, InterruptedException {
//		clickSearchIcon();
//		Thread.sleep(1000);
//		screen.type("Inventory transaction (ITL) query screen");
//		screen.type(Key.ENTER);
//		Thread.sleep(1000);
//		screen.type(Key.ENTER);
//		Thread.sleep(5000);
//	}
}
