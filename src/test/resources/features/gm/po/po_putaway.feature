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

  @boxed @putaway @direct_po @boxed_putaway_direct_po_putaway_validate_mezz_shelving_putaway @dryrun
  Scenario Outline: Validate Mezz/Shelving putaway
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be normal received at "<Location>"
    When I choose existing relocate
    And I proceed with entering the upc and location
    When I perform normal putaway after relocation
    Then the goods receipt should be generated for putaway stock in inventory transaction

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | PO2138927910 | PO050341140825945610 | PO01487710 | REC002   |

  @boxed @putaway @fsv_po @boxed_putaway_fsv_po_putaway_validate_mezz_shelving_putaway
  Scenario Outline: Validate Mezz/Shelving putaway
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be in "Released" status at site id "<SiteID>"
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line "<PreAdviceID>"
    When I receive all skus for the FSV purchase order at location "<Location>"
    When I choose existing relocate
    And I proceed with entering the upc and location
    When I perform normal putaway after relocation
    Then the goods receipt should be generated for putaway stock in inventory transaction

    Examples: 
      | PreAdviceID | Location | SiteID |
      | 25300110368 | REC002   |   5649 |

  @boxed @putaway @idt @boxed_putaway_idt_putaway_validate_mezz_shelving_putaway
  Scenario Outline: Validate Mezz/Shelving putaway
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be normal received at "<Location>"
    When I choose existing relocate
    And I proceed with entering the upc and location
    When I perform normal putaway after relocation
    Then the goods receipt should be generated for putaway stock in inventory transaction

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | PO2138924910 | PO050341140823935610 | PO01476610 | REC002   |

  @boxed @putaway @returns @boxed_putaway_returns_putaway_validate_mezz_shelving_putaway
  Scenario Outline: Validate Mezz/Shelving putaway
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>"
    When I choose existing relocate
    And I proceed with entering the upc and location
    When I perform normal putaway after relocation
    Then the goods receipt should be generated for putaway stock in inventory transaction

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850001251140077010012511400300 | 0000125114 | REC002   | Y         |
