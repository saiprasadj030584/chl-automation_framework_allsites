package com.jda.wms.dao;

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

	public CheckString getCheckString() throws DataException {
		List<CheckString> checkStringList = checkStringDetailsJsonDao.getCheckStringDetails();
		return checkStringList;
	}

	// private Card getCardDetail(String type, List<Card> cardList) {
	// Card card = null;
	// for (int index = 0; index < cardList.size(); index++) {
	// if (cardList.get(index).getType().equals(type)) {
	// card = cardList.get(index);
	// break;
	// }
	// }
	// return card;
	// }

}