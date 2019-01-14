package com.jda.wms.pages.Exit;

import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.dataload.exit.GetTCData;
import com.jda.wms.db.Exit.OrderHeaderDB;
import com.jda.wms.stepdefs.Exit.JDAFooter;

import org.junit.Assert;

public class InventoryTransactionPage{
	private Context context;
	private GetTCData getTCData;
	private JdaHomePage jdaHomePage;
	private JDAFooter jdaFooter;
	private OrderHeaderDB orderHeaderDB;
	private OrderHeaderPage orderheaderpage;
	static Screen screen = new Screen();
	int timeoutInSec = 20;
	@Inject
	public InventoryTransactionPage(Context context,GetTCData getTCData,JdaHomePage jdaHomePage,
			JDAFooter jdaFooter,OrderHeaderDB orderHeaderDB,OrderHeaderPage orderheaderpage){
		this.context=context;
		this.getTCData=getTCData;
		this.jdaHomePage=jdaHomePage;
		this.jdaFooter=jdaFooter;
		this.orderHeaderDB=orderHeaderDB;
		this.orderheaderpage=orderheaderpage;
		
	}
	public void EnterContanierID() throws FindFailed{
		screen.wait("images/InventoryTransactionQuery/MiscellaneousTab.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/MiscellaneousTab.png");
		screen.wait("images/InventoryTransactionQuery/Container.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/Container.png");
		String palletIDforUPI = context.getpalletIDforUPI();
		screen.type(palletIDforUPI);
		
		
	}
	public void EnterContanierIDFSV() throws FindFailed{
		screen.wait("images/InventoryTransactionQuery/MiscellaneousTab.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/MiscellaneousTab.png");
		screen.wait("images/InventoryTransactionQuery/Container.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/Container.png");
		String palletID = context.getPalletID();
		screen.type(palletID);
		
		
	}
	public void Checktransaction() throws FindFailed, InterruptedException{
		screen.wait("images/InventoryTransactionQuery/Container.png", timeoutInSec);
		Match mLocation = screen.find("images/InventoryTransactionQuery/Container.png");
		screen.doubleClick(mLocation.getCenter().below(15));
		Thread.sleep(2000);
		if(screen.find("images/InventoryTransactionQuery/Transactioncode.png").equals(null)){
			System.out.println("Inventory not found");
		}
		else{
			System.out.println("Inventory for Receipt,Allocate, Pick found");
		}}
	public void ChecktransactionforUnpick() throws FindFailed, InterruptedException{
		screen.wait("images/InventoryTransactionQuery/Container.png", timeoutInSec);
		Match mLocation = screen.find("images/InventoryTransactionQuery/Container.png");
		screen.doubleClick(mLocation.getCenter().below(15));
		Thread.sleep(2000);
		if(screen.find("images/InventoryTransactionQuery/General/UnpickITL.png.png").equals(null)){
			System.out.println("Inventory not found");
		}
		else{
			System.out.println("Inventory for Receipt,Allocate, Pick found");
		}}
	public void ChecktransactionForRedStock() throws FindFailed, InterruptedException{
		screen.wait("images/InventoryTransactionQuery/Container.png", timeoutInSec);
		Match mLocation = screen.find("images/InventoryTransactionQuery/Container.png");
		screen.doubleClick(mLocation.getCenter().below(15));
		Thread.sleep(2000);
		if(screen.find("images/InventoryTransactionQuery/TransactioncodeforRedStock.png").equals(null)){
			System.out.println("Inventory not found");
		}
		else{
			System.out.println("Inventory for Receipt,InventoryLock and putaway");
		}}
	
