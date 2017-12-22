@boxed_direct_po_putaway
Feature: Boxed - Direct PO - Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  @yes @hanging @putaway @direct_po @unique_hanging_putaway_direct_po_validate_maximum_locations_per_aisle_per_upc @complete @ds @max
  Scenario: Validate maximum locations per Aisle per UPC
    Given the PO of type "Hanging" with UPI and ASN should be received at "REC001" for maximum aisle
    When I choose existing relocate
    And I proceed with entering the upc and location
    When I perform normal putaway after under receiving and relocation
    Then the goods receipt should be generated for putaway stock in inventory transaction

  @unique_hanging_putaway_direct_po_quantity_field_validation @direct_po @hanging @putaway @complete
  Scenario: Validate Putaway quantity
    Given the PO of type "Hanging" with UPI and ASN should be received at "REC001"
    When I choose normal putaway
    And I proceed without entering po quantity
    Then the error message should be displayed as invalid quantity exception

  @unique_hanging_putaway_direct_po_validate_putaway_logic_for_receiving_singles_when_locations_full @hanging @direct_po @putaway @compete
  Scenario: Validate Putaway Logic for receiving singles when locations full
    Given the PO of type "Hanging" with UPI and ASN should be received at "REC001"
    When I choose normal putaway
    And I proceed by entering less quantity
    Then the ITL should be generated for putaway relocated stock in inventory transaction

  @jenkinsB @unique_hanging_putaway_direct_po_validate_putaway_process @direct_po @hanging @putaway @complete
  Scenario: Validate Putaway Process
    Given the PO of type "Hanging" with UPI and ASN should be received at "REC001"
    When I choose normal putaway
    And I proceed with normal putaway
    Then the goods receipt should be generated for putaway stock in inventory transaction

  @jenkins_analysis @hanging @putaway @direct_po @unique_hanging_putaway_direct_po_validate_putaway_qc_goods @complete @ds
  Scenario: Validate Putaway QC goods
    Given the PO of type "Hanging" with UPI and ASN should be received at "REC001"
    When I choose existing relocate
    And I proceed with entering the upc and location
    When I perform normal putaway after relocation
    Then the goods receipt should be generated for putaway stock in inventory transaction

  @hanging @putaway @direct_po @unique_hanging_putaway_direct_po_validate_mezz/shelving_putaway @complete @ds
  Scenario: Validate Mezz/Shelving putaway
    Given the PO of type "Hanging" with UPI containing "MEZZ" sku and ASN should be normal received at "REC002"
    When I choose existing relocate
    And I proceed with entering the upc and location for MEZZ sku
    When I perform normal putaway after relocation for MEZZ skus
    Then the goods receipt should be generated for putaway stock after relocation in inventory transaction

  @hanging @putaway @direct_po @unique_hanging_putaway_direct_po_validate_sampling/qa_pallet_build @complete @ds 
  Scenario: Validate Sampling/QA Pallet build
    Given the PO of type "Hanging" with UPI and ASN should be received at "REC002" for qa build
    When I choose existing relocate
    And I proceed with entering the upc and location
    When I perform normal putaway after relocation
    Then the goods receipt should be generated for putaway stock after relocation in inventory transaction
    
    @hanging @putaway @direct_po @unique_hanging_putaway_direct_po_validate_hazardous_putaway_location @compete @ds
  Scenario: Validate Hazardous Putaway location
    Given the PO of type "Hanging" with UPI and ASN should be received at "REC002" for hazardous putaway
    When I choose existing relocate
    And I proceed with entering the upc and location
    When I perform normal putaway of hazardous product after relocation
    Then the goods receipt should be generated for putaway stock in inventory transaction