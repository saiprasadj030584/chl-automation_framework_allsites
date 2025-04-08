package com.mns.auto.cd.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import com.google.inject.Inject;
import com.mns.auto.cd.config.Configuration;

public class DeploymentPage {

	private Configuration configuration;

	@Inject
	public DeploymentPage(Configuration configuration) {

		this.configuration = configuration;

	}

	public void connectTelnet() {
		Runtime r = Runtime.getRuntime();
		Process p;

		String connectTelnet = "C:/Program Files/PuTTY/putty.exe -ssh -l "
				+ configuration.getStringProperty("puttyUserName") + " -pw "
				+ configuration.getStringProperty("puttyPassword") + " " + configuration.getStringProperty("telnetHost")
				+ "";
		System.out.println(connectTelnet);

		try {

			p = r.exec(connectTelnet);
			Thread.sleep(2000);

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();

		}
	}

	public void enterAsSudoUser() throws AWTException, InterruptedException {
		StringSelection stringSelection = new StringSelection("sudo su - rpwms");
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);
		Robot robot = new Robot();
		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		Thread.sleep(150);
		robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(150);
	}

	public void spaceCheckInSpecificEnvironment() throws AWTException, InterruptedException {

		StringSelection spaceCheck = new StringSelection("df -gt .");
		Clipboard clipboard1 = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard1.setContents(spaceCheck, spaceCheck);

		Robot robot = new Robot();
		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		Thread.sleep(150);
		robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(1500);

	}

	public void stopApplication() throws AWTException, InterruptedException {

		// StringSelection stopApp = new StringSelection("appstop -y");
		StringSelection stopApp = new StringSelection("appstop");
		Clipboard clipboard2 = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard2.setContents(stopApp, stopApp);

		Robot robot = new Robot();
		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		Thread.sleep(150);
		robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(3500);

	}

	public void startApplication() throws AWTException, InterruptedException {
		StringSelection startApp = new StringSelection("appstart");
		Clipboard clipboard3 = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard3.setContents(startApp, startApp);

		Robot robot = new Robot();
		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		Thread.sleep(150);
		robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(1500);
	}

	public void runReleaseDeployPackage(String version) throws AWTException, InterruptedException {
		String newRegion = "runrelease.sh -v release." + version + " 2>&1|tee -a runrelease." + version + ".txt";
		System.out.println(newRegion);
		StringSelection deploy = new StringSelection(newRegion);

		Clipboard clipboard4 = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard4.setContents(deploy, deploy);

		Robot robot = new Robot();
		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		Thread.sleep(150);
		robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(1500);

	}

}
