@direct_po_inbound_receiving
Feature: Inbound receiving
  As a warehouse user
  I want to receive the articles
  So that I can putaway the purchase order

@yes   @jenkinsA @unique_boxed_inbound_receiving_direct_po_multiple_po_receive_multiple_urn_single_trailer @boxed @inbound_receiving @direct_po @complete @ds
  Scenario: Mulitple PO ,multiple URN ,single ASN
    Given the multiple PO of type "Boxed" with multiple UPI and ASN should be in "Released" status with line items,supplier details
    And the single PO with multiple upi should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header list and po to be linked with upi line
    When I receive all skus for the multiple purchase order with multiple upi at location "REC001"
    And the goods receipt should be generated for the multiple stock received in inventory transaction
    Then the po status should be displayed as "Complete" for all the po

 @yes @jenkinsA  @unique_boxed_inbound_receiving_direct_po_single_po_and_multiple_urn_single_trailer @boxed @inbound_receiving @direct_po @recv @hangRev @complete
  Scenario: Single PO and mulitple URN ,single trailer
    Given the single PO of type "Boxed" with multiple UPI and ASN should be in "Released" status with line items,supplier details
    And the single PO with multiple upi should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header list and po to be linked with upi line
    When I receive all skus for the multiple purchase order with multiple upi at location "REC001"
    And the goods receipt should be generated for the multiple stock received in inventory transaction
    Then the po status should be displayed as "Complete" for all the po

   @yes @jenkinsA  @unique_boxed_inbound_receiving_direct_po_multiple_trailer_multiple_urn_single_po @boxed @inbound_receiving @direct_po @recv @complete
  Scenario: Multiple trailer ,multiple URN,single PO
    Given the single PO of type "Boxed" with multiple UPI and multiple ASN should be in "Released" status with line items,supplier details
    And the single PO with multiple upi should have sku, quantity due details
    And the pallet count should be updated in multiple delivery, asn list to be linked with upi header list and po to be linked with upi line
    When I receive all skus for the multiple purchase order with multiple upi at location "REC001"
    And the goods receipt should be generated for the multiple stock received in inventory transaction
    Then the po status should be displayed as "Complete" for all the po
    
   @yes @jenkinsA @unique_boxed_direct_po_receiving_adding_stock_to_asn_before_receiving @boxed @inbound_receiving @receiving @recv @complete
  Scenario: Adding stock onto an ASN before receiving has started
    Given the single PO of type "Boxed" with multiple UPI and multiple ASN should be in "Released" status with line items,supplier details
    And the single PO with multiple upi should have sku, quantity due details
    And the pallet count should be updated in multiple delivery, asn list to be linked with upi header list and po to be linked with upi line
    And I increase the stock of one pallet
    When I receive all skus for the multiple purchase order with multiple upi after adding stock at location "REC001"
    And the goods receipt should be generated for the multiple stock received after adding stock in inventory transaction
    Then the po status should be displayed as "Complete" for all the po
