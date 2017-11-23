@flatpack_idt_receiving
Feature: Flatpack - IDT - Receiving
  As a warehouse user
  I want to validate receiving

  @flatpack_receiving_idt_validate_idt_receiving_process_by_having_multiple_asn_in_single_trailer @receiving @idt @flatpack @ds @complete
  Scenario: Validate IDT receiving process by having multiple ASN in single trailer
    Given the multiple UPI of type "Flatpack" and ASN should be in "Released" status
    And the multiple upi should have sku, quantity due details
    When I perform idt receiving at location "REC001" for multiple URN
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multiple upi
