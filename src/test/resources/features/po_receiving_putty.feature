@purchase_order
Feature: Purchase order
  As a warehouse user
  I want to receive the articles 
  So that I can complete the purchase order

  @wip_receive1
  Scenario Outline: Receive Purchase order in JDA WMS
    Given I have logged in as warehouse user in putty with host "<Host>" and port "<Port>"
    #When I select the user directed receiving option in main menu
    #And I select the receive under user menu
    #And I select the basic receiving
    #And I select pre-advice receive
    #Then pre-advice entry page should be displayed
    #When I enter Pre-advice id "<PreAdviceID>" and SKU id "<SKUId>" to receive from Supplier "<Supplier>"
    #Then pre-advice cmp page should be displayed
    #When I enter the location "<Location>" and unique tag
    #And I enter the quantity to receive "<QtyToReceive>" and case ratio "<CaseRatio>"
    #And I specify expiry details
    #Then the receiving should be complete
    #When I receive the remaining SKU ids

    Examples: 
      | Host                                    | Port  | PreAdviceID | SKUId    | Supplier | Location | QtyToReceive | CaseRatio |
      | hlxc0dc024.unix.marksandspencercate.com | 20140 |  8050004526 | 21036013 | F01946   | REC002   |           10 |        15 |

  @wip
  Scenario Outline: Receive Purchase order in JDA WMS 2
    Given I have logged in as warehouse user in putty with host "<Host>" and port "<Port>"
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I enter pre-advice id "8050004526" and SKU id "21036013"
    Then the pre-advice id and supplier id should be displayed in the receive pre-adviace page
    When I enter the location "<Location>" and tag
    And I enter the quantity to receive "<QtyToReceive>" and case ratio "<CaseRatio>"
    And I enter the expiry details
    Then I should see the receiving completion
    When I receive the remaining articiles
    Then I should see the receiving completion
