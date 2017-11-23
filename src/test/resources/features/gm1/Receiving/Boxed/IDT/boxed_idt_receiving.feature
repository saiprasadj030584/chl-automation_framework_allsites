@receiving_IDT
Feature: Inbound receiving IDT
  As a warehouse user
  I want to receive the returned articles

  @boxed_receiving_idt_validate_the_idt_receiving_process_normal_urn @idt @receiving @boxed @complete @ds
  Scenario: Validate the IDT receiving process - Normal URN
    Given the UPI and ASN should be in "Released" status for IDT
    And the UPI should have sku, quantity due details
    And ASN and container to be linked with upi header
    When I perform normal urn "Full Receiving" for "single line item" at location "REC001" for IDT
    And the ITL should be generated for IDT received in inventory transaction

  @boxed_receiving_idt_validate_the_idt_receiving_process_normal_urn_multiple_line_item @idt @receiving @boxed @complete @ds
  Scenario: Validate the IDT receiving process - Normal URN - multiple line item
    Given the UPI and ASN should be in "Released" status for IDT
    And the UPI should have sku, quantity due details
    And ASN and container to be linked with upi header
    When I perform normal urn "Full Receiving" for "multiple line item" at location "REC001" for IDT
    And the ITL should be generated for IDT received in inventory transaction
