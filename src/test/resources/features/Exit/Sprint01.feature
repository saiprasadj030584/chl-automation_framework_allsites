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
      | BLACKB |
    
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
      | 5542 |
    
   @SP01 @Master_DATA @TC06_Find_the_Address_in_Address_table
   Scenario Outline: Find the Address load in Address table
   Given Login to JDA Dispatcher web screen
    And Go to Data-GENERAL_SETUP_Address & Click
    And Click on Query
    And click execute 
    Then Verify the Address ID "<AddressID>" displayed 
    Examples: 
      | AddressID |
      | 0010 |
    
   @SP01 @Master_DATA @TC07_Find_the_SKU_LOAD
   Scenario Outline: Find the SKU LOAD
   Given Login to JDA Dispatcher web screen
    And Go to Data-SKU-SKU & Click
    And Click on Query
    And Specify the SKU "<SKU>"
    And click execute  
    Then Verify whether the required fields been populated "<SKU>" in SKU table
     Examples: 
      | SKU |
      | 000000000021071852 |
    
  
    
  
    
