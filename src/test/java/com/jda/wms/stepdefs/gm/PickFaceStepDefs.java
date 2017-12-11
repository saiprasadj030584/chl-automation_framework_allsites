package com.jda.wms.stepdefs.gm;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.MoveTaskDB;

import cucumber.api.java.en.Then;

public class PickFaceStepDefs {
	private MoveTaskDB moveTaskDB;
	private Context context;

	@Inject
	public PickFaceStepDefs(Context context,MoveTaskDB moveTaskDB) {
		this.context = context;
		
		this.moveTaskDB=moveTaskDB;
	}


	
	@Then ("^Pick Face is maintained against the location$")
	public void Pick_Face_is_maintained_against_the_location() throws Throwable
		{
		//navigate to pick face screen
		//Type fixed
		//enterSite Id
		
		moveTaskDB.getLocationId(context.getOrderId());
		//maintain the pick face for the location allocated
		}
}
