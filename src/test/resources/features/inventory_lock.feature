@inventory_lock
Feature: Inventory Lock
  As a warehouse user
  I want to lock a product with lock code CODEAPP
  So that those invetories cannot be used for allocation

  @complete @il
  Scenario Outline: Lock the inventory from unlocked status
    Given I have logged in as warehouse user in JDA dispatcher food application
    And I have tag id "<tagID>" with the "UnLocked" status in inventory
    When I navigate to inventory update page
    And I choose the type of inventory property as "Lock Status Change"
    And I search the tag ID "<tagID>" with location
    Then the record should be displayed in the search result
    When I proceed to lock the record
    And I select the status as "Locked", lock code as "<lockCode>" and reason code as "Head Office Request"
    And I proceed to complete the process
    Then the inventory update home page should be displayed
    Then I should see the updated status as "Locked" and lock code as "<lockCode>" in the inventory query
    Then inventory transaction should be updated with "Locked" status, reason code "HOREQ" and transaction details

    Examples: 
      | tagID      | lockCode                              |
      | 7080001002 | Code Approval                         |
      |  708000101 | Components Stock                      |
      | 7080001002 | 1Damaged                              |
      | 7080001003 | EVENTS                                |
      | 7080001004 | Pick exception lock code              |
      | 7080001005 | 1Expired                              |
      | 7080001006 | Head Office Request                   |
      | 7080001007 | Lock code for new vintage or new wine |
      | 7080001008 | Outlets Stock                         |
      | 7080001009 | Product Recall                        |
      | 7080001010 | Return from RDC                       |
      | 7080001011 | Supplier Damage                       |
      | 7080001012 | Return to Supplier                    |
      | 7080001013 | Warehouse Damage                      |
      | 7080001014 | Hampers Stock                         |
      | 7080001016 | Incubation lock code                  |
