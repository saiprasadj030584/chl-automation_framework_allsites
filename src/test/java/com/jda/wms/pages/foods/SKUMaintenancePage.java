package com.jda.wms.pages.foods;

import org.openqa.selenium.Keys;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SKUMaintenancePage {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void searchSKUid(String skuId) throws FindFailed, InterruptedException {
		clickQuery();
		enterSKUID(skuId);
		clickExecute();
	}

	private void clickExecute() throws InterruptedException {
		//screen.wait("images/execute.PNG", timeoutInSec);
		//screen.click("images/execute.PNG");
		Thread.sleep(3000);
		screen.type(Key.F7);
	}

	private void enterSKUID(String skuId) throws FindFailed, InterruptedException {
		//screen.wait("images/SKUMaintenanceTable/SKU-ID-Search.png", timeoutInSec);
		//screen.click("images/SKUMaintenanceTable/SKU-ID-Search.png");
		Thread.sleep(2000);
		screen.type(skuId);
	}

	private void clickQuery() throws FindFailed, InterruptedException {
		//screen.wait("images/Query.PNG", timeoutInSec);
		//screen.click("images/Query.PNG");
		Thread.sleep(3000);
		screen.type(Key.F2);
	}

	public String getSKUid() throws FindFailed, InterruptedException {
		//String SKUid = null;
		//clickSKUID();
		Match mStatus = screen.find("images/SKUMaintenanceTable/SKU-ID.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
		/*SKUid = App.getClipboard();
		logger.debug("SKU Id is: " + SKUid);
		return SKUid;*/
		}

	private void clickSKUID() throws FindFailed, InterruptedException {
		screen.wait("images/SKUMaintenanceTable/SKU-ID.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/SKU-ID.png");
		Thread.sleep(3000L);
	}

	public String getSKUDescription() throws FindFailed, InterruptedException {
		//String skudesc = null;
		//clickSKUDescription();
		Match mStatus = screen.find("images/SKUMaintenanceTable/SKU-Description.png");
		screen.click(mStatus.getCenter().offset(100, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
		/*skudesc = App.getClipboard();
		logger.debug("SKU Description is: " + skudesc);
		return skudesc;*/
		
		
	}

	private void clickSKUDescription() throws FindFailed, InterruptedException {
		screen.wait("images/SKUMaintenanceTable/SKU-Description.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/SKU-Description.png");
		Thread.sleep(3000L);
	}

	public String getEAN() throws FindFailed, InterruptedException {
		//String ean = null;
		//ClickEAN();
		Match mStatus = screen.find("images/SKUMaintenanceTable/Settings1/EAN.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
		/*ean = App.getClipboard();
		logger.debug("EAN is: " + ean);
		return ean;*/
	}

	private void clickEAN() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/Settings1/EAN.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/Settings1/EAN.png");
		Thread.sleep(3000L);
	}

	public String getUPC() throws FindFailed, InterruptedException {
		//String upc = null;
		//clickUPC();
		Match mStatus = screen.find("images/SKUMaintenanceTable/Settings1/UPC.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
		/*upc = App.getClipboard();
		logger.debug("UPC is: " + upc);
		return upc;*/
	}

	private void clickUPC() throws FindFailed, InterruptedException {
		screen.wait("images/SKUMaintenanceTable/Settings1/UPC.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/Settings1/UPC.png");
		Thread.sleep(3000L);
	}

	public String getProductGroup() throws FindFailed, InterruptedException {
		//String productgroup = null;
		//clickProductGroup();
		Match mStatus = screen.find("images/SKUMaintenanceTable/Settings1/Product-Group.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
		/*productgroup = App.getClipboard();
		logger.debug("UPC is: " + productgroup);
		return productgroup;*/
	}

	private void clickProductGroup() throws FindFailed, InterruptedException {
		screen.wait("images/SKUMaintenanceTable/Settings1/Product-Group.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/Settings1/Product-Group.png");
		Thread.sleep(3000L);	
	}

	public String getEachQuantity() throws FindFailed, InterruptedException {
		//String eachQty = null;
		//clickEachQuantity();
		Match mStatus = screen.find("images/SKUMaintenanceTable/Settings1/Each-Quantity.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
		/*eachQty = App.getClipboard();
		logger.debug("UPC is: " + eachQty);
		return eachQty;*/
	}

	private void clickEachQuantity() throws FindFailed, InterruptedException {
		screen.wait("images/SKUMaintenanceTable/Settings1/Each-Quantity.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/Settings1/Each-Quantity.png");
		Thread.sleep(3000L);
	}

	public boolean verifyTagMerge() throws InterruptedException, FindFailed {
		if (screen.exists("images/SKUMaintenanceTable/Settings1/Tag-Merge-Unchecked.png")!=null)
			return true;
		else 
			return false;
	}

	public void navigateToSettings4() throws FindFailed, InterruptedException {
		screen.wait("images/SKUMaintenanceTable/Settings4.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/Settings4.png");
		Thread.sleep(3000L);
	}

	public void navigateToCustomsAndExcise() throws FindFailed, InterruptedException {
		screen.wait("images/SKUMaintenanceTable/Customs-Excise.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/Customs-Excise.png");
		Thread.sleep(3000L);
	}

	public String getCEWarehouseType() throws FindFailed, InterruptedException {
		//String cewarehousetype = null;
		//clickCEWarehouseType();
		Match mStatus = screen.find("images/SKUMaintenanceTable/CustomsAndExcise/CE-Warehouse-Type.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
		/*cewarehousetype = App.getClipboard();
		logger.debug("UPC is: " + cewarehousetype);
		return cewarehousetype;*/
	}

	private void clickCEWarehouseType() throws FindFailed, InterruptedException {
		screen.wait("images/SKUMaintenanceTable/CustomsAndExcise/CE-Warehouse-Type.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/CustomsAndExcise/CE-Warehouse-Type.png");
		Thread.sleep(3000L);
	}

	public String getCEVatCode() throws FindFailed, InterruptedException {
		//String cevatcode = null;
		//clickCEVATCode();
		Match mStatus = screen.find("images/SKUMaintenanceTable/CustomsAndExcise/CE-VAT-Code.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
		/*cevatcode = App.getClipboard();
		logger.debug("UPC is: " + cevatcode);
		return cevatcode;*/
	}

	private void clickCEVATCode() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/CustomsAndExcise/CE-VAT-Code.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/CustomsAndExcise/CE-VAT-Code.png");
		Thread.sleep(3000L);
	}

	public boolean verifyCESKU() {
		if (screen.exists("images/SKUMaintenanceTable/BatchExpiryData/CE-SKU-Checked.png")!=null)
			return true;
		else 
			return false;
	}

	public boolean verifyNewProduct() {
		if (screen.exists("images/SKUMaintenanceTable/Settings4/New-Product-Checked.png")!=null)
			return true;
		else 
			return false;
	}

	public String getCEAlcoholicStrength() throws FindFailed, InterruptedException {
		//String cealcoholicstrength = null;
		//clickCEAlcoholicStrength();
		Match mStatus = screen.find("images/SKUMaintenanceTable/CustomsAndExcise/CE-Alcoholic-Strength.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
		/*cealcoholicstrength = App.getClipboard();
		logger.debug("UPC is: " + cealcoholicstrength);
		return cealcoholicstrength;*/
	}

	private void clickCEAlcoholicStrength() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/CustomsAndExcise/CE-Alcoholic-Strength.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/CustomsAndExcise/CE-Alcoholic-Strength.png");
		Thread.sleep(3000L);
	}

	public void navigateToLinking() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/Linking.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/Linking.png");
		Thread.sleep(3000L);
	}

	public boolean verifySiteId() {
		if (screen.exists("images/SKUMaintenanceTable/Linking/SiteID-9771.png")!=null)
			return true;
		else 
			return false;
	}

	public void navigateToBatchExpiry() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/Batch-Expiry-Data.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/Batch-Expiry-Data.png");
		Thread.sleep(3000L);
	}

	public String getAllocationGroup() throws FindFailed, InterruptedException {
		//String allocationgroup = null;
		//clickAllocationGroup();
		Match mStatus = screen.find("images/SKUMaintenanceTable/Settings1/AllocationGroup.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
//		allocationgroup = App.getClipboard();
//		logger.debug("UPC is: " + allocationgroup);
//		return allocationgroup;
	}

	private void clickAllocationGroup() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/Settings1/AllocationGroup.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/Settings1/AllocationGroup.png");
		Thread.sleep(3000L);
	}

	public void navigateToUserDefined() throws FindFailed, InterruptedException {
		screen.wait("images/SKUMaintenanceTable/User-Defined.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/User-Defined.png");
		Thread.sleep(3000L);
	}

	public String getBaseUOM() throws FindFailed, InterruptedException {
//		String baseuom = null;
//		clickBaseUOM();
		Match mStatus = screen.find("images/SKUMaintenanceTable/UserDefined/Base-UOM.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
//		baseuom = App.getClipboard();
//		logger.debug("UPC is: " + baseuom);
//		return baseuom;
	}

	private void clickBaseUOM() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/UserDefined/Base-UOM.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/UserDefined/Base-UOM.png");
		Thread.sleep(3000L);
	}

	public String getSAPCreationStatus() throws InterruptedException, FindFailed {
//		String sapcreationstatus = null;
//		clickSAPCreationStatus();
		Match mStatus = screen.find("images/SKUMaintenanceTable/UserDefined/SAP-Creation-Status.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
//		sapcreationstatus = App.getClipboard();
//		logger.debug("UPC is: " + sapcreationstatus);
//		return sapcreationstatus;
	}

	private void clickSAPCreationStatus() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/UserDefined/SAP-Creation-Status", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/UserDefined/SAP-Creation-Status");
		Thread.sleep(3000L);
	}

	public void navigateToSupplierSKU() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/SupplierSKU.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/SupplierSKU.png");
		Thread.sleep(3000L);
	}

	public String getSupplierSKUId() throws InterruptedException, FindFailed {
//		String supplierskuid = null;
//		clickSupplierSKUId();
		Match mStatus = screen.find("images/SKUMaintenanceTable/SupplierSKU/SupplierSKUID.png");
		screen.click(mStatus.getCenter().offset(30, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
//		supplierskuid = App.getClipboard();
//		logger.debug("UPC is: " + supplierskuid);
//		return supplierskuid;
	}

	private void clickSupplierSKUId() throws InterruptedException, FindFailed {
		screen.wait("images/SKUMaintenanceTable/SupplierSKU/SupplierSKUID.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/SupplierSKU/SupplierSKUID.png");
		Thread.sleep(3000L);
	}

	public boolean verifyExpiryRequired() {
		if (screen.exists("images/SKUMaintenanceTable/BatchExpiryData/Expiry-Required-Unchecked.png")!=null)
			return true;
		else 
			return false;
	}
}
