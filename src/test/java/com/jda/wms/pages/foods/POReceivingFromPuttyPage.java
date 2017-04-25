package com.jda.wms.pages.foods;

import java.awt.Robot;
import java.io.IOException;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

import net.sourceforge.htmlunit.corejs.javascript.tools.shell.Environment;

public class POReceivingFromPuttyPage {
	private Robot robot;
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public POReceivingFromPuttyPage(Robot robot) {
		this.robot = robot;
	}
	
	public void invokePutty() throws IOException {
		Process putty = Runtime.getRuntime().exec("putty.exe");
	}

	public void loginPutty(String host, String port) throws FindFailed, InterruptedException {
		screen.wait("images/Putty/HostName.png", timeoutInSec);
		screen.click("images/Putty/HostName.png");
		screen.type(host);
		screen.type(Key.TAB);
		screen.type(port);
		
		screen.wait("images/Putty/Telnet.png", timeoutInSec);
		screen.click("images/Putty/Telnet.png");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		
		screen.wait("images/Putty/Username.png", timeoutInSec);
		screen.type("P9120368");
		screen.type(Key.TAB);
		screen.type("12345");
		screen.type(Key.ENTER);
	}

	public void selectMainMenu() throws FindFailed {
		while (screen.exists("images/Putty/UserDirected.png") == null){
			screen.type(Key.F12);
		}
		screen.type("2");
		screen.type(Key.ENTER);
	}
}
