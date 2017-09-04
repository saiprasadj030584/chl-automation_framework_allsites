@allocation
Feature: Allocation
  As a warehouse user
  I want to perform allocation of stocks

  @allocation @retail @boxed @boxed_allocation_retail_stock_in_suspense_location_not_allocated @complete
  Scenario Outline: Validate whether the stock in suspense location is not allocated-Non allocation
    Given the order id "<OrderNumber>" of type "Retail" should be in "Released" status and skus should be in "Suspense" location
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should not get allocated

    Examples: 
      | OrderNumber |
      |  3764200879 |

  @allocation @retail @boxed @boxed_allocation_retail_allocation_rules @complete
  Scenario Outline: Validate  whether all the stocks are allocated -Allocation Rules-Retail Orders
    Given the order id "<OrderNumber>" of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated

    Examples: 
      | OrderNumber |
      |  8964200898 |

  @boxed @allocation @retail @allocation_boxed_retail_disallowing_prohibition_rules_while_allocating_the_stock @complete
  Scenario Outline: Validate the Prohibition Rules while allocating the stock  -Prohibition Rules -Disallowed
    Given the order "<Order>" is of type "RETAIL"  and it is in "Released" status
    And the order "<Order>" status is in "Released" status raised for the country of origin "<OriginLocation>"
    And the order sku details are verified
    When the inventory is available for the "<AgainstLocation>" and unavailable for "<OriginLocation>"
    Then the order status should be in "Released" status

    Examples: 
      | OriginLocation | AgainstLocation | Order      |
      | TUR            | UK              | 4764300877 |

  @allocation @retail @boxed @boxed_allocation_retail_stocks_allocation_just_in_time_allocation @onhold
  Scenario Outline: Validate whether stocks are allocated to orders  -Just in Time Allocation
    Given the order id "<OrderNumber>" of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated

    Examples: 
      | OrderNumber |
      |  3170001660 |

  @allocation @boxed @retail @boxed_allocation_retail_validate_prohibition_rule @complete
  Scenario Outline: Validating prohibition rule while allocation
    Given the OrderID "<OrderID>" of type "Retail" should be in "Released" status at site "<SiteId>"
    And I have inventory available for the order line items
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then the order should be allocated

    Examples: 
      | OrderID    | SiteId |
      | 3170001591 |   5649 |

  @allocation @boxed @idt @boxed_allocation_retail_validate_stock_availability @complete
  Scenario Outline: Validating availability of stock for allocation
    Given the OrderID "<OrderID>" of type "Retail" should be in "Released" status at site "<SiteId>"
    And I have inventory available for the order line items
    Then sku should be available in inventory

    Examples: 
      | OrderID    | SiteId |
      | 3170002491 |   5649 |
