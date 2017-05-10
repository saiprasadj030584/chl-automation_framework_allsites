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
	private final JDAFooter jdaFooter;
	private final OrderLineMaintenancePage orderLineMaintenancePage;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public OrderHeaderMaintenancePage(WebDriver webDriver, JDAFooter jdaFooter,
			OrderLineMaintenancePage orderLineMaintenancePage) {
		super(webDriver);
		this.webDriver = webDriver;
		this.jdaFooter = jdaFooter;
		this.orderLineMaintenancePage = orderLineMaintenancePage;
	}

	public void enterOrderNo(String OrderNo) throws FindFailed {
		screen.wait("images/OrderNumber.png", timeoutInSec);
		screen.click("images/OrderNumber.png");
		screen.type(OrderNo);
	}

	public void clickLinesButton() throws FindFailed {
		screen.wait("images/OrderHeaderLines.png", timeoutInSec);
		screen.click("images/OrderHeaderLines.png");
	}

	public String getOrderStatus() throws FindFailed, InterruptedException {
		String orderStatus = null;
		jdaFooter.clickOrderHeaderFooterButton();
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

	public String getOrderDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCreatedBy() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getOrderTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCreationDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCreationTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMoveTaskStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFromSiteId() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isTypeExist() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getNumberOfLines() {
		// TODO Auto-generated method stub
		return null;
	}

	public void clickDeliveryAddressTab() {
		// TODO Auto-generated method stub
		
	}

	public String getCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAddress1() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	public void clickDeliveryDetailsTab() {
		// TODO Auto-generated method stub
		
	}

	public String getDeliveryByTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getShipByTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getShipByDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDeliveryByDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public void clickUserDefinedTab() {
		// TODO Auto-generated method stub
		
	}
}
