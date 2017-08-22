@purchase_order_putaway
Feature: Purchase order Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  @boxed_direct_po_putaway_location_full @boxed @direct_po @putaway @review
  Scenario Outline: Validate Putaway Logic for receiving singles when locations full
  	Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>"
    When I choose existing relocate
    And I proceed with entering the palletid
    When I choose normal putaway
    And I proceed by entering less quantity
    Then the ITL should be generated for putaway relocated stock in inventory transaction

    Examples: 
      | PreAdviceID | PalletId             | ASN        | Location |
      #|  1090009006 | 00050456000253606176 | 0000002809 | REC001   |
      |1040019012 |00050473610258814145|0000002763| REC001   |

  @boxed_direct_po_putaway_location_override @boxed @direct_po @putaway
  Scenario Outline: Validate Override Putaway Location
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>" 
    When I choose existing relocate
    And I proceed with entering the palletid
    When I choose normal putaway
    And I proceed by overriding the location  "<Location>"
    And the goods receipt should be generated for putaway stock in inventory transaction
    Examples: 
      | PreAdviceID | PalletId             | ASN        | Location |
      | 1040009049 | 00050456000253606117 |0000003862| REC001   |
