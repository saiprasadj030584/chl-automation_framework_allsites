@boxed_idt_allocation
Feature: Boxed - IDT - Allocation
  As a warehouse user
  I want to perform allocation of stocks

  @allocation @idt @boxed @boxed_allocation_idt_stocks_allocation_just_in_time_allocation @dryrun @onhold @ds
  Scenario: Validate whether stocks are allocated to orders  -Just in Time Allocation
    Given the order id of type "IDT" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated

  @allocation @idt @boxed @boxed_allocation_idt_validate_whether_the_stock_in_suspense_location_is_not_allocated_non_allocation @complete @ds
  Scenario: Validate whether the stock in suspense location is not allocated-Non allocation
    Given the order id of type "IDT" should be in "Released" status and skus should be in "Suspense" location
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should not get allocated

  @boxed @allocation @idt @boxed_allocation_idt_validate_the_prohibition_rules_while_allocating_the_stock_prohibition_rules_disallowed @complete @ds
  Scenario: Validate the Prohibition Rules while allocating the stock  -Prohibition Rules -Disallowed
    Given the order is of type "NONRETAIL" and it is in "Released" status
    And the order status is in "Released" status raised for the country of origin "<OriginLocation>"
    And the order sku details are verified
    When the inventory is available for the "<AgainstLocation>" and unavailable for "<OriginLocation>"
    Then the order status should be in "Released" status

  @allocation @boxed @idt @boxed_allocation_idt_validate_the_prohibition_rules_while_allocating_the_stock_prohibition_rules_prioritize @complete @ds
  Scenario: Validate the Prohibition Rules while allocating the stock  -Prohibition Rules -Prioritize
    Given the OrderID of type "IDT" should be in "Released" status at site
    And I have inventory available for the order line items
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then the order should be allocated

    @jenkinsallo @allocation @boxed @idt @boxed_allocation_idt_validate_whether_stock_is_available_for_allocation @complete @ds
  Scenario: Validate whether stock is available for allocation
    Given the OrderID of type "IDT" should be in "Released" status at site
    And I have inventory available for the order line items
    Then sku should be available in inventory

     @boxed @allocation @idt @boxed_allocation_idt_validate_whether_stocks_are_automatically_allocated_to_orders_auto_allocation @complete @ds 
  Scenario: Validate  whether stocks are automatically allocated to orders -Auto Allocation
   Given the order of "IDT" should be in "Released" status in order header maintenance 
    And the order sku details are verified
    When the inventory is available for the given SKU
    Then the order status should be changed to "Allocated" status

    @jenkinsvi @boxed_allocation_idt_validate_whether_there_is_visibility_in_stock_modularity_for_ordering_visiblity_of_stock_types_for_ordering @allocation @idt @boxed @complete @ds
  Scenario: Validate whether there is visibility in Stock modularity  for ordering -Visiblity of Stock types for ordering
    Given the order of "IDT" should be in "Released" status in order header maintenance
    Then the order stock modularity should be visible