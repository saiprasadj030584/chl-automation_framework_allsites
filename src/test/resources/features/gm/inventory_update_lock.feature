@invetory_update
Feature: Inventory update
  As a warehouse user
  I want to update the inventory for the item 
  So that I can be used for allocation

  @lock_to_Unlock
  Scenario Outline: Inventory update from lock to unlock
    Given I have tagid"<tagID> ",sku"<sku>",locationid "<Location>"in inventory with the status "LOCK"
    When I navigate to inventory update page
    And i enter the tagID, SKU,location
    Then the record should be displayed
    And I select the status as "unlock"
    When I navigate to inventory transaction query page
    And I select the code as "inventory unlock" and enter the  tag id
    Then the status should be updated

    Examples: 
      | tagID                | sku                | Location |
      | 02050456000245132152 | 000000021217868003 | REWORK   |
