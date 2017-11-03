@hanging_direct_po_over_and_under_receiving
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles
  So that I can putaway the purchase order

<<<<<<< HEAD
  @hanging @inbound_receiving @direct_po @hanging_inbound_receiving_direct_po_over_receiving @complete @ds @group_1
=======
  @hanging @inbound_receiving @direct_po @hanging_inbound_receiving_direct_po_over_receiving @complete @ds @jenkins1
>>>>>>> 67531263016304a2f1097a830b347e399c0c5090
  Scenario: Over receiving
    Given the PO of type "Hanging" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I perform "Over Receiving" for all "Hanging" skus at location "REC001"
    Then the error message should be displayed as cannot over receipt

<<<<<<< HEAD
  @hanging @inbound_receiving @direct_po @hanging_inbound_receiving_direct_po_under_receiving @complete @ds @group_1
=======
  @hanging @inbound_receiving @direct_po @hanging_inbound_receiving_direct_po_under_receiving @complete @ds @jenkins1
>>>>>>> 67531263016304a2f1097a830b347e399c0c5090
  Scenario: Under receiving
    Given the PO of type "Hanging" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I perform "Under Receiving" for all "Hanging" skus at location "REC001"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "In Progress"
