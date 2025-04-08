package com.mns.auto.cd.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.google.inject.Inject;

public class SAPScriptExecutionPage {

	@Inject
	public SAPScriptExecutionPage() {

	}

	public void triggerTheInterfaceI102FromSap() throws IOException, InterruptedException {

		Process p = Runtime.getRuntime()
				.exec("cscript src/test/resources/VBScripts/Scripts/Donington_Postal Receipts/TC01_I102.vbs ");
		p.waitFor(15, TimeUnit.MINUTES);
	} 
	
	public void triggerTheInterfaceI181FromSap() throws IOException, InterruptedException {

		Process p = Runtime.getRuntime()
				.exec("cscript src/test/resources/VBScripts/Scripts/Donington_Postal Receipts/TC01_I181 RETURNS -june 5th.vbs ");
		p.waitFor(15, TimeUnit.MINUTES);
	}

}
