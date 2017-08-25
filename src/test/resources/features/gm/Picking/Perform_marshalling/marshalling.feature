@marshalling
Feature: Perform Marshalling
  As a warehouse user
  I want to perform marshalling

  @boxed @retail @marshalling @boxed_marshalling_retail_perform_marshalling
  Scenario Outline: perform marshalling
    Given the OrderID "<OrderID>" of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I enter OrderID as "<OrderID>" for allocation
    Then the order should be allocated for the orderID "<OrderID>"
    And I perform picking
    When I perfom marshalling
    Then the move task should be updated

    Examples: 
      | OrderID    |
      | 4764300831 |

  @boxed @retail @jz @boxed_marshalling_retail_perform_marshalling
  Scenario Outline: perform marshalling
    Given the OrderID "<OrderID>" of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I enter OrderID as "<OrderID>" for allocation
    Then the order should be allocated for the orderID "<OrderID>"
    And I perform picking
    Then the container id should be generated

    Examples: 
      | OrderID    |
      | 3764200470 |
      
       @boxed @retail @js @boxed_marshalling_retail_perform_marshalling
  Scenario Outline: perform marshalling
    Given the OrderID "<OrderID>" of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I enter OrderID as "<OrderID>" for allocation
    Then the order should be allocated for the orderID "<OrderID>"
    When I enter the  invalid  UPC
    Then the error message should be displayed as invalid details
   

    Examples: 
      | OrderID    |
      | 3764200490 |
