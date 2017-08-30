@picking
Feature: order allocation and picking
  As a warehouse user
  I want to pick the allocated stocks

  @boxed @picking @picking @boxed_picking_picking_retail_urn_generated @complete
  Scenario Outline: Validate whether Retail URN is generated for Tote Cage or Pallet
    Given the order id "<OrderNumber>" of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
    And I perform picking
    When I navigate to order container page
    Then the urn id should be updated in order container page

    Examples: 
      | OrderNumber |
      |  4764300919 |

  @boxed @picking @picking @boxed_picking_picking_urn_associate_master_urn @complete
  Scenario Outline: Validate whether URN  are associated to Master URN automatically 
    Given the order id "<OrderID>" of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    And I perform picking
    When I navigate to order container page
    Then the urn id and pallet id should be updated in order container page

    Examples: 
      | OrderID    |
      | 4764300861 |

  @boxed @picking @picking @boxed_picking_picking_unpick @complete
  Scenario Outline: Validate the Unpick process
    Given the order id "<OrderID>" of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    And I perform picking
    When I navigate to order container page
    Then the urn id and pallet id should be updated in order container page
    And order header should be updated for picked stock
    When I navigate to unpicking and unshipping page
    And I perform unpicking
    Then order header should be updated for unpicked stock

    Examples: 
      | OrderID    |
      | 4764300784 |
