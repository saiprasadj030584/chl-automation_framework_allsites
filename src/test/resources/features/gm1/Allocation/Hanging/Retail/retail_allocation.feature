@allocation
Feature: Allocation
  As a warehouse user
  I want to perform allocation of stocks

  @yes @allocation @retail @hanging @hanging_allocation_retail_validate_the_prohibition_rules_while_allocating_the_stock_prohibition_rules_disallowed @complete @ds @gems
  Scenario: Validate the Prohibition Rules while allocating the stock  -Prohibition Rules -Disallowed 
    Given the order id of type "Retail" should be in "Released" status and "Hanging" skus should be applicable for "Prohibition" disallowed
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should not get allocated
    
    
    
     @allocation @retail @hanging @unique_hanging_allocation_retail_validate_whether_different_orders_of_same_store_are_grouped_together_for_one_pick_consolidate_orders @complete 
  Scenario: Validate whether different orders of  same store are grouped together for one pick -  Consolidate Orders
    Given the multiple order id of type "Retail" with "Hanging" skus should be in "Released" status
    And I allocate the stocks using consignment in system allocation page
    Then the multiple stocks should get allocated
    And the orders should get consolidated

 
