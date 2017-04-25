@inventory_abv_update
Feature: ABV update
   As a warehouse user
   I want to update the ABV percentage
   So that the correct value is updated

  @wip2
  Scenario: To update the ABV through Inventory table in JDA WMS dispatcher
    #Given I have logged in as warehouse user in JDA dispatcher food application
    And I navigate to inventory query page
    And I have SKU id, product group and ABV for the tag id "1000000007"
    When I navigate to inventory update page
    And I choose the type of inventory property as "User Defined Update"
    And I search the tag ID "1000000007"
    Then the record should be displayed in the search result
    When I enter ABV value as "13.5"
    And I select the reason code as "ABV change"
    And I proceed to complete the process
    Then the inventory update home page should be displayed
    When I navigate to inventory query
    Then I should see the updated ABV in the inventory query page
    When I navigate to inventory transaction query
    And I search tag id "1000000007" with transaction code as "User Defined Update"
    Then the SKU Id and Reference should be displayed
    When I navigate to miscellaneous tab
    Then I should see the reason code as "ABV"
    When I navigate to user defined tab
    Then I should see the updated ABV in the inventory transaction query page
