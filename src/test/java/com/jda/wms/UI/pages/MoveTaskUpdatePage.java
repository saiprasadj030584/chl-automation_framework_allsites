package com.jda.wms.UI.pages;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class MoveTaskUpdatePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private final JdaHomePage imageCheckFunction;

	@Inject
	public MoveTaskUpdatePage(JdaHomePage imageCheckFunction) {
		this.imageCheckFunction = imageCheckFunction;
	}

	public void clickReleaseButton() throws FindFailed, InterruptedException {
		screen.wait("images/MoveTaskUpdate/Release.png", timeoutInSec);
		screen.click("images/MoveTaskUpdate/Release.png");
		Thread.sleep(3000);
	}
	public void clickHoldButton() throws FindFailed, InterruptedException {
		screen.wait("images/MoveTaskUpdate/Hold.png", timeoutInSec);
		screen.click("images/MoveTaskUpdate/Hold.png");
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
	
	public void enterTaskId(String taskId) throws FindFailed {
			
		/*Match mtaskId = screen.find("images/MoveTaskUpdate/taskid.png");
		screen.click(mtaskId.getCenter().offset(70, 0));
		screen.type(taskId);*/
		
		imageCheckFunction.checkImageOnScreenAndClickTheGivenImageObjectOffset(
				"/images/MoveTaskUpdate/taskid.png", 0.46f, 70);
		screen.type(taskId);
	}

	public String enterListId(String list)throws FindFailed {
		Match mStatus = screen.find("images/MoveTaskUpdate/ListId.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		screen.type(list);
		return App.getClipboard();
		
	}

	public void selectTaskID() throws FindFailed, InterruptedException {

			Match mLocation = screen.find("images/MoveTaskQuery/TaskForSelection.png");
			screen.click(mLocation.getCenter().below(15));
			Thread.sleep(3000);
		}

	public boolean istaskSelected() {
		if ((screen.exists("images/MoveTaskUpdate/SelectedTask.png") != null))
			return true;
		else
			return false;
	}
	public boolean istaskReleased() {
		if ((screen.exists("images/MoveTaskUpdate/TaskToRelease.png") != null))
			return true;
		else
			return false;
	}
	}

