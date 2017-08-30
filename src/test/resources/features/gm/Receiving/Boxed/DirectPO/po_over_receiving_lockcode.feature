@purchase_order
Feature: Purchase order receiving with Lock code
  As a warehouse user
  I want to receive more locked articles
  But i cannot putaway the purchase order

  @over_receiving_with_lock_code @complete
  Scenario Outline: Over receiving process in JDA WMS for Hanging type with Lock Codes
    Given the PO "<PreAdviceID>" of type "Hanging" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I perform "Over Receiving" for all skus at location "<Location>"
    Then the error message should be displayed as cannot over receipt

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      | PO2010002832 | PO050456000511235832 | PO00100832 | QAFTS    | REC001   |
