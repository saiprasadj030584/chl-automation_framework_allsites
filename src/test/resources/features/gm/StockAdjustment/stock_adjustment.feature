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
    And I perform "Over Receiving" for all skus of the returns order for stock adjustment at location "<Location>" with perfect condition "<Condition>"
    When I navigate to inventory transaction query
    And I query with sku and reason code
    When I navigate to stock adjustments page
    And I change on hand qty and reason code
    Then the inventory is updated with locked status

    #When I navigate to inventory transaction query
    #Then the inventory transaction should be updated
    Examples: 
      | PalletId                         | ASN        | Location | Condition |
      | 56490000369490536120006281700900 | 0000004089 | REC003   | N         |
