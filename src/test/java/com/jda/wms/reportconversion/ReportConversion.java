package com.jda.wms.reportconversion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class ReportConversion {
	
	private static Context context = new Context();
	private ExtractOverviewFeatures extractOverviewFeatures;
	private ExtractScenarioMetrics extractScenarioMetrics;
	static private ArrayList featureName = new ArrayList();
	public static File[] listOfFeatureReports;
	
	@Inject
	public ReportConversion(ExtractOverviewFeatures extractOverviewFeatures,ExtractScenarioMetrics extractScenarioMetrics) {
		this.extractOverviewFeatures = extractOverviewFeatures;
		this.extractScenarioMetrics = extractScenarioMetrics;
	}
	
	public static void main(String[] args) throws IOException {
		context.setProjLoc(System.getProperty("user.dir"));
		File folderPath = new File(context.getProjLoc() + "\\target\\cucumber-html-reports");

		// To read overview-features.html file to fetch the project run details
		// and main metrics
//		extractOverviewFeatures.readOverviewFeatures(folderPath + "\\overview-features.html");
		
		//To get Feature and scenario details 
		listOfFeatureReports = folderPath.listFiles();
		for (int i=0; i <listOfFeatureReports.length;i++){
			if (listOfFeatureReports[i].getName().contains("feature")&&(!listOfFeatureReports[i].getName().contains("features"))){
//				System.out.println(listOfFeatureReports[i].getName());
			//	extractScenarioMetrics.getFeatureDetails(folderPath+"\\"+listOfFeatureReports[i].getName());
			}
		}
		
//		extractScenarioMetrics.getFeatureDetails(folderPath+"\\report-feature_purchaseorder_receiving-feature.html");
	}
}
