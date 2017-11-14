@goh_direct_po_receiving
Feature: GOH - Direct PO - Receiving
  As a warehouse user
  I want to validate receiving

  @goh_receiving_direct_po_validate_manual_receipt @goh @receiving @direct_po @complete @ds
  Scenario: Validate manual receipt
    Given the PO of type "GOH" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all "GOH" skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received of GOH type
    And the goods receipt should be generated for GOH received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    
    @jenkinsC @goh_receiving_direct_po_perform_receiving_when_pre_advice_line_quantity_is_lesser_than_the_upi_line_quantity @boxed @receiving @direct_po @complete @ds 
  Scenario: Perform receiving when Pre advice line quantity is lesser than the UPI line quantity
   Given the PO of type "GOH" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details with po quantity "less" than upi quantity
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
     When I receive all skus for the purchase order of type "GOH" at location "REC001"
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be "Complete" while upi and asn status should "Complete"
    
    @jenkinsC @goh_receiving_direct_po_perform_receiving_when_pre_advice_line_quantity_is_greater_than_the_upi_line_quantity @po @complete @boxed @receiving @direct_po @ds 
  Scenario: Perform receiving when Pre advice line quantity is greater than the UPI line quantity
     Given the PO of type "GOH" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details with po quantity "greater" than upi quantity
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order of type "GOH" at location "REC001"
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be "In Progress" while upi and asn status should "Complete"
