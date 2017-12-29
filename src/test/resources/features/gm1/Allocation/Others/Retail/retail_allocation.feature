@allocation
Feature: Allocation
  As a warehouse user
  I want to perform allocation of stocks

@r1  @allocation  @jenkinsC @retail @goh @unique_goh_allocation_retail_validate_whether_all_the_stocks_are_allocated_allocation_rules_retail_orders @complete @new_script @allocation7_check
  Scenario: Validate  whether all the stocks are allocated -Allocation Rules-Retail Orders
    Given the order id of type "Retail" with "GOH" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated

  @r1  @jenkinsC @goh_allocation_retail_validate_whether_there_is_visibility_in_stock_modularity_for_ordering_visiblity_of_stock_types_for_ordering @allocation @retail @goh @complete @stock_vis @retail_check
  Scenario: Validate whether there is visibility in Stock modularity  for ordering -Visiblity of Stock types for ordering
    Given the order id of type "Retail" with "GOH" skus should be in "Released" status
    When I navigate to order header
    And I query with Order Id
    Then the order stock modularity should be visible

 @r1 @allocation  @jenkinsC @retail @goh @goh_allocation_retail_validate_whether_different_orders_of_same_store_are_grouped_together_for_one_pick_consolidate_orders @complete @new_script @retail_check @consolidate_check
  Scenario: Validate whether different orders of  same store are grouped together for one pick -  Consolidate Orders
    Given the multiple order id of type "Retail" with "GOH" skus should be in "Released" status
    And I allocate the stocks using consignment in system allocation page
    Then the multiple stocks should get allocated
    And the orders should get consolidated

 @r1 @yes  @jenkinsC @allocation @retail @goh @goh_allocation_retail_validate_whether_the_stock_in_suspense_location_is_not_allocated_non_allocation @complete @retail_check @suspense_check
  Scenario: Validate whether the stock in suspense location is not allocated-Non allocation
    Given the order id of type "Retail" should be in "Released" status and "GOH" skus should be in "Suspense" location
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should not get allocated

@r1  @allocation @jenkinsC  @retail @goh @goh_allocation_retail_validate_the_prohibition_rules_while_allocating_the_stock_prohibition_rules_disallowed @complete @ds @gems
  Scenario: Validate the Prohibition Rules while allocating the stock  -Prohibition Rules -Disallowed
    Given the order id of type "Retail" should be in "Released" status and "GOH" skus should be applicable for "Prohibition" disallowed
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should not get allocated
