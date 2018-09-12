package com.jda.wms.dataload.exit;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class GetTCData {
	private Context context;
	
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
	
	public void setSto(String stoId) {
		context.setOrderId(stoId);
	}

	public String getSto() {
		return context.getOrderId();
	}
}
