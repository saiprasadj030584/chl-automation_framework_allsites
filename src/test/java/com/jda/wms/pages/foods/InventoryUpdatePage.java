package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class InventoryUpdatePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void selectType(String selectType) throws FindFailed, InterruptedException {
		Match iselectType = screen.find("/images/InventoryUpdate/selectType.png");
		screen.click(iselectType.getCenter().offset(70, 0));
		screen.type(selectType);
		screen.type(Key.F7);
		Thread.sleep(4000);
	}

	public void enterTagId(String tagId) throws FindFailed, InterruptedException {
		Match itagId = screen.find("/images/InventoryUpdate/TagId.png");
		screen.click(itagId.getCenter().offset(70, 0));
		screen.type(tagId);
		screen.type(Key.F7);
		Thread.sleep(4000);
	}

	public Boolean isRecordExists() throws FindFailed, InterruptedException {
		if (screen.exists("/images/InventoryUpdate/1record.png") != null) {
			return true;
		} else
			return false;

	}

	public void clickNext() throws FindFailed, InterruptedException {

		screen.type(Key.F7);
		Thread.sleep(4000);
	}

	public void selectStatus(String status) throws FindFailed, InterruptedException {
		Match istatus = screen.find("/images/InventoryUpdate/LockStatusChange/status.png");
		screen.click(istatus.getCenter().offset(70, 0));
		screen.type(status);
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

	public void selectLockCode(String lockCode) throws FindFailed, InterruptedException {
		Match ilockCode = screen.find("/images/InventoryUpdate/LockStatusChange/lockCode.png");
		screen.click(ilockCode.getCenter().offset(70, 0));
		screen.type(lockCode);
		Thread.sleep(3000);
	}

	public void selectReasonCode(String reasonCode) throws FindFailed, InterruptedException {
		Match ireasonCode = screen.find("/images/InventoryUpdate/LockStatusChange/reasonCode.png");
		screen.click(ireasonCode.getCenter().offset(70, 0));
		screen.type(reasonCode);
		Thread.sleep(3000);
	}

}
