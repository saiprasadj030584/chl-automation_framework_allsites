package com.jda.wms.pages.foods;

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

		logger.debug(fieldName + " - expected [" + expectedResults + "] and actual [" + actualResults + "]");

		if (expectedResults.equals("Not Null")) {
			if (actualResults.equals(null)) {
				failureList.add(fieldName + " does not match. Expected  [Not Null] but was [" + actualResults + "]");
			}
		} else {
			if (!actualResults.contains(expectedResults)) {
				failureList.add(fieldName + " does not match. Expected  [" + expectedResults + "] but was ["
						+ actualResults + "]");
			}
		}
		return failureList;
	}
}
