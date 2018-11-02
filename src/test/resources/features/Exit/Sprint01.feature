
@Sprint01 @Master_data_setup
Feature: Master_data_setup
	As a Exit DC user should be able to login
   so that I validate the  master data setup

@SP01 @Master_DATA @Find_the_Site_Siteup
Scenario Outline: Site_Setup
  Given Login to JDA Dispatcher web screen 
	And Go to Data-GENERAL-SETUP-SITE & Click
	And Click on Query
	And Specify the SITE ID "<SiteID>"
	And click execute
  Then Verify whether the required fields been populated "<SiteID>"


 Examples: 
      | SiteID | 
      | 5542   |
      
      
