@goh_direct_po_putaway
Feature: GOH - Direct PO - Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  @goh @putaway @direct_po @unique_goh_putaway_direct_po_validate_goh_putaway_process @complete @ds
  Scenario: Validate GOH Putaway Process
    Given the PO of type "GOH" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all "GOH" skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received of GOH type
    And the goods receipt should be generated for GOH received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    When I choose normal putaway
    And I proceed with normal putaway for GOH type
    Then the goods receipt should be generated for GOH putaway stock in inventory transaction
