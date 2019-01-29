package com.jda.wms.pages.Exit;

import org.junit.Assert;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.pages.Exit.OrderHeaderMaintenancePage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderContainerPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private final OrderHeaderMaintenancePage orderHeaderMaintenancePage;
	private JDAFooter jdaFooter;
	

	@Inject
	public OrderContainerPage(OrderHeaderMaintenancePage orderHeaderMaintenancePage,JDAFooter jdaFooter) {
		this.orderHeaderMaintenancePage = orderHeaderMaintenancePage;
		this.jdaFooter = jdaFooter;
	}


	public void enterOrderId(String orderId) throws InterruptedException {
		screen.type(orderId);
		Thread.sleep(1000);
	}


	public void enterPallet(String palletId)  throws InterruptedException {
		screen.type(palletId);
		Thread.sleep(1000);
	}


	public void enterContainerId(String containerId) throws InterruptedException {
		screen.type(containerId);
		Thread.sleep(1000);
	}


	public String getContainer() throws FindFailed, InterruptedException {
		
		screen.wait("images/OrderContainer/Container.png", timeoutInSec);
		screen.click("images/OrderContainer/Container.png");
		Match mStatus = screen.find("images/OrderContainer/Container.png");
		screen.click(mStatus.getCenter().offset(70,0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	}
	
	
}
