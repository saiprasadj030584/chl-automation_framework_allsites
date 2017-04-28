@receive_purchase_order
Feature: Receive purchase order
  As a warehouse user
  I want to receive the purchase order 
  So that I can be used for allocation

  @complete
  Scenario: Inventory is updated after the transaction
    Given I have logged in as warehouse user in JDA dispatcher food application
    When I navigate to inventory query page and search the tag id "8050035478"
    Then the quantity on hand, location, site id and status should be displayed in the general tab
    And the expiry date, pallet id, receipt id and supplier details should be displayed in the miscellaneous tab
    And the storage location, base UOM and product groud should be displayed
    When I navigate to pre-advice header maintenance page
    Then the status should be displayed as "Complete"

  Scenario: Load the pre-advice header details
    Given the PO "8050004526" should be "In Progress" status and have future due date, site id, number of lines
    And the PO should have address details in the pre-advice header maintenance table
    Then the supplier should have supplier pallet details in the address maintenanace table
