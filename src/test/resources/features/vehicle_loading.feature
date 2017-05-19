@vehicle_loading
Feature: Vehicle loading for Stock Transfer order
  As a warehouse user
  I want to perform vehicle loading
  So that I can ship the orders

  @sara_wip
  Scenario Outline: Vehicle load for STO using putty
    Given I have logged in as warehouse user in JDA dispatcher food application
    And the Order id "<OrderId>" , "<Category>" , "<Status>" , "<Location>" and "Complete" should be received
    When I navigate to move task update and release all the tags for the SKU
    And I login as warehouse user in Putty
    And I select user directed option in main menu
    And I select normal putaway
    And I do putaway for all the tags
    Then I should see the location zone in inventory page
    When I navigate to inventory transaction query page
    Then I should see the from location, to location, final location, uploaded status and uploaded file name for the tags

    Examples: 
      | OrderId | Category | Status   | Location |
      |  8050004519 | Ambient  | Released | REC002   |
