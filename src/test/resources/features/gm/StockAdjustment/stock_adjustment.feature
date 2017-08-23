@stock_adjustments
Feature: Stock adjustments
  As a warehouse user
  I want to adjust the stock in inventory

  @stock_adjustment @complete
  Scenario Outline: stock adjustment process
    Given I have a sku to adjust the stock
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code should be updated

    Examples: 
      | SiteId | Location | ReasonCode |
      |   5649 | 1AA103   | DIRTY      |
      |   5649 | 1AA103   | DMIT       |
      |   5649 | 1AA103   | EXPD       |
      |   5649 | 1AA103   | FOUND      |
      |   5649 | 1AA103   | INCOMPLETE |
      |   5649 | 1AA103   | LOST       |
      |   5649 | 1AA103   | SAMPLES    |

  @stock_adjustment @returns @boxed @stock_adjustment_returns_verify_reason_code_and_has_movement_label
  Scenario Outline: Verify reason code available for 'Store has sent greater quantity than the expected volume for a product within the URRN and has a movement label.'
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status for adjustment
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
      | PalletId                         | ASN        | Location | Condition |Reason Code														 |
      | 95580085370650011050230212453758 | 0000854749 | REC003   | N         |RMS - Over receipt with movement label |

  @stock_adjustment @returns @boxed @boxed_stock_adjustment_returns_verify_reason_code_without_movement_label
  Scenario Outline: Verify reason code available for 'Store has sent greater quantity than the expected volume for a product within the URRN and has a movement label.'
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status for adjustment
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
      | PalletId                         | ASN        | Location | Condition |Reason Code																|
      | 58850008448480077010082484800300 | 0000844868 | REC003   | N         |RMS - Over receipt without movement label |

  @stock_adjustment @returns @boxed @boxed_stock_adjustment_returns_verify_reason_code_product_not_listed_for_URRN_and_has_movement_label @complete
  Scenario Outline: Verify reason code available for 'Store has sent a product not listed for the URRN and has a movement label' (unexpected)
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status for adjustment
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>" in stoke
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code and uploaded should be updated

    Examples: 
      | PalletId                         | ASN        | SiteId | Location | ReasonCode                                   |
      | 58850008548480077010085484800300 | 0000854848 | 5885   | REC003   | RMS - Unexpected receipt with movement label |
      
   @stock_adjustment @returns @boxed @stock_adjustment_returns_verify_reason_code_without_movement_label_not_listed_for_URRN_and_does_not_have_movement_label @complete
  Scenario Outline: Verify reason code available for 'Store has sent a product not listed for the URRN and does not have a movement label'  (unexpected)
   Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status for adjustment
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>" in stoke
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code and uploaded should be updated

    Examples: 
      | PalletId                         | ASN        | SiteId | Location | ReasonCode                                      |
      | 58850008648480077010086484800300 | 0000864848 | 5885   | REC003   | RMS - Unexpected receipt without movement label |
      
   @stock_adjustment @returns @boxed @stock_adjustment_returns_verify_reason_code_without_movement_label_stock_without_URRN_has_movement_label @complete
  Scenario Outline: Verify reason code available for 'Store has sent stock without any URRN but has a movement label' (Non adviced)
     Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status for adjustment
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>" in stoke
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code and uploaded should be updated

    Examples: 
      | PalletId                         | ASN        | SiteId | Location | ReasonCode                                   |
      | 58850008748480077010087484800300 | 0000874848 | 5885   | REC003   | RMS  Non advised receipt with movement label |

   @stock_adjustment @returns @boxed @stock_adjustment_returns_verify_reason_code_without_any_URRN_or_movement_label @complete
  Scenario Outline: Verify reason code available for 'Store has sent stock without any URRN or movement label' (Non adviced)
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status for adjustment
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>" in stoke
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code and uploaded should be updated

    Examples: 
      | PalletId                         | ASN        | SiteId | Location | ReasonCode                                       |
      | 58850008848480077010088484800300 | 0000884848 | 5885   | REC003   | RMS - Non advised receipt without movement label |
