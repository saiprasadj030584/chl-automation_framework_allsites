@pick_face
Feature: Pick face validation
  As a warehouse user
  I want to validate pick face
  So that I can use them for

  @complete
  Scenario Outline: Validate Pick face
    Given I have logged in as warehouse user in JDA dispatcher food application
    And the location id "<LocationID>" is no more eixst in the location maintenance
    When I add the location Id "<LocationID>" with face type "Fixed", sku "<SkuID>", site id "9771"
    Then the location id should be added
    And I search location Id  "<LocationID>"
    Then the pick face should be updated as "Fixed"

    Examples: 
      | LocationID | SkuID    |
      | AA05A02    | 20001273 |
