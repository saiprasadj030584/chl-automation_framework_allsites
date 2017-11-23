package com.jda.wms.pages.rdt;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class PurchaseOrderReceivingPage {
	Screen screen = new Screen();
	Context context = new Context();
	int timeoutInSec = 20;
	private PuttyFunctionsPage puttyFunctionsPage;

	@Inject
	public PurchaseOrderReceivingPage(PuttyFunctionsPage puttyFunctionsPage, Context context) {
		this.puttyFunctionsPage = puttyFunctionsPage;
		this.context = context;
	}

	public void selectReceiveMenu() throws FindFailed, InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectBasicReceiveMenu() throws FindFailed, InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectPreAdviceReceive() throws FindFailed, InterruptedException {
		screen.type("2");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void selectBlindReceive() throws FindFailed, InterruptedException {
		screen.type("1");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public boolean isPreAdviceEntryDisplayed() throws FindFailed, InterruptedException {
		Thread.sleep(10000);
		if ((screen.exists("images/Putty/Receiving/PreAdvEntry.png") != null))
			return true;
		else if ((screen.exists("images/Putty/Receiving/PreAdvComplete.png") != null)) {
			puttyFunctionsPage.pressEnter();
			return true;
		}
		return false;
	}

	public boolean isBlindEntryDisplayed() throws FindFailed, InterruptedException {
		Thread.sleep(10000);
		if ((screen.exists("images/Putty/Receiving/BlindReceivingEntry.png") != null)) {
			return true;
		}
		return false;
	}


	public boolean isBlindReceivingDoneperfectCondition() throws FindFailed, InterruptedException {
		boolean returnVal = false;
		if ((screen.exists("images/Putty/Receiving/ReturnsCompletedDamaged.png") != null)) {
			returnVal = true;

		} else if ((screen.exists("images/Putty/Receiving/Imperfect_error.png") != null)
				|| (screen.exists("images/Putty/Receiving/Singleshoe_error.png") != null)) {
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
			if ((screen.exists("images/Putty/Receiving/ReturnsCompleted.png") != null)
					|| (screen.exists("images/Putty/Receiving/ReturnsCompletedImperfect_N.png") != null)
					|| (screen.exists("images/Putty/Receiving/ReturnsCompletedSingleshoe_N.png") != null)) {
				puttyFunctionsPage.pressEnter();
				returnVal = true;
			}
		} else if ((screen.exists("images/Putty/Receiving/ReturnsCompleted.png") != null)) {
			returnVal = true;
		}
		return returnVal;
	}

	public boolean isFootWearDigitValdiationDone() throws FindFailed, InterruptedException {
		if ((screen.exists("images/Putty/Receiving/ReturnsCompleted.png") != null)
				|| (screen.exists("images/Putty/Receiving/ReturnsCompletedImperfect_N.png") != null)
				|| (screen.exists("images/Putty/Receiving/ReturnsCompletedSingleshoe_N.png") != null)) {
			puttyFunctionsPage.pressEnter();

			return false;
			// } else if
			// (screen.exists("images/Putty/Receiving/footwear_digit_error.png")
			// != null) {
			// Commented due to Application issues in Dispatcher - IK
		} else {
			puttyFunctionsPage.pressEnter();
			puttyFunctionsPage.pressEnter();
			return true;
		}

	}
	
	
	public boolean isOverReceiptErrorReturnsDisplayed() throws InterruptedException {
		Thread.sleep(2000);
		if ((screen.exists("images/Putty/Receiving/OverReceiptError.png") != null))
			return true;
		else
			return false;

	}
	

	public boolean isBlindReceivingDoneWithoutLockCode() throws FindFailed, InterruptedException {
		while (screen.exists("images/Putty/Receiving/ReturnsCompleted.png") == null) {
			System.out.println("Inside loop");
			puttyFunctionsPage.pressEnter();
		}
		if ((screen.exists("images/Putty/Receiving/ReturnsCompleted.png") != null)) {
			System.out.println("Inside IF");
			return true;
		}
		return false;
	}

	public boolean checkNoOfSingles() throws FindFailed, InterruptedException {
		if ((screen.exists("images/Putty/Receiving/QtySingles.png") != null)) {
			return true;
		}
		return false;
	}
	
	public boolean isDamageReceiptDisplayed() throws FindFailed, InterruptedException {
		if ((screen.exists("images/Putty/Receiving/ReturnsCompletedDamaged.png") != null)) {
			return true;
		}
		return false;
	}

	public boolean isQuantityError() throws FindFailed, InterruptedException {
		while (screen.exists("images/Putty/Receiving/QuantityError.png") == null) {
			puttyFunctionsPage.pressEnter();
		}

		if ((screen.exists("images/Putty/Receiving/QuantityError.png") != null)) {
			return true;
		}
		return false;
	}

	public boolean validate_no_asn_error() throws FindFailed, InterruptedException {
		Thread.sleep(1000);
		if ((screen.exists("images/Putty/Receiving/No_ASN_Error.png") != null)) {
			return true;
		} else
			return false;

	}

	public void enterPreAdvId(String preAdviceId) throws FindFailed, InterruptedException {
		screen.type(preAdviceId);
		Thread.sleep(5000);
	}

	public void enterSKUId(String skuID) throws FindFailed, InterruptedException {
		screen.type(skuID);
		Thread.sleep(2000);
		screen.type(Key.ENTER);
		Thread.sleep(13000);
	}

	public String getPreAdvId() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Receiving/Pre-AdviceDisplayed.png");
		screen.click(mStatus.below(5));
		Thread.sleep(2000);
		screen.doubleClick(mStatus.below(1));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public boolean isSearchInfoDisplayed() throws FindFailed, InterruptedException {
		if ((screen.exists("images/Putty/SearchInfo.png") != null)
				|| (screen.exists("images/Putty/Info - Po.png") != null))
			return true;
		else
			return false;
	}

	public String getSupplierId() throws FindFailed, InterruptedException {
		Match mSupplierId = screen.find("images/Putty/Receiving/SuppDisplayed.png");
		screen.click(mSupplierId.getCenter().offset(50, 0));
		screen.doubleClick(mSupplierId.getCenter().offset(50, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}
	public String getPutawayGroup() throws FindFailed, InterruptedException {
		System.out.println("entered putwaay grp");
		Match putawayGroup = screen.find("images/Putty/Receiving/PutawayGroup.png");
		screen.click(putawayGroup.below(5));
		screen.doubleClick(putawayGroup.below(5));
		Thread.sleep(2000);
		return App.getClipboard();
	}
	
	

	public void enterLocation(String location) throws InterruptedException, FindFailed {
		// screen.wait("images/Putty/Receiving/Location.png", timeoutInSec);
		// screen.click("images/Putty/Receiving/Location.png");
		screen.type(location);
	}
	
	public void enterLoc(String location) throws InterruptedException, FindFailed {
		screen.wait("images/Putty/Receiving/Location.png", timeoutInSec);
		screen.click("images/Putty/Receiving/Location.png");
		screen.type(location);
		puttyFunctionsPage.pressTab();
		Thread.sleep(3000);
	}


	public void enterTagId(String uniqueId) throws InterruptedException {
		screen.type(uniqueId);
		screen.type("x", Key.CTRL);
		screen.type(Key.NUM4);
		Thread.sleep(2000);
	}

	public void enterQuantity(String count) throws InterruptedException {
		screen.type(count);

		Thread.sleep(2000);
	}

	public void enterPerfectCondition(String condition) throws InterruptedException {
		screen.type(condition);

		Thread.sleep(2000);
	}

	public void enterLocationInBlindReceive(String location) throws InterruptedException {
		screen.type(location);
		Thread.sleep(2000);
	}

	public void enterMovementLabel(String upiId) throws InterruptedException {
		screen.type(upiId);
		Thread.sleep(2000);
	}

	public void enterSupplierId(String id) throws InterruptedException {
		screen.type(id);
		Thread.sleep(2000);
	}

	public void enterPartset(String partset) throws InterruptedException {
		screen.type(partset);
		Thread.sleep(2000);
	}

	public void enterQtyToReceive(String qtyToReceive) throws InterruptedException {
		if (Integer.parseInt(qtyToReceive) > 999) {
			screen.type(qtyToReceive);
			Thread.sleep(1000);
		} else {
			screen.type(qtyToReceive);
			screen.type(Key.TAB);
			Thread.sleep(1000);
		}
	}

	public void enterCaseRatio(String caseRatio) throws InterruptedException {
		screen.type(caseRatio);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(Key.TAB); // to navigate to Vintage
		Thread.sleep(2000);
	}

	public void enterExpiryDate(String expDate) throws InterruptedException {
		screen.type(expDate);
		Thread.sleep(1000);
		screen.type(Key.ENTER);
	}

	public boolean isReceiveMenuDisplayed() {
		if (screen.exists("images/Putty/Receiving/ReceiveMenu.png") != null)
			return true;
		else
			return false;
	}

	public boolean isBasicReceiveMenuDisplayed() {
		if (screen.exists("images/Putty/Receiving/BasicReceiveMenu.png") != null)
			return true;
		else
			return false;
	}

	public boolean isRcvPreCmp2Displayed() {
		if (screen.exists("images/Putty/Receiving/RcvPreCmp2.png") != null)
			return true;
		else
			return false;
	}

	public boolean isRcvPreCmp3Displayed() {
		if (screen.exists("images/Putty/Receiving/RcvPreCmp3.png") != null)
			return true;
		else
			return false;
	}

	public String getSKUId() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Receiving/SKUDisplayed.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.doubleClick(mStatus.getCenter().offset(50, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public void enterVintage(String vintage) throws InterruptedException {
		screen.type(vintage);
		Thread.sleep(1000);
	}

	public void enterABV(String abv) throws InterruptedException {
		screen.type(abv);
		Thread.sleep(1000);
	}

	public boolean isNoValidPreAdviceDisplayed() throws InterruptedException {
		if (screen.exists("images/Putty/Receiving/NoValidPreAdvice.png") != null) {
			puttyFunctionsPage.pressEnter();
			return true;
		} else
			return false;
	}
	
	public boolean isNoValidPreAdviceFound() throws InterruptedException {
		if (screen.exists("images/Putty/Receiving/NoValidPreAdvice.png") != null) {
			return true;
		} else
			return false;
	}

	public void enterYes() throws InterruptedException {
		screen.type("y");
		Thread.sleep(3000);
		screen.type(Key.ENTER);
	}

	public boolean isMorePercentageAbv() {
		if (screen.exists("images/Putty/Receiving/MorePercentageAbv.png") != null)
			return true;
		else
			return false;
	}

	public boolean isPreAdviceCompletedDisplayed() {
		if (screen.exists("images/Putty/Receiving/PreAdvComplete.png") != null)
			return true;
		else
			return false;
	}

	public boolean isEnterABVForUpcDisplayed() {
		if (screen.exists("images/Putty/Receiving/EnterTheAbv.png") != null)
			return true;
		else
			return false;
	}

	public boolean isVintageNotExpectedDisplayed() {
		if (screen.exists("images/Putty/Receiving/VintageNotExpected.png") != null)
			return true;
		else
			return false;
	}

	public boolean isEnterVintageForUpcDisplayed() {
		if (screen.exists("images/Putty/Receiving/EnterVintage.png") != null)
			return true;
		else
			return false;
	}

	public void enterURNID(String urn) throws FindFailed, InterruptedException {
		screen.type(urn);
		Thread.sleep(2000);
	}

	public void doConfigMovementLabel() throws FindFailed, InterruptedException {
		if (screen.exists("images/Putty/Receiving/MovementLabel/PuttyTop.png") != null) {
			Match mStatus = screen.find("images/Putty/Receiving/MovementLabel/PuttyTop.png");
			screen.click(mStatus.getCenter().offset(50, 0));
			screen.rightClick();
			Thread.sleep(1000);
			screen.click("images/Putty/Receiving/MovementLabel/ChangeSettings.png");
			Thread.sleep(1000);
			screen.click("images/Putty/Receiving/MovementLabel/Keyboard.png");
			Thread.sleep(1000);
			screen.click("images/Putty/Receiving/MovementLabel/Xtem.png");
			Thread.sleep(1000);
			screen.click("images/Putty/Receiving/MovementLabel/Apply.png");
		}
	}

	public void enterUPC1BEL(String upc) throws FindFailed, InterruptedException {
		screen.type(upc);
		Thread.sleep(2000);
	}

	public void enterUPC2(String upc) throws FindFailed, InterruptedException {
		screen.type(upc);

		Thread.sleep(2000);
	}

	public void enterPalletId(String palletID) throws InterruptedException {
		screen.type(palletID);
		Thread.sleep(2000);
		Thread.sleep(4000);
	}

	public void enterBelCode(String getbelCode) throws InterruptedException {
		screen.type(getbelCode);
	Thread.sleep(2000);
	}

	public void enterNewPallet(String getnewpallet) throws InterruptedException {
		screen.type(getnewpallet);
		Thread.sleep(2000);
		puttyFunctionsPage.pressEnter();
		Thread.sleep(4000);
	}

	public boolean isLocationDisplayed() {
		if (screen.exists("images/Putty/Receiving/Location.png") != null)
			return true;
		else
			return false;
	}

	public String getTagId() throws FindFailed, InterruptedException {
		Match mTagId = screen.find("images/Putty/Receiving/TagId.png");
		screen.click(mTagId.getCenter().offset(50, 0));
		screen.doubleClick(mTagId.getCenter().offset(50, 0));
		String tag1 = App.getClipboard();
		screen.click(mTagId.below(5));
		screen.doubleClick(mTagId.below(1));
		String tag2 = App.getClipboard();
		Thread.sleep(1000);
		return (tag1 + tag2);
	}

	public String getPackConfig() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Receiving/PackConfig.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.doubleClick(mStatus.getCenter().offset(50, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public String getUPC() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Receiving/UPC.png");
		screen.click(mStatus.getCenter().offset(50, 0));
		screen.doubleClick(mStatus.getCenter().offset(50, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}

	public boolean isRcvPalletEntPageDisplayed() {
		if (screen.exists("images/Putty/Receiving/RcvPalletEnt.png") != null)
			return true;
		else
			return false;
	}

	public String getQtyToReceive() throws FindFailed, InterruptedException {
		Match mSupplierId = screen.find("images/Putty/Receiving/QtyToReceive.png");
		screen.click(mSupplierId.getCenter().offset(50, 0));
		screen.doubleClick(mSupplierId.getCenter().offset(50, 0));
		Thread.sleep(2000);
	System.out.println("abba"+App.getClipboard());
		System.out.println("ANS"+App.getClipboard().split("_"));
		String[] qtySplit =App.getClipboard().split("_");
		if(qtySplit.length==0)
		{
			for(int i=0;i<4;i++)
			{
				puttyFunctionsPage.pressTab();
			}
			screen.type(String.valueOf(context.getRcvQtyDue()));
			puttyFunctionsPage.pressTab();
			Match mSupplierId1 = screen.find("images/Putty/Receiving/QtyToReceive.png");
			screen.click(mSupplierId1.getCenter().offset(50, 0));
			screen.doubleClick(mSupplierId1.getCenter().offset(50, 0));
			Thread.sleep(2000);
		}
		return App.getClipboard();
	}
	public String getPutAwayGrp() throws FindFailed, InterruptedException {
		Match mSupplierId = screen.find("images/Putty/Receiving/PutawayGroup.png");
		screen.click(mSupplierId.getCenter().offset(50, 0));
		screen.doubleClick(mSupplierId.getCenter().offset(50, 0));
		Thread.sleep(2000);
		return App.getClipboard();
	}


	public String getPallet() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/Putty/Receiving/PutawayForLock.png");
		screen.click(mStatus.below(5));
		Thread.sleep(2000);
		screen.doubleClick(mStatus.below(1));
		return App.getClipboard();
	}

	public void entertagId(String tagId) throws InterruptedException {
		screen.type(tagId);
	}

	public void enterFullPallet(String fullPallet) throws InterruptedException {
		screen.type(fullPallet);
		Thread.sleep(2000);
	}

	public void enterTagid(String tagId) throws InterruptedException {
		screen.type(tagId);
		Thread.sleep(1000);
	}

	public boolean isBlindReceivingDone() throws FindFailed, InterruptedException {
		if (context.getLockCode().equalsIgnoreCase("IMPERFECT")
				|| context.getLockCode().equalsIgnoreCase("SINGLESHOE")) {
			while ((screen.exists("images/Putty/Receiving/Imperfect_error.png") == null)
					&& (screen.exists("images/Putty/Receiving/Singleshoe_error.png") == null)
					|| (screen.exists("images/Putty/Receiving/IncorrectPartSet.png") == null)) {
				puttyFunctionsPage.pressEnter();
			}

			if ((screen.exists("images/Putty/Receiving/IncorrectPartSet.png") != null)) {
				puttyFunctionsPage.pressEnter();
				puttyFunctionsPage.pressEnter();
				if ((screen.exists("images/Putty/Receiving/Imperfect_error.png") != null)
						|| (screen.exists("images/Putty/Receiving/Singleshoe_error.png") != null)) {
					puttyFunctionsPage.pressEnter();
					puttyFunctionsPage.pressEnter();
					if ((screen.exists("images/Putty/Receiving/ReturnsCompleted.png") != null)
							|| (screen.exists("images/Putty/Receiving/ReturnsCompletedImperfect_N.png") != null)
							|| (screen.exists("images/Putty/Receiving/ReturnsCompletedSingleshoe_N.png") != null)) {
						puttyFunctionsPage.pressEnter();
						return true;
					}
					return false;
				}
				return false;
			}

			if ((screen.exists("images/Putty/Receiving/Imperfect_error.png") != null)
					|| (screen.exists("images/Putty/Receiving/Singleshoe_error.png") != null)) {
				puttyFunctionsPage.pressEnter();
				puttyFunctionsPage.pressEnter();
				if ((screen.exists("images/Putty/Receiving/ReturnsCompleted.png") != null)
						|| (screen.exists("images/Putty/Receiving/ReturnsCompletedImperfect_N.png") != null)
						|| (screen.exists("images/Putty/Receiving/ReturnsCompletedSingleshoe_N.png") != null)) {
					puttyFunctionsPage.pressEnter();
					return true;
				}
				return false;
			}
			return false;
		} else if (context.getLockCode().equalsIgnoreCase("DMGD")) {
			while (screen.exists("images/Putty/Receiving/ReturnsCompletedDamaged.png") == null
					&& screen.exists("images/Putty/Receiving/IncorrectPartSet.png") == null) {
				puttyFunctionsPage.pressEnter();
			}
			if ((screen.exists("images/Putty/Receiving/IncorrectPartSet.png") != null)) {
				puttyFunctionsPage.pressEnter();
				puttyFunctionsPage.pressEnter();
				if ((screen.exists("images/Putty/Receiving/ReturnsCompletedDamaged.png") != null)) {
					puttyFunctionsPage.pressEnter();
					return true;
				}
				return false;
			}
			if ((screen.exists("images/Putty/Receiving/ReturnsCompletedDamaged.png") != null)) {
				puttyFunctionsPage.pressEnter();
				return true;
			}
			return false;
		} else if (context.getLockCode().equalsIgnoreCase("IMPSET")) {

			while (screen.exists("images/Putty/Receiving/ReturnsCompleted.png") == null
					&& screen.exists("images/Putty/Receiving/IncorrectPartSet.png") == null) {
				puttyFunctionsPage.pressEnter();
			}
			if ((screen.exists("images/Putty/Receiving/IncorrectPartSet.png") != null)) {
				puttyFunctionsPage.pressEnter();
				puttyFunctionsPage.pressEnter();
				if ((screen.exists("images/Putty/Receiving/ReturnsCompleted.png") != null)) {
					puttyFunctionsPage.pressEnter();
					return true;
				}
				return false;

			}
			if ((screen.exists("images/Putty/Receiving/ReturnsCompleted.png") != null)) {
				puttyFunctionsPage.pressEnter();
				return true;
			}
			return false;
		}
		return false;
	}

	public void enterURRN(String urrn) throws InterruptedException {
		screen.type(urrn);
		Thread.sleep(2000);

	}

	public boolean isURRNNotExistDisplayed() throws InterruptedException {
		Thread.sleep(2000);
		if ((screen.exists("images/Putty/Receiving/urrnNotExist.png") != null))
			return true;
		else
			return false;
	}

	public boolean isOverReceiptErrorDisplayed() throws InterruptedException {
		Thread.sleep(2000);
		if ((screen.exists("images/Putty/Receiving/canNotOverReceipt.png") != null)||(screen.exists("images/Putty/Receiving/canNotOverReceiptHanging.png") != null))
			return true;
		else
			return false;

	}

	public boolean isExcessReceiptErrorDisplayed() throws InterruptedException {
		Thread.sleep(2000);
		if ((screen.exists("images/Putty/Receiving/ExcessError.png") != null))
			return true;
		else
			return false;

	}

	public boolean isReceiveCompleted() {
		if ((screen.exists("images/Putty/Receiving/receiptcompleted.png") != null))
			return true;
		else
			return false;

	}
	
	public boolean isReceiptCompleteDisplayed() throws InterruptedException {
		Thread.sleep(2000);
		if ((screen.exists("images/Putty/Receiving/ReceivingComplete.png") != null))
			return true;
		else
			return false;
	}
	
	public boolean isRCVLinEnt() throws InterruptedException {
		Thread.sleep(10000);
		if ((screen.exists("images/Putty/Receiving/RcvLinEnt.png") != null))
			return true;

		return false;
	}
	
	public boolean isLabelPrintedPageDisplayed() {
		if (screen.exists("images/Putty/Receiving/Labelprinted.png") != null)
			return true;
		else
			return false;
	}
	
	public void enterUPC(String skuId)throws FindFailed, InterruptedException {
		screen.type(skuId);
		Thread.sleep(2000);
}
	
	public void enterTrlId(String trlId) throws InterruptedException {
		screen.type(trlId);
		Thread.sleep(2000);
	}
	
	public void enterHangingValue() throws InterruptedException {
		screen.type("1");
		Thread.sleep(2000);
	}
	
	public void enterAsn(String asn) throws InterruptedException {
		screen.type(asn);
		Thread.sleep(2000);
	}

	public boolean isPutAwayGroupExists() {
		if (screen.exists("images/Putty/Receiving/PutawayGroup.png") != null)
			return true;
		else
			return false;
	}
	
	public boolean isRcvPalletEntPutGrpPageDisplayed() {
		if (screen.exists("images/Putty/Receiving/RcvPalletEntPutGrp.png") != null)
			return true;
		else
			return false;
	}
	
	public String getQtyToReceiveAfterStockAdd() throws FindFailed, InterruptedException {
		Match mSupplierId = screen.find("images/Putty/Receiving/QtyToReceive.png");
		screen.click(mSupplierId.getCenter().offset(50, 0));
		screen.doubleClick(mSupplierId.getCenter().offset(50, 0));
		Thread.sleep(2000);
	System.out.println("abba"+App.getClipboard());
		System.out.println("ANS"+App.getClipboard().split("_"));
		String[] qtySplit =App.getClipboard().split("_");
		if(qtySplit.length==0)
		{
			for(int i=0;i<4;i++)
			{
				puttyFunctionsPage.pressTab();
			}
			screen.type(String.valueOf(context.getRcvQtyDue()+5));
			puttyFunctionsPage.pressTab();
			Match mSupplierId1 = screen.find("images/Putty/Receiving/QtyToReceive.png");
			screen.click(mSupplierId1.getCenter().offset(50, 0));
			screen.doubleClick(mSupplierId1.getCenter().offset(50, 0));
			Thread.sleep(2000);
		}
		return App.getClipboard();
	}
	
	public void enterPalletID(String urn) throws FindFailed, InterruptedException {
		Match mSupplierId = screen.find("images/Putty/Receiving/Pallet.png");
		
		screen.click(mSupplierId.below(10));
		screen.doubleClick(mSupplierId.below(10));
		Thread.sleep(2000);
	System.out.println("abba"+App.getClipboard());
		System.out.println("ANS"+App.getClipboard().split("_"));
		String[] qtySplit =App.getClipboard().split("_");
		System.out.println("PALLLETTT LENGTH"+qtySplit.length);
		if(qtySplit.length!=0)
		{
			for(int i=0;i<qtySplit.length;i++)
			{
				puttyFunctionsPage.rightArrow();
			}
			for(int i=0;i<qtySplit.length;i++)
			{
				screen.type(Key.BACKSPACE);
			}
			Thread.sleep(1000);
		}
		
		screen.type(urn);
		Thread.sleep(2000);
	}
	
}