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
    
    
    
    @hanging_putaway_direct_po_validate_putaway_process @direct_po @hanging @putaway @complete
    Scenario: Validate Putaway Process
     Given the PO of type "Hanging" with UPI and ASN should be received at "REC001"
    When I choose normal putaway
    And I proceed with normal putaway
    Then the goods receipt should be generated for putaway stock in inventory transaction
    