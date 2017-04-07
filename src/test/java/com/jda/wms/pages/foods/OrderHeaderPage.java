package com.jda.wms.pages.foods;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.App;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.pages.PageObject;

public class OrderHeaderPage extends PageObject {
	Screen screen = new Screen();
	int w = 20;
	private WebDriver webDriver;

	@Inject
	public OrderHeaderPage(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
	}

	public void navigateToOrderHeader() {
		try {
			screen.wait("images/Data_Menu.png", w);
			screen.click("images/Data_Menu.png");
			screen.wait("images/Order_Submenu.png", w);
			screen.click("images/Order_Submenu.png");
			screen.wait("images/Order_Header.png", w);
			screen.click("images/Order_Header.png");
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
			}
			screen.wait("images/Order_Header_Query1.png", w);
			screen.click("images/Order_Header_Query1.png");
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterOrderNo(String OrderNo) throws Throwable {
		try {
			screen.wait("images/Order_Header_no.png", w);
			screen.click("images/Order_Header_no.png");
			screen.type(OrderNo);
			screen.wait("images/Order_Header_Execute.png", w);
			screen.click("images/Order_Header_Execute.png");
		} catch (Exception E) {
			E.printStackTrace();
			Assert.fail();
		}
	}

	public void navigateToOrderLineList() {
		try {
			screen.wait("images/Order_Header_Lines.png", 20);
			screen.click("images/Order_Header_Lines.png");
			screen.wait("images/Order_Line_Maintenance.png", 20);
			screen.doubleClick("images/Order_Line_Maintenance.png");

			try {
				Thread.sleep(2000);
			} catch (Exception e) {
			}
			screen.wait("images/Order_Line_Sku_tobe_doucleclik.png", 10);
			screen.doubleClick("images/Order_Line_Sku_tobe_doucleclik.png");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void allocateOrder() {
		try {
			screen.wait("images/Order_Line_Update.png", 20);
			screen.click("images/Order_Line_Update.png");
			screen.wait("images/Order_line_Allocate_check.png", 20);
			screen.click("images/Order_line_Allocate_check.png");
			Match mQty = screen.find("images/Order_Line_qtyorder_1.png");
			screen.click(mQty.getCenter().offset(70, 0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);
			int quantity = Integer.parseInt(App.getClipboard());

			while (screen.exists("images/Qty_Soft_Allocated1.png") == null) {
				Assert.fail();
			}
			Match mQtysoft1 = screen.find("images/Qty_Soft_Allocated.png");
			screen.click(mQtysoft1.getCenter().offset(70, 0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);
			if ((quantity) > 0) {
				screen.wait("images/Order_Line_Execute.png", 20);
				screen.click("images/Order_Line_Execute.png");
				screen.wait("images/Order_Line_Save_Yes.png", 20);
				screen.click("images/Order_Line_Save_Yes.png");
			} else {
				System.out.println("POC Failure");
				Assert.fail();
			}

			Thread.sleep(60000);
			screen.rightClick();
			screen.wait("images/Order_Line_Refresh.png", 20);
			screen.click("images/Order_Line_Refresh.png");
			screen.wait("images/Order_Line_Refresh_Current.png", 20);
			screen.click("images/Order_Line_Refresh_Current.png");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void verifyOrderStatus() {
		try {
			screen.wait("images/Order_Header_footer.png", 20);
			screen.click("images/Order_Header_footer.png");

			Thread.sleep(3000);
			screen.wait("images/Order_Header_Status1.png", 20);
			screen.click("images/Order_Header_Status1.png");
			screen.rightClick();
			screen.wait("images/Order_Line_Refresh.png", 20);
			screen.click("images/Order_Line_Refresh.png");
			screen.wait("images/Order_Line_Refresh_Current.png", 20);
			screen.click("images/Order_Line_Refresh_Current.png");

			Match mStatus = screen.find("images/Order_Header_Status1.png");
			screen.click(mStatus.getCenter().offset(70, 0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);
			String orderStatus = App.getClipboard();
			if (orderStatus.equals("Allocated")) {
				System.out.println("POC Success");
			} else {
				System.out.println("POC Failure");
				Assert.fail();
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}