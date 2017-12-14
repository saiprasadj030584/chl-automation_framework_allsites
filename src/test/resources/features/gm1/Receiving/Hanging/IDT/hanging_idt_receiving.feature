@receiving_IDT
Feature: Hanging - Receiving - IDT
  As a warehouse user
  I want to receive the returned articles

  @unique_hanging_receiving_idt_validate_the_idt_receiving_process_normal_urn @idt @receiving @hanging @complete @ds @jenkinsfly
  Scenario: Validate the IDT receiving process - Normal URN
    Given the UPI and ASN should be in "Released" status for IDT
    And ASN and container to be linked with upi header
    And the UPI should have sku, quantity due details
    When I perform normal urn "Full Receiving" for "single line item" at location "REC001" for IDT
    And the ITL should be generated for IDT received in inventory transaction

  
