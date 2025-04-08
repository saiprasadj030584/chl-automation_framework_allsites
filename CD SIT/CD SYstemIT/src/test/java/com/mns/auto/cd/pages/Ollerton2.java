package com.mns.auto.cd.pages;

import com.google.inject.Inject;
import com.smartbear.cucumber.TestComplete;

public class Ollerton2 {

	public static TestComplete tc;

	@Inject
	public Ollerton2() {

	}

	public TestComplete TC() {
		if (tc == null) {
			System.out.println("entered");
			tc = new TestComplete("/OLERTON2",
					"Ollerton_Regression", "Ollerton_Regression Log");
			System.out.println("exit");
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					try {
						// Export log
						Ollerton2.tc.ExportLog();
						// Stop playback, after test finishes
						Ollerton2.tc.GetIntegration().Stop();
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
