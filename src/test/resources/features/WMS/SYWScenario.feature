@SYW
Feature: SYW Scenario Automation
	I want to use this template to automate SYW funtionality.
	
 		#input param 1 -UPC
    #input param 2 - Perf indicator
    #input param 3 - Store id
    # use T82 T18 T13 T54 T96 T97 for Brands wholesale scenarios
    # use T83 T19 T58 T94 T55 T66 for Brandand Consignment scenarios
  	#for Non Brands scenarios use other T dept.
@SYW_Checking_for_Brands_and_NonBrands
Scenario: SYW Automation
Given Login to terminal
	And Go to Main menu
	And I perform SYW functionality for the UPC,perf indicator and store id as "5045601524666","N","9999"
	
	
	