package com.mns.auto.cd.pages;

import com.google.inject.Inject;
import com.mns.auto.cd.config.Constants;
import com.smartbear.cucumber.TestComplete;

public class OllertonAr {

	public static TestComplete tc;
	
	//private final Constants constants;

	@Inject
	public OllertonAr() {

	}

	public TestComplete TC() {
		if (tc == null) {
			System.out.println("entered");
			tc = new TestComplete(Constants.OLLERTON1,
					"OllertonRegression", "OllertonRegression Log");
			System.out.println("exit");
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					try {
						// Export log
						OllertonAr.tc.ExportLog();
						// Stop playback, after test finishes
						OllertonAr.tc.GetIntegration().Stop();
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
