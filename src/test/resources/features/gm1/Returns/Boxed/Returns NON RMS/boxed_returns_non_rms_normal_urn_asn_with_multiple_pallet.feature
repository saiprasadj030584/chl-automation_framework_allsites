@boxed_returns_non_rms_receiving
Feature: Boxed - Returns Non RMS - Receiving
  As a warehouse user
  I want to receive the articles

  @boxed_returns_returns_non_rms_validate_the_returns_non_rms_normal_urn_asn_with_multiple_upi @returns @returns_rms @boxed @ds @complete
  Scenario: Validate the Returns Non RMS - Normal URN - ASN with Multiple pallets
    Given the multiple UPI of type "Boxed" and ASN should be in "Released" status
    And the multiple upi should have sku, quantity due details
    And I receive all skus of multiple upi for the returns order at "REC003" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multiple upi
