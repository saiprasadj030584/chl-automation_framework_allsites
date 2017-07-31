package com.jda.wms.pages.gm;

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

	public boolean isNoRecordExist() {
		if (screen.exists("images/Putty/Receiving/ReceiveMenu.png") != null)
			return true;
		else
			return false;
	}
}
