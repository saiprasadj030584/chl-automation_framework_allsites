@goh_retail_picking
Feature: GOH - Retail - Picking
  As a warehouse user
  I want to pick the allocated stocks

  @goh @retail @picking @goh_picking_retail_validate_keying_wrong_upc @complete @ds
  Scenario: Validate keying wrong UPC
    Given the order of "Retail" should be in "Released" status in order header maintenance
    # Given the order id of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I enter OrderID for allocation
    # And I enter OrderID for clustering
    Then the status should be allocated for the orderID
    When I enter the invalid  UPC
    Then the error message should be displayed as invalid details

