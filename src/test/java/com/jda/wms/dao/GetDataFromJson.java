package com.jda.wms.dao;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import com.jda.wms.exception.DataException;
import com.jda.wms.pages.PageObject;

public class GetDataFromJson extends PageObject {

	private final CheckStringDetailsJsonDao checkStringDetailsJsonDao;

	@Inject
	public GetDataFromJson(WebDriver webDriver, CheckStringDetailsJsonDao checkStringDetailsJsonDao) {
		super(webDriver);
		this.checkStringDetailsJsonDao = checkStringDetailsJsonDao;
	}

	public List<String> getCheckString() throws DataException {
		return checkStringDetailsJsonDao.getCheckStringDetails().get(0).getCheckString();
	}

	public List<String> getCheckStrings() throws DataException {
		return checkStringDetailsJsonDao.getCheckStringDetails().get(0).getCheckString();
	}

}
