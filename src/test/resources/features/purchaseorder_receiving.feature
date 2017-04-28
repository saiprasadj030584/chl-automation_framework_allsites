@purchase_order
Feature: Purchase order
  As a warehouse user
  I want to receive the articles 
  So that I can complete the purchase order

  @wip_receive
  Scenario Outline: Receiving process in JDA WMS
    Given I have logged in as warehouse user in Putty with host "<Host>" and port "<Port>"
    And I want to receive the purchase order "<PreAdviceID>", "<NoOfLines>", "<Supplier>"
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus for pre-advice id "<PreAdviceID>" and at location "<Location>"
    Then I should see the receiving completion

    Examples: 
      | Host                                    | Port  | PreAdviceID | NoOfLines | Supplier | Location |
      | hlxc0dc024.unix.marksandspencercate.com | 20140 |  8050005815 |         2 | F01946   | REC002   |
