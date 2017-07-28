package com.jda.wms.stepdefs.gm;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
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

	@Inject
	public TrailerMaintenanceStepDefs(JDAFooter jdaFooter, TrailerMaintenancePage trailerMaintenancePage,
			JdaHomePage jdaHomePage, JdaLoginPage jdaLoginPage, Context context) {
		this.jdaFooter = jdaFooter;
		this.trailerMaintenancePage = trailerMaintenancePage;
		this.jdaHomePage = jdaHomePage;
		this.jdaLoginPage = jdaLoginPage;
		this.context = context;
	}

	@Given("^I create a trailer to receive at the dock door$")
	public void i_create_a_trailer_to_receive_at_the_dock_door() throws Throwable {
		//jdaLoginPage.login();
		jdaHomePage.navigateToTrailerMaintanencePage();
		jdaFooter.clickAddButton();
		String trailerNo = Utilities.getFiveDigitRandomNumber();
		trailerMaintenancePage.enterTrailerNo(trailerNo);
		trailerMaintenancePage.enterTrailerType();
		jdaFooter.clickExecuteButton();
		jdaFooter.PressEnter();
		context.setTrailerNo(trailerNo);
	}

}
