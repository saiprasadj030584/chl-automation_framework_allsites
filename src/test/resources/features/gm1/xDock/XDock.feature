@XDock
Feature: XDock
  As a warehouse user
  I want to perform Xdock of stocks

  @boxed_xDock_receiving @xDock @boxed
  Scenario Outline: xdock
    Given the "Boxed" PO "<PreAdviceID>" and UPI "<PalletId>" and ASN "<ASN>" and order "<OrderId>" should be in "Released" status
   And order header line to be linked with upi header line
    And I create a trailer to receive at the dock door
    And I create a consignment for order "<OrderId>"
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn for xDOCK
   When I perform receiving for all skus at location for x dock
    When I perform marshalling for cross dock
    And I proceed for multi pallet load with dock door
    Then I navigate to Trailer Shipping page
    And I enter the siteId and shipping details
    Then Shipping message should be displayed

    Examples: 
      | PreAdviceID | PalletId            | ASN        | OrderId    | SiteId |
      |  0000938280 |5885127529980280317 | 0000938280 | 6612009834 |   3366 |

  Scenario Outline: xdock
    Given the "Hanging" PO "<PreAdviceID>" and UPI "<PalletId>" and ASN "<ASN>" and order "<OrderId>" should be in "Released" status
    And order header line to be linked with upi header line
    And I create a trailer to receive at the dock door
     And I create a consignment for order "<OrderId>"
    When I navigate to dock scheduler start page
    When I create new dock booking at site "<SiteId>"
    When I select the booking type for consignment
    And I select the slot
    And I create a booking for the asn for xDOCK
    Then the booking details should appear in the dock scheduler booking
    When I perform receiving for all skus at location for x dock
    When I perform marshalling for cross dock
    And I proceed for multi pallet load with dock door
    Then I navigate to Trailer Shipping page
    And I enter the siteId and shipping details
    Then Shipping message should be displayed

    Examples: 
      | PreAdviceID | PalletId            | ASN        | OrderId    |
      |  0000991280 | 5885120521980280317 | 0000991280 | 6612108834 |
