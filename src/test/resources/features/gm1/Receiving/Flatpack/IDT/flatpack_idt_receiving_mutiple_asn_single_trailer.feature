@flatpack_idt_receiving
Feature: Flatpack - IDT - Receiving
  As a warehouse user
  I want to validate receiving

  @unique_flatpack_receiving_idt_validate_idt_receiving_process_by_having_multiple_asn_in_single_trailer @receiving @idt @flatpack @ds @complete
  Scenario: Validate IDT receiving process by having multiple ASN in single trailer
    Given the multiple UPI of type "Flatpack" and ASN should be in "Released" status
    And the multiple upi should have sku, quantity due details
    When I perform idt receiving at location "REC001" for multiple URN
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multiple upi
    
    @unique_flatpack_receiving_idt_validate_the_idt_receiving_process_normal_urn @idt @receiving @flatpack @complete @ds @jenkinsfly
  Scenario: Validate the IDT receiving process - Normal URN
    Given the UPI and ASN should be in "Released" status for IDT
    And ASN and container to be linked with upi header
    And the UPI should have sku, quantity due details
    When I perform normal urn "Full Receiving" for "single line item" at location "REC001" for IDT
    And the ITL should be generated for IDT received in inventory transaction
    
