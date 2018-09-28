package com.jda.wms.dataload.exit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Exit.Database;
import com.jda.wms.db.Exit.PackConfigMaintenanceDB;
import com.jda.wms.utils.DateUtils;
import com.jda.wms.utils.Utilities;

public class InsertDataIntoDB {
	private Context context;
	private Database database;
	private PackConfigMaintenanceDB packConfigMaintenanceDB;

	@Inject
	public InsertDataIntoDB(Context context, Database database, PackConfigMaintenanceDB packConfigMaintenanceDB) {
		this.context = context;
		this.database = database;
		this.packConfigMaintenanceDB = packConfigMaintenanceDB;
	}

	public void insertPreAdviceHeader(String preAdviceId) throws SQLException, ClassNotFoundException {
		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
		String key = getMaxKeyFromDB("INTERFACE_PRE_ADVICE_HEADER");
		String query = "Insert into INTERFACE_PRE_ADVICE_HEADER (KEY,CLIENT_ID,PRE_ADVICE_ID,PRE_ADVICE_TYPE,SITE_ID,OWNER_ID,SUPPLIER_ID,STATUS,BOOKREF_ID,DUE_DSTAMP,CONTACT,CONTACT_PHONE,CONTACT_MOBILE,CONTACT_FAX,CONTACT_EMAIL,NAME,ADDRESS1,ADDRESS2,TOWN,COUNTY,POSTCODE,COUNTRY,RETURN_FLAG,SAMPLING_TYPE,RETURNED_ORDER_ID,EMAIL_CONFIRM,COLLECTION_REQD,CONSIGNMENT,LOAD_SEQUENCE,NOTES,DISALLOW_MERGE_RULES,OAP_RMA,DISALLOW_REPLENS,SUPPLIER_REFERENCE,CARRIER_NAME,CARRIER_REFERENCE,TOD,TOD_PLACE,MODE_OF_TRANSPORT,VAT_NUMBER,USER_DEF_TYPE_1,USER_DEF_TYPE_2,USER_DEF_TYPE_3,USER_DEF_TYPE_4,USER_DEF_TYPE_5,USER_DEF_TYPE_6,USER_DEF_TYPE_7,USER_DEF_TYPE_8,USER_DEF_CHK_1,USER_DEF_CHK_2,USER_DEF_CHK_3,USER_DEF_CHK_4,USER_DEF_DATE_1,USER_DEF_DATE_2,USER_DEF_DATE_3,USER_DEF_DATE_4,USER_DEF_NUM_1,USER_DEF_NUM_2,USER_DEF_NUM_3,USER_DEF_NUM_4,USER_DEF_NOTE_1,USER_DEF_NOTE_2,YARD_CONTAINER_TYPE,YARD_CONTAINER_ID,CE_CONSIGNMENT_ID,MASTER_PRE_ADVICE,COLLECTIVE_MODE,COLLECTIVE_SEQUENCE,CE_INVOICE_NUMBER,STATUS_REASON_CODE,PRIORITY,SESSION_TIME_ZONE_NAME,TIME_ZONE_NAME,NLS_CALENDAR,CLIENT_GROUP,MERGE_ACTION,MERGE_STATUS,MERGE_ERROR,MERGE_DSTAMP) values ('"
                        + key +"','M+S','"
                        +preAdviceId+"','STO','5542','M+S','4624','Released',null,to_timestamp('"
                        +queryInsertDate+" 00.00.00.000000000'),null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,'N','N',null,null,null,'N',null,'N',null,null,null,null,null,null,null,'224101','ZGM',null,null,null,'H',null,null,null,'N','N','N',to_timestamp('"
                        +queryInsertDate+" 12.05.23.000000000'),null,null,null,null,null,null,5581,null,null,null,null,null,'N',null,null,null,null,null,'Europe/Belfast','Europe/London',null,'INT','U','CMEError','MANDSIF001',to_timestamp('"+queryInsertDate+" 08.49.04.679759000'))";
		System.out.println("Insert Pre Advice Header");
		System.out.println(query);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		context.getConnection().commit();
	}
//	public void insertPreAdviceHeader(String preAdviceId) throws SQLException, ClassNotFoundException {
//		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
//		String key = getMaxKeyFromDB("INTERFACE_PRE_ADVICE_HEADER");
//		String query = "Insert into INTERFACE_PRE_ADVICE_HEADER (KEY,CLIENT_ID,PRE_ADVICE_ID,PRE_ADVICE_TYPE,SITE_ID,OWNER_ID,SUPPLIER_ID,STATUS,BOOKREF_ID,DUE_DSTAMP,CONTACT,CONTACT_PHONE,CONTACT_MOBILE,CONTACT_FAX,CONTACT_EMAIL,NAME,ADDRESS1,ADDRESS2,TOWN,COUNTY,POSTCODE,COUNTRY,RETURN_FLAG,SAMPLING_TYPE,RETURNED_ORDER_ID,EMAIL_CONFIRM,COLLECTION_REQD,CONSIGNMENT,LOAD_SEQUENCE,NOTES,DISALLOW_MERGE_RULES,OAP_RMA,DISALLOW_REPLENS,SUPPLIER_REFERENCE,CARRIER_NAME,CARRIER_REFERENCE,TOD,TOD_PLACE,MODE_OF_TRANSPORT,VAT_NUMBER,USER_DEF_TYPE_1,USER_DEF_TYPE_2,USER_DEF_TYPE_3,USER_DEF_TYPE_4,USER_DEF_TYPE_5,USER_DEF_TYPE_6,USER_DEF_TYPE_7,USER_DEF_TYPE_8,USER_DEF_CHK_1,USER_DEF_CHK_2,USER_DEF_CHK_3,USER_DEF_CHK_4,USER_DEF_DATE_1,USER_DEF_DATE_2,USER_DEF_DATE_3,USER_DEF_DATE_4,USER_DEF_NUM_1,USER_DEF_NUM_2,USER_DEF_NUM_3,USER_DEF_NUM_4,USER_DEF_NOTE_1,USER_DEF_NOTE_2,YARD_CONTAINER_TYPE,YARD_CONTAINER_ID,CE_CONSIGNMENT_ID,MASTER_PRE_ADVICE,COLLECTIVE_MODE,COLLECTIVE_SEQUENCE,CE_INVOICE_NUMBER,STATUS_REASON_CODE,PRIORITY,SESSION_TIME_ZONE_NAME,TIME_ZONE_NAME,NLS_CALENDAR,CLIENT_GROUP,MERGE_ACTION,MERGE_STATUS,MERGE_ERROR,MERGE_DSTAMP) values ('"
//                        + key +"','M+S','"
//                        +preAdviceId+"','STO','5542','M+S','4624','Released',null,to_timestamp('"
//                        +queryInsertDate+" 00.00.00.000000000'),null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,'N','N',null,null,null,'N',null,'N',null,null,null,null,null,null,null,'224101','ZGM',null,null,null,'H',null,null,null,'N','N','N',to_timestamp('"
//                        +queryInsertDate+" 12.05.23.000000000'),null,null,null,null,null,null,5581,null,null,null,null,null,'N',null,null,null,null,null,'Europe/Belfast','Europe/London',null,'INT','U','CMEError','MANDSIF001',to_timestamp('"+queryInsertDate+" 08.49.04.679759000'))";
//		System.out.println("Insert Pre Advice Header");
//		System.out.println(query);
//		if (context.getConnection() == null) {
//			database.connect();
//		}
//		Statement stmt = context.getConnection().createStatement();
//		ResultSet rs = stmt.executeQuery(query);
//		context.getConnection().commit();
//	}
//	public void insertPreAdviceHeader(String preAdviceId) throws SQLException, ClassNotFoundException {
//		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
//		String key = getMaxKeyFromDB("INTERFACE_PRE_ADVICE_HEADER");
//		String query = "Insert into INTERFACE_PRE_ADVICE_HEADER (KEY,CLIENT_ID,PRE_ADVICE_ID,PRE_ADVICE_TYPE,SITE_ID,OWNER_ID,SUPPLIER_ID,STATUS,BOOKREF_ID,DUE_DSTAMP,CONTACT,CONTACT_PHONE,CONTACT_MOBILE,CONTACT_FAX,CONTACT_EMAIL,NAME,ADDRESS1,ADDRESS2,TOWN,COUNTY,POSTCODE,COUNTRY,RETURN_FLAG,SAMPLING_TYPE,RETURNED_ORDER_ID,EMAIL_CONFIRM,COLLECTION_REQD,CONSIGNMENT,LOAD_SEQUENCE,NOTES,DISALLOW_MERGE_RULES,OAP_RMA,DISALLOW_REPLENS,SUPPLIER_REFERENCE,CARRIER_NAME,CARRIER_REFERENCE,TOD,TOD_PLACE,MODE_OF_TRANSPORT,VAT_NUMBER,USER_DEF_TYPE_1,USER_DEF_TYPE_2,USER_DEF_TYPE_3,USER_DEF_TYPE_4,USER_DEF_TYPE_5,USER_DEF_TYPE_6,USER_DEF_TYPE_7,USER_DEF_TYPE_8,USER_DEF_CHK_1,USER_DEF_CHK_2,USER_DEF_CHK_3,USER_DEF_CHK_4,USER_DEF_DATE_1,USER_DEF_DATE_2,USER_DEF_DATE_3,USER_DEF_DATE_4,USER_DEF_NUM_1,USER_DEF_NUM_2,USER_DEF_NUM_3,USER_DEF_NUM_4,USER_DEF_NOTE_1,USER_DEF_NOTE_2,YARD_CONTAINER_TYPE,YARD_CONTAINER_ID,CE_CONSIGNMENT_ID,MASTER_PRE_ADVICE,COLLECTIVE_MODE,COLLECTIVE_SEQUENCE,CE_INVOICE_NUMBER,STATUS_REASON_CODE,PRIORITY,SESSION_TIME_ZONE_NAME,TIME_ZONE_NAME,NLS_CALENDAR,CLIENT_GROUP,MERGE_ACTION,MERGE_STATUS,MERGE_ERROR,MERGE_DSTAMP) values ('"
//                        + key +"','M+S','"
//                        +preAdviceId+"','STO','5542','M+S','4624','Released',null,to_timestamp('"
//                        +queryInsertDate+" 00.00.00.000000000'),null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,'N','N',null,null,null,'N',null,'N',null,null,null,null,null,null,null,'224101','ZGM',null,null,null,'H',null,null,null,'N','N','N',to_timestamp('"
//                        +queryInsertDate+" 12.05.23.000000000'),null,null,null,null,null,null,5581,null,null,null,null,null,'N',null,null,null,null,null,'Europe/Belfast','Europe/London',null,'INT','U','CMEError','MANDSIF001',to_timestamp('"+queryInsertDate+" 08.49.04.679759000'))";
//		System.out.println("Insert Pre Advice Header");
//		System.out.println(query);
//		if (context.getConnection() == null) {
//			database.connect();
//		}
//		Statement stmt = context.getConnection().createStatement();
//		ResultSet rs = stmt.executeQuery(query);
//		context.getConnection().commit();
//	}
//	public void insertPreAdviceHeader(String preAdviceId) throws SQLException, ClassNotFoundException {
//		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
//		String key = getMaxKeyFromDB("INTERFACE_PRE_ADVICE_HEADER");
//		String query = "Insert into INTERFACE_PRE_ADVICE_HEADER (KEY,CLIENT_ID,PRE_ADVICE_ID,PRE_ADVICE_TYPE,SITE_ID,OWNER_ID,SUPPLIER_ID,STATUS,BOOKREF_ID,DUE_DSTAMP,CONTACT,CONTACT_PHONE,CONTACT_MOBILE,CONTACT_FAX,CONTACT_EMAIL,NAME,ADDRESS1,ADDRESS2,TOWN,COUNTY,POSTCODE,COUNTRY,RETURN_FLAG,SAMPLING_TYPE,RETURNED_ORDER_ID,EMAIL_CONFIRM,COLLECTION_REQD,CONSIGNMENT,LOAD_SEQUENCE,NOTES,DISALLOW_MERGE_RULES,OAP_RMA,DISALLOW_REPLENS,SUPPLIER_REFERENCE,CARRIER_NAME,CARRIER_REFERENCE,TOD,TOD_PLACE,MODE_OF_TRANSPORT,VAT_NUMBER,USER_DEF_TYPE_1,USER_DEF_TYPE_2,USER_DEF_TYPE_3,USER_DEF_TYPE_4,USER_DEF_TYPE_5,USER_DEF_TYPE_6,USER_DEF_TYPE_7,USER_DEF_TYPE_8,USER_DEF_CHK_1,USER_DEF_CHK_2,USER_DEF_CHK_3,USER_DEF_CHK_4,USER_DEF_DATE_1,USER_DEF_DATE_2,USER_DEF_DATE_3,USER_DEF_DATE_4,USER_DEF_NUM_1,USER_DEF_NUM_2,USER_DEF_NUM_3,USER_DEF_NUM_4,USER_DEF_NOTE_1,USER_DEF_NOTE_2,YARD_CONTAINER_TYPE,YARD_CONTAINER_ID,CE_CONSIGNMENT_ID,MASTER_PRE_ADVICE,COLLECTIVE_MODE,COLLECTIVE_SEQUENCE,CE_INVOICE_NUMBER,STATUS_REASON_CODE,PRIORITY,SESSION_TIME_ZONE_NAME,TIME_ZONE_NAME,NLS_CALENDAR,CLIENT_GROUP,MERGE_ACTION,MERGE_STATUS,MERGE_ERROR,MERGE_DSTAMP) values ('"
//                        + key +"','M+S','"
//                        +preAdviceId+"','STO','5542','M+S','4624','Released',null,to_timestamp('"
//                        +queryInsertDate+" 00.00.00.000000000'),null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,'N','N',null,null,null,'N',null,'N',null,null,null,null,null,null,null,'224101','ZGM',null,null,null,'H',null,null,null,'N','N','N',to_timestamp('"
//                        +queryInsertDate+" 12.05.23.000000000'),null,null,null,null,null,null,5581,null,null,null,null,null,'N',null,null,null,null,null,'Europe/Belfast','Europe/London',null,'INT','U','CMEError','MANDSIF001',to_timestamp('"+queryInsertDate+" 08.49.04.679759000'))";
//		System.out.println("Insert Pre Advice Header");
//		System.out.println(query);
//		if (context.getConnection() == null) {
//			database.connect();
//		}
//		Statement stmt = context.getConnection().createStatement();
//		ResultSet rs = stmt.executeQuery(query);
//		context.getConnection().commit();
//	}
//	public void insertPreAdviceHeader(String preAdviceId) throws SQLException, ClassNotFoundException {
//		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
//		String key = getMaxKeyFromDB("INTERFACE_PRE_ADVICE_HEADER");
//		String query = "Insert into INTERFACE_PRE_ADVICE_HEADER (KEY,CLIENT_ID,PRE_ADVICE_ID,PRE_ADVICE_TYPE,SITE_ID,OWNER_ID,SUPPLIER_ID,STATUS,BOOKREF_ID,DUE_DSTAMP,CONTACT,CONTACT_PHONE,CONTACT_MOBILE,CONTACT_FAX,CONTACT_EMAIL,NAME,ADDRESS1,ADDRESS2,TOWN,COUNTY,POSTCODE,COUNTRY,RETURN_FLAG,SAMPLING_TYPE,RETURNED_ORDER_ID,EMAIL_CONFIRM,COLLECTION_REQD,CONSIGNMENT,LOAD_SEQUENCE,NOTES,DISALLOW_MERGE_RULES,OAP_RMA,DISALLOW_REPLENS,SUPPLIER_REFERENCE,CARRIER_NAME,CARRIER_REFERENCE,TOD,TOD_PLACE,MODE_OF_TRANSPORT,VAT_NUMBER,USER_DEF_TYPE_1,USER_DEF_TYPE_2,USER_DEF_TYPE_3,USER_DEF_TYPE_4,USER_DEF_TYPE_5,USER_DEF_TYPE_6,USER_DEF_TYPE_7,USER_DEF_TYPE_8,USER_DEF_CHK_1,USER_DEF_CHK_2,USER_DEF_CHK_3,USER_DEF_CHK_4,USER_DEF_DATE_1,USER_DEF_DATE_2,USER_DEF_DATE_3,USER_DEF_DATE_4,USER_DEF_NUM_1,USER_DEF_NUM_2,USER_DEF_NUM_3,USER_DEF_NUM_4,USER_DEF_NOTE_1,USER_DEF_NOTE_2,YARD_CONTAINER_TYPE,YARD_CONTAINER_ID,CE_CONSIGNMENT_ID,MASTER_PRE_ADVICE,COLLECTIVE_MODE,COLLECTIVE_SEQUENCE,CE_INVOICE_NUMBER,STATUS_REASON_CODE,PRIORITY,SESSION_TIME_ZONE_NAME,TIME_ZONE_NAME,NLS_CALENDAR,CLIENT_GROUP,MERGE_ACTION,MERGE_STATUS,MERGE_ERROR,MERGE_DSTAMP) values ('"
//                        + key +"','M+S','"
//                        +preAdviceId+"','STO','5542','M+S','4624','Released',null,to_timestamp('"
//                        +queryInsertDate+" 00.00.00.000000000'),null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,'N','N',null,null,null,'N',null,'N',null,null,null,null,null,null,null,'224101','ZGM',null,null,null,'H',null,null,null,'N','N','N',to_timestamp('"
//                        +queryInsertDate+" 12.05.23.000000000'),null,null,null,null,null,null,5581,null,null,null,null,null,'N',null,null,null,null,null,'Europe/Belfast','Europe/London',null,'INT','U','CMEError','MANDSIF001',to_timestamp('"+queryInsertDate+" 08.49.04.679759000'))";
//		System.out.println("Insert Pre Advice Header");
//		System.out.println(query);
//		if (context.getConnection() == null) {
//			database.connect();
//		}
//		Statement stmt = context.getConnection().createStatement();
//		ResultSet rs = stmt.executeQuery(query);
//		context.getConnection().commit();
//	}
//	public void insertPreAdviceHeader(String preAdviceId) throws SQLException, ClassNotFoundException {
//		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
//		String key = getMaxKeyFromDB("INTERFACE_PRE_ADVICE_HEADER");
//		String query = "Insert into INTERFACE_PRE_ADVICE_HEADER (KEY,CLIENT_ID,PRE_ADVICE_ID,PRE_ADVICE_TYPE,SITE_ID,OWNER_ID,SUPPLIER_ID,STATUS,BOOKREF_ID,DUE_DSTAMP,CONTACT,CONTACT_PHONE,CONTACT_MOBILE,CONTACT_FAX,CONTACT_EMAIL,NAME,ADDRESS1,ADDRESS2,TOWN,COUNTY,POSTCODE,COUNTRY,RETURN_FLAG,SAMPLING_TYPE,RETURNED_ORDER_ID,EMAIL_CONFIRM,COLLECTION_REQD,CONSIGNMENT,LOAD_SEQUENCE,NOTES,DISALLOW_MERGE_RULES,OAP_RMA,DISALLOW_REPLENS,SUPPLIER_REFERENCE,CARRIER_NAME,CARRIER_REFERENCE,TOD,TOD_PLACE,MODE_OF_TRANSPORT,VAT_NUMBER,USER_DEF_TYPE_1,USER_DEF_TYPE_2,USER_DEF_TYPE_3,USER_DEF_TYPE_4,USER_DEF_TYPE_5,USER_DEF_TYPE_6,USER_DEF_TYPE_7,USER_DEF_TYPE_8,USER_DEF_CHK_1,USER_DEF_CHK_2,USER_DEF_CHK_3,USER_DEF_CHK_4,USER_DEF_DATE_1,USER_DEF_DATE_2,USER_DEF_DATE_3,USER_DEF_DATE_4,USER_DEF_NUM_1,USER_DEF_NUM_2,USER_DEF_NUM_3,USER_DEF_NUM_4,USER_DEF_NOTE_1,USER_DEF_NOTE_2,YARD_CONTAINER_TYPE,YARD_CONTAINER_ID,CE_CONSIGNMENT_ID,MASTER_PRE_ADVICE,COLLECTIVE_MODE,COLLECTIVE_SEQUENCE,CE_INVOICE_NUMBER,STATUS_REASON_CODE,PRIORITY,SESSION_TIME_ZONE_NAME,TIME_ZONE_NAME,NLS_CALENDAR,CLIENT_GROUP,MERGE_ACTION,MERGE_STATUS,MERGE_ERROR,MERGE_DSTAMP) values ('"
//                        + key +"','M+S','"
//                        +preAdviceId+"','STO','5542','M+S','4624','Released',null,to_timestamp('"
//                        +queryInsertDate+" 00.00.00.000000000'),null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,'N','N',null,null,null,'N',null,'N',null,null,null,null,null,null,null,'224101','ZGM',null,null,null,'H',null,null,null,'N','N','N',to_timestamp('"
//                        +queryInsertDate+" 12.05.23.000000000'),null,null,null,null,null,null,5581,null,null,null,null,null,'N',null,null,null,null,null,'Europe/Belfast','Europe/London',null,'INT','U','CMEError','MANDSIF001',to_timestamp('"+queryInsertDate+" 08.49.04.679759000'))";
//		System.out.println("Insert Pre Advice Header");
//		System.out.println(query);
//		if (context.getConnection() == null) {
//			database.connect();
//		}
//		Statement stmt = context.getConnection().createStatement();
//		ResultSet rs = stmt.executeQuery(query);
//		context.getConnection().commit();
//	}
//	public void insertPreAdviceHeader(String preAdviceId) throws SQLException, ClassNotFoundException {
//		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
//		String key = getMaxKeyFromDB("INTERFACE_PRE_ADVICE_HEADER");
//		String query = "Insert into INTERFACE_PRE_ADVICE_HEADER (KEY,CLIENT_ID,PRE_ADVICE_ID,PRE_ADVICE_TYPE,SITE_ID,OWNER_ID,SUPPLIER_ID,STATUS,BOOKREF_ID,DUE_DSTAMP,CONTACT,CONTACT_PHONE,CONTACT_MOBILE,CONTACT_FAX,CONTACT_EMAIL,NAME,ADDRESS1,ADDRESS2,TOWN,COUNTY,POSTCODE,COUNTRY,RETURN_FLAG,SAMPLING_TYPE,RETURNED_ORDER_ID,EMAIL_CONFIRM,COLLECTION_REQD,CONSIGNMENT,LOAD_SEQUENCE,NOTES,DISALLOW_MERGE_RULES,OAP_RMA,DISALLOW_REPLENS,SUPPLIER_REFERENCE,CARRIER_NAME,CARRIER_REFERENCE,TOD,TOD_PLACE,MODE_OF_TRANSPORT,VAT_NUMBER,USER_DEF_TYPE_1,USER_DEF_TYPE_2,USER_DEF_TYPE_3,USER_DEF_TYPE_4,USER_DEF_TYPE_5,USER_DEF_TYPE_6,USER_DEF_TYPE_7,USER_DEF_TYPE_8,USER_DEF_CHK_1,USER_DEF_CHK_2,USER_DEF_CHK_3,USER_DEF_CHK_4,USER_DEF_DATE_1,USER_DEF_DATE_2,USER_DEF_DATE_3,USER_DEF_DATE_4,USER_DEF_NUM_1,USER_DEF_NUM_2,USER_DEF_NUM_3,USER_DEF_NUM_4,USER_DEF_NOTE_1,USER_DEF_NOTE_2,YARD_CONTAINER_TYPE,YARD_CONTAINER_ID,CE_CONSIGNMENT_ID,MASTER_PRE_ADVICE,COLLECTIVE_MODE,COLLECTIVE_SEQUENCE,CE_INVOICE_NUMBER,STATUS_REASON_CODE,PRIORITY,SESSION_TIME_ZONE_NAME,TIME_ZONE_NAME,NLS_CALENDAR,CLIENT_GROUP,MERGE_ACTION,MERGE_STATUS,MERGE_ERROR,MERGE_DSTAMP) values ('"
//                        + key +"','M+S','"
//                        +preAdviceId+"','STO','5542','M+S','4624','Released',null,to_timestamp('"
//                        +queryInsertDate+" 00.00.00.000000000'),null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,'N','N',null,null,null,'N',null,'N',null,null,null,null,null,null,null,'224101','ZGM',null,null,null,'H',null,null,null,'N','N','N',to_timestamp('"
//                        +queryInsertDate+" 12.05.23.000000000'),null,null,null,null,null,null,5581,null,null,null,null,null,'N',null,null,null,null,null,'Europe/Belfast','Europe/London',null,'INT','U','CMEError','MANDSIF001',to_timestamp('"+queryInsertDate+" 08.49.04.679759000'))";
//		System.out.println("Insert Pre Advice Header");
//		System.out.println(query);
//		if (context.getConnection() == null) {
//			database.connect();
//		}
//		Statement stmt = context.getConnection().createStatement();
//		ResultSet rs = stmt.executeQuery(query);
//		context.getConnection().commit();
//	}
//	public void insertPreAdviceHeader(String preAdviceId) throws SQLException, ClassNotFoundException {
//		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
//		String key = getMaxKeyFromDB("INTERFACE_PRE_ADVICE_HEADER");
//		String query = "Insert into INTERFACE_PRE_ADVICE_HEADER (KEY,CLIENT_ID,PRE_ADVICE_ID,PRE_ADVICE_TYPE,SITE_ID,OWNER_ID,SUPPLIER_ID,STATUS,BOOKREF_ID,DUE_DSTAMP,CONTACT,CONTACT_PHONE,CONTACT_MOBILE,CONTACT_FAX,CONTACT_EMAIL,NAME,ADDRESS1,ADDRESS2,TOWN,COUNTY,POSTCODE,COUNTRY,RETURN_FLAG,SAMPLING_TYPE,RETURNED_ORDER_ID,EMAIL_CONFIRM,COLLECTION_REQD,CONSIGNMENT,LOAD_SEQUENCE,NOTES,DISALLOW_MERGE_RULES,OAP_RMA,DISALLOW_REPLENS,SUPPLIER_REFERENCE,CARRIER_NAME,CARRIER_REFERENCE,TOD,TOD_PLACE,MODE_OF_TRANSPORT,VAT_NUMBER,USER_DEF_TYPE_1,USER_DEF_TYPE_2,USER_DEF_TYPE_3,USER_DEF_TYPE_4,USER_DEF_TYPE_5,USER_DEF_TYPE_6,USER_DEF_TYPE_7,USER_DEF_TYPE_8,USER_DEF_CHK_1,USER_DEF_CHK_2,USER_DEF_CHK_3,USER_DEF_CHK_4,USER_DEF_DATE_1,USER_DEF_DATE_2,USER_DEF_DATE_3,USER_DEF_DATE_4,USER_DEF_NUM_1,USER_DEF_NUM_2,USER_DEF_NUM_3,USER_DEF_NUM_4,USER_DEF_NOTE_1,USER_DEF_NOTE_2,YARD_CONTAINER_TYPE,YARD_CONTAINER_ID,CE_CONSIGNMENT_ID,MASTER_PRE_ADVICE,COLLECTIVE_MODE,COLLECTIVE_SEQUENCE,CE_INVOICE_NUMBER,STATUS_REASON_CODE,PRIORITY,SESSION_TIME_ZONE_NAME,TIME_ZONE_NAME,NLS_CALENDAR,CLIENT_GROUP,MERGE_ACTION,MERGE_STATUS,MERGE_ERROR,MERGE_DSTAMP) values ('"
//                        + key +"','M+S','"
//                        +preAdviceId+"','STO','5542','M+S','4624','Released',null,to_timestamp('"
//                        +queryInsertDate+" 00.00.00.000000000'),null,null,null,null,null,null,null,null,null,null,null,null,'N',null,null,'N','N',null,null,null,'N',null,'N',null,null,null,null,null,null,null,'224101','ZGM',null,null,null,'H',null,null,null,'N','N','N',to_timestamp('"
//                        +queryInsertDate+" 12.05.23.000000000'),null,null,null,null,null,null,5581,null,null,null,null,null,'N',null,null,null,null,null,'Europe/Belfast','Europe/London',null,'INT','U','CMEError','MANDSIF001',to_timestamp('"+queryInsertDate+" 08.49.04.679759000'))";
//		System.out.println("Insert Pre Advice Header");
//		System.out.println(query);
//		if (context.getConnection() == null) {
//			database.connect();
//		}
//		Statement stmt = context.getConnection().createStatement();
//		ResultSet rs = stmt.executeQuery(query);
//		context.getConnection().commit();
//	}
	public void insertUPIReceiptHeader(String palletId) throws SQLException, ClassNotFoundException {
		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
		String key = getMaxKeyFromDB("upi_receipt_header");
		String query = "Insert into upi_receipt_header (KEY,PALLET_ID,SITE_ID,DUE_DSTAMP,RECEIPT_ID,ASN_ID,CLIENT_ID,SUPPLIER_ID,PALLET_CONFIG,VOLUME,HEIGHT,DEPTH,WIDTH,WEIGHT,STATUS,CROSS_DOCK,TO_SITE_ID,SHIP_DOCK,CONSIGNMENT,CUSTOMER_ID,LOAD_SEQUENCE,DISALLOW_MERGE_RULES,NOTES,CARRIER_NAME,CARRIER_REFERENCE,TOD,TOD_PLACE,MODE_OF_TRANSPORT,VAT_NUMBER,USER_DEF_TYPE_1,USER_DEF_TYPE_2,USER_DEF_TYPE_3,USER_DEF_TYPE_4,USER_DEF_TYPE_5,USER_DEF_TYPE_6,USER_DEF_TYPE_7,USER_DEF_TYPE_8,USER_DEF_CHK_1,USER_DEF_CHK_2,USER_DEF_CHK_3,USER_DEF_CHK_4,USER_DEF_DATE_1,USER_DEF_DATE_2,USER_DEF_DATE_3,USER_DEF_DATE_4,USER_DEF_NUM_1,USER_DEF_NUM_2,USER_DEF_NUM_3,USER_DEF_NUM_4,USER_DEF_NOTE_1,USER_DEF_NOTE_2,ROUTE_ID,CROSS_DOCK_TO_SITE,SUP_CONTACT,SUP_CONTACT_PHONE,SUP_CONTACT_MOBILE,SUP_CONTACT_FAX,SUP_CONTACT_EMAIL,SUP_NAME,SUP_ADDRESS1,SUP_ADDRESS2,SUP_TOWN,SUP_COUNTY,SUP_POSTCODE,SUP_COUNTRY,YARD_CONTAINER_TYPE,YARD_CONTAINER_ID,COLLECTIVE_MODE,COLLECTIVE_SEQUENCE,CE_CONSIGNMENT_ID,CE_INVOICE_NUMBER,STATUS_REASON_CODE,PRIORITY,SHIP_BY_DATE,DELIVER_BY_DATE,SESSION_TIME_ZONE_NAME,TIME_ZONE_NAME,NLS_CALENDAR,CLIENT_GROUP,MERGE_ACTION,MERGE_STATUS,MERGE_ERROR,MERGE_DSTAMP) values ('"
						+ key +"','"
				        +palletId+"','5542',to_timestamp('"
						+queryInsertDate+" 00.00.00.000000000'),null,'0000790476','M+S','9577','PALLET',9999,0,null,0,0,'Released','N',null,null,null,null,null,'N',null,null,null,null,null,'ROAD',null,'1','00000000001976602095',null,null,null,'000000000090200020','ZIDC','957700149818012018','N','Y','N','N',to_timestamp('"
						+queryInsertDate+" 10.15.40.000000000'),null,null,null,null,null,null,850,'33826069072210777302501310103210',null,null,'N',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'Europe/Belfast','Europe/London',null,'INT','U','Error','IF1149',to_timestamp('"
						+queryInsertDate+" 08.36.14.552349000'))";

		System.out.println("Insert UPIReceiptheader");
		System.out.println(query);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		context.getConnection().commit();
	}
	
