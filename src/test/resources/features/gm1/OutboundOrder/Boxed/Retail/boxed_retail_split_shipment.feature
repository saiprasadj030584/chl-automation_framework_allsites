@Unload_URN_from_trailer
Feature: Outbound order
  As a warehouse user
  I want to load a trailer
  So that I can unload that trailer

  @outbound_despatch @boxed @retail @boxed_outbound_despatch_retail_split_shipment_and_multiple_vehicle_single_order
  Scenario: Split shipment and multiple vehicle,single order
    Given the order id of type "Retail" with "Boxed" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
    When I navigate to scheduler program page
    And I run the program
    And I perform picking for boxed
    #And I perform split picking for boxed
    Then the order should be Ready to Load
    And I create multiple trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site "5649"
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear
    And I proceed for boxed vehicle loading
    Then Trailer should be loaded
