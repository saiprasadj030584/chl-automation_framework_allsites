package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class UnPickingUnShippingPage {

	Screen screen = new Screen();
	private Context context;

	@Inject
	public UnPickingUnShippingPage(Context context) {
		this.context = context;
	}

	public void performUnpick() throws FindFailed, InterruptedException {
		screen.type("5649");
		screen.type(Key.F7);
		Thread.sleep(1000);
		Match mDescription = screen.find("images/UnPicking/Order.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type(context.getOrderId());
		screen.type(Key.F7);
		screen.type(Key.F7);
		screen.type(Key.F12);
	}

}
