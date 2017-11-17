@Outbound_order
Feature: Incorrect_pallet_ID_enter_during_vehicle_unloading
  As a warehouse user
  I want to load a trailer
  So that I can unload that trailer

  @outbound_despatch @hanging @retail @hanging_outbound_despatch_retail_trailor_not_entered_when_unloading @review
  Scenario Outline: Trailer_not_entered_when_unloading
    Given the OrderID "<OrderID>" of type "Retail" for sku "Hanging" should be in "Released" status at site "<SiteId>"
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then Allocation should be updated
    And I perform picking for hanging
    Then the order should be Ready to Load
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn
    Then the booking details should appear
    And I proceed for vehicle loading
    Then Trailer should be loaded
    When I proceed for vehicle unload
    Then Trailer should enter page displayed

    Examples: 
      | OrderID    | SiteId |
      | 5108567642 |   5649 |
