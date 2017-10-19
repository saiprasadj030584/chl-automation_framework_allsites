@allocation
Feature: Hanging - IDT - Allocation
  As a warehouse user
  I want to perform allocation of stocks

  @hanging @allocation @idt @hanging_allocation_idt_validate_whether_stocks_are_automatically_allocated_to_orders_auto_allocation @complete @ds
  Scenario: Validate  whether stocks are automatically allocated to orders -Auto Allocation
   # Given the order id of type "IDT" with "Hanging" skus should be in "Released" status
    Given the order of "IDT" should be in "Released" status in order header maintenance 
    And the order sku details are verified
    When the inventory is available for the given SKU
    Then the order status should be changed to "Allocated" status
