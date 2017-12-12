package com.jda.wms.pages.gm;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import com.google.inject.Inject;
import org.sikuli.script.Screen;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
public class SchedulerProgramPage {
	private JDAFooter jDAFooter;
	Screen screen = new Screen();
	int timeoutInSec = 20;
	
	@Inject
	public SchedulerProgramPage(JDAFooter jDAFooter) {
		this.jDAFooter = jDAFooter;
	}
	public void enterProgramName() throws InterruptedException {
		screen.type("CLUSTERURNSP");
		Thread.sleep(1000);
		
	}
	public void clickRunNowButton() throws FindFailed, InterruptedException {
		Match run = screen.find("images/schedulerProgram/runButton.png");
		screen.click("images/schedulerProgram/runButton.png");
		Thread.sleep(2000);
	}
}
