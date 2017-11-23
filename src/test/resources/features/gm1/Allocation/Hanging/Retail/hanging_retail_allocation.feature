@hanging_retail_allocation
Feature: Hanging - Retail - Allocation
  As a warehouse user
  I want to perform allocation of stocks

  @hanging_allocation_retail_stock_in_suspense_location_not_allocated @allocation @retail @hanging @complete
  Scenario Outline: Validate whether the stock in suspense location is not allocated-Non allocation
    Given the order id of type "Retail" should be in "Released" status and "Hanging" skus should be in "Suspense" location
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should not get allocated

    Examples: 
      | OrderNumber |
      |  5170201467 |

  @jenkinsB @hanging_allocation_retail_validate_whether_there_is_visibility_in_stock_modularity_for_ordering_visiblity_of_stock_types_for_ordering @allocation @retail @hanging @complete @ds
  Scenario: Validate whether there is visibility in Stock modularity  for ordering -Visiblity of Stock types for ordering
    Given the order id of type "Retail" with "Hanging" skus should be in "Released" status
    Then the order stock modularity should be visible

  @allocation @retail @hanging @hanging_allocation_retail_validate_whether_all_the_stocks_are_allocated_allocation_rules_retail_orders @complete @ds
  Scenario: Validate whether all the stocks are allocated -Allocation Rules-Retail Orders
    Given the order id of type "Retail" with "Hanging" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated

  @hanging @allocation @retail @hanging_allocation_retail_validate_whether_stocks_are_automatically_allocated_to_orders_auto_allocation @complete @ds
  Scenario: Validate  whether stocks are automatically allocated to orders -Auto Allocation
    Given the order id of type "Retail" with "Hanging" skus should be in "Released" status
    And the order sku details are verified
    When the inventory is available for the given SKU
    Then the order status should be changed to "Allocated" status
    
    @hanging @allocation @retail @hanging_allocation_retail_validate_the_allocations_rules_for_hanging @complete @ds
  Scenario: Validate the allocations Rules for Hanging 
    #Given the order id of type "Retail" with "Hanging" skus should be in "Released" status
    Given the order of "Retail" should be in "Released" status in order header maintenance 
    And the order sku details are verified
    When the inventory is available for the given SKU
    Then the order status should be changed to "Allocated" status
    
    @allocation @hanging @retail @hanging_allocation_retail_validate_the_prohibition_rules_while_allocating_the_stock_prohibition_rules_prioritize @complete @ds
  Scenario: Validate the Prohibition Rules while allocating the stock  -Prohibition Rules -Prioritize
    Given the OrderID of type "Retail" for sku "Hanging" should be in "Released" status at site
    And I have inventory available for the order line items
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then the order should be allocated with prohibition flag
    
     @jenkinsB @allocation @hanging @retail @hanging_allocation_retail_validate_whether_stock_is_available_for_allocation @complete @ds
  Scenario: Validate whether stock is available for allocation
    Given the OrderID of type "Retail" for sku "Hanging" should be in "Released" status at site
    And I have inventory available for the order line items
    Then sku should be available in inventory
