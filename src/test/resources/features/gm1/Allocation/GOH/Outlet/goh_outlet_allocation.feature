@goh_outlet_allocation
Feature: Boxed - Outlet - Allocation
  As a warehouse user
  I want to perform allocation of stocks

   @jenkinsC @allocation @outlet @goh @unique_goh_allocation_outlet_validate_whether_all_the_stocks_are_allocated_allocation_rules @complete @ds @jenkall
  Scenario: Validate whether all the stocks are allocated -Allocation Rules-Retail Orders
    Given the order id of type "Outlet" with "GOH" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the order should be allocated
