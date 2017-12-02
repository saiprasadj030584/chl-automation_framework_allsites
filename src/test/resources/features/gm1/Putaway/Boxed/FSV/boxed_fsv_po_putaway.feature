@purchase_order_putaway
Feature: Purchase order Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  @boxed @putaway @fsv_po @unique_boxed_putaway_fsv_po_validate_mezz/shelving_putaway @complete @ds @jenkinsput
  Scenario: Validate Mezz/Shelving putaway
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have MEZZ sku, quantity due details
    And the PO should not be linked with UPI line
    And I update the advice id for all line items
    When I receive all skus for the FSV purchase order at location "REC002" for MEZZ putaway
    When I choose existing relocate
    And I proceed with entering the upc and location of FSV PO
    When I perform normal putaway after relocation for FSV PO
    Then the goods receipt should be generated for putaway FSV stock in inventory transaction

  @boxed @putaway @fsv_po @unique_boxed_putaway_fsv_po_validate_sampling/qa_pallet_build @complete @ds @jenkinsput
  Scenario: Validate Sampling/QA Pallet build
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have sku, quantity due and qa details
    And the PO should not be linked with UPI line
    And I update the advice id for all line items
    And I lock the product with lock code "QACOMP"
    When I receive all skus for the FSV sampling purchase order at location "REC002"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"
    When I choose existing relocate
    And I proceed with entering the upc and location of FSV PO
    When I perform normal putaway
    Then the goods receipt should be generated for putaway FSV stock in inventory transaction

   @jenkinsA @unique_boxed_putaway_fsv_po_validate_putaway_location @fsv_po @complete @putaway @boxed @ds @jenkinsput
  Scenario: Validate Putaway Location
    Given the FSV PO of type "Boxed" should be received at location "REC001" and site id
    When I choose normal putaway
    And I proceed without entering location
    Then the error message should be displayed as cannot find putaway location

  @unique_boxed_putaway_fsv_po_validate_putaway_logic_for_receiving_singles_when_locations_full @boxed @fsv_po @putaway @jenkinsput @compete @ds
  Scenario: Validate Putaway Logic for receiving singles when locations full
    Given the FSV PO of type "Boxed" should be received at location "REC001" and site id
   # When I choose existing relocate
   # And I proceed with entering the location and upc
    When I choose normal putaway
    And I proceed by entering less quantity for FSV
    Then the ITL should be generated for putaway relocated stock in inventory transaction

  @unique_boxed_putaway_fsv_po_validate_override_putaway_location @boxed @fsv_po @putaway @compete @ds @jenkinsput
  Scenario: Validate Override Putaway Location
    Given the FSV PO of type "Boxed" should be received at location "REC001" and site id
    When I choose existing relocate
    And I proceed with entering the location and upc
    When I choose normal putaway
    And I proceed by overriding the location "REC001" for FSV
    And the ITL should be generated for putaway in inventory transaction for override

  @boxed @putaway @fsv_po @unique_boxed_putaway_fsv_po_validate_putaway_process @complete @ds @boxed_jenkins @jenkinsput
  Scenario: Validate Putaway Process
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have sku, quantity due and qa details
    And the PO should not be linked with UPI line
    And I update the advice id for all line items
    When I receive all skus for the FSV sampling purchase order at location "<Location>REC002"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"
    When I choose existing relocate
    And I proceed with entering the upc and location of FSV PO
    When I perform normal putaway
    Then the goods receipt should be generated for putaway FSV stock in inventory transaction

  @boxed @putaway @fsv_po @unique_boxed_putaway_fsv_po_validate_putaway_qc_goods @complete @ds @jenkinsput
  Scenario: Validate Putaway QC goods
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have sku, quantity due and qa details
    And the PO should not be linked with UPI line
    And I update the advice id for all line items
    And I lock the product with lock code "QACOMP"
    When I receive all skus for the FSV sampling purchase order at location "REC002"
    Then the inventory should be displayed for all tags received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"
    When I choose existing relocate
    And I proceed with entering the upc and location of FSV PO
    When I perform normal putaway
    Then the goods receipt should be generated for putaway FSV stock in inventory transaction

  @boxed @putaway @fsv_po @unique_boxed_putaway_fsv_po_validate_hazardous_putaway_location @complete @ds @jenkinsput
  Scenario: Validate Hazardous Putaway location
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have hazardous sku, quantity due and qa details
    And the PO should not be linked with UPI line
    When I receive all skus for the FSV sampling purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received for hazardous FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"
    When I perform normal putaway of hazardous product after relocation for FSV PO
    Then the goods receipt should be generated for putaway FSV stock in inventory transaction
