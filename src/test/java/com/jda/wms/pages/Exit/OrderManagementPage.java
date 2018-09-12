package com.jda.wms.pages.Exit;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class OrderManagementPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject

	public OrderManagementPage() {
	}

	public void selectSiteId(String siteId) throws FindFailed, InterruptedException {
		Match mSiteId = screen.find("images/OrderManagement/SiteId.png");
		screen.click(mSiteId.getCenter().offset(70, 0));
		screen.type(siteId);
		Thread.sleep(3000);
	}

	public void enterOrderId(String orderId) throws FindFailed, InterruptedException {
		screen.wait("images/OrderManagement/Order.png", timeoutInSec);
		screen.click("images/OrderManagement/Order.png");
		// Thread.sleep(3000);
		Match mOrderId = screen.find("images/OrderManagement/Order.png");
		screen.click(mOrderId.getCenter().offset(70, 0));
		screen.type(orderId);
		Thread.sleep(3000);
	}

	public void selectRecord() throws FindFailed, InterruptedException {
		Match mRecord = screen.find("images/OrderManagement/FromSiteId.png");
		Thread.sleep(2000);
		screen.click(mRecord.below(10));
		Thread.sleep(3000);
	}

	public void clickUpdateStatus() throws FindFailed {
		screen.wait("images/OrderManagement/UpdateStatus.png", timeoutInSec);
		screen.click("images/OrderManagement/UpdateStatus.png");
	}

	public void selectStatus(String status) throws FindFailed {
		screen.wait("images/OrderManagement/SelectStatus.png", timeoutInSec);
		screen.click("images/OrderManagement/SelectStatus.png");
		screen.type(status);
		screen.type(Key.ENTER);
	}

	public void clickOk() throws FindFailed {
		screen.wait("images/OrderManagement/Ok.png", timeoutInSec);
		screen.click("images/OrderManagement/Ok.png");
	}

	public boolean isRecordExist() throws FindFailed {
		if (!screen.find("images/OrderManagement/Record.png").equals(null)) {
			return true;
		} else
			return false;
	}

}