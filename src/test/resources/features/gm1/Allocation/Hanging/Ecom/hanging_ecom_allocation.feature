@hanging_ecom_allocation
Feature: Hanging - E-Com - Allocation
  As a warehouse user
  I want to perform allocation of stocks

    @jenkinsec @allocation @e_com @hanging @hanging_allocation_e_com_validate_whether_all_the_stocks_are_allocated_allocation_rules @complete @ds 
  Scenario: Validate  whether all the stocks are allocated -Allocation Rules
    Given the order id of type "Ecom" with "Hanging" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
