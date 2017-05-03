@Receipt_inventory_transaction_query
Feature: Receipt ITL
  As a warehouse user
  I want to verify ITL generated for receipt
  So that I can ensure goods receipt details are sent to downstream systems

  @sara_wip
  Scenario: Generate goods receipt for the received ambient stock
    Given I have logged in as warehouse user in JDA dispatcher food application
    And I navigate to inventory transaction query
    When I select the code as "Receipt" and enter the tag id "2000126301"
    Then the description, from location, to location, update qty, reference and SKU should be displayed in the general tab
    When I navigate to miscellaneous tab
    Then the expiry date,user id, workstation, RDT user mode and supplier details should be displayed
    When I navigate to miscellaneous2 tab
    Then the pallet type, pack config, uploaded status, uploaded filename, uploaded date and uploaded time should be displayed
    When I navigate to customs & excise tab
    Then the original rotation id, rotation id, CE receipt type and under bond should be displayed
    When I navigate to user defined tab
    Then the storage location, base UOM, case ratio ,into destination date should be displayed
    When I navigate to settings 2 tab in the user defined tab
    Then the URN child should be displayed
    
  @sara_wip
	Scenario: Generate goods receipt for the received BWS stock
	Given I have logged in as warehouse user in JDA dispatcher food application
    And I navigate to inventory transaction query
    When I select the code as "Receipt" and enter the tag id "2000122101"
    Then the description, from location, to location, update qty, reference and SKU should be displayed in the general tab
    When I navigate to miscellaneous tab
    Then the expiry date,user id, workstation, RDT user mode and supplier details should be displayed
    When I navigate to miscellaneous2 tab
    Then the pallet type, pack config, uploaded status, uploaded filename, uploaded date and uploaded time should be displayed
    When I navigate to customs & excise tab
    Then the original rotation id, rotation id, CE receipt type and under bond should be displayed
    And the originator, originator reference, CE consignment id, document date, document time should be displayed for BWS
    When I navigate to user defined tab
    Then the storage location, base UOM, case ratio ,into destination date should be displayed
    And ABV percentage and vintage should be displayed for BWS
    When I navigate to settings 2 tab in the user defined tab
    Then the URN child should be displayed
