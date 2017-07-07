package com.jda.wms.pages.gm;

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

	public String getStatus() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/General/Status.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void clickStatus() throws FindFailed {
		screen.wait("/images/InventoryQuery/Status.png", timeoutInSec);
		screen.click("/images/InventoryQuery/Status.png");
	}

	public void selectRefreshOptions() throws FindFailed {
		screen.wait("images/RefreshOption.png", timeoutInSec);
		screen.click("images/RefreshOption.png");
	}

	public void selectRefreshCurrentRecord() throws FindFailed {
		screen.wait("images/RefreshCurrentRecord.png", timeoutInSec);
		screen.click("images/RefreshCurrentRecord.png");
	}

	public String getLockCode() throws FindFailed, InterruptedException {
		Match status = screen.find("/images/InventoryQuery/lockCode.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void refreshUserDefinedTab() throws FindFailed, InterruptedException {
		clickABV();
		screen.rightClick();
		selectRefreshOptions();
		selectRefreshCurrentRecord();
	}

	private void clickABV() throws FindFailed {
		screen.wait("images/InventoryQuery/UserDefined/ABV.png", timeoutInSec);
		screen.click("images/InventoryQuery/UserDefined/ABV.png");

	}

	public String getSkuId() throws FindFailed, InterruptedException {
		Match status = screen.find("images/InventoryQuery/Sku.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void clickUserDefinedTab() throws FindFailed {
		screen.wait("images/InventoryQuery/UserDefined/UserDefined.png", timeoutInSec);
		screen.click("images/InventoryQuery/UserDefined/UserDefined.png");
	}

	public String getABV() throws FindFailed, InterruptedException {
		Match status = screen.find("images/InventoryQuery/UserDefined/ABV.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void refreshInventoryQueryPage() throws FindFailed, InterruptedException {
		clickStatus();
		screen.rightClick();
		selectRefreshOptions();
		selectRefreshCurrentRecord();

	}

	public String getUpdatedABV() throws FindFailed, InterruptedException {
		Match status = screen.find("images/InventoryQuery/UserDefined/ABV.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(3000);
		return App.getClipboard();
	}

	public void enterTagId(String tagId) throws InterruptedException {
		screen.type(tagId);
		Thread.sleep(2000);
	}

	public String getInventorySKUId() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/General/InventorySKUId.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getQtyOnHand() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/General/QtyOnHand.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getcaseRatio() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/General/CaseRatio.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public int getQtyOnhand() throws FindFailed {
		Match mQtyOnhand = screen.find("images/InventoryQuery/General/QtyOnHand.png");
		screen.click(mQtyOnhand.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return Integer.parseInt(App.getClipboard());
	}

	public int getQtyAllocated() throws FindFailed {
		Match mQtyAllocated = screen.find("images/Inventory/QtyAllocated.png");
		screen.click(mQtyAllocated.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return Integer.parseInt(App.getClipboard());
	}

	public String getSiteId() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/General/SiteId.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getLocation() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/General/Location.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getDescription() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/General/Description.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getPackConfig() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/General/PackConfig.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getTrackingLevel() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/General/TrackingLevel.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getCaseRatio() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/General/CaseRatio.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getPalletType() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/Miscellaneous/PalletType.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getSupplier() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/Miscellaneous/Supplier.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getReceiptId() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/Miscellaneous/ReceiptId.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getLineId() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/Miscellaneous/LineId.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getExpiryDate() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/Miscellaneous/ExpiryDate.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getInventoryCreatedBy() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/Miscellaneous/InventoryCreatedBy.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getBaseUOM() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/UserDefined/BaseUOM.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getProductGroup() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/UserDefined/ProductGroup.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getStorageLocation() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/UserDefined/StorageLocation.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void navigateToMiscellaneousTab() throws FindFailed, InterruptedException {
		screen.wait("images/InventoryQuery/MiscellaneousTab.png", timeoutInSec);
		screen.click("images/InventoryQuery/MiscellaneousTab.png");
		Thread.sleep(2000);
	}

	public void navigateToUserDefinedTab() throws FindFailed {
		screen.wait("images/InventoryQuery/UserDefinedTab.png", timeoutInSec);
		screen.click("images/InventoryQuery/UserDefinedTab.png");
	}

	public void enterPOId(String poId) {
		screen.type(poId);
	}

	public String getPreAdviceStatus() throws FindFailed {
		Match mStatus = screen.find("images/PreAdviceHeader/Status.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void clickGeneralTab() throws FindFailed, InterruptedException {
		screen.wait("images/InventoryQuery/GeneralTab.png", timeoutInSec);
		screen.click("images/InventoryQuery/GeneralTab.png");
		Thread.sleep(1000);
	}

	public String getLocationZone() throws FindFailed {
		Match mLocationZone = screen.find("images/InventoryQuery/General/LocationZone.png");
		screen.click(mLocationZone.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void enterSkuId(String skuId) throws FindFailed {
		Match mtagId = screen.find("images/InventoryQuery/General/SkuID.png");
		screen.click(mtagId.getCenter().offset(70, 0));
		screen.type(skuId);

	}

	public void enterlocation(String locationID) throws FindFailed {
		Match mtagId = screen.find("images/InventoryQuery/General/Location.png");
		screen.click(mtagId.getCenter().offset(70, 0));
		screen.type(locationID);

	}

	public String getOrigin() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/General/origin.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

}
