package com.jda.wms.pages.foods;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class InventoryUpdatePage1 {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterABV(String newAbv) throws FindFailed, InterruptedException {
		screen.wait("images/InventoryUpdate/Finish/ABVCheck.png", timeoutInSec);
		screen.click("images/InventoryUpdate/Finish/ABVCheck.png");
		Thread.sleep(5000);
		screen.wait("images/InventoryUpdate/Finish/ABVCheck.png", timeoutInSec);
		screen.click("images/InventoryUpdate/Finish/ABVCheck.png");
		screen.type(newAbv);
		
		
	}
	public void selectReasonCode(String reasonCode) throws FindFailed, InterruptedException {
		Match ireasonCode = screen.find("/images/InventoryUpdate/LockStatusChange/reasonCode.png");
		screen.click(ireasonCode.getCenter().offset(70, 0));
		screen.type(reasonCode);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
		
		
	}
	
}
