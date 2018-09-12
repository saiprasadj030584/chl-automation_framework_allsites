package com.jda.wms.pages.Exit;

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
		scrollNext();
		Match mQtyToReverse = screen.find("images/ReceiptReversal/Reversals/QtyToReverse.png");
		Thread.sleep(2000);
		
		screen.click(mQtyToReverse.below(10));
		screen.type(Integer.toString(qtyReverse));
		Thread.sleep(3000);
	}

	public void enterReasonCode(String reasonCode) throws FindFailed {
		screen.type(reasonCode);
	}

	public boolean isQtyToReverseExists() throws FindFailed {
		if (screen.find("images/ReceiptReversal/Reversals/QtyToReverse.png").equals(null)) {
			return false;
		} else
			return true;

	}

	public void enterReasonCodes(String reasonCode) throws FindFailed {
		Match status = screen.find("images/ReceiptReversal/Reversals/ReasonCode.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type(reasonCode);
		screen.type(Key.ENTER);
	}
}
