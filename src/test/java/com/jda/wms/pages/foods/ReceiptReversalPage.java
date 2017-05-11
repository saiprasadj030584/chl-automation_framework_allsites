package com.jda.wms.pages.foods;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class ReceiptReversalPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public ReceiptReversalPage() {
	}

	public void selectReceiptType(String receiptType) throws FindFailed, InterruptedException {
		screen.type(receiptType);
	}

	public void enterTagId(String tagId) throws InterruptedException, FindFailed {
		Match mtagId = screen.find("images/ReceiptReversal/Start/TagID.png");
		screen.click(mtagId.getCenter().offset(70, 0));
		screen.type(tagId);
	}

	public boolean isRecordExists() throws InterruptedException, FindFailed {
		if (!screen.find("images/ReceiptReversal/Reversals/Pallet.png").equals(null)) {
			return true;
		} else
			return false;
	}

	public void scrollNext() throws FindFailed {
		screen.wait("images/ReceiptReversal/ScrollNext.png", timeoutInSec);
		screen.click("images/ReceiptReversal/ScrollNext.png");
	}

	public void enterQtyToReverse(int qtyReverse) throws FindFailed, InterruptedException {
		// Match mQtyToReverse =
		// screen.find("images/ReceiptReversal/Reversals/QtyToReverse.png");
		// Thread.sleep(2000);
		// screen.click(mQtyToReverse.below(10));
		screen.type(Integer.toString(qtyReverse));
		Thread.sleep(3000);
	}

	public void enterReasonCode(String reasonCode) throws FindFailed {
		screen.type(reasonCode);
	}

	public void clickRecord() throws FindFailed, InterruptedException {
		Match mReceiptId = screen.find("images/ReceiptReversal/ReceiptId.png");
		Thread.sleep(2000);
		screen.click(mReceiptId.below(10));
		Thread.sleep(3000);

		for (int i = 1; i <= 13; i++) {
			// receiptReversalPage.scrollNext();
			screen.type(Key.TAB);
		}
		Thread.sleep(1000);

	}
}
