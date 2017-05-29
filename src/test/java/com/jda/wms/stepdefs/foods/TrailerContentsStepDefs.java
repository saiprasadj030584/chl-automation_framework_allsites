package com.jda.wms.stepdefs.foods;

import com.google.inject.Inject;
import com.jda.wms.db.TrailerContentsDB;
import com.jda.wms.pages.foods.JDAFooter;
import com.jda.wms.pages.foods.JdaHomePage;

import cucumber.api.java.en.Given;
import org.junit.Assert;

public class TrailerContentsStepDefs {

	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdafooter;
	private final TrailerContentsDB trailerContentsDB;

	@Inject
	public TrailerContentsStepDefs(JdaHomePage jdaHomePage, JDAFooter jdafooter, TrailerContentsDB trailerContentsDB) {
		this.jdaHomePage = jdaHomePage;
		this.jdafooter = jdafooter;
		this.trailerContentsDB = trailerContentsDB;
	}

	@Given("^the trailer \"([^\"]*)\" consists of contents$")
	public void the_trailer_consists_of_contents(String trailerNo) throws Throwable {
		Assert.assertEquals("Trailer does not contain any pallets", trailerContentsDB.getNoOfRecords(trailerNo), 0);
	}
}
