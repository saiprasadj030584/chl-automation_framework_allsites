@goh_retail_outbound_order_till_despatch
Feature: GOH - Retail - Outbound Order till despatch
  As a warehouse user
  I want to load a trailer
  So that I can unload that trailer

  @outbound_order_till_despatch @boxed @retail @unique_goh_outbound_order_till_despatch_order_incorrect_pallet_id_keyin_when_unloading @complete @ds
  Scenario: Incorrect pallet id keyin when unloading
    Given the OrderID of type "Retail" for sku "GOH" should be in "Released" status at site
    Given the order id of type "Retail" with "GOH" skus should be in "Released" status
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then Allocation should be updated
    And I perform picking for GOH
    Then the order should be Ready to Load
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear
    And I proceed for vehicle loading
    Then Trailer should be loaded
    When I proceed for vehicle unload
    Then Trailer should display as Invalid Pallet ID

  @outbound_order_till_despatch @goh @retail @unique_goh_outbound_order_till_despatch_order_unload_urn_from_the_trailer @complete @ds
  Scenario: Unload URN from trailer
    Given the OrderID of type "Retail" for sku "GOH" should be in "Released" status at site
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then Allocation should be updated
    And I perform picking for GOH
    Then the order should be Ready to Load
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear
    And I proceed for vehicle loading
    Then Trailer should be loaded
    When I proceed for GOH vehicle unloading
    Then vehicle should be unload

  @outbound_order_till_despatch @boxed @retail @unique_goh_outbound_order_till_despatch_order_trailer_not_entered_when_unloading @complete @ds
  Scenario: Trailer not entered when unloading
    Given the OrderID of type "Retail" for sku "GOH" should be in "Released" status at site
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then Allocation should be updated
    And I perform picking for GOH
    Then the order should be Ready to Load
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear
    And I proceed for vehicle loading
    Then Trailer should be loaded
    When I proceed for vehicle unload
    Then Trailer should enter page displayed
    

    @outbound_despatch @goh @retail @unique_goh_outbound_order_till_despatch_retail_type_order_shipment_and_multiple_vehicle_single_order @ds
  Scenario: Shipment , single vehicle,different store,same x dock dc,different order,same upc and dept
     Given the multiple order id of type "Retail" with "GOH" skus should be in "Released" status
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

    @outbound_order_till_despatch @goh @retail @goh_outbound_retail_order_till_despatch_order_multiple_pallet_in_the_single_trailer_when_unloading @ds @complete 
  Scenario: Multiple pallet in the single trailer when unloading
    Given the multiple order id of type "Retail" with "GOH" skus should be in "Released" status
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
    When I proceed for "GOH" vehicle unloading with multiple order
    Then vehicle should be unload for multiple order

