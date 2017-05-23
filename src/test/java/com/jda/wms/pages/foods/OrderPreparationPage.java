package com.jda.wms.pages.foods;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class OrderPreparationPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterGroupType() throws FindFailed {
		screen.wait("images/AddressMaintenance/AddressId.png", timeoutInSec);
		screen.click("images/AddressMaintenance/AddressId.png");
		screen.type("Consignment");
	}

	public void enterOrderId(String orderId) throws FindFailed {
		screen.wait("images/AddressMaintenance/AddressId.png", timeoutInSec);
		screen.click("images/AddressMaintenance/AddressId.png");
		screen.type(orderId);
	}

	public void selectRecord() throws InterruptedException, FindFailed {
		Match mQtyToReverse = screen.find("images/ReceiptReversal/Reversals/QtyToReverse.png");
		Thread.sleep(2000);
		screen.doubleClick(mQtyToReverse.below(10));
		Thread.sleep(3000);
	}

	public void selectTrailerType() throws FindFailed {
		screen.wait("images/AddressMaintenance/AddressId.png", timeoutInSec);
		screen.click("images/AddressMaintenance/AddressId.png");
		screen.type("TRAILER");
	}

	public boolean isRecordExist() throws FindFailed {
		if (!screen.find("images/ReceiptReversal/Reversals/Pallet.png").equals(null)) {
			return true;
		} else
			return false;
	}

	public void clickOk() throws FindFailed {
		screen.wait("images/AddressMaintenance/AddressId.png", timeoutInSec);
		screen.click("images/AddressMaintenance/AddressId.png");
	}

}
