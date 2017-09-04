@allocation
Feature: Allocation
  As a warehouse user
  I want to perform allocation of stocks

  @allocation @e_com @boxed @boxed_allocation_e_com_allocation_rules @complete
  Scenario Outline: Validate  whether all the stocks are allocated -Allocation Rules-Retail Orders
    Given the order id "<OrderNumber>" of type "Ecom" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated

    Examples: 
      | OrderNumber |
      |  5470000233 |
