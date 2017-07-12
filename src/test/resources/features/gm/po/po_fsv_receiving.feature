@purchase_order
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the sku from full service vendor supplier
  So that I can putaway the full service vendor purchase order

  @po_receive_hanging @po @wip
  Scenario Outline: Receiving process in JDA WMS for Hanging type
    Given the PO "<PreAdviceID>" of type "Hanging" should be in 'Released" status at site id "<SiteID>"
    And the PO line should have sku, quantity due details
    And verify PO should not linked with UPI line "<PreAdviceID>"
    When I receive all skus for the purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

    Examples: 
      | PreAdviceID | SiteID | Location |

  #| PO2420001464 | 5885   |  REC001   |
  @po_receive_boxed @po @inprogress
  Scenario Outline: Receiving process in JDA WMS for Boxed type
    Given the PO "<PreAdviceID>" of type "Boxed" should be in "Released" status at site id "<SiteID>"
    And the PO line should have sku, quantity due details
    And verify PO should not linked with UPI line "<PreAdviceID>"
    When I receive all skus for the FSV purchase order at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

    Examples: 
      | PreAdviceID | SiteID | Location |
      |  2420001464 |   5885 | REC001   |
