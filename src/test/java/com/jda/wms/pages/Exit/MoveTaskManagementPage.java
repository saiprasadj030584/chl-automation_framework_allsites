package com.jda.wms.pages.Exit;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class MoveTaskManagementPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private final JdaHomePage imageCheckFunction;

	@Inject
	public MoveTaskManagementPage(JdaHomePage imageCheckFunction) {
		this.imageCheckFunction = imageCheckFunction;
	}

	public void validateListID() throws FindFailed, InterruptedException {
		screen.click("images/MoveTaskManagement/MANB.png");
		Thread.sleep(1000);
	}
	}

