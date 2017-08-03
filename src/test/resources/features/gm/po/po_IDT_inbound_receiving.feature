@purchase_order_receiving_IDT
Feature: Purchase order receiving IDT
  As a warehouse user
  I want to receive the returned articles

  @po_IDT_inbound_receiving
  Scenario Outline: overreceing
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status for IDT
    And the UPI should have sku, quantity due details
    And ASN to be linked with upi header
    When I perform "Over Receiving" for all skus at location "<Location>" for IDT
    Then the error message should be displayed as excess over receipt.

    Examples: 
      | PalletId             | ASN         | Location |
      | PO000504560005112790 | PO001004696 | REC001   |
