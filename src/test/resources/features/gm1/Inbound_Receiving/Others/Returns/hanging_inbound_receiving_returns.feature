@purchase_order_receiving
Feature: Purchase order receiving
  As a warehouse user
  I want to receive the articles
  So that I can putaway the purchase order

  
      
     @hanging @inbound_receiving @returns @hanging_inbound_receiving_returns_multiple_urn_single_trailer @review
  Scenario Outline: Receiving process in JDA WMS for Hanging type
    Given the PO "<PreAdviceID>" of type "Hanging" with multiple UPI "<PalletId>" and ASN "<ASN>" should be in "Released" status with line items,supplier details
    And the PO with multiple upi should have sku, quantity due details
    And the pallet count should be updated in delivery, asn to be linked with upi header list and po to be linked with upi line
    When I receive all skus for the purchase order with multiple upi at location "<Location>"
    Then the inventory should be displayed for all tags received
    And the goods receipt should be generated for received stock in inventory transaction
    Then the po status should be displayed as "Complete"

    Examples: 
      | PreAdviceID | PalletId                                  | ASN        | Location |
      |  9090002070 | 56490000357211524500009081300308,56490000357211524600009081300308 | 0000006174 | REC001   |

      