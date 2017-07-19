package com.jda.wms.reportconversion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExtractOverviewFeatures {

	String project, date;
	ArrayList features = new ArrayList();
	ArrayList timeRunPerFeature = new ArrayList();
	ArrayList featureStatus = new ArrayList();
	ArrayList passedScenarios = new ArrayList();
	ArrayList failedScenarios = new ArrayList();
	ArrayList totalScenarios = new ArrayList();
	Map<Integer, Map<String, String>> featuresMap = new HashMap<Integer, Map<String, String>>();
	static String fileContent = null;

	static void readOverviewFeatures(String filePath) throws IOException {
		File f = new File(filePath);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null) {
			if (fileContent == null) {
				fileContent = line;
			} else {
				fileContent += line;
			}
		}

		// To get project and date of execution
		getProjectDetails(fileContent);

		// To get metrics
		getMetricsDetails(fileContent);
	}

	private static void getMetricsDetails(String fileContent) {
		String[] fileSplit = fileContent.split("stats-table table-hover");
		String[] fileSplit1 = fileSplit[1].split("navbar navbar-default");
		String[] fileSplit2 = fileSplit1[0].split("<tbody>");
		String[] fileSplit3 = fileSplit2[1].split("</tr>");

		for (int i = 0; i < (fileSplit3.length) - 3; i++) {
			String[] fileSplit4 = fileSplit3[i].split("</td>");
			for (int r = 0; r < fileSplit4.length; r++) {
				String[] fileSplit5 = fileSplit4[r].split(">");
			}

			// To get the feature name
			String[] splt1 = fileSplit4[0].split(".html");
			String featureName = splt1[1].substring(2, splt1[1].length() - 4);

			// To get passed scenario
			String[] splt2 = fileSplit4[7].split("<td >");
			String passedScenario = splt2[0];

			// To get failed scenario
			String[] splt3 = fileSplit4[8].split("<td >");
			String failedScenario = splt2[0];

			// To get total scenarios
			String[] splt4 = fileSplit4[9].split("<td >");
			String totalScenario = splt2[0];

			// To get duration
			String[] splt5 = fileSplit4[10].split("<td >");
			String duration = splt2[0];

			// To get scenario status
			String[] splt6 = fileSplit4[11].split("<td >");
			String status = splt2[0];

			// if (passedScenario.equals(" ")) {
			// }
			// Map<String, String> featureDetailsMap = new HashMap<String,
			// String>();

		}

		// String[] fileSplit2 = fileSplit[0].split("thead");

	}

	private static void getProjectDetails(String fileContent) {
		String[] fileSplit = fileContent.split("</head>");
		String[] fileSplit1 = fileSplit[1].split("report-lead");
		String[] fileSplit2 = fileSplit1[0].split("<tbody>");
		String[] fileSplit3 = fileSplit2[1].split("<td>");
		String[] projectSplit = fileSplit3[1].split("</td>");
		String[] dateSplit = fileSplit3[3].split("</td>");

		String project = projectSplit[0];
		String date = dateSplit[0];
	}
}
