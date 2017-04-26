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
	private final InventoryQueryPage inventoryQueryPage;

	@Inject
	public InventoryTransactionQueryPage(JDAFooter jdaFooter, InventoryQueryPage inventoryQueryPage) {
		this.jdaFooter = jdaFooter;
		this.inventoryQueryPage = inventoryQueryPage;
	}

	public void enterCode(String code) throws FindFailed, InterruptedException {
		screen.wait("images/InventoryTransactionQuery/Code.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/Code.png");
		screen.type(code);
		Thread.sleep(2000);
	}

	public void enterTagId(String tagId) throws InterruptedException, FindFailed {
		Match mtagId = screen.find("images/InventoryTransactionQuery/TagID.png");
		screen.click(mtagId.getCenter().offset(70, 0));
		screen.type(tagId);
		Thread.sleep(3000);

	}

	public void enterTransactionDate() throws FindFailed, InterruptedException {
		Match transactionDate = screen.find("images/InventoryTransactionQuery/TransactionDate.png");
		screen.click(transactionDate.getCenter().offset(70, 0));
		screen.type("0");
		Thread.sleep(3000);
	}

	public void enterLockCode(String lockCode) throws FindFailed, InterruptedException {
		Match ilockCode = screen.find("images/InventoryTransactionQuery/lockCode.png");
		screen.click(ilockCode.getCenter().offset(70, 0));

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

	public void selectCode(String code) throws FindFailed, InterruptedException {
		jdaFooter.clickQueryButton();
		screen.type(code);
		screen.type(Key.TAB);
	}

	public String getOriginalQty() throws FindFailed {
		Match mStatus = screen.find("images/InventoryTransactionQuery/General/OriginalQty.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getStatus() throws FindFailed, InterruptedException {
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

	// TODO move these step into step definitons
	// public void searchTagId(String tagId, String code) throws FindFailed,
	// InterruptedException {
	// jdaFooter.clickQueryButton();
	// enterCode(tagId, code);
	// inventoryQueryPage.enterTagId(tagId);
	// jdaFooter.clickExecuteButton();
	// }

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

	public String getExpiryDate() throws FindFailed {
		Match mExpiryDate = screen.find("images/InventoryTransactionQuery/Miscellaneous/ExpiryDate.png");
		screen.click(mExpiryDate.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
}
