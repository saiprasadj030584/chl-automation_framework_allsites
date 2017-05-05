package com.jda.wms.hooks;

import java.util.List;

import com.jda.wms.dao.CheckStringDetailsJsonDao;
import com.jda.wms.exception.DataException;

public class test {

	public static void main(String[] args) throws DataException {
		CheckStringDetailsJsonDao checkStringDetailsJsonDao = new CheckStringDetailsJsonDao();
		List<String> cs = checkStringDetailsJsonDao.getCheckStringDetails().get(0).getCheckString();
		System.out.println(cs.size());
	}

}
