@Receipt_inventory_transaction_query
Feature: Receipt ITL
  As a warehouse user
  I want to verify ITL generated for receipt
  So that I can ensure goods receipt details are sent to downstream systems

  @sara_wip
  Scenario: Generate goods receipt for the received ambient stock
    Given I navigate to inventory transaction query
    When I search tag id "3201000011" with transaction code as "Receipt" and transaction date as current date
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
