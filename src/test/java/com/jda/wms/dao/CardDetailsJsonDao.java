package com.jda.wms.dao;

import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.jda.wms.exception.DataException;
import com.jda.wms.model.Card;

public class CardDetailsJsonDao {
	private static String CARD_PATH = "./src/test/resources/data/card.json";

	@Inject
	private JsonDataLoader jsonDataLoader;

	public List<Card> getCardDetails() throws DataException {
		return Arrays.asList(jsonDataLoader.getData(CARD_PATH, Card[].class));
	}
}