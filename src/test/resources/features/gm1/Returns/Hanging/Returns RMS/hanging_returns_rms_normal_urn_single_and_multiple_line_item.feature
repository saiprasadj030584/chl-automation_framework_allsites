@hanging_returns_rms_returns
Feature: Boxed - Returns RMS - Returns
  As a warehouse user
  I want to receive the returned articles

  @returns @hanging @returns_rms @unique_hanging_returns_returns_rms_validate_the_returns_rms_normal_urn_single_line_item @ds @complete
  Scenario: Validate the Returns RMS - Normal URN - Single Line item
    Given the normal UPI of type "Boxed" and ASN should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus of single upi for the returns order at "REC003" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for single upi

  @returns @hanging @returns_rms @unique_hanging_returns_returns_rms_validate_the_returns_rms_normal_urn_multiple_line_item @ds 
  Scenario: Validate the Returns RMS - Normal URN - Multiple line item
    Given the normal UPI of type "Hanging" and ASN should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus of single upi for the returns order at "REC003" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for single upi
