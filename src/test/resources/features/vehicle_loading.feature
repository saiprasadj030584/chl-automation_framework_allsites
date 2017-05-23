@vehicle_loading
Feature: Vehicle loading for stock transfer order
  As a warehouse user
  I want to perform vehicle loading
  So that I can ship the orders

  @sara_wip
  Scenario Outline: Vehicle load for stock transfer order
    Given the order "<OrderId>" should be "Ready to Load" status
    When I get the pallet ids from the move task
    Then the pallet id should be displayed
    When I login as warehouse user in putty
    And I select user directed option in main menu
    And I navigate to load menu
    And I perform vehicle loading for all the pallets
    Then the vehicle loading should be completed
    And the order status should be "Complete" in order header
    And no record should exist for the Order ID
    And the inventory transaction should be generated for vehicle load

    Examples: 
      | OrderId    |
      | 6646215737 |
