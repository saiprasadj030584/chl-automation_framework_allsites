@Validate_goods_error
Feature: Validate goods error before putaway
  As a warehouse user
  I want to validate the goods
  So that I can putaway the purchase order

  @validate_goods_error_before_putaway
  Scenario Outline: validating goods error before putaway
    Given the PO "<PreAdviceID>" of type "Hanging" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    #And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"
    #When I navigate to receipt reversal page
    #And I do receipt reversal for the tag received
    #Then the inventory transaction should be updated with reversed receipt tag

    Examples: 
      | PreAdviceID  | PalletId             | ASN        | Location |
      | PO2010002410 | PO000504560005112450 | PO00100412 | REC001   |
      #| PO2010002007 | PO050456000511235616 | PO00100506 | REC001   |
