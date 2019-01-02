@Exit_Dispatch @Sprint04
Feature: Orders_Dispatch
  As a user in Exit DC
  Order should be Auto Allocated
  so that I can Pick and Dispatch
  
  
  @completed @Reports @TC35_Validate_Load_Hazardous_Report
  Scenario: Validate Load Hazardous Report
    Given Login to JDA Dispatcher web screen
    And Go to Reports Selection and click
    Then Select Print to screen and proceed next
    And Search for "M&S DGN- Report"
    And Verify that the record is displayed for Dangerous Goods
    Then Enter Trailer number
    And Validate the confirmation page for Dangerous Goods
    Then Validate the report selection page for Dangerous Goods
    
    @Trailer_Maintenance @TC36_Create_Trailer_id
    Scenario: Create_Trailer_id
    Given Login to JDA Dispatcher web screen
    And I navigate to Trailer mainteinance page
    And Right click to Select Toggle Maintenance Mode
    When I click on Add button
    Then Enter Trailer number
    And Select Trailer Type
    And click execute
    And validate the record is saved
    
    @Trailer_Maintenance @TC037_Validate_consignment_Trailer_linking
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
    
    