package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class StockCheckListGenerationPage {

	Screen screen = new Screen();
	int timeoutInSec = 20;
	private JDAFooter jDAFooter;

	@Inject
	public StockCheckListGenerationPage(JDAFooter jDAFooter) {
		this.jDAFooter = jDAFooter;
	}

	public void navigateToStockCheckListGeneration() throws FindFailed, InterruptedException {
		clickOperations();
		hoverInventory();
		hoverStockCheck();
		clickStockCheckListGeneration();
	}

	public void clickOperations() throws FindFailed {
		screen.wait("images/JDAHome/Operations.png", timeoutInSec);
		screen.click("images/JDAHome/Operations.png");
	}

	public void hoverInventory() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/Inventory.png", timeoutInSec);
		screen.click("images/JDAHome/Inventory.png");
		// Thread.sleep(1000);
	}

	public void hoverStockCheck() throws FindFailed, InterruptedException {
		screen.mouseMove(150, 0);
		screen.wait("images/JDAHome/StockCheck.png", timeoutInSec);
		screen.click("images/JDAHome/StockCheck.png");
		// Thread.sleep(1000);
	}

	public void clickStockCheckListGeneration() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/StockCheckListGeneration.png", timeoutInSec);
		screen.click("images/JDAHome/StockCheckListGeneration.png");
		Thread.sleep(1000);
	}

	public void selectGenerateByLocation() throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		screen.wait("images/StockCheckListGeneration/RadiobuttonGenerateByLocation.png", timeoutInSec);
		screen.click("images/StockCheckListGeneration/RadiobuttonGenerateByLocation.png");
		jDAFooter.clickExecuteButton();
	}

	public void selectGenerateByInventory() throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		screen.wait("images/StockCheckListGeneration/RadiobuttonGenerateByInventory.png", timeoutInSec);
		screen.click("images/StockCheckListGeneration/RadiobuttonGenerateByInventory.png");
		jDAFooter.clickExecuteButton();
	}

	public void setSiteIDandLocation(String siteId, String fromLocation) throws FindFailed, InterruptedException {
		screen.wait("/images/StockCheckListGeneration/SiteId.png", timeoutInSec);
		screen.click("/images/StockCheckListGeneration/SiteId.png");
		screen.type(siteId);
		screen.type(Key.ENTER);
		screen.wait("/images/StockCheckListGeneration/FromLocation.png", timeoutInSec);
		screen.click("/images/StockCheckListGeneration/FromLocation.png");
		screen.type(fromLocation);
	}

	public boolean isListAvailable() {

		if (screen.exists("/images/StockCheckListGeneration/Add.png") != null)
			return true;
		else
			return false;

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
	
	public void clickDoneButton() {
		screen.type(Key.F12);
	}
	
	public boolean isListIdPopupDisplayed(){
		if (screen.exists("/images/StockCheckListGeneration/CreatedListPopup.png") != null)
			return true;
		else
			return false;
	}

	public String getSelectedListConfirmationText() throws FindFailed, InterruptedException {
		Match mConfirmationText = screen.find("/images/StockCheckListGeneration/SelectedListConfirmationText.png");
		screen.click(mConfirmationText.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(1000);
		//mConfirmationText.drag("/images/StockCheckListGeneration/SelectedListConfirmationText.png");
		return App.getClipboard();
	}

	public void clickAddButton() throws FindFailed {
		screen.wait("/images/StockCheckListGeneration/Add.png", timeoutInSec);
		screen.click("/images/StockCheckListGeneration/Add.png");

	}

	public void navigateToSelectedTab() throws FindFailed {
		screen.wait("/images/StockCheckListGeneration/SelectedTAB.png", timeoutInSec);
		screen.click("/images/StockCheckListGeneration/SelectedTAB.png");
	}

	public void enterTagId(String tagId) throws FindFailed {
		screen.wait("/images/StockCheckListGeneration/EnterTagId.png", timeoutInSec);
		screen.click("/images/StockCheckListGeneration/EnterTagId.png");
		screen.type(tagId);
		
	}
	


}
