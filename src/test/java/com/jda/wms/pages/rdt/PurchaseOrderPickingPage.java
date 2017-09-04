package com.jda.wms.pages.rdt;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.jda.wms.context.Context;

public class PurchaseOrderPickingPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private Context context;
	
	public PurchaseOrderPickingPage(Context context) {
		this.context = context;
	}

	public void selectPickingMenu() throws InterruptedException {
		screen.type("3");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectPickingMenu2() throws InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectContainerPick() throws InterruptedException {
		screen.type("3");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void enterListId(String ListID) throws InterruptedException {
		screen.type(ListID);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void enterPrinterNO(String string) throws InterruptedException {
		screen.type(string);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public String getPickingLocation() throws FindFailed, InterruptedException {
		Match mSupplierId = screen.find("images/Putty/Picking/ToLoctn.png");
		Thread.sleep(2000);
		screen.doubleClick((mSupplierId.below(10)));
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public void enterCheckString(String checkString) throws InterruptedException {
		screen.type(checkString);
		Thread.sleep(2000);
	}

	public boolean isPckEntPageDisplayed() {
		if (screen.exists("images/Putty/Picking/PckEnt.png") != null)
			return true;
		else
			return false;
	}

	public boolean isInvalidSkuDetailsDisplayed() throws InterruptedException {
		Thread.sleep(2000);
		if ((screen.exists("images/Putty/Picking/ErrorMessage.png") != null))
			return true;
		else
			return false;

	}

	public boolean isPartSetQtyDisplayed() {
		if (screen.exists("images/Putty/Picking/PartSetQty.png") != null)
			return true;
		else
			return false;
	}
	
	public boolean isPartSetInstructionDisplayed() {
		if (screen.exists("images/Putty/Picking/PartSetInstructions.png") != null)
			return true;
		else
			return false;
	}

	public boolean isMsgMenuDisplayed() {
		if (screen.exists("images/Putty/Picking/MsgMenu.png") != null)
			return true;
		else
			return false;
	}
	
	public void getQuantity() throws FindFailed, InterruptedException {
		Thread.sleep(2000);
		Match mStatus = screen.find("images/Putty/Picking/Qty.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		screen.doubleClick(mStatus.getCenter().offset(40, 0));
		Thread.sleep(2000);
		//context.setQtyReceivedFromPutty(Integer.parseInt(App.getClipboard()));
		screen.type(Key.ENTER);
		Thread.sleep(5000);
//		return App.getClipboard();
	}
	
	public void enterMinimumQty() throws InterruptedException {
//		screen.type(Key.ENTER);
//		Thread.sleep(3000);
//		screen.type(Key.TAB);
//		Thread.sleep(2000);
		screen.type("1");
		Thread.sleep(5000);
//		screen.type(Key.ENTER);
//		Thread.sleep(5000);
	} 
	
	public boolean isPickEnt() {
		if (screen.exists("images/Putty/Picking/PickEntry.png") != null)
			return true;
		else
			return false;
	}
	
	public void getTagId() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/GetTagId.png");
		screen.click(mStatus.getCenter().offset(40, 0));
		screen.doubleClick(mStatus.getCenter().offset(40, 0));
		Thread.sleep(5000);
		context.setTagId(App.getClipboard());
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}
	
	public void enterContainerId() throws FindFailed, InterruptedException {
		screen.wait("images/Putty/Picking/Cont.png", timeoutInSec);
		Match mQty = screen.find("images/Putty/Picking/Cont.png");
		Thread.sleep(5000);
		screen.rightClick(mQty.below(10));
		Thread.sleep(3000);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}
	
	public void selectReason() throws InterruptedException {
		screen.type("6");
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}
}
