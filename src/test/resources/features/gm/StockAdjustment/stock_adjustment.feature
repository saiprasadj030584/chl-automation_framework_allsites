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

  @stock_adjustment_returns_verify_reason_code_and_has_movement_label
  Scenario Outline: Verify reason code available for 'Store has sent greater quantity than the expected volume for a product within the URRN and has a movement label.'
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I perform "Over Receiving" for all skus of the returns order for stock adjustment at location "<Location>" with perfect condition "<Condition>" and movement label enabled
    When I navigate to inventory transaction query
    And I search with sku and reason code
    When I navigate to stock adjustments page
    And I change on hand qty and reason code to "RMS - Over receipt against HU with movement label"
    Then the inventory transaction is updated with locked status
    When I navigate to inventory update page
    And I change the lock status to unlocked
    Then the inventory is unlocked and the return stock is over received

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 95580085370650011050230212341638 | 0000849729 | REC003   | N         |

  @stock_adjustment_returns_verify_reason_code_without_movement_label
  Scenario Outline: Verify reason code available for 'Store has sent greater quantity than the expected volume for a product within the URRN and has a movement label.'
    Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
    And I perform "Over Receiving" for all skus of the returns order for stock adjustment at location "<Location>" with perfect condition "<Condition>"
    When I navigate to inventory transaction query
    And I search with sku and reason code
    When I navigate to stock adjustments page
    And I change on hand qty and reason code to "RMS - Over receipt against HU without movement label"
    Then the inventory transaction is updated with locked status
    When I navigate to inventory update page
    And I change the lock status to unlocked
    Then the inventory is unlocked and the return stock is over received

    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850008347480077010083474800300 | 0000834748 | REC003   | N         |
      
      @stock_adjustment_returns_verify_reason_code_without_movement_label
      Scenario Outline:Verify reason code available for 'Store has sent a product not listed for the URRN and has a movement label' (unexpected)
       Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
       When I navigate to stock adjustments page
      
      Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850008347380077010083473800300 | 0000834738 | REC003   | N         |
      
      
      @stock_adjustment_returns_verify_reason_code_without_movement_label
      Scenario Outline:Verify reason code available for 'Store has sent a product not listed for the URRN and does not have a movement label'  (unexpected)
       Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
      
      Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850008347380077010083473800300 | 0000834738 | REC003   | N         |
      
      
      @stock_adjustment_returns_verify_reason_code_without_movement_label
      Scenario Outline:Verify reason code available for 'Store has sent stock without any URRN but has a movement label' (Non adviced)
       Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status
      
      Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 58850008347380077010083473800300 | 0000834738 | REC003   | N         |
      
      
      @stock_adjustment_returns_verify_reason_code_without_any_URRN_or_movement_label
      Scenario Outline:Verify reason code available for 'Store has sent stock without any URRN or movement label' (Non adviced)
       Given the UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status for adjustment
       When I navigate to stock adjustments page
        And I create a new stock with siteid "<SiteId>" and location "<Location>" in stoke
         And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code and uploaded should be updated
      
      Examples: 
      | PalletId                         | ASN        | Location | ReasonCode 																		|
      | 58850008447380077010084473800300 | 0000844738 | REC003   | RMS-Non advised receipt without movement label |
      
