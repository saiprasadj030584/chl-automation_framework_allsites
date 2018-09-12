package com.jda.wms.pages.Exit;

import java.util.ArrayList;
import java.util.Random;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class PickFaceMaintenancePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterLocation(String location) throws FindFailed {
		Match mLocation = screen.find("images/PickFaceMaintenance/LocationAdd.png");
		screen.click(mLocation.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type(Key.BACKSPACE);
		screen.type(location);
	}
	
	public void enterLocationInLocationMaint(String location) throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/PickFaceMaintenance/LocationId.png");
//		Match mLocation = screen.find("images/PickFaceMaintenance/Location12.png");
		screen.click(mLocation.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type(Key.BACKSPACE);
		screen.type(location);
		
	}
	public void enterLocationInPickFace(String location) throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/PickFaceMaintenance/loc.png");
		screen.click(mLocation.getCenter().offset(70, 0));
		screen.type(location);
		
	}

	public boolean isNoRecordDisplayed() throws FindFailed {
		if (screen.exists("images/PickFaceMaintenance/NoRecords.png") != null) {
			return true;
		} else {
			return false;
		}
	}

	public void enterSkuId(String skuId) throws FindFailed, InterruptedException {
		screen.wait("images/PickFaceMaintenance/SkuId.png", timeoutInSec);
		screen.click("images/PickFaceMaintenance/SkuId.png");
		Thread.sleep(2000);
		screen.type(skuId);
	}

	public void selectSiteId(String SiteId) throws FindFailed, InterruptedException {
		screen.wait("images/PickFaceMaintenance/SiteId.png", timeoutInSec);
		screen.click("images/PickFaceMaintenance/SiteId.png");
		Thread.sleep(2000);
		screen.type(SiteId);
	}

	public void selectFaceType(String faceType) throws FindFailed {
		screen.wait("images/PickFaceMaintenance/FaceType.png", timeoutInSec);
		screen.click("images/PickFaceMaintenance/FaceType.png");
		screen.type(faceType);
	}

	public String getFaceType() throws FindFailed {
		Match mDescription = screen.find("images/PickFaceMaintenance/FaceType.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getSkuId() throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mDescription = screen.find("images/PickFaceMaintenance/Sku.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getLocation() throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mDescription = screen.find("images/PickFaceMaintenance/LocationId.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getSiteId() throws FindFailed {
		Match mDescription = screen.find("images/PickFaceMaintenance/Site.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public boolean isTwoPickFaceRecordsErrorDisplayed() {
		if ((screen.exists("images/PickFaceMaintenance/TwoPickFaceError.png") != null))
			return true;
		else
			return false;
	}

	public void navigateToQuantitiesTab() throws FindFailed, InterruptedException {
		Thread.sleep(4000);
		screen.wait("images/PickFaceMaintenance/Quantities.png", timeoutInSec);
		screen.click("images/PickFaceMaintenance/Quantities.png");
	}

	public void checkReplenishWithoutOrder() throws FindFailed {
		screen.wait("images/PickFaceMaintenance/ReplenishWithoutQuantity.png", timeoutInSec);
		screen.click("images/PickFaceMaintenance/ReplenishWithoutQuantity.png");
	}

	public void enterTriggerQty(String triggerQty) throws FindFailed {
		System.out.println("qty"+triggerQty);
		screen.wait("images/PickFaceMaintenance/TriggerQty.png", timeoutInSec);
		screen.click("images/PickFaceMaintenance/TriggerQty.png");
		screen.type(triggerQty);
	}

	public void selectPrimaryPickFace() throws FindFailed, InterruptedException{
		
//		screen.find("images/PickFaceMaintenance/PickFaceChk.png").click();
		Thread.sleep(2000);
		screen.wait("images/PickFaceMaintenance/PickFaceChk.png", timeoutInSec);
		screen.click("images/PickFaceMaintenance/PickFaceChk.png");
	}
	
	public boolean isPrimaryPickFaceErrorDisplayed() throws FindFailed {
		if (screen.exists("images/PickFaceMaintenance/PrimaryPickFaceError.png") != null) {
			return true;
		} else {
			return false;
		}
	}
	public void enterLocationList(String[] LocationArray) throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/PickFaceMaintenance/LocationId.png");
		screen.click(mLocation.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type(Key.BACKSPACE);
		int rnd = new Random().nextInt(LocationArray.length);
		screen.type(LocationArray[rnd]);
		
	}
	public String getLocationList()throws FindFailed {
		Match mDescription = screen.find("images/PickFaceMaintenance/LocationId.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void navigateTouserDefinedTab() throws FindFailed {
		screen.wait("images/PickFaceMaintenance/UserDefinedTab.png", timeoutInSec);
		screen.click("images/PickFaceMaintenance/UserDefinedTab.png");
	}

	public void enterCaseRatio(String caseRatio) throws FindFailed {
		//---Case ratio changed to User defined type1 for dev006
//		screen.wait("images/PickFaceMaintenance/CaseRatio.png", timeoutInSec);
//		screen.click("images/PickFaceMaintenance/CaseRatio.png");
//		screen.type(caseRatio);
		screen.wait("images/PickFaceMaintenance/caseRatio1.png", timeoutInSec);
		screen.click("images/PickFaceMaintenance/caseRatio1.png");
		screen.type(caseRatio);
	
	}

	public void selectOwnerId()throws FindFailed {
		Match mDescription = screen.find("images/PickFaceMaintenance/ownerID.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("M+S");
	}
	public void EnterClientID()throws FindFailed, InterruptedException{
		Match mDescription = screen.find("images/PickFaceMaintenance/clientID.png");
		screen.click(mDescription.getCenter().offset(70, 0));		
		screen.type("M+S");
		screen.click(mDescription.getCenter().offset(70, 0));
		Thread.sleep(1000);
		}
	
	public void enterLocationInLocation12(String location) throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		
		Match mLocation = screen.find("images/PickFaceMaintenance/Location12.png");
		screen.click(mLocation.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type(Key.BACKSPACE);
		screen.type(location);
		
	}

	public void clickOk()throws FindFailed, InterruptedException {
		
		
	screen.find("images/PickFaceMaintenance/Ok.png");
		screen.click("images/PickFaceMaintenance/Ok.png");
		
		
	}
	}



