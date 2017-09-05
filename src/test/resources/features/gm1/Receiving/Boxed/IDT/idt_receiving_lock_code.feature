@receiving_IDT
Feature: Inbound receiving IDT
  As a warehouse user
  I want to receive the returned articles

  @boxed_receiving_idt_validate_receiving_with_lockcode @idt @receiving @boxed @complete @ds
  Scenario: Validate IDT receiving process with lock code
    Given the UPI and ASN should be in "Released" status for IDT
    And the UPI should have sku, quantity due details
    And ASN and container to be linked with upi header
    When I perform "Full Receiving" for all the skus at location "REC001" for IDT
    And the ITL should be generated for IDT received with lock code stock in inventory transaction
