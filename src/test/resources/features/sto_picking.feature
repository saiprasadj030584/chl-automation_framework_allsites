@sto
Feature: Store Tracking order
  As a warehouse user
  I want to 
  So that I can

  @sto_get_listid
  Scenario Outline: STO for RDC
    Given the STO "<OrderId>" should be "Allocated" status, "RDC" type, order details in the order header table
    And the order should have delivery details
    And the STO should have the SKU,pack config, quantity ordered, quantity tasked,case ratio details for each line items from order line table
    And the STO should have list id, quantity to move,to pallet, to container details from move task table
    When I login as warehouse user in Putty
    And I select user directed option in main menu
    And I select picking with container pick
    Then I should be directed to pick entry page
    When I pick all the list ids for the store tracking order
    Then I should see the picking completion
    #Then the receipt should be generated for the order in inventory transaction table
    
    Examples: 
      | OrderId    |
      | 8800004368 |
