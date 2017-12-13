@sortation
Feature: order sortation
  As a warehouse user
  I want to sort the picked stocks

  @yes @boxed @sortation @retail @boxed_sortation_sortation_validate_whether_master_urn_can_be_sorted_for_retail_order @ds 
  Scenario: Validate whether master URN can be sorted
    Given the order id of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I allocate the stocks
    Then the stock should get allocated
    When I navigate to scheduler program page
    And I run the program
    And I perform picking for boxed
    When I navigate to Order Container Maintainance page
    Then master URN should build
    When I proceed with sortation
    Then URN should be sorted
    
   
