package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class OrderManagementPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public OrderManagementPage() {
	}

	public void enterOrderId(String orderId) throws FindFailed {
		Match morderId = screen.find("images/OrderManagement/OrderID.png");
		screen.click(morderId.getCenter().offset(70, 0));
		screen.type(orderId);
	}

	public boolean isRecordDisplayed() {
		if (screen.exists("images/OrderManagement/Record.png") != null) {
			return true;
		} else
			return false;
	}

	public void navigateOrderHeader() throws FindFailed, InterruptedException {
		screen.wait("images/OrderManagement/SiteId.png", timeoutInSec);
		Match mQty = screen.find("images/OrderManagement/SiteId.png");
		Thread.sleep(2000);
		screen.doubleClick(mQty.below(10));
		Thread.sleep(2000);
	}

	public void clickOrderManagement() throws FindFailed {
		Match morderId = screen.find("images/OrderManagement/OrderManagement.png");
		screen.click(morderId.getCenter().offset(70, 0));
		
	}
	
	public void enterOrderNo(String OrderNo) throws FindFailed {
		screen.wait("images/OrderManagement/Order.png", timeoutInSec);
		screen.click("images/OrderManagement/Order.png");
//		screen.type("a",Key.CTRL);
//		screen.type(Key.BACKSPACE);
		screen.type(OrderNo);
	}

	public void clickHeaderTable() throws FindFailed, InterruptedException {
		screen.wait("images/OrderManagement/Site.png", timeoutInSec);
		Match mQty = screen.find("images/OrderManagement/Site.png");
		Thread.sleep(2000);
		screen.click(mQty.below(10));
	}
	
	public void clickOrderDate() throws FindFailed, InterruptedException {
		screen.wait("images/OrderManagement/OrderDate.png", timeoutInSec);
		screen.click("images/OrderManagement/OrderDate.png");
	}

	public void clickUpdateButton() throws FindFailed {
		screen.wait("images/OrderManagement/UpdateStatus.png", timeoutInSec);
		screen.click("images/OrderManagement/UpdateStatus.png");
	}
	
	public void clickDropDown() throws FindFailed {
		screen.wait("images/OrderManagement/DropDown.png", timeoutInSec);
		screen.click("images/OrderManagement/DropDown.png");
	}
	
	public void selectCancelled() throws FindFailed {
		screen.wait("images/OrderManagement/Cancelled.png", timeoutInSec);
		screen.click("images/OrderManagement/Cancelled.png");
	} 
	
	public void selectReleased() throws FindFailed {
		screen.wait("images/OrderManagement/Released.png", timeoutInSec);
		screen.click("images/OrderManagement/Released.png");
	}
	
	public void clickOk() throws FindFailed {
		screen.wait("images/OrderManagement/Ok.png", timeoutInSec);
		screen.click("images/OrderManagement/Ok.png");
	}
	
	public void clickYes() throws FindFailed {
		screen.wait("images/OrderManagement/Yes.png", timeoutInSec);
		screen.click("images/OrderManagement/Yes.png");
	}
	
	public void clickAvailable() throws FindFailed, InterruptedException {
		screen.wait("images//MoveTaskManagement/Available.png", timeoutInSec);
		Match mQty = screen.find("images//MoveTaskManagement/Available.png");
		Thread.sleep(2000);
		screen.click(mQty.below(30));
	}

}
