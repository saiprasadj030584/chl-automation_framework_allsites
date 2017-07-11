@purchase_order
Feature: Purchase order receiving with LOck code
  As a warehouse user
  I want to receive the locked articles
  But i cannot putaway the purchase order

  @receive_boxed_lock_code @po @wip
  Scenario Outline: Receiving process in JDA WMS for Hanging type with Lock Codes
    Given the PO "<PreAdviceID>" of type "Hanging" with UPI "<PalletId>" and ASN "<ASN>" should be in "Complete" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      #| PO2010002800  | PO050456000511235800 | PO00100800 | QAFTS    | REC001   |
      #| PO2010002801 | PO050456000511235801 | PO00100801 | QAFTS    | REC001   |
      | PO2010002802   | PO050456000511235802 | PO00100802 | QACOMP   | REC001   |
