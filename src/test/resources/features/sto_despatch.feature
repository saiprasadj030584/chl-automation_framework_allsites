@sto_despatch_and_loading
Feature: Despatch STO
  As a warehouse user
  I want to despatch the STO 
  So that the order should be reached to RDC

  @sto_despatch_str_uk_retail
  Scenario Outline: STO despatch for UK Retail
    Given the STO "<OrderId>" of "STR" type should contain order details and be "completely" container picked
    And the order should be "Ready to Load" status
    And the trailer and dock scheduling should be done
    When I get the pallet ids from the move task
    Then the pallet id should be displayed
    When I login as warehouse user in putty
    And I select user directed option in main menu
    And I navigate to load menu
    And I perform vehicle loading for all the pallets
    Then the vehicle loading should be completed and the status should be "Complete" in order header
    And no record should exist for the Order ID
    And the inventory transaction should be generated for vehicle load
    And the order should be in "Complete" status
    When I navigate to Trailer Shipping page
    And I enter the site id and trailer number
    And I enter the seal number
    And I proceed to complete the process
    Then the shipping manifest should be generated
    Then the order closure should be generated in the inventory	for note "Complete --> Shipped"

    Examples: 
      | OrderId    |
      | 6600033100 |

  @sto_despatch_str_eu_store
  Scenario Outline: STO despatch for EU/CI store
    Given the STO "<OrderId>" of "STR" type should contain order details and be "completely" container picked
    And the order should be "Ready to Load" status
    And the trailer and dock scheduling should be done
    When I get the pallet ids from the move task
    Then the pallet id should be displayed
    When I login as warehouse user in putty
    And I select user directed option in main menu
    And I navigate to load menu
    And I perform vehicle loading for all the pallets
    Then the vehicle loading should be completed and the status should be "Complete" in order header
    And no record should exist for the Order ID
    And the inventory transaction should be generated for vehicle load
    And the order should be in "Complete" status
    When I navigate to Trailer Shipping page
    And I enter the site id and trailer number
    And I enter the seal number
    And I proceed to complete the process
    Then the shipping manifest should be generated
    Then the order closure should be generated in the inventory	for note "Complete --> Shipped"

    Examples: 
      | OrderId    |
      | 6600033100 |

  @sto_despatch_str_eu_franchise
  Scenario Outline: STO despatch for EU/CI Franchise
    Given the STO "<OrderId>" of "STR" type should contain order details and be "completely" container picked
    And the order should be "Ready to Load" status
    And the trailer and dock scheduling should be done
    When I get the pallet ids from the move task
    Then the pallet id should be displayed
    When I login as warehouse user in putty
    And I select user directed option in main menu
    And I navigate to load menu
    And I perform vehicle loading for all the pallets
    Then the vehicle loading should be completed and the status should be "Complete" in order header
    And no record should exist for the Order ID
    And the inventory transaction should be generated for vehicle load
    And the order should be in "Complete" status
    When I navigate to Trailer Shipping page
    And I enter the site id and trailer number
    And I enter the seal number
    And I proceed to complete the process
    Then the shipping manifest should be generated
    Then the order closure should be generated in the inventory	for note "Complete --> Shipped"

    Examples: 
      | OrderId    |
      | 6600033100 |

  @sto_despatch_str_uk_franchise
  Scenario Outline: STO despatch for UK franchise
    Given the STO "<OrderId>" of "STR" type should contain order details and be "completely" container picked
    And the order should be "Ready to Load" status
    And the trailer and dock scheduling should be done
    When I get the pallet ids from the move task
    Then the pallet id should be displayed
    When I login as warehouse user in putty
    And I select user directed option in main menu
    And I navigate to load menu
    And I perform vehicle loading for all the pallets
    Then the vehicle loading should be completed and the status should be "Complete" in order header
    And no record should exist for the Order ID
    And the inventory transaction should be generated for vehicle load
    And the order should be in "Complete" status
    When I navigate to Trailer Shipping page
    And I enter the site id and trailer number
    And I enter the seal number
    And I proceed to complete the process
    Then the shipping manifest should be generated
    Then the order closure should be generated in the inventory	for note "Complete --> Shipped"

    Examples: 
      | OrderId    |
      | 6600033100 |

  @sto_despatch_rdc_partial_shipment
  Scenario Outline: STO partial shipment for RDC
    Given the STO "<OrderId>" of "RDC" type should contain order details and be "partially" container picked
    And the order should be "In Progress" status
    And the trailer and dock scheduling should be done
    When I get the pallet ids from the move task
    Then the pallet id should be displayed
    When I login as warehouse user in putty
    And I select user directed option in main menu
    And I navigate to load menu
    And I perform vehicle loading for all the pallets
    Then the vehicle loading should be completed and the status should be "Complete" in order header
    And no record should exist for the Order ID
    And the inventory transaction should be generated for vehicle load
    And the order should be in "Complete" status
    When I navigate to Trailer Shipping page
    And I enter the site id and trailer number
    And I enter the seal number
    And I proceed to complete the process
    Then the shipping manifest should be generated

    Examples: 
      | OrderId    |
      | 8800004470 |

  @sto_despatch_rdc
  Scenario Outline: STO despatch for RDC
    Given the STO "<OrderId>" of "RDC" type should contain order details and be "completely" container picked
    And the order should be "Ready to Load" status
    And the trailer and dock scheduling should be done
    When I get the pallet ids from the move task
    Then the pallet id should be displayed
    When I login as warehouse user in putty
    And I select user directed option in main menu
    And I navigate to load menu
    And I perform vehicle loading for all the pallets
    Then the vehicle loading should be completed and the status should be "Complete" in order header
    And no record should exist for the Order ID
    And the inventory transaction should be generated for vehicle load
    And the order should be in "Complete" status
    When I navigate to Trailer Shipping page
    And I enter the site id and trailer number
    And I enter the seal number
    And I proceed to complete the process
    Then the shipping manifest should be generated
    Then the order closure should be generated in the inventory	for note "Complete --> Shipped"

    Examples: 
      | OrderId    |
      | 8800004470 |

  @sto_despatch_short_pick
  Scenario Outline: STO short picking
    Given the STO "<OrderId>" of "RDC" type should contain order details and be "short picked" container picked
    And the order should be "Ready to Load" status
    And the trailer and dock scheduling should be done
    When I get the pallet ids from the move task
    Then the pallet id should be displayed
    When I login as warehouse user in putty
    And I select user directed option in main menu
    And I navigate to load menu
    And I perform vehicle loading for all the pallets
    Then the vehicle loading should be completed and the status should be "Complete" in order header
    And no record should exist for the Order ID
    And the inventory transaction should be generated for vehicle load
    And the order should be in "Complete" status
    When I navigate to Trailer Shipping page
    And I enter the site id and trailer number
    And I enter the seal number
    And I proceed to complete the process
    Then the shipping manifest should be generated
    Then the order closure should be generated in the inventory	for note "Complete --> Shipped"

    Examples: 
      | OrderId    |
      | 8800004470 |

  @sto_despatch_international
  Scenario Outline: STO despatch for International
    Given the STO "<OrderId>" of "INTSEA" type should contain order details and be "completely" container picked
    And the order should be "Ready to Load" status
    And the trailer and dock scheduling should be done
    When I get the pallet ids from the move task
    Then the pallet id should be displayed
    When I login as warehouse user in putty
    And I select user directed option in main menu
    And I navigate to load menu
    And I perform vehicle loading for all the pallets
    Then the vehicle loading should be completed and the status should be "Complete" in order header
    And no record should exist for the Order ID
    And the inventory transaction should be generated for vehicle load
    And the order should be in "Complete" status
    When I navigate to Trailer Shipping page
    And I enter the site id and trailer number
    And I enter the seal number
    And I proceed to complete the process
    Then the shipping manifest should be generated
    Then the order closure should be generated in the inventory	for note "Complete --> Shipped"

    Examples: 
      | OrderId    |
      | 5800005170 |
