package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.db.Database;

public class SKUMaintenancePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private final JDAFooter jDAFooter;
	private Database database;
	private Configuration configuration;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	public SKUMaintenancePage(JDAFooter jDAFooter, Database database, Configuration configuration) {
		this.jDAFooter = jDAFooter;
		this.database = database;
		this.configuration = configuration;
	}

	// TODO donot use this method and replace with enterSKUID() method
	public void searchSKUid(String skuId) throws FindFailed, InterruptedException {
		jDAFooter.clickQueryButton();
		enterSKUID(skuId);
		jDAFooter.clickExecuteButton();
	}

	public void enterSKUID(String skuId) throws FindFailed, InterruptedException {
		screen.type(skuId);
		Thread.sleep(2000);
	}

	public String getSKUid() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/SKUID.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getSKUDescription() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/SKUDescription.png");
		screen.click(mStatus.getCenter().offset(100, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getEAN() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/Settings1/EAN.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getUPC() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/Settings1/UPC.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getProductGroup() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/Settings1/ProductGroup.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getEachQuantity() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/Settings1/EachQuantity.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public boolean isTagMergeUnchecked() throws InterruptedException, FindFailed {
		if (screen.exists("images/SKUMaintenanceTable/Settings1/TagMergeUnchecked.png") != null)
			return true;
		else
			return false;
	}

	public void navigateToSettings4() throws FindFailed, InterruptedException {
		screen.wait("images/SKUMaintenanceTable/Settings4.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/Settings4.png");
		Thread.sleep(3000L);
	}

	public void clickCustomsAndExcise() throws FindFailed, InterruptedException {
		screen.wait("images/SKUMaintenanceTable/CustomsExcise.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/CustomsExcise.png");
		Thread.sleep(3000L);
	}

	public String getCEWarehouseType() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/CustomsAndExcise/CEWarehouseType.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getCEVatCode() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/CustomsAndExcise/CEVATCode.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public boolean isCESKUChecked() {
		if (screen.exists("images/SKUMaintenanceTable/BatchExpiryData/CESKUChecked.png") != null)
			return true;
		else
			return false;
	}

	public boolean isNewProductChecked() {
		if (screen.exists("images/SKUMaintenanceTable/Settings4/NewProductChecked.png") != null)
			return true;
		else
			return false;
	}

	public String getCEAlcoholicStrength() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/CustomsAndExcise/CEAlcoholicStrength.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void navigateToLinking() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/Linking.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/Linking.png");
		Thread.sleep(3000L);
	}

	public boolean isSiteIdExist() {
		if (screen.exists("images/SKUMaintenanceTable/Linking/SiteID9771.png") != null)
			return true;
		else
			return false;
	}

	public void navigateToBatchExpiry() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/BatchExpiryData.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/BatchExpiryData.png");
		Thread.sleep(3000L);
	}

	public String getAllocationGroup() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/Settings1/AllocationGroup.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void clickUserDefined() throws FindFailed, InterruptedException {
		screen.wait("images/SKUMaintenanceTable/UserDefined.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/UserDefined.png");
		Thread.sleep(3000L);
	}

	public String getBaseUOM() throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/SKUMaintenanceTable/UserDefined/BaseUOM.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getSAPCreationStatus() throws InterruptedException, FindFailed {
		Match mStatus = screen.find("images/SKUMaintenanceTable/UserDefined/SAPCreationStatus.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void navigateToSupplierSKU() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/SupplierSKU.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/SupplierSKU.png");
		Thread.sleep(3000);
	}

	public String getSupplierSKUId() throws InterruptedException, FindFailed {
		Match mStatus = screen.find("images/SKUMaintenanceTable/SupplierSKU/SupplierSKUID.png");
		screen.click(mStatus.getCenter().offset(30, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public boolean isExpiryRequiredUnchecked() {
		if (screen.exists("images/SKUMaintenanceTable/BatchExpiryData/ExpiryRequiredUnchecked.png") != null)
			return true;
		else
			return false;
	}

	public void clicksettings1Tab() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/Settings1.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/Settings1.png");
		Thread.sleep(2000);
	}

	// FIXME Why do you need this here?
	public boolean isCurrentVintage(String currentVintage) {
		if (!currentVintage.equals(null))
			return true;
		else
			return false;
	}

	public void clickUserDefinedTab() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/UserDefined.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/UserDefined.png");
		Thread.sleep(2000);
	}

	public String getCurrentVintage() throws InterruptedException, FindFailed {
		Match mCurrentVintage = screen.find("images/SKUMaintenanceTable/UserDefined/CurrentVintage.png");
		Thread.sleep(2000);
		screen.click(mCurrentVintage.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void clickSettings1Tab() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/Settings1.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/Settings1.png");
		Thread.sleep(2000);
	}

	public void invokeDataBase() throws ClassNotFoundException {
		database.connect();
		logger.debug(database.getABV("60070710"));
	}
}
