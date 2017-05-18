package com.jda.wms.db;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class OrderLineDB {
	private  Context context;
	private Database database;
	
	@Inject
	public OrderLineDB(Context context,Database database) {
		this.context = context;
		this.database = database;

	}
}
