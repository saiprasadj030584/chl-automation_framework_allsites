package com.jda.wms.pages.Exit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.jda.wms.context.Context;

//import com.jda.wms.stepdefs.foods;

import cucumber.api.java.en.When;


public class MoveTaskQueryPage {
	private Context context;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterTaskId(String taskId) throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/MoveTaskQuery/task.png");
		screen.click(mLocation.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type(Key.BACKSPACE);
		screen.type(taskId);

	}
	public void enterPalletId(String Pallet) throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/MoveTaskQuery/pallet.png");
		screen.click(mLocation.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type(Key.BACKSPACE);
		screen.type(Pallet);
	}

	public void selectReplenishMenu() throws FindFailed, InterruptedException {
		screen.type("3");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void selectPickingMenu() throws FindFailed, InterruptedException {
		screen.type("3");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void selectReplenishMenuForTaskId() throws FindFailed, InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectSortingMenu() throws FindFailed, InterruptedException {
		screen.type("2");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void selectReplenishMenuForTaskIdreplenish() throws FindFailed, InterruptedException {
		screen.type("2");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public boolean isnoValidMoveTaskFound() {
		if ((screen.exists("images/Putty/Picking/NoValidPickTaskFound.png") != null))
			return true;
		else
			return false;
	}
	public boolean isinvalidTagId() {
		if ((screen.exists("images/Putty/Picking/InvalidTag.png") != null))
			return true;
		else
			return false;
	}
	public boolean isconsolidationAllowed() {
		if ((screen.exists("images/Putty/Picking/Consolidation.png") != null))
			return true;
		else
			return false;
	}
	public boolean isPalletAutoPopulated() {
		if ((screen.exists("images/Putty/Picking/ToPalletAuto.png") != null))
			return true;
		else
			return false;
	}
	public boolean iscombinationError() {
		if ((screen.exists("images/Putty/Picking/CombinationError.png") != null))
			return true;
		else
			return false;
	}
	public boolean isnowCompletingTaskAvailable() {
		if ((screen.exists("images/Putty/Picking/NowCompletingTask.png") != null))
			return true;
		else
			return false;
	}

	public void enterList(String ListId) throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/Putty/Picking/List.png");
		screen.click(mLocation.getCenter().offset(70, 0));
	
		screen.type(ListId);

	}
	public void enterPallet(String taskId) throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/Putty/Picking/PalletId.png");
		screen.click(mLocation.getCenter().offset(70, 0));
		screen.type(taskId);

	}
	public void enterFrPl(String taskId) throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/Putty/Picking/FrP.png");
		screen.click(mLocation.getCenter().offset(70, 0));
	
		screen.type(taskId);

	}
	public void enterContainerID(String taskId) throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/Putty/Picking/FromContainer.png");
		screen.click(mLocation.getCenter().offset(70, 0));
	
		screen.type(taskId);

	}
	public void enterFromContainerID(String taskId) throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/Putty/Picking/FrContainer.png");
		screen.click(mLocation.getCenter().offset(70, 0));
	
		screen.type(taskId);

	}
	public void selectAnyOption() throws FindFailed, InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void NotEnough() throws FindFailed, InterruptedException {
		screen.type("2");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void DamagedStock() throws FindFailed, InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void SplitPick() throws FindFailed, InterruptedException {
		screen.type("4");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
	public void nextTab() throws InterruptedException {
		Thread.sleep(2000);
		screen.type(Key.TAB);
	}

	public void PressEnter() throws InterruptedException {
		screen.type(Key.ENTER);
		Thread.sleep(1000);
	}

	public void enterInvalidTagId(String tag) throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/Putty/Picking/Tag.png");
		screen.click(mLocation.getCenter().offset(70, 0));
		screen.type(tag);
	}

	public String getQuantity() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/Quantity.png");
		screen.click(mStatus.getCenter().offset(30, 0));
		screen.doubleClick(mStatus.getCenter().offset(30, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}
	
	public void enterQuantity(String FinalQuantity) throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/Putty/Picking/quantity1.png");
		screen.click(mLocation.getCenter().offset(70, 0));
		screen.type(FinalQuantity);
		
	}
	

	public void PressF11() throws InterruptedException {
		screen.type(Key.F11);
		Thread.sleep(2000);
	}

	public void PressF10() throws InterruptedException {
		screen.type(Key.F10);
		Thread.sleep(2000);
	}
	public void enterTag(String tag)throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/Putty/Picking/Tag.png");
		screen.click(mLocation.getCenter().offset(70, 0));
	
		screen.type(tag);

	}
	public void enterYforPallet()throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/Putty/Picking/newPalletY.png");
		screen.click(mLocation.getCenter().offset(70, 0));
	
		screen.type("Y");

	}
	public void enterNforCombine()throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/Putty/Picking/Combine.png");
		screen.click(mLocation.getCenter().offset(70, 0));
	
		screen.type("N");

	}
	public void enterYforCombine()throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/Putty/Picking/Combine.png");
		screen.click(mLocation.getCenter().offset(70, 0));
	
		screen.type("Y");

	}
	public void enterPrinter1()throws FindFailed, InterruptedException {
		
		Thread.sleep(3000);
		Match mLocation = screen.find("images/Putty/Picking/Printer1.png");
		screen.click(mLocation.getCenter().offset(70, 0));
	
		screen.type("DUMMY");

	}
