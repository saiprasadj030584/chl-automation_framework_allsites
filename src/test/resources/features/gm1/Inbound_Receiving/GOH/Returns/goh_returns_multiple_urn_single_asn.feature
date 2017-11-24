@goh_returns_inbound_receiving
Feature: GOH - Returns - Multiple URN Single ASN Receiving
  As a warehouse user
  I want to receive the articles

  @goh_inbound_receiving_returns_multiple_urn_and_single_asn @inbound_receiving @returns_rms @goh @ds 
  Scenario: Multiple URN and single ASN
    Given the multiple UPI of type "GOH" and ASN should be in "Released" status
    And the multiple upi should have sku, quantity due details
    When I perform return receiving at location "REC001" for multiple URN
    When I navigate to inventory transaction query
    Then the inventory transaction should be updated for multiple upi
