#Author: Saravanan.Jeyabalan@msncorp.net
#KEY : ITL -Inventory Transaction
@Receipt_inventory_transaction_query
Feature: Receipt ITL
  As a warehouse user
  I want to verify ITL generated for receipt
  So that

  @sara_wip
  Scenario: validate I0808 (Goods Receipt) is generated successfully for the received stock
    Given I navigate to inventory transaction query page
    When I search tag id "" and code as ""
    Then the Description, From location, to location,Update Qty,Reference and SKU should be displayed in the general tab
    When I navigate to miscellaneous tab
    Then the expiry date, User ID, Workstation, RDT User mode and supplier details should be displayed
    When I navigate to miscellaneous2 tab
    Then the pallet type, Pack Config, Uploaded=Y, Uploaded Filename, uploaded date and uploaded time should be displayed
    When I navigate to Customs & Excise tab
    Then the pallet type,  should be displayed
    When I navigate to Customs & Excise tab
    Then original rotation ID, rotation ID, C&E Receipt Type and Under Bond should be displayed
    When I navigate to User Defined tab -Settings 1
    Then the storage location, Base UOM, Case ratio ,Into Destination Date, vintage and ABV should be displayed
    When I navigate to User Defined tab - Settings 2
    Then the URN Child value should be displayed
