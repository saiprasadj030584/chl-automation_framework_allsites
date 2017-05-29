package com.jda.wms.pages.foods;

import com.google.inject.Inject;
import com.jda.wms.db.TrailerContentsDB;

public class VehicleUnloadingPage {

	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdafooter;

	@Inject
	public VehicleUnloadingPage(JdaHomePage jdaHomePage, JDAFooter jdafooter) {
		this.jdaHomePage = jdaHomePage;
		this.jdafooter = jdafooter;
	}

}
