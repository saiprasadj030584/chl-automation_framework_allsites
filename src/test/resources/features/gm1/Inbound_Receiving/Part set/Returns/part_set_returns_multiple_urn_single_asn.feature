@purchase_order_idt_receiving
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles

  @part_set_inbound_receiving_returns_multiple_urn_and_single_asn @inbound_receiving @returns_rms @partset @ds @complete
  Scenario: Multiple URN and single ASN
    Given the multiple UPI of type "Hanging" and ASN should be in "Released" status with partset
    And the multiple upi should have sku, quantity due details
    And I receive all skus of multiple upi for the returns order at "REC003" with perfect condition "Y" and partset
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multiple upi
