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

	public String getStatus() throws FindFailed, InterruptedException {
		Match status = screen.find("/images/InventoryQuery/Status.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(3000);
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
		Thread.sleep(3000);
		return App.getClipboard();
	}

	public void refreshInventoryQueryPage() throws FindFailed, InterruptedException {
		clickStatus();
		screen.rightClick();
		selectRefreshOptions();
		selectRefreshCurrentRecord();
	}

	public void searchTagId(String tagId) throws FindFailed, InterruptedException {
		jdaFooter.clickQueryButton();
		enterTagId(tagId);
		jdaFooter.clickExecuteButton();
	}

	public void enterTagId(String tagId) throws InterruptedException {
		// screen.wait("images/InventoryQuery/GeneraleTable/TagID.png",timeoutInSec);
		// screen.click("images/InventoryQuery/GeneraleTable/TagID.png");
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
}
