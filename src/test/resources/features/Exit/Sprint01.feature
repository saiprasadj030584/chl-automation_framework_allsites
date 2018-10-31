
@Sprint01 @Master_data_setup
Feature: Master_data_setup
	As a Exit DC user should be able to login
   so that I validate the  master data setup

@SP01 @Master_DATA @Find_the_Site_Siteup
Scenario: Site_Setup
Given I Launch and login the JDA application as Exit DC User 
	And I Go to Data-GENERAL-SETUP-SITE & Click
	And Quering it using SiteID and Executing
Then  I validate the required fields


