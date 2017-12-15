@bhanging_inbound_receiving_idt_over_receiving_with_lockcode
Feature: Inbound Receiving - IDT - Over Receiving with Lock code
  As a warehouse user
  I want to receive the returned articles

   @unique_hanging_inbound_receiving_idt_over_receiving_with_lock_code @idt @inbound_receiving @boxed @complete @ds @maven_check_1
  Scenario: Validate Over receiving with lockcode
    Given the UPI and ASN should be in "Released" status for IDT
    And ASN and container to be linked with upi header
    And the UPI should have sku, quantity due details
    When I perform "Over Receiving" for all locked skus at location "REC001" for IDT
    Then the error message should be displayed as excess over receipt
