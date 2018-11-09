package com.jda.wms.pages.Exit;

import java.sql.SQLException;

import org.junit.Assert;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class ProgramQueryPage{
	Screen screen = new Screen();
	int timeoutInSec = 20;
	
	
	public void enterprogramname(String name) throws FindFailed, InterruptedException {
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