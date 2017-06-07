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
      |  8050024845 | REC002   |

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
      |  7150030202 | REC002   |

  @po_putaway_bws_non_bonded @complete @po
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
      |  7450012190 | REC002   |

  @po_putaway_bws_new_vintage @complete @po
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
      |  7150011191 | REC002   |

 @po_partial_putaway_ambient @complete @po
  Scenario Outline: Putaway for partial receiving Ambient PO
    Given the pre advice id "<preAdviceId>" should be partial receiving with "Ambient", "Released", "<Location>"
    Then the status should be displayed as "In Progress"
    When I release single tag for the SKU in the move task update
    And I login as warehouse user in putty
    And I select user directed option in main menu
    And I select normal putaway
    And I do putaway for single tag
    And I should see the location zone in inventory query
    Then I should see the from location, to location, final location, uploaded status and uploaded file name

    Examples: 
      | preAdviceId | Location |
      |  8050154845 | REC002   |

@po_partial_putaway_bws @complete @po
  Scenario Outline: Putaway for partial receiving BWS PO
    Given the pre advice id "<preAdviceId>" should be partial receiving with "BWS-Non-Bonded", "Released", "<Location>"
    Then the status should be displayed as "In Progress"
    When I release single tag for the SKU in the move task update
    And I login as warehouse user in putty
    And I select user directed option in main menu
    And I select normal putaway
    And I do putaway for single tag
    And I should see the location zone in inventory query
    Then I should see the from location, to location, final location, uploaded status and uploaded file name

    Examples: 
      | preAdviceId | Location |
      |  7150061128 | REC002   |

  @complete @po @po_reserve_putaway
  Scenario Outline: Putaway at reserve location
    Given the pre advice id "<preAdviceId>" should be received with "<Category>", "<Status>", "<Location>"
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
      | preAdviceId | Category | Status   | Location |
      |  8062004512 | Ambient  | Released | REC002   |

  @complete @po @po_override_putaway
  Scenario Outline: Override pickface location during putaway process in JDA WMS
    Given the pre advice id "<preAdviceId>" should be received with "<Category>", "<Status>", "<Location>"
    And I get the pick face location
    When I release all the tags for the SKU in the move task update
    And I have logged in as warehouse user in putty
    And I select user directed option in main menu
    And I select normal putaway
    And I do putaway with pick face location for all the tags
    And I should see the location zone in inventory query
    Then I should see the from location, to location, final location, uploaded status and uploaded file name

    Examples: 
      | preAdviceId | Category | Status   | Location |
      |  8069504512 | Ambient  | Released | REC002   |
