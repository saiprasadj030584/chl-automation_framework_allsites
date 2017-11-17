@Unload_URN_from_trailer
Feature: Outbound order
  As a warehouse user
  I want to load a trailer
  So that I can unload that trailer

  @outbound_despatch @flatpack @retail @flatpack_outbound_despatch_retail_unload_URN_from_trailer
  Scenario Outline: Unload URN from trailer
   Given the order id of type "Retail" with "GOH" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
    And I perform picking for flatpack
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
      | 5971939436 |   5649 |
