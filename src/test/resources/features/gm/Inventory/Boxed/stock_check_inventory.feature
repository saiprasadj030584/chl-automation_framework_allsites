@inventory_stock_check
Feature: Report generation
  As a warehouse user
  I want to validate stock in inventory

  @boxed_inventory_inventory_stock_check_low_and_empty_volume @boxed @inventory @complete
  Scenario: Validate the stock available in any location. Check Empty and Low Volume Slots
    #Given I have logged in as warehouse user in JDA dispatcher GM application
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

  @boxed_inventory_inventory_stock_report_generation @boxed @inventory @complete
  Scenario Outline: Validate whether report is generated based on Stock Accuracy by Location
    Given I have logged in as warehouse user in JDA dispatcher GM application
    When I navigate to report selection page
    And I select print to screen and I search for the stock
    And I enter the siteID "<SiteId>"
    Then the report should be generated for stock in inventory

    Examples: 
      | SiteId |
      |   5649 |

  