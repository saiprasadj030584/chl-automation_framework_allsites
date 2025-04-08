package com.jda.wms.dataload;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class GetTCData {
	private static Context context;
	
	@Inject
	public GetTCData(Context context) {
		this.context = context;
	}
	

	public void setPo(String poId) {
		context.setPreAdviceId(poId);
	}

	public String getPO() {
		return context.getPreAdviceId();
	}
	public void setpoId(String poId) {
		context.setpoId(poId);
	}

	public static String getpoId() {
		return context.getpoId();
	}
	
	public void setSto(String stoId) {
		context.setOrderId(stoId);
	}

	public String getSto() {
		return context.getOrderId();
	}

	public void setpoId2(String poId2) {
		context.setpoId2(poId2);
	}

	public static String getpoId2() {
		return context.getpoId2();
	}
	public void setSto2(String stoId2) {
		context.setOrderId2(stoId2);
	}

	public String getSto2() {
		return context.getOrderId2();
	}
}
