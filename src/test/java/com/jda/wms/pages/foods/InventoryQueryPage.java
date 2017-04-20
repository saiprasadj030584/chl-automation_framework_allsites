package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class InventoryQueryPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public InventoryQueryPage() {
	}

	public void clickQueryButton() throws FindFailed, InterruptedException {
		screen.type(Key.F2);
		Thread.sleep(3000);
	}

	public void enterTagId(String tagId) throws FindFailed {
		// screen.wait("/images/JDASupplierSKU/SKU.png", timeoutInSec);
		// screen.click("/images/JDASupplierSKU/SKU.png");
		screen.type(tagId);
	}

	public void clickExecuteButton() throws InterruptedException {
		screen.type(Key.F7);
		Thread.sleep(3000);
	}

	public String getStatus() throws FindFailed, InterruptedException {
		Match status = screen.find("/images/InventoryQuery/Status.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(3000);
		return App.getClipboard();
	}

}
