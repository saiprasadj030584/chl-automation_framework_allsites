package com.jda.wms.pages.foods;

//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class StockCheckTaskQueryPage {

	Screen screen = new Screen();
	int timeoutInSec = 20;
	private JdaHomePage jdaHomePage;

	@Inject
	public StockCheckTaskQueryPage(JdaHomePage jdaHomePage) {
		this.jdaHomePage = jdaHomePage;
	}

	public void navigateToStockCheckQueryPage() throws FindFailed, InterruptedException {
		jdaHomePage.clickDataMenu();
		jdaHomePage.hoverInventory();
		clickStockCheck();
	}

	public void clickStockCheck() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/StockCheckQuery.png", timeoutInSec);
		screen.click("images/JDAHome/StockCheckQuery.png");
		Thread.sleep(1000);
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
	
	public void enterTaskDate() throws FindFailed {
		
		//Check if the task date field exists
		screen.wait("images/StockCheckQuery/TaskDate.png", timeoutInSec);
		screen.click("images/StockCheckQuery/TaskDate.png");
		
		screen.type("0");
		screen.type(Key.ENTER);
		
		/*
		//Click date selector option for Task date field
		screen.wait("images/StockCheckQuery/DateSelector.png", timeoutInSec);
		screen.click("images/StockCheckQuery/DateSelector.png");
		//Check if the date Selection Pop-up opens
		screen.wait("images/StockCheckQuery/DateSelectionScreen.png", timeoutInSec);
		screen.wait("images/StockCheckQuery/SetDateOkButton.png", timeoutInSec);
		//select today's date by clicking on OK
		screen.click("images/StockCheckQuery/SetDateOkButton.png");*/
		
		// Java Code
		/*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate getCurrentDate = LocalDate.now();
		screen.wait("images/StockCheckQuery/TaskDate.png", timeoutInSec);
		screen.click("images/StockCheckQuery/TaskDate.png");
		screen.type(dtf.format(getCurrentDate));*/
		
	}
	
	public String getListId() throws FindFailed {
		Match mKitId = screen.find("/images/StockCheckQuery/ListId.png");
		screen.click(mKitId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

}
