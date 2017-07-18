package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class DeliveryManagementPage {
	private JDAFooter jDAFooter;
	Screen screen = new Screen();
	int timeoutInSec = 20;
	
	public void enterAsnId(String asnId) throws InterruptedException, FindFailed {
		System.out.println("ASN1="+asnId);
		screen.type(asnId);
		Thread.sleep(1000);
	}
	
	public void chooseDeliveryStatus(String status) throws InterruptedException, FindFailed {
		screen.wait("images/DeliveryManagement/DeliveryStatus.png", timeoutInSec);
		screen.type(status);
		Thread.sleep(1000);
	}

	public void clickDeliveryRecord() throws FindFailed, InterruptedException {
		Match mSiteId = screen.find("images/DeliveryManagement/SiteId.png");
		Thread.sleep(1000);
		screen.click(mSiteId.below(10));
		Thread.sleep(2000);
	}

	public void isUpdateStatusExistAndClick() throws FindFailed, InterruptedException {
		if (screen.exists("images/DeliveryManagement/UpdateStatus.png") != null){
			screen.click("images/DeliveryManagement/UpdateStatus.png");
			Thread.sleep(2000);
		}
	}

	public void clickOkButton() throws InterruptedException, FindFailed {
		Match mStatus = screen.find("images/DeliveryManagement/Ok_button.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		Thread.sleep(2000);
		jDAFooter.PressEnter();
		Thread.sleep(1000);
	}

	public void isWarningPopUpExistsAndClickYes() throws FindFailed, InterruptedException {
		if (screen.exists("images/DeliveryManagement/WarningPopUp.png") != null){
			jDAFooter.PressEnter();
			Thread.sleep(2000);
		}
	}

	public void clickNextButton() throws FindFailed, InterruptedException {
		//Match mStatus = screen.find("images/DeliveryManagement/next_button.png");
		//screen.click(mStatus.getCenter().offset(70, 0));
		//Thread.sleep(1000);
		screen.wait("images/DeliveryManagement/next_button.png", timeoutInSec);
		jDAFooter.PressEnter();
		Thread.sleep(5000);
	}
}
