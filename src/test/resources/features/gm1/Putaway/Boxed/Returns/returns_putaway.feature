@purchase_order_putaway
Feature: Purchase order Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  @boxed @putaway @returns @boxed_putaway_returns_putaway_validate_mezz_shelving_putaway @complete
  Scenario Outline: Validate Mezz/Shelving putaway
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And the upi should have MEZZ sku, quantity due details
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>"
    When I choose existing relocate
    And I proceed with entering the pallet id,upc and location
    When I perform normal returns putaway
    When I perform normal putaway after relocation
    Then the goods receipt should be generated for putaway returns stock in inventory transaction

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850000133660024310013546600700 | 0000004090 | REC002   | Y         |

  @boxed @putaway @returns @boxed_putaway_returns_putaway @complete
  Scenario Outline: Validate Returns Putaway type
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>"
    When I perform normal returns putaway
    Then the goods receipt should be generated for putaway returns stock in inventory transaction

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850008387380077010083856400300 | 0000838858 | REC002   | Y         |

  @boxed_putaway_returns_validate_putaway_location @returns @complete @putaway @boxed
  Scenario Outline: Validate Putaway Location
    Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" and "<Condition>" at site "<SiteId>"
    When I choose normal putaway
    And I proceed without entering location for returns
    Then the warning message should be displayed for returns

    Examples: 
      | PalletId                         | ASN        | Location | Condition | SiteId |
      | 58850004134450077210063862500366 | 0000765627 | REC003   | Y         |   5885 |

  @boxed_putaway_returns_validate_putaway_quantity @returns @complete @putaway @boxed
  Scenario Outline: Validate Putaway quantity
    Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" and "<Condition>" at site "<SiteId>"
    When I choose normal putaway
    And I proceed without entering quantity for returns
    Then the error message should be displayed as invalid quantity exception

    Examples: 
      | PalletId                         | ASN        | Location | Condition | SiteId |
      | 58850008286250077010083862500366 | 0000838662 | REC003   | Y         |   5885 |

  @boxed @putaway @returns @boxed_putaway_returns_sampling_qa_build @hold
  Scenario Outline: Validate Sampling/QA Pallet build
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>"
    When I perform normal returns putaway
    Then the goods receipt should be generated for putaway returns stock in inventory transaction

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 56490000357211524200009081900301 | 0000001736 | REC002   | N         |

  @returns_boxed_putaway_to_preferred_aisle @returns @boxed @putaway @complete
  Scenario Outline: Validate Returns Putaway to preferred aisle
    Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" and "<Condition>" at site "<SiteId>"
    When I choose normal putaway for returns
    And the goods receipt should be generated for putaway stock in inventory transaction

    Examples: 
      | PalletId                         | ASN        | Location | Condition | SiteId |
      | 58850004164350077210083862500366 | 0000765624 | REC003   | Y         |   5885 |

  @boxed_returns_putaway_location_override @boxed @idt @putaway @complete
  Scenario Outline: Validate Override Putaway Location
    Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" and "<Condition>" at site "<SiteId>"
    When I choose normal putaway
    And I proceed by overriding the location  "<Location>" for returns
    And the goods receipt should be generated for putaway stock in inventory transaction for override

    Examples: 
      | PalletId                         | ASN        | Location | Condition | SiteId |
      | 58850004243340077210083862500366 | 0000765586 | REC003   | Y         |   5885 |

  @returns_boxed_putaway_location_full @boxed @returns @boxed @putaway @onhold
  Scenario Outline: Validate Putaway Logic for receiving singles when locations full
    Given the UPI "<PalletId>" and ASN "<ASN>" of type "Boxed" should be received at location "<Location>" and "<Condition>" at site "<SiteId>"
    When I choose existing relocate
    And I proceed with entering the palletid
    When I choose normal putaway
    And I proceed by entering less quantity for IDT
    Then the ITL should be generated for putaway stock in inventory transaction

    Examples: 
      | PalletId                         | ASN        | Location | Condition | SiteId |
      | 58850004254250077210083862500362 | 0000765567 | REC003   | Y         |   5885 |

  @boxed @putaway @returns @boxed_putaway_returns_putaway_process @complete
  Scenario Outline: Validate Putaway Process
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>"
    When I perform normal returns putaway
    Then the goods receipt should be generated for putaway returns stock in inventory transaction

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850008387380077010083856400300 | 0000838858 | REC002   | Y         |
