package com.jda.wms.pages.gm;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class OrderContainerPage {

	Screen screen = new Screen();
	private Context context;

	@Inject
	public OrderContainerPage(Context context) {
		this.context = context;
	}

	
	public void queryWithOrderId(String orderId) throws FindFailed, InterruptedException 
	{
		screen.type(orderId);
		Thread.sleep(1000);
		screen.type(Key.F7);
	}
	
}
