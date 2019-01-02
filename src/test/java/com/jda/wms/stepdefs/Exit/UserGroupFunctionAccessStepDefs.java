package com.jda.wms.stepdefs.Exit;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.OrderHeaderPage;
import com.jda.wms.pages.Exit.UserGroupFunctionAccessPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class UserGroupFunctionAccessStepDefs{
	private final JdaHomePage jdaHomePage;
	private JDAFooter jdaFooter;
	private final UserGroupFunctionAccessPage userGroupFunctionAccessPage;
	
	@Inject
	public UserGroupFunctionAccessStepDefs(JdaHomePage jdaHomePage,JDAFooter jdaFooter,OrderHeaderPage orderheaderpage,UserGroupFunctionAccessPage userGroupFunctionAccessPage) {
		this.jdaHomePage = jdaHomePage;
		this.jdaFooter=jdaFooter;
		this.userGroupFunctionAccessPage=userGroupFunctionAccessPage;
			}
	
	@Then("^Search for \"([^\"]*)\" report$")
	public void search_for_report(String report) throws Throwable{
		userGroupFunctionAccessPage.enterReport(report);
		Thread.sleep(1000);
		jdaFooter.clickSearch();
	}
	@And("^Validate that records should be loaded$")
	public void validate_that_records_should_be_loaded() throws Throwable{
		Assert.assertTrue("Record not displayed", userGroupFunctionAccessPage.isRecordDissplayedAndSelected());
		Thread.sleep(1000);
	}
	
	@And("^Access should be enabled for \"([^\"]*)\" Group$")
	public void access_should_be_enabled_for_group(String group) throws Throwable{
		Assert.assertTrue("Report access not enabled", userGroupFunctionAccessPage.isReportAccessEnabled());
		jdaFooter.clickCloseButton();
	}
	
	@Then("^Search for other group \"([^\"]*)\"$")
	public void search_for_other_group(String group) throws Throwable{
		userGroupFunctionAccessPage.enterGroup();
		Thread.sleep(1000);
		jdaFooter.clickSearch();
		
	}
	@Then("^Search for other group \"([^\"]*)\" for consignment$")
	public void search_for_other_group_for_consignment(String group) throws Throwable{
		userGroupFunctionAccessPage.enterGroupPickClerk();
		Thread.sleep(1000);
		jdaFooter.clickSearch();
		
	}
	
	@And("^Validate that records should be loaded for consignment$")
	public void validate_that_records_should_be_loaded_for_consignment() throws Throwable{
		Assert.assertTrue("Record not displayed", userGroupFunctionAccessPage.isRecordDissplayedAndSelectedForConsignment());
		Thread.sleep(1000);
	}
	
	@And("^Access should be enabled for \"([^\"]*)\" Group for consignment$")
	public void access_should_be_enabled_for_group_for_consignment(String group) throws Throwable{
		Assert.assertTrue("Report access not enabled", userGroupFunctionAccessPage.isReportAccessEnabledForConsignment());
		jdaFooter.clickCloseButton();
	}
	
	
}
