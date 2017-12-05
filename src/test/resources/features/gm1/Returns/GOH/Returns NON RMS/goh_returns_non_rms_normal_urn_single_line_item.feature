@goh_returns_non_rms
Feature: GOH - Returns -NON-RMS- Normal URN Single line item
  As a warehouse user
  I want to receive the articles

  @unique_goh_returns_non_rms_normal_urn_and_single_line_item @returns @returns_non_rms @goh @ds 
  Scenario: Validate the Returns Non RMS - Normal URN- Single line item
    Given the normal UPI of type "GOH" and ASN should be in "Released" status
    And the multiple upi should have sku, quantity due details
    And I receive all skus of multiple upi for the returns order at "REC001" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multiple upi
