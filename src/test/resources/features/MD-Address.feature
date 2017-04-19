@master_data
Feature:Load site details into address table
  As a Warehouse user
  I want to load site details
  So that site details will be accessed from address table

  @address_table
  Scenario: Load the site details
  #Given I have logged in as warehouse user in JDA dispatcher food application
    When I navigate to address maintenance page
    And I search the address id"0008"
    Then the address type, name, address1 and country should be displayed
    When I navigate to customs & excise tab	
    Then the CE & warehouse type should be displayed
    When I navigate to user defined tab
    Then is site flag should be updated as site
    And the site category should be displayed
   
 @address_table1
  Scenario: Load the vendor details
  #Given I have logged in as warehouse user in JDA dispatcher food application
    When I navigate to address maintenance page
    And I search the address id"F01501"
    Then the address type, name, address1 and country should be displayed
    When I navigate to customs & excise tab	
    Then the CE & warehouse type should be displayed
    When I navigate to user defined tab
   Then is site flag should be unchecked	
