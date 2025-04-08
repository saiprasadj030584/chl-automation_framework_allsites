package com.mns.auto.cd.pages;

import com.google.inject.Inject;
import com.smartbear.cucumber.TestComplete;

public class GlobalNDC {
	public static TestComplete tc;

	@Inject
	public GlobalNDC() {

	}

	public TestComplete TC() {
		if (tc == null) {
			System.out.println("entered");
			tc = new TestComplete(
					// Path to the TestComplete suite
					// "D:\\BDD TC\\TestComplete-Project-TestingNotepad\\TestingNotepad.pjs",
					"C:/Automation/CD_Automation/NDC_Auto/CD_Regression.pjs",
					// The name of the test project
					"CD_Regression",
					// The name of the log
					"CD_Regression Log");
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
