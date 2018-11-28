package com.jda.wms.stepdefs.Exit;

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


public class UserGroupStepDefs{
	private final UserGroupPage userGroupPage;
	private final SkuDB skuDB;
	private final JdaHomePage jdaHomePage;
	Screen screen = new Screen();
	int timeoutInSec = 20;
	
	@Inject
	public UserGroupStepDefs(UserGroupPage userGroupPage,SkuDB skuDB,JdaHomePage jdaHomePage){
		this.userGroupPage=userGroupPage;
		this.skuDB=skuDB;
		this.jdaHomePage=jdaHomePage;
		
		
	}

	
	@And("^Specify the UserGroup \"([^\"]*)\"$")
	public void UserGroup(String UserGroup) throws Throwable {
		userGroupPage.enterUserGroup(UserGroup);
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
	@And("^Verify whether the webaccess$")
	public void validation_of_webaccess()throws Throwable {
		jdaHomePage.iswebAccessavail();
	}
	@And("^Verify whether the access is valid$")
	public void verify_whether_the_access_is_valid()throws Throwable {
		jdaHomePage.isUserAccessavaild();
	}
	
}