	public void insertPreAdviceline(String preAdviceId) throws SQLException, ClassNotFoundException {
		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
		String key = getMaxKeyFromDB("INTERFACE_PRE_ADVICE_LINE");
		String query = "Insert into INTERFACE_PRE_ADVICE_LINE (KEY,CLIENT_ID,PRE_ADVICE_ID,LINE_ID,HOST_PRE_ADVICE_ID,HOST_LINE_ID,SKU_ID,CONFIG_ID,BATCH_ID,EXPIRY_DSTAMP,MANUF_DSTAMP,PALLET_CONFIG,ORIGIN_ID,CONDITION_ID,TAG_ID,LOCK_CODE,SPEC_CODE,QTY_DUE,NOTES,SAP_PLANT,SAP_STORE_LOC,DISALLOW_MERGE_RULES,USER_DEF_TYPE_1,USER_DEF_TYPE_2,USER_DEF_TYPE_3,USER_DEF_TYPE_4,USER_DEF_TYPE_5,USER_DEF_TYPE_6,USER_DEF_TYPE_7,USER_DEF_TYPE_8,USER_DEF_CHK_1,USER_DEF_CHK_2,USER_DEF_CHK_3,USER_DEF_CHK_4,USER_DEF_DATE_1,USER_DEF_DATE_2,USER_DEF_DATE_3,USER_DEF_DATE_4,USER_DEF_NUM_1,USER_DEF_NUM_2,USER_DEF_NUM_3,USER_DEF_NUM_4,USER_DEF_NOTE_1,USER_DEF_NOTE_2,TRACKING_LEVEL,QTY_DUE_TOLERANCE,CE_COO,OWNER_ID,CE_CONSIGNMENT_ID,COLLECTIVE_MODE,COLLECTIVE_SEQUENCE,CE_UNDER_BOND,CE_LINK,PRODUCT_PRICE,PRODUCT_CURRENCY,CE_INVOICE_NUMBER,SERIAL_VALID_MERGE,SAMPLING_TYPE,EXPECTED_GROSS_WEIGHT,EXPECTED_NET_WEIGHT,SESSION_TIME_ZONE_NAME,TIME_ZONE_NAME,NLS_CALENDAR,CLIENT_GROUP,MERGE_ACTION,MERGE_STATUS,MERGE_ERROR,MERGE_DSTAMP) values ('"
				       + key +"','M+S','"
				       +preAdviceId+"',10,null,null,'000000000021071852',null,null,null,null,null,null,null,null,null,null,30,null,null,null,'N','969108','ZGM',null,'5571','03835975','SP13','0625A','B',null,null,null,null,to_timestamp('"+queryInsertDate+" 14.05.55.000000000'),null,null,null,null,null,2018,31,null,null,'EA',null,null,'M+S',null,null,null,null,null,null,null,null,'N',null,null,null,'Europe/London',null,null,'INT','A','CMEError','MANDSIF001',to_timestamp('"
				       +queryInsertDate+" 14.05.57.342179000'))";
		System.out.println("Insert Pre Advice line");
		System.out.println(query);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		context.getConnection().commit();
	}
	public void insertUPIReceiptline(String palletId) throws SQLException, ClassNotFoundException {
		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
		String key = getMaxKeyFromDB("upi_receipt_line");
		String query = "Insert into upi_receipt_line (KEY,PALLET_ID,LINE_ID,HOST_PALLET_ID,HOST_LINE_ID,TAG_ID,OWNER_ID,CLIENT_ID,SKU_ID,CONFIG_ID,TRACKING_LEVEL,ORIGIN_ID,CONDITION_ID,LOCK_CODE,SPEC_CODE,SUPPLIER_ID,BATCH_ID,EXPIRY_DSTAMP,MANUF_DSTAMP,RECEIPT_DSTAMP,QTY_DUE,PRE_ADVICE_ID,PRE_ADVICE_LINE_ID,DISALLOW_MERGE_RULES,USER_DEF_TYPE_1,USER_DEF_TYPE_2,USER_DEF_TYPE_3,USER_DEF_TYPE_4,USER_DEF_TYPE_5,USER_DEF_TYPE_6,USER_DEF_TYPE_7,USER_DEF_TYPE_8,USER_DEF_CHK_1,USER_DEF_CHK_2,USER_DEF_CHK_3,USER_DEF_CHK_4,USER_DEF_DATE_1,USER_DEF_DATE_2,USER_DEF_DATE_3,USER_DEF_DATE_4,USER_DEF_NUM_1,USER_DEF_NUM_2,USER_DEF_NUM_3,USER_DEF_NUM_4,USER_DEF_NOTE_1,USER_DEF_NOTE_2,COLLECTIVE_MODE,COLLECTIVE_SEQUENCE,CE_CONSIGNMENT_ID,PRODUCT_PRICE,PRODUCT_CURRENCY,CE_INVOICE_NUMBER,CE_UNDER_BOND,CE_LINK,CE_COO,EXPECTED_GROSS_WEIGHT,EXPECTED_NET_WEIGHT,CONTAINER_ID,SESSION_TIME_ZONE_NAME,TIME_ZONE_NAME,NLS_CALENDAR,CLIENT_GROUP,MERGE_ACTION,MERGE_STATUS,MERGE_ERROR,MERGE_DSTAMP)values ('"
		               + key +"','"
				       +palletId+"',10,null,null,'"
		               +palletId+"','M+S','M+S','000000000021071852',null,'EA',null,null,null,null,'M07972',null,null,null,null,60,null,null,'N','7666561625000010','4026726139','0007','04212287','0000790463',null,'ZIDC',null,'N','N','N','N',to_timestamp('"
				       +queryInsertDate+" 10.31.04.000000000'),null,null,null,1,null,null,716,'28209590001303','71974451022760797202701610103010',null,null,null,null,null,null,'N','N',null,null,null,null,'Europe/Belfast','London/Europe',null,'INT','U','Error','IF0910',to_timestamp('"+queryInsertDate+" 07.47.31.220339000'))";

		System.out.println("Insert upi_receipt_line");
		System.out.println(query);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		context.getConnection().commit();
	}
		public void insertOrderHeader(String orderId, String stoType, String customer)
			throws SQLException, ClassNotFoundException {
		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
		String queryInsertNextDate = DateUtils.getSystemDate1stAddInDBFormat();
		String queryInsertDayAfterTommDate = DateUtils.getSystemDate2ndAddInDBFormat();
		// String key = Utilities.getFourDigitRandomNumber()+".0";
//		String key = "100";
		String key = getMaxKeyFromDB("INTERFACE_ORDER_HEADER");
		System.out.println("sto"+stoType);
		System.out.println("customer"+customer);
		System.out.println(key);

		
//		String key= "2";
		String query = null;
		if (stoType.equals("RETAIL") && (customer.equals("5542"))) {
			System.out.println("inside retail");
			query = "INSERT INTO INTERFACE_ORDER_HEADER (KEY,CLIENT_ID, ORDER_ID, ORDER_TYPE, WORK_ORDER_TYPE, STATUS, MOVE_TASK_STATUS, PRIORITY, REPACK,REPACK_LOC_ID, DELIVERY_POINT, LOAD_SEQUENCE, FROM_SITE_ID, TO_SITE_ID, OWNER_ID, CUSTOMER_ID, CONTACT, CONTACT_PHONE,CONTACT_MOBILE,CONTACT_FAX, CONTACT_EMAIL, NAME, ADDRESS1, ADDRESS2, TOWN, COUNTY, POSTCODE, COUNTRY,SHIP_DOCK, WORK_GROUP, CONSIGNMENT, ORDER_DATE,SHIP_BY_DATE, DELIVER_BY_DATE, DELIVERED_DSTAMP, SIGNATORY, PURCHASE_ORDER, CARRIER_ID, DISPATCH_METHOD, SERVICE_LEVEL, FASTEST_CARRIER,CHEAPEST_CARRIER, INV_ADDRESS_ID, INV_CONTACT, INV_CONTACT_PHONE, INV_CONTACT_MOBILE,INV_CONTACT_FAX,INV_CONTACT_EMAIL, INV_NAME, INV_ADDRESS1, INV_ADDRESS2, INV_TOWN, INV_COUNTY, INV_POSTCODE, INV_COUNTRY, INSTRUCTIONS,PSFT_DMND_SRCE, PSFT_ORDER_ID, SITE_REPLEN, CID_NUMBER, SID_NUMBER, LOCATION_NUMBER, FREIGHT_CHARGES, DISALLOW_MERGE_RULES,EXPORT,USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_6, USER_DEF_TYPE_7, USER_DEF_TYPE_8,USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4,USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, CE_REASON_CODE, CE_REASON_NOTES,CE_ORDER_TYPE, CE_CUSTOMS_CUSTOMER,CE_EXCISE_CUSTOMER, CE_INSTRUCTIONS, ROUTE_ID, CROSS_DOCK_TO_SITE, WEB_SERVICE_ALLOC_IMMED,WEB_SERVICE_ALLOC_CLEAN, DISALLOW_SHORT_SHIP, HUB_ADDRESS_ID, HUB_CONTACT,HUB_CONTACT_PHONE, HUB_CONTACT_MOBILE, HUB_CONTACT_FAX,HUB_CONTACT_EMAIL, HUB_NAME, HUB_ADDRESS1,HUB_ADDRESS2, HUB_TOWN, HUB_COUNTY, HUB_POSTCODE, HUB_COUNTRY, STATUS_REASON_CODE, STAGE_ROUTE_ID, SINGLE_ORDER_SORTATION,FORCE_SINGLE_CARRIER, HUB_CARRIER_ID, HUB_SERVICE_LEVEL, EXPECTED_VOLUME, EXPECTED_WEIGHT, EXPECTED_VALUE, TOD, TOD_PLACE, LANGUAGE,SELLER_NAME, SELLER_PHONE, DOCUMENTATION_TEXT_1, DOCUMENTATION_TEXT_2, DOCUMENTATION_TEXT_3, COD, COD_VALUE, COD_CURRENCY, COD_TYPE,VAT_NUMBER, INV_VAT_NUMBER, HUB_VAT_NUMBER, PRINT_INVOICE, INV_REFERENCE, INV_DSTAMP, INV_CURRENCY, LETTER_OF_CREDIT, PAYMENT_TERMS,SUBTOTAL_1, SUBTOTAL_2, SUBTOTAL_3, SUBTOTAL_4, FREIGHT_COST, FREIGHT_TERMS, INSURANCE_COST, MISC_CHARGES, DISCOUNT, OTHER_FEE, INV_TOTAL_1,INV_TOTAL_2, INV_TOTAL_3,INV_TOTAL_4, TAX_RATE_1, TAX_BASIS_1, TAX_AMOUNT_1, TAX_RATE_2, TAX_BASIS_2, TAX_AMOUNT_2, TAX_RATE_3, TAX_BASIS_3,TAX_AMOUNT_3, TAX_RATE_4, TAX_BASIS_4, TAX_AMOUNT_4, TAX_RATE_5, TAX_BASIS_5, TAX_AMOUNT_5, ORDER_REFERENCE, START_BY_DATE,METAPACK_CARRIER_PRE, SHIPMENT_GROUP, FREIGHT_CURRENCY, NCTS, MPACK_CONSIGNMENT, MPACK_NOMINATED_DSTAMP, GLN, HUB_GLN, INV_GLN,ALLOW_PALLET_PICK, SPLIT_SHIPPING_UNITS, VOL_PCK_SSCC_LABEL, ALLOCATION_PRIORITY, TRAX_USE_HUB_ADDR, DIRECT_TO_STORE, VOL_CTR_LABEL_FORMAT,RETAILER_ID, CARRIER_BAGS, SOH_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS,MERGE_ERROR, MERGE_DSTAMP) VALUES ('"
					+ key + "', 'M+S', '" + orderId
					+"','RETAIL', '', 'Released', 'Released', '20', 'N', '', '', NULL, '5542', '', 'M+S', '8455', 'CZECH', '','', '', '', 'CZECH', 'Marks and Spencer Services sro  BB C', '', 'Vyskocilova 1481/4', '', '140 20', 'CZE', 'TUR1SD', '', '', '', '', '', '', '', '', '', '', '', 'N', 'N', '', '', '', '', '', '', '', '', '', '', '', '','', '140', '', '', 'N', '', '', NULL, '', 'N', 'N', '', '', '', 'IntlFranchise', '', 'Retail', 'ZF24', '', 'N', 'N', 'N', 'N', '"
					+ queryInsertDate
					+" 06.38.46.150479000', '', '', '',NULL, NULL, NULL, NULL, '583CA6D3B3D70510E10080100A9044F1', '', '', '', '', '', '', '', '', 'N', '', '', '', '7993','Bradford International Wa', '12345698', '', '', '', 'Bradford International Warehouse', 'Newhall Way', '', 'Bradford','United Kingdom', '', '', '', '', 'N', 'N', '', '','1.273560', '448.287640', NULL, '', '', '', '', '', '', '','', '', NULL, '', '', '', '', '','N', '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', 'N', '', '', '', '', '', '', '', '', 'N', 'N', 'N', NULL, 'N', 'N', '', '', 'N', '', '', NULL, 'Europe/Belfast', 'Europe/London', '', 'M+S', 'A', 'Pending','', '"
					+ queryInsertDate+ " 06.38.46.150479000')";
} else if (stoType.equals("NONRETAIL") && (customer.equals("5542"))) {
	        System.out.println("inside nonretail");
			query = "INSERT INTO INTERFACE_ORDER_HEADER (KEY,CLIENT_ID, ORDER_ID, ORDER_TYPE, WORK_ORDER_TYPE, STATUS, MOVE_TASK_STATUS, PRIORITY, REPACK,REPACK_LOC_ID, DELIVERY_POINT, LOAD_SEQUENCE, FROM_SITE_ID, TO_SITE_ID, OWNER_ID, CUSTOMER_ID, CONTACT, CONTACT_PHONE,CONTACT_MOBILE,CONTACT_FAX, CONTACT_EMAIL, NAME, ADDRESS1, ADDRESS2, TOWN, COUNTY, POSTCODE, COUNTRY,SHIP_DOCK, WORK_GROUP, CONSIGNMENT, ORDER_DATE,SHIP_BY_DATE, DELIVER_BY_DATE, DELIVERED_DSTAMP, SIGNATORY, PURCHASE_ORDER, CARRIER_ID, DISPATCH_METHOD, SERVICE_LEVEL, FASTEST_CARRIER,CHEAPEST_CARRIER, INV_ADDRESS_ID, INV_CONTACT, INV_CONTACT_PHONE, INV_CONTACT_MOBILE,INV_CONTACT_FAX,INV_CONTACT_EMAIL, INV_NAME, INV_ADDRESS1, INV_ADDRESS2, INV_TOWN, INV_COUNTY, INV_POSTCODE, INV_COUNTRY, INSTRUCTIONS,PSFT_DMND_SRCE, PSFT_ORDER_ID, SITE_REPLEN, CID_NUMBER, SID_NUMBER, LOCATION_NUMBER, FREIGHT_CHARGES, DISALLOW_MERGE_RULES,EXPORT,USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_6, USER_DEF_TYPE_7, USER_DEF_TYPE_8,USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4,USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, CE_REASON_CODE, CE_REASON_NOTES,CE_ORDER_TYPE, CE_CUSTOMS_CUSTOMER,CE_EXCISE_CUSTOMER, CE_INSTRUCTIONS, ROUTE_ID, CROSS_DOCK_TO_SITE, WEB_SERVICE_ALLOC_IMMED,WEB_SERVICE_ALLOC_CLEAN, DISALLOW_SHORT_SHIP, HUB_ADDRESS_ID, HUB_CONTACT,HUB_CONTACT_PHONE, HUB_CONTACT_MOBILE, HUB_CONTACT_FAX,HUB_CONTACT_EMAIL, HUB_NAME, HUB_ADDRESS1,HUB_ADDRESS2, HUB_TOWN, HUB_COUNTY, HUB_POSTCODE, HUB_COUNTRY, STATUS_REASON_CODE, STAGE_ROUTE_ID, SINGLE_ORDER_SORTATION,FORCE_SINGLE_CARRIER, HUB_CARRIER_ID, HUB_SERVICE_LEVEL, EXPECTED_VOLUME, EXPECTED_WEIGHT, EXPECTED_VALUE, TOD, TOD_PLACE, LANGUAGE,SELLER_NAME, SELLER_PHONE, DOCUMENTATION_TEXT_1, DOCUMENTATION_TEXT_2, DOCUMENTATION_TEXT_3, COD, COD_VALUE, COD_CURRENCY, COD_TYPE,VAT_NUMBER, INV_VAT_NUMBER, HUB_VAT_NUMBER, PRINT_INVOICE, INV_REFERENCE, INV_DSTAMP, INV_CURRENCY, LETTER_OF_CREDIT, PAYMENT_TERMS,SUBTOTAL_1, SUBTOTAL_2, SUBTOTAL_3, SUBTOTAL_4, FREIGHT_COST, FREIGHT_TERMS, INSURANCE_COST, MISC_CHARGES, DISCOUNT, OTHER_FEE, INV_TOTAL_1,INV_TOTAL_2, INV_TOTAL_3,INV_TOTAL_4, TAX_RATE_1, TAX_BASIS_1, TAX_AMOUNT_1, TAX_RATE_2, TAX_BASIS_2, TAX_AMOUNT_2, TAX_RATE_3, TAX_BASIS_3,TAX_AMOUNT_3, TAX_RATE_4, TAX_BASIS_4, TAX_AMOUNT_4, TAX_RATE_5, TAX_BASIS_5, TAX_AMOUNT_5, ORDER_REFERENCE, START_BY_DATE,METAPACK_CARRIER_PRE, SHIPMENT_GROUP, FREIGHT_CURRENCY, NCTS, MPACK_CONSIGNMENT, MPACK_NOMINATED_DSTAMP, GLN, HUB_GLN, INV_GLN,ALLOW_PALLET_PICK, SPLIT_SHIPPING_UNITS, VOL_PCK_SSCC_LABEL, ALLOCATION_PRIORITY, TRAX_USE_HUB_ADDR, DIRECT_TO_STORE, VOL_CTR_LABEL_FORMAT,RETAILER_ID, CARRIER_BAGS, SOH_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS,MERGE_ERROR, MERGE_DSTAMP) VALUES ('"
					+ key + "', 'M+S', '" + orderId
					+"','NONRETAIL', '', 'Released', 'Released', '20', 'N', '', '', NULL, '5542', '', 'M+S', '4624', 'DONINGTON EDC/NDC', '','', '', '', 'DONINGTON EDC/NDC', 'EAST MIDLANDS DISTRIBUTION CNTR', '', '', '', 'DE74 2HJ', 'CZE', 'TUR1SD', '', '', '', '', '', '', '', '', '', '', '', 'N', 'N', '', '', '', '', '', '', '', '', '', '', '', '','', '140', '', '', 'N', '', '', NULL, '', 'N', 'N', '', '', '', 'MANUAL', '', 'IDT', 'ZNL1', '', 'N', 'N', 'N', 'N', '"
					+ queryInsertDate
					+" 06.38.46.150479000', '', '', '',NULL, NULL, NULL, NULL, '583CA6D3B3D70510E10080100A9044F1', '', '', '', '', '', '', '', '', 'N', '', '', '', '7993','Bradford International Wa', '12345698', '', '', '', 'Bradford International Warehouse', 'Newhall Way', '', 'Bradford','United Kingdom', '', '', '', '', 'N', 'N', '', '','1.273560', '448.287640', NULL, '', '', '', '', '', '', '','', '', NULL, '', '', '', '', '','N', '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', 'N', '', '', '', '', '', '', '', '', 'N', 'N', 'N', NULL, 'N', 'N', '', '', 'N', '', '', NULL, 'Europe/Belfast', 'Europe/London', '', 'M+S', 'A', 'Pending','', '"
					+ queryInsertDate+ " 06.38.46.150479000')";	
		} else if (stoType.equals("RDC") && (customer.equals("5542"))) {
			query = "INSERT INTO INTERFACE_ORDER_HEADER (KEY,CLIENT_ID, ORDER_ID, ORDER_TYPE, WORK_ORDER_TYPE, STATUS, MOVE_TASK_STATUS, PRIORITY, REPACK,REPACK_LOC_ID, DELIVERY_POINT, LOAD_SEQUENCE, FROM_SITE_ID, TO_SITE_ID, OWNER_ID, CUSTOMER_ID, CONTACT, CONTACT_PHONE,CONTACT_MOBILE,CONTACT_FAX, CONTACT_EMAIL, NAME, ADDRESS1, ADDRESS2, TOWN, COUNTY, POSTCODE, COUNTRY,SHIP_DOCK, WORK_GROUP, CONSIGNMENT, ORDER_DATE,SHIP_BY_DATE, DELIVER_BY_DATE, DELIVERED_DSTAMP, SIGNATORY, PURCHASE_ORDER, CARRIER_ID, DISPATCH_METHOD, SERVICE_LEVEL, FASTEST_CARRIER,CHEAPEST_CARRIER, INV_ADDRESS_ID, INV_CONTACT, INV_CONTACT_PHONE, INV_CONTACT_MOBILE,INV_CONTACT_FAX,INV_CONTACT_EMAIL, INV_NAME, INV_ADDRESS1, INV_ADDRESS2, INV_TOWN, INV_COUNTY, INV_POSTCODE, INV_COUNTRY, INSTRUCTIONS,PSFT_DMND_SRCE, PSFT_ORDER_ID, SITE_REPLEN, CID_NUMBER, SID_NUMBER, LOCATION_NUMBER, FREIGHT_CHARGES, DISALLOW_MERGE_RULES,EXPORT,USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_6, USER_DEF_TYPE_7, USER_DEF_TYPE_8,USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4,USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, CE_REASON_CODE, CE_REASON_NOTES,CE_ORDER_TYPE, CE_CUSTOMS_CUSTOMER,CE_EXCISE_CUSTOMER, CE_INSTRUCTIONS, ROUTE_ID, CROSS_DOCK_TO_SITE, WEB_SERVICE_ALLOC_IMMED,WEB_SERVICE_ALLOC_CLEAN, DISALLOW_SHORT_SHIP, HUB_ADDRESS_ID, HUB_CONTACT,HUB_CONTACT_PHONE, HUB_CONTACT_MOBILE, HUB_CONTACT_FAX,HUB_CONTACT_EMAIL, HUB_NAME, HUB_ADDRESS1,HUB_ADDRESS2, HUB_TOWN, HUB_COUNTY, HUB_POSTCODE, HUB_COUNTRY, STATUS_REASON_CODE, STAGE_ROUTE_ID, SINGLE_ORDER_SORTATION,FORCE_SINGLE_CARRIER, HUB_CARRIER_ID, HUB_SERVICE_LEVEL, EXPECTED_VOLUME, EXPECTED_WEIGHT, EXPECTED_VALUE, TOD, TOD_PLACE, LANGUAGE,SELLER_NAME, SELLER_PHONE, DOCUMENTATION_TEXT_1, DOCUMENTATION_TEXT_2, DOCUMENTATION_TEXT_3, COD, COD_VALUE, COD_CURRENCY, COD_TYPE,VAT_NUMBER, INV_VAT_NUMBER, HUB_VAT_NUMBER, PRINT_INVOICE, INV_REFERENCE, INV_DSTAMP, INV_CURRENCY, LETTER_OF_CREDIT, PAYMENT_TERMS,SUBTOTAL_1, SUBTOTAL_2, SUBTOTAL_3, SUBTOTAL_4, FREIGHT_COST, FREIGHT_TERMS, INSURANCE_COST, MISC_CHARGES, DISCOUNT, OTHER_FEE, INV_TOTAL_1,INV_TOTAL_2, INV_TOTAL_3,INV_TOTAL_4, TAX_RATE_1, TAX_BASIS_1, TAX_AMOUNT_1, TAX_RATE_2, TAX_BASIS_2, TAX_AMOUNT_2, TAX_RATE_3, TAX_BASIS_3,TAX_AMOUNT_3, TAX_RATE_4, TAX_BASIS_4, TAX_AMOUNT_4, TAX_RATE_5, TAX_BASIS_5, TAX_AMOUNT_5, ORDER_REFERENCE, START_BY_DATE,METAPACK_CARRIER_PRE, SHIPMENT_GROUP, FREIGHT_CURRENCY, NCTS, MPACK_CONSIGNMENT, MPACK_NOMINATED_DSTAMP, GLN, HUB_GLN, INV_GLN,ALLOW_PALLET_PICK, SPLIT_SHIPPING_UNITS, VOL_PCK_SSCC_LABEL, ALLOCATION_PRIORITY, TRAX_USE_HUB_ADDR, DIRECT_TO_STORE, VOL_CTR_LABEL_FORMAT,RETAILER_ID, CARRIER_BAGS, SOH_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS,MERGE_ERROR, MERGE_DSTAMP) VALUES ('"
					+ key + "', 'M+S', '" + orderId
					+"','RETAIL', '', 'Released', 'Released', '20', 'N', '', '', NULL, '5542', '', 'M+S', '8455', 'CZECH', '','', '', '', 'CZECH', 'Marks and Spencer Services sro  BB C', '', 'Vyskocilova 1481/4', '', '140 20', 'CZE', 'TUR1SD', '', '', '', '', '', '', '', '', '', '', '', 'N', 'N', '', '', '', '', '', '', '', '', '', '', '', '','', '140', '', '', 'N', '', '', NULL, '', 'N', 'N', '', '', '', 'IntlFranchise', '', 'Retail', 'ZF24', '', 'N', 'N', 'N', 'N', '"
					+ queryInsertDate
					+" 06.38.46.150479000', '', '', '',NULL, NULL, NULL, NULL, '583CA6D3B3D70510E10080100A9044F1', '', '', '', '', '', '', '', '', 'N', '', '', '', '7993','Bradford International Wa', '12345698', '', '', '', 'Bradford International Warehouse', 'Newhall Way', '', 'Bradford','United Kingdom', '', '', '', '', 'N', 'N', '', '','1.273560', '448.287640', NULL, '', '', '', '', '', '', '','', '', NULL, '', '', '', '', '','N', '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', 'N', '', '', '', '', '', '', '', '', 'N', 'N', 'N', NULL, 'N', 'N', '', '', 'N', '', '', NULL, 'Europe/Belfast', 'Europe/London', '', 'M+S', 'A', 'Pending','', '"
					+ queryInsertDate+ " 06.38.46.150479000')";
		} else if (stoType.equals("STR") && (customer.equals("0065"))) {
			query = "INSERT INTO INTERFACE_ORDER_HEADER (KEY, CLIENT_ID, ORDER_ID, ORDER_TYPE, WORK_ORDER_TYPE, STATUS, MOVE_TASK_STATUS, PRIORITY, REPACK, REPACK_LOC_ID, DELIVERY_POINT, LOAD_SEQUENCE, FROM_SITE_ID, TO_SITE_ID, OWNER_ID, CUSTOMER_ID, CONTACT, CONTACT_PHONE, CONTACT_MOBILE, CONTACT_FAX, CONTACT_EMAIL, NAME, ADDRESS1, ADDRESS2, TOWN, COUNTY, POSTCODE, COUNTRY, SHIP_DOCK, WORK_GROUP, CONSIGNMENT, ORDER_DATE, SHIP_BY_DATE, DELIVER_BY_DATE, DELIVERED_DSTAMP, SIGNATORY, PURCHASE_ORDER, CARRIER_ID, DISPATCH_METHOD, SERVICE_LEVEL, FASTEST_CARRIER, CHEAPEST_CARRIER, INV_ADDRESS_ID, INV_CONTACT, INV_CONTACT_PHONE, INV_CONTACT_MOBILE, INV_CONTACT_FAX, INV_CONTACT_EMAIL, INV_NAME, INV_ADDRESS1, INV_ADDRESS2, INV_TOWN, INV_COUNTY, INV_POSTCODE, INV_COUNTRY, INSTRUCTIONS, PSFT_DMND_SRCE, PSFT_ORDER_ID, SITE_REPLEN, CID_NUMBER, SID_NUMBER, LOCATION_NUMBER, FREIGHT_CHARGES, DISALLOW_MERGE_RULES, EXPORT, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_6, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, CE_REASON_CODE, CE_REASON_NOTES, CE_ORDER_TYPE, CE_CUSTOMS_CUSTOMER, CE_EXCISE_CUSTOMER, CE_INSTRUCTIONS, ROUTE_ID, CROSS_DOCK_TO_SITE, WEB_SERVICE_ALLOC_IMMED, WEB_SERVICE_ALLOC_CLEAN, DISALLOW_SHORT_SHIP, HUB_ADDRESS_ID, HUB_CONTACT, HUB_CONTACT_PHONE, HUB_CONTACT_MOBILE, HUB_CONTACT_FAX, HUB_CONTACT_EMAIL, HUB_NAME, HUB_ADDRESS1, HUB_ADDRESS2, HUB_TOWN, HUB_COUNTY, HUB_POSTCODE, HUB_COUNTRY, STATUS_REASON_CODE, STAGE_ROUTE_ID, SINGLE_ORDER_SORTATION, FORCE_SINGLE_CARRIER, HUB_CARRIER_ID, HUB_SERVICE_LEVEL, EXPECTED_VOLUME, EXPECTED_WEIGHT, EXPECTED_VALUE, TOD, TOD_PLACE, LANGUAGE, SELLER_NAME, SELLER_PHONE, DOCUMENTATION_TEXT_1, DOCUMENTATION_TEXT_2, DOCUMENTATION_TEXT_3, COD, COD_VALUE, COD_CURRENCY, COD_TYPE, VAT_NUMBER, INV_VAT_NUMBER, HUB_VAT_NUMBER, PRINT_INVOICE, INV_REFERENCE, INV_DSTAMP, INV_CURRENCY, LETTER_OF_CREDIT, PAYMENT_TERMS, SUBTOTAL_1, SUBTOTAL_2, SUBTOTAL_3, SUBTOTAL_4, FREIGHT_COST, FREIGHT_TERMS, INSURANCE_COST, MISC_CHARGES, DISCOUNT, OTHER_FEE, INV_TOTAL_1, INV_TOTAL_2, INV_TOTAL_3, INV_TOTAL_4, TAX_RATE_1, TAX_BASIS_1, TAX_AMOUNT_1, TAX_RATE_2, TAX_BASIS_2, TAX_AMOUNT_2, TAX_RATE_3, TAX_BASIS_3, TAX_AMOUNT_3, TAX_RATE_4, TAX_BASIS_4, TAX_AMOUNT_4, TAX_RATE_5, TAX_BASIS_5, TAX_AMOUNT_5, ORDER_REFERENCE, START_BY_DATE, METAPACK_CARRIER_PRE, SHIPMENT_GROUP, FREIGHT_CURRENCY, NCTS, MPACK_CONSIGNMENT, MPACK_NOMINATED_DSTAMP, GLN, HUB_GLN, INV_GLN, ALLOW_PALLET_PICK, SPLIT_SHIPPING_UNITS, VOL_PCK_SSCC_LABEL, ALLOCATION_PRIORITY, TRAX_USE_HUB_ADDR, DIRECT_TO_STORE, VOL_CTR_LABEL_FORMAT, RETAILER_ID, CARRIER_BAGS, SOH_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key + ", 'M+S', '" + orderId
					+ "', 'STR', '', 'Released', 'Released', '20', 'N', '', '', NULL, '9771', '', 'M+S', '0065', 'ST JOHNS JERSEY SF', '1534869924', '', '', '', 'ST JOHNS JERSEY SF', 'La Route des Issues  Jersey', '', 'ST JOHNS', '', '', 'JEY', '', '', '', '', '', '', '', '', '', '', '', '', 'N', 'N', '', '', '', '', '', '', '', '', '', '', '', '', '', '140', '', '', 'N', '', '', NULL, '', 'N', 'N', '', '', '', '', '', '', 'DV', '', 'N', 'N', 'N', 'N', '"
					+ queryInsertDate
					+ " 06.38.46.150479000', '', '', '', NULL, NULL, NULL, NULL, '583CA6D3B3D70510E10080000A9044F1', '', '', '', '', '', '', '', '', 'N', '', '', '', '7993', 'Bradford International Wa', '12345678', '', '', '', 'Bradford International Warehouse', 'Newhall Way', '', 'Bradford', 'United Kingdom', '', '', '', '', 'N', 'N', '', '', 1.273567, 448.287648, NULL, '', '', '', '', '', '', '', '', '', NULL, '', '', '', '', '', 'N', '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', 'N', '', '', '', '', '', '', '', '', 'N', 'N', 'N', NULL, 'N', 'N', '', '', 'N', '', '', NULL, 'Europe/Belfast', 'Europe/London', '', 'FOODS', 'U', 'Pending', '', '"
					+ queryInsertDate + " 06.38.46.150479000')";
		} else if (stoType.equals("STR") && (customer.equals("0437"))) {
			query = "INSERT INTO INTERFACE_ORDER_HEADER (KEY, CLIENT_ID, ORDER_ID, ORDER_TYPE, WORK_ORDER_TYPE, STATUS, MOVE_TASK_STATUS, PRIORITY, REPACK, REPACK_LOC_ID, DELIVERY_POINT, LOAD_SEQUENCE, FROM_SITE_ID, TO_SITE_ID, OWNER_ID, CUSTOMER_ID, CONTACT, CONTACT_PHONE, CONTACT_MOBILE, CONTACT_FAX, CONTACT_EMAIL, NAME, ADDRESS1, ADDRESS2, TOWN, COUNTY, POSTCODE, COUNTRY, SHIP_DOCK, WORK_GROUP, CONSIGNMENT, ORDER_DATE, SHIP_BY_DATE, DELIVER_BY_DATE, DELIVERED_DSTAMP, SIGNATORY, PURCHASE_ORDER, CARRIER_ID, DISPATCH_METHOD, SERVICE_LEVEL, FASTEST_CARRIER, CHEAPEST_CARRIER, INV_ADDRESS_ID, INV_CONTACT, INV_CONTACT_PHONE, INV_CONTACT_MOBILE, INV_CONTACT_FAX, INV_CONTACT_EMAIL, INV_NAME, INV_ADDRESS1, INV_ADDRESS2, INV_TOWN, INV_COUNTY, INV_POSTCODE, INV_COUNTRY, INSTRUCTIONS, PSFT_DMND_SRCE, PSFT_ORDER_ID, SITE_REPLEN, CID_NUMBER, SID_NUMBER, LOCATION_NUMBER, FREIGHT_CHARGES, DISALLOW_MERGE_RULES, EXPORT, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_6, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, CE_REASON_CODE, CE_REASON_NOTES, CE_ORDER_TYPE, CE_CUSTOMS_CUSTOMER, CE_EXCISE_CUSTOMER, CE_INSTRUCTIONS, ROUTE_ID, CROSS_DOCK_TO_SITE, WEB_SERVICE_ALLOC_IMMED, WEB_SERVICE_ALLOC_CLEAN, DISALLOW_SHORT_SHIP, HUB_ADDRESS_ID, HUB_CONTACT, HUB_CONTACT_PHONE, HUB_CONTACT_MOBILE, HUB_CONTACT_FAX, HUB_CONTACT_EMAIL, HUB_NAME, HUB_ADDRESS1, HUB_ADDRESS2, HUB_TOWN, HUB_COUNTY, HUB_POSTCODE, HUB_COUNTRY, STATUS_REASON_CODE, STAGE_ROUTE_ID, SINGLE_ORDER_SORTATION, FORCE_SINGLE_CARRIER, HUB_CARRIER_ID, HUB_SERVICE_LEVEL, EXPECTED_VOLUME, EXPECTED_WEIGHT, EXPECTED_VALUE, TOD, TOD_PLACE, LANGUAGE, SELLER_NAME, SELLER_PHONE, DOCUMENTATION_TEXT_1, DOCUMENTATION_TEXT_2, DOCUMENTATION_TEXT_3, COD, COD_VALUE, COD_CURRENCY, COD_TYPE, VAT_NUMBER, INV_VAT_NUMBER, HUB_VAT_NUMBER, PRINT_INVOICE, INV_REFERENCE, INV_DSTAMP, INV_CURRENCY, LETTER_OF_CREDIT, PAYMENT_TERMS, SUBTOTAL_1, SUBTOTAL_2, SUBTOTAL_3, SUBTOTAL_4, FREIGHT_COST, FREIGHT_TERMS, INSURANCE_COST, MISC_CHARGES, DISCOUNT, OTHER_FEE, INV_TOTAL_1, INV_TOTAL_2, INV_TOTAL_3, INV_TOTAL_4, TAX_RATE_1, TAX_BASIS_1, TAX_AMOUNT_1, TAX_RATE_2, TAX_BASIS_2, TAX_AMOUNT_2, TAX_RATE_3, TAX_BASIS_3, TAX_AMOUNT_3, TAX_RATE_4, TAX_BASIS_4, TAX_AMOUNT_4, TAX_RATE_5, TAX_BASIS_5, TAX_AMOUNT_5, ORDER_REFERENCE, START_BY_DATE, METAPACK_CARRIER_PRE, SHIPMENT_GROUP, FREIGHT_CURRENCY, NCTS, MPACK_CONSIGNMENT, MPACK_NOMINATED_DSTAMP, GLN, HUB_GLN, INV_GLN, ALLOW_PALLET_PICK, SPLIT_SHIPPING_UNITS, VOL_PCK_SSCC_LABEL, ALLOCATION_PRIORITY, TRAX_USE_HUB_ADDR, DIRECT_TO_STORE, VOL_CTR_LABEL_FORMAT, RETAILER_ID, CARRIER_BAGS, SOH_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key + ", 'M+S', '" + orderId
					+ "', 'STR', '', 'Released', 'Released', '10', 'N', '', '', NULL, '9771', '', 'M+S', '0437', 'LIVERPOOL', '', '', '', '', 'LIVERPOOL', '35 Church Street', '', 'Liverpool', '', '', 'GBR', '', '', '', '', '', '', '', '', '', '', '', '', 'N', 'N', '', '', '', '', '', '', '', '', '', '', '', '', '', '140', '', '', 'N', '', '', NULL, '', 'N', 'N', '', '', '', 'UK', '', '', '', 'ZN8', 'N', 'N', 'Y', 'N', '"
					+ queryInsertDate
					+ " 06.38.46.150479000', '', '', '', NULL, NULL, NULL, NULL, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', 'GBR', '', '', 'N', 'N', '', '', 1.273567, 448.287648, NULL, '', '', '', '', '', '', '', '', '', NULL, '', '', '', '', '', 'N', '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', 'N', '', '', '', '', '', '', '', '', 'N', 'N', 'N', NULL, 'N', 'N', '', '', 'N', '', '', NULL, '', '', '', 'FOODS', 'U', 'Pending', '', '"
					+ queryInsertDate + " 06.38.46.150479000')";
		}
		System.out.println("Insert Order Header");
		System.out.println(query);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		context.getConnection().commit();
	}

