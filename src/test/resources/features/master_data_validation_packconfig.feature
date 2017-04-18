@master_data
Feature: Master data validation
  As a warehouse user
  I want to validate master data 
  So that I can use them for PO receiving putaway and allocation

  @wip
  Scenario: Validate Pack config table in JDA WMS dispatcher for I016
    #Given I have logged in as warehouse user in JDA dispatcher food application
    And I am on pack config maintenance page
    When I search pack config id as "20001452O01"
    Then the tag volume, volume at each details should be displayed for  given pack config id
    And I click tracking levels tab
    Then the ratios, tracking levels sholud be displayed for given pack config id
    And I click RDT tab
    Then the RDT tracking levels sholud be displayed for given pack config id
