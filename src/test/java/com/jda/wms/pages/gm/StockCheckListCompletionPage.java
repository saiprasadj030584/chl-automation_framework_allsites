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
		screen.wait("images/StockCheckQuery/Location.png", timeoutInSec);
		screen.click("images/StockCheckQuery/Location.png");
		screen.type(listID);
	}

	public void enterUpdateQty(String string) throws FindFailed {
		screen.wait("images/StockCheckQuery/Location.png", timeoutInSec);
		screen.click("images/StockCheckQuery/Location.png");
		screen.type(string);
	}

	public String getSelectedListConfirmationText() throws FindFailed, InterruptedException {
		Match mConfirmationText = screen.find("/images/StockCheckListGeneration/SelectedListConfirmationText.png");
		screen.click(mConfirmationText.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(1000);
		return App.getClipboard();
	}
}
