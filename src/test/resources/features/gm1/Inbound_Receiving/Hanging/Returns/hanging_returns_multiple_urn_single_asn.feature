@hanging_returns_inbound_receiving
Feature: Hanging - Returns - Multiple URN Single ASN Receiving
  As a warehouse user
  I want to receive the articles

   @yes @unique_hanging_inbound_receiving_returns_multiple_urn_and_single_asn @inbound_receiving @returns_rms @hanging @ds
  Scenario: Multiple URN and single ASN
    Given the multiple UPI of type "Hanging" and ASN should be in "Released" status
    And the multiple upi should have sku, quantity due details
    And I receive all skus of multiple upi for the returns order at "REC001" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multiple upi
