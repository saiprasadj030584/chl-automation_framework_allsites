package com.jda.wms.stepdefs.gm;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.gm.DockSchedulerPage;
import com.jda.wms.pages.gm.JDAFooter;

import cucumber.api.java.en.When;

public class DockShedulerStepDefs1 {
	
	private DockSchedulerPage dockSchedulerPage;
	private Context context;
	private JDAFooter jdaFooter;
	
	@Inject
	public DockShedulerStepDefs1(DockSchedulerPage dockSchedulerPage,Context context,JDAFooter jdaFooter) {
		this.dockSchedulerPage = dockSchedulerPage;
		this.context = context;
		this.jdaFooter =jdaFooter;
	}
	
}
