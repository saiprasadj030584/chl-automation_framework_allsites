package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class InventoryQueryPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private final JDAFooter jDAFooter;

	public InventoryQueryPage(JDAFooter jDAFooter) {
		this.jDAFooter = jDAFooter;
	}

	public void searchTagId(String tagId) throws FindFailed, InterruptedException {
		jDAFooter.clickQueryButton();
		enterTagId(tagId);
		jDAFooter.clickExecuteButton();
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
}
