package com.jda.wms.stepdefs.gm;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.MoveTaskDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.MoveTaskPage;
import com.jda.wms.pages.gm.SchedulerProgramPage;
import com.jda.wms.pages.gm.StockAdjustmentsPage;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.And;

public class SchedulerProgramStepDefs {
	private MoveTaskPage moveTaskPage;
	private OrderHeaderDB orderHeaderDB;
	private OrderLineDB orderLineDB;
	private Context context;
	private Verification verification;
	private InventoryDB inventoryDB;
	private StockAdjustmentsPage stockAdjustmentsPage;
	private JDAFooter jDAFooter;
	private JdaHomePage jdaHomePage;
	private MoveTaskDB moveTaskDB;
	private SchedulerProgramPage schedulerProgramPage;

	@Inject
	public SchedulerProgramStepDefs(MoveTaskPage moveTaskPage, JDAFooter jDAFooter, 
			OrderHeaderDB orderHeaderDB, Verification verification, OrderLineDB orderLineDB, Context context,
			InventoryDB inventoryDB, MoveTaskDB moveTaskDB,SchedulerProgramPage schedulerProgramPage) {
		this.jDAFooter = jDAFooter;
		this.orderHeaderDB = orderHeaderDB;
		this.verification = verification;
		this.orderLineDB = orderLineDB;
		this.context = context;
		this.inventoryDB = inventoryDB;
		this.moveTaskDB = moveTaskDB;
		this.moveTaskPage = moveTaskPage;
		this.schedulerProgramPage=schedulerProgramPage;
	}
	@And("^I run the program$")
	public void i_run_the_program() throws Throwable {
		jDAFooter.clickQueryButton();
		schedulerProgramPage.enterProgramName();
		jDAFooter.clickExecuteButton();
		schedulerProgramPage.clickRunNowButton();
	}
}



