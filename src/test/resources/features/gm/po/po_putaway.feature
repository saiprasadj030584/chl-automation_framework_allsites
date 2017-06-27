@purchase_order_putaway
Feature: Purchase order
  As a warehouse user
  I want to receive and putaway the articles
  So that I can complete the purchase order

  @po_putaway_hanging @po @complete
  Scenario Outline: Receiving process in JDA WMS for Hanging type
    Given the PO "<PreAdviceID>" of type "Hanging" with UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>"
    When I do normal putaway for all tags received
    Then the inventory should be displayed for all putaway tags
    And the goods receipt should be generated for putaway stock in inventory transaction

    Examples: 
      | PreAdviceID | PalletId | ASN | Location |

  #|   2010002111 | 00050456000511235601 | 0000100508 | REC001 |
  #| PO2010002001 | PO050456000511235610 | PO00100500 | REC001   |
  
  @po_putaway_boxed @po @complete
  Scenario Outline: Receiving process in JDA WMS for Hanging type
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>"
    When I do normal putaway for all tags received
    Then the inventory should be displayed for all putaway tags
    And the goods receipt should be generated for putaway stock in inventory transaction

    Examples: 
      | PreAdviceID | PalletId | ASN | Location |
      #|   2010002111 | 00050456000511235601 | 0000100508 | REC001 |
      #| PO2010002001 | PO050456000511235610 | PO00100500 | REC001   | 
