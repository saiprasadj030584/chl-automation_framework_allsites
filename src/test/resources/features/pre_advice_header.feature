@purchase_order
Feature: purchase order validation
  As a warehouse user
  I want to validate purchase order
  So that I can use them for

  @complete
  Scenario: Load the pre-advice header details
    Given the PO "8050004526" should be "In Progress" status and have future due date, site id, number of lines
    And the PO should have address details in the pre-advice header maintenance table
    Then the supplier should have supplier pallet details in the address maintenanace table
