package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class SKUMaintenancePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void searchSKUid(String skuId) throws FindFailed, InterruptedException {
		clickQuery();
		enterSKUID(skuId);
		clickExecute();
	}

	private void clickExecute() throws InterruptedException {
		// screen.wait("images/execute.PNG", timeoutInSec);
		// screen.click("images/execute.PNG");
		screen.type(Key.F7);
	}

	private void enterSKUID(String skuId) throws FindFailed, InterruptedException {
		// screen.wait("images/SKUMaintenanceTable/SKU-ID-Search.png",
		// timeoutInSec);
		// screen.click("images/SKUMaintenanceTable/SKU-ID-Search.png");
		screen.type(skuId);
		Thread.sleep(2000);
	}

	private void clickQuery() throws FindFailed, InterruptedException {
		// screen.wait("images/Query.PNG", timeoutInSec);
		// screen.click("images/Query.PNG");
		screen.type(Key.F2);
		Thread.sleep(2000);
	}

	public String getSKUid() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/SKU-ID.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getSKUDescription() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/SKU-Description.png");
		screen.click(mStatus.getCenter().offset(100, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getEAN() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/Settings1/EAN.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getUPC() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/Settings1/UPC.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getProductGroup() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/Settings1/Product-Group.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getEachQuantity() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/Settings1/Each-Quantity.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public boolean isTagMergeUnchecked() throws InterruptedException, FindFailed {
		if (screen.exists("images/SKUMaintenanceTable/Settings1/Tag-Merge-Unchecked.png") != null)
			return true;
		else
			return false;
	}

	public void navigateToSettings4() throws FindFailed, InterruptedException {
		screen.wait("images/SKUMaintenanceTable/Settings4.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/Settings4.png");
		Thread.sleep(3000L);
	}

	public void navigateToCustomsAndExcise() throws FindFailed, InterruptedException {
		screen.wait("images/SKUMaintenanceTable/Customs-Excise.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/Customs-Excise.png");
		Thread.sleep(3000L);
	}

	public String getCEWarehouseType() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/CustomsAndExcise/CE-Warehouse-Type.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getCEVatCode() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/CustomsAndExcise/CE-VAT-Code.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public boolean isCESKUChecked() {
		if (screen.exists("images/SKUMaintenanceTable/BatchExpiryData/CE-SKU-Checked.png") != null)
			return true;
		else
			return false;
	}

	public boolean isNewProductChecked() {
		if (screen.exists("images/SKUMaintenanceTable/Settings4/New-Product-Checked.png") != null)
			return true;
		else
			return false;
	}

	public String getCEAlcoholicStrength() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/CustomsAndExcise/CEAlcoholicStrength.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void navigateToLinking() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/Linking.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/Linking.png");
		Thread.sleep(3000L);
	}

	public boolean isSiteIdExist() {
		if (screen.exists("images/SKUMaintenanceTable/Linking/SiteID-9771.png") != null)
			return true;
		else
			return false;
	}

	public void navigateToBatchExpiry() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/Batch-Expiry-Data.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/Batch-Expiry-Data.png");
		Thread.sleep(3000L);
	}

	public String getAllocationGroup() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/Settings1/AllocationGroup.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void navigateToUserDefined() throws FindFailed, InterruptedException {
		screen.wait("images/SKUMaintenanceTable/User-Defined.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/User-Defined.png");
		Thread.sleep(3000L);
	}

	public String getBaseUOM() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/UserDefined/Base-UOM.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getSAPCreationStatus() throws InterruptedException, FindFailed {
		Match mStatus = screen.find("images/SKUMaintenanceTable/UserDefined/SAP-Creation-Status.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void navigateToSupplierSKU() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/SupplierSKU.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/SupplierSKU.png");
		Thread.sleep(3000L);
	}

	public String getSupplierSKUId() throws InterruptedException, FindFailed {
		Match mStatus = screen.find("images/SKUMaintenanceTable/SupplierSKU/SupplierSKUID.png");
		screen.click(mStatus.getCenter().offset(30, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public boolean isExpiryRequiredUnchecked() {
		if (screen.exists("images/SKUMaintenanceTable/BatchExpiryData/Expiry-Required-Unchecked.png") != null)
			return true;
		else
			return false;
	}
}
