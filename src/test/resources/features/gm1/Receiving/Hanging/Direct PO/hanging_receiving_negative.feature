@po_receiving_negative
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles that are damaged

  @hanging_receiving_direct_po_validate_damaged_on_receipt_from_supplier @direct_po @hanging @receiving @complete @ds
  Scenario: Validate damaged on receipt (From supplier) 
    Given the PO of type "Hanging" with UPI and ASN should be received at "REC001"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lockcode damaged

  @hanging_receiving_direct_po_validate_not_received_ASN @direct_po @hanging @receiving @complete @ds @jenkinsB
  Scenario: Validate not received ASN
    Given the PO of type "Hanging" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, po to be linked with upi line
    When I receive all skus for the purchase order with no asn at location "REC001"
    Then Error message should be displayed on the page

  