	private String getMaxKeyFromDB(String tableName) throws ClassNotFoundException, SQLException {
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select max(key) from " + tableName);
		System.out.println("Query --->" + "select max(key) from " + tableName);

		if (rs.next()) {

			System.out.println("Data not found");
			String key = String.valueOf(Integer.parseInt(rs.getString(1) + 1));
//			String key = String.valueOf(1);
			return key;

		} else {
			String key = String.valueOf(1);
//			String key = String.valueOf(Integer.parseInt(rs.getString(1) + 1));
			return key;

		}
	}

	public void insertPreAdviceLine(String preAdviceId, String productCategory)
			throws SQLException, ClassNotFoundException {
		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
		String key = Utilities.getFourDigitRandomNumber() + ".0";

		String query = null;
		String vintage = DateUtils.getYear();
		String ratio_1To_2 = null;

		if (productCategory.contains("Ambient") || (null == productCategory)) {
			ratio_1To_2 = packConfigMaintenanceDB.getRatio1to2("21036013O01");
			query = "Insert into INTERFACE_PRE_ADVICE_LINE (KEY,CLIENT_ID,PRE_ADVICE_ID,LINE_ID,HOST_PRE_ADVICE_ID,HOST_LINE_ID,SKU_ID,CONFIG_ID,BATCH_ID,EXPIRY_DSTAMP,MANUF_DSTAMP,PALLET_CONFIG,ORIGIN_ID,CONDITION_ID,TAG_ID,LOCK_CODE,SPEC_CODE,QTY_DUE,NOTES,SAP_PLANT,SAP_STORE_LOC,DISALLOW_MERGE_RULES,USER_DEF_TYPE_1,USER_DEF_TYPE_2,USER_DEF_TYPE_3,USER_DEF_TYPE_4,USER_DEF_TYPE_5,USER_DEF_TYPE_6,USER_DEF_TYPE_7,USER_DEF_TYPE_8,USER_DEF_CHK_1,USER_DEF_CHK_2,USER_DEF_CHK_3,USER_DEF_CHK_4,USER_DEF_DATE_1,USER_DEF_DATE_2,USER_DEF_DATE_3,USER_DEF_DATE_4,USER_DEF_NUM_1,USER_DEF_NUM_2,USER_DEF_NUM_3,USER_DEF_NUM_4,USER_DEF_NOTE_1,USER_DEF_NOTE_2,TRACKING_LEVEL,QTY_DUE_TOLERANCE,CE_COO,OWNER_ID,CE_CONSIGNMENT_ID,COLLECTIVE_MODE,COLLECTIVE_SEQUENCE,CE_UNDER_BOND,CE_LINK,PRODUCT_PRICE,PRODUCT_CURRENCY,CE_INVOICE_NUMBER,SERIAL_VALID_MERGE,SAMPLING_TYPE,EXPECTED_GROSS_WEIGHT,EXPECTED_NET_WEIGHT,SESSION_TIME_ZONE_NAME,TIME_ZONE_NAME,NLS_CALENDAR,CLIENT_GROUP,MERGE_ACTION,MERGE_STATUS,MERGE_ERROR,MERGE_DSTAMP) values ('"
                     + key +"','M+S','"
                     +preAdviceId+"',10,null,null,'000000000021071852',null,null,null,null,null,null,null,null,null,null,30,null,null,null,'N','969108','ZGM',null,'5571','03835975','SP13','0625A','B',null,null,null,null,to_timestamp('"+queryInsertDate+" 14.05.55.000000000'),null,null,null,null,null,2018,31,null,null,'EA',null,null,'M+S',null,null,null,null,null,null,null,null,'N',null,null,null,'Europe/London',null,null,'INT','A','CMEError','MANDSIF001',to_timestamp('"
                     +queryInsertDate+" 14.05.57.342179000'))";
		} else if (productCategory.contains("BWS-Bonded")) {
			ratio_1To_2 = packConfigMaintenanceDB.getRatio1to2("60087407O01");
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key + ", 'M+S', '" + preAdviceId
					+ "', 10.0, '60087407', '60087407O01', '"+ratio_1To_2+"', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '"
					+ vintage + "', '', '', '', '', '', '" + queryInsertDate
					+ " 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, 'Y', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"
					+ queryInsertDate + " 10.02.40.993326099')";
		} else if (productCategory.contains("BWS-Non-Bonded")) {
			ratio_1To_2 = packConfigMaintenanceDB.getRatio1to2("20001221O01");
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key + ", 'M+S', '" + preAdviceId + "', 20.0, '20001221', '20001221O01','" + ratio_1To_2 + "', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '"
					+ vintage + "', '', '', '', '', '', '" + queryInsertDate
					+ " 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, 'N', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"
					+ queryInsertDate + " 10.02.40.993326099')";
		} else if (productCategory.contains("BWS-New-Vintage")) {
			ratio_1To_2 = packConfigMaintenanceDB.getRatio1to2("20001266O01");
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key + ", 'M+S', '" + preAdviceId + "', 10.0, '20001266', '20001266O01', '6', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '"
					+ vintage + "', '', '', '', '', '', '" + queryInsertDate
					+ " 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, 'Y', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"
					+ queryInsertDate + " 10.02.40.993326099')";
		} else if (productCategory.contains("BWS-F23-Bonded")) {
			ratio_1To_2 = packConfigMaintenanceDB.getRatio1to2("21126978O01");
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key + ", 'M+S', '" + preAdviceId
					+ "', 20.0, '21126978', '21126978O01', '6', '', '', '', '', '', '', '', '', '', '', '', 100.0, '', '', '', 'N', '', '', '', '', '', '"
					+ vintage + "', '', '', '', '', '', '" + queryInsertDate
					+ " 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, 'Y', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"
					+ queryInsertDate + " 10.02.40.993326099')";
		}
		System.out.println("Insert Pre Advice Line");
		System.out.println(query);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		context.getConnection().commit();
	}
	

