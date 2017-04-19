@stock_adjustments
Feature: Stock adjustments
  As a warehouse user
  I want to do stock adjustments 
  So that I can

  @wip
  Scenario Outline: Stock adjustments of a locked/unlocked product with different reason codes
   	Given I have logged in as warehouse user in JDA dispatcher food application
    And I am on inventory query page
    And I have the tag id "<TagID>" with "<Status>" status
    
    When I navigate to stock adjustments page
    When I search the inventory details 
    Then the record should be displayed in the results
    When I navigate to create or modify tab 
    Then the product details should be displayed as expected from inventory
    When I "<AdjustmentType>" the quantity on hand  
    And I choose the reason code as <"ReasonCode">
    Then the stock adjustments home page should be displayed
    
    When I navigate to inventory transaction query
    And I search tag id "<TagID>" and code as "Adjustment"
    Then I should see the original quantity and updated quantity in the general tab
    When I navigate to miscellaneous tab
    Then I should see the readon code as "<ReasonCode>"
    When I navigate to miscellaneous2 tab
    Then I should see the uploaded filename
    
    Examples: 
      | TagID     |Status|AdjustmentType|ReasonCode|
      | 6327637212 |Locked|Decrement|Damaged by Warehouse|

