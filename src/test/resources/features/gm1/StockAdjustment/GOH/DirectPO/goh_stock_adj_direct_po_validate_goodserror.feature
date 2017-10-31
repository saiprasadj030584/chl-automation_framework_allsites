@goh_validate_goods_error
Feature: Boxed - Validate goods error
  As a warehouse user
  I want to validate the goods
  So that I can putaway the purchase order

  @jenkins_analysis @stock_adjustment @goh @direct_po @goh_stock_adjustment_direct_po_validate_for_goods_in_error_reason_code_before_putaway @complete @ds
  Scenario: Validate for goods in error reason code before putaway
    Given the PO of type "GOH" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all "GOH" skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all GOH received tag
    And the goods receipt should be generated for GOH type
    Then the po status should be displayed as "Complete"
    When I navigate to receipt reversal page
    And I do receipt reversal for the random tag received
    Then the inventory transaction should be updated with reversed receipt tag for random tags received
