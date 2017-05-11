package com.jda.wms.pages.rdt;

import java.io.IOException;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class PurchaseOrderReceivingPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public PurchaseOrderReceivingPage() {
	}

	public void invokePutty() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("putty.exe");
		Thread.sleep(2000);
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
	}

	public void selectUserDirectedMenu() throws FindFailed, InterruptedException {
		screen.type("2");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectReceiveMenu() throws FindFailed, InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectBasicReceiveMenu() throws FindFailed, InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectPreAdviceReceive() throws FindFailed, InterruptedException {
		screen.type("2");
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

	public void enterPreAdvId(String preAdviceId) throws FindFailed, InterruptedException {
		screen.type(preAdviceId);
		Thread.sleep(1000);
	}

	public void enterSKUId(String skuID) throws FindFailed, InterruptedException {
		screen.type(skuID);
		screen.type(Key.ENTER);
		Thread.sleep(8000);
	}

	public String getPreAdvId() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Receiving/Pre-AdviceDisplayed.png");
		screen.click(mStatus.below(10));
		Thread.sleep(2000);
		screen.doubleClick(mStatus.below(1));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getSupplierId() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Receiving/SuppDisplayed.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.doubleClick(mStatus.getCenter().offset(50, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public void enterLocation(String location) throws InterruptedException, FindFailed {
		screen.wait("images/Putty/Receiving/Location.png", timeoutInSec);
		screen.click("images/Putty/Receiving/Location.png");
		screen.type(location);
		screen.type(Key.TAB);
		Thread.sleep(1000);
	}

	public void enterTagId(String uniqueId) throws InterruptedException {
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
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		Thread.sleep(1000);
		screen.type(expDate);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
	}

	public boolean isMainMenuDisplayed() {
		if (screen.exists("images/Putty/MainMenu.png") != null)
			return true;
		else
			return false;
	}

	public boolean isUserMenuDisplayed() {
		if (screen.exists("images/Putty/UserMenu.png") != null)
			return true;
		else
			return false;
	}

	public boolean isReceiveMenuDisplayed() {
		if (screen.exists("images/Putty/Receiving/ReceiveMenu.png") != null)
			return true;
		else
			return false;
	}

	public boolean isBasicReceiveMenuDisplayed() {
		if (screen.exists("images/Putty/Receiving/BasicReceiveMenu.png") != null)
			return true;
		else
			return false;
	}

	public boolean isRcvPreCmp2Displayed() {
		if (screen.exists("images/Putty/Receiving/RcvPreCmp2.png") != null)
			return true;
		else
			return false;
	}

	public boolean isRcvPreCmp3Displayed() {
		if (screen.exists("images/Putty/Receiving/RcvPreCmp3.png") != null)
			return true;
		else
			return false;
	}

	public void enterJdaLogin(String username, String pwd) throws FindFailed, InterruptedException {
		screen.wait("images/Putty/Username.png", timeoutInSec);
		screen.type(username);
		screen.type(Key.TAB);
		screen.type(pwd);
		screen.type(Key.ENTER);
		Thread.sleep(4000);
	}

	public boolean isLoginScreenDisplayed() {
		if (screen.exists("images/Putty/Username.png") != null)
			return true;
		else
			return false;
	}

	public String getSKUId() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Receiving/SKUDisplayed.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.doubleClick(mStatus.getCenter().offset(50, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	}

}
