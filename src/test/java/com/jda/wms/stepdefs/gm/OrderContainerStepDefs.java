package com.jda.wms.stepdefs.gm;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.OrderContainerDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.OrderContainerPage;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Then;

public class OrderContainerStepDefs {
	private Context context;
	private Verification verification;
	private OrderContainerPage orderContainerPage;
	private JDAFooter jDAFooter;
	private JdaLoginPage jdaLoginPage;
	private JDAHomeStepDefs jDAHomeStepDefs;
	private JdaHomePage jdaHomePage;
	private OrderContainerDB orderContainerDB;

	@Inject

	public OrderContainerStepDefs(Context context, JDAHomeStepDefs jDAHomeStepDefs, JdaLoginPage jdaLoginPage,
			Verification verification, OrderContainerPage orderContainerPage, JDAFooter jDAFooter,
			OrderContainerDB orderContainerDB) {
		this.context = context;
		this.verification = verification;
		this.orderContainerPage = orderContainerPage;
		this.orderContainerDB = orderContainerDB;
		this.jdaLoginPage = jdaLoginPage;
		this.jDAHomeStepDefs = jDAHomeStepDefs;
		this.jdaHomePage = jdaHomePage;
		this.jDAFooter = jDAFooter;
	}

	@Then("^the container id should be generated$")
	public void the_container_id_should_be_generated() throws Throwable {
		jdaLoginPage.login();
		jDAHomeStepDefs.i_navigate_to_order_container_page();
		jDAFooter.clickQueryButton();
		orderContainerPage.enterOrderId(context.getOrderId());
		jDAFooter.clickExecuteButton();
		orderContainerDB.getContainerId(context.getOrderId());

	}

}
