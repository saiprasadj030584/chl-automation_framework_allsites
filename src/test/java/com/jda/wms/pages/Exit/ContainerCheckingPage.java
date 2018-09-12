package com.jda.wms.pages.Exit;

import java.util.ArrayList;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class ContainerCheckingPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	
	public void selectSiteId(String SiteId) throws FindFailed {
		screen.wait("images/ContainerChecking/SiteId.png", timeoutInSec);
		screen.click("images/ContainerChecking/SiteId.png");
		screen.type(SiteId);
	}
	public void enterLocationContainerChecking(String location) throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/ContainerChecking/Location.png");
		screen.click(mLocation.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type(Key.BACKSPACE);
		screen.type(location);	
	}
	public boolean isupiPalletIdConatinerIdentered() {
		if ((screen.exists("images/ContainerChecking/Message.png") != null))
			return true;
		else
			return false;
	}
	public void enterContainerId(String container) throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/ContainerChecking/Container.png");
		screen.click(mLocation.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type(Key.BACKSPACE);
		screen.type(container);
		
	}
	
	public void enterQuantity(String quantity) throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/ContainerChecking/Quantity.png");
		screen.click(mLocation.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type(Key.BACKSPACE);
		screen.type(quantity);
		
	}
	public void enterCountedQuantity(String quantity) throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/ContainerChecking/CountedQuantity.png");
		screen.click(mLocation.getCenter().below(15));
		screen.type("a", Key.CTRL);
		screen.type(Key.BACKSPACE);
		screen.type(quantity);
		
	}
	 
	public void selectSkuId() throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/ContainerChecking/Sku.png");
		screen.doubleClick(mLocation.getCenter().below(15));
}
	public void clickSave()throws FindFailed, InterruptedException {
		screen.wait("images/ContainerChecking/Save.png", timeoutInSec);
		screen.click("images/ContainerChecking/Save.png");
		Thread.sleep(3000);
	}
	
	public void clickinitialCheckingDone()throws FindFailed, InterruptedException {
		screen.wait("images/ContainerChecking/InitialChecking.png", timeoutInSec);
		screen.click("images/ContainerChecking/InitialChecking.png");
		Thread.sleep(3000);
	}
	public boolean isPickShortFlagEnabled() {
		if ((screen.exists("images/ContainerChecking/PickShort.png") != null))
			return true;
		else
			return false;
	}
	public void clickPickShortFlag() throws FindFailed, InterruptedException {

		Match mLocation = screen.find("images/ContainerChecking/PickShort.png");
		screen.click(mLocation.getCenter().below(15));
		Thread.sleep(3000);
	}
	public boolean isExtraRemovalFlagEnabled() {
		if ((screen.exists("images/ContainerChecking/ExtraRemoval.png") != null))
			return true;
		else
			return false;
	}
	public void clickExtraRemovalFlag() throws FindFailed, InterruptedException {
		Match mLocation = screen.find("images/ContainerChecking/ExtraRemoval.png");
		screen.click(mLocation.getCenter().below(15));
		Thread.sleep(3000);
		
	}
	}
		
	
		
	
