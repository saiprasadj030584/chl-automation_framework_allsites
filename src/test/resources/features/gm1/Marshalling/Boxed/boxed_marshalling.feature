@marshalling
Feature: Boxed - Perform Marshalling
  As a warehouse user
  I want to perform marshalling

  @boxed @retail @marshalling @boxed_marshalling_retail_perform_marshalling @complete @ds
  Scenario: Perform marshalling
    Given the order is of type "Retail" and it is in "Released" status
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then the order should be allocated
    And I perform picking
    When I perfom marshalling
    Then the move task should be updated

