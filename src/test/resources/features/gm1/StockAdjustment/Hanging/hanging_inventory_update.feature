@hanging_inventory_update
Feature: Hanging - Inventory update
  As a warehouse user
  I want to update the stock in inventory for the required status

  @jenkinsB @hanging @stock_adjustment @inventory_update @unique_hanging_stock_adjustment_inventory_update_unlock_the_stock_from_lock @complete @ds @no_ds
  Scenario Outline: Unlock the stock from Lock
    Given I have a tag in inventory with "<LockStatus>" status for "Hanging"
    When I navigate to inventory update page
    And I select the update type as "Lock Status Change"
    And I search the inventory for the tag
    Then the tag details should be displayed
    And I select the status as "<UpdateStatus>"
    When I navigate to inventory transaction query
    When I choose the code as "<Code>" and search the tag id
    Then the status should be updated

    Examples: 
      | LockStatus | UpdateStatus | Code             |
      | Locked     | UnLocked     | Inventory Unlock |

  @jenkinsB @hanging @stock_adjustment @inventory_update @unique_hanging_stock_adjustment_inventory_update_lock_the_stock_from_unlock @complete @ds @no_ds
  Scenario Outline: Lock the stock from unlock
    Given I have a tag in inventory with "<LockStatus>" status for "Hanging"
    When I navigate to inventory update page
    And I select the update type as "Lock Status Change"
    And I search the inventory for the tag
    Then the tag details should be displayed
    And I select the status as "<UpdateStatus>"
    When I navigate to inventory transaction query
    When I choose the code as "<Code>" and search the tag id
    Then the status should be updated

    Examples: 
      | LockStatus | UpdateStatus | Code           |
      | UnLocked   | Locked       | Inventory Lock |

  @jenkinsD @hanging @stock_adjustment @inventory_update @unique_hanging_stock_adjustment_inventory_update_update_expiry_date @complete @ds @no_ds
  Scenario: Update Expiry date
    #Given I have tag in inventory with expiry "Y" status for "Hanging"
    Given I have tag in inventory with expiry "Y" status for "Hanging" and siteId "5649"
    When I navigate to inventory update page
    And I select the update type as "Expiry Date Update"
    And I search the inventory for the tag
    Then the tag details should be displayed
    And I select the future date
    When I navigate to inventory transaction query
    When I choose the code as "Expiry Update" and search the tag id
    Then the expiry date should be updated

  @jenkinsB @hanging @stock_adjustment @inventory_update @unique_hanging_stock_adjustment_inventory_update_origin_update @complete @ds @no_ds
  Scenario Outline: Origin update
    Given I have a tag in inventory with origin "<Origin>" for "Hanging"
    When I navigate to inventory update page
    And I select the update type as "Origin Update"
    And I search the inventory for the tag
    Then the tag details should be displayed
    And I select the status as "<UpdateOrigin>"
    When I navigate to inventory query page
    Then the origin should be updated

    Examples: 
      | Origin | UpdateOrigin |
      | NONE   | UK origin    |

  @jenkinsD @hanging @stock_adjustment @inventory_update @unique_hanging_stock_adjustment_inventory_update_update_condition_code @complete @ds @no_ds
  Scenario Outline: Update condition code
    Given I have a tag in inventory with condition "<Condition>" for "Hanging"
    When I navigate to inventory update page
    And I select the update type as "Condition Code Update"
    And I search the inventory for the tag
    Then the tag details should be displayed
    And I select the condition as "<UpdateCondition>"
    When I navigate to inventory transaction query
    When I choose the code as "Condition Update" and I search the tag id for condition
    Then the condition should be updated

    Examples: 
      | Condition | UpdateCondition      |
      | FIRST     | Black condition code |

  @jenkinsD @hanging @stock_adjustment @inventory_update @unique_hanging_stock_adjustment_inventory_update_pallet_type_update @complete @ds @no_ds
  Scenario Outline: Pallet type update
    Given I have a tag in inventory with pallet type as "<PalletType>" for "Hanging"
    When I navigate to inventory update page
    And I select the update type as "Pallet Type Update"
    And I search the inventory for the tag
    Then the tag details should be displayed
    And I select the pallet type as "<UpdatePallet>"
    When I navigate to inventory transaction query
    When I choose the code as "Pallet Update" and I search the tag id
    Then the pallet should be updated

    Examples: 
      | PalletType | UpdatePallet |
      | PALLET     | AIR          |

  @jenkinsB @hanging @stock_adjustment @inventory_update @unique_hanging_stock_adjustment_inventory_update_pack_config_update @complete @ds @no_ds
  Scenario: Pack config update
    Given I have a sku in inventory with more than one pack config for "Hanging"
    When I navigate to inventory update page
    And I select the update type as "Pack Configuration Update"
    And I search the inventory for the sku
    Then the sku details should be displayed
    And I update the pack config
    When I navigate to inventory transaction query
    When I choose the code as config update and I search the sku id
    Then the pack config should be updated
