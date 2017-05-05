#@purchase_order
Feature: Purchase order
  As a warehouse user
  I want to receive the articles 
  So that I can complete the purchase order

  #@wip_receive
  Scenario Outline: Receiving process in JDA WMS
    Given I have logged in as warehouse user in Putty with host "<Host>" and port "<Port>"
    And I want to receive the purchase order "<PreAdviceID>", "<NoOfLines>", "<Supplier>"
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I enter pre-advice id "<PreAdviceID>" and SKU id
    Then the pre-advice id and supplier id should be displayed in the receive pre-advice page
    When I enter the location "<Location>" and tag
    And I enter the quantity to receive and case ratio
    And I enter the expiry details
    Then I should see the receiving completion
    When I receive remaining skus
    Then I should see the receiving completion

    Examples: 
      | Host                                    | Port  | PreAdviceID | NoOfLines | Supplier | Location |
      | hlxc0dc024.unix.marksandspencercate.com | 20139 |  8050004530 |         2 | F01946   | REC002   |
