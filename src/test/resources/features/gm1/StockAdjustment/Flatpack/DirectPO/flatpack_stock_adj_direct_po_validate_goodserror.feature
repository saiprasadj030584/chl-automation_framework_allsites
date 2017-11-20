@boxed_validate_goods_error
Feature: Boxed - Validate goods error
  As a warehouse user
  I want to validate the goods
  So that I can putaway the purchase order

  @jenkinsC @flatpack @direct_po @flatpack_stock_adjustment_direct_po_validate_for_goods_in_error_reason_code_before_putaway @ds @complete
  Scenario: Validate for goods in error reason code before putaway
    Given the PO of type "Flatpack" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all "Flatpack" skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received of flatpack type
    And the goods receipt should be generated for flatpack received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    When I navigate to receipt reversal page
    And I do receipt reversal for the random tag received
    Then the inventory transaction should be updated with reversed receipt tag for random tags received

  @stock_adjustment @flatpack @direct_po @flatpack_stock_adjustment_direct_po_validate_for_goods_in_error_reason_code_after_putaway @ds @complete
  Scenario Outline: Validate for goods in error reason code after putaway
    Given the PO of type "Flatpack" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all "Flatpack" skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all tags received of flatpack type
    And the goods receipt should be generated for flatpack received stock in inventory transaction
    Then the po status should be displayed as "Complete"   
    When I choose normal putaway
    And I proceed with normal putaway for flatpack type
    Then the goods receipt should be generated for flatpack putaway stock in inventory transaction
    When I navigate to stock adjustments page
    And I enter SkuId for existing stock at siteId
    And I choose the reason code as "<ReasonCode>"
    Then the inventory should be displayed after stock adjustment for flatpack

    Examples: 
      | ReasonCode |
      | FOUND      |
