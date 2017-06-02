@inventory_lock
Feature: Inventory Lock
  As a warehouse user
  I want to lock a product with lock code CODEAPP
  So that those invetories cannot be used for allocation

  @wip02
  Scenario Outline: Lock the inventory from unlocked status
    Given I have logged in as warehouse user in JDA dispatcher food application
    And I have tag id "<tagID>" with the "Unlocked" status
    When I navigate to inventory update page
    And I choose the type of inventory property as "Lock Status Change"
    And I search the tag ID "<tagID>" with location
    Then the record should be displayed in the search result
    When I proceed to lock the record
    And I select the status as "Locked", lock code as "<lockCode>" and reason code as "Head Office Request"
    And I proceed to complete the process
    Then the inventory update home page should be displayed
    Then I should see the updated status as "Locked" and lock code as "<lockCode>" in the inventory query page
    
    
    Then I should see the status as"Locked",reason code as "HOREQ",and uploaded filename for the "<tagID>",code "Inventory Lock", and lock code "<lockCode>"
    And I search tag id "<tagID>", code as "Inventory Lock" and lock code as "<lockCode>"
    Then I should see the status as "Locked" in the transaction query
    When I navigate to miscellaneous tab
    Then I should see the readon code as "HOREQ"
    When I navigate to miscellaneous2 tab
    Then I should see the uploaded filename
