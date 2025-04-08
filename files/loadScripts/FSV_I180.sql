spool Fsvlog.txt







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
P_ADV_1 Number;
P_ADV_2 Number;
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
select TO_NUMBER(max(user_def_type_2)) A from interface_upi_receipt_line where REGEXP_LIKE(user_def_type_2, '^[[:digit:]]+$')
union all
select TO_NUMBER(max(PRE_ADVICE_ID)) A from PRE_ADVICE_HEADER where USER_DEF_TYPE_1 IS NOT NULL and REGEXP_LIKE(PRE_ADVICE_ID, '^[[:digit:]]+$')
union all
select TO_NUMBER(max(PRE_ADVICE_ID)) A from INTERFACE_PRE_ADVICE_HEADER where USER_DEF_TYPE_1 IS NOT NULL and REGEXP_LIKE(PRE_ADVICE_ID, '^[[:digit:]]+$') );

select max (A) INTO V_ODN_REF from (
select TO_NUMBER(max(substr((user_def_type_1),1,10))) A from upi_receipt_line where REGEXP_LIKE(user_def_type_1, '^[[:digit:]]+$')
union all
select TO_NUMBER(max(substr((user_def_type_1),1,10))) A from interface_upi_receipt_line where REGEXP_LIKE(user_def_type_1, '^[[:digit:]]+$') );

select max (A) INTO V_ASN from (
select TO_NUMBER(max(substr((user_def_type_5),6,5))) A from upi_receipt_line where REGEXP_LIKE(user_def_type_5, '^[[:digit:]]+$')
union all
select TO_NUMBER(max(substr((user_def_type_5),6,5))) A from interface_upi_receipt_line where REGEXP_LIKE(user_def_type_5, '^[[:digit:]]+$') );

select max (A) INTO V_MAS from (
select TO_NUMBER(max(substr((user_def_note_1),6,10))) A from upi_receipt_line where user_def_note_1 <> user_def_note_2 and REGEXP_LIKE(pallet_id, '^[[:digit:]]+$')
union all
select TO_NUMBER(max(substr((user_def_note_1),6,10))) A from interface_upi_receipt_line where user_def_note_1 <> user_def_note_2 and REGEXP_LIKE(pallet_id, '^[[:digit:]]+$') );

/************************************** PRE ADVICE LINE ******************************************/

DBMS_OUTPUT.PUT_LINE( 'PRE_ADV_ID                 '|| 'SKU_ID                ' || 'UPC              ' || 'QTY_ORDERED        ' || 'URN       ' || 'BEL       '  );

FOR PREL IN PRESEARCH
LOOP

V_STO_1 := TO_NUMBER(V_STO) + TO_NUMBER(PREL.STO);
P_ADV_1 := TO_NUMBER(P_ADV) + TO_NUMBER(PREL.STO);
P_ADV_2 := P_ADV_1 + 100000;
SELECT SUPPLIER_SKU_ID INTO V_SUPP_SKU FROM SUPPLIER_SKU WHERE SKU_ID = PREL.SKU_ID and ROWNUM= 1;
SELECT SUBSTR(SUPPLIER_ID,2,5) INTO V_SUPP FROM SUPPLIER_SKU WHERE SKU_ID = PREL.SKU_ID and supplier_id = PREL.source_id and ROWNUM= 1;
SELECT PRODUCT_GROUP INTO V_PROD FROM SKU WHERE SKU_ID = PREL.SKU_ID and ROWNUM= 1;
SELECT USER_DEF_TYPE_8 INTO V_MOD FROM SKU WHERE SKU_ID = PREL.SKU_ID and ROWNUM= 1;
SELECT USER_DEF_TYPE_1 INTO V_STROKE FROM SKU WHERE SKU_ID = PREL.SKU_ID and ROWNUM= 1;

