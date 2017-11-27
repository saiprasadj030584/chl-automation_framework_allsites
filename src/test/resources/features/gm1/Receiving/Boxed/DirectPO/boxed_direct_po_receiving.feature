@boxed_direct_po_receiving
Feature: Boxed - Direct PO - Receiving
  As a warehouse user
  I want to receive the articles
  So that I can putaway the purchase order

  @jenkinsA @jenkinsch @boxed_receiving_direct_po_receiving_without_lockcode @receiving @direct_po @boxed @complete @ds @maven_check_1 @putty_check @maven_check_2 @jenkinsbr 
  Scenario: Validate receipting process without lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

  @jenkinsA @boxed_receiving_direct_po_validate_automatic_document_closure_asn @receiving @direct_po @boxed @complete @ds @jenkinsbr
  Scenario: Validate automatic document closure ASN
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

  @jenkinsA @jenkinsch @boxed_receiving_direct_po_preadvice_qty_greater_than_upi_qty @po @complete @boxed @receiving @direct_po @ds 
  Scenario: Perform receiving when Pre advice line quantity is greater than the UPI line quantity
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details with po quantity "greater" than upi quantity
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be "In Progress" while upi and asn status should "Complete"

  @jenkinsA  @jenkinsch @boxed_receiving_direct_po_preadvice_qty_less_than_upi_qty @boxed @receiving @direct_po @complete @ds 
  Scenario: Perform receiving when Pre advice line quantity is lesser than the UPI line quantity
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details with po quantity "less" than upi quantity
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be "Complete" while upi and asn status should "Complete"

  @jenkins_analysis @boxed @direct_po @receiving @complete @boxed_receiving_direct_po_validate_receiving_process_for_boxed_when_two_putaway_group_involved @ds
  Scenario: Receiving process in JDA WMS for Boxed type having different putaway group
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus having different putaway group for the purchase order at location "REC001"
    Then the inventory should be displayed for all received tags for two putaway group
    And the goods receipt should be generated for received stock in inventory transaction for two putaway group
    Then the po status should be displayed as "Complete"

  @jenkinsA @boxed_receiving_direct_po_validate_the_urgent_delivery_po @boxed @direct_po @receiving @complete @ds @jenkinsbr
  Scenario: Validate the urgent delivery PO
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I am on to pre-advice line maintenance page
    And I mark it as urgent PO
    Then the PO should be updated for urgent delivery
    
    @jenkinsA @boxed_receiving_direct_po_validate_manual_receipt @boxed @receiving @direct_po @complete @ds
  Scenario: Validate manual receipt
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
   
