package com.jda.wms.pages.Exit;

import org.junit.Assert;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

import com.jda.wms.utils.Utilities;
import org.sikuli.script.Region;
public class CEConsignmentMaintenancePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	Region reg = new Region(0, 0, 4000, 1000);
	private final JDAFooter jdaFooter;
	private final Context context;
	@Inject
	public CEConsignmentMaintenancePage(JDAFooter jdaFooter,Context context) {
		this.jdaFooter = jdaFooter;
		this.context = context;
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
	public void selectToggleMaintenanceMode() throws FindFailed,InterruptedException {
		//Thread.sleep(2000);
		screen.rightClick();
		Thread.sleep(1000);
		screen.wait("images/CEConsignmentMaintenance/ToggleMaintenanceMode.png", timeoutInSec);
		Thread.sleep(2000);
		screen.click("images/CEConsignmentMaintenance/ToggleMaintenanceMode.png");
		Thread.sleep(1000);
	}
	
	public void clickAdd() throws FindFailed,InterruptedException {
		Thread.sleep(1000);
		screen.wait("images/CEConsignmentMaintenance/ConsignmentAddButton.png", timeoutInSec);
		screen.click("images/CEConsignmentMaintenance/ConsignmentAddButton.png");
}
	public void enterConsignment() throws FindFailed,InterruptedException {
		Thread.sleep(1000);
		String Random = Utilities.getFiveDigitRandomNumber();
		String consignmentName = "AUTO"+Random;
		System.out.println("The consignment Name:"+consignmentName );
		context.setConsignmentName(consignmentName);
		screen.type(consignmentName);
}
	public void enterConsignment2() throws FindFailed,InterruptedException {
		Thread.sleep(1000);
		String Random = Utilities.getFiveDigitRandomNumber();
		String consignmentName2 = "AUTO"+Random;
		System.out.println("The consignment Name2:"+consignmentName2 );
		context.setConsignmentName2(consignmentName2);
		screen.type(consignmentName2);
		
	}
	public void enterConsignment1() throws FindFailed,InterruptedException {
		Thread.sleep(1000);
		String Random = Utilities.getFiveDigitRandomNumber();
		String consignmentName1 = "AUTO"+Random;
		System.out.println("The consignment Name:"+consignmentName1 );
		context.setConsignmentName(consignmentName1);
		screen.type(consignmentName1);
}
	public void enterSameConsignment() throws FindFailed,InterruptedException {
		Thread.sleep(1000);
		String consignmentName1=context.getConsignmentName();
		screen.type(consignmentName1);
}
	public void enterSameConsignment2() throws FindFailed,InterruptedException {
		Thread.sleep(1000);
		String consignmentName1=context.getConsignmentName2();
		screen.type(consignmentName1);
}
	public String verifyConsignment() throws FindFailed {
		Match ConsignmentID = screen.find("images/CEConsignmentMaintenance/ConsignmentID.png");
		screen.click(ConsignmentID.getCenter().offset(90, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
		
	}

	
	public void selectStatus() throws FindFailed, InterruptedException {
		screen.wait("images/CEConsignmentMaintenance/ConsignmentStatus.png", timeoutInSec);
	    Match status = screen.find("images/CEConsignmentMaintenance/ConsignmentStatus.png");
	    //Thread.sleep(3000);
	    screen.click(status.getCenter().offset(90, 0));
		Thread.sleep(3000);
		 Match header = screen.find("images/CEConsignmentMaintenance/ConsignmentAllocatedStatus.png");
		 //Thread.sleep(3000); 
		 reg=header.below(150).left(5).right(1000);
		   reg.hover(header);
		   reg.click(header);
	}
	public void selectTransportmode() throws FindFailed, InterruptedException {
		screen.wait("images/CEConsignmentMaintenance/ConsignmentTransMode.png", timeoutInSec);
	    Match status = screen.find("images/CEConsignmentMaintenance/ConsignmentTransMode.png");
	    //Thread.sleep(3000);
	    screen.click(status.getCenter().offset(90, 0));
		//Thread.sleep(3000);
		 Match header = screen.find("images/CEConsignmentMaintenance/ConsignmentTransModeAir.png");
		   reg=header.below(150).left(5).right(1000);
		   //Thread.sleep(3000);
		   reg.hover(header);
		   //Thread.sleep(3000);
		   reg.click(header);
		  // Thread.sleep(3000);
	}
	public void checkPackStatus() throws FindFailed,InterruptedException {
		Thread.sleep(3000);
		screen.wait("images/CEConsignmentMaintenance/ConsignmentHazardousCheck.png", timeoutInSec);
		screen.click("images/CEConsignmentMaintenance/ConsignmentHazardousCheck.png");
		Thread.sleep(3000);
		screen.wait("images/CEConsignmentMaintenance/ConsignmentRepackCheck.png", timeoutInSec);
		screen.click("images/CEConsignmentMaintenance/ConsignmentRepackCheck.png");
		Thread.sleep(3000);
}
	
	public void validateRecord() throws FindFailed,InterruptedException {
		
		screen.doubleClick();
		
	}
	
	public void typeConsignment() throws FindFailed,InterruptedException {
		String consignmentNameDrop = context.getConsignmentName();
		System.out.println("The consignment Name:"+consignmentNameDrop);
		screen.type(consignmentNameDrop);
}
	
	public void typeConsignment2() throws FindFailed,InterruptedException {
		String consignmentNameDrop = context.getConsignmentName2();
		System.out.println("The consignment Name:"+consignmentNameDrop);
		screen.type(consignmentNameDrop);}
	public void typeConsignment1() throws FindFailed,InterruptedException {
		String consignmentNameDrop1 = context.getConsignmentName();
		System.out.println("The consignment Name:"+consignmentNameDrop1);
		screen.type(consignmentNameDrop1);
}
	public void typeChamberAddressId() throws FindFailed,InterruptedException {
		Thread.sleep(2000);
		screen.wait("images/CEConsignmentMaintenance/ConsignmentDropChamber.png", timeoutInSec);
	    Match Chamber = screen.find("images/CEConsignmentMaintenance/ConsignmentDropChamber.png");
	    Thread.sleep(2000);
	    screen.click(Chamber.getCenter().offset(90, 0));
		Thread.sleep(2000);
		screen.type("1");
		Thread.sleep(2000);
		screen.wait("images/CEConsignmentMaintenance/ConsignmentDropAddress.png", timeoutInSec);
	    Match Address = screen.find("images/CEConsignmentMaintenance/ConsignmentDropAddress.png");
	    Thread.sleep(3000);
	    screen.click(Address.getCenter().offset(90, 0));
		screen.type("7993");
		Thread.sleep(3000);
}
	public void typeChamberAddressId2() throws FindFailed,InterruptedException {
		Thread.sleep(2000);
		screen.wait("images/CEConsignmentMaintenance/ConsignmentDropChamber.png", timeoutInSec);
	    Match Chamber = screen.find("images/CEConsignmentMaintenance/ConsignmentDropChamber.png");
	    Thread.sleep(2000);
	    screen.click(Chamber.getCenter().offset(90, 0));
		Thread.sleep(2000);
		screen.type("1");
		Thread.sleep(2000);
		screen.wait("images/CEConsignmentMaintenance/ConsignmentDropAddress.png", timeoutInSec);
	    Match Address = screen.find("images/CEConsignmentMaintenance/ConsignmentDropAddress.png");
	    Thread.sleep(3000);
	    screen.click(Address.getCenter().offset(90, 0));
		screen.type("7993");
		Thread.sleep(3000);
}
	public void selectConsignment() throws FindFailed,InterruptedException {
		Thread.sleep(1000);
		screen.wait("images/TrailerShipping/Coinsignment.png", timeoutInSec);
	    Match status = screen.find("images/TrailerShipping/Coinsignment.png");
	    Thread.sleep(5000);
		screen.click(status.getCenter().offset(90, 0));
		Thread.sleep(5000);
		typeConsignment();
		Thread.sleep(3000);
}
	public void selectConsignment2() throws FindFailed,InterruptedException {
		Thread.sleep(1000);
		screen.wait("images/TrailerShipping/Coinsignment.png", timeoutInSec);
	    Match status = screen.find("images/TrailerShipping/Coinsignment.png");
		screen.click(status.getCenter().offset(90, 0));
		typeConsignment2();
}
	public void selectConsignment1() throws FindFailed,InterruptedException {
		Thread.sleep(1000);
		screen.wait("images/TrailerShipping/Coinsignment.png", timeoutInSec);
	    Match status = screen.find("images/TrailerShipping/Coinsignment.png");
		screen.click(status.getCenter().offset(90, 0));
		typeConsignment1();
}
	
	public void consignmentError() throws FindFailed,InterruptedException {
		Thread.sleep(1000);
		screen.wait("images/CEConsignmentMaintenance/ConsignmentError.png", timeoutInSec);
	   screen.find("images/CEConsignmentMaintenance/ConsignmentError.png");
		if (screen.exists("images/CEConsignmentMaintenance/ConsignmentError.png")!= null){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
}
	public void closeConsignment() throws FindFailed,InterruptedException {
		Thread.sleep(1000);
		screen.wait("images/CEConsignmentMaintenance/SelectConsignment1.png", timeoutInSec);
		//Match status = screen.find("images/CEConsignmentMaintenance/SelectConsignment.png");
		//screen.doubleClick("images/CEConsignmentMaintenance/SelectConsignment.png");
	    //screen.doubleClick(status.belowAt().offset(150, 0));
	    Match header = screen.find("images/CEConsignmentMaintenance/SelectConsignment1.png");
		   reg=header.below(8000).left(5000).right(3000);
		   reg.hover(header);
		   reg.click(header);
		   screen.type(Key.F7);
			Thread.sleep(3000);
}
	public void typeChamberDockId() throws FindFailed,InterruptedException {
		Thread.sleep(1000);
		screen.type("1");
		Thread.sleep(3000);
		screen.type(Key.TAB);
		String DockId = context.getDockDoor();
		screen.type(DockId);
		Thread.sleep(2000);
}
}