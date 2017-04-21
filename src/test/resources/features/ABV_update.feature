@wip
Feature: ABV  update
   As a warehouse user
   I want to update the ABV percentage
   So that the correct value is updated
   
 @wip
  Scenario: To update the ABV through Inventory table in JDA WMS dispatcher
    Given I have logged in as warehouse user in JDA dispatcher food application
    And I am on inventory query page
    When I enter the tag id "1000000007"
    Then the SKU Id should be displayed
    When I navigate to user defined tab
    Then the Product Group and ABV value should be displayed
    
    When I navigate to inventory update page
    And I choose the type of inventory property as "User Defined Update"
    And I search the tag ID "1000000007"
    Then the record should be displayed in the search result
    
    When I proceed to update the ABV value as "13.5"
    And I select the Reason code as "ABV change"
    And I proceed to complete the process
    Then the inventory update home page should be displayed
    
    When I navigate to inventory query page
    Then I should see the ABV value as "13.5" 
    
    When I navigate to inventory transaction query
    And I search tag id "1000000007" with transaction code as "User Defined Update" and transaction date as "22/04/2017"
    Then the SKU Id and Reference should be displayed
    When I navigate to miscellaneous tab
    Then I should see the readon code as "ABV"
    When I navigate to user defined tab
    Then the ABV value should be updated as "13.5"
    
    