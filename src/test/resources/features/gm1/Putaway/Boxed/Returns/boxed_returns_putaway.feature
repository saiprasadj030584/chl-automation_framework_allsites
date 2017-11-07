@boxed_returns_putaway
Feature: Boxed - Returns - Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  @boxed @putaway @returns @boxed_putaway_returns_validate_mezz/shelving_putaway @complete @ds
  Scenario: Validate Mezz/Shelving putaway
    Given the UPI and ASN should be in "Released" status
    And the upi should have MEZZ sku, quantity due details
    And I receive all skus for the returns order at "REC002" with perfect condition "Y"
    When I choose existing relocate
    And I proceed with entering the pallet id,upc and location
    When I perform normal returns putaway
    When I perform normal putaway after relocation
    Then the goods receipt should be generated for putaway returns stock in inventory transaction

  @boxed @putaway @returns @boxed_putaway_returns_validate_returns_putaway_type @complete @ds
  Scenario: Validate Returns Putaway type
    Given the UPI and ASN should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order at "REC002" with perfect condition "Y"
    When I perform normal returns putaway
    Then the goods receipt should be generated for putaway returns stock in inventory transaction

  @boxed_putaway_returns_validate_putaway_location @returns @complete @putaway @boxed @ds
  Scenario: Validate Putaway Location
    Given the UPI and ASN of type "Boxed" should be received at location "REC003" and "Y" at site
    When I choose normal putaway
    And I proceed without entering location for returns
    Then the warning message should be displayed for returns

  @boxed_putaway_returns_validate_putaway_quantity @returns @complete @putaway @boxed @ds
  Scenario: Validate Putaway quantity
    Given the UPI and ASN of type "Boxed" should be received at location "REC003" and "Y" at site
    When I choose normal putaway
    And I proceed without entering quantity for returns
    Then the error message should be displayed as invalid quantity exception

  @boxed @putaway @returns @boxed_putaway_returns_sampling_qa_build @hold
  Scenario: Validate Sampling/QA Pallet build
    Given the UPI and ASN should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order at "REC002" with perfect condition "N"
    When I perform normal returns putaway
    Then the goods receipt should be generated for putaway returns stock in inventory transaction

  @boxed_putaway_returns_validate_returns_putaway_to_preferred_aisle @returns @boxed @putaway @complete @ds
  Scenario: Validate Returns Putaway to preferred aisle
    Given the UPI and ASN of type "Boxed" should be received at location "REC003" and "Y" at site
    When I choose normal putaway for returns
    And the goods receipt should be generated for putaway stock in inventory transaction

  @boxed_putaway_returns_validate_override_putaway_location @boxed @returns @putaway @complete @ds
  Scenario: Validate Override Putaway Location
    Given the UPI and ASN of type "Boxed" should be received at location "REC003" and "Y" at site
    When I choose normal putaway
    And I proceed by overriding the location  "REC003" for returns
    And the goods receipt should be generated for putaway stock in inventory transaction for override

  @returns_boxed_putaway_location_full @boxed @returns @boxed @putaway @onhold
  Scenario: Validate Putaway Logic for receiving singles when locations full
    Given the UPI and ASN of type "Boxed" should be received at location "REC003" and "Y" at site
    When I choose existing relocate
    And I proceed with entering the palletid
    When I choose normal putaway
    And I proceed by entering less quantity for IDT
    Then the ITL should be generated for putaway stock in inventory transaction

  @boxed @putaway @returns @boxed_putaway_returns_validate_putaway_qc_goods @complete @ds
  Scenario: Validate Putaway QC goods
    Given the UPI and ASN should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus for the returns order at "REC002" with perfect condition "N"
    When I choose existing relocate
    And I proceed with entering the returns upc and location
    When I perform normal returns putaway after relocation
    Then the goods receipt should be generated for putaway returns stock in inventory transaction
