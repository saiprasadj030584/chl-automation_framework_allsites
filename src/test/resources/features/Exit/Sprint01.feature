@Sprint01 @Master_data_setup
Feature: Master_data_setup
  As a Exit DC user should be able to login
   so that I validate the  master data setup

  @SP01 @Master_DATA @TC01_Find_the_Site_Siteup
  Scenario Outline: Site_Setup
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
  Scenario: Ensure_user_id
    Given Login to JDA Dispatcher web screen
    And Go to Data-GENERAL-SETUP-SITE & Click
    And Click on Query
    #And Ensure the SiteID displayed is tagged

  @SP01 @Master_DATA @TC03_Find_the_Location_setup
  Scenario: Location_setup
    Given Login to JDA Dispatcher web screen
    And Go to Data-LOCATION-Location & Click
    And Click on Query
    And click execute
    
  @SP01 @Master_DATA @TC04_Find_the_LocationZone_setup
  Scenario: Location_setup
    Given Login to JDA Dispatcher web screen
    And Go to Data-LOCATION-LocationZone & Click
    And Click on Query
    And click execute  
    
   @SP01 @Master_DATA @TC05_Find_the_Site_ID_in_Address_table
   Scenario Outline: Address_load_in_address_table
   Given Login to JDA Dispatcher web screen
    And Go to Data-GENERAL_SETUP_Address & Click
    And Click on Query
    And Specify the SITE ID in Addresstable "<SiteID>"
    And click execute 
    Examples: 
      | SiteID |
      |   5542 |
    
    @SP01 @Master_DATA @TC06_Find_the_Address_in_Address_table
   Scenario: Address_load_in_address_table
   Given Login to JDA Dispatcher web screen
    And Go to Data-GENERAL_SETUP_Address & Click
    And Click on Query
    And click execute 
    
   @SP01 @Master_DATA @TC07_Find_the_SKU_LOAD
   Scenario Outline: SKU_Load
   Given Login to JDA Dispatcher web screen
    And Go to Data-SKU-SKU & Click
    And Click on Query
    And Specify the SKU "<SKU>"
    And click execute  
    Then Verify whether the required fields been populated "<SKU>" in SKU table
     Examples: 
      | SKU |
      | 000000000021071852 |
    
  
    
  
    
