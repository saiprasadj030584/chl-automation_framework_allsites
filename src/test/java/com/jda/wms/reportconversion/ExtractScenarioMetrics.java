package com.jda.wms.reportconversion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class ExtractScenarioMetrics {
	
	Map<String, Map<String,String>> scenarioMetrics = new HashMap<String, Map<String,String>>();
	private Context context;
	
	@Inject
	public ExtractScenarioMetrics(Context context) {
		this.context = context;
	}

	public void getFeatureDetails(String filePath) throws IOException {
		String fileContent = null;
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
//		System.out.println(fileContent);
		
		//To get Feature Name
		getFeatureName(fileContent);
		
		//To get Scenarios executed
		getScenarioDetails(fileContent);
	}

	private void getScenarioDetails(String fileContent) throws IOException {
		String [] fileSplit = fileContent.split("<tbody>");
		String [] fileSplit1 = fileSplit[2].split("brief");
		for (int j=0; j<fileSplit1.length;j++){
			String tempLine = fileSplit1[j];
			if (tempLine.contains(">Scenario")){
				String [] fileSplit2 = tempLine.split(">  <span class");
					if (fileSplit2[0].contains("failed")){
						System.out.println("Failed");
					}
					else if (fileSplit2[0].contains("passed")){
						System.out.println("Passed");
					}
					String [] fileSplit3 = tempLine.split("\"name\">");
					String [] fileSplit4 = fileSplit3[1].split("</span>");
					System.out.println(fileSplit4[0]);
			}
		}
	}

	private void getFeatureName(String fileContent) {
		String [] fileSplit = fileContent.split("</title>");
		String [] fileSplit1 = fileSplit[0].split("Feature: ");
		//featureName.add(fileSplit1[1]);
	}
}
