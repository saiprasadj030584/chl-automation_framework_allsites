
spool Crossdocklog.txt
             



SET SERVEROUTPUT ON;

DECLARE 

UPI_TYPE INTEGER := 1; /* 1 - ZIDC, 2 - ZRET */
ORD_TYPE INTEGER := 3; /* 1 - UK Franchise, 2 - Western European store, 3- UK Stores, 4 - Returns */

UPIL_CLOB_H CLOB;
UPIL_CLOB_D_1 CLOB := NULL;
UPIL_CLOB_D_2 CLOB := NULL;
UPIL_CLOB_T CLOB;
UPIL_CLOB CLOB;
UPIH_CLOB CLOB;
V_DATE VARCHAR2(40);
V_LINE_ID VARCHAR2(40);
V_ADVICE VARCHAR2(40);
V_URN VARCHAR2(40);
V_STO VARCHAR2(40);
V_ODN_REF VARCHAR2(40);
V_ASN VARCHAR2(40);
V_MAS VARCHAR2(40);
V_MAS_1 VARCHAR2(40);
V_MAS_2 VARCHAR2(40);
V_PAL VARCHAR2(40);
V_SUPP_SKU VARCHAR2(40);
V_SUPP VARCHAR2(40);
V_PROD VARCHAR2(40);
V_STO_1 VARCHAR2(40);
V_STO_2 VARCHAR2(40);
V_ODN_REF_1 VARCHAR2(40);
V_ASN_1 VARCHAR2(40);
V_TRL VARCHAR2(40);
V_S_ASN VARCHAR2(40);
V_URN_1 INTEGER;
V_ADVICE_1 INTEGER;
V_DUE VARCHAR2(40);
UPIH_COUNT INTEGER ;
DEL_CLOB CLOB;
DEL_COUNT INTEGER ;
ORDL_CLOB CLOB;
ORDH_CLOB CLOB;
PREL_CLOB CLOB;
PREH_CLOB CLOB;
ORDH_COUNT INTEGER;
V_ORDER VARCHAR2(40);
V_ORDER_1 INTEGER;
TYPE nested_type IS TABLE OF VARCHAR2(2000);
UPI_TYPE_1 nested_type;
ORD_TYPE_1 nested_type;
V_ORD_DATE VARCHAR2(40);
V_MOD VARCHAR2(40);
V_STROKE VARCHAR2(40);
P_ADV VARCHAR2(40);
P_ADV_1 INTEGER;
PREH_COUNT INTEGER;
V_CONFIG VARCHAR2(40);
RESULT INTEGER;

 CURSOR keySearch
   IS
               SELECT x.key,
                      y.Interface_id,
                      y.interface_name,
                      x.interface_type,
                      TO_TIMESTAMP ( REPLACE (SUBSTR (y.CreatedTimestamp, 9, 2)
                                           || SUBSTR (y.CreatedTimeStamp, 6, 2)
                                           || SUBSTR (y.CreatedTimeStamp, 1, 4)
                                           || SUBSTR (y.CreatedTimeStamp, 12, 8),':',''),'DDMMYYYYHH24MISS') interface_time
                 FROM dcsdba.web_services_log x,
                      XMLTABLE (
                         XMLNAMESPACES ('http://www.marksandspencer.com/common/envelope/v2' AS "ns0"),
                         'ns0:MnSDocument/ns0:Header'
                         PASSING XMLTYPE (x.payload)
                         COLUMNS Interface_id   PATH 'InterfaceId',
                            Interface_Name PATH 'InterfaceName',
                            CreatedTimestamp PATH 'CreatedTimestamp') y
   WHERE x.interface_type is null and x.start_dstamp >= sysdate - 2/1440
   ORDER BY x.key ASC;

CURSOR UPISEARCH IS
SELECT SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,QTY_DUE,TO_NUMBER(ODN) AS ODN,TO_NUMBER(URN_ADVICE) AS URN_ADVICE,TO_NUMBER(STO) AS STO, 
TO_NUMBER(ASN_ID) AS ASN_ID, TO_NUMBER(MASTER_URN) AS MASTER_URN  FROM SKU_DATA
ORDER BY TO_NUMBER(URN_ADVICE),TO_NUMBER(LINE_ID) ASC;

CURSOR ORDSEARCH IS
SELECT SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,TO_NUMBER(ODN) AS ODN,TO_NUMBER(STO) AS STO,SUM(QTY_DUE) AS QTY_DUE 
FROM SKU_DATA GROUP BY SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,ODN,STO
ORDER BY TO_NUMBER(ODN),TO_NUMBER(LINE_ID) ASC;

CURSOR PRESEARCH IS
SELECT SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,TO_NUMBER(STO) AS STO,SUM(QTY_DUE) AS QTY_DUE 
FROM SKU_DATA GROUP BY SOURCE_ID,HUB_ID,CUSTOMER_ID,SKU_ID,LINE_ID,STO
ORDER BY TO_NUMBER(STO),TO_NUMBER(LINE_ID) ASC;

BEGIN

