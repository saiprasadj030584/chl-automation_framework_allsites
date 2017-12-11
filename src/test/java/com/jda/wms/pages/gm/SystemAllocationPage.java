package com.jda.wms.pages.gm;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class SystemAllocationPage {

	Screen screen = new Screen();
	private Context context;

	@Inject
	public SystemAllocationPage(Context context) {
		this.context = context;
	}

	public void enterOrderID() throws InterruptedException {
		screen.type(context.getOrderId());
		Thread.sleep(1000);
	}

	public String getConsignmentID() throws FindFailed {
		Match mConsignmentID = screen.find("images/PreAdviceHeader/ConsignmentID.png");
		screen.click(mConsignmentID.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	
	public void enterOrderId(String orderId) throws InterruptedException {
		screen.type(orderId);
		Thread.sleep(1000);
	}
	
	public void enterOrderTime(String orderTime) throws FindFailed, InterruptedException {
		Match mConsignmentID = screen.find("images/OrderHeaderMaintenance/OrderTime.png");
		screen.click(mConsignmentID.getCenter().offset(70, 0));
		screen.type(orderTime);
		Thread.sleep(2000);
	}
	
	public void enterConsignmentID(String consignmentID) throws FindFailed, InterruptedException {
		Match mConsignmentID = screen.find("images/OrderHeaderMaintenance/Consignment.png");
		screen.click(mConsignmentID.getCenter().offset(70, 0));
		screen.type(consignmentID);
		Thread.sleep(2000);
	}
	public void selectAllRecords() throws FindFailed, InterruptedException {
		Match mConsignmentID = screen.find("images/SystemAllocation/OrderTab.png");
		Thread.sleep(2000);
		screen.click(mConsignmentID.below(10));
		Thread.sleep(1000);
		screen.type("a", Key.CTRL);
		Thread.sleep(2000);
	}
}
