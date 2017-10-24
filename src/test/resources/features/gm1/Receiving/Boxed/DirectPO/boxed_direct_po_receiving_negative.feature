@po_receiving_negative
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles that are damaged

  @jenkins_analysis @boxed_receiving_direct_po_validate_damaged_on_receipt_from_supplier @direct_po @boxed @receiving @complete @ds
  Scenario: Validate damaged on receipt (From supplier) 
    Given the PO of type "Boxed" with UPI and ASN should be received at "REC001"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lockcode damaged

  @jenkins_analysis @boxed_receiving_direct_po_validate_not_received_asn @direct_po @boxed @receiving @complete @ds
  Scenario: Validate not received ASN
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, po to be linked with upi line
    When I receive all skus for the purchase order with no asn at location "REC001"
    Then Error message should be displayed on the page

  