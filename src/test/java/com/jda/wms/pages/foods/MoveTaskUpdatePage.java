package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class MoveTaskUpdatePage {
	private final JdaHomePage jdaHomePage;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public MoveTaskUpdatePage(JdaHomePage jdaHomePage) {
		this.jdaHomePage = jdaHomePage;
	}

	public void clickReleaseButton() throws FindFailed, InterruptedException {
		screen.wait("images/MoveTaskUpdate/Release.png", timeoutInSec);
		screen.click("images/MoveTaskUpdate/Release.png");
		Thread.sleep(3000);
	}

	public void enterTagId(String tagId) throws FindFailed {
		Match mStatus = screen.find("images/MoveTaskUpdate/mTagID.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.type("a", Key.CTRL);
		screen.type(Key.DELETE);
		screen.type(tagId);
	}

	public String getCheckString() throws FindFailed {
		Match mStatus = screen.find("images/Location/CheckString.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void enterLocation(String location) throws FindFailed {
		screen.type(location);
	}
}