	public void insert2PreAdviceLine(String preAdviceId, String productCategory)
			throws SQLException, ClassNotFoundException, InterruptedException {
		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();

		String key = Utilities.getFourDigitRandomNumber() + ".0";
		String key1 = Utilities.getFourDigitRandomNumber() + ".0";
		String query = null;
		String query1 = null;
		String vintage = DateUtils.getYear();
		if (productCategory.contains("Ambient") || (null == productCategory)) {

			query1 = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key + ", 'M+S', '" + preAdviceId
					+ "', 10.0, '21036475', '21036475O01', '12', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '', '', '', '', '', '', '"
					+ queryInsertDate
					+ " 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, '', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"
					+ queryInsertDate + " 10.02.40.993326099')";

			if (context.getConnection() == null) {
				database.connect();
			}

			Statement stmt1 = context.getConnection().createStatement();
			ResultSet rs1 = stmt1.executeQuery(query1);
			context.getConnection().commit();

			Thread.sleep(2000);

			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key1 + ", 'M+S', '" + preAdviceId
					+ "', 20.0, '21036013', '21036013O01', '100', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '', '', '', '', '', '', '"
					+ queryInsertDate
					+ " 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, '', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"
					+ queryInsertDate + " 10.02.40.993326099')";

			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			context.getConnection().commit();

		} else if (productCategory.contains("BWS-Bonded")) {
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key + ", 'M+S', '" + preAdviceId
					+ "', 10.0, '60087407', '60087407O01', '6', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '"
					+ vintage + "', '', '', '', '', '', '" + queryInsertDate
					+ " 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, 'Y', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"
					+ queryInsertDate + " 10.02.40.993326099')";

			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			context.getConnection().commit();

			Thread.sleep(2000);

			query1 = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key1 + ", 'M+S', '" + preAdviceId
					+ "', 20.0, '20062926', '20062926O01', '6', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '"
					+ vintage + "', '', '', '', '', '', '" + queryInsertDate
					+ " 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, 'Y', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"
					+ queryInsertDate + " 10.02.40.993326099')";

			Statement stmt1 = context.getConnection().createStatement();
			ResultSet rs1 = stmt1.executeQuery(query1);
			context.getConnection().commit();

		} else if (productCategory.contains("BWS-Non-Bonded")) {
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key + ", 'M+S', '" + preAdviceId
					+ "', 10.0, '20001298', '20001298O01', '12', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '"
					+ vintage + "', '', '', '', '', '', '" + queryInsertDate
					+ " 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, 'N', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"
					+ queryInsertDate + " 10.02.40.993326099')";

			Statement stmt = context.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			context.getConnection().commit();

			Thread.sleep(2000);

			query1 = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key1 + ", 'M+S', '" + preAdviceId
					+ "', 20.0, '20002014', '20002014O01', '6', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '"
					+ vintage + "', '', '', '', '', '', '" + queryInsertDate
					+ " 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, 'Y', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"
					+ queryInsertDate + " 10.02.40.993326099')";

			Statement stmt1 = context.getConnection().createStatement();
			ResultSet rs1 = stmt1.executeQuery(query1);
			context.getConnection().commit();

		} else if (productCategory.contains("BWS-New-Vintage")) {
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key + ", 'M+S', '" + preAdviceId
					+ "', 10.0, '20001266', '20001266O01', '6', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '"
					+ vintage + "', '', '', '', '', '', '" + queryInsertDate
					+ " 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, 'Y', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"
					+ queryInsertDate + " 10.02.40.993326099')";
		} else if (productCategory.contains("BWS-F23-Bonded")) {
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key + ", 'M+S', '" + preAdviceId
					+ "', 20.0, '21126978', '21126978O01', '6', '', '', '', '', '', '', '', '', '', '', '', 100.0, '', '', '', 'N', '', '', '', '', '', '"
					+ vintage + "', '', '', '', '', '', '" + queryInsertDate
					+ " 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, 'Y', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"
					+ queryInsertDate + " 10.02.40.993326099')";
		}
		System.out.println("Insert Pre Advice Line");
		System.out.println(query);
		System.out.println(query1);

	}

