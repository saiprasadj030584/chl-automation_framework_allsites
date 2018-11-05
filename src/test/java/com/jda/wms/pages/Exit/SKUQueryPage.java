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




public class SKUQueryPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	
	public void enterSKU(String SKU) throws FindFailed {
		screen.wait("images/SKUMaintenanceTable/SKUID.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/SKUID.png");
		screen.type(SKU);
	}
	public String getCommodityCode(String SKU) throws FindFailed, InterruptedException {
		
		screen.wait("images/SKUMaintenanceTable/settings3.png", timeoutInSec);
		screen.click("images/SKUMaintenanceTable/settings3.png");
		//screen.click("images/SKUMaintenanceTable/CommodityCode.png");
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
/*public void CommodityCode_Validation(String SKU) throws FindFailed, InterruptedException, ClassNotFoundException, SQLException {
	
	String CommodityCode = getCommodityCode(SKU);
	System.out.println("CommodityCode "+CommodityCode);
	String CommodityCodeDB=skuDB.getCommodityCode(SKU);
	System.out.println("CommodityCodeDB "+CommodityCodeDB);
	Assert.assertEquals("Commodity code Validated",CommodityCode,CommodityCodeDB);
}*/
public String getStroke() throws FindFailed, InterruptedException {
	
	Match mStatus = screen.find("images/SKUMaintenanceTable/stroke.png");
	screen.click(mStatus.getCenter().offset(70,0));
	screen.type("a", Key.CTRL);
	screen.type("c", Key.CTRL);
	Thread.sleep(2000);
	return App.getClipboard();
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
}
