@picking
Feature: order allocation and picking
  As a warehouse user
  I want to pick the allocated stocks

  @retail_urn_generated @po @complete
  Scenario Outline: Validate whether Retail URN is generated for Tote Cage or Pallet
    Given the order id "<OrderNumber>" of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
    And I perform picking
    When I navigate to order container page
    Then the picked stock details should be updated

    Examples: 
      | OrderNumber |
      |  4764300918 |

  @po_receive_putaway_hanging @po @complete
  Scenario Outline: Validate whether URN  are associated to Master URN automatically 
    Given the order id "<OrderNumber>" of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    And I perform picking

    Examples: 
      | OrderID    |
      | 4764300886 |

  @unpick @po @complete
  Scenario Outline: Validate the Unpick process
    Given the order id "<OrderNumber>" of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    And I perform picking
    Then order header should be updated for picked stock
    When I navigate to unpicking and unshipping page
    And perform unpicking
    Then order header should be updated for unpicked stock

    Examples: 
      | OrderID    |
      | 4764300886 |
