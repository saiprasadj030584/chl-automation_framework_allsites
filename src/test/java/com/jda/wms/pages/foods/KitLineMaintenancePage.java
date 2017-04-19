package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
//import com.jda.wms.pages.PageObject;

//public class KitLineMaintenancePage extends PageObject {
public class KitLineMaintenancePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	// public KitLineMaintenancePage(WebDriver webDriver) {
	// super(webDriver);
	// this.webDriver = webDriver;
	// }
	public KitLineMaintenancePage() {

	}

	public void navigateToKitLine() throws FindFailed, InterruptedException {
		clickDataMenu();
		hoverSKU();
		hoverKit();
		clickKitLine();
	}

	public void clickDataMenu() throws FindFailed {
		screen.wait("images/JDAHome/DataMenu.png", timeoutInSec);
		screen.click("images/JDAHome/DataMenu.png");
	}

	public void hoverSKU() throws FindFailed {
		screen.wait("images/JDAHome/SKU.png", timeoutInSec);
		screen.click("images/JDAHome/SKU.png");
		screen.mouseMove(80, 0);
	}

	public void hoverKit() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/Kit.png", timeoutInSec);
		screen.click("images/JDAHome/Kit.png");

		// screen.click();
		Thread.sleep(1000);
		// screen.mouseMove(0, 20);

	}

	public void clickKitLine() throws FindFailed, InterruptedException {
		screen.mouseMove(150, 0);
		screen.wait("images/JDAHome/KitLine.png", timeoutInSec);
		screen.click("images/JDAHome/KitLine.png");
		Thread.sleep(1000);
	}

	public void clickQueryButton() {
		screen.type(Key.F2);
	}

	public void enterSKUId(String skuId) throws FindFailed {
		screen.wait("images/JDAKitLine/SKU.png", timeoutInSec);
		screen.click("images/JDAKitLine/SKU.png");
		screen.type(skuId);
	}

	public void clickExecuteButton() throws FindFailed {
		screen.type(Key.F7);
	}
	
	public String getKit() throws FindFailed {
		Match mKit = screen
				.find("/images/JDAKitLine/Kit.png");
		screen.click(mKit.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	public String getSKUId() throws FindFailed {
		Match mSKUId = screen
				.find("/images/JDAKitLine/SKUnoneditable.png");
		screen.click(mSKUId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	public String getQuantity() throws FindFailed {
		Match mQuantity = screen
				.find("/images/JDAKitLine/Quantity.png");
		screen.click(mQuantity.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	public String getLineId() throws FindFailed {
		Match mLineId = screen
				.find("/images/JDAKitLine/LineID.png");
		screen.click(mLineId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
}
