@Unload_URN_from_trailer
Feature: Outbound order
  As a warehouse user
  I want to load a trailer
  So that I can unload that trailer

  @outbound_despatch @goh @retail @goh_outbound_despatch_retail_unload_URN_from_trailer
  Scenario Outline: Unload URN from trailer
    Given the OrderID "<OrderID>" of type "Retail" for sku "GOH" should be in "Released" status at site "<SiteId>"
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then Allocation should be updated
    When I navigate to scheduler praogram page
    And I run the program
    And I perform picking for GOH
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
    #When I proceed for GOH vehicle unloading
    #Then vehicle should be unload

    Examples: 
      | OrderID    | SiteId |
      #| 5104210581 |   5649 |
      #| 4764710844 |   5649 |
      | 5173002561 |   5649 |
