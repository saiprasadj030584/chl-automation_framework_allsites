package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class KitLineMaintenancePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private JdaHomePage jdaHomePage;

	@Inject
	public KitLineMaintenancePage(JdaHomePage jdaHomePage) {
		this.jdaHomePage = jdaHomePage;
	}

	public void navigateToKitLine() throws FindFailed, InterruptedException {
		jdaHomePage.clickDataMenu();
		jdaHomePage.hoverSKU();
		hoverKit();
		clickKitLine();
	}

	public void hoverKit() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/Kit.png", timeoutInSec);
		screen.click("images/JDAHome/Kit.png");
		Thread.sleep(1000);
	}

	public void clickKitLine() throws FindFailed, InterruptedException {
		screen.mouseMove(150, 0);
		screen.wait("images/JDAHome/KitLine.png", timeoutInSec);
		screen.click("images/JDAHome/KitLine.png");
		Thread.sleep(1000);
	}

	public void enterSKUId(String skuId) throws FindFailed {
		screen.wait("images/JDAKitLine/SKUnoneditable.png", timeoutInSec);
		screen.click("images/JDAKitLine/SKUnoneditable.png");
		screen.type(skuId);
	}

	public String getKitId() throws FindFailed {
		Match mKitId = screen.find("/images/JDAKitLine/Kit.png");
		screen.click(mKitId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getQuantity() throws FindFailed {
		Match mQuantity = screen.find("/images/JDAKitLine/Quantity.png");
		screen.click(mQuantity.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getLineId() throws FindFailed {
		Match mLineId = screen.find("/images/JDAKitLine/LineID.png");
		screen.click(mLineId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
}
