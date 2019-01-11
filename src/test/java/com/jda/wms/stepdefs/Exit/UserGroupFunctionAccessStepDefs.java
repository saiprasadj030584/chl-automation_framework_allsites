package com.jda.wms.stepdefs.Exit;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.OrderHeaderPage;
import com.jda.wms.pages.Exit.UserGroupFunctionAccessPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class UserGroupFunctionAccessStepDefs{
	private final JdaHomePage jdaHomePage;
	private JDAFooter jdaFooter;
	private final UserGroupFunctionAccessPage userGroupFunctionAccessPage;
	private Context context;
	
	@Inject
	public UserGroupFunctionAccessStepDefs(JdaHomePage jdaHomePage,JDAFooter jdaFooter,Context context,OrderHeaderPage orderheaderpage,UserGroupFunctionAccessPage userGroupFunctionAccessPage) {
		this.jdaHomePage = jdaHomePage;
		this.jdaFooter=jdaFooter;
		this.userGroupFunctionAccessPage=userGroupFunctionAccessPage;
		this.context=context;
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
		
	}
	@And("^Navigate to --ROOT- > USER > RECEIVING > INT$")
	public void navigate_to_root_user_recieving_int() throws Throwable{
		
		userGroupFunctionAccessPage.clickUser();
		userGroupFunctionAccessPage.clickRecieving();
		userGroupFunctionAccessPage.clickInt();
	}
	@And("^Tried to Add New Rules for prohibition$")
	public void tried_to_add_new_rules_for_prohibition() throws Throwable{
		userGroupFunctionAccessPage.AddNewRules();
	}
    @And("^New pohibition logic should be allowed to include$")
    public void new_prohibiton_logic_should_be_allowed_to_include() throws Throwable{
    Assert.assertTrue("Logic cannot be included", userGroupFunctionAccessPage.isLogicAllowed());	
    }
    
    @And("^Provide the Support Team user ID \"([^\"]*)\" and Click Execute$")
    public void provide_the_support_team_user_id_and_click_execute(String SupportID) throws Throwable{
    	userGroupFunctionAccessPage.click_on_Query();
    	userGroupFunctionAccessPage.enterSupportId(SupportID);
    	userGroupFunctionAccessPage.clickExecuteButton();
    	context.setBasicUser(SupportID);
    	
    }
    @Then("^User group is validated as \"([^\"]*)\"$")
    public void user_group_validtaed(String userGp) throws Throwable{
    	userGroupFunctionAccessPage.getUserGroup();
    	System.out.println("ug="+userGroupFunctionAccessPage.getUserGroup());

        Assert.assertEquals("User gp not as expected",userGroupFunctionAccessPage.getUserGroup(),userGp);	

    }
    @Then("^Enter the New Prohibition for Israel$")
    public void enter_the_new_prohibition_rule_for_israel() throws Throwable{
    	userGroupFunctionAccessPage.enterProfile();
    	userGroupFunctionAccessPage.addDescription();
    	userGroupFunctionAccessPage.clickExecuteButton();
    }
    @Then("^The new rule should be included$")
    public void the_new_rule_should_be_included() throws Throwable{
        Assert.assertTrue("check ", userGroupFunctionAccessPage.isNewRuleAdded());	
        userGroupFunctionAccessPage.deleteValue();
    }
    
    @Then("^Validate the page for basic user$")
    public void validate_the_page_for_basic_user() throws Throwable{
        Assert.assertTrue("Page does not exist", userGroupFunctionAccessPage.isPageExist());	
    }
	
	
}
