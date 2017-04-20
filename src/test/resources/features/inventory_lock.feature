@inventory_lock
Feature: Inventory Lock
  As a warehouse user
  I want to lock a product with lock code CODEAPP
  So that those invetories cannot be used for allocation

  @wip
  Scenario: Validate Inventory table in JDA WMS dispatcher
    #Given I have logged in as warehouse user in JDA dispatcher food application
    And I am on inventory query page
    #And I have the tag id "1000000040" with "Unlocked" status
    When I navigate to inventory update page
    And I choose the type of inventory property as "Lock Status Change"
    And I search the tag ID "1000000040"
    Then the record should be displayed in the search result
    
    When I proceed to lock the record
    And I select the status as "Locked", lock code as "Code Approval" and reason code as "Damaged by Warehouse"
    #And I proceed to complete the process
    #Then the inventory update home page should be displayed
    #
    #When I navigate to inventory query
    #Then the I should see the status as "Locked" and lock code as "CODEAPP" in the inventory query
    #
    #When I navigate to inventory transaction query
    #And I search tag id "1000000046" and transaction date as "18/04/2017"
    #Then the I should see the status as "Locked" and lock code as "CODEAPP" in the transaction query
    #When I navigate to miscellaneous tab
    #Then I should see the readon code as "<ReasonCode>"
    #When I navigate to miscellaneous2 tab
    #Then I should see the uploaded filename
