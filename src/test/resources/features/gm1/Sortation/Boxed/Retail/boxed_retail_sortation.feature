@boxed_retail_sortation
Feature: Boxed - Retail - Sortation
  As a warehouse user
  I want to validate Pallet ID
  So that order can be picked

  @sortation @boxed @retail @boxed_sortation_sortation_validate_whether_master_urn_can_be_build @complete @ds 
  Scenario: Validate whether Master URN can be build
    Given the OrderID of type "Retail" should be in "Released" status at site
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then Allocation should be updated
    And I perform picking for boxed
    Then the order should be picked
    When I navigate to Order Container Maintainance page
    Then master URN should build
