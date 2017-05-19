@vehicle_loading
Feature: Vehicle loading for stock transfer order
  As a warehouse user
  I want to perform vehicle loading
  So that I can ship the orders

  @sara_wip
  Scenario Outline: Perform vehicle load using putty
    Given I have logged in as warehouse user in JDA dispatcher food application
    When I navigate to Order Header Page
    And I search using Order id "<OrderId>"
    Then Order status should be "Ready to Load"
    When I navigate to move task and get all the Pallet IDs
    And I login as warehouse user in Putty
    And I select user directed option in main menu
    And I navigate to Load Menu
    And I perform vehicle loading for all the pallets
    Then I should see the order status as "Complete" in Order Header page
    When I navigate to Move task page
    Then no record should exists for the "<OrderId>"

    Examples: 
      | OrderId    |
      | 8050004519 |
