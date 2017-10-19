@inventory_stock_check
Feature: Report generation
  As a warehouse user
  I want to validate stock in inventory

  @boxed_inventory_inventory_validate_the_stock_available_in_any_location_check_empty_and_low_volume_slots @boxed @inventory @complete @ds @jenkins1 @no_ds
  Scenario: Validate the stock available in any location.Check Empty and Low Volume Slots
    Given I have logged in as warehouse user in JDA dispatcher GM application
    When I navigate to stock check list generation page
    And I select 'Generate by inventory'
    And I enter total quantity in miscelloneus tab
    Then the available list should be displayed
   When I select the record from the available list
    Then the record should be added in the selected list
    And I proceed to next tab
    Then I should see the confirmation for number of items checked
    When I proceed to generate the stock check list
    Then I should see the created list

  @boxed_inventory_inventory_validate_whether_report_is_generated_based_on_stock_accuracy_by_location @boxed @inventory @complete @ds @jenkins1 @no_ds
  Scenario: Validate whether report is generated based on Stock Accuracy by Location
    Given I have logged in as warehouse user in JDA dispatcher GM application
    When I navigate to report selection page
    And I select print to screen and I search for the stock
    And I enter the siteID
    Then the report should be generated for stock in inventory
