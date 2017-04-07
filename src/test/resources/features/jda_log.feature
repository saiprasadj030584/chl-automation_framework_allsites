@login
Feature: Allocate the product for the SKU
  As a store agent
  I want to login
  So that I am able to allocate the product

  @complete
  Scenario: To check the order allocation
    Given user is in JDA Dispatcher Food application
    When User is in Order header and executes the order no
    And User select the SKU line
    And User Allocates the product
    Then Validate the status in Order Header screen

  Scenario: Allocate the order
    Given I have logged in as store agent in JDA dispatcher food application
    When I navigate to order header
    And I search order number
    And I select the SKU line
    And I allocate the product
    Then the order status should updated as "Allocated"
