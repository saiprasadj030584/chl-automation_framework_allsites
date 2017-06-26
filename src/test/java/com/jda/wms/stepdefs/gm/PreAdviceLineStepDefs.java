package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.PreAdviceLineDB;
import com.jda.wms.db.gm.SkuDB;
import com.jda.wms.db.gm.UPIReceiptLineDB;
import com.jda.wms.pages.gm.Verification;
import cucumber.api.java.en.*;

public class PreAdviceLineStepDefs {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Context context;
	private Verification verification;
	private PreAdviceLineDB  preAdviceLineDB;
	private UPIReceiptLineDB  upiReceiptLineDB;
	private SkuDB skuDB;
	
	@Inject
	public PreAdviceLineStepDefs(Context context,Verification verification,PreAdviceLineDB  preAdviceLineDB,UPIReceiptLineDB  upiReceiptLineDB,SkuDB skuDB) {
		this.context = context;
		this.verification = verification;
		this.preAdviceLineDB= preAdviceLineDB;
		this.upiReceiptLineDB = upiReceiptLineDB;
		this.skuDB = skuDB;
	}


	@Given("^the PO should have sku, quantity due details$")
	public void the_PO_should_have_sku_quantity_due_details() throws Throwable {
		ArrayList failureList = new ArrayList();
		ArrayList skuFromPO = new ArrayList();
		ArrayList skuFromUPI = new ArrayList();
		Map<Integer, Map<String, String>> POMap = new HashMap<Integer, Map<String, String>>();
		Map<String, Map<String, String>> UPIMap = new HashMap<String, Map<String, String>>();
		
		skuFromPO = preAdviceLineDB.getSkuIdList(context.getPreAdviceId());
		skuFromUPI = upiReceiptLineDB.getSkuIdList(context.getUpiId());
		
		if (!skuFromPO.containsAll(skuFromUPI)){
			for (int i=0;i<skuFromPO.size();i++){
				if (!skuFromPO.get(i).equals(skuFromUPI.get(i))){
					failureList.add("SKU id "+skuFromPO.get(i)+" from PreAdvice does not match with SKU id "+skuFromUPI+" from UPI for line item "+i);
				}
			}
		}
		else{
			//Add SKU details to PO Map
			for (int i=1;i<=context.getNoOfLines();i++){
				Map<String, String> lineItemsMap = new HashMap<String, String>();
				context.setSkuId((String)skuFromPO.get(i-1));
				lineItemsMap.put("SKU", context.getSkuId());
				lineItemsMap.put("QTY DUE",preAdviceLineDB.getQtyDue(context.getPreAdviceId(), context.getSkuId()));
				lineItemsMap.put("LINE ID",preAdviceLineDB.getLineId(context.getPreAdviceId(), context.getSkuId()));
				POMap.put(i, lineItemsMap);
			}
			context.setPOMap(POMap);
			
			//Add SKU details to UPI Map
			for (int i=1;i<=context.getNoOfLines();i++){
				Map<String, String> lineItemsMap = new HashMap<String, String>();
				context.setSkuId((String)skuFromPO.get(i-1));
//				lineItemsMap.put("SKU", context.getSkuId());
				lineItemsMap.put("QTY DUE",upiReceiptLineDB.getQtyDue(context.getUpiId(),context.getSkuId()));
				lineItemsMap.put("LINE ID",upiReceiptLineDB.getLineId(context.getUpiId(),context.getSkuId()));
				lineItemsMap.put("PACK CONFIG",upiReceiptLineDB.getPackConfig(context.getUpiId(),context.getSkuId()));
				lineItemsMap.put("UPC", "");
				UPIMap.put(context.getSkuId(), lineItemsMap);
			}
			context.setUPIMap(UPIMap);
			
			System.out.println("PO Map "+context.getPOMap());
			System.out.println("UPI Map "+context.getUPIMap());
			
			//To Validate Modularity,New Product Check for SKU
			String type = null;
			switch (context.getSKUType()){
			case "Boxed": type="B";break;
			case "Hanging": type="H";break;
			}
			verification.verifyData("SKU Type", type, skuDB.getSKUType(context.getSkuId()), failureList);
			verification.verifyData("New Product", "N", skuDB.getNewProductCheckValue(context.getSkuId()), failureList);
		}
		
		Assert.assertTrue("PO line item attributes not displayed as expected. [" +Arrays.asList(failureList.toArray()) + "].",failureList.isEmpty());

	}}
