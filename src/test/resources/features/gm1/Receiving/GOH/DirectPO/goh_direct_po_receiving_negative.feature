@goh_direct_po_receiving_negative
Feature: GOH - Receiving - Direct PO - Negative
  As a warehouse user
  I want to receive the articles that are damaged

   @jenkinsC @jenkins_analysis @unique_goh_receiving_direct_po_validate_not_received_asn @direct_po @boxed @receiving @complete @ds
  Scenario: Validate not received ASN
    Given the PO of type "GOH" with UPI and ASN with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I navigate to delivery page
    And I enter an ASN ID
    Then the ASN should not be received
    
     @jenkinsnC @unique_goh_receiving_direct_po_validate_damaged_on_receipt_from_supplier @direct_po @goh @receiving @complete @ds
  Scenario: Validate damaged on receipt (From supplier) 
    Given the PO of type "GOH" with UPI and ASN should be received at "REC001"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated with lockcode damaged
