package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class DeliveryManagementPage {
	private JDAFooter jDAFooter;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public DeliveryManagementPage(JDAFooter jDAFooter) {
		this.jDAFooter = jDAFooter;
	}

	public void enterAsnId(String asnId) throws InterruptedException, FindFailed {
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
		if (screen.exists("images/DeliveryManagement/UpdateStatus.png") != null) {
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
		if (screen.exists("images/DeliveryManagement/WarningPopUp.png") != null) {
			jDAFooter.PressEnter();
		}
	}

	public void clickStart() throws InterruptedException, FindFailed {
		screen.wait("images/DeliveryManagement/Start.png", timeoutInSec);
		screen.click("images/DeliveryManagement/Start.png");
		Thread.sleep(2000);
	}

}
