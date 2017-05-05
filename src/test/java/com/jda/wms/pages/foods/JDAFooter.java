package com.jda.wms.pages.foods;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class JDAFooter {

	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public JDAFooter() {
	}

	public void clickOrderHeaderFooterButton() throws FindFailed, InterruptedException {
		screen.wait("images/OrderHeaderFooter.png", timeoutInSec);
		screen.click("images/OrderHeaderFooter.png");
		Thread.sleep(3000);
	}

	public void clickQueryButton() throws FindFailed, InterruptedException {
		screen.type(Key.F2);
		Thread.sleep(3000);
	}

	public void clickAddButton() throws FindFailed, InterruptedException {
		screen.type(Key.F3);
		Thread.sleep(3000);
	}

	public void clickUpdateButton() throws FindFailed, InterruptedException {
		screen.type(Key.F4);
		Thread.sleep(3000);
	}

	public void clickDeleteButton() throws FindFailed, InterruptedException {
		screen.type(Key.F6);
		Thread.sleep(3000);
	}

	public void clickExecuteButton() throws FindFailed, InterruptedException {
		screen.type(Key.F7);
		Thread.sleep(4000);
	}

	public void clickCancelButton() throws FindFailed, InterruptedException {
		screen.type(Key.F8);
		Thread.sleep(3000);
	}

	public void clickLessButton() throws FindFailed, InterruptedException {
		screen.type(Key.F9);
		Thread.sleep(3000);
	}

	public void clickMoreButton() throws FindFailed, InterruptedException {
		screen.type(Key.F10);
		Thread.sleep(3000);
	}

	public void clickCloseButton() throws FindFailed, InterruptedException {
		screen.type(Key.ESC);
		Thread.sleep(3000);
	}

	public void clickNextButton() throws FindFailed, InterruptedException {
		screen.type(Key.F7);
		Thread.sleep(3000);
	}

	public void clickDoneButton() throws FindFailed, InterruptedException {
		screen.type(Key.F12);
		Thread.sleep(3000);
	}
	
	public void clickNextRecord() throws FindFailed, InterruptedException {
		screen.wait("images/JDAFooter/NextRecord.png", timeoutInSec);
		screen.click("images/JDAFooter/NextRecord.png");
		Thread.sleep(3000);
	}
	
	public void clickPackConfig() throws FindFailed, InterruptedException {
		screen.wait("images/JDAFooter/PackConfig.png", timeoutInSec);
		screen.click("images/JDAFooter/PackConfig.png");
		Thread.sleep(3000);
	}
	
	public void clickPreAdiceLine() throws FindFailed, InterruptedException {
		screen.wait("images/JDAFooter/PreAdviceLine.png", timeoutInSec);
		screen.click("images/JDAFooter/PreAdviceLine.png");
		Thread.sleep(3000);
	}
	
	public void clickSku() throws FindFailed, InterruptedException {
		screen.wait("images/JDAFooter/Sku.png", timeoutInSec);
		screen.click("images/JDAFooter/Sku.png");
		Thread.sleep(2000);
	}
}
