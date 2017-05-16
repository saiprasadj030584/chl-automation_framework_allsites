@putaway
Feature: Purchase order
  As a warehouse user
  I want to putaway the articles 
  So that I can complete the purchase order

  @complete
  Scenario Outline: Putaway process in JDA WMS
    #Given I have logged in as warehouse user in JDA dispatcher food application
    And the pre advice id "<preAdviceId>" , "<Category>" , "<Status>" , "<Location>" and "Complete" should be received
    When I navigate to move task update and release all the tags for the SKU
    And I login as warehouse user in Putty
    And I select user directed option in main menu
    And I select normal putaway
    And I do putaway for all the tags
    Then I should be directed to putent page
    When I naviagate to inventory query page
    Then I should see the location zone in inventory page
    When I navigate to inventory transaction query page
    Then I should see the from location, to location and final location for the tag

    Examples: 
      | preAdviceId | Category | Status   | Location |
      |  8050004506 | Ambient  | Released | REC002   |