PREL_CLOB := '<?xml version="1.0" encoding="UTF-8"?><!--Output from DOMD Version 1.9 for XSLT Transformation for I0180(Line) in WMS04 Adapter --><mns:MnSDocument xmlns:mns="http://www.marksandspencer.com/common/envelope/v2" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
   <mns:Header>
      <EnvelopeVersion>2.0</EnvelopeVersion>
      <Language>en-GB</Language>
      <MessageId>af11bbb1028e11e8cc3c00001a761958</MessageId>
      <CorrelationId/>
      <CreatedTimestamp>'||V_DATE||'</CreatedTimestamp>
      <SourceApplicationName>SAP</SourceApplicationName>
      <InterfaceId>I180</InterfaceId>
      <InterfaceName>Purchase Order to Legacy Systems</InterfaceName>
      <SecurityClassification>Internal Use</SecurityClassification>
      <PayloadName>Purchase Order</PayloadName>
      <PayloadKey/>
      <BatchMessageCount>1</BatchMessageCount>
      <BatchRecordCount>7</BatchRecordCount>
      <BatchRecordSum>750|</BatchRecordSum>
      <ns0:Filter xmlns:ns0="http://www.marksandspencer.com/common/envelope/v2">
         <Level1>5542</Level1>
      </ns0:Filter>
      <ETAG/>
   </mns:Header>
   <mns:Payload>
      <mns:MergeGeneratedPreAdviceLine>
            <PreAdviceLine>
            <batchId xsi:nil="true"/>
            <ceConsignmentId xsi:nil="true"/>
            <ceCoo xsi:nil="true"/>
            <ceInvoiceNumber xsi:nil="true"/>
            <ceLink xsi:nil="true"/>
            <ceUnderBond xsi:nil="true"/>
            <clientGroup>NDC</clientGroup>
            <clientId>M+S</clientId>
            <conditionId xsi:nil="true"/>
            <configId xsi:nil="true"/>
            <disallowMergeRules>N</disallowMergeRules>
            <expectedGrossWeight xsi:nil="true"/>
            <expectedNetWeight xsi:nil="true"/>
            <expiryDstamp xsi:nil="true"/>
            <hostLineId xsi:nil="true"/>
            <hostPreAdviceId xsi:nil="true"/>
            <lineId>'||PREL.LINE_ID||'</lineId>
            <lockCode>@</lockCode>
            <manufDstamp xsi:nil="true"/>
            <mergeAction>A</mergeAction>
            <nlsCalendar xsi:nil="true"/>
            <notes xsi:nil="true"/>
            <originId xsi:nil="true"/>
            <ownerId>M+S</ownerId>
            <palletConfig xsi:nil="true"/>
            <preAdviceId>'||V_STO_1||'</preAdviceId>
            <productCurrency xsi:nil="true"/>
            <productPrice xsi:nil="true"/>
            <qtyDue>'||PREL.QTY_DUE||'.000000'||'</qtyDue>
            <qtyDueTolerance xsi:nil="true"/>
            <samplingType xsi:nil="true"/>
            <sapPlant xsi:nil="true"/>
            <sapStoreLoc xsi:nil="true"/>
            <serialValidMerge>N</serialValidMerge>
            <skuId>'||PREL.SKU_ID||'</skuId>
            <specCode xsi:nil="true"/>
            <tagId xsi:nil="true"/>
            <timeZoneName xsi:nil="true"/>
            <trackingLevel>EA</trackingLevel>
            <userDefChk1>@</userDefChk1>
            <userDefChk2>@</userDefChk2>
            <userDefChk3 xsi:nil="true"/>
            <userDefChk4 xsi:nil="true"/>
            <userDefDate1 xsi:nil="true"/>
            <userDefDate2 xsi:nil="true"/>
            <userDefDate3 xsi:nil="true"/>
            <userDefDate4 xsi:nil="true"/>
            <userDefNote1 xsi:nil="true"/>
            <userDefNote2 xsi:nil="true"/>
            <userDefNum1>@</userDefNum1>
            <userDefNum2>@</userDefNum2>
            <userDefNum3>2018.000000</userDefNum3>
            <userDefNum4 xsi:nil="true"/>
            <userDefNum5>2</userDefNum5>
            <userDefType1>'||P_ADV_1||'</userDefType1>
            <userDefType2>'||V_PROD||'</userDefType2>
            <userDefType3>@</userDefType3>
            <userDefType4>@</userDefType4> 
            <userDefType5>'||V_SUPP_SKU||'</userDefType5>
            <userDefType6/> 
            <userDefType7/>
            <userDefType8>'||V_MOD||'</userDefType8>
         </PreAdviceLine>
      </mns:MergeGeneratedPreAdviceLine>
   </mns:Payload>
