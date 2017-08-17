@inventory_stock_generation
Feature: Report generation
  As a warehouse user
  I want to generate the report for the stock in inventory

  @stock_list_generation_low_and_empty @boxed @inventory
  Scenario: Validate the stock available in any location. Check Empty and Low Volume Slots
    Given I have logged in as warehouse user in JDA dispatcher GM application
    When I navigate to stock check list Generation page
    And I select mode of list generation as 'Generate by inventory'
    And I enter the total quantity on miscelloneus tab
    Then the available list should be displayed
    When I select the record from the available list
    Then the record should be added in the selected list
    And I proceed to next tab
    Then I should see the confirmation for number of items checked
    When I proceed to generate the stock check list
    Then I should see the created list
