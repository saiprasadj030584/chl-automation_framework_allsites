@Sprint04_consignmentLinking
Feature: ConsignmentLinking
	As a Exit DC user should be able to login
   so that I validate repacking
   with ConsignmentLinking
   
@completed @ConsignmentLinking @TC32_Validate_the_container_report
  Scenario: Validate the Container Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "Container Report"
    And Verify that the record is displayed for Container Report or M&S - Short Invoice for Container Report
    Then Enter Trailer number
    Then Validate the confirmation page for Container Report or M&S - Short Invoice for Container Report
    Then Validate the report selection page for Container Report or M&S - Short Invoice for Container Report completed
    
    @completed @ConsignmentLinking @TC35_Validate_Load_Hazardous_report
  Scenario: Validate Load Hazardous Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S DGN- Report"
    And Verify that the record is displayed for Dangerous Goods
    Then Enter Trailer number
    And Validate the confirmation page for Dangerous Goods
    Then Validate the report selection page for Dangerous Goods
 
 		@completed @ConsignmentLinking @TC60_RED_Report_creation
  Scenario: To Verify RED Report creation
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for the M&S Red Report
    And Verify that the record is displayed for Red Report
    Then Validate the confirmation page for Red Report
    And Proceed next to Output tab for the report
    Then Validate the report selection page for Red Report creation
    
    @inprogress @ConsignmentLinking @TC61_Validate_black_stock_adjustment
    Scenario: To Validate Black stock adjustment
    Given Login to JDA Dispatcher web screen
		And Take a sku having stock in BLACK area
		Then Navigate to Stock Adjustment Screen
		And Query with sku id and tag id in BLACK area
		Then Decrease the stock form the sku - quantity in hand
		When Verified in Inventory and ITL
		Then Stock is validated successfully
		 		
 		