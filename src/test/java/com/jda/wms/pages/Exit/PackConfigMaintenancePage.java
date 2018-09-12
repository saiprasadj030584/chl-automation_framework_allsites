package com.jda.wms.pages.Exit;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import com.google.inject.Inject;
import junit.framework.Assert;

public class PackConfigMaintenancePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public PackConfigMaintenancePage() {
	}

	public void enterPackConfigId(String packConfigId) throws FindFailed, InterruptedException {
		screen.wait("images/JDAPackConfig/PackConfig.png", timeoutInSec);
		screen.click("images/JDAPackConfig/PackConfig.png");
		screen.type(packConfigId);
		Thread.sleep(1000);
	}

	public String getTagVolume() throws FindFailed {
		Match mTagVolume = screen.find("/images/JDAPackConfig/General/TagVolume.png");
		screen.click(mTagVolume.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public boolean checkVolumeAtEach() throws FindFailed, InterruptedException {
		if (!screen.exists("/images/JDAPackConfig/General/VolumeAtEach.png").equals(null)) {
			return true;
		} else
			return false;
	}

	public void clickTrackingLevelsTab() throws FindFailed {
		screen.wait("/images/JDAPackConfig/TrackingLevels.png", timeoutInSec);
		screen.click("/images/JDAPackConfig/TrackingLevels.png");
	}

	public void clickRDTTab() throws FindFailed, InterruptedException {
		screen.wait("/images/JDAPackConfig/RDT.png", timeoutInSec);
		screen.click("/images/JDAPackConfig/RDT.png");
		Thread.sleep(5000);
	}

	public String getRatio1To2() throws FindFailed {
		Match mRatios = screen.find("/images/JDAPackConfig/Tracking Level/Ratio1to2.png");
		screen.click(mRatios.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getTrackingLevel1() throws FindFailed {
		Match mTrackingLevel1 = screen.find("/images/JDAPackConfig/Tracking Level/TrackingLevel1.png");
		screen.click(mTrackingLevel1.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getTrackingLevel2() throws FindFailed {
		Match mTrackingLevel2 = screen.find("/images/JDAPackConfig/Tracking Level/TrackingLevel2.png");
		screen.click(mTrackingLevel2.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getRDTTrackingLevel1() throws FindFailed, InterruptedException {
		Match mRDTTrackingLevel1 = screen.find("/images/JDAPackConfig/RDT/RDTTrackingLevel1.png");
		screen.click(mRDTTrackingLevel1.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();

	}

	public String getRDTTrackingLevel2() throws FindFailed {
		Match mRDTTrackingLevel2 = screen.find("/images/JDAPackConfig/RDT/RDTTrackingLevel2.png");
		screen.click(mRDTTrackingLevel2.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	
	public String getRatio2To3() throws FindFailed {
		Match mRatio = screen.find("/images/JDAPackConfig/Tracking Level/Ratio2to3.png");
		screen.click(mRatio.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	
	public void clickGeneraltab() throws FindFailed, InterruptedException {
		screen.wait("/images/JDAPackConfig/General.png", timeoutInSec);
		screen.click("/images/JDAPackConfig/General.png");
		Thread.sleep(2000);
	}
}
