package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class MoveTaskListGenerationPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterTagID(String tagId) throws FindFailed, InterruptedException {
		screen.wait("images/MoveTaskListGeneration/TagID.png", timeoutInSec);
		screen.click("images/MoveTaskListGeneration/TagID.png");
		screen.type(tagId);
		Thread.sleep(1000);
	}

	public void enterskuID(String skuId) throws FindFailed, InterruptedException {
		screen.wait("images/MoveTaskListGeneration/SkuID.png", timeoutInSec);
		screen.click("images/MoveTaskListGeneration/SkuID.png");
		screen.type(skuId);
		Thread.sleep(1000);
	}

	public void clickAddButton() throws FindFailed {
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
}
