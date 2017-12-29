@hanging_international_allocation
Feature: Hanging - International - Allocation
  As a warehouse user
  I want to perform allocation of stocks

 @r1  @jenkinsB @allocation @international @hanging @unique_hanging_allocation_international_validate_whether_all_the_stocks_are_allocated_allocation_rules @complete @ds 
  Scenario: Validate  whether all the stocks are allocated -Allocation Rules
    Given the order id of type "International" with "Hanging" skus should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
