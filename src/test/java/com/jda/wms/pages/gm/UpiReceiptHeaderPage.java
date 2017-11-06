package com.jda.wms.pages.gm;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class UpiReceiptHeaderPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private final JDAFooter jdaFooter;

	@Inject
	public UpiReceiptHeaderPage(JDAFooter jdaFooter) {
		this.jdaFooter = jdaFooter;
	}

	public void enterPallet(String palletId) throws FindFailed, InterruptedException {
		jdaFooter.clickQueryButton();
		screen.wait("images/StockAdjustment/Finish/ReasonCode.png", timeoutInSec);
		screen.type(palletId);
		jdaFooter.clickExecuteButton();
		Thread.sleep(2000);

	}

	public void enterPalletWithReference(String upiReference) throws InterruptedException {
		screen.type(upiReference);
		Thread.sleep(2000);
	}

	public boolean isNoRecordExist() {
		if (screen.exists("images/Putty/Receiving/ReceiveMenu.png") != null)
			return true;
		else
			return false;
	}

	public String getDueDate() throws FindFailed {
		Match mDueDate = screen.find("images/UpiReceiptHeader/DueDate.png");
		screen.click(mDueDate.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getReceiptDate() throws FindFailed {
		Match mDueDate = screen.find("images/UpiReceiptHeader/ReceiptDate.png");
		screen.click(mDueDate.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void fetchRecord(String upi) throws FindFailed, InterruptedException {
		screen.type(Key.F2);
		Thread.sleep(1000);
		screen.type(upi);
		Thread.sleep(1000);
		screen.type(Key.F7);
		Thread.sleep(1000);
	}

	public boolean isDueDateExists() throws FindFailed {
		Match mDueDate = screen.find("images/UpiReceiptHeader/DueDate.png");
		screen.click(mDueDate.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		String duedate = App.getClipboard();
		if (duedate != null) {
			return true;
		}
		return false;
	}

	public boolean isEJBerrorfound() {
		if (screen.exists("images/DuplicateOption/NoRecords.png") != null)
			return true;
		else
			return false;
	}

	public boolean isNoRecordFound() throws FindFailed {
		if (screen.exists("images/DuplicateOption/NoRecords.png") != null)
			return true;
		else
			return false;

	}

}
