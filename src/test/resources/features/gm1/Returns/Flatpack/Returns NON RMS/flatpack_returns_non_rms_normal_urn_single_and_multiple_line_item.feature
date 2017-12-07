@flatpack_returns_non_rms_returns
Feature: Flatpack - Returns Non RMS - Returns
  As a warehouse user
  I want to receive the articles

   @returns @flatpack @returns_non_rms @unique_flatpack_returns_returns_non_rms_validate_the_returns_non_rms_normal_urn_single_line_item @complete @ds
  Scenario: Validate the Returns Non RMS - Normal URN - Single Line item
    Given the normal UPI of type "Boxed" and ASN should be in "Released" status
    And the upi should have sku, quantity due details
     And I receive all skus for the returns order at "REC003" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for single upi

  @returns @flatpack @returns_non_rms @unique_flatpack_returns_returns_non_rms_validate_the_returns_non_rms_normal_urn_multiple_line_item @ds
  Scenario: Validate the Returns Non RMS - Normal URN - Multiple line item
    Given the normal UPI of type "Boxed" and ASN should be in "Released" status
    And the upi should have sku, quantity due details
     And I receive all skus for the returns order at "REC003" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for single upi
