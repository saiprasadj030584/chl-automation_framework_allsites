package com.jda.wms.pages.foods;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class MoveTaskUpdate {
	private final JdaHomePage jdaHomePage;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public MoveTaskUpdate(JdaHomePage jdaHomePage) {
		this.jdaHomePage = jdaHomePage;
	}

	public void clickReleaseButton() throws FindFailed, InterruptedException {
		screen.wait("images/MoveTaskUpdate/Release.png", timeoutInSec);
		screen.click("images/MoveTaskUpdate/Release.png");
		Thread.sleep(3000);
	}

	public void enterTagId(String tagId) throws FindFailed {
		jdaHomePage.enterTabKey();
		jdaHomePage.enterTabKey();
		screen.type(tagId);
	}
}
