@login
Feature: Allocate the product for the SKU
  As a warehouse user
  I want to allocate the product
  So that I can disptach the orders

  @complete
  Scenario: Allocate the order
    Given I have logged in as store agent in JDA dispatcher food application
    When I navigate to order header
    And I navigate to orderline for the order number "6600033481"
    And I select the SKU line
    And I allocate the product
    Then the order status should be updated as "Allocated"
