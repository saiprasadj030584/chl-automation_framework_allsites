package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class DeliveryManagementPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private JDAFooter jdaFooter;
	public void enterAsnId(String asnId) throws InterruptedException, FindFailed {
		screen.type(asnId);
		Thread.sleep(1000);
		jdaFooter.clickNextButton();
		
		//RECORD EXIST IMAGE
		Match mStatus = screen.find("images/StockAdjustment/CreateModifySKUID/SASKUId.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		
		//UPDATE STATUS BUTTON IMAGE
		if (screen.exists("images/StockAdjustment/Results/ResultsRecord.png") != null)
		{
			Match mStatus1 = screen.find("images/StockAdjustment/Results/ResultsRecord.png");
			screen.click(mStatus1.getCenter().offset(70, 0));
		}
		Thread.sleep(3000);
		
		
	}
	public void ChooseDeliveryStatus() throws InterruptedException, FindFailed {
		//SCROLL DOWN IMAGE FOR COMPLETE
		Match mStatus = screen.find("images/StockAdjustment/CreateModifySKUID/SASKUId.png");
		screen.click(mStatus.getCenter().offset(70, 0));
		Thread.sleep(2000);
		screen.type("Complete");
		jdaFooter.PressEnter();
		
		//OK BUTTON IMAGE
		Match mStatus1 = screen.find("images/StockAdjustment/CreateModifySKUID/SASKUId.png");
		screen.click(mStatus1.getCenter().offset(70, 0));
		Thread.sleep(2000);
		jdaFooter.PressEnter();
		
	}
	

}
