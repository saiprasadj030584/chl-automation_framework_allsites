@footwear_returns_inbound_receiving
Feature: Footwear - Returns - Multiple URN Single ASN Receiving
  As a warehouse user
  I want to receive the articles

 @yes @stoke @unique_footwear_inbound_receiving_returns_multiple_urn_and_single_asn @inbound_receiving @returns_rms @footwear @ds @complete
  Scenario: Multiple URN and single ASN
    Given the multiple UPI of type "Footwear" and ASN should be in "Released" status
    And the multiple upi should have sku, quantity due details
    And I receive all skus of multiple upi for the returns order at "REC003" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multiple upi
 
 @yes @stoke @returns @footware @returns_rms @unique_footwear_returns_returns_rms_validate_the_returns_rms_normal_urn_multiple_line_item @ds
  Scenario: Validate the Returns Non RMS - Normal URN - Multiple line item
    Given the normal UPI of type "Footwear" and ASN should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus of single upi for the returns order at "REC003" with perfect condition "Y" for footwear
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for single upi
    
     @returns @stoke @footware @returns_rms @unique_footwear_returns_returns_rms_validate_the_returns_rms_normal_urn_single_line_item @ds
  Scenario: Validate the Returns Non RMS - Normal URN - Single Line item
    Given the normal UPI of type "Footwear" and ASN should be in "Released" status
    And the upi should have sku, quantity due details
    And I receive all skus of single upi for the returns order at "REC003" with perfect condition "Y"
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for single upi
    