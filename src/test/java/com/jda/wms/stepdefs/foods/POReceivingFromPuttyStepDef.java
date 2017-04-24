package com.jda.wms.stepdefs.foods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.POReceivingFromPuttyPage;
import com.jda.wms.pages.foods.StockAdjustmentsPage;

import cucumber.api.java.en.*;

public class POReceivingFromPuttyStepDef {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private POReceivingFromPuttyPage poReceivingFromPuttyPage;
	
	@Inject
	public POReceivingFromPuttyStepDef(POReceivingFromPuttyPage poReceivingFromPuttyPage) {
		this.poReceivingFromPuttyPage = poReceivingFromPuttyPage;
	}
	
	@Given("^I have logged in as warehouse user in Putty with host \"([^\"]*)\" and port \"([^\"]*)\"$")
	public void i_have_logged_in_as_warehouse_user_in_Putty_with_host_and_port(String host, String port) throws Throwable {
		poReceivingFromPuttyPage.invokePutty();
		poReceivingFromPuttyPage.loginPutty(host,port);
	}

	@When("^I select the user directed receiving option in main menu$")
	public void i_select_the_user_directed_receiving_option_in_main_menu() throws Throwable {
		poReceivingFromPuttyPage.selectMainMenu();
	}
}
