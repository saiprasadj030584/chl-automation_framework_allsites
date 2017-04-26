package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class PreAdviceHeaderPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterPreAdviceID(String preAdviceId) throws FindFailed {
		screen.wait("images/PreAdviceHeader/PreAdviceId.png", timeoutInSec);
		screen.click("images/PreAdviceHeader/PreAdviceId.png");
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

	public String getType() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceHeader/Type.png");
		screen.click(mDescription.getCenter().offset(70, 0));
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

	public String getNumberoflines() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceHeader/NumberOfLines.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();

	}

	public void clickAddressTab() throws FindFailed {
		screen.wait("images/PreAdviceHeader/address/Address.png", timeoutInSec);
		screen.click("/images/PreAdviceHeader/address/Address.png");

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
}
