package com.jda.wms.pages.rdt;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class PurchaseOrderStockCheckPage {
	Screen screen = new Screen();
	Context context = new Context();
	int timeoutInSec = 20;
	private PuttyFunctionsPage puttyFunctionsPage;

	@Inject
	public PurchaseOrderStockCheckPage(PuttyFunctionsPage puttyFunctionsPage, Context context) {
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.context = context;
	}

	public void selectInventoryMenu() throws FindFailed, InterruptedException {
		screen.type("5");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	
	public void selectNewStockCheck() throws FindFailed, InterruptedException {
		screen.type("2");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	
	public boolean isNewStockCheckEntPageDisplayed() {
		if (screen.exists("images/Putty/StockCheck/NewStockCheck/StkNewEnt.png") != null)
			return true;
		else
			return false;
	}
	
	public void i_enter_location(String location) throws FindFailed, InterruptedException {
		screen.type(location);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	
	public void i_enter_no_or_yes(String asn) throws InterruptedException {
		screen.type(asn);
		Thread.sleep(2000);
	}
	
	public void i_enter_supplier(String supplier) throws InterruptedException {
		screen.type(supplier);
		Thread.sleep(2000);
	}
	
	public void i_enter_upc(String upc) throws InterruptedException {
		screen.type(upc);
		Thread.sleep(2000);
	}
}