</mns:MnSDocument>';


Insert into dcsdba.web_services_log (Key, identifier, start_dstamp, session_id, payload, success, reference_id)

VALUES (dcsdba.WEB_SERVICES_LOG_PK_SEQ.nextVal, 'I180', systimestamp, NVL(USERENV('SESSIONID'),0),

PREL_CLOB, 'N', dcsdba.WEB_SERVICES_LOG_PK_SEQ.nextVal);


DBMS_OUTPUT.PUT_LINE( V_STO_1 ||'     '|| PREL.SKU_ID ||'    ' || V_SUPP_SKU ||'    ' || PREL.QTY_DUE ||'    '
|| '7401750'||P_ADV_2||V_SUPP||'0'||SUBSTR(V_PROD,2,2)||P_ADV_1||LPAD(PREL.QTY_DUE,3,'0')||'00' ||
'         '  || '020'||SUBSTR(V_SUPP,2,5)||V_SUPP_SKU||LPAD(PREL.QTY_DUE,4,'0')||'9'  );

END LOOP;

/************************************** PRE ADVICE HEADER ******************************************/

FOR PREH IN PRESEARCH
LOOP

IF PREH.STO = PREH_COUNT THEN

V_STO_1 := V_STO + PREH.STO;
P_ADV_1 := P_ADV + PREH.STO;
SELECT USER_DEF_TYPE_8 INTO V_MOD FROM SKU WHERE SKU_ID = PREH.SKU_ID and ROWNUM= 1;
SELECT PRODUCT_GROUP INTO V_PROD FROM SKU WHERE SKU_ID = PREH.SKU_ID and ROWNUM= 1;

