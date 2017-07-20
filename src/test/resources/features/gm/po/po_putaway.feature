@purchase_order_putaway
Feature: Purchase order Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  @po_putaway_hanging @po @complete
  Scenario Outline: Putaway process in JDA WMS for Hanging type
    Given the PO "<PreAdviceID>" of type "Hanging" with UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>"
    When I do normal putaway for all tags received
    Then the inventory should be displayed for all putaway tags
    And the goods receipt should be generated for putaway stock in inventory transaction

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | PO2010002001 | PO050456000511235610 | PO00100500 | REC001   |

  @po_putaway_boxed @po @complete
  Scenario Outline: Putaway process in JDA WMS for Boxed type
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>"
    When I do normal putaway for all tags received
    Then the inventory should be displayed for all putaway tags
    And the goods receipt should be generated for putaway stock in inventory transaction

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | PO2010002001 | PO050456000511235610 | PO00100500 | REC001   |

  @po_putaway_hanging_field_validation @po @complete
  Scenario Outline: Putaway process in JDA WMS for Boxed type
    Given the PO "<PreAdviceID>" of type "Hanging" with UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>"
    When I choose normal putaway
    And I proceed without entering location
    Then the error message should be displayed as cannot find putaway location
    And I proceed without entering quantity
    Then the error message should be displayed as invalid quantity exception

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | PO2010003010 | PO050456000511235711 | PO00100601 | REC001   |
