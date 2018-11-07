package com.jda.wms.pages.Exit;

import org.sikuli.script.FindFailed;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.SiteDB;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.pages.Exit.JdaHomePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import java.sql.SQLException;

import org.junit.Assert;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import java.time.temporal.ValueRange;




public class SKUQueryPage {
	private final SkuDB skuDB;
	Screen screen = new Screen();
	int timeoutInSec = 20;
	@Inject
	public SKUQueryPage(SkuDB skuDB){
		
		this.skuDB=skuDB;
	}
	
	public void enterSKU(String SKU) throws FindFailed {
		screen.wait("images/SKUMaintenanceTable/SKUID.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/SKUID.png");
		screen.type(SKU);
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
}
		
		//i >= minValueInclusive && i <= maxValueInclusive

//private static void validateweight(double from, double to) throws Exception {
//    instanceCheck(to);
//    instanceCheck(from);
//
//    if ((from == 0.00 && to == 999)
//            || (isEmpty(from) && isEmpty(to))
//            || (from == 0.00 && isEmpty(to))
//            || (to == 999 && isEmpty(from))) {
//        throw new Exception("Packed Weight is not in range");
//    }
//    if (!isEmpty(from)) {
//    	validateweight(from);
//    }
//    if (!isEmpty(to)) {
//    	validateweight(to);
//    }
//}
//
//private static void instanceCheck(Object range) throws Exception {
//    if (range != getpackedweight(String SKU)
//            && !(range instanceof Number)
//            && !(range instanceof Number)) {
//        throw new Exception("Packed Weight is not in range");
//    }
//}
//
//private static boolean isEmpty(Object range) throws FindFailed, InterruptedException {
//
//    return (range instanceof String)
//            ? ((String) range).trim().isEmpty() : Boolean.FALSE;
//
//}
//
//private static void validateweight(Object weight) throws Exception {
//    //Valid date
//    if (weight instanceof String) {
//        String input = (String) weight;
//        //Empty check 
//        if (!input.matches("Range should be from 0.01 to 999")) {
//            throw new Exception("Not a valid date");
//        }
//    }





