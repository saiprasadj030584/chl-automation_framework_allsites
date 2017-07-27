package com.jda.wms.pages.gm;
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
	public void selectReceiptType(String receiptType) {
		screen.type(receiptType);
		
	}
	public void enterTagId(String tagId) throws FindFailed {
		Match mtagId = screen.find("images/ReceiptReversal/Start/TagID.png");
		screen.click(mtagId.getCenter().offset(70, 0));
		screen.type(tagId);
		
	}
	public void checkTheCheckbox() throws FindFailed {
		Match mtagId = screen.find("images/ReceiptReversal/Reversals/checkbox.png");
		screen.click(mtagId.getCenter());
		
	}

}
