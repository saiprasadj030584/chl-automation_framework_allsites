@Validate_goods_error
Feature: Validate goods error before putaway
  As a warehouse user
  I want to validate the goods
  So that I can putaway the purchase order

  @validate_goods_error_after_putaway
  Scenario Outline: validating goods error before putaway
    Given the PO "<PreAdviceID>" of type "Boxed" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    When I do normal putaway for all tags received
    Then the inventory should be displayed for all putaway tags
    And the goods receipt should be generated for putaway stock in inventory transaction
    When I navigate to stock adjustments page
    And I create a new stock with siteid "<SiteId>" and location "<Location>"
    And I choose the reason code as "<ReasonCode>"
    When I navigate to inventory transaction query
    And I choose the code as "Adjustment" and search the sku id
    Then the reason code should be updated

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location | SiteId | ReasonCode           |
      | PO2010002004 | PO050456000511235613 | PO00100503 | REC001   |   5649 | Receiving correction |
