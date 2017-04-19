package com.jda.wms.stepdefs.foods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.pages.foods.SKUMaintenancePage;
import com.jda.wms.pages.foods.StockAdjustmentsPage;

public class StockAdjustmentsStepDef {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final StockAdjustmentsPage stockAdjustmentsPage;
	private Context context;
	
	@Inject
	public StockAdjustmentsStepDef(StockAdjustmentsPage stockAdjustmentsPage, Context context) {
		this.stockAdjustmentsPage = stockAdjustmentsPage;
		this.context = context;
	}
}
