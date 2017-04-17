package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class SupplierSKUMaintenancePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public SupplierSKUMaintenancePage() {
	}

	public void clickQueryButton() throws FindFailed, InterruptedException {
		screen.type(Key.F2);
		Thread.sleep(3000);
	}

	public void enterSkuId(String skuId) throws FindFailed {
		// screen.wait("/images/JDASupplierSKU/SKU.png", timeoutInSec);
		// screen.click("/images/JDASupplierSKU/SKU.png");
		screen.type(skuId);
	}

	public void enterSupplier(String supplierId) throws FindFailed {
		screen.wait("/images/JDASupplierSKU/Supplier.png", timeoutInSec);
		screen.click("/images/JDASupplierSKU/Supplier.png");
		screen.type(supplierId);
	}

	public void clickExecuteButton() throws InterruptedException {
		screen.type(Key.F7);
		Thread.sleep(3000);
	}

	public String getDescription() throws FindFailed {
		Match mDescription = screen.find("/images/JDASupplierSKU/Description.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getSupplierSKU() throws FindFailed {
		Match msupplierSKU = screen.find("/images/JDASupplierSKU/SupplierSKU.png");
		screen.click(msupplierSKU.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
}
