@marshalling
Feature: Perform Marshalling
  As a warehouse user
  I want to perform marshalling

  @boxed @retail @marshalling @perform_marshalling
  Scenario Outline: perform marshalling
    Given the OrderID "<OrderID>" of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I enter OrderID as "<OrderID>" for allocation
    Then the order should be allocated for the orderID "<OrderID>"

    And I perform picking
    # When I Perfom marshalling
    #Then the move task should be updated.
    Examples: 
      | OrderID    |
      | 4764300857 |
