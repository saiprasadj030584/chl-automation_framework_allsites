@invetory_update
Feature: Inventory update
  As a warehouse user
  I want to update the inventory for the item 
  So that I can be used for allocation

  @complete
  Scenario: Inventory expiry date update
    Given I have logged in as warehouse user in JDA dispatcher food application
    And the inventory should be expired for SKU id "20001221"
    And the quantity on hand should be more than allocated quantity for tag id "2000122101"
    When I navigate to inventory update page
    And I select the type of invetnotry status as "Expiry Date Update"
    When I navigate to search tab
    And I search the tag id "2000122101"
    And I proceed to next
    And I select the expiry date in future and reason code as "Minimum expiry update"
    And I proceed to complete the process
    When I navigate to inventory transaction query
    And I select the code as "Expiry Update" and enter the tag id "2000122101"
    Then I should see the future expiry date and reason code in the miscellaneous tab
    
    @complete
  Scenario: To update the ABV through Inventory table in JDA WMS dispatcher
    Given I have logged in as warehouse user in JDA dispatcher food application
    And I navigate to inventory query page
    And I have SKU id, product group and ABV for the tag id "1000000012"
    When I navigate to inventory update page
    And I choose the type of inventory property as "User Defined Update"
    And I search the tag ID "1000000012"
    Then the record should be displayed in the search result
    When I enter ABV value as "13.5"
    And I select the reason code as "ABV change"
    And I proceed to complete the process
    Then the inventory update home page should be displayed
    When I navigate to inventory query
    Then I should see the updated ABV in the inventory query page
    When I navigate to inventory transaction query
    And I search tag id "1000000012" with transaction code as "User Defined Update"
    Then the SKU Id and Reference should be displayed
    When I navigate to miscellaneous tab
    Then I should see the reason code as "ABV"
    When I navigate to user defined tab
    Then I should see the updated ABV in the inventory transaction query page
