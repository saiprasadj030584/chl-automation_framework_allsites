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
	private final JDAFooter jDAFooter;
	private final InventoryQueryPage inventoryQueryPage;

	@Inject
	public InventoryTransactionQueryPage(JDAFooter jDAFooter, InventoryQueryPage inventoryQueryPage) {
		this.jDAFooter = jDAFooter;
		this.inventoryQueryPage = inventoryQueryPage;
	}

	public void searchTagID(String tagId, String code, String lockCode) throws FindFailed, InterruptedException {
		jDAFooter.clickQueryButton();
		enterCode(code);
		enterTagId(tagId);
		enterTransactionDate();
		enterLockCode(lockCode);
		jDAFooter.clickExecuteButton();
	}

	private void enterCode(String code) throws FindFailed, InterruptedException {
		screen.wait("images/InventoryTransactionQuery/Code.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/Code.png");
		screen.type(code);
		Thread.sleep(2000);
	}

	private void enterTagId(String tagId) throws InterruptedException, FindFailed {
		Match itagId = screen.find("images/InventoryTransactionQuery/TagID.png");
		screen.click(itagId.getCenter().offset(70, 0));
		screen.type(tagId);
		Thread.sleep(3000);

	}

	private void enterTransactionDate() throws FindFailed, InterruptedException {
		Match transactionDate = screen.find("images/InventoryTransactionQuery/TransactionDate.png");
		screen.click(transactionDate.getCenter().offset(70, 0));
		screen.type("0");
		Thread.sleep(3000);
	}

	private void enterLockCode(String lockCode) throws FindFailed, InterruptedException {
		Match ilockCode = screen.find("images/InventoryTransactionQuery/lockCode.png");
		screen.click(ilockCode.getCenter().offset(70, 0));
		screen.type(lockCode);
		Thread.sleep(3000);
	}

	public String getReasonCode() throws FindFailed {
		Match mStatus = screen.find("images/InventoryTransactionQuery/Miscellaneous/ReasonCode.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public boolean verifyStatus(String status) throws FindFailed, InterruptedException {
		String istatus = getStatus();
		if (!istatus.equals(status)) {
			return false;
		} else
			return true;
	}

	private String getStatus() throws FindFailed, InterruptedException {
		Match status = screen.find("/images/InventoryTransactionQuery/lockStatus.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(3000);
		return App.getClipboard();
	}

	public void navigateToMiscellaneousTab() throws FindFailed, InterruptedException {
		screen.wait("images/InventoryTransactionQuery/MiscellaneousTab.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/MiscellaneousTab.png");
		Thread.sleep(2000);
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
}
