@purchase_order
Feature: Purchase order receiving with Lock code
  As a warehouse user
  I want to receive less locked articles
  But i cannot putaway the purchase order

 @under_receiving_with_lock_code
  Scenario Outline: under receiving process in JDA WMS for Hanging type with Lock Codes
    Given the PO "<PreAdviceID>" of type "Hanging" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I perform "Under Receiving" for all skus at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "In Progress"

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
     # | PO2010002821  | PO050456000511235821 | PO00100821 | QAFTS    | REC001   |
      #| PO2010002901 | PO050456000511235901 | PO00100901 | QAFTS    | REC001   |
      | PO2010002905   | PO050456000511235709 | PO00100905 | QAFTS   | REC001   |
