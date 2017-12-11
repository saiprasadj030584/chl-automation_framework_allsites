@Developing
Feature: XDock
  As a warehouse user
  I want to perform Xdock of stocks




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
    
    @unique_outbound_order_till_despatch @boxed @retail @unique_hanging_outbound_order_till_despatch_idt_order_shipment_single_vehicle_different_store_same_x_dock_dc_different_order_same_upc_and_dept @complete @ds
  Scenario: Trailer not entered when unloading
    Given the OrderID of type "Idt" for sku "Hanging" should be in "Released" status at site
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
    
     @unique_outbound_order_till_despatch @GOH @retail @unique_goh_outbound_order_till_despatch_idt_order_shipment_single_vehicle_different_store_same_x_dock_dc_different_order_same_upc_and_dept @complete @ds
  Scenario: Trailer not entered when unloading
    Given the OrderID of type "Idt" for sku "GOH" should be in "Released" status at site
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
    
     @unique_outbound_order_till_despatch @flatpack @idt @unique_flatpack_outbound_order_till_despatch_idt_order_shipment_single_vehicle_different_store_same_x_dock_dc_different_order_same_upc_and_dept @complete @ds
  Scenario: Trailer not entered when unloading
    Given the OrderID of type "IDT" for sku "Flatpack" should be in "Released" status at site
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
    
     
    