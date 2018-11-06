package com.jda.wms.pages.Exit;

import java.sql.SQLException;

import org.junit.Assert;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.db.Exit.SiteDB;


public class SiteQueryPage {
	private SiteDB siteDB;
	Screen screen = new Screen();
	int timeoutInSec = 20;
	
	@Inject
	public SiteQueryPage(SiteDB siteDB){
		this.siteDB=siteDB;
		
	}

	public void enterSiteID(String siteID) throws FindFailed, InterruptedException {
		
		Match mStatus = screen.find("images/SiteQuery/SiteId.png");
		screen.wait("images/SiteQuery/SiteId.png", timeoutInSec);
		screen.click("images/SiteQuery/SiteId.png");
		mStatus.click();
		screen.type(siteID);
		Thread.sleep(2000);
		
	}
	public String getSiteID() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SiteQuery/SiteId4.png");
		screen.click(mStatus.getCenter().offset(60,0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	}
	public String getTimeZone() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SiteQuery/TimeZone.png");
		screen.click(mStatus.getCenter().offset(70,0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	}
		public String getUDT3() throws FindFailed, InterruptedException {
			Match mStatus = screen.find("images/SiteQuery/UDT3.png");
			screen.click(mStatus.getCenter().offset(70,0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);	
			Thread.sleep(2000);
			return App.getClipboard();
		}
		public void UserDefined() throws FindFailed {
			screen.wait("images/SiteQuery/UserDefined.png", timeoutInSec);
			screen.click("images/SiteQuery/UserDefined.png");
		}
		public String getUDT4() throws FindFailed, InterruptedException {
			Match mStatus = screen.find("images/SiteQuery/UDT4.png");
			screen.click(mStatus.getCenter().offset(70,0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);		
			Thread.sleep(2000);
			return App.getClipboard();
		}
		public String getUDN1() throws FindFailed, InterruptedException {
			Match mStatus = screen.find("images/SiteQuery/UDN1.png");
			screen.click(mStatus.getCenter().offset(70,0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);		
			Thread.sleep(2000);
			return App.getClipboard();
		}
	/*	public void TimeZone_Validation(String siteID) throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
		
			String Timezone = getTimeZone();
			System.out.println("Timezone "+Timezone);
			String TimeZoneDB=SiteDB.getTimeZoneDB(siteID);
			System.out.println("TimeZoneDB "+TimeZoneDB);
			Assert.assertEquals("TimeZone Validated",Timezone,TimeZoneDB);
		}*/
		public void UDT3_Validation(String siteID) throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
			
			UserDefined();
			Thread.sleep(2000);
			/*String UDT3 = getUDT3();
			System.out.println("UDT3 "+UDT3);
			String UDT3DB=SiteDB.getUDT3DB(siteID);
			System.out.println("UDT3DB"+UDT3DB);
			Assert.assertEquals("TimeZone Validated",UDT3,UDT3DB);*/
		}
   /*   public void UDT4_Validation(String siteID) throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
			
//			UserDefined();
			Thread.sleep(2000);
			String UDT4 = getUDT4();
			System.out.println("UDT4 "+UDT4);
			String UDT4DB=SiteDB.getUDT4DB(siteID);
			System.out.println("UDT4DB "+UDT4DB);
			Assert.assertEquals("TimeZone Validated",UDT4,UDT4DB);
		}*/
    /*  public void UDN1_Validation(String siteID) throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
	
//	UserDefined();
	Thread.sleep(2000);
	String UDN1 = getUDN1();
	System.out.println("UDN1 "+UDN1);
	String UDN1DB=SiteDB.getUDN1DB(siteID);
	System.out.println("UDN1DB "+UDN1DB);
	Assert.assertEquals("TimeZone Validated",UDN1,UDN1DB);
}*/
		
		
	}

