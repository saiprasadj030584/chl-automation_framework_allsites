@purchase_order_putaway
Feature: Purchase order Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  @po_location_full
  Scenario Outline: Putaway process in JDA WMS for Hanging type
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>" for full location
    When I choose existing relocate
    And I proceed with entering the palletid
    When I choose normal putaway
    And I proceed by entering less quantity
    Then the inventory should be displayed for all putaway tags
    And the goods receipt should be generated for putaway stock in inventory transaction

    Examples: 
      | PreAdviceID | PalletId             | ASN        | Location |
      |  1090009172 | 00050456000253606127 | 0000112889 | REC001   |
