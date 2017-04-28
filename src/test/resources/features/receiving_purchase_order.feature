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

  @pre_advice_line_table
  Scenario: Load the pre-advice line details
    Given the PO "8050004526" should be "In Progress" status and have future due date, site id, number of lines in the pre-advice header maintenance table
    And the PO should have address details
    And the supplier should have supplier pallet details
    And the PO should have the SKU, Qty due, Tracking level, Pack config, Under bond, case ratio, base UOM details for each pre-advice line items
