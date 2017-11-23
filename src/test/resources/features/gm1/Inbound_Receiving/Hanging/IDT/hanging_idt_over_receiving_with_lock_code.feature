@hanging_inbound_receiving_with_lock_code_IDT
Feature: Hanging - Inbound receiving with Lock code -  IDT
  As a warehouse user
  I want to receive the returned articles

  @hanging_inbound_receiving_idt_over_receiving_with_lock_code @idt @inbound_receiving @hanging @complete @ds
  Scenario Outline: Validate Over receiving with lockcode
    Given the UPI and ASN of "Hanging" type should be in "Released" status for IDT
    And the UPI should have sku, quantity due details
    And ASN and container to be linked with upi header
    When I perform "Over Receiving" for all Locked skus at location "REC001" for IDT
    Then the error message should be displayed as excess over receipt
    And the ITL should be generated for IDT received stock in inventory transaction
