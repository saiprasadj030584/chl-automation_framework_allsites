package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class AddressMaintenancePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void clickQueryButton() throws InterruptedException {
		screen.type(Key.F2);
		Thread.sleep(5000);
	}

	public void enterAddressID(String addressId) throws FindFailed {
		screen.wait("images/JDAAddressMaintenance/AddressId.png", timeoutInSec);
		screen.click("images/JDAAddressMaintenance/AddressId.png");
		screen.type(addressId);
	}

	public void clickExecuteButton() throws FindFailed, InterruptedException {
		screen.wait("images/JDAAddressMaintenance/AddressExecute.png", timeoutInSec);
		screen.click("images/JDAAddressMaintenance/AddressExecute.png");
		Thread.sleep(5000);
	}

	public Object getAddressType() throws FindFailed {
		Match mDescription = screen.find("images/JDAAddressMaintenance/Addresstype.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public Object getName() throws FindFailed {
		Match mDescription = screen.find("images/JDAAddressMaintenance/AddressName.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public Object getAddress1() throws FindFailed {
		Match mDescription = screen.find("images/JDAAddressMaintenance/Address1.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public Object getCountry() throws FindFailed {
		Match mDescription = screen.find("images/JDAAddressMaintenance/Addresscountry.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	
	}

	public void clickCustomsExcisetab() throws FindFailed {
		screen.wait("images/JDAAddressMaintenance/AddressCustomsExcise.png", timeoutInSec);
		screen.click("images/JDAAddressMaintenance/AddressCustomsExcise.png");
	}

	public void clickUserDefinedTab() throws FindFailed {
		screen.wait("images/JDAAddressMaintenance/AddressUserDefined.png", timeoutInSec);
		screen.click("images/JDAAddressMaintenance/AddressUserDefined.png");
	}

	public boolean checkIsSite() throws FindFailed {
		if (!screen.find("images/JDAAddressMaintenance/UserDefined/IsSite.png").equals(null)) {
			return true;
		} else {
			return false;
		}
	}

	public String getSiteCategory() throws FindFailed {
		Match mDescription = screen.find("images/JDAAddressMaintenance/UserDefined/SiteCategory.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getCEWarehouseType() throws FindFailed {
		Match mDescription = screen.find("images/JDAAddressMaintenance/CustomsExcise/CEWarehouseType.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public boolean checkIsVendor() throws FindFailed {
		if (!screen.find("images/JDAAddressMaintenance/UserDefined/IsSiteUnchecked.png").equals(null)) {
			return true;
		} else {
			return false;
		}
		

	}
}
