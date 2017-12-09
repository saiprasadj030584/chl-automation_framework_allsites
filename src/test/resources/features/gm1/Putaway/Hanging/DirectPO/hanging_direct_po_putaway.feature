@hanging_direct_po_putaway
Feature: Hangind Direc PO Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  @hanging @putaway @direct_po @unique_hanging_putaway_direct_po_validate_sampling/qa_pallet_build @complete @ds
  Scenario: Validate Sampling/QA Pallet build
    Given the PO of type "Hanging" with UPI and ASN should be received at "REC002" for qa build
    When I choose existing relocate
    And I proceed with entering the upc and location
    When I perform normal putaway after relocation
    Then the goods receipt should be generated for putaway stock after relocation in inventory transaction
