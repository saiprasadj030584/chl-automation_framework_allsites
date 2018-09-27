package com.jda.wms.stepdefs.Exit;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.pages.Exit.JdaHomePage;


public class JDAExitMoveTaskManagement<JDAFooter>{
	Screen screen = new Screen();
	int timeoutInSec = 20;
	private JDAFooter jDAFooter;
	private JdaHomePage imageCheckFunction;
	
	@Inject
	public JDAExitMoveTaskManagement(JDAFooter jDAFooter, JdaHomePage imageCheckFunction) {
		this.jDAFooter = jDAFooter;
		this.imageCheckFunction = imageCheckFunction;
	}
	public void enterTaskIdInMoveTaskUpdate(String orderID) throws FindFailed, InterruptedException {
		Match mTagId = screen.find("images/MoveTaskUpdate/taskid.png");
		screen.click(mTagId.getCenter().offset(70, 0));
		screen.type(orderID);
		Thread.sleep(1000);
	}
	

}