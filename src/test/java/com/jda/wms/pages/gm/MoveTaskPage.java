package com.jda.wms.pages.gm;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class MoveTaskPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterOrderId(String orderId) throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("VALUE OF ORDERID =" + orderId);
		screen.type(orderId);
		Thread.sleep(1000);
	}

	public String getListId() throws FindFailed {
		Match mSkuId = screen.find("images/MoveTaskUpdate/ListID.png");
		screen.click(mSkuId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public boolean moveTaskHomePage() {
		if (screen.exists("images/JDAHome/PreAdviceHeaderHomePage.png") != null)
			return true;
		else
			return false;
	}
}
