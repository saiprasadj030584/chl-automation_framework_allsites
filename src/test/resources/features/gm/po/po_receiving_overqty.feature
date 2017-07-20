@purchase_order_receiving
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles
  So that I can putaway the purchase order

  @po_receive_overqty
  Scenario Outline: Receiving process in JDA WMS for Hanging type
    Given the PO "<PreAdviceID>" of type "Hanging" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I perform "Over Receiving" for all skus at location "<Location>"
    Then the inventory should be displayed for all tags received

    #And the goods receipt should be generated for received stock in inventory transaction
    #Then the po status should be displayed as "In Progress"
    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | PO2010002099 | PO000504560005112700 | PO00100480 | REC001   |
