package com.jda.wms.pages.foods;

import org.sikuli.script.Screen;

public class StockAdjustmentsPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private final JDAFooter jDAFooter;
	
	public StockAdjustmentsPage(JDAFooter jDAFooter) {
		this.jDAFooter = jDAFooter;
	}

}
