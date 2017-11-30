@hanging_outbound_order_till_despatch_store_order
Feature: Hanging - Outbound Order till despatch - Store Order
  As a warehouse user
  I want to order till dispatch

  @jenkinsB @hanging_outbound_order_till_despatch_store_order_short_store_order_full_cancel @hanging @store_order @outbound_order_till_despatch @complete @ds
  Scenario: Short store order - full cancel
    Given I have logged in as warehouse user in JDA dispatcher GM application
    And the order id of type "Retail" with "Hanging" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    And the status should be turned as "Allocated" in order header
    When I navigate to move task management
    And update the status in move task
    And I delete the order
    When I navigate to Order mangagement
    And update the status as cancelled
    Then verify the status as "Released --> Cancelled" in ITL

  @hanging_outbound_order_till_despatch_store_order_store_order_with_pick_discrepancy_e_g_order_for_10_pick_and_despatch_8 @hanging @store_order @outbound_order_till_despatch @ds  @pick_discrepencies_grp
  Scenario: Store order with pick discrepancy
    Given I have logged in as warehouse user in JDA dispatcher GM application
    Given the order id of type "Retail" with "Hanging" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    And the status should be turned as "Allocated" in order header
    And update the status in move task
    When I have logged in as warehouse user in putty
    And I select user directed option in main menu
    And I select container picking
    And I perform picking for hanging discrepancy
    Then I verify the status as "Ready to Load" in order header
