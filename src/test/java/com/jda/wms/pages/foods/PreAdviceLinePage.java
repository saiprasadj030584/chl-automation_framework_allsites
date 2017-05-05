package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import com.jda.wms.context.Context;

import com.google.inject.Inject;

public class PreAdviceLinePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private Context context;

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

	public void enterPreAdviceId(String preAdviceId) throws FindFailed, InterruptedException {
		screen.wait("images/PreAdviceLine/PreAdviceId.png", timeoutInSec);
		screen.click("images/PreAdviceLine/PreAdviceId.png");
		screen.type(preAdviceId);
		Thread.sleep(1000);
	}

	public void enterSKUId(String skuId) throws FindFailed, InterruptedException {
		screen.wait("images/PreAdviceLine/Sku.png", timeoutInSec);
		screen.click("images/PreAdviceLine/Sku.png");
		screen.type(skuId);
		Thread.sleep(1000);
	}

	public void enterLockCode(String lockCode) throws FindFailed, InterruptedException {
		Match mLockCode = screen.find("images/PreAdviceLine/lockCode.png");
		screen.click(mLockCode.getCenter().offset(80, 0));
		screen.type("a", Key.CTRL);
		screen.type(Key.BACKSPACE);
		Thread.sleep(1000);

		switch (lockCode) {
		case "Code Approval":
			screen.type("CODEAPP");
			context.setLockCode("CODEAPP");
			Thread.sleep(2000);
			break;
		case "Components Stock":
			screen.type("CS");
			context.setLockCode("CS");
			Thread.sleep(2000);
			break;
		case "1Damaged":
			screen.type("DMGD");
			context.setLockCode("DMGD");
			Thread.sleep(2000);
			break;
		case "1Expired":
			screen.type("EXPD");
			context.setLockCode("EXPD");
			Thread.sleep(2000);
			break;
		case "Head Office Request":
			screen.type("HOREQ");
			context.setLockCode("HOREQ");
			Thread.sleep(2000);
			break;
		case "Outlets Stock":
			screen.type("OS");
			context.setLockCode("OS");
			Thread.sleep(2000);
			break;
		case "Product Recall":
			screen.type("PRODRECALL");
			context.setLockCode("PRODRECALL");
			Thread.sleep(2000);
			break;
		case "Return from RDC":
			screen.type("RDCRETURNS");
			context.setLockCode("CODEAPP");
			Thread.sleep(2000);
			break;
		case "Supplier Damage":
			screen.type("SUDMG");
			context.setLockCode("SUDMG");
			Thread.sleep(2000);
			break;
		case "Return to Supplier":
			screen.type("SUPPRETURN");
			context.setLockCode("SUPPRETURN");
			Thread.sleep(2000);
			break;
		case "Warehouse Damage":
			screen.type("WHDMG");
			context.setLockCode("WHDMG");
			Thread.sleep(2000);
			break;
		case "Hampers Stock":
			screen.type("HS");
			context.setLockCode("HS");
			Thread.sleep(2000);
			break;
		case "Incubation":
			screen.type("INCUB");
			context.setLockCode("INCUB");
			Thread.sleep(2000);
			break;
		}
	}

	public String getLockCode() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceLine/Lock.png");
		screen.click(mDescription.getCenter().offset(80, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
		
	public void clickUserDefinedTab() throws FindFailed, InterruptedException {
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
		Match mPreAdviceIdHeader = screen.find("images/PreAdviceLine/PreAdviceIdHeader.png");
		Thread.sleep(2000);
		screen.doubleClick(mPreAdviceIdHeader.below(10));
		Thread.sleep(3000);
	}

	public void clickGeneralTab() throws FindFailed, InterruptedException {
		screen.wait("images/PreAdviceLine/General.png", timeoutInSec);
		screen.click("images/PreAdviceLine/General.png");
		Thread.sleep(3000);
	}

	public String getVintage() throws FindFailed {
		Match mvintage = screen.find("images/PreAdviceLine/UserDefined/Vintage.png");
		screen.click(mvintage.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getConsignmentID() throws FindFailed {
		Match mConsignmentID = screen.find("images/PreAdviceLine/General/CEConsignmentID.png");
		screen.click(mConsignmentID.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
}