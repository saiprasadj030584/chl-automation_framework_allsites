package com.jda.wms.handlers;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import com.google.inject.Inject;
import com.jda.wms.UI.pages.JdaLoginPage;
import com.jda.wms.context.Context;


public class WebHandler {

	private final JdaLoginPage jdaLoginPage;
	Screen screen = new Screen();
	private final Context context;
	int timeoutInSec = 20;

	@Inject
	public WebHandler(JdaLoginPage jdaLoginPage,Context context) {
		this.jdaLoginPage = jdaLoginPage;
		this.context=context;
	}

	public void login_to_JDA_Dispatcher_web_screen() throws Throwable {
		Thread.sleep(10000);
		jdaLoginPage.login();
	}
	public void clickNextButton() throws FindFailed, InterruptedException {
		screen.type(Key.F7);
		Thread.sleep(3000);
	}
	public void clickDoneButton() throws FindFailed, InterruptedException {
		screen.type(Key.F12);
		Thread.sleep(3000);
	}
	public void PressEnter() throws InterruptedException {
		screen.type(Key.ENTER);
	}
	public  void typeString(String str){
		screen.type(str);
	}
	public void typeKey(String key){
		switch(key){
		case "ENTER":
			screen.type(Key.ENTER);
			break;
		case "UP":
			screen.type(Key.UP);
			break;
		case "DOWN":
			screen.type(Key.DOWN);
			break;
		case "TAB":
			screen.type(Key.TAB);
			break;
		case "BACKSPACE":
			screen.type(Key.BACKSPACE);
			break;
		case "CONTROL-C":
			screen.type("c", Key.CTRL);
			break;
		case "CONTROL-A":
			screen.type("a", Key.CTRL);
			break;
		case "SHIFT-DOWN":
			screen.type( Key.DOWN, Key.SHIFT);
			break;
		default :
			System.out.println("Unknown key passed");
			break;
		}		
	}

	public void searchScreen(String Screen)throws FindFailed, InterruptedException {			
		Thread.sleep(1000);
		clickSearchIcon();
		Thread.sleep(1000);
		issearchScreenLoaded();
		Thread.sleep(2000);
		screen.type(Screen);
		screen.type(Key.ENTER);
		Thread.sleep(2000);
		screen.type(Key.ENTER);
		Thread.sleep(1000);
	}
	public void issearchScreenLoaded()throws FindFailed, InterruptedException {
		Thread.sleep(1000);
		if(screen.exists("images/JDAHome/screenLoaded.png")!=null){
			Assert.assertTrue("Screen is not loaded", true);
		}
	}
	public void clickSearchIcon() throws FindFailed, InterruptedException {
		Thread.sleep(5000);
		if (screen.exists("images/JDAHome/SearchIconButton.png") != null) {
			System.out.println("Application search icon found");
			if (screen.exists("images/JDAHome/Welcomed.png") != null) {
				screen.wait("images/JDAHome/Welcomed.png", timeoutInSec);
				screen.click("images/JDAHome/Welcomed.png");
				Thread.sleep(2000);
				screen.type("f", Key.CTRL);
				Thread.sleep(2000);
			} else if (screen.exists("images/JDAHome/Welcome.png") != null) {
				screen.wait("images/JDAHome/Welcome.png", timeoutInSec);
				screen.click("images/JDAHome/Welcome.png");
				Thread.sleep(2000);
				System.out.println("Check - ");
				screen.type("f", Key.CTRL);
				Thread.sleep(2000);
			}
		}

		else if (screen.exists("images/JDAHome/JdaHomeLogin.png") != null) {
			System.out.println("Application login page found instead of search icon");
			jdaLoginPage.enterUsername();
			jdaLoginPage.enterPassword();
			jdaLoginPage.clickConnectButton();
			Thread.sleep(5000);
			if (screen.exists("images/JDAHome/Welcomed.png") != null) {
				screen.wait("images/JDAHome/Welcomed.png", timeoutInSec);
				screen.click("images/JDAHome/Welcomed.png");
				Thread.sleep(2000);
				screen.type("f", Key.CTRL);
				Thread.sleep(2000);
			} else {
				System.out.println("1. Application issue - Kill IE driver and luanch application from first");
				try {
					Process p = Runtime.getRuntime()
							.exec("cmd /c C:/Automation_supporting_files/LnkFiles/Iexplorekill.lnk");
					p.waitFor(30, TimeUnit.SECONDS);
				} catch (IOException e) {

					e.printStackTrace();
				}
				jdaLoginPage.driver = null;
				jdaLoginPage.login();
				screen.wait("images/JDAHome/Welcomed.png", timeoutInSec);
				screen.click("images/JDAHome/Welcomed.png");
				Thread.sleep(2000);
				search();
			}
		} else {
			System.out.println("2. Application issue - Kill IE driver and luanch application from first");
			try {
				Process p = Runtime.getRuntime()
						.exec("cmd /c C:/Automation_supporting_files/LnkFiles/Iexplorekill.lnk");
				p.waitFor(30, TimeUnit.SECONDS);
			} catch (IOException e) {

				e.printStackTrace();
			}
			jdaLoginPage.driver = null;
			jdaLoginPage.login();
			screen.wait("images/JDAHome/Welcomed.png", timeoutInSec);
			screen.click("images/JDAHome/Welcomed.png");
			Thread.sleep(2000);
			search();
		}

	}
	public void search() throws InterruptedException, FindFailed{
		if (screen.exists("images/JDAHome/Welcome.png") != null) {
			screen.wait("images/JDAHome/Welcome.png", timeoutInSec);
			screen.click("images/JDAHome/Welcome.png");
			Thread.sleep(2000);
			System.out.println("Check - ");
			screen.type("f", Key.CTRL);
			Thread.sleep(2000);
		}
	}

