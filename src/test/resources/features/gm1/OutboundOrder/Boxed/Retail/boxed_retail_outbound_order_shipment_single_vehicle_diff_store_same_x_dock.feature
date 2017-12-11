@boxed_retail_outbound_order_till_despatch
Feature: Boxed - Retail - Outbound Order till despatch
  As a warehouse user
  I want to load a trailer
  So that I can ship that trailer
  
  @outbound_order_till_despatch @boxed @retail @boxed_outbound_retail_order_shipment_single_vehicle_different_store_same_x_dock @ds 
  Scenario: Shipment singlr vehicle different store same X dock door
    Given the multiple OrderID of type "Retail" for sku "Boxed" should be in "Released" status at site
    When I create a consignment for multiple order
    When I navigate to system allocation page
    And I enter multiple OrderID for allocation
    Then Allocation should be updated for multiple order
    When I navigate to scheduler program page
    And I run the program
    And I perform picking for multiple order of type boxed
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
    Then Trailer should be shipped