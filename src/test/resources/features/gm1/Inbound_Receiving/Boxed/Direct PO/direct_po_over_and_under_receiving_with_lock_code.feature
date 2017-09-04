@purchase_order_receiving
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles
  So that I can putaway the purchase order

  @boxed_inbound_receiving_direct_po_over_receiving_with_lock_code @complete @boxed @inbound_receiving @complete
  Scenario Outline: Validate Over receiving with lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I perform "Over Receiving" for all skus at location "<Location>"
    Then the error message should be displayed as cannot over receipt

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      | PO2010002832 | PO050456000511235832 | PO00100832 | QAFTS    | REC001   |

  @boxed_inbound_receiving_direct_po_under_receiving_with_lock_code @complete @boxed @inbound_receiving @complete
  Scenario Outline: Validate Under receiving with lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I perform "Under Receiving" for all skus at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "In Progress"

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      | PO2010002905 | PO050456000511235709 | PO00100905 | QAFTS    | REC001   |
