Feature: Perform Clustering
  As a warehouse user
  I want to perform manual clustering

  @boxed @picking @retail @boxed_picking_retail_manual_clustering @complete
  Scenario Outline: Validate whether Clustering is done manually
    Given I have logged in as warehouse user in JDA dispatcher GM application
    Given the OrderID "<OrderID>" of type "Retail" should be in "Released" status
    When I navigate to system allocation page
    And I enter OrderID as "<OrderID>" for clustering
    Then the order should be allocated for the orderID "<OrderID>"
    When I navigate to move task list generation page
    And I enter OrderID as "<OrderID>"
    Then I create the list Id
    When I navigate to move task query page
    And I enter OrderID as "<OrderID>" in move task
    Then the list Id should be generated

    Examples: 
      | OrderID    |
      | 4170001426 |