public void enterPrinter1ForConsolidation()throws FindFailed, InterruptedException {
		
		Thread.sleep(3000);
		Match mLocation = screen.find("images/Putty/Picking/printer1ForConsolidation.png");
		screen.click(mLocation.getCenter().below(10));
	
		screen.type("Printer1");

	}

	public String getLocation() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Putaway/ToLocation.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.doubleClick(mStatus.getCenter().offset(50, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}
	public String getPallet() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Picking/ToPalletAuto.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.doubleClick(mStatus.getCenter().offset(10, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}
	public void enterCheckString(String chkString) throws InterruptedException {
		Thread.sleep(3000);
		screen.type(chkString);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}
	public void enterLocation(String location) throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/Putty/Picking/ToLocation.png");
		screen.click(mLocation.getCenter().offset(70, 0));
	
		screen.type(location);

	}
		public void selectTaskID() throws FindFailed, InterruptedException {

			Match mLocation = screen.find("images/MoveTaskQuery/TaskForSelection.png");
			screen.doubleClick(mLocation.getCenter().below(15));
			Thread.sleep(3000);
		}
//		public String getStatus() throws FindFailed {
//			Match mDescription = screen.find("images/MoveTaskQuery/Status.png");
//			screen.click(mDescription.getCenter().offset(70, 0));
//			screen.type("a", Key.CTRL);
//			screen.type("c", Key.CTRL);
//			return App.getClipboard();			
//		}
		public void navigateToMiscellaneousTab() throws FindFailed, InterruptedException {
			screen.wait("images/MoveTaskQuery/MiscellaneousTab.png", timeoutInSec);
			screen.click("images/MoveTaskQuery/MiscellaneousTab.png");
			Thread.sleep(2000);
		}
		public void clickGeneralTab() throws FindFailed, InterruptedException {
			screen.wait("images/InventoryQuery/GeneralTab.png", timeoutInSec);
			screen.click("images/InventoryQuery/GeneralTab.png");
			Thread.sleep(1000);
		}
		public String getTaskId() throws FindFailed {
			Match mDescription = screen.find("images/MoveTaskQuery/TaskNew.png");
			screen.click(mDescription.getCenter().offset(70, 0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);
			return App.getClipboard();
		}
		public String getcontainer() throws FindFailed {
			Match mDescription = screen.find("images/MoveTaskQuery/contai.png");
			screen.click(mDescription.getCenter().offset(70, 0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);
			return App.getClipboard();
		}
		public String getLocation1() throws FindFailed {
			Match mDescription = screen.find("images/MoveTaskQuery/Location.png");
			screen.click(mDescription.getCenter().offset(70, 0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);
			return App.getClipboard();
		}
		public String getList() throws FindFailed {
			Match mDescription = screen.find("images/MoveTaskQuery/List.png");
			screen.click(mDescription.getCenter().offset(70, 0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);
			return App.getClipboard();
		}
		public void enterTaskId() throws FindFailed, InterruptedException {
			Thread.sleep(3000);
			Match mLocation = screen.find("images/MoveTaskQuery/task.png");
			screen.click(mLocation.getCenter().offset(70, 0));			
			screen.type("REPLENISH");

		}
		public void enterDate() throws FindFailed, InterruptedException {
			Thread.sleep(3000);
			Match mLocation = screen.find("images/MoveTaskQuery/MoveDate.png");
			screen.click(mLocation.getCenter().offset(70, 0));			
			screen.type("0");
			screen.type(Key.ENTER);

		}
		public String getDate()throws FindFailed {
			Match mDescription = screen.find("images/MoveTaskQuery/MoveDate.png");
			screen.click(mDescription.getCenter().offset(70, 0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);
			return App.getClipboard();
		}
		public void getReplenishTask() throws FindFailed, InterruptedException {

			Match mLocation = screen.find("images/MoveTaskQuery/TaskForSelection.png");
			screen.doubleClick(mLocation.getCenter().below(15));
			Thread.sleep(3000);
		}
		public String getTime() throws FindFailed {
			Match mDescription = screen.find("images/MoveTaskQuery/Time.png");
			screen.click(mDescription.getCenter().offset(70, 0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);
			return App.getClipboard();
		}
		public String getTagIdForReplenishTask()throws FindFailed {
			Match mDescription = screen.find("images/MoveTaskQuery/TagNotNull.png");
			screen.click(mDescription.getCenter().offset(70, 0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);
			return App.getClipboard();
		}
		public void enterTime(String pickFaceTime ) throws FindFailed, InterruptedException {
			Thread.sleep(3000);
			Match mLocation = screen.find("images/MoveTaskQuery/Time.png");
			screen.click(mLocation.getCenter().offset(70, 0));	
			screen.type(">"+pickFaceTime);
			screen.type(Key.ENTER);
		}
		public void enterTagId(String tag) throws FindFailed, InterruptedException {
			Thread.sleep(3000);
			Match mLocation = screen.find("images/Putty/Picking/Tag.png");
			screen.click(mLocation.getCenter().offset(70, 0));
			screen.type(tag);
		}
		
	}

