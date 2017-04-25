package com.jda.wms.pages.foods;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class InventoryUpdatePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private final JDAFooter jDAFooter;

	@Inject
	public InventoryUpdatePage(JDAFooter jDAFooter) {
		this.jDAFooter = jDAFooter;
	}

	public void selectType(String selectType) throws FindFailed, InterruptedException {
		Match iselectType = screen.find("/images/InventoryUpdate/selectType.png");
		screen.click(iselectType.getCenter().offset(70, 0));
		screen.type(selectType);
		screen.type(Key.F7);
		Thread.sleep(4000);
	}

	public Boolean isRecordExists() throws FindFailed, InterruptedException {
		if (screen.exists("/images/InventoryUpdate/1record.png") != null) {
			return true;
		} else
			return false;
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
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}

//	public void selectReasonCode(String reasonCode) throws FindFailed, InterruptedException {
//		Match ireasonCode = screen.find("/images/InventoryUpdate/LockStatusChange/reasonCode.png");
//		screen.click(ireasonCode.getCenter().offset(70, 0));
//		screen.type(reasonCode);
//		screen.type(Key.ENTER);
//		Thread.sleep(3000);
//	}

	public Boolean verifyHomePage() throws FindFailed, InterruptedException {
		if (screen.exists("/images/InventoryUpdate/selectType.png") != null) {
			return true;
		} else
			return false;
	}

	public void enterABV(String newAbv) throws FindFailed, InterruptedException {
		screen.wait("images/InventoryUpdate/Finish/ABVCheck.png", timeoutInSec);
		screen.click("images/InventoryUpdate/Finish/ABVCheck.png");
		Thread.sleep(5000);
		screen.wait("images/InventoryUpdate/Finish/ABVCheck.png", timeoutInSec);
		screen.click("images/InventoryUpdate/Finish/ABVCheck.png");
		screen.type(newAbv);

	}

	public void enterExpiryDateUpdate(String expiryDateUpdate) throws FindFailed {
		screen.type(expiryDateUpdate);
	}

	public void enterTagId(String tagId) throws FindFailed {
		screen.type(tagId);
	}

	public String enterFutureDate() throws FindFailed {
		SimpleDateFormat formattedExpiryDate = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 364);
		String futureDateExpiry = formattedExpiryDate.format(cal.getTime());
		screen.type(futureDateExpiry);
		return futureDateExpiry;
	}

	public void selectReasonCode(String reasonCode) throws FindFailed {
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(reasonCode);
	}
}
