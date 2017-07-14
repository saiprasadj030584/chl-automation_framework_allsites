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
      | PO2010002048 | PO000504560005112388 | PO00100548 | REC001   |

  @po_receipt_reversal_hanging_withQAFTSlockcode
  Scenario Outline: Receipt reversal process in JDA WMS for Hanging type without lock code
    Given the PO "<PreAdviceID>" of type "Hanging" with UPI "<PalletId>" and ASN "<ASN>" should be in "Complete" status and locked with code "<LockCode>"
    And the PO should be received at location "<Location>"
    When I navigate to receipt reversal page
    And I do receipt reversal for the tag received
    Then the inventory transaction should be updated with reversed receipt tag with lockcode

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | LockCode | Location |
      | PO2010002800 | PO050456000511235800 | PO00100800 | QAFTS    | REC001   |

  #| PO2010002801 | PO050456000511235801 | PO00100801 | QAFTS    | REC001   |
  #| PO2010002804 | PO050456000511235804 | PO00100804 | QAFTS    | REC001   |
  
  @validate_damage_receipt_from_supplier
  Scenario Outline: Receipt reversal process in JDA WMS for Hanging type without lock code
    Given the PO "<PreAdviceID>" of type "Hanging" with UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lockcode Damaged

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | PO2010002061 | PO000504560005112391 | PO00100551 | REC001   |
