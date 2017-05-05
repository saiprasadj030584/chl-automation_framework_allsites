package com.jda.wms.pages.foods;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class ReceiptReversalPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private final JDAFooter jdaFooter;
	
	@Inject
	public ReceiptReversalPage(JDAFooter jdaFooter) {
		this.jdaFooter = jdaFooter;
	}
	
	public void selectReceiptType(String receiptType) throws FindFailed, InterruptedException {
		screen.type(receiptType);
	}
	
	public void enterTagId(String tagId) throws InterruptedException, FindFailed {
		Match mtagId = screen.find("images/ReceiptReversal/TagID.png");
		screen.click(mtagId.getCenter().offset(70, 0));
		screen.type(tagId);
	}
	
	public boolean isRecordExists() throws InterruptedException, FindFailed {
		if(!screen.find("images/ReceiptReversal/Reversals/Pallet.png").equals(null))
				{
			return false;
				}
		else return true;
	}
	
	
	
}
