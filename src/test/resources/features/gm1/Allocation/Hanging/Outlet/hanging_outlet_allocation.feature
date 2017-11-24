@hanging_outlet_allocation
Feature: Hanging - Outlet - Allocation
  As a warehouse user
  I want to perform allocation of stocks


  @allocation @outlet @hanging @hanging_allocation_outlet_stock_in_suspense_location_not_allocated @review
  Scenario Outline: Validate whether the stock in suspense location is not allocated-Non allocation
    Given the order id "<OrderNumber>" of type "IDT" should be in "Released" status and "Hanging" skus should be in "Suspense" location
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should not get allocated

    Examples: 
      | OrderNumber |
      |  5470000241 |


     @jenkinsB @allocation @outlet @hanging @hanging_allocation_outlet_validate_whether_all_the_stocks_are_allocated_allocation_rules @complete @ds 
  Scenario: Validate  whether all the stocks are allocated -Allocation Rules
    Given the order id of type "Outlet" with "Hanging" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the order should be allocated
