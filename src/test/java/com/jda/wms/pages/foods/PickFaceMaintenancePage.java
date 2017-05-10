package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class PickFaceMaintenancePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterLocation(String location) throws FindFailed {
		screen.wait("images/PickFaceMaintenance/LocationAdd.png", timeoutInSec);
		screen.click("images/PickFaceMaintenance/LocationAdd.png");
		screen.type(location);
	}

	public boolean isNoRecordDisplayed() throws FindFailed {
		if (!screen.find("images/PickFaceMaintenance/NoRecords.png").equals(null)) {
			return true;
		} else {
			return false;
		}
	}

	public void enterSkuId(String skuId) throws FindFailed, InterruptedException {
		screen.wait("images/PickFaceMaintenance/SkuId.png", timeoutInSec);
		screen.click("images/PickFaceMaintenance/SkuId.png");
		screen.type(skuId);
	}

	public void selectSiteId(String SiteId) throws FindFailed {
		screen.wait("images/PickFaceMaintenance/SiteId.png", timeoutInSec);
		screen.click("images/PickFaceMaintenance/SiteId.png");
		screen.type(SiteId);
	}

	public void selectFaceType(String faceType) throws FindFailed {
		screen.wait("images/PickFaceMaintenance/FaceType.png", timeoutInSec);
		screen.click("images/PickFaceMaintenance/FaceType.png");
		screen.type(faceType);
	}

	public String getFaceType() throws FindFailed {
		Match mDescription = screen.find("images/PickFaceMaintenance/FaceTyp.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getSkuId() throws FindFailed {
		Match mDescription = screen.find("images/PickFaceMaintenance/Sku.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getLocation() throws FindFailed {
		Match mDescription = screen.find("images/PickFaceMaintenance/LocationId.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getSiteId() throws FindFailed {
		Match mDescription = screen.find("images/PickFaceMaintenance/Site.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
}
