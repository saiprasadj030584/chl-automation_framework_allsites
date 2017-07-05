@purchase_order_lockcode
Feature: Purchase order Putaway
  As a warehouse user
  I want to  lock the product with lockcode
  So that those product cannot be used for allocation

  @lockcode
  Scenario Outline: Putaway process in JDA WMS for Hanging type
    Given the PO "<PreAdviceID>" of type "Hanging" with UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header and po to be linked with upi line
    And the PO is locked with lockcode "QAFTS" in pre advice line
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

    #When I do normal putaway for all tags received for the lockcode "QAFTS"
    #Then the  error message should be displayed
    Examples: 
      | PreAdviceID | PalletId            | ASN    | Location |
      |   124124124 | 0055000290955260999 | 124124 | REC001   |
