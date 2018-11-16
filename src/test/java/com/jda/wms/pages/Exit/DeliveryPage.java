package com.jda.wms.pages.Exit;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.stepdefs.Exit.JDAFooter;

public class DeliveryPage{
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private JDAFooter jdaFooter;
	private JdaHomePage imageCheckFunction;
	private Context context;
	
	
	
	@Inject
	public void DeliveryPage(JDAFooter jdaFooter,Context context){
		this.jdaFooter=jdaFooter;
		this.context=context;
		
	}
	public void EnterASNID() throws FindFailed, InterruptedException{
		jdaFooter.clickQueryButton();
		Thread.sleep(1000);
		Match mTaskId = screen.find("images/DeliveryManagement/Asn_id.png");
		screen.click(mTaskId.getCenter().offset(70, 0));
		String ASNID = context.getASN();
		screen.type(ASNID);
		Thread.sleep(1000);
		jdaFooter.clickExecuteButton();
		Thread.sleep(1000);
	}
	
	public void ASN_Validation() throws FindFailed{
		
		String supplier_reference=getSuppleir_reference();
		 Assert.assertNotEquals("", supplier_reference);
		
	}
	
	public String getSuppleir_reference() throws FindFailed{
		Match mTaskId = screen.find("images/DeliveryManagement/supplier_reference.png");
		screen.click(mTaskId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
	public String getSuppleir() throws FindFailed{
		Match mTaskId = screen.find("images/DeliveryManagement/supplier_id.png");
		screen.click(mTaskId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
    public void Supplier_Validation() throws FindFailed{
		
		String supplier_id=getSuppleir();
		 Assert.assertNotEquals("", supplier_id);
		
	}
    


}