@hanging_direct_po_receiving
Feature: Hanging - Direct PO - Receiving
  As a warehouse user
  I want to validate trailor_level_discrepancy

  @jenkinsB @hanging_receiving_direct_po_trailer_level_discrepancy @hanging @receiving @direct_po @complete @ds @no_ds
  Scenario: Trailer level discrepancy
    Given I am on report selection page
    When I choose the print to screen option
    And I search for "*DISCREPANCIES*" report
    And I choose M&S-Discrepancies summary as report type of type "Hanging"
    Then the trailor level discrepancy report should be generated
    
     @hanging_receiving_direct_po_validate_automatic_document_closure_asn @receiving @direct_po @boxed @complete @ds @jenkinsbr
    Scenario: Validate automatic document closure ASN
    Given the PO of type "Hanging" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order of type "Hanging" at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

 @jenkinsB @hanging_receiving_direct_po_validate_manual_receipt @hanging @receiving @direct_po @complete @ds @jenkinsrun
  Scenario: Validate manual receipt
    Given the PO of type "Hanging" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all "Hanging" skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received of hanging type
    And the goods receipt should be generated for hanging received stock in inventory transaction
    Then the po status should be displayed as "Complete"

  @jenkinsB @hanging_receiving_direct_po_perform_receiving_when_pre_advice_line_quantity_is_lesser_than_the_upi_line_quantity @boxed @receiving @direct_po @complete @ds
  Scenario: Perform receiving when Pre advice line quantity is lesser than the UPI line quantity
    Given the PO of type "Hanging" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details with po quantity "less" than upi quantity
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order of type "Hanging" at location "REC001"
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be "Complete" while upi and asn status should "Complete"

   @jenkinsB @hanging_receiving_direct_po_perform_receiving_when_pre_advice_line_quantity_is_greater_than_the_upi_line_quantity @po @complete @boxed @receiving @direct_po @ds
  Scenario: Perform receiving when Pre advice line quantity is greater than the UPI line quantity
    Given the PO of type "Hanging" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details with po quantity "greater" than upi quantity
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order of type "Hanging" at location "REC001"
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be "In Progress" while upi and asn status should "Complete"
    
   @jenkinsB @hanging_receiving_direct_po_validate_receipting_process_without_lock_code @hanging @receiving @direct_po @complete @ds @group_3
  Scenario: Validate receipting process without lock code
    Given the PO of type "Hanging" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all "Hanging" skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received of hanging type
    And the goods receipt should be generated for hanging received stock in inventory transaction
    Then the po status should be displayed as "Complete"

    @jenkinsB @hanging_receiving_direct_po_validate_the_urgent_delivery_po @hanging @direct_po @receiving @complete @ds
  Scenario: Validate the urgent delivery PO
    Given the PO of type "Hanging" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I am on to pre-advice line maintenance page
    And I mark it as urgent PO
    Then the PO should be updated for urgent delivery