package com.jda.wms.pages.Exit;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.context.OrderHeaderContext;
import com.jda.wms.dataload.exit.DataSetupRunner;
import com.jda.wms.dataload.exit.DeleteDataFromDB;
import com.jda.wms.dataload.exit.GetTCData;
import com.jda.wms.dataload.exit.InsertDataIntoDB;
import com.jda.wms.dataload.exit.SelectDataFromDB;
import com.jda.wms.dataload.exit.UpdateDataFromDB;
import com.jda.wms.db.Exit.AddressDB;
import com.jda.wms.db.Exit.InventoryDB;
import com.jda.wms.db.Exit.MoveTaskDB;
import com.jda.wms.db.Exit.OrderHeaderDB;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.pages.Exit.AddressMaintenancePage;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.MoveTaskListGenerationPage;
import com.jda.wms.pages.Exit.MoveTaskManagementPage;
import com.jda.wms.pages.Exit.MoveTaskUpdatePage;
import com.jda.wms.pages.Exit.OrderHeaderMaintenancePage;
import com.jda.wms.pages.Exit.PuttyFunctionsPage;
import com.jda.wms.pages.Exit.SystemAllocationPage;
import com.jda.wms.pages.Exit.Verification;
import com.jda.wms.stepdefs.Exit.JDAFooter;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class JDAExitUPIheaderPage{
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private JDAFooter jdaFooter;
	private JdaHomePage imageCheckFunction;
	private Context context;
	
	@Inject
	public JDAExitUPIheaderPage(JDAFooter jdaFooter, JdaHomePage imageCheckFunction,Context context) {
		this.jdaFooter = jdaFooter;
		this.imageCheckFunction = imageCheckFunction;
		this.context=context;
	}
	
	public void EnterPalletID() throws FindFailed, InterruptedException{
		jdaFooter.clickQueryButton();
		Thread.sleep(1000);
		Match mTaskId = screen.find("images/UpiReceiptHeader/pallet.png");
		screen.click(mTaskId.getCenter().offset(70, 0));
		String palletIDforUPI = context.getpalletIDforUPI();
		screen.type(palletIDforUPI);
		Thread.sleep(1000);
		jdaFooter.clickExecuteButton();
		Thread.sleep(1000);
	}
		
	}


