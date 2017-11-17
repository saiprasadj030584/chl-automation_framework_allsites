@direct_po_inbound_receiving
Feature: Inbound receiving
  As a warehouse user
  I want to receive the articles
  So that I can putaway the purchase order

  @boxed_inbound_receiving_direct_po_multiple_po_multiple_urn_single_asn @boxed @inbound_receiving @direct_po @complete @ds
  Scenario: Mulitple PO ,multiple URN ,single ASN
    Given the multiple PO of type "Boxed" with multiple UPI and ASN should be in "Released" status with line items,supplier details
    #And the multiple PO with multiple upi should have sku, quantity due details
    #And the pallet count should be updated in delivery, asn to be linked with upi header list and po to be linked with upi line
    #When I receive all skus for the multiple purchase order with multiple upi at location "REC001"
    #And the goods receipt should be generated for the multiple stock received in inventory transaction
    #Then the po status should be displayed as "Complete" for all the po
    And the single PO with multiple upi should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header list and po to be linked with upi line
    When I receive all skus for the multiple purchase order with multiple upi at location "REC001"
    And the goods receipt should be generated for the multiple stock received in inventory transaction
    Then the po status should be displayed as "Complete" for all the po

  @boxed_inbound_receiving_direct_po_single_po_and_mulitple_urn_single_trailer @boxed @inbound_receiving @direct_po
  Scenario: Receiving process in JDA WMS for Hanging type
    Given the single PO of type "Boxed" with multiple UPI and ASN should be in "Released" status with line items,supplier details
    And the single PO with multiple upi should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header list and po to be linked with upi line
    When I receive all skus for the multiple purchase order with multiple upi at location "REC001"
    And the goods receipt should be generated for the multiple stock received in inventory transaction
    Then the po status should be displayed as "Complete" for all the po
    
    @boxed_inbound_receiving_direct_po_multiple_trailer_multiple_urn_single_po @boxed @inbound_receiving @direct_po
  Scenario: Receiving process in JDA WMS for Hanging type
    Given the single PO of type "Boxed" with multiple UPI and multiple ASN should be in "Released" status with line items,supplier details
    And the single PO with multiple upi should have sku, quantity due details
    And the pallet count should be updated in multiple delivery, asn list to be linked with upi header list and po to be linked with upi line
    When I receive all skus for the multiple purchase order with multiple upi at location "REC001"
    And the goods receipt should be generated for the multiple stock received in inventory transaction
    Then the po status should be displayed as "Complete" for all the po
    
