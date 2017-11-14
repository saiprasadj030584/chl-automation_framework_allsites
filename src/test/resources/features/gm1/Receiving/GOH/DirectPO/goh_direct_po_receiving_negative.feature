@goh_direct_po_receiving_negative
Feature: GOH - Receiving - Direct PO - Negative
  As a warehouse user
  I want to receive the articles that are damaged

   @jenkinsno @jenkins_analysis @goh_receiving_direct_po_validate_not_received_asn @direct_po @boxed @receiving @complete @ds
  Scenario: Validate not received ASN
    Given the PO of type "GOH" with UPI and ASN with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I navigate to delivery page
    And I enter an ASN ID
    Then the ASN should not be received
