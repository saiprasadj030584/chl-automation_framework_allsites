@flatpack_direct_po_receiving
Feature: Flatpack - Direct PO - Receiving
  As a warehouse user
  I want to validate receiving
  
  @flatpack_receiving_direct_po_validate_manual_receipt @flatpack @receiving @direct_po @complete @ds
  Scenario: Validate manual receipt
    Given the PO of type "Flatpack" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all "Flatpack" skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received of flatpack type
    And the goods receipt should be generated for flatpack received stock in inventory transaction
    Then the po status should be displayed as "Complete"
