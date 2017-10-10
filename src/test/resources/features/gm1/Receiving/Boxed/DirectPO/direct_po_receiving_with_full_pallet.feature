@purchase_order_receiving
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles
  So that I can putaway the purchase order

  @jenkins_analysis1 @boxed_receiving_direct_po_validate_pallet_build_for_full_pallet_enter_n_in_full_pallet @boxed @direct_po @receiving @complete @ds
  Scenario: Validate Pallet build for Full pallet - enter 'N' in full pallet
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "REC001" with full pallet "N"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
      
  @jenkins_analysis @boxed_receiving_direct_po_validate_pallet_build_for_full_pallet_enter_y_in_full_pallet @boxed @direct_po @receiving @complete @ds
  Scenario: Validate Pallet build for Full pallet - enter 'Y' in full pallet
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "REC001" with full pallet "Y"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
