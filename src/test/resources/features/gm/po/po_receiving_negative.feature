@po_receiving_negative
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles that are damaged
  
   @validate_damage_receipt_from_supplier_boxed @po @complete
  Scenario Outline: Receipt reversal process in JDA WMS for Hanging type without lock code - Boxed
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lockcode damaged

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | PO2010002061 | PO000504560005112391 | PO00100551 | REC001   |
  
  
  @validate_not_received_ASN_hanging @po @complete
  Scenario Outline: Receiving when Pre advice line quantity is greater than the UPI line quantity - Hanging
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, po to be linked with upi line
    When I receive all skus for the purchase order with no asn at location "<Location>"
    Then Error message should be displayed on the page

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      #|   2010002111 | 00050456000511235601 | 0000100508 | REC001 |
      #| PO2010002001 | PO050456000511235610 | PO00100500 | REC001   |
      #| PO2010002002 | PO050456000511235611 | PO00100501 | REC001   |
      #| PO2010003001 | PO050456000511235710 | PO00100600 | REC001   |
      | PO2010002069 | PO050456000511235619 | PO00100509 | REC001   |
      
      @validate_not_received_ASN_boxed @po @complete
  Scenario Outline: Receiving when Pre advice line quantity is greater than the UPI line quantity - Boxed
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, po to be linked with upi line
    When I receive all skus for the purchase order with no asn at location "<Location>"
    Then Error message should be displayed on the page

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      #|   2010002111 | 00050456000511235601 | 0000100508 | REC001 |
      #| PO2010002001 | PO050456000511235610 | PO00100500 | REC001   |
      #| PO2010002002 | PO050456000511235611 | PO00100501 | REC001   |
      #| PO2010003001 | PO050456000511235710 | PO00100600 | REC001   |
      | PO2010002069 | PO050456000511235619 | PO00100509 | REC001   |