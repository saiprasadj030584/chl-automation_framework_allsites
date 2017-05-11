@inventory_lock_po_receiving
Feature: Purchase order
  As a warehouse user
  I want to receive the articles 
  So that I can complete the purchase order

  @wip
  Scenario: Lock the inventory from unlocked status
    #Given I have logged in as warehouse user in JDA dispatcher food application
    When I navigate to inventory transaction query
    And I search tag id "7080001005" and select the code as "Inventory Lock"
    Then the  description,lock status, reference and lock code should be displayed
    When I navigate to miscellaneous tab
    Then the reason code, supplier and RDT user mode should be displayed
    When I navigate to miscellaneous2 tab
    Then the uploaded status and uploaded file should be displayed
    When i navigate to user defined tab
    Then the ABV should be displayed
