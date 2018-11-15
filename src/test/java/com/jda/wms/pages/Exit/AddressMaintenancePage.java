package com.jda.wms.pages.Exit;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class AddressMaintenancePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterAddressID(String addressId) throws FindFailed {
		screen.wait("images/AddressMaintenance/AddressId.png", timeoutInSec);
		screen.click("images/AddressMaintenance/AddressId.png");
		screen.type(addressId);
	}
	public String getAddressID() throws FindFailed {
		Match mDescription = screen.find("images/AddressMaintenance/AddressId.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	public String getAddressType() throws FindFailed {
		Match mDescription = screen.find("images/AddressMaintenance/Addresstype.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getName() throws FindFailed {
		Match mDescription = screen.find("images/AddressMaintenance/AddressName.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getAddress1() throws FindFailed {
		Match mDescription = screen.find("images/AddressMaintenance/Address1.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getCountry() throws FindFailed {
		Match mDescription = screen.find("images/AddressMaintenance/Addresscountry.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void clickCustomsExciseTab() throws FindFailed, InterruptedException {
		screen.wait("images/AddressMaintenance/AddressCustomsExcise.png", timeoutInSec);
		screen.click("images/AddressMaintenance/AddressCustomsExcise.png");
		Thread.sleep(1000);
	}

	public void clickUserDefinedTab() throws FindFailed {
		screen.wait("images/AddressMaintenance/AddressUserDefined.png", timeoutInSec);
		screen.click("images/AddressMaintenance/AddressUserDefined.png");
	}

	public boolean isIsSiteChecked() throws FindFailed {
		if (!screen.find("images/AddressMaintenance/UserDefined/IsSite.png").equals(null)) {
			return true;
		} else {
			return false;
		}
	}

	public String getSiteCategory() throws FindFailed {
		Match mDescription = screen.find("images/AddressMaintenance/UserDefined/SiteCategory.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getCEWarehouseType() throws FindFailed {
		Match mDescription = screen.find("images/AddressMaintenance/CustomsExcise/CEWarehouseType.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public boolean isIsSiteUnchecked() throws FindFailed {
		if (!screen.find("images/AddressMaintenance/UserDefined/IsSiteUnchecked.png").equals(null)) {
			return true;
		} else {
			return false;
		}
	}

	public String getDefaultSupplierPallet() throws FindFailed {
		Match mDescription = screen.find("images/AddressMaintenance/UserDefined/DefaultSupplierPallet.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}


	public boolean isCSSMChecked() throws FindFailed {
		if (!screen.find("images/AddressMaintenance/UserDefined/CssmChecked.png").equals(null)) {
			return true;
		} else {
			return false;
		}
	}

	public String getCETaxwarehouse() throws FindFailed, InterruptedException {
		Match mDescription = screen.find("images/AddressMaintenance/CustomsExcise/CETaxWarehouse.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(1000);
		return App.getClipboard();

	}
	public void clickQuery()throws FindFailed, InterruptedException {
		screen.type(Key.F2);
		Thread.sleep(1000);
		
	}
	public void execute() throws FindFailed, InterruptedException {
		screen.type(Key.F7);
		Thread.sleep(1000);
	}
	public boolean isRecordDisplayed() throws FindFailed {
		if (!screen.find("images/AddressMaintenance/Record.png").equals(null)) {
			return true;
		} else {
			return false;
		}
	}
	
}
