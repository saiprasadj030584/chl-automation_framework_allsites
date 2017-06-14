@location_status_change
Feature: Location Status Change
  As a warehouse user
  I want to change the location status of REC lane
  So that I manage the locations effectively

  @complete @po @location_status_change
  Scenario Outline: Change REC lane lock status
    Given I have logged in as warehouse user in JDA dispatcher food application
    And I navigate to location maintenance page
    And I search with location ID "<LocationID>"
    Then the location record should be displayed
    When I update the location lock status as "<LockStatus>"
    Then the updated lock status should be displayed

    Examples: 
      | LocationID | LockStatus |
      | REC127     | Inlocked   |
      | REC127     | Locked     |
      | REC127     | Unlocked   |
      | REC127     | Outlocked  |
	
	
	@complete @po @location_status_change_negative
  Scenario Outline: Change REC lane lock status
    #Given I have logged in as warehouse user in JDA dispatcher food application
    And I navigate to location maintenance page
    And I search with location ID "<LocationID>"
    Then the location record should not be displayed

    Examples: 
      | LocationID | LockStatus |
      | RED127     | Inlocked   |
