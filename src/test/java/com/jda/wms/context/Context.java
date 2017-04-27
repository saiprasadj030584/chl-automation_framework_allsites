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
	private String tagId;
	private String status;
	private int qtyOnHandBfrAdjustment;
	private int caseRatio;
	private String code;
	private String adjustmentType;
	private String supplierID;
	private String expiryDate;
	private int qtyReceivedfromPutty;

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

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
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

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getTagId() {
		return tagId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setqtyOnHandBeforeAdjustment(int qtyOnHandBfrAdjustment) {
		this.qtyOnHandBfrAdjustment = qtyOnHandBfrAdjustment;
	}

	public int getQtyOnHandBeforeAdjustment() {
		return qtyOnHandBfrAdjustment;
	}

	public void setCaseRatio(int caseRatio) {
		this.caseRatio = caseRatio;
	}

	public int getCaseRatio() {
		return caseRatio;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public String getAdjustmentType() {
		return adjustmentType;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public String getFutureExpiryDate() {
		return expiryDate;
	}

	public void setFutureExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getQtyReceivedfromPutty() {
		return qtyReceivedfromPutty;
	}

	public int setQtyReceivedfromPutty() {
		return qtyReceivedfromPutty ;
	}
}
