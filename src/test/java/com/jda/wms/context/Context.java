package com.jda.wms.context;

import com.jda.wms.pages.foods.RDTTask;

public class Context {
	private RDTTask currentTask;
	private String preAdviceId;
	private String skuId;
	private String productGroup;
	private String cewarehousetype;
	private String allocationGroup;
	private String ean;
//	private String kitId;
//	private String kitLineId;
//	private String kitSKUId;
//	private String kitQuantity;
	
	public RDTTask getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(RDTTask currentTask) {
		this.currentTask = currentTask;
	}

	public String getPreAdviceId() {
		return preAdviceId;
	}

	public void setPreAdviceId(String preAdviceId) {
		this.preAdviceId = preAdviceId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	
	public String getEAN() {
		return ean;
	}

	public void setEAN(String ean) {
		this.ean = ean;
	}
	
	public String getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}
	
	public String getAllocationGroup() {
		return allocationGroup;
	}

	public void setAllocationGroup(String allocationGroup) {
		this.allocationGroup = allocationGroup;
	}

	public void setCEWarehouseType(String cewarehousetype) {
		this.cewarehousetype = cewarehousetype;
	}
	
	public String getCEWarehouseType() {
		return cewarehousetype;
	}
	
	
//	public void setKitId(String kitId) {
//		this.kitId = kitId;
//	}
//	
//	public String getKitId() {
//		return kitId;
//	}
//	
//	public void setKitSKUId(String kitSKUId) {
//		this.kitSKUId = kitSKUId;
//	}
//	
//	public String getKitSKUId() {
//		return kitSKUId;
//	}
//	public void setKitQuantity(String kitQuantity) {
//		this.kitQuantity=kitQuantity;
//	}
//	
//	public String getKitQuantity() {
//		return kitQuantity;
//	}
//	
//	public void setKitLineId(String kitLineId) {
//		this.kitLineId = kitLineId;
//	}
//	
//	public String getKitLineId() {
//		return kitLineId;
//	}
	
}
