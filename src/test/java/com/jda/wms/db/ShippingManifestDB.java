package com.jda.wms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.google.inject.Inject;
import com.jda.wms.context.Context;

public class ShippingManifestDB {
	private Context context;
	private final Database database;

	@Inject
	public ShippingManifestDB(Context context, Database database) {
		this.context = context;
		this.database = database;
	}

	public HashMap<String, String> getShipmentManifestDetails(String tagId, String orderId) throws SQLException, ClassNotFoundException {

		ResultSet resultSet = null;
		HashMap<String, String> shipmentManifestMap = new HashMap<String, String>();

		if (context.getConnection() == null) {
			database.connect();
		}

		Statement stmt = context.getConnection().createStatement();
		resultSet = stmt.executeQuery(
				"select SKU_ID,CONTAINER_ID,PALLET_ID,CONFIG_ID,CONSIGNMENT,QTY_PICKED,QTY_SHIPPED,PICK_LABEL_ID,SHIPPED_DSTAMP,PICKED_DSTAMP,UPLOADED,UPLOADED_FILENAME,PALLET_CONFIG,CE_ROTATION_ID,CE_CONSIGNMENT_ID,CE_RECEIPT_TYPE,CE_ORIGINATOR,CE_ORIGINATOR_REFERENCE,SHIPMENT_NUMBER,CUSTOMER_ID,USER_DEF_TYPE_2,USER_DEF_TYPE_3,USER_DEF_TYPE_5,USER_DEF_TYPE_8,USER_DEF_TYPE_7,USER_DEF_DATE_2 from SHIPPING_MANIFEST where ORDER_ID='"+orderId+"' AND TAG_ID='"+tagId+"'");
		resultSet.next();

		shipmentManifestMap.put("SKU", resultSet.getString(1));
		shipmentManifestMap.put("Container", resultSet.getString(2));
		shipmentManifestMap.put("Pallet", resultSet.getString(3));
		shipmentManifestMap.put("Pack Config", resultSet.getString(4));

		shipmentManifestMap.put("Consignment", resultSet.getString(5));
		shipmentManifestMap.put("Qty Picked", resultSet.getString(6));
		shipmentManifestMap.put("Qty Shipped", resultSet.getString(7));
		shipmentManifestMap.put("Pick Label ID", resultSet.getString(8));

		shipmentManifestMap.put("Shipped Date", resultSet.getString(9));
		shipmentManifestMap.put("Picked Date", resultSet.getString(10));

		shipmentManifestMap.put("Uploaded", resultSet.getString(11));
		shipmentManifestMap.put("Uploaded Filename", resultSet.getString(12));
		shipmentManifestMap.put("Pallet Type", resultSet.getString(13));
		shipmentManifestMap.put("CE Rotation ID", resultSet.getString(14));
		
		shipmentManifestMap.put("CE Consignment ID", resultSet.getString(15));
		shipmentManifestMap.put("CE Receipt Type", resultSet.getString(16));
		shipmentManifestMap.put("CE Originator", resultSet.getString(17));
		shipmentManifestMap.put("CE Originator Reference", resultSet.getString(18));
		
		shipmentManifestMap.put("Shipment Number", resultSet.getString(19));
		shipmentManifestMap.put("Customer", resultSet.getString(20));
		
		shipmentManifestMap.put("IFOS Order Number", resultSet.getString(21));
		shipmentManifestMap.put("UPC", resultSet.getString(22));
		shipmentManifestMap.put("DWS EDN Reference", resultSet.getString(23));
		shipmentManifestMap.put("STO Reference", resultSet.getString(24));
		shipmentManifestMap.put("Vintage", resultSet.getString(25));
		shipmentManifestMap.put("Delivery By Date", resultSet.getString(26));

		return shipmentManifestMap;
	}
}
