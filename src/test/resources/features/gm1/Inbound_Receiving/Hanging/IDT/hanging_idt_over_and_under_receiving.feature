@hanging_inbound_receiving_IDT
Feature: Hanging - Inbound receiving -  IDT
  As a warehouse user
  I want to receive the returned articles

 @yes @jenkinsB @unique_hanging_inbound_receiving_idt_over_receiving @idt @inbound_receiving @hanging @complete @ds
  Scenario: Validate Over receiving
    Given the UPI and ASN of "Hanging" type should be in "Released" status for IDT
    And ASN and container to be linked with upi header
    And the UPI should have sku, quantity due details
    When I perform "Over Receiving" for all skus at location "REC001" for IDT
    Then the error message should be displayed as excess over receipt
