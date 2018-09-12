package com.jda.wms.pages.Exit;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class CEConsignmentMaintenancePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private final JDAFooter jdaFooter;

	@Inject
	public CEConsignmentMaintenancePage(JDAFooter jdaFooter) {
		this.jdaFooter = jdaFooter;
	}

	public void selectReceiptType(String receiptType) throws FindFailed, InterruptedException {
		screen.wait("images/CEConsignmentMaintenance/ReceiptType.png", timeoutInSec);
		screen.type(receiptType);
		Thread.sleep(1000);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
	}

	public void selectCEStatus(String ceStatus) throws FindFailed, InterruptedException {
		screen.wait("images/CEConsignmentMaintenance/CEStatus.png", timeoutInSec);
		screen.type(ceStatus);
		Thread.sleep(1000);
		screen.type(Key.TAB);
	}

	public void enterSiteID(String siteID) throws FindFailed, InterruptedException {
		screen.wait("images/CEConsignmentMaintenance/SiteID.png", timeoutInSec);
		screen.type(siteID);
		Thread.sleep(1000);
		screen.type(Key.TAB);
	}

	public void enterSupplier(String supplierID) throws FindFailed, InterruptedException {
		screen.wait("images/CEConsignmentMaintenance/Supplier.png", timeoutInSec);
		screen.type(supplierID);
		Thread.sleep(1000);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		Thread.sleep(1000);
	}

	public void enterConsignerExciseNumber(String ceWarehouseTax) throws FindFailed, InterruptedException {
//		Match mceWarehouseTax = screen.find("images/CEConsignmentMaintenance/eadConsignerExciseNo.png");
//		screen.click(mceWarehouseTax.getCenter().offset(90, 0));
//		screen.wait("images/CEConsignmentMaintenance/eadConsignerExciseNo.png", timeoutInSec);
		screen.type(ceWarehouseTax);
		Thread.sleep(1000);
	}

	public void enterECMSEADDate() throws FindFailed, InterruptedException {
		Match mDate = screen.find("images/CEConsignmentMaintenance/EMCSeADDate.png");
		screen.click(mDate.getCenter().offset(90, 0));
		screen.type("0");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(1000);
		screen.type(Key.TAB);
	}

	public void enterECMSEADTime() throws FindFailed, InterruptedException {
		screen.wait("images/CEConsignmentMaintenance/EMCSeADTime.png", timeoutInSec);
		screen.type("0");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(1000);
	}

	public void enterECMSEADARC(String arcNo) throws FindFailed, InterruptedException {
		Match mARCNo = screen.find("images/CEConsignmentMaintenance/EMCSeADARC.png");
		screen.click(mARCNo.getCenter().offset(90, 0));
		screen.type(arcNo);
		Thread.sleep(1000);
	}

	public String getConsignmentID() throws FindFailed {
		Match mConsignmentID = screen.find("images/CEConsignmentMaintenance/CEConsignmentID.png");
		screen.click(mConsignmentID.getCenter().offset(90, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void clickYes() throws InterruptedException {
		screen.type(Key.ENTER);
		Thread.sleep(3000);
	}
}
