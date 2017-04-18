package com.jda.wms.pages.foods;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.jda.wms.pages.PageObject;

public class OrderHeaderMaintenancePage extends PageObject {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final FooterPage footerPage;
	private final OrderLineMaintenancePage orderLineMaintenancePage;

	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public OrderHeaderMaintenancePage(WebDriver webDriver, FooterPage footerPage,
			OrderLineMaintenancePage orderLineMaintenancePage) {
		super(webDriver);
		this.webDriver = webDriver;
		this.footerPage = footerPage;
		this.orderLineMaintenancePage = orderLineMaintenancePage;
	}

	public void clickQueryButton() throws FindFailed {
		screen.wait("images/OrderHeaderQuery.png", timeoutInSec);
		screen.click("images/OrderHeaderQuery.png");
	}

	public void enterOrderNo(String OrderNo) throws FindFailed {
		screen.wait("images/OrderNumber.png", timeoutInSec);
		screen.click("images/OrderNumber.png");
		screen.type(OrderNo);
	}

	public void clickExecuteButton() throws FindFailed {
		screen.wait("images/OrderHeaderExecute.png", timeoutInSec);
		screen.click("images/OrderHeaderExecute.png");
	}

	public void clickLinesButton() throws FindFailed {
		screen.wait("images/OrderHeaderLines.png", timeoutInSec);
		screen.click("images/OrderHeaderLines.png");
	}

	public String getOrderStatus() throws FindFailed, InterruptedException {
		String orderStatus = null;
		footerPage.clickOrderHeaderFooterButton();
		clickOrderHeaderStatus();
		orderLineMaintenancePage.refreshOrderlinePage();
		Match mStatus = screen.find("images/OrderHeaderStatus.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		orderStatus = App.getClipboard();
		logger.debug("Order status is: " + orderStatus);
		return orderStatus;
	}

	public void clickOrderHeaderStatus() throws FindFailed, InterruptedException {
		screen.wait("images/OrderHeaderStatus.png", timeoutInSec);
		screen.click("images/OrderHeaderStatus.png");
		Thread.sleep(3000L);
	}
}
