package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.MoveTaskDB;
import com.jda.wms.db.gm.OrderLineDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.MoveTaskManagementPage;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Then;
import edu.emory.mathcs.backport.java.util.Arrays;

public class MoveTaskManagementStepDefs {
	ArrayList<String> failureList = new ArrayList<String>();
	private Context context;
	private Verification verification;
	private OrderLineDB orderLineDB;
	private MoveTaskDB moveTaskDB;
	private MoveTaskManagementPage moveTaskManagementPage;
	private JDAFooter jDAFooter;

	@Inject
	public MoveTaskManagementStepDefs(Context context, OrderLineDB orderLineDB, Verification verification,
			MoveTaskDB moveTaskDB,MoveTaskManagementPage moveTaskManagementPage,JDAFooter jDAFooter) {
		this.context = context;
		this.orderLineDB = orderLineDB;
		this.verification = verification;
		this.moveTaskDB = moveTaskDB;
		this.moveTaskManagementPage = moveTaskManagementPage;
		this.jDAFooter= jDAFooter;
	}

	@Then("^the list Id should be generated in move task management$")
	public void the_list_Id_should_be_generated_in_move_task_management() throws Throwable {
		ArrayList skuList = (ArrayList) context.getSkuList();
		for (int i = 1; i <= context.getNoOfLines(); i++) {
			context.setSkuId((String) skuList.get(i - 1));
			
			//To verify List IDs
			context.setNoOfMoveTaskRecords(moveTaskDB.getMoveTaskRecordCountBySkuID(context.getOrderId(), context.getSkuId()));
			ArrayList listIDList = moveTaskDB.getListIDList(context.getOrderId(), context.getSkuId());

			if (context.getNoOfMoveTaskRecords()==0){
				failureList.add("No move task records generated for SKU "+context.getSkuId());
			}
			if ((context.getNoOfMoveTaskRecords()!=listIDList.size())&&context.getNoOfMoveTaskRecords()!=0){
			failureList.add("List IDs are not generated for SKU "+context.getSkuId()+". Expected count "+context.getNoOfMoveTaskRecords()+ "Actual count "+listIDList.size());
			}
			else{
				int qtyToMove=0,sum=0;
				for (int j = 1; j <= listIDList.size(); j++){
				qtyToMove = Integer.parseInt(moveTaskDB.getQtyTasked(context.getOrderId(),context.getSkuId(),(String)listIDList.get(j-1)));
				sum+=qtyToMove;
				}
				verification.verifyData("Qty To Move for SKU "+context.getSkuId(), String.valueOf(context.getQtyTaskedList().get(i-1)), String.valueOf(sum),
						failureList);
			}
		}
		Assert.assertTrue("List Id details are not displayed as expected. [" + Arrays.asList(failureList.toArray()) + "].",
				failureList.isEmpty());
}
	
	@Then("^update the status in move task$")
	public void update_the_status_in_move_task() throws Throwable {
		moveTaskDB.updateStatus(context.getOrderId());
	}
	
	@Then("^I delete the order$")
	public void i_delete_the_order_and_list_id_in_move_task() throws Throwable {
		moveTaskManagementPage.clickAvailable();
		jDAFooter.clickNextButton();
		moveTaskManagementPage.enterTaskId(context.getOrderId());
		jDAFooter.clickNextButton();
		moveTaskManagementPage.clickWorkZone();
		moveTaskManagementPage.deleteRecords();
	}
}
