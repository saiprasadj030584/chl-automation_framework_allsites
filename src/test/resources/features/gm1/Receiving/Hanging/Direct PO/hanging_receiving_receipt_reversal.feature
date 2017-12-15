@receiving
Feature: Hanging - Direct PO - Receipt reversal
  As a warehouse user
  I want to receive the articles

  @jenkinsB @unique_hanging_receiving_direct_po_validate_receipt_reversal_process_with_qafts_lock_code @direct_po @hanging @receiving @complete @ds
  Scenario: Validate receipt reversal process with QAFTS lock code
    Given the PO of type "Hanging" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all "Hanging" skus for the purchase order at location "REC001"
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be "Complete" while upi and asn status should "Complete"
    When I navigate to inventory update page
    And I select the update type as "Lock Status Change"
    And I search the inventory for the tag
    Then the tag details should be displayed
    And I select the status as "Locked" and lock code as "1Damaged"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lockcode damaged
    
    @jenkinsB @unique_hanging_receiving_direct_po_validate_receipt_reversal_process_without_lock_code @boxed @direct_po @receiving @complete @ds
  Scenario: Validate receipt reversal process without lock code
    Given the PO of type "Hanging" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all "Hanging" skus for the purchase order at location "REC001"
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be "Complete" while upi and asn status should "Complete"
    And I navigate to receipt reversal page
    And I do receipt reversal for the tag received
    Then the inventory transaction should be updated with reversed receipt tag
