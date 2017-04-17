package com.jda.wms.pages.foods;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.pages.PageObject;

public class JdaHomePage extends PageObject {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public JdaHomePage(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
	}

	public void navigateToOrderHeader() throws FindFailed, InterruptedException {
		clickDataMenu();
		hoverOrder();
		clickOrderHeader();
	}
	
	public void navigateToSKUMaintanence() throws FindFailed {
		clickDataMenu();
		hoverSKU();
		clickSKUsubmenu();
	}

	private void clickSKUsubmenu() throws FindFailed {
		screen.wait("images/Menu/Data/Sku/skuSku.png", timeoutInSec);
		screen.click("images/Menu/Data/Sku/skuSku.png");
	}

	private void hoverSKU() throws FindFailed {
		screen.wait("images/Menu/Data/dataSku.png", timeoutInSec);
		screen.click("images/Menu/Data/dataSku.png");
	}

	private void clickDataMenu() throws FindFailed {
		screen.wait("images/DataMenu.png", timeoutInSec);
		screen.click("images/DataMenu.png");
	}

	private void hoverOrder() throws FindFailed {
		screen.wait("images/OrderSubmenu.png", timeoutInSec);
		screen.click("images/OrderSubmenu.png");
	}

	private void clickOrderHeader() throws FindFailed {
		screen.wait("images/OrderHeader.png", timeoutInSec);
		screen.click("images/OrderHeader.png");
	}

}
