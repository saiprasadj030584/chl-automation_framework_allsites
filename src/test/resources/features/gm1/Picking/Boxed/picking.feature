@picking
Feature: order allocation and picking
  As a warehouse user
  I want to pick the allocated stocks

  @boxed @picking @boxed_picking_picking_retail_urn_generated @complete @ds @retail
  Scenario: Validate whether Retail URN is generated for Tote Cage or Pallet
    Given the order id of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
    And I perform picking
    When I navigate to order container page
    Then the urn id should be updated in order container page

  @boxed @picking @boxed_picking_retail_urn_associate_master_urn @complete @ds
  Scenario: Validate whether URN  are associated to Master URN automatically 
    Given the order id of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    And I perform picking
    When I navigate to order container page
    Then the urn id and pallet id should be updated in order container page

  @boxed @picking @boxed_picking_retail_unpick @onhold @ds @retail
  Scenario: Validate the Unpick process
    Given the order id of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    And I perform picking
    When I navigate to order container page
    Then the urn id and pallet id should be updated in order container page
    And order header should be updated for picked stock
    When I navigate to unpicking and unshipping page
    And I perform unpicking
    Then order header should be updated for unpicked stock

  @boxed @retail @picking @boxed_picking_retail_validate_keying_wrong_UPC @complete @ds
  Scenario: Validate keying wrong UPC
    Given the order id of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I enter OrderID as "<OrderID>" for allocation
    Then the order should be allocated for the orderID "<OrderID>"
    When I enter the invalid  UPC
    Then the error message should be displayed as invalid details
