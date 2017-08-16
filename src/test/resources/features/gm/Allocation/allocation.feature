@allocation
Feature: Allocation
  As a warehouse user
  I want to perform allocation of stocks

  @allocation @retail @boxed @stock_in_suspense_location_not_allocated
  Scenario Outline: Validate whether the stock in suspense location is not allocated-Non allocation
    Given I have a tag in inventory with "<LockStatus>" status
    When I navigate to inventory update page
    And I select the update type as "Lock Status Change"
    And I search the inventory for the tag
    Then the tag details should be displayed
    And I select the status as "<UpdateStatus>"
    When I navigate to inventory transaction query
    When I choose the code as "<Code>" and search the tag id
    Then the status should be updated

    Examples: 
      | OrderNumber|
      | 3764200866 |
      
      @allocation @idt @boxed @stock_in_suspense_location_not_allocated
  Scenario Outline: Validate whether the stock in suspense location is not allocated-Non allocation
    Given I have a tag in inventory with "<LockStatus>" status
    When I navigate to inventory update page
    And I select the update type as "Lock Status Change"
    And I search the inventory for the tag
    Then the tag details should be displayed
    And I select the status as "<UpdateStatus>"
    When I navigate to inventory transaction query
    When I choose the code as "<Code>" and search the tag id
    Then the status should be updated

    Examples: 
       | OrderNumber|
       | 5470000144 |
      
      @allocation @international @boxed @stocks_allocation_international_allocation_rules
  Scenario Outline: Validate  whether all the stocks are allocated -Allocation Rules
    Given I have a tag in inventory with "<LockStatus>" status
    When I navigate to inventory update page
    And I select the update type as "Lock Status Change"
    And I search the inventory for the tag
    Then the tag details should be displayed
    And I select the status as "<UpdateStatus>"
    When I navigate to inventory transaction query
    When I choose the code as "<Code>" and search the tag id
    Then the status should be updated

    Examples: 
      | OrderNumber|
      | 5670000489 |
      
     @allocation @retail @boxed @stocks_allocation_retail_allocation_rules
  Scenario Outline: Validate  whether all the stocks are allocated -Allocation Rules-Retail Orders
    Given I have a "<OrderNumber>"
    When I navigate to system allocation page
    And I select the update type as "Lock Status Change"
    And I search the inventory for the tag
    Then the tag details should be displayed
    And I select the status as "<UpdateStatus>"
    When I navigate to inventory transaction query
    When I choose the code as "<Code>" and search the tag id
    Then the status should be updated

    Examples: 
      | OrderNumber|
      | 8964200866 |

  

 