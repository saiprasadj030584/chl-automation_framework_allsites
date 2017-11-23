package com.jda.wms.pages.gm;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class PackConfigLinkingPage {
	Screen screen = new Screen();
	int timeoutInSec = 20;

	@Inject
	public PackConfigLinkingPage() {
	}

	public void clickPackConigLinkingToSku() throws FindFailed, InterruptedException {
		screen.wait("images/JDAPackConfigLinking/linkPackConfigToSku.png", timeoutInSec);
		screen.click("images/JDAPackConfigLinking/linkPackConfigToSku.png");
		
	}
	
	public void enterSkuId(String sku) throws FindFailed, InterruptedException {
		screen.type(sku);
		Thread.sleep(2000);
	}
	
	public void selectLinkPackConfig() throws FindFailed, InterruptedException {
		screen.wait("/images/JDAPackConfigLinking/PackconfigTab.png", timeoutInSec);
		Match mASN = screen.find("/images/JDAPackConfigLinking/PackConfigTab.png");
		Thread.sleep(2000);
		screen.click(mASN.below(10));
		Thread.sleep(2000);
		screen.doubleClick(mASN.below(10));
		screen.click("images/JDAPackConfigLinking/LinkToAll.png");
		
	}
	
	public void selectPackConfigForUnlink() throws FindFailed, InterruptedException {
		screen.wait("/images/JDAPackConfigLinking/PackConfigForUnlink.png", timeoutInSec);
		Match mASN = screen.find("/images/JDAPackConfigLinking/PackConfigForUnlink.png");
		Thread.sleep(2000);
		screen.click(mASN.below(10));
		Thread.sleep(2000);
		screen.doubleClick(mASN.below(10));
		screen.click("images/JDAPackConfigLinking/UnlinkSelected.png");
		
	}
}
