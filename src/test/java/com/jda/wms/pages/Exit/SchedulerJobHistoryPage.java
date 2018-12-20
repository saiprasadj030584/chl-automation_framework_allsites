package com.jda.wms.pages.Exit;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class SchedulerJobHistoryPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	@Inject

	public void enterSchedulerJob(String job) throws FindFailed, InterruptedException {
					Thread.sleep(2000);
			screen.wait("images/SKUMaintenanceTable/job.png", timeoutInSec);
			Match mJob= screen.find("images/SKUMaintenanceTable/job.png");
			screen.click(mJob.getCenter().offset(70, 0));
			screen.type(job);
		}
	public String getStatus() throws FindFailed, InterruptedException {
		Thread.sleep(2000);
screen.wait("images/SKUMaintenanceTable/status.png", timeoutInSec);
Match mJob= screen.find("images/SKUMaintenanceTable/status.png");
screen.click(mJob.getCenter().offset(70, 0));
screen.doubleClick(mJob.getCenter().offset(70, 0));
return App.getClipboard();
	}
	public void clickQuery() throws FindFailed, InterruptedException {
		screen.type(Key.F2);
		Thread.sleep(3000);
	}
	public void clickExcecute() throws FindFailed, InterruptedException {
		screen.type(Key.F7);
		Thread.sleep(3000);
	}

}
