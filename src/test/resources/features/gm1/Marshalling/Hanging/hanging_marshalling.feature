@marshalling
Feature: Hanging - Perform Marshalling
  As a warehouse user
  I want to perform marshalling

  
    @hanging @retail @marshalling @unique_hanging_marshalling_retail_perform_marshalling @complete @ds @jenkinsB
  Scenario: Perform marshalling
    Given the order is of type "Retail" and it is in "Released" status
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then the order should be allocated
    And I perform picking
    When I perfom marshalling
    Then the move task should be updated
    

