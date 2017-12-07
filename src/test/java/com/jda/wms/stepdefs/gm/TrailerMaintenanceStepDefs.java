package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.TrailerDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.JdaLoginPage;
import com.jda.wms.pages.gm.TrailerMaintenancePage;
import com.jda.wms.utils.Utilities;

import cucumber.api.java.en.Given;

public class TrailerMaintenanceStepDefs {
	private JDAFooter jdaFooter;
	private TrailerMaintenancePage trailerMaintenancePage;
	private JdaHomePage jdaHomePage;
	private JdaLoginPage jdaLoginPage;
	private Context context;
	private TrailerDB trailerDB;

	@Inject
	public TrailerMaintenanceStepDefs(JDAFooter jdaFooter, TrailerMaintenancePage trailerMaintenancePage,
			JdaHomePage jdaHomePage, JdaLoginPage jdaLoginPage, Context context, TrailerDB trailerDB) {
		this.jdaFooter = jdaFooter;
		this.trailerMaintenancePage = trailerMaintenancePage;
		this.jdaHomePage = jdaHomePage;
		this.jdaLoginPage = jdaLoginPage;
		this.context = context;
		this.trailerDB = trailerDB;
	}

	@Given("^I create a trailer to receive at the dock door$")
	public void i_create_a_trailer_to_receive_at_the_dock_door() throws Throwable {
		System.out.println("STEP 4444444444444444444444");
		jdaLoginPage.login();
		System.out.println("STEP 55555555555555");
		jdaHomePage.navigateToTrailerMaintanencePage();
		jdaFooter.clickAddButton();
		String trailerNo = Utilities.getFiveDigitRandomNumber();
		while (trailerDB.isTrailerExists(trailerNo)) {
			trailerNo = Utilities.getFiveDigitRandomNumber();
		}
		trailerMaintenancePage.enterTrailerNo(trailerNo);
		trailerMaintenancePage.enterTrailerType();
		jdaFooter.clickExecuteButton();
		jdaFooter.PressEnter();
		context.setTrailerNo(trailerNo);
	}

	@Given("^I create \"([^\"]*)\" trailer to receive at the dock door$")
	public void i_create_multiple_trailer_to_receive_at_the_dock_door(String count) throws Throwable {
		ArrayList<String> trailerList = new ArrayList<String>();
		for (int i = 0; i < Integer.parseInt(count); i++) {
			jdaHomePage.navigateToTrailerMaintanencePage();
			jdaFooter.clickAddButton();
			String trailerNo = Utilities.getFiveDigitRandomNumber();
			while (trailerDB.isTrailerExists(trailerNo)) {
				trailerNo = Utilities.getFiveDigitRandomNumber();
			}
			trailerMaintenancePage.enterTrailerNo(trailerNo);
			trailerMaintenancePage.enterTrailerType();
			jdaFooter.clickExecuteButton();
			jdaFooter.PressEnter();
			trailerList.add(trailerNo);
		}
		context.setTrailerList(trailerList);
	}

}
