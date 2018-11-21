package com.jda.wms.pages.Exit;

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

public class InventoryTransactionPage{
	private Context context;
	static Screen screen = new Screen();
	int timeoutInSec = 20;
	@Inject
	public void InventoryTransactionPage(Context context){
		this.context=context;
		
	}
	public void EnterContanierID() throws FindFailed{
		screen.wait("images/InventoryTransactionQuery/MiscellaneousTab.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/MiscellaneousTab.png");
		screen.wait("images/InventoryTransactionQuery/Container.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/Container.png");
		String palletIDforUPI = context.getpalletIDforUPI();
		screen.type(palletIDforUPI);
		
		
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
		}
		
	}
}