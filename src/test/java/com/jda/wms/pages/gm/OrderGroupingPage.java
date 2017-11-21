package com.jda.wms.pages.gm;

import org.sikuli.script.*;




public class OrderGroupingPage {
	Screen screen = new Screen();
	int timeoutInSec =20;
	public void clickonOKButton()throws FindFailed
	{
		
		screen.wait("images/OrderGrouping/okButtonOrderGrouping.png",timeoutInSec);
		screen.click("images/OrderGrouping/okButtonOrderGrouping.png");
		System.out.println("Clicked on ok button ");
	}
	public void clickOnWaveplan() throws FindFailed {
		screen.wait("images/OrderGrouping/RunWavePlanButton.png",timeoutInSec);
		Match wavebutton = screen.find("images/OrderGrouping/RunWavePlanButton.png");
		screen.click(wavebutton);
		System.out.println("Clicked on wave plan button ");
					
	}
	public void SiteIddropbox(String site) throws FindFailed, InterruptedException {
		screen.wait("images/OrderGrouping/SiteIddropbox.png",timeoutInSec);
		Match wavebutton = screen.find("images/OrderGrouping/SiteIddropbox.png");
		screen.click(wavebutton);
		Thread.sleep(2000);
		screen.type(site);	
		Thread.sleep(2000);
		System.out.println("Clicked on site id drop box");
	}
	public void TypeinWaveplanTextBox(String wavejob)throws FindFailed, InterruptedException{
//		 screen.click("images/OrderGrouping/Waveplantextbox.png");
		 Thread.sleep(1000);
		 screen.type(wavejob);
			System.out.println("Type in wave plan text box button ");
	 }
	public void ClickNextButton() throws FindFailed{
		// screen.click("images/OrderGrouping/NextButton.png");
		screen.type(Key.F7);
		 
	 }
	
	public void clickDoneButton(){
		 screen.type(Key.F12);
	}
}
