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

	private JDAFooter jdaFooter;

	@Inject
	public InventoryTransactionQueryPage(JDAFooter jdaFooter) {
		this.jdaFooter = jdaFooter;
	}

	public void selectCode(String code) throws FindFailed, InterruptedException {
		jdaFooter.clickQueryButton();
		screen.type(code);
		screen.type(Key.TAB);
	}

	public void enterTagId(String tagId) throws InterruptedException {
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(tagId);
		Thread.sleep(5000);
	}

	public String getOriginalQty() throws FindFailed {
		Match mStatus = screen.find("images/InventoryTransactionQuery/General/OriginalQty.png");
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

	public String getSkuId() throws FindFailed {
		Match mStatus = screen.find("images/InventoryTransactionQuery/Sku.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getReference() throws FindFailed {
		Match mStatus = screen.find("images/InventoryTransactionQuery/Reference.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getAbv() throws FindFailed {
		Match mStatus = screen.find("images/InventoryTransactionQuery/ABV.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getExpiryDate() throws FindFailed {
		Match mExpiryDate = screen.find("images/InventoryTransactionQuery/Miscellaneous/ExpiryDate.png");
		screen.click(mExpiryDate.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void searchTagID(String tagId, String code, String lockCode) throws FindFailed, InterruptedException {
		jdaFooter.clickQueryButton();
		enterCode(code);
		enterTagId(tagId);
		enterTransactionDate();
		enterLockCode(lockCode);
		jdaFooter.clickExecuteButton();
	}

	public void enterCode(String code) throws FindFailed, InterruptedException {
		Match mDescription = screen.find("images/InventoryTransactionQuery/Code.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type(code);
		Thread.sleep(2000);
	}

	public void enterTransactionDate() throws FindFailed, InterruptedException {
		screen.wait("images/InventoryTransactionQuery/TransactionDate.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/TransactionDate.png");
		Match transactionDate = screen.find("images/InventoryTransactionQuery/TransactionDate.png");
		screen.click(transactionDate.getCenter().offset(70, 0));
		Thread.sleep(2000);
		screen.type("0");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	private void enterLockCode(String lockCode) throws FindFailed, InterruptedException {
		Match ilockCode = screen.find("images/InventoryTransactionQuery/lockCode.png");
		screen.click(ilockCode.getCenter().offset(70, 0));
		screen.type(lockCode);
		Thread.sleep(3000);
	}

	public boolean isNoRecordsExists() {
		if (screen.exists("images/InventoryTransactionQuery/NoRecord.png") != null)
			return true;
		else
			return false;
	}
}
