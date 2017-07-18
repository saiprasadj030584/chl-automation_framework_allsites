package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class ReceiptReversalPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public ReceiptReversalPage() {
	}

	public void selectReceiptType(String receiptType) throws FindFailed, InterruptedException {
		screen.type(receiptType);
	}
	
	

	public void enterTagId(String tagId) throws InterruptedException, FindFailed {
		Match mtagId = screen.find("images/ReceiptReversal/Start/TagID.png");
		screen.click(mtagId.getCenter().offset(70, 0));
		screen.type(tagId);
	}
	
	public void checkTheCheckbox() throws InterruptedException, FindFailed {
		Match mtagId = screen.find("images/ReceiptReversal/Reversals/checkbox.png");
		screen.click(mtagId.getCenter());
		
	}

	public boolean isRecordExists() throws InterruptedException, FindFailed {
		if (!screen.find("images/ReceiptReversal/Reversals/Pallet.png").equals(null)) {
			return true;
		} else
			return false;
	}

	public void scrollNext() throws FindFailed {
		screen.wait("images/ReceiptReversal/ScrollNext.png", timeoutInSec);
		screen.click("images/ReceiptReversal/ScrollNext.png");
	}

	public void enterQtyToReverse(int qtyReverse) throws FindFailed, InterruptedException {
		Match mQtyToReverse = screen.find("images/ReceiptReversal/Reversals/QtyToReverse.png");
		Thread.sleep(2000);
		screen.click(mQtyToReverse.below(10));
		screen.type(Integer.toString(qtyReverse));
		Thread.sleep(3000);
	}

	public void enterReasonCode(String reasonCode) throws FindFailed {
		screen.type(reasonCode);
	}
	public void checkReversalUpdationInventory(String tagId) throws FindFailed, InterruptedException {
		screen.type(Key.F2);
		Thread.sleep(3000);
	
		screen.type("Receipt Reversal");
		screen.type(Key.TAB);
		Thread.sleep(1000);
		screen.type(Key.TAB);
		Thread.sleep(1000);
		screen.type(Key.TAB);
		Thread.sleep(1000);
		screen.type(Key.TAB);
		Thread.sleep(1000);
		screen.type(tagId);
		
		screen.type(Key.F7);
	}

	public boolean isQtyToReverseExists() throws FindFailed {
		if (screen.find("images/ReceiptReversal/Reversals/QtyToReverse.png").equals(null)) {
			return false;
		} else
			return true;

	}
	
	public boolean checkRefeIDwithPreadviceID(String refId,String preAdviceId)
	{
		if(refId.equals(preAdviceId))
		{
			return true;
		}
		else
			return false;
	}
	public boolean checkRefeIDwithPreadviceIDlockcode(String refId,String preAdviceId,String lockcode1,String lockcode2)
	{
		if(refId.equals(preAdviceId))
		{
			if(lockcode1.equals(lockcode2))
			{
			return true;
			}
			else
			{
				return false;
			}
		}
		else
			return false;
	}
}