	public void insertOrderLine(String orderId) throws SQLException, ClassNotFoundException {

		String key = Utilities.getFourDigitRandomNumber()+".0";
		
//		String key = getMaxKeyFromDB("INTERFACE_ORDER_LINE") + ".0";

		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
		String query = null;
		query = "INSERT INTO INTERFACE_ORDER_LINE (KEY,CLIENT_ID,ORDER_ID,LINE_ID,HOST_ORDER_ID,HOST_LINE_ID,SKU_ID,CUSTOMER_SKU_ID,CONFIG_ID,TRACKING_LEVEL,BATCH_ID,BATCH_MIXING,SHELF_LIFE_DAYS,SHELF_LIFE_PERCENT,ORIGIN_ID,CONDITION_ID,LOCK_CODE,SPEC_CODE,QTY_ORDERED,ALLOCATE,BACK_ORDERED,KIT_SPLIT,DEALLOCATE,NOTES,PSFT_INT_LINE,PSFT_SCHD_LINE,PSFT_DMND_LINE,SAP_PICK_REQ,DISALLOW_MERGE_RULES,LINE_VALUE,RULE_ID,SOH_ID,USER_DEF_TYPE_1,USER_DEF_TYPE_2,USER_DEF_TYPE_3,USER_DEF_TYPE_4,USER_DEF_TYPE_5,USER_DEF_TYPE_6,USER_DEF_TYPE_7,USER_DEF_TYPE_8,USER_DEF_CHK_1,USER_DEF_CHK_2,USER_DEF_CHK_3,USER_DEF_CHK_4,USER_DEF_DATE_1,USER_DEF_DATE_2,USER_DEF_DATE_3,USER_DEF_DATE_4,USER_DEF_NUM_1,USER_DEF_NUM_2,USER_DEF_NUM_3,USER_DEF_NUM_4,USER_DEF_NOTE_1,USER_DEF_NOTE_2,TASK_PER_EACH,USE_PICK_TO_GRID,IGNORE_WEIGHT_CAPTURE,STAGE_ROUTE_ID,MIN_QTY_ORDERED,MAX_QTY_ORDERED,EXPECTED_VOLUME,EXPECTED_WEIGHT,EXPECTED_VALUE,CUSTOMER_SKU_DESC1,CUSTOMER_SKU_DESC2,PURCHASE_ORDER,PRODUCT_PRICE,PRODUCT_CURRENCY,DOCUMENTATION_UNIT,EXTENDED_PRICE,TAX_1,TAX_2,DOCUMENTATION_TEXT_1,SERIAL_NUMBER,OWNER_ID,COLLECTIVE_MODE,COLLECTIVE_SEQUENCE,CE_RECEIPT_TYPE,CE_COO,KIT_PLAN_ID,LOCATION_ID,UNALLOCATABLE,MIN_FULL_PALLET_PERC,MAX_FULL_PALLET_PERC,FULL_TRACKING_LEVEL_ONLY,SUBSTITUTE_GRADE,DISALLOW_SUBSTITUTION,SESSION_TIME_ZONE_NAME,TIME_ZONE_NAME,NLS_CALENDAR,CLIENT_GROUP,MERGE_ACTION,MERGE_STATUS,MERGE_ERROR,MERGE_DSTAMP) values ('"
		         + key +"','M+S','" + orderId
				 +"',10,null,null,'000000000021071852',null,null,'EA',null,'Y',null,null,null,null,null,null,20,'Y','N','Y',null,null,null,null,null,null,'N',null,'MANUAL FRA',null,null,'0001','01977219','IntlFranchise',null,null,'','MANUAL','N','N','N','N',to_timestamp('"
				 + queryInsertDate 
				 +" 02.44.07.000000000'),null,null,null,null,null,null,690,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,2.56,null,null,null,null,'M+S',null,null,null,null,null,null,'N',null,null,'N',null,'N','Europe/Belfast','Europe/London',null,'INT','A','Pending','IF0014',to_timestamp('"
				 + queryInsertDate +" 10.29.36.292279000'))";
					
		System.out.println("Insert Order Line");
		System.out.println(query);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		context.getConnection().commit();

		
//		21036245,21036245O01
//		21036013,21036013O01
	}
	
