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
    #When I perform normal returns putaway
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




  @boxed @putaway @returns @boxed_putaway_returns_sampling_qa_build @complete
  Scenario Outline: Validate Sampling/QA Pallet build
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order at "<Location>" with perfect condition "<Condition>"
    When I perform normal returns putaway
    Then the goods receipt should be generated for putaway returns stock in inventory transaction

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 56490000357211524200009081900301 | 0000001736 | REC002   | N         |
      
     