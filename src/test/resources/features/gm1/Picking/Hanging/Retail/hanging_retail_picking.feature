@hanging_retail_picking
Feature: Hanging - Retail - Picking
  As a warehouse user
  I want to pick the allocated stocks

  @hanging @retail @picking @unique_hanging_picking_retail_validate_keying_wrong_upc @complete @ds @jenkinsB
  Scenario: Validate keying wrong UPC
    Given the order of "Retail" should be in "Released" status in order header maintenance
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then the order should be allocated
    When I enter the ivalid UPC for hanging
    Then the error message should be displayed as invalid details

