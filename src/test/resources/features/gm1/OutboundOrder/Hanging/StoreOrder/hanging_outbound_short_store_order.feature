@hanging_outbound_order_till_despatch_store_order
Feature: Hanging - Outbound Order till despatch - Store Order
  As a warehouse user
  I want to order till dispatch

  @jenkinsC @hanging_outbound_order_till_despatch_store_order_short_store_order_full_cancel @hanging @store_order @outbound_order_till_despatch @complete @ds
  Scenario: Short store order - full cancel
    Given I have logged in as warehouse user in JDA dispatcher GM application
    And the OrderID of type "Retail" for sku "Hanging" should be in "Released" status at site
    When I navigate to system allocation page
    And I allocate the stocks
    And the status should be turned as "Allocated" in order header
    When I navigate to move task management
    And update the status in move task
    And I delete the order
    When I navigate to Order mangagement
    And update the status as cancelled
    Then verify the status as "Released --> Cancelled" in ITL

  @hanging_outbound_order_till_despatch_store_order_pick_discrepancy @hanging @store_order @outbound_order_till_despatch @complete
  Scenario Outline: Store order with pick discrepancy
    Given I have logged in as warehouse user in JDA dispatcher GM application
    And the OrderID of type "Retail" for sku "Hanging" should be in "Released" status at site
    When I navigate to system allocation page
    And I allocate the stocks
    Then the status should be turned as "Allocated" in order header
    And update the status in move task
    When I have logged in as warehouse user in putty
    And I select user directed option in main menu
    And I select container picking
    And I perform picking for discrepancy
    And I should be directed to pickent page
    Then I verify the status as "In Progress" in order header

    Examples: 
      | OrderId    |
      | 4764303869 |
