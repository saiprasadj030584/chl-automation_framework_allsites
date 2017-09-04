@receiving_IDT
Feature: Inbound receiving IDT
  As a warehouse user
  I want to receive the returned articles

  @boxed_receiving_idt_validate_receiving_with_lockcode @idt @receiving @boxed @complete
  Scenario Outline: Validate Over receiving lockcode for IDT - Boxed
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status for IDT
    And the UPI should have sku, quantity due details
    And ASN and container to be linked with upi header
    When I perform "Full Receiving" for all the skus at location "<Location>" for IDT
    And the ITL should be generated for IDT received with lock code stock in inventory transaction

    Examples: 
      | PalletId                         | ASN         | Location |
      | 56490001060420299900398756000619 | PO001004835 | REC001   |
