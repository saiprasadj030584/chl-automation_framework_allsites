package com.jda.wms.UI.pages;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class StockCheckTaskQueryPage {

	Screen screen = new Screen();
	int timeoutInSec = 20;
	private JdaHomePage imageCheckFunction;

	@Inject
	public StockCheckTaskQueryPage(JdaHomePage imageCheckFunction) {
		this.imageCheckFunction = imageCheckFunction;
	}

	public void enterLocation(String location) throws FindFailed {
		screen.wait("images/StockCheckQuery/Location.png", timeoutInSec);
		screen.click("images/StockCheckQuery/Location.png");
		screen.type(location);
	}

	public void selectSiteId(String siteId) throws FindFailed {
		screen.wait("images/StockCheckQuery/SiteId.png", timeoutInSec);
		screen.click("images/StockCheckQuery/SiteId.png");
		screen.type(siteId);
		screen.type(Key.ENTER);
	}

	public void enterTaskDate() throws FindFailed, InterruptedException {

		// imageCheckFunction.checkImageOnScreenAndClickTheGivenImageObject("/images/StockCheckQuery/TaskDate.png",
		// 0.85f);
		// screen.type("0");
		// screen.type(Key.ENTER);

		Match taskDate = screen.find("/images/StockCheckQuery/TaskDate.png");
		screen.click(taskDate.getCenter().offset(70, 0));
		screen.type("0");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
	}
	public String getListIdnew() throws FindFailed, InterruptedException {
			Match mKitId = screen.find("/images/StockCheckQuery/ListId.png");
			screen.click(mKitId.getCenter().offset(70, 0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);
			return App.getClipboard();
		}
	public String getListId() throws FindFailed, InterruptedException {
		if (screen.exists("/images/StockCheckQuery/ListId.png") != null) {
			Match mKitId = screen.find("/images/StockCheckQuery/ListId.png");
			screen.click(mKitId.getCenter().offset(70, 0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);

		} else {
			Pattern testPattern = new Pattern("/images/StockCheckQuery/MultipleListId.png");
			testPattern.similar(0.95f);
			Match match = screen.find(testPattern);
			screen.wait(testPattern, 120);
			screen.click(testPattern);
			screen.doubleClick(match.below(10));
			Thread.sleep(2000);
			if (screen.exists("/images/StockCheckQuery/ListId.png") != null) {
				Match mKitId = screen.find("/images/StockCheckQuery/ListId.png");
				screen.click(mKitId.getCenter().offset(70, 0));
				screen.type("a", Key.CTRL);
				screen.type("c", Key.CTRL);

			} else {
				System.out.println("List Id not found");
			}
		}
		return App.getClipboard();
	}

	public void enterTagId(String tagId) throws FindFailed, InterruptedException {
		// imageCheckFunction.checkImageOnScreenAndClickTheGivenImageObject("images/StockCheckQuery/TextBoxTagId.png",
		// 1.0f);
		Thread.sleep(1500);
		screen.type(tagId);
	}

	public String getWorkZone() throws FindFailed, InterruptedException {
		if (screen.exists("/images/StockCheckQuery/workZone.png") != null) {
			Match mKitId = screen.find("/images/StockCheckQuery/workZone.png");
			screen.click(mKitId.getCenter().offset(70, 0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);

		} else {
			Pattern testPattern = new Pattern("/images/StockCheckQuery/workZone.png");
			testPattern.similar(0.95f);
			Match match = screen.find(testPattern);
			screen.wait(testPattern, 120);
			screen.click(testPattern);
			screen.doubleClick(match.below(10));
			Thread.sleep(2000);
			if (screen.exists("/images/StockCheckQuery/workZone.png") != null) {
				Match mKitId = screen.find("/images/StockCheckQuery/workZone.png");
				screen.click(mKitId.getCenter().offset(70, 0));
				screen.type("a", Key.CTRL);
				screen.type("c", Key.CTRL);

			} else {
				System.out.println("List Id not found");
			}
		}
		return App.getClipboard();
	}

}
