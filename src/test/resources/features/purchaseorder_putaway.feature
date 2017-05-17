@putaway
Feature: Putaway for purchase order
  As a warehouse user
  I want to putaway the articles 
  So that I can complete the purchase order

  @complete
  Scenario Outline: Normal putaway for PO
    #Given I have logged in as warehouse user in JDA dispatcher food application
    And the pre advice id "<preAdviceId>" , "<Category>" , "<Status>" , "<Location>" and "Complete" should be received
    When I navigate to move task update and release all the tags for the SKU
    And I login as warehouse user in Putty
    And I select user directed option in main menu
    And I select normal putaway
    And I do putaway for all the tags
    Then I should see the location zone in inventory page
    When I navigate to inventory transaction query page
    Then I should see the from location, to location, final location, uploaded status and uploaded file name for the tags

    Examples: 
      | preAdviceId | Category | Status   | Location |
      |  8050004519 | Ambient  | Released | REC002   |
