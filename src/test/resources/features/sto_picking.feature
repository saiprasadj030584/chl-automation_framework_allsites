@sto
Feature: Store Tracking order
  As a warehouse user
  I want to 
  So that I can

  @sto_get_listid
  Scenario Outline: STO
    Given the STO "<OrderId>" should have list id, quantity to move,to pallet, to container details

    Examples: 
      | OrderId    |
      | 6666164803 |
