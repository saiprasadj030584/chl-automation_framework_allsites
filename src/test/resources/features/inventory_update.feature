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

  @wip
  Scenario: Inventory is updated after the transaction
    #Given I have logged in as warehouse user in JDA dispatcher food application
    When I navigate to inventory query page and search the tag id "8050035478"
    Then the quantity on hand, location, site id and status should be displayed in the general tab
    And the expiry date, pallet id, receipt id and supplier details should be displayed in the miscellaneous tab
    And the storage location, base UOM and product groud should be displayed
    When I navigate to pre-advice header maintenance page
    Then the status should be displayed as "Complete"
