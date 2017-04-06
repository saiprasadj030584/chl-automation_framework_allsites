package com.jda.wms.pages.foods;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.sikuli.basics.Settings;
import org.sikuli.script.App;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.pages.PageObject;

public class OrderHeaderPage extends PageObject {

	Screen Order = new Screen();
	Settings st = new Settings();
	int w = 20;
	private WebDriver webDriver;

	@Inject
	public OrderHeaderPage(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
	}

	// public String Query ="";
	// st.OcrTextSearch =true;
	// st.OcrTextRead=true;

	public void jdaOrder() {
		try {
			Order.wait("images/Data_Menu.png",w);
			Order.click("images/Data_Menu.png");
			Order.wait("images/Order_Submenu.png",w);
			Order.click("images/Order_Submenu.png");
			Order.wait("images/Order_Header.png",w);
			Order.click("images/Order_Header.png");
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
			}
			Order.wait("images/Order_Header_Query1.png",w);
			Order.click("images/Order_Header_Query1.png");
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enter_OrderNo(String Order_No) throws Throwable {
		try {
			Order.wait("images/Order_Header_no.png",w);
			Order.click("images/Order_Header_no.png");
			Order.type(Order_No);
			Order.wait("images/Order_Header_Execute.png",w);
			Order.click("images/Order_Header_Execute.png");
		} catch (Exception E) {
			E.printStackTrace();
			Assert.fail();
		}
	}

	public void jdaorderline() {
		try {
			Order.wait("images/Order_Header_Lines.png",20);
			Order.click("images/Order_Header_Lines.png");
			Order.wait("images/Order_Line_Maintenance.png",20);
			Order.doubleClick("images/Order_Line_Maintenance.png");

			try {
				Thread.sleep(2000);

			} catch (Exception e) {
			}
			Order.wait("images/Order_Line_Sku_tobe_doucleclik.png",10);
			Order.doubleClick("images/Order_Line_Sku_tobe_doucleclik.png");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void allocated_product() {
		try {
			Order.wait("images/Order_Line_Update.png",20);
			Order.click("images/Order_Line_Update.png");
			Order.wait("images/Order_line_Allocate_check.png",20);
			Order.click("images/Order_line_Allocate_check.png");
			Match mQty = Order.find("images/Order_Line_qtyorder_1.png");
			Order.click(mQty.getCenter().offset(70, 0));
			Order.type("a", Key.CTRL);
			Order.type("c", Key.CTRL);
			String SA1 = App.getClipboard();

			while (Order.exists("images/Qty_Soft_Allocated1.png") == null) {
				Assert.fail();
			}
			Match mQtysoft1 = Order.find("images/Qty_Soft_Allocated.png");
			// Match mQtytask =
			// Order.find("C:\\Users\\janakiraman.kesavan\\Desktop\\JDA_Image\\Order_Line_Qtytask.png");
			Order.click(mQtysoft1.getCenter().offset(70, 0));
			// Order.click(mQtytask.getCenter().offset(70,0));
			Order.type("a", Key.CTRL);
			Order.type("c", Key.CTRL);
			String SA2 = App.getClipboard();
			if (Integer.parseInt(SA1) > 0) {
				Order.wait("images/Order_Line_Execute.png",20);
				Order.click("images/Order_Line_Execute.png");
				Order.wait("images/Order_Line_Save_Yes.png",20);
				Order.click("images/Order_Line_Save_Yes.png");
			} else {
				System.out.println("POC Failure");
				Assert.fail();
			}

			Thread.sleep(60000);
			Order.rightClick();
			Order.wait("images/Order_Line_Refresh.png",20);
			Order.click("images/Order_Line_Refresh.png");
			Order.wait("images/Order_Line_Refresh_Current.png",20);
			Order.click("images/Order_Line_Refresh_Current.png");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void status_changed() {
		try {
			Order.wait("images/Order_Header_footer.png",20);
			Order.click("images/Order_Header_footer.png");

			Thread.sleep(3000);
			Order.wait("images/Order_Header_Status1.png",20);
			Order.click("images/Order_Header_Status1.png");
			Order.rightClick();
			Order.wait("images/Order_Line_Refresh.png",20);
			Order.click("images/Order_Line_Refresh.png");
			Order.wait("images/Order_Line_Refresh_Current.png",20);
			Order.click("images/Order_Line_Refresh_Current.png");

			/*
			 * Order.wait(
			 * "C:\\Users\\janakiraman.kesavan\\Desktop\\JDA_Image\\Data_Menu.png"
			 * ,10); Order.click(
			 * "C:\\Users\\janakiraman.kesavan\\Desktop\\JDA_Image\\Data_Menu.png"
			 * ); Order.wait(
			 * "C:\\Users\\janakiraman.kesavan\\Desktop\\JDA_Image\\Order_Submenu.png"
			 * ,10); Order.click(
			 * "C:\\Users\\janakiraman.kesavan\\Desktop\\JDA_Image\\Order_Submenu.png"
			 * ); Order.wait(
			 * "C:\\Users\\janakiraman.kesavan\\Desktop\\JDA_Image\\Order_Header.png"
			 * ,10); Order.click(
			 * "C:\\Users\\janakiraman.kesavan\\Desktop\\JDA_Image\\Order_Header.png"
			 * );
			 */

			Match mStatus = Order.find("images/Order_Header_Status1.png");
			Order.click(mStatus.getCenter().offset(70, 0));
			Order.type("a", Key.CTRL);
			Order.type("c", Key.CTRL);
			String SA3 = App.getClipboard();
			if (SA3.equals("Allocated")) {
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

	public void waitForImage(String image, int time) throws InterruptedException {
		for (int i = 0; i < time; i++) {
			if (isImagePresent(image)) {
				break;
			} else {
				Thread.sleep(1000);
			}
		}
	}

	public boolean isImagePresent(String image) {
		boolean status = false;
		Screen s = new Screen();
		try {

			status = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

}