ORD_TYPE_1 := nested_type (

'<userDefType4>Cross Dock</userDefType4><userDefType5>UKFranchise</userDefType5>	
<userDefType6>Retail</userDefType6>	<userDefType7>ZF20</userDefType7>',
'<userDefType4>Cross Dock</userDefType4>	<userDefType5>Store</userDefType5>	
<userDefType6>Retail</userDefType6>	<userDefType7>ZN18</userDefType7>',
'<userDefType4>Cross Dock</userDefType4>	<userDefType5>Store</userDefType5>	
<userDefType6>Retail</userDefType6>	<userDefType7>ZNL6</userDefType7>',	
'<userDefType4>Cross Dock</userDefType4>	<userDefType5>DC</userDefType5>	
<userDefType6>Return</userDefType6>	<userDefType7>ZRL</userDefType7>'	);
               

UPI_TYPE_1 := nested_type (
              '<userDefType7>ZIDC</userDefType7>',
              '<userDefType7>ZRET</userDefType7>');



SELECT TO_CHAR (SYSDATE, 'YYYY-MM-DD')|| 'T'|| TO_CHAR (SYSDATE, 'HH24:MI:SS') INTO V_DATE FROM DUAL;
V_DUE := TO_CHAR (SYSDATE + 10, 'YYYYMMDD');
V_ORD_DATE := TO_CHAR (SYSDATE + 10, 'YYYYMMDDHHMMSS');
UPIH_COUNT := 1;
DEL_COUNT := 1;
ORDH_COUNT := 1;
PREH_COUNT := 1;

select max (A) INTO V_ORDER from (
select TO_NUMBER(max(ORDER_ID)) A from ORDER_HEADER where LENGTH(ORDER_ID) = 10 and REGEXP_LIKE(ORDER_ID, '^[[:digit:]]+$')
union all
select TO_NUMBER(max(ORDER_ID)) A from INTERFACE_ORDER_HEADER where LENGTH(ORDER_ID) = 10 and REGEXP_LIKE(ORDER_ID, '^[[:digit:]]+$') );

select max (A) INTO P_ADV from (
select TO_NUMBER(max(USER_DEF_TYPE_1)) A from PRE_ADVICE_HEADER where USER_DEF_TYPE_1 IS NOT NULL and REGEXP_LIKE(USER_DEF_TYPE_1, '^[[:digit:]]+$')
union all
select TO_NUMBER(max(USER_DEF_TYPE_1)) A from INTERFACE_PRE_ADVICE_HEADER where USER_DEF_TYPE_1 IS NOT NULL and REGEXP_LIKE(USER_DEF_TYPE_1, '^[[:digit:]]+$') );


select max (A) INTO V_ADVICE from (
select TO_NUMBER(max(substr((Container_id),8,6))) A from upi_receipt_line where REGEXP_LIKE(CONTAINER_ID, '^[[:digit:]]+$')
union all
select TO_NUMBER(max(substr((tag_id),8,6))) A from interface_upi_receipt_line where REGEXP_LIKE(TAG_ID, '^[[:digit:]]+$') );

select max (A) INTO V_URN from (
select TO_NUMBER(max(substr((Container_id),22,6))) A from upi_receipt_line where REGEXP_LIKE(CONTAINER_ID, '^[[:digit:]]+$')
union all
select TO_NUMBER(max(substr((tag_id),22,6))) A from interface_upi_receipt_line where REGEXP_LIKE(TAG_ID, '^[[:digit:]]+$') );

select max (A) INTO V_STO from (
select TO_NUMBER(max(user_def_type_2)) A from upi_receipt_line where REGEXP_LIKE(user_def_type_2, '^[[:digit:]]+$')
union all
select TO_NUMBER(max(user_def_type_2)) A from interface_upi_receipt_line where REGEXP_LIKE(user_def_type_2, '^[[:digit:]]+$') );

select max (A) INTO V_ODN_REF from (
select TO_NUMBER(max(substr((user_def_type_1),1,10))) A from upi_receipt_line where REGEXP_LIKE(user_def_type_1, '^[[:digit:]]+$')
union all
select TO_NUMBER(max(substr((user_def_type_1),1,10))) A from interface_upi_receipt_line where REGEXP_LIKE(user_def_type_1, '^[[:digit:]]+$') );

select max (A) INTO V_ASN from (select max(TO_NUMBER((substr((user_def_type_5),6,5)))) A from upi_receipt_line where REGEXP_LIKE(user_def_type_5, '^[[:digit:]]+$') and user_def_type_5 like '000003%'
union all
select max(TO_NUMBER((substr((user_def_type_5),6,5)))) A from interface_upi_receipt_line where REGEXP_LIKE(user_def_type_5, '^[[:digit:]]+$') and user_def_type_5 like '000003%'
);

select max (A) INTO V_MAS from (
select TO_NUMBER(max(substr((user_def_note_1),6,10))) A from upi_receipt_line where user_def_note_1 <> user_def_note_2 and REGEXP_LIKE(pallet_id, '^[[:digit:]]+$')
union all
select TO_NUMBER(max(substr((user_def_note_1),6,10))) A from interface_upi_receipt_line where user_def_note_1 <> user_def_note_2 and REGEXP_LIKE(pallet_id, '^[[:digit:]]+$') );


/************************************** UPI RECEIPT LINE ******************************************/


UPIL_CLOB_H := '<?xml version="1.0" encoding="UTF-8"?><ns0:MnSDocument xmlns:ns0="http://www.marksandspencer.com/common/envelope/v2" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
   <ns0:Header>
      <EnvelopeVersion>2.0</EnvelopeVersion>
      <Language>en-GB</Language>
      <MessageId>586a5446be5f11e799b000001a7618f4</MessageId>
      <CorrelationId>AF00659351</CorrelationId>
      <CreatedTimestamp>'||V_DATE||'</CreatedTimestamp>
      <SourceApplicationName>SAP</SourceApplicationName>
      <InterfaceId>I1003</InterfaceId>
      <InterfaceName>Shipment</InterfaceName>
      <SecurityClassification>Internal Use</SecurityClassification>
      <PayloadName>Shipment Update</PayloadName>
      <PayloadKey/>
      <BatchMessageCount>1</BatchMessageCount>
      <BatchRecordCount>33</BatchRecordCount>
      <BatchRecordSum>8316|8316</BatchRecordSum>
      <ns0:Filter>
         <Level1>5542</Level1>
         <Level2>Har</Level2>
      </ns0:Filter>      
      <ETAG/>
   </ns0:Header>
   <ns0:Payload>
      <ns0:mergeUpiReceiptLines>
         <arrayOfMergeGeneratedUpiReceiptLine_2>';

FOR UPIL IN UPISEARCH
LOOP

SELECT SUPPLIER_SKU_ID INTO V_SUPP_SKU FROM SUPPLIER_SKU WHERE SKU_ID = UPIL.SKU_ID and ROWNUM= 1;
SELECT SUBSTR(SUPPLIER_ID,2,5) INTO V_SUPP FROM SUPPLIER_SKU WHERE SKU_ID = UPIL.SKU_ID and ROWNUM= 1;
SELECT PRODUCT_GROUP INTO V_PROD FROM SKU WHERE SKU_ID = UPIL.SKU_ID and ROWNUM= 1;

V_LINE_ID := LPAD(UPIL.LINE_ID,6,0);
V_ADVICE_1 := TO_NUMBER(UPIL.URN_ADVICE) + TO_NUMBER(V_ADVICE);
V_URN_1 := TO_NUMBER(UPIL.URN_ADVICE) + TO_NUMBER(V_URN);


IF UPI_TYPE = 1 AND UPIL.MASTER_URN IS NULL THEN

V_PAL := UPIL.CUSTOMER_ID||'950'||V_ADVICE_1||'0'||UPIL.SOURCE_ID||'0'||SUBSTR(V_PROD,2,2)|| V_URN_1 ||'00210';
V_MAS_2 := V_PAL;

ELSIF UPI_TYPE = 1 AND UPIL.MASTER_URN IS NOT NULL THEN

V_MAS_1 := TO_NUMBER(UPIL.MASTER_URN) + TO_NUMBER(V_MAS);
V_PAL := UPIL.CUSTOMER_ID||'950'||V_ADVICE_1||'0'||UPIL.SOURCE_ID||'0'||SUBSTR(V_PROD,2,2)|| V_URN_1 ||'00210';
V_MAS_2 := UPIL.SOURCE_ID||'0'||V_MAS_1;

ELSIF UPI_TYPE = 2 AND UPIL.MASTER_URN IS NULL  THEN

V_PAL := UPIL.CUSTOMER_ID||'000'||V_ADVICE_1||'0'||UPIL.SOURCE_ID||'100'||TO_CHAR(SYSDATE, 'DDMMYY')||'00200';
V_MAS_2 := V_PAL;

ELSIF UPI_TYPE = 2 AND UPIL.MASTER_URN IS NOT NULL THEN

V_MAS_1 := TO_NUMBER(UPIL.MASTER_URN) + TO_NUMBER(V_MAS);
V_PAL := UPIL.CUSTOMER_ID||'000'||V_ADVICE_1||'0'||UPIL.SOURCE_ID||'100'||TO_CHAR(SYSDATE, 'DDMMYY')||'00200';
V_MAS_2 := UPIL.HUB_ID||'0'||V_MAS_1;

END IF;




V_STO_1 := V_STO + UPIL.STO;
V_ODN_REF_1 := V_ODN_REF + UPIL.STO||V_LINE_ID;
V_ASN_1 := LPAD(V_ASN + UPIL.ASN_ID,10,0);
V_CONFIG := TRIM(LEADING '0' FROM UPIL.SKU_ID)||'EA';

SELECT TO_CHAR (SYSDATE, 'DDMM')|| LPAD(UPIL.ASN_ID, 3,0) INTO V_TRL FROM DUAL;

SELECT UPIL.SOURCE_ID||TO_CHAR (SYSDATE, 'DDMM')|| LPAD(UPIL.ASN_ID, 3,0) INTO V_S_ASN FROM DUAL;

UPIL_CLOB_D_1 := '<value>
               <batchId xsi:nil="true"/>
               <ceConsignmentId xsi:nil="true"/>
               <ceCoo xsi:nil="true"/>
               <ceInvoiceNumber xsi:nil="true"/>
               <ceLink>N</ceLink>
               <ceUnderBond>N</ceUnderBond>
               <clientGroup>NDC</clientGroup>
               <clientId>M+S</clientId>
               <conditionId xsi:nil="true"/>
               <configId>'||V_CONFIG||'</configId>
               <containerId xsi:nil="true"/>
               <disallowMergeRules>N</disallowMergeRules>
               <expectedGrossWeight xsi:nil="true"/>
               <expectedNetWeight xsi:nil="true"/>
               <expiryDstamp/>
               <hostLineId xsi:nil="true"/>
               <hostPalletId xsi:nil="true"/>
               <lineId>'||V_LINE_ID||'</lineId>
               <lockCode xsi:nil="true"/>
               <manufDstamp xsi:nil="true"/>
               <mergeAction>U</mergeAction>
               <nlsCalendar xsi:nil="true"/>
               <originId xsi:nil="true"/>
               <ownerId>M+S</ownerId>
               <palletId>'||V_MAS_2||'</palletId>
               <preAdviceId xsi:nil="true"/>
               <preAdviceLineId xsi:nil="true"/>
               <productCurrency xsi:nil="true"/>
               <productPrice xsi:nil="true"/>
               <qtyDue>'||UPIL.QTY_DUE||'.000'||'</qtyDue>
               <receiptDstamp xsi:nil="true"/>
               <skuId>'||UPIL.SKU_ID||'</skuId>
               <specCode xsi:nil="true"/>
               <supplierId/>
               <tagId>'||V_PAL||'</tagId>
               <timeZoneName>London/Europe</timeZoneName>
               <trackingLevel>EA</trackingLevel>
               <userDefChk1>N</userDefChk1>
               <userDefChk2>Y</userDefChk2>
               <userDefChk3>N</userDefChk3>
               <userDefChk4>N</userDefChk4>
               <userDefDate1 xsi:nil="true"/>
               <userDefDate2 xsi:nil="true"/>
               <userDefDate3 xsi:nil="true"/>
               <userDefDate4 xsi:nil="true"/>
               <userDefNote1>'||V_MAS_2||'</userDefNote1>
               <userDefNote2>'||V_PAL||'</userDefNote2>
               <userDefNum1>1</userDefNum1>
               <userDefNum2>7.000000</userDefNum2>
               <userDefNum3 xsi:nil="true"/>
               <userDefNum4 xsi:nil="true"/>
               <userDefType1>'||V_ODN_REF_1||'</userDefType1>
               <userDefType2>'||V_STO_1||'</userDefType2>
               <userDefType3>0007</userDefType3>
               <userDefType4>'||V_SUPP_SKU||'</userDefType4>
               <userDefType5>'||V_ASN_1||'</userDefType5>
               <userDefType6>'||V_PROD||'</userDefType6>
               '||UPI_TYPE_1(UPI_TYPE)||'
               <userDefType8 xsi:nil="true"/>
               <userDefType9>'||UPIL.SOURCE_ID||'</userDefType9>
               <userDefType10>'||V_TRL||'</userDefType10>
               <userDefType11>00000000002306270631</userDefType11>
               <userDefType12>'||V_S_ASN||'</userDefType12>
               </value>';
            
    UPIL_CLOB_D_2 := UPIL_CLOB_D_2||UPIL_CLOB_D_1;
    
DBMS_OUTPUT.PUT_LINE('MASTER_URN :'||V_MAS_2 );
DBMS_OUTPUT.PUT_LINE('PALLET_ID  :'||V_PAL );
DBMS_OUTPUT.PUT_LINE('ASN_ID     :'||V_ASN_1 );
DBMS_OUTPUT.PUT_LINE('SKU_ID     :'||UPIL.SKU_ID);
DBMS_OUTPUT.PUT_LINE('UPC        :'||V_SUPP_SKU);
DBMS_OUTPUT.PUT_LINE('QTY_DUE    :'||UPIL.QTY_DUE );
        

            
END LOOP;
   

UPIL_CLOB_T:= '</arrayOfMergeGeneratedUpiReceiptLine_2>
      </ns0:mergeUpiReceiptLines>
   </ns0:Payload>
</ns0:MnSDocument>';

UPIL_CLOB := UPIL_CLOB_H||UPIL_CLOB_D_2||UPIL_CLOB_T;

Insert into dcsdba.web_services_log (Key, identifier, start_dstamp, session_id, payload, success, reference_id)

VALUES (dcsdba.WEB_SERVICES_LOG_PK_SEQ.nextVal, 'I1003', systimestamp, NVL(USERENV('SESSIONID'),0),

UPIL_CLOB, 'N', dcsdba.WEB_SERVICES_LOG_PK_SEQ.nextVal);


/************************************** UPI RECEIPT HEADER ******************************************/

FOR UPIH IN UPISEARCH
LOOP

IF UPIH.URN_ADVICE = UPIH_COUNT THEN

SELECT SUBSTR(SUPPLIER_ID,2,5) INTO V_SUPP FROM SUPPLIER_SKU WHERE SKU_ID = UPIH.SKU_ID and ROWNUM= 1;
SELECT PRODUCT_GROUP INTO V_PROD FROM SKU WHERE SKU_ID = UPIH.SKU_ID and ROWNUM= 1;

V_ADVICE_1 := TO_NUMBER(UPIH.URN_ADVICE) + TO_NUMBER(V_ADVICE);
V_URN_1 := TO_NUMBER(UPIH.URN_ADVICE) + TO_NUMBER(V_URN);
V_ASN_1 := LPAD(V_ASN + UPIH.ASN_ID,10,0);



IF UPI_TYPE = 1 AND UPIH.MASTER_URN IS NULL THEN

V_PAL := UPIH.CUSTOMER_ID||'950'||V_ADVICE_1||'0'||UPIH.SOURCE_ID||'0'||SUBSTR(V_PROD,2,2)|| V_URN_1 ||'00210';
V_MAS_2 := V_PAL;

ELSIF UPI_TYPE = 1 AND UPIH.MASTER_URN IS NOT NULL THEN

V_MAS_1 := TO_NUMBER(UPIH.MASTER_URN) + TO_NUMBER(V_MAS);
V_PAL := UPIH.CUSTOMER_ID||'950'||V_ADVICE_1||'0'||UPIH.SOURCE_ID||'0'||SUBSTR(V_PROD,2,2)|| V_URN_1 ||'00210';
V_MAS_2 := UPIH.SOURCE_ID||'0'||V_MAS_1;

ELSIF UPI_TYPE = 2 AND UPIH.MASTER_URN IS NULL THEN

V_PAL := UPIH.CUSTOMER_ID||'000'||V_ADVICE_1||'0'||UPIH.SOURCE_ID||'100'||TO_CHAR(SYSDATE, 'DDMMYY')||'00200';
V_MAS_2 := V_PAL;

ELSIF UPI_TYPE = 2 AND UPIH.MASTER_URN IS NOT NULL THEN

V_MAS_1 := TO_NUMBER(UPIH.MASTER_URN) + TO_NUMBER(V_MAS);
V_PAL := UPIH.CUSTOMER_ID||'000'||V_ADVICE_1||'0'||UPIH.SOURCE_ID||'100'||TO_CHAR(SYSDATE, 'DDMMYY')||'00200';
V_MAS_2 := UPIH.HUB_ID||'0'||V_MAS_1;

END IF;


SELECT UPIH.SOURCE_ID||TO_CHAR (SYSDATE, 'DDMM')|| LPAD(UPIH.ASN_ID, 3,0) INTO V_S_ASN FROM DUAL;

UPIH_CLOB := '<?xml version="1.0" encoding="UTF-8"?><!--Output from DOMD Version 1.9 for XSLT for Harmonized Header for I1003 in WMS04 Adapter--><ns0:MnSDocument xmlns:ns0="http://www.marksandspencer.com/common/envelope/v2" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
   <ns0:Header>
      <EnvelopeVersion>2.0</EnvelopeVersion>
      <Language>en-GB</Language>
      <MessageId>586a5446be5f11e799b000001a7618f4</MessageId>
      <CorrelationId>AF00659351</CorrelationId>
      <CreatedTimestamp>'||V_DATE||'</CreatedTimestamp>
      <SourceApplicationName>SAP</SourceApplicationName>
      <InterfaceId>I1003</InterfaceId>
      <InterfaceName>Shipment</InterfaceName>
      <SecurityClassification>Internal Use</SecurityClassification>
      <PayloadName>Shipment Update</PayloadName>
      <PayloadKey/>
      <BatchMessageCount>1</BatchMessageCount>
      <BatchRecordCount>33</BatchRecordCount>
      <BatchRecordSum>8316|8316</BatchRecordSum>
      <ns0:Filter>
         <Level1>5542</Level1>
         <Level2>Har</Level2>
      </ns0:Filter>      
      <ETAG/>
   </ns0:Header>
   <ns0:Payload>
      <ns0:mergeUpiReceiptHeaders>
         <arrayOfMergeGeneratedUpiReceiptHeader_2>
               <value>
               <asnId>'||V_ASN_1||'</asnId>
               <carrierName xsi:nil="true"/>
               <carrierReference xsi:nil="true"/>
               <ceConsignmentId xsi:nil="true"/>
               <ceInvoiceNumber xsi:nil="true"/>
               <clientGroup>NDC</clientGroup>
               <clientId>M+S</clientId>
               <consignment xsi:nil="true"/>
               <crossDock>N</crossDock>
               <crossDockToSite>N</crossDockToSite>
               <customerId xsi:nil="true"/>
               <deliverByDate xsi:nil="true"/>
               <depth>0.000</depth>
               <disallowMergeRules>N</disallowMergeRules>
               <dueDstamp>'||V_DUE||'000000'||'</dueDstamp>
               <height>0</height>
               <loadSequence xsi:nil="true"/>
               <mergeAction>U</mergeAction>
               <mergeGeneratedUpiReceiptLines xsi:nil="true"/>
               <modeOfTransport>ROAD</modeOfTransport>
               <nlsCalendar xsi:nil="true"/>
               <notes xsi:nil="true"/>
               <palletConfig xsi:nil="true"/>
               <palletId>'||V_MAS_2||'</palletId>
               <priority xsi:nil="true"/>
               <receiptId xsi:nil="true"/>
               <routeId xsi:nil="true"/>
               <shipByDate xsi:nil="true"/>
               <shipDock xsi:nil="true"/>
               <siteId>'||UPIH.HUB_ID||'</siteId>
               <status>Released</status>
               <statusReasonCode xsi:nil="true"/>
               <supAddress1 xsi:nil="true"/>
               <supAddress2 xsi:nil="true"/>
               <supContact xsi:nil="true"/>
               <supContactEmail xsi:nil="true"/>
               <supContactFax xsi:nil="true"/>
               <supContactMobile xsi:nil="true"/>
               <supContactPhone xsi:nil="true"/>
               <supCountry xsi:nil="true"/>
               <supCounty xsi:nil="true"/>
               <supName xsi:nil="true"/>
               <supPostcode xsi:nil="true"/>
               <supTown xsi:nil="true"/>
               <supplierId></supplierId>
               <timeZoneName>Europe/London</timeZoneName>
               <toSiteId xsi:nil="true"/>
               <tod xsi:nil="true"/>
               <todPlace xsi:nil="true"/>
               <userDefChk1>N</userDefChk1>
               <userDefChk2>Y</userDefChk2>
               <userDefChk3>N</userDefChk3>
               <userDefChk4>N</userDefChk4>
               <userDefDate1 xsi:nil="true"/>
               <userDefDate2 xsi:nil="true"/>
               <userDefDate3 xsi:nil="true"/>
               <userDefDate4 xsi:nil="true"/>
               <userDefNote1>'||V_MAS_2||'</userDefNote1>
               <userDefNote2 xsi:nil="true"/>
               <userDefNum1 xsi:nil="true"/>
               <userDefNum2 xsi:nil="true"/>
               <userDefNum3 xsi:nil="true"/>
               <userDefNum4 xsi:nil="true"/>
               <userDefType1>1</userDefType1>
               <userDefType2>00000000002306270631</userDefType2>
               <userDefType3/>
               <userDefType4 xsi:nil="true"/>
               <userDefType5 xsi:nil="true"/>
               <userDefType6>000000000090200020</userDefType6>
               '||UPI_TYPE_1(UPI_TYPE)||'
               <userDefType8>'||V_S_ASN||'</userDefType8>
               <vatNumber xsi:nil="true"/>
               <volume>9999</volume>
               <weight>0</weight>
               <width>0</width>
               <yardContainerId xsi:nil="true"/>
               <yardContainerType xsi:nil="true"/>
            </value>
         </arrayOfMergeGeneratedUpiReceiptHeader_2>
      </ns0:mergeUpiReceiptHeaders>
   </ns0:Payload>
</ns0:MnSDocument>';


Insert into dcsdba.web_services_log (Key, identifier, start_dstamp, session_id, payload, success, reference_id)

VALUES (dcsdba.WEB_SERVICES_LOG_PK_SEQ.nextVal, 'I1003', systimestamp, NVL(USERENV('SESSIONID'),0),

UPIH_CLOB, 'N', dcsdba.WEB_SERVICES_LOG_PK_SEQ.nextVal);

UPIH_COUNT := UPIH_COUNT + 1;

END IF;

END LOOP;


/************************************** DELIVERY ******************************************/

FOR DEL IN UPISEARCH
LOOP

IF DEL.ASN_ID = DEL_COUNT THEN

V_ASN_1 := LPAD(V_ASN + DEL.ASN_ID,10,0);
SELECT TO_CHAR (SYSDATE, 'DDMM')|| LPAD(DEL.ASN_ID, 3,0) INTO V_TRL FROM DUAL;
SELECT DEL.SOURCE_ID||TO_CHAR (SYSDATE, 'DDMM')|| LPAD(DEL.ASN_ID, 3,0) INTO V_S_ASN FROM DUAL;

DEL_CLOB := '<?xml version="1.0" encoding="UTF-8"?><!--Output from DOMD Version 1.9 for XSLT for Non-Harmonized Delivery for I1003 in WMS04 Adapter--><ns0:MnSDocument xmlns:ns0="http://www.marksandspencer.com/common/envelope/v2" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
   <ns0:Header>
      <EnvelopeVersion>2.0</EnvelopeVersion>
      <Language>en-GB</Language>
      <MessageId>00ffff8fb81211e7a4e300001a760314</MessageId>
      <CorrelationId>AF00652642</CorrelationId>
      <CreatedTimestamp>'||V_DATE||'</CreatedTimestamp>
      <SourceApplicationName>SAP</SourceApplicationName>
      <InterfaceId>I1003</InterfaceId>
      <InterfaceName>Shipment</InterfaceName>
      <SecurityClassification>Internal Use</SecurityClassification>
      <PayloadName>Shipment Update</PayloadName>
      <PayloadKey/>
      <BatchMessageCount>1</BatchMessageCount>
      <BatchRecordCount>58</BatchRecordCount>
      <BatchRecordSum>14980|14980</BatchRecordSum>
      <ns0:Filter>
         <Level1>5542</Level1>
         <Level2>NHar</Level2>
      </ns0:Filter>      
      <ETAG/>
   </ns0:Header>
   <ns0:Payload>
      <ns0:mergeUpiDelivery>
         <arrayOfMergeGeneratedUpiReceiptDelivery_2>
            <value>
               <MergeAction>U</MergeAction>
               <Asn_id>'||V_ASN_1||'</Asn_id>
               <Site_Id>'||DEL.HUB_ID||'</Site_Id>
               <Supplier_Reference>'||V_TRL||'</Supplier_Reference>
               <Status>Released</Status>
               <User_Id xsi:nil="true"/>
               <Supplier_Id>'||DEL.SOURCE_ID||'</Supplier_Id>
               <Due_Dstamp>'||V_DUE||'000000'||'</Due_Dstamp>
               <Carrier_Id xsi:nil="true"/>
               <Trailer_Type xsi:nil="true"/>
               <Seal_Id/>
               <Temperature xsi:nil="true"/>
               <Address_Id xsi:nil="true"/>
               <Contact xsi:nil="true"/>
               <Contact_Phone xsi:nil="true"/>
               <Contact_Fax xsi:nil="true"/>
               <Contact_Email xsi:nil="true"/>
               <Name xsi:nil="true"/>
               <Address1 xsi:nil="true"/>
               <Address2 xsi:nil="true"/>
               <Town xsi:nil="true"/>
               <County xsi:nil="true"/>
               <Postcode xsi:nil="true"/>
               <Country xsi:nil="true"/>
               <Notes>'||V_S_ASN||'</Notes>
               <UserDefType1/>
               <UserDefType2 xsi:nil="true"/>
               <UserDefType3 xsi:nil="true"/>
               <UserDefType4 xsi:nil="true"/>
               <UserDefType5 xsi:nil="true"/>
               <UserDefType6 xsi:nil="true"/>
               '||UPI_TYPE_1(UPI_TYPE)||'
               <UserDefType8>'||V_S_ASN||'</UserDefType8>
               <UserDefChk1 xsi:nil="true"/>
               <UserDefChk2 xsi:nil="true"/>
               <UserDefChk3 xsi:nil="true"/>
               <UserDefChk4 xsi:nil="true"/>
               <UserDefDate1 xsi:nil="true"/>
               <UserDefDate2 xsi:nil="true"/>
               <UserDefDate3 xsi:nil="true"/>
               <UserDefDate4 xsi:nil="true"/>
               <UserDefNum1 xsi:nil="true"/>
               <UserDefNum2 xsi:nil="true"/>
               <UserDefNum3 xsi:nil="true"/>
               <UserDefNum4 xsi:nil="true"/>
               <UserDefNote1 xsi:nil="true"/>
               <UserDefNote2 xsi:nil="true"/>
               <Client_Id>M+S</Client_Id>
               <Time_Zone_Name xsi:nil="true"/>
               <Client_Group>NDC</Client_Group>
               <Disallow_Merge_Rules xsi:nil="true"/>
               <Contact_Mobile xsi:nil="true"/>
               <Nls_Calendar xsi:nil="true"/>
            </value>
         </arrayOfMergeGeneratedUpiReceiptDelivery_2>
      </ns0:mergeUpiDelivery>
   </ns0:Payload>
</ns0:MnSDocument>';


Insert into dcsdba.web_services_log (Key, identifier, start_dstamp, session_id, payload, success, reference_id)

VALUES (dcsdba.WEB_SERVICES_LOG_PK_SEQ.nextVal, 'I1003', systimestamp, NVL(USERENV('SESSIONID'),0),

DEL_CLOB, 'N', dcsdba.WEB_SERVICES_LOG_PK_SEQ.nextVal);

DEL_COUNT := DEL_COUNT + 1;

END IF;

END LOOP;


/************************************** ORDER LINE******************************************/



FOR ORDL IN ORDSEARCH
LOOP

V_LINE_ID := LPAD(ORDL.LINE_ID,6,0);
V_ORDER_1 := TO_NUMBER(ORDL.ODN) + TO_NUMBER(V_ORDER);

SELECT SUPPLIER_SKU_ID INTO V_SUPP_SKU FROM SUPPLIER_SKU WHERE SKU_ID = ORDL.SKU_ID and ROWNUM= 1;
V_ODN_REF_1 := V_ODN_REF + ORDL.STO||V_LINE_ID;
V_STO_1 := V_STO + ORDL.STO;
V_STO_2 := V_STO + ORDL.STO + 10000000;

ORDL_CLOB := '<?xml version="1.0" encoding="UTF-8"?><!--Output from DOMD Version 1.6 for XSLT Transformation(LINE_F0016) for I0177 in {WMS Adapter} --><ns0:MnSDocument xmlns:ns0="http://www.marksandspencer.com/common/envelope/v2" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
   <ns0:Header>
      <EnvelopeVersion>2.0</EnvelopeVersion>
      <Language>en-GB</Language>
      <MessageId>fa12ba18049611e8a55e00001a761958</MessageId>
      <CorrelationId>AK31384882</CorrelationId>
      <CreatedTimestamp>'||V_DATE||'</CreatedTimestamp>
      <SourceApplicationName>SAP</SourceApplicationName>
      <InterfaceId>I177</InterfaceId>
      <InterfaceName>Outbound Delivery To WMS</InterfaceName>
      <SecurityClassification>Internal Use</SecurityClassification>
      <PayloadName>Outbound Delivery</PayloadName>
      <PayloadKey/>
      <BatchMessageCount>1</BatchMessageCount>
      <BatchRecordCount>17</BatchRecordCount>
      <BatchRecordSum>21|21</BatchRecordSum>
      <ns0:Filter>
         <Level1>5542</Level1>
      </ns0:Filter>      
      <ETAG/>
   </ns0:Header>
   <ns0:Payload>
      <ns0:mergeOrderLines>
         <arrayOfMergeGeneratedOrderLine_2>         
              <value>
               <allocate>Y</allocate>
               <backOrdered>N</backOrdered>
               <batchId/>
               <batchMixing>Y</batchMixing>
               <ceCoo xsi:nil="true"/>
               <ceReceiptType xsi:nil="true"/>
               <clientGroup>NDC</clientGroup>
               <clientId>M+S</clientId>
               <conditionId xsi:nil="true"/>
               <configId xsi:nil="true"/>
               <customerSkuDesc1 xsi:nil="true"/>
               <customerSkuDesc2 xsi:nil="true"/>
               <customerSkuId xsi:nil="true"/>
               <deallocate xsi:nil="true"/>
               <disallowMergeRules>N</disallowMergeRules>
               <disallowSubstitution>N</disallowSubstitution>
               <documentationText1 xsi:nil="true"/>
               <documentationUnit xsi:nil="true"/>
               <expectedValue xsi:nil="true"/>
               <expectedVolume xsi:nil="true"/>
               <expectedWeight xsi:nil="true"/>
               <extendedPrice>1.23</extendedPrice>
               <fullTrackingLevelOnly>N</fullTrackingLevelOnly>
               <hostLineId xsi:nil="true"/>
               <hostOrderId xsi:nil="true"/>
               <ignoreWeightCapture>N</ignoreWeightCapture>
               <kitPlanId xsi:nil="true"/>
               <kitSplit>Y</kitSplit>
               <lineId>'||V_LINE_ID||'</lineId>
               <lineValue xsi:nil="true"/>
               <locationId xsi:nil="true"/>
               <lockCode xsi:nil="true"/>
               <maxFullPalletPerc xsi:nil="true"/>
               <maxQtyOrdered xsi:nil="true"/>
               <mergeAction>U</mergeAction>
               <minFullPalletPerc xsi:nil="true"/>
               <minQtyOrdered xsi:nil="true"/>
               <nlsCalendar xsi:nil="true"/>
               <notes xsi:nil="true"/>
               <orderId>'||V_ORDER_1||'</orderId>
               <originId xsi:nil="true"/>
               <ownerId>M+S</ownerId>
               <productCurrency xsi:nil="true"/>
               <productPrice xsi:nil="true"/>
               <psftDmndLine xsi:nil="true"/>
               <psftIntLine xsi:nil="true"/>
               <psftSchdLine xsi:nil="true"/>
               <purchaseOrder xsi:nil="true"/>
               <qtyOrdered>'||ORDL.QTY_DUE||'</qtyOrdered>
               <ruleId xsi:nil="true"/>
               <sapPickReq xsi:nil="true"/>
               <serialNumber/>
               <shelfLifeDays xsi:nil="true"/>
               <shelfLifePercent xsi:nil="true"/>
               <skuId>'||ORDL.SKU_ID||'</skuId>
               <sohId xsi:nil="true"/>
               <specCode xsi:nil="true"/>
               <stageRouteId xsi:nil="true"/>
               <substituteGrade xsi:nil="true"/>
               <taskPerEach>N</taskPerEach>
               <tax1 xsi:nil="true"/>
               <tax2 xsi:nil="true"/>
               <timeZoneName>Europe/London</timeZoneName>
               <trackingLevel>EA</trackingLevel>
               <unallocatable>N</unallocatable>
               <usePickToGrid>N</usePickToGrid>
               <userDefChk1>N</userDefChk1>
               <userDefChk2>N</userDefChk2>
               <userDefChk3>N</userDefChk3>
               <userDefChk4>N</userDefChk4>
               <userDefDate1 xsi:nil="true"/>
               <userDefDate2 xsi:nil="true"/>
               <userDefDate3 xsi:nil="true"/>
               <userDefDate4 xsi:nil="true"/>
               <userDefNote1 xsi:nil="true"/>
               <userDefNote2 xsi:nil="true"/>
               <userDefNum1 xsi:nil="true"/>
               <userDefNum2 xsi:nil="true"/>
               <userDefNum3 xsi:nil="true"/>
               <userDefNum4 xsi:nil="true"/>
               <userDefType1>'||V_ODN_REF_1||'</userDefType1>
               <userDefType2>0007</userDefType2>
               <userDefType3>'||V_SUPP_SKU||'</userDefType3>
               '||ORD_TYPE_1(ORD_TYPE)||'
               <userDefType8></userDefType8>
               <userDefType9>'||V_STO_2||'</userDefType9>
            </value>
          </arrayOfMergeGeneratedOrderLine_2>
      </ns0:mergeOrderLines>
   </ns0:Payload>
</ns0:MnSDocument>';


Insert into dcsdba.web_services_log (Key, identifier, start_dstamp, session_id, payload, success, reference_id)

VALUES (dcsdba.WEB_SERVICES_LOG_PK_SEQ.nextVal, 'I177', systimestamp, NVL(USERENV('SESSIONID'),0),

ORDL_CLOB, 'N', dcsdba.WEB_SERVICES_LOG_PK_SEQ.nextVal);


DBMS_OUTPUT.PUT_LINE( 'ORDER_ID    :'|| V_ORDER_1 );
DBMS_OUTPUT.PUT_LINE( 'SKU_ID      :'|| ORDL.SKU_ID  );
DBMS_OUTPUT.PUT_LINE( 'UPC         :'|| V_SUPP_SKU );
DBMS_OUTPUT.PUT_LINE( 'QTY_ORDERED :'|| ORDL.QTY_DUE  );


END LOOP;

/************************************** ORDER HEADER ******************************************/

FOR ORDH IN ORDSEARCH
LOOP

IF ORDH.ODN = ORDH_COUNT THEN

V_ORDER_1 := TO_NUMBER(ORDH.ODN) + TO_NUMBER(V_ORDER);

ORDH_CLOB := '<?xml version="1.0" encoding="UTF-8"?><!--Output from DOMD Version 1.8 for XSLT for Transformation(Header_F0015) for I0177 in {WMS Adapter} --><ns0:MnSDocument xmlns:ns0="http://www.marksandspencer.com/common/envelope/v2" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
   <ns0:Header>
      <EnvelopeVersion>2.0</EnvelopeVersion>
      <Language>en-GB</Language>
      <MessageId>07b2259c049711e8c62100001a7619bc</MessageId>
      <CorrelationId>AK31385162</CorrelationId>
      <CreatedTimestamp>'||V_DATE||'</CreatedTimestamp>
      <SourceApplicationName>SAP</SourceApplicationName>
      <InterfaceId>I177</InterfaceId>
      <InterfaceName>Outbound Delivery To WMS</InterfaceName>
      <SecurityClassification>Internal Use</SecurityClassification>
      <PayloadName>Outbound Delivery</PayloadName>
      <PayloadKey/>
      <BatchMessageCount>1</BatchMessageCount>
      <BatchRecordCount>66</BatchRecordCount>
      <BatchRecordSum>110|110</BatchRecordSum>
      <ns0:Filter>
         <Level1>5542</Level1>
      </ns0:Filter>      
      <ETAG/>
   </ns0:Header>
   <ns0:Payload>
      <ns0:mergeOrderHeaders>
         <arrayOfMergeGeneratedOrderHeader_2>
            <value>
               <address1 xsi:nil="true"/>
               <address2 xsi:nil="true"/>
               <allocationPriority xsi:nil="true"/>
               <allowPalletPick>N</allowPalletPick>
               <carrierBags>N</carrierBags>
               <carrierId xsi:nil="true"/>
               <ceCustomsCustomer xsi:nil="true"/>
               <ceExciseCustomer xsi:nil="true"/>
               <ceInstructions xsi:nil="true"/>
               <ceOrderType xsi:nil="true"/>
               <ceReasonCode xsi:nil="true"/>
               <ceReasonNotes xsi:nil="true"/>
               <cheapestCarrier>N</cheapestCarrier>
               <cidNumber xsi:nil="true"/>
               <clientGroup>NDC</clientGroup>
               <clientId>M+S</clientId>
               <cod>N</cod>
               <codCurrency xsi:nil="true"/>
               <codType xsi:nil="true"/>
               <codValue xsi:nil="true"/>
               <consignment xsi:nil="true"/>
               <contact xsi:nil="true"/>
               <contactEmail xsi:nil="true"/>
               <contactFax xsi:nil="true"/>
               <contactMobile xsi:nil="true"/>
               <contactPhone xsi:nil="true"/>
               <country xsi:nil="true"/>
               <county xsi:nil="true"/>
               <crossDockToSite>N</crossDockToSite>
               <customerId>'||ORDH.CUSTOMER_ID||'</customerId>
               <deliverByDate xsi:nil="true"/>
               <deliveredDstamp xsi:nil="true"/>
               <deliveryPoint xsi:nil="true"/>
               <directToStore>N</directToStore>
               <disallowMergeRules>N</disallowMergeRules>
               <disallowShortShip xsi:nil="true"/>
               <discount xsi:nil="true"/>
               <dispatchMethod xsi:nil="true"/>
               <documentationText1 xsi:nil="true"/>
               <documentationText2 xsi:nil="true"/>
               <documentationText3 xsi:nil="true"/>
               <expectedValue xsi:nil="true"/>
               <expectedVolume xsi:nil="true"/>
               <expectedWeight xsi:nil="true"/>
               <export>N</export>
               <fastestCarrier>N</fastestCarrier>
               <forceSingleCarrier>N</forceSingleCarrier>
               <freightCharges xsi:nil="true"/>
               <freightCost xsi:nil="true"/>
               <freightCurrency xsi:nil="true"/>
               <freightTerms xsi:nil="true"/>
               <fromSiteId>'||ORDH.HUB_ID||'</fromSiteId>
               <gln xsi:nil="true"/>
               <hubAddress1 xsi:nil="true"/>
               <hubAddress2 xsi:nil="true"/>
               <hubAddressId></hubAddressId>
               <hubCarrierId xsi:nil="true"/>
               <hubContact xsi:nil="true"/>
               <hubContactEmail xsi:nil="true"/>
               <hubContactFax xsi:nil="true"/>
               <hubContactMobile xsi:nil="true"/>
               <hubContactPhone xsi:nil="true"/>
               <hubCountry xsi:nil="true"/>
               <hubCounty xsi:nil="true"/>
               <hubGln xsi:nil="true"/>
               <hubName xsi:nil="true"/>
               <hubPostcode xsi:nil="true"/>
               <hubServiceLevel xsi:nil="true"/>
               <hubTown xsi:nil="true"/>
               <hubVatNumber xsi:nil="true"/>
               <instructions xsi:nil="true"/>
               <insuranceCost xsi:nil="true"/>
               <invAddress1 xsi:nil="true"/>
               <invAddress2 xsi:nil="true"/>
               <invAddressId xsi:nil="true"/>
               <invContact xsi:nil="true"/>
               <invContactEmail xsi:nil="true"/>
               <invContactFax xsi:nil="true"/>
               <invContactMobile xsi:nil="true"/>
               <invContactPhone xsi:nil="true"/>
               <invCountry xsi:nil="true"/>
               <invCounty xsi:nil="true"/>
               <invCurrency xsi:nil="true"/>
               <invDstamp xsi:nil="true"/>
               <invGln xsi:nil="true"/>
               <invName xsi:nil="true"/>
               <invPostcode xsi:nil="true"/>
               <invReference xsi:nil="true"/>
               <invTotal1 xsi:nil="true"/>
               <invTotal2 xsi:nil="true"/>
               <invTotal3 xsi:nil="true"/>
               <invTotal4 xsi:nil="true"/>
               <invTown xsi:nil="true"/>
               <invVatNumber xsi:nil="true"/>
               <language xsi:nil="true"/>
               <letterOfCredit xsi:nil="true"/>
               <loadSequence xsi:nil="true"/>
               <locationNumber xsi:nil="true"/>
               <mergeAction>U</mergeAction>
               <mergeGeneratedOrderLines xsi:nil="true"/>
               <metapackCarrierPre>N</metapackCarrierPre>
               <miscCharges xsi:nil="true"/>
               <moveTaskStatus>Hold</moveTaskStatus>
               <mpackConsignment xsi:nil="true"/>
               <mpackNominatedDstamp xsi:nil="true"/>
               <name xsi:nil="true"/>
               <ncts xsi:nil="true"/>
               <nlsCalendar xsi:nil="true"/>
               <orderDate>'||V_ORD_DATE||'</orderDate>
               <orderId>'||V_ORDER_1||'</orderId>
               <orderReference xsi:nil="true"/>
               <orderType xsi:nil="true"/>
               <otherFee xsi:nil="true"/>
               <ownerId>M+S</ownerId>
               <paymentTerms xsi:nil="true"/>
               <postcode xsi:nil="true"/>
               <printInvoice>N</printInvoice>
               <priority>50</priority>
               <psftDmndSrce xsi:nil="true"/>
               <psftOrderId xsi:nil="true"/>
               <purchaseOrder xsi:nil="true"/>
               <repack xsi:nil="true"/>
               <repackLocId xsi:nil="true"/>
               <retailerId xsi:nil="true"/>
               <routeId xsi:nil="true"/>
               <sellerName xsi:nil="true"/>
               <sellerPhone xsi:nil="true"/>
               <serviceLevel xsi:nil="true"/>
               <shipByDate xsi:nil="true"/>
               <shipDock xsi:nil="true"/>
               <shipmentGroup xsi:nil="true"/>
               <sidNumber xsi:nil="true"/>
               <signatory xsi:nil="true"/>
               <singleOrderSortation>N</singleOrderSortation>
               <siteReplen>N</siteReplen>
               <sohId xsi:nil="true"/>
               <splitShippingUnits>N</splitShippingUnits>
               <stageRouteId xsi:nil="true"/>
               <startByDate xsi:nil="true"/>
               <status>Released</status>
               <statusReasonCode xsi:nil="true"/>
               <subtotal1 xsi:nil="true"/>
               <subtotal2 xsi:nil="true"/>
               <subtotal3 xsi:nil="true"/>
               <subtotal4 xsi:nil="true"/>
               <taxAmount1 xsi:nil="true"/>
               <taxAmount2 xsi:nil="true"/>
               <taxAmount3 xsi:nil="true"/>
               <taxAmount4 xsi:nil="true"/>
               <taxAmount5 xsi:nil="true"/>
               <taxBasis1 xsi:nil="true"/>
               <taxBasis2 xsi:nil="true"/>
               <taxBasis3 xsi:nil="true"/>
               <taxBasis4 xsi:nil="true"/>
               <taxBasis5 xsi:nil="true"/>
               <taxRate1 xsi:nil="true"/>
               <taxRate2 xsi:nil="true"/>
               <taxRate3 xsi:nil="true"/>
               <taxRate4 xsi:nil="true"/>
               <taxRate5 xsi:nil="true"/>
               <timeZoneName>Europe/London</timeZoneName>
               <toSiteId xsi:nil="true"/>
               <tod xsi:nil="true"/>
               <todPlace xsi:nil="true"/>
               <town xsi:nil="true"/>
               <traxUseHubAddr>N</traxUseHubAddr>
               <userDefChk1>N</userDefChk1>
               <userDefChk2>N</userDefChk2>
               <userDefChk3>N</userDefChk3>
               <userDefChk4>N</userDefChk4>
               <userDefDate1 xsi:nil="true"/>
               <userDefDate2 xsi:nil="true"/>
               <userDefDate3 xsi:nil="true"/>
               <userDefDate4 xsi:nil="true"/>
               <userDefNote1 xsi:nil="true"/>
               <userDefNote2 xsi:nil="true"/>
               <userDefNum1 xsi:nil="true"/>
               <userDefNum2 xsi:nil="true"/>
               <userDefNum3 xsi:nil="true"/>
               <userDefNum4 xsi:nil="true"/>
               <userDefType1 xsi:nil="true"/>
               <userDefType2 xsi:nil="true"/>
               <userDefType3 xsi:nil="true"/>
               '||ORD_TYPE_1(ORD_TYPE)||'
               <userDefType8 xsi:nil="true"/>
               <vatNumber xsi:nil="true"/>
               <volCtrLabelFormat xsi:nil="true"/>
               <volPckSsccLabel>N</volPckSsccLabel>
               <webServiceAllocClean>N</webServiceAllocClean>
               <webServiceAllocImmed>N</webServiceAllocImmed>
               <workGroup xsi:nil="true"/>
               <workOrderType xsi:nil="true"/>
            </value>
         </arrayOfMergeGeneratedOrderHeader_2>
      </ns0:mergeOrderHeaders>
   </ns0:Payload>
</ns0:MnSDocument>';

Insert into dcsdba.web_services_log (Key, identifier, start_dstamp, session_id, payload, success, reference_id)

VALUES (dcsdba.WEB_SERVICES_LOG_PK_SEQ.nextVal, 'I177', systimestamp, NVL(USERENV('SESSIONID'),0),

ORDH_CLOB, 'N', dcsdba.WEB_SERVICES_LOG_PK_SEQ.nextVal);

ORDH_COUNT := ORDH_COUNT + 1;

END IF;

END LOOP;

/****************************************  Trigger from Web Services log  ********************************************************/

FOR KeyRow In KeySearch LOOP
    
        IF KeyRow.interface_id = 'I016' THEN
            mands.Lib_Interface.CreateSku(KeyRow.key, 
                                    Result,
                                    KeyRow.interface_time);  
                                                                              
         ELSIF KeyRow.interface_id = 'I177' THEN
            mands.Lib_Interface.CreateOrder(KeyRow.key,
                                      Result,
                                      KeyRow.interface_time);  
                                             
         ELSIF KeyRow.interface_id = 'I1003' THEN
            mands.Lib_Interface.CreateUPI(KeyRow.key,
                                    Result,
                                    KeyRow.interface_time
                                    );        
                                                                    
         ELSIF (KeyRow.interface_id = 'I005' OR KeyRow.interface_id = 'I035') THEN
            mands.Lib_Interface.CreateAddress(KeyRow.key,
                                        Result,
                                        KeyRow.interface_time
                                        );   
                                        
         ELSIF (KeyRow.interface_id = 'I0351') THEN
            mands.Lib_Interface.CreateCons(KeyRow.key,
                                        Result,
                                        KeyRow.interface_time
                                        );   
                                        
         ELSIF (KeyRow.interface_id = 'I180') THEN
            mands.Lib_Interface.CreatePreAdvice(KeyRow.key,
                                        Result,
                                        KeyRow.interface_time
                                        );                                           
        END IF;       
    END LOOP;
END;


/
spool off