	public void clickTarget(String image_url) {
		try {
			System.out.println("Clicking on :" + image_url);
			screen.click(image_url);
			Thread.sleep(1000);
		} catch (FindFailed e) {
			System.out.println("Find failed occured in clickElement method");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted in clickElement method");
			e.printStackTrace();
		}

	}
	public void doubleClickTarget(String image_url) {
		try {
			System.out.println("CLicking on :" + image_url);
			screen.doubleClick(image_url);
			Thread.sleep(1000);
		} catch (FindFailed e) {
			System.out.println("Find failed occured in clickElement method");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted in clickElement method");
			e.printStackTrace();
		}
	}
	public void clickCloseButton() throws FindFailed, InterruptedException {
        screen.type(Key.ESC);
        Thread.sleep(3000);
    }

	/**
	 * Author: Vedika 
	 * The following is the code for Clicking Next Button
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	public void clickNext() throws InterruptedException, FindFailed {
		clickTarget("/images/Clustering/Next.png");
		Thread.sleep(3000);
	}
	
	/**
	 * Author: Vedika 
	 * The following is the code for Clicking Done Button
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	public void clickDone() throws InterruptedException, FindFailed {
		clickTarget("/images/Clustering/Done.png");
		Thread.sleep(3000);
	}
	
	
	/**
	 * Author: Vedika 
	 * The following is the code for Clicking Add Button
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	public void clickAddButton() throws InterruptedException, FindFailed {
		clickTarget("/images/TrailerCreation/AddButton.png");
		Thread.sleep(3000);
	}
	
	
	
	/**
	 * Author: Vedika 
	 * The following is the code for toggling maintenance mode
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	public void toggleMaintenanceMode() throws InterruptedException, FindFailed {
		screen.click();
		screen.rightClick();
		Thread.sleep(4000);
		screen.click("/images/TrailerCreation/ToggleMaintenanceMode.png");
		Thread.sleep(3000);
	}

	
	/**
	 * Author: Vedika 
	 * The following is the code for choosing a value from options
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	public void chooseFromOptions(String image) throws InterruptedException, FindFailed {

		screen.wait(image, timeoutInSec);
	    	Match status = screen.find(image);
	    	screen.click(status.getCenter().offset(155, 0));
	    	Thread.sleep(3000);
		clickTarget("/images/JDAHome/Search_button.png");
		Thread.sleep(2000);
		clickTarget("/images/DockScheduler/Schedule/BookingDetails/Ok.png");
	    
	}

	/**
	 * Author: Vedika 
	 * The following is the code for choosing current date
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 */
	public void chooseCurrentDate() throws InterruptedException, FindFailed {

		typeString("0");
		typeKey("ENTER");
	}
	

}

