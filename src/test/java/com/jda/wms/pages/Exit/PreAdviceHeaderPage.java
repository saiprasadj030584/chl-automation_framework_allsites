package com.jda.wms.pages.Exit;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.dataload.exit.GetTCData;
import com.jda.wms.db.Exit.Database;
import com.jda.wms.db.Exit.PreAdviceHeaderDB;
import com.jda.wms.db.Exit.PreAdviceLineDB;
import com.jda.wms.stepdefs.Exit.JDAFooter;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;




public class PreAdviceHeaderPage{
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private Context context;
	private JDAFooter jdaFooter;
	private GetTCData getTCData;
	@Inject
	public void PreAdviceHeaderPage(JDAFooter jdaFooter,GetTCData getTCData){
		this.jdaFooter=jdaFooter;
		this.getTCData=getTCData;
		
	}
		
	public void enterPreAdviceID(String preAdviceId) throws FindFailed {
		screen.type(preAdviceId);
		
	}
	public void Enterpreadvice() throws FindFailed, InterruptedException{
		jdaFooter.clickQueryButton();
		Thread.sleep(1000);
		Match mTaskId = screen.find("images/PreAdviceHeader/PreAdviceId.png");
		screen.click(mTaskId.getCenter().offset(70, 0));
		String Preadvice=getTCData.getpoId();
		screen.type(Preadvice);
		Thread.sleep(1000);
		jdaFooter.clickExecuteButton();
		Thread.sleep(1000);
	}
	public void Enterpreadvice2() throws FindFailed, InterruptedException{
		
		jdaFooter.clickQueryButton();
		Thread.sleep(1000);
		Match mTaskId = screen.find("images/PreAdviceHeader/PreAdviceId.png");
		screen.click(mTaskId.getCenter().offset(70, 0));
		String Preadvice2=getTCData.getpoId2();
		screen.type(Preadvice2);
		Thread.sleep(1000);
		jdaFooter.clickExecuteButton();
		Thread.sleep(1000);
	}
	public String getStatus() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceHeader/Status.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	 public void validation_of_Status() throws FindFailed, InterruptedException
	    {   
	    	String Status=getStatus();
	    	Assert.assertEquals("Status Validated Successfully","Complete",Status);
	    	System.out.println("PO Status"+Status);
	    }
	
	public String getpreadviceline() throws FindFailed, InterruptedException{
		/*screen.wait("images/PreAdviceHeader/lines.png", timeoutInSec);
	    screen.click("images/PreAdviceHeader/lines.png");*/
	    Thread.sleep(3000);
		Match mDescription = screen.find("images/PreAdviceLine/PreAdviceId.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
		
	}
	public void clickPreadviceLine() throws FindFailed, InterruptedException{
		screen.wait("images/PreAdviceHeader/lines.png", timeoutInSec);
	    screen.click("images/PreAdviceHeader/lines.png");
	    Thread.sleep(2000);
		
	}
	
	public String getsupplier() throws FindFailed, InterruptedException{
		
	    
		Match mDescription = screen.find("images/PreAdviceHeader/Supplier.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
		
	}
	public String getquantity() throws FindFailed{
		Match mDescription = screen.find("images/PreAdviceLine/QtyRcvd.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
		
	}
    public void validation_of_supplier() throws FindFailed, InterruptedException
    {   
    	String supplier=getsupplier();
    	Assert.assertNotEquals("",supplier);
    }
    public void validation_of_quantity() throws FindFailed
    {
    	String quantity=getquantity();
    	Assert.assertNotEquals("",quantity);
    }
    public void validation_of_PreAdviceLine() throws FindFailed, InterruptedException
    {
    	String PreAdviceLine=getpreadviceline();
    	Assert.assertNotEquals("",PreAdviceLine);
    }
	public String getAdviceNo() throws FindFailed, InterruptedException {
		Match mDescription = screen.find("images/PreAdviceLine/preAdviceId.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	 public void validation_of_advice() throws FindFailed, InterruptedException
	    {
	    	String AdviceNo=getAdviceNo();
	    	Assert.assertNotEquals("",AdviceNo);
	    }
 
 
	public String getDuedate() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceHeader/DueDate.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getSiteId() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceHeader/SiteID.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	
	public boolean isTypeExist() throws FindFailed {
		if (!screen.find("images/PreAdviceHeader/Type.png").equals(null)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getOrderType() throws FindFailed {
		Match mOrderType = screen.find("images/PreAdviceHeader/Type.png");
		screen.click(mOrderType.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	

	public String getSupplier() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceHeader/Supplier.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getNumberOfLines() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceHeader/NumberOfLines.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public void clickAddressTab() throws FindFailed, InterruptedException {
		screen.wait("images/PreAdviceHeader/address/Address.png", timeoutInSec);
		screen.click("images/PreAdviceHeader/address/Address.png");
		Thread.sleep(2000);
	}

	public String getName() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceHeader/address/Name.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getAddress1() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceHeader/address/Address1.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getCountry() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceHeader/address/Country.png");
		screen.click(mDescription.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}

	public String getConsignmentID() throws FindFailed {
		Match mConsignmentID = screen.find("images/PreAdviceHeader/ConsignmentID.png");
		screen.click(mConsignmentID.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	
	public void quantity_validation() throws FindFailed
	
	{
		String Quantity = getquantity();
		Assert.assertNotEquals("",Quantity);
	}
	
	
	
	
	
	


	
}
