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
//		jdaFooter.clickExecuteButton();
//		
//			
//					//String[]UserGroup = new String[]{"ABC","DEF"};
//					ArrayList UserGroup = new ArrayList();
//					UserGroup.add("ABC");
//					UserGroup.add("DEF");
//				      //System.out.println("Initial size of al: " + al.size());
//					int size= UserGroup.size();
//					screen.wait("images/JDAHome/Group.png", timeoutInSec);
//					screen.click("images/JDAHome/Group.png");
//					for(int i=0;i<size;i++){
//				 UserGroup.get(i);
//					screen.type((String) UserGroup.get(i));
//					}
//				} 
//
//		
//		}
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
	
}