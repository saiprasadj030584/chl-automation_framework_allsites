package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class UPIManagementPage {

	Screen screen = new Screen();
	int timeoutInSec = 20;
	private Context context;

	@Inject
	public UPIManagementPage(Context context) {
		this.context = context;
	}

	public void searchWithAsn(String asn) throws FindFailed, InterruptedException {
		screen.click("images/UpiManagement/Add.png");
		Thread.sleep(1000);
		screen.type(Key.F7);
		Thread.sleep(2000);
		screen.type(Key.TAB);
		Thread.sleep(1000);
		screen.type(asn);
		Thread.sleep(1000);
		screen.type(Key.F7);
		Thread.sleep(1000);
	}

	public boolean isRecordsFound() throws FindFailed, InterruptedException {
		if (screen.exists("/images/UpiManagement/OnePalletFound.png") != null) {
			return true;
		} else
			return false;
	}

	public void doubleClickTheRecord() throws FindFailed, InterruptedException {
		screen.wait("images/UpiManagement/Pallet.png", timeoutInSec);
		Match mASN = screen.find("images/UpiManagement/Pallet.png");
		Thread.sleep(1000);
		screen.click(mASN.below(10));
		Thread.sleep(1000);
		screen.doubleClick(mASN.below(10));

	}

}
