@boxed_returns_inbound_receiving
Feature: Boxed - Returns - Multiple URN Single ASN Receiving
  As a warehouse user
  I want to receive the articles

 @yes @unique_boxed_inbound_receiving_returns_multiple_urn_and_single_asn @inbound_receiving @returns_rms @boxed @ds @complete
  Scenario: Multiple URN and single ASN
    Given the multiple UPI of type "Boxed" and ASN should be in "Released" status
    And the multiple upi should have sku, quantity due details
    And I receive all skus of multiple upi for the returns order at "REC003" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multiple upi
