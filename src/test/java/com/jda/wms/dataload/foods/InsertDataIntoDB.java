package com.jda.wms.dataload.foods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Database;
import com.jda.wms.utils.DateUtils;
import com.jda.wms.utils.Utilities;

public class InsertDataIntoDB {
	private Context context;
	private Database database;

	@Inject
	public InsertDataIntoDB(Context context,Database database) {
		this.context = context;
		this.database = database;
	}

	public void insertPreAdviceHeader(String preAdviceId) throws SQLException, ClassNotFoundException {
		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
		String query = "INSERT INTO INTERFACE_PRE_ADVICE_HEADER (KEY, CLIENT_ID, PRE_ADVICE_ID, PRE_ADVICE_TYPE, SITE_ID, OWNER_ID, SUPPLIER_ID, CONTACT, CONTACT_PHONE, CONTACT_MOBILE, CONTACT_FAX, CONTACT_EMAIL, NAME, ADDRESS1, ADDRESS2, TOWN, COUNTY, POSTCODE, COUNTRY, STATUS, BOOKREF_ID, DUE_DSTAMP, RETURN_FLAG, SAMPLING_TYPE, RETURNED_ORDER_ID, EMAIL_CONFIRM, COLLECTION_REQD, CONSIGNMENT, LOAD_SEQUENCE, NOTES, DISALLOW_MERGE_RULES, OAP_RMA, DISALLOW_REPLENS, SUPPLIER_REFERENCE, CARRIER_NAME, CARRIER_REFERENCE, TOD, TOD_PLACE, MODE_OF_TRANSPORT, VAT_NUMBER, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_6, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, YARD_CONTAINER_TYPE, YARD_CONTAINER_ID, CE_CONSIGNMENT_ID, MASTER_PRE_ADVICE, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_INVOICE_NUMBER, STATUS_REASON_CODE, PRIORITY, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES (73221, 'M+S', '"+preAdviceId+"', 'PO', '9771', 'M+S', 'F01903', '', '', '', '', 'danielle.blagwin@gmail.com', 'LA CHABLISIENNE', '8 BOUEVARD PASTEUR', '', 'CHABLIS', '', '89800', 'FRA', 'Released', '', to_date('"+queryInsertDate+" 07.48.21.000000099', ''), 'N', '', '', 'N', 'N', '', NULL, '', 'N', NULL, 'N', '', '', '', '', '', 'SEA', '', '', '', '', '', '', '', '', '', 'N', 'N', 'N', 'N', to_date('"+queryInsertDate+" 07.48.21.000000099', ''), to_date('', ''), to_date('', ''), to_date('', ''), NULL, NULL, NULL, NULL, '', '', '', '', '', 'N', '', NULL, '', '', NULL, 'Europe/Belfast', 'Europe/London', '', 'FOODS', 'U', 'Pending', '', to_date('"+queryInsertDate+" 10.02.40.993326099', ''))";
		System.out.println(query);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		context.getConnection().commit();
	}
	
