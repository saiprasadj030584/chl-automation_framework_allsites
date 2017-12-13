@Unload_URN_from_trailer
Feature: Outbound order
  As a warehouse user
  I want to load a trailer
  So that I can unload that trailer

 @yes @outbound_despatch @flatpack @idt @unique_flatpack_outbound_order_till_despatch_idt_order_split_shipment_and_multiple_vehicle_single_order @ds
  Scenario: Split shipment and multiple vehicle,single order
    Given the order id of type "IDT" with "Flatpack" skus should be in "Released" status
    And I create a consignment for order
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
    When I navigate to scheduler program page
    And I run the program
    And I perform split picking for hanging non retail
    Then the order should be Ready to Load
    And I create "2" trailer to receive at the dock door
    And I create multiple dock booking at site "5649"
    And I proceed for boxed vehicle loading for multiple bookings of split pick
    Then Trailer should be loaded
    When I navigate to Trailer Shipping page
    Then Multiple trailer should be shipped
