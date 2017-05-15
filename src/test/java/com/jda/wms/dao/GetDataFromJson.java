package com.jda.wms.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.jda.wms.dataObject.CheckString;
import com.jda.wms.exception.DataException;

public class GetDataFromJson {

	private final CheckStringDetailsJsonDao checkStringDetailsJsonDao;

	@Inject
	public GetDataFromJson(CheckStringDetailsJsonDao checkStringDetailsJsonDao) {
		this.checkStringDetailsJsonDao = checkStringDetailsJsonDao;
	}


	public void getCheckString() throws DataException {
		List<CheckString> checkStringList = checkStringDetailsJsonDao.getCheckStringDetails();
	}

}