	public void insertOrderLine2(String orderId,String preAdviceId) throws SQLException, ClassNotFoundException, InterruptedException {

		String key = Utilities.getFourDigitRandomNumber()+".0";
		String key1 = Utilities.getFourDigitRandomNumber()+".0";
		
//		String key = getMaxKeyFromDB("INTERFACE_ORDER_LINE") + ".0";

		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
		String query = null;
		String query1 = null;

		query = "INSERT INTO INTERFACE_ORDER_LINE (KEY,CLIENT_ID,ORDER_ID,LINE_ID,HOST_ORDER_ID,HOST_LINE_ID,SKU_ID,CUSTOMER_SKU_ID,CONFIG_ID,TRACKING_LEVEL,BATCH_ID,BATCH_MIXING,SHELF_LIFE_DAYS,SHELF_LIFE_PERCENT,ORIGIN_ID,CONDITION_ID,LOCK_CODE,SPEC_CODE,QTY_ORDERED,ALLOCATE,BACK_ORDERED,KIT_SPLIT,DEALLOCATE,NOTES,PSFT_INT_LINE,PSFT_SCHD_LINE,PSFT_DMND_LINE,SAP_PICK_REQ,DISALLOW_MERGE_RULES,LINE_VALUE,RULE_ID,SOH_ID,USER_DEF_TYPE_1,USER_DEF_TYPE_2,USER_DEF_TYPE_3,USER_DEF_TYPE_4,USER_DEF_TYPE_5,USER_DEF_TYPE_6,USER_DEF_TYPE_7,USER_DEF_TYPE_8,USER_DEF_CHK_1,USER_DEF_CHK_2,USER_DEF_CHK_3,USER_DEF_CHK_4,USER_DEF_DATE_1,USER_DEF_DATE_2,USER_DEF_DATE_3,USER_DEF_DATE_4,USER_DEF_NUM_1,USER_DEF_NUM_2,USER_DEF_NUM_3,USER_DEF_NUM_4,USER_DEF_NOTE_1,USER_DEF_NOTE_2,TASK_PER_EACH,USE_PICK_TO_GRID,IGNORE_WEIGHT_CAPTURE,STAGE_ROUTE_ID,MIN_QTY_ORDERED,MAX_QTY_ORDERED,EXPECTED_VOLUME,EXPECTED_WEIGHT,EXPECTED_VALUE,CUSTOMER_SKU_DESC1,CUSTOMER_SKU_DESC2,PURCHASE_ORDER,PRODUCT_PRICE,PRODUCT_CURRENCY,DOCUMENTATION_UNIT,EXTENDED_PRICE,TAX_1,TAX_2,DOCUMENTATION_TEXT_1,SERIAL_NUMBER,OWNER_ID,COLLECTIVE_MODE,COLLECTIVE_SEQUENCE,CE_RECEIPT_TYPE,CE_COO,KIT_PLAN_ID,LOCATION_ID,UNALLOCATABLE,MIN_FULL_PALLET_PERC,MAX_FULL_PALLET_PERC,FULL_TRACKING_LEVEL_ONLY,SUBSTITUTE_GRADE,DISALLOW_SUBSTITUTION,SESSION_TIME_ZONE_NAME,TIME_ZONE_NAME,NLS_CALENDAR,CLIENT_GROUP,MERGE_ACTION,MERGE_STATUS,MERGE_ERROR,MERGE_DSTAMP) values ('"
		         + key +"','M+S','" + orderId
				 +"',10,null,null,'000000000021071852',null,null,'EA',null,'Y',null,null,null,null,null,null,20,'Y','N','Y',null,null,null,null,null,null,'N',null,'MANUAL FRA',null,null,'0001','01977219','IntlFranchise',null,null,'','"
                +preAdviceId+"','N','N','N','N',to_timestamp('"
				 + queryInsertDate 
				 +" 02.44.07.000000000'),null,null,null,null,null,null,690,null,null,'N','N','N',null,null,null,null,null,null,null,null,null,null,null,null,2.56,null,null,null,null,'M+S',null,null,null,null,null,null,'N',null,null,'N',null,'N','Europe/Belfast','Europe/London',null,'INT','A','Pending','IF0014',to_timestamp('"
				 + queryInsertDate +" 10.29.36.292279000'))";
		System.out.println("Insert Order Line");
		System.out.println(query);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		context.getConnection().commit();
		
		Thread.sleep(4000);
		
		query1 = "INSERT INTO INTERFACE_ORDER_LINE (CLIENT_ID, ORDER_ID, LINE_ID, HOST_ORDER_ID, HOST_LINE_ID, SKU_ID, CUSTOMER_SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, TRACKING_LEVEL, BATCH_ID, BATCH_MIXING, SHELF_LIFE_PERCENT, ORIGIN_ID, CONDITION_ID, LOCK_CODE, SPEC_CODE, QTY_ORDERED, ALLOCATE, BACK_ORDERED, KIT_SPLIT, DEALLOCATE, DISALLOW_MERGE_RULES, RULE_ID, LINE_VALUE, NOTES, PSFT_INT_LINE, PSFT_SCHD_LINE, PSFT_DMND_LINE, SAP_PICK_REQ, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TASK_PER_EACH, USE_PICK_TO_GRID, IGNORE_WEIGHT_CAPTURE, STAGE_ROUTE_ID, MIN_QTY_ORDERED, MAX_QTY_ORDERED, EXPECTED_VOLUME, EXPECTED_WEIGHT, EXPECTED_VALUE, CUSTOMER_SKU_DESC1, CUSTOMER_SKU_DESC2, PURCHASE_ORDER, PRODUCT_PRICE, PRODUCT_CURRENCY, DOCUMENTATION_UNIT, EXTENDED_PRICE, TAX_1, TAX_2, DOCUMENTATION_TEXT_1, SERIAL_NUMBER, OWNER_ID, CE_RECEIPT_TYPE, CE_COO, KIT_PLAN_ID, LOCATION_ID, UNALLOCATABLE, MIN_FULL_PALLET_PERC, MAX_FULL_PALLET_PERC, FULL_TRACKING_LEVEL_ONLY, SUBSTITUTE_GRADE, DISALLOW_SUBSTITUTION, KEY, SOH_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES  ('M+S', '"
				+ orderId + "', 20, '', '', '21036475', '', '21036475O01', '12', 'CASE', '', 'Y', NULL, '', '', '', '', 28.0, '', '', 'N', '', 'N', '', NULL, '', NULL, NULL, NULL, '', '', '', '', '', '', '', '', 'N', 'N', 'N', 'N', '', '', '', '',  NULL, NULL, NULL, NULL, '', '', 'N', 'N', 'N', '', NULL, NULL, 0.010308, 6.399996, 0.0, '', '', '', NULL, '', '', NULL, NULL, NULL, '', '', 'M+S', '', '', '', '', '', NULL, NULL, '', '', 'N', '"
				+ key1 + "', '', '', NULL, 'Europe/Belfast', 'Europe/London', '', 'FOODS', 'U', 'Pending', '', '"
				+ queryInsertDate + " 06.38.46.150479000')";
		System.out.println("Insert Order Line2");
		System.out.println(query1);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt1 = context.getConnection().createStatement();
		ResultSet rs1 = stmt1.executeQuery(query1);
		context.getConnection().commit();
		
//		21036245,21036245O01
//		21036013,21036013O01
	}
	public void insertOrderLineForConsolidation(String orderId) throws SQLException, ClassNotFoundException {
		// String key = Utilities.getFourDigitRandomNumber()+".0";
		String key = getMaxKeyFromDB("INTERFACE_ORDER_LINE") + ".0";
		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
		String query = null;
		// query = "INSERT INTO INTERFACE_ORDER_LINE (CLIENT_ID, ORDER_ID,
		// LINE_ID, HOST_ORDER_ID, HOST_LINE_ID, SKU_ID, CUSTOMER_SKU_ID,
		// CONFIG_ID, USER_DEF_TYPE_6, TRACKING_LEVEL, BATCH_ID, BATCH_MIXING,
		// SHELF_LIFE_PERCENT, ORIGIN_ID, CONDITION_ID, LOCK_CODE, SPEC_CODE,
		// QTY_ORDERED, ALLOCATE, BACK_ORDERED, KIT_SPLIT, DEALLOCATE,
		// DISALLOW_MERGE_RULES, RULE_ID, LINE_VALUE, NOTES, PSFT_INT_LINE,
		// PSFT_SCHD_LINE, PSFT_DMND_LINE, SAP_PICK_REQ, USER_DEF_TYPE_1,
		// USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5,
		// USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2,
		// USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2,
		// USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2,
		// USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2,
		// TASK_PER_EACH, USE_PICK_TO_GRID, IGNORE_WEIGHT_CAPTURE,
		// STAGE_ROUTE_ID, MIN_QTY_ORDERED, MAX_QTY_ORDERED, EXPECTED_VOLUME,
		// EXPECTED_WEIGHT, EXPECTED_VALUE, CUSTOMER_SKU_DESC1,
		// CUSTOMER_SKU_DESC2, PURCHASE_ORDER, PRODUCT_PRICE, PRODUCT_CURRENCY,
		// DOCUMENTATION_UNIT, EXTENDED_PRICE, TAX_1, TAX_2,
		// DOCUMENTATION_TEXT_1, SERIAL_NUMBER, OWNER_ID, CE_RECEIPT_TYPE,
		// CE_COO, KIT_PLAN_ID, LOCATION_ID, UNALLOCATABLE,
		// MIN_FULL_PALLET_PERC, MAX_FULL_PALLET_PERC, FULL_TRACKING_LEVEL_ONLY,
		// SUBSTITUTE_GRADE, DISALLOW_SUBSTITUTION, KEY, SOH_ID,
		// COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, SESSION_TIME_ZONE_NAME,
		// TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION,
		// MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ('M+S',
		// '"+orderId+"', 10.0, '', '', '21036013', '', '21036013O01', '15',
		// 'CASE', '', 'Y', NULL, '', '', '', '', 28.0, '', '', 'N', '', 'N',
		// '', NULL, '', NULL, NULL, NULL, '', '', '', '', '', '', '', '', 'N',
		// 'N', 'N', 'N', '"+queryInsertDate+" 06.38.46.150479000',
		// '"+queryInsertDate+" 06.38.46.150479000', '', '', NULL, NULL, NULL,
		// NULL, '', '', 'N', 'N', 'N', '', NULL, NULL, 0.010308, 6.399996, 0.0,
		// '', '', '', NULL, '', '', NULL, NULL, NULL, '', '', 'M+S', '', '',
		// '', '', '', NULL, NULL, '', '', 'N', 1.0, '', '', NULL,
		// 'Europe/Belfast', 'Europe/London', '', 'FOODS', 'U', 'Pending', '',
		// '"+queryInsertDate+" 06.38.46.150479000')";
		query = "INSERT INTO INTERFACE_ORDER_LINE (CLIENT_ID, ORDER_ID, LINE_ID, HOST_ORDER_ID, HOST_LINE_ID, SKU_ID, CUSTOMER_SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, TRACKING_LEVEL, BATCH_ID, BATCH_MIXING, SHELF_LIFE_PERCENT, ORIGIN_ID, CONDITION_ID, LOCK_CODE, SPEC_CODE, QTY_ORDERED, ALLOCATE, BACK_ORDERED, KIT_SPLIT, DEALLOCATE, DISALLOW_MERGE_RULES, RULE_ID, LINE_VALUE, NOTES, PSFT_INT_LINE, PSFT_SCHD_LINE, PSFT_DMND_LINE, SAP_PICK_REQ, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TASK_PER_EACH, USE_PICK_TO_GRID, IGNORE_WEIGHT_CAPTURE, STAGE_ROUTE_ID, MIN_QTY_ORDERED, MAX_QTY_ORDERED, EXPECTED_VOLUME, EXPECTED_WEIGHT, EXPECTED_VALUE, CUSTOMER_SKU_DESC1, CUSTOMER_SKU_DESC2, PURCHASE_ORDER, PRODUCT_PRICE, PRODUCT_CURRENCY, DOCUMENTATION_UNIT, EXTENDED_PRICE, TAX_1, TAX_2, DOCUMENTATION_TEXT_1, SERIAL_NUMBER, OWNER_ID, CE_RECEIPT_TYPE, CE_COO, KIT_PLAN_ID, LOCATION_ID, UNALLOCATABLE, MIN_FULL_PALLET_PERC, MAX_FULL_PALLET_PERC, FULL_TRACKING_LEVEL_ONLY, SUBSTITUTE_GRADE, DISALLOW_SUBSTITUTION, KEY, SOH_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES  ('M+S', '"
				+ orderId
				+ "', 10, '', '', '21036013', '', '21036013O01', '100', 'CASE', '', 'Y', NULL, '', '', '', '', 28.0, '', '', 'N', '', 'N', '', NULL, '', NULL, NULL, NULL, '', '', '', '', '', '', '', '', 'N', 'N', 'N', 'N', '', '', '', '',  NULL, NULL, NULL, NULL, '', '', 'N', 'N', 'N', '', NULL, NULL, 0.010308, 6.399996, 0.0, '', '', '', NULL, '', '', NULL, NULL, NULL, '', '', 'M+S', '', '', '', '', '', NULL, NULL, '', '', 'N', '"
				+ key + "', '', '', NULL, 'Europe/Belfast', 'Europe/London', '', 'FOODS', 'U', 'Pending', '', '"
				+ queryInsertDate + " 06.38.46.150479000')";
		System.out.println("Insert Order Line");
		System.out.println(query);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		context.getConnection().commit();
		
//		21036245,21036245O01
//		21036013,21036013O01
	}
	public void insertOrderLineForContainer(String orderId) throws SQLException, ClassNotFoundException {
		// String key = Utilities.getFourDigitRandomNumber()+".0";
		String key = getMaxKeyFromDB("INTERFACE_ORDER_LINE") + ".0";
		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
		String query = null;
		// query = "INSERT INTO INTERFACE_ORDER_LINE (CLIENT_ID, ORDER_ID,
		// LINE_ID, HOST_ORDER_ID, HOST_LINE_ID, SKU_ID, CUSTOMER_SKU_ID,
		// CONFIG_ID, USER_DEF_TYPE_6, TRACKING_LEVEL, BATCH_ID, BATCH_MIXING,
		// SHELF_LIFE_PERCENT, ORIGIN_ID, CONDITION_ID, LOCK_CODE, SPEC_CODE,
		// QTY_ORDERED, ALLOCATE, BACK_ORDERED, KIT_SPLIT, DEALLOCATE,
		// DISALLOW_MERGE_RULES, RULE_ID, LINE_VALUE, NOTES, PSFT_INT_LINE,
		// PSFT_SCHD_LINE, PSFT_DMND_LINE, SAP_PICK_REQ, USER_DEF_TYPE_1,
		// USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5,
		// USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2,
		// USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2,
		// USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2,
		// USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2,
		// TASK_PER_EACH, USE_PICK_TO_GRID, IGNORE_WEIGHT_CAPTURE,
		// STAGE_ROUTE_ID, MIN_QTY_ORDERED, MAX_QTY_ORDERED, EXPECTED_VOLUME,
		// EXPECTED_WEIGHT, EXPECTED_VALUE, CUSTOMER_SKU_DESC1,
		// CUSTOMER_SKU_DESC2, PURCHASE_ORDER, PRODUCT_PRICE, PRODUCT_CURRENCY,
		// DOCUMENTATION_UNIT, EXTENDED_PRICE, TAX_1, TAX_2,
		// DOCUMENTATION_TEXT_1, SERIAL_NUMBER, OWNER_ID, CE_RECEIPT_TYPE,
		// CE_COO, KIT_PLAN_ID, LOCATION_ID, UNALLOCATABLE,
		// MIN_FULL_PALLET_PERC, MAX_FULL_PALLET_PERC, FULL_TRACKING_LEVEL_ONLY,
		// SUBSTITUTE_GRADE, DISALLOW_SUBSTITUTION, KEY, SOH_ID,
		// COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, SESSION_TIME_ZONE_NAME,
		// TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION,
		// MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ('M+S',
		// '"+orderId+"', 10.0, '', '', '21036013', '', '21036013O01', '15',
		// 'CASE', '', 'Y', NULL, '', '', '', '', 28.0, '', '', 'N', '', 'N',
		// '', NULL, '', NULL, NULL, NULL, '', '', '', '', '', '', '', '', 'N',
		// 'N', 'N', 'N', '"+queryInsertDate+" 06.38.46.150479000',
		// '"+queryInsertDate+" 06.38.46.150479000', '', '', NULL, NULL, NULL,
		// NULL, '', '', 'N', 'N', 'N', '', NULL, NULL, 0.010308, 6.399996, 0.0,
		// '', '', '', NULL, '', '', NULL, NULL, NULL, '', '', 'M+S', '', '',
		// '', '', '', NULL, NULL, '', '', 'N', 1.0, '', '', NULL,
		// 'Europe/Belfast', 'Europe/London', '', 'FOODS', 'U', 'Pending', '',
		// '"+queryInsertDate+" 06.38.46.150479000')";
		query = "INSERT INTO INTERFACE_ORDER_LINE (CLIENT_ID, ORDER_ID, LINE_ID, HOST_ORDER_ID, HOST_LINE_ID, SKU_ID, CUSTOMER_SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, TRACKING_LEVEL, BATCH_ID, BATCH_MIXING, SHELF_LIFE_PERCENT, ORIGIN_ID, CONDITION_ID, LOCK_CODE, SPEC_CODE, QTY_ORDERED, ALLOCATE, BACK_ORDERED, KIT_SPLIT, DEALLOCATE, DISALLOW_MERGE_RULES, RULE_ID, LINE_VALUE, NOTES, PSFT_INT_LINE, PSFT_SCHD_LINE, PSFT_DMND_LINE, SAP_PICK_REQ, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TASK_PER_EACH, USE_PICK_TO_GRID, IGNORE_WEIGHT_CAPTURE, STAGE_ROUTE_ID, MIN_QTY_ORDERED, MAX_QTY_ORDERED, EXPECTED_VOLUME, EXPECTED_WEIGHT, EXPECTED_VALUE, CUSTOMER_SKU_DESC1, CUSTOMER_SKU_DESC2, PURCHASE_ORDER, PRODUCT_PRICE, PRODUCT_CURRENCY, DOCUMENTATION_UNIT, EXTENDED_PRICE, TAX_1, TAX_2, DOCUMENTATION_TEXT_1, SERIAL_NUMBER, OWNER_ID, CE_RECEIPT_TYPE, CE_COO, KIT_PLAN_ID, LOCATION_ID, UNALLOCATABLE, MIN_FULL_PALLET_PERC, MAX_FULL_PALLET_PERC, FULL_TRACKING_LEVEL_ONLY, SUBSTITUTE_GRADE, DISALLOW_SUBSTITUTION, KEY, SOH_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES  ('M+S', '"
				+ orderId
				+ "', 10, '', '', '21036013', '', '21036013O01', '100', 'CASE', '', 'Y', NULL, '', '', '', '', 28.0, '', '', 'N', '', 'N', '', NULL, '', NULL, NULL, NULL, '', '', '', '', '', '', '', '', 'N', 'N', 'N', 'N', '', '', '', '',  NULL, NULL, NULL, NULL, '', '', 'N', 'N', 'N', '', NULL, NULL, 0.010308, 6.399996, 0.0, '', '', '', NULL, '', '', NULL, NULL, NULL, '', '', 'M+S', '', '', '', '', '', NULL, NULL, '', '', 'N', '"
				+ key + "', '', '', NULL, 'Europe/Belfast', 'Europe/London', '', 'FOODS', 'U', 'Pending', '', '"
				+ queryInsertDate + " 06.38.46.150479000')";
		System.out.println("Insert Order Line");
		System.out.println(query);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		context.getConnection().commit();

		// 21036245,21036245O01
		// 21036013,21036013O01
	}

	

