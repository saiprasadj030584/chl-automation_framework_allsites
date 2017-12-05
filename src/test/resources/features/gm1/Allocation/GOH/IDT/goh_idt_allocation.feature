@goh_idt_allocation
Feature: GOH - IDT - Allocation
  As a warehouse user
  I want to perform allocation of stocks

  @allocation @goh @idt @unique_goh_allocation_idt_validate_the_prohibition_rules_while_allocating_the_stock_prohibition_rules_prioritize @complete @ds
  Scenario: Validate the Prohibition Rules while allocating the stock  -Prohibition Rules -Prioritize
    Given the OrderID of type "IDT" for sku "GOH" should be in "Released" status at site
    And I have inventory available for the order line items
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then the order should be allocated with prohibition flag

    @jenkinsC @allocation @goh @idt @unique_goh_allocation_idt_validate_whether_order_is_available_for_allocation @complete @ds
  Scenario: Validate whether stock is available for allocation
    Given the OrderID of type "IDT" for sku "GOH" should be in "Released" status at site
    And I have inventory available for the order line items
    Then sku should be available in inventory

  @jenkinsls @goh @allocation @idt @unique_goh_allocation_idt_validate_whether_stocks_are_automatically_allocated_to_orders_auto_allocation @complete @ds
  Scenario: Validate whether stocks are automatically allocated to orders -Auto Allocation
    Given the OrderID of type "IDT" for sku "GOH" should be in "Released" status at site
    And the order sku details are verified
    When the inventory is available for the given SKU
    Then the order should be allocated

  @jenkinsC @unique_goh_allocation_idt_validate_whether_there_is_visibility_in_stock_modularity_for_ordering_visiblity_of_stock_types_for_ordering @allocation @idt @goh @complete @ds
  Scenario: Validate whether there is visibility in Stock modularity  for ordering -Visiblity of Stock types for ordering
    Given the OrderID of type "IDT" for sku "GOH" should be in "Released" status at site
    Then the order stock modularity should be visible
