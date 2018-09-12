package com.jda.wms.pages.Exit;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class StockCheckListGenerationPage {

	Screen screen = new Screen();
	int timeoutInSec = 20;
	Pattern patternTagId = new Pattern();
	private JDAFooter jDAFooter;
	private JdaHomePage imageCheckFunction;

	@Inject
	public StockCheckListGenerationPage(JDAFooter jDAFooter, JdaHomePage imageCheckFunction) {
		this.jDAFooter = jDAFooter;
		this.imageCheckFunction = imageCheckFunction;
	}

	public void selectGenerateByLocation() throws FindFailed, InterruptedException {
		// screen.wait("images/StockCheckListGeneration/RadiobuttonGenerateByLocation.png",
		// timeoutInSec);
		// screen.click("images/StockCheckListGeneration/RadiobuttonGenerateByLocation.png");
		imageCheckFunction.checkImageOnScreenAndClickTheGivenImageObject(
				"images/StockCheckListGeneration/RadiobuttonGenerateByLocation.png",0.1f);
		jDAFooter.clickExecuteButton();
	}

	public void selectGenerateByInventory() throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		imageCheckFunction.checkImageOnScreenAndClickTheGivenImageObject(
				"images/StockCheckListGeneration/RadiobuttonGenerateByInventory.png",0.94f);
		jDAFooter.clickExecuteButton();
	}

	public void selectSiteId(String siteId) throws FindFailed, InterruptedException {
		// screen.wait("/images/StockCheckListGeneration/SiteId.png",
		// timeoutInSec);
		// screen.click("/images/StockCheckListGeneration/SiteId.png");
		imageCheckFunction.checkImageOnScreenAndClickTheGivenImageObject("/images/StockCheckListGeneration/SiteId.png",0.1f);
		screen.type(siteId);
		screen.type(Key.ENTER);
	}

	public void enterLocation(String fromLocation) throws FindFailed, InterruptedException {
		// screen.wait("/images/StockCheckListGeneration/FromLocation.png",
		// timeoutInSec);
		// screen.click("/images/StockCheckListGeneration/FromLocation.png");
		imageCheckFunction
				.checkImageOnScreenAndClickTheGivenImageObject("/images/StockCheckListGeneration/FromLocation.png",0.1f);
		screen.type(fromLocation);
	}

	public boolean isListAvailable() {

		Pattern patternImg = new Pattern("/images/StockCheckListGeneration/Add.png");
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

	public boolean isSelectedListAvailable() throws FindFailed {
		if (screen.exists("/images/StockCheckListGeneration/ButtonRemove.png") != null)
			return true;
		else
			return false;
	}

	public boolean isSelectedListconfirmation() {
		if (screen.exists("/images/StockCheckListGeneration/SelectedListConfirmationText.png") != null)
			return true;
		else
			return false;
	}

	public boolean isListIdPopupDisplayed() throws InterruptedException {
		if (screen.exists("/images/StockCheckListGeneration/CreatedListPopup.png") != null) {
			screen.type(Key.ENTER);
			return true;
		} else {
			return false;
		}
	}

	public String getSelectedListConfirmationText() throws FindFailed, InterruptedException {
		Match mConfirmationText = screen.find("/images/StockCheckListGeneration/SelectedListConfirmationText.png");
		screen.click(mConfirmationText.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(1000);
		return App.getClipboard();
	}

	public void clickAddButton() throws FindFailed {
		// screen.wait("/images/StockCheckListGeneration/Add.png",
		// timeoutInSec);
		// screen.click("/images/StockCheckListGeneration/Add.png");
		imageCheckFunction.checkImageOnScreenAndClickTheGivenImageObject("/images/StockCheckListGeneration/Add.png",0.1f);
	}

	public void navigateToSelectedTab() throws FindFailed {
		// screen.wait("/images/StockCheckListGeneration/SelectedTAB.png",
		// timeoutInSec);
		// screen.click("/images/StockCheckListGeneration/SelectedTAB.png");
		imageCheckFunction
				.checkImageOnScreenAndClickTheGivenImageObject("/images/StockCheckListGeneration/SelectedTAB.png",0.1f);
	}

	public void enterTagId(String tagId) throws FindFailed, InterruptedException {

		// ************************click and type
		imageCheckFunction
				.checkImageOnScreenAndClickTheGivenImageObject("/images/StockCheckListGeneration/EnterTagId.png",0.83f);
		screen.type(tagId);
		// *****************************
		//By priyanka for stock check
//		screen.find("/images/StockCheckListGeneration/EnterTagId.png");
//		screen.type(tagId);
		Thread.sleep(1000);
	}
	
	public void enterTag_Id(String tagId) throws FindFailed {

		// ************************click and type
		imageCheckFunction
				.checkImageOnScreenAndClickTheGivenImageObject("/images/StockCheckListGeneration/EnterTagId.png",0.83f);
		screen.type(tagId);
		// *****************************

	}

	public void enterListID(String listID) throws FindFailed, InterruptedException{
		screen.find("images/Putty/stockCheck/List.png");
		screen.type(listID);
		Thread.sleep(1000);
		
	}

	public void enterworkZone(String Wzone) 
		throws FindFailed, InterruptedException{
			screen.find("images/Putty/stockCheck/Wzone.png");
			screen.type(Wzone);
			Thread.sleep(1000);
			
		
	}

	
		public void nextTab() throws InterruptedException {
			Thread.sleep(2000);
			screen.type(Key.TAB);
		}

		public void PressEnter() throws InterruptedException {
			screen.type(Key.ENTER);
			Thread.sleep(1000); 
			
		}

		public void enterCheckString(String chkString) throws InterruptedException {
		Thread.sleep(3000);
		screen.type(chkString);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}
		
		public void enterLocationInPutty(String fromLocation) throws FindFailed, InterruptedException {
//			screen.find("images/Putty/stockCheck/Locn.png");
			screen.type(fromLocation);
		}
		
		public void enterTag_IdInPutty(String tagId) throws FindFailed, InterruptedException {
			
//			Match mTagId = screen.find("images/Putty/stockCheck/TagId.png");
//			screen.doubleClick(mTagId.getCenter().offset(70, 0));
			Thread.sleep(1000);
			screen.type(tagId);
			
		}
		public void enterQty(String qty) throws FindFailed {
			screen.type(qty);
		}

		public void entersku(String sku) {
			screen.type(sku);
		}
		
		public void selectOwnerId()throws FindFailed {
			Match mDescription = screen.find("images/PickFaceMaintenance/ownerID.png");
			screen.click(mDescription.getCenter().offset(70, 0));
			screen.type("M+S");
		}
}


