@flatpack_direct_po_receiving
Feature: Flatpack - Direct PO - Receiving
  As a warehouse user
  I want to validate receiving
  
 @jenkinsrun @flatpack_receiving_direct_po_validate_manual_receipt @flatpack @receiving @direct_po @complete @ds
  Scenario: Validate manual receipt
    Given the PO of type "Flatpack" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all "Flatpack" skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received of flatpack type
    And the goods receipt should be generated for flatpack received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    
   @jenkinsC @flatpack_receiving_direct_po_perform_receiving_when_pre_advice_line_quantity_is_lesser_than_the_upi_line_quantity @boxed @receiving @direct_po @complete @ds 
  Scenario: Perform receiving when Pre advice line quantity is lesser than the UPI line quantity
      Given the PO of type "Flatpack" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details with po quantity "less" than upi quantity
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order of type "Hanging" at location "REC001"
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be "Complete" while upi and asn status should "Complete"
    
  @jenkinsC @flatpack_receiving_direct_po_perform_receiving_when_pre_advice_line_quantity_is_greater_than_the_upi_line_quantity @po @complete @boxed @receiving @direct_po @ds 
  Scenario: Perform receiving when Pre advice line quantity is greater than the UPI line quantity
     Given the PO of type "Flatpack" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details with po quantity "greater" than upi quantity
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order of type "Flatpack" at location "REC001"
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be "In Progress" while upi and asn status should "Complete"
    
    @jenkinsm @flatpack_receiving_direct_po_validate_the_urgent_delivery_po @flatpack @direct_po @receiving @complete @ds
  Scenario: Validate the urgent delivery PO
    Given the PO of type "Flatpack" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I am on to pre-advice line maintenance page
    And I mark it as urgent PO
    Then the PO should be updated for urgent delivery
