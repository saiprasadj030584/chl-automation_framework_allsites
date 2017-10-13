@allocation
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

  @hanging_allocation_retail_validate_whether_there_is_visibility_in_stock_modularity_for_ordering_visiblity_of_stock_types_for_ordering @allocation @retail @hanging @complete @ds
  Scenario: Validate whether there is visibility in Stock modularity  for ordering -Visiblity of Stock types for ordering
    Given the order id of type "Retail" should be in "Released" status and "Hanging" skus should be in "Suspense" location
    When I navigate to order header
    And I query with Order Id
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
    Given the order id of type "Retail" with "Hanging" skus should be in "Released" status
    And the order sku details are verified
    When the inventory is available for the given SKU
    Then the order status should be changed to "Allocated" status
