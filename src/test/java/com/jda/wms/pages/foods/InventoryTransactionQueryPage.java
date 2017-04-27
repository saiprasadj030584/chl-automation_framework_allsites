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
	private final JDAFooter jdaFooter;

	@Inject
	public InventoryTransactionQueryPage(JDAFooter jdaFooter) {
		this.jdaFooter = jdaFooter;
	}

	public void enterTagId(String tagId) throws InterruptedException, FindFailed {
		Match mtagId = screen.find("images/InventoryTransactionQuery/TagID.png");
		screen.click(mtagId.getCenter().offset(70, 0));
		screen.type(tagId);
		Thread.sleep(3000);
	}

	public void selectCode(String code) throws FindFailed, InterruptedException {
		jdaFooter.clickQueryButton();
		screen.type(code);
		screen.type(Key.TAB);
	}

	public void enterTransactionDate() throws FindFailed, InterruptedException {
		Match transactionDate = screen.find("images/InventoryTransactionQuery/TransactionDate.png");
		screen.click(transactionDate.getCenter().offset(70, 0));
		screen.type("0");
		Thread.sleep(3000);
		screen.type(Key.ENTER);
	}

	public void enterLockCode(String lockCode) throws FindFailed, InterruptedException {
		Match mLockCode = screen.find("images/InventoryTransactionQuery/lockCode.png");
		screen.click(mLockCode.getCenter().offset(70, 0));

		switch (lockCode) {
		case "Code Approval":
			screen.type("CODEAPP");
			Thread.sleep(2000);
			break;
		case "Components Stock":
			screen.type("CS");
			Thread.sleep(2000);
			break;
		case "1Damaged":
			screen.type("DMGD");
			Thread.sleep(2000);
			break;
		case "EVENTS":
			screen.type("EVENT");
			Thread.sleep(2000);
			break;
		case "Pick exception lock code":
			screen.type("EXCEPT");
			Thread.sleep(2000);
			break;
		case "1Expired":
			screen.type("EXPD");
			Thread.sleep(2000);
			break;
		case "Head Office Request":
			screen.type("HOREQ");
			Thread.sleep(2000);
			break;
		case "Lock code for new vintage or new wine":
			screen.type("NV");
			Thread.sleep(2000);
			break;
		case "Outlets Stock":
			screen.type("OS");
			Thread.sleep(2000);
			break;
		case "Product Recall":
			screen.type("PRODRECALL");
			Thread.sleep(2000);
			break;
		case "Return from RDC":
			screen.type("RDCRETURNS");
			Thread.sleep(2000);
			break;
		case "Supplier Damage":
			screen.type("SUDMG");
			Thread.sleep(2000);
			break;
		case "Return to Supplier":
			screen.type("SUPPRETURN");
			Thread.sleep(2000);
			break;
		case "Warehouse Damage":
			screen.type("WHDMG");
			Thread.sleep(2000);
			break;
		case "Hampers Stock":
			screen.type("HS");
			Thread.sleep(2000);
			break;
		case "Incubation lock code":
			screen.type("INCUB");
			Thread.sleep(2000);
			break;
		}
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

	public String getStatus() throws FindFailed, InterruptedException {
		Match status = screen.find("/images/InventoryTransactionQuery/lockStatus.png");
		screen.click(status.getCenter().offset(70, 0));
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

	public void selectRequiredRecord() throws FindFailed, InterruptedException {
		screen.wait("images/InventoryTransactionQuery/CodeInResults.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/CodeInResults.png");
		Thread.sleep(2000);
		Match mStatus = screen.find("images/InventoryTransactionQuery/CodeInResults.png");
		Thread.sleep(2000);
		screen.click(mStatus.below(10));
		Thread.sleep(2000);
		Match mStatuscode = screen.find("images/InventoryTransactionQuery/CodeInResult.png");
		screen.doubleClick(mStatuscode.below(1));
	}

	public boolean isOneOrNoTransactionDisplayed() throws FindFailed {
		if ((screen.find("images/InventoryTransactionQuery/Record1.png") != null)
				|| (screen.find("images/InventoryTransactionQuery/NoRecords.png") != null))
			return true;
		else
			return false;
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

	public boolean isNoRecordsExists() {
		if (screen.exists("images/InventoryTransactionQuery/NoRecord.png") != null)
			return true;
		else
			return false;
	}

}
