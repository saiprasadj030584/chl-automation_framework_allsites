@flatpack_retail_picking
Feature: Flatpack - Retail - Picking
  As a warehouse user
  I want to pick the allocated stocks

  @flatpack @retail @picking @unique_flatpack_picking_retail_validate_keying_wrong_upc @complete @ds @jenkinsC
  Scenario: Validate keying wrong UPC
    Given the order of "Retail" should be in "Released" status in order header maintenance
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then the order should be allocated
    When I enter the ivalid UPC for hanging
    Then the error message should be displayed as invalid details
    
    @yes @flatpack @picking @unique_flatpack_picking_picking_validate_the_unpick_process @complete @pck1
  Scenario: Validate the Unpick process
    Given the order id of type "Retail" with "Flatpack" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
    And I perform picking for Hanging Retail
    When I navigate to order container page
    Then the urn id and pallet id should be updated in order container page
    And order header should be updated for picked stock
    When I navigate to unpicking and unshipping page
    And I perform unpicking
    Then order header should be updated for unpicked stock


