@pick_face
Feature: Pick face validation
  As a warehouse user
  I want to validate pick face
  So that I can use them for

@Wip_pick1
  Scenario: Validate Pick face 
    #Given I have logged in as warehouse user in JDA dispatcher food application
    And the location id "AA01E02" is no more eixst in the location maintenance
    When I add the location Id "AA01E02" with face type "fixed", sku "20001273", site id "9771"
    Then the location id should be added
    When I navigate to location maintenance page
    And I search location Id  "AA01E02"
    Then the pick face should be updated as "Fixed"
