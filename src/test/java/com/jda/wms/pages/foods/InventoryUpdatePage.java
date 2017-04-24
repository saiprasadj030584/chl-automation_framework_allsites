package com.jda.wms.pages.foods;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class InventoryUpdatePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public InventoryUpdatePage() {
	}

	public void selectType(String selectType) throws FindFailed, InterruptedException {
		Match mSelectType = screen.find("/images/InventoryUpdate/Start/SelectType.png");
		screen.click(mSelectType.getCenter().offset(70, 0));
		screen.type(selectType);
	}

	public void enterTagId(String tagId) throws FindFailed, InterruptedException {
		Match mtagId = screen.find("/images/InventoryUpdate/Search/TagId.png");
		screen.click(mtagId.getCenter().offset(70, 0));
		screen.type(tagId);
	}

	public Boolean isRecordExists() throws FindFailed, InterruptedException {
		if (screen.exists("/images/InventoryUpdate/Results/OneRecord.png") != null) {
			return true;
		} else
			return false;
	}

	public void selectStatus(String status) throws FindFailed, InterruptedException {
		Match mStatus = screen.find("/images/InventoryUpdate/Finish/status.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type(status);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectLockCode(String lockCode) throws FindFailed, InterruptedException {
		Match mLockCode = screen.find("/images/InventoryUpdate/Finish/lockCode.png");
		screen.click(mLockCode.getCenter().offset(70, 0));
		screen.type(lockCode);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectReasonCode(String reasonCode) throws FindFailed, InterruptedException {
		Match mReasonCode = screen.find("/images/InventoryUpdate/Finish/reasonCode.png");
		screen.click(mReasonCode.getCenter().offset(70, 0));
		screen.type(reasonCode);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public Boolean isHomePage() throws FindFailed, InterruptedException {
		if (screen.exists("/images/InventoryUpdate/Start/SelectType.png") != null) {
			return true;
		} else
			return false;
	}
}
