@Validate_goods_error
Feature: Validate goods error before putaway
  As a warehouse user
  I want to validate the goods
  So that I can putaway the purchase order

  @jenkins_analysis @stock_adjustment @boxed @direct_po @boxed_stock_adjustment_direct_po_validate_goods_error_before_putaway @complete @ds @maven_check_1
  Scenario: validating goods error before putaway
    Given the PO of type "Boxed" with UPI and ASN should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "REC001"
    Then the inventory should be displayed for all random tags received
    And the goods receipt should be generated for random tags received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    When I navigate to receipt reversal page
    And I do receipt reversal for the random tag received
    Then the inventory transaction should be updated with reversed receipt tag for random tags received
