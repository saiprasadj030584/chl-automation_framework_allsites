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
    
    @ConsignmentLinking @TC34_Negative_Path_container_and_consignment
    Scenario: Negative Path_container and consignment
    Given I login as warehouse user in putty
    And I select user directed option in main menu
    And I select vehicle loading option in main menu
    And I select vehicle load option
    And I enter dock door "1015"
    And validate the error message is displayed
    
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
    
    @completed @Trailer_Maintenance @TC36_Create_Trailer_id
    Scenario: Create_Trailer_id
    Given Login to JDA Dispatcher web screen
    And I navigate to Trailer mainteinance page
    And Right click to Select Toggle Maintenance Mode
    When I click on Add button
    Then Enter Trailer number
    And Select Trailer Type
    And click execute
    And validate the record is saved
    
   @completed @Trailer_Maintenance @TC037_Validate_consignment_Trailer_linking
  Scenario: Validate consignment Trailer linking
    Given Login to JDA Dispatcher web screen
    And Go to consignment maintainance
    And Right click to Select Toggle Maintenance Mode
    When I click on Add button
    And Enter consignment name
    And Select consignment Status
    And click execute
    And Select Mode of transport
    And click execute
    And validate the record is saved
    And Go to consignment drop maintainance screen
    And Right click to Select Toggle Maintenance Mode
    And I click on Add button
    And Enter consignment
    And Enter chamber and Address Id
    Then click execute
    And validate the record is saved
    And I navigate to Trailer mainteinance page
    And Right click to Select Toggle Maintenance Mode
    And I click on Add button
    And Enter Trailer number
    And Select Trailer Type
    And click execute
    And validate the record is saved
    And Go to Consignment Trailer Linking
    And Select Trailer
    And Select Consignment
    And Click next
    And I click on trailer Add button
    And validate Consignment Trailer is linked
    
     @completed @ConsignmentLinking @TC38_Validate_Proforma_Invoice_report
  Scenario: Validate Proforma_Invoice_report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "Proforma Invoice - by Order ID"
    And Verify that the record is displayed for Proforma Invoice
    Then Enter order number "1015176558"
    And Validate the confirmation page for Proforma Invoice
    Then Validate the report selection page for Proforma Invoice
    
    @ConsignmentLinking @TC39_Check_that_the_User_Groups_have_been_set_up_with_the_required_access_for_the_RDTs
    Scenario: Check that the User Groups have been set up with the required acceses for the RDTs
    Given Login to JDA Dispatcher web screen
    And Go to Admin>ACCESS CNT>USER GROUP FUNCTION ACCESS & Click
    And Select a User Group from the Group dropdown box
    And Verify whether the access is valid
    
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
		 		
 		