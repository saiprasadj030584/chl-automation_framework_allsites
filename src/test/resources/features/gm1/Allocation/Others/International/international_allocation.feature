@allocation
Feature: Allocation
  As a warehouse user
  I want to perform allocation of stocks

  @allocation @international @goh @goh_allocation_international_allocation_rules @complete
  Scenario: Validate  whether all the stocks are allocated -Allocation Rules
    Given the order id of type "International" with "GOH" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated


  