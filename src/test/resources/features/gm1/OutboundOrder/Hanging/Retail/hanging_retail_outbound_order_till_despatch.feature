@hanging_retail_outbound_order_till_despatch
Feature: Hanging - Retail - Outbound Order till despatch
  As a warehouse user
  I want to load a trailer
  So that I can unload that trailer

  @outbound_order_till_despatch @boxed @retail @hanging_outbound_order_till_despatch_order_incorrect_pallet_id_keyin_when_unloading @complete @ds
  Scenario: Incorrect pallet id keyin when unloading
    Given the OrderID of type "Retail" for sku "Hanging" should be in "Released" status at site
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then Allocation should be updated
    And I perform picking for hanging
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

  @outbound_order_till_despatch @hanging @retail @hanging_outbound_order_till_despatch_order_unload_urn_from_the_trailer @complete @ds
  Scenario: Unload URN from trailer
    Given the OrderID of type "Retail" for sku "Hanging" should be in "Released" status at site
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then Allocation should be updated
    And I perform picking for hanging
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
    When I proceed for vehicle unloading
    Then vehicle should be unload

  @outbound_order_till_despatch @boxed @retail @hanging_outbound_order_till_despatch_order_trailer_not_entered_when_unloading @complete @ds
  Scenario: Trailer not entered when unloading
    Given the OrderID of type "Retail" for sku "Hanging" should be in "Released" status at site
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then Allocation should be updated
    And I perform picking for hanging
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