	public void insertOrderHeader(String orderId, String stoType, String customer) throws SQLException, ClassNotFoundException {
		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
		String key = Utilities.getFourDigitRandomNumber()+".0";
		String query= null;
		if (stoType.equals("INTSEA")){
			query = "INSERT INTO INTERFACE_ORDER_HEADER (KEY, CLIENT_ID, ORDER_ID, ORDER_TYPE, WORK_ORDER_TYPE, STATUS, MOVE_TASK_STATUS, PRIORITY, REPACK, REPACK_LOC_ID, DELIVERY_POINT, LOAD_SEQUENCE, FROM_SITE_ID, TO_SITE_ID, OWNER_ID, CUSTOMER_ID, CONTACT, CONTACT_PHONE, CONTACT_MOBILE, CONTACT_FAX, CONTACT_EMAIL, NAME, ADDRESS1, ADDRESS2, TOWN, COUNTY, POSTCODE, COUNTRY, SHIP_DOCK, WORK_GROUP, CONSIGNMENT, ORDER_DATE, SHIP_BY_DATE, DELIVER_BY_DATE, DELIVERED_DSTAMP, SIGNATORY, PURCHASE_ORDER, CARRIER_ID, DISPATCH_METHOD, SERVICE_LEVEL, FASTEST_CARRIER, CHEAPEST_CARRIER, INV_ADDRESS_ID, INV_CONTACT, INV_CONTACT_PHONE, INV_CONTACT_MOBILE, INV_CONTACT_FAX, INV_CONTACT_EMAIL, INV_NAME, INV_ADDRESS1, INV_ADDRESS2, INV_TOWN, INV_COUNTY, INV_POSTCODE, INV_COUNTRY, INSTRUCTIONS, PSFT_DMND_SRCE, PSFT_ORDER_ID, SITE_REPLEN, CID_NUMBER, SID_NUMBER, LOCATION_NUMBER, FREIGHT_CHARGES, DISALLOW_MERGE_RULES, EXPORT, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_6, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, CE_REASON_CODE, CE_REASON_NOTES, CE_ORDER_TYPE, CE_CUSTOMS_CUSTOMER, CE_EXCISE_CUSTOMER, CE_INSTRUCTIONS, ROUTE_ID, CROSS_DOCK_TO_SITE, WEB_SERVICE_ALLOC_IMMED, WEB_SERVICE_ALLOC_CLEAN, DISALLOW_SHORT_SHIP, HUB_ADDRESS_ID, HUB_CONTACT, HUB_CONTACT_PHONE, HUB_CONTACT_MOBILE, HUB_CONTACT_FAX, HUB_CONTACT_EMAIL, HUB_NAME, HUB_ADDRESS1, HUB_ADDRESS2, HUB_TOWN, HUB_COUNTY, HUB_POSTCODE, HUB_COUNTRY, STATUS_REASON_CODE, STAGE_ROUTE_ID, SINGLE_ORDER_SORTATION, FORCE_SINGLE_CARRIER, HUB_CARRIER_ID, HUB_SERVICE_LEVEL, EXPECTED_VOLUME, EXPECTED_WEIGHT, EXPECTED_VALUE, TOD, TOD_PLACE, LANGUAGE, SELLER_NAME, SELLER_PHONE, DOCUMENTATION_TEXT_1, DOCUMENTATION_TEXT_2, DOCUMENTATION_TEXT_3, COD, COD_VALUE, COD_CURRENCY, COD_TYPE, VAT_NUMBER, INV_VAT_NUMBER, HUB_VAT_NUMBER, PRINT_INVOICE, INV_REFERENCE, INV_DSTAMP, INV_CURRENCY, LETTER_OF_CREDIT, PAYMENT_TERMS, SUBTOTAL_1, SUBTOTAL_2, SUBTOTAL_3, SUBTOTAL_4, FREIGHT_COST, FREIGHT_TERMS, INSURANCE_COST, MISC_CHARGES, DISCOUNT, OTHER_FEE, INV_TOTAL_1, INV_TOTAL_2, INV_TOTAL_3, INV_TOTAL_4, TAX_RATE_1, TAX_BASIS_1, TAX_AMOUNT_1, TAX_RATE_2, TAX_BASIS_2, TAX_AMOUNT_2, TAX_RATE_3, TAX_BASIS_3, TAX_AMOUNT_3, TAX_RATE_4, TAX_BASIS_4, TAX_AMOUNT_4, TAX_RATE_5, TAX_BASIS_5, TAX_AMOUNT_5, ORDER_REFERENCE, START_BY_DATE, METAPACK_CARRIER_PRE, SHIPMENT_GROUP, FREIGHT_CURRENCY, NCTS, MPACK_CONSIGNMENT, MPACK_NOMINATED_DSTAMP, GLN, HUB_GLN, INV_GLN, ALLOW_PALLET_PICK, SPLIT_SHIPPING_UNITS, VOL_PCK_SSCC_LABEL, ALLOCATION_PRIORITY, TRAX_USE_HUB_ADDR, DIRECT_TO_STORE, VOL_CTR_LABEL_FORMAT, RETAILER_ID, CARRIER_BAGS, SOH_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("+key+", 'M+S', '"+orderId+"', '"+stoType+"', '', 'Released', 'Released', '20', 'N', '', '', NULL, '9771', '', 'M+S', '8468', 'CZECH', '', '', '', '', 'CZECH', 'Marks and Spencer Services sro  BB C', '', 'Vyskocilova 1481/4', '', '140 00', 'CZE', '', '', '', '08-JUN-17 06.38.46.150479000', '01-JUN-17 06.38.46.150479000', '08-JUN-17 06.38.46.150479000', '', '', '', '', '', '', 'N', 'N', '', '', '', '', '', '', '', '', '', '', '', '', '', '140', '', '', 'N', '', '', NULL, '', 'N', 'N', '', '', '', '', '', '', 'DV', '', 'N', 'N', 'N', 'N', '08-JUN-17 06.38.46.150479000', '', '', '', NULL, NULL, NULL, NULL, '583CA6D3B3D70510E10080000A9044F1', '', '', '', '', '', '', '', '', 'N', '', '', '', '7993', 'Bradford International Wa', '12345678', '', '', '', 'Bradford International Warehouse', 'Newhall Way', '', 'Bradford', 'United Kingdom', '', '', '', '', 'N', 'N', '', '', 1.273567, 448.287648, NULL, '', '', '', '', '', '', '', '', '', NULL, '', '', '', '', '', 'N', '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', 'N', '', '', '', '', '', '', '', '', 'N', 'N', 'N', NULL, 'N', 'N', '', '', 'N', '', '', NULL, 'Europe/Belfast', 'Europe/London', '', 'FOODS', 'U', 'Pending', '', '08-JUN-17 06.38.46.150479000')";
		}
		else if (stoType.equals("RDC")){
			query = "INSERT INTO INTERFACE_ORDER_HEADER (KEY, CLIENT_ID, ORDER_ID, ORDER_TYPE, WORK_ORDER_TYPE, STATUS, MOVE_TASK_STATUS, PRIORITY, REPACK, REPACK_LOC_ID, DELIVERY_POINT, LOAD_SEQUENCE, FROM_SITE_ID, TO_SITE_ID, OWNER_ID, CUSTOMER_ID, CONTACT, CONTACT_PHONE, CONTACT_MOBILE, CONTACT_FAX, CONTACT_EMAIL, NAME, ADDRESS1, ADDRESS2, TOWN, COUNTY, POSTCODE, COUNTRY, SHIP_DOCK, WORK_GROUP, CONSIGNMENT, ORDER_DATE, SHIP_BY_DATE, DELIVER_BY_DATE, DELIVERED_DSTAMP, SIGNATORY, PURCHASE_ORDER, CARRIER_ID, DISPATCH_METHOD, SERVICE_LEVEL, FASTEST_CARRIER, CHEAPEST_CARRIER, INV_ADDRESS_ID, INV_CONTACT, INV_CONTACT_PHONE, INV_CONTACT_MOBILE, INV_CONTACT_FAX, INV_CONTACT_EMAIL, INV_NAME, INV_ADDRESS1, INV_ADDRESS2, INV_TOWN, INV_COUNTY, INV_POSTCODE, INV_COUNTRY, INSTRUCTIONS, PSFT_DMND_SRCE, PSFT_ORDER_ID, SITE_REPLEN, CID_NUMBER, SID_NUMBER, LOCATION_NUMBER, FREIGHT_CHARGES, DISALLOW_MERGE_RULES, EXPORT, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_6, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, CE_REASON_CODE, CE_REASON_NOTES, CE_ORDER_TYPE, CE_CUSTOMS_CUSTOMER, CE_EXCISE_CUSTOMER, CE_INSTRUCTIONS, ROUTE_ID, CROSS_DOCK_TO_SITE, WEB_SERVICE_ALLOC_IMMED, WEB_SERVICE_ALLOC_CLEAN, DISALLOW_SHORT_SHIP, HUB_ADDRESS_ID, HUB_CONTACT, HUB_CONTACT_PHONE, HUB_CONTACT_MOBILE, HUB_CONTACT_FAX, HUB_CONTACT_EMAIL, HUB_NAME, HUB_ADDRESS1, HUB_ADDRESS2, HUB_TOWN, HUB_COUNTY, HUB_POSTCODE, HUB_COUNTRY, STATUS_REASON_CODE, STAGE_ROUTE_ID, SINGLE_ORDER_SORTATION, FORCE_SINGLE_CARRIER, HUB_CARRIER_ID, HUB_SERVICE_LEVEL, EXPECTED_VOLUME, EXPECTED_WEIGHT, EXPECTED_VALUE, TOD, TOD_PLACE, LANGUAGE, SELLER_NAME, SELLER_PHONE, DOCUMENTATION_TEXT_1, DOCUMENTATION_TEXT_2, DOCUMENTATION_TEXT_3, COD, COD_VALUE, COD_CURRENCY, COD_TYPE, VAT_NUMBER, INV_VAT_NUMBER, HUB_VAT_NUMBER, PRINT_INVOICE, INV_REFERENCE, INV_DSTAMP, INV_CURRENCY, LETTER_OF_CREDIT, PAYMENT_TERMS, SUBTOTAL_1, SUBTOTAL_2, SUBTOTAL_3, SUBTOTAL_4, FREIGHT_COST, FREIGHT_TERMS, INSURANCE_COST, MISC_CHARGES, DISCOUNT, OTHER_FEE, INV_TOTAL_1, INV_TOTAL_2, INV_TOTAL_3, INV_TOTAL_4, TAX_RATE_1, TAX_BASIS_1, TAX_AMOUNT_1, TAX_RATE_2, TAX_BASIS_2, TAX_AMOUNT_2, TAX_RATE_3, TAX_BASIS_3, TAX_AMOUNT_3, TAX_RATE_4, TAX_BASIS_4, TAX_AMOUNT_4, TAX_RATE_5, TAX_BASIS_5, TAX_AMOUNT_5, ORDER_REFERENCE, START_BY_DATE, METAPACK_CARRIER_PRE, SHIPMENT_GROUP, FREIGHT_CURRENCY, NCTS, MPACK_CONSIGNMENT, MPACK_NOMINATED_DSTAMP, GLN, HUB_GLN, INV_GLN, ALLOW_PALLET_PICK, SPLIT_SHIPPING_UNITS, VOL_PCK_SSCC_LABEL, ALLOCATION_PRIORITY, TRAX_USE_HUB_ADDR, DIRECT_TO_STORE, VOL_CTR_LABEL_FORMAT, RETAILER_ID, CARRIER_BAGS, SOH_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("+key+", 'M+S', '"+orderId+"', '"+stoType+"', '', 'Released', 'Released', '20', 'N', '', '', NULL, '9771', '', 'M+S', '8468', 'CZECH', '', '', '', '', 'CZECH', 'Marks and Spencer Services sro  BB C', '', 'Vyskocilova 1481/4', '', '140 00', 'CZE', '', '', '', '08-JUN-17 06.38.46.150479000', '01-JUN-17 06.38.46.150479000', '08-JUN-17 06.38.46.150479000', '', '', '', '', '', '', 'N', 'N', '', '', '', '', '', '', '', '', '', '', '', '', '', '140', '', '', 'N', '', '', NULL, '', 'N', 'N', '', '', '', '', '', '', 'DV', '', 'N', 'N', 'N', 'N', '08-JUN-17 06.38.46.150479000', '', '', '', NULL, NULL, NULL, NULL, '583CA6D3B3D70510E10080000A9044F1', '', '', '', '', '', '', '', '', 'N', '', '', '', '7993', 'Bradford International Wa', '12345678', '', '', '', 'Bradford International Warehouse', 'Newhall Way', '', 'Bradford', 'United Kingdom', '', '', '', '', 'N', 'N', '', '', 1.273567, 448.287648, NULL, '', '', '', '', '', '', '', '', '', NULL, '', '', '', '', '', 'N', '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', 'N', '', '', '', '', '', '', '', '', 'N', 'N', 'N', NULL, 'N', 'N', '', '', 'N', '', '', NULL, 'Europe/Belfast', 'Europe/London', '', 'FOODS', 'U', 'Pending', '', '08-JUN-17 06.38.46.150479000')";
		}
		else if (stoType.equals("STR")){
			query = "INSERT INTO INTERFACE_ORDER_HEADER (KEY, CLIENT_ID, ORDER_ID, ORDER_TYPE, WORK_ORDER_TYPE, STATUS, MOVE_TASK_STATUS, PRIORITY, REPACK, REPACK_LOC_ID, DELIVERY_POINT, LOAD_SEQUENCE, FROM_SITE_ID, TO_SITE_ID, OWNER_ID, CUSTOMER_ID, CONTACT, CONTACT_PHONE, CONTACT_MOBILE, CONTACT_FAX, CONTACT_EMAIL, NAME, ADDRESS1, ADDRESS2, TOWN, COUNTY, POSTCODE, COUNTRY, SHIP_DOCK, WORK_GROUP, CONSIGNMENT, ORDER_DATE, SHIP_BY_DATE, DELIVER_BY_DATE, DELIVERED_DSTAMP, SIGNATORY, PURCHASE_ORDER, CARRIER_ID, DISPATCH_METHOD, SERVICE_LEVEL, FASTEST_CARRIER, CHEAPEST_CARRIER, INV_ADDRESS_ID, INV_CONTACT, INV_CONTACT_PHONE, INV_CONTACT_MOBILE, INV_CONTACT_FAX, INV_CONTACT_EMAIL, INV_NAME, INV_ADDRESS1, INV_ADDRESS2, INV_TOWN, INV_COUNTY, INV_POSTCODE, INV_COUNTRY, INSTRUCTIONS, PSFT_DMND_SRCE, PSFT_ORDER_ID, SITE_REPLEN, CID_NUMBER, SID_NUMBER, LOCATION_NUMBER, FREIGHT_CHARGES, DISALLOW_MERGE_RULES, EXPORT, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_6, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, CE_REASON_CODE, CE_REASON_NOTES, CE_ORDER_TYPE, CE_CUSTOMS_CUSTOMER, CE_EXCISE_CUSTOMER, CE_INSTRUCTIONS, ROUTE_ID, CROSS_DOCK_TO_SITE, WEB_SERVICE_ALLOC_IMMED, WEB_SERVICE_ALLOC_CLEAN, DISALLOW_SHORT_SHIP, HUB_ADDRESS_ID, HUB_CONTACT, HUB_CONTACT_PHONE, HUB_CONTACT_MOBILE, HUB_CONTACT_FAX, HUB_CONTACT_EMAIL, HUB_NAME, HUB_ADDRESS1, HUB_ADDRESS2, HUB_TOWN, HUB_COUNTY, HUB_POSTCODE, HUB_COUNTRY, STATUS_REASON_CODE, STAGE_ROUTE_ID, SINGLE_ORDER_SORTATION, FORCE_SINGLE_CARRIER, HUB_CARRIER_ID, HUB_SERVICE_LEVEL, EXPECTED_VOLUME, EXPECTED_WEIGHT, EXPECTED_VALUE, TOD, TOD_PLACE, LANGUAGE, SELLER_NAME, SELLER_PHONE, DOCUMENTATION_TEXT_1, DOCUMENTATION_TEXT_2, DOCUMENTATION_TEXT_3, COD, COD_VALUE, COD_CURRENCY, COD_TYPE, VAT_NUMBER, INV_VAT_NUMBER, HUB_VAT_NUMBER, PRINT_INVOICE, INV_REFERENCE, INV_DSTAMP, INV_CURRENCY, LETTER_OF_CREDIT, PAYMENT_TERMS, SUBTOTAL_1, SUBTOTAL_2, SUBTOTAL_3, SUBTOTAL_4, FREIGHT_COST, FREIGHT_TERMS, INSURANCE_COST, MISC_CHARGES, DISCOUNT, OTHER_FEE, INV_TOTAL_1, INV_TOTAL_2, INV_TOTAL_3, INV_TOTAL_4, TAX_RATE_1, TAX_BASIS_1, TAX_AMOUNT_1, TAX_RATE_2, TAX_BASIS_2, TAX_AMOUNT_2, TAX_RATE_3, TAX_BASIS_3, TAX_AMOUNT_3, TAX_RATE_4, TAX_BASIS_4, TAX_AMOUNT_4, TAX_RATE_5, TAX_BASIS_5, TAX_AMOUNT_5, ORDER_REFERENCE, START_BY_DATE, METAPACK_CARRIER_PRE, SHIPMENT_GROUP, FREIGHT_CURRENCY, NCTS, MPACK_CONSIGNMENT, MPACK_NOMINATED_DSTAMP, GLN, HUB_GLN, INV_GLN, ALLOW_PALLET_PICK, SPLIT_SHIPPING_UNITS, VOL_PCK_SSCC_LABEL, ALLOCATION_PRIORITY, TRAX_USE_HUB_ADDR, DIRECT_TO_STORE, VOL_CTR_LABEL_FORMAT, RETAILER_ID, CARRIER_BAGS, SOH_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("+key+", 'M+S', '"+orderId+"', '"+stoType+"', '', 'Released', 'Released', '20', 'N', '', '', NULL, '9771', '', 'M+S', '8468', 'CZECH', '', '', '', '', 'CZECH', 'Marks and Spencer Services sro  BB C', '', 'Vyskocilova 1481/4', '', '140 00', 'CZE', '', '', '', '08-JUN-17 06.38.46.150479000', '01-JUN-17 06.38.46.150479000', '08-JUN-17 06.38.46.150479000', '', '', '', '', '', '', 'N', 'N', '', '', '', '', '', '', '', '', '', '', '', '', '', '140', '', '', 'N', '', '', NULL, '', 'N', 'N', '', '', '', '', '', '', 'DV', '', 'N', 'N', 'N', 'N', '08-JUN-17 06.38.46.150479000', '', '', '', NULL, NULL, NULL, NULL, '583CA6D3B3D70510E10080000A9044F1', '', '', '', '', '', '', '', '', 'N', '', '', '', '7993', 'Bradford International Wa', '12345678', '', '', '', 'Bradford International Warehouse', 'Newhall Way', '', 'Bradford', 'United Kingdom', '', '', '', '', 'N', 'N', '', '', 1.273567, 448.287648, NULL, '', '', '', '', '', '', '', '', '', NULL, '', '', '', '', '', 'N', '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', 'N', '', '', '', '', '', '', '', '', 'N', 'N', 'N', NULL, 'N', 'N', '', '', 'N', '', '', NULL, 'Europe/Belfast', 'Europe/London', '', 'FOODS', 'U', 'Pending', '', '08-JUN-17 06.38.46.150479000')";
		}
		System.out.println(query);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		context.getConnection().commit();
	}

