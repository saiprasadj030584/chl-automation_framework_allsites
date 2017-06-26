@sto_despatch_and_loading
Feature: Despatch STO
  As a warehouse user
  I want to despatch the STO 
  So that the order should be reached to RDC

  @sto_despatch_str_uk_retail @complete @sto @data1
  Scenario Outline: STO despatch for UK Retail
    Given the STO "<OrderId>" of "STR" type should contain order details and be "completely" container picked for customer "0437"
    #And the order should be "Ready to Load" status
    #And the trailer and dock scheduling should be done
    #When I get the pallet ids from the move task
    #Then the pallet id should be displayed
    #When I login as warehouse user in putty
    #And I select user directed option in main menu
    #And I navigate to load menu
    #And I perform vehicle loading for all the pallets
    #Then the vehicle loading should be completed and the status should be "Complete" in order header
    #And no record should exist for the Order ID
    #And the inventory transaction should be generated for vehicle load
    #And the order should be in "Complete" status
    #When I navigate to Trailer Shipping page
    #And I enter the site id and trailer number
    #And I enter the seal number
    #And I proceed to complete the process
    #Then the shipping manifest should be generated
    #Then the order closure should be generated in the inventory	for note "Complete --> Shipped"

    Examples: 
      | OrderId    |
      #| 5900005201 |
       #| 8800004391 |
       |STO5905100|

  @sto_despatch_str_eu_store @complete @sto
  Scenario Outline: STO despatch for EU/CI store
    Given the STO "<OrderId>" of "STR" type should contain order details and be "completely" container picked for customer "0065"
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
      | 5900005208 |

  @sto_despatch_str_eu_franchise @sto @onhold
  Scenario Outline: STO despatch for EU/CI Franchise
    Given the STO "<OrderId>" of "STR" type should contain order details and be "completely" container picked for customer "8468"
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

  @sto_despatch_str_uk_franchise @sto @onhold
  Scenario Outline: STO despatch for UK franchise
    Given the STO "<OrderId>" of "STR" type should contain order details and be "completely" container picked for customer "so"
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

  @sto_despatch_rdc_partial_shipment @complete @sto 
  Scenario Outline: STO partial shipment for RDC
    Given the STO "<OrderId>" of "RDC" type should contain order details and be "partially" container picked for customer "3942"
    And the order should be "In Progress" status
    And the trailer and dock scheduling should be done
    When I get the pallet ids from the move task
    Then the pallet id should be displayed
    When I login as warehouse user in putty
    And I select user directed option in main menu
    And I navigate to load menu
    And I perform vehicle loading for all the pallets
    Then the vehicle loading should be completed and the status should be "In Progress" in order header
    #And no record should exist for the Order ID
    And the inventory transaction should be generated for vehicle load
    And the order should be in "In Progress" status
    When I navigate to Trailer Shipping page
    And I enter the site id and trailer number
    And I enter the seal number
    And I proceed to complete the process
    Then the shipping manifest should be generated

    Examples: 
      | OrderId    |
      | 5900005209 |

  @sto_despatch_rdc @sto @complete @data
  Scenario Outline: STO despatch for RDC
    Given the STO "<OrderId>" of "RDC" type should contain order details and be "completely" container picked for customer "9010"
    #And the order should be "Ready to Load" status
    #And the trailer and dock scheduling should be done
    #When I get the pallet ids from the move task
    #Then the pallet id should be displayed
    #When I login as warehouse user in putty
    #And I select user directed option in main menu
    #And I navigate to load menu
    #And I perform vehicle loading for all the pallets
    #Then the vehicle loading should be completed and the status should be "Complete" in order header
    #And no record should exist for the Order ID
    #And the inventory transaction should be generated for vehicle load
    #And the order should be in "Complete" status
    #When I navigate to Trailer Shipping page
    #And I enter the site id and trailer number
    #And I enter the seal number
    #And I proceed to complete the process
    #Then the shipping manifest should be generated
    #Then the order closure should be generated in the inventory	for note "Complete --> Shipped"

    Examples: 
      | OrderId    |
      | 5900005210 |
      #|9860005176|

  @sto_despatch_short_pick
  Scenario Outline: STO short picking
    Given the STO "<OrderId>" of "RDC" type should contain order details and be "short picked" container picked for customer "9010"
    And the order should be "In Progress" status
    And the trailer and dock scheduling should be done
    When I get the pallet ids from the move task
    Then the pallet id should be displayed
    When I login as warehouse user in putty
    And I select user directed option in main menu
    And I navigate to load menu
    And I perform vehicle loading for all the pallets
    Then the vehicle loading should be completed and the status should be "In Progress" in order header
    And no record should exist for the Order ID
    And the inventory transaction should be generated for vehicle load
    And the order should be in "In Progress" status
    When I navigate to Trailer Shipping page
    And I enter the site id and trailer number
    And I enter the seal number
    And I proceed to complete the process
    Then the shipping manifest should be generated
    #Then the order closure should be generated in the inventory	for note "Complete --> Shipped"

    Examples: 
      | OrderId    |
      #| 5900005211 |
      |5900005300|

  @sto_despatch_international @complete @sto  @data
  Scenario Outline: STO despatch for International
    Given the STO "<OrderId>" of "INTSEA" type should contain order details and be "completely" container picked for customer "8468"
    #And the order should be "Ready to Load" status
    #And the trailer and dock scheduling should be done
    #When I get the pallet ids from the move task
    #Then the pallet id should be displayed
    #When I login as warehouse user in putty
    #And I select user directed option in main menu
    #And I navigate to load menu
    #And I perform vehicle loading for all the pallets
    #Then the vehicle loading should be completed and the status should be "Complete" in order header
    #And no record should exist for the Order ID
    #And the inventory transaction should be generated for vehicle load
    #And the order should be in "Complete" status
    #When I navigate to Trailer Shipping page
    #And I enter the site id and trailer number
    #And I enter the seal number
    #And I proceed to complete the process
    #Then the shipping manifest should be generated
    #Then the order closure should be generated in the inventory	for note "Complete --> Shipped"

    Examples: 
      | OrderId    |
      |5900005200|
      #| 9860005176 |

  @sto_despatch_multiplepallet_singletrailer @sto @complete
  Scenario: Load multiple STO with single tralier
    Given the multiple order ids should be "Allocated" status
      | OrderID    |
      | 5900005206 |
      | 5900005207 |
    And the STO should be container picked for multiple order ids
    Then the given order ids should be displayed as "Ready to Load" status
    And the trailer has been created
    When I create new dock booking for all orders
    Then the booking details should be appeared in the dock scheduler booking
    When I get the pallet ids from the move task for all orders
    Then the pallet id should be displayed
    When I login as warehouse user in putty
    And I select user directed option in main menu
    And I navigate to load menu
    And I perform vehicle loading for all the pallets
    And all the orders should be in "Complete" status in order header
    And the inventory transaction should be generated for vehicle load
    When I ship the trailer with site id, trailer number and seal number
    Then the shipping manifest should be generated
    Then the order closure should be generated in the inventory	for note "Complete --> Shipped"
