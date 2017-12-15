@Flatpack_dir_po_over_and_under_rec_lck
Feature: Flatpack - Direct PO - Inbound receiving with Lock code
  As a warehouse user
  I want to receive the articles
  So that I can putaway the purchase order

  @jenkinsC @flatpack @inbound_receiving  @direct_po @unique_flatpack_inbound_receiving_direct_po_over_receiving_with_lock_code @complete @ds @group_0
  Scenario: Over receiving with lock code
    Given the PO of type "Flatpack" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAFTS"
    When I perform "Over Receiving" for all "Flatpack" skus at location "REC001"
    Then the error message should be displayed as cannot over receipt

   @Flatpack @jenkinsC @inbound_receiving @direct_po @unique_Flatpack_inbound_receiving_direct_po_under_receiving_with_lock_code @complete @ds  @group_0
  Scenario: Under receiving with lock code
    Given the PO of type "Flatpack" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And I lock the product with lock code "QAFTS"
    When I perform "Under Receiving" for all "Flatpack" skus at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "In Progress"
