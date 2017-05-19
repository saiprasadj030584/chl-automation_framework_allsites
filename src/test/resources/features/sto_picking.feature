@sto
Feature: Store Tracking order
  As a warehouse user
  I want to 
  So that I can

  @sto_get_listid
  Scenario Outline: STO for RDC
    Given the STO "<OrderId>" should be "Released" status, "RDC" type, order details in the order header table
    And the order should have delivery details
    And the STO should have the SKU,pack config, quantity ordered, quantity tasked,case ratio details for each line items from order line table

    #And the STO "<OrderId>" should have list id, quantity to move,to pallet, to container details from move task table
    #When I have logged in as warehouse user in Putty
    #When I select user directed option in main menu
    #And I select picking with container pick
    #Then I should be directed to pick entry page
    #When I pick all the list ids for the store tracking order
    #When I receive all the skus for the purchase order at location "<Location>"
    #Then I should see the receiving completion
    Examples: 
      | OrderId    |
      | 6600033165 |
