package com.mns.auto.cd.email;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.mns.auto.cd.config.Configuration;
import com.mns.auto.cd.memory.KeepInMemory;

public class SendEmail {

	private final EmbeddedImageEmailUtil embeddedImageEmailUtil;
	private final Configuration configuration;

	@Inject
	public SendEmail(Configuration configuration, EmbeddedImageEmailUtil embeddedImageEmailUtil) {
		this.embeddedImageEmailUtil = embeddedImageEmailUtil;
		this.configuration = configuration;
	}

	@SuppressWarnings({ "resource" })
	public void triggerEmailAutomatedTestResults() throws IOException {

		String host = configuration.getStringProperty("db-mnsEmailHost");
		String port = configuration.getStringProperty("db-mnsEmailPort");
		String mailFrom = configuration.getStringProperty("db-mnsEmailFrom");
		String password = configuration.getStringProperty("db-mnsEmailPassword");

		String subject = "Foods PLuM Automation Test Results : ";
		// + requestDetailsRetriever.getRegion(context.getParentRequestId()) + "
		// Environment";
		String body = ".\\results\\TEST_REPORT.html";

		InputStream is = new FileInputStream(body);
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));
		String line = buf.readLine();
		StringBuilder sb = new StringBuilder();

		while (line != null) {
			sb.append(line).append("\n");
			line = buf.readLine();
		}
		String fileAsString = sb.toString();

		Map<String, String> inlineImages = new HashMap<String, String>();
		inlineImages.put("image1", "images/tcs.jpg");
		inlineImages.put("image2", "images/mns.jpg");

		try {
			embeddedImageEmailUtil.send(host, port, mailFrom, password, subject, fileAsString, inlineImages);
			System.out.println("Email sent successfully to our team members");
		} catch (Exception ex) {
			System.out.println("Could not send email.");
			ex.printStackTrace();
		}
	}
}
