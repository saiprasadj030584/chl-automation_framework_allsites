@allocation
Feature: Allocation
  As a warehouse user
  I want to perform allocation of stocks

  @allocation @idt @hanging @hanging_allocation_idt_stock_in_suspense_location_not_allocated @complete
  Scenario: Validate whether the stock in suspense location is not allocated-Non allocation
    Given the order id of type "IDT" should be in "Released" status and "Hanging" skus should be in "Suspense" location
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should not get allocated

  @hanging_allocation_idt_visibility_in_stock_modularity @allocation @idt @hanging @complete
  Scenario: Validate whether there is visibility in Stock modularity  for ordering -Visiblity of Stock types for ordering
    Given the order id of type "IDT" should be in "Released" status and "Hanging" skus should be in "Suspense" location
    When I navigate to order header
    And I query with Order Id
    Then the order stock modularity should be visible

      
       @allocation @idt @hanging @hanging_allocation_idt_consolidate_orders @complete
  Scenario: Validate whether different orders of  same store are grouped together for one pick -  Consolidate Orders
    Given the multiple order id of type "IDT" with "Hanging" skus should be in "Released" status
    And I allocate the stocks using consignment in system allocation page
    Then the multiple stocks should get allocated
    And the orders should get consolidated

   
