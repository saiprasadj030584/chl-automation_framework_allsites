package com.jda.wms.UI.pages;

import java.util.ArrayList;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
public class VehicleUnloadingPage {

	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdafooter;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public VehicleUnloadingPage(JdaHomePage jdaHomePage, JDAFooter jdafooter) {
		this.jdaHomePage = jdaHomePage;
		this.jdafooter = jdafooter;
	}

	public void enterSiteId() throws FindFailed, InterruptedException {
		screen.type("9771");
		screen.type(Key.TAB);
		screen.type(Key.TAB);
	}
	
//	public void enterConsignment(String consignment) throws FindFailed, InterruptedException {
//		screen.type(consignment);
//		screen.type(Key.TAB);
//	}
	
	
	
	
	public void selectPallet() throws FindFailed, InterruptedException {
		Match mSellectPallet = screen.find("images/VehicleUnloading/Finish/SelectPallet.png");
		Thread.sleep(2000);
		screen.doubleClick(mSellectPallet.below(10));
	}

	public void enterConsignment(String consignment) throws FindFailed, InterruptedException {
		screen.wait("images/VehicleUnloading/Consignment.png", timeoutInSec);
		screen.click("images/VehicleUnloading/Consignment.png");
		screen.type(consignment);
		Thread.sleep(2000);
	}

	public void enterLocation(String location) throws FindFailed, InterruptedException {
		screen.wait("images/VehicleUnloading/Location.png", timeoutInSec);
		screen.click("images/VehicleUnloading/Location.png");
		screen.type(location);
		Thread.sleep(2000);
	}

	public void enterTagId(String tagId) throws FindFailed, InterruptedException {
		screen.wait("images/VehicleUnloading/TagId.png", timeoutInSec);
		screen.click("images/VehicleUnloading/TagId.png");
		screen.type(tagId);
		Thread.sleep(2000);
	}

	public void selectRecord() throws FindFailed, InterruptedException {
		screen.wait("images/VehicleUnloading/consignmentRecord.png", timeoutInSec);
		Match mStatus = screen.find("images/VehicleUnloading/AvailableConsignmentRecord.png");
		Thread.sleep(2000);
		screen.click("images/VehicleUnloading/AvailableConsignmentRecord.png");
		screen.doubleClick(mStatus.below(15));
		Thread.sleep(2000);
//		Match mStatuscode = screen.find("images/DockScheduler/Build/FromSiteID1.png");
//		screen.doubleClick(mStatuscode.below(1));
	}

	public void clickAdd() throws FindFailed, InterruptedException {
		screen.wait("images/VehicleUnloading/Add1.png", timeoutInSec);
		Match mAdd= screen.find("images/VehicleUnloading/Add1.png");
		Thread.sleep(2000);
		screen.doubleClick(mAdd);
	}

	public void enterPallet(String pallet) throws FindFailed, InterruptedException {
		screen.wait("images/VehicleUnloading/Pallet.png", timeoutInSec);
		screen.click("images/VehicleUnloading/Pallet.png");
		screen.type(pallet);
		Thread.sleep(2000);
	}

	public void addTheRecord() {
		// TODO Auto-generated method stub
		
	}
	
}
