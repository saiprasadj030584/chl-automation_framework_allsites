package com.jda.wms.pages.gm;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class InventoryTransactionQueryPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	/*private final JDAFooter jdaFooter;

	@Inject
	public InventoryTransactionQueryPage(JDAFooter jdaFooter) {
		this.jdaFooter = jdaFooter;
	}*/

	public void enterTagId(String tagId) throws InterruptedException, FindFailed {
		Match mtagId = screen.find("images/InventoryTransactionQuery/TagIDs.png");
		screen.click(mtagId.getCenter().offset(70, 0));
		screen.type(tagId);
	}

	public void selectCode(String code) throws FindFailed, InterruptedException {
		//jdaFooter.clickQueryButton();
		screen.type(code);
		screen.type(Key.TAB);
	}

	public void enterTransactionDate() throws FindFailed, InterruptedException {
		Match transactionDate = screen.find("images/InventoryTransactionQuery/TransactionDate.png");
		screen.click(transactionDate.getCenter().offset(70, 0));
		screen.type("0");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
	}

	public void enterLockCode(String lockCode) throws FindFailed, InterruptedException {
		Match mLockCode = screen.find("images/InventoryTransactionQuery/lockCode.png");
		screen.click(mLockCode.getCenter().offset(70, 0));

		switch (lockCode) {
		case "Code Approval":
			screen.type("CODEAPP");
			Thread.sleep(2000);
			break;
		case "Components Stock":
			screen.type("CS");
			Thread.sleep(2000);
			break;
		case "1Damaged":
			screen.type("DMGD");
			Thread.sleep(2000);
			break;
		case "EVENTS":
			screen.type("EVENT");
			Thread.sleep(2000);
			break;
		case "Pick exception lock code":
			screen.type("EXCEPT");
			Thread.sleep(2000);
			break;
		case "1Expired":
			screen.type("EXPD");
			Thread.sleep(2000);
			break;
		case "Head Office Request":
			screen.type("HOREQ");
			Thread.sleep(2000);
			break;
		case "Lock code for new vintage or new wine":
			screen.type("NV");
			Thread.sleep(2000);
			break;
		case "Outlets Stock":
			screen.type("OS");
			Thread.sleep(2000);
			break;
		case "Product Recall":
			screen.type("PRODRECALL");
			Thread.sleep(2000);
			break;
		case "Return from RDC":
			screen.type("RDCRETURNS");
			Thread.sleep(2000);
			break;
		case "Supplier Damage":
			screen.type("SUDMG");
			Thread.sleep(2000);
			break;
		case "Return to Supplier":
			screen.type("SUPPRETURN");
			Thread.sleep(2000);
			break;
		case "Warehouse Damage":
			screen.type("WHDMG");
			Thread.sleep(2000);
			break;
		case "Hampers Stock":
			screen.type("HS");
			Thread.sleep(2000);
			break;
		case "Incubation lock code":
			screen.type("INCUB");
			Thread.sleep(2000);
			break;
		}
	}

	public String getOriginalQty() throws FindFailed {
		Match mOriginalQty = screen.find("images/InventoryTransactionQuery/General/OriginalQty.png");
		screen.click(mOriginalQty.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getUpdateQty() throws FindFailed {
		Match mUpdateQty = screen.find("images/InventoryTransactionQuery/General/UpdateQty.png");
		screen.click(mUpdateQty.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getStatus() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("/images/InventoryTransactionQuery/lockStatus.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void clickMiscellaneousTab() throws FindFailed, InterruptedException {
		screen.wait("images/InventoryTransactionQuery/MiscellaneousTab.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/MiscellaneousTab.png");
		Thread.sleep(2000);
	}

	public void clickSettings2Tab() throws FindFailed, InterruptedException {
		screen.wait("images/InventoryTransactionQuery/Settings2Tab.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/Settings2Tab.png");
		Thread.sleep(2000);
	}

	public String getReasonCode() throws FindFailed {
		Match mStatus = screen.find("images/InventoryTransactionQuery/Miscellaneous/ReasonCode.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void clickMiscellaneous2Tab() throws FindFailed, InterruptedException {
		screen.wait("images/InventoryTransactionQuery/Miscellaneous2Tab.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/Miscellaneous2Tab.png");
		Thread.sleep(2000);
	}

	public String getUploaded() throws FindFailed {
		Match mUploaded = screen.find("images/InventoryTransactionQuery/Miscellaneous2/Uploaded.png");
		screen.click(mUploaded.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getUploadedFileName() throws FindFailed {
		Match mFileName = screen.find("images/InventoryTransactionQuery/Miscellaneous2/UploadedFilename.png");
		screen.click(mFileName.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getSkuId() throws FindFailed {
		Match mSkuId = screen.find("images/InventoryTransactionQuery/Sku.png");
		screen.click(mSkuId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getABV() throws FindFailed {
		Match mABV = screen.find("images/InventoryTransactionQuery/ABV.png");
		screen.click(mABV.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		String temp = App.getClipboard();
		App.setClipboard("");
		return temp;
	}

	public void selectRequiredRecord() throws FindFailed, InterruptedException {
		screen.wait("images/InventoryTransactionQuery/CodeInResults.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/CodeInResults.png");
		Thread.sleep(2000);
		Match mStatus = screen.find("images/InventoryTransactionQuery/CodeInResults.png");
		Thread.sleep(2000);
		screen.click(mStatus.below(10));
		Thread.sleep(2000);
		Match mStatuscode = screen.find("images/InventoryTransactionQuery/CodeInResult.png");
		screen.doubleClick(mStatuscode.below(1));
	}

	public boolean isOneOrNoTransactionDisplayed() throws FindFailed {
		if ((screen.find("images/InventoryTransactionQuery/Record1.png") != null)
				|| (screen.find("images/InventoryTransactionQuery/NoRecords.png") != null))
			return true;
		else
			return false;
	}

	public String getFinalLocation() throws FindFailed {
		Match mToLocation = screen.find("images/InventoryTransactionQuery/General/FinalLocation.png");
		screen.click(mToLocation.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getExpiryDate() throws FindFailed {
		Match mExpiryDate = screen.find("images/InventoryTransactionQuery/Miscellaneous/ExpiryDate.png");
		screen.click(mExpiryDate.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void navigateToUserDefinedTab() throws FindFailed, InterruptedException {
		screen.wait("images/InventoryTransactionQuery/UserDefinedTab.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/UserDefinedTab.png");
		Thread.sleep(2000);
	}

	public void enterCode(String code) throws FindFailed, InterruptedException {
		Match mEnterCode = screen.find("images/InventoryTransactionQuery/Code.png");
		screen.click(mEnterCode.getCenter().offset(70, 0));
		screen.type(code);
		screen.type(Key.TAB);
		Thread.sleep(2000);
	}

	public boolean isNoRecordsExists() {
		if (screen.exists("images/InventoryTransactionQuery/NoRecord.png") != null)
			return true;
		else
			return false;
	}

	public String getTagId() throws FindFailed {
		Match mTagId = screen.find("images/InventoryTransactionQuery/TagIDs.png");
		screen.click(mTagId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getURNChild() throws FindFailed {
		Match mURNChild = screen.find("images/InventoryTransactionQuery/UserDefined/Settings2/URNchild.png");
		screen.click(mURNChild.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getCaseRatio() throws FindFailed {
		Match mCaseRatio = screen.find("images/InventoryTransactionQuery/UserDefined/CaseRatio.png");
		screen.click(mCaseRatio.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getBaseUOM() throws FindFailed {
		Match mBaseUOM = screen.find("images/InventoryTransactionQuery/UserDefined/BaseUOM.png");
		screen.click(mBaseUOM.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getIntoDestinationDate() throws FindFailed {
		Match mIntoDestinationDate = screen
				.find("images/InventoryTransactionQuery/UserDefined/IntoDestinationDate.png");
		screen.click(mIntoDestinationDate.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getStorageLocation() throws FindFailed {
		Match mStorageLocation = screen.find("images/InventoryTransactionQuery/UserDefined/StorageLocation.png");
		screen.click(mStorageLocation.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getOriginalRotationId() throws FindFailed {
		Match mOriginalRotationId = screen
				.find("images/InventoryTransactionQuery/Customs & Excise/OriginalRotationID.png");
		screen.click(mOriginalRotationId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getRotationId() throws FindFailed {
		Match mRotationId = screen.find("images/InventoryTransactionQuery/Customs & Excise/RotationID.png");
		screen.click(mRotationId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getCEReceiptType() throws FindFailed {
		Match mCEReceiptType = screen.find("images/InventoryTransactionQuery/Customs & Excise/CEReceiptType.png");
		screen.click(mCEReceiptType.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getUnderBond() throws FindFailed {
		Match mUnderBond = screen.find("images/InventoryTransactionQuery/Customs & Excise/UnderBond.png");
		screen.click(mUnderBond.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getUserId() throws FindFailed {
		Match mUserId = screen.find("images/InventoryTransactionQuery/Miscellaneous/UserID.png");
		screen.click(mUserId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getWorkstation() throws FindFailed {
		Match mWorkstation = screen.find("images/InventoryTransactionQuery/Miscellaneous/Workstation.png");
		screen.click(mWorkstation.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getPalletType() throws FindFailed {
		Match mPalletType = screen.find("images/InventoryTransactionQuery/Miscellaneous2/PalletType.png");
		screen.click(mPalletType.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getPackConfig() throws FindFailed {
		Match mPackConfig = screen.find("images/InventoryTransactionQuery/Miscellaneous2/PackConfig.png");
		screen.click(mPackConfig.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getUploadedDate() throws FindFailed {
		Match mUploadedDate = screen.find("images/InventoryTransactionQuery/Miscellaneous2/UploadedDate.png");
		screen.click(mUploadedDate.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		String temp = App.getClipboard();
		App.setClipboard("");
		return temp;
	}

	public String getUploadedTime() throws FindFailed {
		Match mUploadedTime = screen.find("images/InventoryTransactionQuery/Miscellaneous2/UploadedTime.png");
		screen.click(mUploadedTime.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getOriginator() throws FindFailed {
		Match mOriginator = screen.find("images/InventoryTransactionQuery/Customs & Excise/Originator.png");
		screen.click(mOriginator.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		String temp = App.getClipboard();
		App.setClipboard("");
		return temp;
	}

	public String getOriginatorReference() throws FindFailed {
		Match mOriginatorReference = screen
				.find("images/InventoryTransactionQuery/Customs & Excise/OriginatorReference.png");
		screen.click(mOriginatorReference.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		String temp = App.getClipboard();
		App.setClipboard("");
		return temp;
	}

	public String getCEConsignmentId() throws FindFailed {
		Match mCEConsignmentId = screen.find("images/InventoryTransactionQuery/Customs & Excise/CEConsignmentID.png");
		screen.click(mCEConsignmentId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getDocumentDate() throws FindFailed {
		Match mDocumentDate = screen.find("images/InventoryTransactionQuery/Customs & Excise/DocumentDate.png");
		screen.click(mDocumentDate.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		String temp = App.getClipboard();
		App.setClipboard("");
		return temp;
	}

	public String getDocumentTime() throws FindFailed {
		Match mDocumentTime = screen.find("images/InventoryTransactionQuery/Customs & Excise/DocumentTime.png");
		screen.click(mDocumentTime.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		String temp = App.getClipboard();
		App.setClipboard("");
		return temp;
	}

	public String getVintage() throws FindFailed {
		Match mVintage = screen.find("images/InventoryTransactionQuery/UserDefined/Vintage.png");
		screen.click(mVintage.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		String temp = App.getClipboard();
		App.setClipboard("");
		return temp;
	}

	public boolean isRecordfound() throws FindFailed {
		if (screen.exists("/images/InventoryTransactionQuery/1RecordFound.png", timeoutInSec) != null)
			return true;
		else
			return false;
	}

	public void clickGeneralTab() throws FindFailed, InterruptedException {
		screen.wait("images/InventoryTransactionQuery/GeneralTab.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/GeneralTab.png");
		Thread.sleep(2000);
	}

	public void clickUserDefinedSettings1Tab() throws FindFailed, InterruptedException {
		screen.wait("images/InventoryTransactionQuery/UserDefined/Settings1Tab.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/UserDefined/Settings1Tab.png");
		Thread.sleep(2000);
	}

	public String getDescription() throws FindFailed {
		Match mDescription = screen.find("images/InventoryTransactionQuery/General/Description.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getSupplier() throws FindFailed {
		Match mSupplier = screen.find("images/InventoryTransactionQuery/Miscellaneous/Supplier.png");
		screen.click(mSupplier.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getRDTUserMode() throws FindFailed {
		Match mRDTUserMode = screen.find("images/InventoryTransactionQuery/Miscellaneous/RDTUserMode.png");
		screen.click(mRDTUserMode.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getFromLocation() throws FindFailed {
		Match mFromLocation = screen.find("images/InventoryTransactionQuery/General/FromLocation.png");
		screen.click(mFromLocation.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getToLocation() throws FindFailed {
		Match mToLocation = screen.find("images/InventoryTransactionQuery/General/ToLocation.png");
		screen.click(mToLocation.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getReference() throws FindFailed, InterruptedException {
		Match status = screen.find("/images/InventoryTransactionQuery/Reference.png");
		screen.click(status.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	
	public boolean isNoRecords() throws FindFailed, InterruptedException {
		if (screen.exists("images/DockScheduleBookings/NoRecords.png") != null)
			return false;
		else
			return true;
	}
}
