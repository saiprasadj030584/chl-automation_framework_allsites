@Sprint02_Receiving
Feature: Receiving
  As a Exit DC user should be able to login
   so that I validate the  master data setup
   with Pre-receiving

  @Completed @Receiving @TC01_Batch_and_Expiry_Date_Check
  Scenario Outline: To Check if the Batch and Expiry date are captured in EXIT dispatcher
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SkuId>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick
    And check Qty received is updated in Inventory
    And Check Qty received is updated in Pre-advice line

    Examples: 
      | SkuId              |
      | 000000000021071852 |

  @Completed @Receiving @TC02_Client_ID_Auto_populated
  Scenario: To verify GS128 - Screen - Default Client Auto populated
    Given I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And Verify scan URN screen displayed

  @Completed @Receiving @TC03_Compliance_Check
  Scenario Outline: Compliance Check - Happy path - All the required fields are valid
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SkuId>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date
    And Login to JDA Dispatcher web screen
    #And I navigate to Order header screen to verify the status in Ready to Load
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick
    And check Qty received is updated in Inventory
    And Check Qty received is updated in Pre-advice line
    And Check the Orderline must be allocated

    Examples: 
      | SkuId              |
      | 000000000021071852 |

  @Completed @Receiving @TC04_Validate_Compliance_check_tDept_is_null_or_invalid
  Scenario Outline: To validate Compliance Check - T-Dept is NULL or invalid
    Given The details for the sku "<SkuId>"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then Alter the check weight to make the stock as RED Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code

    Examples: 
      | SkuId              |
      | 000000000021071851 |

  @Completed @Receiving @TC05_Validate_Compliance_check_Stroke_category_is_null_or_invalid
  Scenario Outline: To validate Compliance Check - Stroke Category is NULL or invalid
    Given The details for the sku "<SkuId>"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then Alter the stroke category to make the stock as RED stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code
    Then stroke category is validated as NULL

    Examples: 
      | SkuId              |
      | 000000000021071851 |

  @Completed @Receiving @TC06_Validate_Compliance_check_commodity_code_is_null_or_invalid
  Scenario Outline: To validate Compliance Check - Commodity Code is NULL or invalid
    Given The details for the sku "<SkuId>"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then Alter the commodity Code to make the stock as RED stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code
    Then commodity Code is validated as NULL

    Examples: 
      | SkuId              |
      | 000000000021071851 |

  @Completed @Receiving @TC07_Validate_Compliance_check_check_weight_is_null_or_invalid
  Scenario Outline: To validate Compliance Check - Weight is NULL or less than 0.00 and = 999
    Given The details for the sku "<SkuId>"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then Alter the check weight to make the stock as RED Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code
    Then check weight is validated as null

    Examples: 
      | SkuId              |
      | 000000000021071851 |

  @Completed @Receiving @TC08_Validate_Compliance_supplier_declaration_validity_is_null_or_in_the_past
  Scenario Outline: To validate Compliance Check - Supplier Declaration Validity is NULL or in the past
    Given The details for the sku "<SkuId>"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then Alter the supplier declaration validity to make the stock as RED Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code
    Then Supplier Declaration is validated to be null or in past

    Examples: 
      | SkuId              |
      | 000000000021071851 |

  @Completed @Receiving @TC09_Validate_Compliance_supplier_record_does_not_exist
  Scenario Outline: To validate Compliance Check - Supplier Record does not exist
    Given The details for the sku "<SkuId>"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code
    Then supplier record does not exist

    Examples: 
      | SkuId              |
      | 000000000021071851 |

  @Completed @Receiving @TC10_Direct_receiving_Happy_path_Non_Trusted_Boxed_NonProhibited_inventory
  Scenario Outline: To validate direct receiving happy path non-trusted-non-prohibited inventory
    Given Checking the conditions "Non-Trusted", "Boxed" and "Non-Prohibited" for the sku "<SkuId>" and customerID "7977"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","4624" for Red Stock
    Then Alter the check weight to make the stock as RED Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code

    Examples: 
      | SkuId              |
      | 000000000021071852 |

  @Completed @Receiving @TC11_Direct_receiving_Happy_path_Trusted_Boxed_NonProhibited_inventory
  Scenario Outline: To validate Direct receiving – Happy path – Trusted – Boxed -  NonProhibited inventory
    Given Checking the conditions "Trusted", "Boxed" and "Non-Prohibited" for the sku "<SkuId>" and customerID "4611"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","4624" for "<SkuId>"
    And Login to JDA Dispatcher web screen
    Then Verify PreAdvice header loaded successfully
    Then Verify Supplier is populated in the Pre-advice header table
    Then Verify the Type  is populated as "STO"
    Then Verify PreAdvice line loaded successfully
    Then Verify data in UPI Receipt header screen
    Then Verify ASN ID for the PalletID
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick

    Examples: 
      | SkuId              |
      | 000000000021071852 |

  @complete @Receiving @TC12_Direct_receiving_Happy_path_Non_Trusted_Boxed_Prohibited_inventory
  Scenario Outline: To validate Direct receiving – Happy path – Non Trusted – Boxed – Prohibited inventory
    Given Checking the conditions "Non-Trusted", "Boxed" and "Prohibited" for the sku "<SkuId>" and customerID "7977" and siteID "4624"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then Alter the check weight to make the stock as RED Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code

    Examples: 
      | SkuId              |
      | 000000000021071852 |

  @Complete @Receiving @TC13_Direct_receiving_Happy_path_Trusted_Boxed_Prohibited_inventory
  Scenario Outline: To validate Direct receiving – Happy path – Trusted – Boxed -  Prohibited inventory
    Given Checking the country of origination for sku "<SkuId>"
    Given Checking the conditions "Trusted", "Boxed" and "Prohibited" for the sku "<SkuId>" and customerID "4611"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code for prohibition
    Then Update country for non-prohibition

    Examples: 
      | SkuId              |
      | 000000000021071852 |

  @Complete @Receiving @TC14_Direct_receiving_without_ASN_information_Non_Trusted_Boxed_NonProhibited_inventory
  Scenario Outline: To validate Direct receiving – without ASN information – Non Trusted – Boxed -  NonProhibited inventory
    Given Checking the conditions "Non-Trusted", "Boxed" and "Non-Prohibited" for the sku "<SkuId>" and customerID "7977" and siteID "4624"
    Given Data to be inserted in preadvice header,order header and UPI receipt without ASN "Released","NONRETAIL","5542" for the Red Stock
    Then Alter the check weight to make the stock as RED Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date for hang
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code

    Examples: 
      | SkuId              |
      | 000000000021071852 |

  @Complete @Receiving @TC15_Direct_receiving_Happy_path_Non_Trusted_Boxed_NonProhibited_inventory_Expiry_capture
  Scenario Outline: To validate  Direct receiving – Happy path – Non Trusted – Boxed – NonProhibited inventory – Expiry capture
    Given Checking the conditions "Non-Trusted", "Boxed" and "Non-Prohibited" for the sku "<SkuId>" and customerID "7977" and siteID "4624"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","4624" for Red Stock
    Then Alter the check weight to make the stock as RED Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code

    Examples: 
      | SkuId              |
      | 000000000021071852 |

  @Completed @Receiving @TC16_Direct_receiving_Happy_path_Trusted_Boxed_NonProhibited_inventory_Expiry_capture
  Scenario Outline: To validate Direct receiving – Happy path – Trusted – Boxed -  NonProhibited inventory – Expiry capture
    Given Checking the conditions "Trusted", "Boxed" and "Non-Prohibited" for the sku "<SkuId>" and customerID "4624"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SkuId>"
    And Login to JDA Dispatcher web screen
    Then Verify PreAdvice header loaded successfully
    Then Verify Supplier is populated in the Pre-advice header table
    Then Verify the Type  is populated as "STO"
    Then Verify PreAdvice line loaded successfully
    Then Verify data in UPI Receipt header screen
    Then Verify ASN ID for the PalletID
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date

    Examples: 
      | SkuId              |
      | 000000000021071852 |

  @Complete @Receiving @TC17_Direct_receiving_Happy_path_Non_Trusted_Hanging_NonProhibited_inventory
  Scenario Outline: To validate Direct receiving – Happy path – Non Trusted – Hanging – NonProhibited inventory
    Given Checking the conditions "Non-Trusted", "Hanging" and "Non-Prohibited" for the sku "<SkuId>" and customerID "7977" and siteID "4624"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","4624" for Red Stock
    Then Alter the check weight to make the stock as RED Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code

    Examples: 
      | SkuId              |
      | 000000000021071851 |

  @Complete @Receiving @TC18_Direct_receiving_Happy_path_Trusted_Hanging_NonProhibited_inventory
  Scenario Outline: To validate Direct receiving – Happy path – Trusted – Hanging -  NonProhibited inventory
    Given Checking the conditions "Trusted", "Hanging" and "Non-Prohibited" for the sku "<SkuId>" and customerID "7977"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","4624" for "<SkuId>"
    And Login to JDA Dispatcher web screen
    Then Verify PreAdvice header loaded successfully
    Then Verify Supplier is populated in the Pre-advice header table
    Then Verify the Type  is populated as "STO"
    Then Verify PreAdvice line loaded successfully
    Then Verify data in UPI Receipt header screen
    Then Verify ASN ID for the PalletID
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick

    Examples: 
      | SkuId              |
      | 000000000021071852 |

  @Complete @Receiving @TC19_Direct_receiving_Happy_path_Non_Trusted_Hanging_Prohibited_inventory
  Scenario Outline: To validate Direct receiving – Happy path – Non Trusted – Hanging – Prohibited inventory
    Given Checking the country of origination for sku "<SkuId>"
    Given Checking the conditions "Non-Trusted", "Hanging" and "Prohibited" for the sku "<SkuId>" and customerID "7977" and siteID "4624"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","4624" for Red Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code for prohibition
    Then Update country for non-prohibition

    Examples: 
      | SkuId              |
      | 000000000021071851 |

  @Complete @Receiving @TC20_Direct_receiving_Happy_path_Trusted_Hanging_Prohibited_inventory
  Scenario Outline: To validate Direct receiving – Happy path – Trusted – Hanging -  Prohibited inventory
    Given Checking the country of origination for sku "<SkuId>"
    Given Checking the conditions "Trusted", "Hanging" and "Prohibited" for the sku "<SkuId>" and customerID "7977"
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for Red Stock
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY,Supplier and location for ASN for red stock
    And I navigate to Order header screen to verify the status in Released
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code for prohibition
    Then Update country for non-prohibition

    Examples: 
      | SkuId              |
      | 000000000021071851 |

  @Receiving @TC21_FSV_Receiving_Happy_path_Boxed_Article
  Scenario Outline: To validate FSV Receiving - Happy path –  Boxed Article
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542" for "<SKU>"
    And Login to JDA Dispatcher web screen
    Then Verify PreAdvice header loaded successfully
    Then Verify Supplier is populated in the Pre-advice header table
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier
    And I enter To Pallet
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID for FSV
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Receiving @TC22_FSV_Receiving_Over_receipt_Boxed_Article_Black_Stock_Process
  Scenario Outline: To validate FSV Receiving Over_receipt_Boxed_Article_Black_Stock_Process
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542" for "<SKU>"
    And Login to JDA Dispatcher web screen
    Then Verify PreAdvice header loaded successfully
    Then Verify Supplier is populated in the Pre-advice header table
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for over receipt
    And I enter To Pallet
    And Validate OverReceipt Error

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Receiving @TC23_FSV_Receiving_Unknown_Stock_Boxed_Article_Black_Stock_Process
  Scenario Outline: To validate FSV Receiving Over_receipt_Boxed_Article_Black_Stock_Process
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542" for "<SKU>"
    And Login to JDA Dispatcher web screen
    Then Verify PreAdvice header loaded successfully
    Then Verify Supplier is populated in the Pre-advice header table
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for Unknown
    And Validate Unknown stock Error

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Receiving @TC24_FSV_Receiving_Over_receipt_PO_stock_in_more_than_one_URN_Boxed_Black_Stock_Process
  Scenario Outline: To validate FSV Receiving Over_receipt_Boxed_Article_Black_Stock_Process
    Given Data to be inserted twice in preadvice header and one in order header with "Released","RETAIL","5542" for "<SKU>"
    And Login to JDA Dispatcher web screen
    Then Verify PreAdvice header loaded successfully for Two PO
    Then Verify Supplier is populated in the Pre-advice header table
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier
    And I enter To Pallet for two urn
    And I enter URN and Bel and validation of UPC,QTY and Supplier for two URN
    And I enter To Pallet
    And Validate OverReceipt Error
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID for FSV
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Receiving @TC25_FSV_Receiving_Happy_path_Hanging_Article
  Scenario Outline: To validate FSV Receiving - Happy path –  Hanging Article
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542" for "<SKU>"
    And Login to JDA Dispatcher web screen
    Then Verify PreAdvice header loaded successfully
    Then Verify Supplier is populated in the Pre-advice header table
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier
    And I enter To Pallet
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID for FSV
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick

    Examples: 
      | SKU                |
      | 000000000021071851 |

  @Receiving @TC26_FSV_Receiving_Over_receipt_Hanged_Article_Black_Stock_Process
  Scenario Outline: To validate FSV Receiving Over_receipt_Boxed_Article_Black_Stock_Process
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542" for "<SKU>"
    And Login to JDA Dispatcher web screen
    Then Verify PreAdvice header loaded successfully
    Then Verify Supplier is populated in the Pre-advice header table
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for over receipt
    And I enter To Pallet
    And Validate OverReceipt Error

    Examples: 
      | SKU                |
      | 000000000021071851 |

  @Receiving @TC27_FSV_Receiving_Unknown_Stock_Hanged_Article_Black_Stock_Process
  Scenario Outline: To validate FSV Receiving Over_receipt_Boxed_Article_Black_Stock_Process
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542" for "<SKU>"
    And Login to JDA Dispatcher web screen
    Then Verify PreAdvice header loaded successfully
    Then Verify Supplier is populated in the Pre-advice header table
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for Unknown
    And Validate Unknown stock Error

    Examples: 
      | SKU                |
      | 000000000021071851 |

  @Receiving @TC28_FSV_Receiving_Over_receipt_PO_stock_in_more_than_one_URN_hanged_Black_Stock_Process
  Scenario Outline: To validate FSV Receiving Over_receipt_hanged_Article_Black_Stock_Process
    Given Data to be inserted twice in preadvice header and one in order header with "Released","RETAIL","5542" for "<SKU>"
    And Login to JDA Dispatcher web screen
    Then Verify PreAdvice header loaded successfully for Two PO
    Then Verify Supplier is populated in the Pre-advice header table
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier
    And I enter To Pallet for two urn
    And I enter URN and Bel and validation of UPC,QTY and Supplier for two URN
    And I enter To Pallet
    And Validate OverReceipt Error
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID for FSV
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick

    Examples: 
      | SKU                |
      | 000000000021071851 |

  @Receiving @TC29_FSV_Receiving_Happy_path_Prohibition_check
  Scenario Outline: To validate FSV Receiving - Happy path –  prohibition check
    Given Checking the conditions "Trusted", "Boxed" and "Prohibited" for the sku "<SkuId>" and customerID "4611"
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542" for "<SkuId>"
    And Login to JDA Dispatcher web screen
    Then Verify PreAdvice header loaded successfully
    Then Verify Supplier is populated in the Pre-advice header table
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier
    And I enter To Pallet
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID for FSV
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick

    Examples: 
      | SkuId              |
      | 000000000021071852 |

  @Complete @Receiving @TC30_Prohibition_Check_FSV_CoO_and_Destination_not_allowed
  Scenario Outline: To validate prohibition check FSV and Coo Destination not allowed
    Given Checking the country of origination for sku "<SKU>"
    Given Checking the conditions "Trusted", "Boxed" and "Prohibited" for the sku "<SKU>" and customerID "4611"
    Given Data to be inserted in preadvice header and order header with "Released","RETAIL","5542" for "<SKU>"
    And Login to JDA Dispatcher web screen
    Then Verify PreAdvice header loaded successfully
    Then Verify Supplier is populated in the Pre-advice header table
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier
    And I enter To Pallet for prohibited sku
    Then Validate the message for prohibition
    And Login to JDA Dispatcher web screen
    And check the Inventory Transaction for Receipt, InventoryLock and putaway for the Red lock code
    Then Update country for non-prohibition

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Completed @Receiving @TC031_Location_verification
  Scenario Outline: Find the Location ZONE setup
    Given Login to JDA Dispatcher web screen
    And Go to Data-LOCATION-LocationZone & Click
    And Click on Query
    And Enter the LocationZone "<LocationZone>"
    And click execute
    Then Verify the LocationZone "<LocationZone>" displayed

    Examples: 
      | LocationZone |
      | BLACKB       |
      | REDB         |

  @Completed @Receiving @TC032_Verify_GS1_Receiving_screen
  Scenario: To verify GS1 Receiving screen is displayed
    Given I login as warehouse user in putty
    Then I select user directed option in main menu
    And I select Receiving menu
    
