package com.jda.wms.pages.Exit;

import java.io.IOException;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class PuttyFunctionsPage {

	Screen screen = new Screen();
	int timeoutInSec = 20;
	private Context context;

	@Inject
	public PuttyFunctionsPage(Context context) {
		this.context = context;
	}

	public void invokePutty() throws IOException, InterruptedException {
		Process putty = Runtime.getRuntime().exec("bin/putty/putty.exe");
		context.setPuttyProcess(putty);
		Thread.sleep(2000);
	}

	public void loginPutty(String host, String port) throws FindFailed, InterruptedException {

		screen.type("A", Key.CTRL);
		screen.type(Key.BACKSPACE);
		System.out.println("host"+host);
		screen.type(host);
		screen.type(Key.TAB);
		screen.type("A", Key.CTRL);
		screen.type(Key.BACKSPACE);
		System.out.println("port"+port);
		screen.type(port);
		screen.wait("images/Putty/Telnet.png", timeoutInSec);
		screen.click("images/Putty/Telnet.png");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}
	public void Tab() throws FindFailed, InterruptedException{
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(Key.TAB);
	}

	public void enterJdaLogin(String username, String pwd) throws FindFailed, InterruptedException {
		screen.wait("images/Putty/Username.png", timeoutInSec);
		screen.type(username);
		screen.type(Key.TAB);
		screen.type(pwd);
		screen.type(Key.ENTER);
		Thread.sleep(4000);
	}

	public void mimimizePuty() throws FindFailed, InterruptedException {
		screen.wait("/images/Putty/MinimizePutty.png", timeoutInSec);
		screen.click("/images/Putty/MinimizePutty.png");
		screen.rightClick();
		Thread.sleep(2000);
		screen.wait("/images/Putty/Minimize.png", timeoutInSec);
		screen.click("/images/Putty/Minimize.png");
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
		Match mStatus = screen.find("images/Putty/PuttyMinimise.png");
		screen.mouseMove(63, 0);
		screen.click(mStatus.containsMouse());
		Thread.sleep(2000);
	}

	public boolean isMainMenuDisplayed() {
		if (screen.exists("images/Putty/MainMenu.png") != null) {
			context.setPuttyLoginFlag(true);
			return true;
		} else
			return false;
	}

	public void pressTab() throws InterruptedException {
		screen.type(Key.TAB);
		Thread.sleep(2000);
	}

	public void pressEnter() throws InterruptedException {
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void nextScreen() throws InterruptedException {
		screen.type("x", Key.CTRL);
		screen.type(Key.NUM4);
		Thread.sleep(2000);
	}

	public void enterPrinter() throws InterruptedException {

		screen.type("PRINTER1");
		screen.type(Key.ENTER);

	}
public void enterPrinter1()throws FindFailed, InterruptedException {
		Thread.sleep(3000);
		Match mLocation = screen.find("images/Putty/Picking/Answer1.png");
		screen.click(mLocation.getCenter().offset(70, 0));
		screen.type("PRINTER1");

	}

	public void enterF11() throws InterruptedException {

		screen.type(Key.F11);

	}

}
