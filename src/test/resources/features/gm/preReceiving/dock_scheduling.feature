Feature: Dock Scheduling
  As a warehouse user
  I want to schedult a dock door
  So that I can receive the PO in the scheduled dock door

  @wip_dock
  Scenario Outline: Validate whether ASN can be assigned using the Container ID - Direct PO
    Given the PO "<PreAdviceID>", UPI "<UPIId>", ASN "<ASNId>" details should be displayed
    And I create a trailer to receive at the dock door
    When I navigate to dock scheduler page
    When I choose create new booking for the site id
    When I choose the booking type and provide the ASN
    And I add the ASN details
    And I create a booking for the asn
    Then the dock door scheduling should be complete

    Examples: 
      | PreAdviceID | UPIId | ASNId |
      |             |       |       |
