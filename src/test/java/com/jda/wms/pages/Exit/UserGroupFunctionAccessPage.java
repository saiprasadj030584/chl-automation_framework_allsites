package com.jda.wms.pages.Exit;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.db.Exit.SupplierSkuDB;

public class UserGroupFunctionAccessPage {
	private final SkuDB skuDB;
	private final SupplierSkuDB supplierSkuDB;
	Screen screen = new Screen();
	int timeoutInSec = 20;
	@Inject
	public UserGroupFunctionAccessPage(SkuDB skuDB,SupplierSkuDB supplierSkuDB){
		
		this.skuDB=skuDB;
		this.supplierSkuDB=supplierSkuDB;
	}
	public void enterReport(String report)throws FindFailed, InterruptedException {
		
		screen.wait("images/UserGroupFunctionAccess/Report.png", timeoutInSec);
		screen.click("images/UserGroupFunctionAccess/Report.png");
		Match mStatus = screen.find("images/UserGroupFunctionAccess/Report.png");
		screen.click(mStatus.getCenter().offset(70,0));
		screen.type(report);
	}
	public boolean isRecordDissplayedAndSelected() throws FindFailed, InterruptedException {
	
	if(screen.find("images/UserGroupFunctionAccess/record.png")!=null)
{
	screen.find("images/UserGroupFunctionAccess/record.png");
	screen.click("images/UserGroupFunctionAccess/record.png");
	Thread.sleep(1000);
	return true;
}
	else
	return false;
}
	public boolean isReportAccessEnabled() throws FindFailed, InterruptedException {
		
		if(screen.find("images/UserGroupFunctionAccess/reportEnabled.png")!=null)
	
		return true;
	
		else
		return false;
	}
	public void enterGroup() throws FindFailed, InterruptedException {
		
		screen.wait("images/UserGroupFunctionAccess/Group.png", timeoutInSec);
		screen.click("images/UserGroupFunctionAccess/Group.png");
		Match mStatus = screen.find("images/UserGroupFunctionAccess/Group.png");
		screen.click(mStatus.getCenter().offset(40,0));
		screen.type("O");
	}
public void enterGroupPickClerk() throws FindFailed, InterruptedException {
		
		screen.wait("images/UserGroupFunctionAccess/Group.png", timeoutInSec);
		screen.click("images/UserGroupFunctionAccess/Group.png");
		Match mStatus = screen.find("images/UserGroupFunctionAccess/Group.png");
		screen.click(mStatus.getCenter().offset(40,0));
		screen.type("P");
	}
	public boolean isRecordDissplayedAndSelectedForConsignment() throws FindFailed, InterruptedException {
		
		if(screen.find("images/UserGroupFunctionAccess/recordConsignment.png")!=null)
	{
		screen.find("images/UserGroupFunctionAccess/recordConsignment.png");
	screen.click("images/UserGroupFunctionAccess/record.png");
		Thread.sleep(1000);
		return true;
	}
		else
		return false;
	}
		public boolean isReportAccessEnabledForConsignment() throws FindFailed, InterruptedException {
			
			if(screen.find("images/UserGroupFunctionAccess/reportEnabledConsignment.png")!=null)

			return true;
			else
			return false;
		}
		
		public void clickUser() throws FindFailed, InterruptedException {
			screen.find("images/SystemProfile/User.png");
			screen.doubleClick("images/SystemProfile/User.png");
				Thread.sleep(1000);
		}
		public void clickRecieving()  throws FindFailed, InterruptedException {
			screen.find("images/SystemProfile/Receiving.png");
			screen.doubleClick("images/SystemProfile/Receiving.png");
				Thread.sleep(1000);
		}
		public void clickInt()  throws FindFailed, InterruptedException {
			screen.find("images/SystemProfile/Int.png");
			screen.doubleClick("images/SystemProfile/Int.png");
				Thread.sleep(1000);
		}
		public void AddNewRules() throws FindFailed {
			screen.wait("images/SystemProfile/IntForNewValues.png", timeoutInSec);
			screen.click("images/SystemProfile/IntForNewValues.png");
			Match mStatus = screen.find("images/SystemProfile/IntForNewValues.png");
			screen.rightClick(mStatus.getCenter());
			hoverNewrules();
		}
		public void hoverNewrules() throws FindFailed {
		screen.wait("images/SystemProfile/NewRulesValue.png", timeoutInSec);
		screen.click("images/SystemProfile/NewRulesValue.png");
		screen.mouseMove(70, 0);
	}
		public boolean isLogicAllowed() throws FindFailed, InterruptedException {
			
			if(screen.find("images/SystemProfile/enter.png")!=null)
		{
			screen.wait("images/SystemProfile/enter.png");
			screen.click("images/SystemProfile/enter.png");
			Match mStatus = screen.find("images/SystemProfile/enter.png");
			screen.click(mStatus.getCenter().offset(70, 0));
			Thread.sleep(1000);
			return true;
		}
			else
			return false;
		}
			
		}
