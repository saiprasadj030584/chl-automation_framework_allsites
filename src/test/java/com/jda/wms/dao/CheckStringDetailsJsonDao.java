package com.jda.wms.dao;

import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.jda.wms.dataObject.CheckString;
import com.jda.wms.exception.DataException;

public class CheckStringDetailsJsonDao {
	private static String CHECK_STRING_PATH = "./src/test/resources/data/checkString.json";

	@Inject
	private JsonDataLoader jsonDataLoader;

	public List<CheckString> getCheckStringDetails() throws DataException {
		return Arrays.asList(jsonDataLoader.getData(CHECK_STRING_PATH, CheckString[].class));
	}
}
