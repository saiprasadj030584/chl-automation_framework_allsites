@po_putaway
Feature: Putaway for purchase order
  As a warehouse user
  I want to putaway the articles 
  So that I can complete the purchase order

  @po_putaway_ambient @complete @po 
  Scenario Outline: Putaway for Ambient PO
    Given the pre advice id "<preAdviceId>" should be received with "Ambient", "Released", "<Location>"
    When I release all the tags for the SKU in the move task update
    And I login as warehouse user in putty
    And I select user directed option in main menu
    And I select normal putaway
    And I do putaway for all the tags
    And I should see the location zone in inventory query
    Then I should see the from location, to location, final location, uploaded status and uploaded file name

    Examples: 
      | preAdviceId | Location |
      |  2058206802 | REC002   |

  @po_putaway_bws_bonded @complete @po
  Scenario Outline: Putaway for BWS-Bonded PO
    Given the pre advice id "<preAdviceId>" should be received with "BWS-Bonded", "Released", "<Location>"
    When I release all the tags for the SKU in the move task update
    And I login as warehouse user in putty
    And I select user directed option in main menu
    And I select normal putaway
    And I do putaway for all the tags
    And I should see the location zone in inventory query
    Then I should see the from location, to location, final location, uploaded status and uploaded file name

    Examples: 
      | preAdviceId | Location |
      |  2058206810 | REC002   |

  @po_putaway_bws_non_bonded @complete @po1
  Scenario Outline: Putaway for BWS-Non-Bonded PO
    Given the pre advice id "<preAdviceId>" should be received with "BWS-Non-Bonded", "Released", "<Location>"
    When I release all the tags for the SKU in the move task update
    And I login as warehouse user in putty
    And I select user directed option in main menu
    And I select normal putaway
    And I do putaway for all the tags
    And I should see the location zone in inventory query
    Then I should see the from location, to location, final location, uploaded status and uploaded file name

    Examples: 
      | preAdviceId | Location |
      |  2058206811 | REC002   |

  @po_putaway_bws_F23_bonded @complete @po @demo1
  Scenario Outline: Putaway for BWS-Non-Bonded PO
    Given the pre advice id "<preAdviceId>" should be received with "BWS-F23-Bonded", "Released", "<Location>"
    When I release all the tags for the SKU in the move task update
    And I login as warehouse user in putty
    And I select user directed option in main menu
    And I select normal putaway
    And I do putaway for all the tags
    And I should see the location zone in inventory query
    Then I should see the from location, to location, final location, uploaded status and uploaded file name

    Examples: 
      | preAdviceId | Location |
      |  2058206813 | REC002   |

  @po_putaway_bws_new_vintage @complete @po @demo1
  Scenario Outline: Putaway for BWS-New-Vintage PO
    Given the pre advice id "<preAdviceId>" should be received with "BWS-New Vintage", "Released", "<Location>"
    When I release all the tags for the SKU in the move task update
    And I login as warehouse user in putty
    And I select user directed option in main menu
    And I select normal putaway
    And I do putaway for all the tags
    And I should see the location zone in inventory query
    Then I should see the from location, to location, final location, uploaded status and uploaded file name

    Examples: 
      | preAdviceId | Location |
      |  2058206812 | REC002   |

  @po_partial_putaway_ambient @complete @po @demo
  Scenario Outline: Putaway for partial receiving Ambient PO
    Given the pre advice id "<preAdviceId>" should be partial receiving with "Ambient", "Released", "<Location>"
    Then the pre advice status should be displayed as "In Progress"
    When I release single tag for the SKU in the move task update
    And I login as warehouse user in putty
    And I select user directed option in main menu
    And I select normal putaway
    And I do putaway for single tag
    And I should see the location zone in inventory query
    Then I should see the from location, to location, final location, uploaded status and uploaded file name

    Examples: 
      | preAdviceId | Location |
      |  2058206815 | REC002   |

  @po_partial_putaway_bws @complete @po 
  Scenario Outline: Putaway for partial receiving BWS PO
    Given the pre advice id "<preAdviceId>" should be partial receiving with "BWS-Bonded", "Released", "<Location>"
    Then the pre advice status should be displayed as "In Progress"
    When I release single tag for the SKU in the move task update
    And I login as warehouse user in putty
    And I select user directed option in main menu
    And I select normal putaway
    And I do putaway for single tag
    And I should see the location zone in inventory query
    Then I should see the from location, to location, final location, uploaded status and uploaded file name

    Examples: 
      | preAdviceId | Location |
      |  2058206814 | REC002   |

  @complete @po @po_reserve_putaway
  Scenario Outline: Putaway at reserve location
    Given the pre advice id "<preAdviceId>" should be received with "Ambient", "Released", "<Location>"
    And the inventory exists for pick face location
    And I get the reserve location to putaway the stock
    When I release all the tags for the SKU in the move task update
    And I login as warehouse user in putty
    And I select user directed option in main menu
    And I select normal putaway
    And I do putaway with reserve location for all the tags
    And I should see the location zone in inventory query
    Then I should see the from location, to location, final location, uploaded status and uploaded file name for the tags

    Examples: 
      | preAdviceId | Location |
      |  2058206824 | REC002   |

  @complete @po @po_override_putaway
  Scenario Outline: Override pickface location during putaway process in JDA WMS
    Given the pre advice id "<preAdviceId>" should be received with "Ambient", "Released", "<Location>"
    And I get the pick face location
    When I release all the tags for the SKU in the move task update
    And I have logged in as warehouse user in putty
    And I select user directed option in main menu
    And I select normal putaway
    And I do putaway with pick face location for all the tags
    And I should see the location zone in inventory query
    Then I should see the from location, to location, final location, uploaded status and uploaded file name

    Examples: 
      | preAdviceId | Location |
      |  2058206823 | REC002   |
