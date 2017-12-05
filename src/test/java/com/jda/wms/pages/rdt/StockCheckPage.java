package com.jda.wms.pages.rdt;

import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;


public class StockCheckPage {
Screen screen = new Screen();

public void selectInventoryMenu() throws InterruptedException {
		Thread.sleep(1000);	
		screen.type("5");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}
public void selectNewStockCheckMenu() throws InterruptedException {
	Thread.sleep(1000);	
	screen.type("2");
	Thread.sleep(1000);
	screen.type(Key.ENTER);
	Thread.sleep(2000);
}
public void selectExistingStockCheckMenu() throws InterruptedException {
	Thread.sleep(1000);	
	screen.type("1");
	Thread.sleep(1000);
	screen.type(Key.ENTER);
	Thread.sleep(2000);
}


public void enterLocation(String Location) throws InterruptedException {
	screen.type(Location);
	Thread.sleep(1000);
	screen.type(Key.ENTER);
	Thread.sleep(5000);
}
public void enterCheckString(String CheckString) throws InterruptedException {
	screen.type(CheckString);
	Thread.sleep(1000);
	screen.type(Key.ENTER);
	Thread.sleep(5000);
}	
public void enterQty(String QTY) throws InterruptedException {
	screen.type(QTY);
	Thread.sleep(1000);
	screen.type(Key.ENTER);
	Thread.sleep(5000);
}
public boolean checkStock() throws FindFailed, InterruptedException {
	try {
		System.out.println("entered check stock ");
	if (screen.exists("images/Putty/StockCheck/NewStockCheck/StkCheckerrScreen.png", 10) != null){
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		return true;}
	else
		
		return false;
	}catch(Exception e){e.printStackTrace();return false;
	}
}

public boolean existingStockError() throws FindFailed, InterruptedException {
	try {
		System.out.println("enetered to check existingStockError");
	if (screen.exists("images/Putty/StockCheck/NewStockCheck/StcExistingerror.png", 10) != null){
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		return true;}
	else
		
		return false;
	}catch(Exception e){return false;
	}
}

public boolean checkCheckString() throws FindFailed, InterruptedException {
	try {
		System.out.println("enetered to check existingStockError");
	if (screen.exists("images/Putty/StockCheck/NewStockCheck/checkStringinpicking.png", 10) != null){
		Thread.sleep(1000);
		
		return true;}
	else
		
		return false;
	}catch(Exception e){return false;
	}
}
	public void backmenu() throws InterruptedException {	
	Thread.sleep(1000);
	screen.type(Key.F12);
	Thread.sleep(1000);
}

}
