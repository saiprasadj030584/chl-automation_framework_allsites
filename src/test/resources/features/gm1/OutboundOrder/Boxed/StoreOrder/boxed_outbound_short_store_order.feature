@boxed_outbound_order_till_despatch_store_order
Feature: Boxed - Outbound Order till despatch - Store Order
  As a warehouse user
  I want to order till dispatch

  @jenkinsA @unique_boxed_outbound_order_till_despatch_store_order_short_store_order_full_cancel @boxed @store_order @outbound_order_till_despatch @complete @ds
  Scenario: Short store order - full cancel
    Given the order id of type "Retail" with "Boxed" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the order should be allocated
    When I navigate to move task management
    And update the status in move task
    And I delete the order
    When I navigate to Order mangagement
    And update the status as cancelled
    Then verify the status as "Released --> Cancelled" in ITL

  @unique_boxed_outbound_order_till_despatch_store_order_store_order_with_pick_discrepancy_e_g_order_for_10_pick_and_despatch_8 @boxed @store_order @outbound_order_till_despatch @complete @ds
  Scenario: Store order with pick discrepancy
    Given the order id of type "Retail" with "Boxed" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
     Then the order should be allocated
    And update the status in move task
    When I have logged in as warehouse user in putty
    And I select user directed option in main menu
    And I select container picking
    And I perform picking for discrepancy
    And I should be directed to pickent page
    Then I verify the status as "In Progress" in order header
