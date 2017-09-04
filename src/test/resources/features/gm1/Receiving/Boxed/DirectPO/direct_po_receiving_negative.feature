@po_receiving_negative
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles that are damaged

  @boxed_receiving_direct_po_validate_damage_receipt_from_supplier @direct_po @boxed @receiving @complete
  Scenario Outline: Receipt reversal process in JDA WMS for Hanging type without lock code - Boxed
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lockcode damaged

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | PO2010002061 | PO000504560005112391 | PO00100551 | REC001   |


  @boxed_receiving_direct_po_validate_not_received_ASN_boxed @direct_po @boxed @receiving @complete
  Scenario Outline: Receiving when Pre advice line quantity is greater than the UPI line quantity - Boxed
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, po to be linked with upi line
    When I receive all skus for the purchase order with no asn at location "<Location>"
    Then Error message should be displayed on the page

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | PO2010002069 | PO050456000511235619 | PO00100509 | REC001   |

  