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

import org.junit.Assert;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

public class UserGroupPage{
	
	Screen screen = new Screen();
	int timeoutInSec = 20;
	
	public void enterUserGroup(String UserGroup) throws FindFailed {
		screen.wait("images/JDAHome/Group.png", timeoutInSec);
		screen.click("images/JDAHome/Group.png");
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
	
}
