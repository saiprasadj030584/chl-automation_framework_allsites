@receiving_IDT
Feature: Boxed - Receiving - IDT with Lock Code
  As a warehouse user
  I want to receive the returned articles

   @jenkinsA @unique_boxed_receiving_idt_validate_idt_receiving_process_with_lock_code @idt @receiving @boxed @complete @ds
  Scenario: Validate IDT receiving process with lock code
    Given the UPI and ASN should be in "Released" status for IDT
    And ASN and container to be linked with upi header
    And the UPI should have sku, quantity due details
    When I perform "Full Receiving" for all the skus at location "REC001" for IDT
    And the ITL should be generated for IDT received with lock code stock in inventory transaction
