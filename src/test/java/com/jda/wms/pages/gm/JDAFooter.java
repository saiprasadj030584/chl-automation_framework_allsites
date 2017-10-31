package com.jda.wms.pages.gm;

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

	public void navigateToNextScreen() throws FindFailed, InterruptedException {
		screen.type("x", Key.CTRL);
		screen.type(Key.NUM4);
		Thread.sleep(2000);
	}

	public void clickDoneButton() throws FindFailed, InterruptedException {
		
		screen.wait("images/JDAFooter/Done.png", timeoutInSec);
		screen.click("images/JDAFooter/Done.png");
		Thread.sleep(3000);
		
	}

	public void clickPackConfig() throws FindFailed, InterruptedException {
		screen.wait("images/JDAFooter/PackConfig.png", timeoutInSec);
		screen.click("images/JDAFooter/PackConfig.png");
		Thread.sleep(3000);
	}

	public void clickPreAdviceLine() throws FindFailed, InterruptedException {
		screen.wait("images/JDAFooter/PreAdviceLine.png", timeoutInSec);
		screen.click("images/JDAFooter/PreAdviceLine.png");
		Thread.sleep(3000);
	}

	public void clickSku() throws FindFailed, InterruptedException {
		screen.wait("images/JDAFooter/Sku.png", timeoutInSec);
		screen.click("images/JDAFooter/Sku.png");
		Thread.sleep(2000);
	}

	public void clickNextRecord() throws FindFailed, InterruptedException {
		screen.wait("images/JDAFooter/NextRecord.png", timeoutInSec);
		screen.click("images/JDAFooter/NextRecord.png");
		Thread.sleep(3000);
	}

	public void clickOrderLine() throws FindFailed, InterruptedException {
		screen.wait("images/JDAFooter/OrderLine.png", timeoutInSec);
		screen.click("images/JDAFooter/OrderLine.png");
		Thread.sleep(3000);
	}

	public void PressEnter() throws InterruptedException {
		screen.type(Key.ENTER);
		Thread.sleep(2000);
	}

	public void rightClick() throws InterruptedException {
		screen.rightClick();
		Thread.sleep(2000);
	}

	public void clickSearch() throws FindFailed, InterruptedException {
		clickNextButton();
		Thread.sleep(1000);
	}

	public void pressTab() throws InterruptedException {
		screen.type(Key.TAB);
		Thread.sleep(1000);
	}

	public void deleteExistingContent() throws InterruptedException {
		screen.type("a", Key.CTRL);
		screen.type(Key.BACKSPACE);
		Thread.sleep(1000);
	}

	public void pressBackSpace() {
		screen.type(Key.BACKSPACE);
	}
	
	public void pressF12() throws InterruptedException {
		screen.type(Key.F12);
		Thread.sleep(1000);
	}

	public void clickReceiptReversalDoneButton() throws FindFailed, InterruptedException {
		screen.wait("images/InventoryTransactionQuery/receiptreversaldone.png", timeoutInSec);
		screen.click("images/InventoryTransactionQuery/receiptreversaldone.png");
		Thread.sleep(3000);
	}
}
