@boxed_direct_po_receiving_negative
Feature: Boxed - Receiving - Direct PO - Negative
  As a warehouse user
  I want to receive the articles that are damaged

   @jenkinsA @jenkinsrun @unique_boxed_receiving_direct_po_validate_damaged_on_receipt_from_supplier @direct_po @boxed @receiving @complete @ds @jenkins1
  Scenario: Validate damaged on receipt (From supplier) 
    Given the PO of type "Boxed" with UPI and ASN should be received at "REC001" with lock code damaged
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lockcode damaged

    @jenkinsA @unique_boxed_receiving_direct_po_validate_not_received_asn @direct_po @boxed @receiving @complete @ds
  Scenario: Validate not received ASN
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, po to be linked with upi line
    When I receive all skus for the purchase order with no asn at location "REC001"
    Then Error message should be displayed on the page

  