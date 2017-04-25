package com.jda.wms.pages.foods;

import java.awt.Robot;
import java.io.IOException;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

import net.sourceforge.htmlunit.corejs.javascript.tools.shell.Environment;

public class POReceivingFromPuttyPage {
	private Robot robot;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public POReceivingFromPuttyPage(Robot robot) {
		this.robot = robot;
	}
	
	public void invokePutty() throws IOException {
		Process putty = Runtime.getRuntime().exec("putty.exe");
	}

	public void loginPutty(String host, String port) throws FindFailed, InterruptedException {
		screen.wait("images/Putty/HostName.png", timeoutInSec);
		screen.click("images/Putty/HostName.png");
		screen.type(host);
		screen.type(Key.TAB);
		screen.type(port);
		
		screen.wait("images/Putty/Telnet.png", timeoutInSec);
		screen.click("images/Putty/Telnet.png");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		
		screen.wait("images/Putty/Username.png", timeoutInSec);
		screen.type("P9120368");
		screen.type(Key.TAB);
		screen.type("12345");
		screen.type(Key.ENTER);
	}

	public void selectUserDirectedMenu() throws FindFailed, InterruptedException {
		while (screen.exists("images/Putty/2UserDirected.png") == null){
			screen.type(Key.F12);
		}
		screen.type("2");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectReceiveMenu() throws FindFailed, InterruptedException {
		while (screen.exists("images/Putty/Receiving/1Receive.png") == null){
			screen.type(Key.F12);
		}
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectBasicReceiveMenu() throws FindFailed, InterruptedException {
		while (screen.exists("images/Putty/Receiving/1BasicReceiving.png") == null){
			screen.type(Key.F12);
		}
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectPreAdviceReceive() throws FindFailed, InterruptedException {
		while (screen.exists("images/Putty/Receiving/2PreAdvReceive.png") == null){
			screen.type(Key.F12);
		}
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public boolean isPreAdviceEntryDisplayed() throws FindFailed, InterruptedException {
		if (screen.exists("images/Putty/Receiving/PreAdvEntry.png") != null)
			return true;
		else
			return false;
	}

	public void enterPreAdvEntryDetails(String preAdviceId, String skuID) throws FindFailed, InterruptedException {
		screen.type(preAdviceId);
		screen.type(Key.TAB);
		Thread.sleep(1000);
		
		screen.type(skuID);
		screen.type(Key.ENTER);
		Thread.sleep(8000);
	}

	public String getPreAdvDisplayed() throws FindFailed {
		Match mStatus = screen.find("images/Putty/Receiving/Pre-AdviceDispalyed.png");
		screen.click(mStatus.getBottomLeft().offset(10, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getSupplierDisplayed() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Receiving/SuppDisplayed.png");
		screen.click(mStatus.getBottomLeft().offset(20, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public void enterLocation(String location) throws InterruptedException {
		screen.type(location);
		screen.type(Key.TAB);
		Thread.sleep(1000);
	}

	public void enterUniqueTagId(String uniqueId) throws InterruptedException {
		screen.type(uniqueId);
		screen.type("x", Key.CTRL);
		screen.type(Key.NUM4);
		Thread.sleep(2000);
		
	}

	public void enterQtyToReceive(String qtyToReceive) throws InterruptedException {
		screen.type(qtyToReceive);
		screen.type(Key.TAB);
		Thread.sleep(1000);
	}

	public void enterCaseRatio(String caseRatio) throws InterruptedException {
		screen.type(caseRatio);
		screen.type("x", Key.CTRL);
		screen.type(Key.NUM4);
		Thread.sleep(2000);
	}

	public void enterExpiryDate(String expDate) throws InterruptedException {
		screen.type(expDate);
		screen.type(Key.ENTER);
		Thread.sleep(6000);
	}

}
