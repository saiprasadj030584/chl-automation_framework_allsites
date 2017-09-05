@inventory_update
Feature: Inventory update
  As a warehouse user
  I want to update the stock in inventory for the required status

  @boxed @stock_adjustment @inventory_update  @boxed_stock_adjustment_inventory_update_unlock_status_change @complete @ds
  Scenario Outline: Unlock the stock from Lock
    Given I have a tag in inventory with "<LockStatus>" status
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
      | Locked     | Unlocked     | Inventory Unlock |
      
      @boxed @stock_adjustment @inventory_update @boxed_stock_adjustment_inventory_update_lock_status_change @complete @ds
  Scenario Outline: Lock the stock from unlock
    Given I have a tag in inventory with "<LockStatus>" status
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
      | UnLocked   | Locked       | Inventory Lock   |

   @boxed @stock_adjustment @inventory_update @boxed_stock_adjustment_inventory_update_expiry_date_update @complete @ds
  Scenario: Inventory update for expiry date change
    Given I have tag in inventory with expiry "Y" status
    When I navigate to inventory update page
    And I select the update type as "Expiry Date Update"
    And I search the inventory for the tag
    Then the tag details should be displayed
    And I select the future date
    When I navigate to inventory transaction query
    When I choose the code as "Expiry Update" and search the tag id
    Then the expiry date should be updated

  @boxed @stock_adjustment @inventory_update @boxed_stock_adjustment_inventory_update_origin_update @complete @ds
  Scenario Outline: Inventory update for origin change
    Given I have a tag in inventory with origin "<Origin>"
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

  @boxed @stock_adjustment @inventory_update @boxed_stock_adjustment_inventory_update_condition_code_update @complete @ds
  Scenario Outline: Inventory update for condition code change
    Given I have a tag in inventory with condition "<Condition>"
    When I navigate to inventory update page
    And I select the update type as "Condition Code Update"
    And I search the inventory for the tag
    Then the tag details should be displayed
    And I select the condition as "<UpdateCondition>"
    When I navigate to inventory transaction query
    When I choose the code as "Condition Update" and I search the tag id
    Then the condition should be updated

    Examples: 
      | Condition | UpdateCondition      |
      | FIRST     | Black condition code |

   @boxed @stock_adjustment @inventory_update @boxed_stock_adjustment_inventory_update_pallet_type_update @complete @ds
  Scenario Outline: Inventory update for pallet type change
    Given I have a tag in inventory with pallet type as "<PalletType>"
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

   @boxed @stock_adjustment @inventory_update @boxed_stock_adjustment_inventory_update_owner_update @hold
  Scenario Outline: Inventory update for owner change
    Given I have a tag in inventory with owner as "<Owner>"
    When I navigate to inventory update page
    And I select the update type as "Owner Update"
    And I search the inventory for the tag
    Then the tag details should be displayed
    And I select the owner type as "<Updateowner>"
    When I navigate to inventory transaction query
    When I choose the code as "Owner Update" and I search the tag id
    Then the owner should be updated

    Examples: 
      | Owner | Updateowner |
      | AOWN  | M+S         |

  @boxed @stock_adjustment @inventory_update @boxed_stock_adjustment_inventory_update_pack_config_update @complete @ds
  Scenario: Inventory update for packConfig
    Given I have a sku in inventory with more than one pack config
    When I navigate to inventory update page
    And I select the update type as "Pack Configuration Update"
    And I search the inventory for the sku
    Then the sku details should be displayed
    And I update the pack config
    When I navigate to inventory transaction query
    When I choose the code as config update and I search the sku id
    Then the pack config should be updated
