@BY_WMS @Receiving_Functionality
Feature: BY WMS Receiving Functionality
  This Feature is to enable various scenarios
  from which a user can chose to perform tests
  specific for Receiving across all sites.

  #Instructions to Use the Pack:
  #CSV file location  		- C:\Automation_supporting_files\SkuData
  #Link Files location		- C:\Automation_supporting_files\LnkFiles\Load_Data
  #Change the constants with sit-* values in the config.yml file.
  #IMPORTANT - MAKE SURE THE FILE WITH FILE NAME (BOTH .lnk AND .sql FILES) ARE PRESENT IN THE Link files path
  #RECEIVING TEST PACK
  #TEST SCENARIOS:
  #site input																- NDC/RDC/Hemel/XDock
  #BY_WMS_All_Load_to_SkuData								- Load the data from CSV file to SKU_DATA Table.
  #BY_WMS_All_Load_to_SkuData_to_WMS 				- Load the data from CSV to SKU_DATA and then interface them to WMS by running the link file.
  #BY_WMS_All_Login_to_BlindReceiving				- Login to terminal and complete the blid receiving for the given Site, Pallet and UPC.
  #BY_WMS_All_Login_to_UPIReceiving					- Login to terminal and perform upireceiving with the input site and pallet.
  #BY_WMS_All_Login_to_GS-128Receiving			- Login to Terminal and perform GS-128 Receiving for Heml by giving pallet ID as input
  #BY_WMS_All_Login_to_Pre-Advice_Receiving - Login to Terminal and perform Pre-advice Receiving by giving site and pallet/BEL as input.
  @Receiving @BY_WMS_All_Load_to_SkuData
  Scenario: load the required inbound data to Sku data table
    Given Load Data to Sku_Data Table From CSV

  @Receiving @BY_WMS_All_Load_to_SkuData_to_WMS
  Scenario: load to Sku data and further to WMS data base
    Given Load Data to Sku_Data Table From CSV
    And Load Data to WMS by executing script "I1003_Transfer"

  @Receiving @BY_WMS_All_Login_to_BlindReceiving
  Scenario: Login until BlindReceiving
    Given Login to terminal
    And Go to Main menu
    And Do Blind Receiving with URN and UPC "5665","56650009997790024310006012200200","05802036"
    And Do Data Validation after Receive

  @Receiving @BY_WMS_All_Login_to_BlindReceiving_Pallet
  Scenario: Login until BlindReceiving
    Given Login to terminal
    And Go to Main menu
    And Do Blind Receiving for pallet with URN "7401","740100010000000077010020092300200"
    And Do Data Validation after Receive

  @Receiving @BY_WMS_All_Login_to_BlindReceiving_Pallet_testInputs
  Scenario Outline: Login until BlindReceiving
    Given Login to terminal
    And Go to Main menu
    And Do Blind Receiving for pallet with URN "5649","56650009997560068610010122100200"
    And Do Data Validation after Receive

    Examples: 
      | 5542 |   077095099940200133072100000000310 |
      | 5585 | 07709509994020013307215670000000310 |

  @Receiving @BY_WMS_All_Login_to_BlindReceiving_ASN
  Scenario: Login until BlindReceiving
    Given Login to terminal
    And Go to Main menu
    And Do Blind Receiving for ASN "5665","0000100000"
    And Do Data Validation after Receive

  @Receiving @BY_WMS_All_Login_to_UPIReceiving
  Scenario: Login until UPIReceiving
    Given Login to terminal
    And Go to Main menu
    And Do UPI Receiving with Pallet "7401","4297950100001005665057100000600210"
    And Do Data Validation after Receive

  @Receiving @BY_WMS_All_Login_to_GS-128Receiving
  Scenario: Login until GS128Receiving
    Given Login to terminal
    And Go to Main menu
    And Do GS128 Receiving with Pallet "81969509976870471606699997400210"
    And Do Data Validation after Receive

  #input1-pallet ID
  #input2-random 8 digits to pallet ID
  #user client visibility must be M+S
  @Receiving @BY_WMS_All_Login_to_GS-128Receiving_with_EAN
  Scenario: Login until GS128Receiving
    Given Login to terminal
    And Go to Main menu
    And Do GS128 Receiving with pallet_ID "81969509976870471606699997400210" and To_pallet "P9166666"
    And Do Data Validation after Receive

  @Receiving @BY_WMS_All_Login_to_Pre-Advice_Receiving_Direct
  Scenario: Login until pre-advice Receiving Direct
    Given Login to terminal
    And Go to Main menu
    And Do PreAdvice Receiving with Pallet or BEL "5542","00051456103715349290"
    And Do Data Validation after Receive

  @Receiving @BY_WMS_All_Login_to_Pre-Advice_Receiving_FSV
  Scenario: Login until pre-advice Receiving FSV
    Given Login to terminal
    And Go to Main menu
    And Do PreAdvice Receiving with Pallet and BEL "<site_id>","56102506256","74017505941080004600149410800100","02000460995634600019"

  #Parameters
  #site id
  #URRN
  #UPC or TRL
  #The below feature will perform RMS Receive for Single Line in URRN
  @Receiving @BY_WMS_All_Login_to_RMS
  Scenario: Login until BlindReceiving
    Given Login to terminal
    And Go to Main menu
    And Do RMS with URN and UPC "<site_id>","<URRN>","<UPC>"
    And Do Data Validation after Receive

  #Parameters
  #site id
  #URRN
  #The below feature will perform RMS Receive for All Lines in URRN
  @Receiving @BY_WMS_All_Login_to_RMS_Multi
  Scenario: Login until BlindReceiving
    Given Login to terminal
    And Go to Main menu
    And Do RMS with URN "<site_id>","<URRN>"
    And Do Data Validation after Receive

  @Receiving @BY_WMA_OVERRECEIPT_ALL_login_to_Blindreceiving_RMS
  Scenario: Login until BlindReceiving
    Given Login to terminal
    And Go to Main menu
    And Do Blind Receiving with over receipt URN and Qnty "5665","56650009997020055010025082100200","1"
    And Do Data Validation after Receive

  @Receiving @BY_WMS_UPC_NOT_IN_URRN_All_Login_to_BlindReceiving_RMS
  Scenario: Login until BlindReceiving
    Given Login to terminal
    And Go to Main menu
    And Do Blind Receiving with upc not in urn URN and UPCnotinURN "5665","56650009997030055010025082100200","100731487"

  #And Do Data Validation after Receive for upcnotinurn
  @Receiving @BY_WMS_All_Login_to_RMS_BlindReceiving_Stock_Adjustment
  Scenario: Login until BlindReceiving
    Given Login to terminal
    And Go to Main menu
    And Do Stock Adjustment with UPC , Quantity and Store "04759522","3","0010","5665"
