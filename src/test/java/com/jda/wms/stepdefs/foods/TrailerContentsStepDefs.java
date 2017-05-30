package com.jda.wms.stepdefs.foods;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.TrailerContentsDB;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;

import cucumber.api.java.en.Given;

public class TrailerContentsStepDefs {

	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdafooter;
	private final TrailerContentsDB trailerContentsDB;
	private final Context context;

	@Inject
	public TrailerContentsStepDefs(JdaHomePage jdaHomePage, JDAFooter jdafooter, TrailerContentsDB trailerContentsDB,
			Context context) {
		this.jdaHomePage = jdaHomePage;
		this.jdafooter = jdafooter;
		this.trailerContentsDB = trailerContentsDB;
		this.context = context;
	}

	@Given("^the trailer \"([^\"]*)\" consists of contents$")
	public void the_trailer_consists_of_contents(String trailerNo) throws Throwable {
		context.setTrailerNo(trailerNo);
		Assert.assertTrue("Trailer does not contain any pallets",
				isTrailerContents(trailerContentsDB.getNoOfRecords(trailerNo)));
	}

	public boolean isTrailerContents(String NoOfRecords) {
		if (NoOfRecords.equals("0")) {
			return false;
		}
		return true;
	}

}
