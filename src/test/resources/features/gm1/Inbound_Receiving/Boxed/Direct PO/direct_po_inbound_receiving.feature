@purchase_order_receiving
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles
  So that I can putaway the purchase order

  @boxed_inbound_receiving_direct_po_multiple_po_receive_multiple_urn_single_trailer @boxed @inbound_receiving @direct_po @complete
  Scenario Outline: Receiving process in JDA WMS for Hanging type
    Given the multiple PO "<PreAdviceID>" of type "Hanging" with multiple UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the multiple PO with multiple upi should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header list and po to be linked with upi line
    When I receive all skus for the multiple purchase order with multiple upi at location "<Location>"
    And the goods receipt should be generated for the multiple stock received in inventory transaction
    Then the po status should be displayed as "Complete" for all the po

    Examples: 
      | PreAdviceID           | PalletId                                  | ASN        | Location |
      | 5050008870,5050008890 | 00050456000259616198,00050456000259616188 | 0000002099 | REC001   |
