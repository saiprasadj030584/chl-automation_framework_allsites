@purchase_order_putaway
Feature: Purchase order Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  @jenkins_analysis @boxed_putaway_direct_po_validate_putaway_process @direct_po @complete @putaway @boxed @ds
  Scenario: Validate Putaway Process
    Given the PO of type "Boxed" with UPI and ASN should be received at "REC001"
    When I do normal putaway for all tags received
    Then the inventory should be displayed for all putaway tags
    And the goods receipt should be generated for putaway stock in inventory transaction

  @jenkins_analysis @boxed_putaway_direct_po_validate_putaway_location @direct_po @complete @putaway @boxed @ds
  Scenario: Validate Putaway Location
    Given the PO of type "Boxed" with UPI and ASN should be received at "REC001"
    When I choose normal putaway
    And I proceed without entering location
    Then the error message should be displayed as cannot find putaway location
    And I proceed without entering quantity
    Then the error message should be displayed as invalid quantity exception

  @jenkins_analysis @boxed_putaway_direct_po_validate_putaway_quantity @direct_po @complete @putaway @boxed @ds
  Scenario: Validate Putaway quantity
    Given the PO of type "Boxed" with UPI and ASN should be received at "REC001"
    When I choose normal putaway
    And I proceed without entering location
    Then the error message should be displayed as cannot find putaway location
    And I proceed without entering quantity
    Then the error message should be displayed as invalid quantity exception

  @jenkins_analysis @boxed @putaway @direct_po @boxed_putaway_direct_po_validate_mezz/shelving_putaway @complete @ds
  Scenario: Validate Mezz/Shelving putaway
    Given the PO of type "Boxed" with UPI containing "MEZZ" sku and ASN should be normal received at "REC002"
    When I choose existing relocate
    And I proceed with entering the upc and location
    When I perform normal putaway after relocation
    Then the goods receipt should be generated for putaway stock in inventory transaction

  @jenkins_analysis @boxed @putaway @direct_po @boxed_putaway_direct_po_validate_sampling/qa_pallet_build @complete @ds
  Scenario: Validate Sampling/QA Pallet build
    Given the PO of type "Boxed" with UPI and ASN should be received at "REC001" for qa build
    When I choose existing relocate
    And I proceed with entering the upc and location
    When I perform normal putaway after relocation
    Then the goods receipt should be generated for putaway stock in inventory transaction

  @jenkins_analysis @boxed_putaway_direct_po_validate_putaway_logic_for_receiving_singles_when_locations_full @boxed @direct_po @putaway @compete @ds
  Scenario: Validate Putaway Logic for receiving singles when locations full
    Given the PO of type "Boxed" with UPI and ASN should be received at "REC001"
    When I choose existing relocate
    And I proceed with entering the palletid
    When I choose normal putaway
    And I proceed by entering less quantity
    Then the ITL should be generated for putaway relocated stock in inventory transaction

  @jenkins_analysis @boxed_putaway_direct_po_validate_override_putaway_location @boxed @direct_po @putaway @compete @ds
  Scenario: Validate Override Putaway Location
    Given the PO of type "Boxed" with UPI and ASN should be received at "REC001"
    When I choose normal putaway
    And I proceed by overriding the location  "<Location>" for PO
    And the ITL should be generated for putaway stock in inventory transaction for override
