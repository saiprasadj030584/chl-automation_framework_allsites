package com.jda.wms.stepdefs.Exit;

import java.util.ArrayList;

import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.pages.Exit.JdaHomePage;
import com.jda.wms.pages.Exit.JdaLoginPage;
import com.jda.wms.pages.Exit.SKUQueryPage;
import com.jda.wms.pages.Exit.UserGroupPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


@SuppressWarnings("unused")
public class UserGroupStepDefs{
	private final UserGroupPage userGroupPage;
	private final SkuDB skuDB;
	private final JdaHomePage jdaHomePage;
	private final JDAFooter jdaFooter;
	Screen screen = new Screen();
	int timeoutInSec = 20;
	
	@Inject
	public UserGroupStepDefs(UserGroupPage userGroupPage,SkuDB skuDB,JdaHomePage jdaHomePage,JDAFooter jdaFooter){
		this.userGroupPage=userGroupPage;
		this.skuDB=skuDB;
		this.jdaHomePage=jdaHomePage;
		this.jdaFooter=jdaFooter;
		
		
	}

	
	@And("^Specify the UserGroup \"([^\"]*)\"$")
	public void UserGroup(String UserGroup) throws Throwable {
		userGroupPage.enterUserGroup(UserGroup);

}
	@And("^Specify the sortation group \"([^\"]*)\"$")
	public void Sortationgroup(String UserGroup) throws Throwable {
		userGroupPage.entersortationgroup(UserGroup);
	}
	@Then("^Verify whether the User-group been populated \"([^\"]*)\" in the table$")
	public void validation_of_usergroup(String UserGroup) throws Throwable {
		
		String usergroup=userGroupPage.getUserGroup(UserGroup);
		String usergroupdb=skuDB.getusergroup(UserGroup);
//		Assert.assertEquals("UserGroup valdiated ",userGroupPage.enterUserGroup(UserGroup),skuDB.getusergroup(UserGroup));
		Assert.assertEquals("UserGroup valdiated ",usergroup,usergroupdb);
	}
	@And("^Verify whether the access$")
	public void validation_of_access()throws Throwable {
		jdaHomePage.isUserAccessavail();
	}
	@And("^Validate Blind receipt, pre-advice receipt, repack$")
	public void validate_blind_receipt()throws Throwable {
		jdaHomePage.issortationreceipt();
	}
	@And("^Verify whether the webaccess$")
	public void validation_of_webaccess()throws Throwable {
		jdaHomePage.iswebAccessavail();
	}
	@And("^Verify whether the access is valid$")
	public void verify_whether_the_access_is_valid()throws Throwable {
		jdaHomePage.isUserAccessavaild();
	}
	@And("^Select a User Group from the Group dropdown box$")
	public void select_a_User_Group_from_the_Group_dropdown_box()throws Throwable {
		jdaHomePage.selectUserGroup();
	}
	@And("^I select Allocation creation date by zone option$")
	public void I_select_Allocation_creation_date_by_zone_option()throws Throwable {
		
		jdaHomePage.selectAllocationOpt();
	}
	@And("^type \"([^\"]*)\" in location zone$")
	public void LocationZone(String Zone) throws Throwable {
		jdaHomePage.enterLocationZone(Zone);
	}
	@And("^save the Allocation created$")
	public void save_the_Allocation_created()throws Throwable {
		
		jdaHomePage.saveAllocation();
	}
	@And("^Type in Function Access search for text box$")
	public void Type_in_Search() throws Throwable {
		userGroupPage.enterSearchKey();
	}
	@And("^validate the access is Enabled$")
	public void Access_Enabled() throws Throwable {
		userGroupPage.accessEnabled();
	}
	@And("^Type in and validate Function Access search for text box$")
	public void Type_in_function_Access() throws Throwable {
		userGroupPage.typeSearchKey();
	}
	@And("^Search for Picking and Relocate access$")
	public void search_function_Access() throws Throwable {
		userGroupPage.accessSearchKey();
	}
	@And("^Type in Function Access search \"([^\"]*)\"$")
	public void type_function_access() throws Throwable{
		//userGroupPage.navigateToUserGroupFuntionAccess();
	}
}