@boxed_direct_po_putaway
Feature: Boxed - Direct PO - Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

      @hanging @putaway @direct_po @unique_hanging_putaway_direct_po_validate_maximum_locations_per_aisle_per_upc @complete @ds @max
  Scenario: Validate maximum locations per Aisle per UPC
    Given the PO of type "Hanging" with UPI and ASN should be received at "REC001" for maximum aisle
    When I choose existing relocate
    And I proceed with entering the upc and location
    When I perform normal putaway after under receiving and relocation
    Then the goods receipt should be generated for putaway stock in inventory transaction