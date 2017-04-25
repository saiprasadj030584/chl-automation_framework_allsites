@purchase_order
Feature: Purchase order
  As a warehouse user
  I want to do  
  So that I can

  @wip_receive
  Scenario Outline: Receiving process in JDA WMS
    Given I have logged in as warehouse user in Putty with host "<Host>" and port "<Port>"
    When I select the user directed receiving option in main menu
    And I select the receive under user menu
    And I select the basic receiving
    And I select pre-advice receive
    Then pre-advice entry page should be displayed
    When I enter Pre-advice id "<PreAdviceID>" and SKU id "<SKUId>" to receive from Supplier "<Supplier>"
    Then pre-advice cmp page should be displayed
    When I enter the location "<Location>" and unique tag
    And I enter the quantity to receive "<QtyToReceive>" and case ratio "<CaseRatio>"
    And I specify expiry details
    Then the receiving should be complete
    
    Examples: 
      | Host                                    | Port  | PreAdviceID | SKUId    | Supplier | Location | QtyToReceive | CaseRatio |
      | hlxc0dc024.unix.marksandspencercate.com | 20140 |  8050004526 | 21036013 | F01946   | REC002   |           10 |        15 |
