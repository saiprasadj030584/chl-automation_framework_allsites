package com.jda.wms.pages.gm;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

public class Verification {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	public Verification() {
	}

	public ArrayList<String> verifyData(String fieldName, String expectedResults, String actualResults,

			ArrayList<String> failureList) {
		System.out.println(fieldName + " - expected [" + expectedResults + "] and actual [" + actualResults + "]");
		logger.debug(fieldName + " - expected [" + expectedResults + "] and actual [" + actualResults + "]");
		if (expectedResults.equalsIgnoreCase("Not Null")) {
			if (null == actualResults) {
				failureList.add(fieldName + " does not match. Expected  [Not Null] but was [" + actualResults + "]");
			}
		} else {
			if (!expectedResults.contains(actualResults)) {
				failureList.add(fieldName + " does not match. Expected  [" + expectedResults + "] but was ["
						+ actualResults + "]");
			}
		}
		return failureList;
	}

	public ArrayList<String> verifyDataEempty(String fieldName, String expectedResults, String actualResults,
			ArrayList<String> failureList) {
		logger.debug(fieldName + " - expected [" + expectedResults + "] and actual [" + actualResults + "]");
		if (expectedResults.equalsIgnoreCase("Not Null")) {
			if (null != actualResults) {
				failureList.add(fieldName + ". Expected  [Null] but was [" + actualResults + "]");
			}
		} else {
			if (expectedResults.contains(actualResults)) {
				failureList.add(fieldName + " does match. Expected 'null' but was [" + actualResults + "]");
			}
		}
		return failureList;
	}
}
