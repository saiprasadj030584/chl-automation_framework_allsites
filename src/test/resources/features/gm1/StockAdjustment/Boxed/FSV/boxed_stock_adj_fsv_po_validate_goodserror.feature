@Validate_goods_error_for_fsv
Feature: Validate goods error before putaway
  As a warehouse user
  I want to validate the goods
  So that I can putaway the purchase order

  @stock_adjustment @boxed @fsv_po @boxed_stock_adjustment_fsv_po_validate_goods_error_before_putaway @complete @ds
  Scenario: validating goods error Receiving process in JDA WMS for Boxed type
    Given the FSV PO of type "Boxed" should be in "Released" status at site id
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line
    When I receive all skus for the FSV purchase order at location "REC001"
    Then the inventory should be displayed for all random pallet ID received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"
    When I navigate to receipt reversal page
    And I do receipt reversal for the random palletId received
    Then the inventory transaction should be updated for random palletId with reversed receipt tag
