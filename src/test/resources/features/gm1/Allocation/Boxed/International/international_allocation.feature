@allocation
Feature: Allocation
  As a warehouse user
  I want to perform allocation of stocks

  @allocation @international @boxed @boxed_allocation_international_allocation_rules @complete @ds
  Scenario: Validate  whether all the stocks are allocated -Allocation Rules
    Given the order id of type "International" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated

  