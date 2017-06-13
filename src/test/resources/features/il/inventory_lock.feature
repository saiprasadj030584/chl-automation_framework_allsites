@inventory_lock
Feature: Inventory Lock
  As a warehouse user
  I want to lock a product with lock code CODEAPP
  So that those invetories cannot be used for allocation

  @complete @il @po_demo
  Scenario Outline: Lock the inventory from unlocked status
    Given I have logged in as warehouse user in JDA dispatcher food application
    And I have a tag id with the "UnLocked" status in inventory
    When I navigate to inventory update page
    And I choose the type of inventory property as "Lock Status Change"
    And I search the tag ID with location
    Then the record should be displayed in the search result
    When I proceed to lock the record
    And I select the status as "Locked", lock code as "<LockCode>" and reason code as "Head Office Request"
    And I proceed to complete the process
    Then the inventory update home page should be displayed
    Then I should see the updated status as "Locked" and lock code as "<LockCode>" in the inventory query
    Then inventory transaction should be updated with "Locked" status, reason code "HOREQ" and transaction details

    Examples: 
      | LockCode                              |
      | Code Approval                         |
      #| Components Stock                      |
      #| 1Damaged                              |
      #| EVENTS                                |
      #| Pick exception lock code              |
      #| 1Expired                              |
      #| Head Office Request                   |
      #| Lock code for new vintage or new wine |
      #| Outlets Stock                         |
      #| Product Recall                        |
      #| Return from RDC                       |
      #| Supplier Damage                       |
      #| Return to Supplier                    |
      #| Warehouse Damage                      |
      #| Hampers Stock                         |
      #| Incubation lock code                  |
