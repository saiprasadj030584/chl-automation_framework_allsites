@generate_stock_check_list
Feature: Generate stock check list
  
  As a warehouse user
  I want to generate stock check list 
  So that I can use the list to perform stock check

  @sara
  Scenario: Validate generation of stock check list by location
    #Data :  I verify if any inventory exists for the location "AB01C02" and site ID "9771"
    Given I have logged in as warehouse user in JDA dispatcher food application
    When I navigate to stock check list Generation page
    And I select mode of list generation as 'Generate by location'
    And I select the site ID as "9771" and from location as "AB01C02" on location tab
    And I proceed to next
    Then the available list should be displayed
    When I select the record from the available list
    Then the record should be added in the selected list
    When I proceed to next
    Then I should see the confirmation for number of location checked
    When I proceed to generate the stock check list
    Then I should see the created list
    ## verify the generated stock check list by location
    When I navigate to stock check query page
    And I search the list by site id as "9771", location as "AB01C02" and task date as current date
    Then I should see the created list
