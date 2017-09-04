package com.jda.wms.pages.gm;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class SystemAllocationPage {

	Screen screen = new Screen();
	private Context context;

	@Inject
	public SystemAllocationPage(Context context) {
		this.context = context;
	}

	public void enterOrderID() throws InterruptedException {
		screen.type(context.getOrderId());
		Thread.sleep(1000);
	}

	public String getConsignmentID() throws FindFailed {
		Match mConsignmentID = screen.find("images/PreAdviceHeader/ConsignmentID.png");
		screen.click(mConsignmentID.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	
	public void enterOrderId(String orderId) throws InterruptedException {
		screen.type(orderId);
		Thread.sleep(1000);
	}
}
