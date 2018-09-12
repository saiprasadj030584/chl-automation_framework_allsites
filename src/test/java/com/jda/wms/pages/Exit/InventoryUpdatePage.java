package com.jda.wms.pages.Exit;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class InventoryUpdatePage {
	int timeoutInSec = 20;
	Region reg = new Region(0, 0, 4000, 1000);
	Screen screen = new Screen();
	private final JdaHomePage imageCheckFunction;

	@Inject
	public InventoryUpdatePage(JdaHomePage imageCheckFunction) {
		this.imageCheckFunction = imageCheckFunction;
	}

	public void selectType(String selectType) throws FindFailed, InterruptedException {
		imageCheckFunction.checkImageOnScreenAndClickTheGivenImageObjectOffset(
				"/images/InventoryUpdate/Start/SelectType.png", 0.5742477774620056f, 70);
		screen.type(selectType);
	}

	public void enterTagId(String tagId) throws FindFailed, InterruptedException {
		imageCheckFunction.checkImageOnScreenAndClickTheGivenImageObjectOffset(
				"/images/InventoryUpdate/Search/TagId.png", 0.46f, 70);
		screen.type(tagId);
		Thread.sleep(3000);
	}

	public Boolean isRecordExists() throws FindFailed, InterruptedException {
		if (screen.exists("/images/InventoryUpdate/Results/OneRecord.png") != null) {
			return true;
		} else
			return false;
	}

	public void selectStatus(String status) throws FindFailed, InterruptedException {
		imageCheckFunction.checkImageOnScreenAndClickTheGivenImageObjectOffset(
				"/images/InventoryUpdate/Finish/status.png", 0.97f, 70);
		screen.type(status);
		Thread.sleep(2000);
	}

	public void selectLockCode(String lockCode) throws FindFailed, InterruptedException {
		screen.type(Key.TAB);
		screen.type(lockCode);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void enterABV(String abv) throws FindFailed, InterruptedException {
		Match abvCheck = screen.find("images/InventoryUpdate/Finish/ABVCheck.png");
		screen.wait("images/InventoryUpdate/Finish/ABVCheck.png", timeoutInSec);
		screen.click(abvCheck.getCenter().offset(14, 0));
		Thread.sleep(3000);
		screen.type(abv);
	}

	public Boolean isHomePage() throws FindFailed, InterruptedException { 
		boolean status = imageCheckFunction.checkImageOnScreenForExist("/images/InventoryUpdate/Start/SelectType.png", 0.57f);
		return status;

	}

	public void enterExpiryDateUpdate(String expiryDateUpdate) throws FindFailed {
		screen.type(expiryDateUpdate);
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
		screen.type(reasonCode);
	}

	public void enterReasonCode(String reasonCode) throws FindFailed {
		Match status = screen.find("images/ReceiptReversal/Reversals/ReasonCode.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type(reasonCode);
		screen.type(Key.ENTER);
	} 
	

	public void enterLocation(String location) throws FindFailed {
		imageCheckFunction.checkImageOnScreenAndClickTheGivenImageObjectOffset(
				"images/InventoryUpdate/Search/Location.png", 0.91f, 70);
		screen.type(location);
	}

	public void selectPalletType(String pallettype) throws FindFailed {
		Match status = screen.find("images/ReceiptReversal/Reversals/PalletType.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type(pallettype);
	}

	public void selectPackConfigurationType(String PackConfig) throws FindFailed{
		

		Match status = screen.find("images/PickFaceMaintenance/PackConfig.png");

		screen.click(status.getCenter().offset(70, 0));
		screen.type(PackConfig);
	}
}
