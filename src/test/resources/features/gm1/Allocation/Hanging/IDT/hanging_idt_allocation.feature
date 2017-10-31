@hanging_idt_allocation
Feature: Hanging - IDT - Allocation
  As a warehouse user
  I want to perform allocation of stocks

  @hanging @allocation @idt @hanging_allocation_idt_validate_whether_stocks_are_automatically_allocated_to_orders_auto_allocation @complete @ds
  Scenario: Validate  whether stocks are automatically allocated to orders -Auto Allocation
    Given the order id of type "IDT" with "Hanging" skus should be in "Released" status
    And the order sku details are verified
    When the inventory is available for the given SKU
    Then the order status should be changed to "Allocated" status

  @allocation @hanging @idt @hanging_allocation_idt_validate_the_prohibition_rules_while_allocating_the_stock_prohibition_rules_prioritize @complete @ds
  Scenario: Validating prohibition rule while allocation
    Given the OrderID of type "IDT" for sku "Hanging" should be in "Released" status at site
    And I have inventory available for the order line items
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then the order should be allocated with prohibition flag

  @allocation @hanging @idt @hanging_allocation_idt_validate_whether_stock_is_available_for_allocation @complete @ds
  Scenario: Validating availability of stock for allocation
    Given the OrderID of type "IDT" for sku "Hanging" should be in "Released" status at site
    And I have inventory available for the order line items
    Then sku should be available in inventory
