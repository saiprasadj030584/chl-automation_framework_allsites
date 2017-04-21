package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class InventoryTransactionQueryPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public InventoryTransactionQueryPage() {
	}

	public void enterTagId(String tagId) throws InterruptedException, FindFailed {
		screen.wait("images/InventoryTransactionQuery/TagID.png",timeoutInSec);
		screen.click("images/InventoryTransactionQuery/TagID.png");
		screen.type(tagId);
		Thread.sleep(2000);
	}

	public void enterCode(String code) throws FindFailed, InterruptedException {
		screen.wait("images/InventoryTransactionQuery/Code.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/Code.png");
		Thread.sleep(2000);
		screen.wait("images/InventoryTransactionQuery/CodeAdjustment.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/CodeAdjustment.png");
//		screen.type(code);
		Thread.sleep(2000);
	}

	public String getOriginalQty() throws FindFailed {
		Match mStatus = screen.find("images/InventoryTransactionQuery/General/OriginalQty.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getUpdateQty() throws FindFailed {
		Match mStatus = screen.find("images/InventoryTransactionQuery/General/UpdateQty.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void navigateToMiscellaneousTab() throws FindFailed, InterruptedException {
		screen.wait("images/InventoryTransactionQuery/MiscellaneousTab.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/MiscellaneousTab.png");
		Thread.sleep(2000);
	}

	public String getReasonCode() throws FindFailed {
		Match mStatus = screen.find("images/InventoryTransactionQuery/Miscellaneous/ReasonCode.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void navigateToMiscellaneous2Tab() throws FindFailed, InterruptedException {
		screen.wait("images/InventoryTransactionQuery/Miscellaneous2Tab.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/Miscellaneous2Tab.png");
		Thread.sleep(2000);
	}

	public String getUploaded() throws FindFailed {
		Match mStatus = screen.find("images/InventoryTransactionQuery/Miscellaneous2/Uploaded.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getUploadedFileName() throws FindFailed {
		Match mStatus = screen.find("images/InventoryTransactionQuery/Miscellaneous2/UploadedFilename.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void selectRequiredRecord() throws FindFailed, InterruptedException {
		screen.wait("images/InventoryTransactionQuery/CodeInResults.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/CodeInResults.png");
		Thread.sleep(2000);
		screen.mouseMove(0, 20);
		System.out.println("Location "+screen.getTarget());
		screen.moveTo(screen.getTarget());
		//screen.doubleClick();
		Thread.sleep(2000);
	}
}
