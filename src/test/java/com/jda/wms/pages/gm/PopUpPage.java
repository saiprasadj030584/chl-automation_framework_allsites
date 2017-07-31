package com.jda.wms.pages.gm;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
public class PopUpPage {
	
	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void clickYes() throws FindFailed, InterruptedException {
		screen.type(Key.ENTER);
		Thread.sleep(4000);
		}
	
	public boolean isPopUpDisplayed() throws InterruptedException, FindFailed {
		if (screen.exists("images/PreAdviceLine/Yes.png") != null)
			return true;
		else
			return false;
		}
	}
	


