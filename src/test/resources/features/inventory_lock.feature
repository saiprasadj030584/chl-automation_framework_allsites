@inventory_lock
Feature: Inventory Lock
  As a warehouse user
  I want to lock a product with lock code CODEAPP
  So that those invetories cannot be used for allocation

  @wip
  Scenario Outline: Lock the inventory from unlocked status
    Given I have logged in as warehouse user in JDA dispatcher food application
    And I am on inventory query page
    And I have the tag id "<tagID>" with "<fromStatus>" status
    When I navigate to inventory update page
    And I choose the type of inventory property as "Lock Status Change"
    And I search the tag ID "<tagID>"
    Then the record should be displayed in the search result
    When I proceed to lock the record
    And I select the status as "<toStatus>", lock code as "<lockCode>" and reason code as "<reasonCode>"
    And I proceed to complete the process
    Then the inventory update home page should be displayed
    When I navigate to inventory query
    Then the I should see the updated status and lock code in the inventory query
    When I navigate to inventory transaction query
    And I search tag id "<tagID>", code as "Inventory Lock" and lock code as "<lockCode>"
    Then I should see the status as "<toStatus>" in the transaction query
    When I navigate to miscellaneous tab
    Then I should see the readon code as "<reasonCode>"
    When I navigate to miscellaneous2 tab
    Then I should see the uploaded filename

    Examples: 
      | tagID      | fromStatus | toStatus | lockCode      | reasonCode          |
      | 1000000040 | Unlocked   | Locked   | Code Approval | Damage by Warehouse |
      
