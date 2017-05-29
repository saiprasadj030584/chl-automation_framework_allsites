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
      | preAdviceId | Category        | Status   | Location |
      #|  8050003645 | Ambient         | Released | REC002   |
      #|  7150030202 | BWS-Bonded      | Released | REC002   |
      #|  0072000009 | BWS-Non-Bonded  | Released | REC002   |
      |  7150011189 | BWS-New Vintage | Released | REC002   |

  #@po_receive_ambient1
  #Scenario Outline: Receiving process in JDA WMS for Ambient1 product category
    #Given the PO "<PreAdviceID>" with "Ambient" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    #And the PO should have address detail
    #Then the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    #And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
    #When I have logged in as warehouse user in Putty
    #When I select user directed option in main menu
    #And I receive the PO with basic and pre-advice receiving
    #Then I should be directed to pre-advice entry page
    #When I receive all the skus for the purchase order at location "<Location>"
    #And I should see the receiving completion
    #And I logout the putty
    #Then the inventory details should be displayed for all the tag ids
    #And the status should be displayed as "Complete"
    #Then the goods receipt should be generate for the received stock in inventory transaction table
#
    #Examples: 
      #| PreAdviceID | Location |
      #|  8050004845 | REC002   |
#
  #@po_receive_bws_bonded1
  #Scenario Outline: Receiving process in JDA WMS for BWS-Bonded product category
    #Given the PO "<PreAdviceID>" with "BWS-Bonded" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    #And the PO should have address detail
    #Then the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    #And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
    #When I create consignment for the supplier
    #And I link the consignment to the pre-advice ID
    #Then the pre-advice line items should be linked with the consignment
    #When I have logged in as warehouse user in Putty
    #When I select user directed option in main menu
    #And I receive the PO with basic and pre-advice receiving
    #Then I should be directed to pre-advice entry page
    #When I receive all the skus for the purchase order at location "<Location>"
    #Then I should see the receiving completion
    #And I logout the putty
    #Then the inventory details should be displayed for all the tag ids
    #And the status should be displayed as "Complete"
    #Then the goods receipt should be generate for the received stock in inventory transaction table
#
    #Examples: 
      #| PreAdviceID | Location |
      #|  7150020202 | REC002   |
#
  #@po_receive_bws_non_bonded1
  #Scenario Outline: Receiving process in JDA WMS for BWS-Non-Bonded product category
    #Given the PO "<PreAdviceID>" with "BWS-Non-Bonded" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    #And the PO should have address detail
    #Then the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    #And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
    #When I have logged in as warehouse user in Putty
    #When I select user directed option in main menu
    #And I receive the PO with basic and pre-advice receiving
    #Then I should be directed to pre-advice entry page
    #When I receive all the skus for the purchase order at location "<Location>"
    #Then I should see the receiving completion
    #And I logout the putty
    #Then the inventory details should be displayed for all the tag ids
    #And the status should be displayed as "Complete"
    #Then the goods receipt should be generate for the received stock in inventory transaction table
#
    #Examples: 
      #| PreAdviceID | Location |
      #|  7450010189 | REC002   |
#
  #@po_receive_bws_new_vintage1
  #Scenario Outline: Receiving process in JDA WMS for BWS-Bonded product category
    #Given the PO "<PreAdviceID>" with "BWS-Non-Bonded" category should be "Released" status and have future due date, site id, no of lines in the pre-advice header maintenance table
    #And the PO should have address detail
    #Then the supplier should have supplier pallet and customs excise detail in the address maintenanace table
    #And the PO should have the SKU, quantity due, tracking level, pack config, under bond, case ratio, base UOM details for each pre-advice lines items
    #And the sku should not have any inventory in that particular vintage
    #When I create consignment for the supplier
    #And I link the consignment to the pre-advice ID
    #Then the pre-advice line items should be linked with the consignment
    #When I have logged in as warehouse user in Putty
    #When I select user directed option in main menu
    #And I receive the PO with basic and pre-advice receiving
    #Then I should be directed to pre-advice entry page
    #When I receive all the skus for the purchase order at location "<Location>"
    #Then I should see the receiving completion
    #And I logout the putty
    #Then the inventory details should be displayed for all the tag ids
    #And the status should be displayed as "Complete"
    #Then the goods receipt should be generate for the received stock in inventory transaction table
    #
        #Examples: 
      #| PreAdviceID | Location |
      #|  7150011189 | REC002   |
