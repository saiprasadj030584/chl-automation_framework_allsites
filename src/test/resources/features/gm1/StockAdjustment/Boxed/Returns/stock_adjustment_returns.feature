@stock_adjustments
Feature: Stock adjustments
  As a warehouse user
  I want to adjust the stock in inventory

  @stock_adjustment @returns @boxed @boxed_stock_adjustment_returns_verify_reason_code_and_has_movement_label @complete @ds
  Scenario Outline: Verify reason code available for 'Store has sent greater quantity than the expected volume for a product within the URRN and has a movement label.'
    Given the UPI and ASN should be in "Released" status for adjustment
    And I perform "Over Receiving" for all skus of the returns order for stock adjustment at location "<Location>" with perfect condition "<Condition>" and movement label enabled
    When I navigate to inventory transaction query
    And I search with sku and reason code
    When I navigate to stock adjustments page
    And I change on hand qty and reason code to "<Reason Code>"
    Then the inventory transaction is updated with locked status
    When I navigate to inventory update page
    And I change the lock status to unlocked
    Then the inventory is unlocked and the return stock is over received

    Examples: 
      | Location | Condition | Reason Code                            |
      | REC003   | N         | RMS - Over receipt with movement label |

  @stock_adjustment @returns @boxed @boxed_stock_adjustment_returns_verify_reason_code_without_movement_label @complete @ds
  Scenario Outline: Verify reason code available for 'Store has sent greater quantity than the expected volume for a product within the URRN and has a movement label.'
    Given the UPI and ASN should be in "Released" status for adjustment
    And I perform "Over Receiving" for all skus of the returns order for stock adjustment at location "<Location>" with perfect condition "<Condition>"
    When I navigate to inventory transaction query
    And I search with sku and reason code
    When I navigate to stock adjustments page
    And I change on hand qty and reason code to "<Reason Code>"
    Then the inventory transaction is updated with locked status
    When I navigate to inventory update page
    And I change the lock status to unlocked
    Then the inventory is unlocked and the return stock is over received

    Examples: 
      | Location | Condition | Reason Code                               |
      | REC003   | N         | RMS - Over receipt without movement label |

  @stock_adjustment @returns @boxed @boxed_stock_adjustment_returns_verify_reason_code_product_not_listed_for_URRN_and_has_movement_label @complete @ds
  Scenario Outline: Verify reason code available for 'Store has sent a product not listed for the URRN and has a movement label' (unexpected)
    Given the UPI and ASN should be in "Released" status for adjustment
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code and uploaded should be updated

    Examples: 
       | Location | ReasonCode                                   |
        | REC003   | RMS - Unexpected receipt with movement label |

  @stock_adjustment @returns @boxed @boxed_stock_adjustment_returns_verify_reason_code_without_movement_label_not_listed_for_URRN_and_does_not_have_movement_label @complete @ds
  Scenario Outline: Verify reason code available for 'Store has sent a product not listed for the URRN and does not have a movement label'  (unexpected)
    Given the UPI and ASN should be in "Released" status for adjustment
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code and uploaded should be updated

    Examples: 
      | Location | ReasonCode                                      |
       | REC003   | RMS - Unexpected receipt without movement label |

  @stock_adjustment @returns @boxed @boxed_stock_adjustment_returns_verify_reason_code_without_movement_label_stock_without_URRN_has_movement_label @complete @ds
  Scenario Outline: Verify reason code available for 'Store has sent stock without any URRN but has a movement label' (Non adviced)
    Given the UPI and ASN should be in "Released" status for adjustment
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code and uploaded should be updated

    Examples: 
      | Location | ReasonCode                                   |
      | REC003   | RMS  Non advised receipt with movement label |

  @stock_adjustment @returns @boxed @boxed_stock_adjustment_returns_verify_reason_code_without_any_URRN_or_movement_label @complete @ds
  Scenario Outline: Verify reason code available for 'Store has sent stock without any URRN or movement label' (Non adviced)
    Given the UPI and ASN should be in "Released" status for adjustment
    When I navigate to stock adjustments page
    And I create a new stock with siteid and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code and uploaded should be updated

    Examples: 
      | Location | ReasonCode                                       |
      | REC003   | RMS - Non advised receipt without movement label |
