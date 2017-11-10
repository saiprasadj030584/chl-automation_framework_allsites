@hanging_master_pack_config
Feature: Hanging - Master pack Config
  As a warehouse user
  I want to perform certain 
  modifications on sku

  @jenkinsB @pre_receiving @master_pack_config @hanging @hanging_pre_receiving_master_pack_config_validate_whether_pack_config_can_be_created_for_sku_s_&_amend_existing_pack_config @complete @ds @no_ds @maven_check_1 @check9
  Scenario: Validate whether pack config can be created for SKUs and Amend existing pack config
    Given the sku of type "Hanging" and not new product
    When I have logged in as warehouse user in JDA dispatcher GM application
    And I create config with TagVolume "1" and TrackingLevel "EA"
    When I navigate to pack config linking screen
    And I link SKU with configId in Pack Config Linking
    Then the pack config details should be updated in sku maintenance page
    And I unlink the pack config with the sku
    And I delete the pack config
