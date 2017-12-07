@Unload_URN_from_trailer
Feature: Outbound order
  As a warehouse user
  I want to load a trailer
  So that I can unload that trailer

  @outbound_despatch @boxed @retail @unique_boxed_outbound_order_till_despatch_retail_type_order_split_shipment_and_multiple_vehicle_different_order @ds
  Scenario: Split shipment and multiple vehicle,different order
    Given the multiple order id of type "Retail" with "Boxed" skus should be in "Released" status
    And I create a consignment for multiple order
    When I navigate to system allocation page
    And I enter multiple OrderID for allocation
    Then Allocation should be updated for multiple order
    When I navigate to scheduler program page
    And I run the program
    And I perform split picking for multiple order of type boxed retail
    Then the order should be Ready to Load
    And I create multiple trailer to receive at the dock door
    And I create multiple dock booking at site "5649"
    And I proceed for boxed vehicle loading for multiple bookings of split pick
    Then Trailer should be loaded
    When I navigate to Trailer Shipping page
    Then Multiple trailer should be shipped