@dock_scheduler
Feature: Purchase order receipt reversal
  As a warehouse user
  I want to receive the articles
  So that I can reverse the received purchase order

  @po_receipt_reversal_hanging_withoutlockcode
  Scenario Outline: Receipt reversal process in JDA WMS for Hanging type without lock code
    Given the PO "<PreAdviceID>" of type "Hanging" with UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>"
    When I navigate to receipt reversal page
    And I do receipt reversal for the tag received
    Then the inventory transaction should be updated with reversed receipt tag

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      #|   2010002111 | 00050456000511235601 | 0000100508 | REC001 |
      #| PO2010002001 | PO050456000511235610 | PO00100500 | REC001   |
      #| PO2010002002 | PO050456000511235611 | PO00100501 | REC001   |
      #| PO2010003001 | PO050456000511235710 | PO00100600 | REC001   |
      #| PO2010002004 | PO050456000511235613 | PO00100536 | REC001   |
      #| PO2010002033 | PO000504560005112377 | PO00100536 | REC001   |
      | PO2010002045 | PO000504560005112385 | PO00100545 | REC001   |
