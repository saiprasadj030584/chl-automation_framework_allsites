@purchase_order
Feature: Purchase order
  As a warehouse user
  I want to receive the articles 
  So that I can complete the purchase order

  @wip6
  Scenario Outline: Receiving process in JDA WMS for Ambient product category
    Given the PO "<PreAdviceID>" should be "<Status>" status and have line items
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice line items
    And the ABV should be more than ten percentage of its actual value
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus for the purchase order at location "<Location>" with ABV more than ten percentage
    Then the error message should be displayed in receiving
    Then I proceed to complete the receiving
    When I have logged in as warehouse user in JDA dispatcher food application
    And I navigate to inventory query page
    Then the ABV should be updated for all the Tag Id

    Examples: 
      | PreAdviceID | Status   |Location|
      |  7150010189 | released |REC002	|

  @wip7
  Scenario Outline: Receiving process in JDA WMS for Ambient product category
    Given the PO "<PreAdviceID>" should be "<Status>" status and have line items
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice line items
    And the ABV should be within ten percentage  of its actual value
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus for the purchase order at location "<Location>" with ABV more than ten percentage
    Then the receiving should be completed
    When I have logged in as warehouse user in JDA dispatcher food application
    And I navigate to inventory query page
    Then the ABV should be updated for all the Tag Id

    Examples: 
      | PreAdviceID | Status   |Location|
      |  7150010189 | released |REC002	|

  @wip8
  Scenario Outline: Receiving process in JDA WMS for Ambient product category
    Given the PO "<PreAdviceID>" should be "<Status>" status and have line items
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice line items
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus for the purchase order at location "<Location>" with no ABV 
    Then the error message should be displayed as enter the ABV for the UPC

    Examples: 
      | PreAdviceID | Status   |Location|
      |  7150010189 | released |REC002	|
      
   @wip9
  Scenario Outline: Receiving process in JDA WMS for Ambient product category
    Given the PO "<PreAdviceID>" should be "<Status>" status and have line items
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice line items
    And I have the vintage for each line item
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive  the skus for each line item
    Then the error message should be displayed in receiving

    Examples: 
      | PreAdviceID | Status   |Location|
      |  7150010189 | released |REC002	|
      
      @wip10
  Scenario Outline: Receiving process in JDA WMS for Ambient product category
    Given the PO "<PreAdviceID>" should be "<Status>" status and have line items
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice line items
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive  the skus for each line item
    Then the error message should be displayed in receiving

    Examples: 
      | PreAdviceID | Status   |Location|
      |  7150010189 | released |REC002	|
