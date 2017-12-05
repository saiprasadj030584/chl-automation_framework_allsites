@boxed_retail_outbound_order_till_despatch
Feature: Boxed - Retail - Outbound Order till despatch
  As a warehouse user
  I want to perform split shipment

@outbound_order_till_despatch @boxed @retail @boxed_outbound_retail_order_till_despatch_order_split_shipment_multiple_vehicle_diff_order @ds 
  Scenario: Split shipment and multiple vehicle different order
    Given the multiple order id of type "Retail" with "Boxed" skus should be in "Released" status
    When I create a consignment for multiple order
    When I navigate to system allocation page
    And I enter multiple OrderID for allocation
    Then Allocation should be updated for multiple order
    When I navigate to mannual clustering screen
    And I proceed with clustering
    When I navigate to scheduler program page
    And I run the program
    And I perform picking for multiple order
    Then the order should be Ready to Load
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear
    And I proceed for vehicle loading with multiple order
    Then Trailer should be loaded for multiple order
    When I navigate to Trailer Shipping page
    Then trailer should be shipped