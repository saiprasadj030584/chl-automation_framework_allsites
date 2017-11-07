package com.jda.wms.pages.rdt;

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

		// Clear pre-entered host name
		screen.type("A", Key.CTRL);
		screen.type(Key.BACKSPACE);
		screen.type(host);

		// navigate to Port
		screen.type(Key.TAB);
		// Clear pre-entered Port detail
		screen.type("A", Key.CTRL);
		screen.type(Key.BACKSPACE);
		screen.type(port);

		screen.wait("images/Putty/Telnet.png", timeoutInSec);
		screen.click("images/Putty/Telnet.png");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public void enterJdaLogin(String username, String pwd) throws FindFailed, InterruptedException {
		if (screen.exists("images/Putty/Username.png") != null) {
			screen.wait("images/Putty/Username.png", timeoutInSec);
		} else if (screen.exists("images/Putty/User.png") != null) {
			screen.wait("images/Putty/User.png", timeoutInSec);
		}

		screen.type(username);
		screen.type(Key.TAB);
		screen.type(pwd);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
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
		if ((screen.exists("images/Putty/Username.png") != null) || (screen.exists("images/Putty/User.png") != null))
			return true;
		else
			return false;
	}

	public void selectUserDirectedMenu() throws FindFailed, InterruptedException {
		screen.type("2");
		Thread.sleep(1000);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
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

	public void backSpace() throws InterruptedException {
		screen.type(Key.BACKSPACE);
		Thread.sleep(2000);
	}

	public void pressEnter() throws InterruptedException {
		screen.type(Key.ENTER);
		Thread.sleep(5000);
	}

	public boolean isUserMenuDisplayed() {
		if (screen.exists("images/Putty/UserMenu.png") != null)
			return true;
		else
			return false;
	}

	public void nextScreen() throws InterruptedException {
		screen.type("x", Key.CTRL);
		screen.type(Key.NUM4);
		Thread.sleep(2000);
	}

	public void press() throws InterruptedException {
		screen.type(Key.ENTER);
		Thread.sleep(5000);

	}

	public void backspace() throws InterruptedException {
		screen.type(Key.BACKSPACE);
		Thread.sleep(2000);
	}

	public void rightArrow() throws InterruptedException {
		screen.type(Key.RIGHT);
		Thread.sleep(2000);
	}

	public void backscreen() throws InterruptedException {
		screen.type(Key.F12);
		Thread.sleep(2000);
	}

	public boolean isLoginFailureExists() {
		if (screen.exists("images/Putty/LoginFailure.png") != null)
			return true;
		else if (screen.exists("images/Putty/ReconnectError.png") != null)
			return true;
		else
			return false;
	}
	
	public boolean isVehEntPageDisplayed() throws FindFailed, InterruptedException {
		if (screen.exists("images/Putty/VehicleLoading/LodMEnt.png") != null)
			return true;
		else
			return false;
	}
	
	public boolean isVehicleUnloadEntryPageDisplayed() throws FindFailed {
		if (screen.exists("images/Putty/VehicleLoading/UnLodEnt.png") != null)
			return true;
		else
			return false;
	}

	public boolean isTrailerEnterPageDisplayed() {
		if (screen.exists("images/Putty/VehicleLoading/TrailerEnterPage.png") != null)
			return true;
		else
			return false;
	}
	
	public boolean isinvalidpalletIdPageDisplayed() {
		if (screen.exists("images/Putty/VehicleLoading/invalidPallet.png") != null)
			return true;
		else
			return false;
	}

}