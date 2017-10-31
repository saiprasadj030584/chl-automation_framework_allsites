@goh_retail_allocation
Feature: GOH - Retail - Allocation
  As a warehouse user
  I want to perform allocation of stocks
    
    @allocation @goh @retail @goh_allocation_retail_validate_the_prohibition_rules_while_allocating_the_stock_prohibition_rules_prioritize @complete @ds
  Scenario: Validate the Prohibition Rules while allocating the stock  -Prohibition Rules -Prioritize
     Given the OrderID of type "Retail" for sku "GOH" should be in "Released" status at site
    And I have inventory available for the order line items
    When I navigate to system allocation page
    And I enter OrderID for allocation
    Then the order should be allocated with prohibition flag
    
    @allocation @goh @retail @goh_allocation_retail_validate_whether_stock_is_available_for_allocation @complete @ds
  Scenario: Validating availability of stock for allocation
    Given the OrderID of type "Retail" for sku "GOH" should be in "Released" status at site
    And I have inventory available for the order line items
    Then sku should be available in inventory
