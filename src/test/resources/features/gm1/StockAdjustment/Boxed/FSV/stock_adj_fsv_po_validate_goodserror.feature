@Validate_goods_error_for_fsv
Feature: Validate goods error before putaway
  As a warehouse user
  I want to validate the goods
  So that I can putaway the purchase order

  @stock_adjustment @boxed @fsv_po @boxed_stock_adjustment_fsv_po_validate_goods_error_before_putaway @Complete
  Scenario Outline: validating goods error Receiving process in JDA WMS for Boxed type
    Given the FSV PO "<PreAdviceID>" of type "Boxed" should be in "Released" status at site id "<SiteID>"
    And the FSV PO line should have sku, quantity due details
    And the PO should not be linked with UPI line "<PreAdviceID>"
    When I receive all skus for the FSV purchase order at location "<Location>"
    Then the inventory should be displayed for all random pallet ID received for FSV PO
    And the goods receipt should be generated for FSV PO received stock in inventory transaction
    Then the FSV po status should be displayed as "Complete"
    When I navigate to receipt reversal page
    And I do receipt reversal for the random palletId received
    Then the inventory transaction should be updated for random palletId with reversed receipt tag

    Examples: 
      | PreAdviceID | SiteID | Location |
      # | 2530112298 |   5649 | REC001   |
      |  2530119294 |   5649 | REC001   |
