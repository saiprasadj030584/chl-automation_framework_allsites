package com.jda.wms.pages.gm;


import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;



import cucumber.api.java.en.Then;

public class PickFacePage {
	Screen screen = new Screen();
	int timeoutInSec =20;
	private Context context;
	
	
	@Inject
	public PickFacePage(Context context) {
		this.context = context;
	
		
	}

	public void TypeinTextBox(String text)throws FindFailed, InterruptedException{
//		 screen.click("images/OrderGrouping/Waveplantextbox.png");
		 Thread.sleep(1000);
		 screen.type(text);
			System.out.println("Type "+text);
	 }

}
