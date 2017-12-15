@goh_inbound_receiving_idt_over_and_under_receiving
Feature: Inbound Receiving - IDT - Over & Under Receiving without Lock code
  As a warehouse user
  I want to receive the returned articles

     @unique_goh_inbound_receiving_idt_over_receiving @idt @inbound_receiving @goh @complete @ds @maven_check_1 @jenkinsfly
  Scenario: Validate Over receiving
    Given the UPI and ASN should be in "Released" status for IDT
    And ASN and container to be linked with upi header
    And the UPI should have sku, quantity due details
    When I perform "Over Receiving" for all skus at location "REC001" for IDT
    Then the error message should be displayed as excess over receipt

  @unique_goh_inbound_receiving_idt_under_receiving @idt @inbound_receiving @goh @complete @ds @maven_check_1 @jenkinsfly
  Scenario: Validate Under receiving
    Given the UPI and ASN should be in "Released" status for IDT
    And ASN and container to be linked with upi header
    And the UPI should have sku, quantity due details
    When I perform "Under Receiving" for all skus at location "REC001" for IDT
    Then the inventory should be displayed for all tags received idt
    And the goods receipt should be generated for IDT received stock in inventory transaction
    Then the idt status should be displayed as "In Progress"
    
      
