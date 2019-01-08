package com.jda.wms.pages.Exit;

import org.junit.Assert;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.utils.Utilities;

public class TrailerMaintenancePage {
	Screen screen = new Screen();
	private final JDAFooter jdaFooter;
	private Context context;
	@Inject
	public TrailerMaintenancePage(JDAFooter jdaFooter, Context context) {
		this.jdaFooter = jdaFooter;
		this.context = context;
	}
	int timeoutInSec = 20;
	Region reg = new Region(0, 0, 4000, 1000);
	public void enterTrailerNo(String trailerNo) throws FindFailed, InterruptedException {
		screen.type(trailerNo);
		screen.type(Key.TAB);
		Thread.sleep(1000);
	}

	public void enterTrailerType() throws FindFailed, InterruptedException {
		screen.type("Trailer");
		Thread.sleep(2000);
	}
	public void enterTrailerNumber() throws FindFailed, InterruptedException {
		String Random = Utilities.getSixDigitRandomNumber();
		String trailerNumber = "3"+Random;
		//context.setTrailerNumber(trailerNumber);
		System.out.println("Trailer Number"+trailerNumber);
		screen.type(trailerNumber);
		context.setTrailerNumber(trailerNumber);
		Thread.sleep(1000);
	}
	public void enterSealId() throws FindFailed, InterruptedException {
		String Random = Utilities.getFourDigitRandomNumber();
		String trailerNumber = "SEAL"+Random;
		//context.setTrailerNumber(trailerNumber);
		System.out.println("Trailer Number"+trailerNumber);
		screen.type(trailerNumber);
		Thread.sleep(1000);
	}
	public void validateErrorMsg() throws FindFailed, InterruptedException {
		 Match header = screen.find("images/TrailerShipping/TrailerLinkError.png");
		 while(header==null){
			 Assert.assertTrue("No Pallets foundh",true);
		 }
	}
	
	public void selectTrailerType() throws FindFailed, InterruptedException {
		screen.wait("images/TrailerShipping/TrailerType.png", timeoutInSec);
	    Match status = screen.find("images/TrailerShipping/TrailerType.png");
		screen.click(status.getCenter().offset(90, 0));
		Thread.sleep(1000);
		 Match header = screen.find("images/TrailerShipping/TrailerTypeRoad.png");
		   reg=header.below(150).left(5).right(1000);
		   reg.hover(header);
		   reg.click(header);
	}
	public void selectTrailer() throws FindFailed, InterruptedException {
		String Trailer = context.getTrailerNumber();
		Thread.sleep(1000);
		screen.type(Trailer);
	}
	public void validateTrailerLinked() throws FindFailed, InterruptedException {
	 Match header = screen.find("images/TrailerShipping/TrailerLink.png");
	if(header!=null){
		 Assert.assertTrue("Trailer linked",true);
		 
	 }
	else{
		 Assert.assertFalse("Trailer not linked",false);
		 
	 }
}
	public void clickTrailerAdd() throws FindFailed,InterruptedException {
		Thread.sleep(1000);
		screen.wait("images/TrailerShipping/TrailerAddButton.png", timeoutInSec);
		screen.click("images/TrailerShipping/TrailerAddButton.png");
}
	public void clickTrailerShip() throws FindFailed,InterruptedException {
		Thread.sleep(1000);
		screen.wait("images/TrailerShipping/TrailerShip.png", timeoutInSec);
		Match status = screen.find("images/TrailerShipping/TrailerShip.png");
		screen.click(status.getCenter().offset(90, 0));
		Thread.sleep(1000);
		
}
}
