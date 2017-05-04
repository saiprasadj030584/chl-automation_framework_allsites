package com.jda.wms.pages.foods;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class PreAdviceLinePage {
	private Context context;

	@Inject
	public PreAdviceLinePage(Context context) {
		this.context = context;
	}

	Screen screen = new Screen();
	int timeoutInSec = 20;

	public void enterPreAdviceId(String preAdviceId) throws FindFailed, InterruptedException {
		screen.wait("images/PreAdviceLine/PreAdviceId.png", timeoutInSec);
		screen.click("images/PreAdviceLine/PreAdviceId.png");
		screen.type(preAdviceId);
		Thread.sleep(1000);
	}

	public void enterSKUId(String skuId) throws FindFailed, InterruptedException {
		screen.wait("images/PreAdviceLine/Sku.png", timeoutInSec);
		screen.click("images/PreAdviceLine/Sku.png");
		screen.type(skuId);
		Thread.sleep(1000);
	}

	public void enterLockCode(String lockCode) throws FindFailed, InterruptedException {
		Match mLockCode = screen.find("images/PreAdviceLine/lockCode.png");
		screen.click(mLockCode.getCenter().offset(80, 0));
		screen.type("a", Key.CTRL);
		screen.type(Key.BACKSPACE);
		Thread.sleep(1000);
		
		switch (lockCode) {
		case "Code Approval":
			screen.type("CODEAPP");
			context.setLockCode("CODEAPP"); 
			Thread.sleep(2000);
			break;
		case "Components Stock":
			screen.type("CS");
			context.setLockCode("CS");
			Thread.sleep(2000);
			break;
		case "1Damaged":
			screen.type("DMGD");
			context.setLockCode("DMGD");
			Thread.sleep(2000);
			break;
		case "1Expired":
			screen.type("EXPD");
			context.setLockCode("EXPD");
			Thread.sleep(2000);
			break;
		case "Head Office Request":
			screen.type("HOREQ");
			context.setLockCode("HOREQ");
			Thread.sleep(2000);
			break;
		case "Outlets Stock":
			screen.type("OS");
			context.setLockCode("OS");
			Thread.sleep(2000);
			break;
		case "Product Recall":
			screen.type("PRODRECALL");
			context.setLockCode("PRODRECALL");
			Thread.sleep(2000);
			break;
		case "Return from RDC":
			screen.type("RDCRETURNS");
			context.setLockCode("CODEAPP");
			Thread.sleep(2000);
			break;
		case "Supplier Damage":
			screen.type("SUDMG");
			context.setLockCode("SUDMG");
			Thread.sleep(2000);
			break;
		case "Return to Supplier":
			screen.type("SUPPRETURN");
			context.setLockCode("SUPPRETURN");
			Thread.sleep(2000);
			break;
		case "Warehouse Damage":
			screen.type("WHDMG");
			context.setLockCode("WHDMG");
			Thread.sleep(2000);
			break;
		case "Hampers Stock":
			screen.type("HS");
			context.setLockCode("HS");
			Thread.sleep(2000);
			break;
		case "Incubation":
			screen.type("INCUB");
			context.setLockCode("INCUB");
			Thread.sleep(2000);
			break;
		}
	}

	public String getLockCode() throws FindFailed {
		Match mDescription = screen.find("images/PreAdviceLine/Lock.png");
		screen.click(mDescription.getCenter().offset(80, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
}
