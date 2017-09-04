@inbound_receiving_IDT
Feature: Inbound receiving IDT
  As a warehouse user
  I want to receive the returned articles

  @boxed_inbound_receiving_idt_over_receiving_lockcode @idt @inbound_receiving @boxed @complete
  Scenario Outline: Validate Over receiving with lockcode
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status for IDT
    And the UPI should have sku, quantity due details
    And ASN and container to be linked with upi header
    When I perform "Over Receiving" for all Locked skus at location "<Location>" for IDT
    Then the error message should be displayed as excess over receipt
    And the ITL should be generated for IDT received stock in inventory transaction

    Examples: 
      | PalletId                         | ASN         | Location |
      | 56490001060420299900398756000210 | PO001004616 | REC001   |