	public void insertOrderLineForReplenish(String orderId) throws SQLException, ClassNotFoundException {
		// String key = Utilities.getFourDigitRandomNumber()+".0";
		String key = getMaxKeyFromDB("INTERFACE_ORDER_LINE") + ".0";
		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
		String query = null;
		// query = "INSERT INTO INTERFACE_ORDER_LINE (CLIENT_ID, ORDER_ID,
		// LINE_ID, HOST_ORDER_ID, HOST_LINE_ID, SKU_ID, CUSTOMER_SKU_ID,
		// CONFIG_ID, USER_DEF_TYPE_6, TRACKING_LEVEL, BATCH_ID, BATCH_MIXING,
		// SHELF_LIFE_PERCENT, ORIGIN_ID, CONDITION_ID, LOCK_CODE, SPEC_CODE,
		// QTY_ORDERED, ALLOCATE, BACK_ORDERED, KIT_SPLIT, DEALLOCATE,
		// DISALLOW_MERGE_RULES, RULE_ID, LINE_VALUE, NOTES, PSFT_INT_LINE,
		// PSFT_SCHD_LINE, PSFT_DMND_LINE, SAP_PICK_REQ, USER_DEF_TYPE_1,
		// USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5,
		// USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2,
		// USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2,
		// USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2,
		// USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2,
		// TASK_PER_EACH, USE_PICK_TO_GRID, IGNORE_WEIGHT_CAPTURE,
		// STAGE_ROUTE_ID, MIN_QTY_ORDERED, MAX_QTY_ORDERED, EXPECTED_VOLUME,
		// EXPECTED_WEIGHT, EXPECTED_VALUE, CUSTOMER_SKU_DESC1,
		// CUSTOMER_SKU_DESC2, PURCHASE_ORDER, PRODUCT_PRICE, PRODUCT_CURRENCY,
		// DOCUMENTATION_UNIT, EXTENDED_PRICE, TAX_1, TAX_2,
		// DOCUMENTATION_TEXT_1, SERIAL_NUMBER, OWNER_ID, CE_RECEIPT_TYPE,
		// CE_COO, KIT_PLAN_ID, LOCATION_ID, UNALLOCATABLE,
		// MIN_FULL_PALLET_PERC, MAX_FULL_PALLET_PERC, FULL_TRACKING_LEVEL_ONLY,
		// SUBSTITUTE_GRADE, DISALLOW_SUBSTITUTION, KEY, SOH_ID,
		// COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, SESSION_TIME_ZONE_NAME,
		// TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION,
		// MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ('M+S',
		// '"+orderId+"', 10.0, '', '', '21036013', '', '21036013O01', '15',
		// 'CASE', '', 'Y', NULL, '', '', '', '', 28.0, '', '', 'N', '', 'N',
		// '', NULL, '', NULL, NULL, NULL, '', '', '', '', '', '', '', '', 'N',
		// 'N', 'N', 'N', '"+queryInsertDate+" 06.38.46.150479000',
		// '"+queryInsertDate+" 06.38.46.150479000', '', '', NULL, NULL, NULL,
		// NULL, '', '', 'N', 'N', 'N', '', NULL, NULL, 0.010308, 6.399996, 0.0,
		// '', '', '', NULL, '', '', NULL, NULL, NULL, '', '', 'M+S', '', '',
		// '', '', '', NULL, NULL, '', '', 'N', 1.0, '', '', NULL,
		// 'Europe/Belfast', 'Europe/London', '', 'FOODS', 'U', 'Pending', '',
		// '"+queryInsertDate+" 06.38.46.150479000')";
		query = "INSERT INTO INTERFACE_ORDER_LINE (CLIENT_ID, ORDER_ID, LINE_ID, HOST_ORDER_ID, HOST_LINE_ID, SKU_ID, CUSTOMER_SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, TRACKING_LEVEL, BATCH_ID, BATCH_MIXING, SHELF_LIFE_PERCENT, ORIGIN_ID, CONDITION_ID, LOCK_CODE, SPEC_CODE, QTY_ORDERED, ALLOCATE, BACK_ORDERED, KIT_SPLIT, DEALLOCATE, DISALLOW_MERGE_RULES, RULE_ID, LINE_VALUE, NOTES, PSFT_INT_LINE, PSFT_SCHD_LINE, PSFT_DMND_LINE, SAP_PICK_REQ, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TASK_PER_EACH, USE_PICK_TO_GRID, IGNORE_WEIGHT_CAPTURE, STAGE_ROUTE_ID, MIN_QTY_ORDERED, MAX_QTY_ORDERED, EXPECTED_VOLUME, EXPECTED_WEIGHT, EXPECTED_VALUE, CUSTOMER_SKU_DESC1, CUSTOMER_SKU_DESC2, PURCHASE_ORDER, PRODUCT_PRICE, PRODUCT_CURRENCY, DOCUMENTATION_UNIT, EXTENDED_PRICE, TAX_1, TAX_2, DOCUMENTATION_TEXT_1, SERIAL_NUMBER, OWNER_ID, CE_RECEIPT_TYPE, CE_COO, KIT_PLAN_ID, LOCATION_ID, UNALLOCATABLE, MIN_FULL_PALLET_PERC, MAX_FULL_PALLET_PERC, FULL_TRACKING_LEVEL_ONLY, SUBSTITUTE_GRADE, DISALLOW_SUBSTITUTION, KEY, SOH_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES  ('M+S', '"
				+ orderId
				+ "', 10, '', '', '21036245', '', '21036245O01', '21', 'CASE', '', 'Y', NULL, '', '', '', '', 28.0, '', '', 'N', '', 'N', '', NULL, '', NULL, NULL, NULL, '', '', '', '', '', '', '', '', 'N', 'N', 'N', 'N', '', '', '', '',  NULL, NULL, NULL, NULL, '', '', 'N', 'N', 'N', '', NULL, NULL, 0.010308, 6.399996, 0.0, '', '', '', NULL, '', '', NULL, NULL, NULL, '', '', 'M+S', '', '', '', '', '', NULL, NULL, '', '', 'N', '"
				+ key + "', '', '', NULL, 'Europe/Belfast', 'Europe/London', '', 'FOODS', 'U', 'Pending', '', '"
				+ queryInsertDate + " 06.38.46.150479000')";
		System.out.println("Insert Order Line");
		System.out.println(query);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		context.getConnection().commit();
		// 21036475,12
	}