	public void insertPreAdviceLine(String preAdviceId, String productCategory) throws SQLException, ClassNotFoundException {
		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
		String key = Utilities.getFourDigitRandomNumber()+".0";
		String query = null;
		String vintage = DateUtils.getYear() ;
		if (productCategory.contains("Ambient")||(null==productCategory)){
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("+key+", 'M+S', '"+preAdviceId+"', 20.0, '21036013', '21036013O01', '15', '', '', '', '', '', 'CHEP', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '', '', '', '', '', '', '"+queryInsertDate+" 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, '', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"+queryInsertDate+" 10.02.40.993326099')";
		}
		else if (productCategory.contains("BWS-Bonded")){
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("+key+", 'M+S', '"+preAdviceId+"', 10.0, '20002039', '20002039O01', '6', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '"+vintage+"', '', '', '', '', '', '"+queryInsertDate+" 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, 'Y', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"+queryInsertDate+" 10.02.40.993326099')";
		}
		else if (productCategory.contains("BWS-Non-Bonded")){
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("+key+", 'M+S', '"+preAdviceId+"', 20.0, '20002032', '20002032O01', '6', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '"+vintage+"', '', '', '', '', '', '"+queryInsertDate+" 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, 'N', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"+queryInsertDate+" 10.02.40.993326099')";
		}
		else if (productCategory.contains("BWS-New Vintage")){
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("+key+", 'M+S', '"+preAdviceId+"', 10.0, '20001266', '20001266O01', '6', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '"+vintage+"', '', '', '', '', '', '"+queryInsertDate+" 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, 'Y', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"+queryInsertDate+" 10.02.40.993326099')";
		}
		else if (productCategory.contains("BWS-F23 Bonded")){
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("+key+", 'M+S', '"+preAdviceId+"', 20.0, '21126978', '21126978O01', '6', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '"+vintage+"', '', '', '', '', '', '"+queryInsertDate+" 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, 'Y', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"+queryInsertDate+" 10.02.40.993326099')";
		}
		
		System.out.println(query);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		context.getConnection().commit();
	}
	
	public void insertOrderLine(String orderId) throws SQLException, ClassNotFoundException {
		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
		String key = Utilities.getFourDigitRandomNumber()+".0";
		String query = null;
		String vintage = DateUtils.getYear() ;
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("+key+", 'M+S', '"+orderId+"', 20.0, '21036013', '21036013O01', '6', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '', '', '', '', '', '', '"+queryInsertDate+" 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, '', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"+queryInsertDate+" 10.02.40.993326099')";
		System.out.println(query);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		context.getConnection().commit();
	}
}
