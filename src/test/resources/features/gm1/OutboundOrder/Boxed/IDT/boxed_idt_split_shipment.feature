@Split_Shipment
Feature: Outbound order
  As a warehouse user
  I want to load a trailer
  So that I can unload that trailer

 @yes @outbound_despatch @boxed @idt @unique_boxed_outbound_order_till_despatch_idt_order_split_shipment_and_multiple_vehicle_single_order @ds
  Scenario: Split shipment and multiple vehicle,single order
    Given the multiple order id of type "Retail" with "Boxed" skus should be in "Released" status
    And I allocate the stocks using consignment in system allocation page
    Then the multiple stocks should get allocated
    When I navigate to scheduler program page
    And I run the program
    And I perform picking for boxed
    Then the order should be Ready to Load
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear
    And I proceed for boxed vehicle loading
    Then Trailer should be loaded
    When I navigate to Trailer Shipping page
    Then trailer should be shipped



@unique_outbound_order_till_despatch @boxed @retail @unique_boxed_outbound_order_till_despatch_idt_order_shipment_single_vehicle_different_store_same_x_dock_dc_different_order_same_upc_and_dept @complete @ds
  Scenario: Trailer not entered when unloading
    Given the OrderID of type "Idt" for sku "Boxed" should be in "Released" status at site
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then Allocation should be updated
    When I navigate to scheduler program page
    And I run the program
    And I perform picking for boxed
    Then the order should be Ready to Load
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear
    And I proceed for boxed vehicle loading
    Then Trailer should be loaded
    When I proceed for vehicle unload
    Then Trailer should enter page displayed
    