	public void insertOrderLineForLockingLocations(String orderId) throws SQLException, ClassNotFoundException {
		// String key = Utilities.getFourDigitRandomNumber()+".0";
		String key = getMaxKeyFromDB("INTERFACE_ORDER_LINE") + ".0";
		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
		String query = null;
		// query = "INSERT INTO INTERFACE_ORDER_LINE (CLIENT_ID, ORDER_ID,
		// LINE_ID, HOST_ORDER_ID, HOST_LINE_ID, SKU_ID, CUSTOMER_SKU_ID,
		// CONFIG_ID, USER_DEF_TYPE_6, TRACKING_LEVEL, BATCH_ID, BATCH_MIXING,
		// SHELF_LIFE_PERCENT, ORIGIN_ID, CONDITION_ID, LOCK_CODE, SPEC_CODE,
		// QTY_ORDERED, ALLOCATE, BACK_ORDERED, KIT_SPLIT, DEALLOCATE,
		// DISALLOW_MERGE_RULES, RULE_ID, LINE_VALUE, NOTES, PSFT_INT_LINE,
		// PSFT_SCHD_LINE, PSFT_DMND_LINE, SAP_PICK_REQ, USER_DEF_TYPE_1,
		// USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5,
		// USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2,
		// USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2,
		// USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2,
		// USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2,
		// TASK_PER_EACH, USE_PICK_TO_GRID, IGNORE_WEIGHT_CAPTURE,
		// STAGE_ROUTE_ID, MIN_QTY_ORDERED, MAX_QTY_ORDERED, EXPECTED_VOLUME,
		// EXPECTED_WEIGHT, EXPECTED_VALUE, CUSTOMER_SKU_DESC1,
		// CUSTOMER_SKU_DESC2, PURCHASE_ORDER, PRODUCT_PRICE, PRODUCT_CURRENCY,
		// DOCUMENTATION_UNIT, EXTENDED_PRICE, TAX_1, TAX_2,
		// DOCUMENTATION_TEXT_1, SERIAL_NUMBER, OWNER_ID, CE_RECEIPT_TYPE,
		// CE_COO, KIT_PLAN_ID, LOCATION_ID, UNALLOCATABLE,
		// MIN_FULL_PALLET_PERC, MAX_FULL_PALLET_PERC, FULL_TRACKING_LEVEL_ONLY,
		// SUBSTITUTE_GRADE, DISALLOW_SUBSTITUTION, KEY, SOH_ID,
		// COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, SESSION_TIME_ZONE_NAME,
		// TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION,
		// MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ('M+S',
		// '"+orderId+"', 10.0, '', '', '21036013', '', '21036013O01', '15',
		// 'CASE', '', 'Y', NULL, '', '', '', '', 28.0, '', '', 'N', '', 'N',
		// '', NULL, '', NULL, NULL, NULL, '', '', '', '', '', '', '', '', 'N',
		// 'N', 'N', 'N', '"+queryInsertDate+" 06.38.46.150479000',
		// '"+queryInsertDate+" 06.38.46.150479000', '', '', NULL, NULL, NULL,
		// NULL, '', '', 'N', 'N', 'N', '', NULL, NULL, 0.010308, 6.399996, 0.0,
		// '', '', '', NULL, '', '', NULL, NULL, NULL, '', '', 'M+S', '', '',
		// '', '', '', NULL, NULL, '', '', 'N', 1.0, '', '', NULL,
		// 'Europe/Belfast', 'Europe/London', '', 'FOODS', 'U', 'Pending', '',
		// '"+queryInsertDate+" 06.38.46.150479000')";
		query = "INSERT INTO INTERFACE_ORDER_LINE (CLIENT_ID, ORDER_ID, LINE_ID, HOST_ORDER_ID, HOST_LINE_ID, SKU_ID, CUSTOMER_SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, TRACKING_LEVEL, BATCH_ID, BATCH_MIXING, SHELF_LIFE_PERCENT, ORIGIN_ID, CONDITION_ID, LOCK_CODE, SPEC_CODE, QTY_ORDERED, ALLOCATE, BACK_ORDERED, KIT_SPLIT, DEALLOCATE, DISALLOW_MERGE_RULES, RULE_ID, LINE_VALUE, NOTES, PSFT_INT_LINE, PSFT_SCHD_LINE, PSFT_DMND_LINE, SAP_PICK_REQ, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TASK_PER_EACH, USE_PICK_TO_GRID, IGNORE_WEIGHT_CAPTURE, STAGE_ROUTE_ID, MIN_QTY_ORDERED, MAX_QTY_ORDERED, EXPECTED_VOLUME, EXPECTED_WEIGHT, EXPECTED_VALUE, CUSTOMER_SKU_DESC1, CUSTOMER_SKU_DESC2, PURCHASE_ORDER, PRODUCT_PRICE, PRODUCT_CURRENCY, DOCUMENTATION_UNIT, EXTENDED_PRICE, TAX_1, TAX_2, DOCUMENTATION_TEXT_1, SERIAL_NUMBER, OWNER_ID, CE_RECEIPT_TYPE, CE_COO, KIT_PLAN_ID, LOCATION_ID, UNALLOCATABLE, MIN_FULL_PALLET_PERC, MAX_FULL_PALLET_PERC, FULL_TRACKING_LEVEL_ONLY, SUBSTITUTE_GRADE, DISALLOW_SUBSTITUTION, KEY, SOH_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES  ('M+S', '"
				+ orderId
				+ "', 10, '', '', '21036245', '', '21036245O01', '20', 'CASE', '', 'Y', NULL, '', '', '', '', 28.0, '', '', 'N', '', 'N', '', NULL, '', NULL, NULL, NULL, '', '', '', '', '', '', '', '', 'N', 'N', 'N', 'N', '', '', '', '',  NULL, NULL, NULL, NULL, '', '', 'N', 'N', 'N', '', NULL, NULL, 0.010308, 6.399996, 0.0, '', '', '', NULL, '', '', NULL, NULL, NULL, '', '', 'M+S', '', '', '', '', '', NULL, NULL, '', '', 'N', '"
				+ key + "', '', '', NULL, 'Europe/Belfast', 'Europe/London', '', 'FOODS', 'U', 'Pending', '', '"
				+ queryInsertDate + " 06.38.46.150479000')";
		System.out.println("Insert Order Line");
		System.out.println(query);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		context.getConnection().commit();
	}
	
	public void insertPreAdviceLineTrial(String preAdviceId, String productCategory)
			throws SQLException, ClassNotFoundException {
		String queryInsertDate = DateUtils.getCurrentSystemDateInDBFormat();
		String key = Utilities.getFourDigitRandomNumber() + ".0";

		String query = null;
		String vintage = DateUtils.getYear();
		String ratio_1To_2 = null;

		if (productCategory.contains("Ambient") || (null == productCategory)) {
			ratio_1To_2 = packConfigMaintenanceDB.getRatio1to2("21036013O01");
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key + ", 'M+S', '" + preAdviceId + "', 20.0, '21036013', '21036013O01', '" + ratio_1To_2
					+ "', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', 'DL', '', '', '', '', '', '', '', '"
					+ queryInsertDate
					+ " 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, '', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"
					+ queryInsertDate + " 10.02.40.993326099')";
		} else if (productCategory.contains("BWS-Bonded")) {
			ratio_1To_2 = packConfigMaintenanceDB.getRatio1to2("20001562O01");
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key + ", 'M+S', '" + preAdviceId
					+ "', 10.0, '20001562', '20001562O01', '"+ratio_1To_2+"', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '"
					+ vintage + "', '', '', '', '', '', '" + queryInsertDate
					+ " 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, 'N', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"
					+ queryInsertDate + " 10.02.40.993326099')";
		} else if (productCategory.contains("BWS-Non-Bonded")) {
			ratio_1To_2 = packConfigMaintenanceDB.getRatio1to2("20001221O01");
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key + ", 'M+S', '" + preAdviceId + "', 20.0, '20001221', '20001221O01','" + ratio_1To_2 + "', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '"
					+ vintage + "', '', '', '', '', '', '" + queryInsertDate
					+ " 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, 'N', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"
					+ queryInsertDate + " 10.02.40.993326099')";
		} else if (productCategory.contains("BWS-New-Vintage")) {
			ratio_1To_2 = packConfigMaintenanceDB.getRatio1to2("20001266O01");
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key + ", 'M+S', '" + preAdviceId + "', 10.0, '20001266', '20001266O01', '6', '', '', '', '', '', '', '', '', '', '', '', 980.0, '', '', '', 'N', '', '', '', '', '', '"
					+ vintage + "', '', '', '', '', '', '" + queryInsertDate
					+ " 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, 'Y', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"
					+ queryInsertDate + " 10.02.40.993326099')";
		} else if (productCategory.contains("BWS-F23-Bonded")) {
			ratio_1To_2 = packConfigMaintenanceDB.getRatio1to2("21126978O01");
			query = "INSERT INTO INTERFACE_PRE_ADVICE_LINE (KEY, CLIENT_ID, PRE_ADVICE_ID, LINE_ID, SKU_ID, CONFIG_ID, USER_DEF_TYPE_6, HOST_PRE_ADVICE_ID, HOST_LINE_ID, BATCH_ID, EXPIRY_DSTAMP, MANUF_DSTAMP, PALLET_CONFIG, ORIGIN_ID, CONDITION_ID, TAG_ID, LOCK_CODE, SPEC_CODE, QTY_DUE, NOTES, SAP_PLANT, SAP_STORE_LOC, DISALLOW_MERGE_RULES, USER_DEF_TYPE_1, USER_DEF_TYPE_2, USER_DEF_TYPE_3, USER_DEF_TYPE_4, USER_DEF_TYPE_5, USER_DEF_TYPE_7, USER_DEF_TYPE_8, USER_DEF_CHK_1, USER_DEF_CHK_2, USER_DEF_CHK_3, USER_DEF_CHK_4, USER_DEF_DATE_1, USER_DEF_DATE_2, USER_DEF_DATE_3, USER_DEF_DATE_4, USER_DEF_NUM_1, USER_DEF_NUM_2, USER_DEF_NUM_3, USER_DEF_NUM_4, USER_DEF_NOTE_1, USER_DEF_NOTE_2, TRACKING_LEVEL, QTY_DUE_TOLERANCE, CE_COO, OWNER_ID, CE_CONSIGNMENT_ID, COLLECTIVE_MODE, COLLECTIVE_SEQUENCE, CE_UNDER_BOND, CE_LINK, PRODUCT_PRICE, PRODUCT_CURRENCY, CE_INVOICE_NUMBER, SERIAL_VALID_MERGE, SAMPLING_TYPE, EXPECTED_GROSS_WEIGHT, EXPECTED_NET_WEIGHT, SESSION_TIME_ZONE_NAME, TIME_ZONE_NAME, NLS_CALENDAR, CLIENT_GROUP, MERGE_ACTION, MERGE_STATUS, MERGE_ERROR, MERGE_DSTAMP) VALUES ("
					+ key + ", 'M+S', '" + preAdviceId
					+ "', 20.0, '21126978', '21126978O01', '6', '', '', '', '', '', '', '', '', '', '', '', 100.0, '', '', '', 'N', '', '', '', '', '', '"
					+ vintage + "', '', '', '', '', '', '" + queryInsertDate
					+ " 10.02.40.993326099', '', '', '', NULL, NULL, NULL, NULL, '', '', 'CASE', 150.0, '', 'M+S', '', '', NULL, 'Y', '', NULL, '', '', 'N', '', NULL, NULL, 'Europe/Belfast', '', '', 'FOODS', 'A', 'Pending', '', '"
					+ queryInsertDate + " 10.02.40.993326099')";
		}
		System.out.println("Insert Pre Advice Line");
		System.out.println(query);
		if (context.getConnection() == null) {
			database.connect();
		}
		Statement stmt = context.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		context.getConnection().commit();
	}
}
