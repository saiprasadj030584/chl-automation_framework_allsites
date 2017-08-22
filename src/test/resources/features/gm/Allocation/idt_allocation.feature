@allocation
Feature: Allocation
  As a warehouse user
  I want to perform allocation of stocks

  

  @allocation @idt @boxed @boxed_allocation_idt_stock_in_suspense_location_not_allocated @review
  Scenario Outline: Validate whether the stock in suspense location is not allocated-Non allocation
    Given the order id "<OrderNumber>" of type "IDT" should be in "Released" status and skus should be in "Suspense" location
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should not get allocated

    Examples: 
      | OrderNumber |
      |  5470000241 |

  
 
 

  @allocation @idt @boxed @boxed_allocation_idt_stocks_allocation_just_in_time_allocation @dryrun
  Scenario Outline: Validate whether stocks are allocated to orders  -Just in Time Allocation
    Given the order id "<OrderNumber>" of type "IDT" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated

    Examples: 
      | OrderNumber |
      |  5470000150 |
      
     
