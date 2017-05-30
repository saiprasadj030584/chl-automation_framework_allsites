@putaway
Feature: Putaway for purchase order
  As a warehouse user
  I want to putaway the articles 
  So that I can complete the purchase order

  @complete
  Scenario Outline: Normal putaway for PO
    Given the pre advice id "<preAdviceId>" should be received with "<Category>", "<Status>", "<Location>"
    When I release all the tags for the SKU in the move task update
    And I login as warehouse user in Putty
    And I select user directed option in main menu
    And I select normal putaway
    And I do putaway for all the tags
    And I should see the location zone in inventory query
    Then I should see the from location, to location, final location, uploaded status and uploaded file name

    Examples: 
      | preAdviceId | Category        | Status   | Location |
      |  8050024845 | Ambient         | Released | REC002   |
      |  7150030202 | BWS-Bonded      | Released | REC002   |
      |  7450012190 | BWS-Non-Bonded  | Released | REC002   |
      |  7150011191 | BWS-New Vintage | Released | REC002   |
