@Sprint01 @Master_data_setup
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

  @SP01 @Master_DATA @TC02_Ensure_the_USER_id_tagged_to_right_SITE
  Scenario Outline: Ensure the USER id tagged to right SITE
    Given Login to JDA Dispatcher web screen
    And Go to Data-GENERAL-SETUP-SITE & Click
    And Click on Query
    And click execute
    Then Verify the SiteID "<SiteID>" displayed is tagged

    Examples: 
      | SiteID |
      |   5542 |

  @SP01 @Master_DATA @TC03_Find_the_Location_setup
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

  @SP01 @Master_DATA @TC05_Find_the_Site_ID_in_Address_table
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

  @SP01 @Master_DATA @TC06_Find_the_Address_in_Address_table
  Scenario Outline: Find the Address load in Address table
    Given Login to JDA Dispatcher web screen
    And Go to Data-GENERAL_SETUP_Address & Click
    And Click on Query
    And click execute
    Then Verify the Address ID "<AddressID>" displayed

    Examples: 
      | AddressID |
      |      0010 |

  @SP01 @Master_DATA @TC07_Find_the_SKU_LOAD
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

  @SP01 @USER_ACCESS @TC08_Screen_USER_group_check
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

  @SP01 @USER_ACCESS @TC09_USER_GROUP_FA_test
  Scenario: User group FA test
    Given Login to JDA Dispatcher web screen
    And Go to Admin>ACCESS CNT>USER GROUP FUNCTION ACCESS & Click
    And Verify whether the access

  @SP01 @USER_ACCESS @TC10_WORKSTATION_FA_TEST
  Scenario: WORKSTATION_FA_TEST
    Given Login to JDA Dispatcher web screen
    And Go to Admin>ACCESS CNT>Workstation access control & Click
    And Verify whether the webaccess

  @SP01 @Pre-receiving @TC11_Verify_T_Dept_of_SKU
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
      
    @SP01 @Pre-receiving @TC12_Verify_stroke_details_Stroke_Category
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
      
      
   @SP01 @Pre-receiving @TC13_Verify_commodity_code_of_a_SKU  
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
    
    @SP01 @Pre-receiving @TC14_Verify_Packed_weight_of_a_SKU  
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
   
   @SP01 @Pre-receiving @TC15_Verify_the_supplier_declaration_certificate_expiry_date  
   Scenario Outline: To verify the supplier declaration certificate expiry date
   Given Login to JDA Dispatcher web screen
    And Go to Data-SKU-SupplierSKU & Click
    And Click on Query
    And Specify the SKU "<SKU>"
    And click execute
    And Verify the Delivery lead time in future date
    

    Examples: 
      | SKU                |
      | 000000000021071852 |     
      
      
      
    
