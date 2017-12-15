@footware_returns_rms_returns
Feature: Footware - Returns RMS - Returns
  As a warehouse user
  I want to receive the returned articles

    @returns @footware @unique_footware_returns_returns_rms_validate_the_returns_rms_normal_urn_multiple_line_item @ds
   Scenario: Validate the Returns RMS - Normal URN - Multiple line item
    Given the normal UPI of type "Footware" and ASN should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus of single upi for the returns order at "REC003" with perfect condition "Y" for footware
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for single upi
    
    
  @returns @footware @returns_rms @unique_footware_returns_returns_rms_validate_the_returns_rms_normal_urn_single_line_item @ds 
  Scenario: Validate the Returns RMS - Normal URN - Single Line item
    Given the normal UPI of type "Footware" and ASN should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus of single upi for the returns order at "REC003" with perfect condition "Y" for footware
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for single upi
    
    @returns @footware @unique_footware_returns_returns_rms_validate_the_returns_rms_normal_urn_asn_with_multiple_upi  @returns_rms  @ds 
  Scenario: Validate the Returns RMS - Normal URN - ASN with Multiple pallets
    Given the multiple UPI of type "Footware" and ASN should be in "Released" status
    And the multiple upi should have sku, quantity due details
    And I receive all skus of multiple upi for the returns order at "REC001" with perfect condition "Y" for footware
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multiple upi