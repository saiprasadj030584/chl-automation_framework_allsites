@flatpack_picking_retail_manual_clustering
Feature: Flatpack - Retail - Manual Clustering
  As a warehouse user
  I want to perform manual clustering

  @flatpack @picking @retail @flatpack_picking_retail_validate_whether_clustering_is_done_manually @complete @ds
  Scenario: Validate whether Clustering is done manually
    Given I have logged in as warehouse user in JDA dispatcher GM application
    Given the order of "Retail" should be in "Released" status in order header maintenance
    When I navigate to system allocation page
    And I enter OrderID for clustering
    Then the order should be in "Allocated" status in order header maintenance
    And the move task for the order must be released
    When I navigate to move task list generation page
    And I enter OrderID
    Then I create the list Id
    When I navigate to move task query page
    And I enter OrderID in move task
    Then the list Id should be generated
