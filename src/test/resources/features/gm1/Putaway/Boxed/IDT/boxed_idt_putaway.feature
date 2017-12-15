@boxed_idt_putaway
Feature: Boxed - IDT - Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  @boxed @putaway @idt @unique_boxed_putaway_idt_validate_mezz/shelving_putaway @complete1 @ds
  Scenario: Validate Mezz/Shelving putaway
    Given the UPI and ASN should be in "Released" status for IDT
    And ASN and container to be linked with upi header
    And the UPI should have MEZZ sku, quantity due details IDT
    When I perform receiving for all skus at location "REC002" for IDT
    And the goods receipt should be generated for IDT received stock in inventory transaction
    When I choose existing relocate
    And I proceed with entering the returns upc and location
    When I perform normal putaway after relocation
    Then the goods receipt should be generated for putaway IDT stock in inventory transaction

  @yes @boxed @putaway @idt @unique_boxed_putaway_idt_validate_sampling/qa_pallet_build @complete1 @ds
  Scenario: Validate Sampling/QA Pallet build
    Given the UPI and ASN should be in "Released" status for IDT
    And ASN and container to be linked with upi header
    And the upi should have sku, quantity due details
    When I perform receiving for all skus at location "REC002" for IDT
    When I choose existing relocate
    And I proceed with entering the returns upc and location
    When I perform normal returns putaway after relocation
    Then the goods receipt should be generated for putaway IDT stock in inventory transaction

  @unique_boxed_putaway_idt_validate_putaway_quantity @boxed @putaway @idt @complete1 @ds 
  Scenario: Validate Putaway quantity
    Given the UPI and ASN of type "Boxed" should be received at location "REC001" for IDT
    When I choose normal putaway
    And I proceed without entering IDT quantity
    Then the error message should be displayed as invalid quantity exception

  @unique_boxed_putaway_idt_validate_putaway_location @boxed @putaway @idt @complete1 @ds 
  Scenario: Validate Putaway Location
    Given the UPI and ASN of type "Boxed" should be received at location "REC001" for IDT
    When I choose normal putaway
    And I proceed without entering IDT location
    Then the warning message should be displayed

  @unique_boxed_putaway_idt_validate_putaway_logic_for_receiving_singles_when_locations_full @boxed @idt @putaway @compete1 @ds 
  Scenario: Validate Putaway Logic for receiving singles when locations full
    Given the UPI and ASN of type "Boxed" should be received at location "REC001" for IDT
    When I choose normal putaway
    And I proceed by entering less quantity for IDT
    Then the ITL should be generated for putaway stock in inventory transaction

  @unique_boxed_putaway_idt_validate_override_putaway_location @boxed @idt @putaway @compete1 @ds 
  Scenario: Validate Override Putaway Location for IDT-Boxed
    Given the UPI and ASN of type "Boxed" should be received at location "REC001" for IDT
    When I choose normal putaway
    And I proceed by overriding the location  "REC001" for IDT
    And the goods receipt should be generated for putaway stock in inventory transaction for override

  @boxed @putaway @idt @unique_boxed_putaway_idt_validate_putaway_process @complete1 @ds
  Scenario: Validate Putaway Process
    Given the UPI and ASN should be in "Released" status for IDT
    And ASN and container to be linked with upi header
    And the upi should have sku, quantity due details
    When I perform receiving for all skus at location "REC002" for IDT
    When I choose existing relocate
    And I proceed with entering the returns upc and location
    When I perform normal returns putaway after relocation
    Then the goods receipt should be generated for putaway IDT stock in inventory transaction

  @boxed @putaway @idt @unique_boxed_putaway_idt_validate_hazardous_putaway_location @complete1 @ds
  Scenario Outline: Validate Hazardous Putaway location
    Given the UPI with "Boxed" skus and ASN should be in "Released" status for IDT
    And ASN and container to be linked with upi header
    And the upi should have hazardous sku, quantity due details
    When I perform receiving for all skus at location "REC002" for IDT
    When I choose existing relocate
    And I proceed with entering the returns upc and location
    When I perform normal returns putaway after relocation for hazardous product
    Then the goods receipt should be generated for putaway IDT stock in inventory transaction
    
    @yes @boxed @putaway @idt @boxed_putaway_idt_validate_maximum_locations_per_aisle_per_upc @complete1 @ds @max
  Scenario: Validate maximum locations per Aisle per UPC
    Given the IDT PO of type "Boxed" with UPI and ASN should be received at "REC001" for maximum aisle
    When I choose existing relocate
    And I proceed with entering the returns upc and location
     When I perform normal returns putaway after under receiving and relocation
     Then the goods receipt should be generated for IDT split received stock in inventory transaction
    
