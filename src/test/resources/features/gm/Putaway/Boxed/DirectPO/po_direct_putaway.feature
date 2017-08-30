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

  @boxed @putaway @direct_po @boxed_putaway_direct_po_putaway_validate_mezz_shelving_putaway @complete
  Scenario Outline: Validate Mezz/Shelving putaway
  
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" containing "MEZZ" sku and ASN "<ASN>" should be normal received at "<Location>"
    When I choose existing relocate
    And I proceed with entering the upc and location
    When I perform normal putaway after relocation
    Then the goods receipt should be generated for putaway stock in inventory transaction

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | PO2138927930 | PO050341140825945630 | PO01487730 | REC002   |

  
  
  @boxed @putaway @direct_po @boxed_putaway_direct_po_sampling_qa_build @complete
  Scenario Outline: Validate Sampling/QA Pallet build
 Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be received at "<Location>" for qa build
    When I choose existing relocate
    And I proceed with entering the upc and location
    When I perform normal putaway after relocation
    Then the goods receipt should be generated for putaway stock in inventory transaction

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | 4010002077 | 00050453000258616181 | 0000001343 | REC002   |
      
     