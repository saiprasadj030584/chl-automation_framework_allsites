
package com.jda.wms.UI.pages;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class OrderPreparationPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterOrderId(String orderId) throws FindFailed, InterruptedException {
		Match mOrderId = screen.find("images/OrderPreparation/order.png");
		screen.click(mOrderId.getCenter().offset(70, 0));
		screen.type(orderId);
		Thread.sleep(5000);
	}

	public void selectRecord() throws InterruptedException, FindFailed {
		Match mRecord = screen.find("images/OrderPreparation/OrderID.png");
		Thread.sleep(2000);
		screen.doubleClick(mRecord.below(10));
		Thread.sleep(3000);
	}

	public void selectTrailerType(String trailerType) throws FindFailed, InterruptedException {
		Match mTrailerType = screen.find("images/OrderPreparation/TrailerType.png");
		screen.click(mTrailerType.getCenter().offset(70, 0));
		screen.type(trailerType);
		Thread.sleep(3000);
	}

	public boolean isRecordExist() throws FindFailed {
		if (!screen.find("images/OrderPreparation/SiteId.png").equals(null)) {
			return true;
		} else
			return false;
	}

	public String getTotalOrder() throws FindFailed {
		Match mTotalOrder = screen.find("images/OrderPreparation/TotalOrders.png");
		screen.click(mTotalOrder.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void selectGroupType(String groupType) throws FindFailed {
		Match mGroupType = screen.find("images/OrderPreparation/GroupType.png");
		screen.click(mGroupType.getCenter().offset(70, 0));
		screen.type(groupType);
	}
	public void selectAvailableRecord() throws InterruptedException, FindFailed {
		Match mRecord = screen.find("images/OrderPreparation/AvailableOrder.png");
		Thread.sleep(4000);
		screen.doubleClick(mRecord.below(16));
		Thread.sleep(2000);
	}
}
