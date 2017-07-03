@purchase_order_lockcode
Feature: Purchase order Putaway
  As a warehouse user
  I want to  lock the product with lockcode
  So that those product cannot be used for allocation
  
@lockcode
  Scenario Outline: Putaway process in JDA WMS for Hanging type
    Given the PO "<PreAdviceID>" of type "boxed" with UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>"
    And the PO is lock with lockcode "QAFTS" in pre advice line
    When I do normal putaway for all tags received for the lockcode "QAFTS"
    Then the  error message should be displayed
   
    Examples: 
      | PreAdviceID | PalletId | ASN | Location |

  |   2010002111 | 00050456000511235601 | 0000100508 | REC001 |
  | PO2010002001 | PO050456000511235610 | PO00100500 | REC001   |
  