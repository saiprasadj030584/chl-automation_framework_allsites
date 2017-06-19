package com.jda.wms.stepdefs.foods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.ShippingManifestDB;
import com.jda.wms.pages.foods.Verification;

import cucumber.api.java.en.Then;

public class ShippingManifest {
	
	private Context context;
	private ShippingManifestDB shippingManifestDB;
	private Verification verification;
	private Map<Integer, Map<String, String>> stockTransferOrderMap;
	
	@Inject
	public ShippingManifest(Context context,ShippingManifestDB shippingManifestDB,Verification verification) {
		this.context = context;
		this.shippingManifestDB = shippingManifestDB;
		this.verification = verification;
	}
	
	@Then("^the shipping manifest should be generated$")
	public void the_shipping_manifest_should_be_generated() throws Throwable {
		ArrayList<String> failureList = new ArrayList<String>();
		Map<Integer, Map<String, String>> listIDMap = context.getListIDMap();
		stockTransferOrderMap = context.getStockTransferOrderMap();

		for (int key : listIDMap.keySet()){
			String tagId = listIDMap.get(key).get("TagID");
			String skuFromList = listIDMap.get(key).get("SkuId");
			context.setQtyToMove(Integer.parseInt(listIDMap.get(key).get("QtyToMove")));
			context.setContainerId(listIDMap.get(key).get("ToContainerID"));
			context.setPalletID((listIDMap.get(key).get("ToPalletID")));
			
			for (int stoKey : stockTransferOrderMap.keySet()){
			if (skuFromList.equals(stockTransferOrderMap.get(stoKey).get("SKU"))){	
				context.setVintage(stockTransferOrderMap.get(stoKey).get("Vintage"));
				break;
			}
			}
			
			HashMap<String, String> shipmentManifestMap = shippingManifestDB
					.getShipmentManifestDetails(tagId, context.getOrderId());
			
			verification.verifyData("SKU", skuFromList, shipmentManifestMap.get("SKU"), failureList);
			verification.verifyData("Container", context.getContainerId(), shipmentManifestMap.get("Container"), failureList);
			verification.verifyData("Pallet", context.getContainerId(), shipmentManifestMap.get("Pallet"), failureList);
			verification.verifyData("Pack Config", "Not Null", shipmentManifestMap.get("Pack Config"), failureList);
			verification.verifyData("Consignment", context.getSTOConsignment(), shipmentManifestMap.get("Consignment"), failureList);
			verification.verifyData("Qty Picked", String.valueOf(context.getQtyToMove()), shipmentManifestMap.get("Qty Picked"), failureList);
			verification.verifyData("Qty Shipped", String.valueOf(context.getQtyToMove()), shipmentManifestMap.get("Qty Shipped"), failureList);
			verification.verifyData("Pick Label ID", "Not Null", shipmentManifestMap.get("Pick Label ID"), failureList);
			verification.verifyData("Shipped Date", "Not Null", shipmentManifestMap.get("Shipped Date"), failureList);
			verification.verifyData("Picked Date", "Not Null", shipmentManifestMap.get("Picked Date"), failureList);
			verification.verifyData("Pallet Type", "Not Null", shipmentManifestMap.get("Pallet Type"), failureList);
			
			verification.verifyData("CE Rotation ID", "Not Null", shipmentManifestMap.get("CE Rotation ID"), failureList);
			verification.verifyData("CE Consignment ID", "Not Null", shipmentManifestMap.get("CE Consignment ID"), failureList);
			verification.verifyData("CE Receipt Type", "Not Null", shipmentManifestMap.get("CE Receipt Type"), failureList);
			verification.verifyData("CE Originator", "Not Null", shipmentManifestMap.get("CE Originator"), failureList);
			verification.verifyData("CE Originator Reference", "Not Null", shipmentManifestMap.get("CE Originator Reference"), failureList);
			
			verification.verifyData("Shipment Number", "Not Null", shipmentManifestMap.get("Shipment Number"), failureList);
			verification.verifyData("Customer", context.getCustomer(), shipmentManifestMap.get("Customer"), failureList);
			verification.verifyData("IFOS Order Number", "Not Null", shipmentManifestMap.get("IFOS Order Number"), failureList);
			verification.verifyData("UPC", "Not Null", shipmentManifestMap.get("UPC"), failureList);
			verification.verifyData("DWS EDN Reference", "Not Null", shipmentManifestMap.get("DWS EDN Reference"), failureList);
			verification.verifyData("STO Reference", "Not Null", shipmentManifestMap.get("STO Reference"), failureList);
			verification.verifyData("Vintage", context.getVintage(), shipmentManifestMap.get("Vintage"), failureList);
			verification.verifyData("Delivery By Date", "Not Null", shipmentManifestMap.get("Delivery By Date"), failureList);
			
			String uploadedStatus = shipmentManifestMap.get("Uploaded");
			String uploadedFileName = shipmentManifestMap.get("Uploaded Filename");
			if (uploadedStatus.equals("Y")) {
				if (!uploadedFileName.contains("I0807")) {
					failureList.add("Uploaded File Name not displayed as expected for " + tagId
							+ ". Expected [I0807] but was [" + uploadedFileName + "]");
				}
			}
		}
		Assert.assertTrue("Shipment Manifest details not displayed as expected. ["
				+ Arrays.asList(failureList.toArray()) + "].", failureList.isEmpty());
	}
}
