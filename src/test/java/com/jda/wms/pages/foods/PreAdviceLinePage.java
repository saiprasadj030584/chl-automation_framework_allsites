package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class PreAdviceLinePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public PreAdviceLinePage() {
	}

	public void enterPreAdviceID(String preAdviceId) throws FindFailed {
		screen.type(preAdviceId);
	}

	public String getSkuId() throws FindFailed {
		Match mSkuId = screen.find("images/PreAdviceLine/General/SkuId.png");
		screen.click(mSkuId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	// FIXME mDeAYOOscription - rename with appropriate field names for all the
	// methods
	public String getQtyDue() throws FindFailed {
		Match mQtyDue = screen.find("images/PreAdviceLine/General/QtyDue.png");
		screen.click(mQtyDue.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getTrackingLevel() throws FindFailed {
		Match mTrackingLevel = screen.find("images/PreAdviceLine/General/TrackingLevel.png");
		screen.click(mTrackingLevel.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getPackConfig() throws FindFailed {
		Match mPackConfig = screen.find("images/PreAdviceLine/General/PackConfig.png");
		screen.click(mPackConfig.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getUnderBond() throws FindFailed {
		Match mUnderBond = screen.find("images/PreAdviceLine/General/UnderBond.png");
		screen.click(mUnderBond.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void ClickUserDefinedTab() throws FindFailed, InterruptedException {
		screen.wait("images/PreAdviceLine/UserDefined.png", timeoutInSec);
		screen.click("images/PreAdviceLine/UserDefined.png");
		Thread.sleep(3000);
	}

	public String getCaseRatio() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceLine/UserDefined/CaseRatio.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getBaseUOM() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceLine/UserDefined/BaseUOM.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void selectFirstRecord() throws FindFailed, InterruptedException {
		// screen.wait("images/InventoryTransactionQuery/CodeInResults.png",
		// timeoutInSec);
		// screen.click("images/InventoryTransactionQuery/CodeInResults.png");
		// Thread.sleep(2000);
		Match mPreAdviceIdHeader = screen.find("images/PreAdviceLine/PreAdviceIdHeader.png");
		Thread.sleep(2000);
		screen.click(mPreAdviceIdHeader.below(10));
		Thread.sleep(2000);
		Match mFirstRecord = screen.find("images/PreAdviceLine/FirstRecord.png");
		screen.doubleClick(mFirstRecord.below(1));
	}
}
