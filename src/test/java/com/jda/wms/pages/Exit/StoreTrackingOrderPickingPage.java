package com.jda.wms.pages.Exit;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class StoreTrackingOrderPickingPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void selectPickingMenu() throws InterruptedException {
		Thread.sleep(2000);
		screen.type("3");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void selectRelocate() throws InterruptedException {
		Thread.sleep(2000);
		screen.type("4");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void selectReceivingMenu() throws InterruptedException {
		Thread.sleep(2000);
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void selectPickingMenuForFurtherProcess() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("before enter 3");
		screen.type("3");
		System.out.println("after enter 3");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void selectsortation() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("before enter 2");
		screen.type("2");
		System.out.println("after enter 2");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}


	public boolean isPickMenuDisplayed() {
		if (screen.exists("images/Putty/Picking/PickMenu.png") != null)
			return true;
		else
			return false;
	}
	public boolean isRelocateDisplayed() {
		if (screen.exists("images/Putty/Relocation/RelMenu.png") != null)
			return true;
		else
			return false;
	}
	public boolean isReceivingMenuDisplayed() {
		if (screen.exists("images/Putty/Receiving/ReceiveMenu.png") != null)
			return true;
		else
			return false;
	}
	public boolean isRcvScnEANCMenuDisplayed() {
		if (screen.exists("images/Putty/Receiving/RcvScnEANC.png") != null)
			return true;
		else
			return false;
	}
	public boolean isRCVBLIMenuDisplayed() {
		if (screen.exists("images/RCVBLI.png") != null)
			return true;
		else
			return false;
	}
	
	public boolean isBasicReceivingMenuDisplayed() {
		if (screen.exists("images/Putty/Receiving/BasicReceiveMenu.png") != null)
			return true;
		else
			return false;
	}
	public boolean isUnpickDisplayed() {
		if (screen.exists("images/Putty/Picking/UnpickMenu.png") != null)
			return true;
		else
			return false;
	}
	public boolean isRelocateExistDisplayed() {
		if (screen.exists("images/Putty/Relocation/RelEnt1.png") != null)
			return true;
		else
			return false;
	}
	
	public boolean isImageforPckQtyZerExists() {
		if (screen.exists("images/Putty/Picking/PckQtyZer.png") != null)
			return true;
		else
			return false;
	}
	public void clientID() throws FindFailed{
		if(screen.find("images/Putty/Receiving/RcvScnEANC.png").equals(null)){
			System.out.println("Client ID Found");
		}
		else{
			System.out.println("Client ID skipped as expected");
		}
	}

	public void selectPickingInPickMenu() throws InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void selectSortingInPickMenu() throws InterruptedException {
		screen.type("2");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public boolean isRepackConfirm() {
		if (screen.exists("images/Putty/sortConfirm.png") != null)
			return true;
		else
			return false;
	}
	public boolean isPickTaskMenuDisplayed() {
		if (screen.exists("images/Putty/Picking/PickTaskMenu.png") != null)
			return true;
		else
			return false;
	}

	public void selectContainerPickMenu() throws InterruptedException {
		screen.type("3");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void selectBasicReceivingMenu() throws InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void selectUnpickMenu() throws InterruptedException {
		screen.type("5");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void selectExistingMenu() throws InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void selectGS1_128ReceiveMenu() throws InterruptedException {
		screen.type("3");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}


	public boolean isPickEntryDisplayed() throws InterruptedException {
		Thread.sleep(5000);
		if ((screen.exists("images/Putty/Picking/PickEntry.png") != null))
			return true;
		else
			return false;
	}

	public void enterTaskID(String orderId) throws InterruptedException {
		screen.type(orderId);
		Thread.sleep(1000);
	}
	
	public void enterListID(String listId) throws InterruptedException {
		screen.type(listId);
		Thread.sleep(1000);
	}
	
	public void enterURN(String URN) throws InterruptedException {
		screen.type(URN);
		Thread.sleep(1000);
	}
	
	public void enterBel(String Bel) throws InterruptedException {
		screen.type(Bel);
		Thread.sleep(1000);
	}

	public String getListIDDisplayed() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/ListID.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		screen.doubleClick(mStatus.getCenter().offset(40, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public String getSkuId() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/SKUId.png");
		screen.click(mStatus.below(14));
		Thread.sleep(2000);
		screen.doubleClick(mStatus.below(2));
		return App.getClipboard();
	}
	
	public String getLocation() throws FindFailed, InterruptedException {
		Match mLocation = screen.find("images/Putty/Picking/FromLocation.png");
		screen.click(mLocation.below(10));
		Thread.sleep(2000);
		screen.doubleClick(mLocation.below(1));
		return App.getClipboard();
	}
	public String getFromLocation() throws FindFailed, InterruptedException {
		Match mLocation = screen.find("images/Putty/Picking/FromLocation.png");
		screen.click(mLocation.getCenter().offset(40, 0));
		screen.doubleClick(mLocation.getCenter().offset(40, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public void enterSkuId(String sku) throws InterruptedException {
		System.out.println("sku inside"+sku);
		screen.type(sku);
		Thread.sleep(2000);
	}

	public String getTagId() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/TagId.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		screen.doubleClick(mStatus.getCenter().offset(40, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public void enterQuantity(String qtyToPick) throws InterruptedException {
		screen.type(qtyToPick);
		Thread.sleep(2000);
	}

	public String getQuantity() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/Quantity.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		screen.doubleClick(mStatus.getCenter().offset(40, 0));
		return App.getClipboard();
	}
	public String getQuantity1() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/quantity1.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		screen.doubleClick(mStatus.getCenter().offset(40, 0));
		return App.getClipboard();
	}

	public void enterManufactureDate(String manufactureDate) throws InterruptedException {
		screen.type(manufactureDate);
		Thread.sleep(2000);
	}

	public void enterExpiryDate(String expDate) throws InterruptedException {
		screen.type(expDate);
		Thread.sleep(2000);
	}

	public String getToPallet() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/ToPallet.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.doubleClick(mStatus.getCenter().offset(50, 0));
		return App.getClipboard();
	}

	public String getToLocation() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/ToLocation.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		screen.doubleClick(mStatus.getCenter().offset(40, 0));
		return App.getClipboard();
	}
	

	
	public String getDestination() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/FinalLocation.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		screen.doubleClick(mStatus.getCenter().offset(40, 0));
		return App.getClipboard();
	}

	public void enterDestination(String destination) throws InterruptedException {
		screen.type(destination);
		Thread.sleep(2000);
	}

	public boolean isContainerIDDisplayed() {
		if ((screen.exists("images/Putty/Picking/ContainerIDScreen.png") != null))
			return true;
		else
			return false;
	}

	public void enterContainerID(String containerId) throws InterruptedException {
		screen.type(containerId);
		Thread.sleep(2000);
	}

	public void enterCheckStrings(String chkStrings) throws InterruptedException {
		screen.type(chkStrings);
		Thread.sleep(2000);
	}

	public void selectReplenishPickMenu() throws InterruptedException {
		screen.type("2");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public String getReplenishQuantity() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/ReplenishQuantity.png");
		screen.click(mStatus.below(14));
		Thread.sleep(2000);
		screen.doubleClick(mStatus.below(4));
		return App.getClipboard();
	}

	public void enterReplenishQuantity(String quantity) throws InterruptedException {
		screen.type(quantity);
		Thread.sleep(1000);
	}

	public void enterTagId(String tagId) throws InterruptedException {
		screen.type(tagId);
		Thread.sleep(2000);
	}
	public void enterToLocation(String ToLocation) throws InterruptedException {
		screen.type(ToLocation);
		Thread.sleep(2000);
	}
	public String getLocationInReplenishPick() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/FromLocation.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		screen.doubleClick(mStatus.getCenter().offset(40, 0));
		return App.getClipboard();
	}
	
	public String getToLocationInReplenishPick() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/ReplenishToLocation.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		screen.doubleClick(mStatus.getCenter().offset(40, 0));
		return App.getClipboard();
	}
	
	public String getDestinationInReplenishpick() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/ReplenishDestination.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		screen.doubleClick(mStatus.getCenter().offset(40, 0));
		return App.getClipboard();
	}

	public boolean isPickQtyDownDisplayed() {
		if ((screen.exists("images/Putty/Picking/ShortPickDown.png") != null))
			return true;
		else
			return false;
	}

	public void enterShortPickReason() throws InterruptedException {
		screen.type("1");
		Thread.sleep(2000);
	}

	public boolean isNoPickTaskErrorDisplayed() {
		if ((screen.exists("images/Putty/Picking/NoValidPickTaskError.png") != null))
			return true;
		else
			return false;
	}
	public String getPickingFlow()throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/ReplenishDestination.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		return App.getClipboard();
	}
	public String getPickingFlowImage() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/PickingFlow.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		return App.getClipboard();
	}
	
	public boolean isInvalidLocationErrorDisplayed() {
		if ((screen.exists("images/Putty/Picking/InvalidLocation.png") != null))
			return true;
		else
			return false;
	}
	public boolean isInvalidErrorDisplayed() {
		if ((screen.exists("images/Putty/Picking/InvalidError.png") != null))
			return true;
		else
			return false;
	}
	}

