@Sprint01
Feature: Master_data_setup
  As a Exit DC user should be able to login
   so that I validate the  master data setup

  @SP01 @Master_DATA @TC01_Find_the_Site_Setup
  Scenario Outline: Find the site setup
    Given Login to JDA Dispatcher web screen
    And Go to Data-GENERAL-SETUP-SITE & Click
    And Click on Query
    And Specify the SITE ID "<SiteID>"
    And click execute
    Then Verify whether the required fields been populated "<SiteID>"

    Examples: 
      | SiteID |
      |   5542 |

  @Master_DATA @TC02_Ensure_the_USER_id_tagged_to_right_SITE
  Scenario Outline: Ensure the USER id tagged to right SITE
    Given Login to JDA Dispatcher web screen
    And Go to Data-GENERAL-SETUP-SITE & Click
    And Click on Query
    And click execute
    Then Verify the SiteID "<SiteID>" displayed is tagged

    Examples: 
      | SiteID |
      |   5542 |

  @Master_DATA @TC03_Find_the_Location_setup
  Scenario Outline: Find the Location setup
    Given Login to JDA Dispatcher web screen
    And Go to Data-LOCATION-Location & Click
    And Click on Query
    And click execute
    Then Verify the Locations "<Location>" displayed

    Examples: 
      | Location |
      | 4624ROAD |

  @SP01 @Master_DATA @TC04_Find_the_LocationZone_setup
  Scenario Outline: Find the Location ZONE setup
    Given Login to JDA Dispatcher web screen
    And Go to Data-LOCATION-LocationZone & Click
    And Click on Query
    And click execute
    Then Verify the LocationZone "<LocationZone>" displayed

    Examples: 
      | LocationZone |
      | BLACKB       |

  @Master_DATA @TC05_Find_the_Site_ID_in_Address_table
  Scenario Outline: Find the site load in Address table
    Given Login to JDA Dispatcher web screen
    And Go to Data-GENERAL_SETUP_Address & Click
    And Click on Query
    And Specify the SITE ID in Addresstable "<SiteID>"
    And click execute
    Then Verify the Address ID "<SiteID>" displayed

    Examples: 
      | SiteID |
      |   5542 |

  @Master_DATA @TC06_Find_the_Address_in_Address_table
  Scenario Outline: Find the Address load in Address table
    Given Login to JDA Dispatcher web screen
    And Go to Data-GENERAL_SETUP_Address & Click
    And Click on Query
    And click execute
    Then Verify the Address ID "<AddressID>" displayed

    Examples: 
      | AddressID |
      |      0010 |

  @Master_DATA @TC07_Find_the_SKU_LOAD
  Scenario Outline: Find the SKU LOAD
    Given Login to JDA Dispatcher web screen
    And Go to Data-SKU-SKU & Click
    And Click on Query
    And Specify the SKU "<SKU>"
    And click execute
    Then Verify whether the required fields been populated "<SKU>" in SKU table

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @USER_ACCESS @TC08_Screen_USER_group_check
  Scenario Outline: Screen user group check
    Given Login to JDA Dispatcher web screen
    And Go to Admin-User-UserGroup & click
    And Click on Query
    And Specify the UserGroup "<UserGroup>"
    And click execute
    Then Verify whether the User-group been populated "<UserGroup>" in the table

    Examples: 
      | UserGroup  |
      | SCREEN-INT |

  @USER_ACCESS @TC09_USER_GROUP_FA_test
  Scenario: User group FA test
    Given Login to JDA Dispatcher web screen
    And Go to Admin>ACCESS CNT>USER GROUP FUNCTION ACCESS & Click
    And Verify whether the access

  @USER_ACCESS @TC10_WORKSTATION_FA_TEST
  Scenario: WORKSTATION_FA_TEST
    Given Login to JDA Dispatcher web screen
    And Go to Admin>ACCESS CNT>Workstation access control & Click
    And Verify whether the webaccess

  @Pre_receiving @TC11_Verify_T_Dept_of_SKU
  Scenario Outline: To Verify T-Dept of SKU
    Given Login to JDA Dispatcher web screen
    And Go to Data-SKU-SKUmaintenance & Click
    And Click on Query
    And Specify the SKU "<SKU>"
    And click execute
    And verify the T-Dept "<SKU>"

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Pre_receiving @TC12_Verify_stroke_details_Stroke_Category
  Scenario Outline: To Verify stroke details -Stroke Category
    Given Login to JDA Dispatcher web screen
    And Go to Data-SKU-SKUmaintenance & Click
    And Click on Query
    And Specify the SKU "<SKU>"
    And click execute
    And verify the Stroke "<SKU>"

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Pre_receiving @TC13_Verify_commodity_code_of_a_SKU
  Scenario Outline: To verify the commonidty code of a sku
    Given Login to JDA Dispatcher web screen
    And Go to Data-SKU-SKUmaintenance & Click
    And Click on Query
    And Specify the SKU "<SKU>"
    And click execute
    And verify the Commoditycode "<SKU>"

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @SP01 @Pre_receiving @TC14_Verify_Packed_weight_of_a_SKU
  Scenario Outline: To verify the packed weight of a sku
    Given Login to JDA Dispatcher web screen
    And Go to Data-SKU-SKUmaintenance & Click
    And Click on Query
    And Specify the SKU "<SKU>"
    And click execute
    And verify the Packedweight "<SKU>"
    And Validate the packedweight is in given range "<SKU>"

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Pre_receiving @TC15_Verify_the_supplier_declaration_certificate_expiry_date
  Scenario Outline: To verify the supplier declaration certificate expiry date
    Given Login to JDA Dispatcher web screen
    And Go to Data-SKU-SupplierSKU & Click
    And Click on Query
    And Specify the SKU "<SKU>"
    And click execute
    Then Verify the Delivery lead time in future date

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Pre_receiving @TC16_Verify_the_supplier_record
  Scenario Outline: To verify the supplier record
    Given Login to JDA Dispatcher web screen
    And Go to Data-SKU-SupplierSKU & Click
    And Click on Query
    And Specify the SKU "<SKU>"
    And click execute
    Then Verify the Suppliersku record is available "<SKU>"

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Pre_receiving @TC17_Verify_SKU_Merge_rules
  Scenario Outline: To verify the sku merge rules
    Given Login to JDA Dispatcher web screen
    And Go to Admin_Setup_Scheduler_Schedulerprograms & Click
    And Click on Query
    And Specify the Program name "<Program_name>"
    And click execute
    And Run the program
    And Go to Data-SKU-SKUmaintenance & Click
    And Click on Query
    And Specify the SKU "<SKU>"
    And click execute
    Then Verify the fields in SKU "<SKU>"

    Examples: 
      | SKU                | Program_name        |
      | 000000000021071852 | SKUVALIDATIONCHECKP |

  @Pre_receiving @TC18_Verify_wholesale_price
  Scenario Outline: To verify the wholesaler_price
    Given Login to JDA Dispatcher web screen
    And Go to Data-order_orderline & Click
    And Click on Query
    And Specify the SKU in orderline "<SKU>"
    And click execute
    Then Verify the Wholesalerprice in orderline

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Pre_receiving @TC19_Verify_country_of_origin_of_a_product
  Scenario Outline: To verify the country of origin for a product
    Given Login to JDA Dispatcher web screen
    And Go to Data-SKU-SKUmaintenance & Click
    And Click on Query
    And Specify the SKU "<SKU>"
    And click execute
    And Verify the country of origin "<SKU>"

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Pre_receiving @TC20_Verify_Direct_PO_loading_in_JDA_Dispatcher
  Scenario: To verify the Direct PO loading in JDA dispatcher
    Given Insert Pre-advice data with PO type "DIRECT"
    And Insert UPI data and Delivery data
    And Login to JDA Dispatcher web screen
    Then Verify ASN in Delivery screen
    Then Verify data in UPI Receipt header screen
    Then Verify PO type in Pre Advice header screen

  @Pre_receiving @TC21_Verify_FSV_PO_loading_in_JDA_Dispatcher
  Scenario: To verify the Direct PO loading in JDA dispatcher
    Given Insert Pre-advice data with PO type "DIRECT"
    And Login to JDA Dispatcher web screen
    Then Verify PreAdvice header loaded successfully
    Then Verify Supplier is populated in the Pre-advice header table
    Then Verify the Type  is populated as "PO"
    Then Verify PreAdvice line loaded successfully
    Then Verify quantity and advice number is loaded in Pre-Advice line table

  @Pre_receiving @TC22_Verify_the_ASN_Booking
  Scenario: To verify ASN Booking
    Given Insert Pre-advice data with PO type "DIRECT"
    And Insert UPI data and Delivery data
    And Login to JDA Dispatcher web screen
    Then Verify data in UPI Receipt header screen
    Then Verify ASN ID for the PalletID
    Then Verify Export criteria for ASN details

  @Pre_receiving @TC23_Verify_URN_data_is_available_in_dispatcher
  Scenario: To verify ASN data
    Given Insert Pre-advice data with PO type "DIRECT"
    And Insert UPI data and Delivery data
    And Login to JDA Dispatcher web screen
    Then Verify data in UPI Receipt header screen
    Then Verify pallet id

  @Pre_receiving @TC24_Verify_the_shipment_description
  Scenario Outline: To Verify Shipment description
    Given Login to JDA Dispatcher web screen
    And Go to Data-SKU-SKUmaintenance & Click
    And Click on Query
    And Specify the SKU "<SKU>"
    And click execute
    When I go to user-defined tab
    Then I should be able to verify the description-article

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Pre_receiving @TC25_Verify_the_composition_description
  Scenario Outline: To Verify composition description
    Given Login to JDA Dispatcher web screen
    And Go to Data-SKU-SKUmaintenance & Click
    And Click on Query
    And Specify the SKU "<SKU>"
    And click execute
    When I go to user-defined tab
    Then I should be able to verify the description-composition

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Pre_receiving @TC26_Verify_the_Knit_Woven_indicator
  Scenario Outline: To Verify Knit/Woven indicator
    Given Login to JDA Dispatcher web screen
    And Go to Data-SKU-SKUmaintenance & Click
    And Click on Query
    And Specify the SKU "<SKU>"
    And click execute
    When I go to user-defined tab
    Then I should be able to verify the Knit/Woven indicator

    Examples: 
      | SKU                |
      | 000000000021071852 |

  #@Pre_receiving @TC27_Verify_the_pre_advice_checks_report
  #the required report M&S- Pre Receive SKU master data check is not available anymore
  
  @Pre_receiving @TC28_Verify_the_address_or_site_information
  Scenario: To Verify the address or site information
    Given Login to JDA Dispatcher web screen
    And I navigate to address maintenance page
    When I query, execute and process further
    Then Verify address and site details are loaded into address screen
    
   #@Pre_receiving @TC29_Verify_the_RED_Stock_PO_status
   #Needs Receiving. Would be able to cover with compliance check cases in SPRINT02 

  @Pre_receiving @TC30_Verify_Pallet_Consignment_and_Trailer_details_in_the_system
  Scenario: To verify pallet consignment and trailer details in the system
    Given Insert Pre-advice data with PO type "DIRECT"
    And Insert UPI data and Delivery data
    And Login to JDA Dispatcher web screen
    Then Verify data in UPI Receipt header screen
    Then Verify ASN ID for the PalletID
    Then Navigate to consignment details page
    Then Verify Trailer content in Delivery screen

  @Pre_receiving @TC31_Verify_missing_URN_report
  Scenario Outline: To Verify Missing URN report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for the M&S Identify URN
    And Verify that the record is displayed for Missing Urn
    Then Proceed next and enter the required value of "<SKU>"
    Then Validate the confirmation page
    And Proceed next to Output tab for the report
    Then Validate the report selection page for Identify URN completion

    Examples: 
      | SKU                |
      | 000000000021071852 |
     
     #@Pre_receiving @TC32_Verify_new_stock_check_screen
     #Code change inthe Putty which needs clarification from Dev team
   
  @Pre_receiving @TC33_Verify_INT_URN_label_reprint
  Scenario: To Verify International URN label reprint
    Given Insert Pre-advice data with PO type "DIRECT"
    And Insert UPI data and Delivery data
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for the M&S INT Reprint Label
    And Verify that the record is displayed for International Urn
    Then Proceed next and enter the required value of pallet
    Then Validate the confirmation page for International Urn
    And Proceed next to Output tab for the report
    Then Validate the report selection page for URN international reprint completion
    
    @Pre_receiving @TC34_Verify_Trusted_receiving_data
    Scenario: To verify Trusted receiving data using MANDS SCHEMA
    
    

  @Pre_receiving @TC35_Verify_URN_loaded_into_Dispatcher
  Scenario: To Verify  URN loaded into dispatcher
    Given Insert Pre-advice data with PO type "DIRECT"
    And Insert UPI data and Delivery data
    And Login to JDA Dispatcher web screen
    Then Verify data in UPI Receipt header screen
    Then Click on lines
    And URN lines are validated successfully

  @Pre_receiving @TC36_Verify_factory_code_for_a_supplier
  Scenario Outline: To verify factory code for a supplier
    Given Login to JDA Dispatcher web screen
    And Go to Data-SKU-SupplierSKU & Click
    And Click on Query
    And Query with Supplier_ID "<SKU>"
    And click execute
    Then Verify factory code for a supplier

    Examples: 
      | SKU                |
      | 000000000021071852 |

  @Pre_receiving @TC37_Verify_ASN_Closure
  Scenario: To verify ASN closure
    Given Insert Pre-advice data with PO type "DIRECT"
    And Insert UPI data and Delivery data
    And Login to JDA Dispatcher web screen
    Then Verify the status of ASN in Delivery screen
    #Then Status should be completed

  @Pre_receiving @TC38_Verify_Pre_advice_check_merge_rule
  Scenario: To verify the pre-advice merge rules
    Given Insert Pre-advice data with PO type "DIRECT"
    Then Login to JDA Dispatcher web screen
    Then Verify PreAdvice header loaded successfully
    Then Verify Supplier is populated in the Pre-advice header table
    Then Verify the Type  is populated as "PO"
    Then Verify PreAdvice line loaded successfully
  
  @Pre_receiving @TC39_Verify_the_packConfig_for_the_sku
  Scenario Outline: To Find a Pack Config
    Given Login to JDA Dispatcher web screen
    And I am on pack config maintenance page
    And Execute for verifying the fields
    Then Verify tag volume and tracking levels is auto-populated
    Then Verify pack config is "<pack config>"

    Examples: 
      | pack config |
      | GENERIC     |

  @Pre_receiving @TC40_Verify_stroke_details_garment_type
  Scenario Outline: To Verify stroke details -Garment type
    Given Login to JDA Dispatcher web screen
    And Go to Data-SKU-SKUmaintenance & Click
    And Click on Query
    And Specify the SKU "<SKU>"
    And click execute
    When I go to user-defined tab
    Then Verify stroke details Garment type for SKU "<SKU>"

    Examples: 
      | SKU                |
      | 000000000022479902 |

  @Pre_receiving @TC41_Verify_the_supplier_mismatch_against_a_product
  Scenario Outline: To verify the supplier mismatch against a product
    Given Login to JDA Dispatcher web screen
    And Go to Data-SKU-SupplierSKU & Click
    And Click on Query
    And Specify the SKU "<SKU>"
    And click execute
    Then Verify the Suppliersku record is available "<SKU>"
    And Go to Data-SKU-SKU & Click
    And Click on Query
    And Specify the SKU "<SKU>"
    And click execute
    Then Verify Supplier SKU from SKU table against Supplier SKU table "<SKU>"

    Examples: 
      | SKU                |
      | 000000000021071852 |

      
      #@Pre_receiving @TC42_Verify_UPC_held_with_RED_stock
      #Needs Receiving. Would be able to cover with compliance check cases in SPRINT02
      
      #@Pre_receiving @TC43_Verify_VAT_indicator_for_a-product
      #Not applicable as Field is removed from the EXIT
      
      #@Pre_receiving @TC44_Verify_the_certificate_of_the_stock
      #Not applicable as Field is removed from the EXIT