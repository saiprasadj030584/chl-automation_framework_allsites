@Unload_URN_from_trailer
Feature: Outbound order
  As a warehouse user
  I want to load a trailer
  So that I can unload that trailer

  @outbound_despatch @hanging @retail @hanging_outbound_despatch_retail_unload_URN_from_trailer 
  Scenario Outline: Unload URN from trailer
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
    When I proceed for vehicle unloading
    Then vehicle should be unload

    Examples: 
      | OrderID    | SiteId |
      | 5108563721 |   5649 |
