@allocation
Feature: Allocation
  As a warehouse user
  I want to perform allocation of stocks

  @allocation @retail @goh @goh_allocation_retail_validate_whether_all_the_stocks_are_allocated_allocation_rules_retail_orders @complete @new_script
  Scenario: Validate  whether all the stocks are allocated -Allocation Rules-Retail Orders
    Given the order id of type "Retail" with "GOH" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated


  @goh_allocation_retail_validate_whether_there_is_visibility_in_stock_modularity_for_ordering_visiblity_of_stock_types_for_ordering @allocation @retail @goh @complete  @new_script
  Scenario: Validate whether there is visibility in Stock modularity  for ordering -Visiblity of Stock types for ordering
    Given the order id of type "Retail" with "GOH" skus should be in "Released" status
    When I navigate to order header
    And I query with Order Id
    Then the order stock modularity should be visible


  @allocation @retail @goh @goh_allocation_retail_validate_whether_different_orders_of_same_store_are_grouped_together_for_one_pick_consolidate_orders @complete @new_script
  Scenario: Validate whether different orders of  same store are grouped together for one pick -  Consolidate Orders
    Given the multiple order id of type "Retail" with "GOH" skus should be in "Released" status
    And I allocate the stocks using consignment in system allocation page
    Then the multiple stocks should get allocated
    And the orders should get consolidated

  @allocation @retail @goh @goh_allocation_retail_non_allocation_suspense_location @complete
  Scenario: Validate whether the stock in suspense location is not allocated-Non allocation
    Given the order id of type "Retail" with "GOH" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
