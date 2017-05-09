package com.jda.wms.pages.foods;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class ReceiptReversalPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private final JDAFooter jdaFooter;
	private final Context context;
	
	@Inject
	public ReceiptReversalPage(JDAFooter jdaFooter,Context context) {
		this.jdaFooter = jdaFooter;
		this.context = context;
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

	public boolean isQtyMultipleOfCaseRatio(int qtyToReverse) throws InterruptedException, FindFailed {
		context.setCaseRatio(16);
		int caseRatio = context.getCaseRatio();
		if (qtyToReverse % caseRatio == 0) {

			return true;
		} else
			return false;
	}
	
	public void scrollNext() throws FindFailed {
		screen.wait("images/ReceiptReversal/ScrollNext.png", timeoutInSec);
		screen.click("images/ReceiptReversal/ScrollNext.png");
	}
	
	public void enterQtyToReverse(String qtyReverse) throws FindFailed, InterruptedException {
		Match mQtyToReverse = screen.find("images/ReceiptReversal/Reversals/QtyToReverse.png");
		Thread.sleep(2000);
		screen.click(mQtyToReverse.below(10));
		screen.type(qtyReverse);
		Thread.sleep(3000);
	}
	
	public void enterReasonCode(String reasonCode) throws FindFailed {
//		Match mreasonCode = screen.find("images/ReceiptReversal/ReasonCode.png");
//		screen.click(mreasonCode.getCenter().offset(70, 0));
		screen.type(reasonCode);
	}
	

}
