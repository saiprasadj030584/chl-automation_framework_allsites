package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;

import com.google.inject.Inject;

public class StockCreationForAlocation {
	
	private StockMaintainStepDefs stockMaintainStepDefs;

	@Inject
	public StockCreationForAlocation(StockMaintainStepDefs stockMaintainStepDefs) {
		this.stockMaintainStepDefs = stockMaintainStepDefs;
	}
	
	public void createStockbeforeAllocation(ArrayList skuList, String uniqueTag) throws Throwable{
	stockMaintainStepDefs.i_maintain_stock_in_inventory(skuList,uniqueTag);
	}
	
	public void createStockbeforeAllocationWriteOff(ArrayList skuList, String uniqueTag) throws Throwable{
		stockMaintainStepDefs.i_maintain_stock_in_inventory_write_off(skuList,uniqueTag);
		}
}
