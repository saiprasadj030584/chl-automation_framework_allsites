
package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class OrderPreparationPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void selectGroupType() throws FindFailed {
		Match status = screen.find("images/OrderPreparation/GroupType.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("Consignment");
	}

	public void enterOrderId(String orderId) throws FindFailed, InterruptedException {
		Match status = screen.find("images/OrderPreparation/Order.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type(orderId);
		Thread.sleep(4000);
	}

	public void selectRecord() throws InterruptedException, FindFailed {
		Match mQtyToReverse = screen.find("images/OrderPreparation/OrderID.png");
		Thread.sleep(2000);
		screen.doubleClick(mQtyToReverse.below(10));
		Thread.sleep(3000);
	}

	public void selectTrailerType(String trailerType) throws FindFailed {
		screen.wait("images/OrderPreparation/TrailerType.png", timeoutInSec);
		screen.click("images/OrderPreparation/TrailerType.png");
		screen.type(trailerType);
	}

	public boolean isRecordExist() throws FindFailed {
		if (!screen.find("images/OrderPreparation/SiteId.png").equals(null)) {
			return true;
		} else
			return false;
	}

	public void clickOk() throws FindFailed {
		screen.wait("images/OrderPreparation/Ok.png", timeoutInSec);
		screen.click("images/OrderPreparation/Ok.png");
	}

	public String getTotalOrder() throws FindFailed {
		Match mDescription = screen.find("images/OrderPreparation/TotalOrders.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

}
