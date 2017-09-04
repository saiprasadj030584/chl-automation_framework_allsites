@purchase_order_receiving
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles
  So that I can putaway the purchase order

  @boxed_receiving_direct_po_validate_full_pallet_with_N @boxed @direct_po @receiving @complete
  Scenario Outline: Validate pallet build for full pallet - Boxed type
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "<Location>" with full pallet "<FullPallet>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location | FullPallet |
      | PO2010002227 | PO050456000511235649 | PO00100576 | REC001   | N          |
      
      
  @boxed_receiving_direct_po_validate_full_pallet_with_Y @boxed @direct_po @receiving @complete
  Scenario Outline: Validate pallet build for full pallet - Boxed type
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "<Location>" with full pallet "<FullPallet>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location | FullPallet |
      | PO2010002003 | PO000504560005112357 | PO00001005 | REC001   | Y          |
