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
		if(screen.exists("images/DuplicateOption/NoRecords.png")!= null)
			return true;
			else
				return false;
	}


	public boolean isEJBerrorfound() {
		if(screen.exists("images/DuplicateOption/ejberror.png")!= null)
			return true;
			else
				return false;
	}
}
