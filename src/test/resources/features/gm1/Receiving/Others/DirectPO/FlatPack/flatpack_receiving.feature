@receiving
Feature: Receiving
  As a warehouse user
  I want to receive the articles

  @flatpack_receiving_direct_po_validate_damaged_on_receipt_from_supplier @direct_po @flatpack @receiving @scripting @allocation7_check @check11
  Scenario: Receipt reversal process in JDA WMS for Hanging type without lock code - Boxed
    Given the PO of type "Flatpack" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all "Flatpack" skus for the purchase order at location "REC001"
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be "Complete" while upi and asn status should "Complete"
    When I navigate to inventory update page
    And I select the update type as "Lock Status Change"
    And I search the inventory for the tag
    Then the tag details should be displayed
    And I select the status as "Locked" and lock code as "1Damaged"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lockcode damaged