		public void CheckQtyreceived() throws FindFailed, InterruptedException{
			screen.wait("images/InventoryTransactionQuery/Pick/pick1.png", timeoutInSec);
			screen.click("images/InventoryTransactionQuery/Pick/pick1.png");
			Match mLocation = screen.find("images/InventoryTransactionQuery/Pick/pick.png");
			screen.doubleClick(mLocation.getCenter().below(15));
			Thread.sleep(2000);
			screen.wait("images/InventoryTransactionQuery/Pick/General.png", timeoutInSec);
			screen.click("images/InventoryTransactionQuery/Pick/General.png");
			Thread.sleep(2000);
			screen.wait("images/InventoryTransactionQuery/Pick/General.png", timeoutInSec);
			screen.click("images/InventoryTransactionQuery/Pick/General.png");
			Thread.sleep(2000);
			String OriginalQty = getoriginalqty();
			System.out.println("Original Qty : "+OriginalQty);
			String UpdatedQty = getupdatedqty();
			System.out.println("UpdatedQty : "+UpdatedQty);
			Assert.assertEquals("Quantity Validated",OriginalQty,UpdatedQty);
			
		}
		public String getoriginalqty() throws FindFailed, InterruptedException {
			
			screen.wait("images/InventoryTransactionQuery/General/OriginalQty.png", timeoutInSec);
			screen.click("images/InventoryTransactionQuery/General/OriginalQty.png");
			Match mStatus = screen.find("images/InventoryTransactionQuery/General/OriginalQty.png");
			screen.click(mStatus.getCenter().offset(70,0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);
			Thread.sleep(2000);
			return App.getClipboard();

		}
public String getQuantityOnHand() throws FindFailed, InterruptedException {
			
			screen.wait("images/InventoryTransactionQuery/QtyOnHand.png", timeoutInSec);
			screen.click("images/InventoryTransactionQuery/QtyOnHand.png");
			Match mStatus = screen.find("images/InventoryTransactionQuery/QtyOnHand.png");
			screen.click(mStatus.getCenter().offset(70,0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);
			Thread.sleep(2000);
			return App.getClipboard();

		}
       public String getupdatedqty() throws FindFailed, InterruptedException {
			
			screen.wait("images/InventoryTransactionQuery/Pick/UpdatedQty.png", timeoutInSec);
			screen.click("images/InventoryTransactionQuery/Pick/UpdatedQty.png");
			Match mStatus = screen.find("images/InventoryTransactionQuery/Pick/UpdatedQty.png");
			screen.click(mStatus.getCenter().offset(70,0));
			screen.type("a", Key.CTRL);
			screen.type("c", Key.CTRL);
			Thread.sleep(2000);
			return App.getClipboard();

		}
       public void getstatus() throws InterruptedException, FindFailed, ClassNotFoundException, SQLException
       {
    	   String orderID = getTCData.getSto();
    		System.out.println("New Order ID : " + orderID);
    		Thread.sleep(10000);
    		jdaHomePage.navigateToOrderheaderPage();
    		Thread.sleep(3000);
    		jdaFooter.clickQueryButton();
    		orderheaderpage.enterOrderNo(context.getOrderId());
    		jdaFooter.clickNextButton();
    		Thread.sleep(2000);
    		String orderstatus=orderHeaderDB.getStatus(context.getOrderId());
    		Assert.assertEquals("Order Status", "Ready to Load", orderstatus);
       }
	public void enterSku() throws InterruptedException, FindFailed{
		screen.wait("images/InventoryTransactionQuery/SkuId.png", timeoutInSec);
		Match mSku=screen.find("images/InventoryTransactionQuery/SkuId.png");
		screen.click(mSku.getCenter().offset(70,0));
		Thread.sleep(1000);
		String sku = context.getSkuId2();
		screen.type(sku);
		
		
	}
	public void clickExecuteButton() throws FindFailed, InterruptedException {
		screen.type(Key.F7);
		Thread.sleep(3000);
	}
	public void click_on_Query() throws FindFailed, InterruptedException {
		screen.type(Key.F2);
		Thread.sleep(1000);
	}
	public String checkLockcode() throws FindFailed, InterruptedException {
		
		screen.wait("images/InventoryTransactionQuery/lockCode.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/lockCode.png");
		Match mStatus = screen.find("images/InventoryTransactionQuery/lockCode.png");
		screen.click(mStatus.getCenter().offset(70,0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	}
public String checkReasoncode() throws FindFailed, InterruptedException {
		
		screen.wait("images/InventoryTransactionQuery/ReasonCode.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/ReasonCode.png");
		Match mStatus = screen.find("images/InventoryTransactionQuery/ReasonCode.png");
		screen.click(mStatus.getCenter().offset(70,0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	}
	public void clickMiscellaneousTab() throws FindFailed, InterruptedException {
		
		screen.wait("images/InventoryTransactionQuery/MiscTab.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/MiscTab.png");
		Thread.sleep(1000);
	}
	public void enterLocation(String location) throws FindFailed, InterruptedException {
		
		screen.wait("images/InventoryQuery/LocationID.png", timeoutInSec);
		screen.click("images/InventoryQuery/LocationID.png");
		Match mStatus = screen.find("images/InventoryQuery/LocationID.png");
		screen.click(mStatus.getCenter().offset(70,0));
		screen.type(location);
	}
	public String getSku()throws FindFailed, InterruptedException {
		
		screen.wait("images/InventoryQuery/Sku.png", timeoutInSec);
		screen.click("images/InventoryQuery/Sku.png");
		Match mStatus = screen.find("images/InventoryTransactionQuery/Sku.png");
		screen.click(mStatus.getCenter().offset(70,0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	}
public String getTagId()throws FindFailed, InterruptedException {
		
		screen.wait("images/InventoryTransactionQuery/TagID.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/TagID.png");
		Match mStatus = screen.find("images/InventoryTransactionQuery/TagID.png");
		screen.click(mStatus.getCenter().offset(70,0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	}
public Object getWorkZone() throws FindFailed, InterruptedException {
	
	screen.wait("images/InventoryTransactionQuery/WorkZone.png", timeoutInSec);
	screen.click("images/InventoryTransactionQuery/WorkZone.png");
	Match mStatus = screen.find("images/InventoryTransactionQuery/WorkZone.png");
	screen.click(mStatus.getCenter().offset(70,0));
	screen.type("a", Key.CTRL);
	screen.type("c", Key.CTRL);
	Thread.sleep(2000);
	return App.getClipboard();
}
public void clickNextButton()throws FindFailed, InterruptedException {
	screen.type(Key.F7);
	Thread.sleep(3000);
}
public void enterSku(String skuId)  throws InterruptedException, FindFailed{
	screen.wait("images/InventoryTransactionQuery/SkuId.png", timeoutInSec);
	Match mSku=screen.find("images/InventoryTransactionQuery/SkuId.png");
	screen.click(mSku.getCenter().offset(70,0));
	Thread.sleep(1000);
	
	screen.type(skuId);
	
	
}
}

     
		
