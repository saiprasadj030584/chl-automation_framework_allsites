@boxed_inbound_rec_dir_po_o_and_u_rec
Feature: Inbound Receiving - Direct PO - Over & Under Receiving without Lock code
  As a warehouse user
  I want to receive the articles
  So that I can putaway the purchase order

   @jenkinsch @jenkinsA @boxed_inbound_receiving_direct_po_over_receiving @complete @boxed @inbound_receiving @complete @ds @group_0
  Scenario: Validate Over receiving without lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I perform "Over Receiving" for all skus at location "REC001"
    Then the error message should be displayed as cannot over receipt

  @jenkinsA @jenkinsch @boxed_inbound_receiving_direct_po_under_receiving @complete @boxed @inbound_receiving @complete @ds @group_0
  Scenario: Validate Under receiving without lock code
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I perform "Under Receiving" for all skus at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "In Progress"
