@allocation
Feature: Allocation
  As a warehouse user
  I want to perform allocation of stocks

  @allocation @retail @boxed @allocation_retail_boxed_stock_in_suspense_location_not_allocated
  Scenario Outline: Validate whether the stock in suspense location is not allocated-Non allocation
    Given the order number "<OrderNumber>" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should not get allocated

    Examples: 
      | OrderNumber |
      |  3764200876 |

  @allocation @idt @boxed @allocation_idt_boxed_stock_in_suspense_location_not_allocated
  Scenario Outline: Validate whether the stock in suspense location is not allocated-Non allocation
    Given the order number "<OrderNumber>" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should not get allocated

    Examples: 
      | OrderNumber |
      |  5470000244 |

  @allocation @international @boxed @allocation_international_boxed_stocks_allocation_international_allocation_rules
  Scenario Outline: Validate  whether all the stocks are allocated -Allocation Rules
    Given the order number "<OrderNumber>" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated

    Examples: 
      | OrderNumber |
      |  5670000609 |

  @allocation @retail @boxed @allocation_retail_boxed_stocks_allocation_retail_allocation_rules
  Scenario Outline: Validate  whether all the stocks are allocated -Allocation Rules-Retail Orders
    Given the order number "<OrderNumber>" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated

    Examples: 
      | OrderNumber |
      |   8964200896 |
