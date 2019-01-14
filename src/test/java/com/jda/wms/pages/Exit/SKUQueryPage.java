package com.jda.wms.pages.Exit;

import java.sql.SQLException;

import org.junit.Assert;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.db.Exit.SupplierSkuDB;




public class SKUQueryPage {
	private final SkuDB skuDB;
	private final SupplierSkuDB supplierSkuDB;
	Screen screen = new Screen();
	int timeoutInSec = 20;
	@Inject
	public SKUQueryPage(SkuDB skuDB,SupplierSkuDB supplierSkuDB){
		
		this.skuDB=skuDB;
		this.supplierSkuDB=supplierSkuDB;
	}
	
	public void enterSKU(String SKU) throws FindFailed, InterruptedException {
		Thread.sleep(2000);
		screen.wait("images/SKUMaintenanceTable/SKUID.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/SKUID.png");
		screen.type(SKU);
	}
	public void enterSupplierSKU(String SKU) throws FindFailed, InterruptedException, ClassNotFoundException {
		Thread.sleep(2000);
		screen.wait("images/JDASupplierSKU/SupplierSKU.png", timeoutInSec);
		Match mStatus=screen.find("images/JDASupplierSKU/SupplierSKU.png");
		screen.click(mStatus.getCenter().offset(70,0));
		Thread.sleep(1000);
		String supplier_ID=supplierSkuDB.getSupplierSKU(SKU);
	    Thread.sleep(1000);
		screen.type(supplier_ID);
	}
	public String getCommodityCode(String SKU) throws FindFailed, InterruptedException {
		
		screen.wait("images/SKUMaintenanceTable/settings3.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/settings3.png");
		screen.click("images/SKUMaintenanceTable/CommodityCode.png");
		Match mStatus = screen.find("images/SKUMaintenanceTable/CommodityCode.png");
		screen.click(mStatus.getCenter().offset(70,0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	
}
public String getfactoryCode() throws FindFailed, InterruptedException {
		
		
		screen.click("images/JDASupplierSKU/supplier2.png");
		Match mStatus = screen.find("images/JDASupplierSKU/supplier2.png");
		screen.click(mStatus.getCenter().offset(70,0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	
}
	
public String getCommodityCodeDesc(String SKU) throws FindFailed, InterruptedException {
		
		screen.wait("images/SKUMaintenanceTable/settings3.png.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/settings3.png.png");
		//screen.click("images/SKUMaintenanceTable/CommodityDesc.png");
		Match mStatus = screen.find("images/SKUMaintenanceTable/CommodityDesc.png");
		screen.click(mStatus.getCenter().offset(70,0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
}
public void factorycode_validation() throws FindFailed, InterruptedException
{
	String factorycode = getfactoryCode();
	System.out.println(factorycode);
	if (factorycode.length()!=6){
		System.out.println("Factory code is not 6 digit : "+factorycode);
	}
	else
	{
		System.out.println("Factory code is 6 digit :"+factorycode);
	}
	}
public void CommodityCode_Validation(String SKU) throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
	
	String CommodityCode = getCommodityCode(SKU);
	System.out.println("CommodityCode "+CommodityCode);
	String CommodityCodeDB=skuDB.getCommodityCode(SKU);
	System.out.println("CommodityCodeDB "+CommodityCodeDB);
	Assert.assertEquals("Commodity code Validated",CommodityCode,CommodityCodeDB);
}
public void packedweight_Validation(String SKU) throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
	
	String packedweight = getpackedweight(SKU);
	System.out.println("packedweight "+packedweight);
	String packedweightDB=skuDB.getpackedweight(SKU)+".0";
	System.out.println("packedweightDB "+packedweightDB);
	Assert.assertEquals("packedweight Validated",packedweight,packedweightDB);
}
public String getStroke() throws FindFailed, InterruptedException {
	screen.wait("images/SKUMaintenanceTable/UserDefined2.png", timeoutInSec);
	screen.click("images/SKUMaintenanceTable/UserDefined2.png");
	screen.click("images/SKUMaintenanceTable/stroke.png");
	Match mStatus = screen.find("images/SKUMaintenanceTable/stroke.png");
	screen.click(mStatus.getCenter().offset(70,0));
	screen.type("a", Key.CTRL);
	screen.type("c", Key.CTRL);
	Thread.sleep(2000);
	return App.getClipboard();
}
public void Stroke_Validation(String SKU) throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
	
	String Stroke = getStroke();
	System.out.println("Stroke "+Stroke);
	String StrokeDB=skuDB.getStroke(SKU);
	System.out.println("StrokeDB "+StrokeDB);
	Assert.assertEquals("Stroke Validated",Stroke,StrokeDB);
}

public String StrokeValidation(String SKU) throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
	
	String Stroke = getStroke();
	System.out.println("Stroke "+Stroke);
	String StrokeDB=skuDB.getStroke(SKU);
	System.out.println("StrokeDB "+StrokeDB);
	Assert.assertEquals("Stroke Validated",Stroke,StrokeDB);
	return StrokeDB;
}

public String getPrimarySizeDesc() throws FindFailed, InterruptedException {
	
	Match mStatus = screen.find("images/SKUMaintenanceTable/PriSizedesc.png");
	screen.click(mStatus.getCenter().offset(60,0));
	screen.type("a", Key.CTRL);
	screen.type("c", Key.CTRL);
	Thread.sleep(2000);
	return App.getClipboard();
}
public String getHandlingUnitInd() throws FindFailed, InterruptedException {
	
	Match mStatus = screen.find("images/SKUMaintenanceTable/handlingInd.png");
	screen.click(mStatus.getCenter().offset(70,0));
	screen.type("a", Key.CTRL);
	screen.type("c", Key.CTRL);
	Thread.sleep(2000);
	return App.getClipboard();
}
public String getSKUDesc() throws FindFailed, InterruptedException {
	
	screen.wait("images/SKUMaintenanceTable/UserDefined.png", timeoutInSec);
	screen.click("images/SKUMaintenanceTable/UserDefined.png");
	Match mStatus = screen.find("images/SKUMaintenanceTable/SKUDescription.png");
	screen.click(mStatus.getCenter().offset(70,0));
	screen.type("a", Key.CTRL);
	screen.type("c", Key.CTRL);
	Thread.sleep(2000);
	return App.getClipboard();
}
public String getProductGroup() throws FindFailed, InterruptedException {
	
	Match mStatus = screen.find("images/SKUMaintenanceTable/productgroup.png");
	screen.click(mStatus.getCenter().offset(70,0));
	screen.type("a", Key.CTRL);
	screen.type("c", Key.CTRL);
	Thread.sleep(2000);
	return App.getClipboard();
}
public String getpackedweight(String SKU) throws FindFailed, InterruptedException {
	
	screen.wait("images/SKUMaintenanceTable/dimensions.png", timeoutInSec);
	screen.click("images/SKUMaintenanceTable/dimensions.png");
	screen.click("images/SKUMaintenanceTable/packedweight.png");
	Match mStatus = screen.find("images/SKUMaintenanceTable/packedweight.png");
	screen.click(mStatus.getCenter().offset(70,0));
	screen.type("a", Key.CTRL);
	screen.type("c", Key.CTRL);
	Thread.sleep(2000);
	return App.getClipboard();

}
public boolean validateweight(String SKU) throws FindFailed, InterruptedException, ClassNotFoundException, SQLException{
	double x =0.0;
	double y=999;
//	String weight=getpackedweight(SKU);
	String weight=skuDB.getpackedweight(SKU);
	double packweight = Integer.parseInt(weight);
	if (packweight > x && packweight <= y)
		return true;
    else
        return false;
}

public String getsuppliersku(String SKU) throws FindFailed, InterruptedException {
	
	screen.wait("images/SupplierSKU/suppliersku.png", timeoutInSec);
	screen.click("images/SupplierSKU/suppliersku.png");
	Match mStatus = screen.find("images/SupplierSKU/suppliersku.png");
	screen.click(mStatus.getCenter().offset(70,0));
	screen.type("a", Key.CTRL);
	screen.type("c", Key.CTRL);
	Thread.sleep(2000);
	return App.getClipboard();

}
public String getcountryOrigin(String SKU) throws FindFailed, InterruptedException {
	
	screen.wait("images/SKUMaintenanceTable/CustomsAndExcise/custom.png", timeoutInSec);
	screen.click("images/SKUMaintenanceTable/CustomsAndExcise/custom.png");
	Thread.sleep(2000);
	screen.click("images/SKUMaintenanceTable/CustomsAndExcise/COO.png");
	Match mStatus = screen.find("images/SKUMaintenanceTable/CustomsAndExcise/COO.png");
	screen.click(mStatus.getCenter().offset(70,0));
	screen.type("a", Key.CTRL);
	screen.type("c", Key.CTRL);
	Thread.sleep(2000);
	return App.getClipboard();

}
public void supplierid_Validation(String SKU) throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
	
	String supplierid = getsuppliersku(SKU);
	System.out.println("supplierid "+supplierid);
	String supplierskuDB=supplierSkuDB.getSupplierSKU(SKU);
	System.out.println("supplierskuDB "+supplierskuDB);
	Assert.assertEquals("supplier validated ",supplierid,supplierskuDB);
}
public void supplierid_Validation_against_SKU(String SKU) throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
	
	String supplierSku_sku = getsuppliersku(SKU);
	System.out.println("supplierSKU from SKU: "+supplierSku_sku);
	String suppliersku_supplierSKU=supplierSkuDB.getSupplierSKU(SKU);
	System.out.println("supplierSKU from SupplierSKU:  "+suppliersku_supplierSKU);
	Assert.assertEquals("supplier validated ",supplierSku_sku,suppliersku_supplierSKU);
}
public void COO_Validation(String SKU) throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
	
