package com.mns.auto.cd.pages;

import com.google.inject.Inject;
import com.smartbear.cucumber.TestComplete;

public class GlobalEDC {
	public static TestComplete tc;
	
	@Inject
	public GlobalEDC() {

	}
	
	//C:\RegressionAutomation\Donington_014177MB\cloneSample\LH012183_Boxed\Boxed_cloned

	public TestComplete TC() {
		if (tc == null) {
			System.out.println("entered");
			tc = new TestComplete(
					// Path to the TestComplete suite
					// "D:\\BDD TC\\TestComplete-Project-TestingNotepad\\TestingNotepad.pjs",
					"C:\\Automation\\CD_Automation\\EDC_Automation\\EDC_CD\\EDC_CD.pjs",
					// The name of the test project
					"EDC_CD",
					// The name of the log
					"EDC_CD Log");
			System.out.println("exit");
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					try {
						// Export log
						GlobalEDC.tc.ExportLog();
						// Stop playback, after test finishes
						GlobalEDC.tc.GetIntegration().Stop();
						// Close TestComplete, after test finishes
						// World.tc.Quit();
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			});
		}
		return tc;
	}

}
