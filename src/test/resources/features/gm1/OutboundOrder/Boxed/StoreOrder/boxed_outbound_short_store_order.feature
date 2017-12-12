@boxed_outbound_order_till_despatch_store_order
Feature: Boxed - Outbound Order till despatch - Store Order
  As a warehouse user
  I want to order till dispatch

   @jenkinrun @jenkinsA @jenkinsgm @jenkins14 @unique_boxed_outbound_order_till_despatch_store_order_short_store_order_full_cancel @boxed @store_order @outbound_order_till_despatch @complete @ds
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
    
  @unique_boxed_outbound_order_till_despatch_store_order_short_store_order_partial_cancel  @store_order @outbound_order_till_despatch  @ds
  Scenario: Short store order - partial cancel
   # Given I have logged in as warehouse user in JDA dispatcher GM application
    And the order of type "Retail" with "Boxed" skus should be in "Released" status before partial allocation
    When I create a consignment for single order
    When I navigate to system allocation page
    And I allocate the stocks
    And the status should be turned as "Allocated" in order header
    When I navigate to mannual clustering screen
    And I proceed with clustering
    When I navigate to scheduler program page
    And I run the program
    And I perform picking for single order of type boxed
    Then I verify the status as "In Progress" in order header
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear
    And I proceed for boxed vehicle loading
    Then I verify the status as "In Progress" in order header
    When I navigate to Trailer Shipping page
    Then I proceed for trailer shipping
    Then I verify the status as "In Progress" in order header
    When I navigate to Order mangagement
    And update the status as cancelled
    Then I verify the status as "Cancelled" in order header
