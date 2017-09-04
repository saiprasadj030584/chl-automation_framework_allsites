package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class MoveTaskManagementPage {

	private Context context;
	private JdaHomePage jdaHomePage;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public MoveTaskManagementPage(Context context, JdaHomePage jdaHomePage) {
		this.context = context;
		this.jdaHomePage = jdaHomePage;
	}

	public void enterTaskId(String taskId) throws FindFailed {
		screen.wait("images/MoveTaskManagement/TaskId.png", timeoutInSec);
		screen.click("images/MoveTaskManagement/TaskId.png");
		screen.type(taskId);
	}

	public void clickHorizontalScroll() throws FindFailed, InterruptedException {
		screen.wait("images/OrderManagement/Site.png", timeoutInSec);
		Match mQty = screen.find("images/OrderManagement/Site.png");
		Thread.sleep(2000);
		screen.doubleClick(mQty.left(20));
		// screen.doubleClick();
	}

	public void clickHeaderTable() throws FindFailed, InterruptedException {
		screen.wait("images/MoveTaskManagement/HeaderB2Z1.png", timeoutInSec);
		Match mQty = screen.find("images/MoveTaskManagement/HeaderB2Z1.png");
		Thread.sleep(2000);
		screen.click(mQty.below(10));
	}

	public void clickWorkZone() throws FindFailed, InterruptedException {
		for (int i = 0; i <= 5; i++) {
			clickHorizontalScrollRight();

			if ((screen.exists("/images/MoveTaskManagement/BGColor1.png") != null)
					|| (screen.exists("/images/MoveTaskManagement/BGColor2.png") != null)
					|| (screen.exists("/images/MoveTaskManagement/BGColor3.png") != null)) {
				break;
			}
		}
	}

	public void clickHorizontalScrollRight() throws FindFailed, InterruptedException {
		screen.wait("images/MoveTaskManagement/HorizontalScroll.png", timeoutInSec);
		Match mQty = screen.find("images/MoveTaskManagement/HorizontalScroll.png");
		Thread.sleep(2000);
		screen.click(mQty.left(20));
	}

	public void clickDeleteButton() throws FindFailed, InterruptedException {
		screen.wait("images/MoveTaskManagement/Delete.png", timeoutInSec);
		screen.click("images/MoveTaskManagement/Delete.png");
	}

	public void clickYesButton() throws FindFailed, InterruptedException {
		screen.wait("images/MoveTaskManagement/Yes.png", timeoutInSec);
		screen.click("images/MoveTaskManagement/Yes.png");
	}

	public void deleteRecords() throws FindFailed, InterruptedException {
		while (screen.exists("/images/MoveTaskManagement/BGC.png") != null) {
			screen.wait("images/MoveTaskManagement/BGC.png", timeoutInSec);
			screen.click("images/MoveTaskManagement/BGC.png");

			// screen.wait("images/MoveTaskManagement/ReleasedBackgroundColor1.png",
			// timeoutInSec);
			// Match mQty =
			// screen.find("images/MoveTaskManagement/ReleasedBackgroundColor1.png");
			// screen.click(mQty.getCenter());
			clickDeleteButton();
			clickYesButton();
		}
	}

	public void clickAvailable() throws FindFailed, InterruptedException {
		screen.wait("images//MoveTaskManagement/Available.png", timeoutInSec);
		Match mQty = screen.find("images//MoveTaskManagement/Available.png");
		Thread.sleep(2000);
		screen.click(mQty.below(30));
	}

}
