package com.jda.wms.pages.gm;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class StockCheckListCompletionPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterListID(String listID) throws FindFailed {
		screen.wait("images/StockCheckCompletion/ListId.png", timeoutInSec);
		screen.click("images/StockCheckCompletion/ListId.png");
		screen.type(listID);
	}

	public void enterUpdateQty(String string) throws FindFailed, InterruptedException {

		screen.wait("images/StockCheckCompletion/UpdateQty.png", timeoutInSec);
		Match mQty = screen.find("images/StockCheckCompletion/UpdateQty.png");
		Thread.sleep(2000);
		screen.click(mQty.below(10));
		Thread.sleep(2000);
		screen.type(string);

	}

	public String getSelectedListConfirmationText() throws FindFailed, InterruptedException {
		Match mConfirmationText = screen.find("images/StockCheckCompletion/ConfirmationMsg.png");
		screen.click(mConfirmationText.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(1000);
		return App.getClipboard();
	}
}
