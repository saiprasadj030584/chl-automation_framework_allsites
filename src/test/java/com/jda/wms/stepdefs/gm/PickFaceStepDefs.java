package com.jda.wms.stepdefs.gm;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.MoveTaskDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.PickFacePage;

import cucumber.api.java.en.Then;

public class PickFaceStepDefs {
	private MoveTaskDB moveTaskDB;
	private Context context;
	private JDAHomeStepDefs jDAHomeStepDefs;
	private JDAFooter jDAFooter;
	private PickFacePage pickFacePage;
	@Inject
	public PickFaceStepDefs(Context context,PickFacePage pickFacePage,JDAFooter jDAFooter,MoveTaskDB moveTaskDB,JDAHomeStepDefs jDAHomeStepDefs) {
		this.context = context;
		this.jDAHomeStepDefs=jDAHomeStepDefs;
		this.moveTaskDB=moveTaskDB;
		this.jDAFooter=jDAFooter;
		this.pickFacePage=pickFacePage;
	}


	
	@Then ("^Pick Face is maintained against the location$")
	public void Pick_Face_is_maintained_against_the_location() throws Throwable
		{
		jDAHomeStepDefs.i_navigate_to_JDA_page("PICK FACE");
		pickFacePage.TypeinTextBox("fixed");
		jDAFooter.pressTab();
		pickFacePage.TypeinTextBox("5885");
		jDAFooter.pressTab();
		pickFacePage.TypeinTextBox(moveTaskDB.getLocationId(context.getOrderId()));
		jDAFooter.clickExecuteButton();
		}
}
