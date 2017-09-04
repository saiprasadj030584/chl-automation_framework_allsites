@allocation
Feature: Allocation
  As a warehouse user
  I want to perform allocation of stocks

  @allocation @outlet @boxed @boxed_allocation_outlet_allocation_rules @complete
  Scenario Outline: Validate whether all the stocks are allocated -Allocation Rules-Retail Orders
    Given the order id "<OrderNumber>" of type "Outlet" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated

    Examples: 
      | OrderNumber |
      |  4470000241 |
