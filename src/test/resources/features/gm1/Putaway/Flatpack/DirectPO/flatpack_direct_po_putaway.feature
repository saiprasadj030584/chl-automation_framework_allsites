@flatpack_direct_po_putaway
Feature: Flatpack - Direct PO - Putaway
  As a warehouse user
  I want to putaway the received articles
  So that I can complete the purchase order

  @flatpack_putaway_direct_po_validate_flatpack_putaway_process @direct_po @putaway @boxed @ds @complete
  Scenario: Validate Putaway Process
    Given the PO of type "Flatpack" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all "Flatpack" skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received of flatpack type
    And the goods receipt should be generated for flatpack received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    When I choose normal putaway
    And I proceed with normal putaway for flatpack type
    Then the goods receipt should be generated for flatpack putaway stock in inventory transaction
