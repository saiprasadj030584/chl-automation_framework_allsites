package com.jda.wms.pages.Exit;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class SchedulerSchedulesPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	@Inject

	public void calenderPreview() {
		// TODO Auto-generated method stub
		
	}
	public void clickQuery() throws FindFailed, InterruptedException {
		screen.type(Key.F2);
		Thread.sleep(3000);
	}
	public void clickExcecute() throws FindFailed, InterruptedException {
		screen.type(Key.F7);
		Thread.sleep(3000);
	}
	public void enterSchedulerName(String name) throws FindFailed, InterruptedException {
		Thread.sleep(2000);
screen.wait("images/SKUMaintenanceTable/schedulerName.png", timeoutInSec);
Match mJob= screen.find("images/SKUMaintenanceTable/schedulerName.png");
screen.click(mJob.getCenter().offset(70, 0));
screen.type(name);
}
}
