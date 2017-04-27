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
	private final JDAFooter jdaFooter;

	@Inject
	public InventoryQueryPage(JDAFooter jdaFooter) {
		this.jdaFooter = jdaFooter;
	}

	public void searchTagId(String tagId) throws FindFailed, InterruptedException {
		jdaFooter.clickQueryButton();
		enterTagId(tagId);
		jdaFooter.clickExecuteButton();
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

	public String getStatus() throws FindFailed {
		Match mStatus = screen.find("images/InventoryQuery/General/Status.png");
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
		Match mQtyOnhand = screen.find("images/Inventory/QtyOnHand.png");
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

	public void navigateToMiscellaneousTab() throws FindFailed {
		screen.wait("images/InventoryQuery/MiscellaneousTab.png", timeoutInSec);
		screen.click("images/InventoryQuery/MiscellaneousTab.png");
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
}