	String CountryOrigin = getcountryOrigin(SKU);
	System.out.println("CountryOrigin "+CountryOrigin);
	String CountryOriginDB=skuDB.getCOO(SKU);
	System.out.println("CountryOriginDB "+CountryOriginDB);
	Assert.assertEquals("supplier validated ",CountryOrigin,CountryOriginDB);
}
public void clickSupplierSkuFromSKU() throws FindFailed, InterruptedException {
	
	screen.wait("images/SKUMaintenanceTable/supplierskutab.png", timeoutInSec);
	screen.click("images/SKUMaintenanceTable/supplierskutab.png");
	
	Thread.sleep(3000);
	Match mLocation = screen.find("images/SKUMaintenanceTable/supplierskuvalue.png");
	screen.doubleClick(mLocation.getCenter().below(15));
	
}

public void clickUserDefinedTab() throws FindFailed, InterruptedException {
	screen.wait("images/SKUMaintenanceTable/UserDefined1.png", timeoutInSec);
	screen.click("images/SKUMaintenanceTable/UserDefined1.png");
	Thread.sleep(3000);
	
}

public String getArticleDescription() throws FindFailed, InterruptedException { 
	if(screen.find("images/SKUMaintenanceTable/UserDefined/ArticleDescription.png") != null){
		screen.wait("images/SKUMaintenanceTable/UserDefined/ArticleDescription.png", timeoutInSec);
		Match mdescription= screen.find("images/SKUMaintenanceTable/UserDefined/ArticleDescription.png");
		screen.click(mdescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		
	}
	else{
		System.out.println("Description not found");
	}
	return App.getClipboard();
}


public String getDescription() throws FindFailed, InterruptedException { 
	if(screen.find("images/SKUMaintenanceTable/UserDefined/Description.png") != null){
		screen.wait("images/SKUMaintenanceTable/UserDefined/Description.png", timeoutInSec);
		Match mdescription= screen.find("images/SKUMaintenanceTable/UserDefined/Description.png");
		screen.click(mdescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		
	}
	else{
		System.out.println("Description not found");
	}
	return App.getClipboard();
}

public void click_on_Query() throws FindFailed, InterruptedException {
	screen.type(Key.F2);
	Thread.sleep(3000);
}
public void enterSku(String sku) throws InterruptedException, FindFailed{
	screen.wait("images/InventoryTransactionQuery/SkuId.png", timeoutInSec);
	Match mSku=screen.find("images/InventoryTransactionQuery/SkuId.png");
	screen.click(mSku.getCenter().offset(70,0));
	Thread.sleep(1000);
	screen.type(sku);
	
	
}
public void clickExecuteButton() throws FindFailed, InterruptedException {
	screen.type(Key.F7);
	Thread.sleep(3000);
}

public void navigateTouserDefinedTab() throws FindFailed {
	screen.wait("images/PickFaceMaintenance/UserDefinedTab.png", timeoutInSec);
	screen.click("images/PickFaceMaintenance/UserDefinedTab.png");
}

public void isUserDefChk3() throws FindFailed {
	screen.wait("images/SKUMaintenanceTable/UserDefined/UserDefChk3.png", timeoutInSec);
	screen.click("images/PickFaceMaintenance/UserDefined/UserDefChk3.png");
}

public void Job(String job) throws FindFailed, InterruptedException {
	screen.wait("images/SKUMaintenanceTable/job.png", timeoutInSec);
	Match mSku=screen.find("images/SKUMaintenanceTable/job.png");
	screen.click(mSku.getCenter().offset(70,0));
	Thread.sleep(1000);
	screen.type(job);
}

public Object status() throws FindFailed, InterruptedException {
	screen.wait("images/SKUMaintenanceTable/status.png", timeoutInSec);
	Match mSku=screen.find("images/SKUMaintenanceTable/status.png");
	screen.click(mSku.getCenter().offset(70,0));
	screen.doubleClick(mSku.getCenter().offset(70,0));
	Thread.sleep(1000);
	return mSku;
}
public void selectToggleMaintenanceMode() throws FindFailed,InterruptedException {
	Thread.sleep(3000);
	screen.rightClick();
	screen.wait("images/CEConsignmentMaintenance/ToggleMaintenanceMode.png", timeoutInSec);
	screen.click("images/CEConsignmentMaintenance/ToggleMaintenanceMode.png");
}

public void clickUpdate() throws FindFailed,InterruptedException {
	Thread.sleep(1000);
	screen.wait("images/CEConsignmentMaintenance/SkuUpdate.png", timeoutInSec);
	screen.click("images/CEConsignmentMaintenance/SkuUpdate.png");
}

public String getPutawayGroup() throws FindFailed, InterruptedException {
	
	screen.wait("images/SKUMaintenanceTable/PutawayGroup.png", timeoutInSec);
	screen.click("images/SKUMaintenanceTable/PutawayGroup.png");
	Match mStatus = screen.find("images/SKUMaintenanceTable/PutawayGroup.png");
	screen.click(mStatus.getCenter().offset(70,0));
	screen.type("a", Key.CTRL);
	screen.type("c", Key.CTRL);
	Thread.sleep(2000);
	return App.getClipboard();
}

public void enterPutaway(String putawayGroup) throws FindFailed, InterruptedException {
	
	screen.wait("images/SKUMaintenanceTable/PutawayGroup.png", timeoutInSec);
	screen.click("images/SKUMaintenanceTable/PutawayGroup.png");
	Match mStatus = screen.find("images/SKUMaintenanceTable/PutawayGroup.png");
	screen.click(mStatus.getCenter().offset(70,0));
	screen.type(putawayGroup);
}

public String getSkuID() throws FindFailed, InterruptedException {
	Thread.sleep(2000);
	screen.wait("images/SKUMaintenanceTable/SKUID.png", timeoutInSec);
	screen.click("images/SKUMaintenanceTable/SKUID.png");
	Match mSku=screen.find("images/SKUMaintenanceTable/SKUID.png");
	screen.click(mSku.getCenter().offset(70,0));
	screen.type("a", Key.CTRL);
	screen.type("c", Key.CTRL);
	Thread.sleep(2000);
	return App.getClipboard();
}

public Object isPutawayGroupNull(String putawayGp) throws FindFailed, InterruptedException {
	
	if(putawayGp.equals("BLACKB")||putawayGp.equals("BLACKH")){
		return true;
}
	else
	return false;
}

public void clickDimensions() throws FindFailed {
	screen.wait("images/SKUMaintenanceTable/dimensions.png", timeoutInSec);
	screen.click("images/SKUMaintenanceTable/dimensions.png");
}

public void updateWeight(String Weight) throws FindFailed, InterruptedException {
	
	screen.wait("images/SKUMaintenanceTable/Weight.png", timeoutInSec);
	screen.click("images/SKUMaintenanceTable/Weight.png");
	Match mStatus = screen.find("images/SKUMaintenanceTable/Weight.png");
	screen.click(mStatus.getCenter().offset(70,0));
	screen.type(Weight);
}
}


// public String getDeliveryLeadtime()

		



