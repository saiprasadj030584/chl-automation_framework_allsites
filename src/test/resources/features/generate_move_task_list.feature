@generate_replenish_move_task_list
Feature: Generate move task list
  As a warehouse user
  I want to generate move task list 
  So that I can use the list to perform replenishment

  @replenish_listid_generation
  Scenario: Validate generation of move task list
    Given I have logged in as warehouse user in JDA dispatcher food application
    And the tagid, skuid, quantity to move details should be displayed for "REPLENISH" tasks without list ids
    When I navigate to move task list generation page
    When I generate move task lists for all tags
    Then the list ids should be generated
