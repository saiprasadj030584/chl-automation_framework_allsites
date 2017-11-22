@boxed_ecom_allocation
Feature: Boxed - ECom - Allocation
  As a warehouse user
  I want to perform allocation of stocks

   @jenkinsec @allocation @e_com @boxed @boxed_allocation_e_com_validate_whether_all_the_stocks_are_allocated_allocation_rules @complete @ds @ecum
  Scenario: Validate  whether all the stocks are allocated -Allocation Rules-Retail Orders
    Given the order id of type "Ecom" with "Boxed" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
