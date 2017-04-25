package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class InventoryQueryPage1 {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void clickQueryButton() throws InterruptedException {
		screen.type(Key.F2);
		Thread.sleep(3000);
	}

	public void clickExecuteButton() throws InterruptedException {
		screen.type(Key.F7);
		Thread.sleep(3000);
		
	}

	public void enterTagId(String tagId) throws FindFailed {
		screen.wait("images/InventoryQuery/TagId.png", timeoutInSec);
		screen.click("images/InventoryQuery/TagId.png");
		screen.type(tagId);
		
	}

	public String getSkuId() throws FindFailed, InterruptedException {
		Match status = screen.find("images/InventoryQuery/Sku.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(3000);
		return App.getClipboard();
		
	}

	public String getProductGroup() throws FindFailed, InterruptedException {
		Match status = screen.find("images/InventoryQuery/UserDefined/ProductGroup.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(3000);
		return App.getClipboard();
		
	}
	
	public String getABV() throws FindFailed, InterruptedException {
		Match status = screen.find("images/InventoryQuery/UserDefined/ABV.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(3000);
		return App.getClipboard();
		
	}

	public void clickUserDefinedTab() throws FindFailed {
		screen.wait("images/InventoryQuery/UserDefined/UserDefined.png", timeoutInSec);
		screen.click("images/InventoryQuery/UserDefined/UserDefined.png");
		
	}

	public String checkUpdatedABV() throws FindFailed, InterruptedException {
		Match status = screen.find("images/InventoryQuery/UserDefined/ABV.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(3000);
		return App.getClipboard();
		
		
	}

	private void selectRefreshCurrentRecord() throws FindFailed {
		screen.wait("images/RefreshCurrentRecord.png", timeoutInSec);
		screen.click("images/RefreshCurrentRecord.png");
		
	}

	private void selectRefreshOptions() throws FindFailed {
		screen.wait("images/RefreshOption.png", timeoutInSec);
		screen.click("images/RefreshOption.png");
		
	}
	public void refreshInventoryQueryPage() throws FindFailed {
		screen.rightClick();
		selectRefreshOptions();
		selectRefreshCurrentRecord();
		
	}

	

}
