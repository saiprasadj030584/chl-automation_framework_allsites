@allocation
Feature: Allocation
  As a warehouse user
  I want to perform allocation of stocks

  @allocation @retail @hanging @hanging_allocation_retail_validate_the_prohibition_rules_while_allocating_the_stock_prohibition_rules_disallowed @complete
  Scenario: Validate the Prohibition Rules while allocating the stock  -Prohibition Rules -Disallowed 
    Given the order id of type "Retail" should be in "Released" status and "Hanging" skus should be applicable for "Prohibition" disallowed
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should not get allocated

 
