package com.jda.wms.pages.foods;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.App;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.pages.PageObject;

public class OrderHeaderPage extends PageObject {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private WebDriver webDriver;

	@Inject
	public OrderHeaderPage(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
	}

	public void navigateToOrderHeader() {
		try {
			screen.wait("images/DataMenu.png", timeoutInSec);
			screen.click("images/DataMenu.png");
			screen.wait("images/OrderSubmenu.png", timeoutInSec);
			screen.click("images/OrderSubmenu.png");
			screen.wait("images/OrderHeader.png", timeoutInSec);
			screen.click("images/OrderHeader.png");
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
			}
			screen.wait("images/OrderHeaderQuery1.png", timeoutInSec);
			screen.click("images/OrderHeaderQuery1.png");
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterOrderNo(String OrderNo) throws Throwable {
		try {
			screen.wait("images/OrderHeaderNo.png", timeoutInSec);
			screen.click("images/OrderHeaderNo.png");
			screen.type(OrderNo);
			screen.wait("images/OrderHeaderExecute.png", timeoutInSec);
			screen.click("images/OrderHeaderExecute.png");
		} catch (Exception E) {
			E.printStackTrace();
		}
	}

	public void navigateToOrderLineList() {
		try {
			screen.wait("images/OrderHeaderLines.png", timeoutInSec);
			screen.click("images/OrderHeaderLines.png");
			screen.wait("images/OrderLineMaintenance.png", timeoutInSec);
			screen.doubleClick("images/OrderLineMaintenance.png");

			try {
				Thread.sleep(2000);
			} catch (Exception e) {
			}
			screen.wait("images/OrderlineSkuTobeDoucleclik.png", timeoutInSec);
			screen.doubleClick("images/OrderlineSkuTobeDoucleclik.png");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void allocateOrder() {
		try {
			screen.wait("images/OrderLineUpdate.png", timeoutInSec);
			screen.click("images/OrderLineUpdate.png");
			screen.wait("images/OrderlineAllocateCheck.png", timeoutInSec);
			screen.click("images/OrderlineAllocateCheck.png");

			Match mQty = screen.find("images/OrderlineQuantityOrder.png");
			screen.click(mQty.getCenter().offset(70, 0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);
			int quantity = Integer.parseInt(App.getClipboard());

			while (screen.exists("images/QtySoftAllocated.png") == null) {
				Assert.fail();
			}

			Match mQtysoft1 = screen.find("images/QuantitySoftAllocated.png");
			screen.click(mQtysoft1.getCenter().offset(70, 0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);

			if ((quantity) > 0) {
				screen.wait("images/Order_Line_Execute.png", timeoutInSec);
				screen.click("images/Order_Line_Execute.png");
				screen.wait("images/Order_Line_Save_Yes.png", timeoutInSec);
				screen.click("images/Order_Line_Save_Yes.png");
			} else {
				System.out.println("POC Failure");
				Assert.fail();
			}

			Thread.sleep(60000);
			screen.rightClick();
			screen.wait("images/Order_Line_Refresh.png", timeoutInSec);
			screen.click("images/Order_Line_Refresh.png");
			screen.wait("images/Order_Line_Refresh_Current.png", timeoutInSec);
			screen.click("images/Order_Line_Refresh_Current.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getOrderStatus() {
		String orderStatus = null;
		try {
			screen.wait("images/Order_Header_footer.png", timeoutInSec);
			screen.click("images/Order_Header_footer.png");
			Thread.sleep(3000);

			screen.wait("images/Order_Header_Status1.png", timeoutInSec);
			screen.click("images/Order_Header_Status1.png");
			screen.rightClick();

			screen.wait("images/Order_Line_Refresh.png", timeoutInSec);
			screen.click("images/Order_Line_Refresh.png");
			screen.wait("images/Order_Line_Refresh_Current.png", timeoutInSec);
			screen.click("images/Order_Line_Refresh_Current.png");

			Match mStatus = screen.find("images/Order_Header_Status1.png");
			screen.click(mStatus.getCenter().offset(70, 0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);
			orderStatus = App.getClipboard();
			logger.debug("Order status is: " + orderStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderStatus;
	}
}