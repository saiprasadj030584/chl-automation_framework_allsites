@allocation
Feature: Allocation
  As a warehouse user
  I want to perform allocation of stocks

  @allocation @idt @boxed @boxed_allocation_idt_stocks_allocation_just_in_time_allocation @dryrun @onhold @ds
  Scenario: Validate whether stocks are allocated to orders  -Just in Time Allocation
    Given the order id of type "IDT" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated

  @allocation @idt @boxed @boxed_allocation_idt_stock_in_suspense_location_not_allocated @complete @ds
  Scenario: Validate whether the stock in suspense location is not allocated-Non allocation
    Given the order id of type "IDT" should be in "Released" status and skus should be in "Suspense" location
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should not get allocated

  @boxed @allocation @idt @allocation_boxed_idt_disallowing_prohibition_rules_while_allocating_the_stock @complete @ds
  Scenario: Validate the Prohibition Rules while allocating the stock  -Prohibition Rules -Disallowed
    Given the order is of type "NONRETAIL" and it is in "Released" status
    And the order status is in "Released" status raised for the country of origin "<OriginLocation>"
    And the order sku details are verified
    When the inventory is available for the "<AgainstLocation>" and unavailable for "<OriginLocation>"
    Then the order status should be in "Released" status

  @allocation @boxed @idt @boxed_allocation_idt_validate_prohibition_rule @complete
  Scenario Outline: Validating prohibition rule while allocation
    Given the OrderID "<OrderID>" of type "IDT" should be in "Released" status at site "<SiteId>"
    And I have inventory available for the order line items
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then the order should be allocated

    Examples: 
      | OrderID    | SiteId |
      | 5470000421 |   5649 |

  @allocation @boxed @idt @boxed_allocation_idt_validate_stock_availability @complete
  Scenario Outline: Validating availability of stock for allocation
    Given the OrderID "<OrderID>" of type "IDT" should be in "Released" status at site "<SiteId>"
    And I have inventory available for the order line items
    Then sku should be available in inventory

    Examples: 
      | OrderID    | SiteId |
      | 5470000521 |   5649 |

  @boxed @allocation @idt @allocation_boxed_idt_auto_allocation_while_qty_on_hand @onhold
  Scenario Outline: Validate  whether stocks are automatically allocated to orders -Auto Allocation
    Given the order "<Order>" is of type "TRANSFER" and it is in "Released" status
    And the order sku details are verified
    When the inventory is available for the given SKU
    Then the order status should be changed to "Allocated" status

    Examples: 
      | Order      |
      | 6600013018 |
