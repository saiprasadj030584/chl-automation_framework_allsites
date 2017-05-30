@sto_replenish_pick
Feature: Store Replenishment order
  As a warehouse user
  I want to allocate orders in inventory
  So that do  i can replenishment

  
  @replenish_pick
  Scenario Outline: Replenishment list id generation and picking
    #Given I have logged in as warehouse user in JDA dispatcher food application
    #And the tagid, quantity to move details should be displayed for the sku "<skuid>" with "REPLENISH " tasks
    #When I navigate to move task list generation page
    #When I generate move task lists for all tags
    #Then the list ids should be generated
    When the replenish STO should have list id,quantity to move, tagid, location details and case ratio
    When I release all the tags in the move task update
    When I login as warehouse user in putty
    And I select user directed option in main menu
    And I select picking with replenish pick
    Then I should be directed to pick entry page
    When I pick all the list ids for the replenish task
    Then I should see the picking completion
    Then qty on hand should be updated in inventory query page
    Then qty on hand and location details should be updated for tagid '1000000060' and taskid 'REPLENISH'
    

    Examples: 
      | skuid    |
      | 21106955 |
