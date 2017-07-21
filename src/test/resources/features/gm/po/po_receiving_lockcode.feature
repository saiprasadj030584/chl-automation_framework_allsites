@purchase_order
Feature: Purchase order receiving with LOck code
  As a warehouse user
  I want to receive the locked articles
  But i cannot putaway the purchase order

  @receive_boxed_lock_code @po @wip
  Scenario Outline: Receiving process in JDA WMS for Hanging type with Lock Codes
    Given the PO "<PreAdviceID>" of type "Hanging" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "<LockCode>"
    When I receive all skus at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    And I should not be able to putaway locked PO

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      #| PO2010002821  | PO050456000511235821 | PO00100821 | QAFTS    | REC001   |
      #| PO2010002801 | PO050456000511235801 | PO00100801 | QAFTS    | REC001   |
      #| PO2010002802   | PO050456000511235802 | PO00100802 | QACOMP   | REC001   |
      #| PO2010002803   | PO050456000511235803 | PO00100813 | QAPC   | REC001   |
      #| PO2010002814   | PO050456000511236814 | PO00100214 | FWL   | REC001   |
      #| PO2010002805   | PO050456000511235805 | PO00100805 | REWORK   | REC001   |
      #| PO2010002806   | PO050456000511235806 | PO00100806 | QAFTSFWL   | REC001   |
      #| PO2010002807   | PO050456000511235807 | PO00100807 | QAPCFWL   | REC001   |
      | PO2010002808 | PO050456000511235808 | PO00100808 | QAFTSRW  | REC001   |
      | PO2010002809 | PO050456000511235809 | PO00100809 | QACOMPRW | REC001   |
      | PO2010002810 | PO050456000511235810 | PO00100810 | QAPCRW   | REC001   |
