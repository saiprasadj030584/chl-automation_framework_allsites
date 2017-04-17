package com.jda.wms.pages.foods;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

public class SKUMaintenancePage {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final FooterPage footerPage;
	
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public SKUMaintenancePage(FooterPage footerPage) {
		this.footerPage = footerPage;
		
	}

	public void searchSKUid(String skuId) throws FindFailed {
		clickQuery();
		enterSKUID(skuId);
		clickExecute();
	}

	private void clickExecute() {
		//screen.wait("images/execute.PNG", timeoutInSec);
		//screen.click("images/execute.PNG");
		screen.type(Key.F7);
	}

	private void enterSKUID(String skuId) throws FindFailed {
		screen.wait("images/SKUMaintenanceTable/SKU-ID-Search.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/SKU-ID-Search.png");
		screen.type(skuId);
	}

	private void clickQuery() throws FindFailed {
		//screen.wait("images/Query.PNG", timeoutInSec);
		//screen.click("images/Query.PNG");
		screen.type(Key.F2);
	}

	public String getSKUid() throws FindFailed, InterruptedException {
		String SKUid = null;
		clickSKUID();
		Match mStatus = screen.find("images/SKUMaintenanceTable/SKU-ID.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		SKUid = App.getClipboard();
		logger.debug("SKU Id is: " + SKUid);
		return SKUid;
		}

	private void clickSKUID() throws FindFailed, InterruptedException {
		screen.wait("images/SKUMaintenanceTable/SKU-ID.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/SKU-ID.png");
		Thread.sleep(3000L);
	}

	public String getSKUDescription() throws FindFailed, InterruptedException {
		String skudesc = null;
		clickSKUDescription();
		Match mStatus = screen.find("images/SKUMaintenanceTable/SKU-Description.png");
		screen.click(mStatus.getCenter().offset(100, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		skudesc = App.getClipboard();
		logger.debug("SKU Id is: " + skudesc);
		return skudesc;
		
		
	}

	private void clickSKUDescription() throws FindFailed, InterruptedException {
		screen.wait("images/SKUMaintenanceTable/SKU-Description.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/SKU-Description.png");
		Thread.sleep(3000L);
	}

}
