@receiving_IDT
Feature: Inbound receiving IDT
  As a warehouse user
  I want to receive the returned articles

  @boxed_receiving_idt_validate_receiving_with_lockcode @idt @receiving @boxed @complete
  Scenario: Validate Over receiving lockcode for IDT - Boxed
    Given the UPI and ASN should be in "Released" status for IDT
    And the UPI should have sku, quantity due details
    And ASN and container to be linked with upi header
    When I perform "Full Receiving" for all the skus at location "REC001" for IDT
    And the ITL should be generated for IDT received with lock code stock in inventory transaction

  @boxed_receiving_idt_validate_receiving_with_normal_urn @idt @receiving @boxed @complete
  Scenario Outline: Validate Over receiving for IDT - Boxed
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status for IDT
    And the UPI should have sku, quantity due details
    And ASN and container to be linked with upi header
    When I perform normal urn "Full Receiving" for "single line item" at location "<Location>" for IDT
    And the ITL should be generated for IDT received in inventory transaction

    Examples: 
      | PalletId                         | ASN         | Location |
      | 56490001060420299900398756000229 | PO001004718 | REC001   |

  @boxed_receiving_idt_validate_receiving_with_normal_urn_multiple_line @idt @receiving @boxed @complete
  Scenario Outline: Validate Over receiving for IDT - Boxed
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status for IDT
    And the UPI should have sku, quantity due details
    And ASN and container to be linked with upi header
    When I perform normal urn "Full Receiving" for "multiple line item" at location "<Location>" for IDT
    And the ITL should be generated for IDT received in inventory transaction

    Examples: 
      | PalletId             | ASN        | Location |
      | 00050456000555232887 | 0000009886 | REC001   |