PREH_CLOB:= '<?xml version="1.0" encoding="UTF-8"?><mns:MnSDocument xmlns:mns="http://www.marksandspencer.com/common/envelope/v2" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
   <mns:Header>
      <EnvelopeVersion>2.0</EnvelopeVersion>
      <Language>en-GB</Language>
      <MessageId>cdbbc4f6028e11e8ca7700001a76188e</MessageId>
      <CorrelationId/>
      <CreatedTimestamp>'||V_DATE||'</CreatedTimestamp>
      <SourceApplicationName>SAP</SourceApplicationName>
      <InterfaceId>I180</InterfaceId>
      <InterfaceName>Purchase Order to Legacy Systems</InterfaceName>
      <SecurityClassification>Internal Use</SecurityClassification>
      <PayloadName>Purchase Order</PayloadName>
      <PayloadKey/>
      <BatchMessageCount>1</BatchMessageCount>
      <BatchRecordCount>7</BatchRecordCount>
      <BatchRecordSum>1000|</BatchRecordSum>
      <ns0:Filter xmlns:ns0="http://www.marksandspencer.com/common/envelope/v2">
         <Level1>5542</Level1>
      </ns0:Filter>
      <ETAG/>
   </mns:Header>
   <mns:Payload>
      <mns:MergeGeneratedPreAdviceHeader>
         <PreAdviceHeader>
            <address1 xsi:nil="true"/>
            <address2 xsi:nil="true"/>
            <bookrefId xsi:nil="true"/>
            <carrierName xsi:nil="true"/>
            <carrierReference xsi:nil="true"/>
            <ceConsignmentId xsi:nil="true"/>
            <ceInvoiceNumber xsi:nil="true"/>
            <clientGroup>NDC</clientGroup>
            <clientId>M+S</clientId>
            <collectionReqd>N</collectionReqd>
            <consignment xsi:nil="true"/>
            <contact xsi:nil="true"/>
            <contactEmail xsi:nil="true"/>
            <contactFax xsi:nil="true"/>
            <contactMobile xsi:nil="true"/>
            <contactPhone xsi:nil="true"/>
            <country xsi:nil="true"/>
            <county xsi:nil="true"/>
            <disallowMergeRules>N</disallowMergeRules>
            <disallowReplens>N</disallowReplens>
            <dueDstamp>'||V_DUE||'</dueDstamp>
            <emailConfirm>N</emailConfirm>
            <loadSequence xsi:nil="true"/>
            <masterPreAdvice>N</masterPreAdvice>
            <mergeAction>U</mergeAction>
            <MergeGeneratedPreAdviceLines xsi:nil="true"/>
            <modeOfTransport>SEA</modeOfTransport>
            <name xsi:nil="true"/>
            <nlsCalendar xsi:nil="true"/>
            <notes xsi:nil="true"/>
            <oapRma xsi:nil="true"/>
            <ownerId>M+S</ownerId>
            <postcode xsi:nil="true"/>
            <preAdviceId>'||V_STO_1||'</preAdviceId>
            <preAdviceType>PO</preAdviceType>
            <priority xsi:nil="true"/>
            <returnFlag>N</returnFlag>
            <returnedOrderId xsi:nil="true"/>
            <samplingType xsi:nil="true"/>
            <siteId>'||PREH.HUB_ID||'</siteId>
            <status>Released</status>
            <statusReasonCode xsi:nil="true"/>
            <supplierId>'||PREH.SOURCE_ID||'</supplierId>
            <supplierReference xsi:nil="true"/>
            <timeZoneName>Europe/London</timeZoneName>
            <tod xsi:nil="true"/>
            <todPlace xsi:nil="true"/>
            <town xsi:nil="true"/>
            <userDefChk1>@</userDefChk1>
            <userDefChk2>N</userDefChk2>
            <userDefChk3>N</userDefChk3>
            <userDefChk4>N</userDefChk4>
            <userDefDate1 xsi:nil="true"/>
            <userDefDate2 xsi:nil="true"/>
            <userDefDate3 xsi:nil="true"/>
            <userDefDate4 xsi:nil="true"/>
            <userDefNote1 xsi:nil="true"/>
            <userDefNote2 xsi:nil="true"/>
            <userDefNum1>@</userDefNum1>
            <userDefNum2>@</userDefNum2>
            <userDefNum3 xsi:nil="true"/>
            <userDefNum4 xsi:nil="true"/>
            <userDefType1>'||P_ADV_1||'</userDefType1>
            <userDefType2>'||V_PROD||'</userDefType2>
            <userDefType3>@</userDefType3>
            <userDefType4>@</userDefType4>
            <userDefType5>FSV</userDefType5>
            <userDefType6>'||V_MOD||'</userDefType6>
            <userDefType7 xsi:nil="true"/>
            <userDefType8 xsi:nil="true"/>
            <vatNumber xsi:nil="true"/>
            <yardContainerId xsi:nil="true"/>
            <yardContainerType xsi:nil="true"/>
         </PreAdviceHeader>
      </mns:MergeGeneratedPreAdviceHeader>
   </mns:Payload>
</mns:MnSDocument>';

PREH_COUNT := PREH_COUNT + 1;

Insert into dcsdba.web_services_log (Key, identifier, start_dstamp, session_id, payload, success, reference_id)

VALUES (dcsdba.WEB_SERVICES_LOG_PK_SEQ.nextVal, 'I180', systimestamp, NVL(USERENV('SESSIONID'),0),

PREH_CLOB, 'N', dcsdba.WEB_SERVICES_LOG_PK_SEQ.nextVal);

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