@Completed @Receiving @TC033_Setup_storage_locations
  Scenario Outline: Find the Location ZONE setup
    Given Login to JDA Dispatcher web screen
    And Go to Data-LOCATION-LocationZone & Click
    And Click on Query
    And Enter the LocationZone "<LocationZone>"
    And click execute
    Then Verify the LocationZone "<LocationZone>" displayed

    Examples: 
      | LocationZone |
      | BLACKB       |
      | REDB         |
      
  @Completed @Receiving @TC034_Batch_and_Expiry_Date_Check
  Scenario Outline: To Validate capturing batch and expiry date during receiving
    Given Data to be inserted in preadvice header,order header and UPI receipt with "Released","NONRETAIL","5542" for "<SkuId>"
    Then I login as warehouse user in putty
    And I select user directed option in main menu
    And I select Receiving menu
    And I enter URN and Bel and validation of UPC,QTY and Supplier for ASN with Batch and Expiry date
    And Login to JDA Dispatcher web screen
    And Go to Inventory Transaction & Click
    And Click on Query
    And Enter Container_ID
    And click execute
    And check the Inventory Transaction for Receipt, Allocate and Pick
    And check Qty received is updated in Inventory

    Examples: 
      | SkuId              |
      | 000000000021071852 |

  @Completed @USER_ACCESS @TC036_Screen_USER_group_check_required_by_Hemel
  Scenario Outline: Check that the User Groups required by Hemel have been set up in the User Group table
    Given Login to JDA Dispatcher web screen
    And Go to Admin-User-UserGroup & click
    And Click on Query
    And Specify the UserGroup "<UserGroup>"
    And click execute
    Then Verify whether the User-group been populated "<UserGroup>" in the table

    Examples: 
      | UserGroup  |
      | BASICUSER  |
      | ADVUSER    |
      | SUPERVISOR |
      | STOCKADV   |
      | HEADOFFICE |
      | ZENSAR     |

  @Completed @USER_ACCESS @TC037_USER_GROUP_set_up_with_the_required_acceses
  Scenario: Check that the User Groups have been set up with the required acceses for the Web Browser
    Given Login to JDA Dispatcher web screen
    And Go to Admin>ACCESS CNT>USER GROUP FUNCTION ACCESS & Click
    And Verify whether the access is valid

  @Completed @USER_ACCESS @TC038_USER_GROUP_set_up_with_the_required_acceses_for_the_RDTs
  Scenario: Check that the User Groups have been set up with the required acceses for the RDTs
    Given Login to JDA Dispatcher web screen
    And Go to Admin>ACCESS CNT>USER GROUP FUNCTION ACCESS & Click
    And Select a User Group from the Group dropdown box
    And Verify whether the access is valid
