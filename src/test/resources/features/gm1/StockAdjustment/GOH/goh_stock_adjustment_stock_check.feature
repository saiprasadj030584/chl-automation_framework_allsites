@goh_inventory_stock_check
Feature: GOH - Stock Check Positive Proof
  As a warehouse user
  I want to validate stock in inventory

  @jenkinsC @goh @stock_check @stock_adjustment @goh_stock_adjustment_stock_check_positive_proof_of_stock_check @complete @ds @jenkins1 @no_ds @jenkinssc
  Scenario: Positive proof of stock check
    Given I have logged in as warehouse user in JDA dispatcher GM application
    When I navigate to stock check list generation page
    And I select 'Generate by inventory'
    And I enter the tag ID as on inventory tab for site id for "GOH" 
    Then the available list should be displayed
    When I select the record from the available list
    Then the record should be added in the selected list
    And I proceed to next tab
    Then I should see the confirmation for number of items checked
    When I proceed to generate the stock check list
    Then I should see the created list
    When I navigate to stock check query page
    And I get the list id
    When I navigate to stock check list completion page
    And I enter the list id and update the quantity
    Then I should see the confirmation for number of items generated
    And the inventory should be generated
