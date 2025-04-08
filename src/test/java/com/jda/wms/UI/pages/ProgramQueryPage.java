package com.jda.wms.UI.pages;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class ProgramQueryPage{
	Screen screen = new Screen();
	int timeoutInSec = 20;
	
	
	public void enterProgramName(String name) throws FindFailed, InterruptedException {
		screen.wait("images/ProgramQuery/programname.png", timeoutInSec);
		screen.click("images/ProgramQuery/programname.png");
		screen.type(name);
		Thread.sleep(3000);
	}
	public void clickRun() throws FindFailed, InterruptedException {
		screen.wait("images/ProgramQuery/Runnow.png", timeoutInSec);
		screen.click("images/ProgramQuery/Runnow.png");
		Thread.sleep(3000);
		
	}
}