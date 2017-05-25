@putaway
Feature: Putaway for purchase order
  As a warehouse user
  I want to putaway the articles 
  So that I can complete the purchase order

  @complete
  Scenario Outline: Normal putaway for PO
    Given the pre advice id "<preAdviceId>" should be received with "<Category>", "<Status>", "<Location>"
    When I release all the tags for the SKU in the move task update
    And I login as warehouse user in Putty
    And I select user directed option in main menu
    And I select normal putaway
    And I do putaway for all the tags
    And I should see the location zone in inventory query
    Then I should see the from location, to location, final location, uploaded status and uploaded file name

    Examples: 
      | preAdviceId | Category | Status   | Location |
      |  8050003545 | Ambient  | Released | REC002   |
      #|  7150010138 | BWS-Bonded      | Released | REC002   |
      #|  0072000009 | BWS-Non-Bonded  | Released | REC002   |
      #|  8150010139 | BWS-New Vintage | Released | REC002   |

      
 @po_receive_ambient1
  Scenario Outline: Receiving process in JDA WMS for Ambient1 product category
    Given the PO "<PreAdviceID>" with "Ambient" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    And the PO should have address detail
    Then the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
    When I have logged in as warehouse user in Putty
    When I select user directed option in main menu
    And I receive the PO with basic and pre-advice receiving
    Then I should be directed to pre-advice entry page
    When I receive all the skus for the purchase order at location "<Location>"
    And I should see the receiving completion
    Then the inventory details should be displayed for all the tag ids
    And the status should be displayed as "Complete"
    Then the goods receipt should be generate for the received stock in inventory transaction table

    Examples: 
      | PreAdviceID | Location |
      |  8050004520 | REC002   |