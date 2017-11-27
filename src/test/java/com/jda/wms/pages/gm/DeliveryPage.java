package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class DeliveryPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterAsnId(String asnId) throws FindFailed, InterruptedException {
		screen.type(asnId);
		Thread.sleep(1000);
	}

	public boolean isNoRecordFound() {
		if (screen.exists("images/DuplicateOption/NoRecords.png") != null)
			return true;
		else
			return false;
	}

	public boolean deliveryHomePage() {
		if (screen.exists("images/DeliveryPage.png") != null)
			return true;
		else
			return false;
	}

	public boolean isEJBerrorfound() {
		if (screen.exists("images/EJBError.png") != null)
			return true;
		else
			return false;
	}

	public boolean warningPopUpDuplicateMsg() {
		if (screen.exists("images/Warning_Popup_DuplicateMsg.png") != null)
			return true;
		else
			return false;
	}
}
