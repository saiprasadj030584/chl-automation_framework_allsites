package com.jda.wms.pages.Exit;
import org.sikuli.script.FindFailed;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.SiteDB;
import com.jda.wms.db.Exit.SkuDB;
import com.jda.wms.pages.Exit.JdaHomePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Assert;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

public class UserGroupPage{
	
	Screen screen = new Screen();
	int timeoutInSec = 20;
	Region reg = new Region(0, 0, 4000, 1000);
	public void enterUserGroup(String UserGroup) throws FindFailed {
		screen.wait("images/JDAHome/Group.png", timeoutInSec);
		screen.click("images/JDAHome/Group.png");
		screen.type(UserGroup);
	}
	public void entersortationgroup(String UserGroup) throws FindFailed {
//		screen.wait("images/JDAHome/Group.png", timeoutInSec);
//		screen.click("images/JDAHome/Group.png");
		screen.type(UserGroup);
	}
	
	
public String getUserGroup(String UserGroup) throws FindFailed, InterruptedException {
	
		Match mStatus = screen.find("images/JDAHome/Group.png");
		screen.click(mStatus.getCenter().offset(70,0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		Thread.sleep(2000);
		return App.getClipboard();
	
}
public void validateUserGroup() throws FindFailed {
	
	ArrayList <String> UserGroup = new ArrayList<String>();
	UserGroup.add("BASICUSER");
	UserGroup.add("ADVUSER");
	UserGroup.add("SUPERVISOR");
	UserGroup.add("STOCKADV");
	UserGroup.add("HEADOFFICE");
	UserGroup.add("ZENSAR");
	int size= UserGroup.size();
	screen.wait("images/JDAHome/Group.png", timeoutInSec);
	screen.click("images/JDAHome/Group.png");
	for(int i=0;i<size;i++){

 String element=UserGroup.get(i);
	screen.type(element);
	}
}
public void enterSearchKey() throws FindFailed {
	Match mStatus = screen.find("images/JDAHome/AccessSearch.png");
	screen.click(mStatus.getCenter().offset(80,0));
	screen.type("Allocation Algorithm - Allow Adding, Deleting and Modifying of Allocation Algorithms");
	screen.find("images/JDAHome/AccessSearchButton.png");
	screen.click("images/JDAHome/AccessSearchButton.png");
}
public void accessEnabled() throws FindFailed {
	screen.find("images/JDAHome/checkboxchkd.png");
	if(screen.exists("images/JDAHome/checkboxchkd.png")!=null){
		Assert.assertTrue(true);
	}else{
		Assert.assertFalse(false);
	}
}
	public void typeSearchKey()throws FindFailed, InterruptedException {
		ArrayList <String> AccessGroup = new ArrayList<String>();
		AccessGroup.add("Allocation - User Defined Type 1 is Used for Allocation");
		AccessGroup.add("Allocation - User Defined Type 8 is Used for Allocation");
		AccessGroup.add("Back Ordering - At Receiving Time");
		AccessGroup.add("M&S - Comms - Automatically Set Back Ordered flag on");
		int size= AccessGroup.size();
		
		for(int i=0;i<size;i++){
			Match mStatus = screen.find("images/JDAHome/AccessSearch.png");
			screen.click(mStatus.getCenter().offset(80,0));
	 String element=AccessGroup.get(i);
		screen.type(element);
		screen.find("images/JDAHome/AccessSearchButton.png");
		screen.click("images/JDAHome/AccessSearchButton.png");
		accessEnabled();
	Thread.sleep(2000);
	screen.find("images/JDAHome/AccessSearch.png");
	screen.type("a", Key.CTRL);
	screen.type(Key.BACKSPACE);
		}
	}
	public void accessSearchKey()throws FindFailed, InterruptedException {
		ArrayList <String> AccessGroup = new ArrayList<String>();
		AccessGroup.add("Picking");
		AccessGroup.add("Relocate");
		
		int size= AccessGroup.size();
		
		for(int i=0;i<size;i++){
			Match mStatus = screen.find("images/JDAHome/AccessSearch.png");
			screen.click(mStatus.getCenter().offset(80,0));
	 String element=AccessGroup.get(i);
		screen.type(element);
		screen.find("images/JDAHome/AccessSearchButton.png");
		screen.click("images/JDAHome/AccessSearchButton.png");
		accessEnabled();
	Thread.sleep(2000);
	screen.find("images/JDAHome/AccessSearch.png");
	screen.type("a", Key.CTRL);
	screen.type(Key.BACKSPACE);
		}
	}
	public void SearchFuntionAccess(String search) throws FindFailed, InterruptedException {
		Match mStatus = screen.find("images/JDAHome/AccessSearch.png");
		screen.click(mStatus.getCenter().offset(80,0));
		screen.type(search);
		Thread.sleep(2000);
		screen.find("images/JDAHome/AccessSearchButton.png");
		screen.click("images/JDAHome/AccessSearchButton.png");
	}
	public void selectRDTGroup() throws FindFailed, InterruptedException {
		screen.wait("images/JDAHome/FunctionAccessGroupDropdown1.png", timeoutInSec);
		Match mStatus = screen.find("images/JDAHome/FunctionAccessGroupDropdown1.png");
		Thread.sleep(2000);
		screen.click(mStatus.getCenter().offset(80,0));
		//screen.click("images/JDAHome/FunctionAccessGroupDropdown.png");
		Thread.sleep(2000);
		 Match header = screen.find("images/JDAHome/UserRDT.png");
		   reg=header.below(150).left(5).right(1000);
		   reg.hover(header);
		   reg.click(header);
	}
}

