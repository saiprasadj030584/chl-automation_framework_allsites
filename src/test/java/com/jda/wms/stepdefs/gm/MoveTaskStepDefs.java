package com.jda.wms.stepdefs.gm;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.InventoryDB;
import com.jda.wms.db.gm.MoveTaskDB;
import com.jda.wms.db.gm.OrderHeaderDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.pages.gm.AllocationPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.MoveTaskPage;
import com.jda.wms.pages.gm.StockAdjustmentsPage;
import com.jda.wms.pages.gm.Verification;
import com.jda.wms.utils.DateUtils;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MoveTaskStepDefs {
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

	@Inject
	public MoveTaskStepDefs(MoveTaskPage moveTaskPage, JDAFooter jDAFooter, AllocationPage allocationPage,
			OrderHeaderDB orderHeaderDB, Verification verification, OrderLineDB orderLineDB, Context context,
			InventoryDB inventoryDB, MoveTaskDB moveTaskDB) {
		this.jDAFooter = jDAFooter;
		this.orderHeaderDB = orderHeaderDB;
		this.verification = verification;
		this.orderLineDB = orderLineDB;
		this.context = context;
		this.inventoryDB = inventoryDB;
		this.moveTaskDB = moveTaskDB;
	}

	@Then("^the move task should be updated$")
	public void the_move_task_should_be_updated() throws Throwable {
		String Date = DateUtils.getCurrentSystemDateInDBFormat();
		Assert.assertEquals("updated move task task type are not as expected", "M", moveTaskDB.getTaskType(Date));
	}

	@When("^I enter OrderID in move task$")
	public void i_enter_OrderID_in_move_task() throws Throwable {
		jDAFooter.clickQueryButton();
		System.out.println("VALUE OF ORDERID =" + context.getOrderId());
		moveTaskPage.enterOrderId(context.getOrderId());
		System.out.println("VALUE OF ORDERID =" + context.getOrderId());
		jDAFooter.clickExecuteButton();
	}

	@Then("^the list Id should be generated$")
	public void the_list_Id_should_be_generated() throws Throwable {
		moveTaskPage.getListId();
	}

}
