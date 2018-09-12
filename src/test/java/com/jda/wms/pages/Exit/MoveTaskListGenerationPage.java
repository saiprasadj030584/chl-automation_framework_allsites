package com.jda.wms.pages.Exit;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class MoveTaskListGenerationPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private JDAFooter jDAFooter;
	private JdaHomePage imageCheckFunction;
	
	@Inject
	public MoveTaskListGenerationPage(JDAFooter jDAFooter, JdaHomePage imageCheckFunction) {
		this.jDAFooter = jDAFooter;
		this.imageCheckFunction = imageCheckFunction;
	}

	public void enterTagID(String tagId) throws FindFailed, InterruptedException {
		/*screen.wait("images/MoveTaskListGeneration/TagID.png", timeoutInSec);
		screen.click("images/MoveTaskListGeneration/TagID.png");
		screen.type(tagId);
		Thread.sleep(1000);*/
		
		Match mConfirmationText = screen.find("images/MoveTaskListGeneration/TagID.png");
		screen.click(mConfirmationText.getCenter().offset(70, 0));
		screen.type("");
		screen.type(tagId);
		Thread.sleep(1000);
	}

	public void enterSkuID(String skuId) throws FindFailed, InterruptedException {
		/*screen.wait("images/MoveTaskListGeneration/SkuID.png", timeoutInSec);
		screen.click("images/MoveTaskListGeneration/SkuID.png");
		screen.type(skuId);
		Thread.sleep(1000);*/
		Match mConfirmationText = screen.find("images/MoveTaskListGeneration/SkuID.png");
		screen.click(mConfirmationText.getCenter().offset(70, 0));
		screen.type("");
		screen.type(skuId);
		Thread.sleep(1000);
	}

	public void clickAddButton() throws FindFailed {
		screen.wait("images/MoveTaskListGeneration/Add.png", timeoutInSec);
		screen.click("images/MoveTaskListGeneration/Add.png");

	}
	
	public void clickAddReplenishButton() throws FindFailed {
		screen.wait("images/MoveTaskListGeneration/Add.png", timeoutInSec);
		screen.click("images/MoveTaskListGeneration/Add.png");

	}


	public void navigateToSelectedTab() throws FindFailed, InterruptedException {
		screen.wait("images/MoveTaskListGeneration/SelectedTAB.png", timeoutInSec);
		screen.click("images/MoveTaskListGeneration/SelectedTAB.png");
		Thread.sleep(2000);
	}

	public boolean isSelectedListAvailable() {
		if (screen.exists("images/MoveTaskListGeneration/ButtonRemove.png") != null)
			return true;
		else
			return false;
	}

	public String getSelectedListConfirmationText() throws FindFailed, InterruptedException {
		Match mConfirmationText = screen.find("images/MoveTaskListGeneration/SelectedListConfirmationText.png");
		//screen.find("images/MoveTaskListGeneration/SelectedListConfirmationText.png");
		screen.click(mConfirmationText.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(1000);
		return App.getClipboard();
	}

	public boolean isListIdPopupDisplayed() {
		if (screen.exists("images/MoveTaskListGeneration/CreatedListPopup.png") != null) {
			screen.type(Key.ENTER);
			return true;
		} else {
			return false;
		}

	}
	public void closeAllScreens() throws FindFailed, InterruptedException{
		screen.rightClick("/images/JDAHome/Welcome.png", 25);
		Thread.sleep(4000);
		screen.click("/images/JDAHome/CloseAll.png", 25);
		Thread.sleep(4000);
	}
	public void selectCreate() throws FindFailed, InterruptedException {
		// screen.wait("images/StockCheckListGeneration/RadiobuttonGenerateByLocation.png",
		// timeoutInSec);
		// screen.click("images/StockCheckListGeneration/RadiobuttonGenerateByLocation.png");
		imageCheckFunction.checkImageOnScreenAndClickTheGivenImageObject(
				"images/MoveTaskListGeneration/Create.png",0.1f);
		jDAFooter.clickExecuteButton();
	}
	public boolean isListAvailable() {

		Pattern patternImg = new Pattern("/images/MoveTaskListGeneration/Add.png");
		patternImg.similar((float) 0.5);

		if (screen.exists(patternImg) != null)
		{
			System.out.println("Add button found");
			return true;
		}
		else
		{
			System.out.println("Add button not found");
			return false;
		
		}
	}

	public void selectDisplay() throws FindFailed, InterruptedException {
		// screen.wait("images/StockCheckListGeneration/RadiobuttonGenerateByLocation.png",
		// timeoutInSec);
		// screen.click("images/StockCheckListGeneration/RadiobuttonGenerateByLocation.png");
		imageCheckFunction.checkImageOnScreenAndClickTheGivenImageObject(
				"images/MoveTaskListGeneration/Display.png",0.1f);
		
	}

	public void enterTagIdInMoveTaskUpdate(String tagId) throws FindFailed, InterruptedException {
		Match mTagId = screen.find("images/MoveTaskUpdate/TagIdInMoveTask.png");
		screen.click(mTagId.getCenter().offset(70, 0));
		screen.type(tagId);
		Thread.sleep(1000);
	}
	
	public void enterTaskIdInMoveTaskUpdate(String orderID) throws FindFailed, InterruptedException {
		Match mTagId = screen.find("images/MoveTaskUpdate/taskid.png");
		screen.click(mTagId.getCenter().offset(70, 0));
		screen.type(orderID);
		Thread.sleep(1000);
	}

	public void getLatestTask() throws FindFailed, InterruptedException {

		Match mLocation = screen.find("images/MoveTaskQuery/TaskForSelection.png");
		screen.doubleClick(mLocation.getCenter().below(15));
		Thread.sleep(3000);
	}

	
}

