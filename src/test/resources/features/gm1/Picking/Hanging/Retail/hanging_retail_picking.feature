@hanging_retail_picking
Feature: Hanging - Retail - Picking
  As a warehouse user
  I want to pick the allocated stocks

  @hanging @jenkinsgm  @jenkinsgm1 @retail @picking @unique_hanging_picking_retail_validate_keying_wrong_upc @complete @ds @jenkinsB
  Scenario: Validate keying wrong UPC
    Given the order of "Retail" should be in "Released" status in order header maintenance
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then the order should be allocated
    When I enter the ivalid UPC for hanging
    Then the error message should be displayed as invalid details


   @hanging @hanging_picking_picking_remove_stock_check_putaway_picking_relocate_&_replenishment_v2 @inventory @complete  @ds
    Scenario: Validate quantity field in the stock check screen to be restricted so that there is no risk of entering a UPC as a quantity
  
  	Given the order id of type "Retail" with "Hanging" skus should be in "Released" status
     When I navigate to system allocation page
    And I allocate the stocks
    When I navigate to scheduler program page
    And I run the program
    Then Navigate to order to check order is allocated
    And I check clustering
    When I have logged in as warehouse user in putty
    And I select user directed option in main menu
    And I select container picking
    Then I perform picking for hanging discrepancy for stock check
    When I navigate to stock check list generation page
    And I select 'Generate by inventory'
    And I enter the tag ID as on inventory tab for location 
    Then the available list should be displayed
    When I select the record from the available list
    Then the record should be added in the selected list
    And I proceed to next tab
    Then I should see the confirmation for number of items checked
    When I proceed to generate the stock check list
    Then I should see the created list
    And I get the list id
    And I have to perform stock check for discrepancy
    Then The inventory should be in unlocked status