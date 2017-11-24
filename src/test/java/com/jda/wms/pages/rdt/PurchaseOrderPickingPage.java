package com.jda.wms.pages.rdt;

import java.util.Random;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.jda.wms.context.Context;



public class PurchaseOrderPickingPage {
	Random random = new Random();
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private Context context;

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
	public void enterPicToTagId(String tagId) throws InterruptedException {
		screen.type(tagId);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		 
		}
	public boolean checktagidavailable() throws FindFailed, InterruptedException {
		try {
			System.out.println("entered check stock ");
		if (screen.exists("images/Putty/Picking/scantagid.png", 10) != null){
			Thread.sleep(1000);
			screen.type(Key.ENTER);
			return true;}
		else
			
			return false;
		}catch(Exception e){e.printStackTrace();return false;
		}
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

	public boolean palletIdAvailable() 
	{
		if (screen.exists("images/Putty/Picking/Enterpall.png", 10) != null)
			return true;
		else
			return false;
	}
	public boolean invalidContainerIdAvailable() 
	{
		if (screen.exists("images/Putty/Picking/InvalidContainer.png", 10) != null)
			return true;
		else
			return false;
	}
	public boolean validContainerIdAvailable()
	{
		if (screen.exists("images/Putty/Picking/Scancontainerid.png", 10) != null)
			return true;
		else
			return false;
	}
	public void enter14digitpalletId() throws InterruptedException{
		Thread.sleep(1000);
		
		
		int x = random.nextInt(900) + 100;
		String palletid = "1"+context.getOrderId()+x;
		System.out.println("palletid: "+palletid);
		screen.type(palletid);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}
	public void noOfTag() throws InterruptedException{
		Thread.sleep(1000);
			
		
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}
	public void entertag() throws InterruptedException{
		Thread.sleep(1000);
		int x = random.nextInt(900000) + 100000;
		String container = String.valueOf(x);
		System.out.println("containerid: "+container);
		screen.type(container);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}
	
	public int generate5digitcontainerID() throws InterruptedException{
		Thread.sleep(1000);
		int x = random.nextInt(90000) + 10000;
		
		System.out.println("random no generated: "+x);
		return x;
		}
	public void enterContainerID(String containerId) throws InterruptedException{
		
		screen.type(containerId);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}
	public void enterskuid(String skuid) throws InterruptedException{
		Thread.sleep(1000);
		screen.type(skuid);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
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
		// context.setQtyReceivedFromPutty(Integer.parseInt(App.getClipboard()));
		screen.type(Key.ENTER);
		Thread.sleep(5000);
		// return App.getClipboard();
	}

	public void entrMinimumQty() throws InterruptedException {
		// screen.type(Key.ENTER);
		// Thread.sleep(3000);
		// screen.type(Key.TAB);
		// Thread.sleep(2000);
		screen.type("1");
		Thread.sleep(5000);
		// screen.type(Key.ENTER);
		// Thread.sleep(5000);
	}
	public void enterMinimumQty(String qty) throws InterruptedException {
		// screen.type(Key.ENTER);
		// Thread.sleep(3000);
		// screen.type(Key.TAB);
		// Thread.sleep(2000);
		screen.type(qty);
		Thread.sleep(5000);
		// screen.type(Key.ENTER);
		// Thread.sleep(5000);
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

	public void enterContainerId(String containerId) throws FindFailed, InterruptedException {
		// screen.wait("images/Putty/Picking/EnterTag.png", timeoutInSec);
		// Match mQty = screen.find("images/Putty/Picking/EnterTag.png");
		// Thread.sleep(3000);
		// screen.click(mQty.below(10));
		Thread.sleep(1000);
		screen.type(containerId);
		Thread.sleep(3000);
		screen.type(Key.ENTER);
		// Thread.sleep(3000);

	}

	public boolean isPickCmpPageDisplayed() {
		if (screen.exists("images/Putty/Picking/pickcmp.png") != null)
			return true;
		else
			return false;
	}
	
	public boolean isPickEntPageDisplayed() {
		if (screen.exists("images/Putty/Picking/PickEntry.png") != null)
			return true;
		else
			return false;
	}

	public void enterTagId(String tagId) throws FindFailed, InterruptedException {
		// TODO take new image for EnTTagid
		// screen.wait("images/Putty/Picking/EnterTag.png", timeoutInSec);
		// Match mQty = screen.find("images/Putty/Picking/EnterTag.png");
		Thread.sleep(3000);
		// screen.click(mQty.below(10));
		// Thread.sleep(1000);
		System.out.println(tagId);
		screen.type(tagId);
		Thread.sleep(3000);

	}


}
