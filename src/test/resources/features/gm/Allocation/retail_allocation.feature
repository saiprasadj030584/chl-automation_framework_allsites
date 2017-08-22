@allocation
Feature: Allocation
  As a warehouse user
  I want to perform allocation of stocks

  @allocation @retail @boxed @boxed_allocation_retail_stock_in_suspense_location_not_allocated @dryrun
  Scenario Outline: Validate whether the stock in suspense location is not allocated-Non allocation
    Given the order id "<OrderNumber>" of type "Retail" should be in "Released" status and skus should be in "Suspense" location
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should not get allocated

    Examples: 
      | OrderNumber |
      #|  3764200879 |
      
      
      @allocation @retail @boxed @boxed_allocation_retail_allocation_rules @complete
  Scenario Outline: Validate  whether all the stocks are allocated -Allocation Rules-Retail Orders
    Given the order id "<OrderNumber>" of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated

    Examples: 
      | OrderNumber |
      |  8964200898 |
      
      
       @allocation @retail @boxed @boxed_allocation_retail_stocks_allocation_just_in_time_allocation @dryrun
  Scenario Outline: Validate whether stocks are allocated to orders  -Just in Time Allocation
    Given the order id "<OrderNumber>" of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated

    Examples: 
      | OrderNumber |
      |  3170001660 |