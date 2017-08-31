@receipt_reversal
Feature: Purchase order receipt reversal
  As a warehouse user
  I want to receive the articles
  So that I can reverse the received purchase order

      @boxed_receiving_direct_po_receipt_reversal_boxed_withoutlockcode @boxed @direct_po @receiving @complete
  Scenario Outline: Receipt reversal process in JDA WMS for Boxed type without lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>"
    When I navigate to receipt reversal page
    And I do receipt reversal for the tag received
    Then the inventory transaction should be updated with reversed receipt tag

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | PO2010002048 | PO000504560005112388 | PO00100548 | REC001   |

  @boxed_receiving_direct_po_receipt_reversal_boxed_with_lockcode @boxed @direct_po @receiving @complete
  Scenario Outline: Receipt reversal process in JDA WMS for Boxed type with lock code
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Complete" status and locked with code "<LockCode>"
    And the PO should be received at location "<Location>"
    When I navigate to receipt reversal page
    And I do receipt reversal for the tag received
    Then the inventory transaction should be updated with reversed receipt tag with lockcode

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      | PO2010002800 | PO050456000511235800 | PO00100800 | QAFTS    | REC001   |
  
 
