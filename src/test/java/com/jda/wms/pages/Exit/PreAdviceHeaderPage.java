package com.jda.wms.pages.Exit;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.Database;
import com.jda.wms.db.Exit.PreAdviceHeaderDB;
import com.jda.wms.db.Exit.PreAdviceLineDB;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;



public class PreAdviceHeaderPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private Context context;
		
	public void enterPreAdviceID(String preAdviceId) throws FindFailed {
		screen.type(preAdviceId);
	}

	public String getStatus() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceHeader/Status.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
 
	public String getDuedate() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceHeader/DueDate.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getSiteId() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceHeader/SiteID.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public boolean isTypeExist() throws FindFailed {
		if (!screen.find("images/PreAdviceHeader/Type.png").equals(null)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getOrderType() throws FindFailed {
		Match mOrderType = screen.find("images/PreAdviceHeader/Type.png");
		screen.click(mOrderType.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	

	public String getSupplier() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceHeader/Supplier.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getNumberOfLines() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceHeader/NumberOfLines.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void clickAddressTab() throws FindFailed, InterruptedException {
		screen.wait("images/PreAdviceHeader/address/Address.png", timeoutInSec);
		screen.click("images/PreAdviceHeader/address/Address.png");
		Thread.sleep(2000);
	}

	public String getName() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceHeader/address/Name.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getAddress1() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceHeader/address/Address1.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getCountry() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceHeader/address/Country.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getConsignmentID() throws FindFailed {
		Match mConsignmentID = screen.find("images/PreAdviceHeader/ConsignmentID.png");
		screen.click(mConsignmentID.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	
	
	
	


	
}
