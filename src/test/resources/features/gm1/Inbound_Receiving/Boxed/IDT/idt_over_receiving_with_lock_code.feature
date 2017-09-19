@inbound_receiving_IDT
Feature: Inbound receiving IDT
  As a warehouse user
  I want to receive the returned articles

  @boxed_inbound_receiving_idt_over_receiving_with_lock_code @idt @inbound_receiving @boxed @complete @ds
  Scenario Outline: Validate Over receiving with lockcode
    Given the UPI and ASN should be in "Released" status for IDT
    And the UPI should have sku, quantity due details
    And ASN and container to be linked with upi header
    When I perform "Over Receiving" for all locked skus at location "REC001" for IDT
    Then the error message should be displayed as excess over receipt
    And the ITL should be generated for IDT received stock in inventory transaction
