package com.jda.wms.stepdefs.gm;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.StockCheckTaskDB;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.StockCheckTaskQueryPage;

import cucumber.api.java.en.When;

public class StockCheckTaskQueryStepDefs {
	private StockCheckTaskQueryPage stockCheckTaskQueryPage;
	private JDAFooter jdaFooter;
	private JdaHomePage jdaHomePage;
	private Context context;
	private StockCheckTaskDB stockCheckTaskDB;

	@Inject
	public StockCheckTaskQueryStepDefs(StockCheckTaskQueryPage stockCheckTaskQueryPage, JDAFooter jdaFooter,
			JdaHomePage jdaHomePage, Context context, StockCheckTaskDB stockCheckTaskDB) {
		this.stockCheckTaskQueryPage = stockCheckTaskQueryPage;
		this.jdaFooter = jdaFooter;
		this.jdaHomePage = jdaHomePage;
		this.context = context;
		this.stockCheckTaskDB = stockCheckTaskDB;
	}

	@When("^I get the list id$")
	public void i_get_the_list_id() throws Throwable {
		jdaFooter.clickQueryButton();
		stockCheckTaskQueryPage.enterTagId(context.getTagId());

		jdaFooter.clickExecuteButton();
		context.setListID((String) stockCheckTaskDB.getListId(context.getTagId()));
		context.setListID((String) stockCheckTaskDB.getListId(context.getTagId()));

	}
}
