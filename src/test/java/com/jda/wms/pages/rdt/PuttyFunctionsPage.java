package com.jda.wms.pages.rdt;

import java.io.IOException;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

public class PuttyFunctionsPage {
	
	Screen screen = new Screen();
	int timeoutInSec = 20;
	
	public void invokePutty() throws IOException, InterruptedException {
		Process putty = Runtime.getRuntime().exec("putty.exe");
		Thread.sleep(2000);
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
	}
	
	public void enterJdaLogin(String username, String pwd) throws FindFailed, InterruptedException {
		screen.wait("images/Putty/Username.png", timeoutInSec);
		screen.type(username);
		screen.type(Key.TAB);
		screen.type(pwd);
		screen.type(Key.ENTER);
		Thread.sleep(4000);
	}

	public boolean isLoginScreenDisplayed() {
		if (screen.exists("images/Putty/Username.png") != null)
			return true;
		else
			return false;
	}
	
	public void minimisePutty() throws FindFailed, InterruptedException {
		screen.wait("images/Putty/PuttyMinimise.png", timeoutInSec);
		screen.click("images/Putty/PuttyMinimise.png");
		screen.mouseMove(80,0);
		screen.click("images/Putty/PuttyMinimise.png");
		Thread.sleep(2000);
	}
	
	public boolean isMainMenuDisplayed() {
		if (screen.exists("images/Putty/MainMenu.png") != null)
			return true;
		else
			return false;
	}
}
